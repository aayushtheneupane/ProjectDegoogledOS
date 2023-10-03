package p000;

/* renamed from: cp */
/* compiled from: PG */
public final class C0074cp {

    /* renamed from: a */
    private static final byte[] f5335a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 0, 3, 0, 3, 3, 0, 3, 0, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0};

    /* renamed from: b */
    private static final byte[] f5336b = {2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 9};

    /* renamed from: c */
    private static final int[] f5337c = {0, -1, -16384, 2147419135, 2146435070, -65536, 4194303, -1048576, -242, 65537};

    /* renamed from: a */
    private static boolean m5205a(int i) {
        if (i <= 255) {
            return f5335a[i] == 5;
        }
        if (i < 8206 || i > 8233) {
            return false;
        }
        return i <= 8207 || i >= 8232;
    }

    /* renamed from: b */
    public static int m5206b(CharSequence charSequence, int i) {
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            if (charAt > 255) {
                if (charAt >= 8206) {
                    if (charAt > 12336) {
                        if (charAt >= 64830) {
                            if (charAt <= 65094) {
                                if (charAt <= 64831 || charAt >= 65093) {
                                    break;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (((f5337c[f5336b[(charAt - 8192) >> 5]] >> (charAt & 31)) & 1) != 0) {
                        break;
                    }
                } else {
                    continue;
                }
            } else if (f5335a[charAt] != 0) {
                break;
            }
            i++;
        }
        return i;
    }

    /* renamed from: a */
    public static int m5203a(CharSequence charSequence, int i) {
        while (i < charSequence.length() && m5205a((int) charSequence.charAt(i))) {
            i++;
        }
        return i;
    }

    /* renamed from: a */
    public static String m5204a(String str) {
        if (str.length() == 0) {
            return str;
        }
        int i = 0;
        if (!m5205a((int) str.charAt(0)) && !m5205a((int) str.charAt(str.length() - 1))) {
            return str;
        }
        int length = str.length();
        while (i < length && m5205a((int) str.charAt(i))) {
            i++;
        }
        if (i < length) {
            while (true) {
                int i2 = length - 1;
                if (!m5205a((int) str.charAt(i2))) {
                    break;
                }
                length = i2;
            }
        }
        return str.substring(i, length);
    }
}
