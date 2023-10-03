package p000;

/* renamed from: ajj */
/* compiled from: PG */
public final class ajj {

    /* renamed from: a */
    public final boolean f641a;

    /* renamed from: b */
    public final boolean f642b;

    /* renamed from: c */
    public final boolean f643c;

    /* renamed from: d */
    public final boolean f644d;

    public ajj(boolean z, boolean z2, boolean z3, boolean z4) {
        this.f641a = z;
        this.f642b = z2;
        this.f643c = z3;
        this.f644d = z4;
    }

    public final int hashCode() {
        int i = this.f641a;
        if (this.f642b) {
            i += 16;
        }
        if (this.f643c) {
            i += 256;
        }
        return this.f644d ? i + 4096 : i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ajj) {
            ajj ajj = (ajj) obj;
            return this.f641a == ajj.f641a && this.f642b == ajj.f642b && this.f643c == ajj.f643c && this.f644d == ajj.f644d;
        }
    }

    public final String toString() {
        return String.format("[ Connected=%b Validated=%b Metered=%b NotRoaming=%b ]", new Object[]{Boolean.valueOf(this.f641a), Boolean.valueOf(this.f642b), Boolean.valueOf(this.f643c), Boolean.valueOf(this.f644d)});
    }
}
