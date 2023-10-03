package p000;

import java.util.concurrent.Executor;

/* renamed from: bpr */
/* compiled from: PG */
final /* synthetic */ class bpr implements gpe {

    /* renamed from: a */
    private final hgm f3322a;

    public bpr(hgm hgm) {
        this.f3322a = hgm;
    }

    /* renamed from: a */
    public final gpc mo2654a(Object obj) {
        hgm hgm = this.f3322a;
        gag gag = ((hgb) obj).f12688a;
        gbm gbm = hgm.f12699a;
        String str = gbm.f10841a;
        Object[] objArr = gbm.f10842b;
        gag.mo6371a();
        gbb b = gbb.m9975b(new gae(gag, objArr, str));
        b.mo6387a(gag.f10784b);
        return gpc.m10576a(idb.m12700a((ieh) ife.m12898e((Object) b), (Executor) idh.f13918a));
    }
}
