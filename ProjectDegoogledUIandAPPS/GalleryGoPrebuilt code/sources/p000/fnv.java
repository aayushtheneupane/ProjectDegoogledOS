package p000;

/* renamed from: fnv */
/* compiled from: PG */
public final class fnv {

    /* renamed from: a */
    public final iqw f10113a;

    /* renamed from: b */
    public final Long f10114b;

    /* renamed from: c */
    public final Long f10115c;

    /* renamed from: d */
    public final Long f10116d;

    /* renamed from: e */
    public final Long f10117e;

    /* renamed from: f */
    public final iqm f10118f;

    /* renamed from: g */
    public final String f10119g;

    /* renamed from: h */
    public final Boolean f10120h;

    /* renamed from: i */
    public final iqx f10121i;

    public fnv(iqw iqw, Long l, Long l2, Long l3, Long l4, iqm iqm, String str, Boolean bool, iqx iqx) {
        this.f10113a = iqw;
        this.f10114b = l;
        this.f10115c = l2;
        this.f10116d = l3;
        this.f10117e = l4;
        this.f10118f = iqm;
        this.f10119g = str;
        this.f10120h = bool;
        this.f10121i = iqx;
    }

    public final String toString() {
        return String.format("StatsRecord:\n  elapsed: %d\n  current: %d\n  Primes version: %d\n  version name #: %d\n  customName: %s\n", new Object[]{this.f10114b, this.f10115c, this.f10116d, this.f10117e, this.f10119g});
    }
}
