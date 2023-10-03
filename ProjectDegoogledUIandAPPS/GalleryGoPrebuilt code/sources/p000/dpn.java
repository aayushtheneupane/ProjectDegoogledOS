package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dpn */
/* compiled from: PG */
final /* synthetic */ class dpn implements Consumer {

    /* renamed from: a */
    private final dpt f6993a;

    public dpn(dpt dpt) {
        this.f6993a = dpt;
    }

    public final void accept(Object obj) {
        dpt dpt = this.f6993a;
        Void voidR = (Void) obj;
        dpt.f7016h.mo6987a(dpt.f7017i.mo2828a(), dpt.f7002D);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
