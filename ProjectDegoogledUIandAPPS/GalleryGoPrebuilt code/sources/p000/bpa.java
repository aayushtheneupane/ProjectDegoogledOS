package p000;

/* renamed from: bpa */
/* compiled from: PG */
final /* synthetic */ class bpa implements ice {

    /* renamed from: a */
    private final bph f3285a;

    /* renamed from: b */
    private final cue f3286b;

    public bpa(bph bph, cue cue) {
        this.f3285a = bph;
        this.f3286b = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        bph bph = this.f3285a;
        cue cue = this.f3286b;
        hlj a = hnb.m11765a("Image compression job");
        try {
            bph.f3298c.mo3869a(35);
            ieh a2 = bph.f3297b.mo3847a(new bpb(bph), new bpc(bph), cue);
            cwn.m5509a(a2, "ImageCompressionJob: job failed", new Object[0]);
            bph.f3298c.mo3870a(a2, 36, 37);
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
