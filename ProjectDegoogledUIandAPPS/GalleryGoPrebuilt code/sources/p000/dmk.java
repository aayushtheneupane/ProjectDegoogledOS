package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmk */
/* compiled from: PG */
final /* synthetic */ class dmk implements Consumer {

    /* renamed from: a */
    private final dnn f6840a;

    public dmk(dnn dnn) {
        this.f6840a = dnn;
    }

    public final void accept(Object obj) {
        this.f6840a.mo4275c((cxi) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
