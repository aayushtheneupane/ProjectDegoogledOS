package p000;

import java.util.Set;
import java.util.concurrent.Callable;

/* renamed from: gyn */
/* compiled from: PG */
public final /* synthetic */ class gyn implements icf {

    /* renamed from: a */
    private final iek f12310a;

    /* renamed from: b */
    private final Set f12311b;

    /* renamed from: c */
    private final hfq f12312c;

    public gyn(iek iek, Set set, hfq hfq) {
        this.f12310a = iek;
        this.f12311b = set;
        this.f12312c = hfq;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        return this.f12310a.mo5933a((Callable) new gys(this.f12311b, (String) obj, this.f12312c));
    }
}
