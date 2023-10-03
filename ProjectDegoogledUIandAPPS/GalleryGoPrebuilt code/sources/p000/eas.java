package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: eas */
/* compiled from: PG */
public final /* synthetic */ class eas implements Consumer {

    /* renamed from: a */
    public static final Consumer f7796a = new eas();

    private eas() {
    }

    public final void accept(Object obj) {
        ((Runnable) obj).run();
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
