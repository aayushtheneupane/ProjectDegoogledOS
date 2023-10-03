package p000;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: gno */
/* compiled from: PG */
final /* synthetic */ class gno implements icf {

    /* renamed from: a */
    private final gnr f11697a;

    public gno(gnr gnr) {
        this.f11697a = gnr;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        gnr gnr = this.f11697a;
        Set set = (Set) obj;
        ArrayList arrayList = new ArrayList();
        hvr a = gnr.f11700a.mo7371a().iterator();
        while (a.hasNext()) {
            File[] listFiles = new File((File) a.next(), "accounts").listFiles(new gnq(set));
            if (listFiles == null) {
                listFiles = new File[0];
            }
            for (File a2 : listFiles) {
                arrayList.add(gnr.f11701b.mo6868a(a2));
            }
        }
        return ife.m12866b((Iterable) arrayList).mo8443a((Callable) new gnp(arrayList), (Executor) idh.f13918a);
    }
}
