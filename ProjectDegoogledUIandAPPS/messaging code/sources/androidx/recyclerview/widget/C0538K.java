package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.K */
class C0538K implements Runnable {
    final /* synthetic */ RecyclerView this$0;

    C0538K(RecyclerView recyclerView) {
        this.this$0 = recyclerView;
    }

    public void run() {
        C0594ua uaVar = this.this$0.mItemAnimator;
        if (uaVar != null) {
            uaVar.runPendingAnimations();
        }
        this.this$0.mPostedAnimatorRunner = false;
    }
}
