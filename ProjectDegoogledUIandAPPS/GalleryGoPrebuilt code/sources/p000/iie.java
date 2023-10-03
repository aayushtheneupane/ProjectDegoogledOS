package p000;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: iie */
/* compiled from: PG */
public abstract class iie extends ihk {

    /* renamed from: a */
    public static final boolean f14235a = ilv.f14463b;

    /* renamed from: c */
    private static final Logger f14236c = Logger.getLogger(iie.class.getName());

    /* renamed from: b */
    public iif f14237b;

    /* renamed from: d */
    static int m13414d(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    /* renamed from: d */
    public static int m13416d(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    /* renamed from: f */
    private static long m13423f(long j) {
        return (j >> 63) ^ (j + j);
    }

    /* renamed from: h */
    public static int m13426h(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) != 0) {
            return (i & -268435456) == 0 ? 4 : 5;
        }
        return 3;
    }

    /* renamed from: r */
    private static int m13438r(int i) {
        return (i >> 31) ^ (i + i);
    }

    /* renamed from: a */
    public abstract int mo8652a();

    /* renamed from: a */
    public abstract void mo8653a(byte b);

    /* renamed from: a */
    public abstract void mo8654a(int i);

    /* renamed from: a */
    public abstract void mo8655a(int i, int i2);

    /* renamed from: a */
    public abstract void mo8656a(int i, long j);

    /* renamed from: a */
    public abstract void mo8657a(int i, ihw ihw);

    /* renamed from: a */
    public abstract void mo8658a(int i, ikf ikf);

    /* renamed from: a */
    public abstract void mo8659a(int i, ikf ikf, iky iky);

    /* renamed from: a */
    public abstract void mo8660a(int i, String str);

    /* renamed from: a */
    public abstract void mo8661a(int i, boolean z);

    /* renamed from: a */
    public abstract void mo8662a(long j);

    /* renamed from: a */
    public abstract void mo8663a(ihw ihw);

    /* renamed from: a */
    public abstract void mo8664a(ikf ikf);

    /* renamed from: a */
    public abstract void mo8665a(String str);

    /* renamed from: a */
    public abstract void mo8666a(byte[] bArr, int i);

    /* renamed from: a */
    public abstract void mo8590a(byte[] bArr, int i, int i2);

    /* renamed from: b */
    public abstract void mo8667b();

    /* renamed from: b */
    public abstract void mo8668b(int i);

    /* renamed from: b */
    public abstract void mo8669b(int i, int i2);

    /* renamed from: b */
    public abstract void mo8670b(int i, long j);

    /* renamed from: b */
    public abstract void mo8671b(int i, ihw ihw);

    /* renamed from: b */
    public abstract void mo8672b(long j);

    /* renamed from: c */
    public abstract void mo8673c(int i);

    /* renamed from: c */
    public abstract void mo8674c(int i, int i2);

    /* renamed from: d */
    public abstract void mo8675d(int i, int i2);

    private iie() {
    }

    public /* synthetic */ iie(byte[] bArr) {
    }

