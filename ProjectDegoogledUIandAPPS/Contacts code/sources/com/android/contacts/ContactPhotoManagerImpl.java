package com.android.contacts;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.util.BitmapUtil;
import com.android.contacts.util.PermissionsUtil;
import com.android.contacts.util.UriUtils;
import com.android.contactsbind.util.UserAgentGenerator;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ContactPhotoManager */
class ContactPhotoManagerImpl extends ContactPhotoManager implements Handler.Callback {
    private static final BitmapHolder BITMAP_UNAVAILABLE = new BitmapHolder(new byte[0], 0);
    /* access modifiers changed from: private */
    public static final String[] COLUMNS = {"_id", "data15"};
    /* access modifiers changed from: private */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static int mThumbnailSize;
    private final LruCache<Object, Bitmap> mBitmapCache;
    /* access modifiers changed from: private */
    public final LruCache<Object, BitmapHolder> mBitmapHolderCache;
    private volatile boolean mBitmapHolderCacheAllUnfresh = true;
    /* access modifiers changed from: private */
    public final int mBitmapHolderCacheRedZoneBytes;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final AtomicInteger mFreshCacheOverwrite = new AtomicInteger();
    private LoaderThread mLoaderThread;
    private boolean mLoadingRequested;
    /* access modifiers changed from: private */
    public final Handler mMainThreadHandler = new Handler(this);
    private boolean mPaused;
    private final ConcurrentHashMap<ImageView, Request> mPendingRequests = new ConcurrentHashMap<>();
    private final AtomicInteger mStaleCacheOverwrite = new AtomicInteger();
    /* access modifiers changed from: private */
    public String mUserAgent;

    static {
        BITMAP_UNAVAILABLE.bitmapRef = new SoftReference((Object) null);
    }

    /* compiled from: ContactPhotoManager */
    private static class BitmapHolder {
        Bitmap bitmap;
        Reference<Bitmap> bitmapRef;
        final byte[] bytes;
        int decodedSampleSize;
        volatile boolean fresh = true;
        final int originalSmallerExtent;

        public BitmapHolder(byte[] bArr, int i) {
            this.bytes = bArr;
            this.originalSmallerExtent = i;
        }
    }

