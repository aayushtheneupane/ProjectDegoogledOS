package p000;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.List;

/* renamed from: dwq */
/* compiled from: PG */
final class dwq implements hol {

    /* renamed from: a */
    private final /* synthetic */ dwn f7536a;

    public dwq(dwn dwn) {
        this.f7536a = dwn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        bqj bqj = (bqj) hoi;
        dwn dwn = this.f7536a;
        dwn.f7511c.mo3732b();
        bqn bqn = dwn.f7524p;
        if (bqn.f3377g) {
            cwn.m5510a("DeletionCoordinator: overlaping delete events", new Object[0]);
        } else {
            bqn.f3377g = true;
            List list = bqj.f3368a;
            Bundle bundle = new Bundle();
            imi.m14108a(bundle, "mlk", list);
            bqn.f3373c.mo6986a(grw.m10690e(bqn.f3372b.mo4795a(list)), grt.m10682a((Parcelable) bundle), bqn.f3378h);
        }
        return hom.f13155a;
    }
}
