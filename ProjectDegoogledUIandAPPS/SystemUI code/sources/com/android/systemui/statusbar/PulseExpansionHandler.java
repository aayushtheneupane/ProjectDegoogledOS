package com.android.systemui.statusbar;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.Dependency;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.Interpolators;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationRoundnessManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.phone.HeadsUpManagerPhone;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.ShadeController;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PulseExpansionHandler.kt */
public final class PulseExpansionHandler implements Gefingerpoken {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final float RUBBERBAND_FACTOR_STATIC = RUBBERBAND_FACTOR_STATIC;
    private static final int SPRING_BACK_ANIMATION_LENGTH_MS = SPRING_BACK_ANIMATION_LENGTH_MS;
    private boolean bouncerShowing;
    private final KeyguardBypassController bypassController;
    private ExpansionCallback expansionCallback;
    private final HeadsUpManagerPhone headsUpManager;
    private boolean isExpanding;
    private boolean isWakingToShadeLocked;
    private boolean leavingLockscreen;
    private boolean mDraggedFarEnough;
    private float mEmptyDragAmount;
    private final FalsingManager mFalsingManager;
    private float mInitialTouchX;
    private float mInitialTouchY;
    private final int mMinDragDistance;
    private final PowerManager mPowerManager;
    private boolean mPulsing;
    private boolean mReachedWakeUpHeight;
    private ExpandableView mStartingChild;
    private final int[] mTemp2 = new int[2];
    private final float mTouchSlop;
    private float mWakeUpHeight;
    private Runnable pulseExpandAbortListener;
    private boolean qsExpanded;
    private final NotificationRoundnessManager roundnessManager;
    private ShadeController shadeController;
    private NotificationStackScrollLayout stackScroller;
    private final StatusBarStateController statusBarStateController;
    private VelocityTracker velocityTracker;
    private final NotificationWakeUpCoordinator wakeUpCoordinator;

    /* compiled from: PulseExpansionHandler.kt */
    public interface ExpansionCallback {
        void setEmptyDragAmount(float f);
    }

    public PulseExpansionHandler(Context context, NotificationWakeUpCoordinator notificationWakeUpCoordinator, KeyguardBypassController keyguardBypassController, HeadsUpManagerPhone headsUpManagerPhone, NotificationRoundnessManager notificationRoundnessManager, StatusBarStateController statusBarStateController2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(notificationWakeUpCoordinator, "wakeUpCoordinator");
        Intrinsics.checkParameterIsNotNull(keyguardBypassController, "bypassController");
        Intrinsics.checkParameterIsNotNull(headsUpManagerPhone, "headsUpManager");
        Intrinsics.checkParameterIsNotNull(notificationRoundnessManager, "roundnessManager");
        Intrinsics.checkParameterIsNotNull(statusBarStateController2, "statusBarStateController");
        this.wakeUpCoordinator = notificationWakeUpCoordinator;
        this.bypassController = keyguardBypassController;
        this.headsUpManager = headsUpManagerPhone;
        this.roundnessManager = notificationRoundnessManager;
        this.statusBarStateController = statusBarStateController2;
        this.mMinDragDistance = context.getResources().getDimensionPixelSize(C1775R$dimen.keyguard_drag_down_min_distance);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        Intrinsics.checkExpressionValueIsNotNull(viewConfiguration, "ViewConfiguration.get(context)");
        this.mTouchSlop = (float) viewConfiguration.getScaledTouchSlop();
        Object obj = Dependency.get(FalsingManager.class);
        Intrinsics.checkExpressionValueIsNotNull(obj, "Dependency.get(FalsingManager::class.java)");
        this.mFalsingManager = (FalsingManager) obj;
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
    }

