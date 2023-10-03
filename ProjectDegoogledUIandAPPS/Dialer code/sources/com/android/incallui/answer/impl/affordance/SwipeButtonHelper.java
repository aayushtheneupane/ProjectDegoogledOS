package com.android.incallui.answer.impl.affordance;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.android.dialer.R;
import com.android.incallui.answer.impl.utils.FlingAnimationUtils;
import com.android.incallui.answer.impl.utils.Interpolators;

public class SwipeButtonHelper {
    /* access modifiers changed from: private */
    public final Callback callback;
    private final Context context;
    private FlingAnimationUtils flingAnimationUtils;
    private AnimatorListenerAdapter flingEndListener = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator animator) {
            Animator unused = SwipeButtonHelper.this.swipeAnimator = null;
            boolean unused2 = SwipeButtonHelper.this.swipingInProgress = false;
            View unused3 = SwipeButtonHelper.this.targetedView = null;
        }
    };
    private int hintGrowAmount;
    private float initialTouchX;
    private float initialTouchY;
    private SwipeButtonView leftIcon;
    private int minBackgroundRadius;
    private int minFlingVelocity;
    private int minTranslationAmount;
    private boolean motionCancelled;
    private SwipeButtonView rightIcon;
    /* access modifiers changed from: private */
    public Animator swipeAnimator;
    /* access modifiers changed from: private */
    public boolean swipingInProgress;
    /* access modifiers changed from: private */
    public View targetedView;
    private int touchSlop;
    private boolean touchSlopExeeded;
    private int touchTargetSize;
    /* access modifiers changed from: private */
    public float translation;
    private float translationOnDown;
    private VelocityTracker velocityTracker;

    private class AnimationEndRunnable implements Runnable {
        private final boolean rightPage;

        public AnimationEndRunnable(boolean z) {
            this.rightPage = z;
        }

        public void run() {
            SwipeButtonHelper.this.callback.onAnimationToSideEnded(this.rightPage);
        }
    }

    public interface Callback {
        float getAffordanceFalsingFactor();

        SwipeButtonView getLeftIcon();

        View getLeftPreview();

        float getMaxTranslationDistance();

        SwipeButtonView getRightIcon();

        View getRightPreview();

        void onAnimationToSideEnded(boolean z);

        void onAnimationToSideStarted(boolean z, float f, float f2);

        void onIconClicked(boolean z);

        void onSwipingAborted();

        void onSwipingStarted(boolean z);
    }

    public SwipeButtonHelper(Callback callback2, Context context2) {
        this.context = context2;
        this.callback = callback2;
        init();
    }

    static /* synthetic */ void access$400(SwipeButtonHelper swipeButtonHelper, boolean z, final Runnable runnable) {
        ValueAnimator animatorToRadius = swipeButtonHelper.getAnimatorToRadius(z, 0);
        if (animatorToRadius != null) {
            animatorToRadius.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    Animator unused = SwipeButtonHelper.this.swipeAnimator = null;
                    View unused2 = SwipeButtonHelper.this.targetedView = null;
                    Runnable runnable = runnable;
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            });
            animatorToRadius.setInterpolator(Interpolators.FAST_OUT_LINEAR_IN);
            animatorToRadius.setDuration(350);
            animatorToRadius.setStartDelay(500);
            animatorToRadius.start();
            swipeButtonHelper.swipeAnimator = animatorToRadius;
        } else if (runnable != null) {
            runnable.run();
        }
    }

    static /* synthetic */ float access$500(SwipeButtonHelper swipeButtonHelper, float f) {
        float f2 = (f - ((float) swipeButtonHelper.minBackgroundRadius)) / 0.25f;
        if (f2 > 0.0f) {
            return f2 + ((float) swipeButtonHelper.touchSlop);
        }
        return 0.0f;
    }

    static /* synthetic */ void access$700(SwipeButtonHelper swipeButtonHelper, SwipeButtonView swipeButtonView) {
        float abs = Math.abs(swipeButtonHelper.translation) / ((float) swipeButtonHelper.getMinTranslationAmount());
        float max = Math.max(0.0f, 1.0f - abs);
        SwipeButtonView swipeButtonView2 = swipeButtonHelper.rightIcon;
        if (swipeButtonView == swipeButtonView2) {
            swipeButtonView2 = swipeButtonHelper.leftIcon;
        }
        swipeButtonHelper.updateIconAlpha(swipeButtonView, (swipeButtonView.getRestingAlpha() * max) + abs, false);
        if (swipeButtonView2 != null) {
            swipeButtonHelper.updateIconAlpha(swipeButtonView2, swipeButtonView2.getRestingAlpha() * max, false);
        }
    }

    private void cancelAnimation() {
        Animator animator = this.swipeAnimator;
        if (animator != null) {
            animator.cancel();
        }
    }

    private void endMotion(boolean z, float f, float f2) {
        float f3;
        if (this.swipingInProgress) {
            VelocityTracker velocityTracker2 = this.velocityTracker;
            if (velocityTracker2 == null) {
                f3 = 0.0f;
            } else {
                velocityTracker2.computeCurrentVelocity(1000);
                float xVelocity = this.velocityTracker.getXVelocity();
                float yVelocity = this.velocityTracker.getYVelocity();
                float f4 = f - this.initialTouchX;
                float f5 = f2 - this.initialTouchY;
                f3 = ((yVelocity * f5) + (xVelocity * f4)) / ((float) Math.hypot((double) f4, (double) f5));
                if (this.targetedView == this.rightIcon) {
                    f3 = -f3;
                }
            }
            boolean isBelowFalsingThreshold = isBelowFalsingThreshold();
            boolean z2 = this.translation * f3 < 0.0f;
            boolean z3 = isBelowFalsingThreshold | (Math.abs(f3) > ((float) this.minFlingVelocity) && z2);
            if (z2 ^ z3) {
                f3 = 0.0f;
            }
            boolean z4 = z3 || z;
            boolean z5 = this.translation < 0.0f;
            float maxTranslationDistance = this.callback.getMaxTranslationDistance();
            if (z5) {
                maxTranslationDistance = -maxTranslationDistance;
            }
            if (z4) {
                maxTranslationDistance = 0.0f;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.translation, maxTranslationDistance});
            this.flingAnimationUtils.apply(ofFloat, this.translation, maxTranslationDistance, f3);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float unused = SwipeButtonHelper.this.translation = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                }
            });
            ofFloat.addListener(this.flingEndListener);
            if (!z4) {
                float f6 = 0.375f * f3;
                AnimationEndRunnable animationEndRunnable = new AnimationEndRunnable(z5);
                SwipeButtonView swipeButtonView = z5 ? this.rightIcon : this.leftIcon;
                if (swipeButtonView != null) {
                    swipeButtonView.finishAnimation(f6, animationEndRunnable);
                }
                this.callback.onAnimationToSideStarted(z5, this.translation, f3);
            } else {
                reset(true);
            }
            ofFloat.start();
            this.swipeAnimator = ofFloat;
            if (z4) {
                this.callback.onSwipingAborted();
            }
        } else {
            this.targetedView = null;
        }
        VelocityTracker velocityTracker3 = this.velocityTracker;
        if (velocityTracker3 != null) {
            velocityTracker3.recycle();
            this.velocityTracker = null;
        }
    }

    private ValueAnimator getAnimatorToRadius(final boolean z, int i) {
        final SwipeButtonView swipeButtonView = z ? this.rightIcon : this.leftIcon;
        if (swipeButtonView == null) {
            return null;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{swipeButtonView.getCircleRadius(), (float) i});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                swipeButtonView.setCircleRadiusWithoutAnimation(floatValue);
                float access$500 = SwipeButtonHelper.access$500(SwipeButtonHelper.this, floatValue);
                SwipeButtonHelper swipeButtonHelper = SwipeButtonHelper.this;
                if (z) {
                    access$500 = -access$500;
                }
                float unused = swipeButtonHelper.translation = access$500;
                SwipeButtonHelper.access$700(SwipeButtonHelper.this, swipeButtonView);
            }
        });
        return ofFloat;
    }

    private int getMinTranslationAmount() {
        return (int) (((float) this.minTranslationAmount) * this.callback.getAffordanceFalsingFactor());
    }

    private void initDimens() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this.context);
        this.touchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.minFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.minTranslationAmount = this.context.getResources().getDimensionPixelSize(R.dimen.answer_min_swipe_amount);
        this.minBackgroundRadius = this.context.getResources().getDimensionPixelSize(R.dimen.answer_affordance_min_background_radius);
        this.touchTargetSize = this.context.getResources().getDimensionPixelSize(R.dimen.answer_affordance_touch_target_size);
        this.hintGrowAmount = this.context.getResources().getDimensionPixelSize(R.dimen.hint_grow_amount_sideways);
        this.flingAnimationUtils = new FlingAnimationUtils(this.context, 0.4f);
    }

    private void initIcons() {
        this.leftIcon = this.callback.getLeftIcon();
        this.rightIcon = this.callback.getRightIcon();
        SwipeButtonView swipeButtonView = this.leftIcon;
        if (swipeButtonView != null) {
            swipeButtonView.setPreviewView(this.callback.getLeftPreview());
        }
        SwipeButtonView swipeButtonView2 = this.rightIcon;
        if (swipeButtonView2 != null) {
            swipeButtonView2.setPreviewView(this.callback.getRightPreview());
        }
    }

    private boolean isBelowFalsingThreshold() {
        return Math.abs(this.translation) < Math.abs(this.translationOnDown) + ((float) getMinTranslationAmount());
    }

    private boolean isOnIcon(View view, float f, float f2) {
        return Math.hypot((double) (f - ((((float) view.getWidth()) / 2.0f) + view.getX())), (double) (f2 - ((((float) view.getHeight()) / 2.0f) + view.getY()))) <= ((double) (this.touchTargetSize / 2));
    }

    private boolean leftSwipePossible() {
        SwipeButtonView swipeButtonView = this.leftIcon;
        return swipeButtonView != null && swipeButtonView.getVisibility() == 0;
    }

    private boolean rightSwipePossible() {
        SwipeButtonView swipeButtonView = this.rightIcon;
        return swipeButtonView != null && swipeButtonView.getVisibility() == 0;
    }

    private void setTranslation(float f, boolean z, boolean z2) {
        float f2;
        SwipeButtonView swipeButtonView = this.rightIcon;
        float f3 = 0.0f;
        if (swipeButtonView != null && swipeButtonView.getVisibility() == 0) {
            f2 = f;
        } else {
            f2 = Math.max(0.0f, f);
        }
        SwipeButtonView swipeButtonView2 = this.leftIcon;
        if (!(swipeButtonView2 != null && swipeButtonView2.getVisibility() == 0)) {
            f2 = Math.min(0.0f, f2);
        }
        float f4 = f2;
        float abs = Math.abs(f4);
        if (f4 != this.translation || z) {
            int i = (f4 > 0.0f ? 1 : (f4 == 0.0f ? 0 : -1));
            SwipeButtonView swipeButtonView3 = i > 0 ? this.leftIcon : this.rightIcon;
            SwipeButtonView swipeButtonView4 = i > 0 ? this.rightIcon : this.leftIcon;
            float minTranslationAmount2 = abs / ((float) getMinTranslationAmount());
            float max = Math.max(1.0f - minTranslationAmount2, 0.0f);
            boolean z3 = z && z2;
            boolean z4 = z && !z2;
            float f5 = (float) this.touchSlop;
            if (abs > f5) {
                f3 = ((abs - f5) * 0.25f) + ((float) this.minBackgroundRadius);
            }
            boolean z5 = z && isBelowFalsingThreshold();
            if (swipeButtonView3 != null) {
                if (!z) {
                    updateIcon(swipeButtonView3, f3, minTranslationAmount2 + (swipeButtonView3.getRestingAlpha() * max), false, false, false, false);
                } else {
                    updateIcon(swipeButtonView3, 0.0f, swipeButtonView3.getRestingAlpha() * max, z3, z5, false, z4);
                }
            }
            if (swipeButtonView4 != null) {
                updateIcon(swipeButtonView4, 0.0f, swipeButtonView4.getRestingAlpha() * max, z3, z5, false, z4);
            }
            this.translation = f4;
        }
    }

    private void updateIcon(SwipeButtonView swipeButtonView, float f, float f2, boolean z, boolean z2, boolean z3, boolean z4) {
        if (swipeButtonView != null) {
            if (swipeButtonView.getVisibility() == 0 || z3) {
                if (z4) {
                    swipeButtonView.setCircleRadiusWithoutAnimation(f);
                } else {
                    swipeButtonView.setCircleRadius(f, z2);
                }
                updateIconAlpha(swipeButtonView, f2, z);
            }
        }
    }

    private void updateIconAlpha(SwipeButtonView swipeButtonView, float f, boolean z) {
        float min = Math.min(((f / swipeButtonView.getRestingAlpha()) * 0.2f) + 0.8f, 1.5f);
        swipeButtonView.setImageAlpha(Math.min(1.0f, f), z);
        swipeButtonView.setImageScale(min, z);
    }

    public void animateHideLeftRightIcon() {
        cancelAnimation();
        updateIcon(this.rightIcon, 0.0f, 0.0f, true, false, false, false);
        updateIcon(this.leftIcon, 0.0f, 0.0f, true, false, false, false);
    }

    public void init() {
        initIcons();
        SwipeButtonView swipeButtonView = this.leftIcon;
        updateIcon(swipeButtonView, 0.0f, swipeButtonView != null ? swipeButtonView.getRestingAlpha() : 0.0f, false, false, true, false);
        SwipeButtonView swipeButtonView2 = this.rightIcon;
        updateIcon(swipeButtonView2, 0.0f, swipeButtonView2 != null ? swipeButtonView2.getRestingAlpha() : 0.0f, false, false, true, false);
        initDimens();
    }

    public void onConfigurationChanged() {
        initDimens();
        initIcons();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        SwipeButtonView swipeButtonView;
        View view;
        boolean z;
        float f;
        int actionMasked = motionEvent.getActionMasked();
        boolean z2 = false;
        if (this.motionCancelled && actionMasked != 0) {
            return false;
        }
        float y = motionEvent.getY();
        float x = motionEvent.getX();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                z = true;
            } else if (actionMasked == 2) {
                VelocityTracker velocityTracker2 = this.velocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.addMovement(motionEvent);
                }
                float hypot = (float) Math.hypot((double) (x - this.initialTouchX), (double) (y - this.initialTouchY));
                if (!this.touchSlopExeeded && hypot > ((float) this.touchSlop)) {
                    this.touchSlopExeeded = true;
                }
                if (this.swipingInProgress) {
                    if (this.targetedView == this.rightIcon) {
                        f = Math.min(0.0f, this.translationOnDown - hypot);
                    } else {
                        f = Math.max(0.0f, this.translationOnDown + hypot);
                    }
                    setTranslation(f, false, false);
                }
            } else if (actionMasked == 3) {
                z = false;
            } else if (actionMasked == 5) {
                this.motionCancelled = true;
                endMotion(true, x, y);
            }
            if (this.targetedView == this.rightIcon) {
                z2 = true;
            }
            VelocityTracker velocityTracker3 = this.velocityTracker;
            if (velocityTracker3 != null) {
                velocityTracker3.addMovement(motionEvent);
            }
            endMotion(!z, x, y);
            if (!this.touchSlopExeeded && z) {
                this.callback.onIconClicked(z2);
            }
        } else {
            if (!leftSwipePossible() || !isOnIcon(this.leftIcon, x, y)) {
                swipeButtonView = (!rightSwipePossible() || !isOnIcon(this.rightIcon, x, y)) ? null : this.rightIcon;
            } else {
                swipeButtonView = this.leftIcon;
            }
            if (swipeButtonView == null || !((view = this.targetedView) == null || view == swipeButtonView)) {
                this.motionCancelled = true;
                return false;
            }
            if (this.targetedView != null) {
                cancelAnimation();
            } else {
                this.touchSlopExeeded = false;
            }
            this.callback.onSwipingStarted(swipeButtonView == this.rightIcon);
            this.swipingInProgress = true;
            this.targetedView = swipeButtonView;
            this.initialTouchX = x;
            this.initialTouchY = y;
            this.translationOnDown = this.translation;
            VelocityTracker velocityTracker4 = this.velocityTracker;
            if (velocityTracker4 != null) {
                velocityTracker4.recycle();
            }
            this.velocityTracker = VelocityTracker.obtain();
            VelocityTracker velocityTracker5 = this.velocityTracker;
            if (velocityTracker5 != null) {
                velocityTracker5.addMovement(motionEvent);
            }
            this.motionCancelled = false;
        }
        return true;
    }

    public void reset(boolean z) {
        cancelAnimation();
        setTranslation(0.0f, true, z);
        this.motionCancelled = true;
        if (this.swipingInProgress) {
            this.callback.onSwipingAborted();
            this.swipingInProgress = false;
        }
    }

    public void startHintAnimation(final boolean z, final Runnable runnable) {
        cancelAnimation();
        SwipeButtonView swipeButtonView = z ? this.rightIcon : this.leftIcon;
        ValueAnimator animatorToRadius = getAnimatorToRadius(z, this.hintGrowAmount);
        if (animatorToRadius != null) {
            animatorToRadius.addListener(new AnimatorListenerAdapter() {
                private boolean cancelled;

                public void onAnimationCancel(Animator animator) {
                    this.cancelled = true;
                }

                public void onAnimationEnd(Animator animator) {
                    if (this.cancelled) {
                        Animator unused = SwipeButtonHelper.this.swipeAnimator = null;
                        View unused2 = SwipeButtonHelper.this.targetedView = null;
                        Runnable runnable = runnable;
                        if (runnable != null) {
                            runnable.run();
                            return;
                        }
                        return;
                    }
                    SwipeButtonHelper.access$400(SwipeButtonHelper.this, z, runnable);
                }
            });
            animatorToRadius.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
            animatorToRadius.setDuration(200);
            animatorToRadius.start();
            this.swipeAnimator = animatorToRadius;
            this.targetedView = swipeButtonView;
        } else if (runnable != null) {
            runnable.run();
        }
    }
}
