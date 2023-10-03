package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dxr */
/* compiled from: PG */
final /* synthetic */ class dxr implements Consumer {

    /* renamed from: a */
    private final dxu f7580a;

    public dxr(dxu dxu) {
        this.f7580a = dxu;
    }

    public final void accept(Object obj) {
        dxu dxu = this.f7580a;
        ebd ebd = (ebd) obj;
        if ((ebd.equals(ebd.SLOW) || ebd.equals(ebd.STOPPED)) && dxu.f7601p) {
            dxu.f7601p = false;
            dxu.mo4550d().mo1422a(dxu.f7593h);
        }
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