    /* compiled from: PulseExpansionHandler.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final boolean isExpanding() {
        return this.isExpanding;
    }

    private final void setExpanding(boolean z) {
        boolean z2 = this.isExpanding != z;
        this.isExpanding = z;
        this.bypassController.setPulseExpanding(z);
        if (z2) {
            if (z) {
                NotificationEntry topEntry = this.headsUpManager.getTopEntry();
                if (topEntry != null) {
                    this.roundnessManager.setTrackingHeadsUp(topEntry.getRow());
                }
            } else {
                this.roundnessManager.setTrackingHeadsUp((ExpandableNotificationRow) null);
                if (!this.leavingLockscreen) {
                    this.bypassController.maybePerformPendingUnlock();
                    Runnable runnable = this.pulseExpandAbortListener;
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            }
            this.headsUpManager.unpinAll(true);
        }
    }

    public final boolean getLeavingLockscreen() {
        return this.leavingLockscreen;
    }

    public final boolean isWakingToShadeLocked() {
        return this.isWakingToShadeLocked;
    }

    private final boolean isFalseTouch() {
        return this.mFalsingManager.isFalseTouch();
    }

    public final void setQsExpanded(boolean z) {
        this.qsExpanded = z;
    }

    public final void setPulseExpandAbortListener(Runnable runnable) {
        this.pulseExpandAbortListener = runnable;
    }

    public final void setBouncerShowing(boolean z) {
        this.bouncerShowing = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(motionEvent, "event");
        return maybeStartExpansion(motionEvent);
    }

    private final boolean maybeStartExpansion(MotionEvent motionEvent) {
        if (!this.wakeUpCoordinator.getCanShowPulsingHuns() || this.qsExpanded || this.bouncerShowing) {
            return false;
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.mDraggedFarEnough = false;
                setExpanding(false);
                this.leavingLockscreen = false;
                this.mStartingChild = null;
                this.mInitialTouchY = y;
                this.mInitialTouchX = x;
            } else if (actionMasked == 1) {
                recycleVelocityTracker();
            } else if (actionMasked == 2) {
                float f = y - this.mInitialTouchY;
                if (f > this.mTouchSlop && f > Math.abs(x - this.mInitialTouchX)) {
                    this.mFalsingManager.onStartExpandingFromPulse();
                    setExpanding(true);
                    captureStartingChild(this.mInitialTouchX, this.mInitialTouchY);
                    this.mInitialTouchY = y;
                    this.mInitialTouchX = x;
                    this.mWakeUpHeight = this.wakeUpCoordinator.getWakeUpHeight();
                    this.mReachedWakeUpHeight = false;
                    return true;
                }
            } else if (actionMasked == 3) {
                recycleVelocityTracker();
            }
            return false;
        }
        Intrinsics.throwNpe();
        throw null;
    }

    private final void recycleVelocityTracker() {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
        }
        this.velocityTracker = null;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(motionEvent, "event");
        if (!this.isExpanding) {
            return maybeStartExpansion(motionEvent);
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
            float y = motionEvent.getY() - this.mInitialTouchY;
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1) {
                VelocityTracker velocityTracker3 = this.velocityTracker;
                if (velocityTracker3 != null) {
                    velocityTracker3.computeCurrentVelocity(1000);
                    boolean z = false;
                    if (y > ((float) 0)) {
                        VelocityTracker velocityTracker4 = this.velocityTracker;
                        if (velocityTracker4 == null) {
                            Intrinsics.throwNpe();
                            throw null;
                        } else if (velocityTracker4.getYVelocity() > ((float) -1000) && this.statusBarStateController.getState() != 0) {
                            z = true;
                        }
                    }
                    if (this.mFalsingManager.isUnlockingDisabled() || isFalseTouch() || !z) {
                        cancelExpansion();
                    } else {
                        finishExpansion();
                    }
                    recycleVelocityTracker();
                } else {
                    Intrinsics.throwNpe();
                    throw null;
                }
            } else if (actionMasked == 2) {
                updateExpansionHeight(y);
            } else if (actionMasked == 3) {
                cancelExpansion();
                recycleVelocityTracker();
            }
            return this.isExpanding;
        }
        Intrinsics.throwNpe();
        throw null;
    }

    private final void finishExpansion() {
        resetClock();
        ExpandableView expandableView = this.mStartingChild;
        if (expandableView != null) {
            if (expandableView != null) {
                setUserLocked(expandableView, false);
                this.mStartingChild = null;
            } else {
                Intrinsics.throwNpe();
                throw null;
            }
        }
        ShadeController shadeController2 = this.shadeController;
        if (shadeController2 != null) {
            if (shadeController2.isDozing()) {
                this.isWakingToShadeLocked = true;
                this.wakeUpCoordinator.setWillWakeUp(true);
                PowerManager powerManager = this.mPowerManager;
                if (powerManager != null) {
                    powerManager.wakeUp(SystemClock.uptimeMillis(), 4, "com.android.systemui:PULSEDRAG");
                } else {
                    Intrinsics.throwNpe();
                    throw null;
                }
            }
            ShadeController shadeController3 = this.shadeController;
            if (shadeController3 != null) {
                shadeController3.goToLockedShade(this.mStartingChild);
                this.leavingLockscreen = true;
                setExpanding(false);
                ExpandableView expandableView2 = this.mStartingChild;
                if (expandableView2 instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView2;
                    if (expandableNotificationRow != null) {
                        expandableNotificationRow.onExpandedByGesture(true);
                    } else {
                        Intrinsics.throwNpe();
                        throw null;
                    }
                }
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("shadeController");
                throw null;
            }
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("shadeController");
            throw null;
        }
    }

    private final void updateExpansionHeight(float f) {
        float f2;
        float f3 = 0.0f;
        float max = Math.max(f, 0.0f);
        if (!this.mReachedWakeUpHeight && f > this.mWakeUpHeight) {
            this.mReachedWakeUpHeight = true;
        }
        ExpandableView expandableView = this.mStartingChild;
        if (expandableView == null) {
            if (this.mReachedWakeUpHeight) {
                f3 = this.mWakeUpHeight;
            }
            this.wakeUpCoordinator.setNotificationsVisibleForExpansion(f > f3, true, true);
            f2 = Math.max(this.mWakeUpHeight, max);
        } else if (expandableView != null) {
            int min = Math.min((int) (((float) expandableView.getCollapsedHeight()) + max), expandableView.getMaxContentHeight());
            expandableView.setActualHeight(min);
            f2 = Math.max((float) min, max);
        } else {
            Intrinsics.throwNpe();
            throw null;
        }
        setEmptyDragAmount(this.wakeUpCoordinator.setPulseHeight(f2) * RUBBERBAND_FACTOR_STATIC);
    }

    private final void captureStartingChild(float f, float f2) {
        if (this.mStartingChild == null && !this.bypassController.getBypassEnabled()) {
            this.mStartingChild = findView(f, f2);
            ExpandableView expandableView = this.mStartingChild;
            if (expandableView == null) {
                return;
            }
            if (expandableView != null) {
                setUserLocked(expandableView, true);
            } else {
                Intrinsics.throwNpe();
                throw null;
            }
        }
    }

    /* access modifiers changed from: private */
    public final void setEmptyDragAmount(float f) {
        this.mEmptyDragAmount = f;
        ExpansionCallback expansionCallback2 = this.expansionCallback;
        if (expansionCallback2 != null) {
            expansionCallback2.setEmptyDragAmount(f);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("expansionCallback");
            throw null;
        }
    }

