package p000;

import java.nio.charset.Charset;

/* renamed from: ift */
/* compiled from: PG */
public final class ift {

    /* renamed from: a */
    public static final ian f14017a;

    /* renamed from: b */
    private static final Charset f14018b = Charset.forName("UTF-8");

    /* renamed from: c */
    private final ify f14019c;

    /* renamed from: d */
    private final igb f14020d;

    static {
        iam iam = (iam) ian.f13731e.mo8793g();
        if (iam.f14319c) {
            iam.mo8751b();
            iam.f14319c = false;
        }
        ian ian = (ian) iam.f14318b;
        ian.f13733a |= 1;
        ian.f13734b = 0;
        ian.m12584a(ian);
        if (iam.f14319c) {
            iam.mo8751b();
            iam.f14319c = false;
        }
        ian.m12585b((ian) iam.f14318b);
        f14017a = (ian) iam.mo8770g();
    }

    public ift(String str, int i, String str2, int i2) {
        iir g = ify.f14031e.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        ify ify = (ify) g.f14318b;
        str.getClass();
        int i3 = ify.f14033a | 2;
        ify.f14033a = i3;
        ify.f14035c = str;
        int i4 = i3 | 1;
        ify.f14033a = i4;
        ify.f14034b = i;
        if (str2 != null) {
            str2.getClass();
            ify.f14033a = i4 | 4;
            ify.f14036d = str2;
        }
        this.f14019c = (ify) g.mo8770g();
        iir g2 = igb.f14049c.mo8793g();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        igb igb = (igb) g2.f14318b;
        int i5 = i2 - 1;
        if (i2 != 0) {
            igb.f14052b = i5;
            igb.f14051a |= 1;
            this.f14020d = (igb) g2.mo8770g();
            return;
        }
        throw null;
    }

    /* renamed from: a */
    public final iir mo8493a(iap iap, Object... objArr) {
        iir g = ifz.f14037f.mo8793g();
        iir g2 = ifw.f14023d.mo8793g();
        ify ify = this.f14019c;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        ifw ifw = (ifw) g2.f14318b;
        ify.getClass();
        ifw.f14026b = ify;
        int i = ifw.f14025a | 1;
        ifw.f14025a = i;
        igb igb = this.f14020d;
        igb.getClass();
        ifw.f14027c = igb;
        ifw.f14025a = i | 2;
        ifw ifw2 = (ifw) g2.mo8770g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        ifz ifz = (ifz) g.f14318b;
        ifw2.getClass();
        ifz.f14040b = ifw2;
        int i2 = ifz.f14039a | 1;
        ifz.f14039a = i2;
        iap.getClass();
        ifz.f14041c = iap;
        ifz.f14039a = i2 | 2;
        for (int i3 = 0; i3 < objArr.length; i3++) {
            ifs ifs = objArr[i3];
            if (ifs instanceof ifs) {
                ifs ifs2 = ifs;
                iir g3 = igc.f14053d.mo8793g();
                if (g3.f14319c) {
                    g3.mo8751b();
                    g3.f14319c = false;
                }
                igc igc = (igc) g3.f14318b;
                igc.f14055a |= 1;
                igc.f14056b = i3;
                String ifs3 = ifs2.toString();
                if (g3.f14319c) {
                    g3.mo8751b();
                    g3.f14319c = false;
                }
                igc igc2 = (igc) g3.f14318b;
                ifs3.getClass();
                igc2.f14055a |= 2;
                igc2.f14057c = ifs3;
                igc igc3 = (igc) g3.mo8770g();
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                ifz ifz2 = (ifz) g.f14318b;
                igc3.getClass();
                if (!ifz2.f14043e.mo8521a()) {
                    ifz2.f14043e = iix.m13608a(ifz2.f14043e);
                }
                ifz2.f14043e.add(igc3);
            }
        }
        return g;
    }

    /* renamed from: b */
    public static int m12943b(hwz hwz) {
        return hzm.m12529a().mo8286a(m12942a(hwz), f14018b).mo8293c();
    }

    /* renamed from: a */
    public static Object m12941a(hwz hwz, hwn hwn) {
        return hwz.mo8217l().mo8195b(hwn);
    }

    /* renamed from: a */
    public static String m12942a(hwz hwz) {
        hxk h = hwz.mo8213h();
        if (h != null) {
            return h.f13586b;
        }
        Object j = hwz.mo8215j();
        if (j instanceof String) {
            return (String) j;
        }
        return j != null ? j.getClass().getName() : "null";
    }
}
