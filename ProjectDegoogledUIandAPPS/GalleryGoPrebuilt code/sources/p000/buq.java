package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: buq */
/* compiled from: PG */
final /* synthetic */ class buq implements Consumer {

    /* renamed from: a */
    private final bvv f3643a;

    public buq(bvv bvv) {
        this.f3643a = bvv;
    }

    public final void accept(Object obj) {
        this.f3643a.mo2805a((Throwable) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
