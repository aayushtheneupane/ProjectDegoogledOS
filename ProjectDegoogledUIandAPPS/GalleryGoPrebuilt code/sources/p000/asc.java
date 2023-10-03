package p000;

import android.content.Context;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: asc */
/* compiled from: PG */
public final class asc implements ari {

    /* renamed from: a */
    private final Uri f1509a;

    /* renamed from: b */
    private final ase f1510b;

    /* renamed from: c */
    private InputStream f1511c;

    private asc(Uri uri, ase ase) {
        this.f1509a = uri;
        this.f1510b = ase;
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return InputStream.class;
    }

    /* renamed from: c */
    public final void mo1517c() {
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    /* renamed from: a */
    public static asc m1549a(Context context, Uri uri, asd asd) {
        return new asc(uri, new ase(aow.m1346a(context).f1291d.mo1403a(), asd, aow.m1346a(context).f1292e, context.getContentResolver()));
    }

    /* renamed from: b */
    public final void mo1516b() {
        InputStream inputStream = this.f1511c;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: aro} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002c A[Catch:{ NullPointerException -> 0x005d, FileNotFoundException -> 0x00ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0034 A[Catch:{ NullPointerException -> 0x005d, FileNotFoundException -> 0x00ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003d A[Catch:{ NullPointerException -> 0x005d, FileNotFoundException -> 0x00ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a3 A[Catch:{ NullPointerException -> 0x005d, FileNotFoundException -> 0x00ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c8 A[SYNTHETIC, Splitter:B:57:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00d4 A[SYNTHETIC, Splitter:B:64:0x00d4] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00e0 A[SYNTHETIC, Splitter:B:71:0x00e0] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1514a(p000.apb r9, p000.arh r10) {
        /*
            r8 = this;
            ase r9 = r8.f1510b     // Catch:{ FileNotFoundException -> 0x00ec }
            android.net.Uri r0 = r8.f1509a     // Catch:{ FileNotFoundException -> 0x00ec }
            r1 = 0
            asd r2 = r9.f1512a     // Catch:{ SecurityException -> 0x0030, all -> 0x0029 }
            android.database.Cursor r2 = r2.mo1542a(r0)     // Catch:{ SecurityException -> 0x0030, all -> 0x0029 }
            if (r2 != 0) goto L_0x000e
            goto L_0x001d
        L_0x000e:
            boolean r3 = r2.moveToFirst()     // Catch:{ SecurityException -> 0x0027, all -> 0x0024 }
            if (r3 == 0) goto L_0x001d
            r3 = 0
            java.lang.String r3 = r2.getString(r3)     // Catch:{ SecurityException -> 0x0027, all -> 0x0024 }
            r2.close()     // Catch:{ FileNotFoundException -> 0x00ec }
            goto L_0x0037
        L_0x001d:
            if (r2 == 0) goto L_0x0023
        L_0x001f:
            r2.close()     // Catch:{ FileNotFoundException -> 0x00ec }
            goto L_0x0036
        L_0x0023:
            goto L_0x0036
        L_0x0024:
            r9 = move-exception
            r1 = r2
            goto L_0x002a
        L_0x0027:
            r3 = move-exception
            goto L_0x0032
        L_0x0029:
            r9 = move-exception
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.close()     // Catch:{ FileNotFoundException -> 0x00ec }
        L_0x002f:
            throw r9     // Catch:{ FileNotFoundException -> 0x00ec }
        L_0x0030:
            r2 = move-exception
            r2 = r1
        L_0x0032:
            if (r2 == 0) goto L_0x0035
            goto L_0x001f
        L_0x0035:
        L_0x0036:
            r3 = r1
        L_0x0037:
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch:{ FileNotFoundException -> 0x00ec }
            if (r2 != 0) goto L_0x009f
            java.io.File r2 = new java.io.File     // Catch:{ FileNotFoundException -> 0x00ec }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00ec }
            boolean r3 = r2.exists()     // Catch:{ FileNotFoundException -> 0x00ec }
            if (r3 == 0) goto L_0x009f
            long r3 = r2.length()     // Catch:{ FileNotFoundException -> 0x00ec }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x009e
            android.net.Uri r2 = android.net.Uri.fromFile(r2)     // Catch:{ FileNotFoundException -> 0x00ec }
            android.content.ContentResolver r9 = r9.f1514c     // Catch:{ NullPointerException -> 0x005d }
            java.io.InputStream r9 = r9.openInputStream(r2)     // Catch:{ NullPointerException -> 0x005d }
            goto L_0x00a0
        L_0x005d:
            r9 = move-exception
            java.io.FileNotFoundException r1 = new java.io.FileNotFoundException     // Catch:{ FileNotFoundException -> 0x00ec }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ FileNotFoundException -> 0x00ec }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ FileNotFoundException -> 0x00ec }
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ FileNotFoundException -> 0x00ec }
            int r3 = r3.length()     // Catch:{ FileNotFoundException -> 0x00ec }
            int r3 = r3 + 21
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ FileNotFoundException -> 0x00ec }
            int r4 = r4.length()     // Catch:{ FileNotFoundException -> 0x00ec }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00ec }
            r4.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00ec }
            java.lang.String r3 = "NPE opening uri: "
            r4.append(r3)     // Catch:{ FileNotFoundException -> 0x00ec }
            r4.append(r0)     // Catch:{ FileNotFoundException -> 0x00ec }
            java.lang.String r0 = " -> "
            r4.append(r0)     // Catch:{ FileNotFoundException -> 0x00ec }
            r4.append(r2)     // Catch:{ FileNotFoundException -> 0x00ec }
            java.lang.String r0 = r4.toString()     // Catch:{ FileNotFoundException -> 0x00ec }
            r1.<init>(r0)     // Catch:{ FileNotFoundException -> 0x00ec }
            java.lang.Throwable r9 = r1.initCause(r9)     // Catch:{ FileNotFoundException -> 0x00ec }
            java.io.FileNotFoundException r9 = (java.io.FileNotFoundException) r9     // Catch:{ FileNotFoundException -> 0x00ec }
            throw r9     // Catch:{ FileNotFoundException -> 0x00ec }
        L_0x009e:
        L_0x009f:
            r9 = r1
        L_0x00a0:
            r0 = -1
            if (r9 == 0) goto L_0x00dd
            ase r2 = r8.f1510b     // Catch:{ FileNotFoundException -> 0x00ec }
            android.net.Uri r3 = r8.f1509a     // Catch:{ FileNotFoundException -> 0x00ec }
            android.content.ContentResolver r4 = r2.f1514c     // Catch:{ IOException -> 0x00d0, NullPointerException -> 0x00ce, all -> 0x00c5 }
            java.io.InputStream r1 = r4.openInputStream(r3)     // Catch:{ IOException -> 0x00d0, NullPointerException -> 0x00ce, all -> 0x00c5 }
            java.util.List r3 = r2.f1515d     // Catch:{ IOException -> 0x00c3, NullPointerException -> 0x00c1, all -> 0x00be }
            aui r2 = r2.f1513b     // Catch:{ IOException -> 0x00c3, NullPointerException -> 0x00c1, all -> 0x00be }
            int r2 = p000.C0652xy.m16069b(r3, r1, r2)     // Catch:{ IOException -> 0x00c3, NullPointerException -> 0x00c1, all -> 0x00be }
            if (r1 != 0) goto L_0x00b8
        L_0x00b7:
            goto L_0x00de
        L_0x00b8:
            r1.close()     // Catch:{ IOException -> 0x00bc }
            goto L_0x00b7
        L_0x00bc:
            r1 = move-exception
            goto L_0x00de
        L_0x00be:
            r9 = move-exception
            goto L_0x00c6
        L_0x00c1:
            r2 = move-exception
            goto L_0x00c4
        L_0x00c3:
            r2 = move-exception
        L_0x00c4:
            goto L_0x00d1
        L_0x00c5:
            r9 = move-exception
        L_0x00c6:
            if (r1 == 0) goto L_0x00cd
            r1.close()     // Catch:{ IOException -> 0x00cc }
            goto L_0x00cd
        L_0x00cc:
            r0 = move-exception
        L_0x00cd:
            throw r9     // Catch:{ FileNotFoundException -> 0x00ec }
        L_0x00ce:
            r2 = move-exception
            goto L_0x00d1
        L_0x00d0:
            r2 = move-exception
        L_0x00d1:
            if (r1 != 0) goto L_0x00d4
            goto L_0x00dd
        L_0x00d4:
            r1.close()     // Catch:{ IOException -> 0x00da }
            r2 = -1
            goto L_0x00de
        L_0x00da:
            r1 = move-exception
            r2 = -1
            goto L_0x00de
        L_0x00dd:
            r2 = -1
        L_0x00de:
            if (r2 == r0) goto L_0x00e6
            aro r0 = new aro     // Catch:{ FileNotFoundException -> 0x00ec }
            r0.<init>(r9, r2)     // Catch:{ FileNotFoundException -> 0x00ec }
            r9 = r0
        L_0x00e6:
            r8.f1511c = r9     // Catch:{ FileNotFoundException -> 0x00ec }
            r10.mo1525a((java.lang.Object) r9)
            return
        L_0x00ec:
            r9 = move-exception
            r10.mo1524a((java.lang.Exception) r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.asc.mo1514a(apb, arh):void");
    }
}
