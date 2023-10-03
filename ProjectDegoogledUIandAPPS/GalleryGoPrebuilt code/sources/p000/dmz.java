package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmz */
/* compiled from: PG */
final /* synthetic */ class dmz implements Consumer {

    /* renamed from: a */
    private final dnn f6855a;

    public dmz(dnn dnn) {
        this.f6855a = dnn;
    }

    public final void accept(Object obj) {
        dnn dnn = this.f6855a;
        iir g = bqc.f3349d.mo8793g();
        cyd b = cyc.m5648b((cxi) obj);
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        bqc bqc = (bqc) g.f14318b;
        b.getClass();
        bqc.mo2663a();
        bqc.f3352b.add(b);
        bqd.m3356a((bqc) g.mo8770g()).mo5429b(dnn.f6897f.mo5659r(), "delete_fragment");
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
