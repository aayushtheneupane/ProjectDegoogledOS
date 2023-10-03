package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import p003j$.util.function.Supplier;

/* renamed from: dir */
/* compiled from: PG */
public final /* synthetic */ class dir implements dfz {

    /* renamed from: a */
    private final inw f6631a;

    public dir(inw inw) {
        this.f6631a = inw;
    }

    /* renamed from: a */
    public final ieh mo2592a(cue cue) {
        djd djd = (djd) this.f6631a.mo9034a();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        return cun.m5445a(cun.m5444a(cue, cun.m5441a((Supplier) new diw(djd)), cun.m5441a((Supplier) new dix(djd))), (ice) new diy(djd, atomicBoolean, cue), (Executor) djd.f6660h);
    }
}
