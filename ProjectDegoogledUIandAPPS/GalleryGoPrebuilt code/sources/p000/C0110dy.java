package p000;

import java.util.Arrays;

/* renamed from: dy */
/* compiled from: PG */
public final class C0110dy {

    /* renamed from: g */
    private static int f7620g = 1000;

    /* renamed from: a */
    public final C0109dx f7621a;

    /* renamed from: b */
    public C0107dv[] f7622b;

    /* renamed from: c */
    public boolean[] f7623c;

    /* renamed from: d */
    public int f7624d;

    /* renamed from: e */
    public int f7625e;

    /* renamed from: f */
    public final C0108dw f7626f;

    /* renamed from: h */
    private int f7627h;

    /* renamed from: i */
    private int f7628i;

    /* renamed from: j */
    private int f7629j;

    /* renamed from: k */
    private int f7630k;

    /* renamed from: l */
    private C0114eb[] f7631l;

    /* renamed from: m */
    private int f7632m;

    /* renamed from: n */
    private C0107dv[] f7633n;

    /* renamed from: b */
    public static final int m6890b(Object obj) {
        C0114eb ebVar = ((C0115ec) obj).f7906e;
        if (ebVar != null) {
            return (int) (ebVar.f7816d + 0.5f);
        }
        return 0;
    }

    public C0110dy() {
        this.f7627h = 0;
        this.f7621a = new C0109dx();
        this.f7628i = 32;
        this.f7629j = 32;
        this.f7622b = null;
        this.f7623c = new boolean[32];
        this.f7624d = 1;
        this.f7625e = 0;
        this.f7630k = 32;
        this.f7631l = new C0114eb[f7620g];
        this.f7632m = 0;
        this.f7633n = new C0107dv[32];
        this.f7622b = new C0107dv[32];
        m6894f();
        this.f7626f = new C0108dw();
    }

    /* renamed from: a */
    private final C0114eb m6888a(int i) {
        C0114eb ebVar = (C0114eb) this.f7626f.f7470b.mo4602a();
        if (ebVar == null) {
            ebVar = new C0114eb(i);
        } else {
            ebVar.mo4645a();
            ebVar.f7820h = i;
        }
        int i2 = this.f7632m;
        int i3 = f7620g;
        if (i2 >= i3) {
            int i4 = i3 + i3;
            f7620g = i4;
            this.f7631l = (C0114eb[]) Arrays.copyOf(this.f7631l, i4);
        }
        C0114eb[] ebVarArr = this.f7631l;
        int i5 = this.f7632m;
        this.f7632m = i5 + 1;
        ebVarArr[i5] = ebVar;
        return ebVar;
    }

