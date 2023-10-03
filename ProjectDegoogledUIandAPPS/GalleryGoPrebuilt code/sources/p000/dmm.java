package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmm */
/* compiled from: PG */
final /* synthetic */ class dmm implements Consumer {

    /* renamed from: a */
    private final dnn f6842a;

    public dmm(dnn dnn) {
        this.f6842a = dnn;
    }

    public final void accept(Object obj) {
        this.f6842a.mo4273b((cxi) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
