package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dqs */
/* compiled from: PG */
final /* synthetic */ class dqs implements Consumer {

    /* renamed from: a */
    private final dqx f7128a;

    public dqs(dqx dqx) {
        this.f7128a = dqx;
    }

    public final void accept(Object obj) {
        this.f7128a.mo4357d().ifPresent(new dqt((Integer) obj));
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
