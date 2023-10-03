package p000;

/* renamed from: bgv */
/* compiled from: PG */
public final class bgv {

    /* renamed from: a */
    public static final int[] f2355a = {2, 1, 4, 8};

    /* renamed from: a */
    static int m2519a(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int i = 0;
        for (int i2 : iArr) {
            i |= i2;
        }
        return i;
    }
}
