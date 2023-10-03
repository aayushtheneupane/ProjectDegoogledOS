package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dpo */
/* compiled from: PG */
final /* synthetic */ class dpo implements Consumer {

    /* renamed from: a */
    public static final Consumer f6994a = new dpo();

    private dpo() {
    }

    public final void accept(Object obj) {
        cwn.m5511a((Throwable) obj, "Unable to initialize replacement image.", new Object[0]);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
