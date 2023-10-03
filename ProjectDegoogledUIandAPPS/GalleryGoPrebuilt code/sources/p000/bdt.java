package p000;

/* renamed from: bdt */
/* compiled from: PG */
final class bdt {

    /* renamed from: a */
    public final Class f2099a;

    /* renamed from: b */
    public final arb f2100b;

    /* renamed from: c */
    private final Class f2101c;

    public bdt(Class cls, Class cls2, arb arb) {
        this.f2101c = cls;
        this.f2099a = cls2;
        this.f2100b = arb;
    }

    /* renamed from: a */
    public final boolean mo1843a(Class cls, Class cls2) {
        return this.f2101c.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f2099a);
    }
}
