package p000;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: ihy */
/* compiled from: PG */
final class ihy extends ihz {

    /* renamed from: c */
    private final InputStream f14212c;

    /* renamed from: d */
    private final byte[] f14213d;

    /* renamed from: e */
    private int f14214e;

    /* renamed from: f */
    private int f14215f;

    /* renamed from: g */
    private int f14216g;

    /* renamed from: h */
    private int f14217h;

    /* renamed from: i */
    private int f14218i;

    /* renamed from: j */
    private int f14219j = Integer.MAX_VALUE;

    public /* synthetic */ ihy(InputStream inputStream) {
        super((byte[]) null);
        ijf.m13648a((Object) inputStream, "input");
        this.f14212c = inputStream;
        this.f14213d = new byte[4096];
        this.f14214e = 0;
        this.f14216g = 0;
        this.f14218i = 0;
    }

    /* renamed from: t */
    public final int mo8651t() {
        return this.f14218i + this.f14216g;
    }

    /* renamed from: a */
    public final void mo8629a(int i) {
        if (this.f14217h != i) {
            throw ijh.m13658e();
        }
    }

    /* renamed from: s */
    public final boolean mo8650s() {
        return this.f14216g == this.f14214e && !m13224g(1);
    }

    /* renamed from: d */
    public final void mo8635d(int i) {
        this.f14219j = i;
        m13234z();
    }

    /* renamed from: c */
    public final int mo8633c(int i) {
        if (i >= 0) {
            int i2 = i + this.f14218i + this.f14216g;
            int i3 = this.f14219j;
            if (i2 <= i3) {
                this.f14219j = i2;
                m13234z();
                return i3;
            }
            throw ijh.m13654a();
        }
        throw ijh.m13655b();
    }

    /* renamed from: i */
    public final boolean mo8640i() {
        return m13230v() != 0;
    }

    /* renamed from: l */
    public final ihw mo8643l() {
        int u = m13229u();
        int i = this.f14214e;
        int i2 = this.f14216g;
        if (u <= i - i2 && u > 0) {
            ihw a = ihw.m13163a(this.f14213d, i2, u);
            this.f14216g += u;
            return a;
        } else if (u == 0) {
            return ihw.f14203b;
        } else {
            byte[] h = m13225h(u);
            if (h != null) {
                return ihw.m13162a(h);
            }
            int i3 = this.f14216g;
            int i4 = this.f14214e;
            int i5 = i4 - i3;
            this.f14218i += i4;
            this.f14216g = 0;
            this.f14214e = 0;
            List i6 = m13226i(u - i5);
            byte[] bArr = new byte[u];
            System.arraycopy(this.f14213d, i3, bArr, 0, i5);
            int size = i6.size();
            for (int i7 = 0; i7 < size; i7++) {
                byte[] bArr2 = (byte[]) i6.get(i7);
                int length = bArr2.length;
                System.arraycopy(bArr2, 0, bArr, i5, length);
                i5 += length;
            }
            return ihw.m13164b(bArr);
        }
    }

    /* renamed from: b */
    public final double mo8630b() {
        return Double.longBitsToDouble(m13233y());
    }

    /* renamed from: n */
    public final int mo8645n() {
        return m13229u();
    }

    /* renamed from: h */
    public final int mo8639h() {
        return m13232x();
    }

    /* renamed from: g */
    public final long mo8638g() {
        return m13233y();
    }

    /* renamed from: c */
    public final float mo8632c() {
        return Float.intBitsToFloat(m13232x());
    }

    /* renamed from: f */
    public final int mo8637f() {
        return m13229u();
    }

    /* renamed from: e */
    public final long mo8636e() {
        return m13230v();
    }

    /* renamed from: A */
    private final byte m13222A() {
        if (this.f14216g == this.f14214e) {
            m13223f(1);
        }
        byte[] bArr = this.f14213d;
        int i = this.f14216g;
        this.f14216g = i + 1;
        return bArr[i];
    }

