package p000;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: hio */
/* compiled from: PG */
final /* synthetic */ class hio implements ice {

    /* renamed from: a */
    private final hiq f12804a;

    /* renamed from: b */
    private final ieh f12805b;

    /* renamed from: c */
    private final ieh f12806c;

    public hio(hiq hiq, ieh ieh, ieh ieh2) {
        this.f12804a = hiq;
        this.f12805b = ieh;
        this.f12806c = ieh2;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        hiq hiq = this.f12804a;
        ieh ieh = this.f12805b;
        ieh ieh2 = this.f12806c;
        Set set = (Set) ife.m12871b((Future) ieh);
        Set set2 = (Set) ife.m12871b((Future) ieh2);
        hvn a = ife.m12814a(set, set2);
        hvn a2 = ife.m12814a(set2, set);
        hiq.mo7473a((Set) a);
        HashSet hashSet = new HashSet();
        synchronized (hiq.f12816h) {
            for (hjh hjh : hiq.f12816h.keySet()) {
                if (a2.contains(hjh.f12851c)) {
                    hashSet.add(hjh);
                }
            }
            hiq.mo7472a((Collection) hashSet);
            hiq.f12816h.keySet().removeAll(hashSet);
            goo goo = hiq.f12812d;
            hiz hiz = hiq.f12813e;
            goo.m10562a(goo.mo6881a(hiz.f12841c.mo5931a((Runnable) new hix(hiz, hashSet))), "Error removing accounts from sync. IDs: %s", a2);
        }
        if (!a.isEmpty() || !a2.isEmpty()) {
            return ibv.m12657a(hiq.mo7466a(ife.m12820a((Object) Collections.emptySet())), ife.m12906g((Object) null), (Executor) idh.f13918a);
        }
        return ife.m12820a((Object) null);
    }
}
