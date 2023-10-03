package p000;

import java.util.List;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dfe */
/* compiled from: PG */
final /* synthetic */ class dfe implements Consumer {

    /* renamed from: a */
    private final List f6434a;

    public dfe(List list) {
        this.f6434a = list;
    }

    public final void accept(Object obj) {
        this.f6434a.add((cyg) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
