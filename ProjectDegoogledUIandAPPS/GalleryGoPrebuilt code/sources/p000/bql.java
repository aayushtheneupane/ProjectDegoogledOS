package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.R;
import java.util.List;

/* renamed from: bql */
/* compiled from: PG */
final class bql implements gry {

    /* renamed from: a */
    private final /* synthetic */ bqn f3369a;

    public bql(bqn bqn) {
        this.f3369a = bqn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2651a(Object obj, Throwable th) {
        Bundle bundle = (Bundle) obj;
        cwn.m5515b(th, "PhotoGridFragment: Failed to get permission to delete file.", new Object[0]);
        ihg.m13041a((hoi) dwz.m6850a(this.f3369a.f3371a.mo5635a((int) R.string.delete_failed_snackbar)), this.f3369a.f3371a);
        this.f3369a.f3377g = false;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2649a(Object obj) {
        Bundle bundle = (Bundle) obj;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2650a(Object obj, Object obj2) {
        Bundle bundle = (Bundle) obj;
        bqn bqn = this.f3369a;
        if (!((Boolean) obj2).booleanValue()) {
            ihg.m13041a((hoi) dwz.m6850a(bqn.f3371a.mo5635a((int) R.string.delete_failed_snackbar)), bqn.f3371a);
            bqn.f3377g = false;
            return;
        }
        try {
            List b = imi.m14115b(bundle, "mlk", cyd.f5985i, bqn.f3376f);
            iir g = bqo.f3381c.mo8793g();
            int size = b.size();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            bqo bqo = (bqo) g.f14318b;
            bqo.f3383a |= 1;
            bqo.f3384b = size;
            bqr bqr = new bqr();
            ftr.m9611b(bqr);
            ftr.m9610a((C0147fh) bqr);
            hcl.m11210a(bqr, (bqo) g.mo8770g());
            bqn.f3380j = bqr;
            bqn.f3380j.mo5429b(bqn.f3371a.mo5659r(), "delete_progress");
            bqn.f3373c.mo6987a(grw.m10686a(bqn.f3374d.mo4013a(b)), bqn.f3379i);
            bqn.f3375e.mo3766f();
            bqn.f3375e.mo3755a();
        } catch (ijh e) {
            cwn.m5515b((Throwable) e, "PhotoGridFragment: Invalid media list bundle", new Object[0]);
            ihg.m13041a((hoi) dwz.m6850a(bqn.f3371a.mo5635a((int) R.string.delete_failed_snackbar)), bqn.f3371a);
            bqn.f3377g = false;
        }
    }
}
