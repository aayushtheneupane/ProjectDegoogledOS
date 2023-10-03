package p000;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: glr */
/* compiled from: PG */
final /* synthetic */ class glr implements ice {

    /* renamed from: a */
    private final glx f11587a;

    /* renamed from: b */
    private final Set f11588b;

    public glr(glx glx, Set set) {
        this.f11587a = glx;
        this.f11588b = set;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        glx glx = this.f11587a;
        Set<glq> set = this.f11588b;
        if (set.isEmpty()) {
            return glx.mo6836a();
        }
        ArrayList arrayList = new ArrayList(set.size());
        for (glq a : set) {
            arrayList.add(a.mo6835a());
        }
        return ibv.m12658a(ibv.m12658a((ieh) idq.m12731c(ife.m12819a((Iterable) arrayList)), hmq.m11744a((icf) new glu(glx)), (Executor) idh.f13918a), hmq.m11744a((icf) new glv(glx)), (Executor) idh.f13918a);
    }
}
