package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: egm */
/* compiled from: PG */
final /* synthetic */ class egm implements Consumer {

    /* renamed from: a */
    private final Throwable f8207a;

    public egm(Throwable th) {
        this.f8207a = th;
    }

    public final void accept(Object obj) {
        ((iev) obj).mo6952a(this.f8207a);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
