package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cpk */
/* compiled from: PG */
final /* synthetic */ class cpk implements Consumer {

    /* renamed from: a */
    private final cpp f5365a;

    public cpk(cpp cpp) {
        this.f5365a = cpp;
    }

    public final void accept(Object obj) {
        cpp cpp = this.f5365a;
        if (((Boolean) obj).booleanValue()) {
            cpp.f5373c.ifPresent(cpn.f5369a);
        }
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
