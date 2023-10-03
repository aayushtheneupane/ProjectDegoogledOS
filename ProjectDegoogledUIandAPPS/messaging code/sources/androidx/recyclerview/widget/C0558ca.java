package androidx.recyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.p025a.C0361c;
import androidx.core.view.p025a.C0362d;
import androidx.core.view.p025a.C0363e;
import java.util.ArrayList;
import p000a.p012f.C0047b;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.ca */
public abstract class C0558ca {

    /* renamed from: Nr */
    private final C0528Da f626Nr = new C0551Y(this);

    /* renamed from: Or */
    private final C0528Da f627Or = new C0552Z(this);

    /* renamed from: Pr */
    C0530Ea f628Pr = new C0530Ea(this.f626Nr);

    /* renamed from: Qr */
    C0530Ea f629Qr = new C0530Ea(this.f627Or);

    /* renamed from: Rr */
    private boolean f630Rr = true;

    /* renamed from: Sr */
    private boolean f631Sr = true;

    /* renamed from: Tr */
    private int f632Tr;

    /* renamed from: Ur */
    private int f633Ur;
    boolean mAutoMeasure = false;
    C0559d mChildHelper;
    private int mHeight;
    boolean mIsAttachedToWindow = false;
    int mPrefetchMaxCountObserved;
    boolean mPrefetchMaxObservedInInitialPrefetch;
    RecyclerView mRecyclerView;
    boolean mRequestedSimpleAnimations = false;
    C0529E mSmoothScroller;
    private int mWidth;

