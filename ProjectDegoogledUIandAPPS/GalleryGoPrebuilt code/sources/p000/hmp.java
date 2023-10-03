package p000;

/* renamed from: hmp */
/* compiled from: PG */
final class hmp implements icf {

    /* renamed from: a */
    private final /* synthetic */ hlp f13045a;

    /* renamed from: b */
    private final /* synthetic */ icf f13046b;

    public hmp(hlp hlp, icf icf) {
        this.f13045a = hlp;
        this.f13046b = icf;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        hlp b = hnb.m11776b(this.f13045a);
        try {
            return this.f13046b.mo2538a(obj);
        } finally {
            hnb.m11776b(b);
        }
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13046b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("propagating=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
