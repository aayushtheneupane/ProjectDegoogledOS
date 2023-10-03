package p000;

/* renamed from: gwv */
/* compiled from: PG */
public final class gwv extends gxc {

    /* renamed from: a */
    private final Object f12216a;

    /* renamed from: b */
    private final int f12217b;

    public gwv(Object obj, int i) {
        if (obj != null) {
            this.f12216a = obj;
            this.f12217b = i;
            return;
        }
        throw new NullPointerException("Null getValue");
    }

    /* renamed from: a */
    public final Object mo7164a() {
        return this.f12216a;
    }

    /* renamed from: b */
    public final int mo7165b() {
        return this.f12217b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gxc) {
            gxc gxc = (gxc) obj;
            return this.f12216a.equals(gxc.mo7164a()) && this.f12217b == gxc.mo7165b();
        }
    }

    public final int hashCode() {
        return ((this.f12216a.hashCode() ^ 1000003) * 1000003) ^ this.f12217b;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12216a);
        int i = this.f12217b;
        String str = i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "PROTO_VALUE" : "BYTES_VALUE" : "STRING_VALUE" : "DOUBLE_VALUE" : "BOOLEAN_VALUE" : "LONG_VALUE";
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 33 + str.length());
        sb.append("FlagValueHolder{getValue=");
        sb.append(valueOf);
        sb.append(", type=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
