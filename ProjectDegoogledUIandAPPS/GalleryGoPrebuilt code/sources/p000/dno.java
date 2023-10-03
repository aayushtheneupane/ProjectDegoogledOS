package p000;

import java.util.List;

/* renamed from: dno */
/* compiled from: PG */
final class dno implements hol {

    /* renamed from: a */
    private final /* synthetic */ dnn f6918a;

    public dno(dnn dnn) {
        this.f6918a = dnn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        bqj bqj = (bqj) hoi;
        dnn dnn = this.f6918a;
        if (dnn.f6883M) {
            cwn.m5510a("OneUpFragmentPeer: overlaping delete events", new Object[0]);
        } else {
            boolean z = true;
            dnn.f6883M = true;
            if (bqj.f3368a.size() != 1) {
                z = false;
            }
            ife.m12846a(z, "Oneup received a delete event for the deletion of %s items", bqj.f3368a.size());
            cyd cyd = (cyd) bqj.f3368a.get(0);
            dnn.f6908q.mo6986a(grw.m10690e(dnn.f6910s.mo4795a((List) hso.m12033a((Object) cyd))), new grt(imi.m14101a((ikf) cyd)), dnn.f6875E);
        }
        return hom.f13155a;
    }
}
