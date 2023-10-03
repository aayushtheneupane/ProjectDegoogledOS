package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AlarmManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Trace;
import android.util.Log;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.util.function.TriConsumer;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.statusbar.ScrimView;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.util.AlarmTimeout;
import com.android.systemui.util.wakelock.DelayedWakeLock;
import com.android.systemui.util.wakelock.WakeLock;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class ScrimController implements ViewTreeObserver.OnPreDrawListener, ColorExtractor.OnColorsChangedListener, Dumpable {
    private static final boolean DEBUG = Log.isLoggable("ScrimController", 3);
    private static final int TAG_END_ALPHA = C1777R$id.scrim_alpha_end;
    static final int TAG_KEY_ANIM = C1777R$id.scrim;
    private static final int TAG_START_ALPHA = C1777R$id.scrim_alpha_start;
    protected boolean mAnimateChange;
    private long mAnimationDelay;
    protected long mAnimationDuration = -1;
    private boolean mBlankScreen;
    private Runnable mBlankingTransitionRunnable;
    /* access modifiers changed from: private */
    public Callback mCallback;
    private final SysuiColorExtractor mColorExtractor;
    private ColorExtractor.GradientColors mColors;
    private final Context mContext;
    private float mCurrentBehindAlpha = -1.0f;
    private int mCurrentBehindTint;
    private float mCurrentInFrontAlpha = -1.0f;
    private int mCurrentInFrontTint;
    private boolean mDarkenWhileDragging;
    /* access modifiers changed from: private */
    public boolean mDeferFinishedListener;
    private final DozeParameters mDozeParameters;
    private boolean mExpansionAffectsAlpha = true;
    private float mExpansionFraction = 1.0f;
    private final Handler mHandler;
    private final Interpolator mInterpolator = new DecelerateInterpolator();
    private boolean mKeyguardOccluded;
    private final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    private final KeyguardVisibilityCallback mKeyguardVisibilityCallback;
    /* access modifiers changed from: private */
    public boolean mNeedsDrawableColorUpdate;
    /* access modifiers changed from: private */
    public Runnable mOnAnimationFinished;
    private Runnable mPendingFrameCallback;
    private boolean mScreenBlankingCallbackCalled;
    private boolean mScreenOn;
    protected final ScrimView mScrimBehind;
    protected float mScrimBehindAlpha;
    protected float mScrimBehindAlphaKeyguard = 0.2f;
    protected float mScrimBehindAlphaResValue;
    protected final ScrimView mScrimInFront;
    private final TriConsumer<ScrimState, Float, ColorExtractor.GradientColors> mScrimStateListener;
    private final Consumer<Integer> mScrimVisibleListener;
    private int mScrimsVisibility;
    private ScrimState mState = ScrimState.UNINITIALIZED;
    private final AlarmTimeout mTimeTicker;
    private boolean mTracking;
    private final UnlockMethodCache mUnlockMethodCache;
    private boolean mUpdatePending;
    private final WakeLock mWakeLock;
    private boolean mWakeLockHeld;
    private boolean mWallpaperSupportsAmbientMode;
    private boolean mWallpaperVisibilityTimedOut;

    public interface Callback {
        void onCancelled() {
        }

        void onDisplayBlanked() {
        }

        void onFinished() {
        }

        void onStart() {
        }

        boolean shouldTimeoutWallpaper() {
            return false;
        }
    }

    public void setCurrentUser(int i) {
    }

    public ScrimController(ScrimView scrimView, ScrimView scrimView2, TriConsumer<ScrimState, Float, ColorExtractor.GradientColors> triConsumer, Consumer<Integer> consumer, DozeParameters dozeParameters, AlarmManager alarmManager, final KeyguardMonitor keyguardMonitor) {
        this.mScrimBehind = scrimView;
        this.mScrimInFront = scrimView2;
        this.mScrimStateListener = triConsumer;
        this.mScrimVisibleListener = consumer;
        this.mContext = scrimView.getContext();
        this.mUnlockMethodCache = UnlockMethodCache.getInstance(this.mContext);
        this.mDarkenWhileDragging = !this.mUnlockMethodCache.canSkipBouncer();
        this.mKeyguardUpdateMonitor = KeyguardUpdateMonitor.getInstance(this.mContext);
        this.mKeyguardVisibilityCallback = new KeyguardVisibilityCallback();
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardVisibilityCallback);
        this.mScrimBehindAlphaResValue = this.mContext.getResources().getFloat(C1775R$dimen.scrim_behind_alpha);
        this.mHandler = getHandler();
        this.mTimeTicker = new AlarmTimeout(alarmManager, new AlarmManager.OnAlarmListener() {
            public final void onAlarm() {
                ScrimController.this.onHideWallpaperTimeout();
            }
        }, "hide_aod_wallpaper", this.mHandler);
        this.mWakeLock = createWakeLock();
        this.mScrimBehindAlpha = this.mScrimBehindAlphaResValue;
        this.mDozeParameters = dozeParameters;
        keyguardMonitor.addCallback(new KeyguardMonitor.Callback() {
            public void onKeyguardFadingAwayChanged() {
                ScrimController.this.setKeyguardFadingAway(keyguardMonitor.isKeyguardFadingAway(), keyguardMonitor.getKeyguardFadingAwayDuration());
            }
        });
        this.mColorExtractor = (SysuiColorExtractor) Dependency.get(SysuiColorExtractor.class);
        this.mColorExtractor.addOnColorsChangedListener(this);
        this.mColors = this.mColorExtractor.getNeutralColors();
        this.mNeedsDrawableColorUpdate = true;
        ScrimState[] values = ScrimState.values();
        for (int i = 0; i < values.length; i++) {
            values[i].init(this.mScrimInFront, this.mScrimBehind, this.mDozeParameters);
            values[i].setScrimBehindAlphaKeyguard(this.mScrimBehindAlphaKeyguard);
        }
        this.mScrimBehind.setDefaultFocusHighlightEnabled(false);
        this.mScrimInFront.setDefaultFocusHighlightEnabled(false);
        updateScrims();
    }

    public void transitionTo(ScrimState scrimState) {
        transitionTo(scrimState, (Callback) null);
    }

    public void transitionTo(ScrimState scrimState, Callback callback) {
        if (scrimState != this.mState) {
            if (DEBUG) {
                Log.d("ScrimController", "State changed to: " + scrimState);
            }
            if (scrimState != ScrimState.UNINITIALIZED) {
                ScrimState scrimState2 = this.mState;
                this.mState = scrimState;
                Trace.traceCounter(4096, "scrim_state", this.mState.getIndex());
                Callback callback2 = this.mCallback;
                if (callback2 != null) {
                    callback2.onCancelled();
                }
                this.mCallback = callback;
                scrimState.prepare(scrimState2);
                this.mScreenBlankingCallbackCalled = false;
                this.mAnimationDelay = 0;
                this.mBlankScreen = scrimState.getBlanksScreen();
                this.mAnimateChange = scrimState.getAnimateChange();
                this.mAnimationDuration = scrimState.getAnimationDuration();
                this.mCurrentInFrontTint = scrimState.getFrontTint();
                this.mCurrentBehindTint = scrimState.getBehindTint();
                this.mCurrentInFrontAlpha = scrimState.getFrontAlpha();
                this.mCurrentBehindAlpha = scrimState.getBehindAlpha();
                if (Float.isNaN(this.mCurrentBehindAlpha) || Float.isNaN(this.mCurrentInFrontAlpha)) {
                    throw new IllegalStateException("Scrim opacity is NaN for state: " + scrimState + ", front: " + this.mCurrentInFrontAlpha + ", back: " + this.mCurrentBehindAlpha);
                }
                applyExpansionToAlpha();
                this.mScrimInFront.setFocusable(!scrimState.isLowPowerState());
                this.mScrimBehind.setFocusable(!scrimState.isLowPowerState());
                Runnable runnable = this.mPendingFrameCallback;
                if (runnable != null) {
                    this.mScrimBehind.removeCallbacks(runnable);
                    this.mPendingFrameCallback = null;
                }
                if (this.mHandler.hasCallbacks(this.mBlankingTransitionRunnable)) {
                    this.mHandler.removeCallbacks(this.mBlankingTransitionRunnable);
                    this.mBlankingTransitionRunnable = null;
                }
                this.mNeedsDrawableColorUpdate = scrimState != ScrimState.BRIGHTNESS_MIRROR;
                if (this.mState.isLowPowerState()) {
                    holdWakeLock();
                }
                this.mWallpaperVisibilityTimedOut = false;
                if (shouldFadeAwayWallpaper()) {
                    this.mTimeTicker.schedule(this.mDozeParameters.getWallpaperAodDuration(), 1);
                } else {
                    this.mTimeTicker.cancel();
                }
                if (this.mKeyguardUpdateMonitor.needsSlowUnlockTransition() && this.mState == ScrimState.UNLOCKED) {
                    this.mScrimInFront.postOnAnimationDelayed(new Runnable() {
                        public final void run() {
                            ScrimController.this.scheduleUpdate();
                        }
                    }, 16);
                    this.mAnimationDelay = 100;
                } else if ((this.mDozeParameters.getAlwaysOn() || scrimState2 != ScrimState.AOD) && (this.mState != ScrimState.AOD || this.mDozeParameters.getDisplayNeedsBlanking())) {
                    scheduleUpdate();
                } else {
                    onPreDraw();
                }
                dispatchScrimState(this.mScrimBehind.getViewAlpha());
                return;
            }
            throw new IllegalArgumentException("Cannot change to UNINITIALIZED.");
        } else if (callback != null && this.mCallback != callback) {
            callback.onFinished();
        }
    }

    private boolean shouldFadeAwayWallpaper() {
        Callback callback;
        if (!this.mWallpaperSupportsAmbientMode) {
            return false;
        }
        if (this.mState == ScrimState.AOD && this.mDozeParameters.getAlwaysOn()) {
            return true;
        }
        if (this.mState != ScrimState.PULSING || (callback = this.mCallback) == null || !callback.shouldTimeoutWallpaper()) {
            return false;
        }
        return true;
    }

    public ScrimState getState() {
        return this.mState;
    }

    public void onTrackingStarted() {
        this.mTracking = true;
        this.mDarkenWhileDragging = true ^ this.mUnlockMethodCache.canSkipBouncer();
    }

    public void onExpandingFinished() {
        this.mTracking = false;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void onHideWallpaperTimeout() {
        ScrimState scrimState = this.mState;
        if (scrimState == ScrimState.AOD || scrimState == ScrimState.PULSING) {
            holdWakeLock();
            this.mWallpaperVisibilityTimedOut = true;
            this.mAnimateChange = true;
            this.mAnimationDuration = this.mDozeParameters.getWallpaperFadeOutDuration();
            scheduleUpdate();
        }
    }

    private void holdWakeLock() {
        if (!this.mWakeLockHeld) {
            WakeLock wakeLock = this.mWakeLock;
            if (wakeLock != null) {
                this.mWakeLockHeld = true;
                wakeLock.acquire("ScrimController");
                return;
            }
            Log.w("ScrimController", "Cannot hold wake lock, it has not been set yet");
        }
    }

    public void setPanelExpansion(float f) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException("Fraction should not be NaN");
        } else if (this.mExpansionFraction != f) {
            this.mExpansionFraction = f;
            ScrimState scrimState = this.mState;
            if ((scrimState == ScrimState.UNLOCKED || scrimState == ScrimState.KEYGUARD || scrimState == ScrimState.PULSING) && this.mExpansionAffectsAlpha) {
                applyExpansionToAlpha();
                if (!this.mUpdatePending) {
                    setOrAdaptCurrentAnimation(this.mScrimBehind);
                    setOrAdaptCurrentAnimation(this.mScrimInFront);
                    dispatchScrimState(this.mScrimBehind.getViewAlpha());
                    if (this.mWallpaperVisibilityTimedOut) {
                        this.mWallpaperVisibilityTimedOut = false;
                        this.mTimeTicker.schedule(this.mDozeParameters.getWallpaperAodDuration(), 1);
                    }
                }
            }
        }
    }

    private void setOrAdaptCurrentAnimation(View view) {
        if (!isAnimating(view)) {
            updateScrimColor(view, getCurrentScrimAlpha(view), getCurrentScrimTint(view));
            return;
        }
        ValueAnimator valueAnimator = (ValueAnimator) view.getTag(TAG_KEY_ANIM);
        float currentScrimAlpha = getCurrentScrimAlpha(view);
        view.setTag(TAG_START_ALPHA, Float.valueOf(((Float) view.getTag(TAG_START_ALPHA)).floatValue() + (currentScrimAlpha - ((Float) view.getTag(TAG_END_ALPHA)).floatValue())));
        view.setTag(TAG_END_ALPHA, Float.valueOf(currentScrimAlpha));
        valueAnimator.setCurrentPlayTime(valueAnimator.getCurrentPlayTime());
    }

    private void applyExpansionToAlpha() {
        if (this.mExpansionAffectsAlpha) {
            ScrimState scrimState = this.mState;
            if (scrimState == ScrimState.UNLOCKED) {
                this.mCurrentBehindAlpha = ((float) Math.pow((double) getInterpolatedFraction(), 0.800000011920929d)) * 0.7f;
                this.mCurrentInFrontAlpha = 0.0f;
            } else if (scrimState == ScrimState.KEYGUARD || scrimState == ScrimState.PULSING) {
                float interpolatedFraction = getInterpolatedFraction();
                float behindAlpha = this.mState.getBehindAlpha();
                if (this.mDarkenWhileDragging) {
                    this.mCurrentBehindAlpha = MathUtils.lerp(0.7f, behindAlpha, interpolatedFraction);
                    this.mCurrentInFrontAlpha = 0.0f;
                } else {
                    this.mCurrentBehindAlpha = MathUtils.lerp(0.0f, behindAlpha, interpolatedFraction);
                    this.mCurrentInFrontAlpha = 0.0f;
                }
                this.mCurrentBehindTint = ColorUtils.blendARGB(ScrimState.BOUNCER.getBehindTint(), this.mState.getBehindTint(), interpolatedFraction);
            }
            if (Float.isNaN(this.mCurrentBehindAlpha) || Float.isNaN(this.mCurrentInFrontAlpha)) {
                throw new IllegalStateException("Scrim opacity is NaN for state: " + this.mState + ", front: " + this.mCurrentInFrontAlpha + ", back: " + this.mCurrentBehindAlpha);
            }
        }
    }

    public void setAodFrontScrimAlpha(float f) {
        if (this.mState == ScrimState.AOD && this.mDozeParameters.getAlwaysOn() && this.mCurrentInFrontAlpha != f) {
            this.mCurrentInFrontAlpha = f;
            updateScrims();
        }
        ScrimState.AOD.setAodFrontScrimAlpha(f);
    }

    public void setWakeLockScreenSensorActive(boolean z) {
        for (ScrimState wakeLockScreenSensorActive : ScrimState.values()) {
            wakeLockScreenSensorActive.setWakeLockScreenSensorActive(z);
        }
        ScrimState scrimState = this.mState;
        if (scrimState == ScrimState.PULSING) {
            float behindAlpha = scrimState.getBehindAlpha();
            if (this.mCurrentBehindAlpha != behindAlpha) {
                this.mCurrentBehindAlpha = behindAlpha;
                if (!Float.isNaN(this.mCurrentBehindAlpha)) {
                    updateScrims();
                    return;
                }
                throw new IllegalStateException("Scrim opacity is NaN for state: " + this.mState + ", back: " + this.mCurrentBehindAlpha);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void scheduleUpdate() {
        if (!this.mUpdatePending) {
            this.mScrimBehind.invalidate();
            this.mScrimBehind.getViewTreeObserver().addOnPreDrawListener(this);
            this.mUpdatePending = true;
        }
    }

    /* access modifiers changed from: protected */
    public void updateScrims() {
        boolean z = true;
        if (this.mNeedsDrawableColorUpdate) {
            this.mNeedsDrawableColorUpdate = false;
            boolean z2 = this.mScrimInFront.getViewAlpha() != 0.0f && !this.mBlankScreen;
            boolean z3 = this.mScrimBehind.getViewAlpha() != 0.0f && !this.mBlankScreen;
            this.mScrimInFront.setColors(this.mColors, z2);
            this.mScrimBehind.setColors(this.mColors, z3);
            this.mScrimBehindAlpha = Math.max(this.mScrimBehindAlphaResValue, ((float) ColorUtils.calculateMinimumBackgroundAlpha(this.mColors.supportsDarkText() ? -16777216 : -1, this.mColors.getMainColor(), 4.5f)) / 255.0f);
            dispatchScrimState(this.mScrimBehind.getViewAlpha());
        }
        ScrimState scrimState = this.mState;
        boolean z4 = (scrimState == ScrimState.AOD || scrimState == ScrimState.PULSING) && this.mWallpaperVisibilityTimedOut;
        ScrimState scrimState2 = this.mState;
        if (!(scrimState2 == ScrimState.PULSING || scrimState2 == ScrimState.AOD) || !this.mKeyguardOccluded) {
            z = false;
        }
        if (z4 || z) {
            this.mCurrentBehindAlpha = 1.0f;
        }
        setScrimInFrontAlpha(this.mCurrentInFrontAlpha);
        setScrimBehindAlpha(this.mCurrentBehindAlpha);
        dispatchScrimsVisible();
    }

    private void dispatchScrimState(float f) {
        this.mScrimStateListener.accept(this.mState, Float.valueOf(f), this.mScrimInFront.getColors());
    }

    /* access modifiers changed from: private */
    public void dispatchScrimsVisible() {
        int i = (this.mScrimInFront.getViewAlpha() == 1.0f || this.mScrimBehind.getViewAlpha() == 1.0f) ? 2 : (this.mScrimInFront.getViewAlpha() == 0.0f && this.mScrimBehind.getViewAlpha() == 0.0f) ? 0 : 1;
        if (this.mScrimsVisibility != i) {
            this.mScrimsVisibility = i;
            this.mScrimVisibleListener.accept(Integer.valueOf(i));
        }
    }

    private float getInterpolatedFraction() {
        float f = (this.mExpansionFraction * 1.2f) - 0.2f;
        if (f <= 0.0f) {
            return 0.0f;
        }
        return (float) (1.0d - ((1.0d - Math.cos(Math.pow((double) (1.0f - f), 2.0d) * 3.141590118408203d)) * 0.5d));
    }

    private void setScrimBehindAlpha(float f) {
        setScrimAlpha(this.mScrimBehind, f);
    }

    private void setScrimInFrontAlpha(float f) {
        setScrimAlpha(this.mScrimInFront, f);
    }

    private void setScrimAlpha(ScrimView scrimView, float f) {
        boolean z = false;
        if (f == 0.0f) {
            scrimView.setClickable(false);
        } else {
            if (this.mState != ScrimState.AOD) {
                z = true;
            }
            scrimView.setClickable(z);
        }
        updateScrim(scrimView, f);
    }

    private void updateScrimColor(View view, float f, int i) {
        float max = Math.max(0.0f, Math.min(1.0f, f));
        if (view instanceof ScrimView) {
            ScrimView scrimView = (ScrimView) view;
            Trace.traceCounter(4096, view == this.mScrimInFront ? "front_scrim_alpha" : "back_scrim_alpha", (int) (255.0f * max));
            Trace.traceCounter(4096, view == this.mScrimInFront ? "front_scrim_tint" : "back_scrim_tint", Color.alpha(i));
            scrimView.setTint(i);
            scrimView.setViewAlpha(max);
        } else {
            view.setAlpha(max);
        }
        dispatchScrimsVisible();
    }

    private void startScrimAnimation(final View view, float f) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(view, view instanceof ScrimView ? ((ScrimView) view).getTint() : 0) {
            private final /* synthetic */ View f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScrimController.this.lambda$startScrimAnimation$0$ScrimController(this.f$1, this.f$2, valueAnimator);
            }
        });
        ofFloat.setInterpolator(this.mInterpolator);
        ofFloat.setStartDelay(this.mAnimationDelay);
        ofFloat.setDuration(this.mAnimationDuration);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            private Callback lastCallback = ScrimController.this.mCallback;

            public void onAnimationEnd(Animator animator) {
                ScrimController.this.onFinished(this.lastCallback);
                view.setTag(ScrimController.TAG_KEY_ANIM, (Object) null);
                ScrimController.this.dispatchScrimsVisible();
                if (!ScrimController.this.mDeferFinishedListener && ScrimController.this.mOnAnimationFinished != null) {
                    ScrimController.this.mOnAnimationFinished.run();
                    Runnable unused = ScrimController.this.mOnAnimationFinished = null;
                }
            }
        });
        view.setTag(TAG_START_ALPHA, Float.valueOf(f));
        view.setTag(TAG_END_ALPHA, Float.valueOf(getCurrentScrimAlpha(view)));
        view.setTag(TAG_KEY_ANIM, ofFloat);
        ofFloat.start();
    }

    public /* synthetic */ void lambda$startScrimAnimation$0$ScrimController(View view, int i, ValueAnimator valueAnimator) {
        float floatValue = ((Float) view.getTag(TAG_START_ALPHA)).floatValue();
        float floatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        updateScrimColor(view, MathUtils.constrain(MathUtils.lerp(floatValue, getCurrentScrimAlpha(view), floatValue2), 0.0f, 1.0f), ColorUtils.blendARGB(i, getCurrentScrimTint(view), floatValue2));
        dispatchScrimsVisible();
    }

    private float getCurrentScrimAlpha(View view) {
        if (view == this.mScrimInFront) {
            return this.mCurrentInFrontAlpha;
        }
        if (view == this.mScrimBehind) {
            return this.mCurrentBehindAlpha;
        }
        throw new IllegalArgumentException("Unknown scrim view");
    }

    private int getCurrentScrimTint(View view) {
        if (view == this.mScrimInFront) {
            return this.mCurrentInFrontTint;
        }
        if (view == this.mScrimBehind) {
            return this.mCurrentBehindTint;
        }
        throw new IllegalArgumentException("Unknown scrim view");
    }

    public boolean onPreDraw() {
        this.mScrimBehind.getViewTreeObserver().removeOnPreDrawListener(this);
        this.mUpdatePending = false;
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onStart();
        }
        updateScrims();
        if (this.mOnAnimationFinished == null || isAnimating(this.mScrimInFront) || isAnimating(this.mScrimBehind)) {
            return true;
        }
        this.mOnAnimationFinished.run();
        this.mOnAnimationFinished = null;
        return true;
    }

    private void onFinished() {
        onFinished(this.mCallback);
    }

    /* access modifiers changed from: private */
    public void onFinished(Callback callback) {
        if (this.mWakeLockHeld) {
            this.mWakeLock.release("ScrimController");
            this.mWakeLockHeld = false;
        }
        if (callback != null) {
            callback.onFinished();
            if (callback == this.mCallback) {
                this.mCallback = null;
            }
        }
        if (this.mState == ScrimState.UNLOCKED) {
            this.mCurrentInFrontTint = 0;
            this.mCurrentBehindTint = 0;
        }
    }

    private boolean isAnimating(View view) {
        return view.getTag(TAG_KEY_ANIM) != null;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setOnAnimationFinished(Runnable runnable) {
        this.mOnAnimationFinished = runnable;
    }

    private void updateScrim(ScrimView scrimView, float f) {
        Callback callback;
        float viewAlpha = scrimView.getViewAlpha();
        ValueAnimator valueAnimator = (ValueAnimator) ViewState.getChildTag(scrimView, TAG_KEY_ANIM);
        boolean z = false;
        if (valueAnimator != null) {
            if (this.mAnimateChange) {
                this.mDeferFinishedListener = true;
            }
            cancelAnimator(valueAnimator);
            this.mDeferFinishedListener = false;
        }
        if (this.mPendingFrameCallback == null) {
            if (this.mBlankScreen) {
                blankDisplay();
                return;
            }
            if (!this.mScreenBlankingCallbackCalled && (callback = this.mCallback) != null) {
                callback.onDisplayBlanked();
                this.mScreenBlankingCallbackCalled = true;
            }
            if (scrimView == this.mScrimBehind) {
                dispatchScrimState(f);
            }
            boolean z2 = f != viewAlpha;
            if (scrimView.getTint() != getCurrentScrimTint(scrimView)) {
                z = true;
            }
            if (!z2 && !z) {
                onFinished();
            } else if (this.mAnimateChange) {
                startScrimAnimation(scrimView, viewAlpha);
            } else {
                updateScrimColor(scrimView, f, getCurrentScrimTint(scrimView));
                onFinished();
            }
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void cancelAnimator(ValueAnimator valueAnimator) {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    private void blankDisplay() {
        updateScrimColor(this.mScrimInFront, 1.0f, -16777216);
        this.mPendingFrameCallback = new Runnable() {
            public final void run() {
                ScrimController.this.lambda$blankDisplay$2$ScrimController();
            }
        };
        doOnTheNextFrame(this.mPendingFrameCallback);
    }

    public /* synthetic */ void lambda$blankDisplay$2$ScrimController() {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onDisplayBlanked();
            this.mScreenBlankingCallbackCalled = true;
        }
        this.mBlankingTransitionRunnable = new Runnable() {
            public final void run() {
                ScrimController.this.lambda$blankDisplay$1$ScrimController();
            }
        };
        int i = this.mScreenOn ? 32 : 500;
        if (DEBUG) {
            Log.d("ScrimController", "Fading out scrims with delay: " + i);
        }
        this.mHandler.postDelayed(this.mBlankingTransitionRunnable, (long) i);
    }

    public /* synthetic */ void lambda$blankDisplay$1$ScrimController() {
        this.mBlankingTransitionRunnable = null;
        this.mPendingFrameCallback = null;
        this.mBlankScreen = false;
        updateScrims();
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void doOnTheNextFrame(Runnable runnable) {
        this.mScrimBehind.postOnAnimationDelayed(runnable, 32);
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public Handler getHandler() {
        return new Handler();
    }

    public void setScrimBehindChangeRunnable(Runnable runnable) {
        this.mScrimBehind.setChangeRunnable(runnable);
    }

    public void onColorsChanged(ColorExtractor colorExtractor, int i) {
        this.mColors = this.mColorExtractor.getNeutralColors();
        this.mNeedsDrawableColorUpdate = true;
        scheduleUpdate();
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public WakeLock createWakeLock() {
        return new DelayedWakeLock(this.mHandler, WakeLock.createPartial(this.mContext, "Scrims"));
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println(" ScrimController: ");
        printWriter.print("  state: ");
        printWriter.println(this.mState);
        printWriter.print("  frontScrim:");
        printWriter.print(" viewAlpha=");
        printWriter.print(this.mScrimInFront.getViewAlpha());
        printWriter.print(" alpha=");
        printWriter.print(this.mCurrentInFrontAlpha);
        printWriter.print(" tint=0x");
        printWriter.println(Integer.toHexString(this.mScrimInFront.getTint()));
        printWriter.print("  backScrim:");
        printWriter.print(" viewAlpha=");
        printWriter.print(this.mScrimBehind.getViewAlpha());
        printWriter.print(" alpha=");
        printWriter.print(this.mCurrentBehindAlpha);
        printWriter.print(" tint=0x");
        printWriter.println(Integer.toHexString(this.mScrimBehind.getTint()));
        printWriter.print("  mTracking=");
        printWriter.println(this.mTracking);
        printWriter.print("  mExpansionFraction=");
        printWriter.println(this.mExpansionFraction);
    }

    public void setWallpaperSupportsAmbientMode(boolean z) {
        this.mWallpaperSupportsAmbientMode = z;
        ScrimState[] values = ScrimState.values();
        for (ScrimState wallpaperSupportsAmbientMode : values) {
            wallpaperSupportsAmbientMode.setWallpaperSupportsAmbientMode(z);
        }
    }

    public void onScreenTurnedOn() {
        this.mScreenOn = true;
        if (this.mHandler.hasCallbacks(this.mBlankingTransitionRunnable)) {
            if (DEBUG) {
                Log.d("ScrimController", "Shorter blanking because screen turned on. All good.");
            }
            this.mHandler.removeCallbacks(this.mBlankingTransitionRunnable);
            this.mBlankingTransitionRunnable.run();
        }
    }

    public void onScreenTurnedOff() {
        this.mScreenOn = false;
    }

    public void setExpansionAffectsAlpha(boolean z) {
        this.mExpansionAffectsAlpha = z;
    }

    public void setKeyguardOccluded(boolean z) {
        this.mKeyguardOccluded = z;
        updateScrims();
    }

    public void setHasBackdrop(boolean z) {
        for (ScrimState hasBackdrop : ScrimState.values()) {
            hasBackdrop.setHasBackdrop(z);
        }
        ScrimState scrimState = this.mState;
        if (scrimState == ScrimState.AOD || scrimState == ScrimState.PULSING) {
            float behindAlpha = this.mState.getBehindAlpha();
            if (Float.isNaN(behindAlpha)) {
                throw new IllegalStateException("Scrim opacity is NaN for state: " + this.mState + ", back: " + this.mCurrentBehindAlpha);
            } else if (this.mCurrentBehindAlpha != behindAlpha) {
                this.mCurrentBehindAlpha = behindAlpha;
                updateScrims();
            }
        }
    }

    /* access modifiers changed from: private */
    public void setKeyguardFadingAway(boolean z, long j) {
        for (ScrimState keyguardFadingAway : ScrimState.values()) {
            keyguardFadingAway.setKeyguardFadingAway(z, j);
        }
    }

    public void setLaunchingAffordanceWithPreview(boolean z) {
        for (ScrimState launchingAffordanceWithPreview : ScrimState.values()) {
            launchingAffordanceWithPreview.setLaunchingAffordanceWithPreview(z);
        }
    }

    private class KeyguardVisibilityCallback extends KeyguardUpdateMonitorCallback {
        private KeyguardVisibilityCallback() {
        }

        public void onKeyguardVisibilityChanged(boolean z) {
            boolean unused = ScrimController.this.mNeedsDrawableColorUpdate = true;
            ScrimController.this.scheduleUpdate();
        }
    }
}
