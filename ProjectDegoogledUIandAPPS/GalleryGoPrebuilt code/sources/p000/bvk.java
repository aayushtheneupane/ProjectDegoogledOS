package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bvk */
/* compiled from: PG */
final /* synthetic */ class bvk implements Consumer {

    /* renamed from: a */
    private final bvv f3676a;

    public bvk(bvv bvv) {
        this.f3676a = bvv;
    }

    public final void accept(Object obj) {
        this.f3676a.mo2805a((Throwable) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
