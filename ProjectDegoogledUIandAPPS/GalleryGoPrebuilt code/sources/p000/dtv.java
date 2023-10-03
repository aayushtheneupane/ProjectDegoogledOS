package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dtv */
/* compiled from: PG */
final /* synthetic */ class dtv implements Consumer {

    /* renamed from: a */
    private final boolean f7367a;

    public dtv(boolean z) {
        this.f7367a = z;
    }

    public final void accept(Object obj) {
        ((dlr) obj).mo4223a(this.f7367a);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
