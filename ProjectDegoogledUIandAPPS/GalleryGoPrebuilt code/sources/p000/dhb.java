package p000;

import java.util.List;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dhb */
/* compiled from: PG */
final /* synthetic */ class dhb implements Consumer {

    /* renamed from: a */
    private final List f6532a;

    public dhb(List list) {
        this.f6532a = list;
    }

    public final void accept(Object obj) {
        this.f6532a.add((String) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
