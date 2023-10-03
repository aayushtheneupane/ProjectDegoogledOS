package p000;

import com.google.android.apps.photosgo.R;

/* renamed from: bqm */
/* compiled from: PG */
final class bqm implements gry {

    /* renamed from: a */
    private final /* synthetic */ bqn f3370a;

    public bqm(bqn bqn) {
        this.f3370a = bqn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2651a(Object obj, Throwable th) {
        Void voidR = (Void) obj;
        cwn.m5515b(th, "PhotoGridFragment: Failed to delete media.", new Object[0]);
        ihg.m13041a((hoi) dwz.m6850a(this.f3370a.f3371a.mo5635a((int) R.string.delete_failed_snackbar)), this.f3370a.f3371a);
        this.f3370a.mo2674d();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2649a(Object obj) {
        Void voidR = (Void) obj;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2650a(Object obj, Object obj2) {
        int i;
        Void voidR = (Void) obj;
        czd czd = czd.SUCCESS;
        int ordinal = ((czd) obj2).ordinal();
        if (ordinal != 0) {
            i = ordinal != 1 ? ordinal != 2 ? 0 : R.string.delete_already_running : R.string.cancelled_delete_completed_snackbar;
        } else {
            i = R.string.delete_completed_snackbar;
        }
        ihg.m13041a((hoi) dwz.m6850a(this.f3370a.f3371a.mo5635a(i)), this.f3370a.f3371a);
        this.f3370a.mo2674d();
    }
}
