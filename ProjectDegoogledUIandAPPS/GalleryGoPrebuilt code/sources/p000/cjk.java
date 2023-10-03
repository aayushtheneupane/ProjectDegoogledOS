package p000;

import java.io.UnsupportedEncodingException;

/* renamed from: cjk */
/* compiled from: PG */
public class cjk {
    /* renamed from: a */
    public static byte[] m4397a(byte b) {
        byte b2 = b & 255;
        if (b2 >= 128) {
            if (b2 == 129 || b2 == 141 || b2 == 143 || b2 == 144 || b2 == 157) {
                return new byte[]{32};
            }
            try {
                return new String(new byte[]{b}, "cp1252").getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        return new byte[]{b};
    }

    /* renamed from: a */
    public static void m4396a(fwy fwy) {
        ihg.m13037a((C0140fa) fwy, hog.class, (hol) new cek());
    }

    /* renamed from: a */
    public static gbv m4395a(gbv gbv, double d, double d2) {
        iir iir = (iir) gbv.mo8790b(5);
        iir.mo8503a((iix) gbv);
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        gbv gbv2 = gbv.f10871o;
        ((gbv) iir.f14318b).f10875c = gbv.m13615l();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        gbv gbv3 = (gbv) iir.f14318b;
        gbv3.f10874b = null;
        int i = gbv3.f10873a & -2;
        gbv3.f10873a = i;
        gbv3.f10886n = null;
        gbv3.f10873a = -134217729 & i;
        ije ije = gbv.f10875c;
        int size = ije.size();
        for (int i2 = 0; i2 < size; i2++) {
            gbx gbx = (gbx) ije.get(i2);
            iir g = gbx.f10892f.mo8793g();
            int a = gbz.m9992a(gbx.f10898e);
            if (a == 0) {
                a = 15001;
            }
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            gbx gbx2 = (gbx) g.f14318b;
            gbx2.f10898e = a - 1;
            int i3 = gbx2.f10894a | 8;
            gbx2.f10894a = i3;
            float f = gbx.f10895b;
            int i4 = i3 | 1;
            gbx2.f10894a = i4;
            double d3 = (double) f;
            Double.isNaN(d3);
            gbx2.f10895b = (float) (d3 * d);
            float f2 = gbx.f10896c;
            int i5 = i4 | 2;
            gbx2.f10894a = i5;
            double d4 = (double) f2;
            Double.isNaN(d4);
            gbx2.f10896c = (float) (d4 * d2);
            float f3 = gbx.f10897d;
            gbx2.f10894a = i5 | 4;
            gbx2.f10897d = f3;
            if (iir.f14319c) {
                iir.mo8751b();
                iir.f14319c = false;
            }
            gbv gbv4 = (gbv) iir.f14318b;
            gbx gbx3 = (gbx) g.mo8770g();
            gbx3.getClass();
            if (!gbv4.f10875c.mo8521a()) {
                gbv4.f10875c = iix.m13608a(gbv4.f10875c);
            }
            gbv4.f10875c.add(gbx3);
        }
        gbs gbs = gbv.f10874b;
        if (gbs == null) {
            gbs = gbs.f10849f;
        }
        iir g2 = gbs.f10849f.mo8793g();
        double d5 = (double) gbs.f10852b;
        Double.isNaN(d5);
        float f4 = (float) (d5 * d);
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        gbs gbs2 = (gbs) g2.f14318b;
        int i6 = gbs2.f10851a | 1;
        gbs2.f10851a = i6;
        gbs2.f10852b = f4;
        float f5 = gbs.f10853c;
        int i7 = i6 | 2;
        gbs2.f10851a = i7;
        double d6 = (double) f5;
        Double.isNaN(d6);
        gbs2.f10853c = (float) (d6 * d2);
        float f6 = gbs.f10854d;
        int i8 = i7 | 4;
        gbs2.f10851a = i8;
        double d7 = (double) f6;
        Double.isNaN(d7);
        gbs2.f10854d = (float) (d * d7);
        float f7 = gbs.f10855e;
        gbs2.f10851a = i8 | 8;
        double d8 = (double) f7;
        Double.isNaN(d8);
        gbs2.f10855e = (float) (d2 * d8);
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        gbv gbv5 = (gbv) iir.f14318b;
        gbs gbs3 = (gbs) g2.mo8770g();
        gbs3.getClass();
        gbv5.f10874b = gbs3;
        gbv5.f10873a |= 1;
        return (gbv) iir.mo8770g();
    }
}
