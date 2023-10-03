package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.J */
class C0537J implements Runnable {
    final /* synthetic */ RecyclerView this$0;

    C0537J(RecyclerView recyclerView) {
        this.this$0 = recyclerView;
    }

    public void run() {
        RecyclerView recyclerView = this.this$0;
        if (recyclerView.mFirstLayoutComplete && !recyclerView.isLayoutRequested()) {
            RecyclerView recyclerView2 = this.this$0;
            if (!recyclerView2.mIsAttached) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.f572Zh) {
                recyclerView2.f571Yh = true;
            } else {
                recyclerView2.consumePendingUpdateOperations();
            }
        }
    }
}
