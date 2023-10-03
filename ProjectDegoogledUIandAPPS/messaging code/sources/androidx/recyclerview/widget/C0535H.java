package androidx.recyclerview.widget;

import android.view.View;

/* renamed from: androidx.recyclerview.widget.H */
final class C0535H extends C0536I {
    C0535H(C0558ca caVar) {
        super(caVar, (C0533G) null);
    }

    public int getDecoratedEnd(View view) {
        return this.mLayoutManager.getDecoratedBottom(view) + ((C0560da) view.getLayoutParams()).bottomMargin;
    }

    public int getDecoratedMeasurement(View view) {
        C0560da daVar = (C0560da) view.getLayoutParams();
        return this.mLayoutManager.getDecoratedMeasuredHeight(view) + daVar.topMargin + daVar.bottomMargin;
    }

    public int getDecoratedMeasurementInOther(View view) {
        C0560da daVar = (C0560da) view.getLayoutParams();
        return this.mLayoutManager.getDecoratedMeasuredWidth(view) + daVar.leftMargin + daVar.rightMargin;
    }

    public int getDecoratedStart(View view) {
        return this.mLayoutManager.getDecoratedTop(view) - ((C0560da) view.getLayoutParams()).topMargin;
    }

    public int getEnd() {
        return this.mLayoutManager.getHeight();
    }

    public int getEndAfterPadding() {
        return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
    }

    public int getEndPadding() {
        return this.mLayoutManager.getPaddingBottom();
    }

    public int getMode() {
        return this.mLayoutManager.getHeightMode();
    }

    public int getModeInOther() {
        return this.mLayoutManager.getWidthMode();
    }

    public int getStartAfterPadding() {
        return this.mLayoutManager.getPaddingTop();
    }

    public int getTotalSpace() {
        return (this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingTop()) - this.mLayoutManager.getPaddingBottom();
    }

    public int getTransformedEndWithDecoration(View view) {
        this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
        return this.mTmpRect.bottom;
    }

    public int getTransformedStartWithDecoration(View view) {
        this.mLayoutManager.getTransformedBoundingBox(view, true, this.mTmpRect);
        return this.mTmpRect.top;
    }

    public void offsetChildren(int i) {
        this.mLayoutManager.offsetChildrenVertical(i);
    }
}
