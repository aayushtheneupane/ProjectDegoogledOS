package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmn */
/* compiled from: PG */
final /* synthetic */ class dmn implements Consumer {

    /* renamed from: a */
    private final dnn f6843a;

    public dmn(dnn dnn) {
        this.f6843a = dnn;
    }

    public final void accept(Object obj) {
        this.f6843a.mo4273b((cxi) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
