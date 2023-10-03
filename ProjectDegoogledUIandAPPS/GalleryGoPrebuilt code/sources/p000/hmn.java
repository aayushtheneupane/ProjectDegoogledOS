package p000;

/* renamed from: hmn */
/* compiled from: PG */
final class hmn implements ice {

    /* renamed from: a */
    private final /* synthetic */ hlp f13041a;

    /* renamed from: b */
    private final /* synthetic */ ice f13042b;

    public hmn(hlp hlp, ice ice) {
        this.f13041a = hlp;
        this.f13042b = ice;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        hlp b = hnb.m11776b(this.f13041a);
        try {
            return this.f13042b.mo2539a();
        } finally {
            hnb.m11776b(b);
        }
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13042b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("propagating=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
