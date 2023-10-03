package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.W */
class C0549W {
    final /* synthetic */ RecyclerView this$0;

    C0549W(RecyclerView recyclerView) {
        this.this$0 = recyclerView;
    }

    /* renamed from: j */
    public void mo4984j(C0586qa qaVar) {
        qaVar.setIsRecyclable(true);
        if (qaVar.mShadowedHolder != null && qaVar.mShadowingHolder == null) {
            qaVar.mShadowedHolder = null;
        }
        qaVar.mShadowingHolder = null;
        if (!qaVar.mo5192Yc() && !this.this$0.removeAnimatingView(qaVar.itemView) && qaVar.isTmpDetached()) {
            this.this$0.removeDetachedView(qaVar.itemView, false);
        }
    }
}
