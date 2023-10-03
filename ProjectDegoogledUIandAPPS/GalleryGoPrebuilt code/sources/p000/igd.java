package p000;

/* renamed from: igd */
/* compiled from: PG */
public final class igd {

    /* renamed from: a */
    private static final int[] f14058a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31, -1, -1, 10, 11, 12, -1, 13, 14, 15, -1, 16, 17, 18, 19, 20, -1, 21, 22, 23, 24, 25, -1, 26, 27, 28, 29, 30, -1, -1, -1, -1, -1};

    /* renamed from: b */
    private static final String[] f14059b = {"/m/", "/g/", "/t/", "/x/", "/n/", "/p/", "/r/", "/s/"};

    /* renamed from: b */
    private static final int m12964b(long j) {
        return (int) ((j >>> 59) & 7);
    }

    /* renamed from: c */
    private static final int m12967c(long j) {
        return (int) (j >>> 62);
    }

    /* renamed from: a */
    private static int m12960a(char c) {
        return f14058a[c];
    }

    /* renamed from: a */
    private static final long m12961a(int i, long j) {
        if ((-576460752303423488L & j) == 0) {
            return j | (((long) i) << 59);
        }
        String hexString = Long.toHexString(576460752303423487L);
        String b = m12966b(i);
        String hexString2 = Long.toHexString(j);
        int length = String.valueOf(hexString).length();
        StringBuilder sb = new StringBuilder(length + 42 + String.valueOf(b).length() + String.valueOf(hexString2).length());
        sb.append("MID exceeds max size 0x");
        sb.append(hexString);
        sb.append(" for namespace ");
        sb.append(b);
        sb.append(": 0x");
        sb.append(hexString2);
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: a */
    public static String m12963a(long j) {
        if (j == 0) {
            return "/m/0";
        }
        if (m12967c(j) == 0) {
            StringBuilder sb = new StringBuilder();
            int b = m12964b(j);
            sb.append(m12966b(b));
            sb.append(m12959a(b));
            long j2 = j & 576460752303423487L;
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < 13; i++) {
                sb2.append(m12959a((int) (31 & j2)));
                j2 >>>= 5;
                if (j2 == 0) {
                    break;
                }
            }
            sb.append(sb2.reverse().toString());
            return sb.toString();
        }
        throw new IllegalArgumentException("Invalid MID version");
    }

    /* renamed from: a */
    private static char m12959a(int i) {
        return "0123456789bcdfghjklmnpqrstvwxyz_".charAt(i);
    }

    /* renamed from: b */
    private static long m12965b(String str) {
        if (str.length() == 32) {
            String substring = str.substring(0, 16);
            String substring2 = str.substring(16, 32);
            long a = ife.m12810a(substring);
            long a2 = ife.m12810a(substring2);
            long j = a + 7925586850557696993L;
            if (j < 8 && (-17179869184L & a2) == Long.MIN_VALUE) {
                return m12961a((int) j, a2 & 17179869183L);
            }
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Invalid GUID: ") : "Invalid GUID: ".concat(valueOf));
        }
        throw new IllegalArgumentException("Invalid GUID length");
    }

    /* renamed from: a */
    public static long m12962a(String str) {
        if (str.length() > 3) {
            int i = 0;
            if (str.charAt(0) == '/') {
                int i2 = 2;
                if (str.charAt(2) == '/') {
                    char charAt = str.charAt(1);
                    if (charAt == 'g') {
                        i2 = 1;
                    } else if (charAt == 'p') {
                        i2 = 5;
                    } else if (charAt != 't') {
                        if (charAt == 'x') {
                            i2 = 3;
                        } else if (charAt == 'm') {
                            i2 = 0;
                        } else if (charAt == 'n') {
                            i2 = 4;
                        } else {
                            StringBuilder sb = new StringBuilder(21);
                            sb.append("Invalid MID prefix: ");
                            sb.append(charAt);
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    String substring = str.substring(3);
                    int a = m12960a(substring.charAt(0));
                    int i3 = a >>> 3;
                    if (i3 == 0) {
                        int i4 = a & 7;
                        String substring2 = substring.substring(1);
                        if (substring2.length() <= 13) {
                            long j = 0;
                            if (substring2.length() != 13 || m12960a(substring2.charAt(0)) <= 15) {
                                while (i < substring2.length()) {
                                    int a2 = m12960a(substring2.charAt(i));
                                    if (a2 >= 0) {
                                        j = (j << 5) + ((long) a2);
                                        i++;
                                    } else {
                                        throw new IllegalArgumentException("Invalid munch character");
                                    }
                                }
                                long a3 = m12961a(i4, j);
                                if (m12964b(a3) == i2) {
                                    return a3;
                                }
                                int c = m12967c(a3);
                                if (c == 0) {
                                    String b = m12966b(m12964b(a3));
                                    StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(b).length());
                                    sb2.append("Inconsistent MID prefix: ");
                                    sb2.append(str);
                                    sb2.append(" (expected ");
                                    sb2.append(b);
                                    sb2.append(")");
                                    throw new IllegalArgumentException(sb2.toString());
                                }
                                StringBuilder sb3 = new StringBuilder(32);
                                sb3.append("Invalid MID version: ");
                                sb3.append(c);
                                throw new IllegalArgumentException(sb3.toString());
                            }
                        }
                        throw new IllegalArgumentException("65-bit value");
                    }
                    StringBuilder sb4 = new StringBuilder(32);
                    sb4.append("Invalid MID version: ");
                    sb4.append(i3);
                    throw new IllegalArgumentException(sb4.toString());
                }
            }
        }
        if (str.startsWith("/guid/")) {
            return m12965b(str.substring(6));
        }
        if (str.startsWith("#")) {
            return m12965b(str.substring(1));
        }
        String valueOf = String.valueOf(str);
        throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Unknown ID format: ") : "Unknown ID format: ".concat(valueOf));
    }

    /* renamed from: b */
    private static final String m12966b(int i) {
        boolean z = false;
        if (i >= 0 && i < 6) {
            z = true;
        }
        ife.m12846a(z, "Invalid graph ID: %i", i);
        return f14059b[i];
    }
}
