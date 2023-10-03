package p000;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: fzm */
/* compiled from: PG */
public final class fzm implements icf {

    /* renamed from: a */
    public final List f10744a;

    /* renamed from: b */
    public final Executor f10745b;

    public fzm(List list, Executor executor) {
        this.f10744a = list;
        this.f10745b = executor;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ieh mo2538a(Object obj) {
        fzw fzw = (fzw) obj;
        int size = this.f10744a.size();
        ArrayList arrayList = new ArrayList(size);
        List list = this.f10744a;
        int size2 = list.size();
        for (int i = 0; i < size2; i++) {
            arrayList.add(((fzh) list.get(i)).mo6355a());
        }
        icf a = hmq.m11744a((icf) new fzi(this, arrayList, size));
        idh idh = idh.f13918a;
        List list2 = hnb.f13076a;
        return ibv.m12658a(ibv.m12658a(ife.m12817a(fzw.f10760a.f10763c.mo6948a()), hmq.m11744a((icf) new fzv(fzw, a, idh)), (Executor) idh.f13918a), hmq.m11744a((icf) new fzj(this, size, arrayList)), (Executor) idh.f13918a);
    }
}
