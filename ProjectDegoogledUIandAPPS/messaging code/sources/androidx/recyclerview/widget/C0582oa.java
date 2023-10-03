package androidx.recyclerview.widget;

import android.util.SparseArray;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.oa */
public class C0582oa {

    /* renamed from: gr */
    int f658gr = -1;
    private SparseArray mData;
    int mDeletedInvisibleItemCountSincePreviousLayout = 0;
    long mFocusedItemId;
    int mFocusedItemPosition;
    int mFocusedSubChildId;
    boolean mInPreLayout = false;
    boolean mIsMeasuring = false;
    int mItemCount = 0;
    int mLayoutStep = 1;
    int mPreviousLayoutItemCount = 0;
    boolean mRunPredictiveAnimations = false;
    boolean mRunSimpleAnimations = false;
    boolean mStructureChanged = false;
    boolean mTrackOldChangeHolders = false;

    /* access modifiers changed from: package-private */
    public void assertLayoutStep(int i) {
        if ((this.mLayoutStep & i) == 0) {
            StringBuilder Pa = C0632a.m1011Pa("Layout state should be one of ");
            Pa.append(Integer.toBinaryString(i));
            Pa.append(" but it is ");
            Pa.append(Integer.toBinaryString(this.mLayoutStep));
            throw new IllegalStateException(Pa.toString());
        }
    }

    public int getItemCount() {
        return this.mInPreLayout ? this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout : this.mItemCount;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("State{mTargetPosition=");
        Pa.append(this.f658gr);
        Pa.append(", mData=");
        Pa.append(this.mData);
        Pa.append(", mItemCount=");
        Pa.append(this.mItemCount);
        Pa.append(", mIsMeasuring=");
        Pa.append(this.mIsMeasuring);
        Pa.append(", mPreviousLayoutItemCount=");
        Pa.append(this.mPreviousLayoutItemCount);
        Pa.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
        Pa.append(this.mDeletedInvisibleItemCountSincePreviousLayout);
        Pa.append(", mStructureChanged=");
        Pa.append(this.mStructureChanged);
        Pa.append(", mInPreLayout=");
        Pa.append(this.mInPreLayout);
        Pa.append(", mRunSimpleAnimations=");
        Pa.append(this.mRunSimpleAnimations);
        Pa.append(", mRunPredictiveAnimations=");
        Pa.append(this.mRunPredictiveAnimations);
        Pa.append('}');
        return Pa.toString();
    }
}
