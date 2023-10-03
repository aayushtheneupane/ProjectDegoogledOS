package p000;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: fzj */
/* compiled from: PG */
final /* synthetic */ class fzj implements icf {

    /* renamed from: a */
    private final fzm f10736a;

    /* renamed from: b */
    private final int f10737b;

    /* renamed from: c */
    private final List f10738c;

    public fzj(fzm fzm, int i, List list) {
        this.f10736a = fzm;
        this.f10737b = i;
        this.f10738c = list;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        fzm fzm = this.f10736a;
        int i = this.f10737b;
        List list = this.f10738c;
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            if (((Boolean) ife.m12871b((Future) list.get(i2))).booleanValue()) {
                arrayList.add(((fzh) fzm.f10744a.get(i2)).mo6357b());
            }
        }
        return ife.m12883c((Iterable) arrayList).mo8443a(ife.m12887c(), (Executor) idh.f13918a);
    }
}
