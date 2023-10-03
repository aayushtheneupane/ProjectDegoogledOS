package p000;

/* renamed from: cow */
/* compiled from: PG */
final /* synthetic */ class cow implements ice {

    /* renamed from: a */
    private final cpd f5329a;

    /* renamed from: b */
    private final cue f5330b;

    public cow(cpd cpd, cue cue) {
        this.f5329a = cpd;
        this.f5330b = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        cpd cpd = this.f5329a;
        cue cue = this.f5330b;
        hlj a = hnb.m11765a("Thumbnailer job");
        try {
            cpd.f5346e.mo3869a(5);
            ieh a2 = cpd.f5344c.mo3847a(new cox(cpd), new coy(cpd), cue);
            cwn.m5509a(a2, "ThumbnailerJob: job failed", new Object[0]);
            cpd.f5346e.mo3870a(a2, 6, 7);
            ieh a3 = a.mo7548a(a2);
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
