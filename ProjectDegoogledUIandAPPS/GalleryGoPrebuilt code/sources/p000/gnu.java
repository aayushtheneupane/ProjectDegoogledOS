package p000;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* renamed from: gnu */
/* compiled from: PG */
final /* synthetic */ class gnu implements ice {

    /* renamed from: a */
    private final gnw f11705a;

    /* renamed from: b */
    private final gkn f11706b;

    public gnu(gnw gnw, gkn gkn) {
        this.f11705a = gnw;
        this.f11706b = gkn;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        gnw gnw = this.f11705a;
        String a = gnn.m10546a(this.f11706b);
        ArrayList arrayList = new ArrayList();
        hvr a2 = gnw.f11709b.mo7371a().iterator();
        while (a2.hasNext()) {
            arrayList.add(gnw.mo6868a(new File((File) a2.next(), a)));
        }
        return ife.m12883c((Iterable) arrayList).mo8443a(ife.m12887c(), (Executor) idh.f13918a);
    }
}
