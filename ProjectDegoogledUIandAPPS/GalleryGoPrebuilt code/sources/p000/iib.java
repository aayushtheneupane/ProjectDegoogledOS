package p000;

import java.io.OutputStream;

/* renamed from: iib */
/* compiled from: PG */
final class iib extends iie {

    /* renamed from: c */
    private final byte[] f14227c;

    /* renamed from: d */
    private final int f14228d;

    /* renamed from: e */
    private int f14229e;

    /* renamed from: f */
    private int f14230f;

    /* renamed from: g */
    private final OutputStream f14231g;

    /* renamed from: b */
    private final void m13342b(byte b) {
        byte[] bArr = this.f14227c;
        int i = this.f14229e;
        this.f14229e = i + 1;
        bArr[i] = b;
        this.f14230f++;
    }

    /* renamed from: s */
    private final void m13349s(int i) {
        byte[] bArr = this.f14227c;
        int i2 = this.f14229e;
        int i3 = i2 + 1;
        this.f14229e = i3;
        bArr[i2] = (byte) i;
        int i4 = i3 + 1;
        this.f14229e = i4;
        bArr[i3] = (byte) (i >> 8);
        int i5 = i4 + 1;
        this.f14229e = i5;
        bArr[i4] = (byte) (i >> 16);
        this.f14229e = i5 + 1;
        bArr[i5] = (byte) (i >> 24);
        this.f14230f += 4;
    }

    /* renamed from: g */
    private final void m13346g(long j) {
        byte[] bArr = this.f14227c;
        int i = this.f14229e;
        int i2 = i + 1;
        this.f14229e = i2;
        bArr[i] = (byte) ((int) (j & 255));
        int i3 = i2 + 1;
        this.f14229e = i3;
        bArr[i2] = (byte) ((int) ((j >> 8) & 255));
        int i4 = i3 + 1;
        this.f14229e = i4;
        bArr[i3] = (byte) ((int) ((j >> 16) & 255));
        int i5 = i4 + 1;
        this.f14229e = i5;
        bArr[i4] = (byte) ((int) (255 & (j >> 24)));
        int i6 = i5 + 1;
        this.f14229e = i6;
        bArr[i5] = (byte) ((int) (j >> 32));
        int i7 = i6 + 1;
        this.f14229e = i7;
        bArr[i6] = (byte) ((int) (j >> 40));
        int i8 = i7 + 1;
        this.f14229e = i8;
        bArr[i7] = (byte) ((int) (j >> 48));
        this.f14229e = i8 + 1;
        bArr[i8] = (byte) ((int) (j >> 56));
        this.f14230f += 8;
    }

    /* renamed from: j */
    private final void m13347j(int i, int i2) {
        m13348r(imd.m14073a(i, i2));
    }

