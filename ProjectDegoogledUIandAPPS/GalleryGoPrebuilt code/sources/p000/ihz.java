package p000;

import java.io.InputStream;

/* renamed from: ihz */
/* compiled from: PG */
public abstract class ihz {

    /* renamed from: a */
    public int f14220a;

    /* renamed from: b */
    public iia f14221b;

    private ihz() {
    }

    /* renamed from: a */
    public static long m13260a(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    /* renamed from: e */
    public static int m13264e(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    /* renamed from: a */
    public abstract int mo8628a();

    /* renamed from: a */
    public abstract void mo8629a(int i);

    /* renamed from: b */
    public abstract double mo8630b();

    /* renamed from: b */
    public abstract boolean mo8631b(int i);

    /* renamed from: c */
    public abstract float mo8632c();

    /* renamed from: c */
    public abstract int mo8633c(int i);

    /* renamed from: d */
    public abstract long mo8634d();

    /* renamed from: d */
    public abstract void mo8635d(int i);

    /* renamed from: e */
    public abstract long mo8636e();

    /* renamed from: f */
    public abstract int mo8637f();

    /* renamed from: g */
    public abstract long mo8638g();

    /* renamed from: h */
    public abstract int mo8639h();

    /* renamed from: i */
    public abstract boolean mo8640i();

    /* renamed from: j */
    public abstract String mo8641j();

    /* renamed from: k */
    public abstract String mo8642k();

    /* renamed from: l */
    public abstract ihw mo8643l();

    /* renamed from: m */
    public abstract int mo8644m();

    /* renamed from: n */
    public abstract int mo8645n();

    /* renamed from: o */
    public abstract int mo8646o();

    /* renamed from: p */
    public abstract long mo8647p();

    /* renamed from: q */
    public abstract int mo8648q();

    /* renamed from: r */
    public abstract long mo8649r();

    /* renamed from: s */
    public abstract boolean mo8650s();

    /* renamed from: t */
    public abstract int mo8651t();

    public /* synthetic */ ihz(byte[] bArr) {
    }

    /* renamed from: a */
    public static ihz m13261a(InputStream inputStream) {
        if (inputStream == null) {
            return m13262a(ijf.f14337b);
        }
        return new ihy(inputStream);
    }

    /* renamed from: a */
    public static ihz m13262a(byte[] bArr) {
        return m13263a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    static ihz m13263a(byte[] bArr, int i, int i2) {
        ihx ihx = new ihx(bArr, i, i2);
        try {
            ihx.mo8633c(i2);
            return ihx;
        } catch (ijh e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* renamed from: a */
    public static int m13259a(int i, InputStream inputStream) {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & 127;
        int i3 = 7;
        while (i3 < 32) {
            int read = inputStream.read();
            if (read != -1) {
                i2 |= (read & 127) << i3;
                if ((read & 128) == 0) {
                    return i2;
                }
                i3 += 7;
            } else {
                throw ijh.m13654a();
            }
        }
        while (i3 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw ijh.m13654a();
            } else if ((read2 & 128) == 0) {
                return i2;
            } else {
                i3 += 7;
            }
        }
        throw ijh.m13656c();
    }
}
