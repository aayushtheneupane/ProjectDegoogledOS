package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: eat */
/* compiled from: PG */
final /* synthetic */ class eat implements Consumer {

    /* renamed from: a */
    public static final Consumer f7797a = new eat();

    private eat() {
    }

    public final void accept(Object obj) {
        ((Runnable) obj).run();
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
