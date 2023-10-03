package com.android.systemui.bubbles.animation;

import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1778R$integer;
import com.android.systemui.Interpolators;
import com.android.systemui.bubbles.animation.PhysicsAnimationLayout;
import com.google.android.collect.Sets;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Set;

public class ExpandedAnimationController extends PhysicsAnimationLayout.PhysicsAnimationController {
    private Runnable mAfterCollapse;
    private Runnable mAfterExpand;
    private boolean mAnimatingCollapse = false;
    private boolean mAnimatingExpand = false;
    private boolean mBubbleDraggedOutEnough = false;
    private View mBubbleDraggingOut;
    private float mBubblePaddingTop;
    private float mBubbleSizePx;
    private int mBubblesMaxRendered;
    private PointF mCollapsePoint;
    private Point mDisplaySize;
    private int mExpandedViewPadding;
    private boolean mIndividualBubbleWithinDismissTarget = false;
    private float mLauncherGridDiff;
    private int mScreenOrientation;
    private boolean mSpringingBubbleToTouch = false;
    private float mStackOffsetPx;
    private float mStatusBarHeight;

    /* access modifiers changed from: package-private */
    public int getNextAnimationInChain(DynamicAnimation.ViewProperty viewProperty, int i) {
        return -1;
    }

    /* access modifiers changed from: package-private */
    public float getOffsetForChainedPropertyAnimation(DynamicAnimation.ViewProperty viewProperty) {
        return 0.0f;
    }

    public ExpandedAnimationController(Point point, int i, int i2) {
        updateOrientation(i2, point);
        this.mExpandedViewPadding = i;
        this.mLauncherGridDiff = 30.0f;
    }

    public void expandFromStack(Runnable runnable) {
        this.mAnimatingCollapse = false;
        this.mAnimatingExpand = true;
        this.mAfterExpand = runnable;
        startOrUpdatePathAnimation(true);
    }

    public void collapseBackToStack(PointF pointF, Runnable runnable) {
        this.mAnimatingExpand = false;
        this.mAnimatingCollapse = true;
        this.mAfterCollapse = runnable;
        this.mCollapsePoint = pointF;
        startOrUpdatePathAnimation(false);
    }

    public void updateOrientation(int i, Point point) {
        this.mScreenOrientation = i;
        this.mDisplaySize = point;
        PhysicsAnimationLayout physicsAnimationLayout = this.mLayout;
        if (physicsAnimationLayout != null) {
            Resources resources = physicsAnimationLayout.getContext().getResources();
            this.mBubblePaddingTop = (float) resources.getDimensionPixelSize(C1775R$dimen.bubble_padding_top);
            this.mStatusBarHeight = (float) resources.getDimensionPixelSize(17105434);
        }
    }

    private void startOrUpdatePathAnimation(boolean z) {
        Runnable runnable;
        if (z) {
            runnable = new Runnable() {
                public final void run() {
                    ExpandedAnimationController.this.lambda$startOrUpdatePathAnimation$0$ExpandedAnimationController();
                }
            };
        } else {
            runnable = new Runnable() {
                public final void run() {
                    ExpandedAnimationController.this.lambda$startOrUpdatePathAnimation$1$ExpandedAnimationController();
                }
            };
        }
        animationsForChildrenFromIndex(0, new PhysicsAnimationLayout.PhysicsAnimationController.ChildAnimationConfigurator(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void configureAnimationForChildAtIndex(int i, PhysicsAnimationLayout.PhysicsPropertyAnimator physicsPropertyAnimator) {
                ExpandedAnimationController.this.lambda$startOrUpdatePathAnimation$2$ExpandedAnimationController(this.f$1, i, physicsPropertyAnimator);
            }
        }).startAll(runnable);
    }

    public /* synthetic */ void lambda$startOrUpdatePathAnimation$0$ExpandedAnimationController() {
        this.mAnimatingExpand = false;
        Runnable runnable = this.mAfterExpand;
        if (runnable != null) {
            runnable.run();
        }
        this.mAfterExpand = null;
    }

    public /* synthetic */ void lambda$startOrUpdatePathAnimation$1$ExpandedAnimationController() {
        this.mAnimatingCollapse = false;
        Runnable runnable = this.mAfterCollapse;
        if (runnable != null) {
            runnable.run();
        }
        this.mAfterCollapse = null;
    }

