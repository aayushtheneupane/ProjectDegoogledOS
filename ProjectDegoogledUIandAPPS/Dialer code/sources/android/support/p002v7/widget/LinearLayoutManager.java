package android.support.p002v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.GapWorker;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

/* renamed from: android.support.v7.widget.LinearLayoutManager */
public class LinearLayoutManager extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {
    final AnchorInfo mAnchorInfo;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;

    /* renamed from: android.support.v7.widget.LinearLayoutManager$AnchorInfo */
    static class AnchorInfo {
        int mCoordinate;
        boolean mLayoutFromEnd;
        OrientationHelper mOrientationHelper;
        int mPosition;
        boolean mValid;

        AnchorInfo() {
            reset();
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

        public void assignFromView(View view, int i) {
            if (this.mLayoutFromEnd) {
                this.mCoordinate = this.mOrientationHelper.getTotalSpaceChange() + this.mOrientationHelper.getDecoratedEnd(view);
            } else {
                this.mCoordinate = this.mOrientationHelper.getDecoratedStart(view);
            }
            this.mPosition = i;
        }

        public void assignFromViewAndKeepVisibleRect(View view, int i) {
            int totalSpaceChange = this.mOrientationHelper.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                assignFromView(view, i);
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
        public boolean isViewValidAsAnchor(View view, RecyclerView.State state) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return !layoutParams.isItemRemoved() && layoutParams.getViewLayoutPosition() >= 0 && layoutParams.getViewLayoutPosition() < state.getItemCount();
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mPosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mValid = false;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("AnchorInfo{mPosition=");
            outline13.append(this.mPosition);
            outline13.append(", mCoordinate=");
            outline13.append(this.mCoordinate);
            outline13.append(", mLayoutFromEnd=");
            outline13.append(this.mLayoutFromEnd);
            outline13.append(", mValid=");
            outline13.append(this.mValid);
            outline13.append('}');
            return outline13.toString();
        }
    }

    /* renamed from: android.support.v7.widget.LinearLayoutManager$LayoutChunkResult */
    protected static class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        protected LayoutChunkResult() {
        }
    }

    /* renamed from: android.support.v7.widget.LinearLayoutManager$LayoutState */
    static class LayoutState {
        int mAvailable;
        int mCurrentPosition;
        int mExtra = 0;
        boolean mInfinite;
        int mItemDirection;
        int mLastScrollDelta;
        int mLayoutDirection;
        int mOffset;
        boolean mRecycle = true;
        List<RecyclerView.ViewHolder> mScrapList = null;
        int mScrollingOffset;

        LayoutState() {
        }

        public void assignPositionFromScrapList(View view) {
            int viewLayoutPosition;
            int size = this.mScrapList.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.mScrapList.get(i2).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.isItemRemoved() && (viewLayoutPosition = (layoutParams.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection) >= 0 && viewLayoutPosition < i) {
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
                this.mCurrentPosition = ((RecyclerView.LayoutParams) view2.getLayoutParams()).getViewLayoutPosition();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasMore(RecyclerView.State state) {
            int i = this.mCurrentPosition;
            return i >= 0 && i < state.getItemCount();
        }

        /* access modifiers changed from: package-private */
        public View next(RecyclerView.Recycler recycler) {
            List<RecyclerView.ViewHolder> list = this.mScrapList;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    View view = this.mScrapList.get(i).itemView;
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                    if (!layoutParams.isItemRemoved() && this.mCurrentPosition == layoutParams.getViewLayoutPosition()) {
                        assignPositionFromScrapList(view);
                        return view;
                    }
                }
                return null;
            }
            View view2 = recycler.tryGetViewHolderForPositionByDeadline(this.mCurrentPosition, false, Long.MAX_VALUE).itemView;
            this.mCurrentPosition += this.mItemDirection;
            return view2;
        }
    }

    /* renamed from: android.support.v7.widget.LinearLayoutManager$SavedState */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public Object[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean mAnchorLayoutFromEnd;
        int mAnchorOffset;
        int mAnchorPosition;

        public SavedState() {
        }

        public int describeContents() {
            return 0;
        }

        /* access modifiers changed from: package-private */
        public boolean hasValidAnchor() {
            return this.mAnchorPosition >= 0;
        }

        /* access modifiers changed from: package-private */
        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
        }

        SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
            this.mAnchorLayoutFromEnd = parcel.readInt() != 1 ? false : true;
        }

        public SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        OrientationHelper orientationHelper = this.mOrientationHelper;
        View findFirstVisibleChildClosestToStart = findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true);
        return TooltipCompat.computeScrollExtent(state, orientationHelper, findFirstVisibleChildClosestToStart, findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        OrientationHelper orientationHelper = this.mOrientationHelper;
        View findFirstVisibleChildClosestToStart = findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true);
        return TooltipCompat.computeScrollOffset(state, orientationHelper, findFirstVisibleChildClosestToStart, findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        OrientationHelper orientationHelper = this.mOrientationHelper;
        View findFirstVisibleChildClosestToStart = findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true);
        return TooltipCompat.computeScrollRange(state, orientationHelper, findFirstVisibleChildClosestToStart, findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private View findFirstPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
    }

    private View findFirstReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return findReferenceChild(recycler, state, 0, getChildCount(), state.getItemCount());
    }

    private View findFirstVisibleChildClosestToEnd(boolean z, boolean z2) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(0, getChildCount(), z, z2);
        }
        return findOneVisibleChild(getChildCount() - 1, -1, z, z2);
    }

    private View findFirstVisibleChildClosestToStart(boolean z, boolean z2) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(getChildCount() - 1, -1, z, z2);
        }
        return findOneVisibleChild(0, getChildCount(), z, z2);
    }

    private View findLastPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
    }

    private View findLastReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return findReferenceChild(recycler, state, getChildCount() - 1, -1, state.getItemCount());
    }

    private int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i;
        if (endAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -scrollBy(-endAfterPadding2, recycler, state);
        int i3 = i + i2;
        if (!z || (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i3) <= 0) {
            return i2;
        }
        this.mOrientationHelper.offsetChildren(endAfterPadding);
        return endAfterPadding + i2;
    }

    private int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int startAfterPadding2 = i - this.mOrientationHelper.getStartAfterPadding();
        if (startAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -scrollBy(startAfterPadding2, recycler, state);
        int i3 = i + i2;
        if (!z || (startAfterPadding = i3 - this.mOrientationHelper.getStartAfterPadding()) <= 0) {
            return i2;
        }
        this.mOrientationHelper.offsetChildren(-startAfterPadding);
        return i2 - startAfterPadding;
    }

    private View getChildClosestToEnd() {
        return getChildAt(this.mShouldReverseLayout ? 0 : getChildCount() - 1);
    }

    private View getChildClosestToStart() {
        return getChildAt(this.mShouldReverseLayout ? getChildCount() - 1 : 0);
    }

    private void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (layoutState.mRecycle && !layoutState.mInfinite) {
            if (layoutState.mLayoutDirection == -1) {
                int i = layoutState.mScrollingOffset;
                int childCount = getChildCount();
                if (i >= 0) {
                    int end = this.mOrientationHelper.getEnd() - i;
                    if (this.mShouldReverseLayout) {
                        for (int i2 = 0; i2 < childCount; i2++) {
                            View childAt = getChildAt(i2);
                            if (this.mOrientationHelper.getDecoratedStart(childAt) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt) < end) {
                                recycleChildren(recycler, 0, i2);
                                return;
                            }
                        }
                        return;
                    }
                    int i3 = childCount - 1;
                    for (int i4 = i3; i4 >= 0; i4--) {
                        View childAt2 = getChildAt(i4);
                        if (this.mOrientationHelper.getDecoratedStart(childAt2) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt2) < end) {
                            recycleChildren(recycler, i3, i4);
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            int i5 = layoutState.mScrollingOffset;
            if (i5 >= 0) {
                int childCount2 = getChildCount();
                if (this.mShouldReverseLayout) {
                    int i6 = childCount2 - 1;
                    for (int i7 = i6; i7 >= 0; i7--) {
                        View childAt3 = getChildAt(i7);
                        if (this.mOrientationHelper.getDecoratedEnd(childAt3) > i5 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt3) > i5) {
                            recycleChildren(recycler, i6, i7);
                            return;
                        }
                    }
                    return;
                }
                for (int i8 = 0; i8 < childCount2; i8++) {
                    View childAt4 = getChildAt(i8);
                    if (this.mOrientationHelper.getDecoratedEnd(childAt4) > i5 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt4) > i5) {
                        recycleChildren(recycler, 0, i8);
                        return;
                    }
                }
            }
        }
    }

    private void recycleChildren(RecyclerView.Recycler recycler, int i, int i2) {
        if (i != i2) {
            if (i2 > i) {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    removeAndRecycleViewAt(i3, recycler);
                }
                return;
            }
            while (i > i2) {
                removeAndRecycleViewAt(i, recycler);
                i--;
            }
        }
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    private void updateLayoutState(int i, int i2, boolean z, RecyclerView.State state) {
        int i3;
        this.mLayoutState.mInfinite = resolveIsInfinite();
        this.mLayoutState.mExtra = getExtraLayoutSpace(state);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mLayoutDirection = i;
        int i4 = -1;
        if (i == 1) {
            layoutState.mExtra = this.mOrientationHelper.getEndPadding() + layoutState.mExtra;
            View childClosestToEnd = getChildClosestToEnd();
            LayoutState layoutState2 = this.mLayoutState;
            if (!this.mShouldReverseLayout) {
                i4 = 1;
            }
            layoutState2.mItemDirection = i4;
            LayoutState layoutState3 = this.mLayoutState;
            int position = getPosition(childClosestToEnd);
            LayoutState layoutState4 = this.mLayoutState;
            layoutState3.mCurrentPosition = position + layoutState4.mItemDirection;
            layoutState4.mOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
            i3 = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd) - this.mOrientationHelper.getEndAfterPadding();
        } else {
            View childClosestToStart = getChildClosestToStart();
            LayoutState layoutState5 = this.mLayoutState;
            layoutState5.mExtra = this.mOrientationHelper.getStartAfterPadding() + layoutState5.mExtra;
            LayoutState layoutState6 = this.mLayoutState;
            if (this.mShouldReverseLayout) {
                i4 = 1;
            }
            layoutState6.mItemDirection = i4;
            LayoutState layoutState7 = this.mLayoutState;
            int position2 = getPosition(childClosestToStart);
            LayoutState layoutState8 = this.mLayoutState;
            layoutState7.mCurrentPosition = position2 + layoutState8.mItemDirection;
            layoutState8.mOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart);
            i3 = (-this.mOrientationHelper.getDecoratedStart(childClosestToStart)) + this.mOrientationHelper.getStartAfterPadding();
        }
        LayoutState layoutState9 = this.mLayoutState;
        layoutState9.mAvailable = i2;
        if (z) {
            layoutState9.mAvailable -= i3;
        }
        this.mLayoutState.mScrollingOffset = i3;
    }

    private void updateLayoutStateToFillEnd(int i, int i2) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - i2;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = i;
        layoutState.mLayoutDirection = 1;
        layoutState.mOffset = i2;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillStart(int i, int i2) {
        this.mLayoutState.mAvailable = i2 - this.mOrientationHelper.getStartAfterPadding();
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = i;
        layoutState.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.mLayoutDirection = -1;
        layoutState2.mOffset = i2;
        layoutState2.mScrollingOffset = Integer.MIN_VALUE;
    }

    public void assertNotInLayoutOrScroll(String str) {
        RecyclerView recyclerView;
        if (this.mPendingSavedState == null && (recyclerView = this.mRecyclerView) != null) {
            recyclerView.assertNotInLayoutOrScroll(str);
        }
    }

    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            ensureLayoutState();
            updateLayoutState(i > 0 ? 1 : -1, Math.abs(i), true, state);
            collectPrefetchPositionsForLayoutState(state, this.mLayoutState, layoutPrefetchRegistry);
        }
    }

    public void collectInitialPrefetchPositions(int i, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        boolean z;
        int i2;
        SavedState savedState = this.mPendingSavedState;
        int i3 = -1;
        if (savedState == null || !savedState.hasValidAnchor()) {
            resolveShouldLayoutReverse();
            z = this.mShouldReverseLayout;
            i2 = this.mPendingScrollPosition;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.mPendingSavedState;
            z = savedState2.mAnchorLayoutFromEnd;
            i2 = savedState2.mAnchorPosition;
        }
        if (!z) {
            i3 = 1;
        }
        int i4 = i2;
        for (int i5 = 0; i5 < this.mInitialPrefetchItemCount && i4 >= 0 && i4 < i; i5++) {
            ((GapWorker.LayoutPrefetchRegistryImpl) layoutPrefetchRegistry).addPosition(i4, 0);
            i4 += i3;
        }
    }

    /* access modifiers changed from: package-private */
    public void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i = layoutState.mCurrentPosition;
        if (i >= 0 && i < state.getItemCount()) {
            ((GapWorker.LayoutPrefetchRegistryImpl) layoutPrefetchRegistry).addPosition(i, Math.max(0, layoutState.mScrollingOffset));
        }
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        boolean z = false;
        int i2 = 1;
        if (i < getPosition(getChildAt(0))) {
            z = true;
        }
        if (z != this.mShouldReverseLayout) {
            i2 = -1;
        }
        if (this.mOrientation == 0) {
            return new PointF((float) i2, 0.0f);
        }
        return new PointF(0.0f, (float) i2);
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    /* access modifiers changed from: package-private */
    public int convertFocusDirectionToLayoutDirection(int i) {
        if (i == 1) {
            return (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
        }
        if (i == 2) {
            return (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1;
        }
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i != 130) {
                        return Integer.MIN_VALUE;
                    }
                    return this.mOrientation == 1 ? 1 : Integer.MIN_VALUE;
                } else if (this.mOrientation == 0) {
                    return 1;
                } else {
                    return Integer.MIN_VALUE;
                }
            } else if (this.mOrientation == 1) {
                return -1;
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (this.mOrientation == 0) {
            return -1;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /* access modifiers changed from: package-private */
    public LayoutState createLayoutState() {
        return new LayoutState();
    }

    /* access modifiers changed from: package-private */
    public void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = createLayoutState();
        }
    }

    /* access modifiers changed from: package-private */
    public int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state, boolean z) {
        int i = layoutState.mAvailable;
        int i2 = layoutState.mScrollingOffset;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                layoutState.mScrollingOffset = i2 + i;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int i3 = layoutState.mAvailable + layoutState.mExtra;
        LayoutChunkResult layoutChunkResult = this.mLayoutChunkResult;
        while (true) {
            if ((!layoutState.mInfinite && i3 <= 0) || !layoutState.hasMore(state)) {
                break;
            }
            layoutChunkResult.mConsumed = 0;
            layoutChunkResult.mFinished = false;
            layoutChunkResult.mIgnoreConsumed = false;
            layoutChunkResult.mFocusable = false;
            layoutChunk(recycler, state, layoutState, layoutChunkResult);
            if (!layoutChunkResult.mFinished) {
                layoutState.mOffset = (layoutChunkResult.mConsumed * layoutState.mLayoutDirection) + layoutState.mOffset;
                if (!layoutChunkResult.mIgnoreConsumed || this.mLayoutState.mScrapList != null || !state.mInPreLayout) {
                    int i4 = layoutState.mAvailable;
                    int i5 = layoutChunkResult.mConsumed;
                    layoutState.mAvailable = i4 - i5;
                    i3 -= i5;
                }
                int i6 = layoutState.mScrollingOffset;
                if (i6 != Integer.MIN_VALUE) {
                    layoutState.mScrollingOffset = i6 + layoutChunkResult.mConsumed;
                    int i7 = layoutState.mAvailable;
                    if (i7 < 0) {
                        layoutState.mScrollingOffset += i7;
                    }
                    recycleByLayoutState(recycler, layoutState);
                }
                if (z && layoutChunkResult.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - layoutState.mAvailable;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), true, false);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findFirstVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), false, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    /* access modifiers changed from: package-private */
    public View findOnePartiallyOrCompletelyInvisibleChild(int i, int i2) {
        int i3;
        int i4;
        ensureLayoutState();
        if ((i2 > i ? 1 : i2 < i ? (char) 65535 : 0) == 0) {
            return getChildAt(i);
        }
        if (this.mOrientationHelper.getDecoratedStart(getChildAt(i)) < this.mOrientationHelper.getStartAfterPadding()) {
            i4 = 16644;
            i3 = 16388;
        } else {
            i4 = 4161;
            i3 = 4097;
        }
        if (this.mOrientation == 0) {
            return this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(i, i2, i4, i3);
        }
        return this.mVerticalBoundCheck.findOneViewWithinBoundFlags(i, i2, i4, i3);
    }

    /* access modifiers changed from: package-private */
    public View findOneVisibleChild(int i, int i2, boolean z, boolean z2) {
        ensureLayoutState();
        int i3 = 320;
        int i4 = z ? 24579 : 320;
        if (!z2) {
            i3 = 0;
        }
        if (this.mOrientation == 0) {
            return this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(i, i2, i4, i3);
        }
        return this.mVerticalBoundCheck.findOneViewWithinBoundFlags(i, i2, i4, i3);
    }

    /* access modifiers changed from: package-private */
    public View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, int i3) {
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(childAt) < endAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) >= startAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i) {
                return childAt;
            }
        }
        int childCount2 = getChildCount();
        for (int i2 = 0; i2 < childCount2; i2++) {
            View childAt2 = getChildAt(i2);
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt2);
            if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.mInPreLayout || !childViewHolderInt.isRemoved())) {
                return childAt2;
            }
        }
        return null;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public int getExtraLayoutSpace(RecyclerView.State state) {
        if (state.hasTargetScrollPosition()) {
            return this.mOrientationHelper.getTotalSpace();
        }
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean isAutoMeasureEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    /* access modifiers changed from: package-private */
    public void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        View next = layoutState.next(recycler);
        if (next == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) next.getLayoutParams();
        if (layoutState.mScrapList == null) {
            if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
                addView(next);
            } else {
                addView(next, 0);
            }
        } else {
            if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
                addDisappearingView(next);
            } else {
                addDisappearingView(next, 0);
            }
        }
        measureChildWithMargins(next, 0, 0);
        layoutChunkResult.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(next);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                i5 = getWidth() - getPaddingRight();
                i4 = i5 - this.mOrientationHelper.getDecoratedMeasurementInOther(next);
            } else {
                i4 = getPaddingLeft();
                i5 = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + i4;
            }
            if (layoutState.mLayoutDirection == -1) {
                int i6 = layoutState.mOffset;
                i = i6;
                i2 = i5;
                i3 = i6 - layoutChunkResult.mConsumed;
            } else {
                int i7 = layoutState.mOffset;
                i3 = i7;
                i2 = i5;
                i = layoutChunkResult.mConsumed + i7;
            }
        } else {
            int paddingTop = getPaddingTop();
            int decoratedMeasurementInOther = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + paddingTop;
            if (layoutState.mLayoutDirection == -1) {
                int i8 = layoutState.mOffset;
                i2 = i8;
                i3 = paddingTop;
                i = decoratedMeasurementInOther;
                i4 = i8 - layoutChunkResult.mConsumed;
            } else {
                int i9 = layoutState.mOffset;
                i3 = paddingTop;
                i2 = layoutChunkResult.mConsumed + i9;
                i = decoratedMeasurementInOther;
                i4 = i9;
            }
        }
        layoutDecoratedWithMargins(next, i4, i3, i2, i);
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            layoutChunkResult.mIgnoreConsumed = true;
        }
        layoutChunkResult.mFocusable = next.hasFocusable();
    }

    /* access modifiers changed from: package-private */
    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo, int i) {
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        onDetachedFromWindow(recyclerView);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int convertFocusDirectionToLayoutDirection;
        View view2;
        View view3;
        resolveShouldLayoutReverse();
        if (getChildCount() == 0 || (convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i)) == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        ensureLayoutState();
        updateLayoutState(convertFocusDirectionToLayoutDirection, (int) (((float) this.mOrientationHelper.getTotalSpace()) * 0.33333334f), false, state);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
        layoutState.mRecycle = false;
        fill(recycler, layoutState, state, true);
        if (convertFocusDirectionToLayoutDirection == -1) {
            if (this.mShouldReverseLayout) {
                view2 = findLastPartiallyOrCompletelyInvisibleChild();
            } else {
                view2 = findFirstPartiallyOrCompletelyInvisibleChild();
            }
        } else if (this.mShouldReverseLayout) {
            view2 = findFirstPartiallyOrCompletelyInvisibleChild();
        } else {
            view2 = findLastPartiallyOrCompletelyInvisibleChild();
        }
        if (convertFocusDirectionToLayoutDirection == -1) {
            view3 = getChildClosestToStart();
        } else {
            view3 = getChildClosestToEnd();
        }
        if (!view3.hasFocusable()) {
            return view2;
        }
        if (view2 == null) {
            return null;
        }
        return view3;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        onInitializeAccessibilityEvent(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0184  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayoutChildren(android.support.p002v7.widget.RecyclerView.Recycler r17, android.support.p002v7.widget.RecyclerView.State r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            android.support.v7.widget.LinearLayoutManager$SavedState r3 = r0.mPendingSavedState
            r4 = -1
            if (r3 != 0) goto L_0x000f
            int r3 = r0.mPendingScrollPosition
            if (r3 == r4) goto L_0x0019
        L_0x000f:
            int r3 = r18.getItemCount()
            if (r3 != 0) goto L_0x0019
            r16.removeAndRecycleAllViews(r17)
            return
        L_0x0019:
            android.support.v7.widget.LinearLayoutManager$SavedState r3 = r0.mPendingSavedState
            if (r3 == 0) goto L_0x0029
            boolean r3 = r3.hasValidAnchor()
            if (r3 == 0) goto L_0x0029
            android.support.v7.widget.LinearLayoutManager$SavedState r3 = r0.mPendingSavedState
            int r3 = r3.mAnchorPosition
            r0.mPendingScrollPosition = r3
        L_0x0029:
            r16.ensureLayoutState()
            android.support.v7.widget.LinearLayoutManager$LayoutState r3 = r0.mLayoutState
            r5 = 0
            r3.mRecycle = r5
            r16.resolveShouldLayoutReverse()
            android.view.View r3 = r16.getFocusedChild()
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r6 = r0.mAnchorInfo
            boolean r6 = r6.mValid
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = 1
            if (r6 == 0) goto L_0x0073
            int r6 = r0.mPendingScrollPosition
            if (r6 != r4) goto L_0x0073
            android.support.v7.widget.LinearLayoutManager$SavedState r6 = r0.mPendingSavedState
            if (r6 == 0) goto L_0x004a
            goto L_0x0073
        L_0x004a:
            if (r3 == 0) goto L_0x022a
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getDecoratedStart(r3)
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getEndAfterPadding()
            if (r6 >= r9) goto L_0x0068
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getDecoratedEnd(r3)
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getStartAfterPadding()
            if (r6 > r9) goto L_0x022a
        L_0x0068:
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r6 = r0.mAnchorInfo
            int r9 = r0.getPosition(r3)
            r6.assignFromViewAndKeepVisibleRect(r3, r9)
            goto L_0x022a
        L_0x0073:
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r3 = r0.mAnchorInfo
            r3.reset()
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r3 = r0.mAnchorInfo
            boolean r6 = r0.mShouldReverseLayout
            boolean r9 = r0.mStackFromEnd
            r6 = r6 ^ r9
            r3.mLayoutFromEnd = r6
            boolean r6 = r2.mInPreLayout
            if (r6 != 0) goto L_0x017f
            int r6 = r0.mPendingScrollPosition
            if (r6 != r4) goto L_0x008b
            goto L_0x017f
        L_0x008b:
            if (r6 < 0) goto L_0x017b
            int r9 = r18.getItemCount()
            if (r6 < r9) goto L_0x0095
            goto L_0x017b
        L_0x0095:
            int r6 = r0.mPendingScrollPosition
            r3.mPosition = r6
            android.support.v7.widget.LinearLayoutManager$SavedState r6 = r0.mPendingSavedState
            if (r6 == 0) goto L_0x00cb
            boolean r6 = r6.hasValidAnchor()
            if (r6 == 0) goto L_0x00cb
            android.support.v7.widget.LinearLayoutManager$SavedState r6 = r0.mPendingSavedState
            boolean r6 = r6.mAnchorLayoutFromEnd
            r3.mLayoutFromEnd = r6
            boolean r6 = r3.mLayoutFromEnd
            if (r6 == 0) goto L_0x00bc
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getEndAfterPadding()
            android.support.v7.widget.LinearLayoutManager$SavedState r9 = r0.mPendingSavedState
            int r9 = r9.mAnchorOffset
            int r6 = r6 - r9
            r3.mCoordinate = r6
            goto L_0x0179
        L_0x00bc:
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getStartAfterPadding()
            android.support.v7.widget.LinearLayoutManager$SavedState r9 = r0.mPendingSavedState
            int r9 = r9.mAnchorOffset
            int r6 = r6 + r9
            r3.mCoordinate = r6
            goto L_0x0179
        L_0x00cb:
            int r6 = r0.mPendingScrollPositionOffset
            if (r6 != r7) goto L_0x015c
            int r6 = r0.mPendingScrollPosition
            android.view.View r6 = r0.findViewByPosition(r6)
            if (r6 == 0) goto L_0x013a
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getDecoratedMeasurement(r6)
            android.support.v7.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r10 = r10.getTotalSpace()
            if (r9 <= r10) goto L_0x00ea
            r3.assignCoordinateFromPadding()
            goto L_0x0179
        L_0x00ea:
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getDecoratedStart(r6)
            android.support.v7.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r10 = r10.getStartAfterPadding()
            int r9 = r9 - r10
            if (r9 >= 0) goto L_0x0105
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getStartAfterPadding()
            r3.mCoordinate = r6
            r3.mLayoutFromEnd = r5
            goto L_0x0179
        L_0x0105:
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getEndAfterPadding()
            android.support.v7.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r10 = r10.getDecoratedEnd(r6)
            int r9 = r9 - r10
            if (r9 >= 0) goto L_0x011f
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getEndAfterPadding()
            r3.mCoordinate = r6
            r3.mLayoutFromEnd = r8
            goto L_0x0179
        L_0x011f:
            boolean r9 = r3.mLayoutFromEnd
            if (r9 == 0) goto L_0x0131
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r6 = r9.getDecoratedEnd(r6)
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getTotalSpaceChange()
            int r9 = r9 + r6
            goto L_0x0137
        L_0x0131:
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getDecoratedStart(r6)
        L_0x0137:
            r3.mCoordinate = r9
            goto L_0x0179
        L_0x013a:
            int r6 = r16.getChildCount()
            if (r6 <= 0) goto L_0x0158
            android.view.View r6 = r0.getChildAt(r5)
            int r6 = r0.getPosition(r6)
            int r9 = r0.mPendingScrollPosition
            if (r9 >= r6) goto L_0x014e
            r6 = r8
            goto L_0x014f
        L_0x014e:
            r6 = r5
        L_0x014f:
            boolean r9 = r0.mShouldReverseLayout
            if (r6 != r9) goto L_0x0155
            r6 = r8
            goto L_0x0156
        L_0x0155:
            r6 = r5
        L_0x0156:
            r3.mLayoutFromEnd = r6
        L_0x0158:
            r3.assignCoordinateFromPadding()
            goto L_0x0179
        L_0x015c:
            boolean r6 = r0.mShouldReverseLayout
            r3.mLayoutFromEnd = r6
            if (r6 == 0) goto L_0x016e
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getEndAfterPadding()
            int r9 = r0.mPendingScrollPositionOffset
            int r6 = r6 - r9
            r3.mCoordinate = r6
            goto L_0x0179
        L_0x016e:
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getStartAfterPadding()
            int r9 = r0.mPendingScrollPositionOffset
            int r6 = r6 + r9
            r3.mCoordinate = r6
        L_0x0179:
            r6 = r8
            goto L_0x0180
        L_0x017b:
            r0.mPendingScrollPosition = r4
            r0.mPendingScrollPositionOffset = r7
        L_0x017f:
            r6 = r5
        L_0x0180:
            if (r6 == 0) goto L_0x0184
            goto L_0x0226
        L_0x0184:
            int r6 = r16.getChildCount()
            if (r6 != 0) goto L_0x018c
            goto L_0x0212
        L_0x018c:
            android.view.View r6 = r16.getFocusedChild()
            if (r6 == 0) goto L_0x01a1
            boolean r9 = r3.isViewValidAsAnchor(r6, r2)
            if (r9 == 0) goto L_0x01a1
            int r9 = r0.getPosition(r6)
            r3.assignFromViewAndKeepVisibleRect(r6, r9)
            goto L_0x0210
        L_0x01a1:
            boolean r6 = r0.mLastStackFromEnd
            boolean r9 = r0.mStackFromEnd
            if (r6 == r9) goto L_0x01a9
            goto L_0x0212
        L_0x01a9:
            boolean r6 = r3.mLayoutFromEnd
            if (r6 == 0) goto L_0x01bb
            boolean r6 = r0.mShouldReverseLayout
            if (r6 == 0) goto L_0x01b6
            android.view.View r6 = r16.findFirstReferenceChild(r17, r18)
            goto L_0x01c8
        L_0x01b6:
            android.view.View r6 = r16.findLastReferenceChild(r17, r18)
            goto L_0x01c8
        L_0x01bb:
            boolean r6 = r0.mShouldReverseLayout
            if (r6 == 0) goto L_0x01c4
            android.view.View r6 = r16.findLastReferenceChild(r17, r18)
            goto L_0x01c8
        L_0x01c4:
            android.view.View r6 = r16.findFirstReferenceChild(r17, r18)
        L_0x01c8:
            if (r6 == 0) goto L_0x0212
            int r9 = r0.getPosition(r6)
            r3.assignFromView(r6, r9)
            boolean r9 = r2.mInPreLayout
            if (r9 != 0) goto L_0x0210
            boolean r9 = r16.supportsPredictiveItemAnimations()
            if (r9 == 0) goto L_0x0210
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getDecoratedStart(r6)
            android.support.v7.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r10 = r10.getEndAfterPadding()
            if (r9 >= r10) goto L_0x01fa
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r6 = r9.getDecoratedEnd(r6)
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getStartAfterPadding()
            if (r6 >= r9) goto L_0x01f8
            goto L_0x01fa
        L_0x01f8:
            r6 = r5
            goto L_0x01fb
        L_0x01fa:
            r6 = r8
        L_0x01fb:
            if (r6 == 0) goto L_0x0210
            boolean r6 = r3.mLayoutFromEnd
            if (r6 == 0) goto L_0x0208
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getEndAfterPadding()
            goto L_0x020e
        L_0x0208:
            android.support.v7.widget.OrientationHelper r6 = r0.mOrientationHelper
            int r6 = r6.getStartAfterPadding()
        L_0x020e:
            r3.mCoordinate = r6
        L_0x0210:
            r6 = r8
            goto L_0x0213
        L_0x0212:
            r6 = r5
        L_0x0213:
            if (r6 == 0) goto L_0x0216
            goto L_0x0226
        L_0x0216:
            r3.assignCoordinateFromPadding()
            boolean r6 = r0.mStackFromEnd
            if (r6 == 0) goto L_0x0223
            int r6 = r18.getItemCount()
            int r6 = r6 + r4
            goto L_0x0224
        L_0x0223:
            r6 = r5
        L_0x0224:
            r3.mPosition = r6
        L_0x0226:
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r3 = r0.mAnchorInfo
            r3.mValid = r8
        L_0x022a:
            int r3 = r0.getExtraLayoutSpace(r2)
            android.support.v7.widget.LinearLayoutManager$LayoutState r6 = r0.mLayoutState
            int r6 = r6.mLastScrollDelta
            if (r6 < 0) goto L_0x0237
            r6 = r3
            r3 = r5
            goto L_0x0238
        L_0x0237:
            r6 = r5
        L_0x0238:
            android.support.v7.widget.OrientationHelper r9 = r0.mOrientationHelper
            int r9 = r9.getStartAfterPadding()
            int r9 = r9 + r3
            android.support.v7.widget.OrientationHelper r3 = r0.mOrientationHelper
            int r3 = r3.getEndPadding()
            int r3 = r3 + r6
            boolean r6 = r2.mInPreLayout
            if (r6 == 0) goto L_0x0281
            int r6 = r0.mPendingScrollPosition
            if (r6 == r4) goto L_0x0281
            int r10 = r0.mPendingScrollPositionOffset
            if (r10 == r7) goto L_0x0281
            android.view.View r6 = r0.findViewByPosition(r6)
            if (r6 == 0) goto L_0x0281
            boolean r7 = r0.mShouldReverseLayout
            if (r7 == 0) goto L_0x026c
            android.support.v7.widget.OrientationHelper r7 = r0.mOrientationHelper
            int r7 = r7.getEndAfterPadding()
            android.support.v7.widget.OrientationHelper r10 = r0.mOrientationHelper
            int r6 = r10.getDecoratedEnd(r6)
            int r7 = r7 - r6
            int r6 = r0.mPendingScrollPositionOffset
            goto L_0x027b
        L_0x026c:
            android.support.v7.widget.OrientationHelper r7 = r0.mOrientationHelper
            int r6 = r7.getDecoratedStart(r6)
            android.support.v7.widget.OrientationHelper r7 = r0.mOrientationHelper
            int r7 = r7.getStartAfterPadding()
            int r6 = r6 - r7
            int r7 = r0.mPendingScrollPositionOffset
        L_0x027b:
            int r7 = r7 - r6
            if (r7 <= 0) goto L_0x0280
            int r9 = r9 + r7
            goto L_0x0281
        L_0x0280:
            int r3 = r3 - r7
        L_0x0281:
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r6 = r0.mAnchorInfo
            boolean r6 = r6.mLayoutFromEnd
            if (r6 == 0) goto L_0x028c
            boolean r6 = r0.mShouldReverseLayout
            if (r6 == 0) goto L_0x0290
            goto L_0x0292
        L_0x028c:
            boolean r6 = r0.mShouldReverseLayout
            if (r6 == 0) goto L_0x0292
        L_0x0290:
            r6 = r4
            goto L_0x0293
        L_0x0292:
            r6 = r8
        L_0x0293:
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r7 = r0.mAnchorInfo
            r0.onAnchorReady(r1, r2, r7, r6)
            r16.detachAndScrapAttachedViews(r17)
            android.support.v7.widget.LinearLayoutManager$LayoutState r6 = r0.mLayoutState
            boolean r7 = r16.resolveIsInfinite()
            r6.mInfinite = r7
            boolean r6 = r2.mInPreLayout
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r6 = r0.mAnchorInfo
            boolean r7 = r6.mLayoutFromEnd
            if (r7 == 0) goto L_0x02f3
            int r7 = r6.mPosition
            int r6 = r6.mCoordinate
            r0.updateLayoutStateToFillStart(r7, r6)
            android.support.v7.widget.LinearLayoutManager$LayoutState r6 = r0.mLayoutState
            r6.mExtra = r9
            r0.fill(r1, r6, r2, r5)
            android.support.v7.widget.LinearLayoutManager$LayoutState r6 = r0.mLayoutState
            int r7 = r6.mOffset
            int r9 = r6.mCurrentPosition
            int r6 = r6.mAvailable
            if (r6 <= 0) goto L_0x02c4
            int r3 = r3 + r6
        L_0x02c4:
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r6 = r0.mAnchorInfo
            int r10 = r6.mPosition
            int r6 = r6.mCoordinate
            r0.updateLayoutStateToFillEnd(r10, r6)
            android.support.v7.widget.LinearLayoutManager$LayoutState r6 = r0.mLayoutState
            r6.mExtra = r3
            int r3 = r6.mCurrentPosition
            int r10 = r6.mItemDirection
            int r3 = r3 + r10
            r6.mCurrentPosition = r3
            r0.fill(r1, r6, r2, r5)
            android.support.v7.widget.LinearLayoutManager$LayoutState r3 = r0.mLayoutState
            int r6 = r3.mOffset
            int r3 = r3.mAvailable
            if (r3 <= 0) goto L_0x02f1
            r0.updateLayoutStateToFillStart(r9, r7)
            android.support.v7.widget.LinearLayoutManager$LayoutState r7 = r0.mLayoutState
            r7.mExtra = r3
            r0.fill(r1, r7, r2, r5)
            android.support.v7.widget.LinearLayoutManager$LayoutState r3 = r0.mLayoutState
            int r7 = r3.mOffset
        L_0x02f1:
            r9 = r7
            goto L_0x0339
        L_0x02f3:
            int r7 = r6.mPosition
            int r6 = r6.mCoordinate
            r0.updateLayoutStateToFillEnd(r7, r6)
            android.support.v7.widget.LinearLayoutManager$LayoutState r6 = r0.mLayoutState
            r6.mExtra = r3
            r0.fill(r1, r6, r2, r5)
            android.support.v7.widget.LinearLayoutManager$LayoutState r3 = r0.mLayoutState
            int r6 = r3.mOffset
            int r7 = r3.mCurrentPosition
            int r3 = r3.mAvailable
            if (r3 <= 0) goto L_0x030c
            int r9 = r9 + r3
        L_0x030c:
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r3 = r0.mAnchorInfo
            int r10 = r3.mPosition
            int r3 = r3.mCoordinate
            r0.updateLayoutStateToFillStart(r10, r3)
            android.support.v7.widget.LinearLayoutManager$LayoutState r3 = r0.mLayoutState
            r3.mExtra = r9
            int r9 = r3.mCurrentPosition
            int r10 = r3.mItemDirection
            int r9 = r9 + r10
            r3.mCurrentPosition = r9
            r0.fill(r1, r3, r2, r5)
            android.support.v7.widget.LinearLayoutManager$LayoutState r3 = r0.mLayoutState
            int r9 = r3.mOffset
            int r3 = r3.mAvailable
            if (r3 <= 0) goto L_0x0339
            r0.updateLayoutStateToFillEnd(r7, r6)
            android.support.v7.widget.LinearLayoutManager$LayoutState r6 = r0.mLayoutState
            r6.mExtra = r3
            r0.fill(r1, r6, r2, r5)
            android.support.v7.widget.LinearLayoutManager$LayoutState r3 = r0.mLayoutState
            int r6 = r3.mOffset
        L_0x0339:
            int r3 = r16.getChildCount()
            if (r3 <= 0) goto L_0x035d
            boolean r3 = r0.mShouldReverseLayout
            boolean r7 = r0.mStackFromEnd
            r3 = r3 ^ r7
            if (r3 == 0) goto L_0x0351
            int r3 = r0.fixLayoutEndGap(r6, r1, r2, r8)
            int r9 = r9 + r3
            int r6 = r6 + r3
            int r3 = r0.fixLayoutStartGap(r9, r1, r2, r5)
            goto L_0x035b
        L_0x0351:
            int r3 = r0.fixLayoutStartGap(r9, r1, r2, r8)
            int r9 = r9 + r3
            int r6 = r6 + r3
            int r3 = r0.fixLayoutEndGap(r6, r1, r2, r5)
        L_0x035b:
            int r9 = r9 + r3
            int r6 = r6 + r3
        L_0x035d:
            boolean r3 = r2.mRunPredictiveAnimations
            if (r3 == 0) goto L_0x03ff
            int r3 = r16.getChildCount()
            if (r3 == 0) goto L_0x03ff
            boolean r3 = r2.mInPreLayout
            if (r3 != 0) goto L_0x03ff
            boolean r3 = r16.supportsPredictiveItemAnimations()
            if (r3 != 0) goto L_0x0373
            goto L_0x03ff
        L_0x0373:
            java.util.List r3 = r17.getScrapList()
            int r7 = r3.size()
            android.view.View r10 = r0.getChildAt(r5)
            int r10 = r0.getPosition(r10)
            r11 = r5
            r12 = r11
            r13 = r12
        L_0x0386:
            if (r11 >= r7) goto L_0x03c0
            java.lang.Object r14 = r3.get(r11)
            android.support.v7.widget.RecyclerView$ViewHolder r14 = (android.support.p002v7.widget.RecyclerView.ViewHolder) r14
            boolean r15 = r14.isRemoved()
            if (r15 == 0) goto L_0x0395
            goto L_0x03bc
        L_0x0395:
            int r15 = r14.getLayoutPosition()
            if (r15 >= r10) goto L_0x039d
            r15 = r8
            goto L_0x039e
        L_0x039d:
            r15 = r5
        L_0x039e:
            boolean r8 = r0.mShouldReverseLayout
            if (r15 == r8) goto L_0x03a4
            r8 = r4
            goto L_0x03a5
        L_0x03a4:
            r8 = 1
        L_0x03a5:
            if (r8 != r4) goto L_0x03b2
            android.support.v7.widget.OrientationHelper r8 = r0.mOrientationHelper
            android.view.View r14 = r14.itemView
            int r8 = r8.getDecoratedMeasurement(r14)
            int r8 = r8 + r12
            r12 = r8
            goto L_0x03bc
        L_0x03b2:
            android.support.v7.widget.OrientationHelper r8 = r0.mOrientationHelper
            android.view.View r14 = r14.itemView
            int r8 = r8.getDecoratedMeasurement(r14)
            int r8 = r8 + r13
            r13 = r8
        L_0x03bc:
            int r11 = r11 + 1
            r8 = 1
            goto L_0x0386
        L_0x03c0:
            android.support.v7.widget.LinearLayoutManager$LayoutState r4 = r0.mLayoutState
            r4.mScrapList = r3
            r3 = 0
            if (r12 <= 0) goto L_0x03e0
            android.view.View r4 = r16.getChildClosestToStart()
            int r4 = r0.getPosition(r4)
            r0.updateLayoutStateToFillStart(r4, r9)
            android.support.v7.widget.LinearLayoutManager$LayoutState r4 = r0.mLayoutState
            r4.mExtra = r12
            r4.mAvailable = r5
            r4.assignPositionFromScrapList(r3)
            android.support.v7.widget.LinearLayoutManager$LayoutState r4 = r0.mLayoutState
            r0.fill(r1, r4, r2, r5)
        L_0x03e0:
            if (r13 <= 0) goto L_0x03fb
            android.view.View r4 = r16.getChildClosestToEnd()
            int r4 = r0.getPosition(r4)
            r0.updateLayoutStateToFillEnd(r4, r6)
            android.support.v7.widget.LinearLayoutManager$LayoutState r4 = r0.mLayoutState
            r4.mExtra = r13
            r4.mAvailable = r5
            r4.assignPositionFromScrapList(r3)
            android.support.v7.widget.LinearLayoutManager$LayoutState r4 = r0.mLayoutState
            r0.fill(r1, r4, r2, r5)
        L_0x03fb:
            android.support.v7.widget.LinearLayoutManager$LayoutState r1 = r0.mLayoutState
            r1.mScrapList = r3
        L_0x03ff:
            boolean r1 = r2.mInPreLayout
            if (r1 != 0) goto L_0x0409
            android.support.v7.widget.OrientationHelper r1 = r0.mOrientationHelper
            r1.onLayoutComplete()
            goto L_0x040e
        L_0x0409:
            android.support.v7.widget.LinearLayoutManager$AnchorInfo r1 = r0.mAnchorInfo
            r1.reset()
        L_0x040e:
            boolean r1 = r0.mStackFromEnd
            r0.mLastStackFromEnd = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.LinearLayoutManager.onLayoutChildren(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):void");
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.reset();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean z = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            savedState2.mAnchorLayoutFromEnd = z;
            if (z) {
                View childClosestToEnd = getChildClosestToEnd();
                savedState2.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
                savedState2.mAnchorPosition = getPosition(childClosestToEnd);
            } else {
                View childClosestToStart = getChildClosestToStart();
                savedState2.mAnchorPosition = getPosition(childClosestToStart);
                savedState2.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
            }
        } else {
            savedState2.invalidateAnchor();
        }
        return savedState2;
    }

    public void prepareForDrop(View view, View view2, int i, int i2) {
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        resolveShouldLayoutReverse();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        boolean z = position < position2 ? true : true;
        if (this.mShouldReverseLayout) {
            if (z) {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedMeasurement(view) + this.mOrientationHelper.getDecoratedStart(view2)));
            } else {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(view2));
            }
        } else if (z) {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.getDecoratedStart(view2));
        } else {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.getDecoratedEnd(view2) - this.mOrientationHelper.getDecoratedMeasurement(view));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean resolveIsInfinite() {
        return this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0;
    }

    /* access modifiers changed from: package-private */
    public int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        this.mLayoutState.mRecycle = true;
        ensureLayoutState();
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        updateLayoutState(i2, abs, true, state);
        LayoutState layoutState = this.mLayoutState;
        int fill = layoutState.mScrollingOffset + fill(recycler, layoutState, state, false);
        if (fill < 0) {
            return 0;
        }
        if (abs > fill) {
            i = i2 * fill;
        }
        this.mOrientationHelper.offsetChildren(-i);
        this.mLayoutState.mLastScrollDelta = i;
        return i;
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    public void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = i2;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        requestLayout();
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (i != this.mOrientation || this.mOrientationHelper == null) {
                this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, i);
                this.mAnchorInfo.mOrientationHelper = this.mOrientationHelper;
                this.mOrientation = i;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("invalid orientation:", i));
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll((String) null);
        if (z != this.mReverseLayout) {
            this.mReverseLayout = z;
            requestLayout();
        }
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll((String) null);
        if (this.mStackFromEnd != z) {
            this.mStackFromEnd = z;
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldMeasureTwice() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !hasFlexibleChildInBothOrientations()) ? false : true;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        setOrientation(i);
        setReverseLayout(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        setReverseLayout(properties.reverseLayout);
        setStackFromEnd(properties.stackFromEnd);
    }
}
