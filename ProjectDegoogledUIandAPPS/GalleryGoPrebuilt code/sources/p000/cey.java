package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cey */
/* compiled from: PG */
final /* synthetic */ class cey implements Consumer {

    /* renamed from: a */
    private final iir f4218a;

    public cey(iir iir) {
        this.f4218a = iir;
    }

    public final void accept(Object obj) {
        iir iir = this.f4218a;
        long longValue = ((Long) obj).longValue();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        cxi cxi = (cxi) iir.f14318b;
        cxi cxi2 = cxi.f5908x;
        cxi.f5909a |= 2;
        cxi.f5911c = longValue;
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
