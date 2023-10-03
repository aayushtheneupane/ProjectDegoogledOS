package p000;

/* renamed from: cpq */
/* compiled from: PG */
final class cpq extends cpj {

    /* renamed from: a */
    private final cpi f5383a;

    public cpq(cpi cpi) {
        this.f5383a = cpi;
    }

    /* renamed from: a */
    public final cpi mo3725a() {
        return this.f5383a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cpj) {
            return this.f5383a.equals(((cpj) obj).mo3725a());
        }
        return false;
    }

    public final int hashCode() {
        return this.f5383a.hashCode() ^ 1000003;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f5383a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 43);
        sb.append("ActionModeExitedEvent{actionModeBehaviour=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
