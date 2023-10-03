package p000;

import java.util.concurrent.Executor;

/* renamed from: dqc */
/* compiled from: PG */
final /* synthetic */ class dqc implements icf {

    /* renamed from: a */
    private final dqi f7099a;

    /* renamed from: b */
    private final dpz f7100b;

    public dqc(dqi dqi, dpz dpz) {
        this.f7099a = dqi;
        this.f7100b = dpz;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        ieh ieh;
        dqi dqi = this.f7099a;
        dpz dpz = this.f7100b;
        cxd cxd = (cxd) obj;
        if (cxd.f5887b == 1) {
            ieh = gte.m10770a(dqi.f7106a.f7115e.mo2731b(cxd), dqf.f7103a, (Executor) idh.f13918a);
        } else {
            ieh = dqi.f7106a.f7113c.mo3942a(cxd);
        }
        dpz.getClass();
        return gte.m10770a(ieh, (hpr) new dqh(dpz), (Executor) idh.f13918a);
    }
}
