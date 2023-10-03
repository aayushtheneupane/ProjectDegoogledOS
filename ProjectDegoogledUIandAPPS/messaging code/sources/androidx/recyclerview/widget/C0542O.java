package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.O */
class C0542O {
    final /* synthetic */ RecyclerView this$0;

    C0542O(RecyclerView recyclerView) {
        this.this$0 = recyclerView;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo4793b(C0553a aVar) {
        int i = aVar.cmd;
        if (i == 1) {
            RecyclerView recyclerView = this.this$0;
            recyclerView.mLayout.mo4723c(recyclerView, aVar.positionStart, aVar.itemCount);
        } else if (i == 2) {
            RecyclerView recyclerView2 = this.this$0;
            recyclerView2.mLayout.mo4725d(recyclerView2, aVar.positionStart, aVar.itemCount);
        } else if (i == 4) {
            RecyclerView recyclerView3 = this.this$0;
            recyclerView3.mLayout.mo4715a(recyclerView3, aVar.positionStart, aVar.itemCount, aVar.payload);
        } else if (i == 8) {
            RecyclerView recyclerView4 = this.this$0;
            recyclerView4.mLayout.mo4714a(recyclerView4, aVar.positionStart, aVar.itemCount, 1);
        }
    }

    public C0586qa findViewHolder(int i) {
        C0586qa findViewHolderForPosition = this.this$0.findViewHolderForPosition(i, true);
        if (findViewHolderForPosition != null && !this.this$0.mChildHelper.isHidden(findViewHolderForPosition.itemView)) {
            return findViewHolderForPosition;
        }
        return null;
    }

    public void markViewHoldersUpdated(int i, int i2, Object obj) {
        this.this$0.viewRangeUpdate(i, i2, obj);
        this.this$0.mItemsChanged = true;
    }
}
