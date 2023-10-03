package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dff */
/* compiled from: PG */
final /* synthetic */ class dff implements Consumer {

    /* renamed from: a */
    private final cyf f6435a;

    public dff(cyf cyf) {
        this.f6435a = cyf;
    }

    public final void accept(Object obj) {
        this.f6435a.mo3981f((String) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
