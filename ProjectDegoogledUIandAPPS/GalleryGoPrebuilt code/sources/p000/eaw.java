package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: eaw */
/* compiled from: PG */
final class eaw extends C0652xy {

    /* renamed from: a */
    private boolean f7807a = false;

    /* renamed from: b */
    private final /* synthetic */ ebc f7808b;

    public eaw(ebc ebc) {
        this.f7808b = ebc;
    }

    /* renamed from: a */
    public final void mo4639a(RecyclerView recyclerView, int i) {
        if (i != 0) {
            if (!this.f7807a) {
                this.f7808b.f7829e.mo4638a(true);
            }
            this.f7807a = true;
            return;
        }
        this.f7807a = false;
        this.f7808b.f7829e.mo4638a(false);
        this.f7808b.f7829e.mo4635a(this.f7808b.f7825a.mo10481n());
    }
}
