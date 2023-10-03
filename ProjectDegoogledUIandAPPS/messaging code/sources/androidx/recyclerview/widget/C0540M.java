package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.M */
class C0540M {
    final /* synthetic */ RecyclerView this$0;

    C0540M(RecyclerView recyclerView) {
        this.this$0 = recyclerView;
    }

    /* renamed from: c */
    public void mo4785c(C0586qa qaVar, C0548V v, C0548V v2) {
        qaVar.setIsRecyclable(false);
        RecyclerView recyclerView = this.this$0;
        if (recyclerView.mDataSetHasChangedAfterLayout) {
            if (recyclerView.mItemAnimator.mo5239a(qaVar, qaVar, v, v2)) {
                this.this$0.postAnimationRunner();
            }
        } else if (recyclerView.mItemAnimator.mo5243d(qaVar, v, v2)) {
            this.this$0.postAnimationRunner();
        }
    }
}
