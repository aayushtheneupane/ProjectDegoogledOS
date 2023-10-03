package android.support.p002v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p002v7.widget.GapWorker;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* renamed from: android.support.v7.widget.StaggeredGridLayoutManager */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    private final AnchorInfo mAnchorInfo = new AnchorInfo();
    private final Runnable mCheckForGapsRunnable = new Runnable() {
        public void run() {
            StaggeredGridLayoutManager.this.checkForGaps();
        }
    };
    private int mFullSizeSpec;
    private int mGapStrategy = 2;
    private boolean mLaidOutInvalidFullSpan = false;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    private final LayoutState mLayoutState;
    LazySpanLookup mLazySpanLookup = new LazySpanLookup();
    private int mOrientation;
    private SavedState mPendingSavedState;
    int mPendingScrollPosition = -1;
    int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    private int[] mPrefetchDistances;
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    boolean mReverseLayout = false;
    OrientationHelper mSecondaryOrientation;
    boolean mShouldReverseLayout = false;
    private int mSizePerSpan;
    private boolean mSmoothScrollbarEnabled = true;
    private int mSpanCount = -1;
    Span[] mSpans;
    private final Rect mTmpRect = new Rect();

    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$AnchorInfo */
    class AnchorInfo {
        boolean mInvalidateOffsets;
        boolean mLayoutFromEnd;
        int mOffset;
        int mPosition;
        int[] mSpanReferenceLines;
        boolean mValid;

        AnchorInfo() {
            reset();
        }

        /* access modifiers changed from: package-private */
        public void assignCoordinateFromPadding() {
            int i;
            if (this.mLayoutFromEnd) {
                i = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            } else {
                i = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            }
            this.mOffset = i;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
            this.mValid = false;
            int[] iArr = this.mSpanReferenceLines;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        /* access modifiers changed from: package-private */
        public void saveSpanReferenceLines(Span[] spanArr) {
            int length = spanArr.length;
            int[] iArr = this.mSpanReferenceLines;
            if (iArr == null || iArr.length < length) {
                this.mSpanReferenceLines = new int[StaggeredGridLayoutManager.this.mSpans.length];
            }
            for (int i = 0; i < length; i++) {
                this.mSpanReferenceLines[i] = spanArr[i].getStartLine(Integer.MIN_VALUE);
            }
        }
    }

    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        boolean mFullSpan;
        Span mSpan;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$SavedState */
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
        int mAnchorPosition;
        List<LazySpanLookup.FullSpanItem> mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int[] mSpanLookup;
        int mSpanLookupSize;
        int[] mSpanOffsets;
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;

        public SavedState() {
        }

        public int describeContents() {
            return 0;
        }

        /* access modifiers changed from: package-private */
        public void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }

        /* access modifiers changed from: package-private */
        public void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mVisibleAnchorPosition);
            parcel.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                parcel.writeIntArray(this.mSpanOffsets);
            }
            parcel.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                parcel.writeIntArray(this.mSpanLookup);
            }
            parcel.writeInt(this.mReverseLayout ? 1 : 0);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
            parcel.writeInt(this.mLastLayoutRTL ? 1 : 0);
            parcel.writeList(this.mFullSpanItems);
        }

        SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mVisibleAnchorPosition = parcel.readInt();
            this.mSpanOffsetsSize = parcel.readInt();
            int i = this.mSpanOffsetsSize;
            if (i > 0) {
                this.mSpanOffsets = new int[i];
                parcel.readIntArray(this.mSpanOffsets);
            }
            this.mSpanLookupSize = parcel.readInt();
            int i2 = this.mSpanLookupSize;
            if (i2 > 0) {
                this.mSpanLookup = new int[i2];
                parcel.readIntArray(this.mSpanLookup);
            }
            boolean z = false;
            this.mReverseLayout = parcel.readInt() == 1;
            this.mAnchorLayoutFromEnd = parcel.readInt() == 1;
            this.mLastLayoutRTL = parcel.readInt() == 1 ? true : z;
            this.mFullSpanItems = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.mSpanOffsetsSize = savedState.mSpanOffsetsSize;
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mVisibleAnchorPosition = savedState.mVisibleAnchorPosition;
            this.mSpanOffsets = savedState.mSpanOffsets;
            this.mSpanLookupSize = savedState.mSpanLookupSize;
            this.mSpanLookup = savedState.mSpanLookup;
            this.mReverseLayout = savedState.mReverseLayout;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = savedState.mLastLayoutRTL;
            this.mFullSpanItems = savedState.mFullSpanItems;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        this.mLayoutState = new LayoutState();
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    private int calculateScrollDirectionForPosition(int i) {
        if (getChildCount() != 0) {
            if ((i < getFirstChildPosition()) != this.mShouldReverseLayout) {
                return -1;
            }
            return 1;
        } else if (this.mShouldReverseLayout) {
            return 1;
        } else {
            return -1;
        }
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return TooltipCompat.computeScrollExtent(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return TooltipCompat.computeScrollOffset(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return TooltipCompat.computeScrollRange(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r10v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r10v4 */
    private int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state) {
        int i;
        int i2;
        int i3;
        Span span;
        int i4;
        int i5;
        int i6;
        int i7;
        LayoutParams layoutParams;
        boolean z;
        int i8;
        int i9;
        boolean z2;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        RecyclerView.Recycler recycler2 = recycler;
        LayoutState layoutState2 = layoutState;
        ? r10 = 0;
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        if (this.mLayoutState.mInfinite) {
            i = layoutState2.mLayoutDirection == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            if (layoutState2.mLayoutDirection == 1) {
                i15 = layoutState2.mEndLine + layoutState2.mAvailable;
            } else {
                i15 = layoutState2.mStartLine - layoutState2.mAvailable;
            }
            i = i15;
        }
        updateAllRemainingSpans(layoutState2.mLayoutDirection, i);
        if (this.mShouldReverseLayout) {
            i2 = this.mPrimaryOrientation.getEndAfterPadding();
        } else {
            i2 = this.mPrimaryOrientation.getStartAfterPadding();
        }
        int i16 = i2;
        boolean z3 = false;
        while (true) {
            int i17 = layoutState2.mCurrentPosition;
            if (!((i17 < 0 || i17 >= state.getItemCount()) ? r10 : true) || (!this.mLayoutState.mInfinite && this.mRemainingSpans.isEmpty())) {
                RecyclerView.Recycler recycler3 = recycler2;
                int i18 = r10;
            } else {
                View view = recycler2.tryGetViewHolderForPositionByDeadline(layoutState2.mCurrentPosition, r10, Long.MAX_VALUE).itemView;
                layoutState2.mCurrentPosition += layoutState2.mItemDirection;
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                int viewLayoutPosition = layoutParams2.getViewLayoutPosition();
                int[] iArr = this.mLazySpanLookup.mData;
                int i19 = (iArr == null || viewLayoutPosition >= iArr.length) ? -1 : iArr[viewLayoutPosition];
                boolean z4 = i19 == -1 ? true : r10;
                if (z4) {
                    if (layoutParams2.mFullSpan) {
                        span = this.mSpans[r10];
                    } else {
                        if (preferLastSpan(layoutState2.mLayoutDirection)) {
                            i14 = this.mSpanCount - 1;
                            i13 = -1;
                            i12 = -1;
                        } else {
                            i13 = this.mSpanCount;
                            i12 = 1;
                            i14 = r10;
                        }
                        Span span2 = null;
                        if (layoutState2.mLayoutDirection == 1) {
                            int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
                            int i20 = Integer.MAX_VALUE;
                            while (i14 != i13) {
                                Span span3 = this.mSpans[i14];
                                int endLine = span3.getEndLine(startAfterPadding);
                                if (endLine < i20) {
                                    span2 = span3;
                                    i20 = endLine;
                                }
                                i14 += i12;
                            }
                        } else {
                            int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
                            int i21 = Integer.MIN_VALUE;
                            while (i14 != i13) {
                                Span span4 = this.mSpans[i14];
                                int startLine = span4.getStartLine(endAfterPadding);
                                if (startLine > i21) {
                                    span2 = span4;
                                    i21 = startLine;
                                }
                                i14 += i12;
                            }
                        }
                        span = span2;
                    }
                    LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
                    lazySpanLookup.ensureSize(viewLayoutPosition);
                    lazySpanLookup.mData[viewLayoutPosition] = span.mIndex;
                } else {
                    span = this.mSpans[i19];
                }
                Span span5 = span;
                layoutParams2.mSpan = span5;
                if (layoutState2.mLayoutDirection == 1) {
                    addView(view);
                } else {
                    addView(view, 0);
                }
                if (layoutParams2.mFullSpan) {
                    if (this.mOrientation == 1) {
                        measureChildWithDecorationsAndMargin(view, this.mFullSizeSpec, RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop(), layoutParams2.height, true), false);
                    } else {
                        measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft(), layoutParams2.width, true), this.mFullSizeSpec, false);
                    }
                } else if (this.mOrientation == 1) {
                    measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getWidthMode(), 0, layoutParams2.width, false), RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop(), layoutParams2.height, true), false);
                } else {
                    measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft(), layoutParams2.width, true), RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getHeightMode(), 0, layoutParams2.height, false), false);
                }
                if (layoutState2.mLayoutDirection == 1) {
                    if (layoutParams2.mFullSpan) {
                        i11 = getMaxEnd(i16);
                    } else {
                        i11 = span5.getEndLine(i16);
                    }
                    int decoratedMeasurement = this.mPrimaryOrientation.getDecoratedMeasurement(view) + i11;
                    if (z4 && layoutParams2.mFullSpan) {
                        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
                        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
                        for (int i22 = 0; i22 < this.mSpanCount; i22++) {
                            fullSpanItem.mGapPerSpan[i22] = i11 - this.mSpans[i22].getEndLine(i11);
                        }
                        fullSpanItem.mGapDir = -1;
                        fullSpanItem.mPosition = viewLayoutPosition;
                        this.mLazySpanLookup.addFullSpanItem(fullSpanItem);
                    }
                    i5 = i11;
                    i4 = decoratedMeasurement;
                } else {
                    if (layoutParams2.mFullSpan) {
                        i10 = getMinStart(i16);
                    } else {
                        i10 = span5.getStartLine(i16);
                    }
                    int decoratedMeasurement2 = i10 - this.mPrimaryOrientation.getDecoratedMeasurement(view);
                    if (z4 && layoutParams2.mFullSpan) {
                        LazySpanLookup.FullSpanItem fullSpanItem2 = new LazySpanLookup.FullSpanItem();
                        fullSpanItem2.mGapPerSpan = new int[this.mSpanCount];
                        for (int i23 = 0; i23 < this.mSpanCount; i23++) {
                            fullSpanItem2.mGapPerSpan[i23] = this.mSpans[i23].getStartLine(i10) - i10;
                        }
                        fullSpanItem2.mGapDir = 1;
                        fullSpanItem2.mPosition = viewLayoutPosition;
                        this.mLazySpanLookup.addFullSpanItem(fullSpanItem2);
                    }
                    i4 = i10;
                    i5 = decoratedMeasurement2;
                }
                if (layoutParams2.mFullSpan && layoutState2.mItemDirection == -1) {
                    if (z4) {
                        this.mLaidOutInvalidFullSpan = true;
                    } else {
                        if (layoutState2.mLayoutDirection == 1) {
                            z2 = areAllEndsEqual();
                        } else {
                            z2 = areAllStartsEqual();
                        }
                        if (!z2) {
                            LazySpanLookup.FullSpanItem fullSpanItem3 = this.mLazySpanLookup.getFullSpanItem(viewLayoutPosition);
                            if (fullSpanItem3 != null) {
                                fullSpanItem3.mHasUnwantedGapAfter = true;
                            }
                            this.mLaidOutInvalidFullSpan = true;
                        }
                    }
                }
                if (layoutState2.mLayoutDirection == 1) {
                    if (layoutParams2.mFullSpan) {
                        int i24 = this.mSpanCount;
                        while (true) {
                            i24--;
                            if (i24 < 0) {
                                break;
                            }
                            this.mSpans[i24].appendToSpan(view);
                        }
                    } else {
                        layoutParams2.mSpan.appendToSpan(view);
                    }
                } else if (layoutParams2.mFullSpan) {
                    int i25 = this.mSpanCount;
                    while (true) {
                        i25--;
                        if (i25 < 0) {
                            break;
                        }
                        this.mSpans[i25].prependToSpan(view);
                    }
                } else {
                    layoutParams2.mSpan.prependToSpan(view);
                }
                if (!isLayoutRTL() || this.mOrientation != 1) {
                    if (layoutParams2.mFullSpan) {
                        i8 = this.mSecondaryOrientation.getStartAfterPadding();
                    } else {
                        i8 = (span5.mIndex * this.mSizePerSpan) + this.mSecondaryOrientation.getStartAfterPadding();
                    }
                    i7 = i8;
                    i6 = this.mSecondaryOrientation.getDecoratedMeasurement(view) + i8;
                } else {
                    if (layoutParams2.mFullSpan) {
                        i9 = this.mSecondaryOrientation.getEndAfterPadding();
                    } else {
                        i9 = this.mSecondaryOrientation.getEndAfterPadding() - (((this.mSpanCount - 1) - span5.mIndex) * this.mSizePerSpan);
                    }
                    i6 = i9;
                    i7 = i9 - this.mSecondaryOrientation.getDecoratedMeasurement(view);
                }
                if (this.mOrientation == 1) {
                    layoutParams = layoutParams2;
                    layoutDecoratedWithMargins(view, i7, i5, i6, i4);
                } else {
                    layoutParams = layoutParams2;
                    layoutDecoratedWithMargins(view, i5, i7, i4, i6);
                }
                if (layoutParams.mFullSpan) {
                    updateAllRemainingSpans(this.mLayoutState.mLayoutDirection, i);
                } else {
                    updateRemainingSpans(span5, this.mLayoutState.mLayoutDirection, i);
                }
                RecyclerView.Recycler recycler4 = recycler;
                recycle(recycler4, this.mLayoutState);
                if (this.mLayoutState.mStopInFocusable && view.hasFocusable()) {
                    if (layoutParams.mFullSpan) {
                        this.mRemainingSpans.clear();
                    } else {
                        z = false;
                        this.mRemainingSpans.set(span5.mIndex, false);
                        recycler2 = recycler4;
                        r10 = z;
                        z3 = true;
                    }
                }
                z = false;
                recycler2 = recycler4;
                r10 = z;
                z3 = true;
            }
        }
        RecyclerView.Recycler recycler32 = recycler2;
        int i182 = r10;
        if (!z3) {
            recycle(recycler32, this.mLayoutState);
        }
        if (this.mLayoutState.mLayoutDirection == -1) {
            i3 = this.mPrimaryOrientation.getStartAfterPadding() - getMinStart(this.mPrimaryOrientation.getStartAfterPadding());
        } else {
            i3 = getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        }
        return i3 > 0 ? Math.min(layoutState2.mAvailable, i3) : i182;
    }

    private void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int maxEnd = getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE && (endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - maxEnd) > 0) {
            int i = endAfterPadding - (-scrollBy(-endAfterPadding, recycler, state));
            if (z && i > 0) {
                this.mPrimaryOrientation.offsetChildren(i);
            }
        }
    }

    private void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int minStart = getMinStart(Integer.MAX_VALUE);
        if (minStart != Integer.MAX_VALUE && (startAfterPadding = minStart - this.mPrimaryOrientation.getStartAfterPadding()) > 0) {
            int scrollBy = startAfterPadding - scrollBy(startAfterPadding, recycler, state);
            if (z && scrollBy > 0) {
                this.mPrimaryOrientation.offsetChildren(-scrollBy);
            }
        }
    }

    private int getMaxEnd(int i) {
        int endLine = this.mSpans[0].getEndLine(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int endLine2 = this.mSpans[i2].getEndLine(i);
            if (endLine2 > endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    private int getMinStart(int i) {
        int startLine = this.mSpans[0].getStartLine(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int startLine2 = this.mSpans[i2].getStartLine(i);
            if (startLine2 < startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleUpdate(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.mShouldReverseLayout
            if (r0 == 0) goto L_0x0009
            int r0 = r6.getLastChildPosition()
            goto L_0x000d
        L_0x0009:
            int r0 = r6.getFirstChildPosition()
        L_0x000d:
            r1 = 8
            if (r9 != r1) goto L_0x001b
            if (r7 >= r8) goto L_0x0016
            int r2 = r8 + 1
            goto L_0x001d
        L_0x0016:
            int r2 = r7 + 1
            r3 = r2
            r2 = r8
            goto L_0x001f
        L_0x001b:
            int r2 = r7 + r8
        L_0x001d:
            r3 = r2
            r2 = r7
        L_0x001f:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.mLazySpanLookup
            r4.invalidateAfter(r2)
            r4 = 1
            if (r9 == r4) goto L_0x003e
            r5 = 2
            if (r9 == r5) goto L_0x0038
            if (r9 == r1) goto L_0x002d
            goto L_0x0043
        L_0x002d:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.offsetForRemoval(r7, r4)
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.mLazySpanLookup
            r7.offsetForAddition(r8, r4)
            goto L_0x0043
        L_0x0038:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.offsetForRemoval(r7, r8)
            goto L_0x0043
        L_0x003e:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.offsetForAddition(r7, r8)
        L_0x0043:
            if (r3 > r0) goto L_0x0046
            return
        L_0x0046:
            boolean r7 = r6.mShouldReverseLayout
            if (r7 == 0) goto L_0x004f
            int r7 = r6.getFirstChildPosition()
            goto L_0x0053
        L_0x004f:
            int r7 = r6.getLastChildPosition()
        L_0x0053:
            if (r2 > r7) goto L_0x0058
            r6.requestLayout()
        L_0x0058:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.StaggeredGridLayoutManager.handleUpdate(int, int, int):void");
    }

    private void measureChildWithDecorationsAndMargin(View view, int i, int i2, boolean z) {
        boolean z2;
        calculateItemDecorationsForChild(view, this.mTmpRect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.leftMargin;
        Rect rect = this.mTmpRect;
        int updateSpecWithExtra = updateSpecWithExtra(i, i3 + rect.left, layoutParams.rightMargin + rect.right);
        int i4 = layoutParams.topMargin;
        Rect rect2 = this.mTmpRect;
        int updateSpecWithExtra2 = updateSpecWithExtra(i2, i4 + rect2.top, layoutParams.bottomMargin + rect2.bottom);
        if (z) {
            z2 = shouldReMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        } else {
            z2 = shouldMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        }
        if (z2) {
            view.measure(updateSpecWithExtra, updateSpecWithExtra2);
        }
    }

    private boolean preferLastSpan(int i) {
        if (this.mOrientation == 0) {
            if ((i == -1) != this.mShouldReverseLayout) {
                return true;
            }
            return false;
        }
        if (((i == -1) == this.mShouldReverseLayout) == isLayoutRTL()) {
            return true;
        }
        return false;
    }

    private void recycle(RecyclerView.Recycler recycler, LayoutState layoutState) {
        int i;
        int i2;
        if (layoutState.mRecycle && !layoutState.mInfinite) {
            if (layoutState.mAvailable != 0) {
                int i3 = 1;
                if (layoutState.mLayoutDirection == -1) {
                    int i4 = layoutState.mStartLine;
                    int startLine = this.mSpans[0].getStartLine(i4);
                    while (i3 < this.mSpanCount) {
                        int startLine2 = this.mSpans[i3].getStartLine(i4);
                        if (startLine2 > startLine) {
                            startLine = startLine2;
                        }
                        i3++;
                    }
                    int i5 = i4 - startLine;
                    if (i5 < 0) {
                        i2 = layoutState.mEndLine;
                    } else {
                        i2 = layoutState.mEndLine - Math.min(i5, layoutState.mAvailable);
                    }
                    recycleFromEnd(recycler, i2);
                    return;
                }
                int i6 = layoutState.mEndLine;
                int endLine = this.mSpans[0].getEndLine(i6);
                while (i3 < this.mSpanCount) {
                    int endLine2 = this.mSpans[i3].getEndLine(i6);
                    if (endLine2 < endLine) {
                        endLine = endLine2;
                    }
                    i3++;
                }
                int i7 = endLine - layoutState.mEndLine;
                if (i7 < 0) {
                    i = layoutState.mStartLine;
                } else {
                    i = Math.min(i7, layoutState.mAvailable) + layoutState.mStartLine;
                }
                recycleFromStart(recycler, i);
            } else if (layoutState.mLayoutDirection == -1) {
                recycleFromEnd(recycler, layoutState.mEndLine);
            } else {
                recycleFromStart(recycler, layoutState.mStartLine);
            }
        }
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int i) {
        int childCount = getChildCount() - 1;
        while (childCount >= 0) {
            View childAt = getChildAt(childCount);
            if (this.mPrimaryOrientation.getDecoratedStart(childAt) >= i && this.mPrimaryOrientation.getTransformedStartWithDecoration(childAt) >= i) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.mFullSpan) {
                    int i2 = 0;
                    while (i2 < this.mSpanCount) {
                        if (this.mSpans[i2].mViews.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                        this.mSpans[i3].popEnd();
                    }
                } else if (layoutParams.mSpan.mViews.size() != 1) {
                    layoutParams.mSpan.popEnd();
                } else {
                    return;
                }
                removeAndRecycleView(childAt, recycler);
                childCount--;
            } else {
                return;
            }
        }
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int i) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) <= i && this.mPrimaryOrientation.getTransformedEndWithDecoration(childAt) <= i) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.mFullSpan) {
                    int i2 = 0;
                    while (i2 < this.mSpanCount) {
                        if (this.mSpans[i2].mViews.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                        this.mSpans[i3].popStart();
                    }
                } else if (layoutParams.mSpan.mViews.size() != 1) {
                    layoutParams.mSpan.popStart();
                } else {
                    return;
                }
                removeAndRecycleView(childAt, recycler);
            } else {
                return;
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

    private void setLayoutStateDirection(int i) {
        LayoutState layoutState = this.mLayoutState;
        layoutState.mLayoutDirection = i;
        int i2 = 1;
        if (this.mShouldReverseLayout != (i == -1)) {
            i2 = -1;
        }
        layoutState.mItemDirection = i2;
    }

    private void updateAllRemainingSpans(int i, int i2) {
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            if (!this.mSpans[i3].mViews.isEmpty()) {
                updateRemainingSpans(this.mSpans[i3], i, i2);
            }
        }
    }

    private void updateLayoutState(int i, RecyclerView.State state) {
        int i2;
        int i3;
        int targetScrollPosition;
        LayoutState layoutState = this.mLayoutState;
        boolean z = false;
        layoutState.mAvailable = 0;
        layoutState.mCurrentPosition = i;
        if (!isSmoothScrolling() || (targetScrollPosition = state.getTargetScrollPosition()) == -1) {
            i3 = 0;
            i2 = 0;
        } else {
            if (this.mShouldReverseLayout == (targetScrollPosition < i)) {
                i3 = this.mPrimaryOrientation.getTotalSpace();
                i2 = 0;
            } else {
                i2 = this.mPrimaryOrientation.getTotalSpace();
                i3 = 0;
            }
        }
        if (getClipToPadding()) {
            this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - i2;
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + i3;
        } else {
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEnd() + i3;
            this.mLayoutState.mStartLine = -i2;
        }
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.mStopInFocusable = false;
        layoutState2.mRecycle = true;
        if (this.mPrimaryOrientation.getMode() == 0 && this.mPrimaryOrientation.getEnd() == 0) {
            z = true;
        }
        layoutState2.mInfinite = z;
    }

    private void updateRemainingSpans(Span span, int i, int i2) {
        int i3 = span.mDeletedSize;
        if (i == -1) {
            int i4 = span.mCachedStart;
            if (i4 == Integer.MIN_VALUE) {
                span.calculateCachedStart();
                i4 = span.mCachedStart;
            }
            if (i4 + i3 <= i2) {
                this.mRemainingSpans.set(span.mIndex, false);
                return;
            }
            return;
        }
        int i5 = span.mCachedEnd;
        if (i5 == Integer.MIN_VALUE) {
            span.calculateCachedEnd();
            i5 = span.mCachedEnd;
        }
        if (i5 - i3 >= i2) {
            this.mRemainingSpans.set(span.mIndex, false);
        }
    }

    private int updateSpecWithExtra(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode);
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public boolean areAllEndsEqual() {
        int endLine = this.mSpans[0].getEndLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; i++) {
            if (this.mSpans[i].getEndLine(Integer.MIN_VALUE) != endLine) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean areAllStartsEqual() {
        int startLine = this.mSpans[0].getStartLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; i++) {
            if (this.mSpans[i].getStartLine(Integer.MIN_VALUE) != startLine) {
                return false;
            }
        }
        return true;
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

    /* access modifiers changed from: package-private */
    public boolean checkForGaps() {
        int i;
        int i2;
        if (getChildCount() == 0 || this.mGapStrategy == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.mShouldReverseLayout) {
            i2 = getLastChildPosition();
            i = getFirstChildPosition();
        } else {
            i2 = getFirstChildPosition();
            i = getLastChildPosition();
        }
        if (i2 == 0 && hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        } else if (!this.mLaidOutInvalidFullSpan) {
            return false;
        } else {
            int i3 = this.mShouldReverseLayout ? -1 : 1;
            int i4 = i + 1;
            LazySpanLookup.FullSpanItem firstFullSpanItemInRange = this.mLazySpanLookup.getFirstFullSpanItemInRange(i2, i4, i3, true);
            if (firstFullSpanItemInRange == null) {
                this.mLaidOutInvalidFullSpan = false;
                this.mLazySpanLookup.forceInvalidateAfter(i4);
                return false;
            }
            LazySpanLookup.FullSpanItem firstFullSpanItemInRange2 = this.mLazySpanLookup.getFirstFullSpanItemInRange(i2, firstFullSpanItemInRange.mPosition, i3 * -1, true);
            if (firstFullSpanItemInRange2 == null) {
                this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange.mPosition);
            } else {
                this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange2.mPosition + 1);
            }
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i3;
        int i4;
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            prepareLayoutStateForDelta(i, state);
            int[] iArr = this.mPrefetchDistances;
            if (iArr == null || iArr.length < this.mSpanCount) {
                this.mPrefetchDistances = new int[this.mSpanCount];
            }
            int i5 = 0;
            for (int i6 = 0; i6 < this.mSpanCount; i6++) {
                LayoutState layoutState = this.mLayoutState;
                if (layoutState.mItemDirection == -1) {
                    i4 = layoutState.mStartLine;
                    i3 = this.mSpans[i6].getStartLine(i4);
                } else {
                    i4 = this.mSpans[i6].getEndLine(layoutState.mEndLine);
                    i3 = this.mLayoutState.mEndLine;
                }
                int i7 = i4 - i3;
                if (i7 >= 0) {
                    this.mPrefetchDistances[i5] = i7;
                    i5++;
                }
            }
            Arrays.sort(this.mPrefetchDistances, 0, i5);
            int i8 = 0;
            while (i8 < i5) {
                int i9 = this.mLayoutState.mCurrentPosition;
                if (i9 >= 0 && i9 < state.getItemCount()) {
                    ((GapWorker.LayoutPrefetchRegistryImpl) layoutPrefetchRegistry).addPosition(this.mLayoutState.mCurrentPosition, this.mPrefetchDistances[i8]);
                    LayoutState layoutState2 = this.mLayoutState;
                    layoutState2.mCurrentPosition += layoutState2.mItemDirection;
                    i8++;
                } else {
                    return;
                }
            }
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
        int calculateScrollDirectionForPosition = calculateScrollDirectionForPosition(i);
        PointF pointF = new PointF();
        if (calculateScrollDirectionForPosition == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = (float) calculateScrollDirectionForPosition;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = (float) calculateScrollDirectionForPosition;
        }
        return pointF;
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
    public View findFirstVisibleItemClosestToEnd(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    /* access modifiers changed from: package-private */
    public View findFirstVisibleItemClosestToStart(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    /* access modifiers changed from: package-private */
    public int findFirstVisibleItemPositionInt() {
        View view;
        if (this.mShouldReverseLayout) {
            view = findFirstVisibleItemClosestToEnd(true);
        } else {
            view = findFirstVisibleItemClosestToStart(true);
        }
        if (view == null) {
            return -1;
        }
        return getPosition(view);
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.mAdapter == null || !canScrollHorizontally()) {
            return 1;
        }
        return this.mRecyclerView.mAdapter.getItemCount();
    }

    /* access modifiers changed from: package-private */
    public int getFirstChildPosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    /* access modifiers changed from: package-private */
    public int getLastChildPosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.mAdapter == null || !canScrollVertically()) {
            return 1;
        }
        return this.mRecyclerView.mAdapter.getItemCount();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b3, code lost:
        if (r11 == r12) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c5, code lost:
        if (r11 == r12) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c9, code lost:
        r11 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x008b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View hasGapsToFix() {
        /*
            r13 = this;
            int r0 = r13.getChildCount()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r13.mSpanCount
            r2.<init>(r3)
            int r3 = r13.mSpanCount
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r13.mOrientation
            r5 = -1
            if (r3 != r1) goto L_0x0020
            boolean r3 = r13.isLayoutRTL()
            if (r3 == 0) goto L_0x0020
            r3 = r1
            goto L_0x0021
        L_0x0020:
            r3 = r5
        L_0x0021:
            boolean r6 = r13.mShouldReverseLayout
            if (r6 == 0) goto L_0x0027
            r6 = r5
            goto L_0x002b
        L_0x0027:
            int r0 = r0 + 1
            r6 = r0
            r0 = r4
        L_0x002b:
            if (r0 >= r6) goto L_0x002f
            r7 = r1
            goto L_0x0030
        L_0x002f:
            r7 = r5
        L_0x0030:
            if (r0 == r6) goto L_0x00eb
            android.view.View r8 = r13.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r9 = (android.support.p002v7.widget.StaggeredGridLayoutManager.LayoutParams) r9
            android.support.v7.widget.StaggeredGridLayoutManager$Span r10 = r9.mSpan
            int r10 = r10.mIndex
            boolean r10 = r2.get(r10)
            if (r10 == 0) goto L_0x0093
            android.support.v7.widget.StaggeredGridLayoutManager$Span r10 = r9.mSpan
            boolean r11 = r13.mShouldReverseLayout
            if (r11 == 0) goto L_0x006c
            int r11 = r10.getEndLine()
            android.support.v7.widget.OrientationHelper r12 = r13.mPrimaryOrientation
            int r12 = r12.getEndAfterPadding()
            if (r11 >= r12) goto L_0x0088
            java.util.ArrayList<android.view.View> r11 = r10.mViews
            int r12 = r11.size()
            int r12 = r12 + r5
            java.lang.Object r11 = r11.get(r12)
            android.view.View r11 = (android.view.View) r11
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r10 = r10.getLayoutParams(r11)
            boolean r10 = r10.mFullSpan
            goto L_0x0086
        L_0x006c:
            int r11 = r10.getStartLine()
            android.support.v7.widget.OrientationHelper r12 = r13.mPrimaryOrientation
            int r12 = r12.getStartAfterPadding()
            if (r11 <= r12) goto L_0x0088
            java.util.ArrayList<android.view.View> r11 = r10.mViews
            java.lang.Object r11 = r11.get(r4)
            android.view.View r11 = (android.view.View) r11
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r10 = r10.getLayoutParams(r11)
            boolean r10 = r10.mFullSpan
        L_0x0086:
            r10 = r10 ^ r1
            goto L_0x0089
        L_0x0088:
            r10 = r4
        L_0x0089:
            if (r10 == 0) goto L_0x008c
            return r8
        L_0x008c:
            android.support.v7.widget.StaggeredGridLayoutManager$Span r10 = r9.mSpan
            int r10 = r10.mIndex
            r2.clear(r10)
        L_0x0093:
            boolean r10 = r9.mFullSpan
            if (r10 == 0) goto L_0x0098
            goto L_0x00e8
        L_0x0098:
            int r10 = r0 + r7
            if (r10 == r6) goto L_0x00e8
            android.view.View r10 = r13.getChildAt(r10)
            boolean r11 = r13.mShouldReverseLayout
            if (r11 == 0) goto L_0x00b6
            android.support.v7.widget.OrientationHelper r11 = r13.mPrimaryOrientation
            int r11 = r11.getDecoratedEnd(r8)
            android.support.v7.widget.OrientationHelper r12 = r13.mPrimaryOrientation
            int r12 = r12.getDecoratedEnd(r10)
            if (r11 >= r12) goto L_0x00b3
            return r8
        L_0x00b3:
            if (r11 != r12) goto L_0x00c9
            goto L_0x00c7
        L_0x00b6:
            android.support.v7.widget.OrientationHelper r11 = r13.mPrimaryOrientation
            int r11 = r11.getDecoratedStart(r8)
            android.support.v7.widget.OrientationHelper r12 = r13.mPrimaryOrientation
            int r12 = r12.getDecoratedStart(r10)
            if (r11 <= r12) goto L_0x00c5
            return r8
        L_0x00c5:
            if (r11 != r12) goto L_0x00c9
        L_0x00c7:
            r11 = r1
            goto L_0x00ca
        L_0x00c9:
            r11 = r4
        L_0x00ca:
            if (r11 == 0) goto L_0x00e8
            android.view.ViewGroup$LayoutParams r10 = r10.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r10 = (android.support.p002v7.widget.StaggeredGridLayoutManager.LayoutParams) r10
            android.support.v7.widget.StaggeredGridLayoutManager$Span r9 = r9.mSpan
            int r9 = r9.mIndex
            android.support.v7.widget.StaggeredGridLayoutManager$Span r10 = r10.mSpan
            int r10 = r10.mIndex
            int r9 = r9 - r10
            if (r9 >= 0) goto L_0x00df
            r9 = r1
            goto L_0x00e0
        L_0x00df:
            r9 = r4
        L_0x00e0:
            if (r3 >= 0) goto L_0x00e4
            r10 = r1
            goto L_0x00e5
        L_0x00e4:
            r10 = r4
        L_0x00e5:
            if (r9 == r10) goto L_0x00e8
            return r8
        L_0x00e8:
            int r0 = r0 + r7
            goto L_0x0030
        L_0x00eb:
            r13 = 0
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.StaggeredGridLayoutManager.hasGapsToFix():android.view.View");
    }

    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    public boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public void offsetChildrenHorizontal(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenHorizontal(i);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            Span span = this.mSpans[i2];
            int i3 = span.mCachedStart;
            if (i3 != Integer.MIN_VALUE) {
                span.mCachedStart = i3 + i;
            }
            int i4 = span.mCachedEnd;
            if (i4 != Integer.MIN_VALUE) {
                span.mCachedEnd = i4 + i;
            }
        }
    }

    public void offsetChildrenVertical(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenVertical(i);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            Span span = this.mSpans[i2];
            int i3 = span.mCachedStart;
            if (i3 != Integer.MIN_VALUE) {
                span.mCachedStart = i3 + i;
            }
            int i4 = span.mCachedEnd;
            if (i4 != Integer.MIN_VALUE) {
                span.mCachedEnd = i4 + i;
            }
        }
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        onDetachedFromWindow(recyclerView);
        removeCallbacks(this.mCheckForGapsRunnable);
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].clear();
        }
        recyclerView.requestLayout();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003b, code lost:
        if (r9.mOrientation == 1) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0041, code lost:
        if (r9.mOrientation == 0) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004d, code lost:
        if (isLayoutRTL() == false) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0059, code lost:
        if (isLayoutRTL() == false) goto L_0x003d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onFocusSearchFailed(android.view.View r10, int r11, android.support.p002v7.widget.RecyclerView.Recycler r12, android.support.p002v7.widget.RecyclerView.State r13) {
        /*
            r9 = this;
            int r0 = r9.getChildCount()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            android.view.View r10 = r9.findContainingItemView(r10)
            if (r10 != 0) goto L_0x000f
            return r1
        L_0x000f:
            r9.resolveShouldLayoutReverse()
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = -1
            r3 = 1
            if (r11 == r3) goto L_0x0050
            r4 = 2
            if (r11 == r4) goto L_0x0044
            r4 = 17
            if (r11 == r4) goto L_0x003f
            r4 = 33
            if (r11 == r4) goto L_0x0039
            r4 = 66
            if (r11 == r4) goto L_0x0034
            r4 = 130(0x82, float:1.82E-43)
            if (r11 == r4) goto L_0x002c
            goto L_0x0032
        L_0x002c:
            int r11 = r9.mOrientation
            if (r11 != r3) goto L_0x0032
        L_0x0030:
            r11 = r3
            goto L_0x005c
        L_0x0032:
            r11 = r0
            goto L_0x005c
        L_0x0034:
            int r11 = r9.mOrientation
            if (r11 != 0) goto L_0x0032
            goto L_0x0030
        L_0x0039:
            int r11 = r9.mOrientation
            if (r11 != r3) goto L_0x0032
        L_0x003d:
            r11 = r2
            goto L_0x005c
        L_0x003f:
            int r11 = r9.mOrientation
            if (r11 != 0) goto L_0x0032
        L_0x0043:
            goto L_0x003d
        L_0x0044:
            int r11 = r9.mOrientation
            if (r11 != r3) goto L_0x0049
            goto L_0x0030
        L_0x0049:
            boolean r11 = r9.isLayoutRTL()
            if (r11 == 0) goto L_0x0030
            goto L_0x0054
        L_0x0050:
            int r11 = r9.mOrientation
            if (r11 != r3) goto L_0x0055
        L_0x0054:
            goto L_0x0043
        L_0x0055:
            boolean r11 = r9.isLayoutRTL()
            if (r11 == 0) goto L_0x003d
            goto L_0x0030
        L_0x005c:
            if (r11 != r0) goto L_0x005f
            return r1
        L_0x005f:
            android.view.ViewGroup$LayoutParams r0 = r10.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r0 = (android.support.p002v7.widget.StaggeredGridLayoutManager.LayoutParams) r0
            boolean r4 = r0.mFullSpan
            android.support.v7.widget.StaggeredGridLayoutManager$Span r0 = r0.mSpan
            if (r11 != r3) goto L_0x0070
            int r5 = r9.getLastChildPosition()
            goto L_0x0074
        L_0x0070:
            int r5 = r9.getFirstChildPosition()
        L_0x0074:
            r9.updateLayoutState(r5, r13)
            r9.setLayoutStateDirection(r11)
            android.support.v7.widget.LayoutState r6 = r9.mLayoutState
            int r7 = r6.mItemDirection
            int r7 = r7 + r5
            r6.mCurrentPosition = r7
            r7 = 1051372203(0x3eaaaaab, float:0.33333334)
            android.support.v7.widget.OrientationHelper r8 = r9.mPrimaryOrientation
            int r8 = r8.getTotalSpace()
            float r8 = (float) r8
            float r8 = r8 * r7
            int r7 = (int) r8
            r6.mAvailable = r7
            android.support.v7.widget.LayoutState r6 = r9.mLayoutState
            r6.mStopInFocusable = r3
            r7 = 0
            r6.mRecycle = r7
            r9.fill(r12, r6, r13)
            boolean r12 = r9.mShouldReverseLayout
            r9.mLastLayoutFromEnd = r12
            if (r4 != 0) goto L_0x00a8
            android.view.View r12 = r0.getFocusableViewAfter(r5, r11)
            if (r12 == 0) goto L_0x00a8
            if (r12 == r10) goto L_0x00a8
            return r12
        L_0x00a8:
            boolean r12 = r9.preferLastSpan(r11)
            if (r12 == 0) goto L_0x00c3
            int r12 = r9.mSpanCount
            int r12 = r12 - r3
        L_0x00b1:
            if (r12 < 0) goto L_0x00d8
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r13 = r9.mSpans
            r13 = r13[r12]
            android.view.View r13 = r13.getFocusableViewAfter(r5, r11)
            if (r13 == 0) goto L_0x00c0
            if (r13 == r10) goto L_0x00c0
            return r13
        L_0x00c0:
            int r12 = r12 + -1
            goto L_0x00b1
        L_0x00c3:
            r12 = r7
        L_0x00c4:
            int r13 = r9.mSpanCount
            if (r12 >= r13) goto L_0x00d8
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r13 = r9.mSpans
            r13 = r13[r12]
            android.view.View r13 = r13.getFocusableViewAfter(r5, r11)
            if (r13 == 0) goto L_0x00d5
            if (r13 == r10) goto L_0x00d5
            return r13
        L_0x00d5:
            int r12 = r12 + 1
            goto L_0x00c4
        L_0x00d8:
            boolean r12 = r9.mReverseLayout
            r12 = r12 ^ r3
            if (r11 != r2) goto L_0x00df
            r13 = r3
            goto L_0x00e0
        L_0x00df:
            r13 = r7
        L_0x00e0:
            if (r12 != r13) goto L_0x00e4
            r12 = r3
            goto L_0x00e5
        L_0x00e4:
            r12 = r7
        L_0x00e5:
            if (r4 != 0) goto L_0x00fb
            if (r12 == 0) goto L_0x00ee
            int r13 = r0.findFirstPartiallyVisibleItemPosition()
            goto L_0x00f2
        L_0x00ee:
            int r13 = r0.findLastPartiallyVisibleItemPosition()
        L_0x00f2:
            android.view.View r13 = r9.findViewByPosition(r13)
            if (r13 == 0) goto L_0x00fb
            if (r13 == r10) goto L_0x00fb
            return r13
        L_0x00fb:
            boolean r11 = r9.preferLastSpan(r11)
            if (r11 == 0) goto L_0x012a
            int r11 = r9.mSpanCount
            int r11 = r11 - r3
        L_0x0104:
            if (r11 < 0) goto L_0x014d
            int r13 = r0.mIndex
            if (r11 != r13) goto L_0x010b
            goto L_0x0127
        L_0x010b:
            if (r12 == 0) goto L_0x0116
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r13 = r9.mSpans
            r13 = r13[r11]
            int r13 = r13.findFirstPartiallyVisibleItemPosition()
            goto L_0x011e
        L_0x0116:
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r13 = r9.mSpans
            r13 = r13[r11]
            int r13 = r13.findLastPartiallyVisibleItemPosition()
        L_0x011e:
            android.view.View r13 = r9.findViewByPosition(r13)
            if (r13 == 0) goto L_0x0127
            if (r13 == r10) goto L_0x0127
            return r13
        L_0x0127:
            int r11 = r11 + -1
            goto L_0x0104
        L_0x012a:
            int r11 = r9.mSpanCount
            if (r7 >= r11) goto L_0x014d
            if (r12 == 0) goto L_0x0139
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r11 = r9.mSpans
            r11 = r11[r7]
            int r11 = r11.findFirstPartiallyVisibleItemPosition()
            goto L_0x0141
        L_0x0139:
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r11 = r9.mSpans
            r11 = r11[r7]
            int r11 = r11.findLastPartiallyVisibleItemPosition()
        L_0x0141:
            android.view.View r11 = r9.findViewByPosition(r11)
            if (r11 == 0) goto L_0x014a
            if (r11 == r10) goto L_0x014a
            return r11
        L_0x014a:
            int r7 = r7 + 1
            goto L_0x012a
        L_0x014d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.StaggeredGridLayoutManager.onFocusSearchFailed(android.view.View, int, android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):android.view.View");
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        onInitializeAccessibilityEvent(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        if (getChildCount() > 0) {
            View findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(false);
            View findFirstVisibleItemClosestToEnd = findFirstVisibleItemClosestToEnd(false);
            if (findFirstVisibleItemClosestToStart != null && findFirstVisibleItemClosestToEnd != null) {
                int position = getPosition(findFirstVisibleItemClosestToStart);
                int position2 = getPosition(findFirstVisibleItemClosestToEnd);
                if (position < position2) {
                    accessibilityEvent.setFromIndex(position);
                    accessibilityEvent.setToIndex(position2);
                    return;
                }
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int i = 1;
        int i2 = -1;
        if (this.mOrientation == 0) {
            Span span = layoutParams2.mSpan;
            if (span != null) {
                i2 = span.mIndex;
            }
            int i3 = i2;
            if (layoutParams2.mFullSpan) {
                i = this.mSpanCount;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i3, i, -1, -1, layoutParams2.mFullSpan, false));
            return;
        }
        Span span2 = layoutParams2.mSpan;
        if (span2 != null) {
            i2 = span2.mIndex;
        }
        int i4 = i2;
        if (layoutParams2.mFullSpan) {
            i = this.mSpanCount;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, i4, i, layoutParams2.mFullSpan, false));
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        handleUpdate(i, i2, 1);
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        handleUpdate(i, i2, 8);
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        handleUpdate(i, i2, 2);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        handleUpdate(i, i2, 4);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        int i;
        int i2;
        int i3;
        int[] iArr;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        savedState2.mReverseLayout = this.mReverseLayout;
        savedState2.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        savedState2.mLastLayoutRTL = this.mLastLayoutRTL;
        LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.mData) == null) {
            savedState2.mSpanLookupSize = 0;
        } else {
            savedState2.mSpanLookup = iArr;
            savedState2.mSpanLookupSize = savedState2.mSpanLookup.length;
            savedState2.mFullSpanItems = lazySpanLookup.mFullSpanItems;
        }
        if (getChildCount() > 0) {
            if (this.mLastLayoutFromEnd) {
                i = getLastChildPosition();
            } else {
                i = getFirstChildPosition();
            }
            savedState2.mAnchorPosition = i;
            savedState2.mVisibleAnchorPosition = findFirstVisibleItemPositionInt();
            int i4 = this.mSpanCount;
            savedState2.mSpanOffsetsSize = i4;
            savedState2.mSpanOffsets = new int[i4];
            for (int i5 = 0; i5 < this.mSpanCount; i5++) {
                if (this.mLastLayoutFromEnd) {
                    i2 = this.mSpans[i5].getEndLine(Integer.MIN_VALUE);
                    if (i2 != Integer.MIN_VALUE) {
                        i3 = this.mPrimaryOrientation.getEndAfterPadding();
                    } else {
                        savedState2.mSpanOffsets[i5] = i2;
                    }
                } else {
                    i2 = this.mSpans[i5].getStartLine(Integer.MIN_VALUE);
                    if (i2 != Integer.MIN_VALUE) {
                        i3 = this.mPrimaryOrientation.getStartAfterPadding();
                    } else {
                        savedState2.mSpanOffsets[i5] = i2;
                    }
                }
                i2 -= i3;
                savedState2.mSpanOffsets[i5] = i2;
            }
        } else {
            savedState2.mAnchorPosition = -1;
            savedState2.mVisibleAnchorPosition = -1;
            savedState2.mSpanOffsetsSize = 0;
        }
        return savedState2;
    }

    public void onScrollStateChanged(int i) {
        if (i == 0) {
            checkForGaps();
        }
    }

    /* access modifiers changed from: package-private */
    public void prepareLayoutStateForDelta(int i, RecyclerView.State state) {
        int i2;
        int i3;
        if (i > 0) {
            i3 = getLastChildPosition();
            i2 = 1;
        } else {
            i2 = -1;
            i3 = getFirstChildPosition();
        }
        this.mLayoutState.mRecycle = true;
        updateLayoutState(i3, state);
        setLayoutStateDirection(i2);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = i3 + layoutState.mItemDirection;
        layoutState.mAvailable = Math.abs(i);
    }

    /* access modifiers changed from: package-private */
    public int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        prepareLayoutStateForDelta(i, state);
        int fill = fill(recycler, this.mLayoutState, state);
        if (this.mLayoutState.mAvailable >= fill) {
            i = i < 0 ? -fill : fill;
        }
        this.mPrimaryOrientation.offsetChildren(-i);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        LayoutState layoutState = this.mLayoutState;
        layoutState.mAvailable = 0;
        recycle(recycler, layoutState);
        return i;
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i, recycler, state);
    }

    public void scrollToPosition(int i) {
        SavedState savedState = this.mPendingSavedState;
        if (!(savedState == null || savedState.mAnchorPosition == i)) {
            savedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i, recycler, state);
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int i3;
        int i4;
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            i4 = RecyclerView.LayoutManager.chooseSize(i2, rect.height() + paddingBottom, getMinimumHeight());
            i3 = RecyclerView.LayoutManager.chooseSize(i, (this.mSizePerSpan * this.mSpanCount) + paddingRight, getMinimumWidth());
        } else {
            i3 = RecyclerView.LayoutManager.chooseSize(i, rect.width() + paddingRight, getMinimumWidth());
            i4 = RecyclerView.LayoutManager.chooseSize(i2, (this.mSizePerSpan * this.mSpanCount) + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(i3, i4);
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (i != this.mOrientation) {
                this.mOrientation = i;
                OrientationHelper orientationHelper = this.mPrimaryOrientation;
                this.mPrimaryOrientation = this.mSecondaryOrientation;
                this.mSecondaryOrientation = orientationHelper;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll((String) null);
        SavedState savedState = this.mPendingSavedState;
        if (!(savedState == null || savedState.mReverseLayout == z)) {
            savedState.mReverseLayout = z;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public void setSpanCount(int i) {
        assertNotInLayoutOrScroll((String) null);
        if (i != this.mSpanCount) {
            invalidateSpanAssignments();
            this.mSpanCount = i;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new Span[this.mSpanCount];
            for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                this.mSpans[i2] = new Span(i2);
            }
            requestLayout();
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    /* access modifiers changed from: package-private */
    public boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i;
        int i2;
        int i3;
        boolean z = false;
        if (!state.mInPreLayout && (i = this.mPendingScrollPosition) != -1) {
            if (i < 0 || i >= state.getItemCount()) {
                this.mPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            } else {
                SavedState savedState = this.mPendingSavedState;
                if (savedState == null || savedState.mAnchorPosition == -1 || savedState.mSpanOffsetsSize < 1) {
                    View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                    if (findViewByPosition != null) {
                        if (this.mShouldReverseLayout) {
                            i2 = getLastChildPosition();
                        } else {
                            i2 = getFirstChildPosition();
                        }
                        anchorInfo.mPosition = i2;
                        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                            if (anchorInfo.mLayoutFromEnd) {
                                anchorInfo.mOffset = (this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedEnd(findViewByPosition);
                            } else {
                                anchorInfo.mOffset = (this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedStart(findViewByPosition);
                            }
                            return true;
                        } else if (this.mPrimaryOrientation.getDecoratedMeasurement(findViewByPosition) > this.mPrimaryOrientation.getTotalSpace()) {
                            if (anchorInfo.mLayoutFromEnd) {
                                i3 = this.mPrimaryOrientation.getEndAfterPadding();
                            } else {
                                i3 = this.mPrimaryOrientation.getStartAfterPadding();
                            }
                            anchorInfo.mOffset = i3;
                            return true;
                        } else {
                            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(findViewByPosition) - this.mPrimaryOrientation.getStartAfterPadding();
                            if (decoratedStart < 0) {
                                anchorInfo.mOffset = -decoratedStart;
                                return true;
                            }
                            int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(findViewByPosition);
                            if (endAfterPadding < 0) {
                                anchorInfo.mOffset = endAfterPadding;
                                return true;
                            }
                            anchorInfo.mOffset = Integer.MIN_VALUE;
                        }
                    } else {
                        anchorInfo.mPosition = this.mPendingScrollPosition;
                        int i4 = this.mPendingScrollPositionOffset;
                        if (i4 == Integer.MIN_VALUE) {
                            if (calculateScrollDirectionForPosition(anchorInfo.mPosition) == 1) {
                                z = true;
                            }
                            anchorInfo.mLayoutFromEnd = z;
                            anchorInfo.assignCoordinateFromPadding();
                        } else if (anchorInfo.mLayoutFromEnd) {
                            anchorInfo.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - i4;
                        } else {
                            anchorInfo.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding() + i4;
                        }
                        anchorInfo.mInvalidateOffsets = true;
                    }
                } else {
                    anchorInfo.mOffset = Integer.MIN_VALUE;
                    anchorInfo.mPosition = this.mPendingScrollPosition;
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void updateAnchorInfoForLayout(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (!updateAnchorFromPendingData(state, anchorInfo)) {
            int i = 0;
            if (!this.mLastLayoutFromEnd) {
                int itemCount = state.getItemCount();
                int childCount = getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 < childCount) {
                        int position = getPosition(getChildAt(i2));
                        if (position >= 0 && position < itemCount) {
                            i = position;
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            } else {
                int itemCount2 = state.getItemCount();
                int childCount2 = getChildCount();
                while (true) {
                    childCount2--;
                    if (childCount2 >= 0) {
                        int position2 = getPosition(getChildAt(childCount2));
                        if (position2 >= 0 && position2 < itemCount2) {
                            i = position2;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            anchorInfo.mPosition = i;
            anchorInfo.mOffset = Integer.MIN_VALUE;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateMeasureSpecs(int i) {
        this.mSizePerSpan = i / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(i, this.mSecondaryOrientation.getMode());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:151:0x029b, code lost:
        if (checkForGaps() != false) goto L_0x029f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onLayoutChildren(android.support.p002v7.widget.RecyclerView.Recycler r12, android.support.p002v7.widget.RecyclerView.State r13, boolean r14) {
        /*
            r11 = this;
            android.support.v7.widget.StaggeredGridLayoutManager$AnchorInfo r0 = r11.mAnchorInfo
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r1 = r11.mPendingSavedState
            r2 = -1
            if (r1 != 0) goto L_0x000b
            int r1 = r11.mPendingScrollPosition
            if (r1 == r2) goto L_0x0018
        L_0x000b:
            int r1 = r13.getItemCount()
            if (r1 != 0) goto L_0x0018
            r11.removeAndRecycleAllViews(r12)
            r0.reset()
            return
        L_0x0018:
            boolean r1 = r0.mValid
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0029
            int r1 = r11.mPendingScrollPosition
            if (r1 != r2) goto L_0x0029
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r1 = r11.mPendingSavedState
            if (r1 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r1 = r3
            goto L_0x002a
        L_0x0029:
            r1 = r4
        L_0x002a:
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == 0) goto L_0x00b3
            r0.reset()
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            if (r6 == 0) goto L_0x00a7
            int r7 = r6.mSpanOffsetsSize
            if (r7 <= 0) goto L_0x0077
            int r8 = r11.mSpanCount
            if (r7 != r8) goto L_0x006e
            r6 = r3
        L_0x003e:
            int r7 = r11.mSpanCount
            if (r6 >= r7) goto L_0x0077
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r7 = r11.mSpans
            r7 = r7[r6]
            r7.clear()
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r7 = r11.mPendingSavedState
            int[] r8 = r7.mSpanOffsets
            r8 = r8[r6]
            if (r8 == r5) goto L_0x0063
            boolean r7 = r7.mAnchorLayoutFromEnd
            if (r7 == 0) goto L_0x005c
            android.support.v7.widget.OrientationHelper r7 = r11.mPrimaryOrientation
            int r7 = r7.getEndAfterPadding()
            goto L_0x0062
        L_0x005c:
            android.support.v7.widget.OrientationHelper r7 = r11.mPrimaryOrientation
            int r7 = r7.getStartAfterPadding()
        L_0x0062:
            int r8 = r8 + r7
        L_0x0063:
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r7 = r11.mSpans
            r7 = r7[r6]
            r7.mCachedStart = r8
            r7.mCachedEnd = r8
            int r6 = r6 + 1
            goto L_0x003e
        L_0x006e:
            r6.invalidateSpanInfo()
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            int r7 = r6.mVisibleAnchorPosition
            r6.mAnchorPosition = r7
        L_0x0077:
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            boolean r7 = r6.mLastLayoutRTL
            r11.mLastLayoutRTL = r7
            boolean r6 = r6.mReverseLayout
            r11.setReverseLayout(r6)
            r11.resolveShouldLayoutReverse()
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            int r7 = r6.mAnchorPosition
            if (r7 == r2) goto L_0x0092
            r11.mPendingScrollPosition = r7
            boolean r6 = r6.mAnchorLayoutFromEnd
            r0.mLayoutFromEnd = r6
            goto L_0x0096
        L_0x0092:
            boolean r6 = r11.mShouldReverseLayout
            r0.mLayoutFromEnd = r6
        L_0x0096:
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            int r7 = r6.mSpanLookupSize
            if (r7 <= r4) goto L_0x00ae
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r11.mLazySpanLookup
            int[] r8 = r6.mSpanLookup
            r7.mData = r8
            java.util.List<android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r6 = r6.mFullSpanItems
            r7.mFullSpanItems = r6
            goto L_0x00ae
        L_0x00a7:
            r11.resolveShouldLayoutReverse()
            boolean r6 = r11.mShouldReverseLayout
            r0.mLayoutFromEnd = r6
        L_0x00ae:
            r11.updateAnchorInfoForLayout(r13, r0)
            r0.mValid = r4
        L_0x00b3:
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            if (r6 != 0) goto L_0x00d0
            int r6 = r11.mPendingScrollPosition
            if (r6 != r2) goto L_0x00d0
            boolean r6 = r0.mLayoutFromEnd
            boolean r7 = r11.mLastLayoutFromEnd
            if (r6 != r7) goto L_0x00c9
            boolean r6 = r11.isLayoutRTL()
            boolean r7 = r11.mLastLayoutRTL
            if (r6 == r7) goto L_0x00d0
        L_0x00c9:
            android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup r6 = r11.mLazySpanLookup
            r6.clear()
            r0.mInvalidateOffsets = r4
        L_0x00d0:
            int r6 = r11.getChildCount()
            if (r6 <= 0) goto L_0x0167
            android.support.v7.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            if (r6 == 0) goto L_0x00de
            int r6 = r6.mSpanOffsetsSize
            if (r6 >= r4) goto L_0x0167
        L_0x00de:
            boolean r6 = r0.mInvalidateOffsets
            if (r6 == 0) goto L_0x00fd
            r1 = r3
        L_0x00e3:
            int r6 = r11.mSpanCount
            if (r1 >= r6) goto L_0x0167
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r6 = r11.mSpans
            r6 = r6[r1]
            r6.clear()
            int r6 = r0.mOffset
            if (r6 == r5) goto L_0x00fa
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r7 = r11.mSpans
            r7 = r7[r1]
            r7.mCachedStart = r6
            r7.mCachedEnd = r6
        L_0x00fa:
            int r1 = r1 + 1
            goto L_0x00e3
        L_0x00fd:
            if (r1 != 0) goto L_0x011f
            android.support.v7.widget.StaggeredGridLayoutManager$AnchorInfo r1 = r11.mAnchorInfo
            int[] r1 = r1.mSpanReferenceLines
            if (r1 != 0) goto L_0x0106
            goto L_0x011f
        L_0x0106:
            r1 = r3
        L_0x0107:
            int r6 = r11.mSpanCount
            if (r1 >= r6) goto L_0x0167
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r6 = r11.mSpans
            r6 = r6[r1]
            r6.clear()
            android.support.v7.widget.StaggeredGridLayoutManager$AnchorInfo r7 = r11.mAnchorInfo
            int[] r7 = r7.mSpanReferenceLines
            r7 = r7[r1]
            r6.mCachedStart = r7
            r6.mCachedEnd = r7
            int r1 = r1 + 1
            goto L_0x0107
        L_0x011f:
            r1 = r3
        L_0x0120:
            int r6 = r11.mSpanCount
            if (r1 >= r6) goto L_0x0160
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r6 = r11.mSpans
            r6 = r6[r1]
            boolean r7 = r11.mShouldReverseLayout
            int r8 = r0.mOffset
            if (r7 == 0) goto L_0x0133
            int r9 = r6.getEndLine(r5)
            goto L_0x0137
        L_0x0133:
            int r9 = r6.getStartLine(r5)
        L_0x0137:
            r6.clear()
            if (r9 != r5) goto L_0x013d
            goto L_0x015d
        L_0x013d:
            if (r7 == 0) goto L_0x0149
            android.support.v7.widget.StaggeredGridLayoutManager r10 = android.support.p002v7.widget.StaggeredGridLayoutManager.this
            android.support.v7.widget.OrientationHelper r10 = r10.mPrimaryOrientation
            int r10 = r10.getEndAfterPadding()
            if (r9 < r10) goto L_0x015d
        L_0x0149:
            if (r7 != 0) goto L_0x0156
            android.support.v7.widget.StaggeredGridLayoutManager r7 = android.support.p002v7.widget.StaggeredGridLayoutManager.this
            android.support.v7.widget.OrientationHelper r7 = r7.mPrimaryOrientation
            int r7 = r7.getStartAfterPadding()
            if (r9 <= r7) goto L_0x0156
            goto L_0x015d
        L_0x0156:
            if (r8 == r5) goto L_0x0159
            int r9 = r9 + r8
        L_0x0159:
            r6.mCachedEnd = r9
            r6.mCachedStart = r9
        L_0x015d:
            int r1 = r1 + 1
            goto L_0x0120
        L_0x0160:
            android.support.v7.widget.StaggeredGridLayoutManager$AnchorInfo r1 = r11.mAnchorInfo
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r6 = r11.mSpans
            r1.saveSpanReferenceLines(r6)
        L_0x0167:
            r11.detachAndScrapAttachedViews(r12)
            android.support.v7.widget.LayoutState r1 = r11.mLayoutState
            r1.mRecycle = r3
            r11.mLaidOutInvalidFullSpan = r3
            android.support.v7.widget.OrientationHelper r1 = r11.mSecondaryOrientation
            int r1 = r1.getTotalSpace()
            r11.updateMeasureSpecs(r1)
            int r1 = r0.mPosition
            r11.updateLayoutState(r1, r13)
            boolean r1 = r0.mLayoutFromEnd
            if (r1 == 0) goto L_0x019a
            r11.setLayoutStateDirection(r2)
            android.support.v7.widget.LayoutState r1 = r11.mLayoutState
            r11.fill(r12, r1, r13)
            r11.setLayoutStateDirection(r4)
            android.support.v7.widget.LayoutState r1 = r11.mLayoutState
            int r2 = r0.mPosition
            int r6 = r1.mItemDirection
            int r2 = r2 + r6
            r1.mCurrentPosition = r2
            r11.fill(r12, r1, r13)
            goto L_0x01b1
        L_0x019a:
            r11.setLayoutStateDirection(r4)
            android.support.v7.widget.LayoutState r1 = r11.mLayoutState
            r11.fill(r12, r1, r13)
            r11.setLayoutStateDirection(r2)
            android.support.v7.widget.LayoutState r1 = r11.mLayoutState
            int r2 = r0.mPosition
            int r6 = r1.mItemDirection
            int r2 = r2 + r6
            r1.mCurrentPosition = r2
            r11.fill(r12, r1, r13)
        L_0x01b1:
            android.support.v7.widget.OrientationHelper r1 = r11.mSecondaryOrientation
            int r1 = r1.getMode()
            r2 = 1073741824(0x40000000, float:2.0)
            if (r1 != r2) goto L_0x01bd
            goto L_0x025c
        L_0x01bd:
            r1 = 0
            int r2 = r11.getChildCount()
            r6 = r1
            r1 = r3
        L_0x01c4:
            if (r1 >= r2) goto L_0x01ee
            android.view.View r7 = r11.getChildAt(r1)
            android.support.v7.widget.OrientationHelper r8 = r11.mSecondaryOrientation
            int r8 = r8.getDecoratedMeasurement(r7)
            float r8 = (float) r8
            int r9 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r9 >= 0) goto L_0x01d6
            goto L_0x01eb
        L_0x01d6:
            android.view.ViewGroup$LayoutParams r7 = r7.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r7 = (android.support.p002v7.widget.StaggeredGridLayoutManager.LayoutParams) r7
            boolean r7 = r7.mFullSpan
            if (r7 == 0) goto L_0x01e7
            r7 = 1065353216(0x3f800000, float:1.0)
            float r8 = r8 * r7
            int r7 = r11.mSpanCount
            float r7 = (float) r7
            float r8 = r8 / r7
        L_0x01e7:
            float r6 = java.lang.Math.max(r6, r8)
        L_0x01eb:
            int r1 = r1 + 1
            goto L_0x01c4
        L_0x01ee:
            int r1 = r11.mSizePerSpan
            int r7 = r11.mSpanCount
            float r7 = (float) r7
            float r6 = r6 * r7
            int r6 = java.lang.Math.round(r6)
            android.support.v7.widget.OrientationHelper r7 = r11.mSecondaryOrientation
            int r7 = r7.getMode()
            if (r7 != r5) goto L_0x020a
            android.support.v7.widget.OrientationHelper r5 = r11.mSecondaryOrientation
            int r5 = r5.getTotalSpace()
            int r6 = java.lang.Math.min(r6, r5)
        L_0x020a:
            r11.updateMeasureSpecs(r6)
            int r5 = r11.mSizePerSpan
            if (r5 != r1) goto L_0x0212
            goto L_0x025c
        L_0x0212:
            r5 = r3
        L_0x0213:
            if (r5 >= r2) goto L_0x025c
            android.view.View r6 = r11.getChildAt(r5)
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            android.support.v7.widget.StaggeredGridLayoutManager$LayoutParams r7 = (android.support.p002v7.widget.StaggeredGridLayoutManager.LayoutParams) r7
            boolean r8 = r7.mFullSpan
            if (r8 == 0) goto L_0x0224
            goto L_0x0259
        L_0x0224:
            boolean r8 = r11.isLayoutRTL()
            if (r8 == 0) goto L_0x0244
            int r8 = r11.mOrientation
            if (r8 != r4) goto L_0x0244
            int r8 = r11.mSpanCount
            int r9 = r8 + -1
            android.support.v7.widget.StaggeredGridLayoutManager$Span r7 = r7.mSpan
            int r7 = r7.mIndex
            int r9 = r9 - r7
            int r9 = -r9
            int r10 = r11.mSizePerSpan
            int r9 = r9 * r10
            int r8 = r8 - r4
            int r8 = r8 - r7
            int r7 = -r8
            int r7 = r7 * r1
            int r9 = r9 - r7
            r6.offsetLeftAndRight(r9)
            goto L_0x0259
        L_0x0244:
            android.support.v7.widget.StaggeredGridLayoutManager$Span r7 = r7.mSpan
            int r7 = r7.mIndex
            int r8 = r11.mSizePerSpan
            int r8 = r8 * r7
            int r7 = r7 * r1
            int r9 = r11.mOrientation
            if (r9 != r4) goto L_0x0255
            int r8 = r8 - r7
            r6.offsetLeftAndRight(r8)
            goto L_0x0259
        L_0x0255:
            int r8 = r8 - r7
            r6.offsetTopAndBottom(r8)
        L_0x0259:
            int r5 = r5 + 1
            goto L_0x0213
        L_0x025c:
            int r1 = r11.getChildCount()
            if (r1 <= 0) goto L_0x0273
            boolean r1 = r11.mShouldReverseLayout
            if (r1 == 0) goto L_0x026d
            r11.fixEndGap(r12, r13, r4)
            r11.fixStartGap(r12, r13, r3)
            goto L_0x0273
        L_0x026d:
            r11.fixStartGap(r12, r13, r4)
            r11.fixEndGap(r12, r13, r3)
        L_0x0273:
            if (r14 == 0) goto L_0x029e
            boolean r14 = r13.mInPreLayout
            if (r14 != 0) goto L_0x029e
            int r14 = r11.mGapStrategy
            if (r14 == 0) goto L_0x028f
            int r14 = r11.getChildCount()
            if (r14 <= 0) goto L_0x028f
            boolean r14 = r11.mLaidOutInvalidFullSpan
            if (r14 != 0) goto L_0x028d
            android.view.View r14 = r11.hasGapsToFix()
            if (r14 == 0) goto L_0x028f
        L_0x028d:
            r14 = r4
            goto L_0x0290
        L_0x028f:
            r14 = r3
        L_0x0290:
            if (r14 == 0) goto L_0x029e
            java.lang.Runnable r14 = r11.mCheckForGapsRunnable
            r11.removeCallbacks(r14)
            boolean r14 = r11.checkForGaps()
            if (r14 == 0) goto L_0x029e
            goto L_0x029f
        L_0x029e:
            r4 = r3
        L_0x029f:
            boolean r14 = r13.mInPreLayout
            if (r14 == 0) goto L_0x02a8
            android.support.v7.widget.StaggeredGridLayoutManager$AnchorInfo r14 = r11.mAnchorInfo
            r14.reset()
        L_0x02a8:
            boolean r14 = r0.mLayoutFromEnd
            r11.mLastLayoutFromEnd = r14
            boolean r14 = r11.isLayoutRTL()
            r11.mLastLayoutRTL = r14
            if (r4 == 0) goto L_0x02bc
            android.support.v7.widget.StaggeredGridLayoutManager$AnchorInfo r14 = r11.mAnchorInfo
            r14.reset()
            r11.onLayoutChildren(r12, r13, r3)
        L_0x02bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.StaggeredGridLayoutManager.onLayoutChildren(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, boolean):void");
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$Span */
    class Span {
        int mCachedEnd = Integer.MIN_VALUE;
        int mCachedStart = Integer.MIN_VALUE;
        int mDeletedSize = 0;
        final int mIndex;
        ArrayList<View> mViews = new ArrayList<>();

        Span(int i) {
            this.mIndex = i;
        }

        /* access modifiers changed from: package-private */
        public void appendToSpan(View view) {
            LayoutParams layoutParams = getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(view);
            this.mCachedEnd = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view) + this.mDeletedSize;
            }
        }

        /* access modifiers changed from: package-private */
        public void calculateCachedEnd() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            ArrayList<View> arrayList = this.mViews;
            View view = arrayList.get(arrayList.size() - 1);
            LayoutParams layoutParams = getLayoutParams(view);
            this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
            if (layoutParams.mFullSpan && (fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition())) != null && fullSpanItem.mGapDir == 1) {
                this.mCachedEnd += fullSpanItem.getGapForSpan(this.mIndex);
            }
        }

        /* access modifiers changed from: package-private */
        public void calculateCachedStart() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            View view = this.mViews.get(0);
            LayoutParams layoutParams = getLayoutParams(view);
            this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            if (layoutParams.mFullSpan && (fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition())) != null && fullSpanItem.mGapDir == -1) {
                this.mCachedStart -= fullSpanItem.getGapForSpan(this.mIndex);
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            this.mViews.clear();
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
            this.mDeletedSize = 0;
        }

        public int findFirstPartiallyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
            }
            return findOnePartiallyVisibleChild(0, this.mViews.size(), true);
        }

        public int findLastPartiallyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOnePartiallyVisibleChild(0, this.mViews.size(), true);
            }
            return findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
        }

        /* access modifiers changed from: package-private */
        public int findOnePartiallyVisibleChild(int i, int i2, boolean z) {
            int startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            int endAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.mViews.get(i);
                int decoratedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
                int decoratedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
                boolean z2 = false;
                boolean z3 = !z ? decoratedStart < endAfterPadding : decoratedStart <= endAfterPadding;
                if (!z ? decoratedEnd > startAfterPadding : decoratedEnd >= startAfterPadding) {
                    z2 = true;
                }
                if (z3 && z2 && (decoratedStart < startAfterPadding || decoratedEnd > endAfterPadding)) {
                    return StaggeredGridLayoutManager.this.getPosition(view);
                }
                i += i3;
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public int getEndLine(int i) {
            int i2 = this.mCachedEnd;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.mViews.size() == 0) {
                return i;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        public View getFocusableViewAfter(int i, int i2) {
            View view = null;
            if (i2 != -1) {
                int size = this.mViews.size() - 1;
                while (size >= 0) {
                    View view2 = this.mViews.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.mReverseLayout && staggeredGridLayoutManager.getPosition(view2) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.mReverseLayout && staggeredGridLayoutManager2.getPosition(view2) <= i) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.mViews.size();
                int i3 = 0;
                while (i3 < size2) {
                    View view3 = this.mViews.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.mReverseLayout && staggeredGridLayoutManager3.getPosition(view3) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.mReverseLayout && staggeredGridLayoutManager4.getPosition(view3) >= i) || !view3.hasFocusable()) {
                        break;
                    }
                    i3++;
                    view = view3;
                }
            }
            return view;
        }

        /* access modifiers changed from: package-private */
        public LayoutParams getLayoutParams(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        /* access modifiers changed from: package-private */
        public int getStartLine(int i) {
            int i2 = this.mCachedStart;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.mViews.size() == 0) {
                return i;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        /* access modifiers changed from: package-private */
        public void popEnd() {
            int size = this.mViews.size();
            View remove = this.mViews.remove(size - 1);
            LayoutParams layoutParams = getLayoutParams(remove);
            layoutParams.mSpan = null;
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(remove);
            }
            if (size == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        /* access modifiers changed from: package-private */
        public void popStart() {
            View remove = this.mViews.remove(0);
            LayoutParams layoutParams = getLayoutParams(remove);
            layoutParams.mSpan = null;
            if (this.mViews.size() == 0) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(remove);
            }
            this.mCachedStart = Integer.MIN_VALUE;
        }

        /* access modifiers changed from: package-private */
        public void prependToSpan(View view) {
            LayoutParams layoutParams = getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(0, view);
            this.mCachedStart = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view) + this.mDeletedSize;
            }
        }

        /* access modifiers changed from: package-private */
        public int getEndLine() {
            int i = this.mCachedEnd;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        /* access modifiers changed from: package-private */
        public int getStartLine() {
            int i = this.mCachedStart;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }
    }

    /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup */
    static class LazySpanLookup {
        int[] mData;
        List<FullSpanItem> mFullSpanItems;

        LazySpanLookup() {
        }

        public void addFullSpanItem(FullSpanItem fullSpanItem) {
            if (this.mFullSpanItems == null) {
                this.mFullSpanItems = new ArrayList();
            }
            int size = this.mFullSpanItems.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(i);
                if (fullSpanItem2.mPosition == fullSpanItem.mPosition) {
                    this.mFullSpanItems.remove(i);
                }
                if (fullSpanItem2.mPosition >= fullSpanItem.mPosition) {
                    this.mFullSpanItems.add(i, fullSpanItem);
                    return;
                }
            }
            this.mFullSpanItems.add(fullSpanItem);
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            int[] iArr = this.mData;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.mFullSpanItems = null;
        }

        /* access modifiers changed from: package-private */
        public void ensureSize(int i) {
            int[] iArr = this.mData;
            if (iArr == null) {
                this.mData = new int[(Math.max(i, 10) + 1)];
                Arrays.fill(this.mData, -1);
            } else if (i >= iArr.length) {
                int length = iArr.length;
                while (length <= i) {
                    length *= 2;
                }
                this.mData = new int[length];
                System.arraycopy(iArr, 0, this.mData, 0, iArr.length);
                int[] iArr2 = this.mData;
                Arrays.fill(iArr2, iArr.length, iArr2.length, -1);
            }
        }

        /* access modifiers changed from: package-private */
        public int forceInvalidateAfter(int i) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.mFullSpanItems.get(size).mPosition >= i) {
                        this.mFullSpanItems.remove(size);
                    }
                }
            }
            return invalidateAfter(i);
        }

        public FullSpanItem getFirstFullSpanItemInRange(int i, int i2, int i3, boolean z) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(i4);
                int i5 = fullSpanItem.mPosition;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || fullSpanItem.mGapDir == i3 || (z && fullSpanItem.mHasUnwantedGapAfter))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem getFullSpanItem(int i) {
            List<FullSpanItem> list = this.mFullSpanItems;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                if (fullSpanItem.mPosition == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0048  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0052  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int invalidateAfter(int r5) {
            /*
                r4 = this;
                int[] r0 = r4.mData
                r1 = -1
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                int r0 = r0.length
                if (r5 < r0) goto L_0x000a
                return r1
            L_0x000a:
                java.util.List<android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r0 = r4.mFullSpanItems
                if (r0 != 0) goto L_0x0010
            L_0x000e:
                r0 = r1
                goto L_0x0046
            L_0x0010:
                android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r0 = r4.getFullSpanItem(r5)
                if (r0 == 0) goto L_0x001b
                java.util.List<android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r2 = r4.mFullSpanItems
                r2.remove(r0)
            L_0x001b:
                java.util.List<android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r0 = r4.mFullSpanItems
                int r0 = r0.size()
                r2 = 0
            L_0x0022:
                if (r2 >= r0) goto L_0x0034
                java.util.List<android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r3 = r4.mFullSpanItems
                java.lang.Object r3 = r3.get(r2)
                android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r3 = (android.support.p002v7.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem) r3
                int r3 = r3.mPosition
                if (r3 < r5) goto L_0x0031
                goto L_0x0035
            L_0x0031:
                int r2 = r2 + 1
                goto L_0x0022
            L_0x0034:
                r2 = r1
            L_0x0035:
                if (r2 == r1) goto L_0x000e
                java.util.List<android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r0 = r4.mFullSpanItems
                java.lang.Object r0 = r0.get(r2)
                android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r0 = (android.support.p002v7.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem) r0
                java.util.List<android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> r3 = r4.mFullSpanItems
                r3.remove(r2)
                int r0 = r0.mPosition
            L_0x0046:
                if (r0 != r1) goto L_0x0052
                int[] r0 = r4.mData
                int r2 = r0.length
                java.util.Arrays.fill(r0, r5, r2, r1)
                int[] r4 = r4.mData
                int r4 = r4.length
                return r4
            L_0x0052:
                int[] r4 = r4.mData
                int r0 = r0 + 1
                java.util.Arrays.fill(r4, r5, r0, r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.StaggeredGridLayoutManager.LazySpanLookup.invalidateAfter(int):int");
        }

        /* access modifiers changed from: package-private */
        public void offsetForAddition(int i, int i2) {
            int[] iArr = this.mData;
            if (iArr != null && i < iArr.length) {
                int i3 = i + i2;
                ensureSize(i3);
                int[] iArr2 = this.mData;
                System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
                Arrays.fill(this.mData, i, i3, -1);
                List<FullSpanItem> list = this.mFullSpanItems;
                if (list != null) {
                    for (int size = list.size() - 1; size >= 0; size--) {
                        FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                        int i4 = fullSpanItem.mPosition;
                        if (i4 >= i) {
                            fullSpanItem.mPosition = i4 + i2;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void offsetForRemoval(int i, int i2) {
            int[] iArr = this.mData;
            if (iArr != null && i < iArr.length) {
                int i3 = i + i2;
                ensureSize(i3);
                int[] iArr2 = this.mData;
                System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
                int[] iArr3 = this.mData;
                Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
                List<FullSpanItem> list = this.mFullSpanItems;
                if (list != null) {
                    for (int size = list.size() - 1; size >= 0; size--) {
                        FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                        int i4 = fullSpanItem.mPosition;
                        if (i4 >= i) {
                            if (i4 < i3) {
                                this.mFullSpanItems.remove(size);
                            } else {
                                fullSpanItem.mPosition = i4 - i2;
                            }
                        }
                    }
                }
            }
        }

        /* renamed from: android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem */
        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new Parcelable.Creator<FullSpanItem>() {
                public Object createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                public Object[] newArray(int i) {
                    return new FullSpanItem[i];
                }
            };
            int mGapDir;
            int[] mGapPerSpan;
            boolean mHasUnwantedGapAfter;
            int mPosition;

            FullSpanItem(Parcel parcel) {
                this.mPosition = parcel.readInt();
                this.mGapDir = parcel.readInt();
                this.mHasUnwantedGapAfter = parcel.readInt() != 1 ? false : true;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    this.mGapPerSpan = new int[readInt];
                    parcel.readIntArray(this.mGapPerSpan);
                }
            }

            public int describeContents() {
                return 0;
            }

            /* access modifiers changed from: package-private */
            public int getGapForSpan(int i) {
                int[] iArr = this.mGapPerSpan;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i];
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("FullSpanItem{mPosition=");
                outline13.append(this.mPosition);
                outline13.append(", mGapDir=");
                outline13.append(this.mGapDir);
                outline13.append(", mHasUnwantedGapAfter=");
                outline13.append(this.mHasUnwantedGapAfter);
                outline13.append(", mGapPerSpan=");
                outline13.append(Arrays.toString(this.mGapPerSpan));
                outline13.append('}');
                return outline13.toString();
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.mPosition);
                parcel.writeInt(this.mGapDir);
                parcel.writeInt(this.mHasUnwantedGapAfter ? 1 : 0);
                int[] iArr = this.mGapPerSpan;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                    return;
                }
                parcel.writeInt(iArr.length);
                parcel.writeIntArray(this.mGapPerSpan);
            }

            FullSpanItem() {
            }
        }
    }

    public StaggeredGridLayoutManager(int i, int i2) {
        this.mOrientation = i2;
        setSpanCount(i);
        this.mLayoutState = new LayoutState();
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }
}
