package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmv */
/* compiled from: PG */
final /* synthetic */ class dmv implements Consumer {

    /* renamed from: a */
    private final dnn f6851a;

    public dmv(dnn dnn) {
        this.f6851a = dnn;
    }

    public final void accept(Object obj) {
        this.f6851a.mo4275c((cxi) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
