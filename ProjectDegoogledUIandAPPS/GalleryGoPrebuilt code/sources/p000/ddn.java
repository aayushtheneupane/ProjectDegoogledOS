package p000;

/* renamed from: ddn */
/* compiled from: PG */
public final class ddn extends ddt {

    /* renamed from: a */
    public final int f6358a;

    /* renamed from: b */
    public final int f6359b;

    public ddn(int i, int i2) {
        this.f6358a = i;
        this.f6359b = i2;
    }

    /* renamed from: a */
    public final int mo4074a() {
        return this.f6358a;
    }

    /* renamed from: b */
    public final int mo4075b() {
        return this.f6359b;
    }

    public final int hashCode() {
        return ((this.f6358a ^ 1000003) * 1000003) ^ this.f6359b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ddt) {
            ddt ddt = (ddt) obj;
            return this.f6358a == ddt.mo4074a() && this.f6359b == ddt.mo4075b();
        }
    }

    public final String toString() {
        int i = this.f6358a;
        int i2 = this.f6359b;
        StringBuilder sb = new StringBuilder(51);
        sb.append("OffsetData{offset=");
        sb.append(i);
        sb.append(", padding=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }
}
