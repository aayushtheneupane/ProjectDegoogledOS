package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import p003j$.util.function.Supplier;

/* renamed from: bmw */
/* compiled from: PG */
public final /* synthetic */ class bmw implements dfz {

    /* renamed from: a */
    private final inw f3165a;

    public bmw(inw inw) {
        this.f3165a = inw;
    }

    /* renamed from: a */
    public final ieh mo2592a(cue cue) {
        bmy bmy = (bmy) this.f3165a.mo9034a();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        return cun.m5445a(cun.m5444a(cue, cun.m5441a((Supplier) new bmq(bmy))), (ice) new bmr(bmy, atomicBoolean, cue), (Executor) bmy.f3171e);
    }
}
