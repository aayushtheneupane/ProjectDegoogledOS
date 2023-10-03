package p000;

/* renamed from: ipn */
/* compiled from: PG */
final class ipn extends ipk {

    /* renamed from: a */
    private final hso f14626a;

    public ipn(Iterable iterable) {
        this.f14626a = hso.m12032a(iterable);
    }

    /* renamed from: a */
    public final ipl mo7603a(Object obj) {
        hsj j = hso.m12048j();
        hvs i = this.f14626a.listIterator();
        while (i.hasNext()) {
            ipk ipk = (ipk) i.next();
            try {
                ipl a = ipk.mo7603a(obj);
                if (a != null) {
                    j.mo7908c(a);
                }
            } catch (RuntimeException e) {
                ips.m14312a(e, ipk, obj);
            }
        }
        hso a2 = j.mo7905a();
        if (!a2.isEmpty()) {
            return a2.size() == 1 ? new ipr((ipl) ife.m12903f((Iterable) a2)) : new ipo(a2);
        }
        return ipl.f14624a;
    }
}
