package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bus */
/* compiled from: PG */
final /* synthetic */ class bus implements Consumer {

    /* renamed from: a */
    private final bvv f3645a;

    public bus(bvv bvv) {
        this.f3645a = bvv;
    }

    public final void accept(Object obj) {
        this.f3645a.mo2805a((Throwable) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
