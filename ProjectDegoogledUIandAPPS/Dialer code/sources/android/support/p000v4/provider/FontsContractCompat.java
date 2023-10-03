package android.support.p000v4.provider;

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
import android.support.p000v4.content.res.FontResourcesParserCompat;
import android.support.p000v4.graphics.PathParser;
import android.support.p000v4.graphics.TypefaceCompat;
import android.support.p000v4.provider.SelfDestructiveThread;
import android.support.p000v4.util.LruCache;
import android.support.p000v4.util.SimpleArrayMap;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: android.support.v4.provider.FontsContractCompat */
public class FontsContractCompat {
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() {
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: byte} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int compare(java.lang.Object r4, java.lang.Object r5) {
            /*
                r3 = this;
                byte[] r4 = (byte[]) r4
                byte[] r5 = (byte[]) r5
                int r3 = r4.length
                int r0 = r5.length
                if (r3 == r0) goto L_0x000b
                int r3 = r4.length
                int r4 = r5.length
                goto L_0x001a
            L_0x000b:
                r3 = 0
                r0 = r3
            L_0x000d:
                int r1 = r4.length
                if (r0 >= r1) goto L_0x001f
                byte r1 = r4[r0]
                byte r2 = r5[r0]
                if (r1 == r2) goto L_0x001c
                byte r3 = r4[r0]
                byte r4 = r5[r0]
            L_0x001a:
                int r3 = r3 - r4
                goto L_0x001f
            L_0x001c:
                int r0 = r0 + 1
                goto L_0x000d
            L_0x001f:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.provider.FontsContractCompat.C01335.compare(java.lang.Object, java.lang.Object):int");
        }
    };
    static final Object sLock = new Object();
    static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> sPendingReplies = new SimpleArrayMap<>();
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);

    /* renamed from: android.support.v4.provider.FontsContractCompat$FontFamilyResult */
    public static class FontFamilyResult {
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

    /* renamed from: android.support.v4.provider.FontsContractCompat$FontInfo */
    public static class FontInfo {
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

    /* renamed from: android.support.v4.provider.FontsContractCompat$TypefaceResult */
    private static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(Typeface typeface, int i) {
            this.mTypeface = typeface;
            this.mResult = i;
        }
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
            cursor = context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest.getQuery()}, (String) null, cancellationSignal);
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex("result_code");
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex("file_id");
                int columnIndex4 = cursor.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursor.getColumnIndex("font_weight");
                int columnIndex6 = cursor.getColumnIndex("font_italic");
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
        FontFamilyResult fontFamilyResult;
        try {
            ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
            int i2 = 0;
            if (provider == null) {
                fontFamilyResult = new FontFamilyResult(1, (FontInfo[]) null);
            } else {
                fontFamilyResult = new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, (CancellationSignal) null));
            }
            int i3 = -3;
            if (fontFamilyResult.getStatusCode() == 0) {
                Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, (CancellationSignal) null, fontFamilyResult.getFonts(), i);
                if (createFromFontInfo == null) {
                    i2 = -3;
                }
                return new TypefaceResult(createFromFontInfo, i2);
            }
            if (fontFamilyResult.getStatusCode() == 1) {
                i3 = -2;
            }
            return new TypefaceResult((Typeface) null, i3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult((Typeface) null, -1);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008e, code lost:
        sBackgroundThread.postAndReply(r1, new android.support.p000v4.provider.FontsContractCompat.C01323());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0098, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Typeface getFontSync(final android.content.Context r2, final android.support.p000v4.provider.FontRequest r3, final android.support.p000v4.content.res.ResourcesCompat$FontCallback r4, final android.os.Handler r5, boolean r6, int r7, final int r8) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r3.getIdentifier()
            r0.append(r1)
            java.lang.String r1 = "-"
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            android.support.v4.util.LruCache<java.lang.String, android.graphics.Typeface> r1 = sTypefaceCache
            java.lang.Object r1 = r1.get(r0)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L_0x0028
            if (r4 == 0) goto L_0x0027
            r4.onFontRetrieved(r1)
        L_0x0027:
            return r1
        L_0x0028:
            if (r6 == 0) goto L_0x0043
            r1 = -1
            if (r7 != r1) goto L_0x0043
            android.support.v4.provider.FontsContractCompat$TypefaceResult r2 = getFontInternal(r2, r3, r8)
            if (r4 == 0) goto L_0x0040
            int r3 = r2.mResult
            if (r3 != 0) goto L_0x003d
            android.graphics.Typeface r3 = r2.mTypeface
            r4.callbackSuccessAsync(r3, r5)
            goto L_0x0040
        L_0x003d:
            r4.callbackFailAsync(r3, r5)
        L_0x0040:
            android.graphics.Typeface r2 = r2.mTypeface
            return r2
        L_0x0043:
            android.support.v4.provider.FontsContractCompat$1 r1 = new android.support.v4.provider.FontsContractCompat$1
            r1.<init>(r2, r3, r8, r0)
            r2 = 0
            if (r6 == 0) goto L_0x0056
            android.support.v4.provider.SelfDestructiveThread r3 = sBackgroundThread     // Catch:{ InterruptedException -> 0x0055 }
            java.lang.Object r3 = r3.postAndWait(r1, r7)     // Catch:{ InterruptedException -> 0x0055 }
            android.support.v4.provider.FontsContractCompat$TypefaceResult r3 = (android.support.p000v4.provider.FontsContractCompat.TypefaceResult) r3     // Catch:{ InterruptedException -> 0x0055 }
            android.graphics.Typeface r2 = r3.mTypeface     // Catch:{ InterruptedException -> 0x0055 }
        L_0x0055:
            return r2
        L_0x0056:
            if (r4 != 0) goto L_0x005a
            r3 = r2
            goto L_0x005f
        L_0x005a:
            android.support.v4.provider.FontsContractCompat$2 r3 = new android.support.v4.provider.FontsContractCompat$2
            r3.<init>(r4, r5)
        L_0x005f:
            java.lang.Object r4 = sLock
            monitor-enter(r4)
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.util.ArrayList<android.support.v4.provider.SelfDestructiveThread$ReplyCallback<android.support.v4.provider.FontsContractCompat$TypefaceResult>>> r5 = sPendingReplies     // Catch:{ all -> 0x0099 }
            int r5 = r5.indexOfKey(r0)     // Catch:{ all -> 0x0099 }
            if (r5 < 0) goto L_0x006c
            r5 = 1
            goto L_0x006d
        L_0x006c:
            r5 = 0
        L_0x006d:
            if (r5 == 0) goto L_0x007e
            if (r3 == 0) goto L_0x007c
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.util.ArrayList<android.support.v4.provider.SelfDestructiveThread$ReplyCallback<android.support.v4.provider.FontsContractCompat$TypefaceResult>>> r5 = sPendingReplies     // Catch:{ all -> 0x0099 }
            java.lang.Object r5 = r5.get(r0)     // Catch:{ all -> 0x0099 }
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ all -> 0x0099 }
            r5.add(r3)     // Catch:{ all -> 0x0099 }
        L_0x007c:
            monitor-exit(r4)     // Catch:{ all -> 0x0099 }
            return r2
        L_0x007e:
            if (r3 == 0) goto L_0x008d
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0099 }
            r5.<init>()     // Catch:{ all -> 0x0099 }
            r5.add(r3)     // Catch:{ all -> 0x0099 }
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.util.ArrayList<android.support.v4.provider.SelfDestructiveThread$ReplyCallback<android.support.v4.provider.FontsContractCompat$TypefaceResult>>> r3 = sPendingReplies     // Catch:{ all -> 0x0099 }
            r3.put(r0, r5)     // Catch:{ all -> 0x0099 }
        L_0x008d:
            monitor-exit(r4)     // Catch:{ all -> 0x0099 }
            android.support.v4.provider.SelfDestructiveThread r3 = sBackgroundThread
            android.support.v4.provider.FontsContractCompat$3 r4 = new android.support.v4.provider.FontsContractCompat$3
            r4.<init>(r0)
            r3.postAndReply(r1, r4)
            return r2
        L_0x0099:
            r2 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0099 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.provider.FontsContractCompat.getFontSync(android.content.Context, android.support.v4.provider.FontRequest, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, int, int):android.graphics.Typeface");
    }

    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws PackageManager.NameNotFoundException {
        List<List<byte[]>> list;
        boolean z;
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException(GeneratedOutlineSupport.outline8("No package found for authority: ", providerAuthority));
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            Signature[] signatureArr = packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures;
            ArrayList arrayList = new ArrayList();
            for (Signature byteArray : signatureArr) {
                arrayList.add(byteArray.toByteArray());
            }
            Collections.sort(arrayList, sByteArrayComparator);
            if (fontRequest.getCertificates() != null) {
                list = fontRequest.getCertificates();
            } else {
                list = FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
            }
            for (int i = 0; i < list.size(); i++) {
                ArrayList arrayList2 = new ArrayList(list.get(i));
                Collections.sort(arrayList2, sByteArrayComparator);
                if (arrayList.size() == arrayList2.size()) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            z = true;
                            break;
                        } else if (!Arrays.equals((byte[]) arrayList.get(i2), (byte[]) arrayList2.get(i2))) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                z = false;
                if (z) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
    }

    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, PathParser.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }
}
