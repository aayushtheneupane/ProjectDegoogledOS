package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: adf */
/* compiled from: PG */
final class adf implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ adk f208a;

    public adf(adk adk) {
        this.f208a = adk;
    }

    public final void run() {
        RecyclerView recyclerView = this.f208a.f219c;
        recyclerView.focusableViewAvailable(recyclerView);
    }
}
