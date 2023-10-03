package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmu */
/* compiled from: PG */
final /* synthetic */ class dmu implements Consumer {

    /* renamed from: a */
    private final dnn f6850a;

    public dmu(dnn dnn) {
        this.f6850a = dnn;
    }

    public final void accept(Object obj) {
        this.f6850a.mo4276d((cxi) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
