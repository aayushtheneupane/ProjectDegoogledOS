package p000;

import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dzg */
/* compiled from: PG */
final /* synthetic */ class dzg implements ice {

    /* renamed from: a */
    private final dzl f7710a;

    public dzg(dzl dzl) {
        this.f7710a = dzl;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        dzl dzl = this.f7710a;
        if (!dzl.f7721c.mo3175a()) {
            return ife.m12820a((Object) Optional.empty());
        }
        if (!dzl.f7722d.mo3175a()) {
            return ife.m12820a((Object) dzl.m6969a(dzl.f7719a));
        }
        hlj a = hnb.m11765a("Fetch Photos promo");
        try {
            dzh dzh = new dzh(dzl);
            ieh a2 = a.mo7548a(ife.m12816a(hmq.m11743a((ice) dzh), (Executor) dzl.f7720b));
            if (a == null) {
                return a2;
            }
            a.close();
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
