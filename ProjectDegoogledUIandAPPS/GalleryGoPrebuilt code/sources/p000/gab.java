package p000;

/* renamed from: gab */
/* compiled from: PG */
public final /* synthetic */ class gab implements Runnable {

    /* renamed from: a */
    private final iei f10773a;

    /* renamed from: b */
    private final gbr f10774b;

    public gab(iei iei, gbr gbr) {
        this.f10773a = iei;
        this.f10774b = gbr;
    }

    public final void run() {
        iei iei = this.f10773a;
        gbr gbr = this.f10774b;
        if (iei.isCancelled()) {
            gbr.f10847a.cancel();
        }
    }
}