    /* renamed from: a */
    public final void mo4559a(C0114eb ebVar, C0114eb ebVar2, int i, float f, C0114eb ebVar3, C0114eb ebVar4, int i2) {
        C0107dv b = mo4561b();
        b.mo4472a(ebVar, ebVar2, i, f, ebVar3, ebVar4, i2);
        C0114eb d = mo4565d();
        C0114eb d2 = mo4565d();
        d.f7815c = 4;
        d2.f7815c = 4;
        b.mo4470a(d, d2);
        mo4556a(b);
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x011c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo4556a(p000.C0107dv r13) {
        /*
            r12 = this;
            if (r13 == 0) goto L_0x0190
            int r0 = r12.f7625e
            r1 = 1
            int r0 = r0 + r1
            int r2 = r12.f7630k
            if (r0 >= r2) goto L_0x0012
            int r0 = r12.f7624d
            int r0 = r0 + r1
            int r2 = r12.f7629j
            if (r0 >= r2) goto L_0x0012
            goto L_0x0015
        L_0x0012:
            r12.m6893e()
        L_0x0015:
            boolean r0 = r13.f7442e
            r2 = 0
            if (r0 != 0) goto L_0x013c
            int r0 = r12.f7625e
            r3 = -1
            if (r0 <= 0) goto L_0x0094
            du r0 = r13.f7441d
            dv[] r4 = r12.f7622b
            int r5 = r0.f7377f
            r6 = 0
        L_0x0026:
            if (r5 != r3) goto L_0x0029
            goto L_0x008a
        L_0x0029:
            int r7 = r0.f7372a
            if (r6 >= r7) goto L_0x008a
            dw r7 = r0.f7373b
            eb[] r7 = r7.f7471c
            int[] r8 = r0.f7374c
            r8 = r8[r5]
            r7 = r7[r8]
            int r8 = r7.f7814b
            if (r8 == r3) goto L_0x0083
            float[] r6 = r0.f7376e
            r5 = r6[r5]
            r0.mo4445a((p000.C0114eb) r7)
            int r6 = r7.f7814b
            r6 = r4[r6]
            boolean r7 = r6.f7442e
            if (r7 != 0) goto L_0x0070
            du r7 = r6.f7441d
            int r8 = r7.f7377f
            r9 = 0
        L_0x004f:
            if (r8 != r3) goto L_0x0052
            goto L_0x0070
        L_0x0052:
            int r10 = r7.f7372a
            if (r9 >= r10) goto L_0x0070
            dw r10 = r0.f7373b
            eb[] r10 = r10.f7471c
            int[] r11 = r7.f7374c
            r11 = r11[r8]
            r10 = r10[r11]
            float[] r11 = r7.f7376e
            r11 = r11[r8]
            float r11 = r11 * r5
            r0.mo4451b(r10, r11)
            int[] r10 = r7.f7375d
            r8 = r10[r8]
            int r9 = r9 + 1
            goto L_0x004f
        L_0x0070:
            float r7 = r13.f7439b
            float r8 = r6.f7439b
            float r8 = r8 * r5
            float r7 = r7 + r8
            r13.f7439b = r7
            eb r5 = r6.f7438a
            r5.mo4646a(r13)
            int r5 = r0.f7377f
            r6 = 0
            goto L_0x0026
        L_0x0083:
            int[] r7 = r0.f7375d
            r5 = r7[r5]
            int r6 = r6 + 1
            goto L_0x0026
        L_0x008a:
            du r0 = r13.f7441d
            int r0 = r0.f7372a
            if (r0 == 0) goto L_0x0091
            goto L_0x0094
        L_0x0091:
            r13.f7442e = r1
        L_0x0094:
            float r0 = r13.f7439b
            r4 = 0
            int r5 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r5 >= 0) goto L_0x00b8
            float r0 = -r0
            r13.f7439b = r0
            du r0 = r13.f7441d
            int r5 = r0.f7377f
            r6 = 0
        L_0x00a3:
            if (r5 != r3) goto L_0x00a6
        L_0x00a5:
            goto L_0x00b8
        L_0x00a6:
            int r7 = r0.f7372a
            if (r6 >= r7) goto L_0x00a5
            float[] r7 = r0.f7376e
            r8 = r7[r5]
            float r8 = -r8
            r7[r5] = r8
            int[] r7 = r0.f7375d
            r5 = r7[r5]
            int r6 = r6 + 1
            goto L_0x00a3
        L_0x00b8:
            du r0 = r13.f7441d
            int r5 = r0.f7377f
            r6 = 0
            r7 = r6
            r8 = 0
        L_0x00bf:
            if (r5 == r3) goto L_0x0119
            int r9 = r0.f7372a
            if (r8 >= r9) goto L_0x0119
            float[] r9 = r0.f7376e
            r10 = r9[r5]
            int r11 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r11 < 0) goto L_0x00d8
            r11 = 981668463(0x3a83126f, float:0.001)
            int r11 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r11 >= 0) goto L_0x00df
            r9[r5] = r4
            r10 = 0
            goto L_0x00e5
        L_0x00d8:
            r11 = -1165815185(0xffffffffba83126f, float:-0.001)
            int r11 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r11 > 0) goto L_0x00e0
        L_0x00df:
            goto L_0x00e5
        L_0x00e0:
            r9[r5] = r4
            r10 = 0
        L_0x00e5:
            int r9 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0110
            dw r9 = r0.f7373b
            eb[] r9 = r9.f7471c
            int[] r11 = r0.f7374c
            r11 = r11[r5]
            r9 = r9[r11]
            int r11 = r9.f7820h
            if (r11 == r1) goto L_0x0106
            int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r10 < 0) goto L_0x00fc
            goto L_0x0110
        L_0x00fc:
            if (r7 == 0) goto L_0x0104
            int r10 = r9.f7815c
            int r11 = r7.f7815c
            if (r10 >= r11) goto L_0x0110
        L_0x0104:
            r7 = r9
            goto L_0x0111
        L_0x0106:
            int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r10 >= 0) goto L_0x010c
            r6 = r9
            goto L_0x011d
        L_0x010c:
            if (r6 != 0) goto L_0x0110
            r6 = r9
            goto L_0x0111
        L_0x0110:
        L_0x0111:
            int[] r9 = r0.f7375d
            r5 = r9[r5]
            int r8 = r8 + 1
            goto L_0x00bf
        L_0x0119:
            if (r6 == 0) goto L_0x011c
            goto L_0x011d
        L_0x011c:
            r6 = r7
        L_0x011d:
            if (r6 != 0) goto L_0x0120
            goto L_0x0123
        L_0x0120:
            r13.mo4468a((p000.C0114eb) r6)
        L_0x0123:
            du r0 = r13.f7441d
            int r0 = r0.f7372a
            if (r0 == 0) goto L_0x012a
            goto L_0x012c
        L_0x012a:
            r13.f7442e = r1
        L_0x012c:
            eb r0 = r13.f7438a
            if (r0 == 0) goto L_0x013b
            int r0 = r0.f7820h
            if (r0 == r1) goto L_0x013a
            float r0 = r13.f7439b
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 < 0) goto L_0x013b
        L_0x013a:
            goto L_0x013c
        L_0x013b:
            return
        L_0x013c:
            dv[] r0 = r12.f7622b
            int r3 = r12.f7625e
            r0 = r0[r3]
            if (r0 == 0) goto L_0x014b
            dw r3 = r12.f7626f
            dz r3 = r3.f7469a
            r3.mo4603a(r0)
        L_0x014b:
            boolean r0 = r13.f7442e
            if (r0 == 0) goto L_0x0150
            goto L_0x0153
        L_0x0150:
            r13.mo4465a()
        L_0x0153:
            dv[] r0 = r12.f7622b
            int r3 = r12.f7625e
            r0[r3] = r13
            eb r0 = r13.f7438a
            r0.f7814b = r3
            int r3 = r3 + r1
            r12.f7625e = r3
            int r0 = r0.f7819g
            if (r0 <= 0) goto L_0x018f
        L_0x0164:
            dv[] r1 = r12.f7633n
            int r3 = r1.length
            if (r3 >= r0) goto L_0x016f
            int r3 = r3 + r3
            dv[] r1 = new p000.C0107dv[r3]
            r12.f7633n = r1
            goto L_0x0164
        L_0x016f:
            r3 = 0
        L_0x0170:
            if (r3 >= r0) goto L_0x017d
            eb r4 = r13.f7438a
            dv[] r4 = r4.f7818f
            r4 = r4[r3]
            r1[r3] = r4
            int r3 = r3 + 1
            goto L_0x0170
        L_0x017d:
        L_0x017e:
            if (r2 >= r0) goto L_0x018f
            r3 = r1[r2]
            if (r3 == r13) goto L_0x018c
            du r4 = r3.f7441d
            r4.mo4447a((p000.C0107dv) r3, (p000.C0107dv) r13)
            r3.mo4465a()
        L_0x018c:
            int r2 = r2 + 1
            goto L_0x017e
        L_0x018f:
            return
        L_0x0190:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0110dy.mo4556a(dv):void");
    }

    /* renamed from: a */
    public final void mo4558a(C0114eb ebVar, int i) {
        int i2 = ebVar.f7814b;
        if (i2 != -1) {
            C0107dv dvVar = this.f7622b[i2];
            if (dvVar.f7442e) {
                dvVar.f7439b = (float) i;
                return;
            }
            C0107dv b = mo4561b();
            b.mo4469a(ebVar, i);
            mo4556a(b);
            return;
        }
        C0107dv b2 = mo4561b();
        b2.f7438a = ebVar;
        float f = (float) i;
        ebVar.f7816d = f;
        b2.f7439b = f;
        b2.f7442e = true;
        mo4556a(b2);
    }

    /* renamed from: c */
    public final void mo4564c(C0114eb ebVar, C0114eb ebVar2, int i, int i2) {
        C0107dv b = mo4561b();
        b.mo4471a(ebVar, ebVar2, i);
        C0114eb d = mo4565d();
        C0114eb d2 = mo4565d();
        d.f7815c = i2;
        d2.f7815c = i2;
        b.mo4470a(d, d2);
        mo4556a(b);
    }

    /* renamed from: a */
    public final void mo4560a(C0114eb ebVar, C0114eb ebVar2, int i, int i2) {
        C0107dv b = mo4561b();
        C0114eb c = mo4563c();
        c.f7815c = i2;
        b.mo4473a(ebVar, ebVar2, c, i);
        mo4556a(b);
    }

    /* renamed from: b */
    public final void mo4562b(C0114eb ebVar, C0114eb ebVar2, int i, int i2) {
        C0107dv b = mo4561b();
        C0114eb c = mo4563c();
        c.f7815c = i2;
        b.mo4475b(ebVar, ebVar2, c, i);
        mo4556a(b);
    }

    /* renamed from: a */
    private final void m6889a(C0107dv dvVar, int i) {
        dvVar.f7441d.mo4448a(mo4565d(), (float) i);
    }

    /* renamed from: d */
    public final C0114eb mo4565d() {
        if (this.f7624d + 1 >= this.f7629j) {
            m6893e();
        }
        C0114eb a = m6888a(4);
        int i = this.f7627h + 1;
        this.f7627h = i;
        this.f7624d++;
        a.f7813a = i;
        this.f7626f.f7471c[i] = a;
        return a;
    }

    /* renamed from: a */
    public final C0114eb mo4554a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (this.f7624d + 1 >= this.f7629j) {
            m6893e();
        }
        C0115ec ecVar = (C0115ec) obj;
        C0114eb ebVar = ecVar.f7906e;
        if (ebVar == null) {
            ecVar.mo4671d();
            ebVar = ecVar.f7906e;
        }
        int i = ebVar.f7813a;
        if (i != -1) {
            if (i > this.f7627h || this.f7626f.f7471c[i] == null) {
                if (i != -1) {
                    ebVar.mo4645a();
                }
            }
            return ebVar;
        }
        int i2 = this.f7627h + 1;
        this.f7627h = i2;
        this.f7624d++;
        ebVar.f7813a = i2;
        ebVar.f7820h = 1;
        this.f7626f.f7471c[i2] = ebVar;
        return ebVar;
    }

    /* renamed from: b */
    public final C0107dv mo4561b() {
        C0107dv dvVar = (C0107dv) this.f7626f.f7469a.mo4602a();
        if (dvVar == null) {
            return new C0107dv(this.f7626f);
        }
        dvVar.f7438a = null;
        C0106du duVar = dvVar.f7441d;
        duVar.f7377f = -1;
        duVar.f7378g = -1;
        duVar.f7379h = false;
        duVar.f7372a = 0;
        dvVar.f7439b = 0.0f;
        dvVar.f7442e = false;
        return dvVar;
    }

    /* renamed from: a */
    public static C0107dv m6885a(C0110dy dyVar, C0114eb ebVar, C0114eb ebVar2, int i, float f, C0114eb ebVar3, C0114eb ebVar4, int i2, boolean z) {
        C0107dv b = dyVar.mo4561b();
        b.mo4472a(ebVar, ebVar2, i, f, ebVar3, ebVar4, i2);
        if (z) {
            C0114eb d = dyVar.mo4565d();
            C0114eb d2 = dyVar.mo4565d();
            d.f7815c = 4;
            d2.f7815c = 4;
            b.mo4470a(d, d2);
        }
        return b;
    }

    /* renamed from: a */
    public static C0107dv m6887a(C0110dy dyVar, C0114eb ebVar, C0114eb ebVar2, C0114eb ebVar3, float f) {
        C0107dv b = dyVar.mo4561b();
        b.f7441d.mo4448a(ebVar, -1.0f);
        b.f7441d.mo4448a(ebVar2, 1.0f - f);
        b.f7441d.mo4448a(ebVar3, f);
        return b;
    }

    /* renamed from: a */
    public static C0107dv m6886a(C0110dy dyVar, C0114eb ebVar, C0114eb ebVar2, int i, boolean z) {
        C0107dv b = dyVar.mo4561b();
        b.mo4471a(ebVar, ebVar2, i);
        if (z) {
            dyVar.m6889a(b, 1);
        }
        return b;
    }

    /* renamed from: b */
    public static C0107dv m6891b(C0110dy dyVar, C0114eb ebVar, C0114eb ebVar2, int i, boolean z) {
        C0114eb c = dyVar.mo4563c();
        C0107dv b = dyVar.mo4561b();
        b.mo4473a(ebVar, ebVar2, c, i);
        if (z) {
            dyVar.m6889a(b, (int) (-b.f7441d.mo4450b(c)));
        }
        return b;
    }

    /* renamed from: c */
    public static C0107dv m6892c(C0110dy dyVar, C0114eb ebVar, C0114eb ebVar2, int i, boolean z) {
        C0114eb c = dyVar.mo4563c();
        C0107dv b = dyVar.mo4561b();
        b.mo4475b(ebVar, ebVar2, c, i);
        if (z) {
            dyVar.m6889a(b, (int) (-b.f7441d.mo4450b(c)));
        }
        return b;
    }

    /* renamed from: c */
    public final C0114eb mo4563c() {
        if (this.f7624d + 1 >= this.f7629j) {
            m6893e();
        }
        C0114eb a = m6888a(3);
        int i = this.f7627h + 1;
        this.f7627h = i;
        this.f7624d++;
        a.f7813a = i;
        this.f7626f.f7471c[i] = a;
        return a;
    }

    /* renamed from: a */
    public final void mo4557a(C0109dx dxVar) {
        int i = 0;
        while (true) {
            if (i >= this.f7625e) {
                break;
            }
            C0107dv dvVar = this.f7622b[i];
            if (dvVar.f7438a.f7820h != 1 && dvVar.f7439b < 0.0f) {
                boolean z = false;
                while (!z) {
                    float f = Float.MAX_VALUE;
                    int i2 = -1;
                    int i3 = -1;
                    int i4 = 0;
                    for (int i5 = 0; i5 < this.f7625e; i5++) {
                        C0107dv dvVar2 = this.f7622b[i5];
                        if (dvVar2.f7438a.f7820h != 1 && dvVar2.f7439b < 0.0f) {
                            for (int i6 = 1; i6 < this.f7624d; i6++) {
                                C0114eb ebVar = this.f7626f.f7471c[i6];
                                float b = dvVar2.f7441d.mo4450b(ebVar);
                                if (b > 0.0f) {
                                    for (int i7 = 0; i7 < 6; i7++) {
                                        float f2 = ebVar.f7817e[i7] / b;
                                        if ((f2 < f && i7 == i4) || i7 > i4) {
                                            f = f2;
                                            i2 = i5;
                                            i3 = i6;
                                            i4 = i7;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (i2 != -1) {
                        C0107dv dvVar3 = this.f7622b[i2];
                        dvVar3.f7438a.f7814b = -1;
                        dvVar3.mo4468a(this.f7626f.f7471c[i3]);
                        dvVar3.f7438a.f7814b = i2;
                        for (int i8 = 0; i8 < this.f7625e; i8++) {
                            this.f7622b[i8].mo4467a(dvVar3);
                        }
                        dxVar.mo4534a(this);
                    } else {
                        C0109dx dxVar2 = dxVar;
                        z = true;
                    }
                }
            } else {
                C0109dx dxVar3 = dxVar;
                i++;
            }
        }
        int i9 = 0;
        while (i9 < this.f7625e) {
            C0107dv dvVar4 = this.f7622b[i9];
            if (dvVar4.f7438a.f7820h == 1 || dvVar4.f7439b >= 0.0f) {
                i9++;
            } else {
                return;
            }
        }
    }

    /* renamed from: e */
    private final void m6893e() {
        int i = this.f7628i;
        int i2 = i + i;
        this.f7628i = i2;
        this.f7622b = (C0107dv[]) Arrays.copyOf(this.f7622b, i2);
        C0108dw dwVar = this.f7626f;
        dwVar.f7471c = (C0114eb[]) Arrays.copyOf(dwVar.f7471c, this.f7628i);
        int i3 = this.f7628i;
        this.f7623c = new boolean[i3];
        this.f7629j = i3;
        this.f7630k = i3;
        this.f7621a.f7549a.clear();
    }

    /* renamed from: f */
    private final void m6894f() {
        int i = 0;
        while (true) {
            C0107dv[] dvVarArr = this.f7622b;
            if (i < dvVarArr.length) {
                C0107dv dvVar = dvVarArr[i];
                if (dvVar != null) {
                    this.f7626f.f7469a.mo4603a(dvVar);
                }
                this.f7622b[i] = null;
                i++;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public final void mo4555a() {
        C0108dw dwVar;
        int i = 0;
        while (true) {
            dwVar = this.f7626f;
            C0114eb[] ebVarArr = dwVar.f7471c;
            if (i >= ebVarArr.length) {
                break;
            }
            C0114eb ebVar = ebVarArr[i];
            if (ebVar != null) {
                ebVar.mo4645a();
            }
            i++;
        }
        C0111dz dzVar = dwVar.f7470b;
        C0114eb[] ebVarArr2 = this.f7631l;
        int i2 = this.f7632m;
        int length = ebVarArr2.length;
        if (i2 > length) {
            i2 = length;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            C0114eb ebVar2 = ebVarArr2[i3];
            C0113ea eaVar = (C0113ea) dzVar;
            int i4 = eaVar.f7759b;
            Object[] objArr = eaVar.f7758a;
            if (i4 < 256) {
                objArr[i4] = ebVar2;
                eaVar.f7759b = i4 + 1;
            }
        }
        this.f7632m = 0;
        Arrays.fill(this.f7626f.f7471c, (Object) null);
        this.f7627h = 0;
        this.f7621a.f7549a.clear();
        this.f7624d = 1;
        for (int i5 = 0; i5 < this.f7625e; i5++) {
            this.f7622b[i5].f7440c = false;
        }
        m6894f();
        this.f7625e = 0;
    }
}
