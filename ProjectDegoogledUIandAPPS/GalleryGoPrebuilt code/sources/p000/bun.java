package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bun */
/* compiled from: PG */
final /* synthetic */ class bun implements Consumer {

    /* renamed from: a */
    private final bvv f3640a;

    public bun(bvv bvv) {
        this.f3640a = bvv;
    }

    public final void accept(Object obj) {
        bvv bvv = this.f3640a;
        Void voidR = (Void) obj;
        bvv.f3736r.mo6987a(bvv.f3733o.mo2828a(), bvv.f3707T);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