    /* renamed from: r */
    private final void m13348r(int i) {
        if (iie.f14235a) {
            long j = (long) this.f14229e;
            while ((i & -128) != 0) {
                byte[] bArr = this.f14227c;
                int i2 = this.f14229e;
                this.f14229e = i2 + 1;
                ilv.m14038a(bArr, (long) i2, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            byte[] bArr2 = this.f14227c;
            int i3 = this.f14229e;
            this.f14229e = i3 + 1;
            ilv.m14038a(bArr2, (long) i3, (byte) i);
            this.f14230f += (int) (((long) this.f14229e) - j);
            return;
        }
        while ((i & -128) != 0) {
            byte[] bArr3 = this.f14227c;
            int i4 = this.f14229e;
            this.f14229e = i4 + 1;
            bArr3[i4] = (byte) ((i & 127) | 128);
            this.f14230f++;
            i >>>= 7;
        }
        byte[] bArr4 = this.f14227c;
        int i5 = this.f14229e;
        this.f14229e = i5 + 1;
        bArr4[i5] = (byte) i;
        this.f14230f++;
    }

    /* renamed from: f */
    private final void m13345f(long j) {
        if (iie.f14235a) {
            long j2 = (long) this.f14229e;
            while ((j & -128) != 0) {
                byte[] bArr = this.f14227c;
                int i = this.f14229e;
                this.f14229e = i + 1;
                ilv.m14038a(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            byte[] bArr2 = this.f14227c;
            int i2 = this.f14229e;
            this.f14229e = i2 + 1;
            ilv.m14038a(bArr2, (long) i2, (byte) ((int) j));
            this.f14230f += (int) (((long) this.f14229e) - j2);
            return;
        }
        while ((j & -128) != 0) {
            byte[] bArr3 = this.f14227c;
            int i3 = this.f14229e;
            this.f14229e = i3 + 1;
            bArr3[i3] = (byte) ((((int) j) & 127) | 128);
            this.f14230f++;
            j >>>= 7;
        }
        byte[] bArr4 = this.f14227c;
        int i4 = this.f14229e;
        this.f14229e = i4 + 1;
        bArr4[i4] = (byte) ((int) j);
        this.f14230f++;
    }

    /* renamed from: a */
    public final int mo8652a() {
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
    }

    public iib(OutputStream outputStream, int i) {
        super((byte[]) null);
        if (i >= 0) {
            byte[] bArr = new byte[Math.max(i, 20)];
            this.f14227c = bArr;
            this.f14228d = bArr.length;
            if (outputStream != null) {
                this.f14231g = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }
        throw new IllegalArgumentException("bufferSize must be >= 0");
    }

    /* renamed from: d */
    private final void m13344d() {
        this.f14231g.write(this.f14227c, 0, this.f14229e);
        this.f14229e = 0;
    }

    /* renamed from: b */
    public final void mo8667b() {
        if (this.f14229e > 0) {
            m13344d();
        }
    }

    /* renamed from: t */
    private final void m13350t(int i) {
        if (this.f14228d - this.f14229e < i) {
            m13344d();
        }
    }

    /* renamed from: a */
    public final void mo8653a(byte b) {
        if (this.f14229e == this.f14228d) {
            m13344d();
        }
        m13342b(b);
    }

    /* renamed from: b */
    private final void m13343b(byte[] bArr, int i, int i2) {
        int i3 = this.f14228d;
        int i4 = this.f14229e;
        int i5 = i3 - i4;
        if (i5 < i2) {
            System.arraycopy(bArr, i, this.f14227c, i4, i5);
            int i6 = i + i5;
            int i7 = i2 - i5;
            this.f14229e = this.f14228d;
            this.f14230f += i5;
            m13344d();
            if (i7 <= this.f14228d) {
                System.arraycopy(bArr, i6, this.f14227c, 0, i7);
                this.f14229e = i7;
            } else {
                this.f14231g.write(bArr, i6, i7);
            }
            this.f14230f += i7;
            return;
        }
        System.arraycopy(bArr, i, this.f14227c, i4, i2);
        this.f14229e += i2;
        this.f14230f += i2;
    }

    /* renamed from: a */
    public final void mo8661a(int i, boolean z) {
        m13350t(11);
        m13347j(i, 0);
        m13342b(z ? (byte) 1 : 0);
    }

    /* renamed from: a */
    public final void mo8666a(byte[] bArr, int i) {
        mo8668b(i);
        m13343b(bArr, 0, i);
    }

    /* renamed from: a */
    public final void mo8657a(int i, ihw ihw) {
        mo8655a(i, 2);
        mo8663a(ihw);
    }

    /* renamed from: a */
    public final void mo8663a(ihw ihw) {
        mo8668b(ihw.mo8597a());
        ihw.mo8609a((ihk) this);
    }

    /* renamed from: d */
    public final void mo8675d(int i, int i2) {
        m13350t(14);
        m13347j(i, 5);
        m13349s(i2);
    }

    /* renamed from: c */
    public final void mo8673c(int i) {
        m13350t(4);
        m13349s(i);
    }

    /* renamed from: b */
    public final void mo8670b(int i, long j) {
        m13350t(18);
        m13347j(i, 1);
        m13346g(j);
    }

    /* renamed from: b */
    public final void mo8672b(long j) {
        m13350t(8);
        m13346g(j);
    }

    /* renamed from: b */
    public final void mo8669b(int i, int i2) {
        m13350t(20);
        m13347j(i, 0);
        if (i2 < 0) {
            m13345f((long) i2);
        } else {
            m13348r(i2);
        }
    }

    /* renamed from: a */
    public final void mo8654a(int i) {
        if (i < 0) {
            mo8662a((long) i);
        } else {
            mo8668b(i);
        }
    }

    /* renamed from: a */
    public final void mo8590a(byte[] bArr, int i, int i2) {
        m13343b(bArr, i, i2);
    }

    /* renamed from: a */
    public final void mo8659a(int i, ikf ikf, iky iky) {
        mo8655a(i, 2);
        iha iha = (iha) ikf;
        int d = iha.mo8515d();
        if (d == -1) {
            d = iky.mo8869b(iha);
            iha.mo8510a(d);
        }
        mo8668b(d);
        iky.mo8866a((Object) ikf, (ime) this.f14237b);
    }

    /* renamed from: a */
    public final void mo8664a(ikf ikf) {
        mo8668b(ikf.mo8795i());
        ikf.mo8789a((iie) this);
    }

    /* renamed from: a */
    public final void mo8658a(int i, ikf ikf) {
        mo8655a(1, 3);
        mo8674c(2, i);
        mo8655a(3, 2);
        mo8664a(ikf);
        mo8655a(1, 4);
    }

    /* renamed from: b */
    public final void mo8671b(int i, ihw ihw) {
        mo8655a(1, 3);
        mo8674c(2, i);
        mo8657a(3, ihw);
        mo8655a(1, 4);
    }

    /* renamed from: a */
    public final void mo8660a(int i, String str) {
        mo8655a(i, 2);
        mo8665a(str);
    }

    /* renamed from: a */
    public final void mo8665a(String str) {
        int i;
        int i2;
        try {
            int length = str.length() * 3;
            int h = m13426h(length);
            int i3 = h + length;
            int i4 = this.f14228d;
            if (i3 > i4) {
                byte[] bArr = new byte[length];
                int a = ima.m14068a(str, bArr, 0, length);
                mo8668b(a);
                m13343b(bArr, 0, a);
                return;
            }
            if (i3 > i4 - this.f14229e) {
                m13344d();
            }
            int h2 = m13426h(str.length());
            i = this.f14229e;
            if (h2 != h) {
                i2 = ima.m14067a((CharSequence) str);
                m13348r(i2);
                this.f14229e = ima.m14068a(str, this.f14227c, this.f14229e, i2);
            } else {
                int i5 = i + h2;
                this.f14229e = i5;
                int a2 = ima.m14068a(str, this.f14227c, i5, this.f14228d - i5);
                this.f14229e = i;
                i2 = (a2 - i) - h2;
                m13348r(i2);
                this.f14229e = a2;
            }
            this.f14230f += i2;
        } catch (ily e) {
            this.f14230f -= this.f14229e - i;
            this.f14229e = i;
            throw e;
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new iid(e2);
        } catch (ily e3) {
            mo8680a(str, e3);
        }
    }

    /* renamed from: a */
    public final void mo8655a(int i, int i2) {
        mo8668b(imd.m14073a(i, i2));
    }

    /* renamed from: c */
    public final void mo8674c(int i, int i2) {
        m13350t(20);
        m13347j(i, 0);
        m13348r(i2);
    }

    /* renamed from: b */
    public final void mo8668b(int i) {
        m13350t(5);
        m13348r(i);
    }

    /* renamed from: a */
    public final void mo8656a(int i, long j) {
        m13350t(20);
        m13347j(i, 0);
        m13345f(j);
    }

    /* renamed from: a */
    public final void mo8662a(long j) {
        m13350t(10);
        m13345f(j);
    }
}
