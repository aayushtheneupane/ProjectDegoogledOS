package p000;

/* renamed from: hyt */
/* compiled from: PG */
public final class hyt extends RuntimeException {
    public hyt(String str) {
        super(str);
    }

    public final synchronized Throwable fillInStackTrace() {
        return this;
    }

    /* renamed from: a */
    public static hyt m12475a(String str, String str2, int i) {
        return new hyt(m12478b(str, str2, i, i + 1));
    }

    /* renamed from: b */
    private static String m12478b(String str, String str2, int i, int i2) {
        if (i2 < 0) {
            i2 = str2.length();
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(": ");
        if (i > 8) {
            sb.append("...");
            sb.append(str2, i - 5, i);
        } else {
            sb.append(str2, 0, i);
        }
        sb.append('[');
        sb.append(str2.substring(i, i2));
        sb.append(']');
        if (str2.length() - i2 > 8) {
            sb.append(str2, i2, i2 + 5);
            sb.append("...");
        } else {
            sb.append(str2, i2, str2.length());
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static hyt m12476a(String str, String str2, int i, int i2) {
        return new hyt(m12478b(str, str2, i, i2));
    }

    /* renamed from: b */
    public static hyt m12477b(String str, String str2, int i) {
        return new hyt(m12478b(str, str2, i, -1));
    }
}
