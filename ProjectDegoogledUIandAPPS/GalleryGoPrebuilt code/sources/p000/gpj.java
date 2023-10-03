package p000;

/* renamed from: gpj */
/* compiled from: PG */
final /* synthetic */ class gpj implements Runnable {

    /* renamed from: a */
    private final gpt f11787a;

    /* renamed from: b */
    private final iei f11788b;

    public gpj(gpt gpt, iei iei) {
        this.f11787a = gpt;
        this.f11788b = iei;
    }

    public final void run() {
        this.f11787a.execute(new gpo(this.f11788b));
    }
}
