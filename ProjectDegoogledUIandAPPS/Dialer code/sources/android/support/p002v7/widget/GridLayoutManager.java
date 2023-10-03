package android.support.p002v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p002v7.widget.GapWorker;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: android.support.v7.widget.GridLayoutManager */
public class GridLayoutManager extends LinearLayoutManager {
    int[] mCachedBorders;
    final Rect mDecorInsets = new Rect();
    boolean mPendingSpanCountChange = false;
    final SparseIntArray mPreLayoutSpanIndexCache = new SparseIntArray();
    final SparseIntArray mPreLayoutSpanSizeCache = new SparseIntArray();
    View[] mSet;
    int mSpanCount = -1;
    SpanSizeLookup mSpanSizeLookup = new DefaultSpanSizeLookup();

    /* renamed from: android.support.v7.widget.GridLayoutManager$DefaultSpanSizeLookup */
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        public int getSpanIndex(int i, int i2) {
            return i % i2;
        }

        public int getSpanSize(int i) {
            return 1;
        }
    }

    /* renamed from: android.support.v7.widget.GridLayoutManager$SpanSizeLookup */
    public static abstract class SpanSizeLookup {
        private boolean mCacheSpanIndices = false;
        final SparseIntArray mSpanIndexCache = new SparseIntArray();

        /* access modifiers changed from: package-private */
        public int getCachedSpanIndex(int i, int i2) {
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(i, i2);
            }
            int i3 = this.mSpanIndexCache.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int spanIndex = getSpanIndex(i, i2);
            this.mSpanIndexCache.put(i, spanIndex);
            return spanIndex;
        }

        public int getSpanGroupIndex(int i, int i2) {
            int spanSize = getSpanSize(i);
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int spanSize2 = getSpanSize(i5);
                i3 += spanSize2;
                if (i3 == i2) {
                    i4++;
                    i3 = 0;
                } else if (i3 > i2) {
                    i4++;
                    i3 = spanSize2;
                }
            }
            return i3 + spanSize > i2 ? i4 + 1 : i4;
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x0055  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0067 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0068 A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getSpanIndex(int r8, int r9) {
            /*
                r7 = this;
                int r0 = r7.getSpanSize(r8)
                r1 = 0
                if (r0 != r9) goto L_0x0008
                return r1
            L_0x0008:
                boolean r2 = r7.mCacheSpanIndices
                if (r2 == 0) goto L_0x0051
                android.util.SparseIntArray r2 = r7.mSpanIndexCache
                int r2 = r2.size()
                if (r2 <= 0) goto L_0x0051
                android.util.SparseIntArray r2 = r7.mSpanIndexCache
                int r2 = r2.size()
                r3 = -1
                int r2 = r2 + r3
                r4 = r2
                r2 = r1
            L_0x001e:
                if (r2 > r4) goto L_0x0032
                int r5 = r2 + r4
                int r5 = r5 >>> 1
                android.util.SparseIntArray r6 = r7.mSpanIndexCache
                int r6 = r6.keyAt(r5)
                if (r6 >= r8) goto L_0x002f
                int r2 = r5 + 1
                goto L_0x001e
            L_0x002f:
                int r4 = r5 + -1
                goto L_0x001e
            L_0x0032:
                int r2 = r2 + r3
                if (r2 < 0) goto L_0x0043
                android.util.SparseIntArray r4 = r7.mSpanIndexCache
                int r4 = r4.size()
                if (r2 >= r4) goto L_0x0043
                android.util.SparseIntArray r3 = r7.mSpanIndexCache
                int r3 = r3.keyAt(r2)
            L_0x0043:
                if (r3 < 0) goto L_0x0051
                android.util.SparseIntArray r2 = r7.mSpanIndexCache
                int r2 = r2.get(r3)
                int r4 = r7.getSpanSize(r3)
                int r2 = r2 + r4
                goto L_0x0061
            L_0x0051:
                r2 = r1
                r3 = r2
            L_0x0053:
                if (r3 >= r8) goto L_0x0064
                int r4 = r7.getSpanSize(r3)
                int r2 = r2 + r4
                if (r2 != r9) goto L_0x005e
                r2 = r1
                goto L_0x0061
            L_0x005e:
                if (r2 <= r9) goto L_0x0061
                r2 = r4
            L_0x0061:
                int r3 = r3 + 1
                goto L_0x0053
            L_0x0064:
                int r0 = r0 + r2
                if (r0 > r9) goto L_0x0068
                return r2
            L_0x0068:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.GridLayoutManager.SpanSizeLookup.getSpanIndex(int, int):int");
        }

        public abstract int getSpanSize(int i);
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setSpanCount(RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2).spanCount);
    }

    private void calculateItemBorders(int i) {
        int i2;
        int[] iArr = this.mCachedBorders;
        int i3 = this.mSpanCount;
        if (!(iArr != null && iArr.length == i3 + 1 && iArr[iArr.length - 1] == i)) {
            iArr = new int[(i3 + 1)];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i / i3;
        int i6 = i % i3;
        int i7 = 0;
        for (int i8 = 1; i8 <= i3; i8++) {
            i4 += i6;
            if (i4 <= 0 || i3 - i4 >= i6) {
                i2 = i5;
            } else {
                i2 = i5 + 1;
                i4 -= i3;
            }
            i7 += i2;
            iArr[i8] = i7;
        }
        this.mCachedBorders = iArr;
    }

    private void ensureViewSet() {
        View[] viewArr = this.mSet;
        if (viewArr == null || viewArr.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }

    private int getSpanGroupIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (!state.mInPreLayout) {
            return this.mSpanSizeLookup.getSpanGroupIndex(i, this.mSpanCount);
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.mSpanSizeLookup.getSpanGroupIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    private int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (!state.mInPreLayout) {
            return this.mSpanSizeLookup.getCachedSpanIndex(i, this.mSpanCount);
        }
        int i2 = this.mPreLayoutSpanIndexCache.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.mSpanSizeLookup.getCachedSpanIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    private int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (!state.mInPreLayout) {
            return this.mSpanSizeLookup.getSpanSize(i);
        }
        int i2 = this.mPreLayoutSpanSizeCache.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.mSpanSizeLookup.getSpanSize(convertPreLayoutPositionToPostLayout);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    private void measureChild(View view, int i, boolean z) {
        int i2;
        int i3;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.mDecorInsets;
        int i4 = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        int i5 = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
        int spaceForSpanRange = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
        if (this.mOrientation == 1) {
            i2 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, i, i5, layoutParams.width, false);
            i3 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), i4, layoutParams.height, true);
        } else {
            int childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, i, i4, layoutParams.height, false);
            int childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), i5, layoutParams.width, true);
            i3 = childMeasureSpec;
            i2 = childMeasureSpec2;
        }
        measureChildWithDecorationsAndMargin(view, i2, i3, z);
    }

    private void measureChildWithDecorationsAndMargin(View view, int i, int i2, boolean z) {
        boolean z2;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z) {
            z2 = shouldReMeasureChild(view, i, i2, layoutParams);
        } else {
            z2 = shouldMeasureChild(view, i, i2, layoutParams);
        }
        if (z2) {
            view.measure(i, i2);
        }
    }

    private void updateMeasurements() {
        int i;
        int i2;
        if (getOrientation() == 1) {
            i2 = getWidth() - getPaddingRight();
            i = getPaddingLeft();
        } else {
            i2 = getHeight() - getPaddingBottom();
            i = getPaddingTop();
        }
        calculateItemBorders(i2 - i);
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: package-private */
    public void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i = this.mSpanCount;
        for (int i2 = 0; i2 < this.mSpanCount && layoutState.hasMore(state) && i > 0; i2++) {
            int i3 = layoutState.mCurrentPosition;
            ((GapWorker.LayoutPrefetchRegistryImpl) layoutPrefetchRegistry).addPosition(i3, Math.max(0, layoutState.mScrollingOffset));
            i -= this.mSpanSizeLookup.getSpanSize(i3);
            layoutState.mCurrentPosition += layoutState.mItemDirection;
        }
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
            if (position >= 0 && position < i3 && getSpanIndex(recycler, state, position) == 0) {
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
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    /* access modifiers changed from: package-private */
    public int getSpaceForSpanRange(int i, int i2) {
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            int[] iArr = this.mCachedBorders;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.mCachedBorders;
        int i3 = this.mSpanCount;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0252  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0254  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layoutChunk(android.support.p002v7.widget.RecyclerView.Recycler r21, android.support.p002v7.widget.RecyclerView.State r22, android.support.p002v7.widget.LinearLayoutManager.LayoutState r23, android.support.p002v7.widget.LinearLayoutManager.LayoutChunkResult r24) {
        /*
            r20 = this;
            r6 = r20
            r0 = r21
            r1 = r22
            r2 = r23
            r7 = r24
            android.support.v7.widget.OrientationHelper r3 = r6.mOrientationHelper
            int r3 = r3.getModeInOther()
            r4 = 1073741824(0x40000000, float:2.0)
            r8 = 1
            r5 = 0
            if (r3 == r4) goto L_0x0018
            r9 = r8
            goto L_0x0019
        L_0x0018:
            r9 = r5
        L_0x0019:
            int r10 = r20.getChildCount()
            if (r10 <= 0) goto L_0x0026
            int[] r10 = r6.mCachedBorders
            int r11 = r6.mSpanCount
            r10 = r10[r11]
            goto L_0x0027
        L_0x0026:
            r10 = r5
        L_0x0027:
            if (r9 == 0) goto L_0x002c
            r20.updateMeasurements()
        L_0x002c:
            int r11 = r2.mItemDirection
            if (r11 != r8) goto L_0x0032
            r11 = r8
            goto L_0x0033
        L_0x0032:
            r11 = r5
        L_0x0033:
            int r12 = r6.mSpanCount
            if (r11 != 0) goto L_0x0044
            int r12 = r2.mCurrentPosition
            int r12 = r6.getSpanIndex(r0, r1, r12)
            int r13 = r2.mCurrentPosition
            int r13 = r6.getSpanSize(r0, r1, r13)
            int r12 = r12 + r13
        L_0x0044:
            r13 = r12
            r12 = r5
        L_0x0046:
            int r14 = r6.mSpanCount
            if (r12 >= r14) goto L_0x009e
            boolean r14 = r2.hasMore(r1)
            if (r14 == 0) goto L_0x009e
            if (r13 <= 0) goto L_0x009e
            int r14 = r2.mCurrentPosition
            int r15 = r6.getSpanSize(r0, r1, r14)
            int r4 = r6.mSpanCount
            if (r15 > r4) goto L_0x0070
            int r13 = r13 - r15
            if (r13 >= 0) goto L_0x0060
            goto L_0x009e
        L_0x0060:
            android.view.View r4 = r2.next(r0)
            if (r4 != 0) goto L_0x0067
            goto L_0x009e
        L_0x0067:
            android.view.View[] r14 = r6.mSet
            r14[r12] = r4
            int r12 = r12 + 1
            r4 = 1073741824(0x40000000, float:2.0)
            goto L_0x0046
        L_0x0070:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Item at position "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r2 = " requires "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r2 = " spans but GridLayoutManager has only "
            r1.append(r2)
            int r2 = r6.mSpanCount
            r1.append(r2)
            java.lang.String r2 = " spans."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x009e:
            if (r12 != 0) goto L_0x00a3
            r7.mFinished = r8
            return
        L_0x00a3:
            if (r11 == 0) goto L_0x00ab
            r4 = r5
            r14 = r4
            r17 = r8
            r15 = r12
            goto L_0x00b1
        L_0x00ab:
            int r14 = r12 + -1
            r4 = r5
            r15 = -1
            r17 = -1
        L_0x00b1:
            if (r14 == r15) goto L_0x00d2
            android.view.View[] r13 = r6.mSet
            r13 = r13[r14]
            android.view.ViewGroup$LayoutParams r18 = r13.getLayoutParams()
            r8 = r18
            android.support.v7.widget.GridLayoutManager$LayoutParams r8 = (android.support.p002v7.widget.GridLayoutManager.LayoutParams) r8
            int r13 = r6.getPosition(r13)
            int r13 = r6.getSpanSize(r0, r1, r13)
            r8.mSpanSize = r13
            r8.mSpanIndex = r4
            int r8 = r8.mSpanSize
            int r4 = r4 + r8
            int r14 = r14 + r17
            r8 = 1
            goto L_0x00b1
        L_0x00d2:
            r0 = r5
            r1 = r0
            r16 = 0
        L_0x00d6:
            if (r0 >= r12) goto L_0x0121
            android.view.View[] r4 = r6.mSet
            r4 = r4[r0]
            java.util.List<android.support.v7.widget.RecyclerView$ViewHolder> r8 = r2.mScrapList
            if (r8 != 0) goto L_0x00ea
            if (r11 == 0) goto L_0x00e6
            r6.addView(r4)
            goto L_0x00f3
        L_0x00e6:
            r6.addView(r4, r5)
            goto L_0x00f3
        L_0x00ea:
            if (r11 == 0) goto L_0x00f0
            r6.addDisappearingView(r4)
            goto L_0x00f3
        L_0x00f0:
            r6.addDisappearingView(r4, r5)
        L_0x00f3:
            android.graphics.Rect r8 = r6.mDecorInsets
            r6.calculateItemDecorationsForChild(r4, r8)
            r6.measureChild(r4, r3, r5)
            android.support.v7.widget.OrientationHelper r8 = r6.mOrientationHelper
            int r8 = r8.getDecoratedMeasurement(r4)
            if (r8 <= r1) goto L_0x0104
            r1 = r8
        L_0x0104:
            android.view.ViewGroup$LayoutParams r8 = r4.getLayoutParams()
            android.support.v7.widget.GridLayoutManager$LayoutParams r8 = (android.support.p002v7.widget.GridLayoutManager.LayoutParams) r8
            r13 = 1065353216(0x3f800000, float:1.0)
            android.support.v7.widget.OrientationHelper r14 = r6.mOrientationHelper
            int r4 = r14.getDecoratedMeasurementInOther(r4)
            float r4 = (float) r4
            float r4 = r4 * r13
            int r8 = r8.mSpanSize
            float r8 = (float) r8
            float r4 = r4 / r8
            int r8 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r8 <= 0) goto L_0x011e
            r16 = r4
        L_0x011e:
            int r0 = r0 + 1
            goto L_0x00d6
        L_0x0121:
            if (r9 == 0) goto L_0x014d
            int r0 = r6.mSpanCount
            float r0 = (float) r0
            float r16 = r16 * r0
            int r0 = java.lang.Math.round(r16)
            int r0 = java.lang.Math.max(r0, r10)
            r6.calculateItemBorders(r0)
            r0 = r5
            r1 = r0
        L_0x0135:
            if (r0 >= r12) goto L_0x014d
            android.view.View[] r3 = r6.mSet
            r3 = r3[r0]
            r4 = 1073741824(0x40000000, float:2.0)
            r8 = 1
            r6.measureChild(r3, r4, r8)
            android.support.v7.widget.OrientationHelper r4 = r6.mOrientationHelper
            int r3 = r4.getDecoratedMeasurement(r3)
            if (r3 <= r1) goto L_0x014a
            r1 = r3
        L_0x014a:
            int r0 = r0 + 1
            goto L_0x0135
        L_0x014d:
            r0 = r5
        L_0x014e:
            if (r0 >= r12) goto L_0x01af
            android.view.View[] r3 = r6.mSet
            r3 = r3[r0]
            android.support.v7.widget.OrientationHelper r4 = r6.mOrientationHelper
            int r4 = r4.getDecoratedMeasurement(r3)
            if (r4 == r1) goto L_0x01a9
            android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
            android.support.v7.widget.GridLayoutManager$LayoutParams r4 = (android.support.p002v7.widget.GridLayoutManager.LayoutParams) r4
            android.graphics.Rect r8 = r4.mDecorInsets
            int r9 = r8.top
            int r10 = r8.bottom
            int r9 = r9 + r10
            int r10 = r4.topMargin
            int r9 = r9 + r10
            int r10 = r4.bottomMargin
            int r9 = r9 + r10
            int r10 = r8.left
            int r8 = r8.right
            int r10 = r10 + r8
            int r8 = r4.leftMargin
            int r10 = r10 + r8
            int r8 = r4.rightMargin
            int r10 = r10 + r8
            int r8 = r4.mSpanIndex
            int r11 = r4.mSpanSize
            int r8 = r6.getSpaceForSpanRange(r8, r11)
            int r11 = r6.mOrientation
            r13 = 1
            if (r11 != r13) goto L_0x0196
            int r4 = r4.width
            r11 = 1073741824(0x40000000, float:2.0)
            int r4 = android.support.p002v7.widget.RecyclerView.LayoutManager.getChildMeasureSpec(r8, r11, r10, r4, r5)
            int r8 = r1 - r9
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r11)
            goto L_0x01a5
        L_0x0196:
            r11 = 1073741824(0x40000000, float:2.0)
            int r10 = r1 - r10
            int r10 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r11)
            int r4 = r4.height
            int r8 = android.support.p002v7.widget.RecyclerView.LayoutManager.getChildMeasureSpec(r8, r11, r9, r4, r5)
            r4 = r10
        L_0x01a5:
            r6.measureChildWithDecorationsAndMargin(r3, r4, r8, r13)
            goto L_0x01ac
        L_0x01a9:
            r11 = 1073741824(0x40000000, float:2.0)
            r13 = 1
        L_0x01ac:
            int r0 = r0 + 1
            goto L_0x014e
        L_0x01af:
            r13 = 1
            r7.mConsumed = r1
            int r0 = r6.mOrientation
            if (r0 != r13) goto L_0x01ca
            int r0 = r2.mLayoutDirection
            r3 = -1
            if (r0 != r3) goto L_0x01c2
            int r0 = r2.mOffset
            int r1 = r0 - r1
            r3 = r0
            r2 = r1
            goto L_0x01c7
        L_0x01c2:
            int r0 = r2.mOffset
            int r1 = r1 + r0
            r2 = r0
            r3 = r1
        L_0x01c7:
            r0 = r5
            r1 = r0
            goto L_0x01e0
        L_0x01ca:
            r3 = -1
            int r0 = r2.mLayoutDirection
            if (r0 != r3) goto L_0x01db
            int r0 = r2.mOffset
            int r1 = r0 - r1
            r2 = r5
            r3 = r2
            r19 = r1
            r1 = r0
            r0 = r19
            goto L_0x01e0
        L_0x01db:
            int r0 = r2.mOffset
            int r1 = r1 + r0
            r2 = r5
            r3 = r2
        L_0x01e0:
            r8 = r5
        L_0x01e1:
            if (r8 >= r12) goto L_0x0268
            android.view.View[] r4 = r6.mSet
            r9 = r4[r8]
            android.view.ViewGroup$LayoutParams r4 = r9.getLayoutParams()
            r10 = r4
            android.support.v7.widget.GridLayoutManager$LayoutParams r10 = (android.support.p002v7.widget.GridLayoutManager.LayoutParams) r10
            int r4 = r6.mOrientation
            r5 = 1
            if (r4 != r5) goto L_0x0225
            boolean r0 = r20.isLayoutRTL()
            if (r0 == 0) goto L_0x0212
            int r0 = r20.getPaddingLeft()
            int[] r1 = r6.mCachedBorders
            int r4 = r6.mSpanCount
            int r5 = r10.mSpanIndex
            int r4 = r4 - r5
            r1 = r1[r4]
            int r0 = r0 + r1
            android.support.v7.widget.OrientationHelper r1 = r6.mOrientationHelper
            int r1 = r1.getDecoratedMeasurementInOther(r9)
            int r1 = r0 - r1
            r14 = r0
            r11 = r1
            goto L_0x0239
        L_0x0212:
            int r0 = r20.getPaddingLeft()
            int[] r1 = r6.mCachedBorders
            int r4 = r10.mSpanIndex
            r1 = r1[r4]
            int r0 = r0 + r1
            android.support.v7.widget.OrientationHelper r1 = r6.mOrientationHelper
            int r1 = r1.getDecoratedMeasurementInOther(r9)
            int r1 = r1 + r0
            goto L_0x0237
        L_0x0225:
            int r2 = r20.getPaddingTop()
            int[] r3 = r6.mCachedBorders
            int r4 = r10.mSpanIndex
            r3 = r3[r4]
            int r2 = r2 + r3
            android.support.v7.widget.OrientationHelper r3 = r6.mOrientationHelper
            int r3 = r3.getDecoratedMeasurementInOther(r9)
            int r3 = r3 + r2
        L_0x0237:
            r11 = r0
            r14 = r1
        L_0x0239:
            r13 = r2
            r15 = r3
            r0 = r20
            r1 = r9
            r2 = r11
            r3 = r13
            r4 = r14
            r5 = r15
            r0.layoutDecoratedWithMargins(r1, r2, r3, r4, r5)
            boolean r0 = r10.isItemRemoved()
            if (r0 != 0) goto L_0x0254
            boolean r0 = r10.isItemChanged()
            if (r0 == 0) goto L_0x0252
            goto L_0x0254
        L_0x0252:
            r0 = 1
            goto L_0x0257
        L_0x0254:
            r0 = 1
            r7.mIgnoreConsumed = r0
        L_0x0257:
            boolean r1 = r7.mFocusable
            boolean r2 = r9.hasFocusable()
            r1 = r1 | r2
            r7.mFocusable = r1
            int r8 = r8 + 1
            r0 = r11
            r2 = r13
            r1 = r14
            r3 = r15
            goto L_0x01e1
        L_0x0268:
            android.view.View[] r0 = r6.mSet
            r1 = 0
            java.util.Arrays.fill(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.GridLayoutManager.layoutChunk(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, android.support.v7.widget.LinearLayoutManager$LayoutState, android.support.v7.widget.LinearLayoutManager$LayoutChunkResult):void");
    }

    /* access modifiers changed from: package-private */
    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i) {
        updateMeasurements();
        if (state.getItemCount() > 0 && !state.mInPreLayout) {
            boolean z = i == 1;
            int spanIndex = getSpanIndex(recycler, state, anchorInfo.mPosition);
            if (z) {
                while (spanIndex > 0) {
                    int i2 = anchorInfo.mPosition;
                    if (i2 <= 0) {
                        break;
                    }
                    anchorInfo.mPosition = i2 - 1;
                    spanIndex = getSpanIndex(recycler, state, anchorInfo.mPosition);
                }
            } else {
                int itemCount = state.getItemCount() - 1;
                int i3 = anchorInfo.mPosition;
                while (i3 < itemCount) {
                    int i4 = i3 + 1;
                    int spanIndex2 = getSpanIndex(recycler, state, i4);
                    if (spanIndex2 <= spanIndex) {
                        break;
                    }
                    i3 = i4;
                    spanIndex = spanIndex2;
                }
                anchorInfo.mPosition = i3;
            }
        }
        ensureViewSet();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d7, code lost:
        if (r13 == (r2 > r8)) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f7, code lost:
        if (r13 == r11) goto L_0x00b7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0105  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onFocusSearchFailed(android.view.View r24, int r25, android.support.p002v7.widget.RecyclerView.Recycler r26, android.support.p002v7.widget.RecyclerView.State r27) {
        /*
            r23 = this;
            r0 = r23
            r1 = r26
            r2 = r27
            android.view.View r3 = r23.findContainingItemView(r24)
            r4 = 0
            if (r3 != 0) goto L_0x000e
            return r4
        L_0x000e:
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            android.support.v7.widget.GridLayoutManager$LayoutParams r5 = (android.support.p002v7.widget.GridLayoutManager.LayoutParams) r5
            int r6 = r5.mSpanIndex
            int r5 = r5.mSpanSize
            int r5 = r5 + r6
            android.view.View r7 = super.onFocusSearchFailed(r24, r25, r26, r27)
            if (r7 != 0) goto L_0x0020
            return r4
        L_0x0020:
            r7 = r25
            int r7 = r0.convertFocusDirectionToLayoutDirection(r7)
            r9 = 1
            if (r7 != r9) goto L_0x002b
            r7 = r9
            goto L_0x002c
        L_0x002b:
            r7 = 0
        L_0x002c:
            boolean r10 = r0.mShouldReverseLayout
            if (r7 == r10) goto L_0x0032
            r7 = r9
            goto L_0x0033
        L_0x0032:
            r7 = 0
        L_0x0033:
            r10 = -1
            if (r7 == 0) goto L_0x003e
            int r7 = r23.getChildCount()
            int r7 = r7 - r9
            r11 = r10
            r12 = r11
            goto L_0x0045
        L_0x003e:
            int r7 = r23.getChildCount()
            r11 = r7
            r12 = r9
            r7 = 0
        L_0x0045:
            int r13 = r0.mOrientation
            if (r13 != r9) goto L_0x0051
            boolean r13 = r23.isLayoutRTL()
            if (r13 == 0) goto L_0x0051
            r13 = r9
            goto L_0x0052
        L_0x0051:
            r13 = 0
        L_0x0052:
            int r14 = r0.getSpanGroupIndex(r1, r2, r7)
            r8 = r10
            r17 = r8
            r15 = 0
            r16 = 0
            r10 = r4
        L_0x005d:
            if (r7 == r11) goto L_0x0147
            int r9 = r0.getSpanGroupIndex(r1, r2, r7)
            android.view.View r1 = r0.getChildAt(r7)
            if (r1 != r3) goto L_0x006b
            goto L_0x0147
        L_0x006b:
            boolean r18 = r1.hasFocusable()
            if (r18 == 0) goto L_0x0085
            if (r9 == r14) goto L_0x0085
            if (r4 == 0) goto L_0x0077
            goto L_0x0147
        L_0x0077:
            r18 = r3
            r19 = r8
            r21 = r10
            r20 = r11
            r8 = r16
            r10 = r17
            goto L_0x0133
        L_0x0085:
            android.view.ViewGroup$LayoutParams r9 = r1.getLayoutParams()
            android.support.v7.widget.GridLayoutManager$LayoutParams r9 = (android.support.p002v7.widget.GridLayoutManager.LayoutParams) r9
            int r2 = r9.mSpanIndex
            r18 = r3
            int r3 = r9.mSpanSize
            int r3 = r3 + r2
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x009d
            if (r2 != r6) goto L_0x009d
            if (r3 != r5) goto L_0x009d
            return r1
        L_0x009d:
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x00a5
            if (r4 == 0) goto L_0x00ad
        L_0x00a5:
            boolean r19 = r1.hasFocusable()
            if (r19 != 0) goto L_0x00b9
            if (r10 != 0) goto L_0x00b9
        L_0x00ad:
            r19 = r8
            r21 = r10
        L_0x00b1:
            r20 = r11
            r8 = r16
            r10 = r17
        L_0x00b7:
            r11 = 1
            goto L_0x0103
        L_0x00b9:
            int r19 = java.lang.Math.max(r2, r6)
            int r20 = java.lang.Math.min(r3, r5)
            r21 = r10
            int r10 = r20 - r19
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x00da
            if (r10 <= r15) goto L_0x00d0
        L_0x00cd:
            r19 = r8
            goto L_0x00b1
        L_0x00d0:
            if (r10 != r15) goto L_0x00fa
            if (r2 <= r8) goto L_0x00d6
            r10 = 1
            goto L_0x00d7
        L_0x00d6:
            r10 = 0
        L_0x00d7:
            if (r13 != r10) goto L_0x00fa
            goto L_0x00cd
        L_0x00da:
            if (r4 != 0) goto L_0x00fa
            r19 = r8
            r20 = r11
            r8 = 0
            r11 = 1
            boolean r22 = r0.isViewPartiallyVisible(r1, r8, r11)
            r8 = r16
            if (r22 == 0) goto L_0x0100
            if (r10 <= r8) goto L_0x00ef
            r10 = r17
            goto L_0x0103
        L_0x00ef:
            if (r10 != r8) goto L_0x0100
            r10 = r17
            if (r2 <= r10) goto L_0x00f6
            goto L_0x00f7
        L_0x00f6:
            r11 = 0
        L_0x00f7:
            if (r13 != r11) goto L_0x0102
            goto L_0x00b7
        L_0x00fa:
            r19 = r8
            r20 = r11
            r8 = r16
        L_0x0100:
            r10 = r17
        L_0x0102:
            r11 = 0
        L_0x0103:
            if (r11 == 0) goto L_0x0133
            boolean r11 = r1.hasFocusable()
            if (r11 == 0) goto L_0x0120
            int r4 = r9.mSpanIndex
            int r3 = java.lang.Math.min(r3, r5)
            int r2 = java.lang.Math.max(r2, r6)
            int r3 = r3 - r2
            r15 = r3
            r16 = r8
            r17 = r10
            r10 = r21
            r8 = r4
            r4 = r1
            goto L_0x013b
        L_0x0120:
            int r8 = r9.mSpanIndex
            int r3 = java.lang.Math.min(r3, r5)
            int r2 = java.lang.Math.max(r2, r6)
            int r3 = r3 - r2
            r10 = r1
            r16 = r3
            r17 = r8
            r8 = r19
            goto L_0x013b
        L_0x0133:
            r16 = r8
            r17 = r10
            r8 = r19
            r10 = r21
        L_0x013b:
            int r7 = r7 + r12
            r1 = r26
            r2 = r27
            r3 = r18
            r11 = r20
            r9 = 1
            goto L_0x005d
        L_0x0147:
            r21 = r10
            if (r4 == 0) goto L_0x014c
            goto L_0x014e
        L_0x014c:
            r4 = r21
        L_0x014e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.GridLayoutManager.onFocusSearchFailed(android.view.View, int, android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):android.view.View");
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int spanGroupIndex = getSpanGroupIndex(recycler, state, layoutParams2.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            int i = layoutParams2.mSpanIndex;
            int i2 = layoutParams2.mSpanSize;
            int i3 = this.mSpanCount;
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i, i2, spanGroupIndex, 1, i3 > 1 && i2 == i3, false));
            return;
        }
        int i4 = layoutParams2.mSpanIndex;
        int i5 = layoutParams2.mSpanSize;
        int i6 = this.mSpanCount;
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanGroupIndex, 1, i4, i5, i6 > 1 && i5 == i6, false));
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        this.mSpanSizeLookup.mSpanIndexCache.clear();
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.mSpanSizeLookup.mSpanIndexCache.clear();
    }

    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        this.mSpanSizeLookup.mSpanIndexCache.clear();
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        this.mSpanSizeLookup.mSpanIndexCache.clear();
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.mSpanSizeLookup.mSpanIndexCache.clear();
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.mInPreLayout) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
                int viewLayoutPosition = layoutParams.getViewLayoutPosition();
                this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, layoutParams.mSpanSize);
                this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, layoutParams.mSpanIndex);
            }
        }
        super.onLayoutChildren(recycler, state);
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int i3;
        int i4;
        if (this.mCachedBorders == null) {
            setMeasuredDimension(RecyclerView.LayoutManager.chooseSize(i, getPaddingRight() + getPaddingLeft() + rect.width(), getMinimumWidth()), RecyclerView.LayoutManager.chooseSize(i2, getPaddingBottom() + getPaddingTop() + rect.height(), getMinimumHeight()));
        }
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            i4 = RecyclerView.LayoutManager.chooseSize(i2, rect.height() + paddingBottom, getMinimumHeight());
            int[] iArr = this.mCachedBorders;
            i3 = RecyclerView.LayoutManager.chooseSize(i, iArr[iArr.length - 1] + paddingRight, getMinimumWidth());
        } else {
            i3 = RecyclerView.LayoutManager.chooseSize(i, rect.width() + paddingRight, getMinimumWidth());
            int[] iArr2 = this.mCachedBorders;
            i4 = RecyclerView.LayoutManager.chooseSize(i2, iArr2[iArr2.length - 1] + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(i3, i4);
    }

    public void setSpanCount(int i) {
        if (i != this.mSpanCount) {
            this.mPendingSpanCountChange = true;
            if (i >= 1) {
                this.mSpanCount = i;
                this.mSpanSizeLookup.mSpanIndexCache.clear();
                requestLayout();
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("Span count should be at least 1. Provided ", i));
        }
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public void setStackFromEnd(boolean z) {
        if (!z) {
            super.setStackFromEnd(false);
            return;
        }
        throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* renamed from: android.support.v7.widget.GridLayoutManager$LayoutParams */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        int mSpanIndex = -1;
        int mSpanSize = 0;

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

    public GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i2, z);
        setSpanCount(i);
    }

    public GridLayoutManager(Context context, int i) {
        super(context, 1, false);
        setSpanCount(i);
    }
}
