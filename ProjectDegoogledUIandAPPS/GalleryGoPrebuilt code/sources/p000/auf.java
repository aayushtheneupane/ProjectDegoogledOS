package p000;

/* renamed from: auf */
/* compiled from: PG */
final class auf implements arh {

    /* renamed from: a */
    private final /* synthetic */ axn f1705a;

    /* renamed from: b */
    private final /* synthetic */ aug f1706b;

    public auf(aug aug, axn axn) {
        this.f1706b = aug;
        this.f1705a = axn;
    }

    /* renamed from: a */
    public final void mo1525a(Object obj) {
        if (this.f1706b.mo1629a(this.f1705a)) {
            aug aug = this.f1706b;
            axn axn = this.f1705a;
            atc atc = aug.f1707a.f1552p;
            if (obj != null && atc.mo1579a(axn.f1831c.mo1518d())) {
                aug.f1709c = obj;
                aug.f1708b.mo1555c();
                return;
            }
            asp asp = aug.f1708b;
            aqu aqu = axn.f1829a;
            ari ari = axn.f1831c;
            asp.mo1554a(aqu, obj, ari, ari.mo1518d(), aug.f1710d);
        }
    }

    /* renamed from: a */
    public final void mo1524a(Exception exc) {
        if (this.f1706b.mo1629a(this.f1705a)) {
            aug aug = this.f1706b;
            axn axn = this.f1705a;
            asp asp = aug.f1708b;
            asn asn = aug.f1710d;
            ari ari = axn.f1831c;
            asp.mo1553a(asn, exc, ari, ari.mo1518d());
        }
    }
}
