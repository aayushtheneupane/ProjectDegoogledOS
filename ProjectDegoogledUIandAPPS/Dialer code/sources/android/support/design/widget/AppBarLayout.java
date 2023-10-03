package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.R$dimen;
import android.support.design.R$styleable;
import android.support.design.animation.AnimationUtils;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.android.dialer.R;
import java.lang.ref.WeakReference;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class AppBarLayout extends LinearLayout {
    private int downPreScrollRange;
    private int downScrollRange;
    private boolean haveChildWithInterpolator;
    private WindowInsetsCompat lastInsets;
    private boolean liftOnScroll;
    private boolean liftable;
    private boolean liftableOverride;
    private boolean lifted;
    private List<BaseOnOffsetChangedListener> listeners;
    private int pendingAction;
    private int[] tmpStatesArray;
    private int totalScrollRange;

    protected static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        private WeakReference<View> lastNestedScrollingChildRef;
        private int lastStartedType;
        private ValueAnimator offsetAnimator;
        /* access modifiers changed from: private */
        public int offsetDelta;
        private int offsetToChildIndexOnLayout = -1;
        private boolean offsetToChildIndexOnLayoutIsMinHeight;
        private float offsetToChildIndexOnLayoutPerc;

        public BaseBehavior() {
        }

        private void animateOffsetTo(final CoordinatorLayout coordinatorLayout, final T t, int i, float f) {
            int i2;
            int abs = Math.abs(getTopBottomOffsetForScrollingSibling() - i);
            float abs2 = Math.abs(f);
            if (abs2 > 0.0f) {
                i2 = Math.round((((float) abs) / abs2) * 1000.0f) * 3;
            } else {
                i2 = (int) (((((float) abs) / ((float) t.getHeight())) + 1.0f) * 150.0f);
            }
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            if (topBottomOffsetForScrollingSibling == i) {
                ValueAnimator valueAnimator = this.offsetAnimator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.offsetAnimator.cancel();
                    return;
                }
                return;
            }
            ValueAnimator valueAnimator2 = this.offsetAnimator;
            if (valueAnimator2 == null) {
                this.offsetAnimator = new ValueAnimator();
                this.offsetAnimator.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        BaseBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, t, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
            } else {
                valueAnimator2.cancel();
            }
            this.offsetAnimator.setDuration((long) Math.min(i2, 600));
            this.offsetAnimator.setIntValues(new int[]{topBottomOffsetForScrollingSibling, i});
            this.offsetAnimator.start();
        }

        private static boolean checkFlag(int i, int i2) {
            return (i & i2) == i2;
        }

        private void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, T t) {
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            int childCount = t.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    i = -1;
                    break;
                }
                View childAt = t.getChildAt(i);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (checkFlag(layoutParams.scrollFlags, 32)) {
                    top -= layoutParams.topMargin;
                    bottom += layoutParams.bottomMargin;
                }
                int i2 = -topBottomOffsetForScrollingSibling;
                if (top <= i2 && bottom >= i2) {
                    break;
                }
                i++;
            }
            if (i >= 0) {
                View childAt2 = t.getChildAt(i);
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                int i3 = layoutParams2.scrollFlags;
                if ((i3 & 17) == 17) {
                    int i4 = -childAt2.getTop();
                    int i5 = -childAt2.getBottom();
                    if (i == t.getChildCount() - 1) {
                        i5 += t.getTopInset();
                    }
                    if (checkFlag(i3, 2)) {
                        i5 += ViewCompat.getMinimumHeight(childAt2);
                    } else if (checkFlag(i3, 5)) {
                        int minimumHeight = ViewCompat.getMinimumHeight(childAt2) + i5;
                        if (topBottomOffsetForScrollingSibling < minimumHeight) {
                            i4 = minimumHeight;
                        } else {
                            i5 = minimumHeight;
                        }
                    }
                    if (checkFlag(i3, 32)) {
                        i4 += layoutParams2.topMargin;
                        i5 -= layoutParams2.bottomMargin;
                    }
                    if (topBottomOffsetForScrollingSibling < (i5 + i4) / 2) {
                        i4 = i5;
                    }
                    animateOffsetTo(coordinatorLayout, t, R$dimen.clamp(i4, -t.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        private void stopNestedScrollIfNeeded(int i, T t, View view, int i2) {
            if (i2 == 1) {
                int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
                if ((i < 0 && topBottomOffsetForScrollingSibling == 0) || (i > 0 && topBottomOffsetForScrollingSibling == (-t.getDownNestedScrollRange()))) {
                    ViewCompat.stopNestedScroll(view, 1);
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:26:0x0063  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x008a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void updateAppBarLayoutDrawableState(android.support.design.widget.CoordinatorLayout r7, T r8, int r9, int r10, boolean r11) {
            /*
                r6 = this;
                int r6 = java.lang.Math.abs(r9)
                int r0 = r8.getChildCount()
                r1 = 0
                r2 = r1
            L_0x000a:
                r3 = 0
                if (r2 >= r0) goto L_0x0021
                android.view.View r4 = r8.getChildAt(r2)
                int r5 = r4.getTop()
                if (r6 < r5) goto L_0x001e
                int r5 = r4.getBottom()
                if (r6 > r5) goto L_0x001e
                goto L_0x0022
            L_0x001e:
                int r2 = r2 + 1
                goto L_0x000a
            L_0x0021:
                r4 = r3
            L_0x0022:
                if (r4 == 0) goto L_0x00bb
                android.view.ViewGroup$LayoutParams r6 = r4.getLayoutParams()
                android.support.design.widget.AppBarLayout$LayoutParams r6 = (android.support.design.widget.AppBarLayout.LayoutParams) r6
                int r6 = r6.scrollFlags
                r0 = r6 & 1
                r2 = 1
                if (r0 == 0) goto L_0x005c
                int r0 = android.support.p000v4.view.ViewCompat.getMinimumHeight(r4)
                if (r10 <= 0) goto L_0x004a
                r10 = r6 & 12
                if (r10 == 0) goto L_0x004a
                int r6 = -r9
                int r9 = r4.getBottom()
                int r9 = r9 - r0
                int r10 = r8.getTopInset()
                int r9 = r9 - r10
                if (r6 < r9) goto L_0x005c
            L_0x0048:
                r6 = r2
                goto L_0x005d
            L_0x004a:
                r6 = r6 & 2
                if (r6 == 0) goto L_0x005c
                int r6 = -r9
                int r9 = r4.getBottom()
                int r9 = r9 - r0
                int r10 = r8.getTopInset()
                int r9 = r9 - r10
                if (r6 < r9) goto L_0x005c
                goto L_0x0048
            L_0x005c:
                r6 = r1
            L_0x005d:
                boolean r9 = r8.isLiftOnScroll()
                if (r9 == 0) goto L_0x0082
                int r9 = r7.getChildCount()
                r10 = r1
            L_0x0068:
                if (r10 >= r9) goto L_0x0076
                android.view.View r0 = r7.getChildAt(r10)
                boolean r4 = r0 instanceof android.support.p000v4.view.NestedScrollingChild
                if (r4 == 0) goto L_0x0073
                goto L_0x0077
            L_0x0073:
                int r10 = r10 + 1
                goto L_0x0068
            L_0x0076:
                r0 = r3
            L_0x0077:
                if (r0 == 0) goto L_0x0082
                int r6 = r0.getScrollY()
                if (r6 <= 0) goto L_0x0081
                r6 = r2
                goto L_0x0082
            L_0x0081:
                r6 = r1
            L_0x0082:
                boolean r6 = r8.setLiftedState(r6)
                int r9 = android.os.Build.VERSION.SDK_INT
                if (r11 != 0) goto L_0x00b8
                if (r6 == 0) goto L_0x00bb
                java.util.List r6 = r7.getDependents(r8)
                int r7 = r6.size()
                r9 = r1
            L_0x0095:
                if (r9 >= r7) goto L_0x00b6
                java.lang.Object r10 = r6.get(r9)
                android.view.View r10 = (android.view.View) r10
                android.view.ViewGroup$LayoutParams r10 = r10.getLayoutParams()
                android.support.design.widget.CoordinatorLayout$LayoutParams r10 = (android.support.design.widget.CoordinatorLayout.LayoutParams) r10
                android.support.design.widget.CoordinatorLayout$Behavior r10 = r10.mBehavior
                boolean r11 = r10 instanceof android.support.design.widget.AppBarLayout.ScrollingViewBehavior
                if (r11 == 0) goto L_0x00b3
                android.support.design.widget.AppBarLayout$ScrollingViewBehavior r10 = (android.support.design.widget.AppBarLayout.ScrollingViewBehavior) r10
                int r6 = r10.getOverlayTop()
                if (r6 == 0) goto L_0x00b6
                r1 = r2
                goto L_0x00b6
            L_0x00b3:
                int r9 = r9 + 1
                goto L_0x0095
            L_0x00b6:
                if (r1 == 0) goto L_0x00bb
            L_0x00b8:
                r8.jumpDrawablesToCurrentState()
            L_0x00bb:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.BaseBehavior.updateAppBarLayoutDrawableState(android.support.design.widget.CoordinatorLayout, android.support.design.widget.AppBarLayout, int, int, boolean):void");
        }

        /* access modifiers changed from: package-private */
        public int getTopBottomOffsetForScrollingSibling() {
            return getTopAndBottomOffset() + this.offsetDelta;
        }

        /* access modifiers changed from: package-private */
        public boolean isOffsetAnimatorRunning() {
            ValueAnimator valueAnimator = this.offsetAnimator;
            return valueAnimator != null && valueAnimator.isRunning();
        }

        /* access modifiers changed from: package-private */
        public boolean canDragView(T t) {
            WeakReference<View> weakReference = this.lastNestedScrollingChildRef;
            if (weakReference == null) {
                return true;
            }
            View view = (View) weakReference.get();
            if (view == null || !view.isShown() || view.canScrollVertically(-1)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public int getMaxDragOffset(T t) {
            return -t.getDownNestedScrollRange();
        }

        /* access modifiers changed from: package-private */
        public int getScrollRangeForDragFling(T t) {
            return t.getTotalScrollRange();
        }

        /* access modifiers changed from: package-private */
        public void onFlingFinished(CoordinatorLayout coordinatorLayout, T t) {
            snapToChildIfNeeded(coordinatorLayout, t);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, T t, int i) {
            int i2;
            super.onLayoutChild(coordinatorLayout, t, i);
            int pendingAction = t.getPendingAction();
            int i3 = this.offsetToChildIndexOnLayout;
            if (i3 >= 0 && (pendingAction & 8) == 0) {
                View childAt = t.getChildAt(i3);
                int i4 = -childAt.getBottom();
                if (this.offsetToChildIndexOnLayoutIsMinHeight) {
                    i2 = ViewCompat.getMinimumHeight(childAt) + t.getTopInset();
                } else {
                    i2 = Math.round(((float) childAt.getHeight()) * this.offsetToChildIndexOnLayoutPerc);
                }
                setHeaderTopBottomOffset(coordinatorLayout, t, i2 + i4);
            } else if (pendingAction != 0) {
                boolean z = (pendingAction & 4) != 0;
                if ((pendingAction & 2) != 0) {
                    int i5 = -t.getUpNestedPreScrollRange();
                    if (z) {
                        animateOffsetTo(coordinatorLayout, t, i5, 0.0f);
                    } else {
                        setHeaderTopBottomOffset(coordinatorLayout, t, i5);
                    }
                } else if ((pendingAction & 1) != 0) {
                    if (z) {
                        animateOffsetTo(coordinatorLayout, t, 0, 0.0f);
                    } else {
                        setHeaderTopBottomOffset(coordinatorLayout, t, 0);
                    }
                }
            }
            t.resetPendingAction();
            this.offsetToChildIndexOnLayout = -1;
            setTopAndBottomOffset(R$dimen.clamp(getTopAndBottomOffset(), -t.getTotalScrollRange(), 0));
            updateAppBarLayoutDrawableState(coordinatorLayout, t, getTopAndBottomOffset(), 0, true);
            t.dispatchOffsetUpdates(getTopAndBottomOffset());
            return true;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, T t, int i, int i2, int i3, int i4) {
            if (((CoordinatorLayout.LayoutParams) t.getLayoutParams()).height != -2) {
                return false;
            }
            coordinatorLayout.onMeasureChild(t, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0), i4);
            return true;
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, T t, View view, int i, int i2, int[] iArr, int i3) {
            int i4;
            int i5;
            if (i2 != 0) {
                if (i2 < 0) {
                    i4 = -t.getTotalScrollRange();
                    i5 = t.getDownNestedPreScrollRange() + i4;
                } else {
                    i4 = -t.getUpNestedPreScrollRange();
                    i5 = 0;
                }
                int i6 = i4;
                int i7 = i5;
                if (i6 != i7) {
                    iArr[1] = scroll(coordinatorLayout, t, i2, i6, i7);
                    stopNestedScrollIfNeeded(i2, t, view, i3);
                }
            }
        }

        public void onNestedScroll(CoordinatorLayout coordinatorLayout, T t, View view, int i, int i2, int i3, int i4, int i5) {
            if (i4 < 0) {
                scroll(coordinatorLayout, t, i4, -t.getDownNestedScrollRange(), 0);
                stopNestedScrollIfNeeded(i4, t, view, i5);
            }
            if (t.isLiftOnScroll()) {
                t.setLiftedState(view.getScrollY() > 0);
            }
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, T t, Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                SavedState savedState = (SavedState) parcelable;
                savedState.getSuperState();
                this.offsetToChildIndexOnLayout = savedState.firstVisibleChildIndex;
                this.offsetToChildIndexOnLayoutPerc = savedState.firstVisibleChildPercentageShown;
                this.offsetToChildIndexOnLayoutIsMinHeight = savedState.firstVisibleChildAtMinimumHeight;
                return;
            }
            this.offsetToChildIndexOnLayout = -1;
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, T t) {
            AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            int topAndBottomOffset = getTopAndBottomOffset();
            int childCount = t.getChildCount();
            boolean z = false;
            int i = 0;
            while (i < childCount) {
                View childAt = t.getChildAt(i);
                int bottom = childAt.getBottom() + topAndBottomOffset;
                if (childAt.getTop() + topAndBottomOffset > 0 || bottom < 0) {
                    i++;
                } else {
                    SavedState savedState = new SavedState(absSavedState);
                    savedState.firstVisibleChildIndex = i;
                    if (bottom == ViewCompat.getMinimumHeight(childAt) + t.getTopInset()) {
                        z = true;
                    }
                    savedState.firstVisibleChildAtMinimumHeight = z;
                    savedState.firstVisibleChildPercentageShown = ((float) bottom) / ((float) childAt.getHeight());
                    return savedState;
                }
            }
            return absSavedState;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
            if ((r3.hasScrollableChildren() && r2.getHeight() - r4.getHeight() <= r3.getHeight()) != false) goto L_0x0028;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onStartNestedScroll(android.support.design.widget.CoordinatorLayout r2, T r3, android.view.View r4, android.view.View r5, int r6, int r7) {
            /*
                r1 = this;
                r5 = r6 & 2
                r6 = 1
                r0 = 0
                if (r5 == 0) goto L_0x0027
                boolean r5 = r3.isLiftOnScroll()
                if (r5 != 0) goto L_0x0028
                boolean r5 = r3.hasScrollableChildren()
                if (r5 == 0) goto L_0x0023
                int r2 = r2.getHeight()
                int r4 = r4.getHeight()
                int r2 = r2 - r4
                int r3 = r3.getHeight()
                if (r2 > r3) goto L_0x0023
                r2 = r6
                goto L_0x0024
            L_0x0023:
                r2 = r0
            L_0x0024:
                if (r2 == 0) goto L_0x0027
                goto L_0x0028
            L_0x0027:
                r6 = r0
            L_0x0028:
                if (r6 == 0) goto L_0x0031
                android.animation.ValueAnimator r2 = r1.offsetAnimator
                if (r2 == 0) goto L_0x0031
                r2.cancel()
            L_0x0031:
                r2 = 0
                r1.lastNestedScrollingChildRef = r2
                r1.lastStartedType = r7
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.BaseBehavior.onStartNestedScroll(android.support.design.widget.CoordinatorLayout, android.support.design.widget.AppBarLayout, android.view.View, android.view.View, int, int):boolean");
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, T t, View view, int i) {
            if (this.lastStartedType == 0 || i == 1) {
                snapToChildIfNeeded(coordinatorLayout, t);
            }
            this.lastNestedScrollingChildRef = new WeakReference<>(view);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00a3  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00a5  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int setHeaderTopBottomOffset(android.support.design.widget.CoordinatorLayout r9, T r10, int r11, int r12, int r13) {
            /*
                r8 = this;
                int r0 = r8.getTopBottomOffsetForScrollingSibling()
                r1 = 0
                if (r12 == 0) goto L_0x00af
                if (r0 < r12) goto L_0x00af
                if (r0 > r13) goto L_0x00af
                int r5 = android.support.design.R$dimen.clamp(r11, r12, r13)
                if (r0 == r5) goto L_0x00b1
                boolean r11 = r10.hasChildWithInterpolator()
                if (r11 == 0) goto L_0x0084
                int r11 = java.lang.Math.abs(r5)
                int r12 = r10.getChildCount()
                r13 = r1
            L_0x0020:
                if (r13 >= r12) goto L_0x0084
                android.view.View r2 = r10.getChildAt(r13)
                android.view.ViewGroup$LayoutParams r3 = r2.getLayoutParams()
                android.support.design.widget.AppBarLayout$LayoutParams r3 = (android.support.design.widget.AppBarLayout.LayoutParams) r3
                android.view.animation.Interpolator r4 = r3.getScrollInterpolator()
                int r6 = r2.getTop()
                if (r11 < r6) goto L_0x0081
                int r6 = r2.getBottom()
                if (r11 > r6) goto L_0x0081
                if (r4 == 0) goto L_0x0084
                int r12 = r3.scrollFlags
                r13 = r12 & 1
                if (r13 == 0) goto L_0x0058
                int r13 = r2.getHeight()
                int r6 = r3.topMargin
                int r13 = r13 + r6
                int r3 = r3.bottomMargin
                int r13 = r13 + r3
                int r1 = r1 + r13
                r12 = r12 & 2
                if (r12 == 0) goto L_0x0058
                int r12 = android.support.p000v4.view.ViewCompat.getMinimumHeight(r2)
                int r1 = r1 - r12
            L_0x0058:
                boolean r12 = android.support.p000v4.view.ViewCompat.getFitsSystemWindows(r2)
                if (r12 == 0) goto L_0x0063
                int r12 = r10.getTopInset()
                int r1 = r1 - r12
            L_0x0063:
                if (r1 <= 0) goto L_0x0084
                int r12 = r2.getTop()
                int r11 = r11 - r12
                float r12 = (float) r1
                float r11 = (float) r11
                float r11 = r11 / r12
                float r11 = r4.getInterpolation(r11)
                float r11 = r11 * r12
                int r11 = java.lang.Math.round(r11)
                int r12 = java.lang.Integer.signum(r5)
                int r13 = r2.getTop()
                int r13 = r13 + r11
                int r13 = r13 * r12
                goto L_0x0085
            L_0x0081:
                int r13 = r13 + 1
                goto L_0x0020
            L_0x0084:
                r13 = r5
            L_0x0085:
                boolean r11 = r8.setTopAndBottomOffset(r13)
                int r1 = r0 - r5
                int r12 = r5 - r13
                r8.offsetDelta = r12
                if (r11 != 0) goto L_0x009a
                boolean r11 = r10.hasChildWithInterpolator()
                if (r11 == 0) goto L_0x009a
                r9.dispatchDependentViewsChanged(r10)
            L_0x009a:
                int r11 = r8.getTopAndBottomOffset()
                r10.dispatchOffsetUpdates(r11)
                if (r5 >= r0) goto L_0x00a5
                r11 = -1
                goto L_0x00a6
            L_0x00a5:
                r11 = 1
            L_0x00a6:
                r6 = r11
                r7 = 0
                r2 = r8
                r3 = r9
                r4 = r10
                r2.updateAppBarLayoutDrawableState(r3, r4, r5, r6, r7)
                goto L_0x00b1
            L_0x00af:
                r8.offsetDelta = r1
            L_0x00b1:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.BaseBehavior.setHeaderTopBottomOffset(android.support.design.widget.CoordinatorLayout, android.support.design.widget.AppBarLayout, int, int, int):int");
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected static class SavedState extends android.support.p000v4.view.AbsSavedState {
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
            boolean firstVisibleChildAtMinimumHeight;
            int firstVisibleChildIndex;
            float firstVisibleChildPercentageShown;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.firstVisibleChildIndex = parcel.readInt();
                this.firstVisibleChildPercentageShown = parcel.readFloat();
                this.firstVisibleChildAtMinimumHeight = parcel.readByte() != 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeInt(this.firstVisibleChildIndex);
                parcel.writeFloat(this.firstVisibleChildPercentageShown);
                parcel.writeByte(this.firstVisibleChildAtMinimumHeight ? (byte) 1 : 0);
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }
        }
    }

    public interface BaseOnOffsetChangedListener<T extends AppBarLayout> {
        void onOffsetChanged(T t, int i);
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            super.onLayoutChild(coordinatorLayout, appBarLayout, i);
            return true;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, int i3, int i4) {
            if (((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).height != -2) {
                return false;
            }
            coordinatorLayout.onMeasureChild(appBarLayout, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0), i4);
            return true;
        }

        public /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr, int i3) {
            super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i, i2, iArr, i3);
        }

        public /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int i3, int i4, int i5) {
            super.onNestedScroll(coordinatorLayout, appBarLayout, view, i, i2, i3, i4, i5);
        }

        public /* bridge */ /* synthetic */ void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            int topAndBottomOffset = getTopAndBottomOffset();
            int childCount = appBarLayout.getChildCount();
            boolean z = false;
            int i = 0;
            while (i < childCount) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + topAndBottomOffset;
                if (childAt.getTop() + topAndBottomOffset > 0 || bottom < 0) {
                    i++;
                } else {
                    BaseBehavior.SavedState savedState = new BaseBehavior.SavedState(absSavedState);
                    savedState.firstVisibleChildIndex = i;
                    if (bottom == ViewCompat.getMinimumHeight(childAt) + appBarLayout.getTopInset()) {
                        z = true;
                    }
                    savedState.firstVisibleChildAtMinimumHeight = z;
                    savedState.firstVisibleChildPercentageShown = ((float) bottom) / ((float) childAt.getHeight());
                    return savedState;
                }
            }
            return absSavedState;
        }

        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i, int i2) {
            return super.onStartNestedScroll(coordinatorLayout, appBarLayout, view, view2, i, i2);
        }

        public /* bridge */ /* synthetic */ void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
            super.onStopNestedScroll(coordinatorLayout, appBarLayout, view, i);
        }

        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i) {
            return super.setTopAndBottomOffset(i);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        /* access modifiers changed from: package-private */
        public float getOverlapRatioForOffset(View view) {
            int i;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).mBehavior;
                int topBottomOffsetForScrollingSibling = behavior instanceof BaseBehavior ? ((BaseBehavior) behavior).getTopBottomOffsetForScrollingSibling() : 0;
                if ((downNestedPreScrollRange == 0 || totalScrollRange + topBottomOffsetForScrollingSibling > downNestedPreScrollRange) && (i = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (((float) topBottomOffsetForScrollingSibling) / ((float) i)) + 1.0f;
                }
            }
            return 0.0f;
        }

        /* access modifiers changed from: package-private */
        public int getScrollRange(View view) {
            if (view instanceof AppBarLayout) {
                return ((AppBarLayout) view).getTotalScrollRange();
            }
            return view.getMeasuredHeight();
        }

        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) view2.getLayoutParams()).mBehavior;
            if (behavior instanceof BaseBehavior) {
                ViewCompat.offsetTopAndBottom(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) behavior).offsetDelta) + getVerticalLayoutGap()) - getOverlapPixelsForOffset(view2));
            }
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.isLiftOnScroll()) {
                    appBarLayout.setLiftedState(view.getScrollY() > 0);
                }
            }
            return false;
        }

        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            super.onLayoutChild(coordinatorLayout, view, i);
            return true;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            View findFirstDependency;
            int i5 = view.getLayoutParams().height;
            if ((i5 != -1 && i5 != -2) || (findFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view))) == null) {
                return false;
            }
            if (!ViewCompat.getFitsSystemWindows(findFirstDependency) || ViewCompat.getFitsSystemWindows(view)) {
                View view2 = view;
            } else {
                View view3 = view;
                ViewCompat.setFitsSystemWindows(view, true);
                if (ViewCompat.getFitsSystemWindows(view)) {
                    view.requestLayout();
                    return true;
                }
            }
            int size = View.MeasureSpec.getSize(i3);
            if (size == 0) {
                size = coordinatorLayout.getHeight();
            }
            coordinatorLayout.onMeasureChild(view, i, i2, View.MeasureSpec.makeMeasureSpec((size - findFirstDependency.getMeasuredHeight()) + getScrollRange(findFirstDependency), i5 == -1 ? 1073741824 : Integer.MIN_VALUE), i4);
            return true;
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout findFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view));
            if (findFirstDependency != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.tempRect1;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    findFirstDependency.setExpanded(false, !z);
                    return true;
                }
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ScrollingViewBehavior_Layout);
            setOverlayTop(obtainStyledAttributes.getDimensionPixelSize(R$styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: package-private */
        public AppBarLayout findFirstDependency(List<View> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: package-private */
    public void dispatchOffsetUpdates(int i) {
        List<BaseOnOffsetChangedListener> list = this.listeners;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                BaseOnOffsetChangedListener baseOnOffsetChangedListener = this.listeners.get(i2);
                if (baseOnOffsetChangedListener != null) {
                    baseOnOffsetChangedListener.onOffsetChanged(this, i);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getDownNestedPreScrollRange() {
        int i;
        int i2 = this.downPreScrollRange;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = layoutParams.scrollFlags;
            if ((i4 & 5) == 5) {
                int i5 = layoutParams.topMargin + layoutParams.bottomMargin + i3;
                if ((i4 & 8) != 0) {
                    i3 = ViewCompat.getMinimumHeight(childAt) + i5;
                } else {
                    if ((i4 & 2) != 0) {
                        i = ViewCompat.getMinimumHeight(childAt);
                    } else {
                        i = getTopInset();
                    }
                    i3 = (measuredHeight - i) + i5;
                }
            } else if (i3 > 0) {
                break;
            }
        }
        int max = Math.max(0, i3);
        this.downPreScrollRange = max;
        return max;
    }

    /* access modifiers changed from: package-private */
    public int getDownNestedScrollRange() {
        int i = this.downScrollRange;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = layoutParams.topMargin + layoutParams.bottomMargin + childAt.getMeasuredHeight();
            int i4 = layoutParams.scrollFlags;
            if ((i4 & 1) == 0) {
                break;
            }
            i3 += measuredHeight;
            if ((i4 & 2) != 0) {
                i3 -= ViewCompat.getMinimumHeight(childAt) + getTopInset();
                break;
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.downScrollRange = max;
        return max;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int minimumHeight = ViewCompat.getMinimumHeight(this);
        if (minimumHeight == 0) {
            int childCount = getChildCount();
            minimumHeight = childCount >= 1 ? ViewCompat.getMinimumHeight(getChildAt(childCount - 1)) : 0;
            if (minimumHeight == 0) {
                return getHeight() / 3;
            }
        }
        return (minimumHeight * 2) + topInset;
    }

    /* access modifiers changed from: package-private */
    public int getPendingAction() {
        return this.pendingAction;
    }

    /* access modifiers changed from: package-private */
    public final int getTopInset() {
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            return windowInsetsCompat.getSystemWindowInsetTop();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i = this.totalScrollRange;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = layoutParams.scrollFlags;
            if ((i4 & 1) == 0) {
                break;
            }
            i3 += measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin;
            if ((i4 & 2) != 0) {
                i3 -= ViewCompat.getMinimumHeight(childAt);
                break;
            }
            i2++;
        }
        int max = Math.max(0, i3 - getTopInset());
        this.totalScrollRange = max;
        return max;
    }

    /* access modifiers changed from: package-private */
    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    /* access modifiers changed from: package-private */
    public boolean hasChildWithInterpolator() {
        return this.haveChildWithInterpolator;
    }

    /* access modifiers changed from: package-private */
    public boolean hasScrollableChildren() {
        return getTotalScrollRange() != 0;
    }

    public boolean isLiftOnScroll() {
        return this.liftOnScroll;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] iArr = this.tmpStatesArray;
        int[] onCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        iArr[0] = this.liftable ? R.attr.state_liftable : -2130969008;
        iArr[1] = (!this.liftable || !this.lifted) ? -2130969009 : R.attr.state_lifted;
        iArr[2] = this.liftable ? R.attr.state_collapsible : -2130969007;
        iArr[3] = (!this.liftable || !this.lifted) ? -2130969006 : R.attr.state_collapsed;
        return LinearLayout.mergeDrawableStates(onCreateDrawableState, iArr);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        if (r3 != false) goto L_0x0059;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        /*
            r1 = this;
            super.onLayout(r2, r3, r4, r5, r6)
            r2 = -1
            r1.totalScrollRange = r2
            r1.downPreScrollRange = r2
            r1.downScrollRange = r2
            r2 = 0
            r1.haveChildWithInterpolator = r2
            int r3 = r1.getChildCount()
            r4 = r2
        L_0x0012:
            r5 = 1
            if (r4 >= r3) goto L_0x0029
            android.view.View r6 = r1.getChildAt(r4)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            android.support.design.widget.AppBarLayout$LayoutParams r6 = (android.support.design.widget.AppBarLayout.LayoutParams) r6
            android.view.animation.Interpolator r6 = r6.scrollInterpolator
            if (r6 == 0) goto L_0x0026
            r1.haveChildWithInterpolator = r5
            goto L_0x0029
        L_0x0026:
            int r4 = r4 + 1
            goto L_0x0012
        L_0x0029:
            boolean r3 = r1.liftableOverride
            if (r3 != 0) goto L_0x0063
            boolean r3 = r1.liftOnScroll
            if (r3 != 0) goto L_0x0059
            int r3 = r1.getChildCount()
            r4 = r2
        L_0x0036:
            if (r4 >= r3) goto L_0x0056
            android.view.View r6 = r1.getChildAt(r4)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            android.support.design.widget.AppBarLayout$LayoutParams r6 = (android.support.design.widget.AppBarLayout.LayoutParams) r6
            int r6 = r6.scrollFlags
            r0 = r6 & 1
            if (r0 != r5) goto L_0x004e
            r6 = r6 & 10
            if (r6 == 0) goto L_0x004e
            r6 = r5
            goto L_0x004f
        L_0x004e:
            r6 = r2
        L_0x004f:
            if (r6 == 0) goto L_0x0053
            r3 = r5
            goto L_0x0057
        L_0x0053:
            int r4 = r4 + 1
            goto L_0x0036
        L_0x0056:
            r3 = r2
        L_0x0057:
            if (r3 == 0) goto L_0x005a
        L_0x0059:
            r2 = r5
        L_0x005a:
            boolean r3 = r1.liftable
            if (r3 == r2) goto L_0x0063
            r1.liftable = r2
            r1.refreshDrawableState()
        L_0x0063:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.AppBarLayout.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
    }

    /* access modifiers changed from: package-private */
    public void resetPendingAction() {
        this.pendingAction = 0;
    }

    public void setExpanded(boolean z, boolean z2) {
        int i = z ? 1 : 2;
        int i2 = 0;
        if (z2) {
            i2 = 4;
        }
        this.pendingAction = i | i2 | 8;
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public boolean setLiftedState(boolean z) {
        if (this.lifted == z) {
            return false;
        }
        this.lifted = z;
        refreshDrawableState();
        return true;
    }

    public void setOrientation(int i) {
        if (i == 1) {
            super.setOrientation(i);
            return;
        }
        throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        int i = Build.VERSION.SDK_INT;
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return new LayoutParams((LinearLayout.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        int scrollFlags = 1;
        Interpolator scrollInterpolator;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout_Layout);
            this.scrollFlags = obtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            if (obtainStyledAttributes.hasValue(1)) {
                this.scrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(1, 0));
            }
            obtainStyledAttributes.recycle();
        }

        public Interpolator getScrollInterpolator() {
            return this.scrollInterpolator;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }
}
