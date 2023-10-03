package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bup */
/* compiled from: PG */
final /* synthetic */ class bup implements Consumer {

    /* renamed from: a */
    private final bvv f3642a;

    public bup(bvv bvv) {
        this.f3642a = bvv;
    }

    public final void accept(Object obj) {
        bvv bvv = this.f3642a;
        Void voidR = (Void) obj;
        bvv.f3736r.mo6987a(bvv.f3733o.mo2830a(((byc) bvv.f3727i).f3888c), bvv.f3705R);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
