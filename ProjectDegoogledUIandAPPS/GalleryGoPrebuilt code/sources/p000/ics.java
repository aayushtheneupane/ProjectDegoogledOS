package p000;

import java.util.logging.Logger;

/* renamed from: ics */
/* compiled from: PG */
public final class ics implements ice {

    /* renamed from: a */
    private final /* synthetic */ icu f13889a;

    /* renamed from: b */
    private final /* synthetic */ icv f13890b;

    public ics(icv icv, icu icu) {
        this.f13890b = icv;
        this.f13889a = icu;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        icx icx = new icx(this.f13890b.f13893b);
        icu icu = this.f13889a;
        icp icp = this.f13890b.f13892a;
        icx.f13894a = true;
        icp icp2 = new icp((byte[]) null);
        try {
            idb idb = ((gov) icu).f11773a.mo2675a().f11782a;
            Logger logger = idb.f13904a;
            idb.mo8399a(icp);
            return idb.f13907d;
        } finally {
            icp.mo8381a(icp2, idh.f13918a);
            icx.f13894a = false;
        }
    }

    public final String toString() {
        return this.f13889a.toString();
    }
}
