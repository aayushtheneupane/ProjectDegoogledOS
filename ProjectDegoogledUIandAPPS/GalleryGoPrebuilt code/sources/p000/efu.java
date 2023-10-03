package p000;

import android.content.Context;

/* renamed from: efu */
/* compiled from: PG */
public final class efu {

    /* renamed from: a */
    public final Context f8173a;

    public efu(Context context) {
        this.f8173a = context;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00bf A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x000e A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.eft mo4788a(java.io.File r9) {
        /*
            r8 = this;
            android.content.Context r0 = r8.f8173a
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.util.List r0 = r0.getPersistedUriPermissions()
            java.util.Iterator r0 = r0.iterator()
        L_0x000e:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x00c0
            java.lang.Object r1 = r0.next()
            android.content.UriPermission r1 = (android.content.UriPermission) r1
            android.net.Uri r3 = r1.getUri()
            boolean r1 = r1.isWritePermission()
            if (r1 == 0) goto L_0x000e
            boolean r1 = android.provider.DocumentsContract.isTreeUri(r3)
            if (r1 == 0) goto L_0x000e
            android.content.Context r1 = r8.f8173a
            abt r1 = p000.abt.m164a(r1, r3)
            java.lang.String r4 = r9.getAbsolutePath()
            android.net.Uri r4 = android.net.Uri.parse(r4)
            java.util.List r4 = r4.getPathSegments()
            boolean r5 = r4.isEmpty()
            if (r5 != 0) goto L_0x00a1
            r5 = 0
            java.lang.Object r6 = r4.get(r5)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r7 = "storage"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0053
            goto L_0x00a1
        L_0x0053:
            java.lang.String r3 = r3.getLastPathSegment()
            if (r3 == 0) goto L_0x009c
            java.lang.String r6 = ":"
            java.lang.String r7 = "/"
            java.lang.String r3 = r3.replaceFirst(r6, r7)
            java.lang.String[] r3 = r3.split(r7, r5)
            int r6 = r4.size()
            int r7 = r3.length
            if (r6 > r7) goto L_0x0071
            j$.util.Optional r3 = p003j$.util.Optional.empty()
            goto L_0x00a5
        L_0x0071:
        L_0x0072:
            int r6 = r3.length
            if (r5 >= r6) goto L_0x008c
            int r6 = r5 + 1
            java.lang.Object r7 = r4.get(r6)
            java.lang.String r7 = (java.lang.String) r7
            r5 = r3[r5]
            boolean r5 = r7.equals(r5)
            if (r5 != 0) goto L_0x008a
            j$.util.Optional r3 = p003j$.util.Optional.empty()
            goto L_0x00a5
        L_0x008a:
            r5 = r6
            goto L_0x0072
        L_0x008c:
            int r6 = r6 + 1
            int r3 = r4.size()
            java.util.List r3 = r4.subList(r6, r3)
            j$.util.Optional r3 = p003j$.util.Optional.m16285of(r3)
            goto L_0x00a5
        L_0x009c:
            j$.util.Optional r3 = p003j$.util.Optional.empty()
            goto L_0x00a5
        L_0x00a1:
            j$.util.Optional r3 = p003j$.util.Optional.empty()
        L_0x00a5:
            boolean r4 = r3.isPresent()
            if (r4 == 0) goto L_0x00bc
            java.lang.Object r2 = r3.get()
            java.util.List r2 = (java.util.List) r2
            efm r3 = new efm
            hso r2 = p000.hso.m12041a((java.util.Collection) r2)
            r3.<init>(r1, r2)
            r2 = r3
            goto L_0x00bd
        L_0x00bc:
        L_0x00bd:
            if (r2 == 0) goto L_0x000e
            return r2
        L_0x00c0:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.efu.mo4788a(java.io.File):eft");
    }
}