    /* renamed from: k */
    private final byte[] m13228k(int i) {
        byte[] h = m13225h(i);
        if (h != null) {
            return h;
        }
        int i2 = this.f14216g;
        int i3 = this.f14214e;
        int i4 = i3 - i2;
        this.f14218i += i3;
        this.f14216g = 0;
        this.f14214e = 0;
        List i5 = m13226i(i - i4);
        byte[] bArr = new byte[i];
        System.arraycopy(this.f14213d, i2, bArr, 0, i4);
        int size = i5.size();
        for (int i6 = 0; i6 < size; i6++) {
            byte[] bArr2 = (byte[]) i5.get(i6);
            int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i4, length);
            i4 += length;
        }
        return bArr;
    }

    /* renamed from: h */
    private final byte[] m13225h(int i) {
        if (i == 0) {
            return ijf.f14337b;
        }
        if (i >= 0) {
            int i2 = this.f14218i;
            int i3 = this.f14216g;
            int i4 = i2 + i3 + i;
            if (-2147483647 + i4 <= 0) {
                int i5 = this.f14219j;
                if (i4 <= i5) {
                    int i6 = this.f14214e - i3;
                    int i7 = i - i6;
                    if (i7 >= 4096 && i7 > this.f14212c.available()) {
                        return null;
                    }
                    byte[] bArr = new byte[i];
                    System.arraycopy(this.f14213d, this.f14216g, bArr, 0, i6);
                    this.f14218i += this.f14214e;
                    this.f14216g = 0;
                    this.f14214e = 0;
                    while (i6 < i) {
                        int read = this.f14212c.read(bArr, i6, i - i6);
                        if (read != -1) {
                            this.f14218i += read;
                            i6 += read;
                        } else {
                            throw ijh.m13654a();
                        }
                    }
                    return bArr;
                }
                m13227j((i5 - i2) - i3);
                throw ijh.m13654a();
            }
            throw ijh.m13660g();
        }
        throw ijh.m13655b();
    }

    /* renamed from: i */
    private final List m13226i(int i) {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            int min = Math.min(i, 4096);
            byte[] bArr = new byte[min];
            int i2 = 0;
            while (i2 < min) {
                int read = this.f14212c.read(bArr, i2, min - i2);
                if (read != -1) {
                    this.f14218i += read;
                    i2 += read;
                } else {
                    throw ijh.m13654a();
                }
            }
            i -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    /* renamed from: x */
    private final int m13232x() {
        int i = this.f14216g;
        if (this.f14214e - i < 4) {
            m13223f(4);
            i = this.f14216g;
        }
        byte[] bArr = this.f14213d;
        this.f14216g = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* renamed from: y */
    private final long m13233y() {
        int i = this.f14216g;
        if (this.f14214e - i < 8) {
            m13223f(8);
            i = this.f14216g;
        }
        byte[] bArr = this.f14213d;
        this.f14216g = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        if (r2[r3] >= 0) goto L_0x006a;
     */
    /* renamed from: u */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int m13229u() {
        /*
            r5 = this;
            int r0 = r5.f14216g
            int r1 = r5.f14214e
            if (r1 == r0) goto L_0x0070
            byte[] r2 = r5.f14213d
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
            r5.f14216g = r1
            return r0
        L_0x006d:
            r5.f14216g = r3
            return r0
        L_0x0070:
            long r0 = r5.m13231w()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ihy.m13229u():int");
    }

    /* renamed from: v */
    private final long m13230v() {
        int i;
        long j;
        byte b;
        long j2;
        long j3;
        int i2 = this.f14216g;
        int i3 = this.f14214e;
        if (i3 != i2) {
            byte[] bArr = this.f14213d;
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 >= 0) {
                this.f14216g = i4;
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
                    this.f14216g = i;
                    return j;
                }
                b = b3 ^ Byte.MIN_VALUE;
                i = i5;
                j = (long) b;
                this.f14216g = i;
                return j;
            }
        }
        return m13231w();
    }

    /* renamed from: w */
    private final long m13231w() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte A = m13222A();
            j |= ((long) (A & Byte.MAX_VALUE)) << i;
            if ((A & 128) == 0) {
                return j;
            }
        }
        throw ijh.m13656c();
    }

    /* renamed from: o */
    public final int mo8646o() {
        return m13232x();
    }

    /* renamed from: p */
    public final long mo8647p() {
        return m13233y();
    }

    /* renamed from: q */
    public final int mo8648q() {
        return m13264e(m13229u());
    }

    /* renamed from: r */
    public final long mo8649r() {
        return m13260a(m13230v());
    }

    /* renamed from: j */
    public final String mo8641j() {
        int u = m13229u();
        if (u > 0) {
            int i = this.f14214e;
            int i2 = this.f14216g;
            if (u <= i - i2) {
                String str = new String(this.f14213d, i2, u, ijf.f14336a);
                this.f14216g += u;
                return str;
            }
        }
        if (u == 0) {
            return "";
        }
        if (u > this.f14214e) {
            return new String(m13228k(u), ijf.f14336a);
        }
        m13223f(u);
        String str2 = new String(this.f14213d, this.f14216g, u, ijf.f14336a);
        this.f14216g += u;
        return str2;
    }

    /* renamed from: k */
    public final String mo8642k() {
        byte[] bArr;
        int u = m13229u();
        int i = this.f14216g;
        int i2 = this.f14214e;
        if (u <= i2 - i && u > 0) {
            bArr = this.f14213d;
            this.f14216g = i + u;
        } else if (u == 0) {
            return "";
        } else {
            if (u <= i2) {
                m13223f(u);
                bArr = this.f14213d;
                this.f14216g = u;
                i = 0;
            } else {
                bArr = m13228k(u);
                i = 0;
            }
        }
        return ima.m14071c(bArr, i, u);
    }

    /* renamed from: a */
    public final int mo8628a() {
        if (mo8650s()) {
            this.f14217h = 0;
            return 0;
        }
        int u = m13229u();
        this.f14217h = u;
        if (imd.m14074b(u) != 0) {
            return this.f14217h;
        }
        throw ijh.m13657d();
    }

    /* renamed from: m */
    public final int mo8644m() {
        return m13229u();
    }

    /* renamed from: d */
    public final long mo8634d() {
        return m13230v();
    }

    /* renamed from: z */
    private final void m13234z() {
        int i = this.f14214e + this.f14215f;
        this.f14214e = i;
        int i2 = this.f14218i + i;
        int i3 = this.f14219j;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.f14215f = i4;
            this.f14214e = i - i4;
            return;
        }
        this.f14215f = 0;
    }

    /* renamed from: f */
    private final void m13223f(int i) {
        if (m13224g(i)) {
            return;
        }
        if (i <= (Integer.MAX_VALUE - this.f14218i) - this.f14216g) {
            throw ijh.m13654a();
        }
        throw ijh.m13660g();
    }

    /* renamed from: b */
    public final boolean mo8631b(int i) {
        int a;
        int a2 = imd.m14072a(i);
        int i2 = 0;
        if (a2 == 0) {
            if (this.f14214e - this.f14216g < 10) {
                while (i2 < 10) {
                    if (m13222A() < 0) {
                        i2++;
                    }
                }
                throw ijh.m13656c();
            }
            while (i2 < 10) {
                byte[] bArr = this.f14213d;
                int i3 = this.f14216g;
                this.f14216g = i3 + 1;
                if (bArr[i3] < 0) {
                    i2++;
                }
            }
            throw ijh.m13656c();
            return true;
        } else if (a2 == 1) {
            m13227j(8);
            return true;
        } else if (a2 == 2) {
            m13227j(m13229u());
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
                m13227j(4);
                return true;
            }
            throw ijh.m13659f();
        }
    }

    /* renamed from: j */
    private final void m13227j(int i) {
        int i2 = this.f14214e;
        int i3 = this.f14216g;
        int i4 = i2 - i3;
        if (i <= i4 && i >= 0) {
            this.f14216g = i3 + i;
        } else if (i >= 0) {
            int i5 = this.f14218i;
            int i6 = i5 + i3;
            int i7 = this.f14219j;
            if (i6 + i <= i7) {
                this.f14218i = i6;
                this.f14214e = 0;
                this.f14216g = 0;
                while (i4 < i) {
                    try {
                        long j = (long) (i - i4);
                        long skip = this.f14212c.skip(j);
                        if (skip >= 0 && skip <= j) {
                            if (skip == 0) {
                                break;
                            }
                            i4 += (int) skip;
                        } else {
                            String valueOf = String.valueOf(this.f14212c.getClass());
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 92);
                            sb.append(valueOf);
                            sb.append("#skip returned invalid result: ");
                            sb.append(skip);
                            sb.append("\nThe InputStream implementation is buggy.");
                            throw new IllegalStateException(sb.toString());
                        }
                    } catch (Throwable th) {
                        this.f14218i += i4;
                        m13234z();
                        throw th;
                    }
                }
                this.f14218i += i4;
                m13234z();
                if (i4 < i) {
                    int i8 = this.f14214e;
                    int i9 = i8 - this.f14216g;
                    this.f14216g = i8;
                    m13223f(1);
                    while (true) {
                        int i10 = i - i9;
                        int i11 = this.f14214e;
                        if (i10 > i11) {
                            i9 += i11;
                            this.f14216g = i11;
                            m13223f(1);
                        } else {
                            this.f14216g = i10;
                            return;
                        }
                    }
                }
            } else {
                m13227j((i7 - i5) - i3);
                throw ijh.m13654a();
            }
        } else {
            throw ijh.m13655b();
        }
    }

    /* renamed from: g */
    private final boolean m13224g(int i) {
        int i2 = this.f14216g;
        int i3 = this.f14214e;
        if (i2 + i > i3) {
            int i4 = this.f14218i;
            if (i > (Integer.MAX_VALUE - i4) - i2 || i4 + i2 + i > this.f14219j) {
                return false;
            }
            if (i2 > 0) {
                if (i3 > i2) {
                    byte[] bArr = this.f14213d;
                    System.arraycopy(bArr, i2, bArr, 0, i3 - i2);
                }
                i4 = this.f14218i + i2;
                this.f14218i = i4;
                i3 = this.f14214e - i2;
                this.f14214e = i3;
                this.f14216g = 0;
            }
            InputStream inputStream = this.f14212c;
            byte[] bArr2 = this.f14213d;
            int read = inputStream.read(bArr2, i3, Math.min(bArr2.length - i3, (Integer.MAX_VALUE - i4) - i3));
            if (read == 0 || read < -1 || read > this.f14213d.length) {
                String valueOf = String.valueOf(this.f14212c.getClass());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 91);
                sb.append(valueOf);
                sb.append("#read(byte[]) returned invalid result: ");
                sb.append(read);
                sb.append("\nThe InputStream implementation is buggy.");
                throw new IllegalStateException(sb.toString());
            } else if (read <= 0) {
                return false;
            } else {
                this.f14214e += read;
                m13234z();
                if (this.f14214e >= i) {
                    return true;
                }
                return m13224g(i);
            }
        } else {
            StringBuilder sb2 = new StringBuilder(77);
            sb2.append("refillBuffer() called when ");
            sb2.append(i);
            sb2.append(" bytes were already available in buffer");
            throw new IllegalStateException(sb2.toString());
        }
    }
}
