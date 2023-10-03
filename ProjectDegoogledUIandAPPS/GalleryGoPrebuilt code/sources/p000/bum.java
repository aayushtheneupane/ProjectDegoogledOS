package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bum */
/* compiled from: PG */
final /* synthetic */ class bum implements Consumer {

    /* renamed from: a */
    private final bvv f3639a;

    public bum(bvv bvv) {
        this.f3639a = bvv;
    }

    public final void accept(Object obj) {
        bvv bvv = this.f3639a;
        if (!((Boolean) obj).booleanValue()) {
            bvv.mo2805a((Throwable) new bvu());
        } else {
            bvv.mo2807b();
        }
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
