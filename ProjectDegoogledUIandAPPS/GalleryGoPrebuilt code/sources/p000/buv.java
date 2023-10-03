package p000;

import java.util.List;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: buv */
/* compiled from: PG */
final /* synthetic */ class buv implements Consumer {

    /* renamed from: a */
    private final bvv f3648a;

    public buv(bvv bvv) {
        this.f3648a = bvv;
    }

    public final void accept(Object obj) {
        bvv bvv = this.f3648a;
        List list = (List) obj;
        cbp a = bvv.f3697J.mo2635n();
        a.f4019c = list;
        a.f4018b.mo7129a(list);
        a.mo2999a(car.ORIGINAL);
        bvv.f3726h.mo2884a();
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
