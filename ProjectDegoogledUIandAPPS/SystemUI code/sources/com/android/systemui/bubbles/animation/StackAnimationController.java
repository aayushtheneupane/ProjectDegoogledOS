package com.android.systemui.bubbles.animation;

import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.bubbles.animation.PhysicsAnimationLayout;
import com.google.android.collect.Sets;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

public class StackAnimationController extends PhysicsAnimationLayout.PhysicsAnimationController {
    private int mBubbleIconBitmapSize;
    private int mBubbleOffscreen;
    private int mBubblePaddingTop;
    private int mBubbleSize;
    private boolean mFirstBubbleSpringingToTouch = false;
    private float mImeHeight = 0.0f;
    private boolean mIsMovingFromFlinging = false;
    private float mPreImeY = Float.MIN_VALUE;
    private PointF mRestingStackPosition;
    private boolean mStackMovedToStartPosition = false;
    private float mStackOffset;
    private PointF mStackPosition = new PointF(-1.0f, -1.0f);
    private HashMap<DynamicAnimation.ViewProperty, DynamicAnimation> mStackPositionAnimations = new HashMap<>();
    private int mStackStartingVerticalOffset;
    private float mStatusBarHeight;
    private boolean mWithinDismissTarget = false;

    public void moveFirstBubbleWithStackFollowing(float f, float f2) {
        this.mPreImeY = Float.MIN_VALUE;
        moveFirstBubbleWithStackFollowing(DynamicAnimation.TRANSLATION_X, f);
        moveFirstBubbleWithStackFollowing(DynamicAnimation.TRANSLATION_Y, f2);
        this.mIsMovingFromFlinging = false;
    }

    public PointF getStackPosition() {
        return this.mStackPosition;
    }

    public boolean isStackOnLeftSide() {
        if (this.mLayout == null || !isStackPositionSet() || this.mStackPosition.x + ((float) (this.mBubbleIconBitmapSize / 2)) >= ((float) (this.mLayout.getWidth() / 2))) {
            return false;
        }
        return true;
    }

    public void springStack(float f, float f2) {
        DynamicAnimation.ViewProperty viewProperty = DynamicAnimation.TRANSLATION_X;
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(750.0f);
        springForce.setDampingRatio(0.85f);
        springFirstBubbleWithStackFollowing(viewProperty, springForce, 0.0f, f, new Runnable[0]);
        DynamicAnimation.ViewProperty viewProperty2 = DynamicAnimation.TRANSLATION_Y;
        SpringForce springForce2 = new SpringForce();
        springForce2.setStiffness(750.0f);
        springForce2.setDampingRatio(0.85f);
        springFirstBubbleWithStackFollowing(viewProperty2, springForce2, 0.0f, f2, new Runnable[0]);
    }

    public float flingStackThenSpringToEdge(float f, float f2, float f3) {
        float f4;
        boolean z = !(((f - ((float) (this.mBubbleIconBitmapSize / 2))) > ((float) (this.mLayout.getWidth() / 2)) ? 1 : ((f - ((float) (this.mBubbleIconBitmapSize / 2))) == ((float) (this.mLayout.getWidth() / 2)) ? 0 : -1)) < 0) ? f2 < -750.0f : f2 < 750.0f;
        RectF allowableStackPositionRegion = getAllowableStackPositionRegion();
        float f5 = z ? allowableStackPositionRegion.left : allowableStackPositionRegion.right;
        PhysicsAnimationLayout physicsAnimationLayout = this.mLayout;
        if (!(physicsAnimationLayout == null || physicsAnimationLayout.getChildCount() == 0)) {
            float f6 = (f5 - f) * 9.24f;
            if (z) {
                f4 = Math.min(f6, f2);
            } else {
                f4 = Math.max(f6, f2);
            }
            DynamicAnimation.ViewProperty viewProperty = DynamicAnimation.TRANSLATION_X;
            SpringForce springForce = new SpringForce();
            springForce.setStiffness(750.0f);
            springForce.setDampingRatio(0.85f);
            flingThenSpringFirstBubbleWithStackFollowing(viewProperty, f4, 2.2f, springForce, Float.valueOf(f5));
            DynamicAnimation.ViewProperty viewProperty2 = DynamicAnimation.TRANSLATION_Y;
            SpringForce springForce2 = new SpringForce();
            springForce2.setStiffness(750.0f);
            springForce2.setDampingRatio(0.85f);
            flingThenSpringFirstBubbleWithStackFollowing(viewProperty2, f3, 2.2f, springForce2, (Float) null);
            this.mFirstBubbleSpringingToTouch = false;
            this.mIsMovingFromFlinging = true;
        }
        return f5;
    }

