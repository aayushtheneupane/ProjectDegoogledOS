package p000;

/* renamed from: chq */
/* compiled from: PG */
final /* synthetic */ class chq implements ice {

    /* renamed from: a */
    private final chw f4417a;

    /* renamed from: b */
    private final cue f4418b;

    public chq(chw chw, cue cue) {
        this.f4417a = chw;
        this.f4418b = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        chw chw = this.f4417a;
        cue cue = this.f4418b;
        hlj a = hnb.m11765a("Face Thumbnailing job");
        try {
            chw.f4434g.mo3869a(17);
            ieh a2 = chw.f4432e.mo3847a(new chr(chw), new chs(chw), cue);
            cwn.m5509a(a2, "FaceThumbnailingJob: Thumbnailing failed", new Object[0]);
            chw.f4434g.mo3870a(a2, 18, 19);
            ieh a3 = a.mo7548a(chw.f4433f.mo2553a(a2, chw.f4431d));
            if (a != null) {
                a.close();
            }
            return a3;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
