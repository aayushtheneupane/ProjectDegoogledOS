package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmt */
/* compiled from: PG */
final /* synthetic */ class dmt implements Consumer {

    /* renamed from: a */
    private final dnn f6849a;

    public dmt(dnn dnn) {
        this.f6849a = dnn;
    }

    public final void accept(Object obj) {
        this.f6849a.mo4277e((cxi) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
