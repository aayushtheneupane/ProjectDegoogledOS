package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: egk */
/* compiled from: PG */
final /* synthetic */ class egk implements Consumer {

    /* renamed from: a */
    private final boolean f8204a;

    public egk(boolean z) {
        this.f8204a = z;
    }

    public final void accept(Object obj) {
        ((iev) obj).mo8346b((Object) Boolean.valueOf(this.f8204a));
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
