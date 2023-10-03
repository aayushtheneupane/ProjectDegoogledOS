package p000;

/* renamed from: hwg */
/* compiled from: PG */
public abstract class hwg {

    /* renamed from: a */
    public static final hwg f13519a = new hwe();

    /* renamed from: a */
    public abstract String mo8219a();

    /* renamed from: b */
    public abstract String mo8220b();

    /* renamed from: c */
    public abstract int mo8221c();

    /* renamed from: d */
    public abstract String mo8222d();

    @Deprecated
    /* renamed from: a */
    public static hwg m12306a(String str, String str2, int i, String str3) {
        return new hwf(str, str2, i, str3);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LogSite{ class=");
        sb.append(mo8219a());
        sb.append(", method=");
        sb.append(mo8220b());
        sb.append(", line=");
        sb.append(mo8221c());
        if (mo8222d() != null) {
            sb.append(", file=");
            sb.append(mo8222d());
        }
        sb.append(" }");
        return sb.toString();
    }
}
