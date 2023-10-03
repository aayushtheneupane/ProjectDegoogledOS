package p000;

/* renamed from: ant */
/* compiled from: PG */
public final class ant {

    /* renamed from: a */
    private static boolean[] f1210a = new boolean[256];

    /* renamed from: b */
    private static boolean[] f1211b = new boolean[256];

    static {
        char c = 0;
        while (c < f1211b.length) {
            boolean z = true;
            f1210a[c] = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ':' || c == '_' || ((c >= 192 && c <= 214) || (c >= 216 && c <= 246));
            boolean[] zArr = f1211b;
            if ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z') && !((c >= '0' && c <= '9') || c == ':' || c == '_' || c == '-' || c == '.' || c == 183 || ((c >= 192 && c <= 214) || (c >= 216 && c <= 246))))) {
                z = false;
            }
            zArr[c] = z;
            c = (char) (c + 1);
        }
    }

    /* renamed from: a */
    static boolean m1193a(char c) {
        return ((c > 31 && c != 127) || c == 9 || c == 10 || c == 13) ? false : true;
    }

    /* renamed from: c */
    static boolean m1197c(String str) {
        if (str != null) {
            int i = 0;
            boolean z = true;
            int i2 = 0;
            while (i < str.length()) {
                if (str.charAt(i) == '-') {
                    i2++;
                    z = z && (i == 8 || i == 13 || i == 18 || i == 23);
                }
                i++;
            }
            return z && i2 == 4 && i == 36;
        }
    }

    /* renamed from: a */
    public static String m1192a(String str, boolean z) {
        String str2;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '<' || charAt == '>' || charAt == '&' || charAt == 9 || charAt == 10 || charAt == 13 || (z && charAt == '\"')) {
                StringBuffer stringBuffer = new StringBuffer((str.length() << 2) / 3);
                for (int i2 = 0; i2 < str.length(); i2++) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == 9 || charAt2 == 10 || charAt2 == 13) {
                        stringBuffer.append("&#x");
                        stringBuffer.append(Integer.toHexString(charAt2).toUpperCase());
                        stringBuffer.append(';');
                    } else if (charAt2 == '\"') {
                        if (!z) {
                            str2 = "\"";
                        } else {
                            str2 = "&quot;";
                        }
                        stringBuffer.append(str2);
                    } else if (charAt2 == '&') {
                        stringBuffer.append("&amp;");
                    } else if (charAt2 == '<') {
                        stringBuffer.append("&lt;");
                    } else if (charAt2 != '>') {
                        stringBuffer.append(charAt2);
                    } else {
                        stringBuffer.append("&gt;");
                    }
                }
                return stringBuffer.toString();
            }
        }
        return str;
    }

    /* renamed from: c */
    private static boolean m1196c(char c) {
        return c > 255 || f1211b[c];
    }

    /* renamed from: b */
    private static boolean m1194b(char c) {
        return c > 255 || f1210a[c];
    }

    /* renamed from: d */
    public static boolean m1198d(String str) {
        if (str.length() > 0 && !m1194b(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            if (!m1196c(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: e */
    public static boolean m1199e(String str) {
        if (str.length() > 0 && (!m1194b(str.charAt(0)) || str.charAt(0) == ':')) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            if (!m1196c(str.charAt(i)) || str.charAt(i) == ':') {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static String m1191a(String str) {
        if ("x-default".equals(str)) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 1;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != ' ') {
                if (charAt == '-' || charAt == '_') {
                    stringBuffer.append('-');
                    i++;
                } else if (i == 2) {
                    stringBuffer.append(Character.toUpperCase(str.charAt(i2)));
                } else {
                    stringBuffer.append(Character.toLowerCase(str.charAt(i2)));
                }
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: f */
    static String m1200f(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (m1193a(stringBuffer.charAt(i))) {
                stringBuffer.setCharAt(i, ' ');
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public static String[] m1195b(String str) {
        int indexOf = str.indexOf(61);
        String substring = str.substring(str.charAt(1) == '?' ? 2 : 1, indexOf);
        int i = indexOf + 1;
        char charAt = str.charAt(i);
        int i2 = i + 1;
        int length = str.length() - 2;
        StringBuffer stringBuffer = new StringBuffer(length - indexOf);
        while (i2 < length) {
            stringBuffer.append(str.charAt(i2));
            i2++;
            if (str.charAt(i2) == charAt) {
                i2++;
            }
        }
        return new String[]{substring, stringBuffer.toString()};
    }
}
