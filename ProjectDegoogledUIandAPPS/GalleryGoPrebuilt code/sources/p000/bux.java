package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bux */
/* compiled from: PG */
final /* synthetic */ class bux implements Consumer {

    /* renamed from: a */
    private final bvv f3650a;

    public bux(bvv bvv) {
        this.f3650a = bvv;
    }

    public final void accept(Object obj) {
        this.f3650a.mo2805a((Throwable) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
