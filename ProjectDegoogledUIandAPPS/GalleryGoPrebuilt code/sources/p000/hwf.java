package p000;

/* renamed from: hwf */
/* compiled from: PG */
final class hwf extends hwg {

    /* renamed from: b */
    private final String f13514b;

    /* renamed from: c */
    private final String f13515c;

    /* renamed from: d */
    private final int f13516d;

    /* renamed from: e */
    private final String f13517e;

    /* renamed from: f */
    private int f13518f = 0;

    public /* synthetic */ hwf(String str, String str2, int i, String str3) {
        this.f13514b = (String) ife.m12827a((Object) str, "class name");
        this.f13515c = (String) ife.m12827a((Object) str2, "method name");
        this.f13516d = i;
        this.f13517e = str3;
    }

    /* renamed from: b */
    public final String mo8220b() {
        return this.f13515c;
    }

    /* renamed from: c */
    public final int mo8221c() {
        return this.f13516d;
    }

    /* renamed from: d */
    public final String mo8222d() {
        return this.f13517e;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof hwf) {
            hwf hwf = (hwf) obj;
            if (!this.f13514b.equals(hwf.f13514b) || !this.f13515c.equals(hwf.f13515c) || this.f13516d != hwf.f13516d) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final String mo8219a() {
        return this.f13514b.replace('/', '.');
    }

    public final int hashCode() {
        int i = this.f13518f;
        if (i != 0) {
            return i;
        }
        int hashCode = ((((this.f13514b.hashCode() + 4867) * 31) + this.f13515c.hashCode()) * 31) + this.f13516d;
        this.f13518f = hashCode;
        return hashCode;
    }
}
