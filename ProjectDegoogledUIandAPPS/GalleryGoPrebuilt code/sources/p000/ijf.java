package p000;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* renamed from: ijf */
/* compiled from: PG */
public final class ijf {

    /* renamed from: a */
    public static final Charset f14336a = Charset.forName("UTF-8");

    /* renamed from: b */
    public static final byte[] f14337b;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        f14337b = bArr;
        ByteBuffer.wrap(bArr);
        ihz.m13262a(f14337b);
    }

    /* renamed from: a */
    public static int m13645a(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* renamed from: a */
    public static int m13646a(boolean z) {
        return z ? 1231 : 1237;
    }

    /* renamed from: a */
    static void m13650a(Object obj) {
        if (obj == null) {
            throw null;
        }
    }

    /* renamed from: a */
    static Object m13648a(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    /* renamed from: c */
    public static int m13653c(byte[] bArr) {
        int length = bArr.length;
        int a = m13644a(length, bArr, 0, length);
        if (a == 0) {
            return 1;
        }
        return a;
    }

    /* renamed from: a */
    static void m13649a(ikf ikf) {
        if (ikf instanceof ihb) {
            ihb ihb = (ihb) ikf;
            throw null;
        }
    }

    /* renamed from: a */
    public static boolean m13651a(byte[] bArr) {
        return ima.f14474a.mo8990a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    static Object m13647a(Object obj, Object obj2) {
        return ((ikf) obj).mo8796m().mo8506a((ikf) obj2).mo8766f();
    }

    /* renamed from: a */
    static int m13644a(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    /* renamed from: b */
    public static String m13652b(byte[] bArr) {
        return new String(bArr, f14336a);
    }
}
