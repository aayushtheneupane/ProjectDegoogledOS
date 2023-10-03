package com.android.systemui.statusbar.notification.row;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.android.systemui.C1774R$color;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.notification.FakeShadowView;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.phone.DoubleTapHelper;
import java.util.Objects;

public abstract class ActivatableNotificationView extends ExpandableOutlineView {
    private static final Interpolator ACTIVATE_INVERSE_ALPHA_INTERPOLATOR = new PathInterpolator(0.0f, 0.0f, 0.5f, 1.0f);
    private static final Interpolator ACTIVATE_INVERSE_INTERPOLATOR = new PathInterpolator(0.6f, 0.0f, 0.5f, 1.0f);
    private final AccessibilityManager mAccessibilityManager;
    private boolean mActivated;
    private float mAnimationTranslationY;
    /* access modifiers changed from: private */
    public float mAppearAnimationFraction = -1.0f;
    private RectF mAppearAnimationRect = new RectF();
    private float mAppearAnimationTranslation;
    private ValueAnimator mAppearAnimator;
    /* access modifiers changed from: private */
    public ObjectAnimator mBackgroundAnimator;
    /* access modifiers changed from: private */
    public ValueAnimator mBackgroundColorAnimator;
    /* access modifiers changed from: private */
    public NotificationBackgroundView mBackgroundDimmed;
    protected NotificationBackgroundView mBackgroundNormal;
    private ValueAnimator.AnimatorUpdateListener mBackgroundVisibilityUpdater = new ValueAnimator.AnimatorUpdateListener() {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ActivatableNotificationView activatableNotificationView = ActivatableNotificationView.this;
            activatableNotificationView.setNormalBackgroundVisibilityAmount(activatableNotificationView.mBackgroundNormal.getAlpha());
            ActivatableNotificationView activatableNotificationView2 = ActivatableNotificationView.this;
            float unused = activatableNotificationView2.mDimmedBackgroundFadeInAmount = activatableNotificationView2.mBackgroundDimmed.getAlpha();
        }
    };
    private float mBgAlpha = 1.0f;
    protected int mBgTint = 0;
    private boolean mBlockNextTouch;
    private Interpolator mCurrentAlphaInterpolator;
    private Interpolator mCurrentAppearInterpolator;
    private int mCurrentBackgroundTint;
    private boolean mDimmed;
    private int mDimmedAlpha;
    /* access modifiers changed from: private */
    public float mDimmedBackgroundFadeInAmount = -1.0f;
    private final DoubleTapHelper mDoubleTapHelper;
    private boolean mDrawingAppearAnimation;
    private FakeShadowView mFakeShadow;
    private final FalsingManager mFalsingManager = ((FalsingManager) Dependency.get(FalsingManager.class));
    private boolean mFirstInSection;
    private int mHeadsUpAddStartLocation;
    private float mHeadsUpLocation;
    private boolean mIsAppearing;
    private boolean mIsBelowSpeedBump;
    private boolean mIsHeadsUpAnimation;
    private boolean mLastInSection;
    private boolean mNeedsDimming;
    private float mNormalBackgroundVisibilityAmount;
    private int mNormalColor;
    protected int mNormalRippleColor;
    private OnActivatedListener mOnActivatedListener;
    private float mOverrideAmount;
    private int mOverrideTint;
    private boolean mShadowHidden;
    private final Interpolator mSlowOutFastInInterpolator = new PathInterpolator(0.8f, 0.0f, 0.6f, 1.0f);
    private final Interpolator mSlowOutLinearInInterpolator = new PathInterpolator(0.8f, 0.0f, 1.0f, 1.0f);
    /* access modifiers changed from: private */
    public int mStartTint;
    private final Runnable mTapTimeoutRunnable = new Runnable() {
        public void run() {
            ActivatableNotificationView.this.makeInactive(true);
        }
    };
    /* access modifiers changed from: private */
    public int mTargetTint;
    private int mTintedRippleColor;

    public interface OnActivatedListener {
        void onActivated(ActivatableNotificationView activatableNotificationView);

        void onActivationReset(ActivatableNotificationView activatableNotificationView);
    }

    /* access modifiers changed from: protected */
    public boolean disallowSingleClick(MotionEvent motionEvent) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract View getContentView();

    /* access modifiers changed from: protected */
    public boolean handleSlideBack() {
        return false;
    }

    public boolean isDimmable() {
        return true;
    }

    public boolean isHeadsUpAnimatingAway() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isInteractive() {
        return true;
    }

    public boolean isPinned() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onAppearAnimationFinished(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void onBelowSpeedBumpChanged() {
    }

    /* access modifiers changed from: protected */
    public boolean shouldHideBackground() {
        return false;
    }

    public ActivatableNotificationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setClipChildren(false);
        setClipToPadding(false);
        updateColors();
        this.mAccessibilityManager = AccessibilityManager.getInstance(this.mContext);
        C1156x6898a656 r2 = new DoubleTapHelper.ActivationListener() {
            public final void onActiveChanged(boolean z) {
                ActivatableNotificationView.this.lambda$new$0$ActivatableNotificationView(z);
            }
        };
        C1155xcd239a26 r3 = new DoubleTapHelper.DoubleTapListener() {
            public final boolean onDoubleTap() {
                return ActivatableNotificationView.this.lambda$new$1$ActivatableNotificationView();
            }
        };
        $$Lambda$ELEe9GisA3PeCbD7mpobFwmaM r4 = new DoubleTapHelper.SlideBackListener() {
            public final boolean onSlideBack() {
                return ActivatableNotificationView.this.handleSlideBack();
            }
        };
        FalsingManager falsingManager = this.mFalsingManager;
        Objects.requireNonNull(falsingManager);
        this.mDoubleTapHelper = new DoubleTapHelper(this, r2, r3, r4, new DoubleTapHelper.DoubleTapLogListener() {
            public final void onDoubleTapLog(boolean z, float f, float f2) {
                FalsingManager.this.onNotificationDoubleTap(z, f, f2);
            }
        });
        initDimens();
    }

    public /* synthetic */ void lambda$new$0$ActivatableNotificationView(boolean z) {
        if (z) {
            makeActive();
        } else {
            makeInactive(true);
        }
    }

    public /* synthetic */ boolean lambda$new$1$ActivatableNotificationView() {
        return super.performClick();
    }

    private void updateColors() {
        this.mNormalColor = this.mContext.getColor(C1774R$color.notification_material_background_color);
        this.mTintedRippleColor = this.mContext.getColor(C1774R$color.notification_ripple_tinted_color);
        this.mNormalRippleColor = this.mContext.getColor(C1774R$color.notification_ripple_untinted_color);
        this.mDimmedAlpha = Color.alpha(this.mContext.getColor(C1774R$color.notification_material_background_dimmed_color));
    }

    private void initDimens() {
        this.mHeadsUpAddStartLocation = getResources().getDimensionPixelSize(17105311);
    }

    public void onDensityOrFontScaleChanged() {
        super.onDensityOrFontScaleChanged();
        initDimens();
    }

    /* access modifiers changed from: protected */
    public void updateBackgroundColors() {
        updateColors();
        initBackground();
        updateBackgroundTint();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mBackgroundNormal = (NotificationBackgroundView) findViewById(C1777R$id.backgroundNormal);
        this.mFakeShadow = (FakeShadowView) findViewById(C1777R$id.fake_shadow);
        this.mShadowHidden = this.mFakeShadow.getVisibility() != 0;
        this.mBackgroundDimmed = (NotificationBackgroundView) findViewById(C1777R$id.backgroundDimmed);
        initBackground();
        updateBackground();
        updateBackgroundTint();
        updateOutlineAlpha();
    }

    /* access modifiers changed from: protected */
    public void initBackground() {
        this.mBackgroundNormal.setCustomBackground(C1776R$drawable.notification_material_bg);
        this.mBackgroundDimmed.setCustomBackground(C1776R$drawable.notification_material_bg_dim);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mNeedsDimming && motionEvent.getActionMasked() == 0 && disallowSingleClick(motionEvent) && !isTouchExplorationEnabled()) {
            if (!this.mActivated) {
                return true;
            }
            if (!this.mDoubleTapHelper.isWithinDoubleTapSlop(motionEvent)) {
                this.mBlockNextTouch = true;
                makeInactive(true);
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private boolean isTouchExplorationEnabled() {
        return this.mAccessibilityManager.isTouchExplorationEnabled();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mBlockNextTouch) {
            this.mBlockNextTouch = false;
            return false;
        } else if (!this.mNeedsDimming || isTouchExplorationEnabled() || !isInteractive()) {
            return super.onTouchEvent(motionEvent);
        } else {
            boolean z = this.mActivated;
            boolean handleTouchEventDimmed = handleTouchEventDimmed(motionEvent);
            if (!z || !handleTouchEventDimmed || motionEvent.getAction() != 1) {
                return handleTouchEventDimmed;
            }
            removeCallbacks(this.mTapTimeoutRunnable);
            return handleTouchEventDimmed;
        }
    }

    public void drawableHotspotChanged(float f, float f2) {
        if (!this.mDimmed) {
            this.mBackgroundNormal.drawableHotspotChanged(f, f2);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mDimmed) {
            this.mBackgroundDimmed.setState(getDrawableState());
        } else {
            this.mBackgroundNormal.setState(getDrawableState());
        }
    }

    public void setRippleAllowed(boolean z) {
        this.mBackgroundNormal.setPressedAllowed(z);
    }

    private boolean handleTouchEventDimmed(MotionEvent motionEvent) {
        if (this.mNeedsDimming && !this.mDimmed) {
            super.onTouchEvent(motionEvent);
        }
        return this.mDoubleTapHelper.onTouchEvent(motionEvent, getActualHeight());
    }

    public boolean performClick() {
        if (!this.mNeedsDimming || isTouchExplorationEnabled()) {
            return super.performClick();
        }
        return false;
    }

    private void makeActive() {
        this.mFalsingManager.onNotificationActive();
        startActivateAnimation(false);
        this.mActivated = true;
        OnActivatedListener onActivatedListener = this.mOnActivatedListener;
        if (onActivatedListener != null) {
            onActivatedListener.onActivated(this);
        }
    }

    private void startActivateAnimation(final boolean z) {
        Animator animator;
        Interpolator interpolator;
        Interpolator interpolator2;
        if (isAttachedToWindow() && isDimmable()) {
            int width = this.mBackgroundNormal.getWidth() / 2;
            int actualHeight = this.mBackgroundNormal.getActualHeight() / 2;
            float sqrt = (float) Math.sqrt((double) ((width * width) + (actualHeight * actualHeight)));
            if (z) {
                animator = ViewAnimationUtils.createCircularReveal(this.mBackgroundNormal, width, actualHeight, sqrt, 0.0f);
            } else {
                animator = ViewAnimationUtils.createCircularReveal(this.mBackgroundNormal, width, actualHeight, 0.0f, sqrt);
            }
            this.mBackgroundNormal.setVisibility(0);
            if (!z) {
                interpolator2 = Interpolators.LINEAR_OUT_SLOW_IN;
                interpolator = interpolator2;
            } else {
                interpolator2 = ACTIVATE_INVERSE_INTERPOLATOR;
                interpolator = ACTIVATE_INVERSE_ALPHA_INTERPOLATOR;
            }
            animator.setInterpolator(interpolator2);
            animator.setDuration(220);
            float f = 1.0f;
            if (z) {
                this.mBackgroundNormal.setAlpha(1.0f);
                animator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        ActivatableNotificationView.this.updateBackground();
                    }
                });
                animator.start();
            } else {
                this.mBackgroundNormal.setAlpha(0.4f);
                animator.start();
            }
            ViewPropertyAnimator animate = this.mBackgroundNormal.animate();
            if (z) {
                f = 0.0f;
            }
            animate.alpha(f).setInterpolator(interpolator).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    if (z) {
                        animatedFraction = 1.0f - animatedFraction;
                    }
                    ActivatableNotificationView.this.setNormalBackgroundVisibilityAmount(animatedFraction);
                }
            }).setDuration(220);
        }
    }

    public void makeInactive(boolean z) {
        if (this.mActivated) {
            this.mActivated = false;
            if (this.mDimmed) {
                if (z) {
                    startActivateAnimation(true);
                } else {
                    updateBackground();
                }
            }
        }
        OnActivatedListener onActivatedListener = this.mOnActivatedListener;
        if (onActivatedListener != null) {
            onActivatedListener.onActivationReset(this);
        }
        removeCallbacks(this.mTapTimeoutRunnable);
    }

    public void setDimmed(boolean z, boolean z2) {
        this.mNeedsDimming = z;
        boolean isDimmable = z & isDimmable();
        if (this.mDimmed != isDimmable) {
            this.mDimmed = isDimmable;
            resetBackgroundAlpha();
            if (z2) {
                fadeDimmedBackground();
            } else {
                updateBackground();
            }
        }
    }

    private void updateOutlineAlpha() {
        setOutlineAlpha((0.3f * this.mNormalBackgroundVisibilityAmount) + 0.7f);
    }

    public void setNormalBackgroundVisibilityAmount(float f) {
        this.mNormalBackgroundVisibilityAmount = f;
        updateOutlineAlpha();
    }

    public void setBelowSpeedBump(boolean z) {
        super.setBelowSpeedBump(z);
        if (z != this.mIsBelowSpeedBump) {
            this.mIsBelowSpeedBump = z;
            updateBackgroundTint();
            onBelowSpeedBumpChanged();
        }
    }

    public boolean isBelowSpeedBump() {
        return this.mIsBelowSpeedBump;
    }

    public void setTintColor(int i) {
        setTintColor(i, false);
    }

    public void setTintColor(int i, boolean z) {
        if (i != this.mBgTint) {
            this.mBgTint = i;
            updateBackgroundTint(z);
        }
    }

    public void setDistanceToTopRoundness(float f) {
        super.setDistanceToTopRoundness(f);
        this.mBackgroundNormal.setDistanceToTopRoundness(f);
        this.mBackgroundDimmed.setDistanceToTopRoundness(f);
    }

    public boolean isLastInSection() {
        return this.mLastInSection;
    }

    public boolean isFirstInSection() {
        return this.mFirstInSection;
    }

    public void setLastInSection(boolean z) {
        if (z != this.mLastInSection) {
            this.mLastInSection = z;
            this.mBackgroundNormal.setLastInSection(z);
            this.mBackgroundDimmed.setLastInSection(z);
        }
    }

    public void setFirstInSection(boolean z) {
        if (z != this.mFirstInSection) {
            this.mFirstInSection = z;
            this.mBackgroundNormal.setFirstInSection(z);
            this.mBackgroundDimmed.setFirstInSection(z);
        }
    }

    public void setOverrideTintColor(int i, float f) {
        this.mOverrideTint = i;
        this.mOverrideAmount = f;
        setBackgroundTintColor(calculateBgColor());
        if (isDimmable() || !this.mNeedsDimming) {
            this.mBackgroundNormal.setDrawableAlpha(255);
        } else {
            this.mBackgroundNormal.setDrawableAlpha((int) NotificationUtils.interpolate(255.0f, (float) this.mDimmedAlpha, f));
        }
    }

    /* access modifiers changed from: protected */
    public void updateBackgroundTint() {
        updateBackgroundTint(false);
    }

    private void updateBackgroundTint(boolean z) {
        ValueAnimator valueAnimator = this.mBackgroundColorAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        int rippleColor = getRippleColor();
        this.mBackgroundDimmed.setRippleColor(rippleColor);
        this.mBackgroundNormal.setRippleColor(rippleColor);
        int calculateBgColor = calculateBgColor();
        if (!z) {
            setBackgroundTintColor(calculateBgColor);
            return;
        }
        int i = this.mCurrentBackgroundTint;
        if (calculateBgColor != i) {
            this.mStartTint = i;
            this.mTargetTint = calculateBgColor;
            this.mBackgroundColorAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.mBackgroundColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ActivatableNotificationView.this.setBackgroundTintColor(NotificationUtils.interpolateColors(ActivatableNotificationView.this.mStartTint, ActivatableNotificationView.this.mTargetTint, valueAnimator.getAnimatedFraction()));
                }
            });
            this.mBackgroundColorAnimator.setDuration(360);
            this.mBackgroundColorAnimator.setInterpolator(Interpolators.LINEAR);
            this.mBackgroundColorAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ValueAnimator unused = ActivatableNotificationView.this.mBackgroundColorAnimator = null;
                }
            });
            this.mBackgroundColorAnimator.start();
        }
    }

    /* access modifiers changed from: protected */
    public void setBackgroundTintColor(int i) {
        if (i != this.mCurrentBackgroundTint) {
            this.mCurrentBackgroundTint = i;
            if (i == this.mNormalColor) {
                i = 0;
            }
            this.mBackgroundDimmed.setTint(i);
            this.mBackgroundNormal.setTint(i);
        }
    }

    private void fadeDimmedBackground() {
        this.mBackgroundDimmed.animate().cancel();
        this.mBackgroundNormal.animate().cancel();
        if (this.mActivated) {
            updateBackground();
            return;
        }
        if (!shouldHideBackground()) {
            if (this.mDimmed) {
                this.mBackgroundDimmed.setVisibility(0);
            } else {
                this.mBackgroundNormal.setVisibility(0);
            }
        }
        float f = 1.0f;
        float f2 = this.mDimmed ? 1.0f : 0.0f;
        if (this.mDimmed) {
            f = 0.0f;
        }
        int i = 220;
        ObjectAnimator objectAnimator = this.mBackgroundAnimator;
        if (objectAnimator != null) {
            f2 = ((Float) objectAnimator.getAnimatedValue()).floatValue();
            i = (int) this.mBackgroundAnimator.getCurrentPlayTime();
            this.mBackgroundAnimator.removeAllListeners();
            this.mBackgroundAnimator.cancel();
            if (i <= 0) {
                updateBackground();
                return;
            }
        }
        this.mBackgroundNormal.setAlpha(f2);
        this.mBackgroundAnimator = ObjectAnimator.ofFloat(this.mBackgroundNormal, View.ALPHA, new float[]{f2, f});
        this.mBackgroundAnimator.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        this.mBackgroundAnimator.setDuration((long) i);
        this.mBackgroundAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ActivatableNotificationView.this.updateBackground();
                ObjectAnimator unused = ActivatableNotificationView.this.mBackgroundAnimator = null;
                float unused2 = ActivatableNotificationView.this.mDimmedBackgroundFadeInAmount = -1.0f;
            }
        });
        this.mBackgroundAnimator.addUpdateListener(this.mBackgroundVisibilityUpdater);
        this.mBackgroundAnimator.start();
    }

    /* access modifiers changed from: protected */
    public void updateBackgroundAlpha(float f) {
        if (!isChildInGroup() || !this.mDimmed) {
            f = 1.0f;
        }
        this.mBgAlpha = f;
        float f2 = this.mDimmedBackgroundFadeInAmount;
        if (f2 != -1.0f) {
            this.mBgAlpha *= f2;
        }
        this.mBackgroundDimmed.setAlpha(this.mBgAlpha);
    }

    /* access modifiers changed from: protected */
    public void resetBackgroundAlpha() {
        updateBackgroundAlpha(0.0f);
    }

    /* access modifiers changed from: protected */
    public void updateBackground() {
        cancelFadeAnimations();
        float f = 1.0f;
        int i = 4;
        if (shouldHideBackground()) {
            this.mBackgroundDimmed.setVisibility(4);
            NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
            if (this.mActivated) {
                i = 0;
            }
            notificationBackgroundView.setVisibility(i);
        } else if (this.mDimmed) {
            boolean z = isGroupExpansionChanging() && isChildInGroup();
            this.mBackgroundDimmed.setVisibility(z ? 4 : 0);
            NotificationBackgroundView notificationBackgroundView2 = this.mBackgroundNormal;
            if (this.mActivated || z) {
                i = 0;
            }
            notificationBackgroundView2.setVisibility(i);
        } else {
            this.mBackgroundDimmed.setVisibility(4);
            this.mBackgroundNormal.setVisibility(0);
            this.mBackgroundNormal.setAlpha(1.0f);
            removeCallbacks(this.mTapTimeoutRunnable);
            makeInactive(false);
        }
        if (this.mBackgroundNormal.getVisibility() != 0) {
            f = 0.0f;
        }
        setNormalBackgroundVisibilityAmount(f);
    }

    /* access modifiers changed from: protected */
    public void updateBackgroundClipping() {
        boolean z = true;
        this.mBackgroundNormal.setBottomAmountClips(!isChildInGroup());
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundDimmed;
        if (isChildInGroup()) {
            z = false;
        }
        notificationBackgroundView.setBottomAmountClips(z);
    }

    private void cancelFadeAnimations() {
        ObjectAnimator objectAnimator = this.mBackgroundAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.mBackgroundDimmed.animate().cancel();
        this.mBackgroundNormal.animate().cancel();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setPivotX((float) (getWidth() / 2));
    }

    public void setActualHeight(int i, boolean z) {
        super.setActualHeight(i, z);
        setPivotY((float) (i / 2));
        this.mBackgroundNormal.setActualHeight(i);
        this.mBackgroundDimmed.setActualHeight(i);
    }

    public void setClipTopAmount(int i) {
        super.setClipTopAmount(i);
        this.mBackgroundNormal.setClipTopAmount(i);
        this.mBackgroundDimmed.setClipTopAmount(i);
    }

    public void setClipBottomAmount(int i) {
        super.setClipBottomAmount(i);
        this.mBackgroundNormal.setClipBottomAmount(i);
        this.mBackgroundDimmed.setClipBottomAmount(i);
    }

    public long performRemoveAnimation(long j, long j2, float f, boolean z, float f2, Runnable runnable, AnimatorListenerAdapter animatorListenerAdapter) {
        enableAppearDrawing(true);
        this.mIsHeadsUpAnimation = z;
        this.mHeadsUpLocation = f2;
        if (this.mDrawingAppearAnimation) {
            startAppearAnimation(false, f, j2, j, runnable, animatorListenerAdapter);
            return 0;
        } else if (runnable == null) {
            return 0;
        } else {
            runnable.run();
            return 0;
        }
    }

    public void performAddAnimation(long j, long j2, boolean z) {
        enableAppearDrawing(true);
        this.mIsHeadsUpAnimation = z;
        this.mHeadsUpLocation = (float) this.mHeadsUpAddStartLocation;
        if (this.mDrawingAppearAnimation) {
            startAppearAnimation(true, z ? 0.0f : -1.0f, j, j2, (Runnable) null, (AnimatorListenerAdapter) null);
        }
    }

    private void startAppearAnimation(final boolean z, float f, long j, long j2, final Runnable runnable, AnimatorListenerAdapter animatorListenerAdapter) {
        cancelAppearAnimation();
        this.mAnimationTranslationY = f * ((float) getActualHeight());
        float f2 = 1.0f;
        if (this.mAppearAnimationFraction == -1.0f) {
            if (z) {
                this.mAppearAnimationFraction = 0.0f;
                this.mAppearAnimationTranslation = this.mAnimationTranslationY;
            } else {
                this.mAppearAnimationFraction = 1.0f;
                this.mAppearAnimationTranslation = 0.0f;
            }
        }
        this.mIsAppearing = z;
        if (z) {
            this.mCurrentAppearInterpolator = this.mSlowOutFastInInterpolator;
            this.mCurrentAlphaInterpolator = Interpolators.LINEAR_OUT_SLOW_IN;
        } else {
            this.mCurrentAppearInterpolator = Interpolators.FAST_OUT_SLOW_IN;
            this.mCurrentAlphaInterpolator = this.mSlowOutLinearInInterpolator;
            f2 = 0.0f;
        }
        this.mAppearAnimator = ValueAnimator.ofFloat(new float[]{this.mAppearAnimationFraction, f2});
        this.mAppearAnimator.setInterpolator(Interpolators.LINEAR);
        this.mAppearAnimator.setDuration((long) (((float) j2) * Math.abs(this.mAppearAnimationFraction - f2)));
        this.mAppearAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = ActivatableNotificationView.this.mAppearAnimationFraction = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ActivatableNotificationView.this.updateAppearAnimationAlpha();
                ActivatableNotificationView.this.updateAppearRect();
                ActivatableNotificationView.this.invalidate();
            }
        });
        if (animatorListenerAdapter != null) {
            this.mAppearAnimator.addListener(animatorListenerAdapter);
        }
        if (j > 0) {
            updateAppearAnimationAlpha();
            updateAppearRect();
            this.mAppearAnimator.setStartDelay(j);
        }
        this.mAppearAnimator.addListener(new AnimatorListenerAdapter() {
            private boolean mWasCancelled;

            public void onAnimationEnd(Animator animator) {
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
                if (!this.mWasCancelled) {
                    ActivatableNotificationView.this.enableAppearDrawing(false);
                    ActivatableNotificationView.this.onAppearAnimationFinished(z);
                }
            }

            public void onAnimationStart(Animator animator) {
                this.mWasCancelled = false;
            }

            public void onAnimationCancel(Animator animator) {
                this.mWasCancelled = true;
            }
        });
        this.mAppearAnimator.start();
    }

    private void cancelAppearAnimation() {
        ValueAnimator valueAnimator = this.mAppearAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mAppearAnimator = null;
        }
    }

    public void cancelAppearDrawing() {
        cancelAppearAnimation();
        enableAppearDrawing(false);
    }

    /* access modifiers changed from: private */
    public void updateAppearRect() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5 = 1.0f - this.mAppearAnimationFraction;
        float interpolation = this.mCurrentAppearInterpolator.getInterpolation(f5) * this.mAnimationTranslationY;
        this.mAppearAnimationTranslation = interpolation;
        float f6 = f5 - 0.0f;
        float interpolation2 = 1.0f - this.mCurrentAppearInterpolator.getInterpolation(Math.min(1.0f, Math.max(0.0f, f6 / 0.8f)));
        float lerp = MathUtils.lerp((!this.mIsHeadsUpAnimation || this.mIsAppearing) ? 0.05f : 0.0f, 1.0f, interpolation2) * ((float) getWidth());
        if (this.mIsHeadsUpAnimation) {
            f2 = MathUtils.lerp(this.mHeadsUpLocation, 0.0f, interpolation2);
            f = lerp + f2;
        } else {
            f2 = (((float) getWidth()) * 0.5f) - (lerp / 2.0f);
            f = ((float) getWidth()) - f2;
        }
        float interpolation3 = this.mCurrentAppearInterpolator.getInterpolation(Math.max(0.0f, f6 / 1.0f));
        int actualHeight = getActualHeight();
        float f7 = this.mAnimationTranslationY;
        if (f7 > 0.0f) {
            f3 = (((float) actualHeight) - ((f7 * interpolation3) * 0.1f)) - interpolation;
            f4 = interpolation3 * f3;
        } else {
            float f8 = (float) actualHeight;
            float f9 = (((f7 + f8) * interpolation3) * 0.1f) - interpolation;
            f3 = (f8 * (1.0f - interpolation3)) + (interpolation3 * f9);
            f4 = f9;
        }
        this.mAppearAnimationRect.set(f2, f4, f, f3);
        float f10 = this.mAppearAnimationTranslation;
        setOutlineRect(f2, f4 + f10, f, f3 + f10);
    }

    /* access modifiers changed from: private */
    public void updateAppearAnimationAlpha() {
        setContentAlpha(this.mCurrentAlphaInterpolator.getInterpolation(Math.min(1.0f, this.mAppearAnimationFraction / 1.0f)));
    }

    private void setContentAlpha(float f) {
        View contentView = getContentView();
        if (contentView.hasOverlappingRendering()) {
            int i = (f == 0.0f || f == 1.0f) ? 0 : 2;
            if (contentView.getLayerType() != i) {
                contentView.setLayerType(i, (Paint) null);
            }
        }
        contentView.setAlpha(f);
    }

    /* access modifiers changed from: protected */
    public void applyRoundness() {
        super.applyRoundness();
        applyBackgroundRoundness(getCurrentBackgroundRadiusTop(), getCurrentBackgroundRadiusBottom());
    }

    /* access modifiers changed from: protected */
    public void applyBackgroundRoundness(float f, float f2) {
        this.mBackgroundDimmed.setRoundness(f, f2);
        this.mBackgroundNormal.setRoundness(f, f2);
    }

    /* access modifiers changed from: protected */
    public void setBackgroundTop(int i) {
        this.mBackgroundDimmed.setBackgroundTop(i);
        this.mBackgroundNormal.setBackgroundTop(i);
    }

    public int calculateBgColor() {
        return calculateBgColor(true, true);
    }

    /* access modifiers changed from: protected */
    public boolean childNeedsClipping(View view) {
        if (!(view instanceof NotificationBackgroundView) || !isClippingNeeded()) {
            return super.childNeedsClipping(view);
        }
        return true;
    }

    private int calculateBgColor(boolean z, boolean z2) {
        int i;
        if (z2 && this.mOverrideTint != 0) {
            return NotificationUtils.interpolateColors(calculateBgColor(z, false), this.mOverrideTint, this.mOverrideAmount);
        }
        if (!z || (i = this.mBgTint) == 0) {
            return this.mNormalColor;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int getRippleColor() {
        if (this.mBgTint != 0) {
            return this.mTintedRippleColor;
        }
        return this.mNormalRippleColor;
    }

    /* access modifiers changed from: private */
    public void enableAppearDrawing(boolean z) {
        if (z != this.mDrawingAppearAnimation) {
            this.mDrawingAppearAnimation = z;
            if (!z) {
                setContentAlpha(1.0f);
                this.mAppearAnimationFraction = -1.0f;
                setOutlineRect((RectF) null);
            }
            invalidate();
        }
    }

    public boolean isDrawingAppearAnimation() {
        return this.mDrawingAppearAnimation;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.mDrawingAppearAnimation) {
            canvas.save();
            canvas.translate(0.0f, this.mAppearAnimationTranslation);
        }
        super.dispatchDraw(canvas);
        if (this.mDrawingAppearAnimation) {
            canvas.restore();
        }
    }

    public void setOnActivatedListener(OnActivatedListener onActivatedListener) {
        this.mOnActivatedListener = onActivatedListener;
    }

    public void setFakeShadowIntensity(float f, float f2, int i, int i2) {
        boolean z = this.mShadowHidden;
        this.mShadowHidden = f == 0.0f;
        if (!this.mShadowHidden || !z) {
            this.mFakeShadow.setFakeShadowTranslationZ(f * (getTranslationZ() + 0.1f), f2, i, i2);
        }
    }

    public int getBackgroundColorWithoutTint() {
        return calculateBgColor(false, false);
    }

    public int getCurrentBackgroundTint() {
        return this.mCurrentBackgroundTint;
    }

    public int getHeadsUpHeightWithoutHeader() {
        return getHeight();
    }
}
