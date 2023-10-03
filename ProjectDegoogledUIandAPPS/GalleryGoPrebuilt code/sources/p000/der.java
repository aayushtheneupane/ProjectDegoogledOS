package p000;

import java.util.concurrent.Executor;

/* renamed from: der */
/* compiled from: PG */
final /* synthetic */ class der implements ice {

    /* renamed from: a */
    private final det f6406a;

    public der(det det) {
        this.f6406a = det;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        det det = this.f6406a;
        hlj a = hnb.m11765a("sync");
        try {
            ieh d = ((deu) det.f6408a.mo2097a()).mo2110a(det.f6409b.mo8999a("MediaStoreSyncer")).mo2111a().mo2115d();
            cwn.m5509a(d, "Syncer: MediaStore sync failed", new Object[0]);
            ieh a2 = a.mo7548a(gte.m10770a(d, (hpr) new des(det), (Executor) det.f6415h));
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
