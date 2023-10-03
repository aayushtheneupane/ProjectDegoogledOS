package com.android.systemui.statusbar.notification;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.MathUtils;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationAdapter;
import android.view.RemoteAnimationTarget;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.systemui.Interpolators;
import com.android.systemui.statusbar.notification.ActivityLaunchAnimator;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.phone.NotificationPanelView;
import com.android.systemui.statusbar.phone.StatusBarWindowView;

public class ActivityLaunchAnimator {
    private boolean mAnimationPending;
    /* access modifiers changed from: private */
    public boolean mAnimationRunning;
    /* access modifiers changed from: private */
    public Callback mCallback;
    private boolean mIsLaunchForActivity;
    /* access modifiers changed from: private */
    public final NotificationListContainer mNotificationContainer;
    /* access modifiers changed from: private */
    public final NotificationPanelView mNotificationPanel;
    /* access modifiers changed from: private */
    public final StatusBarWindowView mStatusBarWindow;
    private final Runnable mTimeoutRunnable = new Runnable() {
        public final void run() {
            ActivityLaunchAnimator.this.lambda$new$0$ActivityLaunchAnimator();
        }
    };
    /* access modifiers changed from: private */
    public final float mWindowCornerRadius;

    public interface Callback {
        boolean areLaunchAnimationsEnabled();

        void onExpandAnimationFinished(boolean z);

        void onExpandAnimationTimedOut();

        void onLaunchAnimationCancelled();
    }

    public /* synthetic */ void lambda$new$0$ActivityLaunchAnimator() {
        setAnimationPending(false);
        this.mCallback.onExpandAnimationTimedOut();
    }

    public ActivityLaunchAnimator(StatusBarWindowView statusBarWindowView, Callback callback, NotificationPanelView notificationPanelView, NotificationListContainer notificationListContainer) {
        this.mNotificationPanel = notificationPanelView;
        this.mNotificationContainer = notificationListContainer;
        this.mStatusBarWindow = statusBarWindowView;
        this.mCallback = callback;
        this.mWindowCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(statusBarWindowView.getResources());
    }

    public RemoteAnimationAdapter getLaunchAnimation(View view, boolean z) {
        if (!(view instanceof ExpandableNotificationRow) || !this.mCallback.areLaunchAnimationsEnabled() || z) {
            return null;
        }
        return new RemoteAnimationAdapter(new AnimationRunner((ExpandableNotificationRow) view), 400, 250);
    }

    public boolean isAnimationPending() {
        return this.mAnimationPending;
    }

    public void setLaunchResult(int i, boolean z) {
        this.mIsLaunchForActivity = z;
        setAnimationPending((i == 2 || i == 0) && this.mCallback.areLaunchAnimationsEnabled());
    }

    public boolean isLaunchForActivity() {
        return this.mIsLaunchForActivity;
    }

    /* access modifiers changed from: private */
    public void setAnimationPending(boolean z) {
        this.mAnimationPending = z;
        this.mStatusBarWindow.setExpandAnimationPending(z);
        if (z) {
            this.mStatusBarWindow.postDelayed(this.mTimeoutRunnable, 500);
        } else {
            this.mStatusBarWindow.removeCallbacks(this.mTimeoutRunnable);
        }
    }

    public boolean isAnimationRunning() {
        return this.mAnimationRunning;
    }

    class AnimationRunner extends IRemoteAnimationRunner.Stub {
        /* access modifiers changed from: private */
        public float mCornerRadius;
        private boolean mIsFullScreenLaunch = true;
        /* access modifiers changed from: private */
        public final float mNotificationCornerRadius;
        /* access modifiers changed from: private */
        public final ExpandAnimationParameters mParams;
        private final ExpandableNotificationRow mSourceNotification;
        private final SyncRtSurfaceTransactionApplier mSyncRtTransactionApplier;
        private final Rect mWindowCrop = new Rect();

        public AnimationRunner(ExpandableNotificationRow expandableNotificationRow) {
            this.mSourceNotification = expandableNotificationRow;
            this.mParams = new ExpandAnimationParameters();
            this.mSyncRtTransactionApplier = new SyncRtSurfaceTransactionApplier(this.mSourceNotification);
            this.mNotificationCornerRadius = Math.max(this.mSourceNotification.getCurrentTopRoundness(), this.mSourceNotification.getCurrentBottomRoundness());
        }

