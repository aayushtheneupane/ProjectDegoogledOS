package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: eho */
/* compiled from: PG */
public final class eho {

    /* renamed from: a */
    public final fzx f8300a;

    /* renamed from: b */
    public final String f8301b;

    /* renamed from: c */
    public final inw f8302c;

    public eho(fzx fzx, String str, inw inw) {
        this.f8300a = fzx;
        this.f8301b = str;
        this.f8302c = inw;
    }

    /* renamed from: b */
    public final ieh mo4814b() {
        return gte.m10771a(mo4812a(), (icf) new ehk(this), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo4812a() {
        return gte.m10771a(this.f8300a.mo6359a(), (icf) new ehj(this), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo4813a(cjm cjm) {
        return gte.m10778b(this.f8300a.mo6360a(new ehm(cjm), idh.f13918a)).mo7613a((Callable) new ehn(cjm), (Executor) idh.f13918a);
    }
}
