package p000;

/* renamed from: ipo */
/* compiled from: PG */
final class ipo extends ipl {

    /* renamed from: b */
    private final hso f14627b;

    public ipo(hso hso) {
        this.f14627b = hso;
    }

    /* renamed from: a */
    public final ipg mo7602a(iph iph) {
        hsj j = hso.m12048j();
        hvs i = this.f14627b.listIterator();
        while (i.hasNext()) {
            ipl ipl = (ipl) i.next();
            try {
                ipg a = ipl.mo7602a(iph);
                if (a != null) {
                    j.mo7908c(a);
                }
            } catch (RuntimeException e) {
                ips.m14313a(e, ipl, iph);
            }
        }
        hso a2 = j.mo7905a();
        if (!a2.isEmpty()) {
            return a2.size() == 1 ? new ipp((ipg) ife.m12903f((Iterable) a2)) : new ipm(a2);
        }
        return ipg.f14621a;
    }
}
