package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bxi */
/* compiled from: PG */
final /* synthetic */ class bxi implements Consumer {

    /* renamed from: a */
    private final bxl f3829a;

    public bxi(bxl bxl) {
        this.f3829a = bxl;
    }

    public final void accept(Object obj) {
        this.f3829a.f3839g.mo2888b((bxp) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
