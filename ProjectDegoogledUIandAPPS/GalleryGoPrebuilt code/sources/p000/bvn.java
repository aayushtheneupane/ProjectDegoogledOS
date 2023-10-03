package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bvn */
/* compiled from: PG */
final /* synthetic */ class bvn implements Consumer {

    /* renamed from: a */
    private final bvv f3679a;

    public bvn(bvv bvv) {
        this.f3679a = bvv;
    }

    public final void accept(Object obj) {
        bvv bvv = this.f3679a;
        Void voidR = (Void) obj;
        bvv.f3698K.mo2820c();
        bvv.f3698K.mo2817a();
        bvv.f3736r.mo6987a(bvv.f3733o.mo2828a(), bvv.f3704Q);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
