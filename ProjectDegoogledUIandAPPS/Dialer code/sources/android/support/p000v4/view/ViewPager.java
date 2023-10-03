package android.support.p000v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: android.support.v4.view.ViewPager */
public class ViewPager extends ViewGroup {
    private static final Comparator<ItemInfo> COMPARATOR = new Comparator<ItemInfo>() {
        public int compare(Object obj, Object obj2) {
            return ((ItemInfo) obj).position - ((ItemInfo) obj2).position;
        }
    };
    static final int[] LAYOUT_ATTRS = {16842931};
    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
    private int mActivePointerId = -1;
    PagerAdapter mAdapter;
    private List<OnAdapterChangeListener> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable = new Runnable() {
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.populate();
        }
    };
    private int mExpectedAdapterCount;
    private boolean mFakeDragging;
    private boolean mFirstLayout = true;
    private float mFirstOffset = -3.4028235E38f;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems = new ArrayList<>();
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset = Float.MAX_VALUE;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit = 1;
    private OnPageChangeListener mOnPageChangeListener;
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private int mPageMargin;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState = null;
    private ClassLoader mRestoredClassLoader = null;
    private int mRestoredCurItem = -1;
    private EdgeEffect mRightEdge;
    private int mScrollState = 0;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final ItemInfo mTempItem = new ItemInfo();
    private final Rect mTempRect = new Rect();
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    @Inherited
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* renamed from: android.support.v4.view.ViewPager$DecorView */
    public @interface DecorView {
    }

    /* renamed from: android.support.v4.view.ViewPager$ItemInfo */
    static class ItemInfo {
        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;

        ItemInfo() {
        }
    }

    /* renamed from: android.support.v4.view.ViewPager$MyAccessibilityDelegate */
    class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        MyAccessibilityDelegate() {
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            PagerAdapter pagerAdapter;
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            PagerAdapter pagerAdapter2 = ViewPager.this.mAdapter;
            boolean z = true;
            if (pagerAdapter2 == null || pagerAdapter2.getCount() <= 1) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            if (accessibilityEvent.getEventType() == 4096 && (pagerAdapter = ViewPager.this.mAdapter) != null) {
                accessibilityEvent.setItemCount(pagerAdapter.getCount());
                accessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
                accessibilityEvent.setToIndex(ViewPager.this.mCurItem);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
            PagerAdapter pagerAdapter = ViewPager.this.mAdapter;
            accessibilityNodeInfoCompat.setScrollable(pagerAdapter != null && pagerAdapter.getCount() > 1);
            if (ViewPager.this.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
            }
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            if (i != 4096) {
                if (i != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.mCurItem - 1);
                return true;
            } else if (!ViewPager.this.canScrollHorizontally(1)) {
                return false;
            } else {
                ViewPager viewPager2 = ViewPager.this;
                viewPager2.setCurrentItem(viewPager2.mCurItem + 1);
                return true;
            }
        }
    }

    /* renamed from: android.support.v4.view.ViewPager$OnAdapterChangeListener */
    public interface OnAdapterChangeListener {
        void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    /* renamed from: android.support.v4.view.ViewPager$OnPageChangeListener */
    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, int i2);

        void onPageSelected(int i);
    }

    /* renamed from: android.support.v4.view.ViewPager$PagerObserver */
    private class PagerObserver extends DataSetObserver {
        PagerObserver() {
        }

        public void onChanged() {
            ViewPager.this.dataSetChanged();
        }

        public void onInvalidated() {
            ViewPager.this.dataSetChanged();
        }
    }

    /* renamed from: android.support.v4.view.ViewPager$SavedState */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public Object[] newArray(int i) {
                return new SavedState[i];
            }

            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        Parcelable adapterState;
        ClassLoader loader;
        int position;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("FragmentPager.SavedState{");
            outline13.append(Integer.toHexString(System.identityHashCode(this)));
            outline13.append(" position=");
            outline13.append(this.position);
            outline13.append("}");
            return outline13.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.position);
            parcel.writeParcelable(this.adapterState, i);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? SavedState.class.getClassLoader() : classLoader;
            this.position = parcel.readInt();
            this.adapterState = parcel.readParcelable(classLoader);
            this.loader = classLoader;
        }
    }

    /* renamed from: android.support.v4.view.ViewPager$ViewPositionComparator */
    static class ViewPositionComparator implements Comparator<View> {
        ViewPositionComparator() {
        }

        public int compare(Object obj, Object obj2) {
            LayoutParams layoutParams = (LayoutParams) ((View) obj).getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) ((View) obj2).getLayoutParams();
            boolean z = layoutParams.isDecor;
            if (z != layoutParams2.isDecor) {
                return z ? 1 : -1;
            }
            return layoutParams.position - layoutParams2.position;
        }
    }

    public ViewPager(Context context) {
        super(context);
        initViewPager();
    }

    private void completeScroll(boolean z) {
        boolean z2 = this.mScrollState == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (!(scrollX == currX && scrollY == currY)) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        pageScrolled(currX);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        boolean z3 = z2;
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo itemInfo = this.mItems.get(i);
            if (itemInfo.scrolling) {
                itemInfo.scrolling = false;
                z3 = true;
            }
        }
        if (!z3) {
            return;
        }
        if (z) {
            ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
        } else {
            this.mEndScrollRunnable.run();
        }
    }

    private void dispatchOnPageSelected(int i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListeners.get(i2);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.onPageSelected(i);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageSelected(i);
        }
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left = viewGroup.getLeft() + rect.left;
            rect.right = viewGroup.getRight() + rect.right;
            rect.top = viewGroup.getTop() + rect.top;
            rect.bottom = viewGroup.getBottom() + rect.bottom;
            parent = viewGroup.getParent();
        }
        return rect;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private ItemInfo infoForCurrentScrollPosition() {
        int i;
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? ((float) getScrollX()) / ((float) clientWidth) : 0.0f;
        float f = clientWidth > 0 ? ((float) this.mPageMargin) / ((float) clientWidth) : 0.0f;
        ItemInfo itemInfo = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i2 = 0;
        int i3 = -1;
        boolean z = true;
        while (i2 < this.mItems.size()) {
            ItemInfo itemInfo2 = this.mItems.get(i2);
            if (!z && itemInfo2.position != (i = i3 + 1)) {
                itemInfo2 = this.mTempItem;
                itemInfo2.offset = f2 + f3 + f;
                itemInfo2.position = i;
                this.mAdapter.getPageWidth(itemInfo2.position);
                itemInfo2.widthFactor = 1.0f;
                i2--;
            }
            f2 = itemInfo2.offset;
            float f4 = itemInfo2.widthFactor + f2 + f;
            if (!z && scrollX < f2) {
                return itemInfo;
            }
            if (scrollX < f4 || i2 == this.mItems.size() - 1) {
                return itemInfo2;
            }
            i3 = itemInfo2.position;
            f3 = itemInfo2.widthFactor;
            i2++;
            z = false;
            itemInfo = itemInfo2;
        }
        return itemInfo;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            this.mLastMotionX = motionEvent.getX(i);
            this.mActivePointerId = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private boolean pageScrolled(int i) {
        if (this.mItems.size() != 0) {
            ItemInfo infoForCurrentScrollPosition = infoForCurrentScrollPosition();
            int clientWidth = getClientWidth();
            int i2 = this.mPageMargin;
            int i3 = clientWidth + i2;
            float f = (float) clientWidth;
            int i4 = infoForCurrentScrollPosition.position;
            float f2 = ((((float) i) / f) - infoForCurrentScrollPosition.offset) / (infoForCurrentScrollPosition.widthFactor + (((float) i2) / f));
            this.mCalledSuper = false;
            onPageScrolled(i4, f2, (int) (((float) i3) * f2));
            if (this.mCalledSuper) {
                return true;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else if (this.mFirstLayout) {
            return false;
        } else {
            this.mCalledSuper = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
    }

    private boolean performDrag(float f) {
        boolean z;
        boolean z2;
        float f2 = this.mLastMotionX - f;
        this.mLastMotionX = f;
        float scrollX = ((float) getScrollX()) + f2;
        float clientWidth = (float) getClientWidth();
        float f3 = this.mFirstOffset * clientWidth;
        float f4 = this.mLastOffset * clientWidth;
        boolean z3 = false;
        ItemInfo itemInfo = this.mItems.get(0);
        ArrayList<ItemInfo> arrayList = this.mItems;
        ItemInfo itemInfo2 = arrayList.get(arrayList.size() - 1);
        if (itemInfo.position != 0) {
            f3 = itemInfo.offset * clientWidth;
            z = false;
        } else {
            z = true;
        }
        if (itemInfo2.position != this.mAdapter.getCount() - 1) {
            f4 = itemInfo2.offset * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (scrollX < f3) {
            if (z) {
                this.mLeftEdge.onPull(Math.abs(f3 - scrollX) / clientWidth);
                z3 = true;
            }
            scrollX = f3;
        } else if (scrollX > f4) {
            if (z2) {
                this.mRightEdge.onPull(Math.abs(scrollX - f4) / clientWidth);
                z3 = true;
            }
            scrollX = f4;
        }
        int i = (int) scrollX;
        this.mLastMotionX = (scrollX - ((float) i)) + this.mLastMotionX;
        scrollTo(i, getScrollY());
        pageScrolled(i);
        return z3;
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        if (this.mLeftEdge.isFinished() || this.mRightEdge.isFinished()) {
            return true;
        }
        return false;
    }

    private void scrollToItem(int i, boolean z, int i2, boolean z2) {
        int i3;
        ItemInfo infoForPosition = infoForPosition(i);
        if (infoForPosition != null) {
            i3 = (int) (Math.max(this.mFirstOffset, Math.min(infoForPosition.offset, this.mLastOffset)) * ((float) getClientWidth()));
        } else {
            i3 = 0;
        }
        if (z) {
            smoothScrollTo(i3, 0, i2);
            if (z2) {
                dispatchOnPageSelected(i);
                return;
            }
            return;
        }
        if (z2) {
            dispatchOnPageSelected(i);
        }
        completeScroll(false);
        scrollTo(i3, 0);
        pageScrolled(i3);
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.mDrawingOrderedChildren.add(getChildAt(i));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        ItemInfo infoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.mCurItem) {
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if ((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) {
            arrayList.add(this);
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo addNewItem(int i, int i2) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.position = i;
        itemInfo.object = this.mAdapter.instantiateItem(this, i);
        this.mAdapter.getPageWidth(i);
        itemInfo.widthFactor = 1.0f;
        if (i2 < 0 || i2 >= this.mItems.size()) {
            this.mItems.add(itemInfo);
        } else {
            this.mItems.add(i2, itemInfo);
        }
        return itemInfo;
    }

    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(onPageChangeListener);
    }

    public void addTouchables(ArrayList<View> arrayList) {
        ItemInfo infoForChild;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.mCurItem) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        layoutParams2.isDecor |= view.getClass().getAnnotation(DecorView.class) != null;
        if (!this.mInLayout) {
            super.addView(view, i, layoutParams);
        } else if (!layoutParams2.isDecor) {
            layoutParams2.needsMeasure = true;
            addViewInLayout(view, i, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean arrowScroll(int r7) {
        /*
            r6 = this;
            android.view.View r0 = r6.findFocus()
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != r6) goto L_0x000a
            goto L_0x0063
        L_0x000a:
            if (r0 == 0) goto L_0x0064
            android.view.ViewParent r4 = r0.getParent()
        L_0x0010:
            boolean r5 = r4 instanceof android.view.ViewGroup
            if (r5 == 0) goto L_0x001d
            if (r4 != r6) goto L_0x0018
            r4 = r1
            goto L_0x001e
        L_0x0018:
            android.view.ViewParent r4 = r4.getParent()
            goto L_0x0010
        L_0x001d:
            r4 = r2
        L_0x001e:
            if (r4 != 0) goto L_0x0064
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r4.append(r5)
            android.view.ViewParent r0 = r0.getParent()
        L_0x0034:
            boolean r5 = r0 instanceof android.view.ViewGroup
            if (r5 == 0) goto L_0x004d
            java.lang.String r5 = " => "
            r4.append(r5)
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r4.append(r5)
            android.view.ViewParent r0 = r0.getParent()
            goto L_0x0034
        L_0x004d:
            java.lang.String r0 = "arrowScroll tried to find focus based on non-child current focused view "
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r4 = "ViewPager"
            android.util.Log.e(r4, r0)
        L_0x0063:
            r0 = r3
        L_0x0064:
            android.view.FocusFinder r3 = android.view.FocusFinder.getInstance()
            android.view.View r3 = r3.findNextFocus(r6, r0, r7)
            r4 = 66
            r5 = 17
            if (r3 == 0) goto L_0x00b5
            if (r3 == r0) goto L_0x00b5
            if (r7 != r5) goto L_0x0095
            android.graphics.Rect r1 = r6.mTempRect
            android.graphics.Rect r1 = r6.getChildRectInPagerCoordinates(r1, r3)
            int r1 = r1.left
            android.graphics.Rect r2 = r6.mTempRect
            android.graphics.Rect r2 = r6.getChildRectInPagerCoordinates(r2, r0)
            int r2 = r2.left
            if (r0 == 0) goto L_0x008f
            if (r1 < r2) goto L_0x008f
            boolean r0 = r6.pageLeft()
            goto L_0x0093
        L_0x008f:
            boolean r0 = r3.requestFocus()
        L_0x0093:
            r2 = r0
            goto L_0x00c8
        L_0x0095:
            if (r7 != r4) goto L_0x00c8
            android.graphics.Rect r1 = r6.mTempRect
            android.graphics.Rect r1 = r6.getChildRectInPagerCoordinates(r1, r3)
            int r1 = r1.left
            android.graphics.Rect r2 = r6.mTempRect
            android.graphics.Rect r2 = r6.getChildRectInPagerCoordinates(r2, r0)
            int r2 = r2.left
            if (r0 == 0) goto L_0x00b0
            if (r1 > r2) goto L_0x00b0
            boolean r0 = r6.pageRight()
            goto L_0x0093
        L_0x00b0:
            boolean r0 = r3.requestFocus()
            goto L_0x0093
        L_0x00b5:
            if (r7 == r5) goto L_0x00c4
            if (r7 != r1) goto L_0x00ba
            goto L_0x00c4
        L_0x00ba:
            if (r7 == r4) goto L_0x00bf
            r0 = 2
            if (r7 != r0) goto L_0x00c8
        L_0x00bf:
            boolean r2 = r6.pageRight()
            goto L_0x00c8
        L_0x00c4:
            boolean r2 = r6.pageLeft()
        L_0x00c8:
            if (r2 == 0) goto L_0x00d1
            int r7 = android.view.SoundEffectConstants.getContantForFocusDirection(r7)
            r6.playSoundEffect(r7)
        L_0x00d1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.ViewPager.arrowScroll(int):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        int i4;
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i5 = i2 + scrollX;
                if (i5 >= childAt.getLeft() && i5 < childAt.getRight() && (i4 = i3 + scrollY) >= childAt.getTop() && i4 < childAt.getBottom()) {
                    if (canScroll(childAt, true, i, i5 - childAt.getLeft(), i4 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (!z || !view.canScrollHorizontally(-i)) {
            return false;
        }
        return true;
    }

    public boolean canScrollHorizontally(int i) {
        if (this.mAdapter == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i < 0) {
            if (scrollX > ((int) (((float) clientWidth) * this.mFirstOffset))) {
                return true;
            }
            return false;
        } else if (i <= 0 || scrollX >= ((int) (((float) clientWidth) * this.mLastOffset))) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            completeScroll(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!pageScrolled(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: package-private */
    public void dataSetChanged() {
        int count = this.mAdapter.getCount();
        this.mExpectedAdapterCount = count;
        boolean z = this.mItems.size() < (this.mOffscreenPageLimit * 2) + 1 && this.mItems.size() < count;
        int i = this.mCurItem;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < this.mItems.size()) {
            ItemInfo itemInfo = this.mItems.get(i2);
            int itemPosition = this.mAdapter.getItemPosition(itemInfo.object);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.mItems.remove(i2);
                    i2--;
                    if (!z2) {
                        this.mAdapter.startUpdate(this);
                        z2 = true;
                    }
                    this.mAdapter.destroyItem(this, itemInfo.position, itemInfo.object);
                    int i3 = this.mCurItem;
                    if (i3 == itemInfo.position) {
                        i = Math.max(0, Math.min(i3, count - 1));
                    }
                } else {
                    int i4 = itemInfo.position;
                    if (i4 != itemPosition) {
                        if (i4 == this.mCurItem) {
                            i = itemPosition;
                        }
                        itemInfo.position = itemPosition;
                    }
                }
                z = true;
            }
            i2++;
        }
        if (z2) {
            this.mAdapter.finishUpdate(this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (z) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i5).getLayoutParams();
                if (!layoutParams.isDecor) {
                    layoutParams.widthFactor = 0.0f;
                }
            }
            setCurrentItemInternal(i, false, true);
            requestLayout();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ItemInfo infoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    public void draw(Canvas canvas) {
        PagerAdapter pagerAdapter;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean z = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (pagerAdapter = this.mAdapter) != null && pagerAdapter.getCount() > 1)) {
            if (!this.mLeftEdge.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) (getPaddingTop() + (-height)), this.mFirstOffset * ((float) width));
                this.mLeftEdge.setSize(height, width);
                z = false | this.mLeftEdge.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.mRightEdge.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.mLastOffset + 1.0f)) * ((float) width2));
                this.mRightEdge.setSize(height2, width2);
                z |= this.mRightEdge.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 21) {
                if (keyCode != 22) {
                    if (keyCode == 61) {
                        if (keyEvent.hasNoModifiers()) {
                            return arrowScroll(2);
                        }
                        if (keyEvent.hasModifiers(1)) {
                            return arrowScroll(1);
                        }
                    }
                } else if (keyEvent.hasModifiers(2)) {
                    return pageRight();
                } else {
                    return arrowScroll(66);
                }
            } else if (keyEvent.hasModifiers(2)) {
                return pageLeft();
            } else {
                return arrowScroll(17);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        if (this.mDrawingOrder == 2) {
            i2 = (i - 1) - i2;
        }
        return ((LayoutParams) this.mDrawingOrderedChildren.get(i2).getLayoutParams()).childIndex;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForAnyChild(View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent == this) {
                return infoForChild(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForChild(View view) {
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo itemInfo = this.mItems.get(i);
            if (this.mAdapter.isViewFromObject(view, itemInfo.object)) {
                return itemInfo;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForPosition(int i) {
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            ItemInfo itemInfo = this.mItems.get(i2);
            if (itemInfo.position == i) {
                return itemInfo;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context);
        this.mRightEdge = new EdgeEffect(context);
        this.mFlingDistance = (int) (25.0f * f);
        this.mCloseEnough = (int) (2.0f * f);
        this.mDefaultGutterSize = (int) (f * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            int i = Build.VERSION.SDK_INT;
            setImportantForAccessibility(1);
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
            private final Rect mTempRect = new Rect();

            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat onApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                if (onApplyWindowInsets.isConsumed()) {
                    return onApplyWindowInsets;
                }
                Rect rect = this.mTempRect;
                rect.left = onApplyWindowInsets.getSystemWindowInsetLeft();
                rect.top = onApplyWindowInsets.getSystemWindowInsetTop();
                rect.right = onApplyWindowInsets.getSystemWindowInsetRight();
                rect.bottom = onApplyWindowInsets.getSystemWindowInsetBottom();
                int childCount = ViewPager.this.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = ViewPager.this.getChildAt(i);
                    int i2 = Build.VERSION.SDK_INT;
                    WindowInsets windowInsets = (WindowInsets) WindowInsetsCompat.unwrap(onApplyWindowInsets);
                    WindowInsets dispatchApplyWindowInsets = childAt.dispatchApplyWindowInsets(windowInsets);
                    if (dispatchApplyWindowInsets != windowInsets) {
                        windowInsets = new WindowInsets(dispatchApplyWindowInsets);
                    }
                    WindowInsetsCompat wrap = WindowInsetsCompat.wrap(windowInsets);
                    rect.left = Math.min(wrap.getSystemWindowInsetLeft(), rect.left);
                    rect.top = Math.min(wrap.getSystemWindowInsetTop(), rect.top);
                    rect.right = Math.min(wrap.getSystemWindowInsetRight(), rect.right);
                    rect.bottom = Math.min(wrap.getSystemWindowInsetBottom(), rect.bottom);
                }
                return onApplyWindowInsets.replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        float f2;
        super.onDraw(canvas);
        if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f3 = (float) width;
            float f4 = ((float) this.mPageMargin) / f3;
            int i = 0;
            ItemInfo itemInfo = this.mItems.get(0);
            float f5 = itemInfo.offset;
            int size = this.mItems.size();
            int i2 = itemInfo.position;
            int i3 = this.mItems.get(size - 1).position;
            while (i2 < i3) {
                while (i2 > itemInfo.position && i < size) {
                    i++;
                    itemInfo = this.mItems.get(i);
                }
                if (i2 == itemInfo.position) {
                    float f6 = itemInfo.offset;
                    float f7 = itemInfo.widthFactor;
                    f = (f6 + f7) * f3;
                    f5 = f6 + f7 + f4;
                } else {
                    this.mAdapter.getPageWidth(i2);
                    f = (f5 + 1.0f) * f3;
                    f5 = 1.0f + f4 + f5;
                }
                if (((float) this.mPageMargin) + f > ((float) scrollX)) {
                    f2 = f4;
                    this.mMarginDrawable.setBounds(Math.round(f), this.mTopPageBounds, Math.round(((float) this.mPageMargin) + f), this.mBottomPageBounds);
                    this.mMarginDrawable.draw(canvas);
                } else {
                    Canvas canvas2 = canvas;
                    f2 = f4;
                }
                if (f <= ((float) (scrollX + width))) {
                    i2++;
                    f4 = f2;
                } else {
                    return;
                }
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            resetTouch();
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.mInitialMotionX = x;
            this.mLastMotionX = x;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent2.getPointerId(0);
            this.mIsUnableToDrag = false;
            this.mIsScrollStarted = true;
            this.mScroller.computeScrollOffset();
            if (this.mScrollState != 2 || Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) <= this.mCloseEnough) {
                completeScroll(false);
                this.mIsBeingDragged = false;
            } else {
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                populate();
                this.mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i = this.mActivePointerId;
            if (i != -1) {
                int findPointerIndex = motionEvent2.findPointerIndex(i);
                float x2 = motionEvent2.getX(findPointerIndex);
                float f = x2 - this.mLastMotionX;
                float abs = Math.abs(f);
                float y2 = motionEvent2.getY(findPointerIndex);
                float abs2 = Math.abs(y2 - this.mInitialMotionY);
                int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (i2 != 0) {
                    float f2 = this.mLastMotionX;
                    if (!((f2 < ((float) this.mGutterSize) && i2 > 0) || (f2 > ((float) (getWidth() - this.mGutterSize)) && f < 0.0f))) {
                        if (canScroll(this, false, (int) f, (int) x2, (int) y2)) {
                            this.mLastMotionX = x2;
                            this.mLastMotionY = y2;
                            this.mIsUnableToDrag = true;
                            return false;
                        }
                    }
                }
                if (abs > ((float) this.mTouchSlop) && abs * 0.5f > abs2) {
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                    float f3 = this.mInitialMotionX;
                    float f4 = (float) this.mTouchSlop;
                    this.mLastMotionX = i2 > 0 ? f3 + f4 : f3 - f4;
                    this.mLastMotionY = y2;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > ((float) this.mTouchSlop)) {
                    this.mIsUnableToDrag = true;
                }
                if (this.mIsBeingDragged && performDrag(x2)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else if (action == 6) {
            onSecondaryPointerUp(motionEvent);
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent2);
        return this.mIsBeingDragged;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        ItemInfo infoForChild;
        int i5;
        int i6;
        int childCount = getChildCount();
        int i7 = i3 - i;
        int i8 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i9 = paddingBottom;
        int i10 = 0;
        int i11 = paddingTop;
        int i12 = paddingLeft;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isDecor) {
                    int i14 = layoutParams.gravity;
                    int i15 = i14 & 7;
                    int i16 = i14 & 112;
                    if (i15 == 1) {
                        i5 = Math.max((i7 - childAt.getMeasuredWidth()) / 2, i12);
                    } else if (i15 == 3) {
                        i5 = i12;
                        i12 = childAt.getMeasuredWidth() + i12;
                    } else if (i15 != 5) {
                        i5 = i12;
                    } else {
                        i5 = (i7 - paddingRight) - childAt.getMeasuredWidth();
                        paddingRight += childAt.getMeasuredWidth();
                    }
                    if (i16 == 16) {
                        i6 = Math.max((i8 - childAt.getMeasuredHeight()) / 2, i11);
                    } else if (i16 == 48) {
                        i6 = i11;
                        i11 = childAt.getMeasuredHeight() + i11;
                    } else if (i16 != 80) {
                        i6 = i11;
                    } else {
                        i6 = (i8 - i9) - childAt.getMeasuredHeight();
                        i9 += childAt.getMeasuredHeight();
                    }
                    int i17 = i5 + scrollX;
                    childAt.layout(i17, i6, childAt.getMeasuredWidth() + i17, childAt.getMeasuredHeight() + i6);
                    i10++;
                }
            }
        }
        int i18 = (i7 - i12) - paddingRight;
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt2 = getChildAt(i19);
            if (childAt2.getVisibility() != 8) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams2.isDecor && (infoForChild = infoForChild(childAt2)) != null) {
                    float f = (float) i18;
                    int i20 = ((int) (infoForChild.offset * f)) + i12;
                    if (layoutParams2.needsMeasure) {
                        layoutParams2.needsMeasure = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f * layoutParams2.widthFactor), 1073741824), View.MeasureSpec.makeMeasureSpec((i8 - i11) - i9, 1073741824));
                    }
                    childAt2.layout(i20, i11, childAt2.getMeasuredWidth() + i20, childAt2.getMeasuredHeight() + i11);
                }
            }
        }
        this.mTopPageBounds = i11;
        this.mBottomPageBounds = i8 - i9;
        this.mDecorChildCount = i10;
        if (this.mFirstLayout) {
            z2 = false;
            scrollToItem(this.mCurItem, false, 0, false);
        } else {
            z2 = false;
        }
        this.mFirstLayout = z2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        LayoutParams layoutParams;
        LayoutParams layoutParams2;
        int i3;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i), ViewGroup.getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i4 = measuredHeight;
        int i5 = paddingLeft;
        int i6 = 0;
        while (true) {
            boolean z = true;
            int i7 = 1073741824;
            if (i6 >= childCount) {
                break;
            }
            View childAt = getChildAt(i6);
            if (!(childAt.getVisibility() == 8 || (layoutParams2 = (LayoutParams) childAt.getLayoutParams()) == null || !layoutParams2.isDecor)) {
                int i8 = layoutParams2.gravity;
                int i9 = i8 & 7;
                int i10 = i8 & 112;
                boolean z2 = i10 == 48 || i10 == 80;
                if (!(i9 == 3 || i9 == 5)) {
                    z = false;
                }
                int i11 = Integer.MIN_VALUE;
                if (z2) {
                    i3 = Integer.MIN_VALUE;
                    i11 = 1073741824;
                } else {
                    i3 = z ? 1073741824 : Integer.MIN_VALUE;
                }
                int i12 = layoutParams2.width;
                if (i12 != -2) {
                    if (i12 == -1) {
                        i12 = i5;
                    }
                    i11 = 1073741824;
                } else {
                    i12 = i5;
                }
                int i13 = layoutParams2.height;
                if (i13 == -2) {
                    i13 = i4;
                    i7 = i3;
                } else if (i13 == -1) {
                    i13 = i4;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i12, i11), View.MeasureSpec.makeMeasureSpec(i13, i7));
                if (z2) {
                    i4 -= childAt.getMeasuredHeight();
                } else if (z) {
                    i5 -= childAt.getMeasuredWidth();
                }
            }
            i6++;
        }
        View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        int childCount2 = getChildCount();
        for (int i14 = 0; i14 < childCount2; i14++) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != 8 && ((layoutParams = (LayoutParams) childAt2.getLayoutParams()) == null || !layoutParams.isDecor)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (((float) i5) * layoutParams.widthFactor), 1073741824), this.mChildHeightMeasureSpec);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPageScrolled(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.mDecorChildCount
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L_0x006d
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r4
            r4 = r3
            r3 = r1
        L_0x001d:
            if (r3 >= r6) goto L_0x006d
            android.view.View r8 = r12.getChildAt(r3)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            android.support.v4.view.ViewPager$LayoutParams r9 = (android.support.p000v4.view.ViewPager.LayoutParams) r9
            boolean r10 = r9.isDecor
            if (r10 != 0) goto L_0x002e
            goto L_0x006a
        L_0x002e:
            int r9 = r9.gravity
            r9 = r9 & 7
            if (r9 == r2) goto L_0x004f
            r10 = 3
            if (r9 == r10) goto L_0x0049
            r10 = 5
            if (r9 == r10) goto L_0x003c
            r9 = r4
            goto L_0x005e
        L_0x003c:
            int r9 = r5 - r7
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r7 = r7 + r10
            goto L_0x005b
        L_0x0049:
            int r9 = r8.getWidth()
            int r9 = r9 + r4
            goto L_0x005e
        L_0x004f:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r4)
        L_0x005b:
            r11 = r9
            r9 = r4
            r4 = r11
        L_0x005e:
            int r4 = r4 + r0
            int r10 = r8.getLeft()
            int r4 = r4 - r10
            if (r4 == 0) goto L_0x0069
            r8.offsetLeftAndRight(r4)
        L_0x0069:
            r4 = r9
        L_0x006a:
            int r3 = r3 + 1
            goto L_0x001d
        L_0x006d:
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r12.mOnPageChangeListener
            if (r0 == 0) goto L_0x0074
            r0.onPageScrolled(r13, r14, r15)
        L_0x0074:
            java.util.List<android.support.v4.view.ViewPager$OnPageChangeListener> r0 = r12.mOnPageChangeListeners
            if (r0 == 0) goto L_0x008e
            int r0 = r0.size()
        L_0x007c:
            if (r1 >= r0) goto L_0x008e
            java.util.List<android.support.v4.view.ViewPager$OnPageChangeListener> r3 = r12.mOnPageChangeListeners
            java.lang.Object r3 = r3.get(r1)
            android.support.v4.view.ViewPager$OnPageChangeListener r3 = (android.support.p000v4.view.ViewPager.OnPageChangeListener) r3
            if (r3 == 0) goto L_0x008b
            r3.onPageScrolled(r13, r14, r15)
        L_0x008b:
            int r1 = r1 + 1
            goto L_0x007c
        L_0x008e:
            android.support.v4.view.ViewPager$OnPageChangeListener r0 = r12.mInternalPageChangeListener
            if (r0 == 0) goto L_0x0095
            r0.onPageScrolled(r13, r14, r15)
        L_0x0095:
            r12.mCalledSuper = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.ViewPager.onPageScrolled(int, float, int):void");
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3;
        ItemInfo infoForChild;
        int childCount = getChildCount();
        int i4 = -1;
        if ((i & 2) != 0) {
            i4 = childCount;
            i3 = 0;
            i2 = 1;
        } else {
            i3 = childCount - 1;
            i2 = -1;
        }
        while (i3 != i4) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.mCurItem && childAt.requestFocus(i, rect)) {
                return true;
            }
            i3 += i2;
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.restoreState(savedState.adapterState, savedState.loader);
            setCurrentItemInternal(savedState.position, false, true);
            return;
        }
        this.mRestoredCurItem = savedState.position;
        this.mRestoredAdapterState = savedState.adapterState;
        this.mRestoredClassLoader = savedState.loader;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.position = this.mCurItem;
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            savedState.adapterState = pagerAdapter.saveState();
        }
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            int i5 = this.mPageMargin;
            if (i3 <= 0 || this.mItems.isEmpty()) {
                ItemInfo infoForPosition = infoForPosition(this.mCurItem);
                int min = (int) ((infoForPosition != null ? Math.min(infoForPosition.offset, this.mLastOffset) : 0.0f) * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
                if (min != getScrollX()) {
                    completeScroll(false);
                    scrollTo(min, getScrollY());
                }
            } else if (!this.mScroller.isFinished()) {
                this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
            } else {
                scrollTo((int) ((((float) getScrollX()) / ((float) (((i3 - getPaddingLeft()) - getPaddingRight()) + i5))) * ((float) (((i - getPaddingLeft()) - getPaddingRight()) + i5))), getScrollY());
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        PagerAdapter pagerAdapter;
        if (this.mFakeDragging) {
            return true;
        }
        boolean z = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (pagerAdapter = this.mAdapter) == null || pagerAdapter.getCount() == 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.mScroller.abortAnimation();
            this.mPopulatePending = false;
            populate();
            float x = motionEvent.getX();
            this.mInitialMotionX = x;
            this.mLastMotionX = x;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent.getPointerId(0);
        } else if (action != 1) {
            if (action == 2) {
                if (!this.mIsBeingDragged) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex == -1) {
                        z = resetTouch();
                    } else {
                        float x2 = motionEvent.getX(findPointerIndex);
                        float abs = Math.abs(x2 - this.mLastMotionX);
                        float y2 = motionEvent.getY(findPointerIndex);
                        float abs2 = Math.abs(y2 - this.mLastMotionY);
                        if (abs > ((float) this.mTouchSlop) && abs > abs2) {
                            this.mIsBeingDragged = true;
                            requestParentDisallowInterceptTouchEvent(true);
                            float f = this.mInitialMotionX;
                            this.mLastMotionX = x2 - f > 0.0f ? f + ((float) this.mTouchSlop) : f - ((float) this.mTouchSlop);
                            this.mLastMotionY = y2;
                            setScrollState(1);
                            setScrollingCacheEnabled(true);
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                }
                if (this.mIsBeingDragged) {
                    z = false | performDrag(motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)));
                }
            } else if (action != 3) {
                if (action == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    this.mLastMotionX = motionEvent.getX(actionIndex);
                    this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                } else if (action == 6) {
                    onSecondaryPointerUp(motionEvent);
                    this.mLastMotionX = motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId));
                }
            } else if (this.mIsBeingDragged) {
                scrollToItem(this.mCurItem, true, 0, false);
                z = resetTouch();
            }
        } else if (this.mIsBeingDragged) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
            int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
            this.mPopulatePending = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            ItemInfo infoForCurrentScrollPosition = infoForCurrentScrollPosition();
            float f2 = (float) clientWidth;
            float f3 = ((float) this.mPageMargin) / f2;
            int i = infoForCurrentScrollPosition.position;
            float f4 = ((((float) scrollX) / f2) - infoForCurrentScrollPosition.offset) / (infoForCurrentScrollPosition.widthFactor + f3);
            if (Math.abs((int) (motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX)) <= this.mFlingDistance || Math.abs(xVelocity) <= this.mMinimumVelocity) {
                i += (int) (f4 + (i >= this.mCurItem ? 0.4f : 0.6f));
            } else if (xVelocity <= 0) {
                i++;
            }
            if (this.mItems.size() > 0) {
                ArrayList<ItemInfo> arrayList = this.mItems;
                i = Math.max(this.mItems.get(0).position, Math.min(i, arrayList.get(arrayList.size() - 1).position));
            }
            setCurrentItemInternal(i, true, true, xVelocity);
            z = resetTouch();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageLeft() {
        int i = this.mCurItem;
        if (i <= 0) {
            return false;
        }
        setCurrentItem(i - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageRight() {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null || this.mCurItem >= pagerAdapter.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.mCurItem + 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void populate() {
        populate(this.mCurItem);
    }

    public void removeOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.remove(onPageChangeListener);
        }
    }

    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        PagerAdapter pagerAdapter2 = this.mAdapter;
        if (pagerAdapter2 != null) {
            pagerAdapter2.setViewPagerObserver((DataSetObserver) null);
            this.mAdapter.startUpdate(this);
            for (int i = 0; i < this.mItems.size(); i++) {
                ItemInfo itemInfo = this.mItems.get(i);
                this.mAdapter.destroyItem(this, itemInfo.position, itemInfo.object);
            }
            this.mAdapter.finishUpdate(this);
            this.mItems.clear();
            int i2 = 0;
            while (i2 < getChildCount()) {
                if (!((LayoutParams) getChildAt(i2).getLayoutParams()).isDecor) {
                    removeViewAt(i2);
                    i2--;
                }
                i2++;
            }
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter3 = this.mAdapter;
        this.mAdapter = pagerAdapter;
        this.mExpectedAdapterCount = 0;
        if (this.mAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new PagerObserver();
            }
            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean z = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (!z) {
                populate();
            } else {
                requestLayout();
            }
        }
        List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
        if (list != null && !list.isEmpty()) {
            int size = this.mAdapterChangeListeners.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.mAdapterChangeListeners.get(i3).onAdapterChanged(this, pagerAdapter3, pagerAdapter);
            }
        }
    }

    public void setCurrentItem(int i) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i, !this.mFirstLayout, false);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i, boolean z, boolean z2) {
        setCurrentItemInternal(i, z, z2, 0);
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to " + 1);
            i = 1;
        }
        if (i != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i;
            populate();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    /* access modifiers changed from: package-private */
    public void setScrollState(int i) {
        if (this.mScrollState != i) {
            this.mScrollState = i;
            OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrollStateChanged(i);
            }
            List<OnPageChangeListener> list = this.mOnPageChangeListeners;
            if (list != null) {
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListeners.get(i2);
                    if (onPageChangeListener2 != null) {
                        onPageChangeListener2.onPageScrollStateChanged(i);
                    }
                }
            }
            OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
            if (onPageChangeListener3 != null) {
                onPageChangeListener3.onPageScrollStateChanged(i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void smoothScrollTo(int i, int i2, int i3) {
        int i4;
        int i5;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            i4 = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            i4 = getScrollX();
        }
        int i6 = i4;
        int scrollY = getScrollY();
        int i7 = i - i6;
        int i8 = i2 - scrollY;
        if (i7 == 0 && i8 == 0) {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i9 = clientWidth / 2;
        float f = (float) clientWidth;
        float f2 = (float) i9;
        float distanceInfluenceForSnapDuration = (distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(i7)) * 1.0f) / f)) * f2) + f2;
        int abs = Math.abs(i3);
        if (abs > 0) {
            i5 = Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) abs)) * 1000.0f) * 4;
        } else {
            this.mAdapter.getPageWidth(this.mCurItem);
            i5 = (int) (((((float) Math.abs(i7)) / ((f * 1.0f) + ((float) this.mPageMargin))) + 1.0f) * 100.0f);
        }
        int min = Math.min(i5, 600);
        this.mIsScrollStarted = false;
        this.mScroller.startScroll(i6, scrollY, i7, i8, min);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mMarginDrawable;
    }

    /* renamed from: android.support.v4.view.ViewPager$LayoutParams */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor = 0.0f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.LAYOUT_ATTRS);
            this.gravity = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005b, code lost:
        if (r5 == r6) goto L_0x0062;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populate(int r15) {
        /*
            r14 = this;
            int r0 = r14.mCurItem
            if (r0 == r15) goto L_0x000b
            android.support.v4.view.ViewPager$ItemInfo r0 = r14.infoForPosition(r0)
            r14.mCurItem = r15
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            android.support.v4.view.PagerAdapter r15 = r14.mAdapter
            if (r15 != 0) goto L_0x0014
            r14.sortChildDrawingOrder()
            return
        L_0x0014:
            boolean r15 = r14.mPopulatePending
            if (r15 == 0) goto L_0x001c
            r14.sortChildDrawingOrder()
            return
        L_0x001c:
            android.os.IBinder r15 = r14.getWindowToken()
            if (r15 != 0) goto L_0x0023
            return
        L_0x0023:
            android.support.v4.view.PagerAdapter r15 = r14.mAdapter
            r15.startUpdate(r14)
            int r15 = r14.mOffscreenPageLimit
            int r1 = r14.mCurItem
            int r1 = r1 - r15
            r2 = 0
            int r1 = java.lang.Math.max(r2, r1)
            android.support.v4.view.PagerAdapter r3 = r14.mAdapter
            int r3 = r3.getCount()
            int r4 = r3 + -1
            int r5 = r14.mCurItem
            int r5 = r5 + r15
            int r15 = java.lang.Math.min(r4, r5)
            int r4 = r14.mExpectedAdapterCount
            if (r3 != r4) goto L_0x033e
        L_0x0045:
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r4 = r14.mItems
            int r4 = r4.size()
            if (r2 >= r4) goto L_0x0061
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r4 = r14.mItems
            java.lang.Object r4 = r4.get(r2)
            android.support.v4.view.ViewPager$ItemInfo r4 = (android.support.p000v4.view.ViewPager.ItemInfo) r4
            int r5 = r4.position
            int r6 = r14.mCurItem
            if (r5 < r6) goto L_0x005e
            if (r5 != r6) goto L_0x0061
            goto L_0x0062
        L_0x005e:
            int r2 = r2 + 1
            goto L_0x0045
        L_0x0061:
            r4 = 0
        L_0x0062:
            if (r4 != 0) goto L_0x006c
            if (r3 <= 0) goto L_0x006c
            int r4 = r14.mCurItem
            android.support.v4.view.ViewPager$ItemInfo r4 = r14.addNewItem(r4, r2)
        L_0x006c:
            r5 = 0
            if (r4 == 0) goto L_0x02cb
            int r6 = r2 + -1
            if (r6 < 0) goto L_0x007c
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            java.lang.Object r7 = r7.get(r6)
            android.support.v4.view.ViewPager$ItemInfo r7 = (android.support.p000v4.view.ViewPager.ItemInfo) r7
            goto L_0x007d
        L_0x007c:
            r7 = 0
        L_0x007d:
            int r8 = r14.getClientWidth()
            r9 = 1073741824(0x40000000, float:2.0)
            if (r8 > 0) goto L_0x0087
            r10 = r5
            goto L_0x0093
        L_0x0087:
            float r10 = r4.widthFactor
            float r10 = r9 - r10
            int r11 = r14.getPaddingLeft()
            float r11 = (float) r11
            float r12 = (float) r8
            float r11 = r11 / r12
            float r10 = r10 + r11
        L_0x0093:
            int r11 = r14.mCurItem
            int r11 = r11 + -1
            r12 = r6
            r6 = r2
            r2 = r5
        L_0x009a:
            if (r11 < 0) goto L_0x00f8
            int r13 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r13 < 0) goto L_0x00c8
            if (r11 >= r1) goto L_0x00c8
            if (r7 != 0) goto L_0x00a5
            goto L_0x00f8
        L_0x00a5:
            int r13 = r7.position
            if (r11 != r13) goto L_0x00f5
            boolean r13 = r7.scrolling
            if (r13 != 0) goto L_0x00f5
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r13 = r14.mItems
            r13.remove(r12)
            android.support.v4.view.PagerAdapter r13 = r14.mAdapter
            java.lang.Object r7 = r7.object
            r13.destroyItem(r14, r11, r7)
            int r12 = r12 + -1
            int r6 = r6 + -1
            if (r12 < 0) goto L_0x00f4
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            java.lang.Object r7 = r7.get(r12)
            android.support.v4.view.ViewPager$ItemInfo r7 = (android.support.p000v4.view.ViewPager.ItemInfo) r7
            goto L_0x00f5
        L_0x00c8:
            if (r7 == 0) goto L_0x00de
            int r13 = r7.position
            if (r11 != r13) goto L_0x00de
            float r7 = r7.widthFactor
            float r2 = r2 + r7
            int r12 = r12 + -1
            if (r12 < 0) goto L_0x00f4
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            java.lang.Object r7 = r7.get(r12)
            android.support.v4.view.ViewPager$ItemInfo r7 = (android.support.p000v4.view.ViewPager.ItemInfo) r7
            goto L_0x00f5
        L_0x00de:
            int r7 = r12 + 1
            android.support.v4.view.ViewPager$ItemInfo r7 = r14.addNewItem(r11, r7)
            float r7 = r7.widthFactor
            float r2 = r2 + r7
            int r6 = r6 + 1
            if (r12 < 0) goto L_0x00f4
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            java.lang.Object r7 = r7.get(r12)
            android.support.v4.view.ViewPager$ItemInfo r7 = (android.support.p000v4.view.ViewPager.ItemInfo) r7
            goto L_0x00f5
        L_0x00f4:
            r7 = 0
        L_0x00f5:
            int r11 = r11 + -1
            goto L_0x009a
        L_0x00f8:
            float r1 = r4.widthFactor
            int r2 = r6 + 1
            int r7 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r7 >= 0) goto L_0x018e
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            int r7 = r7.size()
            if (r2 >= r7) goto L_0x0111
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            java.lang.Object r7 = r7.get(r2)
            android.support.v4.view.ViewPager$ItemInfo r7 = (android.support.p000v4.view.ViewPager.ItemInfo) r7
            goto L_0x0112
        L_0x0111:
            r7 = 0
        L_0x0112:
            if (r8 > 0) goto L_0x0116
            r8 = r5
            goto L_0x011f
        L_0x0116:
            int r10 = r14.getPaddingRight()
            float r10 = (float) r10
            float r8 = (float) r8
            float r10 = r10 / r8
            float r8 = r10 + r9
        L_0x011f:
            int r9 = r14.mCurItem
            int r9 = r9 + 1
            r10 = r2
        L_0x0124:
            if (r9 >= r3) goto L_0x018e
            int r11 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r11 < 0) goto L_0x0154
            if (r9 <= r15) goto L_0x0154
            if (r7 != 0) goto L_0x012f
            goto L_0x018e
        L_0x012f:
            int r11 = r7.position
            if (r9 != r11) goto L_0x018b
            boolean r11 = r7.scrolling
            if (r11 != 0) goto L_0x018b
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r11 = r14.mItems
            r11.remove(r10)
            android.support.v4.view.PagerAdapter r11 = r14.mAdapter
            java.lang.Object r7 = r7.object
            r11.destroyItem(r14, r9, r7)
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x018a
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            java.lang.Object r7 = r7.get(r10)
            android.support.v4.view.ViewPager$ItemInfo r7 = (android.support.p000v4.view.ViewPager.ItemInfo) r7
            goto L_0x018b
        L_0x0154:
            if (r7 == 0) goto L_0x0170
            int r11 = r7.position
            if (r9 != r11) goto L_0x0170
            float r7 = r7.widthFactor
            float r1 = r1 + r7
            int r10 = r10 + 1
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x018a
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            java.lang.Object r7 = r7.get(r10)
            android.support.v4.view.ViewPager$ItemInfo r7 = (android.support.p000v4.view.ViewPager.ItemInfo) r7
            goto L_0x018b
        L_0x0170:
            android.support.v4.view.ViewPager$ItemInfo r7 = r14.addNewItem(r9, r10)
            int r10 = r10 + 1
            float r7 = r7.widthFactor
            float r1 = r1 + r7
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x018a
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r7 = r14.mItems
            java.lang.Object r7 = r7.get(r10)
            android.support.v4.view.ViewPager$ItemInfo r7 = (android.support.p000v4.view.ViewPager.ItemInfo) r7
            goto L_0x018b
        L_0x018a:
            r7 = 0
        L_0x018b:
            int r9 = r9 + 1
            goto L_0x0124
        L_0x018e:
            android.support.v4.view.PagerAdapter r15 = r14.mAdapter
            int r15 = r15.getCount()
            int r1 = r14.getClientWidth()
            if (r1 <= 0) goto L_0x01a1
            int r3 = r14.mPageMargin
            float r3 = (float) r3
            float r1 = (float) r1
            float r1 = r3 / r1
            goto L_0x01a2
        L_0x01a1:
            r1 = r5
        L_0x01a2:
            r3 = 1065353216(0x3f800000, float:1.0)
            if (r0 == 0) goto L_0x023b
            int r7 = r0.position
            int r8 = r4.position
            if (r7 >= r8) goto L_0x01f8
            float r8 = r0.offset
            float r0 = r0.widthFactor
            float r8 = r8 + r0
            float r8 = r8 + r1
            r0 = 0
        L_0x01b3:
            int r7 = r7 + 1
            int r9 = r4.position
            if (r7 > r9) goto L_0x023b
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r9 = r14.mItems
            int r9 = r9.size()
            if (r0 >= r9) goto L_0x023b
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r9 = r14.mItems
            java.lang.Object r9 = r9.get(r0)
            android.support.v4.view.ViewPager$ItemInfo r9 = (android.support.p000v4.view.ViewPager.ItemInfo) r9
        L_0x01c9:
            int r10 = r9.position
            if (r7 <= r10) goto L_0x01e2
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r10 = r14.mItems
            int r10 = r10.size()
            int r10 = r10 + -1
            if (r0 >= r10) goto L_0x01e2
            int r0 = r0 + 1
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r9 = r14.mItems
            java.lang.Object r9 = r9.get(r0)
            android.support.v4.view.ViewPager$ItemInfo r9 = (android.support.p000v4.view.ViewPager.ItemInfo) r9
            goto L_0x01c9
        L_0x01e2:
            int r10 = r9.position
            if (r7 >= r10) goto L_0x01f1
            android.support.v4.view.PagerAdapter r10 = r14.mAdapter
            r10.getPageWidth(r7)
            float r10 = r3 + r1
            float r8 = r8 + r10
            int r7 = r7 + 1
            goto L_0x01e2
        L_0x01f1:
            r9.offset = r8
            float r9 = r9.widthFactor
            float r9 = r9 + r1
            float r8 = r8 + r9
            goto L_0x01b3
        L_0x01f8:
            if (r7 <= r8) goto L_0x023b
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r8 = r14.mItems
            int r8 = r8.size()
            int r8 = r8 + -1
            float r0 = r0.offset
        L_0x0204:
            int r7 = r7 + -1
            int r9 = r4.position
            if (r7 < r9) goto L_0x023b
            if (r8 < 0) goto L_0x023b
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r9 = r14.mItems
            java.lang.Object r9 = r9.get(r8)
            android.support.v4.view.ViewPager$ItemInfo r9 = (android.support.p000v4.view.ViewPager.ItemInfo) r9
        L_0x0214:
            int r10 = r9.position
            if (r7 >= r10) goto L_0x0225
            if (r8 <= 0) goto L_0x0225
            int r8 = r8 + -1
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r9 = r14.mItems
            java.lang.Object r9 = r9.get(r8)
            android.support.v4.view.ViewPager$ItemInfo r9 = (android.support.p000v4.view.ViewPager.ItemInfo) r9
            goto L_0x0214
        L_0x0225:
            int r10 = r9.position
            if (r7 <= r10) goto L_0x0234
            android.support.v4.view.PagerAdapter r10 = r14.mAdapter
            r10.getPageWidth(r7)
            float r10 = r3 + r1
            float r0 = r0 - r10
            int r7 = r7 + -1
            goto L_0x0225
        L_0x0234:
            float r10 = r9.widthFactor
            float r10 = r10 + r1
            float r0 = r0 - r10
            r9.offset = r0
            goto L_0x0204
        L_0x023b:
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r0 = r14.mItems
            int r0 = r0.size()
            float r7 = r4.offset
            int r8 = r4.position
            int r9 = r8 + -1
            if (r8 != 0) goto L_0x024b
            r8 = r7
            goto L_0x024e
        L_0x024b:
            r8 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
        L_0x024e:
            r14.mFirstOffset = r8
            int r8 = r4.position
            int r15 = r15 + -1
            if (r8 != r15) goto L_0x025d
            float r8 = r4.offset
            float r10 = r4.widthFactor
            float r8 = r8 + r10
            float r8 = r8 - r3
            goto L_0x0260
        L_0x025d:
            r8 = 2139095039(0x7f7fffff, float:3.4028235E38)
        L_0x0260:
            r14.mLastOffset = r8
            int r6 = r6 + -1
        L_0x0264:
            if (r6 < 0) goto L_0x028d
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r8 = r14.mItems
            java.lang.Object r8 = r8.get(r6)
            android.support.v4.view.ViewPager$ItemInfo r8 = (android.support.p000v4.view.ViewPager.ItemInfo) r8
        L_0x026e:
            int r10 = r8.position
            if (r9 <= r10) goto L_0x027e
            android.support.v4.view.PagerAdapter r10 = r14.mAdapter
            int r11 = r9 + -1
            r10.getPageWidth(r9)
            float r9 = r3 + r1
            float r7 = r7 - r9
            r9 = r11
            goto L_0x026e
        L_0x027e:
            float r11 = r8.widthFactor
            float r11 = r11 + r1
            float r7 = r7 - r11
            r8.offset = r7
            if (r10 != 0) goto L_0x0288
            r14.mFirstOffset = r7
        L_0x0288:
            int r6 = r6 + -1
            int r9 = r9 + -1
            goto L_0x0264
        L_0x028d:
            float r6 = r4.offset
            float r7 = r4.widthFactor
            float r6 = r6 + r7
            float r6 = r6 + r1
            int r7 = r4.position
        L_0x0295:
            int r7 = r7 + 1
            if (r2 >= r0) goto L_0x02c2
            java.util.ArrayList<android.support.v4.view.ViewPager$ItemInfo> r8 = r14.mItems
            java.lang.Object r8 = r8.get(r2)
            android.support.v4.view.ViewPager$ItemInfo r8 = (android.support.p000v4.view.ViewPager.ItemInfo) r8
        L_0x02a1:
            int r9 = r8.position
            if (r7 >= r9) goto L_0x02b1
            android.support.v4.view.PagerAdapter r9 = r14.mAdapter
            int r10 = r7 + 1
            r9.getPageWidth(r7)
            float r7 = r3 + r1
            float r6 = r6 + r7
            r7 = r10
            goto L_0x02a1
        L_0x02b1:
            if (r9 != r15) goto L_0x02b9
            float r9 = r8.widthFactor
            float r9 = r9 + r6
            float r9 = r9 - r3
            r14.mLastOffset = r9
        L_0x02b9:
            r8.offset = r6
            float r8 = r8.widthFactor
            float r8 = r8 + r1
            float r6 = r6 + r8
            int r2 = r2 + 1
            goto L_0x0295
        L_0x02c2:
            android.support.v4.view.PagerAdapter r15 = r14.mAdapter
            int r0 = r14.mCurItem
            java.lang.Object r1 = r4.object
            r15.setPrimaryItem(r14, r0, r1)
        L_0x02cb:
            android.support.v4.view.PagerAdapter r15 = r14.mAdapter
            r15.finishUpdate(r14)
            int r15 = r14.getChildCount()
            r0 = 0
        L_0x02d5:
            if (r0 >= r15) goto L_0x02fe
            android.view.View r1 = r14.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r2 = r1.getLayoutParams()
            android.support.v4.view.ViewPager$LayoutParams r2 = (android.support.p000v4.view.ViewPager.LayoutParams) r2
            r2.childIndex = r0
            boolean r3 = r2.isDecor
            if (r3 != 0) goto L_0x02fb
            float r3 = r2.widthFactor
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x02fb
            android.support.v4.view.ViewPager$ItemInfo r1 = r14.infoForChild(r1)
            if (r1 == 0) goto L_0x02fb
            float r3 = r1.widthFactor
            r2.widthFactor = r3
            int r1 = r1.position
            r2.position = r1
        L_0x02fb:
            int r0 = r0 + 1
            goto L_0x02d5
        L_0x02fe:
            r14.sortChildDrawingOrder()
            boolean r15 = r14.hasFocus()
            if (r15 == 0) goto L_0x033d
            android.view.View r15 = r14.findFocus()
            if (r15 == 0) goto L_0x0312
            android.support.v4.view.ViewPager$ItemInfo r15 = r14.infoForAnyChild(r15)
            goto L_0x0313
        L_0x0312:
            r15 = 0
        L_0x0313:
            if (r15 == 0) goto L_0x031b
            int r15 = r15.position
            int r0 = r14.mCurItem
            if (r15 == r0) goto L_0x033d
        L_0x031b:
            r15 = 0
        L_0x031c:
            int r0 = r14.getChildCount()
            if (r15 >= r0) goto L_0x033d
            android.view.View r0 = r14.getChildAt(r15)
            android.support.v4.view.ViewPager$ItemInfo r1 = r14.infoForChild(r0)
            if (r1 == 0) goto L_0x033a
            int r1 = r1.position
            int r2 = r14.mCurItem
            if (r1 != r2) goto L_0x033a
            r1 = 2
            boolean r0 = r0.requestFocus(r1)
            if (r0 == 0) goto L_0x033a
            goto L_0x033d
        L_0x033a:
            int r15 = r15 + 1
            goto L_0x031c
        L_0x033d:
            return
        L_0x033e:
            android.content.res.Resources r15 = r14.getResources()     // Catch:{ NotFoundException -> 0x034b }
            int r0 = r14.getId()     // Catch:{ NotFoundException -> 0x034b }
            java.lang.String r15 = r15.getResourceName(r0)     // Catch:{ NotFoundException -> 0x034b }
            goto L_0x0353
        L_0x034b:
            int r15 = r14.getId()
            java.lang.String r15 = java.lang.Integer.toHexString(r15)
        L_0x0353:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: "
            java.lang.StringBuilder r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r1)
            int r2 = r14.mExpectedAdapterCount
            r1.append(r2)
            java.lang.String r2 = ", found: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = " Pager id: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r15 = " Pager class: "
            r1.append(r15)
            java.lang.Class r15 = r14.getClass()
            r1.append(r15)
            java.lang.String r15 = " Problematic adapter: "
            r1.append(r15)
            android.support.v4.view.PagerAdapter r14 = r14.mAdapter
            java.lang.Class r14 = r14.getClass()
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            r0.<init>(r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.ViewPager.populate(int):void");
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i, boolean z, boolean z2, int i2) {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null || pagerAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.mCurItem != i || this.mItems.size() == 0) {
            boolean z3 = true;
            if (i < 0) {
                i = 0;
            } else if (i >= this.mAdapter.getCount()) {
                i = this.mAdapter.getCount() - 1;
            }
            int i3 = this.mOffscreenPageLimit;
            int i4 = this.mCurItem;
            if (i > i4 + i3 || i < i4 - i3) {
                for (int i5 = 0; i5 < this.mItems.size(); i5++) {
                    this.mItems.get(i5).scrolling = true;
                }
            }
            if (this.mCurItem == i) {
                z3 = false;
            }
            if (this.mFirstLayout) {
                this.mCurItem = i;
                if (z3) {
                    dispatchOnPageSelected(i);
                }
                requestLayout();
                return;
            }
            populate(i);
            scrollToItem(i, z, i2, z3);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    public void setCurrentItem(int i, boolean z) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i, z, false);
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViewPager();
    }
}
