package p000;

import java.util.Collection;

/* renamed from: gmo */
/* compiled from: PG */
public final /* synthetic */ class gmo implements hpr {

    /* renamed from: a */
    private final Collection f11630a;

    public gmo(Collection collection) {
        this.f11630a = collection;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: gnj} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object mo1484a(java.lang.Object r15) {
        /*
            r14 = this;
            java.util.Collection r0 = r14.f11630a
            gng r15 = (p000.gng) r15
            r1 = 5
            java.lang.Object r2 = r15.mo8790b((int) r1)
            iir r2 = (p000.iir) r2
            r2.mo8503a((p000.iix) r15)
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.util.Iterator r4 = r0.iterator()
        L_0x0017:
            boolean r5 = r4.hasNext()
            r6 = 2
            r7 = 1
            r8 = 0
            if (r5 == 0) goto L_0x011e
            java.lang.Object r5 = r4.next()
            gle r5 = (p000.gle) r5
            iix r9 = r2.f14318b
            gng r9 = (p000.gng) r9
            ijy r9 = r9.f11678c
            java.util.Map r9 = java.util.Collections.unmodifiableMap(r9)
            java.util.Map r9 = java.util.Collections.unmodifiableMap(r9)
            java.util.Collection r9 = r9.values()
            java.util.Iterator r9 = r9.iterator()
        L_0x003c:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x00b0
            java.lang.Object r10 = r9.next()
            gnj r10 = (p000.gnj) r10
            gle r11 = r10.f11687c
            if (r11 == 0) goto L_0x004d
            goto L_0x004f
        L_0x004d:
            gle r11 = p000.gle.f11566i
        L_0x004f:
            java.lang.String r12 = r11.f11575h
            java.lang.String r13 = r5.f11575h
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x003c
            java.lang.String r11 = r11.f11569b
            java.lang.String r12 = r5.f11569b
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x003c
            java.lang.Object r7 = r10.mo8790b((int) r1)
            iir r7 = (p000.iir) r7
            r7.mo8503a((p000.iix) r10)
            boolean r9 = r7.f14319c
            if (r9 != 0) goto L_0x0071
            goto L_0x0076
        L_0x0071:
            r7.mo8751b()
            r7.f14319c = r8
        L_0x0076:
            iix r8 = r7.f14318b
            gnj r8 = (p000.gnj) r8
            gnj r9 = p000.gnj.f11683e
            r5.getClass()
            gle r9 = r8.f11687c
            if (r9 == 0) goto L_0x009b
            gle r11 = p000.gle.f11566i
            if (r9 == r11) goto L_0x009b
            gle r9 = r8.f11687c
            gle r11 = p000.gle.f11566i
            iir r9 = r11.mo8788a((p000.iix) r9)
            r9.mo8503a((p000.iix) r5)
            iix r9 = r9.mo8766f()
            gle r9 = (p000.gle) r9
            r8.f11687c = r9
            goto L_0x009d
        L_0x009b:
            r8.f11687c = r5
        L_0x009d:
            int r9 = r8.f11685a
            r6 = r6 | r9
            r8.f11685a = r6
            iix r6 = r7.mo8770g()
            gnj r6 = (p000.gnj) r6
            int r7 = r10.f11686b
            r2.mo8744a((int) r7, (p000.gnj) r6)
            int r6 = r10.f11686b
            goto L_0x0113
        L_0x00b0:
            gnj r9 = p000.gnj.f11683e
            iir r9 = r9.mo8793g()
            boolean r10 = r9.f14319c
            if (r10 != 0) goto L_0x00bb
            goto L_0x00c0
        L_0x00bb:
            r9.mo8751b()
            r9.f14319c = r8
        L_0x00c0:
            iix r10 = r9.f14318b
            gnj r10 = (p000.gnj) r10
            r5.getClass()
            r10.f11687c = r5
            int r11 = r10.f11685a
            r6 = r6 | r11
            r10.f11685a = r6
            r10.f11688d = r8
            r6 = r6 | 4
            r10.f11685a = r6
            iix r6 = r2.f14318b
            gng r6 = (p000.gng) r6
            int r6 = r6.f11677b
            int r10 = r6 + 1
            boolean r11 = r2.f14319c
            if (r11 != 0) goto L_0x00e1
            goto L_0x00e6
        L_0x00e1:
            r2.mo8751b()
            r2.f14319c = r8
        L_0x00e6:
            iix r11 = r2.f14318b
            gng r11 = (p000.gng) r11
            gng r12 = p000.gng.f11674d
            int r12 = r11.f11676a
            r12 = r12 | r7
            r11.f11676a = r12
            r11.f11677b = r10
            boolean r10 = r9.f14319c
            if (r10 != 0) goto L_0x00f8
            goto L_0x00fd
        L_0x00f8:
            r9.mo8751b()
            r9.f14319c = r8
        L_0x00fd:
            iix r8 = r9.f14318b
            gnj r8 = (p000.gnj) r8
            int r10 = r8.f11685a
            r7 = r7 | r10
            r8.f11685a = r7
            r8.f11686b = r6
            iix r7 = r9.mo8770g()
            gnj r7 = (p000.gnj) r7
            r2.mo8744a((int) r6, (p000.gnj) r7)
        L_0x0113:
            gtf r7 = p000.gtf.f12011a
            gkn r6 = p000.gkn.m10445a(r6, r7)
            r3.put(r6, r5)
            goto L_0x0017
        L_0x011e:
            hsu r3 = p000.hsu.m12069a((java.util.Map) r3)
            int r4 = r3.size()
            int r0 = r0.size()
            if (r4 != r0) goto L_0x012d
            goto L_0x012f
        L_0x012d:
            r7 = 0
        L_0x012f:
            java.lang.String r0 = "Provider had duplicate accounts."
            p000.ife.m12876b((boolean) r7, (java.lang.Object) r0)
            htm r0 = p000.hto.m12130j()
            ijy r15 = r15.f11678c
            java.util.Map r15 = java.util.Collections.unmodifiableMap(r15)
            java.util.Collection r15 = r15.values()
            java.util.Iterator r15 = r15.iterator()
        L_0x0146:
            boolean r4 = r15.hasNext()
            if (r4 == 0) goto L_0x015e
            java.lang.Object r4 = r15.next()
            gnj r4 = (p000.gnj) r4
            int r4 = r4.f11686b
            gtf r5 = p000.gtf.f12011a
            gkn r4 = p000.gkn.m10445a(r4, r5)
            r0.mo7874b(r4)
            goto L_0x0146
        L_0x015e:
            hto r15 = r0.mo7993a()
            hto r0 = r3.keySet()
            hvn r15 = p000.ife.m12814a((java.util.Set) r15, (java.util.Set) r0)
            hto r15 = p000.hto.m12125a((java.util.Collection) r15)
            hsq r0 = p000.hsu.m12070g()
            java.util.Iterator r3 = r15.iterator()
        L_0x0176:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0202
            java.lang.Object r4 = r3.next()
            gkn r4 = (p000.gkn) r4
            int r5 = r4.mo6807a()
            gnj r7 = p000.gnj.f11683e
            iix r9 = r2.f14318b
            gng r9 = (p000.gng) r9
            ijy r9 = r9.f11678c
            java.util.Map r9 = java.util.Collections.unmodifiableMap(r9)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            boolean r10 = r9.containsKey(r5)
            if (r10 == 0) goto L_0x01a3
            java.lang.Object r5 = r9.get(r5)
            r7 = r5
            gnj r7 = (p000.gnj) r7
        L_0x01a3:
            int r5 = r7.f11688d
            int r5 = p000.hgh.m11441a(r5)
            if (r5 == 0) goto L_0x0176
            if (r5 != r6) goto L_0x0176
            r0.mo7932a(r4, r7)
            int r5 = r4.mo6807a()
            iix r7 = r2.f14318b
            gng r7 = (p000.gng) r7
            ijy r7 = r7.f11678c
            java.util.Map r7 = java.util.Collections.unmodifiableMap(r7)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            boolean r9 = r7.containsKey(r5)
            if (r9 == 0) goto L_0x01fc
            java.lang.Object r5 = r7.get(r5)
            gnj r5 = (p000.gnj) r5
            java.lang.Object r7 = r5.mo8790b((int) r1)
            iir r7 = (p000.iir) r7
            r7.mo8503a((p000.iix) r5)
            boolean r5 = r7.f14319c
            if (r5 != 0) goto L_0x01dc
            goto L_0x01e1
        L_0x01dc:
            r7.mo8751b()
            r7.f14319c = r8
        L_0x01e1:
            iix r5 = r7.f14318b
            gnj r5 = (p000.gnj) r5
            r5.f11688d = r6
            int r9 = r5.f11685a
            r9 = r9 | 4
            r5.f11685a = r9
            iix r5 = r7.mo8770g()
            gnj r5 = (p000.gnj) r5
            int r4 = r4.mo6807a()
            r2.mo8744a((int) r4, (p000.gnj) r5)
            goto L_0x0176
        L_0x01fc:
            java.lang.IllegalArgumentException r15 = new java.lang.IllegalArgumentException
            r15.<init>()
            throw r15
        L_0x0202:
            hsu r0 = r0.mo7930a()
            android.util.Pair r15 = android.util.Pair.create(r0, r15)
            iix r0 = r2.mo8770g()
            gng r0 = (p000.gng) r0
            android.util.Pair r15 = android.util.Pair.create(r15, r0)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gmo.mo1484a(java.lang.Object):java.lang.Object");
    }
}
