package p000;

import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: cgb */
/* compiled from: PG */
public final class cgb extends iox {

    /* renamed from: b */
    private final ioq f4312b;

    /* renamed from: c */
    private final ioq f4313c;

    public cgb(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2) {
        super(iqk2, iph.m14288a(cgb.class), iqk);
        this.f4312b = ipd.m14274a(ioq);
        this.f4313c = ipd.m14274a(ioq2);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        cfi cfi = (cfi) list.get(0);
        hge hge = (hge) list.get(1);
        hsj j = hso.m12048j();
        hvs i = cfi.f4263b.listIterator();
        while (i.hasNext()) {
            cia cia = (cia) i.next();
            if (!cia.mo3107a().isPresent()) {
                j.mo7908c(cia);
            }
        }
        return hge.mo7394a().mo6898b((icf) new cfs(cfi, j), (Executor) idh.f13918a).mo6894a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f4312b.mo9044b(), this.f4313c.mo9044b());
    }
}
