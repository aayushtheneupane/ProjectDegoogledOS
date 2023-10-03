package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cez */
/* compiled from: PG */
final /* synthetic */ class cez implements Consumer {

    /* renamed from: a */
    private final iir f4219a;

    public cez(iir iir) {
        this.f4219a = iir;
    }

    public final void accept(Object obj) {
        iir iir = this.f4219a;
        long longValue = ((Integer) obj).longValue();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        cxi cxi = (cxi) iir.f14318b;
        cxi cxi2 = cxi.f5908x;
        cxi.f5909a |= 32;
        cxi.f5915g = longValue;
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