    public static int chooseSize(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != Integer.MIN_VALUE) {
            return mode != 1073741824 ? Math.max(i2, i3) : size;
        }
        return Math.min(size, Math.max(i2, i3));
    }

    public static int getChildMeasureSpec(int i, int i2, int i3, int i4, boolean z) {
        int i5;
        int i6 = i - i3;
        int i7 = 0;
        int max = Math.max(0, i6);
        if (z) {
            if (i4 < 0) {
                if (i4 == -1) {
                    if (i2 == Integer.MIN_VALUE || (i2 != 0 && i2 == 1073741824)) {
                        i5 = max;
                    } else {
                        i2 = 0;
                        i5 = 0;
                    }
                    i7 = i2;
                    max = i5;
                    return View.MeasureSpec.makeMeasureSpec(max, i7);
                }
                max = 0;
                return View.MeasureSpec.makeMeasureSpec(max, i7);
            }
        } else if (i4 < 0) {
            if (i4 == -1) {
                i7 = i2;
            } else {
                if (i4 == -2) {
                    if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                        i7 = Integer.MIN_VALUE;
                    }
                }
                max = 0;
            }
            return View.MeasureSpec.makeMeasureSpec(max, i7);
        }
        max = i4;
        i7 = 1073741824;
        return View.MeasureSpec.makeMeasureSpec(max, i7);
    }

    public static C0556ba getProperties(Context context, AttributeSet attributeSet, int i, int i2) {
        C0556ba baVar = new C0556ba();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0047b.RecyclerView, i, i2);
        baVar.orientation = obtainStyledAttributes.getInt(C0047b.RecyclerView_android_orientation, 1);
        baVar.spanCount = obtainStyledAttributes.getInt(C0047b.RecyclerView_spanCount, 1);
        baVar.reverseLayout = obtainStyledAttributes.getBoolean(C0047b.RecyclerView_reverseLayout, false);
        baVar.stackFromEnd = obtainStyledAttributes.getBoolean(C0047b.RecyclerView_stackFromEnd, false);
        obtainStyledAttributes.recycle();
        return baVar;
    }

    /* renamed from: a */
    public int mo4710a(int i, C0572ja jaVar, C0582oa oaVar) {
        return 0;
    }

    /* renamed from: a */
    public View mo4712a(View view, int i, C0572ja jaVar, C0582oa oaVar) {
        return null;
    }

    /* renamed from: a */
    public void mo4745a(int i, int i2, C0582oa oaVar, C0554aa aaVar) {
    }

    /* renamed from: a */
    public void mo4746a(int i, C0554aa aaVar) {
    }

    /* renamed from: a */
    public void mo5018a(C0543P p, C0543P p2) {
    }

    /* renamed from: a */
    public void mo4714a(RecyclerView recyclerView, int i, int i2, int i3) {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5019a(RecyclerView recyclerView, C0572ja jaVar) {
        this.mIsAttachedToWindow = false;
        mo4750b(recyclerView, jaVar);
    }

    /* renamed from: a */
    public boolean mo5029a(RecyclerView recyclerView, ArrayList arrayList, int i, int i2) {
        return false;
    }

    /* renamed from: a */
    public boolean mo4720a(C0560da daVar) {
        return daVar != null;
    }

    /* renamed from: a */
    public boolean mo5031a(C0572ja jaVar, C0582oa oaVar, View view, int i, Bundle bundle) {
        return false;
    }

    public void addDisappearingView(View view) {
        addDisappearingView(view, -1);
    }

    public void addView(View view) {
        addView(view, -1);
    }

    public void assertNotInLayoutOrScroll(String str) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.assertNotInLayoutOrScroll(str);
        }
    }

    public void attachView(View view, int i) {
        mo5013a(view, i, (C0560da) view.getLayoutParams());
    }

    /* renamed from: b */
    public int mo4721b(int i, C0572ja jaVar, C0582oa oaVar) {
        return 0;
    }

    /* renamed from: b */
    public void mo4750b(RecyclerView recyclerView, C0572ja jaVar) {
        mo5049f(recyclerView);
    }

    /* renamed from: c */
    public int mo5040c(C0572ja jaVar, C0582oa oaVar) {
        return 0;
    }

    /* renamed from: c */
    public int mo4752c(C0582oa oaVar) {
        return 0;
    }

    /* renamed from: c */
    public void mo4723c(RecyclerView recyclerView, int i, int i2) {
    }

    /* renamed from: c */
    public void mo5041c(C0572ja jaVar) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (!RecyclerView.getChildViewHolderInt(getChildAt(childCount)).shouldIgnore()) {
                mo5012a(childCount, jaVar);
            }
        }
    }

    public void calculateItemDecorationsForChild(View view, Rect rect) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.getItemDecorInsetsForChild(view));
        }
    }

    public boolean canScrollHorizontally() {
        return false;
    }

    public boolean canScrollVertically() {
        return false;
    }

    /* renamed from: d */
    public int mo4724d(C0582oa oaVar) {
        return 0;
    }

    /* renamed from: d */
    public void mo4725d(RecyclerView recyclerView, int i, int i2) {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo5044d(C0572ja jaVar) {
        int size = jaVar.mAttachedScrap.size();
        for (int i = size - 1; i >= 0; i--) {
            View view = ((C0586qa) jaVar.mAttachedScrap.get(i)).itemView;
            C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.setIsRecyclable(false);
                if (childViewHolderInt.isTmpDetached()) {
                    this.mRecyclerView.removeDetachedView(view, false);
                }
                C0594ua uaVar = this.mRecyclerView.mItemAnimator;
                if (uaVar != null) {
                    uaVar.mo5179w(childViewHolderInt);
                }
                childViewHolderInt.setIsRecyclable(true);
                C0586qa childViewHolderInt2 = RecyclerView.getChildViewHolderInt(view);
                childViewHolderInt2.f666bt = null;
                childViewHolderInt2.f667ct = false;
                childViewHolderInt2.clearReturnedFromScrapFlag();
                jaVar.mo5146l(childViewHolderInt2);
            }
        }
        jaVar.mAttachedScrap.clear();
        ArrayList arrayList = jaVar.mChangedScrap;
        if (arrayList != null) {
            arrayList.clear();
        }
        if (size > 0) {
            this.mRecyclerView.invalidate();
        }
    }

    /* renamed from: d */
    public boolean mo5045d(C0572ja jaVar, C0582oa oaVar) {
        return false;
    }

    public void detachViewAt(int i) {
        getChildAt(i);
        this.mChildHelper.detachViewFromParent(i);
    }

    /* renamed from: e */
    public int mo4726e(C0582oa oaVar) {
        return 0;
    }

    /* renamed from: e */
    public void mo5047e(RecyclerView recyclerView) {
    }

    /* renamed from: e */
    public void mo5048e(RecyclerView recyclerView, int i, int i2) {
    }

    /* renamed from: e */
    public void mo4727e(C0572ja jaVar, C0582oa oaVar) {
        Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }

    /* renamed from: f */
    public int mo4759f(C0582oa oaVar) {
        return 0;
    }

    @Deprecated
    /* renamed from: f */
    public void mo5049f(RecyclerView recyclerView) {
    }

    public View findContainingItemView(View view) {
        View findContainingItemView;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (findContainingItemView = recyclerView.findContainingItemView(view)) == null || this.mChildHelper.mHiddenViews.contains(findContainingItemView)) {
            return null;
        }
        return findContainingItemView;
    }

    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
            if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.mInPreLayout || !childViewHolderInt.isRemoved())) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: g */
    public int mo4728g(C0582oa oaVar) {
        return 0;
    }

    /* renamed from: g */
    public void mo4729g(RecyclerView recyclerView) {
    }

    public abstract C0560da generateDefaultLayoutParams();

    public C0560da generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof C0560da) {
            return new C0560da((C0560da) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C0560da((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C0560da(layoutParams);
    }

    public int getBaseline() {
        return -1;
    }

    public int getBottomDecorationHeight(View view) {
        return ((C0560da) view.getLayoutParams()).mDecorInsets.bottom;
    }

    public View getChildAt(int i) {
        C0559d dVar = this.mChildHelper;
        if (dVar != null) {
            return dVar.getChildAt(i);
        }
        return null;
    }

    public int getChildCount() {
        C0559d dVar = this.mChildHelper;
        if (dVar != null) {
            return dVar.getChildCount();
        }
        return 0;
    }

    public boolean getClipToPadding() {
        RecyclerView recyclerView = this.mRecyclerView;
        return recyclerView != null && recyclerView.mClipToPadding;
    }

    public int getDecoratedBottom(View view) {
        return getBottomDecorationHeight(view) + view.getBottom();
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public int getDecoratedLeft(View view) {
        return view.getLeft() - getLeftDecorationWidth(view);
    }

    public int getDecoratedMeasuredHeight(View view) {
        Rect rect = ((C0560da) view.getLayoutParams()).mDecorInsets;
        return view.getMeasuredHeight() + rect.top + rect.bottom;
    }

    public int getDecoratedMeasuredWidth(View view) {
        Rect rect = ((C0560da) view.getLayoutParams()).mDecorInsets;
        return view.getMeasuredWidth() + rect.left + rect.right;
    }

    public int getDecoratedRight(View view) {
        return getRightDecorationWidth(view) + view.getRight();
    }

    public int getDecoratedTop(View view) {
        return view.getTop() - getTopDecorationHeight(view);
    }

    public View getFocusedChild() {
        View focusedChild;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.mChildHelper.mHiddenViews.contains(focusedChild)) {
            return null;
        }
        return focusedChild;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getHeightMode() {
        return this.f633Ur;
    }

    public int getItemCount() {
        RecyclerView recyclerView = this.mRecyclerView;
        C0543P adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        if (adapter != null) {
            return adapter.getItemCount();
        }
        return 0;
    }

    public int getLayoutDirection() {
        return ViewCompat.getLayoutDirection(this.mRecyclerView);
    }

    public int getLeftDecorationWidth(View view) {
        return ((C0560da) view.getLayoutParams()).mDecorInsets.left;
    }

    public int getMinimumHeight() {
        return ViewCompat.getMinimumHeight(this.mRecyclerView);
    }

    public int getMinimumWidth() {
        return ViewCompat.getMinimumWidth(this.mRecyclerView);
    }

    public int getPaddingBottom() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }

    public int getPaddingLeft() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public int getPaddingRight() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public int getPaddingTop() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public int getPosition(View view) {
        return ((C0560da) view.getLayoutParams()).getViewLayoutPosition();
    }

    public int getRightDecorationWidth(View view) {
        return ((C0560da) view.getLayoutParams()).mDecorInsets.right;
    }

    public int getTopDecorationHeight(View view) {
        return ((C0560da) view.getLayoutParams()).mDecorInsets.top;
    }

    public void getTransformedBoundingBox(View view, boolean z, Rect rect) {
        Matrix matrix;
        if (z) {
            Rect rect2 = ((C0560da) view.getLayoutParams()).mDecorInsets;
            rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        } else {
            rect.set(0, 0, view.getWidth(), view.getHeight());
        }
        if (!(this.mRecyclerView == null || (matrix = view.getMatrix()) == null || matrix.isIdentity())) {
            RectF rectF = this.mRecyclerView.mTempRectF;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getWidthMode() {
        return this.f632Tr;
    }

    /* renamed from: h */
    public int mo4733h(C0582oa oaVar) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo5081h(RecyclerView recyclerView) {
        setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    /* access modifiers changed from: package-private */
    public boolean hasFlexibleChildInBothOrientations() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup.LayoutParams layoutParams = getChildAt(i).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public void mo5083i(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.mRecyclerView = null;
            this.mChildHelper = null;
            this.mWidth = 0;
            this.mHeight = 0;
        } else {
            this.mRecyclerView = recyclerView;
            this.mChildHelper = recyclerView.mChildHelper;
            this.mWidth = recyclerView.getWidth();
            this.mHeight = recyclerView.getHeight();
        }
        this.f632Tr = 1073741824;
        this.f633Ur = 1073741824;
    }

    /* renamed from: i */
    public void mo4734i(C0582oa oaVar) {
    }

    public boolean isAttachedToWindow() {
        return this.mIsAttachedToWindow;
    }

    public boolean isAutoMeasureEnabled() {
        return this.mAutoMeasure;
    }

    public final boolean isItemPrefetchEnabled() {
        return this.f631Sr;
    }

    public boolean isSmoothScrolling() {
        C0529E e = this.mSmoothScroller;
        return e != null && e.isRunning();
    }

    public void layoutDecoratedWithMargins(View view, int i, int i2, int i3, int i4) {
        C0560da daVar = (C0560da) view.getLayoutParams();
        Rect rect = daVar.mDecorInsets;
        view.layout(i + rect.left + daVar.leftMargin, i2 + rect.top + daVar.topMargin, (i3 - rect.right) - daVar.rightMargin, (i4 - rect.bottom) - daVar.bottomMargin);
    }

    public void measureChildWithMargins(View view, int i, int i2) {
        C0560da daVar = (C0560da) view.getLayoutParams();
        Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
        int i3 = itemDecorInsetsForChild.left + itemDecorInsetsForChild.right + i;
        int i4 = itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom + i2;
        int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft() + daVar.leftMargin + daVar.rightMargin + i3, daVar.width, canScrollHorizontally());
        int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop() + daVar.topMargin + daVar.bottomMargin + i4, daVar.height, canScrollVertically());
        if (mo5023a(view, childMeasureSpec, childMeasureSpec2, daVar)) {
            view.measure(childMeasureSpec, childMeasureSpec2);
        }
    }

    public void moveView(int i, int i2) {
        View childAt = getChildAt(i);
        if (childAt != null) {
            detachViewAt(i);
            attachView(childAt, i2);
            return;
        }
        throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.mRecyclerView.toString());
    }

    public void offsetChildrenHorizontal(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenHorizontal(i);
        }
    }

    public void offsetChildrenVertical(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenVertical(i);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        mo5021a(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
    }

    public View onInterceptFocusSearch(View view, int i) {
        return null;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    public Parcelable onSaveInstanceState() {
        return null;
    }

    public void onScrollStateChanged(int i) {
    }

    /* access modifiers changed from: package-private */
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        RecyclerView recyclerView = this.mRecyclerView;
        return mo5030a(recyclerView.mRecycler, recyclerView.mState, i, bundle);
    }

    /* access modifiers changed from: package-private */
    public boolean performAccessibilityActionForItem(View view, int i, Bundle bundle) {
        RecyclerView recyclerView = this.mRecyclerView;
        return mo5031a(recyclerView.mRecycler, recyclerView.mState, view, i, bundle);
    }

    public boolean removeCallbacks(Runnable runnable) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.removeCallbacks(runnable);
        }
        return false;
    }

    public void removeView(View view) {
        this.mChildHelper.removeView(view);
    }

    public void removeViewAt(int i) {
        if (getChildAt(i) != null) {
            this.mChildHelper.removeViewAt(i);
        }
    }

    public void requestLayout() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public void requestSimpleAnimationsInNextLayout() {
        this.mRequestedSimpleAnimations = true;
    }

    public void scrollToPosition(int i) {
    }

    /* access modifiers changed from: package-private */
    public void setMeasureSpecs(int i, int i2) {
        this.mWidth = View.MeasureSpec.getSize(i);
        this.f632Tr = View.MeasureSpec.getMode(i);
        if (this.f632Tr == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.mWidth = 0;
        }
        this.mHeight = View.MeasureSpec.getSize(i2);
        this.f633Ur = View.MeasureSpec.getMode(i2);
        if (this.f633Ur == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.mHeight = 0;
        }
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        setMeasuredDimension(chooseSize(i, getPaddingRight() + getPaddingLeft() + rect.width(), getMinimumWidth()), chooseSize(i2, getPaddingBottom() + getPaddingTop() + rect.height(), getMinimumHeight()));
    }

    /* access modifiers changed from: package-private */
    public void setMeasuredDimensionFromChildren(int i, int i2) {
        int childCount = getChildCount();
        if (childCount == 0) {
            this.mRecyclerView.defaultOnMeasure(i, i2);
            return;
        }
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MAX_VALUE;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            Rect rect = this.mRecyclerView.mTempRect;
            getDecoratedBoundsWithMargins(childAt, rect);
            int i8 = rect.left;
            if (i8 < i3) {
                i3 = i8;
            }
            int i9 = rect.right;
            if (i9 > i4) {
                i4 = i9;
            }
            int i10 = rect.top;
            if (i10 < i6) {
                i6 = i10;
            }
            int i11 = rect.bottom;
            if (i11 > i5) {
                i5 = i11;
            }
        }
        this.mRecyclerView.mTempRect.set(i3, i6, i4, i5);
        setMeasuredDimension(this.mRecyclerView.mTempRect, i, i2);
    }

    /* access modifiers changed from: package-private */
    public boolean shouldMeasureTwice() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void stopSmoothScroller() {
        C0529E e = this.mSmoothScroller;
        if (e != null) {
            e.stop();
        }
    }

    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    /* renamed from: e */
    private static boolean m820e(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    public void addDisappearingView(View view, int i) {
        m819a(view, i, true);
    }

    public void addView(View view, int i) {
        m819a(view, i, false);
    }

    /* renamed from: b */
    public void mo5037b(C0529E e) {
        C0529E e2 = this.mSmoothScroller;
        if (!(e2 == null || e == e2 || !e2.isRunning())) {
            this.mSmoothScroller.stop();
        }
        this.mSmoothScroller = e;
        this.mSmoothScroller.mo4647a(this.mRecyclerView, this);
    }

    /* renamed from: a */
    public void mo4747a(RecyclerView recyclerView, C0582oa oaVar, int i) {
        Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
    }

    /* renamed from: a */
    private void m819a(View view, int i, boolean z) {
        C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (z || childViewHolderInt.isRemoved()) {
            this.mRecyclerView.mViewInfoStore.mo4707y(childViewHolderInt);
        } else {
            this.mRecyclerView.mViewInfoStore.mo4697E(childViewHolderInt);
        }
        C0560da daVar = (C0560da) view.getLayoutParams();
        if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            this.mChildHelper.attachViewToParent(view, i, view.getLayoutParams(), false);
        } else if (view.getParent() == this.mRecyclerView) {
            int indexOfChild = this.mChildHelper.indexOfChild(view);
            if (i == -1) {
                i = this.mChildHelper.getChildCount();
            }
            if (indexOfChild == -1) {
                StringBuilder Pa = C0632a.m1011Pa("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                Pa.append(this.mRecyclerView.indexOfChild(view));
                Pa.append(this.mRecyclerView.mo4815Vb());
                throw new IllegalStateException(Pa.toString());
            } else if (indexOfChild != i) {
                this.mRecyclerView.mLayout.moveView(indexOfChild, i);
            }
        } else {
            this.mChildHelper.addView(view, i, false);
            daVar.mInsetsDirty = true;
            C0529E e = this.mSmoothScroller;
            if (e != null && e.isRunning()) {
                this.mSmoothScroller.onChildAttachedToWindow(view);
            }
        }
        if (daVar.mPendingInvalidate) {
            childViewHolderInt.itemView.invalidate();
            daVar.mPendingInvalidate = false;
        }
    }

    public C0560da generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new C0560da(context, attributeSet);
    }

    public void setMeasuredDimension(int i, int i2) {
        this.mRecyclerView.setMeasuredDimension(i, i2);
    }

    /* renamed from: b */
    public void mo5038b(C0572ja jaVar) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
            if (!childViewHolderInt.shouldIgnore()) {
                if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || this.mRecyclerView.mAdapter.hasStableIds()) {
                    detachViewAt(childCount);
                    jaVar.scrapView(childAt);
                    this.mRecyclerView.mViewInfoStore.mo4694B(childViewHolderInt);
                } else {
                    removeViewAt(childCount);
                    jaVar.mo5146l(childViewHolderInt);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo5039b(View view, int i, int i2, C0560da daVar) {
        return !this.f630Rr || !m820e(view.getMeasuredWidth(), i, daVar.width) || !m820e(view.getMeasuredHeight(), i2, daVar.height);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo5043d(RecyclerView recyclerView) {
        this.mIsAttachedToWindow = true;
        mo5047e(recyclerView);
    }

    /* renamed from: b */
    public int mo4722b(C0572ja jaVar, C0582oa oaVar) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.mAdapter == null || !canScrollVertically()) {
            return 1;
        }
        return this.mRecyclerView.mAdapter.getItemCount();
    }

    /* renamed from: a */
    public void mo5013a(View view, int i, C0560da daVar) {
        C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.isRemoved()) {
            this.mRecyclerView.mViewInfoStore.mo4707y(childViewHolderInt);
        } else {
            this.mRecyclerView.mViewInfoStore.mo4697E(childViewHolderInt);
        }
        this.mChildHelper.attachViewToParent(view, i, daVar, childViewHolderInt.isRemoved());
    }

    /* renamed from: a */
    public void mo5015a(View view, C0572ja jaVar) {
        removeView(view);
        jaVar.recycleView(view);
    }

    /* renamed from: a */
    public void mo5012a(int i, C0572ja jaVar) {
        View childAt = getChildAt(i);
        removeViewAt(i);
        jaVar.recycleView(childAt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo5023a(View view, int i, int i2, C0560da daVar) {
        return view.isLayoutRequested() || !this.f630Rr || !m820e(view.getWidth(), i, daVar.width) || !m820e(view.getHeight(), i2, daVar.height);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00bb, code lost:
        if (r9 == false) goto L_0x00c2;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo5026a(androidx.recyclerview.widget.RecyclerView r10, android.view.View r11, android.graphics.Rect r12, boolean r13, boolean r14) {
        /*
            r9 = this;
            r0 = 2
            int[] r0 = new int[r0]
            int r1 = r9.getPaddingLeft()
            int r2 = r9.getPaddingTop()
            int r3 = r9.getWidth()
            int r4 = r9.getPaddingRight()
            int r3 = r3 - r4
            int r4 = r9.getHeight()
            int r5 = r9.getPaddingBottom()
            int r4 = r4 - r5
            int r5 = r11.getLeft()
            int r6 = r12.left
            int r5 = r5 + r6
            int r6 = r11.getScrollX()
            int r5 = r5 - r6
            int r6 = r11.getTop()
            int r7 = r12.top
            int r6 = r6 + r7
            int r11 = r11.getScrollY()
            int r6 = r6 - r11
            int r11 = r12.width()
            int r11 = r11 + r5
            int r12 = r12.height()
            int r12 = r12 + r6
            int r5 = r5 - r1
            r1 = 0
            int r7 = java.lang.Math.min(r1, r5)
            int r6 = r6 - r2
            int r2 = java.lang.Math.min(r1, r6)
            int r11 = r11 - r3
            int r3 = java.lang.Math.max(r1, r11)
            int r12 = r12 - r4
            int r12 = java.lang.Math.max(r1, r12)
            int r4 = r9.getLayoutDirection()
            r8 = 1
            if (r4 != r8) goto L_0x0063
            if (r3 == 0) goto L_0x005e
            goto L_0x006b
        L_0x005e:
            int r3 = java.lang.Math.max(r7, r11)
            goto L_0x006b
        L_0x0063:
            if (r7 == 0) goto L_0x0066
            goto L_0x006a
        L_0x0066:
            int r7 = java.lang.Math.min(r5, r3)
        L_0x006a:
            r3 = r7
        L_0x006b:
            if (r2 == 0) goto L_0x006e
            goto L_0x0072
        L_0x006e:
            int r2 = java.lang.Math.min(r6, r12)
        L_0x0072:
            r0[r1] = r3
            r0[r8] = r2
            r11 = r0[r1]
            r12 = r0[r8]
            if (r14 == 0) goto L_0x00bd
            android.view.View r14 = r10.getFocusedChild()
            if (r14 != 0) goto L_0x0084
        L_0x0082:
            r9 = r1
            goto L_0x00bb
        L_0x0084:
            int r0 = r9.getPaddingLeft()
            int r2 = r9.getPaddingTop()
            int r3 = r9.getWidth()
            int r4 = r9.getPaddingRight()
            int r3 = r3 - r4
            int r4 = r9.getHeight()
            int r5 = r9.getPaddingBottom()
            int r4 = r4 - r5
            androidx.recyclerview.widget.RecyclerView r5 = r9.mRecyclerView
            android.graphics.Rect r5 = r5.mTempRect
            r9.getDecoratedBoundsWithMargins(r14, r5)
            int r9 = r5.left
            int r9 = r9 - r11
            if (r9 >= r3) goto L_0x0082
            int r9 = r5.right
            int r9 = r9 - r11
            if (r9 <= r0) goto L_0x0082
            int r9 = r5.top
            int r9 = r9 - r12
            if (r9 >= r4) goto L_0x0082
            int r9 = r5.bottom
            int r9 = r9 - r12
            if (r9 > r2) goto L_0x00ba
            goto L_0x0082
        L_0x00ba:
            r9 = r8
        L_0x00bb:
            if (r9 == 0) goto L_0x00c2
        L_0x00bd:
            if (r11 != 0) goto L_0x00c3
            if (r12 == 0) goto L_0x00c2
            goto L_0x00c3
        L_0x00c2:
            return r1
        L_0x00c3:
            if (r13 == 0) goto L_0x00c9
            r10.scrollBy(r11, r12)
            goto L_0x00cc
        L_0x00c9:
            r10.smoothScrollBy(r11, r12)
        L_0x00cc:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.C0558ca.mo5026a(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    /* renamed from: a */
    public boolean mo5025a(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        return mo5026a(recyclerView, view, rect, z, false);
    }

    /* renamed from: a */
    public boolean mo5024a(View view, boolean z, boolean z2) {
        boolean z3 = this.f628Pr.mo4660c(view, 24579) && this.f629Qr.mo4660c(view, 24579);
        return z ? z3 : !z3;
    }

    @Deprecated
    /* renamed from: a */
    public boolean mo5027a(RecyclerView recyclerView, View view, View view2) {
        return isSmoothScrolling() || recyclerView.isComputingLayout();
    }

    /* renamed from: a */
    public boolean mo5028a(RecyclerView recyclerView, C0582oa oaVar, View view, View view2) {
        return mo5027a(recyclerView, view, view2);
    }

    /* renamed from: a */
    public void mo4715a(RecyclerView recyclerView, int i, int i2, Object obj) {
        mo5048e(recyclerView, i, i2);
    }

    /* renamed from: a */
    public void mo5020a(C0572ja jaVar, C0582oa oaVar, int i, int i2) {
        this.mRecyclerView.defaultOnMeasure(i, i2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5017a(C0529E e) {
        if (this.mSmoothScroller == e) {
            this.mSmoothScroller = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5016a(C0363e eVar) {
        RecyclerView recyclerView = this.mRecyclerView;
        mo5022a(recyclerView.mRecycler, recyclerView.mState, eVar);
    }

    /* renamed from: a */
    public void mo5022a(C0572ja jaVar, C0582oa oaVar, C0363e eVar) {
        if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
            eVar.addAction(8192);
            eVar.setScrollable(true);
        }
        if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
            eVar.addAction(NotificationCompat.FLAG_BUBBLE);
            eVar.setScrollable(true);
        }
        eVar.mo3856g(C0361c.obtain(mo4722b(jaVar, oaVar), mo4711a(jaVar, oaVar), mo5045d(jaVar, oaVar), mo5040c(jaVar, oaVar)));
    }

    /* renamed from: a */
    public void mo5021a(C0572ja jaVar, C0582oa oaVar, AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && accessibilityEvent != null) {
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            C0543P p = this.mRecyclerView.mAdapter;
            if (p != null) {
                accessibilityEvent.setItemCount(p.getItemCount());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5014a(View view, C0363e eVar) {
        C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && !this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
            RecyclerView recyclerView = this.mRecyclerView;
            mo4716a(recyclerView.mRecycler, recyclerView.mState, view, eVar);
        }
    }

    /* renamed from: a */
    public void mo4716a(C0572ja jaVar, C0582oa oaVar, View view, C0363e eVar) {
        int i = 0;
        int position = canScrollVertically() ? getPosition(view) : 0;
        if (canScrollHorizontally()) {
            i = getPosition(view);
        }
        eVar.mo3858h(C0362d.obtain(position, 1, i, 1, false, false));
    }

    /* renamed from: a */
    public int mo4711a(C0572ja jaVar, C0582oa oaVar) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.mAdapter == null || !canScrollHorizontally()) {
            return 1;
        }
        return this.mRecyclerView.mAdapter.getItemCount();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0075 A[ADDED_TO_REGION] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo5030a(androidx.recyclerview.widget.C0572ja r8, androidx.recyclerview.widget.C0582oa r9, int r10, android.os.Bundle r11) {
        /*
            r7 = this;
            androidx.recyclerview.widget.RecyclerView r8 = r7.mRecyclerView
            r9 = 0
            if (r8 != 0) goto L_0x0006
            return r9
        L_0x0006:
            r11 = 4096(0x1000, float:5.74E-42)
            r0 = 1
            if (r10 == r11) goto L_0x0042
            r11 = 8192(0x2000, float:1.14794E-41)
            if (r10 == r11) goto L_0x0012
            r2 = r9
            r3 = r2
            goto L_0x0073
        L_0x0012:
            r10 = -1
            boolean r8 = r8.canScrollVertically(r10)
            if (r8 == 0) goto L_0x0029
            int r8 = r7.getHeight()
            int r11 = r7.getPaddingTop()
            int r8 = r8 - r11
            int r11 = r7.getPaddingBottom()
            int r8 = r8 - r11
            int r8 = -r8
            goto L_0x002a
        L_0x0029:
            r8 = r9
        L_0x002a:
            androidx.recyclerview.widget.RecyclerView r11 = r7.mRecyclerView
            boolean r10 = r11.canScrollHorizontally(r10)
            if (r10 == 0) goto L_0x0071
            int r10 = r7.getWidth()
            int r11 = r7.getPaddingLeft()
            int r10 = r10 - r11
            int r11 = r7.getPaddingRight()
            int r10 = r10 - r11
            int r10 = -r10
            goto L_0x006e
        L_0x0042:
            boolean r8 = r8.canScrollVertically(r0)
            if (r8 == 0) goto L_0x0057
            int r8 = r7.getHeight()
            int r10 = r7.getPaddingTop()
            int r8 = r8 - r10
            int r10 = r7.getPaddingBottom()
            int r8 = r8 - r10
            goto L_0x0058
        L_0x0057:
            r8 = r9
        L_0x0058:
            androidx.recyclerview.widget.RecyclerView r10 = r7.mRecyclerView
            boolean r10 = r10.canScrollHorizontally(r0)
            if (r10 == 0) goto L_0x0071
            int r10 = r7.getWidth()
            int r11 = r7.getPaddingLeft()
            int r10 = r10 - r11
            int r11 = r7.getPaddingRight()
            int r10 = r10 - r11
        L_0x006e:
            r3 = r8
            r2 = r10
            goto L_0x0073
        L_0x0071:
            r3 = r8
            r2 = r9
        L_0x0073:
            if (r3 != 0) goto L_0x0078
            if (r2 != 0) goto L_0x0078
            return r9
        L_0x0078:
            androidx.recyclerview.widget.RecyclerView r1 = r7.mRecyclerView
            r4 = 0
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = 1
            r1.mo4817a(r2, r3, r4, r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.C0558ca.mo5030a(androidx.recyclerview.widget.ja, androidx.recyclerview.widget.oa, int, android.os.Bundle):boolean");
    }
}
