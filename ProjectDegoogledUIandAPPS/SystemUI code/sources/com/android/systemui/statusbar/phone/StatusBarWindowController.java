package com.android.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Binder;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.Trace;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1778R$integer;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.google.android.collect.Lists;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class StatusBarWindowController implements RemoteInputController.Callback, Dumpable, ConfigurationController.ConfigurationListener {
    private final IActivityManager mActivityManager;
    private int mBarHeight;
    private final ArrayList<WeakReference<StatusBarWindowCallback>> mCallbacks;
    private final SysuiColorExtractor mColorExtractor;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final State mCurrentState;
    private long mCurrentTimeout;
    private final DozeParameters mDozeParameters;
    private ForcePluginOpenListener mForcePluginOpenListener;
    private boolean mHasTopUi;
    private boolean mHasTopUiChanged;
    private final KeyguardBypassController mKeyguardBypassController;
    private final Display.Mode mKeyguardDisplayMode;
    /* access modifiers changed from: private */
    public boolean mKeyguardScreenRotation;
    private OtherwisedCollapsedListener mListener;
    private final long mLockScreenDisplayTimeout;
    private WindowManager.LayoutParams mLp;
    private final WindowManager.LayoutParams mLpChanged;
    private float mScreenBrightnessDoze;
    private final StatusBarStateController.StateListener mStateListener;
    private ViewGroup mStatusBarView;
    private final WindowManager mWindowManager;

    public interface ForcePluginOpenListener {
        void onChange(boolean z);
    }

    public interface OtherwisedCollapsedListener {
        void setWouldOtherwiseCollapse(boolean z);
    }

    public StatusBarWindowController(Context context, StatusBarStateController statusBarStateController, ConfigurationController configurationController, KeyguardBypassController keyguardBypassController) {
        this(context, (WindowManager) context.getSystemService(WindowManager.class), ActivityManager.getService(), DozeParameters.getInstance(context), statusBarStateController, configurationController, keyguardBypassController);
    }

    @VisibleForTesting
    public StatusBarWindowController(Context context, WindowManager windowManager, IActivityManager iActivityManager, DozeParameters dozeParameters, StatusBarStateController statusBarStateController, ConfigurationController configurationController, KeyguardBypassController keyguardBypassController) {
        this.mCurrentState = new State();
        this.mCallbacks = Lists.newArrayList();
        this.mColorExtractor = (SysuiColorExtractor) Dependency.get(SysuiColorExtractor.class);
        this.mStateListener = new StatusBarStateController.StateListener() {
            public void onStateChanged(int i) {
                StatusBarWindowController.this.setStatusBarState(i);
            }

            public void onDozingChanged(boolean z) {
                StatusBarWindowController.this.setDozing(z);
            }
        };
        this.mContext = context;
        this.mWindowManager = windowManager;
        this.mActivityManager = iActivityManager;
        this.mKeyguardScreenRotation = shouldEnableKeyguardScreenRotation();
        this.mDozeParameters = dozeParameters;
        this.mScreenBrightnessDoze = this.mDozeParameters.getScreenBrightnessDoze();
        this.mLpChanged = new WindowManager.LayoutParams();
        this.mKeyguardBypassController = keyguardBypassController;
        this.mLockScreenDisplayTimeout = (long) context.getResources().getInteger(C1778R$integer.config_lockScreenDisplayTimeout);
        ((SysuiStatusBarStateController) statusBarStateController).addCallback(this.mStateListener, 1);
        configurationController.addCallback(this);
        Display.Mode[] supportedModes = context.getDisplay().getSupportedModes();
        Display.Mode mode = context.getDisplay().getMode();
        this.mKeyguardDisplayMode = (Display.Mode) Arrays.stream(supportedModes).filter(new Predicate(context.getResources().getInteger(C1778R$integer.config_keyguardRefreshRate), mode) {
            private final /* synthetic */ int f$0;
            private final /* synthetic */ Display.Mode f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final boolean test(Object obj) {
                return StatusBarWindowController.lambda$new$0(this.f$0, this.f$1, (Display.Mode) obj);
            }
        }).findFirst().orElse((Object) null);
    }

    static /* synthetic */ boolean lambda$new$0(int i, Display.Mode mode, Display.Mode mode2) {
        return ((int) mode2.getRefreshRate()) == i && mode2.getPhysicalWidth() == mode.getPhysicalWidth() && mode2.getPhysicalHeight() == mode.getPhysicalHeight();
    }

    public void registerCallback(StatusBarWindowCallback statusBarWindowCallback) {
        int i = 0;
        while (i < this.mCallbacks.size()) {
            if (this.mCallbacks.get(i).get() != statusBarWindowCallback) {
                i++;
            } else {
                return;
            }
        }
        this.mCallbacks.add(new WeakReference(statusBarWindowCallback));
    }

    /* access modifiers changed from: private */
    public boolean shouldEnableKeyguardScreenRotation() {
        Resources resources = this.mContext.getResources();
        boolean z = Settings.System.getInt(this.mContext.getContentResolver(), "accelerometer_rotation", 1) != 0;
        boolean z2 = Settings.System.getInt(this.mContext.getContentResolver(), "lockscreen_rotation", 0) != 0;
        if (SystemProperties.getBoolean("lockscreen.rot_override", false)) {
            return true;
        }
        if (!resources.getBoolean(C1773R$bool.config_enableLockScreenRotation) || !z2 || !z) {
            return false;
        }
        return true;
    }

    public void add(ViewGroup viewGroup, int i) {
        this.mLp = new WindowManager.LayoutParams(-1, i, 2000, -2138832824, -3);
        this.mLp.token = new Binder();
        WindowManager.LayoutParams layoutParams = this.mLp;
        layoutParams.gravity = 48;
        layoutParams.softInputMode = 16;
        layoutParams.setTitle(StatusBar.TAG);
        this.mLp.packageName = this.mContext.getPackageName();
        WindowManager.LayoutParams layoutParams2 = this.mLp;
        layoutParams2.layoutInDisplayCutoutMode = 1;
        this.mStatusBarView = viewGroup;
        this.mBarHeight = i;
        this.mWindowManager.addView(this.mStatusBarView, layoutParams2);
        this.mLpChanged.copyFrom(this.mLp);
        new SettingsObserver(new Handler()).observe(this.mContext);
        onThemeChanged();
    }

    public ViewGroup getStatusBarView() {
        return this.mStatusBarView;
    }

    public void setDozeScreenBrightness(int i) {
        this.mScreenBrightnessDoze = ((float) i) / 255.0f;
    }

    private void setKeyguardDark(boolean z) {
        int systemUiVisibility = this.mStatusBarView.getSystemUiVisibility();
        this.mStatusBarView.setSystemUiVisibility(z ? systemUiVisibility | 16 | 8192 : systemUiVisibility & -17 & -8193);
    }

    private void applyKeyguardFlags(State state) {
        if (state.keyguardShowing) {
            this.mLpChanged.privateFlags |= 1024;
        } else {
            this.mLpChanged.privateFlags &= -1025;
        }
        boolean z = true;
        boolean z2 = state.scrimsVisibility == 2;
        if (!(state.keyguardShowing || (state.dozing && this.mDozeParameters.getAlwaysOn())) || state.backdropShowing || z2) {
            this.mLpChanged.flags &= -1048577;
        } else {
            this.mLpChanged.flags |= 1048576;
        }
        if (state.dozing) {
            this.mLpChanged.privateFlags |= 524288;
        } else {
            this.mLpChanged.privateFlags &= -524289;
        }
        if (this.mKeyguardDisplayMode != null) {
            if (!this.mKeyguardBypassController.getBypassEnabled() || state.statusBarState != 1 || state.keyguardFadingAway || state.keyguardGoingAway) {
                z = false;
            }
            if (state.dozing || z) {
                this.mLpChanged.preferredDisplayModeId = this.mKeyguardDisplayMode.getModeId();
            } else {
                this.mLpChanged.preferredDisplayModeId = 0;
            }
            Trace.setCounter("display_mode_id", (long) this.mLpChanged.preferredDisplayModeId);
        }
    }

    private void adjustScreenOrientation(State state) {
        if (!state.isKeyguardShowingAndNotOccluded() && !state.dozing) {
            this.mLpChanged.screenOrientation = -1;
        } else if (this.mKeyguardScreenRotation) {
            this.mLpChanged.screenOrientation = 2;
        } else {
            this.mLpChanged.screenOrientation = 5;
        }
    }

    private void applyFocusableFlag(State state) {
        boolean z = state.statusBarFocusable && state.panelExpanded;
        if ((state.bouncerShowing && (state.keyguardOccluded || state.keyguardNeedsInput)) || ((NotificationRemoteInputManager.ENABLE_REMOTE_INPUT && state.remoteInputActive) || state.bubbleExpanded)) {
            WindowManager.LayoutParams layoutParams = this.mLpChanged;
            layoutParams.flags &= -9;
            layoutParams.flags &= -131073;
        } else if (state.isKeyguardShowingAndNotOccluded() || z) {
            WindowManager.LayoutParams layoutParams2 = this.mLpChanged;
            layoutParams2.flags &= -9;
            layoutParams2.flags |= 131072;
        } else {
            WindowManager.LayoutParams layoutParams3 = this.mLpChanged;
            layoutParams3.flags |= 8;
            layoutParams3.flags &= -131073;
        }
        this.mLpChanged.softInputMode = 16;
    }

    private void applyForceShowNavigationFlag(State state) {
        if (state.panelExpanded || state.bouncerShowing || (NotificationRemoteInputManager.ENABLE_REMOTE_INPUT && state.remoteInputActive)) {
            this.mLpChanged.privateFlags |= 8388608;
            return;
        }
        this.mLpChanged.privateFlags &= -8388609;
    }

    private void applyHeight(State state) {
        boolean isExpanded = isExpanded(state);
        if (state.forcePluginOpen) {
            OtherwisedCollapsedListener otherwisedCollapsedListener = this.mListener;
            if (otherwisedCollapsedListener != null) {
                otherwisedCollapsedListener.setWouldOtherwiseCollapse(isExpanded);
            }
            isExpanded = true;
        }
        if (isExpanded) {
            this.mLpChanged.height = -1;
            return;
        }
        this.mLpChanged.height = this.mBarHeight;
    }

    private boolean isExpanded(State state) {
        return !state.forceCollapsed && (state.isKeyguardShowingAndNotOccluded() || state.panelVisible || state.keyguardFadingAway || state.bouncerShowing || state.headsUpShowing || state.bubblesShowing || state.scrimsVisibility != 0);
    }

    private void applyFitsSystemWindows(State state) {
        boolean z = !state.isKeyguardShowingAndNotOccluded();
        ViewGroup viewGroup = this.mStatusBarView;
        if (viewGroup != null && viewGroup.getFitsSystemWindows() != z) {
            this.mStatusBarView.setFitsSystemWindows(z);
            this.mStatusBarView.requestApplyInsets();
        }
    }

    private void applyUserActivityTimeout(State state) {
        long j;
        updateSettings();
        if (!state.isKeyguardShowingAndNotOccluded() || state.statusBarState != 1 || state.qsExpanded) {
            this.mLpChanged.userActivityTimeout = -1;
            return;
        }
        WindowManager.LayoutParams layoutParams = this.mLpChanged;
        if (state.bouncerShowing) {
            j = 10000;
        } else {
            j = this.mCurrentTimeout;
        }
        layoutParams.userActivityTimeout = j;
    }

    private void applyInputFeatures(State state) {
        if (!state.isKeyguardShowingAndNotOccluded() || state.statusBarState != 1 || state.qsExpanded || state.forceUserActivity) {
            this.mLpChanged.inputFeatures &= -5;
            return;
        }
        this.mLpChanged.inputFeatures |= 4;
    }

    private void applyStatusBarColorSpaceAgnosticFlag(State state) {
        if (!isExpanded(state)) {
            this.mLpChanged.privateFlags |= 16777216;
            return;
        }
        this.mLpChanged.privateFlags &= -16777217;
    }

    /* access modifiers changed from: private */
    public void apply(State state) {
        applyKeyguardFlags(state);
        applyForceStatusBarVisibleFlag(state);
        applyFocusableFlag(state);
        applyForceShowNavigationFlag(state);
        adjustScreenOrientation(state);
        applyHeight(state);
        applyUserActivityTimeout(state);
        applyInputFeatures(state);
        applyFitsSystemWindows(state);
        applyModalFlag(state);
        applyBrightness(state);
        applyHasTopUi(state);
        applyNotTouchable(state);
        applyStatusBarColorSpaceAgnosticFlag(state);
        WindowManager.LayoutParams layoutParams = this.mLp;
        if (!(layoutParams == null || layoutParams.copyFrom(this.mLpChanged) == 0)) {
            this.mWindowManager.updateViewLayout(this.mStatusBarView, this.mLp);
        }
        boolean z = this.mHasTopUi;
        boolean z2 = this.mHasTopUiChanged;
        if (z != z2) {
            try {
                this.mActivityManager.setHasTopUi(z2);
            } catch (RemoteException e) {
                Log.e("StatusBarWindowController", "Failed to call setHasTopUi", e);
            }
            this.mHasTopUi = this.mHasTopUiChanged;
        }
        notifyStateChangedCallbacks();
    }

    public void notifyStateChangedCallbacks() {
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            StatusBarWindowCallback statusBarWindowCallback = (StatusBarWindowCallback) this.mCallbacks.get(i).get();
            if (statusBarWindowCallback != null) {
                State state = this.mCurrentState;
                statusBarWindowCallback.onStateChanged(state.keyguardShowing, state.keyguardOccluded, state.bouncerShowing);
            }
        }
    }

    private void applyForceStatusBarVisibleFlag(State state) {
        if (state.forceStatusBarVisible || state.forcePluginOpen) {
            this.mLpChanged.privateFlags |= 4096;
            return;
        }
        this.mLpChanged.privateFlags &= -4097;
    }

    private void applyModalFlag(State state) {
        if (state.headsUpShowing) {
            this.mLpChanged.flags |= 32;
            return;
        }
        this.mLpChanged.flags &= -33;
    }

    private void applyBrightness(State state) {
        if (state.forceDozeBrightness) {
            this.mLpChanged.screenBrightness = this.mScreenBrightnessDoze;
            return;
        }
        this.mLpChanged.screenBrightness = -1.0f;
    }

    private void applyHasTopUi(State state) {
        this.mHasTopUiChanged = state.forceHasTopUi || isExpanded(state);
    }

    private void applyNotTouchable(State state) {
        if (state.notTouchable) {
            this.mLpChanged.flags |= 16;
            return;
        }
        this.mLpChanged.flags &= -17;
    }

    public void setKeyguardShowing(boolean z) {
        State state = this.mCurrentState;
        state.keyguardShowing = z;
        apply(state);
    }

    public void setKeyguardOccluded(boolean z) {
        State state = this.mCurrentState;
        state.keyguardOccluded = z;
        apply(state);
    }

    public void setKeyguardNeedsInput(boolean z) {
        State state = this.mCurrentState;
        state.keyguardNeedsInput = z;
        apply(state);
    }

    public void setPanelVisible(boolean z) {
        State state = this.mCurrentState;
        state.panelVisible = z;
        state.statusBarFocusable = z;
        apply(state);
    }

    public void setStatusBarFocusable(boolean z) {
        State state = this.mCurrentState;
        state.statusBarFocusable = z;
        apply(state);
    }

    public void setBouncerShowing(boolean z) {
        State state = this.mCurrentState;
        state.bouncerShowing = z;
        apply(state);
    }

    public void setBackdropShowing(boolean z) {
        State state = this.mCurrentState;
        state.backdropShowing = z;
        apply(state);
    }

    public void setKeyguardFadingAway(boolean z) {
        State state = this.mCurrentState;
        state.keyguardFadingAway = z;
        apply(state);
    }

    public void setQsExpanded(boolean z) {
        State state = this.mCurrentState;
        state.qsExpanded = z;
        apply(state);
    }

    public void setScrimsVisibility(int i) {
        State state = this.mCurrentState;
        state.scrimsVisibility = i;
        apply(state);
    }

    public void setHeadsUpShowing(boolean z) {
        State state = this.mCurrentState;
        state.headsUpShowing = z;
        apply(state);
    }

    public void setWallpaperSupportsAmbientMode(boolean z) {
        State state = this.mCurrentState;
        state.wallpaperSupportsAmbientMode = z;
        apply(state);
    }

    /* access modifiers changed from: private */
    public void setStatusBarState(int i) {
        State state = this.mCurrentState;
        state.statusBarState = i;
        apply(state);
    }

    public void setForceStatusBarVisible(boolean z) {
        State state = this.mCurrentState;
        state.forceStatusBarVisible = z;
        apply(state);
    }

    public void setForceWindowCollapsed(boolean z) {
        State state = this.mCurrentState;
        state.forceCollapsed = z;
        apply(state);
    }

    public void setPanelExpanded(boolean z) {
        State state = this.mCurrentState;
        state.panelExpanded = z;
        apply(state);
    }

    public void onRemoteInputActive(boolean z) {
        State state = this.mCurrentState;
        state.remoteInputActive = z;
        apply(state);
    }

    public void setForceDozeBrightness(boolean z) {
        State state = this.mCurrentState;
        state.forceDozeBrightness = z;
        apply(state);
    }

    public void setDozing(boolean z) {
        State state = this.mCurrentState;
        state.dozing = z;
        apply(state);
    }

    public void setBarHeight(int i) {
        this.mBarHeight = i;
        apply(this.mCurrentState);
    }

    public void setForcePluginOpen(boolean z) {
        State state = this.mCurrentState;
        state.forcePluginOpen = z;
        apply(state);
        ForcePluginOpenListener forcePluginOpenListener = this.mForcePluginOpenListener;
        if (forcePluginOpenListener != null) {
            forcePluginOpenListener.onChange(z);
        }
    }

    public boolean getForcePluginOpen() {
        return this.mCurrentState.forcePluginOpen;
    }

    public void setNotTouchable(boolean z) {
        State state = this.mCurrentState;
        state.notTouchable = z;
        apply(state);
    }

    public void setBubblesShowing(boolean z) {
        State state = this.mCurrentState;
        state.bubblesShowing = z;
        apply(state);
    }

    public boolean getBubblesShowing() {
        return this.mCurrentState.bubblesShowing;
    }

    public void setBubbleExpanded(boolean z) {
        State state = this.mCurrentState;
        state.bubbleExpanded = z;
        apply(state);
    }

    public boolean getPanelExpanded() {
        return this.mCurrentState.panelExpanded;
    }

    public void setStateListener(OtherwisedCollapsedListener otherwisedCollapsedListener) {
        this.mListener = otherwisedCollapsedListener;
    }

    public void setForcePluginOpenListener(ForcePluginOpenListener forcePluginOpenListener) {
        this.mForcePluginOpenListener = forcePluginOpenListener;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("StatusBarWindowController:");
        printWriter.println("  mKeyguardDisplayMode=" + this.mKeyguardDisplayMode);
        printWriter.println(this.mCurrentState);
    }

    public boolean isShowingWallpaper() {
        return !this.mCurrentState.backdropShowing;
    }

    public void onThemeChanged() {
        if (this.mStatusBarView != null) {
            setKeyguardDark(this.mColorExtractor.getNeutralColors().supportsDarkText());
        }
    }

    public void setKeyguardGoingAway(boolean z) {
        State state = this.mCurrentState;
        state.keyguardGoingAway = z;
        apply(state);
    }

    public boolean getForceHasTopUi() {
        return this.mCurrentState.forceHasTopUi;
    }

    public void setForceHasTopUi(boolean z) {
        State state = this.mCurrentState;
        state.forceHasTopUi = z;
        apply(state);
    }

    private static class State {
        boolean backdropShowing;
        boolean bouncerShowing;
        boolean bubbleExpanded;
        boolean bubblesShowing;
        boolean dozing;
        boolean forceCollapsed;
        boolean forceDozeBrightness;
        boolean forceHasTopUi;
        boolean forcePluginOpen;
        boolean forceStatusBarVisible;
        boolean forceUserActivity;
        boolean headsUpShowing;
        boolean keyguardFadingAway;
        boolean keyguardGoingAway;
        boolean keyguardNeedsInput;
        boolean keyguardOccluded;
        boolean keyguardShowing;
        boolean notTouchable;
        boolean panelExpanded;
        boolean panelVisible;
        boolean qsExpanded;
        boolean remoteInputActive;
        int scrimsVisibility;
        boolean statusBarFocusable;
        int statusBarState;
        boolean wallpaperSupportsAmbientMode;

        private State() {
        }

        /* access modifiers changed from: private */
        public boolean isKeyguardShowingAndNotOccluded() {
            return this.keyguardShowing && !this.keyguardOccluded;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Window State {");
            sb.append("\n");
            for (Field field : State.class.getDeclaredFields()) {
                sb.append("  ");
                try {
                    sb.append(field.getName());
                    sb.append(": ");
                    sb.append(field.get(this));
                } catch (IllegalAccessException unused) {
                }
                sb.append("\n");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    private class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        public void observe(Context context) {
            context.getContentResolver().registerContentObserver(Settings.System.getUriFor("accelerometer_rotation"), false, this);
            context.getContentResolver().registerContentObserver(Settings.System.getUriFor("lockscreen_rotation"), false, this);
            context.getContentResolver().registerContentObserver(Settings.System.getUriFor("lockscreen_timeout"), false, this, -1);
        }

        public void onChange(boolean z) {
            StatusBarWindowController statusBarWindowController = StatusBarWindowController.this;
            boolean unused = statusBarWindowController.mKeyguardScreenRotation = statusBarWindowController.shouldEnableKeyguardScreenRotation();
            StatusBarWindowController statusBarWindowController2 = StatusBarWindowController.this;
            statusBarWindowController2.apply(statusBarWindowController2.mCurrentState);
            StatusBarWindowController.this.updateSettings();
        }
    }

    /* access modifiers changed from: private */
    public void updateSettings() {
        this.mCurrentTimeout = (long) Settings.System.getIntForUser(this.mContext.getContentResolver(), "lockscreen_timeout", 15000, -2);
    }
}
