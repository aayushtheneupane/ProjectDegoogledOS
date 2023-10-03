package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dow */
/* compiled from: PG */
final /* synthetic */ class dow implements Consumer {

    /* renamed from: a */
    private final dpt f6971a;

    public dow(dpt dpt) {
        this.f6971a = dpt;
    }

    public final void accept(Object obj) {
        dpt dpt = this.f6971a;
        Void voidR = (Void) obj;
        dpt.f7016h.mo6987a(dpt.f7017i.mo2828a(), dpt.f6999A);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
