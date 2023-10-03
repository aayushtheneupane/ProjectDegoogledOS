package p000;

/* renamed from: ta */
/* compiled from: PG */
public final class C0520ta {

    /* renamed from: a */
    public int f15900a;

    /* renamed from: b */
    public int f15901b;

    /* renamed from: c */
    public Object f15902c;

    /* renamed from: d */
    public int f15903d;

    public C0520ta(int i, int i2, int i3, Object obj) {
        this.f15900a = i;
        this.f15901b = i2;
        this.f15903d = i3;
        this.f15902c = obj;
    }

    public final int hashCode() {
        return (((this.f15900a * 31) + this.f15901b) * 31) + this.f15903d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0520ta) {
            C0520ta taVar = (C0520ta) obj;
            int i = this.f15900a;
            if (i == taVar.f15900a) {
                if (i == 8 && Math.abs(this.f15903d - this.f15901b) == 1 && this.f15903d == taVar.f15901b && this.f15901b == taVar.f15903d) {
                    return true;
                }
                if (this.f15903d == taVar.f15903d && this.f15901b == taVar.f15901b) {
                    Object obj2 = this.f15902c;
                    if (obj2 != null) {
                        if (!obj2.equals(taVar.f15902c)) {
                            return false;
                        }
                    } else if (taVar.f15902c != null) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i = this.f15900a;
        if (i != 1) {
            str = i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm";
        } else {
            str = "add";
        }
        sb.append(str);
        sb.append(",s:");
        sb.append(this.f15901b);
        sb.append("c:");
        sb.append(this.f15903d);
        sb.append(",p:");
        sb.append(this.f15902c);
        sb.append("]");
        return sb.toString();
    }
}
