package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cpn */
/* compiled from: PG */
final /* synthetic */ class cpn implements Consumer {

    /* renamed from: a */
    public static final Consumer f5369a = new cpn();

    private cpn() {
    }

    public final void accept(Object obj) {
        ((Runnable) obj).run();
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
