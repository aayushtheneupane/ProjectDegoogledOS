package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dfg */
/* compiled from: PG */
final /* synthetic */ class dfg implements Consumer {

    /* renamed from: a */
    private final cyf f6436a;

    public dfg(cyf cyf) {
        this.f6436a = cyf;
    }

    public final void accept(Object obj) {
        this.f6436a.mo3956a(((Long) obj).longValue());
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
