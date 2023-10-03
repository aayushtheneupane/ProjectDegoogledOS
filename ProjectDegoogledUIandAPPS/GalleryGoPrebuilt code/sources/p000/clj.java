package p000;

import java.util.List;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: clj */
/* compiled from: PG */
final /* synthetic */ class clj implements Consumer {

    /* renamed from: a */
    private final clr f4613a;

    public clj(clr clr) {
        this.f4613a = clr;
    }

    public final void accept(Object obj) {
        clr clr = this.f4613a;
        List list = (List) obj;
        if (list.isEmpty()) {
            cwn.m5514b("FolderCreationFragmentPeer: Unable to add items into folder.", new Object[0]);
            clr.f4627c.mo3274e();
            return;
        }
        cnh cnh = clr.f4627c;
        cxe cxe = ((cxi) ((imh) list.get(0)).mo8995a(cxi.f5908x, clr.f4637m)).f5919k;
        if (cxe == null) {
            cxe = cxe.f5893f;
        }
        cnh.mo3270a(cjw.m4432a(cxe));
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