    public ContactPhotoManagerImpl(Context context) {
        this.mContext = context;
        float f = ((ActivityManager) context.getSystemService("activity")).isLowRamDevice() ? 0.5f : 1.0f;
        this.mBitmapCache = new LruCache<Object, Bitmap>((int) (1769472.0f * f)) {
            /* access modifiers changed from: protected */
            public void entryRemoved(boolean z, Object obj, Bitmap bitmap, Bitmap bitmap2) {
            }

            /* access modifiers changed from: protected */
            public int sizeOf(Object obj, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        int i = (int) (2000000.0f * f);
        this.mBitmapHolderCache = new LruCache<Object, BitmapHolder>(i) {
            /* access modifiers changed from: protected */
            public void entryRemoved(boolean z, Object obj, BitmapHolder bitmapHolder, BitmapHolder bitmapHolder2) {
            }

            /* access modifiers changed from: protected */
            public int sizeOf(Object obj, BitmapHolder bitmapHolder) {
                byte[] bArr = bitmapHolder.bytes;
                if (bArr != null) {
                    return bArr.length;
                }
                return 0;
            }
        };
        this.mBitmapHolderCacheRedZoneBytes = (int) (((double) i) * 0.75d);
        Log.i("ContactPhotoManager", "Cache adj: " + f);
        mThumbnailSize = context.getResources().getDimensionPixelSize(R.dimen.contact_browser_list_item_photo_size);
        this.mUserAgent = UserAgentGenerator.getUserAgent(context);
        if (this.mUserAgent == null) {
            this.mUserAgent = "";
        }
    }

    public void onTrimMemory(int i) {
        if (i >= 60) {
            clear();
        }
    }

    public void preloadPhotosInBackground() {
        ensureLoaderThread();
        this.mLoaderThread.requestPreloading();
    }

    public void loadThumbnail(ImageView imageView, long j, boolean z, boolean z2, ContactPhotoManager.DefaultImageRequest defaultImageRequest, ContactPhotoManager.DefaultImageProvider defaultImageProvider) {
        if (j == 0) {
            defaultImageProvider.applyDefaultImage(imageView, -1, z, defaultImageRequest);
            this.mPendingRequests.remove(imageView);
            return;
        }
        loadPhotoByIdOrUri(imageView, Request.createFromThumbnailId(j, z, z2, defaultImageProvider, defaultImageRequest));
    }

    public void loadPhoto(ImageView imageView, Uri uri, int i, boolean z, boolean z2, ContactPhotoManager.DefaultImageRequest defaultImageRequest, ContactPhotoManager.DefaultImageProvider defaultImageProvider) {
        if (uri == null) {
            defaultImageProvider.applyDefaultImage(imageView, i, z, defaultImageRequest);
            this.mPendingRequests.remove(imageView);
        } else if (isDefaultImageUri(uri)) {
            createAndApplyDefaultImageForUri(imageView, uri, i, z, z2, defaultImageProvider);
        } else {
            loadPhotoByIdOrUri(imageView, Request.createFromUri(uri, i, z, z2, defaultImageProvider, defaultImageRequest));
        }
    }

    private void createAndApplyDefaultImageForUri(ImageView imageView, Uri uri, int i, boolean z, boolean z2, ContactPhotoManager.DefaultImageProvider defaultImageProvider) {
        ContactPhotoManager.DefaultImageRequest defaultImageRequestFromUri = ContactPhotoManager.getDefaultImageRequestFromUri(uri);
        defaultImageRequestFromUri.isCircular = z2;
        defaultImageProvider.applyDefaultImage(imageView, i, z, defaultImageRequestFromUri);
    }

    private void loadPhotoByIdOrUri(ImageView imageView, Request request) {
        if (loadCachedPhoto(imageView, request, false)) {
            this.mPendingRequests.remove(imageView);
            return;
        }
        this.mPendingRequests.put(imageView, request);
        if (!this.mPaused) {
            requestLoading();
        }
    }

    public void removePhoto(ImageView imageView) {
        imageView.setImageDrawable((Drawable) null);
        this.mPendingRequests.remove(imageView);
    }

    public void cancelPendingRequests(View view) {
        if (view == null) {
            this.mPendingRequests.clear();
            return;
        }
        Iterator<Map.Entry<ImageView, Request>> it = this.mPendingRequests.entrySet().iterator();
        while (it.hasNext()) {
            ImageView imageView = (ImageView) it.next().getKey();
            if (imageView.getParent() == null || isChildView(view, imageView)) {
                it.remove();
            }
        }
    }

    private static boolean isChildView(View view, View view2) {
        return view2.getParent() != null && (view2.getParent() == view || ((view2.getParent() instanceof ViewGroup) && isChildView(view, (ViewGroup) view2.getParent())));
    }

    public void refreshCache() {
        if (!this.mBitmapHolderCacheAllUnfresh) {
            this.mBitmapHolderCacheAllUnfresh = true;
            for (BitmapHolder next : this.mBitmapHolderCache.snapshot().values()) {
                if (next != BITMAP_UNAVAILABLE) {
                    next.fresh = false;
                }
            }
        }
    }

    private boolean loadCachedPhoto(ImageView imageView, Request request, boolean z) {
        BitmapHolder bitmapHolder = this.mBitmapHolderCache.get(request.getKey());
        if (bitmapHolder == null) {
            request.applyDefaultImage(imageView, request.mIsCircular);
            return false;
        }
        byte[] bArr = bitmapHolder.bytes;
        if (bArr == null || bArr.length == 0) {
            request.applyDefaultImage(imageView, request.mIsCircular);
            return bitmapHolder.fresh;
        }
        Reference<Bitmap> reference = bitmapHolder.bitmapRef;
        Bitmap bitmap = reference == null ? null : reference.get();
        if (bitmap == null) {
            if (bitmapHolder.bytes.length < 8192) {
                inflateBitmap(bitmapHolder, request.getRequestedExtent());
                bitmap = bitmapHolder.bitmap;
                if (bitmap == null) {
                    return false;
                }
            } else {
                request.applyDefaultImage(imageView, request.mIsCircular);
                return false;
            }
        }
        Drawable drawable = imageView.getDrawable();
        if (!z || drawable == null) {
            imageView.setImageDrawable(getDrawableForBitmap(this.mContext.getResources(), bitmap, request));
        } else {
            Drawable[] drawableArr = new Drawable[2];
            if (drawable instanceof TransitionDrawable) {
                TransitionDrawable transitionDrawable = (TransitionDrawable) drawable;
                drawableArr[0] = transitionDrawable.getDrawable(transitionDrawable.getNumberOfLayers() - 1);
            } else {
                drawableArr[0] = drawable;
            }
            drawableArr[1] = getDrawableForBitmap(this.mContext.getResources(), bitmap, request);
            TransitionDrawable transitionDrawable2 = new TransitionDrawable(drawableArr);
            imageView.setImageDrawable(transitionDrawable2);
            transitionDrawable2.startTransition(200);
        }
        if (bitmap.getByteCount() < this.mBitmapCache.maxSize() / 6) {
            this.mBitmapCache.put(request.getKey(), bitmap);
        }
        bitmapHolder.bitmap = null;
        return bitmapHolder.fresh;
    }

    private Drawable getDrawableForBitmap(Resources resources, Bitmap bitmap, Request request) {
        if (!request.mIsCircular) {
            return new BitmapDrawable(resources, bitmap);
        }
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(resources, bitmap);
        create.setAntiAlias(true);
        create.setCornerRadius((float) (bitmap.getHeight() / 2));
        return create;
    }

    private static void inflateBitmap(BitmapHolder bitmapHolder, int i) {
        Reference<Bitmap> reference;
        int findOptimalSampleSize = BitmapUtil.findOptimalSampleSize(bitmapHolder.originalSmallerExtent, i);
        byte[] bArr = bitmapHolder.bytes;
        if (bArr != null && bArr.length != 0) {
            if (findOptimalSampleSize == bitmapHolder.decodedSampleSize && (reference = bitmapHolder.bitmapRef) != null) {
                bitmapHolder.bitmap = reference.get();
                if (bitmapHolder.bitmap != null) {
                    return;
                }
            }
            try {
                Bitmap decodeBitmapFromBytes = BitmapUtil.decodeBitmapFromBytes(bArr, findOptimalSampleSize);
                int height = decodeBitmapFromBytes.getHeight();
                int width = decodeBitmapFromBytes.getWidth();
                if (height != width && Math.min(height, width) <= mThumbnailSize * 2) {
                    int min = Math.min(height, width);
                    decodeBitmapFromBytes = ThumbnailUtils.extractThumbnail(decodeBitmapFromBytes, min, min);
                }
                bitmapHolder.decodedSampleSize = findOptimalSampleSize;
                bitmapHolder.bitmap = decodeBitmapFromBytes;
                bitmapHolder.bitmapRef = new SoftReference(decodeBitmapFromBytes);
            } catch (OutOfMemoryError unused) {
            }
        }
    }

    public void clear() {
        this.mPendingRequests.clear();
        this.mBitmapHolderCache.evictAll();
        this.mBitmapCache.evictAll();
    }

    public void pause() {
        this.mPaused = true;
    }

    public void resume() {
        this.mPaused = false;
        if (!this.mPendingRequests.isEmpty()) {
            requestLoading();
        }
    }

    private void requestLoading() {
        if (!this.mLoadingRequested) {
            this.mLoadingRequested = true;
            this.mMainThreadHandler.sendEmptyMessage(1);
        }
    }

    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.mLoadingRequested = false;
            if (!this.mPaused) {
                ensureLoaderThread();
                this.mLoaderThread.requestLoading();
            }
            return true;
        } else if (i != 2) {
            return false;
        } else {
            if (!this.mPaused) {
                processLoadedImages();
            }
            return true;
        }
    }

