package p000;

import java.util.Arrays;

/* renamed from: ihx */
/* compiled from: PG */
final class ihx extends ihz {

    /* renamed from: c */
    private final byte[] f14205c;

    /* renamed from: d */
    private int f14206d;

    /* renamed from: e */
    private int f14207e;

    /* renamed from: f */
    private int f14208f;

    /* renamed from: g */
    private final int f14209g;

    /* renamed from: h */
    private int f14210h;

    /* renamed from: i */
    private int f14211i = Integer.MAX_VALUE;

    public /* synthetic */ ihx(byte[] bArr, int i, int i2) {
        super((byte[]) null);
        this.f14205c = bArr;
        this.f14206d = i2 + i;
        this.f14208f = i;
        this.f14209g = i;
    }

    /* renamed from: s */
    public final boolean mo8650s() {
        return this.f14208f == this.f14206d;
    }

    /* renamed from: t */
    public final int mo8651t() {
        return this.f14208f - this.f14209g;
    }

    /* renamed from: a */
    public final void mo8629a(int i) {
        if (this.f14210h != i) {
            throw ijh.m13658e();
        }
    }

    /* renamed from: d */
    public final void mo8635d(int i) {
        this.f14211i = i;
        m13197z();
    }

    /* renamed from: c */
    public final int mo8633c(int i) {
        if (i >= 0) {
            int t = i + mo8651t();
            int i2 = this.f14211i;
            if (t <= i2) {
                this.f14211i = t;
                m13197z();
                return i2;
            }
            throw ijh.m13654a();
        }
        throw ijh.m13655b();
    }

    /* renamed from: i */
    public final boolean mo8640i() {
        return m13193v() != 0;
    }

    /* renamed from: l */
    public final ihw mo8643l() {
        int u = m13192u();
        if (u > 0) {
            int i = this.f14206d;
            int i2 = this.f14208f;
            if (u <= i - i2) {
                ihw a = ihw.m13163a(this.f14205c, i2, u);
                this.f14208f += u;
                return a;
            }
        }
        if (u == 0) {
            return ihw.f14203b;
        }
        if (u > 0) {
            int i3 = this.f14206d;
            int i4 = this.f14208f;
            if (u <= i3 - i4) {
                int i5 = u + i4;
                this.f14208f = i5;
                return ihw.m13164b(Arrays.copyOfRange(this.f14205c, i4, i5));
            }
        }
        if (u <= 0) {
            throw ijh.m13655b();
        }
        throw ijh.m13654a();
    }

    /* renamed from: b */
    public final double mo8630b() {
        return Double.longBitsToDouble(m13196y());
    }

    /* renamed from: n */
    public final int mo8645n() {
        return m13192u();
    }

    /* renamed from: h */
    public final int mo8639h() {
        return m13195x();
    }

    /* renamed from: g */
    public final long mo8638g() {
        return m13196y();
    }

    /* renamed from: c */
    public final float mo8632c() {
        return Float.intBitsToFloat(m13195x());
    }

    /* renamed from: f */
    public final int mo8637f() {
        return m13192u();
    }

    /* renamed from: e */
    public final long mo8636e() {
        return m13193v();
    }

    /* renamed from: A */
    private final byte m13190A() {
        int i = this.f14208f;
        if (i != this.f14206d) {
            byte[] bArr = this.f14205c;
            this.f14208f = i + 1;
            return bArr[i];
        }
        throw ijh.m13654a();
    }

    /* renamed from: x */
    private final int m13195x() {
        int i = this.f14208f;
        if (this.f14206d - i >= 4) {
            byte[] bArr = this.f14205c;
            this.f14208f = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }
        throw ijh.m13654a();
    }

    /* renamed from: y */
    private final long m13196y() {
        int i = this.f14208f;
        if (this.f14206d - i >= 8) {
            byte[] bArr = this.f14205c;
            this.f14208f = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw ijh.m13654a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        if (r2[r3] >= 0) goto L_0x006a;
     */
    /* renamed from: u */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int m13192u() {
        /*
            r5 = this;
            int r0 = r5.f14208f
            int r1 = r5.f14206d
            if (r1 == r0) goto L_0x0070
            byte[] r2 = r5.f14205c
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 >= 0) goto L_0x006d
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x0070
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 < 0) goto L_0x0066
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 >= 0) goto L_0x0061
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 < 0) goto L_0x005c
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x005a
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006a
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x005a
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006a
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x005a
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x0070
            goto L_0x006a
        L_0x005a:
            r1 = r3
            goto L_0x006a
        L_0x005c:
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0068
        L_0x0061:
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r3
            goto L_0x006a
        L_0x0066:
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
        L_0x0068:
        L_0x006a:
            r5.f14208f = r1
            return r0
        L_0x006d:
            r5.f14208f = r3
            return r0
        L_0x0070:
            long r0 = r5.m13194w()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ihx.m13192u():int");
    }

    /* renamed from: v */
    private final long m13193v() {
        int i;
        long j;
        byte b;
        long j2;
        long j3;
        int i2 = this.f14208f;
        int i3 = this.f14206d;
        if (i3 != i2) {
            byte[] bArr = this.f14205c;
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 >= 0) {
                this.f14208f = i4;
                return (long) b2;
            } else if (i3 - i4 >= 9) {
                int i5 = i4 + 1;
                byte b3 = b2 ^ (bArr[i4] << 7);
                if (b3 >= 0) {
                    i = i5 + 1;
                    byte b4 = b3 ^ (bArr[i5] << 14);
                    if (b4 < 0) {
                        i5 = i + 1;
                        byte b5 = b4 ^ (bArr[i] << 21);
                        if (b5 >= 0) {
                            i = i5 + 1;
                            long j4 = (((long) bArr[i5]) << 28) ^ ((long) b5);
                            if (j4 < 0) {
                                int i6 = i + 1;
                                long j5 = j4 ^ (((long) bArr[i]) << 35);
                                if (j5 >= 0) {
                                    i = i6 + 1;
                                    j4 = j5 ^ (((long) bArr[i6]) << 42);
                                    if (j4 < 0) {
                                        i6 = i + 1;
                                        j5 = j4 ^ (((long) bArr[i]) << 49);
                                        if (j5 >= 0) {
                                            i = i6 + 1;
                                            j = (j5 ^ (((long) bArr[i6]) << 56)) ^ 71499008037633920L;
                                            if (j < 0) {
                                                int i7 = i + 1;
                                                if (((long) bArr[i]) >= 0) {
                                                    i = i7;
                                                }
                                            }
                                        } else {
                                            j3 = -558586000294016L;
                                        }
                                    } else {
                                        j2 = 4363953127296L;
                                    }
                                } else {
                                    j3 = -34093383808L;
                                }
                                j = j5 ^ j3;
                                i = i6;
                            } else {
                                j2 = 266354560;
                            }
                            j = j4 ^ j2;
                        } else {
                            b = b5 ^ -2080896;
                        }
                    } else {
                        j = (long) (b4 ^ 16256);
                    }
                    this.f14208f = i;
                    return j;
                }
                b = b3 ^ Byte.MIN_VALUE;
                i = i5;
                j = (long) b;
                this.f14208f = i;
                return j;
            }
        }
        return m13194w();
    }

    /* renamed from: w */
    private final long m13194w() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte A = m13190A();
            j |= ((long) (A & Byte.MAX_VALUE)) << i;
            if ((A & 128) == 0) {
                return j;
            }
        }
        throw ijh.m13656c();
    }

