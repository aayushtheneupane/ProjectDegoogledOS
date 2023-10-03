package p000;

/* renamed from: ick */
/* compiled from: PG */
final class ick implements icf {

    /* renamed from: a */
    private final /* synthetic */ ico f13881a;

    /* renamed from: b */
    private final /* synthetic */ idb f13882b;

    public ick(idb idb, ico ico) {
        this.f13882b = idb;
        this.f13881a = ico;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        icp icp = this.f13882b.f13906c;
        ico ico = this.f13881a;
        icp icp2 = new icp();
        try {
            idb a = ico.mo6373a(icp2, obj);
            a.mo8399a(icp2);
            return a.f13907d;
        } finally {
            icp.mo8381a(icp2, idh.f13918a);
        }
    }

    public final String toString() {
        return this.f13881a.toString();
    }
}
