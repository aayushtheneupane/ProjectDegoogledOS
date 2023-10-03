package p000;

/* renamed from: iic */
/* compiled from: PG */
final class iic extends iie {

    /* renamed from: c */
    private final byte[] f14232c;

    /* renamed from: d */
    private final int f14233d;

    /* renamed from: e */
    private int f14234e;

    public iic(byte[] bArr, int i) {
        super((byte[]) null);
        if (bArr != null) {
            int length = bArr.length;
            if (((length - i) | i) >= 0) {
                this.f14232c = bArr;
                this.f14234e = 0;
                this.f14233d = i;
                return;
            }
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(length), 0, Integer.valueOf(i)}));
        }
        throw new NullPointerException("buffer");
    }

    /* renamed from: a */
    public final int mo8652a() {
        return this.f14233d - this.f14234e;
    }

    /* renamed from: b */
    public final void mo8667b() {
    }

    /* renamed from: a */
    public final void mo8653a(byte b) {
        try {
            byte[] bArr = this.f14232c;
            int i = this.f14234e;
            this.f14234e = i + 1;
            bArr[i] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new iid(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f14234e), Integer.valueOf(this.f14233d), 1}), e);
        }
    }

    /* renamed from: b */
    private final void m13376b(byte[] bArr, int i, int i2) {
        try {
            System.arraycopy(bArr, i, this.f14232c, this.f14234e, i2);
            this.f14234e += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new iid(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f14234e), Integer.valueOf(this.f14233d), Integer.valueOf(i2)}), e);
        }
    }

    /* renamed from: a */
    public final void mo8661a(int i, boolean z) {
        mo8655a(i, 0);
        mo8653a(z ? (byte) 1 : 0);
    }

    /* renamed from: a */
    public final void mo8666a(byte[] bArr, int i) {
        mo8668b(i);
        m13376b(bArr, 0, i);
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
        mo8655a(i, 5);
        mo8673c(i2);
    }

    /* renamed from: c */
    public final void mo8673c(int i) {
        try {
            byte[] bArr = this.f14232c;
            int i2 = this.f14234e;
            int i3 = i2 + 1;
            this.f14234e = i3;
            bArr[i2] = (byte) i;
            int i4 = i3 + 1;
            this.f14234e = i4;
            bArr[i3] = (byte) (i >> 8);
            int i5 = i4 + 1;
            this.f14234e = i5;
            bArr[i4] = (byte) (i >> 16);
            this.f14234e = i5 + 1;
            bArr[i5] = (byte) (i >> 24);
        } catch (IndexOutOfBoundsException e) {
            throw new iid(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f14234e), Integer.valueOf(this.f14233d), 1}), e);
        }
    }

    /* renamed from: b */
    public final void mo8670b(int i, long j) {
        mo8655a(i, 1);
        mo8672b(j);
    }

    /* renamed from: b */
    public final void mo8672b(long j) {
        try {
            byte[] bArr = this.f14232c;
            int i = this.f14234e;
            int i2 = i + 1;
            this.f14234e = i2;
            bArr[i] = (byte) ((int) j);
            int i3 = i2 + 1;
            this.f14234e = i3;
            bArr[i2] = (byte) ((int) (j >> 8));
            int i4 = i3 + 1;
            this.f14234e = i4;
            bArr[i3] = (byte) ((int) (j >> 16));
            int i5 = i4 + 1;
            this.f14234e = i5;
            bArr[i4] = (byte) ((int) (j >> 24));
            int i6 = i5 + 1;
            this.f14234e = i6;
            bArr[i5] = (byte) ((int) (j >> 32));
            int i7 = i6 + 1;
            this.f14234e = i7;
            bArr[i6] = (byte) ((int) (j >> 40));
            int i8 = i7 + 1;
            this.f14234e = i8;
            bArr[i7] = (byte) ((int) (j >> 48));
            this.f14234e = i8 + 1;
            bArr[i8] = (byte) ((int) (j >> 56));
        } catch (IndexOutOfBoundsException e) {
            throw new iid(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f14234e), Integer.valueOf(this.f14233d), 1}), e);
        }
    }

    /* renamed from: b */
    public final void mo8669b(int i, int i2) {
        mo8655a(i, 0);
        mo8654a(i2);
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
        m13376b(bArr, i, i2);
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
        int i = this.f14234e;
        try {
            int h = m13426h(str.length() * 3);
            int h2 = m13426h(str.length());
            if (h2 != h) {
                mo8668b(ima.m14067a((CharSequence) str));
                this.f14234e = ima.m14068a(str, this.f14232c, this.f14234e, mo8652a());
                return;
            }
            int i2 = i + h2;
            this.f14234e = i2;
            int a = ima.m14068a(str, this.f14232c, i2, mo8652a());
            this.f14234e = i;
            mo8668b((a - i) - h2);
            this.f14234e = a;
        } catch (ily e) {
            this.f14234e = i;
            mo8680a(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new iid(e2);
        }
    }

    /* renamed from: a */
    public final void mo8655a(int i, int i2) {
        mo8668b(imd.m14073a(i, i2));
    }

    /* renamed from: c */
    public final void mo8674c(int i, int i2) {
        mo8655a(i, 0);
        mo8668b(i2);
    }

    /* renamed from: b */
    public final void mo8668b(int i) {
        if (!iie.f14235a || ihe.m13011a() || mo8652a() < 5) {
            while ((i & -128) != 0) {
                try {
                    byte[] bArr = this.f14232c;
                    int i2 = this.f14234e;
                    this.f14234e = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new iid(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f14234e), Integer.valueOf(this.f14233d), 1}), e);
                }
            }
            byte[] bArr2 = this.f14232c;
            int i3 = this.f14234e;
            this.f14234e = i3 + 1;
            bArr2[i3] = (byte) i;
        } else if ((i & -128) != 0) {
            byte[] bArr3 = this.f14232c;
            int i4 = this.f14234e;
            this.f14234e = i4 + 1;
            ilv.m14038a(bArr3, (long) i4, (byte) (i | 128));
            int i5 = i >>> 7;
            if ((i5 & -128) == 0) {
                byte[] bArr4 = this.f14232c;
                int i6 = this.f14234e;
                this.f14234e = i6 + 1;
                ilv.m14038a(bArr4, (long) i6, (byte) i5);
                return;
            }
            byte[] bArr5 = this.f14232c;
            int i7 = this.f14234e;
            this.f14234e = i7 + 1;
            ilv.m14038a(bArr5, (long) i7, (byte) (i5 | 128));
            int i8 = i5 >>> 7;
            if ((i8 & -128) == 0) {
                byte[] bArr6 = this.f14232c;
                int i9 = this.f14234e;
                this.f14234e = i9 + 1;
                ilv.m14038a(bArr6, (long) i9, (byte) i8);
                return;
            }
            byte[] bArr7 = this.f14232c;
            int i10 = this.f14234e;
            this.f14234e = i10 + 1;
            ilv.m14038a(bArr7, (long) i10, (byte) (i8 | 128));
            int i11 = i8 >>> 7;
            if ((i11 & -128) == 0) {
                byte[] bArr8 = this.f14232c;
                int i12 = this.f14234e;
                this.f14234e = i12 + 1;
                ilv.m14038a(bArr8, (long) i12, (byte) i11);
                return;
            }
            byte[] bArr9 = this.f14232c;
            int i13 = this.f14234e;
            this.f14234e = i13 + 1;
            ilv.m14038a(bArr9, (long) i13, (byte) (i11 | 128));
            byte[] bArr10 = this.f14232c;
            int i14 = this.f14234e;
            this.f14234e = i14 + 1;
            ilv.m14038a(bArr10, (long) i14, (byte) (i11 >>> 7));
        } else {
            byte[] bArr11 = this.f14232c;
            int i15 = this.f14234e;
            this.f14234e = i15 + 1;
            ilv.m14038a(bArr11, (long) i15, (byte) i);
        }
    }

    /* renamed from: a */
    public final void mo8656a(int i, long j) {
        mo8655a(i, 0);
        mo8662a(j);
    }

    /* renamed from: a */
    public final void mo8662a(long j) {
        if (!iie.f14235a || mo8652a() < 10) {
            while ((j & -128) != 0) {
                try {
                    byte[] bArr = this.f14232c;
                    int i = this.f14234e;
                    this.f14234e = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new iid(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.f14234e), Integer.valueOf(this.f14233d), 1}), e);
                }
            }
            byte[] bArr2 = this.f14232c;
            int i2 = this.f14234e;
            this.f14234e = i2 + 1;
            bArr2[i2] = (byte) ((int) j);
            return;
        }
        while ((j & -128) != 0) {
            byte[] bArr3 = this.f14232c;
            int i3 = this.f14234e;
            this.f14234e = i3 + 1;
            ilv.m14038a(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        byte[] bArr4 = this.f14232c;
        int i4 = this.f14234e;
        this.f14234e = i4 + 1;
        ilv.m14038a(bArr4, (long) i4, (byte) ((int) j));
    }
}
