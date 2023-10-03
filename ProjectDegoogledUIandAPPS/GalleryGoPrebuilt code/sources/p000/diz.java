package p000;

import java.util.concurrent.atomic.AtomicBoolean;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: diz */
/* compiled from: PG */
final /* synthetic */ class diz implements Consumer {

    /* renamed from: a */
    private final djd f6641a;

    /* renamed from: b */
    private final AtomicBoolean f6642b;

    public diz(djd djd, AtomicBoolean atomicBoolean) {
        this.f6641a = djd;
        this.f6642b = atomicBoolean;
    }

    public final void accept(Object obj) {
        djd djd = this.f6641a;
        String str = (String) obj;
        this.f6642b.set(true);
        djd.f6659g.mo3869a(27);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
