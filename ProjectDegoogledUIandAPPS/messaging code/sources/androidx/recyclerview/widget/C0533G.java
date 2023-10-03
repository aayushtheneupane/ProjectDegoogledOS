package androidx.recyclerview.widget;

import android.view.View;

/* renamed from: androidx.recyclerview.widget.G */
final class C0533G extends C0536I {
    C0533G(C0558ca caVar) {
        super(caVar, (C0533G) null);
    }

    public int getDecoratedEnd(View view) {
        return this.mLayoutManager.getDecoratedRight(view) + ((C0560da) view.getLayoutParams()).rightMargin;
    }

    public int getDecoratedMeasurement(View view) {
        C0560da daVar = (C0560da) view.getLayoutParams();
        return this.mLayoutManager.getDecoratedMeasuredWidth(view) + daVar.leftMargin + daVar.rightMargin;
    }

    public int getDecoratedMeasurementInOther(View view) {
        C0560da daVar = (C0560da) view.getLayoutParams();
        return this.mLayoutManager.getDecoratedMeasuredHeight(view) + daVar.topMargin + daVar.bottomMargin;
    }

    public int getDecoratedStart(View view) {
        return this.mLayoutManager.getDecoratedLeft(view) - ((C0560da) view.getLayoutParams()).leftMargin;
    }

    public int getEnd() {
        return this.mLayoutManager.getWidth();
    }

    public int getEndAfterPadding() {
        return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingRight();
    }

    public int getEndPadding() {
        return this.mLayoutManager.getPaddingRight();
    }

    public int getMode() {
        return this.mLayoutManager.getWidthMode();
    }

    public int getModeInOther() {
        return this.mLayoutManager.getHeightMode();
    }

    public int getStartAfterPadding() {
        return this.mLayoutManager.getPaddingLeft();
    }

    public int getTotalSpace() {
        return (this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingLeft()) - this.mLayoutManager.getPaddingRight();
    }

    public int getTransformedEndWithDecoration(View view) {
        this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
        return this.mTmpRect.right;
    }

    public int getTransformedStartWithDecoration(View view) {
        this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
        return this.mTmpRect.left;
    }

    public void offsetChildren(int i) {
        this.mLayoutManager.offsetChildrenHorizontal(i);
    }
}
