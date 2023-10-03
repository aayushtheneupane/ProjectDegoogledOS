package p000;

/* renamed from: ddm */
/* compiled from: PG */
public final class ddm extends ddp {

    /* renamed from: a */
    private final String f6354a;

    /* renamed from: b */
    private final String f6355b;

    /* renamed from: c */
    private final int f6356c;

    /* renamed from: d */
    private final int f6357d;

    public /* synthetic */ ddm(String str, String str2, int i, int i2) {
        this.f6354a = str;
        this.f6355b = str2;
        this.f6356c = i;
        this.f6357d = i2;
    }

    /* renamed from: a */
    public final String mo4067a() {
        return this.f6354a;
    }

    /* renamed from: b */
    public final String mo4068b() {
        return this.f6355b;
    }

    /* renamed from: c */
    public final int mo4069c() {
        return this.f6356c;
    }

    /* renamed from: d */
    public final int mo4070d() {
        return this.f6357d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ddp) {
            ddp ddp = (ddp) obj;
            return this.f6354a.equals(ddp.mo4067a()) && this.f6355b.equals(ddp.mo4068b()) && this.f6356c == ddp.mo4069c() && this.f6357d == ddp.mo4070d();
        }
    }

    public final int hashCode() {
        return ((((((this.f6354a.hashCode() ^ 1000003) * 1000003) ^ this.f6355b.hashCode()) * 1000003) ^ this.f6356c) * 1000003) ^ this.f6357d;
    }

    public final String toString() {
        String str = this.f6354a;
        String str2 = this.f6355b;
        int i = this.f6356c;
        int i2 = this.f6357d;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 85 + String.valueOf(str2).length());
        sb.append("MicroVideoXmpContainerItem{mime=");
        sb.append(str);
        sb.append(", semantic=");
        sb.append(str2);
        sb.append(", length=");
        sb.append(i);
        sb.append(", padding=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }
}