    private final void reset(ExpandableView expandableView) {
        if (expandableView.getActualHeight() == expandableView.getCollapsedHeight()) {
            setUserLocked(expandableView, false);
            return;
        }
        ObjectAnimator ofInt = ObjectAnimator.ofInt(expandableView, "actualHeight", new int[]{expandableView.getActualHeight(), expandableView.getCollapsedHeight()});
        Intrinsics.checkExpressionValueIsNotNull(ofInt, "anim");
        ofInt.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        ofInt.setDuration((long) SPRING_BACK_ANIMATION_LENGTH_MS);
        ofInt.addListener(new PulseExpansionHandler$reset$1(this, expandableView));
        ofInt.start();
    }

    /* access modifiers changed from: private */
    public final void setUserLocked(ExpandableView expandableView, boolean z) {
        if (expandableView instanceof ExpandableNotificationRow) {
            ((ExpandableNotificationRow) expandableView).setUserLocked(z);
        }
    }

    private final void resetClock() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.mEmptyDragAmount, 0.0f});
        Intrinsics.checkExpressionValueIsNotNull(ofFloat, "anim");
        ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        ofFloat.setDuration((long) SPRING_BACK_ANIMATION_LENGTH_MS);
        ofFloat.addUpdateListener(new PulseExpansionHandler$resetClock$1(this));
        ofFloat.start();
    }

    private final void cancelExpansion() {
        setExpanding(false);
        this.mFalsingManager.onExpansionFromPulseStopped();
        ExpandableView expandableView = this.mStartingChild;
        if (expandableView == null) {
            resetClock();
        } else if (expandableView != null) {
            reset(expandableView);
            this.mStartingChild = null;
        } else {
            Intrinsics.throwNpe();
            throw null;
        }
        this.wakeUpCoordinator.setNotificationsVisibleForExpansion(false, true, false);
    }

    private final ExpandableView findView(float f, float f2) {
        NotificationStackScrollLayout notificationStackScrollLayout = this.stackScroller;
        if (notificationStackScrollLayout != null) {
            notificationStackScrollLayout.getLocationOnScreen(this.mTemp2);
            int[] iArr = this.mTemp2;
            float f3 = f + ((float) iArr[0]);
            float f4 = f2 + ((float) iArr[1]);
            NotificationStackScrollLayout notificationStackScrollLayout2 = this.stackScroller;
            if (notificationStackScrollLayout2 != null) {
                ExpandableView childAtRawPosition = notificationStackScrollLayout2.getChildAtRawPosition(f3, f4);
                if (childAtRawPosition == null || !childAtRawPosition.isContentExpandable()) {
                    return null;
                }
                return childAtRawPosition;
            }
            Intrinsics.throwUninitializedPropertyAccessException("stackScroller");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("stackScroller");
        throw null;
    }

    public final void setUp(NotificationStackScrollLayout notificationStackScrollLayout, ExpansionCallback expansionCallback2, ShadeController shadeController2) {
        Intrinsics.checkParameterIsNotNull(notificationStackScrollLayout, "stackScroller");
        Intrinsics.checkParameterIsNotNull(expansionCallback2, "expansionCallback");
        Intrinsics.checkParameterIsNotNull(shadeController2, "shadeController");
        this.expansionCallback = expansionCallback2;
        this.shadeController = shadeController2;
        this.stackScroller = notificationStackScrollLayout;
    }

    public final void setPulsing(boolean z) {
        this.mPulsing = z;
    }

    public final void onStartedWakingUp() {
        this.isWakingToShadeLocked = false;
    }
}
