package p000;

/* renamed from: cvi */
/* compiled from: PG */
final /* synthetic */ class cvi implements ice {

    /* renamed from: a */
    private final cvm f5739a;

    /* renamed from: b */
    private final Runnable f5740b;

    /* renamed from: c */
    private final cvk f5741c;

    /* renamed from: d */
    private final cvl f5742d;

    /* renamed from: e */
    private final cue f5743e;

    public cvi(cvm cvm, Runnable runnable, cvk cvk, cvl cvl, cue cue) {
        this.f5739a = cvm;
        this.f5740b = runnable;
        this.f5741c = cvk;
        this.f5742d = cvl;
        this.f5743e = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        cvm cvm = this.f5739a;
        Runnable runnable = this.f5740b;
        cvk cvk = this.f5741c;
        cvl cvl = this.f5742d;
        cue cue = this.f5743e;
        runnable.run();
        return cvm.mo3848a(cvk, cvl, cue, runnable);
    }
}
