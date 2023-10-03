package p000;

/* renamed from: czb */
/* compiled from: PG */
final /* synthetic */ class czb implements hpr {

    /* renamed from: a */
    private final cze f6084a;

    public czb(cze cze) {
        this.f6084a = cze;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        cze cze = this.f6084a;
        bip bip = (bip) obj;
        cze.f6094f.set(false);
        return cze.f6095g.getAndSet(false) ? czd.CANCELLED : czd.SUCCESS;
    }
}
