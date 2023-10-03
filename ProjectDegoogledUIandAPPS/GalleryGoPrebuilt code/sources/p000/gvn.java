package p000;

/* renamed from: gvn */
/* compiled from: PG */
final /* synthetic */ class gvn implements Runnable {

    /* renamed from: a */
    private final gvu f12142a;

    /* renamed from: b */
    private final gva f12143b;

    /* renamed from: c */
    private final guo f12144c;

    public gvn(gvu gvu, gva gva, guo guo) {
        this.f12142a = gvu;
        this.f12143b = gva;
        this.f12144c = guo;
    }

    public final void run() {
        gvu gvu = this.f12142a;
        gvu.f12156b.execute(new gvq(gvu, this.f12143b, this.f12144c));
    }
}
