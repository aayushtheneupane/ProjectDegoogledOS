package p000;

/* renamed from: gxp */
/* compiled from: PG */
public final class gxp implements gzx {

    /* renamed from: a */
    private final String f12267a;

    public gxp(String str) {
        this.f12267a = str;
    }

    /* renamed from: a */
    public final String mo7187a() {
        return this.f12267a;
    }

    public final Class annotationType() {
        return gzx.class;
    }

    /* renamed from: a */
    private static void m11008a(StringBuilder sb, String str, int i) {
        for (int length = i - str.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(str);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gzx) {
            return this.f12267a.equals(((gzx) obj).mo7187a());
        }
        return false;
    }

    public final int hashCode() {
        return this.f12267a.hashCode() ^ 809595467;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("@com.google.apps.tiktok.experiments.phenotype.Subpackage(");
        String str = this.f12267a;
        sb.append("basePackage=\"");
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == 9) {
                sb.append("\\t");
            } else if (charAt == 10) {
                sb.append("\\n");
            } else if (charAt == 13) {
                sb.append("\\r");
            } else if (charAt == '\"' || charAt == '\'' || charAt == '\\') {
                sb.append('\\');
                sb.append(charAt);
            } else if (charAt < ' ') {
                sb.append('\\');
                m11008a(sb, Integer.toOctalString(charAt), 3);
            } else if (charAt < 127 || Character.isLetter(charAt)) {
                sb.append(charAt);
            } else {
                sb.append("\\u");
                m11008a(sb, Integer.toHexString(charAt), 4);
            }
        }
        sb.append("\")");
        return sb.toString();
    }
}