    public void ensureLoaderThread() {
        if (this.mLoaderThread == null) {
            this.mLoaderThread = new LoaderThread(this.mContext.getContentResolver());
            this.mLoaderThread.start();
        }
    }

    private void processLoadedImages() {
        Iterator<Map.Entry<ImageView, Request>> it = this.mPendingRequests.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (loadCachedPhoto((ImageView) next.getKey(), (Request) next.getValue(), false)) {
                it.remove();
            }
        }
        softenCache();
        if (!this.mPendingRequests.isEmpty()) {
            requestLoading();
        }
    }

    private void softenCache() {
        for (BitmapHolder bitmapHolder : this.mBitmapHolderCache.snapshot().values()) {
            bitmapHolder.bitmap = null;
        }
    }

    /* access modifiers changed from: private */
    public void cacheBitmap(Object obj, byte[] bArr, boolean z, int i) {
        int i2;
        if (bArr == null) {
            i2 = -1;
        } else {
            i2 = BitmapUtil.getSmallerExtentFromBytes(bArr);
        }
        BitmapHolder bitmapHolder = new BitmapHolder(bArr, i2);
        if (!z) {
            inflateBitmap(bitmapHolder, i);
        }
        if (bArr != null) {
            this.mBitmapHolderCache.put(obj, bitmapHolder);
            if (this.mBitmapHolderCache.get(obj) != bitmapHolder) {
                Log.w("ContactPhotoManager", "Bitmap too big to fit in cache.");
                this.mBitmapHolderCache.put(obj, BITMAP_UNAVAILABLE);
            }
        } else {
            this.mBitmapHolderCache.put(obj, BITMAP_UNAVAILABLE);
        }
        this.mBitmapHolderCacheAllUnfresh = false;
    }

    public void cacheBitmap(Uri uri, Bitmap bitmap, byte[] bArr) {
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Request createFromUri = Request.createFromUri(uri, min, false, false, ContactPhotoManager.DEFAULT_AVATAR);
        BitmapHolder bitmapHolder = new BitmapHolder(bArr, min);
        bitmapHolder.bitmapRef = new SoftReference(bitmap);
        this.mBitmapHolderCache.put(createFromUri.getKey(), bitmapHolder);
        this.mBitmapHolderCacheAllUnfresh = false;
        this.mBitmapCache.put(createFromUri.getKey(), bitmap);
    }

    /* access modifiers changed from: private */
    public void obtainPhotoIdsAndUrisToLoad(Set<Long> set, Set<String> set2, Set<Request> set3) {
        Reference<Bitmap> reference;
        set.clear();
        set2.clear();
        set3.clear();
        boolean z = false;
        for (Request next : this.mPendingRequests.values()) {
            BitmapHolder bitmapHolder = this.mBitmapHolderCache.get(next.getKey());
            if (bitmapHolder != BITMAP_UNAVAILABLE) {
                if (bitmapHolder != null && bitmapHolder.bytes != null && bitmapHolder.fresh && ((reference = bitmapHolder.bitmapRef) == null || reference.get() == null)) {
                    inflateBitmap(bitmapHolder, next.getRequestedExtent());
                    z = true;
                } else if (bitmapHolder == null || !bitmapHolder.fresh) {
                    if (next.isUriRequest()) {
                        set3.add(next);
                    } else {
                        set.add(Long.valueOf(next.getId()));
                        set2.add(String.valueOf(next.mId));
                    }
                }
            }
        }
        if (z) {
            this.mMainThreadHandler.sendEmptyMessage(2);
        }
    }

    /* compiled from: ContactPhotoManager */
    private class LoaderThread extends HandlerThread implements Handler.Callback {
        private byte[] mBuffer;
        private Handler mLoaderThreadHandler;
        private final Set<Long> mPhotoIds = Sets.newHashSet();
        private final Set<String> mPhotoIdsAsStrings = Sets.newHashSet();
        private final Set<Request> mPhotoUris = Sets.newHashSet();
        private final List<Long> mPreloadPhotoIds = Lists.newArrayList();
        private int mPreloadStatus = 0;
        private final ContentResolver mResolver;
        private final StringBuilder mStringBuilder = new StringBuilder();

        public LoaderThread(ContentResolver contentResolver) {
            super("ContactPhotoLoader");
            this.mResolver = contentResolver;
        }

        public void ensureHandler() {
            if (this.mLoaderThreadHandler == null) {
                this.mLoaderThreadHandler = new Handler(getLooper(), this);
            }
        }

        public void requestPreloading() {
            if (this.mPreloadStatus != 2) {
                ensureHandler();
                if (!this.mLoaderThreadHandler.hasMessages(1)) {
                    this.mLoaderThreadHandler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        }

        public void requestLoading() {
            ensureHandler();
            this.mLoaderThreadHandler.removeMessages(0);
            this.mLoaderThreadHandler.sendEmptyMessage(1);
        }

        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                preloadPhotosInBackground();
            } else if (i == 1) {
                loadPhotosInBackground();
            }
            return true;
        }

        private void preloadPhotosInBackground() {
            int i = this.mPreloadStatus;
            if (i != 2) {
                if (i == 0) {
                    queryPhotosForPreload();
                    if (this.mPreloadPhotoIds.isEmpty()) {
                        this.mPreloadStatus = 2;
                    } else {
                        this.mPreloadStatus = 1;
                    }
                    requestPreloading();
                } else if (ContactPhotoManagerImpl.this.mBitmapHolderCache.size() > ContactPhotoManagerImpl.this.mBitmapHolderCacheRedZoneBytes) {
                    this.mPreloadStatus = 2;
                } else {
                    this.mPhotoIds.clear();
                    this.mPhotoIdsAsStrings.clear();
                    int i2 = 0;
                    int size = this.mPreloadPhotoIds.size();
                    while (size > 0 && this.mPhotoIds.size() < 25) {
                        size--;
                        i2++;
                        Long l = this.mPreloadPhotoIds.get(size);
                        this.mPhotoIds.add(l);
                        this.mPhotoIdsAsStrings.add(l.toString());
                        this.mPreloadPhotoIds.remove(size);
                    }
                    loadThumbnails(true);
                    if (size == 0) {
                        this.mPreloadStatus = 2;
                    }
                    if (Log.isLoggable("ContactPhotoManager", 2)) {
                        Log.v("ContactPhotoManager", "Preloaded " + i2 + " photos.  Cached bytes: " + ContactPhotoManagerImpl.this.mBitmapHolderCache.size());
                    }
                    requestPreloading();
                }
            }
        }

        private void queryPhotosForPreload() {
            Cursor cursor = null;
            try {
                cursor = this.mResolver.query(ContactsContract.Contacts.CONTENT_URI.buildUpon().appendQueryParameter("directory", String.valueOf(0)).appendQueryParameter("limit", String.valueOf(100)).build(), new String[]{"photo_id"}, "photo_id NOT NULL AND photo_id!=0", (String[]) null, "starred DESC, last_time_contacted DESC");
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        this.mPreloadPhotoIds.add(0, Long.valueOf(cursor.getLong(0)));
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        private void loadPhotosInBackground() {
            if (PermissionsUtil.hasPermission(ContactPhotoManagerImpl.this.mContext, PermissionsUtil.CONTACTS)) {
                ContactPhotoManagerImpl.this.obtainPhotoIdsAndUrisToLoad(this.mPhotoIds, this.mPhotoIdsAsStrings, this.mPhotoUris);
                loadThumbnails(false);
                loadUriBasedPhotos();
                requestPreloading();
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:53:0x0108  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0112  */
        /* JADX WARNING: Removed duplicated region for block: B:65:0x0129  */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x00b6 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void loadThumbnails(boolean r15) {
            /*
                r14 = this;
                java.util.Set<java.lang.Long> r0 = r14.mPhotoIds
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L_0x0009
                return
            L_0x0009:
                r0 = 2
                r1 = 1
                if (r15 != 0) goto L_0x0033
                int r2 = r14.mPreloadStatus
                if (r2 != r1) goto L_0x0033
                java.util.Set<java.lang.Long> r2 = r14.mPhotoIds
                java.util.Iterator r2 = r2.iterator()
            L_0x0017:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0029
                java.lang.Object r3 = r2.next()
                java.lang.Long r3 = (java.lang.Long) r3
                java.util.List<java.lang.Long> r4 = r14.mPreloadPhotoIds
                r4.remove(r3)
                goto L_0x0017
            L_0x0029:
                java.util.List<java.lang.Long> r2 = r14.mPreloadPhotoIds
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L_0x0033
                r14.mPreloadStatus = r0
            L_0x0033:
                java.lang.StringBuilder r2 = r14.mStringBuilder
                r3 = 0
                r2.setLength(r3)
                java.lang.StringBuilder r2 = r14.mStringBuilder
                java.lang.String r4 = "_id IN("
                r2.append(r4)
                r2 = 0
            L_0x0041:
                java.util.Set<java.lang.Long> r4 = r14.mPhotoIds
                int r4 = r4.size()
                if (r2 >= r4) goto L_0x005c
                if (r2 == 0) goto L_0x0052
                java.lang.StringBuilder r4 = r14.mStringBuilder
                r5 = 44
                r4.append(r5)
            L_0x0052:
                java.lang.StringBuilder r4 = r14.mStringBuilder
                r5 = 63
                r4.append(r5)
                int r2 = r2 + 1
                goto L_0x0041
            L_0x005c:
                java.lang.StringBuilder r2 = r14.mStringBuilder
                r4 = 41
                r2.append(r4)
                r2 = 0
                android.content.ContentResolver r4 = r14.mResolver     // Catch:{ all -> 0x0126 }
                android.net.Uri r5 = android.provider.ContactsContract.Data.CONTENT_URI     // Catch:{ all -> 0x0126 }
                java.lang.String[] r6 = com.android.contacts.ContactPhotoManagerImpl.COLUMNS     // Catch:{ all -> 0x0126 }
                java.lang.StringBuilder r7 = r14.mStringBuilder     // Catch:{ all -> 0x0126 }
                java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0126 }
                java.util.Set<java.lang.String> r8 = r14.mPhotoIdsAsStrings     // Catch:{ all -> 0x0126 }
                java.lang.String[] r9 = com.android.contacts.ContactPhotoManagerImpl.EMPTY_STRING_ARRAY     // Catch:{ all -> 0x0126 }
                java.lang.Object[] r8 = r8.toArray(r9)     // Catch:{ all -> 0x0126 }
                java.lang.String[] r8 = (java.lang.String[]) r8     // Catch:{ all -> 0x0126 }
                r9 = 0
                android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0126 }
                r5 = -1
                if (r4 == 0) goto L_0x00ab
            L_0x0086:
                boolean r6 = r4.moveToNext()     // Catch:{ all -> 0x00a7 }
                if (r6 == 0) goto L_0x00ab
                long r6 = r4.getLong(r3)     // Catch:{ all -> 0x00a7 }
                java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x00a7 }
                byte[] r7 = r4.getBlob(r1)     // Catch:{ all -> 0x00a7 }
                if (r7 != 0) goto L_0x009c
                byte[] r7 = new byte[r3]     // Catch:{ all -> 0x00a7 }
            L_0x009c:
                com.android.contacts.ContactPhotoManagerImpl r8 = com.android.contacts.ContactPhotoManagerImpl.this     // Catch:{ all -> 0x00a7 }
                r8.cacheBitmap(r6, r7, r15, r5)     // Catch:{ all -> 0x00a7 }
                java.util.Set<java.lang.Long> r7 = r14.mPhotoIds     // Catch:{ all -> 0x00a7 }
                r7.remove(r6)     // Catch:{ all -> 0x00a7 }
                goto L_0x0086
            L_0x00a7:
                r15 = move-exception
                r2 = r4
                goto L_0x0127
            L_0x00ab:
                if (r4 == 0) goto L_0x00b0
                r4.close()
            L_0x00b0:
                java.util.Set<java.lang.Long> r4 = r14.mPhotoIds
                java.util.Iterator r4 = r4.iterator()
            L_0x00b6:
                boolean r6 = r4.hasNext()
                if (r6 == 0) goto L_0x011c
                java.lang.Object r6 = r4.next()
                java.lang.Long r6 = (java.lang.Long) r6
                long r7 = r6.longValue()
                boolean r7 = android.provider.ContactsContract.isProfileId(r7)
                if (r7 == 0) goto L_0x0116
                android.content.ContentResolver r8 = r14.mResolver     // Catch:{ all -> 0x010e }
                android.net.Uri r7 = android.provider.ContactsContract.Data.CONTENT_URI     // Catch:{ all -> 0x010e }
                long r9 = r6.longValue()     // Catch:{ all -> 0x010e }
                android.net.Uri r9 = android.content.ContentUris.withAppendedId(r7, r9)     // Catch:{ all -> 0x010e }
                java.lang.String[] r10 = com.android.contacts.ContactPhotoManagerImpl.COLUMNS     // Catch:{ all -> 0x010e }
                r11 = 0
                r12 = 0
                r13 = 0
                android.database.Cursor r7 = r8.query(r9, r10, r11, r12, r13)     // Catch:{ all -> 0x010e }
                if (r7 == 0) goto L_0x0101
                boolean r8 = r7.moveToFirst()     // Catch:{ all -> 0x010c }
                if (r8 == 0) goto L_0x0101
                byte[] r6 = r7.getBlob(r1)     // Catch:{ all -> 0x010c }
                if (r6 != 0) goto L_0x00f3
                byte[] r6 = new byte[r3]     // Catch:{ all -> 0x010c }
            L_0x00f3:
                com.android.contacts.ContactPhotoManagerImpl r8 = com.android.contacts.ContactPhotoManagerImpl.this     // Catch:{ all -> 0x010c }
                long r9 = r7.getLong(r3)     // Catch:{ all -> 0x010c }
                java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x010c }
                r8.cacheBitmap(r9, r6, r15, r5)     // Catch:{ all -> 0x010c }
                goto L_0x0106
            L_0x0101:
                com.android.contacts.ContactPhotoManagerImpl r8 = com.android.contacts.ContactPhotoManagerImpl.this     // Catch:{ all -> 0x010c }
                r8.cacheBitmap(r6, r2, r15, r5)     // Catch:{ all -> 0x010c }
            L_0x0106:
                if (r7 == 0) goto L_0x00b6
                r7.close()
                goto L_0x00b6
            L_0x010c:
                r15 = move-exception
                goto L_0x0110
            L_0x010e:
                r15 = move-exception
                r7 = r2
            L_0x0110:
                if (r7 == 0) goto L_0x0115
                r7.close()
            L_0x0115:
                throw r15
            L_0x0116:
                com.android.contacts.ContactPhotoManagerImpl r7 = com.android.contacts.ContactPhotoManagerImpl.this
                r7.cacheBitmap(r6, r2, r15, r5)
                goto L_0x00b6
            L_0x011c:
                com.android.contacts.ContactPhotoManagerImpl r15 = com.android.contacts.ContactPhotoManagerImpl.this
                android.os.Handler r15 = r15.mMainThreadHandler
                r15.sendEmptyMessage(r0)
                return
            L_0x0126:
                r15 = move-exception
            L_0x0127:
                if (r2 == 0) goto L_0x012c
                r2.close()
            L_0x012c:
                throw r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.ContactPhotoManagerImpl.LoaderThread.loadThumbnails(boolean):void");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(2:19|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            r9.disconnect();
            r9 = null;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0076 */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x007f A[Catch:{ all -> 0x00ae, Exception | OutOfMemoryError -> 0x00d6 }] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00b3 A[Catch:{ all -> 0x00ae, Exception | OutOfMemoryError -> 0x00d6 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void loadUriBasedPhotos() {
            /*
                r13 = this;
                java.lang.String r0 = "Cannot load photo "
                java.lang.String r1 = "ContactPhotoManager"
                java.util.Set<com.android.contacts.ContactPhotoManagerImpl$Request> r2 = r13.mPhotoUris
                java.util.Iterator r2 = r2.iterator()
            L_0x000a:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x00fc
                java.lang.Object r3 = r2.next()
                com.android.contacts.ContactPhotoManagerImpl$Request r3 = (com.android.contacts.ContactPhotoManagerImpl.Request) r3
                android.net.Uri r4 = r3.getUri()
                android.net.Uri r5 = com.android.contacts.ContactPhotoManager.removeContactType(r4)
                byte[] r6 = r13.mBuffer
                if (r6 != 0) goto L_0x0028
                r6 = 16384(0x4000, float:2.2959E-41)
                byte[] r6 = new byte[r6]
                r13.mBuffer = r6
            L_0x0028:
                r6 = 0
                r7 = 2
                r8 = 0
                java.lang.String r9 = r5.getScheme()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                java.lang.String r10 = "http"
                boolean r10 = r9.equals(r10)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                if (r10 != 0) goto L_0x0047
                java.lang.String r10 = "https"
                boolean r9 = r9.equals(r10)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                if (r9 == 0) goto L_0x0040
                goto L_0x0047
            L_0x0040:
                android.content.ContentResolver r9 = r13.mResolver     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                java.io.InputStream r9 = r9.openInputStream(r5)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                goto L_0x007d
            L_0x0047:
                r9 = 1
                android.net.TrafficStats.setThreadStatsTag(r9)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                java.net.URL r9 = new java.net.URL     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                java.lang.String r10 = r5.toString()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r9.<init>(r10)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                java.net.URLConnection r9 = r9.openConnection()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                com.android.contacts.ContactPhotoManagerImpl r10 = com.android.contacts.ContactPhotoManagerImpl.this     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                java.lang.String r10 = r10.mUserAgent     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                if (r10 != 0) goto L_0x0071
                java.lang.String r10 = "User-Agent"
                com.android.contacts.ContactPhotoManagerImpl r11 = com.android.contacts.ContactPhotoManagerImpl.this     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                java.lang.String r11 = r11.mUserAgent     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r9.setRequestProperty(r10, r11)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
            L_0x0071:
                java.io.InputStream r9 = r9.getInputStream()     // Catch:{ IOException -> 0x0076 }
                goto L_0x007a
            L_0x0076:
                r9.disconnect()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r9 = r6
            L_0x007a:
                android.net.TrafficStats.clearThreadStatsTag()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
            L_0x007d:
                if (r9 == 0) goto L_0x00b3
                java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r10.<init>()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
            L_0x0084:
                byte[] r11 = r13.mBuffer     // Catch:{ all -> 0x00ae }
                int r11 = r9.read(r11)     // Catch:{ all -> 0x00ae }
                r12 = -1
                if (r11 == r12) goto L_0x0093
                byte[] r12 = r13.mBuffer     // Catch:{ all -> 0x00ae }
                r10.write(r12, r8, r11)     // Catch:{ all -> 0x00ae }
                goto L_0x0084
            L_0x0093:
                r9.close()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                com.android.contacts.ContactPhotoManagerImpl r9 = com.android.contacts.ContactPhotoManagerImpl.this     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                byte[] r10 = r10.toByteArray()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                int r11 = r3.getRequestedExtent()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r9.cacheBitmap(r4, r10, r8, r11)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                com.android.contacts.ContactPhotoManagerImpl r9 = com.android.contacts.ContactPhotoManagerImpl.this     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                android.os.Handler r9 = r9.mMainThreadHandler     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r9.sendEmptyMessage(r7)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                goto L_0x000a
            L_0x00ae:
                r10 = move-exception
                r9.close()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                throw r10     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
            L_0x00b3:
                boolean r9 = android.util.Log.isLoggable(r1, r7)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                if (r9 == 0) goto L_0x00cb
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r9.<init>()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r9.append(r0)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r9.append(r5)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                android.util.Log.v(r1, r9)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
            L_0x00cb:
                com.android.contacts.ContactPhotoManagerImpl r9 = com.android.contacts.ContactPhotoManagerImpl.this     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                int r10 = r3.getRequestedExtent()     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                r9.cacheBitmap(r4, r6, r8, r10)     // Catch:{ Exception -> 0x00d8, OutOfMemoryError -> 0x00d6 }
                goto L_0x000a
            L_0x00d6:
                r9 = move-exception
                goto L_0x00d9
            L_0x00d8:
                r9 = move-exception
            L_0x00d9:
                boolean r7 = android.util.Log.isLoggable(r1, r7)
                if (r7 == 0) goto L_0x00f1
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                r7.append(r0)
                r7.append(r5)
                java.lang.String r5 = r7.toString()
                android.util.Log.v(r1, r5, r9)
            L_0x00f1:
                com.android.contacts.ContactPhotoManagerImpl r5 = com.android.contacts.ContactPhotoManagerImpl.this
                int r3 = r3.getRequestedExtent()
                r5.cacheBitmap(r4, r6, r8, r3)
                goto L_0x000a
            L_0x00fc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.ContactPhotoManagerImpl.LoaderThread.loadUriBasedPhotos():void");
        }
    }

    /* compiled from: ContactPhotoManager */
    private static final class Request {
        private final boolean mDarkTheme;
        private final ContactPhotoManager.DefaultImageProvider mDefaultProvider;
        private final ContactPhotoManager.DefaultImageRequest mDefaultRequest;
        /* access modifiers changed from: private */
        public final long mId;
        /* access modifiers changed from: private */
        public final boolean mIsCircular;
        private final int mRequestedExtent;
        private final Uri mUri;

        private Request(long j, Uri uri, int i, boolean z, boolean z2, ContactPhotoManager.DefaultImageProvider defaultImageProvider, ContactPhotoManager.DefaultImageRequest defaultImageRequest) {
            this.mId = j;
            this.mUri = uri;
            this.mDarkTheme = z;
            this.mIsCircular = z2;
            this.mRequestedExtent = i;
            this.mDefaultProvider = defaultImageProvider;
            this.mDefaultRequest = defaultImageRequest;
        }

        public static Request createFromThumbnailId(long j, boolean z, boolean z2, ContactPhotoManager.DefaultImageProvider defaultImageProvider, ContactPhotoManager.DefaultImageRequest defaultImageRequest) {
            return new Request(j, (Uri) null, -1, z, z2, defaultImageProvider, defaultImageRequest);
        }

        public static Request createFromUri(Uri uri, int i, boolean z, boolean z2, ContactPhotoManager.DefaultImageProvider defaultImageProvider) {
            return createFromUri(uri, i, z, z2, defaultImageProvider, (ContactPhotoManager.DefaultImageRequest) null);
        }

        public static Request createFromUri(Uri uri, int i, boolean z, boolean z2, ContactPhotoManager.DefaultImageProvider defaultImageProvider, ContactPhotoManager.DefaultImageRequest defaultImageRequest) {
            return new Request(0, uri, i, z, z2, defaultImageProvider, defaultImageRequest);
        }

        public boolean isUriRequest() {
            return this.mUri != null;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public long getId() {
            return this.mId;
        }

        public int getRequestedExtent() {
            return this.mRequestedExtent;
        }

        public int hashCode() {
            long j = this.mId;
            int i = (((((int) (j ^ (j >>> 32))) + 31) * 31) + this.mRequestedExtent) * 31;
            Uri uri = this.mUri;
            return i + (uri == null ? 0 : uri.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Request.class != obj.getClass()) {
                return false;
            }
            Request request = (Request) obj;
            return this.mId == request.mId && this.mRequestedExtent == request.mRequestedExtent && UriUtils.areEqual(this.mUri, request.mUri);
        }

        public Object getKey() {
            Uri uri = this.mUri;
            return uri == null ? Long.valueOf(this.mId) : uri;
        }

        public void applyDefaultImage(ImageView imageView, boolean z) {
            ContactPhotoManager.DefaultImageRequest defaultImageRequest;
            ContactPhotoManager.DefaultImageRequest defaultImageRequest2 = this.mDefaultRequest;
            if (defaultImageRequest2 == null) {
                if (z) {
                    if (ContactPhotoManager.isBusinessContactUri(this.mUri)) {
                        defaultImageRequest = ContactPhotoManager.DefaultImageRequest.EMPTY_CIRCULAR_BUSINESS_IMAGE_REQUEST;
                    } else {
                        defaultImageRequest = ContactPhotoManager.DefaultImageRequest.EMPTY_CIRCULAR_DEFAULT_IMAGE_REQUEST;
                    }
                } else if (ContactPhotoManager.isBusinessContactUri(this.mUri)) {
                    defaultImageRequest = ContactPhotoManager.DefaultImageRequest.EMPTY_DEFAULT_BUSINESS_IMAGE_REQUEST;
                } else {
                    defaultImageRequest = ContactPhotoManager.DefaultImageRequest.EMPTY_DEFAULT_IMAGE_REQUEST;
                }
                defaultImageRequest2 = defaultImageRequest;
            }
            this.mDefaultProvider.applyDefaultImage(imageView, this.mRequestedExtent, this.mDarkTheme, defaultImageRequest2);
        }
    }
}
