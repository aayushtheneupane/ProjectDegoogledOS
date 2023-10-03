package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dlb */
/* compiled from: PG */
final /* synthetic */ class dlb implements Consumer {

    /* renamed from: a */
    private final iir f6765a;

    public dlb(iir iir) {
        this.f6765a = iir;
    }

    public final void accept(Object obj) {
        iir iir = this.f6765a;
        String str = (String) obj;
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        ceq ceq = (ceq) iir.f14318b;
        ceq ceq2 = ceq.f4197g;
        str.getClass();
        ceq.f4199a |= 8;
        ceq.f4204f = str;
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
