package p000;

import java.util.List;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dbv */
/* compiled from: PG */
final /* synthetic */ class dbv implements Consumer {

    /* renamed from: a */
    private final List f6219a;

    public dbv(List list) {
        this.f6219a = list;
    }

    public final void accept(Object obj) {
        this.f6219a.add((cxi) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
