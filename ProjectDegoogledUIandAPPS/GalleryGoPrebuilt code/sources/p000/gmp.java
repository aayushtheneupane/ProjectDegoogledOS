package p000;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: gmp */
/* compiled from: PG */
public final /* synthetic */ class gmp implements icf {

    /* renamed from: a */
    private final glp f11631a;

    public gmp(glp glp) {
        this.f11631a = glp;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        glp glp = this.f11631a;
        ArrayList arrayList = new ArrayList();
        hvr a = ((hsu) ((Pair) obj).first).entrySet().iterator();
        while (a.hasNext()) {
            Map.Entry entry = (Map.Entry) a.next();
            gle gle = ((gma) glp.m10479a((gnj) entry.getValue())).f11614a;
            ife.m12898e((Object) gtf.f12011a);
            gmb gmb = new gmb((gkn) entry.getKey(), gle);
            Set<glh> set = (Set) glp.f11585c.mo2097a();
            ArrayList arrayList2 = new ArrayList(set.size());
            for (glh a2 : set) {
                try {
                    arrayList2.add(a2.mo6814a());
                } catch (Exception e) {
                    arrayList2.add(ife.m12822a((Throwable) e));
                }
            }
            ieh a3 = ife.m12883c((Iterable) arrayList2).mo8443a(ife.m12887c(), (Executor) idh.f13918a);
            Set<glf> set2 = (Set) glp.f11586d.mo2097a();
            ArrayList arrayList3 = new ArrayList(set2.size());
            for (glf a4 : set2) {
                try {
                    arrayList3.add(a4.mo6829a(gmb));
                } catch (Exception e2) {
                    arrayList3.add(ife.m12822a((Throwable) e2));
                }
            }
            arrayList.add(ife.m12884c(a3, ife.m12883c((Iterable) arrayList3).mo8443a(ife.m12887c(), (Executor) idh.f13918a)).mo8443a(ife.m12887c(), (Executor) idh.f13918a));
        }
        return ife.m12883c((Iterable) arrayList).mo8443a(ife.m12887c(), (Executor) idh.f13918a);
    }
}
