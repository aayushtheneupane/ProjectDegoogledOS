package p000;

/* renamed from: ddi */
/* compiled from: PG */
public final class ddi extends ddl {

    /* renamed from: a */
    public final int f6338a;

    /* renamed from: b */
    public final int f6339b;

    public /* synthetic */ ddi(int i, int i2) {
        this.f6338a = i;
        this.f6339b = i2;
    }

    /* renamed from: a */
    public final int mo4062a() {
        return this.f6338a;
    }

    /* renamed from: b */
    public final int mo4063b() {
        return this.f6339b;
    }

    public final int hashCode() {
        return ((this.f6338a ^ 1000003) * 1000003) ^ this.f6339b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ddl) {
            ddl ddl = (ddl) obj;
            return this.f6338a == ddl.mo4062a() && this.f6339b == ddl.mo4063b();
        }
    }

    public final String toString() {
        int i = this.f6338a;
        int i2 = this.f6339b;
        StringBuilder sb = new StringBuilder(43);
        sb.append("Size{width=");
        sb.append(i);
        sb.append(", height=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }
}
