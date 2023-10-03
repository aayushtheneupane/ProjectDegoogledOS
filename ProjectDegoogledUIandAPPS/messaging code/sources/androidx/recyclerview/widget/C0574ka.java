package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.ka */
class C0574ka extends C0545S {
    final /* synthetic */ RecyclerView this$0;

    C0574ka(RecyclerView recyclerView) {
        this.this$0 = recyclerView;
    }

    public void onChanged() {
        this.this$0.assertNotInLayoutOrScroll((String) null);
        RecyclerView recyclerView = this.this$0;
        recyclerView.mState.mStructureChanged = true;
        recyclerView.mo4946u(true);
        if (!this.this$0.mAdapterHelper.hasPendingUpdates()) {
            this.this$0.requestLayout();
        }
    }
}
