package p000;

import java.util.concurrent.Executor;

/* renamed from: cvm */
/* compiled from: PG */
public final class cvm {

    /* renamed from: a */
    public final iel f5746a;

    public cvm(iel iel) {
        this.f5746a = iel;
    }

    /* renamed from: a */
    public final ieh mo3847a(cvk cvk, cvl cvl, cue cue) {
        return mo3848a(cvk, cvl, cue, cvf.f5728a);
    }

    /* renamed from: a */
    public final ieh mo3848a(cvk cvk, cvl cvl, cue cue, Runnable runnable) {
        return gte.m10771a(cvk.mo2589a(), (icf) new cve(this, cvk, cvl, cue, runnable), (Executor) this.f5746a);
    }
}