    public PointF getStackPositionAlongNearestHorizontalEdge() {
        PointF stackPosition = getStackPosition();
        boolean isFirstChildXLeftOfCenter = this.mLayout.isFirstChildXLeftOfCenter(stackPosition.x);
        RectF allowableStackPositionRegion = getAllowableStackPositionRegion();
        stackPosition.x = isFirstChildXLeftOfCenter ? allowableStackPositionRegion.left : allowableStackPositionRegion.right;
        return stackPosition;
    }

    public void moveStackToSimilarPositionAfterRotation(boolean z, float f) {
        RectF allowableStackPositionRegion = getAllowableStackPositionRegion();
        setStackPosition(new PointF(z ? allowableStackPositionRegion.left : allowableStackPositionRegion.right, ((allowableStackPositionRegion.bottom - allowableStackPositionRegion.top) * f) + allowableStackPositionRegion.top));
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("StackAnimationController state:");
        printWriter.print("  isActive:             ");
        printWriter.println(isActiveController());
        printWriter.print("  restingStackPos:      ");
        PointF pointF = this.mRestingStackPosition;
        printWriter.println(pointF != null ? pointF.toString() : "null");
        printWriter.print("  currentStackPos:      ");
        printWriter.println(this.mStackPosition.toString());
        printWriter.print("  isMovingFromFlinging: ");
        printWriter.println(this.mIsMovingFromFlinging);
        printWriter.print("  withinDismiss:        ");
        printWriter.println(this.mWithinDismissTarget);
        printWriter.print("  firstBubbleSpringing: ");
        printWriter.println(this.mFirstBubbleSpringingToTouch);
    }

