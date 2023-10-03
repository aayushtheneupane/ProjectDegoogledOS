package p000;

import android.net.Uri;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: ckk */
/* compiled from: PG */
public final class ckk {

    /* renamed from: a */
    public final ebi f4560a;

    /* renamed from: b */
    public final dbs f4561b;

    /* renamed from: c */
    public final cze f4562c;

    /* renamed from: d */
    public final dav f4563d;

    /* renamed from: e */
    public final deo f4564e;

    /* renamed from: f */
    public final efr f4565f;

    /* renamed from: g */
    public final iek f4566g;

    /* renamed from: h */
    public final iek f4567h;

    /* renamed from: i */
    public final cjr f4568i;

    public ckk(ebi ebi, dbs dbs, cze cze, dav dav, deo deo, efr efr, iek iek, iek iek2, cjr cjr) {
        this.f4560a = ebi;
        this.f4561b = dbs;
        this.f4562c = cze;
        this.f4563d = dav;
        this.f4564e = deo;
        this.f4565f = efr;
        this.f4566g = iek;
        this.f4567h = iek2;
        this.f4568i = cjr;
    }

    /* renamed from: a */
    public final ieh mo3201a(hto hto, String str, boolean z, Runnable runnable, AtomicBoolean atomicBoolean) {
        this.f4564e.mo4100a();
        ieh a = ife.m12820a((Object) ife.m12904f());
        hvr a2 = hto.iterator();
        while (a2.hasNext()) {
            cyd cyd = (cyd) a2.next();
            a = gte.m10771a(a, (icf) new ckg(this, new ckc(this, cyd, Uri.parse(cyd.f5988b), str, z, runnable, atomicBoolean)), (Executor) this.f4567h);
        }
        return gte.m10770a(a, (hpr) new ckd(this), (Executor) this.f4567h);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e2  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m4447a(p000.abt r13, java.lang.String r14) {
        /*
            android.content.Context r0 = r13.f105a
            android.net.Uri r2 = r13.f106b
            r7 = 2
            int r1 = r0.checkCallingOrSelfUriPermission(r2, r7)
            if (r1 != 0) goto L_0x00f1
            java.lang.String r1 = "mime_type"
            java.lang.String r8 = p000.C0652xy.m16065a((android.content.Context) r0, (android.net.Uri) r2, (java.lang.String) r1)
            android.content.ContentResolver r1 = r0.getContentResolver()
            java.lang.String r0 = "flags"
            r3 = 1
            r9 = 0
            r11 = 0
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r12 = 0
            r3[r12] = r0     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            boolean r0 = r11.moveToFirst()     // Catch:{ Exception -> 0x003b }
            if (r0 == 0) goto L_0x0037
            boolean r0 = r11.isNull(r12)     // Catch:{ Exception -> 0x003b }
            if (r0 != 0) goto L_0x0037
            long r9 = r11.getLong(r12)     // Catch:{ Exception -> 0x003b }
        L_0x0037:
            p000.C0652xy.m16067a((java.lang.AutoCloseable) r11)
            goto L_0x0058
        L_0x003b:
            r0 = move-exception
            goto L_0x0041
        L_0x003d:
            r13 = move-exception
            goto L_0x00ed
        L_0x0040:
            r0 = move-exception
        L_0x0041:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r1.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r2 = "Failed query: "
            r1.append(r2)     // Catch:{ all -> 0x00ea }
            r1.append(r0)     // Catch:{ all -> 0x00ea }
            java.lang.String r0 = "DocumentFile"
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.w(r0, r1)     // Catch:{ all -> 0x00ea }
            goto L_0x0037
        L_0x0058:
            int r0 = (int) r9
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            if (r1 != 0) goto L_0x00f1
            r1 = r0 & 4
            if (r1 != 0) goto L_0x0078
            java.lang.String r1 = "vnd.android.document/directory"
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x006f
            r1 = r0 & 8
            if (r1 != 0) goto L_0x0078
        L_0x006f:
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            if (r1 != 0) goto L_0x00f1
            r0 = r0 & r7
            if (r0 == 0) goto L_0x00f1
        L_0x0078:
            java.lang.String r0 = android.os.Environment.DIRECTORY_DCIM
            abt r0 = r13.mo121a((java.lang.String) r0)
            if (r0 == 0) goto L_0x0081
            goto L_0x0087
        L_0x0081:
            java.lang.String r0 = android.os.Environment.DIRECTORY_DCIM
            abt r0 = r13.mo123b(r0)
        L_0x0087:
            java.lang.Object r1 = p000.ife.m12898e((java.lang.Object) r0)
            abt r1 = (p000.abt) r1
            abt r1 = r1.mo121a((java.lang.String) r14)
            if (r1 != 0) goto L_0x0096
            r0.mo123b(r14)
        L_0x0096:
            java.lang.String r0 = r13.mo122a()
            if (r0 == 0) goto L_0x00e2
            java.lang.String r13 = r13.mo122a()
            java.lang.Object r13 = p000.ife.m12898e((java.lang.Object) r13)
            java.lang.String r13 = (java.lang.String) r13
            java.lang.String r13 = p000.ckx.m4481a((java.lang.String) r13)
            java.lang.String r0 = android.os.Environment.DIRECTORY_DCIM
            java.lang.String r1 = java.lang.String.valueOf(r13)
            int r1 = r1.length()
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            java.lang.String r3 = java.lang.String.valueOf(r14)
            int r3 = r3.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = r1 + r7
            int r1 = r1 + r2
            int r1 = r1 + r3
            r4.<init>(r1)
            r4.append(r13)
            java.lang.String r13 = "/"
            r4.append(r13)
            r4.append(r0)
            r4.append(r13)
            r4.append(r14)
            java.lang.String r13 = r4.toString()
            return r13
        L_0x00e2:
            java.io.IOException r13 = new java.io.IOException
            java.lang.String r14 = "Unknown volume."
            r13.<init>(r14)
            throw r13
        L_0x00ea:
            r13 = move-exception
        L_0x00ed:
            p000.C0652xy.m16067a((java.lang.AutoCloseable) r11)
            throw r13
        L_0x00f1:
            java.lang.SecurityException r13 = new java.lang.SecurityException
            java.lang.String r14 = "No permissions to create folder in the specified location"
            r13.<init>(r14)
            goto L_0x00fa
        L_0x00f9:
            throw r13
        L_0x00fa:
            goto L_0x00f9
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ckk.m4447a(abt, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public final ieh mo3202a(ieh ieh, List list) {
        return gte.m10772a(ieh, Exception.class, (hpr) new ckj(list), (Executor) this.f4567h);
    }
}
