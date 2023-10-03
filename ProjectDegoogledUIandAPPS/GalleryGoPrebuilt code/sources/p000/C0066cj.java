package p000;

import java.util.List;

/* renamed from: cj */
/* compiled from: PG */
public final class C0066cj {

    /* renamed from: a */
    private final String f4480a;

    /* renamed from: b */
    private final boolean f4481b;

    /* renamed from: c */
    private final List f4482c;

    public C0066cj(String str, boolean z, List list) {
        this.f4480a = str;
        this.f4481b = z;
        this.f4482c = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0066cj) {
            C0066cj cjVar = (C0066cj) obj;
            if (this.f4481b == cjVar.f4481b && this.f4482c.equals(cjVar.f4482c)) {
                if (!this.f4480a.startsWith("index_")) {
                    return this.f4480a.equals(cjVar.f4480a);
                }
                return cjVar.f4480a.startsWith("index_");
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        if (!this.f4480a.startsWith("index_")) {
            i = this.f4480a.hashCode();
        } else {
            i = -1184239155;
        }
        return (((i * 31) + (this.f4481b ? 1 : 0)) * 31) + this.f4482c.hashCode();
    }

    public final String toString() {
        return "Index{name='" + this.f4480a + "', unique=" + this.f4481b + ", columns=" + this.f4482c + '}';
    }
}