    /* renamed from: c */
    public final void mo8681c() {
        if (mo8652a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* renamed from: k */
    public static int m13431k(int i) {
        return m13420f(i) + 1;
    }

    /* renamed from: b */
    public static int m13412b(byte[] bArr) {
        return m13430j(bArr.length);
    }

    /* renamed from: c */
    public static int m13413c(int i, ihw ihw) {
        return m13420f(i) + m13409b(ihw);
    }

    /* renamed from: b */
    public static int m13409b(ihw ihw) {
        return m13430j(ihw.mo8597a());
    }

    /* renamed from: l */
    public static int m13432l(int i) {
        return m13420f(i) + 8;
    }

    /* renamed from: i */
    public static int m13429i(int i, int i2) {
        return m13420f(i) + m13424g(i2);
    }

    /* renamed from: m */
    public static int m13433m(int i) {
        return m13420f(i) + 4;
    }

    /* renamed from: n */
    public static int m13434n(int i) {
        return m13420f(i) + 8;
    }

    /* renamed from: o */
    public static int m13435o(int i) {
        return m13420f(i) + 4;
    }

    @Deprecated
    /* renamed from: b */
    static int m13407b(int i, ikf ikf, iky iky) {
        int f = m13420f(i);
        int i2 = f + f;
        iha iha = (iha) ikf;
        int d = iha.mo8515d();
        if (d == -1) {
            d = iky.mo8869b(iha);
            iha.mo8510a(d);
        }
        return i2 + d;
    }

    @Deprecated
    /* renamed from: d */
    public static int m13417d(ikf ikf) {
        return ikf.mo8795i();
    }

    /* renamed from: f */
    public static int m13421f(int i, int i2) {
        return m13420f(i) + m13424g(i2);
    }

    /* renamed from: g */
    public static int m13424g(int i) {
        if (i >= 0) {
            return m13426h(i);
        }
        return 10;
    }

    /* renamed from: d */
    public static int m13415d(int i, long j) {
        return m13420f(i) + m13416d(j);
    }

    /* renamed from: a */
    public static int m13402a(int i, ijm ijm) {
        return m13420f(i) + m13403a(ijm);
    }

    /* renamed from: a */
    public static int m13403a(ijm ijm) {
        int i;
        if (ijm.f14352b != null) {
            i = ijm.f14352b.mo8597a();
        } else {
            i = ijm.f14351a != null ? ijm.f14351a.mo8795i() : 0;
        }
        return m13430j(i);
    }

    /* renamed from: j */
    static int m13430j(int i) {
        return m13426h(i) + i;
    }

    /* renamed from: b */
    public static int m13410b(ikf ikf) {
        return m13430j(ikf.mo8795i());
    }

    /* renamed from: a */
    static int m13404a(ikf ikf, iky iky) {
        iha iha = (iha) ikf;
        int d = iha.mo8515d();
        if (d == -1) {
            d = iky.mo8869b(iha);
            iha.mo8510a(d);
        }
        return m13430j(d);
    }

    /* renamed from: p */
    public static int m13436p(int i) {
        return m13420f(i) + 4;
    }

    /* renamed from: q */
    public static int m13437q(int i) {
        return m13420f(i) + 8;
    }

    /* renamed from: h */
    public static int m13427h(int i, int i2) {
        return m13420f(i) + m13428i(i2);
    }

    /* renamed from: i */
    public static int m13428i(int i) {
        return m13426h(m13438r(i));
    }

    /* renamed from: f */
    public static int m13422f(int i, long j) {
        return m13420f(i) + m13419e(j);
    }

    /* renamed from: e */
    public static int m13419e(long j) {
        return m13416d(m13423f(j));
    }

    /* renamed from: b */
    public static int m13408b(int i, String str) {
        return m13420f(i) + m13411b(str);
    }

    /* renamed from: b */
    public static int m13411b(String str) {
        int i;
        try {
            i = ima.m14067a((CharSequence) str);
        } catch (ily e) {
            i = str.getBytes(ijf.f14336a).length;
        }
        return m13430j(i);
    }

    /* renamed from: f */
    public static int m13420f(int i) {
        return m13426h(imd.m14073a(i, 0));
    }

    /* renamed from: g */
    public static int m13425g(int i, int i2) {
        return m13420f(i) + m13426h(i2);
    }

    /* renamed from: e */
    public static int m13418e(int i, long j) {
        return m13420f(i) + m13416d(j);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8680a(String str, ily ily) {
        f14236c.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", ily);
        byte[] bytes = str.getBytes(ijf.f14336a);
        try {
            int length = bytes.length;
            mo8668b(length);
            mo8590a(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new iid(e);
        } catch (iid e2) {
            throw e2;
        }
    }

    /* renamed from: a */
    public static iie m13405a(OutputStream outputStream, int i) {
        return new iib(outputStream, i);
    }

    /* renamed from: a */
    public static iie m13406a(byte[] bArr) {
        return new iic(bArr, bArr.length);
    }

    /* renamed from: a */
    public final void mo8678a(int i, double d) {
        mo8670b(i, Double.doubleToRawLongBits(d));
    }

    /* renamed from: a */
    public final void mo8676a(double d) {
        mo8672b(Double.doubleToRawLongBits(d));
    }

    /* renamed from: a */
    public final void mo8679a(int i, float f) {
        mo8675d(i, Float.floatToRawIntBits(f));
    }

    /* renamed from: a */
    public final void mo8677a(float f) {
        mo8673c(Float.floatToRawIntBits(f));
    }

    @Deprecated
    /* renamed from: c */
    public final void mo8684c(ikf ikf) {
        ikf.mo8789a(this);
    }

    /* renamed from: e */
    public final void mo8686e(int i, int i2) {
        mo8674c(i, m13438r(i2));
    }

    /* renamed from: e */
    public final void mo8685e(int i) {
        mo8668b(m13438r(i));
    }

    /* renamed from: c */
    public final void mo8682c(int i, long j) {
        mo8656a(i, m13423f(j));
    }

    /* renamed from: c */
    public final void mo8683c(long j) {
        mo8662a(m13423f(j));
    }
}
