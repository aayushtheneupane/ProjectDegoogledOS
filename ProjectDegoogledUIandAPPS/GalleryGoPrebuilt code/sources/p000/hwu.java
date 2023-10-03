package p000;

/* renamed from: hwu */
/* compiled from: PG */
public enum hwu {
    STRING('s', hww.GENERAL, "-#", true),
    BOOLEAN('b', hww.BOOLEAN, "-", true),
    CHAR('c', hww.CHARACTER, "-", true),
    DECIMAL('d', hww.INTEGRAL, "-0+ ,(", false),
    OCTAL('o', hww.INTEGRAL, "-#0(", false),
    HEX('x', hww.INTEGRAL, "-#0(", true),
    FLOAT('f', hww.FLOAT, "-#0+ ,(", false),
    EXPONENT('e', hww.FLOAT, "-#0+ (", true),
    GENERAL('g', hww.FLOAT, "-0+ ,(", true),
    EXPONENT_HEX('a', hww.FLOAT, "-#0+ ", true);
    

    /* renamed from: c */
    public static final hwu[] f13537c = null;

    /* renamed from: d */
    public final char f13547d;

    /* renamed from: e */
    public final hww f13548e;

    /* renamed from: f */
    public final int f13549f;

    /* renamed from: g */
    public final String f13550g;

    /* renamed from: a */
    public static int m12338a(char c) {
        return (c | ' ') - 'a';
    }

    static {
        int i;
        f13537c = new hwu[26];
        for (hwu hwu : values()) {
            f13537c[m12338a(hwu.f13547d)] = hwu;
        }
    }

    private hwu(char c, hww hww, String str, boolean z) {
        this.f13547d = c;
        this.f13548e = hww;
        this.f13549f = hwv.m12341a(str, z);
        StringBuilder sb = new StringBuilder(2);
        sb.append("%");
        sb.append(c);
        this.f13550g = sb.toString();
    }
}
