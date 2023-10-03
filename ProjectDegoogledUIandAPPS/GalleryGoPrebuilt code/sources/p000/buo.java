package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: buo */
/* compiled from: PG */
final /* synthetic */ class buo implements Consumer {

    /* renamed from: a */
    private final bvv f3641a;

    public buo(bvv bvv) {
        this.f3641a = bvv;
    }

    public final void accept(Object obj) {
        this.f3641a.mo2805a((Throwable) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
