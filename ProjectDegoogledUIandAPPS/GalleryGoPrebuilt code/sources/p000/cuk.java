package p000;

import java.util.concurrent.Executor;

/* renamed from: cuk */
/* compiled from: PG */
final /* synthetic */ class cuk implements ice {

    /* renamed from: a */
    private final Iterable f5672a;

    /* renamed from: b */
    private final cue f5673b;

    /* renamed from: c */
    private final Executor f5674c;

    public cuk(Iterable iterable, cue cue, Executor executor) {
        this.f5672a = iterable;
        this.f5673b = cue;
        this.f5674c = executor;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        Iterable<cum> iterable = this.f5672a;
        cue cue = this.f5673b;
        Executor executor = this.f5674c;
        ieh a = ife.m12820a((Object) null);
        for (cum cul : iterable) {
            a = gte.m10769a(a).mo7611a((ice) new cul(cul, cue), executor);
        }
        return a;
    }
}
