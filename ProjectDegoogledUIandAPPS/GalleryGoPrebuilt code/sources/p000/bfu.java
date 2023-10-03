package p000;

/* renamed from: bfu */
/* compiled from: PG */
final class bfu implements C0306lc {

    /* renamed from: a */
    private final bft f2221a;

    /* renamed from: b */
    private final bfw f2222b;

    /* renamed from: c */
    private final C0306lc f2223c;

    public bfu(C0306lc lcVar, bft bft, bfw bfw) {
        this.f2223c = lcVar;
        this.f2221a = bft;
        this.f2222b = bfw;
    }

    /* renamed from: a */
    public final Object mo1971a() {
        Object a = this.f2223c.mo1971a();
        if (a == null) {
            a = this.f2221a.mo1583a();
        }
        if (a instanceof bfv) {
            ((bfv) a).mo1573af().f2225a = false;
        }
        return a;
    }

    /* renamed from: a */
    public final boolean mo1972a(Object obj) {
        if (obj instanceof bfv) {
            ((bfv) obj).mo1573af().f2225a = true;
        }
        this.f2222b.mo1970a(obj);
        return this.f2223c.mo1972a(obj);
    }
}
