package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: xa */
/* compiled from: PG */
public final class C0628xa implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ RecyclerView f16282a;

    public C0628xa(RecyclerView recyclerView) {
        this.f16282a = recyclerView;
    }

    public final void run() {
        RecyclerView recyclerView = this.f16282a;
        if (recyclerView.mFirstLayoutComplete && !recyclerView.isLayoutRequested()) {
            RecyclerView recyclerView2 = this.f16282a;
            if (!recyclerView2.mIsAttached) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.mLayoutSuppressed) {
                recyclerView2.mLayoutWasDefered = true;
            } else {
                recyclerView2.consumePendingUpdateOperations();
            }
        }
    }
}
