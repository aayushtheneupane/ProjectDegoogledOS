package p000;

/* renamed from: ima */
/* compiled from: PG */
final class ima {

    /* renamed from: a */
    public static final ilw f14474a;

    static {
        ilw ilw;
        if (ilv.f14463b && ilv.f14462a && !ihe.m13011a()) {
            ilw = new ilz();
        } else {
            ilw = new ilx();
        }
        f14474a = ilw;
    }

    /* renamed from: a */
    public static int m14064a(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* renamed from: a */
    public static int m14065a(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* renamed from: a */
    public static int m14066a(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    /* renamed from: c */
    static String m14071c(byte[] bArr, int i, int i2) {
        return f14474a.mo8991b(bArr, i, i2);
    }

    /* renamed from: a */
    static int m14068a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return f14474a.mo8989a(charSequence, bArr, i, i2);
    }

    /* renamed from: a */
    static int m14067a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (charAt2 >= 55296 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) >= 65536) {
                                i2++;
                            } else {
                                throw new ily(i2, length2);
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(((long) i3) + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: b */
    public static int m14070b(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            return m14064a((int) b);
        }
        if (i3 == 1) {
            return m14065a(b, bArr[i]);
        }
        if (i3 == 2) {
            return m14066a((int) b, (int) bArr[i], (int) bArr[i + 1]);
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public static boolean m14069a(byte[] bArr, int i, int i2) {
        return f14474a.mo8990a(bArr, i, i2);
    }
}