    /* renamed from: o */
    public final int mo8646o() {
        return m13195x();
    }

    /* renamed from: p */
    public final long mo8647p() {
        return m13196y();
    }

    /* renamed from: q */
    public final int mo8648q() {
        return m13264e(m13192u());
    }

    /* renamed from: r */
    public final long mo8649r() {
        return m13260a(m13193v());
    }

    /* renamed from: j */
    public final String mo8641j() {
        int u = m13192u();
        if (u > 0) {
            int i = this.f14206d;
            int i2 = this.f14208f;
            if (u <= i - i2) {
                String str = new String(this.f14205c, i2, u, ijf.f14336a);
                this.f14208f += u;
                return str;
            }
        }
        if (u == 0) {
            return "";
        }
        if (u < 0) {
            throw ijh.m13655b();
        }
        throw ijh.m13654a();
    }

    /* renamed from: k */
    public final String mo8642k() {
        int u = m13192u();
        if (u > 0) {
            int i = this.f14206d;
            int i2 = this.f14208f;
            if (u <= i - i2) {
                String c = ima.m14071c(this.f14205c, i2, u);
                this.f14208f += u;
                return c;
            }
        }
        if (u == 0) {
            return "";
        }
        if (u <= 0) {
            throw ijh.m13655b();
        }
        throw ijh.m13654a();
    }

    /* renamed from: a */
    public final int mo8628a() {
        if (mo8650s()) {
            this.f14210h = 0;
            return 0;
        }
        int u = m13192u();
        this.f14210h = u;
        if (imd.m14074b(u) != 0) {
            return this.f14210h;
        }
        throw ijh.m13657d();
    }

    /* renamed from: m */
    public final int mo8644m() {
        return m13192u();
    }

    /* renamed from: d */
    public final long mo8634d() {
        return m13193v();
    }

    /* renamed from: z */
    private final void m13197z() {
        int i = this.f14206d + this.f14207e;
        this.f14206d = i;
        int i2 = i - this.f14209g;
        int i3 = this.f14211i;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.f14207e = i4;
            this.f14206d = i - i4;
            return;
        }
        this.f14207e = 0;
    }

    /* renamed from: b */
    public final boolean mo8631b(int i) {
        int a;
        int a2 = imd.m14072a(i);
        int i2 = 0;
        if (a2 == 0) {
            if (this.f14206d - this.f14208f < 10) {
                while (i2 < 10) {
                    if (m13190A() < 0) {
                        i2++;
                    }
                }
                throw ijh.m13656c();
            }
            while (i2 < 10) {
                byte[] bArr = this.f14205c;
                int i3 = this.f14208f;
                this.f14208f = i3 + 1;
                if (bArr[i3] < 0) {
                    i2++;
                }
            }
            throw ijh.m13656c();
            return true;
        } else if (a2 == 1) {
            m13191f(8);
            return true;
        } else if (a2 == 2) {
            m13191f(m13192u());
            return true;
        } else if (a2 == 3) {
            do {
                a = mo8628a();
                if (a == 0 || !mo8631b(a)) {
                    mo8629a(imd.m14073a(imd.m14074b(i), 4));
                }
                a = mo8628a();
                break;
            } while (!mo8631b(a));
            mo8629a(imd.m14073a(imd.m14074b(i), 4));
            return true;
        } else if (a2 == 4) {
            return false;
        } else {
            if (a2 == 5) {
                m13191f(4);
                return true;
            }
            throw ijh.m13659f();
        }
    }

    /* renamed from: f */
    private final void m13191f(int i) {
        if (i >= 0) {
            int i2 = this.f14206d;
            int i3 = this.f14208f;
            if (i <= i2 - i3) {
                this.f14208f = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw ijh.m13655b();
        }
        throw ijh.m13654a();
    }
}
