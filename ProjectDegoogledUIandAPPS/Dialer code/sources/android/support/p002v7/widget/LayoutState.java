package android.support.p002v7.widget;

import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: android.support.v7.widget.LayoutState */
class LayoutState {
    int mAvailable;
    int mCurrentPosition;
    int mEndLine = 0;
    boolean mInfinite;
    int mItemDirection;
    int mLayoutDirection;
    boolean mRecycle = true;
    int mStartLine = 0;
    boolean mStopInFocusable;

    LayoutState() {
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("LayoutState{mAvailable=");
        outline13.append(this.mAvailable);
        outline13.append(", mCurrentPosition=");
        outline13.append(this.mCurrentPosition);
        outline13.append(", mItemDirection=");
        outline13.append(this.mItemDirection);
        outline13.append(", mLayoutDirection=");
        outline13.append(this.mLayoutDirection);
        outline13.append(", mStartLine=");
        outline13.append(this.mStartLine);
        outline13.append(", mEndLine=");
        outline13.append(this.mEndLine);
        outline13.append('}');
        return outline13.toString();
    }
}
