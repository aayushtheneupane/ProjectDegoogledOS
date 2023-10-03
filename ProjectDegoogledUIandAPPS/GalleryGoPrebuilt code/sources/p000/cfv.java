package p000;

import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: cfv */
/* compiled from: PG */
public final class cfv extends iox {

    /* renamed from: b */
    private final ioq f4290b;

    /* renamed from: c */
    private final ioq f4291c;

    /* renamed from: d */
    private final ioq f4292d;

    public cfv(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(cfv.class), iqk);
        this.f4290b = ipd.m14274a(ioq);
        this.f4291c = ipd.m14274a(ioq2);
        this.f4292d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        cfj cfj = (cfj) list.get(0);
        hso hso = (hso) list.get(1);
        hso hso2 = (hso) list.get(2);
        Object[] objArr = {Integer.valueOf(hso.size()), Integer.valueOf(hso2.size())};
        hlj a = hnb.m11765a("Assign Face Clusters");
        try {
            ieh a2 = a.mo7548a(gte.m10771a(gte.m10770a(cfj.f4264a.f4453a.mo6359a(), cic.f4451a, (Executor) idh.f13918a), (icf) new cfg(cfj, hso, hso2), (Executor) cfj.f4265b));
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f4290b.mo9044b(), this.f4291c.mo9044b(), this.f4292d.mo9044b());
    }
}
