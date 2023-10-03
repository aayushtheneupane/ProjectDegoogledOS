package p000;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: diy */
/* compiled from: PG */
final /* synthetic */ class diy implements ice {

    /* renamed from: a */
    private final djd f6638a;

    /* renamed from: b */
    private final AtomicBoolean f6639b;

    /* renamed from: c */
    private final cue f6640c;

    public diy(djd djd, AtomicBoolean atomicBoolean, cue cue) {
        this.f6638a = djd;
        this.f6639b = atomicBoolean;
        this.f6640c = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        djd djd = this.f6638a;
        AtomicBoolean atomicBoolean = this.f6639b;
        return djd.f6655c.mo3848a(new dja(djd), new djb(djd, atomicBoolean), this.f6640c, new djc(djd, atomicBoolean));
    }
}
