package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dpc */
/* compiled from: PG */
final /* synthetic */ class dpc implements Consumer {

    /* renamed from: a */
    private final dpt f6982a;

    public dpc(dpt dpt) {
        this.f6982a = dpt;
    }

    public final void accept(Object obj) {
        this.f6982a.mo4327a((Throwable) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
