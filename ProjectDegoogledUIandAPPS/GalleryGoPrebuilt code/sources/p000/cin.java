package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cin */
/* compiled from: PG */
final /* synthetic */ class cin implements Consumer {

    /* renamed from: a */
    private final hgn f4467a;

    public cin(hgn hgn) {
        this.f4467a = hgn;
    }

    public final void accept(Object obj) {
        hgn hgn = this.f4467a;
        hgn.mo7409a(" , ? ");
        hgn.mo7411b((String) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
