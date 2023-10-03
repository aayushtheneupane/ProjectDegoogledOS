package p000;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: glu */
/* compiled from: PG */
final /* synthetic */ class glu implements icf {

    /* renamed from: a */
    private final glx f11591a;

    public glu(glx glx) {
        this.f11591a = glx;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        glx glx = this.f11591a;
        hsj j = hso.m12048j();
        Iterator it = ((List) obj).iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                break;
            }
            hso hso = (hso) it.next();
            hvs i = hso.listIterator();
            while (i.hasNext()) {
                gle gle = (gle) i.next();
                ife.m12896d(str == null || str.equals(gle.f11575h));
                str = gle.f11575h;
            }
            j.mo7907b((Iterable) hso);
        }
        glp glp = glx.f11594a;
        hso a = j.mo7905a();
        ife.m12898e((Object) gtf.f12011a);
        hlj a2 = hnb.m11765a("Sync Accounts");
        try {
            fzx fzx = glp.f11583a;
            gmo gmo = new gmo(a);
            AtomicReference atomicReference = new AtomicReference();
            ieh a3 = ibv.m12657a(fzx.mo6360a(hmq.m11742a((hpr) new gmm(gmo, atomicReference)), glp.f11584b), hmq.m11742a((hpr) new gmn(atomicReference)), (Executor) idh.f13918a);
            ieh a4 = ibv.m12658a(a3, hmq.m11744a((icf) new gmp(glp)), (Executor) glp.f11584b);
            ieh a5 = ife.m12884c(a4, a2.mo7548a(ibv.m12658a(ibv.m12658a((ieh) idq.m12731c(ibd.m12603a(a4, Throwable.class, ife.m12906g((Object) null), (Executor) idh.f13918a)), hmq.m11744a((icf) new gmq(a3)), (Executor) idh.f13918a), hmq.m11744a((icf) new gmr(glp)), (Executor) idh.f13918a))).mo8443a(ife.m12887c(), (Executor) idh.f13918a);
            if (a2 != null) {
                a2.close();
            }
            return a5;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
