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
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.core.content.p022a.C0308a;
import androidx.core.content.p022a.C0318k;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import p000a.p005b.C0020g;
import p000a.p005b.C0027n;
import p026b.p027a.p030b.p031a.C0632a;

public class FontsContractCompat {
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    public static final String PARCEL_FONT_RESULTS = "font_results";
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS);
    private static final Comparator sByteArrayComparator = new Comparator() {
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
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontsContractCompat.C03445.compare(byte[], byte[]):int");
        }
    };
    private static Executor sExecutor;
    static final Object sLock = new Object();
    static final C0027n sPendingReplies = new C0027n();
    static final C0020g sTypefaceCache = new C0020g(16);

    public final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    public class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        public FontFamilyResult(int i, FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = fontInfoArr;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }
    }

    public class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        public FontInfo(Uri uri, int i, int i2, boolean z, int i3) {
            if (uri != null) {
                this.mUri = uri;
                this.mTtcIndex = i;
                this.mWeight = i2;
                this.mItalic = z;
                this.mResultCode = i3;
                return;
            }
            throw new NullPointerException();
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    public class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        public static final int RESULT_OK = 0;

        @Retention(RetentionPolicy.SOURCE)
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int i) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }

    interface OnCompletedCallback {
        void onCompleted(Object obj);
    }

    final class OnFetchCompletedAndFirePendingReplyCallback implements OnCompletedCallback {
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
                a.b.n r1 = androidx.core.provider.FontsContractCompat.sPendingReplies     // Catch:{ all -> 0x002d }
                java.lang.String r2 = r3.mCacheId     // Catch:{ all -> 0x002d }
                java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x002d }
                java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x002d }
                if (r1 != 0) goto L_0x0011
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return
            L_0x0011:
                a.b.n r2 = androidx.core.provider.FontsContractCompat.sPendingReplies     // Catch:{ all -> 0x002d }
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

    final class SyncFontFetchTask extends FutureTask {

        final class CallableWrapper implements Callable {
            private final Callable mOriginalCallback;
            private final OnCompletedCallback mTypefaceResultOnCompletedCallback;

            CallableWrapper(Callable callable, OnCompletedCallback onCompletedCallback) {
                this.mOriginalCallback = callable;
                this.mTypefaceResultOnCompletedCallback = onCompletedCallback;
            }

            public TypefaceResult call() {
                TypefaceResult typefaceResult = (TypefaceResult) this.mOriginalCallback.call();
                this.mTypefaceResultOnCompletedCallback.onCompleted(typefaceResult);
                return typefaceResult;
            }
        }

        SyncFontFetchTask(SyncFontFetchTaskCallable syncFontFetchTaskCallable) {
            super(syncFontFetchTaskCallable);
        }

        SyncFontFetchTask(SyncFontFetchTaskCallable syncFontFetchTaskCallable, OnCompletedCallback onCompletedCallback) {
            super(new CallableWrapper(syncFontFetchTaskCallable, onCompletedCallback));
        }
    }

    final class SyncFontFetchTaskCallable implements Callable {
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

        public TypefaceResult call() {
            TypefaceResult fontInternal = FontsContractCompat.getFontInternal(this.mAppContext, this.mRequest, this.mStyle);
            Typeface typeface = fontInternal.mTypeface;
            if (typeface != null) {
                FontsContractCompat.sTypefaceCache.put(this.mCacheId, typeface);
            }
            return fontInternal;
        }
    }

    final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(Typeface typeface, int i) {
            this.mTypeface = typeface;
            this.mResult = i;
        }
    }

    private FontsContractCompat() {
    }

    public static Typeface buildTypeface(Context context, CancellationSignal cancellationSignal, FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    private static List convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    private static boolean equalsByteArrayList(List list, List list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals((byte[]) list.get(i), (byte[]) list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static FontFamilyResult fetchFonts(Context context, CancellationSignal cancellationSignal, FontRequest fontRequest) {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, (FontInfo[]) null);
        }
        return new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    private static List getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return C0308a.m243a(resources, fontRequest.getCertificatesArrayResId());
    }

    static FontInfo[] getFontFromProvider(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        Uri uri;
        String str2 = str;
        ArrayList arrayList = new ArrayList();
        Uri build = new Uri.Builder().scheme("content").authority(str2).build();
        Uri build2 = new Uri.Builder().scheme("content").authority(str2).appendPath("file").build();
        Cursor cursor = null;
        try {
            int i = Build.VERSION.SDK_INT;
            cursor = context.getContentResolver().query(build, new String[]{"_id", Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, (String) null, cancellationSignal);
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex(Columns.RESULT_CODE);
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex(Columns.FILE_ID);
                int columnIndex4 = cursor.getColumnIndex(Columns.TTC_INDEX);
                int columnIndex5 = cursor.getColumnIndex(Columns.WEIGHT);
                int columnIndex6 = cursor.getColumnIndex(Columns.ITALIC);
                while (cursor.moveToNext()) {
                    int i2 = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                    int i3 = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        uri = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                    } else {
                        uri = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                    }
                    arrayList2.add(new FontInfo(uri, i3, columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1, i2));
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

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b0, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c1, code lost:
        if (r10 != null) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00c3, code lost:
        sBackgroundThread.postAndReply(r2, new androidx.core.provider.FontsContractCompat.C03333());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ce, code lost:
        r10.execute(new androidx.core.provider.FontsContractCompat.SyncFontFetchTask(r2, new androidx.core.provider.FontsContractCompat.OnFetchCompletedAndFirePendingReplyCallback(r0)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00db, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Typeface getFontSync(android.content.Context r3, androidx.core.provider.FontRequest r4, final androidx.core.content.p022a.C0318k r5, final android.os.Handler r6, boolean r7, int r8, int r9, boolean r10) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r4.getIdentifier()
            r0.append(r1)
            java.lang.String r1 = "-"
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            a.b.g r1 = sTypefaceCache
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
            r3.<init>(r6)
            goto L_0x009d
        L_0x0096:
            androidx.core.provider.FontsContractCompat$2 r3 = new androidx.core.provider.FontsContractCompat$2
            r3.<init>()
            goto L_0x009d
        L_0x009c:
            r3 = r1
        L_0x009d:
            java.lang.Object r4 = sLock
            monitor-enter(r4)
            a.b.n r5 = sPendingReplies     // Catch:{ all -> 0x00dc }
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
            a.b.n r3 = sPendingReplies     // Catch:{ all -> 0x00dc }
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontsContractCompat.getFontSync(android.content.Context, androidx.core.provider.FontRequest, androidx.core.content.a.k, android.os.Handler, boolean, int, int, boolean):android.graphics.Typeface");
    }

    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException(C0632a.m1025k("No package found for authority: ", providerAuthority));
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            List convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(convertToByteArrayList, sByteArrayComparator);
            List certificates = getCertificates(fontRequest, resources);
            for (int i = 0; i < certificates.size(); i++) {
                ArrayList arrayList = new ArrayList((Collection) certificates.get(i));
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

    public static Map prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
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

    public static void requestFont(Context context, FontRequest fontRequest, FontRequestCallback fontRequestCallback, Handler handler) {
        requestFontInternal(context.getApplicationContext(), fontRequest, fontRequestCallback, handler);
    }

    private static void requestFontInternal(final Context context, final FontRequest fontRequest, final FontRequestCallback fontRequestCallback, Handler handler) {
        final Handler handler2 = new Handler();
        handler.post(new Runnable() {
            public void run() {
                try {
                    FontFamilyResult fetchFonts = FontsContractCompat.fetchFonts(context, (CancellationSignal) null, fontRequest);
                    if (fetchFonts.getStatusCode() != 0) {
                        int statusCode = fetchFonts.getStatusCode();
                        if (statusCode == 1) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-2);
                                }
                            });
                        } else if (statusCode != 2) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                        } else {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                        }
                    } else {
                        FontInfo[] fonts = fetchFonts.getFonts();
                        if (fonts == null || fonts.length == 0) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(1);
                                }
                            });
                            return;
                        }
                        int length = fonts.length;
                        int i = 0;
                        while (i < length) {
                            FontInfo fontInfo = fonts[i];
                            if (fontInfo.getResultCode() != 0) {
                                final int resultCode = fontInfo.getResultCode();
                                if (resultCode < 0) {
                                    handler2.post(new Runnable() {
                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(-3);
                                        }
                                    });
                                    return;
                                } else {
                                    handler2.post(new Runnable() {
                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(resultCode);
                                        }
                                    });
                                    return;
                                }
                            } else {
                                i++;
                            }
                        }
                        final Typeface buildTypeface = FontsContractCompat.buildTypeface(context, (CancellationSignal) null, fonts);
                        if (buildTypeface == null) {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                        } else {
                            handler2.post(new Runnable() {
                                public void run() {
                                    fontRequestCallback.onTypefaceRetrieved(buildTypeface);
                                }
                            });
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    handler2.post(new Runnable() {
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-1);
                        }
                    });
                }
            }
        });
    }

    public static void resetCache() {
        sTypefaceCache.evictAll();
    }

    public static Typeface getFontSync(Context context, FontRequest fontRequest, C0318k kVar, Handler handler, boolean z, int i, int i2) {
        return getFontSync(context, fontRequest, kVar, handler, z, i, i2, false);
    }
}
