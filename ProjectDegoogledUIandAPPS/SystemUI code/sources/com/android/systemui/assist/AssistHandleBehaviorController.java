package com.android.systemui.assist;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.DeviceConfig;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.app.AssistUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DumpController;
import com.android.systemui.Dumpable;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.phone.NavigationModeController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

public final class AssistHandleBehaviorController implements AssistHandleCallbacks, Dumpable {
    private static final AssistHandleBehavior DEFAULT_BEHAVIOR = AssistHandleBehavior.REMINDER_EXP;
    private static final long DEFAULT_SHOW_AND_GO_DURATION_MS = TimeUnit.SECONDS.toMillis(3);
    private final AssistUtils mAssistUtils;
    private final Map<AssistHandleBehavior, BehaviorController> mBehaviorMap;
    private final Context mContext;
    private AssistHandleBehavior mCurrentBehavior = AssistHandleBehavior.OFF;
    private final DeviceConfigHelper mDeviceConfigHelper;
    private final Handler mHandler;
    private long mHandlesLastHiddenAt;
    private boolean mHandlesShowing = false;
    private final Runnable mHideHandles = new Runnable() {
        public final void run() {
            AssistHandleBehaviorController.this.hideHandles();
        }
    };
    private boolean mInGesturalMode;
    private final Provider<ScreenDecorations> mScreenDecorations;
    private final Runnable mShowAndGo = new Runnable() {
        public final void run() {
            AssistHandleBehaviorController.this.showAndGoInternal();
        }
    };
    private long mShowAndGoEndsAt;

    interface BehaviorController {
        void dump(PrintWriter printWriter, String str) {
        }

        void onAssistHandlesRequested() {
        }

        void onAssistantGesturePerformed() {
        }

        void onModeActivated(Context context, AssistHandleCallbacks assistHandleCallbacks);

        void onModeDeactivated() {
        }
    }

