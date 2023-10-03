package android.support.design.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.animation.AnimationUtils;
import android.support.design.behavior.HideBottomViewOnScrollBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
    private Animator attachAnimator;
    private int fabAlignmentMode;
    AnimatorListenerAdapter fabAnimationListener;
    private boolean fabAttached;
    private final int fabOffsetEndMode;
    private boolean hideOnScroll;
    private Animator menuAnimator;
    private Animator modeAnimator;

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        private final Rect fabContentRect = new Rect();

        public Behavior() {
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i) {
            FloatingActionButton access$1100 = bottomAppBar.findDependentFab();
            if (access$1100 != null) {
                ((CoordinatorLayout.LayoutParams) access$1100.getLayoutParams()).anchorGravity = 17;
                access$1100.removeOnHideAnimationListener(bottomAppBar.fabAnimationListener);
                access$1100.removeOnShowAnimationListener(bottomAppBar.fabAnimationListener);
                access$1100.addOnHideAnimationListener(bottomAppBar.fabAnimationListener);
                access$1100.addOnShowAnimationListener(bottomAppBar.fabAnimationListener);
                access$1100.getMeasuredContentRect(this.fabContentRect);
                this.fabContentRect.height();
                throw null;
            } else if (BottomAppBar.access$1200(bottomAppBar)) {
                coordinatorLayout.onLayoutChild(bottomAppBar, i);
                super.onLayoutChild(coordinatorLayout, bottomAppBar, i);
                return false;
            } else {
                BottomAppBar.access$1300(bottomAppBar);
                throw null;
            }
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i, int i2) {
            return bottomAppBar.getHideOnScroll() && super.onStartNestedScroll(coordinatorLayout, bottomAppBar, view, view2, i, i2);
        }

        /* access modifiers changed from: protected */
        public void slideDown(BottomAppBar bottomAppBar) {
            super.slideDown(bottomAppBar);
            FloatingActionButton access$1100 = bottomAppBar.findDependentFab();
            if (access$1100 != null) {
                access$1100.getContentRect(this.fabContentRect);
                float measuredHeight = (float) (access$1100.getMeasuredHeight() - this.fabContentRect.height());
                access$1100.clearAnimation();
                access$1100.animate().translationY(((float) (-access$1100.getPaddingBottom())) + measuredHeight).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setDuration(175);
            }
        }

        /* access modifiers changed from: protected */
        public void slideUp(BottomAppBar bottomAppBar) {
            super.slideUp(bottomAppBar);
            FloatingActionButton access$1100 = bottomAppBar.findDependentFab();
            if (access$1100 != null) {
                access$1100.clearAnimation();
                ViewPropertyAnimator animate = access$1100.animate();
                float unused = bottomAppBar.getFabTranslationY();
                animate.translationY(0.0f).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setDuration(225);
            }
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    static class SavedState extends AbsSavedState {
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
        int fabAlignmentMode;
        boolean fabAttached;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.fabAlignmentMode);
            parcel.writeInt(this.fabAttached ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.fabAlignmentMode = parcel.readInt();
            this.fabAttached = parcel.readInt() != 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = r1.menuAnimator;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        r1 = r1.modeAnimator;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ boolean access$1200(android.support.design.bottomappbar.BottomAppBar r1) {
        /*
            android.animation.Animator r0 = r1.attachAnimator
            if (r0 == 0) goto L_0x000a
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L_0x001e
        L_0x000a:
            android.animation.Animator r0 = r1.menuAnimator
            if (r0 == 0) goto L_0x0014
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L_0x001e
        L_0x0014:
            android.animation.Animator r1 = r1.modeAnimator
            if (r1 == 0) goto L_0x0020
            boolean r1 = r1.isRunning()
            if (r1 == 0) goto L_0x0020
        L_0x001e:
            r1 = 1
            goto L_0x0021
        L_0x0020:
            r1 = 0
        L_0x0021:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.bottomappbar.BottomAppBar.access$1200(android.support.design.bottomappbar.BottomAppBar):boolean");
    }

    static /* synthetic */ void access$1300(BottomAppBar bottomAppBar) {
        bottomAppBar.setCutoutState();
        throw null;
    }

    /* access modifiers changed from: private */
    public FloatingActionButton findDependentFab() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View next : ((CoordinatorLayout) getParent()).getDependents(this)) {
            if (next instanceof FloatingActionButton) {
                return (FloatingActionButton) next;
            }
        }
        return null;
    }

    private float getFabTranslationX() {
        int i = this.fabAlignmentMode;
        int i2 = 0;
        int i3 = 1;
        boolean z = ViewCompat.getLayoutDirection(this) == 1;
        if (i == 1) {
            int measuredWidth = (getMeasuredWidth() / 2) - this.fabOffsetEndMode;
            if (z) {
                i3 = -1;
            }
            i2 = measuredWidth * i3;
        }
        return (float) i2;
    }

    /* access modifiers changed from: private */
    public float getFabTranslationY() {
        FloatingActionButton findDependentFab = findDependentFab();
        if (findDependentFab == null) {
            return 0.0f;
        }
        Rect rect = new Rect();
        findDependentFab.getContentRect(rect);
        if (((float) rect.height()) == 0.0f) {
            findDependentFab.getMeasuredHeight();
        }
        findDependentFab.getHeight();
        findDependentFab.getHeight();
        rect.height();
        throw null;
    }

    private void setCutoutState() {
        getFabTranslationX();
        throw null;
    }

    public boolean getHideOnScroll() {
        return this.hideOnScroll;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        Animator animator = this.attachAnimator;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.menuAnimator;
        if (animator2 != null) {
            animator2.cancel();
        }
        Animator animator3 = this.modeAnimator;
        if (animator3 != null) {
            animator3.cancel();
        }
        setCutoutState();
        throw null;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.fabAlignmentMode = savedState.fabAlignmentMode;
        this.fabAttached = savedState.fabAttached;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.fabAlignmentMode = this.fabAlignmentMode;
        savedState.fabAttached = this.fabAttached;
        return savedState;
    }

    public void setSubtitle(CharSequence charSequence) {
    }

    public void setTitle(CharSequence charSequence) {
    }
}
