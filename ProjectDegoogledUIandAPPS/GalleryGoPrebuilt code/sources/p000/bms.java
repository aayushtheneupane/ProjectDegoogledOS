package p000;

import java.util.concurrent.atomic.AtomicBoolean;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bms */
/* compiled from: PG */
final /* synthetic */ class bms implements Consumer {

    /* renamed from: a */
    private final AtomicBoolean f3159a;

    public bms(AtomicBoolean atomicBoolean) {
        this.f3159a = atomicBoolean;
    }

    public final void accept(Object obj) {
        String str = (String) obj;
        this.f3159a.set(true);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
