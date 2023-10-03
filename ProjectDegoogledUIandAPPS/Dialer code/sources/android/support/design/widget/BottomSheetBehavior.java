package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.R$dimen;
import android.support.design.R$styleable;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    int activePointerId;
    private BottomSheetCallback callback;
    int collapsedOffset;
    private final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback() {
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return view.getLeft();
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            int access$100 = BottomSheetBehavior.this.getExpandedOffset();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return R$dimen.clamp(i, access$100, bottomSheetBehavior.hideable ? bottomSheetBehavior.parentHeight : bottomSheetBehavior.collapsedOffset);
        }

        public int getViewVerticalDragRange(View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            if (bottomSheetBehavior.hideable) {
                return bottomSheetBehavior.parentHeight;
            }
            return bottomSheetBehavior.collapsedOffset;
        }

        public void onViewDragStateChanged(int i) {
            if (i == 1) {
                BottomSheetBehavior.this.setStateInternal(1);
            }
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.dispatchOnSlide(i2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:44:0x00d4  */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x00e5  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onViewReleased(android.view.View r8, float r9, float r10) {
            /*
                r7 = this;
                r0 = 0
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                r2 = 0
                r3 = 4
                r4 = 6
                r5 = 3
                if (r1 >= 0) goto L_0x0028
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                boolean r9 = r9.fitToContents
                if (r9 == 0) goto L_0x0018
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                int r9 = r9.fitToContentsOffset
            L_0x0015:
                r3 = r5
                goto L_0x00c6
            L_0x0018:
                int r9 = r8.getTop()
                android.support.design.widget.BottomSheetBehavior r10 = android.support.design.widget.BottomSheetBehavior.this
                int r10 = r10.halfExpandedOffset
                if (r9 <= r10) goto L_0x0026
                r9 = r10
            L_0x0023:
                r3 = r4
                goto L_0x00c6
            L_0x0026:
                r9 = r2
                goto L_0x0015
            L_0x0028:
                android.support.design.widget.BottomSheetBehavior r1 = android.support.design.widget.BottomSheetBehavior.this
                boolean r6 = r1.hideable
                if (r6 == 0) goto L_0x0051
                boolean r1 = r1.shouldHide(r8, r10)
                if (r1 == 0) goto L_0x0051
                int r1 = r8.getTop()
                android.support.design.widget.BottomSheetBehavior r6 = android.support.design.widget.BottomSheetBehavior.this
                int r6 = r6.collapsedOffset
                if (r1 > r6) goto L_0x004a
                float r1 = java.lang.Math.abs(r9)
                float r6 = java.lang.Math.abs(r10)
                int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
                if (r1 >= 0) goto L_0x0051
            L_0x004a:
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                int r9 = r9.parentHeight
                r3 = 5
                goto L_0x00c6
            L_0x0051:
                int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r0 == 0) goto L_0x0067
                float r9 = java.lang.Math.abs(r9)
                float r10 = java.lang.Math.abs(r10)
                int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
                if (r9 <= 0) goto L_0x0062
                goto L_0x0067
            L_0x0062:
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                int r9 = r9.collapsedOffset
                goto L_0x00c6
            L_0x0067:
                int r9 = r8.getTop()
                android.support.design.widget.BottomSheetBehavior r10 = android.support.design.widget.BottomSheetBehavior.this
                boolean r10 = r10.fitToContents
                if (r10 == 0) goto L_0x0093
                android.support.design.widget.BottomSheetBehavior r10 = android.support.design.widget.BottomSheetBehavior.this
                int r10 = r10.fitToContentsOffset
                int r10 = r9 - r10
                int r10 = java.lang.Math.abs(r10)
                android.support.design.widget.BottomSheetBehavior r0 = android.support.design.widget.BottomSheetBehavior.this
                int r0 = r0.collapsedOffset
                int r9 = r9 - r0
                int r9 = java.lang.Math.abs(r9)
                if (r10 >= r9) goto L_0x008d
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                int r2 = r9.fitToContentsOffset
                goto L_0x0026
            L_0x008d:
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                int r2 = r9.collapsedOffset
            L_0x0091:
                r9 = r2
                goto L_0x00c6
            L_0x0093:
                android.support.design.widget.BottomSheetBehavior r10 = android.support.design.widget.BottomSheetBehavior.this
                int r0 = r10.halfExpandedOffset
                if (r9 >= r0) goto L_0x00a9
                int r10 = r10.collapsedOffset
                int r10 = r9 - r10
                int r10 = java.lang.Math.abs(r10)
                if (r9 >= r10) goto L_0x00a4
                goto L_0x0026
            L_0x00a4:
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                int r2 = r9.halfExpandedOffset
                goto L_0x00be
            L_0x00a9:
                int r10 = r9 - r0
                int r10 = java.lang.Math.abs(r10)
                android.support.design.widget.BottomSheetBehavior r0 = android.support.design.widget.BottomSheetBehavior.this
                int r0 = r0.collapsedOffset
                int r9 = r9 - r0
                int r9 = java.lang.Math.abs(r9)
                if (r10 >= r9) goto L_0x00c1
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                int r2 = r9.halfExpandedOffset
            L_0x00be:
                r9 = r2
                goto L_0x0023
            L_0x00c1:
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                int r2 = r9.collapsedOffset
                goto L_0x0091
            L_0x00c6:
                android.support.design.widget.BottomSheetBehavior r10 = android.support.design.widget.BottomSheetBehavior.this
                android.support.v4.widget.ViewDragHelper r10 = r10.viewDragHelper
                int r0 = r8.getLeft()
                boolean r9 = r10.settleCapturedViewAt(r0, r9)
                if (r9 == 0) goto L_0x00e5
                android.support.design.widget.BottomSheetBehavior r9 = android.support.design.widget.BottomSheetBehavior.this
                r10 = 2
                r9.setStateInternal(r10)
                android.support.design.widget.BottomSheetBehavior$SettleRunnable r9 = new android.support.design.widget.BottomSheetBehavior$SettleRunnable
                android.support.design.widget.BottomSheetBehavior r7 = android.support.design.widget.BottomSheetBehavior.this
                r9.<init>(r8, r3)
                android.support.p000v4.view.ViewCompat.postOnAnimation(r8, r9)
                goto L_0x00ea
            L_0x00e5:
                android.support.design.widget.BottomSheetBehavior r7 = android.support.design.widget.BottomSheetBehavior.this
                r7.setStateInternal(r3)
            L_0x00ea:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetBehavior.C00362.onViewReleased(android.view.View, float, float):void");
        }

        public boolean tryCaptureView(View view, int i) {
            View view2;
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i2 = bottomSheetBehavior.state;
            if (i2 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                return false;
            }
            if (i2 == 3 && bottomSheetBehavior.activePointerId == i && (view2 = (View) bottomSheetBehavior.nestedScrollingChildRef.get()) != null && view2.canScrollVertically(-1)) {
                return false;
            }
            WeakReference<V> weakReference = BottomSheetBehavior.this.viewRef;
            if (weakReference == null || weakReference.get() != view) {
                return false;
            }
            return true;
        }
    };
    /* access modifiers changed from: private */
    public boolean fitToContents = true;
    int fitToContentsOffset;
    int halfExpandedOffset;
    boolean hideable;
    private boolean ignoreEvents;
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private int lastNestedScrollDy;
    private int lastPeekHeight;
    private float maximumVelocity;
    private boolean nestedScrolled;
    WeakReference<View> nestedScrollingChildRef;
    int parentHeight;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightMin;
    private boolean skipCollapsed;
    int state = 4;
    boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    ViewDragHelper viewDragHelper;
    WeakReference<V> viewRef;

    public static abstract class BottomSheetCallback {
        public abstract void onSlide(View view, float f);

        public abstract void onStateChanged(View view, int i);
    }

    private class SettleRunnable implements Runnable {
        private final int targetState;
        private final View view;

        SettleRunnable(View view2, int i) {
            this.view = view2;
            this.targetState = i;
        }

        public void run() {
            ViewDragHelper viewDragHelper = BottomSheetBehavior.this.viewDragHelper;
            if (viewDragHelper == null || !viewDragHelper.continueSettling(true)) {
                BottomSheetBehavior.this.setStateInternal(this.targetState);
            } else {
                ViewCompat.postOnAnimation(this.view, this);
            }
        }
    }

    public BottomSheetBehavior() {
    }

    private void calculateCollapsedOffset() {
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - this.lastPeekHeight, this.fitToContentsOffset);
        } else {
            this.collapsedOffset = this.parentHeight - this.lastPeekHeight;
        }
    }

    public static <V extends View> BottomSheetBehavior<V> from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).mBehavior;
            if (behavior instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    /* access modifiers changed from: private */
    public int getExpandedOffset() {
        if (this.fitToContents) {
            return this.fitToContentsOffset;
        }
        return 0;
    }

    private void updateImportantForAccessibility(boolean z) {
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            ViewParent parent = ((View) weakReference.get()).getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                int i = Build.VERSION.SDK_INT;
                if (z) {
                    if (this.importantForAccessibilityMap == null) {
                        this.importantForAccessibilityMap = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = coordinatorLayout.getChildAt(i2);
                    if (childAt != this.viewRef.get()) {
                        if (!z) {
                            Map<View, Integer> map = this.importantForAccessibilityMap;
                            if (map != null && map.containsKey(childAt)) {
                                ViewCompat.setImportantForAccessibility(childAt, this.importantForAccessibilityMap.get(childAt).intValue());
                            }
                        } else {
                            int i3 = Build.VERSION.SDK_INT;
                            this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                            ViewCompat.setImportantForAccessibility(childAt, 4);
                        }
                    }
                }
                if (!z) {
                    this.importantForAccessibilityMap = null;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnSlide(int i) {
        BottomSheetCallback bottomSheetCallback;
        View view = (View) this.viewRef.get();
        if (view != null && (bottomSheetCallback = this.callback) != null) {
            int i2 = this.collapsedOffset;
            if (i > i2) {
                bottomSheetCallback.onSlide(view, ((float) (i2 - i)) / ((float) (this.parentHeight - i2)));
            } else {
                bottomSheetCallback.onSlide(view, ((float) (i2 - i)) / ((float) (i2 - getExpandedOffset())));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
            if (findScrollingChild != null) {
                return findScrollingChild;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    public final int getState() {
        return this.state;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.support.design.widget.CoordinatorLayout r9, V r10, android.view.MotionEvent r11) {
        /*
            r8 = this;
            boolean r0 = r10.isShown()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x000b
            r8.ignoreEvents = r2
            return r1
        L_0x000b:
            int r0 = r11.getActionMasked()
            r3 = 0
            r4 = -1
            if (r0 != 0) goto L_0x001e
            r8.activePointerId = r4
            android.view.VelocityTracker r5 = r8.velocityTracker
            if (r5 == 0) goto L_0x001e
            r5.recycle()
            r8.velocityTracker = r3
        L_0x001e:
            android.view.VelocityTracker r5 = r8.velocityTracker
            if (r5 != 0) goto L_0x0028
            android.view.VelocityTracker r5 = android.view.VelocityTracker.obtain()
            r8.velocityTracker = r5
        L_0x0028:
            android.view.VelocityTracker r5 = r8.velocityTracker
            r5.addMovement(r11)
            if (r0 == 0) goto L_0x0040
            if (r0 == r2) goto L_0x0035
            r10 = 3
            if (r0 == r10) goto L_0x0035
            goto L_0x007f
        L_0x0035:
            r8.touchingScrollingChild = r1
            r8.activePointerId = r4
            boolean r10 = r8.ignoreEvents
            if (r10 == 0) goto L_0x007f
            r8.ignoreEvents = r1
            return r1
        L_0x0040:
            float r5 = r11.getX()
            int r5 = (int) r5
            float r6 = r11.getY()
            int r6 = (int) r6
            r8.initialY = r6
            java.lang.ref.WeakReference<android.view.View> r6 = r8.nestedScrollingChildRef
            if (r6 == 0) goto L_0x0057
            java.lang.Object r6 = r6.get()
            android.view.View r6 = (android.view.View) r6
            goto L_0x0058
        L_0x0057:
            r6 = r3
        L_0x0058:
            if (r6 == 0) goto L_0x006e
            int r7 = r8.initialY
            boolean r6 = r9.isPointInChildBounds(r6, r5, r7)
            if (r6 == 0) goto L_0x006e
            int r6 = r11.getActionIndex()
            int r6 = r11.getPointerId(r6)
            r8.activePointerId = r6
            r8.touchingScrollingChild = r2
        L_0x006e:
            int r6 = r8.activePointerId
            if (r6 != r4) goto L_0x007c
            int r4 = r8.initialY
            boolean r10 = r9.isPointInChildBounds(r10, r5, r4)
            if (r10 != 0) goto L_0x007c
            r10 = r2
            goto L_0x007d
        L_0x007c:
            r10 = r1
        L_0x007d:
            r8.ignoreEvents = r10
        L_0x007f:
            boolean r10 = r8.ignoreEvents
            if (r10 != 0) goto L_0x008e
            android.support.v4.widget.ViewDragHelper r10 = r8.viewDragHelper
            if (r10 == 0) goto L_0x008e
            boolean r10 = r10.shouldInterceptTouchEvent(r11)
            if (r10 == 0) goto L_0x008e
            return r2
        L_0x008e:
            java.lang.ref.WeakReference<android.view.View> r10 = r8.nestedScrollingChildRef
            if (r10 == 0) goto L_0x0099
            java.lang.Object r10 = r10.get()
            r3 = r10
            android.view.View r3 = (android.view.View) r3
        L_0x0099:
            r10 = 2
            if (r0 != r10) goto L_0x00d2
            if (r3 == 0) goto L_0x00d2
            boolean r10 = r8.ignoreEvents
            if (r10 != 0) goto L_0x00d2
            int r10 = r8.state
            if (r10 == r2) goto L_0x00d2
            float r10 = r11.getX()
            int r10 = (int) r10
            float r0 = r11.getY()
            int r0 = (int) r0
            boolean r9 = r9.isPointInChildBounds(r3, r10, r0)
            if (r9 != 0) goto L_0x00d2
            android.support.v4.widget.ViewDragHelper r9 = r8.viewDragHelper
            if (r9 == 0) goto L_0x00d2
            int r9 = r8.initialY
            float r9 = (float) r9
            float r10 = r11.getY()
            float r9 = r9 - r10
            float r9 = java.lang.Math.abs(r9)
            android.support.v4.widget.ViewDragHelper r8 = r8.viewDragHelper
            int r8 = r8.getTouchSlop()
            float r8 = (float) r8
            int r8 = (r9 > r8 ? 1 : (r9 == r8 ? 0 : -1))
            if (r8 <= 0) goto L_0x00d2
            r1 = r2
        L_0x00d2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetBehavior.onInterceptTouchEvent(android.support.design.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i);
        this.parentHeight = coordinatorLayout.getHeight();
        if (this.peekHeightAuto) {
            if (this.peekHeightMin == 0) {
                this.peekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            }
            this.lastPeekHeight = Math.max(this.peekHeightMin, this.parentHeight - ((coordinatorLayout.getWidth() * 9) / 16));
        } else {
            this.lastPeekHeight = this.peekHeight;
        }
        this.fitToContentsOffset = Math.max(0, this.parentHeight - v.getHeight());
        this.halfExpandedOffset = this.parentHeight / 2;
        calculateCollapsedOffset();
        int i2 = this.state;
        if (i2 == 3) {
            int expandedOffset = getExpandedOffset();
            int i3 = Build.VERSION.SDK_INT;
            v.offsetTopAndBottom(expandedOffset);
        } else if (i2 == 6) {
            int i4 = this.halfExpandedOffset;
            int i5 = Build.VERSION.SDK_INT;
            v.offsetTopAndBottom(i4);
        } else if (!this.hideable || i2 != 5) {
            int i6 = this.state;
            if (i6 == 4) {
                int i7 = this.collapsedOffset;
                int i8 = Build.VERSION.SDK_INT;
                v.offsetTopAndBottom(i7);
            } else if (i6 == 1 || i6 == 2) {
                int top2 = top - v.getTop();
                int i9 = Build.VERSION.SDK_INT;
                v.offsetTopAndBottom(top2);
            }
        } else {
            int i10 = this.parentHeight;
            int i11 = Build.VERSION.SDK_INT;
            v.offsetTopAndBottom(i10);
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
        }
        this.viewRef = new WeakReference<>(v);
        this.nestedScrollingChildRef = new WeakReference<>(findScrollingChild(v));
        return true;
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        return view == this.nestedScrollingChildRef.get() && this.state != 3;
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        if (i3 != 1 && view == ((View) this.nestedScrollingChildRef.get())) {
            int top = v.getTop();
            int i4 = top - i2;
            if (i2 > 0) {
                if (i4 < getExpandedOffset()) {
                    iArr[1] = top - getExpandedOffset();
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    setStateInternal(3);
                } else {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(1);
                }
            } else if (i2 < 0 && !view.canScrollVertically(-1)) {
                int i5 = this.collapsedOffset;
                if (i4 <= i5 || this.hideable) {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(1);
                } else {
                    iArr[1] = top - i5;
                    ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                    setStateInternal(4);
                }
            }
            dispatchOnSlide(v.getTop());
            this.lastNestedScrollDy = i2;
            this.nestedScrolled = true;
        }
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        savedState.getSuperState();
        int i = savedState.state;
        if (i == 1 || i == 2) {
            this.state = 4;
        } else {
            this.state = i;
        }
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState((Parcelable) View.BaseSavedState.EMPTY_STATE, this.state);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        if ((i & 2) != 0) {
            return true;
        }
        return false;
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        int i2;
        int i3;
        float f;
        int i4 = 3;
        if (v.getTop() == getExpandedOffset()) {
            setStateInternal(3);
        } else if (view == this.nestedScrollingChildRef.get() && this.nestedScrolled) {
            if (this.lastNestedScrollDy > 0) {
                i2 = getExpandedOffset();
            } else {
                if (this.hideable) {
                    VelocityTracker velocityTracker2 = this.velocityTracker;
                    if (velocityTracker2 == null) {
                        f = 0.0f;
                    } else {
                        velocityTracker2.computeCurrentVelocity(1000, this.maximumVelocity);
                        f = this.velocityTracker.getYVelocity(this.activePointerId);
                    }
                    if (shouldHide(v, f)) {
                        i2 = this.parentHeight;
                        i4 = 5;
                    }
                }
                if (this.lastNestedScrollDy == 0) {
                    int top = v.getTop();
                    if (!this.fitToContents) {
                        int i5 = this.halfExpandedOffset;
                        if (top < i5) {
                            if (top < Math.abs(top - this.collapsedOffset)) {
                                i2 = 0;
                            } else {
                                i2 = this.halfExpandedOffset;
                            }
                        } else if (Math.abs(top - i5) < Math.abs(top - this.collapsedOffset)) {
                            i2 = this.halfExpandedOffset;
                        } else {
                            i3 = this.collapsedOffset;
                        }
                        i4 = 6;
                    } else if (Math.abs(top - this.fitToContentsOffset) < Math.abs(top - this.collapsedOffset)) {
                        i2 = this.fitToContentsOffset;
                    } else {
                        i3 = this.collapsedOffset;
                    }
                } else {
                    i3 = this.collapsedOffset;
                }
                i4 = 4;
            }
            if (this.viewDragHelper.smoothSlideViewTo(v, v.getLeft(), i2)) {
                setStateInternal(2);
                ViewCompat.postOnAnimation(v, new SettleRunnable(v, i4));
            } else {
                setStateInternal(i4);
            }
            this.nestedScrolled = false;
        }
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        ViewDragHelper viewDragHelper2 = this.viewDragHelper;
        if (viewDragHelper2 != null) {
            viewDragHelper2.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            this.activePointerId = -1;
            VelocityTracker velocityTracker2 = this.velocityTracker;
            if (velocityTracker2 != null) {
                velocityTracker2.recycle();
                this.velocityTracker = null;
            }
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 2 && !this.ignoreEvents && Math.abs(((float) this.initialY) - motionEvent.getY()) > ((float) this.viewDragHelper.getTouchSlop())) {
            this.viewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.callback = bottomSheetCallback;
    }

    public void setFitToContents(boolean z) {
        if (this.fitToContents != z) {
            this.fitToContents = z;
            if (this.viewRef != null) {
                calculateCollapsedOffset();
            }
            setStateInternal((!this.fitToContents || this.state != 6) ? this.state : 3);
        }
    }

    public void setHideable(boolean z) {
        this.hideable = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setPeekHeight(int r4) {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            r2 = -1
            if (r4 != r2) goto L_0x000c
            boolean r4 = r3.peekHeightAuto
            if (r4 != 0) goto L_0x0015
            r3.peekHeightAuto = r0
            goto L_0x0024
        L_0x000c:
            boolean r2 = r3.peekHeightAuto
            if (r2 != 0) goto L_0x0017
            int r2 = r3.peekHeight
            if (r2 == r4) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r0 = r1
            goto L_0x0024
        L_0x0017:
            r3.peekHeightAuto = r1
            int r1 = java.lang.Math.max(r1, r4)
            r3.peekHeight = r1
            int r1 = r3.parentHeight
            int r1 = r1 - r4
            r3.collapsedOffset = r1
        L_0x0024:
            if (r0 == 0) goto L_0x003a
            int r4 = r3.state
            r0 = 4
            if (r4 != r0) goto L_0x003a
            java.lang.ref.WeakReference<V> r3 = r3.viewRef
            if (r3 == 0) goto L_0x003a
            java.lang.Object r3 = r3.get()
            android.view.View r3 = (android.view.View) r3
            if (r3 == 0) goto L_0x003a
            r3.requestLayout()
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetBehavior.setPeekHeight(int):void");
    }

    public void setSkipCollapsed(boolean z) {
        this.skipCollapsed = z;
    }

    public final void setState(final int i) {
        if (i != this.state) {
            WeakReference<V> weakReference = this.viewRef;
            if (weakReference != null) {
                final View view = (View) weakReference.get();
                if (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(view)) {
                        startSettlingAnimation(view, i);
                    } else {
                        view.post(new Runnable() {
                            public void run() {
                                BottomSheetBehavior.this.startSettlingAnimation(view, i);
                            }
                        });
                    }
                }
            } else if (i == 4 || i == 3 || i == 6 || (this.hideable && i == 5)) {
                this.state = i;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setStateInternal(int i) {
        BottomSheetCallback bottomSheetCallback;
        if (this.state != i) {
            this.state = i;
            if (i == 6 || i == 3) {
                updateImportantForAccessibility(true);
            } else if (i == 5 || i == 4) {
                updateImportantForAccessibility(false);
            }
            View view = (View) this.viewRef.get();
            if (view != null && (bottomSheetCallback = this.callback) != null) {
                bottomSheetCallback.onStateChanged(view, i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldHide(View view, float f) {
        if (this.skipCollapsed) {
            return true;
        }
        if (view.getTop() < this.collapsedOffset) {
            return false;
        }
        if (Math.abs(((f * 0.1f) + ((float) view.getTop())) - ((float) this.collapsedOffset)) / ((float) this.peekHeight) > 0.5f) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void startSettlingAnimation(View view, int i) {
        int i2;
        int i3;
        if (i == 4) {
            i2 = this.collapsedOffset;
        } else if (i == 6) {
            int i4 = this.halfExpandedOffset;
            if (!this.fitToContents || i4 > (i3 = this.fitToContentsOffset)) {
                i2 = i4;
            } else {
                i = 3;
                i2 = i3;
            }
        } else if (i == 3) {
            i2 = getExpandedOffset();
        } else if (!this.hideable || i != 5) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("Illegal state argument: ", i));
        } else {
            i2 = this.parentHeight;
        }
        if (this.viewDragHelper.smoothSlideViewTo(view, view.getLeft(), i2)) {
            setStateInternal(2);
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
            return;
        }
        setStateInternal(i);
    }

    protected static class SavedState extends AbsSavedState {
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
        final int state;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.state = i;
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        TypedValue peekValue = obtainStyledAttributes.peekValue(2);
        if (peekValue == null || (i = peekValue.data) != -1) {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(2, -1));
        } else {
            setPeekHeight(i);
        }
        setHideable(obtainStyledAttributes.getBoolean(1, false));
        setFitToContents(obtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        setSkipCollapsed(obtainStyledAttributes.getBoolean(3, false));
        obtainStyledAttributes.recycle();
        this.maximumVelocity = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
