package p000;

import java.util.concurrent.Executor;
import p003j$.util.Optional;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmj */
/* compiled from: PG */
final /* synthetic */ class dmj implements Consumer {

    /* renamed from: a */
    private final dnn f6839a;

    public dmj(dnn dnn) {
        this.f6839a = dnn;
    }

    public final void accept(Object obj) {
        dnn dnn = this.f6839a;
        cxi cxi = (cxi) obj;
        dnn.f6883M = true;
        dnn.f6884N = dnn.f6878H.f6934a.mo4634a().map(dnz.f6927a);
        dnn.f6885O = Optional.m16285of(cxi);
        if (cyc.m5645a((cxi) dnn.f6884N.get(), cxi)) {
            dnn.f6883M = false;
            dnn.f6884N = Optional.empty();
            dnn.f6885O = Optional.empty();
            return;
        }
        grx grx = dnn.f6908q;
        bnb bnb = dnn.f6872B;
        cxi cxi2 = (cxi) dnn.f6884N.get();
        hlj a = hnb.m11765a("Set burst primary");
        try {
            ieh a2 = a.mo7548a(gte.m10771a(bnb.mo2596a(cxi2, false), (icf) new bmz(bnb, cxi), (Executor) idh.f13918a));
            if (a != null) {
                a.close();
            }
            grx.mo6987a(grw.m10691f(a2), dnn.f6873C);
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
