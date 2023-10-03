package p000;

import java.util.concurrent.Executor;

/* renamed from: cht */
/* compiled from: PG */
final /* synthetic */ class cht implements icf {

    /* renamed from: a */
    private final chw f4421a;

    /* renamed from: b */
    private final cia f4422b;

    public cht(chw chw, cia cia) {
        this.f4421a = chw;
        this.f4422b = cia;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        chw chw = this.f4421a;
        cia cia = this.f4422b;
        cff cff = (cff) ((hsu) obj).get(cia.mo3109c());
        if (cff == null) {
            return ife.m12820a((Object) null);
        }
        cho cho = chw.f4430c;
        chv chv = new chv(chw, cia, cff);
        hlj a = hnb.m11765a("Process Face Thumbnail");
        try {
            ieh a2 = a.mo7548a(gte.m10771a(cho.f4412a.mo3995a(cff.mo3093c()), (icf) new chn(cho, cff, chv), (Executor) cho.f4414c));
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
