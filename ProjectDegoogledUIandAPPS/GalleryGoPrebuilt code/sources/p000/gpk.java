package p000;

/* renamed from: gpk */
/* compiled from: PG */
final /* synthetic */ class gpk implements Runnable {

    /* renamed from: a */
    private final gpt f11789a;

    /* renamed from: b */
    private final iei f11790b;

    public gpk(gpt gpt, iei iei) {
        this.f11789a = gpt;
        this.f11790b = iei;
    }

    public final void run() {
        this.f11789a.execute(new gpn(this.f11790b));
    }
}