    public /* synthetic */ void lambda$startOrUpdatePathAnimation$2$ExpandedAnimationController(boolean z, int i, PhysicsAnimationLayout.PhysicsPropertyAnimator physicsPropertyAnimator) {
        int i2;
        View childAt = this.mLayout.getChildAt(i);
        Path path = new Path();
        path.moveTo(childAt.getTranslationX(), childAt.getTranslationY());
        float expandedY = getExpandedY();
        if (z) {
            path.lineTo(childAt.getTranslationX(), expandedY);
            path.lineTo(getBubbleLeft(i), expandedY);
        } else {
            float f = this.mCollapsePoint.x + ((this.mLayout.isFirstChildXLeftOfCenter(this.mCollapsePoint.x) ? -1.0f : 1.0f) * ((float) i) * this.mStackOffsetPx);
            path.lineTo(f, expandedY);
            path.lineTo(f, this.mCollapsePoint.y);
        }
        if ((z && !this.mLayout.isFirstChildXLeftOfCenter(childAt.getTranslationX())) || (!z && this.mLayout.isFirstChildXLeftOfCenter(this.mCollapsePoint.x))) {
            i2 = i * 10;
        } else {
            i2 = (this.mLayout.getChildCount() - i) * 10;
        }
        physicsPropertyAnimator.followAnimatedTargetAlongPath(path, 175, Interpolators.LINEAR, new Runnable[0]);
        physicsPropertyAnimator.withStartDelay((long) i2);
        physicsPropertyAnimator.withStiffness(1000.0f);
    }

    public void prepareForBubbleDrag(View view) {
        this.mLayout.cancelAnimationsOnView(view);
        this.mBubbleDraggingOut = view;
        this.mBubbleDraggingOut.setTranslationZ(32767.0f);
    }

    public void dragBubbleOut(View view, float f, float f2) {
        boolean z = true;
        if (this.mSpringingBubbleToTouch) {
            if (this.mLayout.arePropertiesAnimatingOnView(view, DynamicAnimation.TRANSLATION_X, DynamicAnimation.TRANSLATION_Y)) {
                PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(this.mBubbleDraggingOut);
                animationForChild.translationX(f, new Runnable[0]);
                animationForChild.translationY(f2, new Runnable[0]);
                animationForChild.withStiffness(10000.0f);
                animationForChild.start(new Runnable[0]);
            } else {
                this.mSpringingBubbleToTouch = false;
            }
        }
        if (!this.mSpringingBubbleToTouch && !this.mIndividualBubbleWithinDismissTarget) {
            view.setTranslationX(f);
            view.setTranslationY(f2);
        }
        if (f2 <= getExpandedY() + this.mBubbleSizePx && f2 >= getExpandedY() - this.mBubbleSizePx) {
            z = false;
        }
        if (z != this.mBubbleDraggedOutEnough) {
            updateBubblePositions();
            this.mBubbleDraggedOutEnough = z;
        }
    }

    public void dismissDraggedOutBubble(View view, Runnable runnable) {
        this.mIndividualBubbleWithinDismissTarget = false;
        PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(view);
        animationForChild.withStiffness(10000.0f);
        animationForChild.scaleX(1.1f, new Runnable[0]);
        animationForChild.scaleY(1.1f, new Runnable[0]);
        animationForChild.alpha(0.0f, runnable);
        animationForChild.start(new Runnable[0]);
        updateBubblePositions();
    }

    public View getDraggedOutBubble() {
        return this.mBubbleDraggingOut;
    }

    public void magnetBubbleToDismiss(View view, float f, float f2, float f3, Runnable runnable) {
        this.mIndividualBubbleWithinDismissTarget = true;
        this.mSpringingBubbleToTouch = false;
        PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(view);
        animationForChild.withStiffness(1500.0f);
        animationForChild.withDampingRatio(0.75f);
        animationForChild.withPositionStartVelocities(f, f2);
        animationForChild.translationX((((float) this.mLayout.getWidth()) / 2.0f) - (this.mBubbleSizePx / 2.0f), new Runnable[0]);
        animationForChild.translationY(f3, runnable);
        animationForChild.start(new Runnable[0]);
    }

    public void demagnetizeBubbleTo(float f, float f2, float f3, float f4) {
        this.mIndividualBubbleWithinDismissTarget = false;
        this.mSpringingBubbleToTouch = true;
        PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(this.mBubbleDraggingOut);
        animationForChild.translationX(f, new Runnable[0]);
        animationForChild.translationY(f2, new Runnable[0]);
        animationForChild.withPositionStartVelocities(f3, f4);
        animationForChild.withStiffness(10000.0f);
        animationForChild.start(new Runnable[0]);
    }

