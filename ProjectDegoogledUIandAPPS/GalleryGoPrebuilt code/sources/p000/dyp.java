package p000;

import java.util.concurrent.Executor;

/* renamed from: dyp */
/* compiled from: PG */
final /* synthetic */ class dyp implements ice {

    /* renamed from: a */
    private final dyq f7659a;

    /* renamed from: b */
    private final dyj f7660b;

    public dyp(dyq dyq, dyj dyj) {
        this.f7659a = dyq;
        this.f7660b = dyj;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        ieh ieh;
        dyq dyq = this.f7659a;
        dyj dyj = this.f7660b;
        dyr dyr = dyq.f7661a;
        hlj a = hnb.m11765a("Get items for photo grid");
        try {
            cxd a2 = dyj.mo4566a();
            if (a2.f5887b != 1) {
                ieh = dyr.f7662a.mo3942a(a2);
            } else {
                ieh = gte.m10770a(dyr.f7663b.mo2731b(a2), dyo.f7658a, (Executor) idh.f13918a);
            }
            ieh a3 = a.mo7548a(gte.m10770a(ieh, (hpr) new dyk(dyr, dyj), (Executor) dyr.f7666e));
            if (a != null) {
                a.close();
            }
            return a3;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
