package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dph */
/* compiled from: PG */
final /* synthetic */ class dph implements Consumer {

    /* renamed from: a */
    public static final Consumer f6987a = new dph();

    private dph() {
    }

    public final void accept(Object obj) {
        cwn.m5511a((Throwable) obj, "Unable to initialize image.", new Object[0]);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
