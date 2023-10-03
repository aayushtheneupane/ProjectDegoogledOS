package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: xb */
/* compiled from: PG */
public final class C0629xb implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ RecyclerView f16283a;

    public C0629xb(RecyclerView recyclerView) {
        this.f16283a = recyclerView;
    }

    public final void run() {
        C0641xn xnVar = this.f16283a.mItemAnimator;
        if (xnVar != null) {
            xnVar.mo10365a();
        }
        this.f16283a.mPostedAnimatorRunner = false;
    }
}