        public void onAnimationStart(RemoteAnimationTarget[] remoteAnimationTargetArr, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) throws RemoteException {
            this.mSourceNotification.post(new Runnable(remoteAnimationTargetArr, iRemoteAnimationFinishedCallback) {
                private final /* synthetic */ RemoteAnimationTarget[] f$1;
                private final /* synthetic */ IRemoteAnimationFinishedCallback f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    ActivityLaunchAnimator.AnimationRunner.this.lambda$onAnimationStart$0$ActivityLaunchAnimator$AnimationRunner(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onAnimationStart$0$ActivityLaunchAnimator$AnimationRunner(RemoteAnimationTarget[] remoteAnimationTargetArr, final IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            final RemoteAnimationTarget primaryRemoteAnimationTarget = getPrimaryRemoteAnimationTarget(remoteAnimationTargetArr);
            if (primaryRemoteAnimationTarget == null) {
                ActivityLaunchAnimator.this.setAnimationPending(false);
                invokeCallback(iRemoteAnimationFinishedCallback);
                ActivityLaunchAnimator.this.mNotificationPanel.collapse(false, 1.0f);
                return;
            }
            boolean z = true;
            setExpandAnimationRunning(true);
            if (primaryRemoteAnimationTarget.position.y != 0 || primaryRemoteAnimationTarget.sourceContainerBounds.height() < ActivityLaunchAnimator.this.mNotificationPanel.getHeight()) {
                z = false;
            }
            this.mIsFullScreenLaunch = z;
            if (!this.mIsFullScreenLaunch) {
                ActivityLaunchAnimator.this.mNotificationPanel.collapseWithDuration(400);
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.mParams.startPosition = this.mSourceNotification.getLocationOnScreen();
            this.mParams.startTranslationZ = this.mSourceNotification.getTranslationZ();
            this.mParams.startClipTopAmount = this.mSourceNotification.getClipTopAmount();
            if (this.mSourceNotification.isChildInGroup()) {
                int clipTopAmount = this.mSourceNotification.getNotificationParent().getClipTopAmount();
                this.mParams.parentStartClipTopAmount = clipTopAmount;
                if (clipTopAmount != 0) {
                    float translationY = ((float) clipTopAmount) - this.mSourceNotification.getTranslationY();
                    if (translationY > 0.0f) {
                        this.mParams.startClipTopAmount = (int) Math.ceil((double) translationY);
                    }
                }
            }
            final int width = primaryRemoteAnimationTarget.sourceContainerBounds.width();
            final int actualHeight = this.mSourceNotification.getActualHeight() - this.mSourceNotification.getClipBottomAmount();
            final int width2 = this.mSourceNotification.getWidth();
            ofFloat.setDuration(400);
            ofFloat.setInterpolator(Interpolators.LINEAR);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    AnimationRunner.this.mParams.linearProgress = valueAnimator.getAnimatedFraction();
                    float interpolation = Interpolators.FAST_OUT_SLOW_IN.getInterpolation(AnimationRunner.this.mParams.linearProgress);
                    int lerp = (int) MathUtils.lerp((float) width2, (float) width, interpolation);
                    AnimationRunner.this.mParams.left = (int) (((float) (width - lerp)) / 2.0f);
                    AnimationRunner.this.mParams.right = AnimationRunner.this.mParams.left + lerp;
                    AnimationRunner.this.mParams.top = (int) MathUtils.lerp((float) AnimationRunner.this.mParams.startPosition[1], (float) primaryRemoteAnimationTarget.position.y, interpolation);
                    ExpandAnimationParameters access$600 = AnimationRunner.this.mParams;
                    RemoteAnimationTarget remoteAnimationTarget = primaryRemoteAnimationTarget;
                    access$600.bottom = (int) MathUtils.lerp((float) (AnimationRunner.this.mParams.startPosition[1] + actualHeight), (float) (remoteAnimationTarget.position.y + remoteAnimationTarget.sourceContainerBounds.bottom), interpolation);
                    AnimationRunner animationRunner = AnimationRunner.this;
                    float unused = animationRunner.mCornerRadius = MathUtils.lerp(animationRunner.mNotificationCornerRadius, ActivityLaunchAnimator.this.mWindowCornerRadius, interpolation);
                    AnimationRunner.this.applyParamsToWindow(primaryRemoteAnimationTarget);
                    AnimationRunner animationRunner2 = AnimationRunner.this;
                    animationRunner2.applyParamsToNotification(animationRunner2.mParams);
                    AnimationRunner animationRunner3 = AnimationRunner.this;
                    animationRunner3.applyParamsToNotificationList(animationRunner3.mParams);
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    AnimationRunner.this.setExpandAnimationRunning(false);
                    AnimationRunner.this.invokeCallback(iRemoteAnimationFinishedCallback);
                }
            });
            ofFloat.start();
            ActivityLaunchAnimator.this.setAnimationPending(false);
        }

        /* access modifiers changed from: private */
        public void invokeCallback(IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            try {
                iRemoteAnimationFinishedCallback.onAnimationFinished();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private RemoteAnimationTarget getPrimaryRemoteAnimationTarget(RemoteAnimationTarget[] remoteAnimationTargetArr) {
            for (RemoteAnimationTarget remoteAnimationTarget : remoteAnimationTargetArr) {
                if (remoteAnimationTarget.mode == 0) {
                    return remoteAnimationTarget;
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        public void setExpandAnimationRunning(boolean z) {
            ActivityLaunchAnimator.this.mNotificationPanel.setLaunchingNotification(z);
            this.mSourceNotification.setExpandAnimationRunning(z);
            ActivityLaunchAnimator.this.mStatusBarWindow.setExpandAnimationRunning(z);
            ActivityLaunchAnimator.this.mNotificationContainer.setExpandingNotification(z ? this.mSourceNotification : null);
            boolean unused = ActivityLaunchAnimator.this.mAnimationRunning = z;
            if (!z) {
                ActivityLaunchAnimator.this.mCallback.onExpandAnimationFinished(this.mIsFullScreenLaunch);
                applyParamsToNotification((ExpandAnimationParameters) null);
                applyParamsToNotificationList((ExpandAnimationParameters) null);
            }
        }

        /* access modifiers changed from: private */
        public void applyParamsToNotificationList(ExpandAnimationParameters expandAnimationParameters) {
            ActivityLaunchAnimator.this.mNotificationContainer.applyExpandAnimationParams(expandAnimationParameters);
            ActivityLaunchAnimator.this.mNotificationPanel.applyExpandAnimationParams(expandAnimationParameters);
        }

        /* access modifiers changed from: private */
        public void applyParamsToNotification(ExpandAnimationParameters expandAnimationParameters) {
            this.mSourceNotification.applyExpandAnimationParams(expandAnimationParameters);
        }

        /* access modifiers changed from: private */
        public void applyParamsToWindow(RemoteAnimationTarget remoteAnimationTarget) {
            Matrix matrix = new Matrix();
            matrix.postTranslate(0.0f, (float) (this.mParams.top - remoteAnimationTarget.position.y));
            Rect rect = this.mWindowCrop;
            ExpandAnimationParameters expandAnimationParameters = this.mParams;
            rect.set(expandAnimationParameters.left, 0, expandAnimationParameters.right, expandAnimationParameters.getHeight());
            SyncRtSurfaceTransactionApplier.SurfaceParams surfaceParams = new SyncRtSurfaceTransactionApplier.SurfaceParams(remoteAnimationTarget.leash, 1.0f, matrix, this.mWindowCrop, remoteAnimationTarget.prefixOrderIndex, this.mCornerRadius, true);
            this.mSyncRtTransactionApplier.scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{surfaceParams});
        }

        public void onAnimationCancelled() throws RemoteException {
            this.mSourceNotification.post(new Runnable() {
                public final void run() {
                    ActivityLaunchAnimator.AnimationRunner.this.mo13298x3974b8c7();
                }
            });
        }

        /* renamed from: lambda$onAnimationCancelled$1$ActivityLaunchAnimator$AnimationRunner */
        public /* synthetic */ void mo13298x3974b8c7() {
            ActivityLaunchAnimator.this.setAnimationPending(false);
            ActivityLaunchAnimator.this.mCallback.onLaunchAnimationCancelled();
        }
    }

    public static class ExpandAnimationParameters {
        int bottom;
        int left;
        float linearProgress;
        int parentStartClipTopAmount;
        int right;
        int startClipTopAmount;
        int[] startPosition;
        float startTranslationZ;
        int top;

        public int getTop() {
            return this.top;
        }

        public int getBottom() {
            return this.bottom;
        }

        public int getWidth() {
            return this.right - this.left;
        }

        public int getHeight() {
            return this.bottom - this.top;
        }

        public int getTopChange() {
            int i = this.startClipTopAmount;
            return Math.min((this.top - this.startPosition[1]) - (((float) i) != 0.0f ? (int) MathUtils.lerp(0.0f, (float) i, Interpolators.FAST_OUT_SLOW_IN.getInterpolation(this.linearProgress)) : 0), 0);
        }

        public float getProgress() {
            return this.linearProgress;
        }

        public float getProgress(long j, long j2) {
            return MathUtils.constrain(((this.linearProgress * 400.0f) - ((float) j)) / ((float) j2), 0.0f, 1.0f);
        }

        public int getStartClipTopAmount() {
            return this.startClipTopAmount;
        }

        public int getParentStartClipTopAmount() {
            return this.parentStartClipTopAmount;
        }

        public float getStartTranslationZ() {
            return this.startTranslationZ;
        }
    }
}
