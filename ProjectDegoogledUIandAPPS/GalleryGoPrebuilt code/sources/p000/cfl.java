package p000;

/* renamed from: cfl */
/* compiled from: PG */
final /* synthetic */ class cfl implements ice {

    /* renamed from: a */
    private final cfu f4267a;

    /* renamed from: b */
    private final cue f4268b;

    public cfl(cfu cfu, cue cue) {
        this.f4267a = cfu;
        this.f4268b = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        cfu cfu = this.f4267a;
        cue cue = this.f4268b;
        hlj a = hnb.m11765a("Face Clustering job");
        try {
            cfu.f4286e.mo3869a(14);
            ieh a2 = cfu.mo3130a(cue);
            cwn.m5509a(a2, "FaceClusteringJob: Face clustering failed", new Object[0]);
            cfu.f4286e.mo3870a(a2, 15, 16);
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
