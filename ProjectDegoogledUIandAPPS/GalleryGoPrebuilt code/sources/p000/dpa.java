package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dpa */
/* compiled from: PG */
final /* synthetic */ class dpa implements Consumer {

    /* renamed from: a */
    private final dpt f6980a;

    public dpa(dpt dpt) {
        this.f6980a = dpt;
    }

    public final void accept(Object obj) {
        this.f6980a.mo4327a((Throwable) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
