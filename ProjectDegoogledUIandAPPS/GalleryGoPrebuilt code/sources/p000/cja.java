package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cja */
/* compiled from: PG */
public final /* synthetic */ class cja implements Consumer {

    /* renamed from: a */
    private final hgn f4483a;

    public cja(hgn hgn) {
        this.f4483a = hgn;
    }

    public final void accept(Object obj) {
        hgn hgn = this.f4483a;
        hgn.mo7409a(" , ? ");
        hgn.mo7411b((String) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