    AssistHandleBehaviorController(Context context, AssistUtils assistUtils, Handler handler, Provider<ScreenDecorations> provider, DeviceConfigHelper deviceConfigHelper, Map<AssistHandleBehavior, BehaviorController> map, NavigationModeController navigationModeController, DumpController dumpController) {
        this.mContext = context;
        this.mAssistUtils = assistUtils;
        this.mHandler = handler;
        this.mScreenDecorations = provider;
        this.mDeviceConfigHelper = deviceConfigHelper;
        this.mBehaviorMap = map;
        this.mInGesturalMode = QuickStepContract.isGesturalMode(navigationModeController.addListener(new NavigationModeController.ModeChangedListener() {
            public final void onNavigationModeChanged(int i) {
                AssistHandleBehaviorController.this.handleNavigationModeChange(i);
            }
        }));
        setBehavior(getBehaviorMode());
        DeviceConfigHelper deviceConfigHelper2 = this.mDeviceConfigHelper;
        Handler handler2 = this.mHandler;
        Objects.requireNonNull(handler2);
        deviceConfigHelper2.addOnPropertiesChangedListener(new Executor(handler2) {
            private final /* synthetic */ Handler f$0;

            {
                this.f$0 = r1;
            }

            public final void execute(Runnable runnable) {
                this.f$0.post(runnable);
            }
        }, new DeviceConfig.OnPropertiesChangedListener() {
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                AssistHandleBehaviorController.this.lambda$new$0$AssistHandleBehaviorController(properties);
            }
        });
        dumpController.addListener(this);
    }

    public /* synthetic */ void lambda$new$0$AssistHandleBehaviorController(DeviceConfig.Properties properties) {
        if (properties.getKeyset().contains("assist_handles_behavior_mode")) {
            setBehavior(properties.getString("assist_handles_behavior_mode", (String) null));
        }
    }

    public void hide() {
        clearPendingCommands();
        this.mHandler.post(this.mHideHandles);
    }

    public void showAndGo() {
        clearPendingCommands();
        this.mHandler.post(this.mShowAndGo);
    }

    /* access modifiers changed from: private */
    public void showAndGoInternal() {
        maybeShowHandles(false);
        long showAndGoDuration = getShowAndGoDuration();
        this.mShowAndGoEndsAt = SystemClock.elapsedRealtime() + showAndGoDuration;
        this.mHandler.postDelayed(this.mHideHandles, showAndGoDuration);
    }

    public void showAndGoDelayed(long j, boolean z) {
        clearPendingCommands();
        if (z) {
            this.mHandler.post(this.mHideHandles);
        }
        this.mHandler.postDelayed(this.mShowAndGo, j);
    }

    public void showAndStay() {
        clearPendingCommands();
        this.mHandler.post(new Runnable() {
            public final void run() {
                AssistHandleBehaviorController.this.lambda$showAndStay$1$AssistHandleBehaviorController();
            }
        });
    }

    public /* synthetic */ void lambda$showAndStay$1$AssistHandleBehaviorController() {
        maybeShowHandles(true);
    }

    public long getShowAndGoRemainingTimeMs() {
        return Long.max(this.mShowAndGoEndsAt - SystemClock.elapsedRealtime(), 0);
    }

    /* access modifiers changed from: package-private */
    public boolean areHandlesShowing() {
        return this.mHandlesShowing;
    }

    /* access modifiers changed from: package-private */
    public void onAssistantGesturePerformed() {
        this.mBehaviorMap.get(this.mCurrentBehavior).onAssistantGesturePerformed();
    }

    /* access modifiers changed from: package-private */
    public void onAssistHandlesRequested() {
        if (this.mInGesturalMode) {
            this.mBehaviorMap.get(this.mCurrentBehavior).onAssistHandlesRequested();
        }
    }

    /* access modifiers changed from: package-private */
    public void setBehavior(AssistHandleBehavior assistHandleBehavior) {
        if (this.mCurrentBehavior != assistHandleBehavior) {
            if (!this.mBehaviorMap.containsKey(assistHandleBehavior)) {
                Log.e("AssistHandleBehavior", "Unsupported behavior requested: " + assistHandleBehavior.toString());
                return;
            }
            if (this.mInGesturalMode) {
                this.mBehaviorMap.get(this.mCurrentBehavior).onModeDeactivated();
                this.mBehaviorMap.get(assistHandleBehavior).onModeActivated(this.mContext, this);
            }
            this.mCurrentBehavior = assistHandleBehavior;
        }
    }

    private void setBehavior(String str) {
        try {
            setBehavior(AssistHandleBehavior.valueOf(str));
        } catch (IllegalArgumentException | NullPointerException e) {
            Log.e("AssistHandleBehavior", "Invalid behavior: " + str, e);
        }
    }

    private boolean handlesUnblocked(boolean z) {
        boolean z2 = z || SystemClock.elapsedRealtime() - this.mHandlesLastHiddenAt >= getShownFrequencyThreshold();
        ComponentName assistComponentForUser = this.mAssistUtils.getAssistComponentForUser(KeyguardUpdateMonitor.getCurrentUser());
        if (!z2 || assistComponentForUser == null) {
            return false;
        }
        return true;
    }

    private long getShownFrequencyThreshold() {
        return this.mDeviceConfigHelper.getLong("assist_handles_shown_frequency_threshold_ms", 0);
    }

    private long getShowAndGoDuration() {
        return this.mDeviceConfigHelper.getLong("assist_handles_show_and_go_duration_ms", DEFAULT_SHOW_AND_GO_DURATION_MS);
    }

    private String getBehaviorMode() {
        return this.mDeviceConfigHelper.getString("assist_handles_behavior_mode", DEFAULT_BEHAVIOR.toString());
    }

    private void maybeShowHandles(boolean z) {
        if (!this.mHandlesShowing && handlesUnblocked(z)) {
            ScreenDecorations screenDecorations = this.mScreenDecorations.get();
            if (screenDecorations == null) {
                Log.w("AssistHandleBehavior", "Couldn't show handles, ScreenDecorations unavailable");
                return;
            }
            this.mHandlesShowing = true;
            screenDecorations.lambda$setAssistHintVisible$1$ScreenDecorations(true);
        }
    }

    /* access modifiers changed from: private */
    public void hideHandles() {
        if (this.mHandlesShowing) {
            ScreenDecorations screenDecorations = this.mScreenDecorations.get();
            if (screenDecorations == null) {
                Log.w("AssistHandleBehavior", "Couldn't hide handles, ScreenDecorations unavailable");
                return;
            }
            this.mHandlesShowing = false;
            this.mHandlesLastHiddenAt = SystemClock.elapsedRealtime();
            screenDecorations.lambda$setAssistHintVisible$1$ScreenDecorations(false);
        }
    }

    /* access modifiers changed from: private */
    public void handleNavigationModeChange(int i) {
        boolean isGesturalMode = QuickStepContract.isGesturalMode(i);
        if (this.mInGesturalMode != isGesturalMode) {
            this.mInGesturalMode = isGesturalMode;
            if (this.mInGesturalMode) {
                this.mBehaviorMap.get(this.mCurrentBehavior).onModeActivated(this.mContext, this);
                return;
            }
            this.mBehaviorMap.get(this.mCurrentBehavior).onModeDeactivated();
            hide();
        }
    }

    private void clearPendingCommands() {
        this.mHandler.removeCallbacks(this.mHideHandles);
        this.mHandler.removeCallbacks(this.mShowAndGo);
        this.mShowAndGoEndsAt = 0;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setInGesturalModeForTest(boolean z) {
        this.mInGesturalMode = z;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("Current AssistHandleBehaviorController State:");
        printWriter.println("   mHandlesShowing=" + this.mHandlesShowing);
        printWriter.println("   mHandlesLastHiddenAt=" + this.mHandlesLastHiddenAt);
        printWriter.println("   mInGesturalMode=" + this.mInGesturalMode);
        printWriter.println("   Phenotype Flags:");
        printWriter.println("      assist_handles_show_and_go_duration_ms=" + getShowAndGoDuration());
        printWriter.println("      assist_handles_shown_frequency_threshold_ms=" + getShownFrequencyThreshold());
        printWriter.println("      assist_handles_behavior_mode=" + getBehaviorMode());
        printWriter.println("   mCurrentBehavior=" + this.mCurrentBehavior.toString());
        this.mBehaviorMap.get(this.mCurrentBehavior).dump(printWriter, "   ");
    }
}
