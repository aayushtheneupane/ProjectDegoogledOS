package p000;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: fzk */
/* compiled from: PG */
final /* synthetic */ class fzk implements ice {

    /* renamed from: a */
    private final fzm f10739a;

    /* renamed from: b */
    private final ikf f10740b;

    /* renamed from: c */
    private final int f10741c;

    /* renamed from: d */
    private final List f10742d;

    public fzk(fzm fzm, ikf ikf, int i, List list) {
        this.f10739a = fzm;
        this.f10740b = ikf;
        this.f10741c = i;
        this.f10742d = list;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        fzm fzm = this.f10739a;
        ikf ikf = this.f10740b;
        int i = this.f10741c;
        List list = this.f10742d;
        ieh a = ife.m12820a((Object) ikf);
        for (int i2 = 0; i2 < i; i2++) {
            if (((Boolean) ife.m12871b((Future) list.get(i2))).booleanValue()) {
                a = ibv.m12658a(a, hmq.m11744a((icf) new fzl((fzh) fzm.f10744a.get(i2))), (Executor) idh.f13918a);
            }
        }
        return a;
    }
}
