package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: xo */
/* compiled from: PG */
public final class C0642xo implements C0639xl {

    /* renamed from: a */
    private final /* synthetic */ RecyclerView f16293a;

    public C0642xo(RecyclerView recyclerView) {
        this.f16293a = recyclerView;
    }

    /* renamed from: a */
    public final void mo10552a(C0667ym ymVar) {
        ymVar.mo10639a(true);
        if (ymVar.f16389h != null && ymVar.f16390i == null) {
            ymVar.f16389h = null;
        }
        ymVar.f16390i = null;
        if ((ymVar.f16391j & 16) == 0 && !this.f16293a.removeAnimatingView(ymVar.f16382a) && ymVar.mo10654n()) {
            this.f16293a.removeDetachedView(ymVar.f16382a, false);
        }
    }
}
