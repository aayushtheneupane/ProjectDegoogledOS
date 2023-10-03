package p000;

/* renamed from: gvm */
/* compiled from: PG */
final /* synthetic */ class gvm implements Runnable {

    /* renamed from: a */
    private final gvu f12139a;

    /* renamed from: b */
    private final gva f12140b;

    /* renamed from: c */
    private final guk f12141c;

    public gvm(gvu gvu, gva gva, guk guk) {
        this.f12139a = gvu;
        this.f12140b = gva;
        this.f12141c = guk;
    }

    public final void run() {
        gvu gvu = this.f12139a;
        gvu.f12156b.execute(new gvr(gvu, this.f12140b, this.f12141c));
    }
}
