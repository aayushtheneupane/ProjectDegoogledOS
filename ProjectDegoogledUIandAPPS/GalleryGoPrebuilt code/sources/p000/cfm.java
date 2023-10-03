package p000;

import java.util.concurrent.Executor;

/* renamed from: cfm */
/* compiled from: PG */
final /* synthetic */ class cfm implements ice {

    /* renamed from: a */
    private final cfu f4269a;

    /* renamed from: b */
    private final cue f4270b;

    public cfm(cfu cfu, cue cue) {
        this.f4269a = cfu;
        this.f4270b = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        cfu cfu = this.f4269a;
        return gte.m10771a(cfu.f4284c.mo3160b(1000), (icf) new cfp(cfu, this.f4270b), (Executor) cfu.f4285d);
    }
}
