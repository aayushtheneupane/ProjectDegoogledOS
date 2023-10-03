package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dqz */
/* compiled from: PG */
final /* synthetic */ class dqz implements Consumer {

    /* renamed from: a */
    private final hdt f7139a;

    public dqz(hdt hdt) {
        this.f7139a = hdt;
    }

    public final void accept(Object obj) {
        this.f7139a.mo7312a((ber) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
