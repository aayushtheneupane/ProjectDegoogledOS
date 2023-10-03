package p000;

/* renamed from: ipr */
/* compiled from: PG */
final class ipr extends ipl {

    /* renamed from: b */
    private final ipl f14630b;

    public ipr(ipl ipl) {
        this.f14630b = ipl;
    }

    /* renamed from: a */
    public final ipg mo7602a(iph iph) {
        try {
            ipg a = this.f14630b.mo7602a(iph);
            return a != null ? new ipp(a) : ipg.f14621a;
        } catch (RuntimeException e) {
            ips.m14313a(e, this.f14630b, iph);
            return ipg.f14621a;
        }
    }
}
