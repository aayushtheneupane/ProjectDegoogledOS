package p000a.p005b;

/* renamed from: a.b.e */
class C0018e {

    /* renamed from: Wn */
    static final int[] f9Wn = new int[0];

    /* renamed from: Xn */
    static final long[] f10Xn = new long[0];

    /* renamed from: Yn */
    static final Object[] f11Yn = new Object[0];

    /* renamed from: K */
    public static int m23K(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            int i3 = (1 << i2) - 12;
            if (i <= i3) {
                return i3;
            }
        }
        return i;
    }

    /* renamed from: L */
    public static int m24L(int i) {
        return m23K(i * 4) / 4;
    }

    /* renamed from: M */
    public static int m25M(int i) {
        return m23K(i * 8) / 8;
    }

    /* renamed from: b */
    public static boolean m26b(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static int binarySearch(int[] iArr, int i, int i2) {
        int i3 = i - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i4 = i5 + 1;
            } else if (i6 <= i2) {
                return i5;
            } else {
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    static int binarySearch(long[] jArr, int i, long j) {
        int i2 = i - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = (jArr[i4] > j ? 1 : (jArr[i4] == j ? 0 : -1));
            if (i5 < 0) {
                i3 = i4 + 1;
            } else if (i5 <= 0) {
                return i4;
            } else {
                i2 = i4 - 1;
            }
        }
        return ~i3;
    }
}
