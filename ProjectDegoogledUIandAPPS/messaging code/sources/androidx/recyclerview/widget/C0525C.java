package androidx.recyclerview.widget;

import android.view.View;
import java.util.List;

/* renamed from: androidx.recyclerview.widget.C */
class C0525C {

    /* renamed from: er */
    int f507er = 0;

    /* renamed from: fr */
    int f508fr = 0;
    int mAvailable;
    int mCurrentPosition;
    boolean mInfinite;
    int mItemDirection;
    int mLastScrollDelta;
    int mLayoutDirection;
    int mOffset;
    boolean mRecycle = true;
    List mScrapList = null;
    int mScrollingOffset;

    C0525C() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public View mo4635a(C0572ja jaVar) {
        List list = this.mScrapList;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = ((C0586qa) this.mScrapList.get(i)).itemView;
                C0560da daVar = (C0560da) view.getLayoutParams();
                if (!daVar.isItemRemoved() && this.mCurrentPosition == daVar.getViewLayoutPosition()) {
                    assignPositionFromScrapList(view);
                    return view;
                }
            }
            return null;
        }
        View view2 = jaVar.tryGetViewHolderForPositionByDeadline(this.mCurrentPosition, false, Long.MAX_VALUE).itemView;
        this.mCurrentPosition += this.mItemDirection;
        return view2;
    }

    public void assignPositionFromScrapList(View view) {
        int viewLayoutPosition;
        int size = this.mScrapList.size();
        View view2 = null;
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            View view3 = ((C0586qa) this.mScrapList.get(i2)).itemView;
            C0560da daVar = (C0560da) view3.getLayoutParams();
            if (view3 != view && !daVar.isItemRemoved() && (viewLayoutPosition = (daVar.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection) >= 0 && viewLayoutPosition < i) {
                view2 = view3;
                if (viewLayoutPosition == 0) {
                    break;
                }
                i = viewLayoutPosition;
            }
        }
        if (view2 == null) {
            this.mCurrentPosition = -1;
        } else {
            this.mCurrentPosition = ((C0560da) view2.getLayoutParams()).getViewLayoutPosition();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo4637b(C0582oa oaVar) {
        int i = this.mCurrentPosition;
        return i >= 0 && i < oaVar.getItemCount();
    }
}
