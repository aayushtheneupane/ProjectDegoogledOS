package android.support.p001v4.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.util.HashMap;

/* renamed from: android.support.v4.content.FileProvider */
/* compiled from: PG */
public class FileProvider extends ContentProvider {

    /* renamed from: a */
    private static final String[] f825a = {"_display_name", "_size"};

    /* renamed from: b */
    private static final File f826b = new File("/");

    /* renamed from: c */
    private static HashMap f827c = new HashMap();

    /* renamed from: d */
    private C0220hy f828d;

    public final boolean onCreate() {
        return true;
    }

    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (providerInfo.grantUriPermissions) {
            this.f828d = m807a(context, providerInfo.authority);
        } else {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        return this.f828d.mo8261a(uri).delete() ? 1 : 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        f827c.put(r11, r1);
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0122, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x012a, code lost:
        throw new java.lang.IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", r10);
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0122 A[ExcHandler: XmlPullParserException (r10v3 'e' org.xmlpull.v1.XmlPullParserException A[CUSTOM_DECLARE]), Splitter:B:5:0x000d] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.C0220hy m807a(android.content.Context r10, java.lang.String r11) {
        /*
            java.util.HashMap r0 = f827c
            monitor-enter(r0)
            java.util.HashMap r1 = f827c     // Catch:{ all -> 0x0137 }
            java.lang.Object r1 = r1.get(r11)     // Catch:{ all -> 0x0137 }
            hy r1 = (p000.C0220hy) r1     // Catch:{ all -> 0x0137 }
            if (r1 != 0) goto L_0x0134
            hz r1 = new hz     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r1.<init>(r11)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            android.content.pm.PackageManager r2 = r10.getPackageManager()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r3 = 128(0x80, float:1.794E-43)
            android.content.pm.ProviderInfo r2 = r2.resolveContentProvider(r11, r3)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r2 == 0) goto L_0x010b
            android.content.pm.PackageManager r3 = r10.getPackageManager()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r4 = "android.support.FILE_PROVIDER_PATHS"
            android.content.res.XmlResourceParser r2 = r2.loadXmlMetaData(r3, r4)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r2 == 0) goto L_0x0103
        L_0x002a:
            int r3 = r2.next()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r4 = 1
            if (r3 != r4) goto L_0x0038
            java.util.HashMap r10 = f827c     // Catch:{ all -> 0x0137 }
            r10.put(r11, r1)     // Catch:{ all -> 0x0137 }
            goto L_0x0134
        L_0x0038:
            r5 = 2
            if (r3 != r5) goto L_0x002a
            java.lang.String r3 = r2.getName()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r5 = "name"
            r6 = 0
            java.lang.String r5 = r2.getAttributeValue(r6, r5)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r7 = "path"
            java.lang.String r7 = r2.getAttributeValue(r6, r7)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r8 = "root-path"
            boolean r8 = r8.equals(r3)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r9 = 0
            if (r8 != 0) goto L_0x00b9
            java.lang.String r8 = "files-path"
            boolean r8 = r8.equals(r3)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r8 != 0) goto L_0x00b4
            java.lang.String r8 = "cache-path"
            boolean r8 = r8.equals(r3)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r8 != 0) goto L_0x00af
            java.lang.String r8 = "external-path"
            boolean r8 = r8.equals(r3)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r8 != 0) goto L_0x00aa
            java.lang.String r8 = "external-files-path"
            boolean r8 = r8.equals(r3)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r8 == 0) goto L_0x0081
            java.io.File[] r3 = p000.C0071co.m4676d(r10)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            int r8 = r3.length     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r8 <= 0) goto L_0x007f
            r6 = r3[r9]     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            goto L_0x0080
        L_0x007f:
        L_0x0080:
            goto L_0x00bb
        L_0x0081:
            java.lang.String r8 = "external-cache-path"
            boolean r8 = r8.equals(r3)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r8 != 0) goto L_0x009e
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r8 = "external-media-path"
            boolean r3 = r8.equals(r3)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r3 == 0) goto L_0x0080
            java.io.File[] r3 = r10.getExternalMediaDirs()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            int r8 = r3.length     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r8 <= 0) goto L_0x009d
            r6 = r3[r9]     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            goto L_0x00bb
        L_0x009d:
            goto L_0x0080
        L_0x009e:
            java.io.File[] r3 = p000.C0071co.m4671b((android.content.Context) r10)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            int r8 = r3.length     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r8 > 0) goto L_0x00a6
            goto L_0x0080
        L_0x00a6:
            r6 = r3[r9]     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            goto L_0x00bb
        L_0x00aa:
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            goto L_0x00bb
        L_0x00af:
            java.io.File r6 = r10.getCacheDir()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            goto L_0x00bb
        L_0x00b4:
            java.io.File r6 = r10.getFilesDir()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            goto L_0x00bb
        L_0x00b9:
            java.io.File r6 = f826b     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
        L_0x00bb:
            if (r6 == 0) goto L_0x002a
            java.lang.String[] r3 = new java.lang.String[r4]     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r3[r9] = r7     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r7 = 0
        L_0x00c2:
            if (r7 > 0) goto L_0x00d2
            r7 = r3[r9]     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r7 == 0) goto L_0x00ce
            java.io.File r8 = new java.io.File     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r8.<init>(r6, r7)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r6 = r8
        L_0x00ce:
            r7 = 1
            goto L_0x00c2
        L_0x00d2:
            boolean r3 = android.text.TextUtils.isEmpty(r5)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            if (r3 != 0) goto L_0x00fb
            java.io.File r3 = r6.getCanonicalFile()     // Catch:{ IOException -> 0x00e3, XmlPullParserException -> 0x0122 }
            java.util.HashMap r4 = r1.f13670a     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r4.put(r5, r3)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            goto L_0x002a
        L_0x00e3:
            r10 = move-exception
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r1.<init>()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r2 = "Failed to resolve canonical path for "
            r1.append(r2)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r1.append(r6)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r11.<init>(r1, r10)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            throw r11     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
        L_0x00fb:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r11 = "Name must not be empty"
            r10.<init>(r11)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            throw r10     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
        L_0x0103:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r11 = "Missing android.support.FILE_PROVIDER_PATHS meta-data"
            r10.<init>(r11)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            throw r10     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
        L_0x010b:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r1.<init>()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r2 = "Couldn't find meta-data for provider with authority "
            r1.append(r2)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r1.append(r11)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            java.lang.String r11 = r1.toString()     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            r10.<init>(r11)     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
            throw r10     // Catch:{ IOException -> 0x012b, XmlPullParserException -> 0x0122 }
        L_0x0122:
            r10 = move-exception
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0137 }
            java.lang.String r1 = "Failed to parse android.support.FILE_PROVIDER_PATHS meta-data"
            r11.<init>(r1, r10)     // Catch:{ all -> 0x0137 }
            throw r11     // Catch:{ all -> 0x0137 }
        L_0x012b:
            r10 = move-exception
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0137 }
            java.lang.String r1 = "Failed to parse android.support.FILE_PROVIDER_PATHS meta-data"
            r11.<init>(r1, r10)     // Catch:{ all -> 0x0137 }
            throw r11     // Catch:{ all -> 0x0137 }
        L_0x0134:
            monitor-exit(r0)     // Catch:{ all -> 0x0137 }
            return r1
        L_0x0137:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0137 }
            goto L_0x013b
        L_0x013a:
            throw r10
        L_0x013b:
            goto L_0x013a
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p001v4.content.FileProvider.m807a(android.content.Context, java.lang.String):hy");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0012, code lost:
        r3 = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(r3.getName().substring(r0 + 1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getType(android.net.Uri r3) {
        /*
            r2 = this;
            hy r0 = r2.f828d
            java.io.File r3 = r0.mo8261a((android.net.Uri) r3)
            java.lang.String r0 = r3.getName()
            r1 = 46
            int r0 = r0.lastIndexOf(r1)
            if (r0 < 0) goto L_0x0028
            java.lang.String r3 = r3.getName()
            int r0 = r0 + 1
            java.lang.String r3 = r3.substring(r0)
            android.webkit.MimeTypeMap r0 = android.webkit.MimeTypeMap.getSingleton()
            java.lang.String r3 = r0.getMimeTypeFromExtension(r3)
            if (r3 != 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            return r3
        L_0x0028:
            java.lang.String r3 = "application/octet-stream"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p001v4.content.FileProvider.getType(android.net.Uri):java.lang.String");
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    public final ParcelFileDescriptor openFile(Uri uri, String str) {
        File a = this.f828d.mo8261a(uri);
        int i = 738197504;
        if ("r".equals(str)) {
            i = 268435456;
        } else if (!"w".equals(str) && !"wt".equals(str)) {
            if ("wa".equals(str)) {
                i = 704643072;
            } else if ("rw".equals(str)) {
                i = 939524096;
            } else if ("rwt".equals(str)) {
                i = 1006632960;
            } else {
                throw new IllegalArgumentException("Invalid mode: " + str);
            }
        }
        return ParcelFileDescriptor.open(a, i);
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        File a = this.f828d.mo8261a(uri);
        if (strArr == null) {
            strArr = f825a;
        }
        String[] strArr3 = new String[r9];
        Object[] objArr = new Object[r9];
        int i = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i] = "_display_name";
                objArr[i] = a.getName();
                i++;
            } else if ("_size".equals(str3)) {
                strArr3[i] = "_size";
                objArr[i] = Long.valueOf(a.length());
                i++;
            }
        }
        String[] strArr4 = new String[i];
        System.arraycopy(strArr3, 0, strArr4, 0, i);
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        MatrixCursor matrixCursor = new MatrixCursor(strArr4, 1);
        matrixCursor.addRow(objArr2);
        return matrixCursor;
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
