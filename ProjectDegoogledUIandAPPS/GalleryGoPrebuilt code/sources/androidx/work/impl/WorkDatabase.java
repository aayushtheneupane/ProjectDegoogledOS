package androidx.work.impl;

import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public abstract class WorkDatabase extends C0053bx {

    /* renamed from: h */
    private static final long f1170h = TimeUnit.DAYS.toMillis(7);

    /* renamed from: j */
    public abstract alh mo1226j();

    /* renamed from: k */
    public abstract akk mo1227k();

    /* renamed from: l */
    public abstract alt mo1228l();

    /* renamed from: m */
    public abstract aks mo1229m();

    /* renamed from: n */
    public abstract akx mo1230n();

    /* renamed from: o */
    public abstract ala mo1231o();

    /* renamed from: p */
    public abstract ako mo1232p();

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x014c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.work.impl.WorkDatabase m1113a(android.content.Context r20, java.util.concurrent.Executor r21, boolean r22) {
        /*
            r0 = r20
            java.lang.Class<androidx.work.impl.WorkDatabase> r1 = androidx.work.impl.WorkDatabase.class
            r2 = 0
            r3 = 1
            if (r22 == 0) goto L_0x0010
            bv r4 = new bv
            r4.<init>(r0, r1, r2)
            r4.f3660h = r3
            goto L_0x002b
        L_0x0010:
            java.io.File r4 = p000.ain.m546b(r20)
            java.lang.String r4 = r4.getPath()
            if (r4 == 0) goto L_0x0157
            java.lang.String r5 = r4.trim()
            int r5 = r5.length()
            if (r5 == 0) goto L_0x0157
            bv r5 = new bv
            r5.<init>(r0, r1, r4)
            r4 = r5
        L_0x002b:
            r1 = r21
            r4.f3657e = r1
            aid r1 = new aid
            r1.<init>()
            java.util.ArrayList r5 = r4.f3656d
            if (r5 != 0) goto L_0x003f
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r4.f3656d = r5
        L_0x003f:
            java.util.ArrayList r5 = r4.f3656d
            r5.add(r1)
            cf[] r1 = new p000.C0062cf[r3]
            cf r5 = p000.aim.f540a
            r6 = 0
            r1[r6] = r5
            r4.mo2786a(r1)
            cf[] r1 = new p000.C0062cf[r3]
            aik r5 = new aik
            r7 = 2
            r8 = 3
            r5.<init>(r0, r7, r8)
            r1[r6] = r5
            r4.mo2786a(r1)
            cf[] r1 = new p000.C0062cf[r3]
            cf r5 = p000.aim.f541b
            r1[r6] = r5
            r4.mo2786a(r1)
            cf[] r1 = new p000.C0062cf[r3]
            cf r5 = p000.aim.f542c
            r1[r6] = r5
            r4.mo2786a(r1)
            cf[] r1 = new p000.C0062cf[r3]
            aik r5 = new aik
            r9 = 5
            r10 = 6
            r5.<init>(r0, r9, r10)
            r1[r6] = r5
            r4.mo2786a(r1)
            cf[] r1 = new p000.C0062cf[r3]
            cf r5 = p000.aim.f543d
            r1[r6] = r5
            r4.mo2786a(r1)
            cf[] r1 = new p000.C0062cf[r3]
            cf r5 = p000.aim.f544e
            r1[r6] = r5
            r4.mo2786a(r1)
            cf[] r1 = new p000.C0062cf[r3]
            cf r5 = p000.aim.f545f
            r1[r6] = r5
            r4.mo2786a(r1)
            cf[] r1 = new p000.C0062cf[r3]
            ail r5 = new ail
            r5.<init>(r0)
            r1[r6] = r5
            r4.mo2786a(r1)
            r4.f3661i = r6
            r4.f3662j = r3
            android.content.Context r0 = r4.f3655c
            if (r0 == 0) goto L_0x014f
            java.util.concurrent.Executor r0 = r4.f3657e
            if (r0 != 0) goto L_0x00ba
            java.util.concurrent.Executor r1 = r4.f3658f
            if (r1 != 0) goto L_0x00ba
            java.util.concurrent.Executor r0 = p000.C0029b.f1924a
            r4.f3658f = r0
            r4.f3657e = r0
            goto L_0x00cb
        L_0x00ba:
            if (r0 == 0) goto L_0x00c3
            java.util.concurrent.Executor r1 = r4.f3658f
            if (r1 != 0) goto L_0x00c3
            r4.f3658f = r0
            goto L_0x00cb
        L_0x00c3:
            if (r0 != 0) goto L_0x00cb
            java.util.concurrent.Executor r0 = r4.f3658f
            if (r0 == 0) goto L_0x00cb
            r4.f3657e = r0
        L_0x00cb:
            bd r0 = r4.f3659g
            if (r0 == 0) goto L_0x00d0
            goto L_0x00d7
        L_0x00d0:
            bn r0 = new bn
            r0.<init>()
            r4.f3659g = r0
        L_0x00d7:
            bq r0 = new bq
            android.content.Context r10 = r4.f3655c
            java.lang.String r11 = r4.f3654b
            bw r12 = r4.f3663k
            java.util.ArrayList r13 = r4.f3656d
            boolean r14 = r4.f3660h
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "activity"
            java.lang.Object r1 = r10.getSystemService(r1)
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1
            if (r1 == 0) goto L_0x00fb
            int r5 = android.os.Build.VERSION.SDK_INT
            boolean r1 = r1.isLowRamDevice()
            if (r1 == 0) goto L_0x00f8
            goto L_0x00fb
        L_0x00f8:
            r15 = 3
            goto L_0x00fc
        L_0x00fb:
            r15 = 2
        L_0x00fc:
            java.util.concurrent.Executor r1 = r4.f3657e
            java.util.concurrent.Executor r5 = r4.f3658f
            boolean r7 = r4.f3661i
            boolean r9 = r4.f3662j
            r19 = r9
            r9 = r0
            r16 = r1
            r17 = r5
            r18 = r7
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            java.lang.Class r1 = r4.f3653a
            java.lang.String r4 = "_Impl"
            java.lang.Object r1 = p000.C0257jh.m14471a((java.lang.Class) r1, (java.lang.String) r4)
            bx r1 = (p000.C0053bx) r1
            be r4 = r1.mo1233a((p000.C0046bq) r0)
            r1.f3802c = r4
            be r4 = r1.f3802c
            boolean r5 = r4 instanceof p000.C0058cb
            if (r5 != 0) goto L_0x014c
            int r2 = android.os.Build.VERSION.SDK_INT
            int r2 = r0.f3347j
            if (r2 != r8) goto L_0x012d
            goto L_0x012f
        L_0x012d:
            r3 = 0
        L_0x012f:
            be r2 = r1.f3802c
            r2.mo1893a(r3)
            java.util.List r2 = r0.f3341d
            r1.f3806g = r2
            java.util.concurrent.Executor r2 = r0.f3343f
            r1.f3801b = r2
            ce r2 = new ce
            java.util.concurrent.Executor r4 = r0.f3344g
            r2.<init>(r4)
            boolean r0 = r0.f3342e
            r1.f3804e = r0
            r1.f3805f = r3
            androidx.work.impl.WorkDatabase r1 = (androidx.work.impl.WorkDatabase) r1
            return r1
        L_0x014c:
            cb r4 = (p000.C0058cb) r4
            throw r2
        L_0x014f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Cannot provide null context for the database."
            r0.<init>(r1)
            throw r0
        L_0x0157:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.WorkDatabase.m1113a(android.content.Context, java.util.concurrent.Executor, boolean):androidx.work.impl.WorkDatabase");
    }

    /* renamed from: i */
    public static String m1114i() {
        return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (period_start_time + minimum_retention_duration) < " + (System.currentTimeMillis() - f1170h) + " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    }
}