    public void snapBubbleBack(View view, float f, float f2) {
        int indexOfChild = this.mLayout.indexOfChild(view);
        PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChildAtIndex = animationForChildAtIndex(indexOfChild);
        animationForChildAtIndex.position(getBubbleLeft(indexOfChild), getExpandedY(), new Runnable[0]);
        animationForChildAtIndex.withPositionStartVelocities(f, f2);
        animationForChildAtIndex.start(new Runnable(view) {
            private final /* synthetic */ View f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                this.f$0.setTranslationZ(0.0f);
            }
        });
        updateBubblePositions();
    }

    public void onGestureFinished() {
        this.mBubbleDraggedOutEnough = false;
        this.mBubbleDraggingOut = null;
        updateBubblePositions();
    }

    public void updateYPosition(Runnable runnable) {
        if (this.mLayout != null) {
            animationsForChildrenFromIndex(0, new PhysicsAnimationLayout.PhysicsAnimationController.ChildAnimationConfigurator() {
                public final void configureAnimationForChildAtIndex(int i, PhysicsAnimationLayout.PhysicsPropertyAnimator physicsPropertyAnimator) {
                    ExpandedAnimationController.this.lambda$updateYPosition$4$ExpandedAnimationController(i, physicsPropertyAnimator);
                }
            }).startAll(runnable);
        }
    }

    public /* synthetic */ void lambda$updateYPosition$4$ExpandedAnimationController(int i, PhysicsAnimationLayout.PhysicsPropertyAnimator physicsPropertyAnimator) {
        physicsPropertyAnimator.translationY(getExpandedY(), new Runnable[0]);
    }

    public float getExpandedY() {
        PhysicsAnimationLayout physicsAnimationLayout = this.mLayout;
        float f = 0.0f;
        if (physicsAnimationLayout == null || physicsAnimationLayout.getRootWindowInsets() == null) {
            return 0.0f;
        }
        WindowInsets rootWindowInsets = this.mLayout.getRootWindowInsets();
        float f2 = this.mBubblePaddingTop;
        float f3 = this.mStatusBarHeight;
        if (rootWindowInsets.getDisplayCutout() != null) {
            f = (float) rootWindowInsets.getDisplayCutout().getSafeInsetTop();
        }
        return f2 + Math.max(f3, f);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("ExpandedAnimationController state:");
        printWriter.print("  isActive:          ");
        printWriter.println(isActiveController());
        printWriter.print("  animatingExpand:   ");
        printWriter.println(this.mAnimatingExpand);
        printWriter.print("  animatingCollapse: ");
        printWriter.println(this.mAnimatingCollapse);
        printWriter.print("  bubbleInDismiss:   ");
        printWriter.println(this.mIndividualBubbleWithinDismissTarget);
        printWriter.print("  springingBubble:   ");
        printWriter.println(this.mSpringingBubbleToTouch);
    }

    /* access modifiers changed from: package-private */
    public void onActiveControllerForLayout(PhysicsAnimationLayout physicsAnimationLayout) {
        Resources resources = physicsAnimationLayout.getResources();
        this.mStackOffsetPx = (float) resources.getDimensionPixelSize(C1775R$dimen.bubble_stack_offset);
        this.mBubblePaddingTop = (float) resources.getDimensionPixelSize(C1775R$dimen.bubble_padding_top);
        this.mBubbleSizePx = (float) resources.getDimensionPixelSize(C1775R$dimen.individual_bubble_size);
        this.mStatusBarHeight = (float) resources.getDimensionPixelSize(17105434);
        this.mBubblesMaxRendered = resources.getInteger(C1778R$integer.bubbles_max_rendered);
        this.mLayout.setVisibility(0);
        animationsForChildrenFromIndex(0, C0695x64c2867e.INSTANCE).startAll(new Runnable[0]);
    }

    static /* synthetic */ void lambda$onActiveControllerForLayout$5(int i, PhysicsAnimationLayout.PhysicsPropertyAnimator physicsPropertyAnimator) {
        physicsPropertyAnimator.scaleX(1.0f, new Runnable[0]);
        physicsPropertyAnimator.scaleY(1.0f, new Runnable[0]);
        physicsPropertyAnimator.alpha(1.0f, new Runnable[0]);
    }

    /* access modifiers changed from: package-private */
    public Set<DynamicAnimation.ViewProperty> getAnimatedProperties() {
        return Sets.newHashSet(new DynamicAnimation.ViewProperty[]{DynamicAnimation.TRANSLATION_X, DynamicAnimation.TRANSLATION_Y, DynamicAnimation.SCALE_X, DynamicAnimation.SCALE_Y, DynamicAnimation.ALPHA});
    }

    /* access modifiers changed from: package-private */
    public SpringForce getSpringForce(DynamicAnimation.ViewProperty viewProperty, View view) {
        SpringForce springForce = new SpringForce();
        springForce.setDampingRatio(0.75f);
        springForce.setStiffness(200.0f);
        return springForce;
    }

    /* access modifiers changed from: package-private */
    public void onChildAdded(View view, int i) {
        if (this.mAnimatingExpand) {
            startOrUpdatePathAnimation(true);
        } else if (this.mAnimatingCollapse) {
            startOrUpdatePathAnimation(false);
        } else {
            view.setTranslationX(getBubbleLeft(i));
            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(view);
            animationForChild.translationY(getExpandedY() - (this.mBubbleSizePx * 4.0f), getExpandedY(), new Runnable[0]);
            animationForChild.start(new Runnable[0]);
            updateBubblePositions();
        }
    }

    /* access modifiers changed from: package-private */
    public void onChildRemoved(View view, int i, Runnable runnable) {
        PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(view);
        if (view.equals(this.mBubbleDraggingOut)) {
            this.mBubbleDraggingOut = null;
            runnable.run();
        } else {
            animationForChild.alpha(0.0f, runnable);
            animationForChild.withStiffness(10000.0f);
            animationForChild.withDampingRatio(1.0f);
            animationForChild.scaleX(1.1f, new Runnable[0]);
            animationForChild.scaleY(1.1f, new Runnable[0]);
            animationForChild.start(new Runnable[0]);
        }
        updateBubblePositions();
    }

    /* access modifiers changed from: package-private */
    public void onChildReordered(View view, int i, int i2) {
        updateBubblePositions();
        if (this.mAnimatingCollapse) {
            startOrUpdatePathAnimation(false);
        }
    }

    private void updateBubblePositions() {
        if (!this.mAnimatingExpand && !this.mAnimatingCollapse) {
            int i = 0;
            while (i < this.mLayout.getChildCount()) {
                View childAt = this.mLayout.getChildAt(i);
                if (!childAt.equals(this.mBubbleDraggingOut)) {
                    PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(childAt);
                    animationForChild.translationX(getBubbleLeft(i), new Runnable[0]);
                    animationForChild.start(new Runnable[0]);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public float getBubbleLeft(int i) {
        return getRowLeft() + (((float) i) * (this.mBubbleSizePx + getSpaceBetweenBubbles()));
    }

    private float getWidthForDisplayingBubbles() {
        float availableScreenWidth = getAvailableScreenWidth(true);
        return this.mScreenOrientation == 2 ? Math.max((float) this.mDisplaySize.y, availableScreenWidth * 0.66f) : availableScreenWidth;
    }

    private float getAvailableScreenWidth(boolean z) {
        int i;
        int i2;
        float f = (float) this.mDisplaySize.x;
        PhysicsAnimationLayout physicsAnimationLayout = this.mLayout;
        WindowInsets rootWindowInsets = physicsAnimationLayout != null ? physicsAnimationLayout.getRootWindowInsets() : null;
        if (rootWindowInsets == null) {
            return f;
        }
        DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
        int i3 = 0;
        if (displayCutout != null) {
            i = displayCutout.getSafeInsetLeft();
            i2 = displayCutout.getSafeInsetRight();
        } else {
            i2 = 0;
            i = 0;
        }
        int stableInsetLeft = z ? rootWindowInsets.getStableInsetLeft() : 0;
        if (z) {
            i3 = rootWindowInsets.getStableInsetRight();
        }
        return (f - ((float) Math.max(stableInsetLeft, i))) - ((float) Math.max(i3, i2));
    }

    private float getRowLeft() {
        PhysicsAnimationLayout physicsAnimationLayout = this.mLayout;
        if (physicsAnimationLayout == null) {
            return 0.0f;
        }
        int childCount = physicsAnimationLayout.getChildCount();
        return (getAvailableScreenWidth(false) / 2.0f) - (((((float) (childCount - 1)) * getSpaceBetweenBubbles()) + (((float) childCount) * this.mBubbleSizePx)) / 2.0f);
    }

    private float getSpaceBetweenBubbles() {
        float widthForDisplayingBubbles = getWidthForDisplayingBubbles() - ((((float) this.mExpandedViewPadding) + this.mLauncherGridDiff) * 2.0f);
        int i = this.mBubblesMaxRendered;
        return (widthForDisplayingBubbles - (((float) i) * this.mBubbleSizePx)) / ((float) (i - 1));
    }
}
