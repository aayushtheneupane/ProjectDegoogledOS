package com.android.systemui.statusbar.phone;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.MathUtils;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.ColorUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.settingslib.Utils;
import com.android.systemui.C1772R$attr;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.statusbar.VibratorHelper;

public class NavigationBarEdgePanel extends View {
    private static final FloatPropertyCompat<NavigationBarEdgePanel> CURRENT_ANGLE = new FloatPropertyCompat<NavigationBarEdgePanel>("currentAngle") {
        public void setValue(NavigationBarEdgePanel navigationBarEdgePanel, float f) {
            navigationBarEdgePanel.setCurrentAngle(f);
        }

        public float getValue(NavigationBarEdgePanel navigationBarEdgePanel) {
            return navigationBarEdgePanel.getCurrentAngle();
        }
    };
    private static final FloatPropertyCompat<NavigationBarEdgePanel> CURRENT_TRANSLATION = new FloatPropertyCompat<NavigationBarEdgePanel>("currentTranslation") {
        public void setValue(NavigationBarEdgePanel navigationBarEdgePanel, float f) {
            navigationBarEdgePanel.setCurrentTranslation(f);
        }

        public float getValue(NavigationBarEdgePanel navigationBarEdgePanel) {
            return navigationBarEdgePanel.getCurrentTranslation();
        }
    };
    private static final FloatPropertyCompat<NavigationBarEdgePanel> CURRENT_VERTICAL_TRANSLATION = new FloatPropertyCompat<NavigationBarEdgePanel>("verticalTranslation") {
        public void setValue(NavigationBarEdgePanel navigationBarEdgePanel, float f) {
            navigationBarEdgePanel.setVerticalTranslation(f);
        }

        public float getValue(NavigationBarEdgePanel navigationBarEdgePanel) {
            return navigationBarEdgePanel.getVerticalTranslation();
        }
    };
    private static final Interpolator RUBBER_BAND_INTERPOLATOR = new PathInterpolator(0.2f, 1.0f, 1.0f, 1.0f);
    private static final Interpolator RUBBER_BAND_INTERPOLATOR_APPEAR = new PathInterpolator(0.25f, 1.0f, 1.0f, 1.0f);
    private boolean mAlmostLongSwipe;
    private final SpringAnimation mAngleAnimation;
    private final SpringForce mAngleAppearForce;
    private final SpringForce mAngleDisappearForce;
    private float mAngleOffset;
    /* access modifiers changed from: private */
    public int mArrowColor;
    private final ValueAnimator mArrowColorAnimator;
    private int mArrowColorDark;
    private int mArrowColorLight;
    private final ValueAnimator mArrowDisappearAnimation;
    private final float mArrowLength;
    private int mArrowPaddingEnd;
    private final Path mArrowPath = new Path();
    /* access modifiers changed from: private */
    public int mArrowStartColor;
    private final float mArrowThickness;
    private boolean mArrowsPointLeft;
    private boolean mBackArrowVisibility;
    private boolean mBackHapticEnabled;
    private int mBackSwipeType;
    private final float mBaseTranslation;
    private Context mContext;
    private float mCurrentAngle;
    private int mCurrentArrowColor;
    private float mCurrentTranslation;
    private final float mDensity;
    private float mDesiredAngle;
    private float mDesiredTranslation;
    private float mDesiredVerticalTranslation;
    private float mDisappearAmount;
    private boolean mDragSlopPassed;
    private boolean mIsDark = false;
    private boolean mIsLeftPanel;
    private float mLongSwipeThreshold;
    private float mMaxTranslation;
    private final float mMinDeltaForSwitch;
    private final Paint mPaint = new Paint();
    private float mPreviousTouchTranslation;
    private int mProtectionColor;
    private int mProtectionColorDark;
    private int mProtectionColorLight;
    private final Paint mProtectionPaint;
    private final SpringForce mRegularTranslationSpring;
    private int mScreenSize;
    private DynamicAnimation.OnAnimationEndListener mSetGoneEndListener = new DynamicAnimation.OnAnimationEndListener() {
        public void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
            dynamicAnimation.removeEndListener(this);
            if (!z) {
                NavigationBarEdgePanel.this.setVisibility(8);
            }
        }
    };
    private boolean mShowProtection = false;
    private float mStartX;
    private float mStartY;
    private final float mSwipeThreshold;
    private float mTotalTouchDelta;
    private final SpringAnimation mTranslationAnimation;
    private boolean mTriggerBack;
    private final SpringForce mTriggerBackSpring;
    private VelocityTracker mVelocityTracker;
    private float mVerticalTranslation;
    private final SpringAnimation mVerticalTranslationAnimation;
    private boolean mVibrateOnOpening;
    private long mVibrationTime;
    private final Vibrator mVibrator;
    private final VibratorHelper mVibratorHelper;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public NavigationBarEdgePanel(Context context) {
        super(context);
        this.mContext = context;
        this.mVibrator = (Vibrator) context.getSystemService(Vibrator.class);
        this.mVibratorHelper = (VibratorHelper) Dependency.get(VibratorHelper.class);
        this.mDensity = context.getResources().getDisplayMetrics().density;
        this.mBaseTranslation = m38dp(32.0f);
        this.mArrowLength = m38dp(18.0f);
        this.mArrowThickness = m38dp(2.5f);
        this.mMinDeltaForSwitch = m38dp(32.0f);
        this.mPaint.setStrokeWidth(this.mArrowThickness);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mArrowColorAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mArrowColorAnimator.setDuration(120);
        this.mArrowColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                NavigationBarEdgePanel.this.setCurrentArrowColor(ColorUtils.blendARGB(NavigationBarEdgePanel.this.mArrowStartColor, NavigationBarEdgePanel.this.mArrowColor, valueAnimator.getAnimatedFraction()));
            }
        });
        this.mArrowDisappearAnimation = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mArrowDisappearAnimation.setDuration(100);
        this.mArrowDisappearAnimation.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        this.mArrowDisappearAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NavigationBarEdgePanel.this.lambda$new$0$NavigationBarEdgePanel(valueAnimator);
            }
        });
        this.mAngleAnimation = new SpringAnimation(this, CURRENT_ANGLE);
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(500.0f);
        springForce.setDampingRatio(0.5f);
        this.mAngleAppearForce = springForce;
        SpringForce springForce2 = new SpringForce();
        springForce2.setStiffness(1500.0f);
        springForce2.setDampingRatio(0.5f);
        springForce2.setFinalPosition(90.0f);
        this.mAngleDisappearForce = springForce2;
        SpringAnimation springAnimation = this.mAngleAnimation;
        springAnimation.setSpring(this.mAngleAppearForce);
        springAnimation.setMaxValue(90.0f);
        this.mTranslationAnimation = new SpringAnimation(this, CURRENT_TRANSLATION);
        SpringForce springForce3 = new SpringForce();
        springForce3.setStiffness(1500.0f);
        springForce3.setDampingRatio(0.75f);
        this.mRegularTranslationSpring = springForce3;
        SpringForce springForce4 = new SpringForce();
        springForce4.setStiffness(450.0f);
        springForce4.setDampingRatio(0.75f);
        this.mTriggerBackSpring = springForce4;
        this.mTranslationAnimation.setSpring(this.mRegularTranslationSpring);
        this.mVerticalTranslationAnimation = new SpringAnimation(this, CURRENT_VERTICAL_TRANSLATION);
        SpringAnimation springAnimation2 = this.mVerticalTranslationAnimation;
        SpringForce springForce5 = new SpringForce();
        springForce5.setStiffness(1500.0f);
        springForce5.setDampingRatio(0.75f);
        springAnimation2.setSpring(springForce5);
        this.mProtectionPaint = new Paint(this.mPaint);
        this.mProtectionPaint.setStrokeWidth(this.mArrowThickness + 2.0f);
        loadDimens();
        loadColors(context);
        updateArrowDirection();
        setBackArrowVisibility();
        setBackGestureHaptic();
        this.mSwipeThreshold = context.getResources().getDimension(C1775R$dimen.navigation_edge_action_drag_threshold);
        setVisibility(8);
        this.mVibrateOnOpening = context.getResources().getBoolean(C1773R$bool.config_vibrateOnIconAnimation);
        setExtendedSwipe();
    }

    public /* synthetic */ void lambda$new$0$NavigationBarEdgePanel(ValueAnimator valueAnimator) {
        this.mDisappearAmount = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    public boolean shouldTriggerBack() {
        return this.mTriggerBack;
    }

    public void setIsDark(boolean z, boolean z2) {
        this.mIsDark = z;
        updateIsDark(z2);
    }

    public void setIsLeftPanel(boolean z) {
        this.mIsLeftPanel = z;
    }

    public void setBackArrowVisibility() {
        boolean z = true;
        if (Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "show_back_arrow_gesture", 1, -2) != 1) {
            z = false;
        }
        this.mBackArrowVisibility = z;
    }

    public void setBackGestureHaptic() {
        boolean z = true;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "back_gesture_haptic", 1, -2) != 1) {
            z = false;
        }
        this.mBackHapticEnabled = z;
    }

    public void adjustRectToBoundingBox(Rect rect) {
        float f = this.mDesiredTranslation;
        if (!this.mTriggerBack) {
            f = this.mBaseTranslation;
            if ((this.mIsLeftPanel && this.mArrowsPointLeft) || (!this.mIsLeftPanel && !this.mArrowsPointLeft)) {
                f -= getStaticArrowWidth();
            }
        }
        float f2 = f - (this.mArrowThickness / 2.0f);
        if (!this.mIsLeftPanel) {
            f2 = ((float) rect.width()) - f2;
        }
        float staticArrowWidth = getStaticArrowWidth();
        float polarToCartY = polarToCartY(56.0f) * this.mArrowLength * 2.0f;
        if (!this.mArrowsPointLeft) {
            f2 -= staticArrowWidth;
        }
        rect.offset((int) f2, (int) (((((float) getHeight()) * 0.5f) + this.mDesiredVerticalTranslation) - (polarToCartY / 2.0f)));
        int i = rect.left;
        int i2 = rect.top;
        rect.set(i, i2, (int) (((float) i) + staticArrowWidth), (int) (((float) i2) + polarToCartY));
    }

    public void handleTouch(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    handleMoveEvent(motionEvent);
                    return;
                } else if (actionMasked != 3) {
                    return;
                }
            }
            if (this.mTriggerBack) {
                triggerBack();
            } else if (this.mTranslationAnimation.isRunning()) {
                this.mTranslationAnimation.addEndListener(this.mSetGoneEndListener);
            } else {
                setVisibility(8);
            }
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
            return;
        }
        int i = 0;
        this.mAlmostLongSwipe = false;
        this.mDragSlopPassed = false;
        resetOnDown();
        this.mStartX = motionEvent.getX();
        this.mStartY = motionEvent.getY();
        if (!this.mBackArrowVisibility) {
            i = 4;
        }
        setVisibility(i);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateArrowDirection();
        loadDimens();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f = this.mCurrentTranslation - (this.mArrowThickness / 2.0f);
        canvas.save();
        if (!this.mIsLeftPanel) {
            f = ((float) getWidth()) - f;
        }
        canvas.translate(f, (((float) getHeight()) * 0.5f) + this.mVerticalTranslation);
        float polarToCartX = polarToCartX(this.mCurrentAngle) * this.mArrowLength;
        float polarToCartY = polarToCartY(this.mCurrentAngle) * this.mArrowLength;
        Path calculatePath = calculatePath(polarToCartX, polarToCartY);
        if (this.mAlmostLongSwipe) {
            calculatePath.addPath(calculatePath(polarToCartX, polarToCartY), this.mArrowThickness * 2.0f * ((float) (this.mIsLeftPanel ? 1 : -1)), 0.0f);
        }
        if (this.mShowProtection) {
            canvas.drawPath(calculatePath, this.mProtectionPaint);
        }
        canvas.drawPath(calculatePath, this.mPaint);
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mMaxTranslation = (float) (getWidth() - this.mArrowPaddingEnd);
    }

    private void loadDimens() {
        this.mArrowPaddingEnd = getContext().getResources().getDimensionPixelSize(C1775R$dimen.navigation_edge_panel_padding);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mScreenSize = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.mLongSwipeThreshold = ((float) displayMetrics.widthPixels) * 0.45f;
    }

    private void updateArrowDirection() {
        this.mArrowsPointLeft = getLayoutDirection() == 0;
        invalidate();
    }

    private void loadColors(Context context) {
        int themeAttr = Utils.getThemeAttr(context, C1772R$attr.darkIconTheme);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, Utils.getThemeAttr(context, C1772R$attr.lightIconTheme));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(context, themeAttr);
        this.mArrowColorLight = Utils.getColorAttrDefaultColor(contextThemeWrapper, C1772R$attr.singleToneColor);
        this.mArrowColorDark = Utils.getColorAttrDefaultColor(contextThemeWrapper2, C1772R$attr.singleToneColor);
        this.mProtectionColorDark = this.mArrowColorLight;
        this.mProtectionColorLight = this.mArrowColorDark;
        updateIsDark(false);
    }

    private void updateIsDark(boolean z) {
        this.mProtectionColor = this.mIsDark ? this.mProtectionColorDark : this.mProtectionColorLight;
        this.mProtectionPaint.setColor(this.mProtectionColor);
        this.mArrowColor = this.mIsDark ? this.mArrowColorDark : this.mArrowColorLight;
        this.mArrowColorAnimator.cancel();
        if (!z) {
            setCurrentArrowColor(this.mArrowColor);
            return;
        }
        this.mArrowStartColor = this.mCurrentArrowColor;
        this.mArrowColorAnimator.start();
    }

    /* access modifiers changed from: private */
    public void setCurrentArrowColor(int i) {
        this.mCurrentArrowColor = i;
        this.mPaint.setColor(i);
        invalidate();
    }

    private float getStaticArrowWidth() {
        return polarToCartX(56.0f) * this.mArrowLength;
    }

    private float polarToCartX(float f) {
        return (float) Math.cos(Math.toRadians((double) f));
    }

    private float polarToCartY(float f) {
        return (float) Math.sin(Math.toRadians((double) f));
    }

    private Path calculatePath(float f, float f2) {
        if (!this.mArrowsPointLeft) {
            f = -f;
        }
        float lerp = MathUtils.lerp(1.0f, 0.75f, this.mDisappearAmount);
        float f3 = f * lerp;
        float f4 = f2 * lerp;
        this.mArrowPath.reset();
        this.mArrowPath.moveTo(f3, f4);
        this.mArrowPath.lineTo(0.0f, 0.0f);
        this.mArrowPath.lineTo(f3, -f4);
        return this.mArrowPath;
    }

    /* access modifiers changed from: private */
    public float getCurrentAngle() {
        return this.mCurrentAngle;
    }

    /* access modifiers changed from: private */
    public float getCurrentTranslation() {
        return this.mCurrentTranslation;
    }

    private void triggerBack() {
        this.mVelocityTracker.computeCurrentVelocity(1000);
        boolean z = Math.abs(this.mVelocityTracker.getXVelocity()) < 500.0f;
        if (this.mBackHapticEnabled && (z || SystemClock.uptimeMillis() - this.mVibrationTime >= 400)) {
            this.mVibratorHelper.vibrate(0);
        }
        float f = this.mAngleOffset;
        if (f > -4.0f) {
            this.mAngleOffset = Math.max(-8.0f, f - 8.0f);
            updateAngle(true);
        }
        final $$Lambda$NavigationBarEdgePanel$qL_Cvd7_6Xne4NYpi_Ofi326YV0 r0 = new Runnable() {
            public final void run() {
                NavigationBarEdgePanel.this.lambda$triggerBack$2$NavigationBarEdgePanel();
            }
        };
        if (this.mTranslationAnimation.isRunning()) {
            this.mTranslationAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                public void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                    dynamicAnimation.removeEndListener(this);
                    if (!z) {
                        r0.run();
                    }
                }
            });
        } else {
            r0.run();
        }
    }

    public /* synthetic */ void lambda$triggerBack$2$NavigationBarEdgePanel() {
        this.mAngleOffset = Math.max(0.0f, this.mAngleOffset + 8.0f);
        updateAngle(true);
        this.mTranslationAnimation.setSpring(this.mTriggerBackSpring);
        setDesiredTranslation(this.mDesiredTranslation - m38dp(32.0f), true);
        animate().alpha(0.0f).setDuration(80).withEndAction(new Runnable() {
            public final void run() {
                NavigationBarEdgePanel.this.lambda$triggerBack$1$NavigationBarEdgePanel();
            }
        });
        this.mArrowDisappearAnimation.start();
    }

    public /* synthetic */ void lambda$triggerBack$1$NavigationBarEdgePanel() {
        setVisibility(8);
    }

    public void resetOnDown() {
        animate().cancel();
        this.mAngleAnimation.cancel();
        this.mTranslationAnimation.cancel();
        this.mVerticalTranslationAnimation.cancel();
        this.mArrowDisappearAnimation.cancel();
        this.mAngleOffset = 0.0f;
        this.mTranslationAnimation.setSpring(this.mRegularTranslationSpring);
        setTriggerBack(false, false);
        setDesiredTranslation(0.0f, false);
        setCurrentTranslation(0.0f);
        updateAngle(false);
        this.mPreviousTouchTranslation = 0.0f;
        this.mTotalTouchDelta = 0.0f;
        this.mVibrationTime = 0;
        setDesiredVerticalTransition(0.0f, false);
    }

    private void handleMoveEvent(MotionEvent motionEvent) {
        float f;
        float f2;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float abs = MathUtils.abs(x - this.mStartX);
        this.mAlmostLongSwipe = this.mBackSwipeType == 1 && abs > this.mLongSwipeThreshold;
        float f3 = y - this.mStartY;
        float f4 = abs - this.mPreviousTouchTranslation;
        if (Math.abs(f4) > 0.0f) {
            if (Math.signum(f4) == Math.signum(this.mTotalTouchDelta)) {
                this.mTotalTouchDelta += f4;
            } else {
                this.mTotalTouchDelta = f4;
            }
        }
        this.mPreviousTouchTranslation = abs;
        if (!this.mDragSlopPassed && abs > this.mSwipeThreshold) {
            this.mDragSlopPassed = true;
            if (this.mBackHapticEnabled) {
                if (this.mVibrateOnOpening) {
                    this.mVibratorHelper.vibrate(2);
                    this.mVibrationTime = SystemClock.uptimeMillis();
                } else {
                    this.mVibrator.vibrate(VibrationEffect.createOneShot(30, -1));
                }
            }
            this.mDisappearAmount = 0.0f;
            setAlpha(1.0f);
            setTriggerBack(true, true);
        }
        float f5 = this.mBaseTranslation;
        if (abs > f5) {
            float interpolation = RUBBER_BAND_INTERPOLATOR.getInterpolation(MathUtils.saturate((abs - f5) / (((float) this.mScreenSize) - f5)));
            float f6 = this.mMaxTranslation;
            float f7 = this.mBaseTranslation;
            f = f7 + (interpolation * (f6 - f7));
        } else {
            float interpolation2 = RUBBER_BAND_INTERPOLATOR_APPEAR.getInterpolation(MathUtils.saturate((f5 - abs) / f5));
            float f8 = this.mBaseTranslation;
            f = f8 - (interpolation2 * (f8 / 4.0f));
        }
        boolean z = this.mTriggerBack;
        if (Math.abs(this.mTotalTouchDelta) > this.mMinDeltaForSwitch) {
            z = this.mTotalTouchDelta > 0.0f;
        }
        this.mVelocityTracker.computeCurrentVelocity(1000);
        float xVelocity = this.mVelocityTracker.getXVelocity();
        this.mAngleOffset = Math.min((MathUtils.mag(xVelocity, this.mVelocityTracker.getYVelocity()) / 1000.0f) * 4.0f, 4.0f) * Math.signum(xVelocity);
        if ((this.mIsLeftPanel && this.mArrowsPointLeft) || (!this.mIsLeftPanel && !this.mArrowsPointLeft)) {
            this.mAngleOffset *= -1.0f;
        }
        if (Math.abs(f3) > Math.abs(x - this.mStartX) * 2.0f) {
            z = false;
        }
        setTriggerBack(z, true);
        if (!this.mTriggerBack) {
            f2 = 0.0f;
        } else {
            f2 = ((!this.mIsLeftPanel || !this.mArrowsPointLeft) && (this.mIsLeftPanel || this.mArrowsPointLeft)) ? f : f - getStaticArrowWidth();
        }
        setDesiredTranslation(f2, true);
        updateAngle(true);
        float height = (((float) getHeight()) / 2.0f) - this.mArrowLength;
        setDesiredVerticalTransition(RUBBER_BAND_INTERPOLATOR.getInterpolation(MathUtils.constrain(Math.abs(f3) / (15.0f * height), 0.0f, 1.0f)) * height * Math.signum(f3), true);
    }

    private void setDesiredVerticalTransition(float f, boolean z) {
        if (this.mDesiredVerticalTranslation != f) {
            this.mDesiredVerticalTranslation = f;
            if (!z) {
                setVerticalTranslation(f);
            } else {
                this.mVerticalTranslationAnimation.animateToFinalPosition(f);
            }
            invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void setVerticalTranslation(float f) {
        this.mVerticalTranslation = f;
        invalidate();
    }

    /* access modifiers changed from: private */
    public float getVerticalTranslation() {
        return this.mVerticalTranslation;
    }

    private void setDesiredTranslation(float f, boolean z) {
        if (this.mDesiredTranslation != f) {
            this.mDesiredTranslation = f;
            if (!z) {
                setCurrentTranslation(f);
            } else {
                this.mTranslationAnimation.animateToFinalPosition(f);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setCurrentTranslation(float f) {
        this.mCurrentTranslation = f;
        invalidate();
    }

    private void setTriggerBack(boolean z, boolean z2) {
        if (this.mTriggerBack != z) {
            this.mTriggerBack = z;
            this.mAngleAnimation.cancel();
            updateAngle(z2);
            this.mTranslationAnimation.cancel();
        }
    }

    private void updateAngle(boolean z) {
        float f = this.mTriggerBack ? this.mAngleOffset + 56.0f : 90.0f;
        if (f != this.mDesiredAngle) {
            if (!z) {
                setCurrentAngle(f);
            } else {
                this.mAngleAnimation.setSpring(this.mTriggerBack ? this.mAngleAppearForce : this.mAngleDisappearForce);
                this.mAngleAnimation.animateToFinalPosition(f);
            }
            this.mDesiredAngle = f;
        }
    }

    /* access modifiers changed from: private */
    public void setCurrentAngle(float f) {
        this.mCurrentAngle = f;
        invalidate();
    }

    /* renamed from: dp */
    private float m38dp(float f) {
        return this.mDensity * f;
    }

    public void setExtendedSwipe() {
        this.mBackSwipeType = Settings.System.getIntForUser(this.mContext.getContentResolver(), "back_swipe_type", 0, -2);
    }
}