    /* access modifiers changed from: protected */
    public void flingThenSpringFirstBubbleWithStackFollowing(DynamicAnimation.ViewProperty viewProperty, float f, float f2, SpringForce springForce, Float f3) {
        float f4;
        float f5;
        DynamicAnimation.ViewProperty viewProperty2 = viewProperty;
        Log.d("Bubbs.StackCtrl", String.format("Flinging %s.", new Object[]{PhysicsAnimationLayout.getReadablePropertyName(viewProperty)}));
        StackPositionProperty stackPositionProperty = new StackPositionProperty(viewProperty);
        float value = stackPositionProperty.getValue(this);
        RectF allowableStackPositionRegion = getAllowableStackPositionRegion();
        if (viewProperty.equals(DynamicAnimation.TRANSLATION_X)) {
            f4 = allowableStackPositionRegion.left;
        } else {
            f4 = allowableStackPositionRegion.top;
        }
        float f6 = f4;
        if (viewProperty.equals(DynamicAnimation.TRANSLATION_X)) {
            f5 = allowableStackPositionRegion.right;
        } else {
            f5 = allowableStackPositionRegion.bottom;
        }
        float f7 = f5;
        FlingAnimation flingAnimation = new FlingAnimation(this, stackPositionProperty);
        float f8 = f2;
        flingAnimation.setFriction(f2);
        float f9 = f;
        flingAnimation.setStartVelocity(f);
        flingAnimation.setMinValue(Math.min(value, f6));
        flingAnimation.setMaxValue(Math.max(value, f7));
        flingAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener(viewProperty, springForce, f3, f6, f7) {
            private final /* synthetic */ DynamicAnimation.ViewProperty f$1;
            private final /* synthetic */ SpringForce f$2;
            private final /* synthetic */ Float f$3;
            private final /* synthetic */ float f$4;
            private final /* synthetic */ float f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                StackAnimationController.this.mo9841xc55c386e(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, dynamicAnimation, z, f, f2);
            }
        });
        cancelStackPositionAnimation(viewProperty);
        this.mStackPositionAnimations.put(viewProperty, flingAnimation);
        flingAnimation.start();
    }

    /* renamed from: lambda$flingThenSpringFirstBubbleWithStackFollowing$0$StackAnimationController */
    public /* synthetic */ void mo9841xc55c386e(DynamicAnimation.ViewProperty viewProperty, SpringForce springForce, Float f, float f2, float f3, DynamicAnimation dynamicAnimation, boolean z, float f4, float f5) {
        float f6;
        if (!z) {
            this.mRestingStackPosition = new PointF();
            this.mRestingStackPosition.set(this.mStackPosition);
            if (f != null) {
                f6 = f.floatValue();
            } else {
                f6 = Math.max(f2, Math.min(f3, f4));
            }
            springFirstBubbleWithStackFollowing(viewProperty, springForce, f5, f6, new Runnable[0]);
        }
    }

    public void cancelStackPositionAnimations() {
        cancelStackPositionAnimation(DynamicAnimation.TRANSLATION_X);
        cancelStackPositionAnimation(DynamicAnimation.TRANSLATION_Y);
        removeEndActionForProperty(DynamicAnimation.TRANSLATION_X);
        removeEndActionForProperty(DynamicAnimation.TRANSLATION_Y);
    }

    public void setImeHeight(int i) {
        this.mImeHeight = (float) i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void animateForImeVisibility(boolean r9) {
        /*
            r8 = this;
            android.graphics.RectF r0 = r8.getAllowableStackPositionRegion()
            float r0 = r0.bottom
            r1 = 1
            if (r9 == 0) goto L_0x001a
            android.graphics.PointF r9 = r8.mStackPosition
            float r9 = r9.y
            int r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0024
            float r2 = r8.mPreImeY
            int r2 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r2 != 0) goto L_0x0024
            r8.mPreImeY = r9
            goto L_0x0022
        L_0x001a:
            float r0 = r8.mPreImeY
            int r9 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r9 <= 0) goto L_0x0024
            r8.mPreImeY = r1
        L_0x0022:
            r6 = r0
            goto L_0x0025
        L_0x0024:
            r6 = r1
        L_0x0025:
            int r9 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r9 <= 0) goto L_0x003d
            androidx.dynamicanimation.animation.DynamicAnimation$ViewProperty r3 = androidx.dynamicanimation.animation.DynamicAnimation.TRANSLATION_Y
            r9 = 0
            androidx.dynamicanimation.animation.SpringForce r4 = r8.getSpringForce(r3, r9)
            r9 = 1128792064(0x43480000, float:200.0)
            r4.setStiffness(r9)
            r5 = 0
            r9 = 0
            java.lang.Runnable[] r7 = new java.lang.Runnable[r9]
            r2 = r8
            r2.springFirstBubbleWithStackFollowing(r3, r4, r5, r6, r7)
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bubbles.animation.StackAnimationController.animateForImeVisibility(boolean):void");
    }

    public RectF getAllowableStackPositionRegion() {
        WindowInsets rootWindowInsets = this.mLayout.getRootWindowInsets();
        RectF rectF = new RectF();
        if (rootWindowInsets != null) {
            int i = 0;
            rectF.left = (float) ((-this.mBubbleOffscreen) + Math.max(rootWindowInsets.getSystemWindowInsetLeft(), rootWindowInsets.getDisplayCutout() != null ? rootWindowInsets.getDisplayCutout().getSafeInsetLeft() : 0));
            rectF.right = (float) (((this.mLayout.getWidth() - this.mBubbleSize) + this.mBubbleOffscreen) - Math.max(rootWindowInsets.getSystemWindowInsetRight(), rootWindowInsets.getDisplayCutout() != null ? rootWindowInsets.getDisplayCutout().getSafeInsetRight() : 0));
            float f = 0.0f;
            rectF.top = ((float) this.mBubblePaddingTop) + Math.max(this.mStatusBarHeight, rootWindowInsets.getDisplayCutout() != null ? (float) rootWindowInsets.getDisplayCutout().getSafeInsetTop() : 0.0f);
            int height = this.mLayout.getHeight() - this.mBubbleSize;
            int i2 = this.mBubblePaddingTop;
            float f2 = (float) (height - i2);
            float f3 = this.mImeHeight;
            if (f3 > Float.MIN_VALUE) {
                f = f3 + ((float) i2);
            }
            float f4 = f2 - f;
            int systemWindowInsetBottom = rootWindowInsets.getSystemWindowInsetBottom();
            if (rootWindowInsets.getDisplayCutout() != null) {
                i = rootWindowInsets.getDisplayCutout().getSafeInsetBottom();
            }
            rectF.bottom = f4 - ((float) Math.max(systemWindowInsetBottom, i));
        }
        return rectF;
    }

    public void moveStackFromTouch(float f, float f2) {
        if (this.mFirstBubbleSpringingToTouch) {
            SpringAnimation springAnimation = (SpringAnimation) this.mStackPositionAnimations.get(DynamicAnimation.TRANSLATION_X);
            SpringAnimation springAnimation2 = (SpringAnimation) this.mStackPositionAnimations.get(DynamicAnimation.TRANSLATION_Y);
            if (springAnimation.isRunning() || springAnimation2.isRunning()) {
                springAnimation.animateToFinalPosition(f);
                springAnimation2.animateToFinalPosition(f2);
            } else {
                this.mFirstBubbleSpringingToTouch = false;
            }
        }
        if (!this.mFirstBubbleSpringingToTouch && !this.mWithinDismissTarget) {
            moveFirstBubbleWithStackFollowing(f, f2);
        }
    }

    public void demagnetizeFromDismissToPoint(float f, float f2, float f3, float f4) {
        this.mWithinDismissTarget = false;
        this.mFirstBubbleSpringingToTouch = true;
        DynamicAnimation.ViewProperty viewProperty = DynamicAnimation.TRANSLATION_X;
        SpringForce springForce = new SpringForce();
        springForce.setDampingRatio(0.9f);
        springForce.setStiffness(12000.0f);
        springFirstBubbleWithStackFollowing(viewProperty, springForce, f3, f, new Runnable[0]);
        DynamicAnimation.ViewProperty viewProperty2 = DynamicAnimation.TRANSLATION_Y;
        SpringForce springForce2 = new SpringForce();
        springForce2.setDampingRatio(0.9f);
        springForce2.setStiffness(12000.0f);
        springFirstBubbleWithStackFollowing(viewProperty2, springForce2, f4, f2, new Runnable[0]);
    }

    public void magnetToDismiss(float f, float f2, float f3, Runnable runnable) {
        this.mWithinDismissTarget = true;
        this.mFirstBubbleSpringingToTouch = false;
        DynamicAnimation.ViewProperty viewProperty = DynamicAnimation.TRANSLATION_X;
        SpringForce springForce = new SpringForce();
        springForce.setDampingRatio(0.75f);
        springForce.setStiffness(1500.0f);
        springFirstBubbleWithStackFollowing(viewProperty, springForce, f, (((float) this.mLayout.getWidth()) / 2.0f) - (((float) this.mBubbleIconBitmapSize) / 2.0f), new Runnable[0]);
        DynamicAnimation.ViewProperty viewProperty2 = DynamicAnimation.TRANSLATION_Y;
        SpringForce springForce2 = new SpringForce();
        springForce2.setDampingRatio(0.75f);
        springForce2.setStiffness(1500.0f);
        springFirstBubbleWithStackFollowing(viewProperty2, springForce2, f2, f3, runnable);
    }

    public void implodeStack(Runnable runnable) {
        PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChildAtIndex = animationForChildAtIndex(0);
        animationForChildAtIndex.scaleX(0.5f, new Runnable[0]);
        animationForChildAtIndex.scaleY(0.5f, new Runnable[0]);
        animationForChildAtIndex.alpha(0.0f, new Runnable[0]);
        animationForChildAtIndex.withDampingRatio(1.0f);
        animationForChildAtIndex.withStiffness(10000.0f);
        animationForChildAtIndex.start(new Runnable(runnable) {
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StackAnimationController.this.lambda$implodeStack$1$StackAnimationController(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$implodeStack$1$StackAnimationController(Runnable runnable) {
        runnable.run();
        this.mWithinDismissTarget = false;
    }

    /* access modifiers changed from: protected */
    public void springFirstBubbleWithStackFollowing(DynamicAnimation.ViewProperty viewProperty, SpringForce springForce, float f, float f2, Runnable... runnableArr) {
        if (this.mLayout.getChildCount() != 0) {
            Log.d("Bubbs.StackCtrl", String.format("Springing %s to final position %f.", new Object[]{PhysicsAnimationLayout.getReadablePropertyName(viewProperty), Float.valueOf(f2)}));
            SpringAnimation springAnimation = new SpringAnimation(this, new StackPositionProperty(viewProperty));
            springAnimation.setSpring(springForce);
            springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener(runnableArr) {
                private final /* synthetic */ Runnable[] f$0;

                {
                    this.f$0 = r1;
                }

                public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                    StackAnimationController.lambda$springFirstBubbleWithStackFollowing$2(this.f$0, dynamicAnimation, z, f, f2);
                }
            });
            SpringAnimation springAnimation2 = springAnimation;
            springAnimation2.setStartVelocity(f);
            SpringAnimation springAnimation3 = springAnimation2;
            cancelStackPositionAnimation(viewProperty);
            this.mStackPositionAnimations.put(viewProperty, springAnimation3);
            springAnimation3.animateToFinalPosition(f2);
        }
    }

    static /* synthetic */ void lambda$springFirstBubbleWithStackFollowing$2(Runnable[] runnableArr, DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        if (runnableArr != null) {
            for (Runnable run : runnableArr) {
                run.run();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Set<DynamicAnimation.ViewProperty> getAnimatedProperties() {
        return Sets.newHashSet(new DynamicAnimation.ViewProperty[]{DynamicAnimation.TRANSLATION_X, DynamicAnimation.TRANSLATION_Y, DynamicAnimation.ALPHA, DynamicAnimation.SCALE_X, DynamicAnimation.SCALE_Y});
    }

    /* access modifiers changed from: package-private */
    public int getNextAnimationInChain(DynamicAnimation.ViewProperty viewProperty, int i) {
        if (viewProperty.equals(DynamicAnimation.TRANSLATION_X) || viewProperty.equals(DynamicAnimation.TRANSLATION_Y)) {
            return i + 1;
        }
        if (this.mWithinDismissTarget) {
            return i + 1;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public float getOffsetForChainedPropertyAnimation(DynamicAnimation.ViewProperty viewProperty) {
        if (!viewProperty.equals(DynamicAnimation.TRANSLATION_X) || this.mWithinDismissTarget) {
            return 0.0f;
        }
        return this.mLayout.isFirstChildXLeftOfCenter(this.mStackPosition.x) ? -this.mStackOffset : this.mStackOffset;
    }

    /* access modifiers changed from: package-private */
    public SpringForce getSpringForce(DynamicAnimation.ViewProperty viewProperty, View view) {
        SpringForce springForce = new SpringForce();
        springForce.setDampingRatio(0.9f);
        springForce.setStiffness(this.mIsMovingFromFlinging ? 20000.0f : 12000.0f);
        return springForce;
    }

    /* access modifiers changed from: package-private */
    public void onChildAdded(View view, int i) {
        if (!this.mWithinDismissTarget) {
            if (this.mLayout.getChildCount() == 1) {
                moveStackToStartPosition();
            } else if (isStackPositionSet() && this.mLayout.indexOfChild(view) == 0) {
                animateInBubble(view, i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onChildRemoved(View view, int i, Runnable runnable) {
        float offsetForChainedPropertyAnimation = getOffsetForChainedPropertyAnimation(DynamicAnimation.TRANSLATION_X);
        PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(view);
        animationForChild.alpha(0.0f, runnable);
        animationForChild.scaleX(1.15f, new Runnable[0]);
        animationForChild.scaleY(1.15f, new Runnable[0]);
        animationForChild.translationX(this.mStackPosition.x - ((-offsetForChainedPropertyAnimation) * 4.0f), new Runnable[0]);
        animationForChild.start(new Runnable[0]);
        if (this.mLayout.getChildCount() > 0) {
            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChildAtIndex = animationForChildAtIndex(0);
            animationForChildAtIndex.translationX(this.mStackPosition.x, new Runnable[0]);
            animationForChildAtIndex.start(new Runnable[0]);
            return;
        }
        this.mWithinDismissTarget = false;
    }

    /* access modifiers changed from: package-private */
    public void onChildReordered(View view, int i, int i2) {
        if (isStackPositionSet()) {
            setStackPosition(this.mStackPosition);
        }
    }

    /* access modifiers changed from: package-private */
    public void onActiveControllerForLayout(PhysicsAnimationLayout physicsAnimationLayout) {
        Resources resources = physicsAnimationLayout.getResources();
        this.mStackOffset = (float) resources.getDimensionPixelSize(C1775R$dimen.bubble_stack_offset);
        this.mBubbleSize = resources.getDimensionPixelSize(C1775R$dimen.individual_bubble_size);
        this.mBubbleIconBitmapSize = resources.getDimensionPixelSize(C1775R$dimen.bubble_icon_bitmap_size);
        this.mBubblePaddingTop = resources.getDimensionPixelSize(C1775R$dimen.bubble_padding_top);
        this.mBubbleOffscreen = resources.getDimensionPixelSize(C1775R$dimen.bubble_stack_offscreen);
        this.mStackStartingVerticalOffset = resources.getDimensionPixelSize(C1775R$dimen.bubble_stack_starting_offset_y);
        this.mStatusBarHeight = (float) resources.getDimensionPixelSize(17105434);
    }

    public void updateOrientation(int i) {
        PhysicsAnimationLayout physicsAnimationLayout = this.mLayout;
        if (physicsAnimationLayout != null) {
            Resources resources = physicsAnimationLayout.getContext().getResources();
            this.mBubblePaddingTop = resources.getDimensionPixelSize(C1775R$dimen.bubble_padding_top);
            this.mStatusBarHeight = (float) resources.getDimensionPixelSize(17105434);
        }
    }

    private void moveStackToStartPosition() {
        this.mLayout.setVisibility(4);
        this.mLayout.post(new Runnable() {
            public final void run() {
                StackAnimationController.this.lambda$moveStackToStartPosition$3$StackAnimationController();
            }
        });
    }

    public /* synthetic */ void lambda$moveStackToStartPosition$3$StackAnimationController() {
        PointF pointF = this.mRestingStackPosition;
        if (pointF == null) {
            pointF = getDefaultStartPosition();
        }
        setStackPosition(pointF);
        this.mStackMovedToStartPosition = true;
        this.mLayout.setVisibility(0);
        if (this.mLayout.getChildCount() > 0) {
            animateInBubble(this.mLayout.getChildAt(0), 0);
        }
    }

    /* access modifiers changed from: private */
    public void moveFirstBubbleWithStackFollowing(DynamicAnimation.ViewProperty viewProperty, float f) {
        if (viewProperty.equals(DynamicAnimation.TRANSLATION_X)) {
            this.mStackPosition.x = f;
        } else if (viewProperty.equals(DynamicAnimation.TRANSLATION_Y)) {
            this.mStackPosition.y = f;
        }
        if (this.mLayout.getChildCount() > 0) {
            viewProperty.setValue(this.mLayout.getChildAt(0), f);
            if (this.mLayout.getChildCount() > 1) {
                PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChildAtIndex = animationForChildAtIndex(1);
                animationForChildAtIndex.property(viewProperty, f + getOffsetForChainedPropertyAnimation(viewProperty), new Runnable[0]);
                animationForChildAtIndex.start(new Runnable[0]);
            }
        }
    }

    private void setStackPosition(PointF pointF) {
        Log.d("Bubbs.StackCtrl", String.format("Setting position to (%f, %f).", new Object[]{Float.valueOf(pointF.x), Float.valueOf(pointF.y)}));
        this.mStackPosition.set(pointF.x, pointF.y);
        if (isActiveController()) {
            this.mLayout.cancelAllAnimationsOfProperties(DynamicAnimation.TRANSLATION_X, DynamicAnimation.TRANSLATION_Y);
            cancelStackPositionAnimations();
            float offsetForChainedPropertyAnimation = getOffsetForChainedPropertyAnimation(DynamicAnimation.TRANSLATION_X);
            float offsetForChainedPropertyAnimation2 = getOffsetForChainedPropertyAnimation(DynamicAnimation.TRANSLATION_Y);
            for (int i = 0; i < this.mLayout.getChildCount(); i++) {
                float f = (float) i;
                this.mLayout.getChildAt(i).setTranslationX(pointF.x + (f * offsetForChainedPropertyAnimation));
                this.mLayout.getChildAt(i).setTranslationY(pointF.y + (f * offsetForChainedPropertyAnimation2));
            }
        }
    }

    private PointF getDefaultStartPosition() {
        return new PointF(getAllowableStackPositionRegion().right, getAllowableStackPositionRegion().top + ((float) this.mStackStartingVerticalOffset));
    }

    private boolean isStackPositionSet() {
        return this.mStackMovedToStartPosition;
    }

    private void animateInBubble(View view, int i) {
        if (isActiveController()) {
            float offsetForChainedPropertyAnimation = getOffsetForChainedPropertyAnimation(DynamicAnimation.TRANSLATION_X);
            view.setTranslationX(this.mStackPosition.x + (((float) i) * offsetForChainedPropertyAnimation));
            view.setTranslationY(this.mStackPosition.y);
            view.setScaleX(0.0f);
            view.setScaleY(0.0f);
            int i2 = i + 1;
            if (i2 < this.mLayout.getChildCount()) {
                PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChildAtIndex = animationForChildAtIndex(i2);
                animationForChildAtIndex.translationX(this.mStackPosition.x + (offsetForChainedPropertyAnimation * ((float) i2)), new Runnable[0]);
                animationForChildAtIndex.withStiffness(200.0f);
                animationForChildAtIndex.start(new Runnable[0]);
            }
            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(view);
            animationForChild.scaleX(1.0f, new Runnable[0]);
            animationForChild.scaleY(1.0f, new Runnable[0]);
            animationForChild.withStiffness(1000.0f);
            animationForChild.withStartDelay(this.mLayout.getChildCount() > 1 ? 25 : 0);
            animationForChild.start(new Runnable[0]);
        }
    }

    private void cancelStackPositionAnimation(DynamicAnimation.ViewProperty viewProperty) {
        if (this.mStackPositionAnimations.containsKey(viewProperty)) {
            this.mStackPositionAnimations.get(viewProperty).cancel();
        }
    }

    private class StackPositionProperty extends FloatPropertyCompat<StackAnimationController> {
        private final DynamicAnimation.ViewProperty mProperty;

        private StackPositionProperty(DynamicAnimation.ViewProperty viewProperty) {
            super(viewProperty.toString());
            this.mProperty = viewProperty;
        }

        public float getValue(StackAnimationController stackAnimationController) {
            if (StackAnimationController.this.mLayout.getChildCount() > 0) {
                return this.mProperty.getValue(StackAnimationController.this.mLayout.getChildAt(0));
            }
            return 0.0f;
        }

        public void setValue(StackAnimationController stackAnimationController, float f) {
            StackAnimationController.this.moveFirstBubbleWithStackFollowing(this.mProperty, f);
        }
    }
}
