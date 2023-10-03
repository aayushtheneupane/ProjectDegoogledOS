package androidx.core.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.provider.SelfDestructiveThread;
import androidx.core.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class FontsContractCompat {
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() {
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int compare(byte[] r4, byte[] r5) {
            /*
                r3 = this;
                int r3 = r4.length
                int r0 = r5.length
                if (r3 == r0) goto L_0x0008
                int r3 = r4.length
                int r4 = r5.length
            L_0x0006:
                int r3 = r3 - r4
                return r3
            L_0x0008:
                r3 = 0
                r0 = r3
            L_0x000a:
                int r1 = r4.length
                if (r0 >= r1) goto L_0x001b
                byte r1 = r4[r0]
                byte r2 = r5[r0]
                if (r1 == r2) goto L_0x0018
                byte r3 = r4[r0]
                byte r4 = r5[r0]
                goto L_0x0006
            L_0x0018:
                int r0 = r0 + 1
                goto L_0x000a
            L_0x001b:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontsContractCompat.C01165.compare(byte[], byte[]):int");
        }
    };
    private static Executor sExecutor;
    static final Object sLock = new Object();
    static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> sPendingReplies = new SimpleArrayMap<>();
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);

    private interface OnCompletedCallback<T> {
        void onCompleted(T t);
    }

    static TypefaceResult getFontInternal(Context context, FontRequest fontRequest, int i) {
        try {
            FontFamilyResult fetchFonts = fetchFonts(context, (CancellationSignal) null, fontRequest);
            int i2 = -3;
            if (fetchFonts.getStatusCode() == 0) {
                Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, (CancellationSignal) null, fetchFonts.getFonts(), i);
                if (createFromFontInfo != null) {
                    i2 = 0;
                }
                return new TypefaceResult(createFromFontInfo, i2);
            }
            if (fetchFonts.getStatusCode() == 1) {
                i2 = -2;
            }
            return new TypefaceResult((Typeface) null, i2);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult((Typeface) null, -1);
        }
    }

    private static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(Typeface typeface, int i) {
            this.mTypeface = typeface;
            this.mResult = i;
        }
    }

    private static final class SyncFontFetchTaskCallable implements Callable<TypefaceResult> {
        private final Context mAppContext;
        private final String mCacheId;
        private final FontRequest mRequest;
        private final int mStyle;

        SyncFontFetchTaskCallable(Context context, FontRequest fontRequest, int i, String str) {
            this.mCacheId = str;
            this.mAppContext = context.getApplicationContext();
            this.mRequest = fontRequest;
            this.mStyle = i;
        }

        public TypefaceResult call() throws Exception {
            TypefaceResult fontInternal = FontsContractCompat.getFontInternal(this.mAppContext, this.mRequest, this.mStyle);
            Typeface typeface = fontInternal.mTypeface;
            if (typeface != null) {
                FontsContractCompat.sTypefaceCache.put(this.mCacheId, typeface);
            }
            return fontInternal;
        }
    }

    private static final class OnFetchCompletedAndFirePendingReplyCallback implements OnCompletedCallback<TypefaceResult> {
        private final String mCacheId;

        OnFetchCompletedAndFirePendingReplyCallback(String str) {
            this.mCacheId = str;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
            if (r3 >= r1.size()) goto L_0x002c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
            ((androidx.core.provider.SelfDestructiveThread.ReplyCallback) r1.get(r3)).onReply(r4);
            r3 = r3 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
            r3 = 0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCompleted(androidx.core.provider.FontsContractCompat.TypefaceResult r4) {
            /*
                r3 = this;
                java.lang.Object r0 = androidx.core.provider.FontsContractCompat.sLock
                monitor-enter(r0)
                androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.provider.SelfDestructiveThread$ReplyCallback<androidx.core.provider.FontsContractCompat$TypefaceResult>>> r1 = androidx.core.provider.FontsContractCompat.sPendingReplies     // Catch:{ all -> 0x002d }
                java.lang.String r2 = r3.mCacheId     // Catch:{ all -> 0x002d }
                java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x002d }
                java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x002d }
                if (r1 != 0) goto L_0x0011
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return
            L_0x0011:
                androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.provider.SelfDestructiveThread$ReplyCallback<androidx.core.provider.FontsContractCompat$TypefaceResult>>> r2 = androidx.core.provider.FontsContractCompat.sPendingReplies     // Catch:{ all -> 0x002d }
                java.lang.String r3 = r3.mCacheId     // Catch:{ all -> 0x002d }
                r2.remove(r3)     // Catch:{ all -> 0x002d }
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                r3 = 0
            L_0x001a:
                int r0 = r1.size()
                if (r3 >= r0) goto L_0x002c
                java.lang.Object r0 = r1.get(r3)
                androidx.core.provider.SelfDestructiveThread$ReplyCallback r0 = (androidx.core.provider.SelfDestructiveThread.ReplyCallback) r0
                r0.onReply(r4)
                int r3 = r3 + 1
                goto L_0x001a
            L_0x002c:
                return
            L_0x002d:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontsContractCompat.OnFetchCompletedAndFirePendingReplyCallback.onCompleted(androidx.core.provider.FontsContractCompat$TypefaceResult):void");
        }
    }

    private static final class SyncFontFetchTask extends FutureTask<TypefaceResult> {

        private static final class CallableWrapper implements Callable<TypefaceResult> {
            private final Callable<TypefaceResult> mOriginalCallback;
            private final OnCompletedCallback<TypefaceResult> mTypefaceResultOnCompletedCallback;

            CallableWrapper(Callable<TypefaceResult> callable, OnCompletedCallback<TypefaceResult> onCompletedCallback) {
                this.mOriginalCallback = callable;
                this.mTypefaceResultOnCompletedCallback = onCompletedCallback;
            }

            public TypefaceResult call() throws Exception {
                TypefaceResult call = this.mOriginalCallback.call();
                this.mTypefaceResultOnCompletedCallback.onCompleted(call);
                return call;
            }
        }

        SyncFontFetchTask(SyncFontFetchTaskCallable syncFontFetchTaskCallable) {
            super(syncFontFetchTaskCallable);
        }

        SyncFontFetchTask(SyncFontFetchTaskCallable syncFontFetchTaskCallable, OnCompletedCallback<TypefaceResult> onCompletedCallback) {
            super(new CallableWrapper(syncFontFetchTaskCallable, onCompletedCallback));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b0, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c1, code lost:
        if (r10 != null) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00c3, code lost:
        sBackgroundThread.postAndReply(r2, new androidx.core.provider.FontsContractCompat.C01153());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ce, code lost:
        r10.execute(new androidx.core.provider.FontsContractCompat.SyncFontFetchTask(r2, new androidx.core.provider.FontsContractCompat.OnFetchCompletedAndFirePendingReplyCallback(r0)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00db, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Typeface getFontSync(android.content.Context r3, androidx.core.provider.FontRequest r4, final androidx.core.content.res.ResourcesCompat.FontCallback r5, final android.os.Handler r6, boolean r7, int r8, int r9, boolean r10) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r4.getIdentifier()
            r0.append(r1)
            java.lang.String r1 = "-"
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            androidx.collection.LruCache<java.lang.String, android.graphics.Typeface> r1 = sTypefaceCache
            java.lang.Object r1 = r1.get(r0)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L_0x0028
            if (r5 == 0) goto L_0x0027
            r5.onFontRetrieved(r1)
        L_0x0027:
            return r1
        L_0x0028:
            if (r7 == 0) goto L_0x0043
            r1 = -1
            if (r8 != r1) goto L_0x0043
            androidx.core.provider.FontsContractCompat$TypefaceResult r3 = getFontInternal(r3, r4, r9)
            if (r5 == 0) goto L_0x0040
            int r4 = r3.mResult
            if (r4 != 0) goto L_0x003d
            android.graphics.Typeface r4 = r3.mTypeface
            r5.callbackSuccessAsync(r4, r6)
            goto L_0x0040
        L_0x003d:
            r5.callbackFailAsync(r4, r6)
        L_0x0040:
            android.graphics.Typeface r3 = r3.mTypeface
            return r3
        L_0x0043:
            r1 = 0
            if (r10 == 0) goto L_0x0062
            if (r6 != 0) goto L_0x0062
            java.util.concurrent.Executor r10 = sExecutor
            if (r10 != 0) goto L_0x005f
            java.lang.Object r10 = sLock
            monitor-enter(r10)
            java.util.concurrent.Executor r2 = sExecutor     // Catch:{ all -> 0x005c }
            if (r2 != 0) goto L_0x005a
            r2 = 1
            java.util.concurrent.ExecutorService r2 = java.util.concurrent.Executors.newFixedThreadPool(r2)     // Catch:{ all -> 0x005c }
            sExecutor = r2     // Catch:{ all -> 0x005c }
        L_0x005a:
            monitor-exit(r10)     // Catch:{ all -> 0x005c }
            goto L_0x005f
        L_0x005c:
            r3 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x005c }
            throw r3
        L_0x005f:
            java.util.concurrent.Executor r10 = sExecutor
            goto L_0x0063
        L_0x0062:
            r10 = r1
        L_0x0063:
            androidx.core.provider.FontsContractCompat$SyncFontFetchTaskCallable r2 = new androidx.core.provider.FontsContractCompat$SyncFontFetchTaskCallable
            r2.<init>(r3, r4, r9, r0)
            if (r7 == 0) goto L_0x008c
            if (r10 != 0) goto L_0x0077
            androidx.core.provider.SelfDestructiveThread r3 = sBackgroundThread     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            java.lang.Object r3 = r3.postAndWait(r2, r8)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            androidx.core.provider.FontsContractCompat$TypefaceResult r3 = (androidx.core.provider.FontsContractCompat.TypefaceResult) r3     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            android.graphics.Typeface r3 = r3.mTypeface     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            return r3
        L_0x0077:
            androidx.core.provider.FontsContractCompat$SyncFontFetchTask r3 = new androidx.core.provider.FontsContractCompat$SyncFontFetchTask     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            r3.<init>(r2)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            r10.execute(r3)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            long r4 = (long) r8     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            java.lang.Object r3 = r3.get(r4, r6)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            androidx.core.provider.FontsContractCompat$TypefaceResult r3 = (androidx.core.provider.FontsContractCompat.TypefaceResult) r3     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            android.graphics.Typeface r3 = r3.mTypeface     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008b }
            return r3
        L_0x008b:
            return r1
        L_0x008c:
            if (r5 == 0) goto L_0x009c
            if (r10 != 0) goto L_0x0096
            androidx.core.provider.FontsContractCompat$1 r3 = new androidx.core.provider.FontsContractCompat$1
            r3.<init>(r5, r6)
            goto L_0x009d
        L_0x0096:
            androidx.core.provider.FontsContractCompat$2 r3 = new androidx.core.provider.FontsContractCompat$2
            r3.<init>(r5)
            goto L_0x009d
        L_0x009c:
            r3 = r1
        L_0x009d:
            java.lang.Object r4 = sLock
            monitor-enter(r4)
            androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.provider.SelfDestructiveThread$ReplyCallback<androidx.core.provider.FontsContractCompat$TypefaceResult>>> r5 = sPendingReplies     // Catch:{ all -> 0x00dc }
            java.lang.Object r5 = r5.get(r0)     // Catch:{ all -> 0x00dc }
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ all -> 0x00dc }
            if (r5 == 0) goto L_0x00b1
            if (r3 == 0) goto L_0x00af
            r5.add(r3)     // Catch:{ all -> 0x00dc }
        L_0x00af:
            monitor-exit(r4)     // Catch:{ all -> 0x00dc }
            return r1
        L_0x00b1:
            if (r3 == 0) goto L_0x00c0
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x00dc }
            r5.<init>()     // Catch:{ all -> 0x00dc }
            r5.add(r3)     // Catch:{ all -> 0x00dc }
            androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.provider.SelfDestructiveThread$ReplyCallback<androidx.core.provider.FontsContractCompat$TypefaceResult>>> r3 = sPendingReplies     // Catch:{ all -> 0x00dc }
            r3.put(r0, r5)     // Catch:{ all -> 0x00dc }
        L_0x00c0:
            monitor-exit(r4)     // Catch:{ all -> 0x00dc }
            if (r10 != 0) goto L_0x00ce
            androidx.core.provider.SelfDestructiveThread r3 = sBackgroundThread
            androidx.core.provider.FontsContractCompat$3 r4 = new androidx.core.provider.FontsContractCompat$3
            r4.<init>(r0)
            r3.postAndReply(r2, r4)
            goto L_0x00db
        L_0x00ce:
            androidx.core.provider.FontsContractCompat$SyncFontFetchTask r3 = new androidx.core.provider.FontsContractCompat$SyncFontFetchTask
            androidx.core.provider.FontsContractCompat$OnFetchCompletedAndFirePendingReplyCallback r4 = new androidx.core.provider.FontsContractCompat$OnFetchCompletedAndFirePendingReplyCallback
            r4.<init>(r0)
            r3.<init>(r2, r4)
            r10.execute(r3)
        L_0x00db:
            return r1
        L_0x00dc:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00dc }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontsContractCompat.getFontSync(android.content.Context, androidx.core.provider.FontRequest, androidx.core.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, int, int, boolean):android.graphics.Typeface");
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        public FontInfo(Uri uri, int i, int i2, boolean z, int i3) {
            Preconditions.checkNotNull(uri);
            this.mUri = uri;
            this.mTtcIndex = i;
            this.mWeight = i2;
            this.mItalic = z;
            this.mResultCode = i3;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    public static class FontFamilyResult {
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        public FontFamilyResult(int i, FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = fontInfoArr;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public static FontFamilyResult fetchFonts(Context context, CancellationSignal cancellationSignal, FontRequest fontRequest) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, (FontInfo[]) null);
        }
        return new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(convertToByteArrayList, sByteArrayComparator);
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            for (int i = 0; i < certificates.size(); i++) {
                ArrayList arrayList = new ArrayList(certificates.get(i));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    static FontInfo[] getFontFromProvider(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        Uri uri;
        Cursor query;
        String str2 = str;
        ArrayList arrayList = new ArrayList();
        Uri build = new Uri.Builder().scheme("content").authority(str2).build();
        Uri build2 = new Uri.Builder().scheme("content").authority(str2).appendPath("file").build();
        Cursor cursor = null;
        try {
            if (Build.VERSION.SDK_INT > 16) {
                query = context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest.getQuery()}, (String) null, cancellationSignal);
            } else {
                query = context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest.getQuery()}, (String) null);
            }
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex("result_code");
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex("file_id");
                int columnIndex4 = cursor.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursor.getColumnIndex("font_weight");
                int columnIndex6 = cursor.getColumnIndex("font_italic");
                while (cursor.moveToNext()) {
                    int i = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                    int i2 = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        uri = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                    } else {
                        uri = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                    }
                    arrayList2.add(new FontInfo(uri, i2, columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1, i));
                }
                arrayList = arrayList2;
            }
            return (FontInfo[]) arrayList.toArray(new FontInfo[0]);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
