package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmi */
/* compiled from: PG */
final /* synthetic */ class dmi implements Consumer {

    /* renamed from: a */
    private final dnn f6838a;

    public dmi(dnn dnn) {
        this.f6838a = dnn;
    }

    public final void accept(Object obj) {
        dnn dnn = this.f6838a;
        css css = new css();
        ftr.m9611b(css);
        ftr.m9610a((C0147fh) css);
        hcl.m11210a(css, (cxi) obj);
        dnn.f6898g.mo3271a(new dmo(css), "info_sheet");
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
