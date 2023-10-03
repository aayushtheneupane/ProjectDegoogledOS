package p000;

import java.util.concurrent.Executor;

/* renamed from: cfn */
/* compiled from: PG */
final /* synthetic */ class cfn implements ice {

    /* renamed from: a */
    private final cfu f4271a;

    /* renamed from: b */
    private final hso f4272b;

    /* renamed from: c */
    private final cue f4273c;

    public cfn(cfu cfu, hso hso, cue cue) {
        this.f4271a = cfu;
        this.f4272b = hso;
        this.f4273c = cue;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        cfu cfu = this.f4271a;
        hso hso = this.f4272b;
        cue cue = this.f4273c;
        hlj a = hnb.m11765a("Process Batch");
        try {
            ieh a2 = a.mo7548a(((cfq) cfu.f4282a.mo2097a()).mo2103a(hso).mo2104a(cfu.f4283b.mo8999a("FaceClusteringJob")).mo2105a().mo2109c());
            if (a != null) {
                a.close();
            }
            return gte.m10769a(a2).mo7611a((ice) new cfo(cfu, cue), (Executor) cfu.f4285d);
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
