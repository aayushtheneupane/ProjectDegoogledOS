package com.android.systemui.statusbar.phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ParceledListSlice;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.MathUtils;
import android.util.StatsLog;
import android.view.IPinnedStackController;
import android.view.IPinnedStackListener;
import android.view.ISystemGestureExclusionListener;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.InputMonitor;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.util.havoc.ActionUtils;
import com.android.internal.util.havoc.Utils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.bubbles.BubbleController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.WindowManagerWrapper;
import com.android.systemui.statusbar.phone.EdgeBackGestureHandler;
import com.android.systemui.statusbar.phone.RegionSamplingHelper;
import java.io.PrintWriter;
import java.util.concurrent.Executor;

public class EdgeBackGestureHandler implements DisplayManager.DisplayListener {
    private static final int MAX_LONG_PRESS_TIMEOUT = SystemProperties.getInt("gestures.back_timeout", 250);
    private boolean mAllowGesture = false;
    private AssistManager mAssistManager;
    /* access modifiers changed from: private */
    public boolean mBackHapticEnabled;
    private int mBackSwipeType;
    /* access modifiers changed from: private */
    public boolean mBlockNextEvent;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                if (!Utils.isPackageInstalled(context, schemeSpecificPart)) {
                    String stringForUser = Settings.System.getStringForUser(context.getContentResolver(), "left_long_back_swipe_app_action", -2);
                    String stringForUser2 = Settings.System.getStringForUser(context.getContentResolver(), "right_long_back_swipe_app_action", -2);
                    if (schemeSpecificPart.equals(stringForUser)) {
                        EdgeBackGestureHandler.this.resetApplicationAction(true);
                    }
                    if (schemeSpecificPart.equals(stringForUser2)) {
                        EdgeBackGestureHandler.this.resetApplicationAction(false);
                    }
                }
            }
        }
    };
    private final Context mContext;
    /* access modifiers changed from: private */
    public final int mDisplayId;
    private final Point mDisplaySize = new Point();
    private final PointF mDownPoint = new PointF();
    private int mEdgeHeight;
    /* access modifiers changed from: private */
    public NavigationBarEdgePanel mEdgePanel;
    private WindowManager.LayoutParams mEdgePanelLp;
    private int mEdgeWidth;
    /* access modifiers changed from: private */
    public final Region mExcludeRegion = new Region();
    private final int mFingerOffset;
    private ISystemGestureExclusionListener mGestureExclusionListener = new ISystemGestureExclusionListener.Stub() {
        public void onSystemGestureExclusionChanged(int i, Region region, Region region2) {
            if (i == EdgeBackGestureHandler.this.mDisplayId) {
                EdgeBackGestureHandler.this.mMainExecutor.execute(new Runnable(region, region2) {
                    private final /* synthetic */ Region f$1;
                    private final /* synthetic */ Region f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        EdgeBackGestureHandler.C13312.this.mo15235x7f99a267(this.f$1, this.f$2);
                    }
                });
            }
        }

        /* renamed from: lambda$onSystemGestureExclusionChanged$0$EdgeBackGestureHandler$2 */
        public /* synthetic */ void mo15235x7f99a267(Region region, Region region2) {
            EdgeBackGestureHandler.this.mExcludeRegion.set(region);
            Region access$400 = EdgeBackGestureHandler.this.mUnrestrictedExcludeRegion;
            if (region2 != null) {
                region = region2;
            }
            access$400.set(region);
        }
    };
    private Handler mHandler;
    private final IPinnedStackListener.Stub mImeChangedListener = new IPinnedStackListener.Stub() {
        public void onActionsChanged(ParceledListSlice parceledListSlice) {
        }

        public void onListenerRegistered(IPinnedStackController iPinnedStackController) {
        }

        public void onMinimizedStateChanged(boolean z) {
        }

        public void onMovementBoundsChanged(Rect rect, Rect rect2, Rect rect3, boolean z, boolean z2, int i) {
        }

        public void onShelfVisibilityChanged(boolean z, int i) {
        }

        public void onImeVisibilityChanged(boolean z, int i) {
            EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
            if (!z) {
                i = 0;
            }
            int unused = edgeBackGestureHandler.mImeHeight = i;
        }
    };
    /* access modifiers changed from: private */
    public int mImeHeight = 0;
    private boolean mInRejectedExclusion = false;
    private InputEventReceiver mInputEventReceiver;
    private InputMonitor mInputMonitor;
    private IntentFilter mIntentFilter;
    private boolean mIsAttached;
    private boolean mIsEnabled;
    private boolean mIsGesturalModeEnabled;
    private boolean mIsInTransientImmersiveStickyState;
    /* access modifiers changed from: private */
    public boolean mIsOnLeftEdge;
    private int mLeftInset;
    private int mLeftLongSwipeAction;
    private final int mLongPressTimeout;
    private LongSwipeRunnable mLongSwipeAction = new LongSwipeRunnable();
    /* access modifiers changed from: private */
    public final Executor mMainExecutor;
    private final int mMinArrowPosition;
    private final int mNavBarHeight;
    private final OverviewProxyService mOverviewProxyService;
    private RegionSamplingHelper mRegionSamplingHelper;
    private int mRightInset;
    private int mRightLongSwipeAction;
    /* access modifiers changed from: private */
    public final Rect mSamplingRect = new Rect();
    private boolean mThresholdCrossed = false;
    private int mTimeout = 3000;
    private final float mTouchSlop;
    /* access modifiers changed from: private */
    public final Region mUnrestrictedExcludeRegion = new Region();
    /* access modifiers changed from: private */
    public final Vibrator mVibrator;
    private final WindowManager mWm;

    public void onDisplayAdded(int i) {
    }

    public void onDisplayRemoved(int i) {
    }

    public EdgeBackGestureHandler(Context context, OverviewProxyService overviewProxyService) {
        Resources resources = context.getResources();
        this.mContext = context;
        this.mDisplayId = context.getDisplayId();
        this.mMainExecutor = context.getMainExecutor();
        this.mWm = (WindowManager) context.getSystemService(WindowManager.class);
        this.mOverviewProxyService = overviewProxyService;
        this.mTouchSlop = ((float) ViewConfiguration.get(context).getScaledTouchSlop()) * 0.75f;
        this.mLongPressTimeout = Math.min(MAX_LONG_PRESS_TIMEOUT, ViewConfiguration.getLongPressTimeout());
        this.mNavBarHeight = resources.getDimensionPixelSize(C1775R$dimen.navigation_bar_frame_height);
        this.mMinArrowPosition = resources.getDimensionPixelSize(C1775R$dimen.navigation_edge_arrow_min_y);
        this.mFingerOffset = resources.getDimensionPixelSize(C1775R$dimen.navigation_edge_finger_offset);
        updateCurrentUserResources(resources);
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        this.mIntentFilter.addDataScheme("package");
        this.mAssistManager = (AssistManager) Dependency.get(AssistManager.class);
        this.mHandler = new Handler();
        setLongSwipeOptions();
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
    }

    public void updateCurrentUserResources(Resources resources) {
        this.mEdgeWidth = resources.getDimensionPixelSize(17105050);
    }

    private void updateEdgeHeightValue() {
        if (this.mDisplaySize != null) {
            int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "back_gesture_height", 0, -2);
            if (intForUser == 0) {
                this.mEdgeHeight = this.mDisplaySize.y;
            } else if (intForUser == 1) {
                this.mEdgeHeight = (this.mDisplaySize.y * 3) / 4;
            } else if (intForUser == 2) {
                this.mEdgeHeight = this.mDisplaySize.y / 2;
            } else {
                this.mEdgeHeight = this.mDisplaySize.y / 4;
            }
        }
    }

    public void onNavBarAttached() {
        this.mIsAttached = true;
        updateIsEnabled();
    }

    public void onNavBarDetached() {
        this.mIsAttached = false;
        updateIsEnabled();
    }

    public void onNavigationModeChanged(int i, Context context) {
        this.mIsGesturalModeEnabled = QuickStepContract.isGesturalMode(i);
        updateIsEnabled();
        updateCurrentUserResources(context.getResources());
    }

    public void setStateForBackArrowGesture() {
        NavigationBarEdgePanel navigationBarEdgePanel = this.mEdgePanel;
        if (navigationBarEdgePanel != null) {
            navigationBarEdgePanel.setBackArrowVisibility();
        }
    }

    public void setStateForBackGestureHaptic() {
        NavigationBarEdgePanel navigationBarEdgePanel = this.mEdgePanel;
        if (navigationBarEdgePanel != null) {
            navigationBarEdgePanel.setBackGestureHaptic();
        }
    }

    public void onSystemUiVisibilityChanged(int i) {
        this.mIsInTransientImmersiveStickyState = ((i & 4096) == 0 || (i & 134217728) == 0) ? false : true;
    }

    public void onSettingsChanged() {
        updateEdgeHeightValue();
    }

    private void disposeInputChannel() {
        InputEventReceiver inputEventReceiver = this.mInputEventReceiver;
        if (inputEventReceiver != null) {
            inputEventReceiver.dispose();
            this.mInputEventReceiver = null;
        }
        InputMonitor inputMonitor = this.mInputMonitor;
        if (inputMonitor != null) {
            inputMonitor.dispose();
            this.mInputMonitor = null;
        }
    }

    /* access modifiers changed from: private */
    public void resetApplicationAction(boolean z) {
        if (z) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "left_long_back_swipe_action", 0, -2);
            Settings.System.putStringForUser(this.mContext.getContentResolver(), "left_long_back_swipe_app_fr_action", "", -2);
            return;
        }
        Settings.System.putIntForUser(this.mContext.getContentResolver(), "right_long_back_swipe_action", 0, -2);
        Settings.System.putStringForUser(this.mContext.getContentResolver(), "right_long_back_swipe_app_fr_action", "", -2);
    }

    private void updateIsEnabled() {
        boolean z = this.mIsAttached && this.mIsGesturalModeEnabled;
        if (z != this.mIsEnabled) {
            this.mIsEnabled = z;
            disposeInputChannel();
            NavigationBarEdgePanel navigationBarEdgePanel = this.mEdgePanel;
            if (navigationBarEdgePanel != null) {
                this.mWm.removeView(navigationBarEdgePanel);
                this.mEdgePanel = null;
                this.mRegionSamplingHelper.stop();
                this.mRegionSamplingHelper = null;
            }
            if (!this.mIsEnabled) {
                WindowManagerWrapper.getInstance().removePinnedStackListener(this.mImeChangedListener);
                ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).unregisterDisplayListener(this);
                try {
                    WindowManagerGlobal.getWindowManagerService().unregisterSystemGestureExclusionListener(this.mGestureExclusionListener, this.mDisplayId);
                } catch (Exception e) {
                    Log.e("EdgeBackGestureHandler", "Failed to unregister window manager callbacks", e);
                }
                this.mContext.unregisterReceiver(this.mBroadcastReceiver);
                return;
            }
            updateDisplaySize();
            ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).registerDisplayListener(this, this.mContext.getMainThreadHandler());
            try {
                WindowManagerWrapper.getInstance().addPinnedStackListener(this.mImeChangedListener);
                WindowManagerGlobal.getWindowManagerService().registerSystemGestureExclusionListener(this.mGestureExclusionListener, this.mDisplayId);
            } catch (Exception e2) {
                Log.e("EdgeBackGestureHandler", "Failed to register window manager callbacks", e2);
            }
            this.mInputMonitor = InputManager.getInstance().monitorGestureInput("edge-swipe", this.mDisplayId);
            this.mInputEventReceiver = new SysUiInputEventReceiver(this.mInputMonitor.getInputChannel(), Looper.getMainLooper());
            this.mEdgePanel = new NavigationBarEdgePanel(this.mContext);
            this.mEdgePanelLp = new WindowManager.LayoutParams(this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.navigation_edge_panel_width), this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.navigation_edge_panel_height), 2024, 8388904, -3);
            WindowManager.LayoutParams layoutParams = this.mEdgePanelLp;
            layoutParams.privateFlags |= 16;
            layoutParams.setTitle("EdgeBackGestureHandler" + this.mDisplayId);
            this.mEdgePanelLp.accessibilityTitle = this.mContext.getString(C1784R$string.nav_bar_edge_panel);
            WindowManager.LayoutParams layoutParams2 = this.mEdgePanelLp;
            layoutParams2.windowAnimations = 0;
            this.mEdgePanel.setLayoutParams(layoutParams2);
            this.mWm.addView(this.mEdgePanel, this.mEdgePanelLp);
            this.mRegionSamplingHelper = new RegionSamplingHelper(this.mEdgePanel, new RegionSamplingHelper.SamplingCallback() {
                public void onRegionDarknessChanged(boolean z) {
                    EdgeBackGestureHandler.this.mEdgePanel.setIsDark(!z, true);
                }

                public Rect getSampledRegion(View view) {
                    return EdgeBackGestureHandler.this.mSamplingRect;
                }
            });
            this.mContext.registerReceiver(this.mBroadcastReceiver, this.mIntentFilter);
        }
    }

    /* access modifiers changed from: private */
    public void onInputEvent(InputEvent inputEvent) {
        if (inputEvent instanceof MotionEvent) {
            onMotionEvent((MotionEvent) inputEvent);
        }
    }

    private boolean isWithinTouchRegion(int i, int i2) {
        boolean z;
        if (i2 > this.mDisplaySize.y - Math.max(this.mImeHeight, this.mNavBarHeight)) {
            return false;
        }
        if (this.mEdgeHeight != 0 && i2 < (this.mDisplaySize.y - Math.max(this.mImeHeight, this.mNavBarHeight)) - this.mEdgeHeight) {
            return false;
        }
        int i3 = this.mEdgeWidth;
        if (i > this.mLeftInset + i3 && i < (this.mDisplaySize.x - i3) - this.mRightInset) {
            return false;
        }
        if (this.mIsInTransientImmersiveStickyState) {
            return true;
        }
        if (this.mBackSwipeType == 1 || ((this.mLeftLongSwipeAction != 0 && this.mIsOnLeftEdge) || (this.mRightLongSwipeAction != 0 && !this.mIsOnLeftEdge))) {
            z = this.mExcludeRegion.contains(i, i2) && i2 < (this.mDisplaySize.y / 4) * 3;
        } else {
            z = this.mExcludeRegion.contains(i, i2);
        }
        if (z) {
            this.mOverviewProxyService.notifyBackAction(false, -1, -1, false, !this.mIsOnLeftEdge);
            StatsLog.write(224, 3, i2, this.mIsOnLeftEdge ? 1 : 2);
        } else {
            this.mInRejectedExclusion = this.mUnrestrictedExcludeRegion.contains(i, i2);
        }
        if (!z) {
            return true;
        }
        return false;
    }

    private void cancelGesture(MotionEvent motionEvent) {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mAllowGesture = false;
        this.mInRejectedExclusion = false;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        this.mEdgePanel.handleTouch(obtain);
        obtain.recycle();
    }

    public void setLongSwipeOptions() {
        boolean z = false;
        this.mBackSwipeType = Settings.System.getIntForUser(this.mContext.getContentResolver(), "back_swipe_type", 0, -2);
        NavigationBarEdgePanel navigationBarEdgePanel = this.mEdgePanel;
        if (navigationBarEdgePanel != null) {
            navigationBarEdgePanel.setExtendedSwipe();
        }
        this.mTimeout = Settings.System.getIntForUser(this.mContext.getContentResolver(), "long_back_swipe_timeout", 2000, -2);
        this.mLeftLongSwipeAction = Settings.System.getIntForUser(this.mContext.getContentResolver(), "left_long_back_swipe_action", 0, -2);
        this.mRightLongSwipeAction = Settings.System.getIntForUser(this.mContext.getContentResolver(), "right_long_back_swipe_action", 0, -2);
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "back_gesture_haptic", 1, -2) == 1) {
            z = true;
        }
        this.mBackHapticEnabled = z;
    }

    private void onMotionEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        boolean z = true;
        if (actionMasked == 0) {
            int systemUiStateFlags = this.mOverviewProxyService.getSystemUiStateFlags();
            this.mIsOnLeftEdge = motionEvent.getX() <= ((float) (this.mEdgeWidth + this.mLeftInset));
            this.mInRejectedExclusion = false;
            if (QuickStepContract.isBackGestureDisabled(systemUiStateFlags) || !isWithinTouchRegion((int) motionEvent.getX(), (int) motionEvent.getY())) {
                z = false;
            }
            this.mAllowGesture = z;
            if (this.mAllowGesture) {
                this.mEdgePanelLp.gravity = this.mIsOnLeftEdge ? 51 : 53;
                this.mEdgePanel.setIsLeftPanel(this.mIsOnLeftEdge);
                this.mEdgePanel.handleTouch(motionEvent);
                updateEdgePanelPosition(motionEvent.getY());
                this.mWm.updateViewLayout(this.mEdgePanel, this.mEdgePanelLp);
                this.mRegionSamplingHelper.start(this.mSamplingRect);
                this.mDownPoint.set(motionEvent.getX(), motionEvent.getY());
                this.mThresholdCrossed = false;
            }
        } else if (this.mAllowGesture && !this.mBlockNextEvent) {
            if (!this.mThresholdCrossed) {
                if (actionMasked == 5) {
                    cancelGesture(motionEvent);
                    return;
                } else if (actionMasked == 2) {
                    int eventTime = (int) (motionEvent.getEventTime() - motionEvent.getDownTime());
                    if (eventTime > this.mLongPressTimeout) {
                        cancelGesture(motionEvent);
                        return;
                    }
                    float abs = Math.abs(motionEvent.getX() - this.mDownPoint.x);
                    float abs2 = Math.abs(motionEvent.getY() - this.mDownPoint.y);
                    if (abs2 > abs && abs2 > this.mTouchSlop) {
                        cancelGesture(motionEvent);
                        return;
                    } else if (abs > abs2 && abs > this.mTouchSlop) {
                        this.mThresholdCrossed = true;
                        if (this.mBackSwipeType == 0 && ((this.mLeftLongSwipeAction != 0 && this.mIsOnLeftEdge) || (this.mRightLongSwipeAction != 0 && !this.mIsOnLeftEdge))) {
                            this.mHandler.postDelayed(this.mLongSwipeAction, (long) (this.mTimeout - eventTime));
                        }
                        this.mInputMonitor.pilferPointers();
                    }
                }
            }
            this.mEdgePanel.handleTouch(motionEvent);
            boolean z2 = actionMasked == 1;
            boolean z3 = actionMasked == 3;
            int i = 4;
            if ((actionMasked == 2) && this.mBackSwipeType == 1 && Math.abs(motionEvent.getX() - this.mDownPoint.x) > ((float) ((this.mDisplaySize.x / 4) * 3))) {
                this.mLongSwipeAction.run();
            }
            if (z2 || z3) {
                this.mHandler.removeCallbacksAndMessages((Object) null);
            }
            if (z2) {
                boolean shouldTriggerBack = this.mEdgePanel.shouldTriggerBack();
                if (shouldTriggerBack) {
                    sendEvent(0, 4);
                    sendEvent(1, 4);
                }
                OverviewProxyService overviewProxyService = this.mOverviewProxyService;
                PointF pointF = this.mDownPoint;
                overviewProxyService.notifyBackAction(shouldTriggerBack, (int) pointF.x, (int) pointF.y, false, !this.mIsOnLeftEdge);
                if (shouldTriggerBack) {
                    i = this.mInRejectedExclusion ? 2 : 1;
                }
                int i2 = (int) this.mDownPoint.y;
                if (!this.mIsOnLeftEdge) {
                    z = true;
                }
                StatsLog.write(224, i, i2, z ? 1 : 0);
            }
            if (z2 || z3) {
                this.mRegionSamplingHelper.stop();
                return;
            }
            updateSamplingRect();
            this.mRegionSamplingHelper.updateSamplingRect();
        } else if (this.mBlockNextEvent) {
            this.mBlockNextEvent = false;
            cancelGesture(motionEvent);
        }
    }

    private class LongSwipeRunnable implements Runnable {
        private LongSwipeRunnable() {
        }

        public void run() {
            boolean unused = EdgeBackGestureHandler.this.mBlockNextEvent = true;
            EdgeBackGestureHandler.this.mEdgePanel.resetOnDown();
            EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
            edgeBackGestureHandler.triggerAction(edgeBackGestureHandler.mIsOnLeftEdge);
            if (EdgeBackGestureHandler.this.mBackHapticEnabled) {
                EdgeBackGestureHandler.this.mVibrator.vibrate(VibrationEffect.get(5));
            }
        }
    }

    public void triggerAction(boolean z) {
        int i;
        if (z) {
            i = this.mLeftLongSwipeAction;
        } else {
            i = this.mRightLongSwipeAction;
        }
        switch (i) {
            case 1:
                this.mAssistManager.startAssist(new Bundle());
                return;
            case 2:
                ActionUtils.launchVoiceSearch(this.mContext);
                return;
            case 3:
                ActionUtils.launchCamera(this.mContext);
                return;
            case 4:
                ActionUtils.toggleCameraFlash();
                return;
            case 5:
                launchApp(this.mContext, z);
                return;
            case 6:
                ActionUtils.toggleVolumePanel(this.mContext);
                return;
            case 7:
                ActionUtils.switchScreenOff(this.mContext);
                return;
            case 8:
                ActionUtils.takeScreenshot(true);
                return;
            case 9:
                ActionUtils.toggleNotifications();
                return;
            case 10:
                ActionUtils.toggleQsPanel();
                return;
            case 11:
                ActionUtils.clearAllNotifications();
                return;
            case 12:
                ActionUtils.toggleRingerModes(this.mContext);
                return;
            case 13:
                ActionUtils.killForegroundApp();
                return;
            case 14:
                ActionUtils.sendSystemKeyToStatusBar(87);
                return;
            case 15:
                ActionUtils.sendSystemKeyToStatusBar(88);
                return;
            default:
                return;
        }
    }

    private void launchApp(Context context, boolean z) {
        Intent intent;
        String stringForUser = Settings.System.getStringForUser(context.getContentResolver(), z ? "left_long_back_swipe_app_action" : "right_long_back_swipe_app_action", -2);
        String stringForUser2 = Settings.System.getStringForUser(context.getContentResolver(), z ? "left_long_back_swipe_app_activity_action" : "right_long_back_swipe_app_activity_action", -2);
        if (stringForUser2 != null && !TextUtils.equals("NONE", stringForUser2)) {
            try {
                intent = new Intent("android.intent.action.MAIN");
                intent.setClassName(stringForUser, stringForUser2);
            } catch (Exception unused) {
                return;
            }
        } else {
            intent = context.getPackageManager().getLaunchIntentForPackage(stringForUser);
        }
        intent.setFlags(335544320);
        context.startActivity(intent);
    }

    private void updateEdgePanelPosition(float f) {
        float max = Math.max(f - ((float) this.mFingerOffset), (float) this.mMinArrowPosition) - (((float) this.mEdgePanelLp.height) / 2.0f);
        this.mEdgePanelLp.y = MathUtils.constrain((int) max, 0, this.mDisplaySize.y);
        updateSamplingRect();
    }

    private void updateSamplingRect() {
        WindowManager.LayoutParams layoutParams = this.mEdgePanelLp;
        int i = layoutParams.y;
        int i2 = this.mIsOnLeftEdge ? this.mLeftInset : (this.mDisplaySize.x - this.mRightInset) - layoutParams.width;
        this.mSamplingRect.set(i2, i, this.mEdgePanelLp.width + i2, this.mEdgePanelLp.height + i);
        this.mEdgePanel.adjustRectToBoundingBox(this.mSamplingRect);
    }

    public void onDisplayChanged(int i) {
        if (i == this.mDisplayId) {
            updateDisplaySize();
        }
    }

    private void updateDisplaySize() {
        ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).getDisplay(this.mDisplayId).getRealSize(this.mDisplaySize);
        updateEdgeHeightValue();
    }

    private void sendEvent(int i, int i2) {
        long uptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(uptimeMillis, uptimeMillis, i, i2, 0, 0, -1, 0, 72, 67108865);
        int expandedDisplayId = ((BubbleController) Dependency.get(BubbleController.class)).getExpandedDisplayId(this.mContext);
        if (i2 == 4 && expandedDisplayId != -1) {
            keyEvent.setDisplayId(expandedDisplayId);
        }
        InputManager.getInstance().injectInputEvent(keyEvent, 0);
    }

    public void setInsets(int i, int i2) {
        this.mLeftInset = i;
        this.mRightInset = i2;
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("EdgeBackGestureHandler:");
        printWriter.println("  mIsEnabled=" + this.mIsEnabled);
        printWriter.println("  mAllowGesture=" + this.mAllowGesture);
        printWriter.println("  mInRejectedExclusion" + this.mInRejectedExclusion);
        printWriter.println("  mExcludeRegion=" + this.mExcludeRegion);
        printWriter.println("  mUnrestrictedExcludeRegion=" + this.mUnrestrictedExcludeRegion);
        printWriter.println("  mImeHeight=" + this.mImeHeight);
        printWriter.println("  mIsAttached=" + this.mIsAttached);
        printWriter.println("  mEdgeWidth=" + this.mEdgeWidth);
    }

    class SysUiInputEventReceiver extends InputEventReceiver {
        SysUiInputEventReceiver(InputChannel inputChannel, Looper looper) {
            super(inputChannel, looper);
        }

        public void onInputEvent(InputEvent inputEvent) {
            EdgeBackGestureHandler.this.onInputEvent(inputEvent);
            finishInputEvent(inputEvent, true);
        }
    }
}
