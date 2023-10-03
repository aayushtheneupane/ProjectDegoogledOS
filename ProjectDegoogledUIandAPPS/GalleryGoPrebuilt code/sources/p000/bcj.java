package p000;

/* renamed from: bcj */
/* compiled from: PG */
final class bcj {

    /* renamed from: a */
    public final bci f2052a;

    /* renamed from: b */
    private final Class f2053b;

    /* renamed from: c */
    private final Class f2054c;

    public bcj(Class cls, Class cls2, bci bci) {
        this.f2053b = cls;
        this.f2054c = cls2;
        this.f2052a = bci;
    }

    /* renamed from: a */
    public final boolean mo1807a(Class cls, Class cls2) {
        return this.f2053b.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f2054c);
    }
}
