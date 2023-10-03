package p000;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: bmr */
/* compiled from: PG */
final /* synthetic */ class bmr implements ice {

    /* renamed from: a */
    private final bmy f3156a;

    /* renamed from: b */
    private final AtomicBoolean f3157b;

    /* renamed from: c */
    private final cue f3158c;

    public bmr(bmy bmy, AtomicBoolean atomicBoolean, cue cue) {
        this.f3156a = bmy;
        this.f3157b = atomicBoolean;
        this.f3158c = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        bmy bmy = this.f3156a;
        AtomicBoolean atomicBoolean = this.f3157b;
        return bmy.f3168b.mo3848a(new bmt(bmy), new bmu(bmy, atomicBoolean), this.f3158c, new bmv(bmy, atomicBoolean));
    }
}
