package com.android.dialer.contactphoto;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.constants.Constants;
import com.android.dialer.contactphoto.ContactPhotoManager;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class ContactPhotoManagerImpl extends ContactPhotoManager implements Handler.Callback {
    private static final BitmapHolder BITMAP_UNAVAILABLE = new BitmapHolder(new byte[0], 0);
    /* access modifiers changed from: private */
    public static final String[] COLUMNS = {"_id", "data15"};
    /* access modifiers changed from: private */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static int thumbnailSize;
    private final LruCache<Object, Bitmap> bitmapCache;
    /* access modifiers changed from: private */
    public final LruCache<Object, BitmapHolder> bitmapHolderCache;
    /* access modifiers changed from: private */
    public final int bitmapHolderCacheRedZoneBytes;
    /* access modifiers changed from: private */
    public final Context context;
    private LoaderThread loaderThread;
    private boolean loadingRequested;
    /* access modifiers changed from: private */
    public final Handler mainThreadHandler = new Handler(this);
    private boolean paused;
    private final ConcurrentHashMap<ImageView, Request> pendingRequests = new ConcurrentHashMap<>();
    /* access modifiers changed from: private */
    public String userAgent;

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

    private class LoaderThread extends HandlerThread implements Handler.Callback {
        private byte[] buffer;
        private Handler loaderThreadHandler;
        private final Set<Long> photoIds = new HashSet();
        private final Set<String> photoIdsAsStrings = new HashSet();
        private final Set<Request> photoUris = new HashSet();
        private final List<Long> preloadPhotoIds = new ArrayList();
        private int preloadStatus = 0;
        private final ContentResolver resolver;
        private final StringBuilder stringBuilder = new StringBuilder();

        public LoaderThread(ContentResolver contentResolver) {
            super("ContactPhotoLoader");
            this.resolver = contentResolver;
        }

        /* JADX WARNING: Removed duplicated region for block: B:47:0x0100  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x010a  */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x0121  */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x00b2 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void loadThumbnails(boolean r15) {
            /*
                r14 = this;
                java.util.Set<java.lang.Long> r0 = r14.photoIds
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L_0x0009
                return
            L_0x0009:
                r0 = 2
                r1 = 1
                if (r15 != 0) goto L_0x0033
                int r2 = r14.preloadStatus
                if (r2 != r1) goto L_0x0033
                java.util.Set<java.lang.Long> r2 = r14.photoIds
                java.util.Iterator r2 = r2.iterator()
            L_0x0017:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0029
                java.lang.Object r3 = r2.next()
                java.lang.Long r3 = (java.lang.Long) r3
                java.util.List<java.lang.Long> r4 = r14.preloadPhotoIds
                r4.remove(r3)
                goto L_0x0017
            L_0x0029:
                java.util.List<java.lang.Long> r2 = r14.preloadPhotoIds
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L_0x0033
                r14.preloadStatus = r0
            L_0x0033:
                java.lang.StringBuilder r2 = r14.stringBuilder
                r3 = 0
                r2.setLength(r3)
                java.lang.StringBuilder r2 = r14.stringBuilder
                java.lang.String r4 = "_id IN("
                r2.append(r4)
                r2 = r3
            L_0x0041:
                java.util.Set<java.lang.Long> r4 = r14.photoIds
                int r4 = r4.size()
                if (r2 >= r4) goto L_0x005c
                if (r2 == 0) goto L_0x0052
                java.lang.StringBuilder r4 = r14.stringBuilder
                r5 = 44
                r4.append(r5)
            L_0x0052:
                java.lang.StringBuilder r4 = r14.stringBuilder
                r5 = 63
                r4.append(r5)
                int r2 = r2 + 1
                goto L_0x0041
            L_0x005c:
                java.lang.StringBuilder r2 = r14.stringBuilder
                r4 = 41
                r2.append(r4)
                r2 = 0
                android.content.ContentResolver r4 = r14.resolver     // Catch:{ all -> 0x011e }
                android.net.Uri r5 = android.provider.ContactsContract.Data.CONTENT_URI     // Catch:{ all -> 0x011e }
                java.lang.String[] r6 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.COLUMNS     // Catch:{ all -> 0x011e }
                java.lang.StringBuilder r7 = r14.stringBuilder     // Catch:{ all -> 0x011e }
                java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x011e }
                java.util.Set<java.lang.String> r8 = r14.photoIdsAsStrings     // Catch:{ all -> 0x011e }
                java.lang.String[] r9 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.EMPTY_STRING_ARRAY     // Catch:{ all -> 0x011e }
                java.lang.Object[] r8 = r8.toArray(r9)     // Catch:{ all -> 0x011e }
                java.lang.String[] r8 = (java.lang.String[]) r8     // Catch:{ all -> 0x011e }
                r9 = 0
                android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x011e }
                r5 = -1
                if (r4 == 0) goto L_0x00a7
            L_0x0086:
                boolean r6 = r4.moveToNext()     // Catch:{ all -> 0x00a3 }
                if (r6 == 0) goto L_0x00a7
                long r6 = r4.getLong(r3)     // Catch:{ all -> 0x00a3 }
                java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x00a3 }
                byte[] r7 = r4.getBlob(r1)     // Catch:{ all -> 0x00a3 }
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r8 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this     // Catch:{ all -> 0x00a3 }
                r8.cacheBitmap(r6, r7, r15, r5)     // Catch:{ all -> 0x00a3 }
                java.util.Set<java.lang.Long> r7 = r14.photoIds     // Catch:{ all -> 0x00a3 }
                r7.remove(r6)     // Catch:{ all -> 0x00a3 }
                goto L_0x0086
            L_0x00a3:
                r14 = move-exception
                r2 = r4
                goto L_0x011f
            L_0x00a7:
                if (r4 == 0) goto L_0x00ac
                r4.close()
            L_0x00ac:
                java.util.Set<java.lang.Long> r4 = r14.photoIds
                java.util.Iterator r4 = r4.iterator()
            L_0x00b2:
                boolean r6 = r4.hasNext()
                if (r6 == 0) goto L_0x0114
                java.lang.Object r6 = r4.next()
                java.lang.Long r6 = (java.lang.Long) r6
                long r7 = r6.longValue()
                boolean r7 = android.provider.ContactsContract.isProfileId(r7)
                if (r7 == 0) goto L_0x010e
                android.content.ContentResolver r8 = r14.resolver     // Catch:{ all -> 0x0106 }
                android.net.Uri r7 = android.provider.ContactsContract.Data.CONTENT_URI     // Catch:{ all -> 0x0106 }
                long r9 = r6.longValue()     // Catch:{ all -> 0x0106 }
                android.net.Uri r9 = android.content.ContentUris.withAppendedId(r7, r9)     // Catch:{ all -> 0x0106 }
                java.lang.String[] r10 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.COLUMNS     // Catch:{ all -> 0x0106 }
                r11 = 0
                r12 = 0
                r13 = 0
                android.database.Cursor r7 = r8.query(r9, r10, r11, r12, r13)     // Catch:{ all -> 0x0106 }
                if (r7 == 0) goto L_0x00f9
                boolean r8 = r7.moveToFirst()     // Catch:{ all -> 0x0104 }
                if (r8 == 0) goto L_0x00f9
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r6 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this     // Catch:{ all -> 0x0104 }
                long r8 = r7.getLong(r3)     // Catch:{ all -> 0x0104 }
                java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0104 }
                byte[] r9 = r7.getBlob(r1)     // Catch:{ all -> 0x0104 }
                r6.cacheBitmap(r8, r9, r15, r5)     // Catch:{ all -> 0x0104 }
                goto L_0x00fe
            L_0x00f9:
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r8 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this     // Catch:{ all -> 0x0104 }
                r8.cacheBitmap(r6, r2, r15, r5)     // Catch:{ all -> 0x0104 }
            L_0x00fe:
                if (r7 == 0) goto L_0x00b2
                r7.close()
                goto L_0x00b2
            L_0x0104:
                r14 = move-exception
                goto L_0x0108
            L_0x0106:
                r14 = move-exception
                r7 = r2
            L_0x0108:
                if (r7 == 0) goto L_0x010d
                r7.close()
            L_0x010d:
                throw r14
            L_0x010e:
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r7 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this
                r7.cacheBitmap(r6, r2, r15, r5)
                goto L_0x00b2
            L_0x0114:
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r14 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this
                android.os.Handler r14 = r14.mainThreadHandler
                r14.sendEmptyMessage(r0)
                return
            L_0x011e:
                r14 = move-exception
            L_0x011f:
                if (r2 == 0) goto L_0x0124
                r2.close()
            L_0x0124:
                throw r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.contactphoto.ContactPhotoManagerImpl.LoaderThread.loadThumbnails(boolean):void");
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [byte[], android.database.Cursor] */
        /* JADX WARNING: Can't wrap try/catch for region: R(3:25|26|27) */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            r8.disconnect();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x009d, code lost:
            r8 = r0;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x009a */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00a3 A[Catch:{ all -> 0x00f0, all -> 0x00d2, Exception | OutOfMemoryError -> 0x00f5 }] */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x00d7 A[Catch:{ all -> 0x00f0, all -> 0x00d2, Exception | OutOfMemoryError -> 0x00f5 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean handleMessage(android.os.Message r13) {
            /*
                r12 = this;
                int r13 = r13.what
                r0 = 0
                r1 = 0
                r2 = 2
                java.lang.String r3 = "android.permission.READ_CONTACTS"
                r4 = 1
                if (r13 == 0) goto L_0x0118
                if (r13 == r4) goto L_0x000e
                goto L_0x0212
            L_0x000e:
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r13 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this
                android.content.Context r13 = r13.context
                boolean r13 = com.android.dialer.util.PermissionsUtil.hasPermission(r13, r3)
                if (r13 != 0) goto L_0x001c
                goto L_0x0212
            L_0x001c:
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r13 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this
                java.util.Set<java.lang.Long> r3 = r12.photoIds
                java.util.Set<java.lang.String> r5 = r12.photoIdsAsStrings
                java.util.Set<com.android.dialer.contactphoto.ContactPhotoManagerImpl$Request> r6 = r12.photoUris
                r13.obtainPhotoIdsAndUrisToLoad(r3, r5, r6)
                r12.loadThumbnails(r1)
                java.lang.String r13 = "cannot load photo "
                java.util.Set<com.android.dialer.contactphoto.ContactPhotoManagerImpl$Request> r3 = r12.photoUris
                java.util.Iterator r3 = r3.iterator()
            L_0x0032:
                boolean r5 = r3.hasNext()
                if (r5 == 0) goto L_0x0113
                java.lang.Object r5 = r3.next()
                com.android.dialer.contactphoto.ContactPhotoManagerImpl$Request r5 = (com.android.dialer.contactphoto.ContactPhotoManagerImpl.Request) r5
                android.net.Uri r6 = r5.getUri()
                android.net.Uri r7 = com.android.dialer.contactphoto.ContactPhotoManager.removeContactType(r6)
                byte[] r8 = r12.buffer
                if (r8 != 0) goto L_0x0050
                r8 = 16384(0x4000, float:2.2959E-41)
                byte[] r8 = new byte[r8]
                r12.buffer = r8
            L_0x0050:
                java.lang.String r8 = r7.getScheme()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                java.lang.String r9 = "http"
                boolean r9 = r8.equals(r9)     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                if (r9 != 0) goto L_0x006c
                java.lang.String r9 = "https"
                boolean r8 = r8.equals(r9)     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                if (r8 == 0) goto L_0x0065
                goto L_0x006c
            L_0x0065:
                android.content.ContentResolver r8 = r12.resolver     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                java.io.InputStream r8 = r8.openInputStream(r7)     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                goto L_0x00a1
            L_0x006c:
                android.net.TrafficStats.setThreadStatsTag(r4)     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                java.net.URL r8 = new java.net.URL     // Catch:{ all -> 0x00f0 }
                java.lang.String r9 = r7.toString()     // Catch:{ all -> 0x00f0 }
                r8.<init>(r9)     // Catch:{ all -> 0x00f0 }
                java.net.URLConnection r8 = r8.openConnection()     // Catch:{ all -> 0x00f0 }
                java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ all -> 0x00f0 }
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r9 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this     // Catch:{ all -> 0x00f0 }
                java.lang.String r9 = r9.userAgent     // Catch:{ all -> 0x00f0 }
                boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x00f0 }
                if (r9 != 0) goto L_0x0095
                java.lang.String r9 = "User-Agent"
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r10 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this     // Catch:{ all -> 0x00f0 }
                java.lang.String r10 = r10.userAgent     // Catch:{ all -> 0x00f0 }
                r8.setRequestProperty(r9, r10)     // Catch:{ all -> 0x00f0 }
            L_0x0095:
                java.io.InputStream r8 = r8.getInputStream()     // Catch:{ IOException -> 0x009a }
                goto L_0x009e
            L_0x009a:
                r8.disconnect()     // Catch:{ all -> 0x00f0 }
                r8 = r0
            L_0x009e:
                android.net.TrafficStats.clearThreadStatsTag()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
            L_0x00a1:
                if (r8 == 0) goto L_0x00d7
                java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                r9.<init>()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
            L_0x00a8:
                byte[] r10 = r12.buffer     // Catch:{ all -> 0x00d2 }
                int r10 = r8.read(r10)     // Catch:{ all -> 0x00d2 }
                r11 = -1
                if (r10 == r11) goto L_0x00b7
                byte[] r11 = r12.buffer     // Catch:{ all -> 0x00d2 }
                r9.write(r11, r1, r10)     // Catch:{ all -> 0x00d2 }
                goto L_0x00a8
            L_0x00b7:
                r8.close()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r8 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                byte[] r9 = r9.toByteArray()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                int r10 = r5.getRequestedExtent()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                r8.cacheBitmap(r6, r9, r1, r10)     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r8 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                android.os.Handler r8 = r8.mainThreadHandler     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                r8.sendEmptyMessage(r2)     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                goto L_0x0032
            L_0x00d2:
                r9 = move-exception
                r8.close()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                throw r9     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
            L_0x00d7:
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                r8.<init>()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                r8.append(r13)     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                r8.append(r7)     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                r8.toString()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r8 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                int r9 = r5.getRequestedExtent()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                r8.cacheBitmap(r6, r0, r1, r9)     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                goto L_0x0032
            L_0x00f0:
                r8 = move-exception
                android.net.TrafficStats.clearThreadStatsTag()     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
                throw r8     // Catch:{ Exception | OutOfMemoryError -> 0x00f5 }
            L_0x00f5:
                r8 = move-exception
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r9.<init>()
                r9.append(r13)
                r9.append(r7)
                r9.toString()
                java.lang.Object[] r7 = new java.lang.Object[r4]
                r7[r1] = r8
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r7 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this
                int r5 = r5.getRequestedExtent()
                r7.cacheBitmap(r6, r0, r1, r5)
                goto L_0x0032
            L_0x0113:
                r12.requestPreloading()
                goto L_0x0212
            L_0x0118:
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r13 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this
                android.content.Context r13 = r13.context
                boolean r13 = com.android.dialer.util.PermissionsUtil.hasPermission(r13, r3)
                if (r13 != 0) goto L_0x0126
                goto L_0x0212
            L_0x0126:
                int r13 = r12.preloadStatus
                if (r13 != r2) goto L_0x012c
                goto L_0x0212
            L_0x012c:
                if (r13 != 0) goto L_0x0195
                android.net.Uri r13 = android.provider.ContactsContract.Contacts.CONTENT_URI     // Catch:{ all -> 0x018e }
                android.net.Uri$Builder r13 = r13.buildUpon()     // Catch:{ all -> 0x018e }
                java.lang.String r3 = "directory"
                r5 = 0
                java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x018e }
                android.net.Uri$Builder r13 = r13.appendQueryParameter(r3, r5)     // Catch:{ all -> 0x018e }
                java.lang.String r3 = "limit"
                r5 = 100
                java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x018e }
                android.net.Uri$Builder r13 = r13.appendQueryParameter(r3, r5)     // Catch:{ all -> 0x018e }
                android.net.Uri r6 = r13.build()     // Catch:{ all -> 0x018e }
                android.content.ContentResolver r5 = r12.resolver     // Catch:{ all -> 0x018e }
                java.lang.String r13 = "photo_id"
                java.lang.String[] r7 = new java.lang.String[]{r13}     // Catch:{ all -> 0x018e }
                java.lang.String r8 = "photo_id NOT NULL AND photo_id!=0"
                r9 = 0
                java.lang.String r10 = "starred DESC, last_time_contacted DESC"
                android.database.Cursor r0 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x018e }
                if (r0 == 0) goto L_0x0177
            L_0x0163:
                boolean r13 = r0.moveToNext()     // Catch:{ all -> 0x018e }
                if (r13 == 0) goto L_0x0177
                java.util.List<java.lang.Long> r13 = r12.preloadPhotoIds     // Catch:{ all -> 0x018e }
                long r5 = r0.getLong(r1)     // Catch:{ all -> 0x018e }
                java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x018e }
                r13.add(r1, r3)     // Catch:{ all -> 0x018e }
                goto L_0x0163
            L_0x0177:
                if (r0 == 0) goto L_0x017c
                r0.close()
            L_0x017c:
                java.util.List<java.lang.Long> r13 = r12.preloadPhotoIds
                boolean r13 = r13.isEmpty()
                if (r13 == 0) goto L_0x0187
                r12.preloadStatus = r2
                goto L_0x0189
            L_0x0187:
                r12.preloadStatus = r4
            L_0x0189:
                r12.requestPreloading()
                goto L_0x0212
            L_0x018e:
                r12 = move-exception
                if (r0 == 0) goto L_0x0194
                r0.close()
            L_0x0194:
                throw r12
            L_0x0195:
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r13 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this
                android.util.LruCache r13 = r13.bitmapHolderCache
                int r13 = r13.size()
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r0 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this
                int r0 = r0.bitmapHolderCacheRedZoneBytes
                if (r13 <= r0) goto L_0x01aa
                r12.preloadStatus = r2
                goto L_0x0212
            L_0x01aa:
                java.util.Set<java.lang.Long> r13 = r12.photoIds
                r13.clear()
                java.util.Set<java.lang.String> r13 = r12.photoIdsAsStrings
                r13.clear()
                java.util.List<java.lang.Long> r13 = r12.preloadPhotoIds
                int r13 = r13.size()
            L_0x01ba:
                if (r13 <= 0) goto L_0x01e6
                java.util.Set<java.lang.Long> r0 = r12.photoIds
                int r0 = r0.size()
                r3 = 25
                if (r0 >= r3) goto L_0x01e6
                int r13 = r13 + -1
                int r1 = r1 + 1
                java.util.List<java.lang.Long> r0 = r12.preloadPhotoIds
                java.lang.Object r0 = r0.get(r13)
                java.lang.Long r0 = (java.lang.Long) r0
                java.util.Set<java.lang.Long> r3 = r12.photoIds
                r3.add(r0)
                java.util.Set<java.lang.String> r3 = r12.photoIdsAsStrings
                java.lang.String r0 = r0.toString()
                r3.add(r0)
                java.util.List<java.lang.Long> r0 = r12.preloadPhotoIds
                r0.remove(r13)
                goto L_0x01ba
            L_0x01e6:
                r12.loadThumbnails(r4)
                if (r13 != 0) goto L_0x01ed
                r12.preloadStatus = r2
            L_0x01ed:
                java.lang.StringBuilder r13 = new java.lang.StringBuilder
                r13.<init>()
                java.lang.String r0 = "preloaded "
                r13.append(r0)
                r13.append(r1)
                java.lang.String r0 = " photos.  cached bytes: "
                r13.append(r0)
                com.android.dialer.contactphoto.ContactPhotoManagerImpl r0 = com.android.dialer.contactphoto.ContactPhotoManagerImpl.this
                android.util.LruCache r0 = r0.bitmapHolderCache
                int r0 = r0.size()
                r13.append(r0)
                r13.toString()
                r12.requestPreloading()
            L_0x0212:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.contactphoto.ContactPhotoManagerImpl.LoaderThread.handleMessage(android.os.Message):boolean");
        }

        public void requestLoading() {
            if (this.loaderThreadHandler == null) {
                this.loaderThreadHandler = new Handler(getLooper(), this);
            }
            this.loaderThreadHandler.removeMessages(0);
            this.loaderThreadHandler.sendEmptyMessage(1);
        }

        public void requestPreloading() {
            if (this.preloadStatus != 2) {
                if (this.loaderThreadHandler == null) {
                    this.loaderThreadHandler = new Handler(getLooper(), this);
                }
                if (!this.loaderThreadHandler.hasMessages(1)) {
                    this.loaderThreadHandler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        }
    }

    private static final class Request {
        private final boolean darkTheme;
        private final ContactPhotoManager.DefaultImageProvider defaultProvider;
        /* access modifiers changed from: private */

        /* renamed from: id */
        public final long f18id;
        /* access modifiers changed from: private */
        public final boolean isCircular;
        private final int requestedExtent;
        private final Uri uri;

        private Request(long j, Uri uri2, int i, boolean z, boolean z2, ContactPhotoManager.DefaultImageProvider defaultImageProvider) {
            this.f18id = j;
            this.uri = uri2;
            this.darkTheme = z;
            this.isCircular = z2;
            this.requestedExtent = i;
            this.defaultProvider = defaultImageProvider;
        }

        public static Request createFromThumbnailId(long j, boolean z, boolean z2, ContactPhotoManager.DefaultImageProvider defaultImageProvider) {
            return new Request(j, (Uri) null, -1, z, z2, defaultImageProvider);
        }

        public static Request createFromUri(Uri uri2, int i, boolean z, boolean z2, ContactPhotoManager.DefaultImageProvider defaultImageProvider) {
            return new Request(0, uri2, i, z, z2, defaultImageProvider);
        }

        public void applyDefaultImage(ImageView imageView, boolean z) {
            ContactPhotoManager.DefaultImageRequest defaultImageRequest;
            if (z) {
                if (ContactPhotoManager.isBusinessContactUri(this.uri)) {
                    defaultImageRequest = ContactPhotoManager.DefaultImageRequest.EMPTY_CIRCULAR_BUSINESS_IMAGE_REQUEST;
                } else {
                    defaultImageRequest = ContactPhotoManager.DefaultImageRequest.EMPTY_CIRCULAR_DEFAULT_IMAGE_REQUEST;
                }
            } else if (ContactPhotoManager.isBusinessContactUri(this.uri)) {
                defaultImageRequest = ContactPhotoManager.DefaultImageRequest.EMPTY_DEFAULT_BUSINESS_IMAGE_REQUEST;
            } else {
                defaultImageRequest = ContactPhotoManager.DefaultImageRequest.EMPTY_DEFAULT_IMAGE_REQUEST;
            }
            this.defaultProvider.applyDefaultImage(imageView, this.requestedExtent, this.darkTheme, defaultImageRequest);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Request.class != obj.getClass()) {
                return false;
            }
            Request request = (Request) obj;
            return this.f18id == request.f18id && this.requestedExtent == request.requestedExtent && R$style.areEqual(this.uri, request.uri);
        }

        public long getId() {
            return this.f18id;
        }

        public Object getKey() {
            Uri uri2 = this.uri;
            return uri2 == null ? Long.valueOf(this.f18id) : uri2;
        }

        public int getRequestedExtent() {
            return this.requestedExtent;
        }

        public Uri getUri() {
            return this.uri;
        }

        public int hashCode() {
            long j = this.f18id;
            int i = (((((int) (j ^ (j >>> 32))) + 31) * 31) + this.requestedExtent) * 31;
            Uri uri2 = this.uri;
            return i + (uri2 == null ? 0 : uri2.hashCode());
        }

        public boolean isUriRequest() {
            return this.uri != null;
        }
    }

    static {
        BITMAP_UNAVAILABLE.bitmapRef = new SoftReference((Object) null);
    }

    public ContactPhotoManagerImpl(Context context2) {
        new AtomicInteger();
        new AtomicInteger();
        this.context = context2;
        float f = ((ActivityManager) context2.getSystemService("activity")).isLowRamDevice() ? 0.5f : 1.0f;
        this.bitmapCache = new LruCache<Object, Bitmap>(this, (int) (1769472.0f * f)) {
            /* access modifiers changed from: protected */
            public void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
                Bitmap bitmap = (Bitmap) obj2;
                Bitmap bitmap2 = (Bitmap) obj3;
            }

            /* access modifiers changed from: protected */
            public int sizeOf(Object obj, Object obj2) {
                return ((Bitmap) obj2).getByteCount();
            }
        };
        int i = (int) (2000000.0f * f);
        this.bitmapHolderCache = new LruCache<Object, BitmapHolder>(this, i) {
            /* access modifiers changed from: protected */
            public void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
                BitmapHolder bitmapHolder = (BitmapHolder) obj2;
                BitmapHolder bitmapHolder2 = (BitmapHolder) obj3;
            }

            /* access modifiers changed from: protected */
            public int sizeOf(Object obj, Object obj2) {
                byte[] bArr = ((BitmapHolder) obj2).bytes;
                if (bArr != null) {
                    return bArr.length;
                }
                return 0;
            }
        };
        this.bitmapHolderCacheRedZoneBytes = (int) (((double) i) * 0.75d);
        LogUtil.m9i("ContactPhotoManagerImpl.ContactPhotoManagerImpl", "cache adj: " + f, new Object[0]);
        thumbnailSize = context2.getResources().getDimensionPixelSize(R.dimen.contact_browser_list_item_photo_size);
        this.userAgent = Constants.get().getUserAgent(context2);
        if (this.userAgent == null) {
            this.userAgent = "";
        }
    }

    /* access modifiers changed from: private */
    public void cacheBitmap(Object obj, byte[] bArr, boolean z, int i) {
        int i2;
        if (bArr == null) {
            i2 = -1;
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            i2 = Math.min(options.outWidth, options.outHeight);
        }
        BitmapHolder bitmapHolder = new BitmapHolder(bArr, i2);
        if (!z) {
            inflateBitmap(bitmapHolder, i);
        }
        if (bArr != null) {
            this.bitmapHolderCache.put(obj, bitmapHolder);
            if (this.bitmapHolderCache.get(obj) != bitmapHolder) {
                LogUtil.m10w("ContactPhotoManagerImpl.cacheBitmap", "bitmap too big to fit in cache.", new Object[0]);
                this.bitmapHolderCache.put(obj, BITMAP_UNAVAILABLE);
                return;
            }
            return;
        }
        this.bitmapHolderCache.put(obj, BITMAP_UNAVAILABLE);
    }

    private Drawable getDrawableForBitmap(Resources resources, Bitmap bitmap, Request request) {
        if (!request.isCircular) {
            return new BitmapDrawable(resources, bitmap);
        }
        RoundedBitmapDrawable create = DrawableCompat.create(resources, bitmap);
        create.setAntiAlias(true);
        create.setCornerRadius((float) (create.getIntrinsicHeight() / 2));
        return create;
    }

    private static void inflateBitmap(BitmapHolder bitmapHolder, int i) {
        int i2;
        BitmapFactory.Options options;
        Reference<Bitmap> reference;
        int i3 = bitmapHolder.originalSmallerExtent;
        if (i >= 1 && i3 >= 1) {
            i2 = 1;
            while (true) {
                i3 >>= 1;
                if (((float) i3) < ((float) i) * 0.8f) {
                    break;
                }
                i2 <<= 1;
            }
        } else {
            i2 = 1;
        }
        byte[] bArr = bitmapHolder.bytes;
        if (bArr != null && bArr.length != 0) {
            if (i2 == bitmapHolder.decodedSampleSize && (reference = bitmapHolder.bitmapRef) != null) {
                bitmapHolder.bitmap = reference.get();
                if (bitmapHolder.bitmap != null) {
                    return;
                }
            }
            if (i2 <= 1) {
                options = null;
            } else {
                try {
                    options = new BitmapFactory.Options();
                    options.inSampleSize = i2;
                } catch (OutOfMemoryError unused) {
                    return;
                }
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int height = decodeByteArray.getHeight();
            int width = decodeByteArray.getWidth();
            if (height != width && Math.min(height, width) <= thumbnailSize * 2) {
                int min = Math.min(height, width);
                decodeByteArray = ThumbnailUtils.extractThumbnail(decodeByteArray, min, min);
            }
            bitmapHolder.decodedSampleSize = i2;
            bitmapHolder.bitmap = decodeByteArray;
            bitmapHolder.bitmapRef = new SoftReference(decodeByteArray);
        }
    }

    private boolean loadCachedPhoto(ImageView imageView, Request request, boolean z) {
        BitmapHolder bitmapHolder = this.bitmapHolderCache.get(request.getKey());
        if (bitmapHolder == null) {
            request.applyDefaultImage(imageView, request.isCircular);
            return false;
        } else if (bitmapHolder.bytes == null) {
            request.applyDefaultImage(imageView, request.isCircular);
            return bitmapHolder.fresh;
        } else {
            Reference<Bitmap> reference = bitmapHolder.bitmapRef;
            Bitmap bitmap = reference == null ? null : reference.get();
            if (bitmap == null) {
                request.applyDefaultImage(imageView, request.isCircular);
                return false;
            }
            Drawable drawable = imageView.getDrawable();
            if (!z || drawable == null) {
                imageView.setImageDrawable(getDrawableForBitmap(this.context.getResources(), bitmap, request));
            } else {
                Drawable[] drawableArr = new Drawable[2];
                if (drawable instanceof TransitionDrawable) {
                    TransitionDrawable transitionDrawable = (TransitionDrawable) drawable;
                    drawableArr[0] = transitionDrawable.getDrawable(transitionDrawable.getNumberOfLayers() - 1);
                } else {
                    drawableArr[0] = drawable;
                }
                drawableArr[1] = getDrawableForBitmap(this.context.getResources(), bitmap, request);
                TransitionDrawable transitionDrawable2 = new TransitionDrawable(drawableArr);
                imageView.setImageDrawable(transitionDrawable2);
                transitionDrawable2.startTransition(200);
            }
            if (bitmap.getByteCount() < this.bitmapCache.maxSize() / 6) {
                this.bitmapCache.put(request.getKey(), bitmap);
            }
            bitmapHolder.bitmap = null;
            return bitmapHolder.fresh;
        }
    }

    private void loadPhotoByIdOrUri(ImageView imageView, Request request) {
        if (loadCachedPhoto(imageView, request, false)) {
            this.pendingRequests.remove(imageView);
            return;
        }
        this.pendingRequests.put(imageView, request);
        if (!this.paused && !this.loadingRequested) {
            this.loadingRequested = true;
            this.mainThreadHandler.sendEmptyMessage(1);
        }
    }

    /* access modifiers changed from: private */
    public void obtainPhotoIdsAndUrisToLoad(Set<Long> set, Set<String> set2, Set<Request> set3) {
        Reference<Bitmap> reference;
        set.clear();
        set2.clear();
        set3.clear();
        boolean z = false;
        for (Request next : this.pendingRequests.values()) {
            BitmapHolder bitmapHolder = this.bitmapHolderCache.get(next.getKey());
            if (bitmapHolder != BITMAP_UNAVAILABLE) {
                if (bitmapHolder != null && bitmapHolder.bytes != null && bitmapHolder.fresh && ((reference = bitmapHolder.bitmapRef) == null || reference.get() == null)) {
                    inflateBitmap(bitmapHolder, next.getRequestedExtent());
                    z = true;
                } else if (bitmapHolder == null || !bitmapHolder.fresh) {
                    if (next.isUriRequest()) {
                        set3.add(next);
                    } else {
                        set.add(Long.valueOf(next.getId()));
                        set2.add(String.valueOf(next.f18id));
                    }
                }
            }
        }
        if (z) {
            this.mainThreadHandler.sendEmptyMessage(2);
        }
    }

    private void requestLoading() {
        if (!this.loadingRequested) {
            this.loadingRequested = true;
            this.mainThreadHandler.sendEmptyMessage(1);
        }
    }

    public void ensureLoaderThread() {
        if (this.loaderThread == null) {
            this.loaderThread = new LoaderThread(this.context.getContentResolver());
            this.loaderThread.start();
        }
    }

    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.loadingRequested = false;
            if (!this.paused) {
                ensureLoaderThread();
                this.loaderThread.requestLoading();
            }
            return true;
        } else if (i != 2) {
            return false;
        } else {
            if (!this.paused) {
                Iterator<Map.Entry<ImageView, Request>> it = this.pendingRequests.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry next = it.next();
                    if (loadCachedPhoto((ImageView) next.getKey(), (Request) next.getValue(), false)) {
                        it.remove();
                    }
                }
                for (BitmapHolder bitmapHolder : this.bitmapHolderCache.snapshot().values()) {
                    bitmapHolder.bitmap = null;
                }
                if (!this.pendingRequests.isEmpty()) {
                    requestLoading();
                }
            }
            return true;
        }
    }

    public void loadPhoto(ImageView imageView, Uri uri, int i, boolean z, boolean z2, ContactPhotoManager.DefaultImageRequest defaultImageRequest, ContactPhotoManager.DefaultImageProvider defaultImageProvider) {
        boolean z3;
        if (uri == null) {
            defaultImageProvider.applyDefaultImage(imageView, i, z, defaultImageRequest);
            this.pendingRequests.remove(imageView);
            return;
        }
        if (!"android.resource".equals(uri.getScheme())) {
            z3 = false;
        } else {
            z3 = uri.getPathSegments().get(0).equals("drawable");
        }
        if (z3) {
            imageView.setImageURI(uri);
            this.pendingRequests.remove(imageView);
        } else if ("defaultimage".equals(uri.getScheme())) {
            ContactPhotoManager.DefaultImageRequest defaultImageRequest2 = new ContactPhotoManager.DefaultImageRequest(uri.getQueryParameter("display_name"), uri.getQueryParameter("identifier"), false);
            try {
                String queryParameter = uri.getQueryParameter("contact_type");
                if (!TextUtils.isEmpty(queryParameter)) {
                    defaultImageRequest2.contactType = Integer.valueOf(queryParameter).intValue();
                }
                String queryParameter2 = uri.getQueryParameter("scale");
                if (!TextUtils.isEmpty(queryParameter2)) {
                    defaultImageRequest2.scale = Float.valueOf(queryParameter2).floatValue();
                }
                String queryParameter3 = uri.getQueryParameter("offset");
                if (!TextUtils.isEmpty(queryParameter3)) {
                    defaultImageRequest2.offset = Float.valueOf(queryParameter3).floatValue();
                }
                String queryParameter4 = uri.getQueryParameter("is_circular");
                if (!TextUtils.isEmpty(queryParameter4)) {
                    defaultImageRequest2.isCircular = Boolean.valueOf(queryParameter4).booleanValue();
                }
            } catch (NumberFormatException unused) {
                LogUtil.m10w("ContactPhotoManager.getDefaultImageRequestFromUri", "Invalid DefaultImageRequest image parameters provided, ignoring and using defaults.", new Object[0]);
            }
            defaultImageRequest2.isCircular = z2;
            defaultImageProvider.applyDefaultImage(imageView, i, z, defaultImageRequest2);
        } else {
            loadPhotoByIdOrUri(imageView, Request.createFromUri(uri, i, z, z2, defaultImageProvider));
        }
    }

    public void loadThumbnail(ImageView imageView, long j, boolean z, boolean z2, ContactPhotoManager.DefaultImageRequest defaultImageRequest, ContactPhotoManager.DefaultImageProvider defaultImageProvider) {
        if (j == 0) {
            defaultImageProvider.applyDefaultImage(imageView, -1, z, defaultImageRequest);
            this.pendingRequests.remove(imageView);
            return;
        }
        loadPhotoByIdOrUri(imageView, Request.createFromThumbnailId(j, z, z2, defaultImageProvider));
    }

    public void onTrimMemory(int i) {
        if (i >= 60) {
            this.pendingRequests.clear();
            this.bitmapHolderCache.evictAll();
            this.bitmapCache.evictAll();
        }
    }

    public void preloadPhotosInBackground() {
        ensureLoaderThread();
        this.loaderThread.requestPreloading();
    }
}
