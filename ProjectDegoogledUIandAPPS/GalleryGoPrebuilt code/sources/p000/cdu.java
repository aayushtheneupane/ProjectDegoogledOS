package p000;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* renamed from: cdu */
/* compiled from: PG */
public class cdu {
    /* renamed from: a */
    public static /* synthetic */ String m4144a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "null" : "TYPE_NOT_SET" : "INTENT" : "MEDIA";
    }

    /* renamed from: b */
    public static int m4147b(int i) {
        if (i == 0) {
            return 3;
        }
        if (i != 1) {
            return i != 4 ? 0 : 2;
        }
        return 1;
    }

    /* renamed from: a */
    public static String m4145a(ane ane) {
        StringBuffer stringBuffer = new StringBuffer();
        DecimalFormat decimalFormat = new DecimalFormat("0000", new DecimalFormatSymbols(Locale.ENGLISH));
        stringBuffer.append(decimalFormat.format((long) ane.mo1246a()));
        if (ane.mo1248b() == 0) {
            return stringBuffer.toString();
        }
        decimalFormat.applyPattern("'-'00");
        stringBuffer.append(decimalFormat.format((long) ane.mo1248b()));
        if (ane.mo1250c() == 0) {
            return stringBuffer.toString();
        }
        stringBuffer.append(decimalFormat.format((long) ane.mo1250c()));
        if (!(ane.mo1251d() == 0 && ane.mo1252e() == 0 && ane.mo1253f() == 0 && ane.mo1254g() == 0 && (ane.mo1255h() == null || ane.mo1255h().getRawOffset() == 0))) {
            stringBuffer.append('T');
            decimalFormat.applyPattern("00");
            stringBuffer.append(decimalFormat.format((long) ane.mo1251d()));
            stringBuffer.append(':');
            stringBuffer.append(decimalFormat.format((long) ane.mo1252e()));
            if (!(ane.mo1253f() == 0 && ane.mo1254g() == 0)) {
                int f = ane.mo1253f();
                int g = ane.mo1254g();
                decimalFormat.applyPattern(":00.#########");
                double d = (double) f;
                double d2 = (double) g;
                Double.isNaN(d2);
                Double.isNaN(d);
                stringBuffer.append(decimalFormat.format(d + (d2 / 1.0E9d)));
            }
            if (ane.mo1255h() != null) {
                int offset = ane.mo1255h().getOffset(ane.mo1256i().getTimeInMillis());
                if (offset == 0) {
                    stringBuffer.append('Z');
                } else {
                    int abs = Math.abs((offset % 3600000) / 60000);
                    decimalFormat.applyPattern("+00;-00");
                    stringBuffer.append(decimalFormat.format((long) (offset / 3600000)));
                    decimalFormat.applyPattern(":00");
                    stringBuffer.append(decimalFormat.format((long) abs));
                }
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static void m4146a(fwy fwy) {
        ihg.m13037a((C0140fa) fwy, hog.class, (hol) new cdt());
    }
}
