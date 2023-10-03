package androidx.recyclerview.widget;

import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.z */
class C0603z {

    /* renamed from: br */
    int f685br = 0;

    /* renamed from: cr */
    int f686cr = 0;

    /* renamed from: dr */
    boolean f687dr;
    int mAvailable;
    int mCurrentPosition;
    boolean mInfinite;
    int mItemDirection;
    int mLayoutDirection;
    boolean mRecycle = true;

    C0603z() {
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("LayoutState{mAvailable=");
        Pa.append(this.mAvailable);
        Pa.append(", mCurrentPosition=");
        Pa.append(this.mCurrentPosition);
        Pa.append(", mItemDirection=");
        Pa.append(this.mItemDirection);
        Pa.append(", mLayoutDirection=");
        Pa.append(this.mLayoutDirection);
        Pa.append(", mStartLine=");
        Pa.append(this.f685br);
        Pa.append(", mEndLine=");
        Pa.append(this.f686cr);
        Pa.append('}');
        return Pa.toString();
    }
}
