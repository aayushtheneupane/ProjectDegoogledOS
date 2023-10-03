package p000;

import java.util.concurrent.Executor;

/* renamed from: cgz */
/* compiled from: PG */
final /* synthetic */ class cgz implements cvl {

    /* renamed from: a */
    private final chf f4369a;

    /* renamed from: b */
    private final cgv f4370b;

    /* renamed from: c */
    private final che f4371c;

    public cgz(chf chf, cgv cgv, che che) {
        this.f4369a = chf;
        this.f4370b = cgv;
        this.f4371c = che;
    }

    /* renamed from: a */
    public final ieh mo2590a(Object obj) {
        chf chf = this.f4369a;
        cgv cgv = this.f4370b;
        che che = this.f4371c;
        cff cff = (cff) obj;
        hlj a = hnb.m11765a("Embed single face");
        try {
            Object[] objArr = {cff.mo3091a().get(), Long.valueOf(cff.mo3093c())};
            ieh a2 = gte.m10771a(chf.f4390a.mo3995a(cff.mo3093c()), (icf) new cha(chf, cgv, che, cff), (Executor) chf.f4395f);
            cwn.m5509a(a2, "FaceEmbeddingJob: Embedding failed for face (id=%d)", cff.mo3091a().get());
            ieh a3 = a.mo7548a(gte.m10773a(a2, Throwable.class, (icf) new chb(chf, cff), (Executor) chf.f4395f));
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
