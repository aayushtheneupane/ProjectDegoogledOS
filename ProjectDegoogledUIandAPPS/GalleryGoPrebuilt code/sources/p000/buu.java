package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: buu */
/* compiled from: PG */
final /* synthetic */ class buu implements Consumer {

    /* renamed from: a */
    private final bvv f3647a;

    public buu(bvv bvv) {
        this.f3647a = bvv;
    }

    public final void accept(Object obj) {
        this.f3647a.mo2805a((Throwable) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
