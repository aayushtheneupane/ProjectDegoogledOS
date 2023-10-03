package p000;

import java.util.Set;
import java.util.concurrent.Callable;

/* renamed from: hag */
/* compiled from: PG */
public final /* synthetic */ class hag implements icf {

    /* renamed from: a */
    private final iek f12403a;

    /* renamed from: b */
    private final Set f12404b;

    /* renamed from: c */
    private final hfq f12405c;

    public hag(iek iek, Set set, hfq hfq) {
        this.f12403a = iek;
        this.f12404b = set;
        this.f12405c = hfq;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        return this.f12403a.mo5933a((Callable) new hal(this.f12404b, (String) obj, this.f12405c));
    }
}
