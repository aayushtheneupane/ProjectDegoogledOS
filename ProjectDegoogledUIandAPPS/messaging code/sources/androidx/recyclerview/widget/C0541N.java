package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: androidx.recyclerview.widget.N */
class C0541N {
    final /* synthetic */ RecyclerView this$0;

    C0541N(RecyclerView recyclerView) {
        this.this$0 = recyclerView;
    }

    public void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams) {
        C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached() || childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt + this.this$0.mo4815Vb());
            }
        }
        this.this$0.attachViewToParent(view, i, layoutParams);
    }

    public View getChildAt(int i) {
        return this.this$0.getChildAt(i);
    }

    public int getChildCount() {
        return this.this$0.getChildCount();
    }

    public C0586qa getChildViewHolder(View view) {
        return RecyclerView.getChildViewHolderInt(view);
    }

    public void onEnteredHiddenState(View view) {
        C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.mo5216k(this.this$0);
        }
    }

    public void onLeftHiddenState(View view) {
        C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.mo5217l(this.this$0);
        }
    }

    public void removeViewAt(int i) {
        View childAt = this.this$0.getChildAt(i);
        if (childAt != null) {
            this.this$0.dispatchChildDetached(childAt);
            childAt.clearAnimation();
        }
        this.this$0.removeViewAt(i);
    }
}
