package p000;

/* renamed from: coz */
/* compiled from: PG */
final /* synthetic */ class coz implements icf {

    /* renamed from: a */
    private final cpd f5333a;

    /* renamed from: b */
    private final cyg f5334b;

    public coz(cpd cpd, cyg cyg) {
        this.f5333a = cpd;
        this.f5334b = cyg;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        cpd cpd = this.f5333a;
        cyg cyg = this.f5334b;
        byte[] bArr = (byte[]) obj;
        hlj a = hnb.m11765a("Store FAST_GRID_THUMBNAIL in DB");
        try {
            ieh a2 = cpd.f5343b.f5327a.mo2656a(new cor(((Long) cyg.mo3907a().get()).longValue(), bArr, cpd.f5348g.mo3839b(cyg.mo3906M(), cpd.f5342a, true)));
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
