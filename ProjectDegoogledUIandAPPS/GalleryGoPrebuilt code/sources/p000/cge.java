package p000;

import java.util.concurrent.Executor;

/* renamed from: cge */
/* compiled from: PG */
final /* synthetic */ class cge implements ice {

    /* renamed from: a */
    private final cgi f4319a;

    public cge(cgi cgi) {
        this.f4319a = cgi;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        cgi cgi = this.f4319a;
        hlj a = hnb.m11765a("Face Clustering wipeout job");
        try {
            cgi.f4324b.mo3869a(22);
            hnm b = gte.m10778b(cgi.f4325c.mo7394a().mo6898b(cgf.f4320a, (Executor) idh.f13918a).mo6894a());
            cie cie = cgi.f4323a;
            cie.getClass();
            ieh a2 = b.mo7611a((ice) new cgg(cie), (Executor) idh.f13918a);
            cwn.m5509a(a2, "FaceClusteringWipeOutJob: Face clustering wipeout failed", new Object[0]);
            cgi.f4324b.mo3870a(a2, 23, 24);
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
