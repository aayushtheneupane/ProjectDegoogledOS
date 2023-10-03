package p000;

/* renamed from: huz */
/* compiled from: PG */
final class huz extends hto {

    /* renamed from: a */
    private final transient hsu f13447a;

    /* renamed from: b */
    private final transient hso f13448b;

    public huz(hsu hsu, hso hso) {
        this.f13447a = hsu;
        this.f13448b = hso;
    }

    /* renamed from: g */
    public final hso mo7884g() {
        return this.f13448b;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return true;
    }

    public final int size() {
        return ((hvb) this.f13447a).f13455b;
    }

    public final boolean contains(Object obj) {
        return this.f13447a.get(obj) != null;
    }

    /* renamed from: a */
    public final int mo7875a(Object[] objArr, int i) {
        return this.f13448b.mo7875a(objArr, i);
    }

    /* renamed from: a */
    public final hvr iterator() {
        return this.f13448b.listIterator();
    }
}
