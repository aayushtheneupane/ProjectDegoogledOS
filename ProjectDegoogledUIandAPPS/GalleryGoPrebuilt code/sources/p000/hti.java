package p000;

/* renamed from: hti */
/* compiled from: PG */
final class hti extends htq {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final /* synthetic */ htk f13380a;

    public /* synthetic */ hti(htk htk) {
        this.f13380a = htk;
    }

    public final boolean contains(Object obj) {
        if (obj instanceof hun) {
            hun hun = (hun) obj;
            if (hun.mo8080b() <= 0 || this.f13380a.mo7769a(hun.mo8079a()) != hun.mo8080b()) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo7968a(int i) {
        return this.f13380a.mo7987a(i);
    }

    public final int hashCode() {
        return this.f13380a.hashCode();
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return this.f13380a.mo7885h();
    }

    public final int size() {
        return this.f13380a.mo7990i().size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new htj(this.f13380a);
    }
}
