package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dkx */
/* compiled from: PG */
final /* synthetic */ class dkx implements Consumer {

    /* renamed from: a */
    private final iir f6753a;

    public dkx(iir iir) {
        this.f6753a = iir;
    }

    public final void accept(Object obj) {
        iir iir = this.f6753a;
        String str = (String) obj;
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        ceq ceq = (ceq) iir.f14318b;
        ceq ceq2 = ceq.f4197g;
        str.getClass();
        ceq.f4199a |= 1;
        ceq.f4200b = str;
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
