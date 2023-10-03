package androidx.recyclerview.widget;

import android.view.View;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.A */
class C0521A {
    int mCoordinate;
    boolean mLayoutFromEnd;
    C0536I mOrientationHelper;
    int mPosition;
    boolean mValid;

    C0521A() {
        reset();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo4612a(View view, C0582oa oaVar) {
        C0560da daVar = (C0560da) view.getLayoutParams();
        return !daVar.isItemRemoved() && daVar.getViewLayoutPosition() >= 0 && daVar.getViewLayoutPosition() < oaVar.getItemCount();
    }

    /* access modifiers changed from: package-private */
    public void assignCoordinateFromPadding() {
        int i;
        if (this.mLayoutFromEnd) {
            i = this.mOrientationHelper.getEndAfterPadding();
        } else {
            i = this.mOrientationHelper.getStartAfterPadding();
        }
        this.mCoordinate = i;
    }

    /* renamed from: b */
    public void mo4614b(View view, int i) {
        int totalSpaceChange = this.mOrientationHelper.getTotalSpaceChange();
        if (totalSpaceChange >= 0) {
            mo4611a(view, i);
            return;
        }
        this.mPosition = i;
        if (this.mLayoutFromEnd) {
            int endAfterPadding = (this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange) - this.mOrientationHelper.getDecoratedEnd(view);
            this.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - endAfterPadding;
            if (endAfterPadding > 0) {
                int decoratedMeasurement = this.mCoordinate - this.mOrientationHelper.getDecoratedMeasurement(view);
                int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                int min = decoratedMeasurement - (Math.min(this.mOrientationHelper.getDecoratedStart(view) - startAfterPadding, 0) + startAfterPadding);
                if (min < 0) {
                    this.mCoordinate = Math.min(endAfterPadding, -min) + this.mCoordinate;
                    return;
                }
                return;
            }
            return;
        }
        int decoratedStart = this.mOrientationHelper.getDecoratedStart(view);
        int startAfterPadding2 = decoratedStart - this.mOrientationHelper.getStartAfterPadding();
        this.mCoordinate = decoratedStart;
        if (startAfterPadding2 > 0) {
            int endAfterPadding2 = (this.mOrientationHelper.getEndAfterPadding() - Math.min(0, (this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange) - this.mOrientationHelper.getDecoratedEnd(view))) - (this.mOrientationHelper.getDecoratedMeasurement(view) + decoratedStart);
            if (endAfterPadding2 < 0) {
                this.mCoordinate -= Math.min(startAfterPadding2, -endAfterPadding2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.mPosition = -1;
        this.mCoordinate = RtlSpacingHelper.UNDEFINED;
        this.mLayoutFromEnd = false;
        this.mValid = false;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("AnchorInfo{mPosition=");
        Pa.append(this.mPosition);
        Pa.append(", mCoordinate=");
        Pa.append(this.mCoordinate);
        Pa.append(", mLayoutFromEnd=");
        Pa.append(this.mLayoutFromEnd);
        Pa.append(", mValid=");
        Pa.append(this.mValid);
        Pa.append('}');
        return Pa.toString();
    }

    /* renamed from: a */
    public void mo4611a(View view, int i) {
        if (this.mLayoutFromEnd) {
            this.mCoordinate = this.mOrientationHelper.getTotalSpaceChange() + this.mOrientationHelper.getDecoratedEnd(view);
        } else {
            this.mCoordinate = this.mOrientationHelper.getDecoratedStart(view);
        }
        this.mPosition = i;
    }
}
