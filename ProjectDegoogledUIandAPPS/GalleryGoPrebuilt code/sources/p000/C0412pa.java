package p000;

import android.content.IntentFilter;

/* renamed from: pa */
/* compiled from: PG */
final class C0412pa extends C0410oz {

    /* renamed from: a */
    private final C0429pr f15435a;

    /* renamed from: b */
    private final /* synthetic */ C0416pe f15436b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0412pa(C0416pe peVar, C0429pr prVar) {
        super(peVar);
        this.f15436b = peVar;
        this.f15435a = prVar;
    }

    /* renamed from: c */
    public final IntentFilter mo9592c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        return intentFilter;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00eb A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo9590a() {
        /*
            r22 = this;
            r0 = r22
            pr r1 = r0.f15435a
            pq r2 = r1.f15535c
            long r3 = r2.f15532b
            long r5 = java.lang.System.currentTimeMillis()
            r7 = 1
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 > 0) goto L_0x00e7
            android.content.Context r3 = r1.f15534b
            java.lang.String r4 = "android.permission.ACCESS_COARSE_LOCATION"
            int r3 = p000.C0071co.m4653a((android.content.Context) r3, (java.lang.String) r4)
            r4 = 0
            if (r3 != 0) goto L_0x0023
            java.lang.String r3 = "network"
            android.location.Location r3 = r1.mo9641a(r3)
            goto L_0x0025
        L_0x0023:
            r3 = r4
        L_0x0025:
            android.content.Context r5 = r1.f15534b
            java.lang.String r6 = "android.permission.ACCESS_FINE_LOCATION"
            int r5 = p000.C0071co.m4653a((android.content.Context) r5, (java.lang.String) r6)
            if (r5 != 0) goto L_0x0036
            java.lang.String r4 = "gps"
            android.location.Location r4 = r1.mo9641a(r4)
            goto L_0x0038
        L_0x0036:
        L_0x0038:
            if (r4 == 0) goto L_0x0049
            if (r3 == 0) goto L_0x0049
            long r5 = r4.getTime()
            long r8 = r3.getTime()
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 > 0) goto L_0x004b
            goto L_0x004c
        L_0x0049:
            if (r4 == 0) goto L_0x004c
        L_0x004b:
            r3 = r4
        L_0x004c:
            if (r3 != 0) goto L_0x0061
            java.util.Calendar r1 = java.util.Calendar.getInstance()
            r2 = 11
            int r1 = r1.get(r2)
            r2 = 6
            if (r1 < r2) goto L_0x00ec
            r2 = 22
            if (r1 < r2) goto L_0x00eb
            goto L_0x00ec
        L_0x0061:
            pq r1 = r1.f15535c
            long r4 = java.lang.System.currentTimeMillis()
            pp r6 = p000.C0427pp.f15527a
            if (r6 == 0) goto L_0x006c
            goto L_0x0073
        L_0x006c:
            pp r6 = new pp
            r6.<init>()
            p000.C0427pp.f15527a = r6
        L_0x0073:
            pp r6 = p000.C0427pp.f15527a
            r8 = -86400000(0xfffffffffad9a400, double:NaN)
            long r10 = r4 + r8
            double r12 = r3.getLatitude()
            double r14 = r3.getLongitude()
            r8 = r6
            r9 = r10
            r11 = r12
            r13 = r14
            r8.mo9640a(r9, r11, r13)
            double r11 = r3.getLatitude()
            double r13 = r3.getLongitude()
            r9 = r4
            r8.mo9640a(r9, r11, r13)
            int r15 = r6.f15530d
            long r13 = r6.f15529c
            long r11 = r6.f15528b
            r8 = 86400000(0x5265c00, double:4.2687272E-316)
            long r16 = r4 + r8
            double r18 = r3.getLatitude()
            double r20 = r3.getLongitude()
            r8 = r6
            r9 = r16
            r16 = r11
            r11 = r18
            r18 = r13
            r13 = r20
            r8.mo9640a(r9, r11, r13)
            long r13 = r6.f15529c
            r8 = -1
            int r3 = (r18 > r8 ? 1 : (r18 == r8 ? 0 : -1))
            if (r3 != 0) goto L_0x00bf
        L_0x00be:
            goto L_0x00d5
        L_0x00bf:
            int r3 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r3 == 0) goto L_0x00be
            int r3 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r3 > 0) goto L_0x00d0
            int r3 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r3 > 0) goto L_0x00ce
            r13 = r18
            goto L_0x00d0
        L_0x00ce:
            r13 = r16
        L_0x00d0:
            r3 = 60000(0xea60, double:2.9644E-319)
            long r13 = r13 + r3
            goto L_0x00da
        L_0x00d5:
            r8 = 43200000(0x2932e00, double:2.1343636E-316)
            long r13 = r4 + r8
        L_0x00da:
            if (r15 != 0) goto L_0x00de
            r3 = 0
            goto L_0x00e0
        L_0x00de:
            r3 = 1
        L_0x00e0:
            r1.f15531a = r3
            r1.f15532b = r13
            boolean r1 = r2.f15531a
            goto L_0x00e9
        L_0x00e7:
            boolean r1 = r2.f15531a
        L_0x00e9:
            if (r1 != 0) goto L_0x00ec
        L_0x00eb:
            return r7
        L_0x00ec:
            r1 = 2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0412pa.mo9590a():int");
    }

    /* renamed from: b */
    public final void mo9591b() {
        this.f15436b.mo9564j();
    }
}
