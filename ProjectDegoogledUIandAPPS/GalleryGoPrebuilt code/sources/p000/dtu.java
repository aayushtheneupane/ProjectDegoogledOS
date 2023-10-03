package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dtu */
/* compiled from: PG */
final /* synthetic */ class dtu implements Consumer {

    /* renamed from: a */
    private final int f7366a;

    public dtu(int i) {
        this.f7366a = i;
    }

    public final void accept(Object obj) {
        ((dlr) obj).mo4221a(this.f7366a);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
