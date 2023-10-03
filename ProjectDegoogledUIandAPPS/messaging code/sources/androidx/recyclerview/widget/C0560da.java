package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* renamed from: androidx.recyclerview.widget.da */
public class C0560da extends ViewGroup.MarginLayoutParams {
    final Rect mDecorInsets = new Rect();
    boolean mInsetsDirty = true;
    boolean mPendingInvalidate = false;
    C0586qa mViewHolder;

    public C0560da(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getViewLayoutPosition() {
        return this.mViewHolder.getLayoutPosition();
    }

    public boolean isItemChanged() {
        return this.mViewHolder.isUpdated();
    }

    public boolean isItemRemoved() {
        return this.mViewHolder.isRemoved();
    }

    public C0560da(int i, int i2) {
        super(i, i2);
    }

    public C0560da(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
    }

    public C0560da(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public C0560da(C0560da daVar) {
        super(daVar);
    }
}
