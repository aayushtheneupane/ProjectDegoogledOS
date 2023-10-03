package p000;

/* renamed from: fgs */
/* compiled from: PG */
final class fgs extends fgl {

    /* renamed from: a */
    private final /* synthetic */ fgt f9531a;

    public fgs(fgt fgt) {
        this.f9531a = fgt;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0152, code lost:
        if (p000.fgq.m8792a(r19, r3.f9525g, r3.f9526h) != false) goto L_0x015e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01ad  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo5586a(long r34) {
        /*
            r33 = this;
            r0 = r33
            fgt r1 = r0.f9531a
            r2 = 1000000(0xf4240, double:4.940656E-318)
            long r2 = r34 / r2
            r4 = 0
            r1.f9536e = r4
            long r5 = r1.f9535d
            r7 = -1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x0015
            goto L_0x0016
        L_0x0015:
            r5 = r2
        L_0x0016:
            long r5 = r2 - r5
            double r5 = (double) r5
            r7 = 4652007308841189376(0x408f400000000000, double:1000.0)
            java.lang.Double.isNaN(r5)
            double r5 = r5 / r7
            r1.f9535d = r2
            int r2 = r1.f9534c
            if (r2 == 0) goto L_0x01c9
            r2 = 4589276106681592316(0x3fb0624dd2f1a9fc, double:0.064)
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 > 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r5 = r2
        L_0x0034:
            java.util.concurrent.CopyOnWriteArrayList r2 = r1.f9532a
            java.util.Iterator r2 = r2.iterator()
        L_0x003a:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01c5
            java.lang.Object r3 = r2.next()
            fgq r3 = (p000.fgq) r3
            lf r7 = r1.f9533b
            java.lang.Object r7 = r7.get(r3)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            if (r7 == 0) goto L_0x01be
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x01b7
            boolean r7 = r3.mo5599a()
            if (r7 != 0) goto L_0x005d
            goto L_0x0067
        L_0x005d:
            boolean r8 = r3.f9527i
            if (r8 == 0) goto L_0x0067
            r18 = r1
            r16 = r5
            goto L_0x0194
        L_0x0067:
            double r8 = r3.f9529k
            double r8 = r8 + r5
            r3.f9529k = r8
            double r8 = r3.f9522d
            double r10 = r3.f9523e
            double r12 = r3.f9524f
            double r14 = r3.f9520b
            r16 = r5
            double r4 = r3.f9521c
        L_0x0078:
            r35 = r7
            double r6 = r3.f9529k
            r18 = r1
            r0 = 0
            r19 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
            int r21 = (r6 > r19 ? 1 : (r6 == r19 ? 0 : -1))
            if (r21 < 0) goto L_0x011c
            r4 = -4661117527937406468(0xbf50624dd2f1a9fc, double:-0.001)
            double r6 = r6 + r4
            r3.f9529k = r6
            double r4 = r3.f9526h
            double r6 = r4 - r8
            r12 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r6 = r6 * r12
            r14 = 4625759767262920704(0x4032000000000000, double:18.0)
            double r21 = r10 * r14
            double r6 = r6 - r21
            double r21 = r6 * r19
            r23 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r21 = r21 * r23
            double r21 = r10 + r21
            double r25 = r10 * r19
            double r25 = r25 * r23
            double r25 = r8 + r25
            double r25 = r4 - r25
            double r25 = r25 * r12
            double r27 = r21 * r14
            double r25 = r25 - r27
            double r27 = r25 * r19
            double r27 = r27 * r23
            double r27 = r10 + r27
            double r29 = r21 * r19
            double r29 = r29 * r23
            double r29 = r8 + r29
            double r23 = r4 - r29
            double r23 = r23 * r12
            double r29 = r27 * r14
            double r23 = r23 - r29
            double r29 = r27 * r19
            double r29 = r8 + r29
            double r31 = r23 * r19
            double r31 = r10 + r31
            double r21 = r21 + r27
            double r25 = r25 + r23
            double r21 = r21 + r21
            double r21 = r10 + r21
            double r21 = r21 + r31
            r23 = 4618441417868443648(0x4018000000000000, double:6.0)
            double r21 = r21 / r23
            double r21 = r21 * r19
            double r27 = r8 + r21
            double r25 = r25 + r25
            double r6 = r6 + r25
            double r21 = r4 - r29
            double r21 = r21 * r12
            double r31 = r31 * r14
            double r21 = r21 - r31
            double r6 = r6 + r21
            double r6 = r6 / r23
            double r6 = r6 * r19
            double r6 = r6 + r10
            boolean r12 = r3.f9528j
            if (r12 == 0) goto L_0x010c
            double r12 = r3.f9525g
            r21 = r27
            r23 = r12
            r25 = r4
            boolean r4 = p000.fgq.m8792a(r21, r23, r25)
            if (r4 == 0) goto L_0x010c
            r3.f9529k = r0
            goto L_0x010d
        L_0x010c:
        L_0x010d:
            r0 = r33
            r14 = r8
            r4 = r10
            r1 = r18
            r8 = r27
            r12 = r29
            r10 = r6
            r7 = r35
            goto L_0x0078
        L_0x011c:
            r3.f9524f = r12
            r3.f9522d = r8
            r3.f9523e = r10
            r3.f9520b = r14
            r3.f9521c = r4
            int r12 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r12 <= 0) goto L_0x0140
            double r6 = r6 / r19
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r12 = r12 - r6
            double r8 = r8 * r6
            double r14 = r14 * r12
            double r8 = r8 + r14
            r3.f9522d = r8
            double r10 = r10 * r6
            double r4 = r4 * r12
            double r10 = r10 + r4
            r3.f9523e = r10
            r19 = r8
            goto L_0x0142
        L_0x0140:
            r19 = r8
        L_0x0142:
            boolean r4 = r3.f9528j
            if (r4 == 0) goto L_0x0155
            double r4 = r3.f9525g
            double r6 = r3.f9526h
            r21 = r4
            r23 = r6
            boolean r4 = p000.fgq.m8792a(r19, r21, r23)
            if (r4 == 0) goto L_0x0155
            goto L_0x015e
        L_0x0155:
            boolean r4 = r3.mo5599a()
            if (r4 != 0) goto L_0x015e
            r7 = r35
            goto L_0x0169
        L_0x015e:
            double r4 = r3.f9526h
            r3.f9525g = r4
            r3.f9522d = r4
            r3.f9523e = r0
            r3.f9529k = r0
            r7 = 1
        L_0x0169:
            boolean r0 = r3.f9527i
            r3.f9527i = r7
            java.util.concurrent.CopyOnWriteArrayList r1 = r3.f9519a
            java.util.Iterator r1 = r1.iterator()
        L_0x0173:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0190
            java.lang.Object r4 = r1.next()
            fgp r4 = (p000.fgp) r4
            if (r0 != 0) goto L_0x0182
            goto L_0x0185
        L_0x0182:
            r4.mo5593a()
        L_0x0185:
            double r5 = r3.f9522d
            r4.mo5594a((double) r5)
            if (r7 == 0) goto L_0x0173
            r4.mo5596b()
            goto L_0x0173
        L_0x0190:
            r0 = r7 ^ 1
            if (r0 != 0) goto L_0x01ad
        L_0x0194:
            r0 = r18
            lf r1 = r0.f9533b
            r4 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
            r1.put(r3, r5)
            int r1 = r0.f9534c
            int r1 = r1 + -1
            r0.f9534c = r1
            r1 = r0
            r5 = r16
            r0 = r33
            goto L_0x003a
        L_0x01ad:
            r0 = r18
            r4 = 0
            r1 = r0
            r5 = r16
            r0 = r33
            goto L_0x003a
        L_0x01b7:
            r0 = r1
            r16 = r5
            r0 = r33
            goto L_0x003a
        L_0x01be:
            r0 = r1
            r16 = r5
            r0 = r33
            goto L_0x003a
        L_0x01c5:
            r0 = r1
            r0.mo5604c()
        L_0x01c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fgs.mo5586a(long):void");
    }
}
