package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.IApplicationThread;
import android.app.IWallpaperManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProfilerInfo;
import android.app.StatusBarManager;
import android.app.UiModeManager;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.om.IOverlayManager;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.metrics.LogMaker;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.service.dreams.IDreamManager;
import android.service.notification.StatusBarNotification;
import android.util.DisplayMetrics;
import android.util.EventLog;
import android.util.Log;
import android.util.Slog;
import android.view.Display;
import android.view.IWindowManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.RemoteAnimationAdapter;
import android.view.ThreadedRenderer;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DateTimeView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.RegisterStatusBarResult;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.havoc.Utils;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.ViewMediatorCallback;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.ActivityStarterDelegate;
import com.android.systemui.AutoReinflateContainer;
import com.android.systemui.C1771R$array;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.C1785R$style;
import com.android.systemui.DemoMode;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.EventLogTags;
import com.android.systemui.ForegroundServiceController;
import com.android.systemui.ImageUtilities;
import com.android.systemui.InitController;
import com.android.systemui.Interpolators;
import com.android.systemui.Prefs;
import com.android.systemui.SystemUI;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.appops.AppOpsController;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.bubbles.BubbleController;
import com.android.systemui.charging.WirelessChargingAnimation;
import com.android.systemui.classifier.FalsingLog;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.doze.DozeHost;
import com.android.systemui.doze.DozeLog;
import com.android.systemui.doze.DozeReceiver;
import com.android.systemui.fragments.ExtensionFragmentListener;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.navigation.pulse.VisualizerView;
import com.android.systemui.p006qs.QSFragment;
import com.android.systemui.p006qs.QSPanel;
import com.android.systemui.p006qs.QuickQSPanel;
import com.android.systemui.p006qs.QuickStatusBarHeader;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.p005qs.C0862QS;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.recents.Recents;
import com.android.systemui.recents.ScreenPinningRequest;
import com.android.systemui.shared.system.WindowManagerWrapper;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.WindowManagerProxy;
import com.android.systemui.statusbar.AODDimView;
import com.android.systemui.statusbar.BackDropView;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.EmptyShadeView;
import com.android.systemui.statusbar.GestureRecorder;
import com.android.systemui.statusbar.KeyboardShortcuts;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.NavigationBarController;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationPresenter;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.NotificationViewHierarchyManager;
import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.ScrimView;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.notification.ActivityLaunchAnimator;
import com.android.systemui.statusbar.notification.BypassHeadsUpNotifier;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.NotificationActivityStarter;
import com.android.systemui.statusbar.notification.NotificationAlertingManager;
import com.android.systemui.statusbar.notification.NotificationClicker;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.NotificationInterruptionStateProvider;
import com.android.systemui.statusbar.notification.NotificationListController;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.ViewGroupFadeHelper;
import com.android.systemui.statusbar.notification.VisualStabilityManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.NotificationRowBinderImpl;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.phone.UnlockMethodCache;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BrightnessMirrorController;
import com.android.systemui.statusbar.policy.BurnInProtectionController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.ExtensionController;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcher;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.policy.PulseController;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisabler;
import com.android.systemui.statusbar.policy.TaskHelper;
import com.android.systemui.statusbar.policy.UserInfoController;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.util.InjectionInflationController;
import com.android.systemui.util.leak.RotationUtils;
import com.android.systemui.volume.VolumeComponent;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StatusBar extends SystemUI implements DemoMode, ActivityStarter, UnlockMethodCache.OnUnlockMethodChangedListener, OnHeadsUpChangedListener, CommandQueue.Callbacks, ZenModeController.Callback, ColorExtractor.OnColorsChangedListener, ConfigurationController.ConfigurationListener, StatusBarStateController.StateListener, ShadeController, ActivityLaunchAnimator.Callback, AppOpsController.Callback {
    public static final String ACTION_FAKE_ARTWORK = "fake_artwork";
    protected static final int[] APP_OPS = {26, 24, 27, 0, 1};
    private static final String BANNER_ACTION_CANCEL = "com.android.systemui.statusbar.banner_action_cancel";
    private static final String BANNER_ACTION_SETUP = "com.android.systemui.statusbar.banner_action_setup";
    private static final int BRIGHTNESS_CONTROL_LINGER_THRESHOLD = 20;
    private static final int BRIGHTNESS_CONTROL_LONG_PRESS_TIMEOUT = 750;
    private static final float BRIGHTNESS_CONTROL_PADDING = 0.15f;
    public static final boolean CHATTY = false;
    protected static final boolean CLOSE_PANEL_WHEN_EMPTIED = true;
    public static final boolean DEBUG = false;
    public static final boolean DEBUG_CAMERA_LIFT = false;
    public static final boolean DEBUG_GESTURES = false;
    public static final boolean DEBUG_MEDIA_FAKE_ARTWORK = false;
    public static final boolean DEBUG_WINDOW_STATE = false;
    public static final boolean DUMPTRUCK = true;
    public static final boolean ENABLE_CHILD_NOTIFICATIONS = SystemProperties.getBoolean("debug.child_notifs", true);
    public static final boolean ENABLE_LOCKSCREEN_WALLPAPER = true;
    public static final int FADE_KEYGUARD_DURATION = 300;
    public static final int FADE_KEYGUARD_DURATION_PULSING = 96;
    public static final int FADE_KEYGUARD_START_DELAY = 100;
    private static final int HINT_RESET_DELAY_MS = 1200;
    private static final long LAUNCH_TRANSITION_TIMEOUT_MS = 5000;
    protected static final int MSG_CANCEL_PRELOAD_RECENT_APPS = 1023;
    private static final int MSG_CLOSE_PANELS = 1001;
    protected static final int MSG_DISMISS_KEYBOARD_SHORTCUTS_MENU = 1027;
    protected static final int MSG_HIDE_RECENT_APPS = 1020;
    private static final int MSG_LAUNCH_TRANSITION_TIMEOUT = 1003;
    private static final int MSG_OPEN_NOTIFICATION_PANEL = 1000;
    private static final int MSG_OPEN_SETTINGS_PANEL = 1002;
    protected static final int MSG_PRELOAD_RECENT_APPS = 1022;
    protected static final int MSG_TOGGLE_KEYBOARD_SHORTCUTS_MENU = 1026;
    public static final boolean MULTIUSER_DEBUG = false;
    public static final boolean ONLY_CORE_APPS;
    public static final boolean SHOW_LOCKSCREEN_MEDIA_ARTWORK = true;
    public static final boolean SPEW = false;
    public static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    public static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    public static final String SYSTEM_DIALOG_REASON_SCREENSHOT = "screenshot";
    public static final String TAG = "StatusBar";
    private static final AudioAttributes VIBRATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(13).build();
    private boolean blurperformed = false;
    /* access modifiers changed from: private */
    public AODDimView mAODDimView;
    private final int[] mAbsPos = new int[2];
    protected AccessibilityManager mAccessibilityManager;
    private ActivityIntentHelper mActivityIntentHelper;
    private ActivityLaunchAnimator mActivityLaunchAnimator;
    boolean mAllowNotificationLongPress;
    /* access modifiers changed from: private */
    public View mAmbientIndicationContainer;
    private final Runnable mAnimateCollapsePanels = new Runnable() {
        public final void run() {
            StatusBar.this.animateCollapsePanels();
        }
    };
    protected AppOpsController mAppOpsController;
    protected AssistManager mAssistManager;
    @VisibleForTesting
    protected AutoHideController mAutoHideController;
    private boolean mAutomaticBrightness;
    private final BroadcastReceiver mBannerActionBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (StatusBar.BANNER_ACTION_CANCEL.equals(action) || StatusBar.BANNER_ACTION_SETUP.equals(action)) {
                ((NotificationManager) StatusBar.this.mContext.getSystemService("notification")).cancel(5);
                Settings.Secure.putInt(StatusBar.this.mContext.getContentResolver(), "show_note_about_notification_hiding", 0);
                if (StatusBar.BANNER_ACTION_SETUP.equals(action)) {
                    StatusBar.this.animateCollapsePanels(2, true);
                    StatusBar.this.mContext.startActivity(new Intent("android.settings.ACTION_APP_NOTIFICATION_REDACTION").addFlags(268435456));
                }
            }
        }
    };
    protected IStatusBarService mBarService;
    protected BatteryController mBatteryController;
    protected BiometricUnlockController mBiometricUnlockController;
    protected boolean mBouncerShowing;
    private boolean mBouncerWasShowingWhenHidden;
    private boolean mBrightnessChanged;
    private boolean mBrightnessControl;
    private BrightnessMirrorController mBrightnessMirrorController;
    private boolean mBrightnessMirrorVisible;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int i = 0;
            if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action)) {
                KeyboardShortcuts.dismiss();
                if (StatusBar.this.mRemoteInputManager.getController() != null) {
                    StatusBar.this.mRemoteInputManager.getController().closeRemoteInputs();
                }
                BubbleController bubbleController = StatusBar.this.mBubbleController;
                if (bubbleController != null && bubbleController.isStackExpanded()) {
                    StatusBar.this.mBubbleController.collapseStack();
                }
                if (StatusBar.this.mLockscreenUserManager.isCurrentProfile(getSendingUserId())) {
                    String stringExtra = intent.getStringExtra("reason");
                    if (stringExtra != null && stringExtra.equals(StatusBar.SYSTEM_DIALOG_REASON_RECENT_APPS)) {
                        i = 2;
                    }
                    StatusBar.this.animateCollapsePanels(i);
                }
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                StatusBarWindowController statusBarWindowController = StatusBar.this.mStatusBarWindowController;
                if (statusBarWindowController != null) {
                    statusBarWindowController.setNotTouchable(false);
                }
                BubbleController bubbleController2 = StatusBar.this.mBubbleController;
                if (bubbleController2 != null && bubbleController2.isStackExpanded()) {
                    StatusBar.this.mBubbleController.collapseStack();
                }
                StatusBar.this.finishBarAnimations();
                StatusBar.this.resetUserExpandedStates();
            } else if ("android.app.action.SHOW_DEVICE_MONITORING_DIALOG".equals(action)) {
                StatusBar.this.mQSPanel.showDeviceMonitoringDialog();
            } else if ("android.intent.action.SCREEN_CAMERA_GESTURE".equals(action)) {
                if (Settings.Secure.getInt(StatusBar.this.mContext.getContentResolver(), "user_setup_complete", 0) != 0) {
                    i = 1;
                }
                if (i != 0) {
                    StatusBar.this.onCameraLaunchGestureDetected(3);
                }
            } else if ("com.android.systemui.ACTION_DISMISS_KEYGUARD".equals(action) && intent.hasExtra("launch")) {
                StatusBar.this.startActivityDismissingKeyguard((Intent) intent.getParcelableExtra("launch"), true, true);
            }
        }
    };
    protected BubbleController mBubbleController;
    private final BubbleController.BubbleExpandListener mBubbleExpandListener = new BubbleController.BubbleExpandListener() {
        public final void onBubbleExpandChanged(boolean z, String str) {
            StatusBar.this.lambda$new$1$StatusBar(z, str);
        }
    };
    /* access modifiers changed from: private */
    public BurnInProtectionController mBurnInProtectionController;
    BypassHeadsUpNotifier mBypassHeadsUpNotifier;
    private long[] mCameraLaunchGestureVibePattern;
    View mCenterClockLayout;
    /* access modifiers changed from: private */
    public final Runnable mCheckBarModes = new Runnable() {
        public final void run() {
            StatusBar.this.checkBarModes();
        }
    };
    protected SysuiColorExtractor mColorExtractor;
    protected CommandQueue mCommandQueue;
    private final Point mCurrentDisplaySize = new Point();
    private boolean mDemoMode;
    private boolean mDemoModeAllowed;
    private final BroadcastReceiver mDemoReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.android.systemui.demo".equals(action)) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String lowerCase = extras.getString("command", "").trim().toLowerCase();
                    if (lowerCase.length() > 0) {
                        try {
                            StatusBar.this.dispatchDemoCommand(lowerCase, extras);
                        } catch (Throwable th) {
                            Log.w(StatusBar.TAG, "Error running demo command, intent=" + intent, th);
                        }
                    }
                }
            } else {
                StatusBar.ACTION_FAKE_ARTWORK.equals(action);
            }
        }
    };
    protected boolean mDeviceInteractive;
    protected DevicePolicyManager mDevicePolicyManager;
    protected DeviceProvisionedController mDeviceProvisionedController = ((DeviceProvisionedController) Dependency.get(DeviceProvisionedController.class));
    private int mDisabled1 = 0;
    private int mDisabled2 = 0;
    protected Display mDisplay;
    private int mDisplayId;
    private DisplayManager mDisplayManager;
    private final DisplayMetrics mDisplayMetrics = ((DisplayMetrics) Dependency.get(DisplayMetrics.class));
    protected DozeScrimController mDozeScrimController;
    @VisibleForTesting
    DozeServiceHost mDozeServiceHost = new DozeServiceHost();
    protected boolean mDozing;
    /* access modifiers changed from: private */
    public boolean mDozingRequested;
    private NotificationEntry mDraggedDownEntry;
    private IDreamManager mDreamManager;
    DynamicPrivacyController mDynamicPrivacyController;
    protected EmptyShadeView mEmptyShadeView;
    protected NotificationEntryManager mEntryManager;
    private boolean mExpandedVisible;
    protected FalsingManager mFalsingManager;
    private FlashlightController mFlashlightController;
    protected ForegroundServiceController mForegroundServiceController;
    private boolean mFpDismissNotifications;
    private boolean mGamingModeActivated;
    private final GestureRecorder mGestureRec = null;
    protected PowerManager.WakeLock mGestureWakeLock;
    private final View.OnClickListener mGoToLockedShadeListener = new View.OnClickListener() {
        public final void onClick(View view) {
            StatusBar.this.lambda$new$0$StatusBar(view);
        }
    };
    protected NotificationGroupAlertTransferHelper mGroupAlertTransferHelper;
    protected NotificationGroupManager mGroupManager;
    protected NotificationGutsManager mGutsManager;
    protected final C1479H mHandler = createHandler();
    private HeadsUpAppearanceController mHeadsUpAppearanceController;
    private boolean mHeadsUpDisabled;
    protected HeadsUpManagerPhone mHeadsUpManager;
    private boolean mHideIconsForBouncer;
    protected StatusBarIconController mIconController;
    private PhoneStatusBarPolicy mIconPolicy;
    private int mImmerseMode;
    /* access modifiers changed from: private */
    public int mInitialTouchX;
    private int mInitialTouchY;
    InjectionInflationController mInjectionInflater;
    private int mInteractingWindows;
    protected boolean mIsKeyguard;
    private boolean mIsOccluded;
    private boolean mJustPeeked;
    KeyguardBypassController mKeyguardBypassController;
    KeyguardIndicationController mKeyguardIndicationController;
    protected KeyguardLiftController mKeyguardLiftController;
    protected KeyguardManager mKeyguardManager;
    protected KeyguardMonitor mKeyguardMonitor;
    @VisibleForTesting
    KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    private KeyguardUserSwitcher mKeyguardUserSwitcher;
    protected KeyguardViewMediator mKeyguardViewMediator;
    private ViewMediatorCallback mKeyguardViewMediatorCallback;
    /* access modifiers changed from: private */
    public int mLastCameraLaunchSource;
    private final Rect mLastDockedStackBounds = new Rect();
    private final Rect mLastFullscreenStackBounds = new Rect();
    private int mLastLoggedStateFingerprint;
    /* access modifiers changed from: private */
    public boolean mLaunchCameraOnFinishedGoingToSleep;
    /* access modifiers changed from: private */
    public boolean mLaunchCameraWhenFinishedWaking;
    private Runnable mLaunchTransitionEndRunnable;
    protected LightBarController mLightBarController;
    /* access modifiers changed from: private */
    public int mLinger;
    protected NotificationLockscreenUserManager mLockscreenUserManager;
    protected LockscreenWallpaper mLockscreenWallpaper;
    Runnable mLongPressBrightnessChange = new Runnable() {
        public void run() {
            StatusBar.this.mStatusBarView.performHapticFeedback(0);
            StatusBar statusBar = StatusBar.this;
            statusBar.adjustBrightness(statusBar.mInitialTouchX);
            int unused = StatusBar.this.mLinger = 21;
        }
    };
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    protected NotificationMediaManager mMediaManager;
    private final MetricsLogger mMetricsLogger = ((MetricsLogger) Dependency.get(MetricsLogger.class));
    private int mMinBrightness;
    private int mNaturalBarHeight = -1;
    protected NavigationBarController mNavigationBarController;
    private NavigationBarController.SystemUiVisibility mNavigationBarSystemUiVisibility;
    protected NetworkController mNetworkController;
    private boolean mNoAnimationOnNextBarModeChange;
    private NotificationActivityStarter mNotificationActivityStarter;
    protected final NotificationAlertingManager mNotificationAlertingManager = ((NotificationAlertingManager) Dependency.get(NotificationAlertingManager.class));
    protected NotificationIconAreaController mNotificationIconAreaController;
    protected NotificationInterruptionStateProvider mNotificationInterruptionStateProvider;
    protected NotificationListController mNotificationListController;
    protected NotificationListener mNotificationListener;
    protected NotificationLogger mNotificationLogger;
    protected NotificationPanelView mNotificationPanel;
    protected NotificationShelf mNotificationShelf;
    private IOverlayManager mOverlayManager;
    protected boolean mPanelExpanded;
    private View mPendingRemoteInputView;
    private boolean mPortrait = true;
    private final ArrayList<Runnable> mPostCollapseRunnables = new ArrayList<>();
    protected PowerManager mPowerManager;
    protected StatusBarNotificationPresenter mPresenter;
    PulseExpansionHandler mPulseExpansionHandler;
    /* access modifiers changed from: private */
    public boolean mPulsing;
    private View mQSBarHeader;
    public ImageView mQSBlurView;
    /* access modifiers changed from: private */
    public QSPanel mQSPanel;
    private final Object mQueueLock = new Object();
    private QuickQSPanel mQuickQSPanel;
    private int mQuickQsTotalHeight;
    protected Recents mRecents;
    protected NotificationRemoteInputManager mRemoteInputManager;
    private RemoteInputQuickSettingsDisabler mRemoteInputQuickSettingsDisabler = ((RemoteInputQuickSettingsDisabler) Dependency.get(RemoteInputQuickSettingsDisabler.class));
    private View mReportRejectedTouch;
    private SbSettingsObserver mSbSettingsObserver = new SbSettingsObserver(this.mHandler);
    protected ScreenLifecycle mScreenLifecycle;
    final ScreenLifecycle.Observer mScreenObserver = new ScreenLifecycle.Observer() {
        public void onScreenTurningOn() {
            StatusBar.this.mFalsingManager.onScreenTurningOn();
            StatusBar.this.mNotificationPanel.onScreenTurningOn();
        }

        public void onScreenTurnedOn() {
            StatusBar.this.mScrimController.onScreenTurnedOn();
        }

        public void onScreenTurnedOff() {
            StatusBar.this.updateDozing();
            StatusBar.this.mFalsingManager.onScreenOff();
            StatusBar.this.mScrimController.onScreenTurnedOff();
            boolean unused = StatusBar.this.updateIsKeyguard();
        }
    };
    private ScreenPinningRequest mScreenPinningRequest;
    protected ScrimController mScrimController;
    private ShadeController mShadeController;
    private boolean mShowNavBar;
    private StatusBarSignalPolicy mSignalPolicy;
    protected ViewGroup mStackScroller;
    final Runnable mStartTracing = new Runnable() {
        public void run() {
            StatusBar.this.vibrate();
            SystemClock.sleep(250);
            Log.d(StatusBar.TAG, "startTracing");
            Debug.startMethodTracing("/data/statusbar-traces/trace");
            StatusBar statusBar = StatusBar.this;
            statusBar.mHandler.postDelayed(statusBar.mStopTracing, 10000);
        }
    };
    protected int mState;
    LinearLayout mStatusBarContent;
    protected StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    private int mStatusBarMode;
    /* access modifiers changed from: private */
    public final SysuiStatusBarStateController mStatusBarStateController = ((SysuiStatusBarStateController) Dependency.get(StatusBarStateController.class));
    private LogMaker mStatusBarStateLog;
    protected PhoneStatusBarView mStatusBarView;
    protected StatusBarWindowView mStatusBarWindow;
    protected StatusBarWindowController mStatusBarWindowController;
    private boolean mStatusBarWindowHidden;
    private int mStatusBarWindowState = 0;
    private boolean mStockStatusBar = true;
    final Runnable mStopTracing = new Runnable() {
        public final void run() {
            StatusBar.this.lambda$new$23$StatusBar();
        }
    };
    private int mSystemUiVisibility = 0;
    private boolean mSysuiRoundedFwvals;
    protected TaskHelper mTaskHelper;
    public Ticker mTicker;
    /* access modifiers changed from: private */
    public int mTickerAnimationMode;
    public boolean mTickerEnabled;
    public int mTickerMode;
    /* access modifiers changed from: private */
    public int mTickerTickDuration;
    /* access modifiers changed from: private */
    public boolean mTicking;
    Animation.AnimationListener mTickingDoneListener = new Animation.AnimationListener() {
        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            boolean unused = StatusBar.this.mTicking = false;
        }
    };
    private final int[] mTmpInt2 = new int[2];
    private boolean mTopHidesStatusBar;
    private UiModeManager mUiModeManager;
    private final UiOffloadThread mUiOffloadThread = ((UiOffloadThread) Dependency.get(UiOffloadThread.class));
    protected UnlockMethodCache mUnlockMethodCache;
    private final ScrimController.Callback mUnlockScrimCallback = new ScrimController.Callback() {
        public void onFinished() {
            StatusBar statusBar = StatusBar.this;
            if (statusBar.mStatusBarKeyguardViewManager == null) {
                Log.w(StatusBar.TAG, "Tried to notify keyguard visibility when mStatusBarKeyguardViewManager was null");
            } else if (statusBar.mKeyguardMonitor.isKeyguardFadingAway()) {
                StatusBar.this.mStatusBarKeyguardViewManager.onKeyguardFadedAway();
            }
        }

        public void onCancelled() {
            onFinished();
        }
    };
    private final KeyguardUpdateMonitorCallback mUpdateCallback = new KeyguardUpdateMonitorCallback() {
        public void onDreamingStateChanged(boolean z) {
            if (z) {
                StatusBar.this.maybeEscalateHeadsUp();
            }
            if (StatusBar.this.mAODDimView != null) {
                if (z) {
                    StatusBar.this.mAODDimView.setVisible(true, true);
                }
                if (!z) {
                    StatusBar.this.mAODDimView.setVisible(false);
                }
            }
        }

        public void onStrongAuthStateChanged(int i) {
            super.onStrongAuthStateChanged(i);
            StatusBar.this.mEntryManager.updateNotifications();
        }
    };
    @VisibleForTesting
    protected boolean mUserSetup = false;
    private final DeviceProvisionedController.DeviceProvisionedListener mUserSetupObserver = new DeviceProvisionedController.DeviceProvisionedListener() {
        public void onUserSetupChanged() {
            DeviceProvisionedController deviceProvisionedController = StatusBar.this.mDeviceProvisionedController;
            boolean isUserSetup = deviceProvisionedController.isUserSetup(deviceProvisionedController.getCurrentUser());
            Log.d(StatusBar.TAG, "mUserSetupObserver - DeviceProvisionedListener called for user " + StatusBar.this.mDeviceProvisionedController.getCurrentUser());
            StatusBar statusBar = StatusBar.this;
            if (isUserSetup != statusBar.mUserSetup) {
                statusBar.mUserSetup = isUserSetup;
                if (!statusBar.mUserSetup && statusBar.mStatusBarView != null) {
                    statusBar.animateCollapseQuickSettings();
                }
                StatusBar statusBar2 = StatusBar.this;
                NotificationPanelView notificationPanelView = statusBar2.mNotificationPanel;
                if (notificationPanelView != null) {
                    notificationPanelView.setUserSetupComplete(statusBar2.mUserSetup);
                }
                StatusBar.this.updateQsExpansionEnabled();
            }
        }
    };
    protected UserSwitcherController mUserSwitcherController;
    private boolean mVibrateOnOpening;
    private Vibrator mVibrator;
    protected VibratorHelper mVibratorHelper;
    protected NotificationViewHierarchyManager mViewHierarchyManager;
    protected boolean mVisible;
    private boolean mVisibleToUser;
    protected VisualStabilityManager mVisualStabilityManager;
    private VisualizerView mVisualizerView;
    private VolumeComponent mVolumeComponent;
    /* access modifiers changed from: private */
    public boolean mWakeUpComingFromTouch;
    NotificationWakeUpCoordinator mWakeUpCoordinator;
    /* access modifiers changed from: private */
    public PointF mWakeUpTouchLocation;
    @VisibleForTesting
    protected WakefulnessLifecycle mWakefulnessLifecycle;
    @VisibleForTesting
    final WakefulnessLifecycle.Observer mWakefulnessObserver = new WakefulnessLifecycle.Observer() {
        public void onFinishedGoingToSleep() {
            StatusBar.this.mNotificationPanel.onAffordanceLaunchEnded();
            StatusBar.this.releaseGestureWakeLock();
            boolean unused = StatusBar.this.mLaunchCameraWhenFinishedWaking = false;
            StatusBar statusBar = StatusBar.this;
            statusBar.mDeviceInteractive = false;
            boolean unused2 = statusBar.mWakeUpComingFromTouch = false;
            PointF unused3 = StatusBar.this.mWakeUpTouchLocation = null;
            StatusBar.this.mVisualStabilityManager.setScreenOn(false);
            StatusBar.this.updateVisibleToUser();
            StatusBar.this.updateNotificationPanelTouchState();
            StatusBar.this.mStatusBarWindow.cancelCurrentTouch();
            if (StatusBar.this.mBurnInProtectionController != null) {
                StatusBar.this.mBurnInProtectionController.stopSwiftTimer();
            }
            if (StatusBar.this.mLaunchCameraOnFinishedGoingToSleep) {
                boolean unused4 = StatusBar.this.mLaunchCameraOnFinishedGoingToSleep = false;
                StatusBar.this.mHandler.post(new Runnable() {
                    public final void run() {
                        StatusBar.C146717.this.lambda$onFinishedGoingToSleep$0$StatusBar$17();
                    }
                });
            }
            boolean unused5 = StatusBar.this.updateIsKeyguard();
        }

        public /* synthetic */ void lambda$onFinishedGoingToSleep$0$StatusBar$17() {
            StatusBar statusBar = StatusBar.this;
            statusBar.onCameraLaunchGestureDetected(statusBar.mLastCameraLaunchSource);
        }

        public void onStartedGoingToSleep() {
            StatusBar.this.updateNotificationPanelTouchState();
            StatusBar.this.notifyHeadsUpGoingToSleep();
            StatusBar.this.dismissVolumeDialog();
            StatusBar.this.mWakeUpCoordinator.setFullyAwake(false);
            StatusBar.this.mBypassHeadsUpNotifier.setFullyAwake(false);
            StatusBar.this.mKeyguardBypassController.onStartedGoingToSleep();
        }

        public void onStartedWakingUp() {
            StatusBar statusBar = StatusBar.this;
            statusBar.mDeviceInteractive = true;
            statusBar.mWakeUpCoordinator.setWakingUp(true);
            if (!StatusBar.this.mKeyguardBypassController.getBypassEnabled()) {
                StatusBar.this.mHeadsUpManager.releaseAllImmediately();
            }
            StatusBar.this.mVisualStabilityManager.setScreenOn(true);
            StatusBar.this.updateVisibleToUser();
            boolean unused = StatusBar.this.updateIsKeyguard();
            StatusBar.this.mDozeServiceHost.stopDozing();
            StatusBar.this.updateNotificationPanelTouchState();
            StatusBar.this.mPulseExpansionHandler.onStartedWakingUp();
        }

        public void onFinishedWakingUp() {
            StatusBar.this.mWakeUpCoordinator.setFullyAwake(true);
            StatusBar.this.mBypassHeadsUpNotifier.setFullyAwake(true);
            StatusBar.this.mWakeUpCoordinator.setWakingUp(false);
            if (StatusBar.this.mLaunchCameraWhenFinishedWaking) {
                StatusBar statusBar = StatusBar.this;
                statusBar.mNotificationPanel.launchCamera(false, statusBar.mLastCameraLaunchSource);
                boolean unused = StatusBar.this.mLaunchCameraWhenFinishedWaking = false;
            }
            StatusBar.this.updateScrimController();
            if (StatusBar.this.mBurnInProtectionController != null) {
                StatusBar.this.mBurnInProtectionController.startSwiftTimer();
            }
        }
    };
    private final BroadcastReceiver mWallpaperChangedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (!StatusBar.this.mWallpaperSupported) {
                Log.wtf(StatusBar.TAG, "WallpaperManager not supported");
                return;
            }
            WallpaperInfo wallpaperInfo = ((WallpaperManager) context.getSystemService(WallpaperManager.class)).getWallpaperInfo(-2);
            boolean z = StatusBar.this.mContext.getResources().getBoolean(17891429);
            boolean z2 = true;
            boolean z3 = !DozeParameters.getInstance(StatusBar.this.mContext).getDisplayNeedsBlanking();
            if (!z || ((wallpaperInfo != null || !z3) && (wallpaperInfo == null || !wallpaperInfo.supportsAmbientMode()))) {
                z2 = false;
            }
            StatusBar.this.mStatusBarWindowController.setWallpaperSupportsAmbientMode(z2);
            StatusBar.this.mScrimController.setWallpaperSupportsAmbientMode(z2);
            boolean unused = StatusBar.this.mWallpaperSupportsAmbientMode = z2;
        }
    };
    /* access modifiers changed from: private */
    public boolean mWallpaperSupported;
    /* access modifiers changed from: private */
    public boolean mWallpaperSupportsAmbientMode;
    private boolean mWereIconsJustHidden;
    protected WindowManager mWindowManager;
    protected IWindowManager mWindowManagerService;
    protected ZenModeController mZenController;

    public interface StatusBarInjector {
        void createStatusBar(StatusBar statusBar);
    }

    private int barMode(int i) {
        if ((67108864 & i) != 0) {
            return 1;
        }
        if ((1073741824 & i) != 0) {
            return 2;
        }
        if ((i & 9) == 9) {
            return 6;
        }
        if ((i & 8) != 0) {
            return 4;
        }
        return (i & 1) != 0 ? 3 : 0;
    }

    private static int getLoggingFingerprint(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return (i & 255) | ((z ? 1 : 0) << true) | ((z2 ? 1 : 0) << true) | ((z3 ? 1 : 0) << true) | ((z4 ? 1 : 0) << true) | ((z5 ? 1 : 0) << true);
    }

    static {
        boolean z;
        try {
            z = IPackageManager.Stub.asInterface(ServiceManager.getService("package")).isOnlyCoreApps();
        } catch (RemoteException unused) {
            z = false;
        }
        ONLY_CORE_APPS = z;
    }

    public void resetTrackInfo() {
        Ticker ticker = this.mTicker;
        if (ticker != null) {
            ticker.resetShownMediaMetadata();
        }
    }

    public boolean wallpaperSupportsAmbientMode() {
        return this.mWallpaperSupportsAmbientMode;
    }

    public /* synthetic */ void lambda$new$0$StatusBar(View view) {
        if (this.mState == 1) {
            wakeUpIfDozing(SystemClock.uptimeMillis(), view, "SHADE_CLICK");
            goToLockedShade((View) null);
        }
    }

    public /* synthetic */ void lambda$new$1$StatusBar(boolean z, String str) {
        this.mEntryManager.updateNotifications();
        updateScrimController();
    }

    public void onActiveStateChanged(int i, int i2, String str, boolean z) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable(i, i2, str, z) {
            private final /* synthetic */ int f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ String f$3;
            private final /* synthetic */ boolean f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void run() {
                StatusBar.this.lambda$onActiveStateChanged$2$StatusBar(this.f$1, this.f$2, this.f$3, this.f$4);
            }
        });
    }

    public /* synthetic */ void lambda$onActiveStateChanged$2$StatusBar(int i, int i2, String str, boolean z) {
        this.mForegroundServiceController.onAppOpChanged(i, i2, str, z);
        this.mNotificationListController.updateNotificationsForAppOp(i, i2, str, z);
    }

    public void start() {
        RegisterStatusBarResult registerStatusBarResult;
        WakefulnessLifecycle.Observer observer;
        ScreenLifecycle.Observer observer2;
        getDependencies();
        ScreenLifecycle screenLifecycle = this.mScreenLifecycle;
        if (!(screenLifecycle == null || (observer2 = this.mScreenObserver) == null)) {
            screenLifecycle.addObserver(observer2);
        }
        WakefulnessLifecycle wakefulnessLifecycle = this.mWakefulnessLifecycle;
        if (!(wakefulnessLifecycle == null || (observer = this.mWakefulnessObserver) == null)) {
            wakefulnessLifecycle.addObserver(observer);
        }
        this.mNotificationListener.registerAsSystemService();
        BubbleController bubbleController = this.mBubbleController;
        if (bubbleController != null) {
            bubbleController.setExpandListener(this.mBubbleExpandListener);
        }
        this.mUiModeManager = (UiModeManager) this.mContext.getSystemService(UiModeManager.class);
        this.mKeyguardViewMediator = (KeyguardViewMediator) getComponent(KeyguardViewMediator.class);
        this.mNavigationBarSystemUiVisibility = this.mNavigationBarController.createSystemUiVisibility();
        this.mActivityIntentHelper = new ActivityIntentHelper(this.mContext);
        KeyguardSliceProvider attachedInstance = KeyguardSliceProvider.getAttachedInstance();
        if (attachedInstance != null) {
            attachedInstance.initDependencies(this.mMediaManager, this.mStatusBarStateController, this.mKeyguardBypassController, DozeParameters.getInstance(this.mContext));
        } else {
            Log.w(TAG, "Cannot init KeyguardSliceProvider dependencies");
        }
        this.mColorExtractor.addOnColorsChangedListener(this);
        this.mStatusBarStateController.addCallback(this, 0);
        this.mDisplayManager = (DisplayManager) this.mContext.getSystemService(DisplayManager.class);
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        this.mDreamManager = IDreamManager.Stub.asInterface(ServiceManager.checkService("dreams"));
        this.mDisplay = this.mWindowManager.getDefaultDisplay();
        this.mDisplayId = this.mDisplay.getDisplayId();
        updateDisplaySize();
        this.mVibrateOnOpening = this.mContext.getResources().getBoolean(C1773R$bool.config_vibrateOnIconAnimation);
        this.mVibratorHelper = (VibratorHelper) Dependency.get(VibratorHelper.class);
        DateTimeView.setReceiverHandler((Handler) Dependency.get(Dependency.TIME_TICK_HANDLER));
        putComponent(StatusBar.class, this);
        this.mWindowManagerService = WindowManagerGlobal.getWindowManagerService();
        this.mDevicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService("device_policy");
        this.mAccessibilityManager = (AccessibilityManager) this.mContext.getSystemService("accessibility");
        this.mPowerManager = (PowerManager) this.mContext.getSystemService("power");
        this.mKeyguardUpdateMonitor = KeyguardUpdateMonitor.getInstance(this.mContext);
        this.mKeyguardUpdateMonitor.setKeyguardBypassController(this.mKeyguardBypassController);
        this.mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
        this.mRecents = (Recents) getComponent(Recents.class);
        this.mKeyguardManager = (KeyguardManager) this.mContext.getSystemService("keyguard");
        this.mFalsingManager = (FalsingManager) Dependency.get(FalsingManager.class);
        this.mWallpaperSupported = ((WallpaperManager) this.mContext.getSystemService(WallpaperManager.class)).isWallpaperSupported();
        this.mCommandQueue = (CommandQueue) getComponent(CommandQueue.class);
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
        try {
            registerStatusBarResult = this.mBarService.registerStatusBar(this.mCommandQueue);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            registerStatusBarResult = null;
        }
        createAndAddWindows(registerStatusBarResult);
        this.mSbSettingsObserver.observe();
        this.mSbSettingsObserver.update();
        if (this.mWallpaperSupported) {
            this.mContext.registerReceiverAsUser(this.mWallpaperChangedReceiver, UserHandle.ALL, new IntentFilter("android.intent.action.WALLPAPER_CHANGED"), (String) null, (Handler) null);
            this.mWallpaperChangedReceiver.onReceive(this.mContext, (Intent) null);
        }
        setUpPresenter();
        setSystemUiVisibility(this.mDisplayId, registerStatusBarResult.mSystemUiVisibility, registerStatusBarResult.mFullscreenStackSysUiVisibility, registerStatusBarResult.mDockedStackSysUiVisibility, -1, registerStatusBarResult.mFullscreenStackBounds, registerStatusBarResult.mDockedStackBounds, registerStatusBarResult.mNavbarColorManagedByIme);
        setImeWindowStatus(this.mDisplayId, registerStatusBarResult.mImeToken, registerStatusBarResult.mImeWindowVis, registerStatusBarResult.mImeBackDisposition, registerStatusBarResult.mShowImeSwitcher);
        int size = registerStatusBarResult.mIcons.size();
        for (int i = 0; i < size; i++) {
            this.mCommandQueue.setIcon((String) registerStatusBarResult.mIcons.keyAt(i), (StatusBarIcon) registerStatusBarResult.mIcons.valueAt(i));
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BANNER_ACTION_CANCEL);
        intentFilter.addAction(BANNER_ACTION_SETUP);
        this.mContext.registerReceiver(this.mBannerActionBroadcastReceiver, intentFilter, "com.android.systemui.permission.SELF", (Handler) null);
        if (this.mWallpaperSupported) {
            try {
                IWallpaperManager.Stub.asInterface(ServiceManager.getService("wallpaper")).setInAmbientMode(false, 0);
            } catch (RemoteException unused) {
            }
        }
        this.mIconPolicy = new PhoneStatusBarPolicy(this.mContext, this.mIconController);
        this.mSignalPolicy = new StatusBarSignalPolicy(this.mContext, this.mIconController);
        this.mUnlockMethodCache = UnlockMethodCache.getInstance(this.mContext);
        this.mUnlockMethodCache.addListener(this);
        startKeyguard();
        this.mKeyguardUpdateMonitor.registerCallback(this.mUpdateCallback);
        putComponent(DozeHost.class, this.mDozeServiceHost);
        this.mScreenPinningRequest = new ScreenPinningRequest(this.mContext);
        ((ActivityStarterDelegate) Dependency.get(ActivityStarterDelegate.class)).setActivityStarterImpl(this);
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
        ((InitController) Dependency.get(InitController.class)).addPostInitTask(new Runnable() {
            public final void run() {
                StatusBar.this.updateAreThereNotifications();
            }
        });
        ((InitController) Dependency.get(InitController.class)).addPostInitTask(new Runnable(registerStatusBarResult.mDisabledFlags1, registerStatusBarResult.mDisabledFlags2) {
            private final /* synthetic */ int f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                StatusBar.this.lambda$start$3$StatusBar(this.f$1, this.f$2);
            }
        });
        this.mMediaManager.addCallback((NotificationMediaManager.MediaListener) Dependency.get(PulseController.class));
    }

    /* access modifiers changed from: protected */
    public void makeStatusBarView(RegisterStatusBarResult registerStatusBarResult) {
        Context context = this.mContext;
        updateDisplaySize();
        updateResources();
        updateTheme();
        inflateStatusBarWindow(context);
        this.mStatusBarWindow.setService(this);
        this.mStatusBarWindow.setBypassController(this.mKeyguardBypassController);
        this.mStatusBarWindow.setOnTouchListener(getStatusBarWindowTouchListener());
        this.mMinBrightness = context.getResources().getInteger(17694905);
        this.mNotificationPanel = (NotificationPanelView) this.mStatusBarWindow.findViewById(C1777R$id.notification_panel);
        this.mStackScroller = (ViewGroup) this.mStatusBarWindow.findViewById(C1777R$id.notification_stack_scroller);
        this.mZenController.addCallback(this);
        this.mQSBlurView = (ImageView) this.mStatusBarWindow.findViewById(C1777R$id.qs_blur);
        this.mAODDimView = (AODDimView) this.mStatusBarWindow.findViewById(C1777R$id.aod_screen_dim);
        this.mNotificationLogger.setUpWithContainer((NotificationListContainer) this.mStackScroller);
        this.mNotificationIconAreaController = SystemUIFactory.getInstance().createNotificationIconAreaController(context, this, this.mWakeUpCoordinator, this.mKeyguardBypassController, this.mStatusBarStateController);
        this.mWakeUpCoordinator.setIconAreaController(this.mNotificationIconAreaController);
        inflateShelf();
        this.mNotificationIconAreaController.setupShelf(this.mNotificationShelf);
        NotificationPanelView notificationPanelView = this.mNotificationPanel;
        NotificationIconAreaController notificationIconAreaController = this.mNotificationIconAreaController;
        Objects.requireNonNull(notificationIconAreaController);
        notificationPanelView.setOnReinflationListener(new Runnable() {
            public final void run() {
                NotificationIconAreaController.this.initAodIcons();
            }
        });
        this.mNotificationPanel.addExpansionListener(this.mWakeUpCoordinator);
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).addDarkReceiver((DarkIconDispatcher.DarkReceiver) this.mNotificationIconAreaController);
        ((PluginDependencyProvider) Dependency.get(PluginDependencyProvider.class)).allowPluginDependency(DarkIconDispatcher.class);
        ((PluginDependencyProvider) Dependency.get(PluginDependencyProvider.class)).allowPluginDependency(StatusBarStateController.class);
        FragmentHostManager fragmentHostManager = FragmentHostManager.get(this.mStatusBarWindow);
        fragmentHostManager.addTagListener("CollapsedStatusBarFragment", new FragmentHostManager.FragmentListener() {
            public final void onFragmentViewCreated(String str, Fragment fragment) {
                StatusBar.this.lambda$makeStatusBarView$4$StatusBar(str, fragment);
            }
        });
        fragmentHostManager.getFragmentManager().beginTransaction().replace(C1777R$id.status_bar_container, new CollapsedStatusBarFragment(), "CollapsedStatusBarFragment").commit();
        this.mIconController = (StatusBarIconController) Dependency.get(StatusBarIconController.class);
        this.mHeadsUpManager.setUp(this.mStatusBarWindow, this.mGroupManager, this, this.mVisualStabilityManager);
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this.mHeadsUpManager);
        this.mHeadsUpManager.addListener(this);
        this.mHeadsUpManager.addListener(this.mNotificationPanel);
        this.mHeadsUpManager.addListener(this.mGroupManager);
        this.mHeadsUpManager.addListener(this.mGroupAlertTransferHelper);
        this.mHeadsUpManager.addListener(this.mVisualStabilityManager);
        this.mNotificationPanel.setHeadsUpManager(this.mHeadsUpManager);
        this.mGroupManager.setHeadsUpManager(this.mHeadsUpManager);
        this.mGroupAlertTransferHelper.setHeadsUpManager(this.mHeadsUpManager);
        this.mNotificationLogger.setHeadsUpManager(this.mHeadsUpManager);
        putComponent(HeadsUpManager.class, this.mHeadsUpManager);
        updateNavigationBar(registerStatusBarResult, true);
        if (this.mWallpaperSupported) {
            this.mLockscreenWallpaper = new LockscreenWallpaper(this.mContext, this, this.mHandler);
        }
        this.mKeyguardIndicationController = SystemUIFactory.getInstance().createKeyguardIndicationController(this.mContext, (ViewGroup) this.mStatusBarWindow.findViewById(C1777R$id.keyguard_indication_area), (LockIcon) this.mStatusBarWindow.findViewById(C1777R$id.lock_icon));
        this.mNotificationPanel.setKeyguardIndicationController(this.mKeyguardIndicationController);
        this.mAmbientIndicationContainer = this.mStatusBarWindow.findViewById(C1777R$id.ambient_indication_container);
        BatteryController batteryController = this.mBatteryController;
        if (batteryController != null) {
            batteryController.addCallback(new BatteryController.BatteryStateChangeCallback() {
                public void onBatteryLevelChanged(int i, boolean z, boolean z2) {
                }

                public void onPowerSaveChanged(boolean z) {
                    StatusBar statusBar = StatusBar.this;
                    statusBar.mHandler.post(statusBar.mCheckBarModes);
                    DozeServiceHost dozeServiceHost = StatusBar.this.mDozeServiceHost;
                    if (dozeServiceHost != null) {
                        dozeServiceHost.firePowerSaveChanged(z);
                    }
                }
            });
        }
        this.mAutoHideController = (AutoHideController) Dependency.get(AutoHideController.class);
        this.mAutoHideController.setStatusBar(this);
        this.mLightBarController = (LightBarController) Dependency.get(LightBarController.class);
        this.mScrimController = SystemUIFactory.getInstance().createScrimController((ScrimView) this.mStatusBarWindow.findViewById(C1777R$id.scrim_behind), (ScrimView) this.mStatusBarWindow.findViewById(C1777R$id.scrim_in_front), this.mLockscreenWallpaper, new TriConsumer() {
            public final void accept(Object obj, Object obj2, Object obj3) {
                StatusBar.this.lambda$makeStatusBarView$5$StatusBar((ScrimState) obj, (Float) obj2, (ColorExtractor.GradientColors) obj3);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                StatusBar.this.lambda$makeStatusBarView$6$StatusBar((Integer) obj);
            }
        }, DozeParameters.getInstance(this.mContext), (AlarmManager) this.mContext.getSystemService(AlarmManager.class), this.mKeyguardMonitor);
        this.mNotificationPanel.initDependencies(this, this.mGroupManager, this.mNotificationShelf, this.mHeadsUpManager, this.mNotificationIconAreaController, this.mScrimController);
        this.mDozeScrimController = new DozeScrimController(DozeParameters.getInstance(context));
        BackDropView backDropView = (BackDropView) this.mStatusBarWindow.findViewById(C1777R$id.backdrop);
        this.mMediaManager.setup(backDropView, (ImageView) backDropView.findViewById(C1777R$id.backdrop_front), (ImageView) backDropView.findViewById(C1777R$id.backdrop_back), this.mScrimController, this.mLockscreenWallpaper);
        this.mVisualizerView = (VisualizerView) this.mStatusBarWindow.findViewById(C1777R$id.visualizerview);
        this.mVolumeComponent = (VolumeComponent) getComponent(VolumeComponent.class);
        this.mNotificationPanel.setUserSetupComplete(this.mUserSetup);
        if (UserManager.get(this.mContext).isUserSwitcherEnabled()) {
            createUserSwitcher();
        }
        NotificationPanelView notificationPanelView2 = this.mNotificationPanel;
        StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
        Objects.requireNonNull(statusBarWindowView);
        notificationPanelView2.setLaunchAffordanceListener(new Consumer() {
            public final void accept(Object obj) {
                StatusBarWindowView.this.onShowingLaunchAffordanceChanged(((Boolean) obj).booleanValue());
            }
        });
        setUpQuickSettingsTilePanel();
        this.mReportRejectedTouch = this.mStatusBarWindow.findViewById(C1777R$id.report_rejected_touch);
        if (this.mReportRejectedTouch != null) {
            updateReportRejectedTouchVisibility();
            this.mReportRejectedTouch.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    StatusBar.this.lambda$makeStatusBarView$7$StatusBar(view);
                }
            });
        }
        PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
        if (!powerManager.isScreenOn()) {
            this.mBroadcastReceiver.onReceive(this.mContext, new Intent("android.intent.action.SCREEN_OFF"));
        }
        this.mGestureWakeLock = powerManager.newWakeLock(10, "GestureWakeLock");
        this.mVibrator = (Vibrator) this.mContext.getSystemService(Vibrator.class);
        int[] intArray = this.mContext.getResources().getIntArray(C1771R$array.config_cameraLaunchGestureVibePattern);
        this.mCameraLaunchGestureVibePattern = new long[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            this.mCameraLaunchGestureVibePattern[i] = (long) intArray[i];
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.app.action.SHOW_DEVICE_MONITORING_DIALOG");
        intentFilter.addAction("android.intent.action.SCREEN_CAMERA_GESTURE");
        intentFilter.addAction("com.android.systemui.ACTION_DISMISS_KEYGUARD");
        context.registerReceiverAsUser(this.mBroadcastReceiver, UserHandle.ALL, intentFilter, (String) null, (Handler) null);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.android.systemui.demo");
        context.registerReceiverAsUser(this.mDemoReceiver, UserHandle.ALL, intentFilter2, "android.permission.DUMP", (Handler) null);
        this.mDeviceProvisionedController.addCallback(this.mUserSetupObserver);
        this.mUserSetupObserver.onUserSetupChanged();
        ThreadedRenderer.overrideProperty("disableProfileBars", "true");
        ThreadedRenderer.overrideProperty("ambientRatio", String.valueOf(1.5f));
        this.mFlashlightController = (FlashlightController) Dependency.get(FlashlightController.class);
    }

    public /* synthetic */ void lambda$makeStatusBarView$4$StatusBar(String str, Fragment fragment) {
        ((CollapsedStatusBarFragment) fragment).initNotificationIconArea(this.mNotificationIconAreaController);
        PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
        this.mStatusBarView = (PhoneStatusBarView) fragment.getView();
        this.mStatusBarView.setBar(this);
        this.mStatusBarView.setPanel(this.mNotificationPanel);
        this.mStatusBarView.setScrimController(this.mScrimController);
        if (this.mHeadsUpManager.hasPinnedHeadsUp()) {
            this.mNotificationPanel.notifyBarPanelExpansionChanged();
        }
        this.mStatusBarView.setBouncerShowing(this.mBouncerShowing);
        if (phoneStatusBarView != null) {
            this.mStatusBarView.panelExpansionChanged(phoneStatusBarView.getExpansionFraction(), phoneStatusBarView.isExpanded());
        }
        HeadsUpAppearanceController headsUpAppearanceController = this.mHeadsUpAppearanceController;
        if (headsUpAppearanceController != null) {
            headsUpAppearanceController.destroy();
        }
        this.mHeadsUpAppearanceController = new HeadsUpAppearanceController(this.mNotificationIconAreaController, this.mHeadsUpManager, this.mStatusBarWindow, this.mStatusBarStateController, this.mKeyguardBypassController, this.mWakeUpCoordinator);
        this.mHeadsUpAppearanceController.readFrom(headsUpAppearanceController);
        this.mStatusBarWindow.setStatusBarView(this.mStatusBarView);
        updateAreThereNotifications();
        checkBarModes();
        this.mBurnInProtectionController = new BurnInProtectionController(this.mContext, this, this.mStatusBarView);
        this.mStatusBarContent = (LinearLayout) this.mStatusBarView.findViewById(C1777R$id.status_bar_contents);
        this.mCenterClockLayout = this.mStatusBarView.findViewById(C1777R$id.center_clock_layout);
        handleCutout();
    }

    public /* synthetic */ void lambda$makeStatusBarView$5$StatusBar(ScrimState scrimState, Float f, ColorExtractor.GradientColors gradientColors) {
        this.mLightBarController.setScrimState(scrimState, f.floatValue(), gradientColors);
    }

    public /* synthetic */ void lambda$makeStatusBarView$6$StatusBar(Integer num) {
        StatusBarWindowController statusBarWindowController = this.mStatusBarWindowController;
        if (statusBarWindowController != null) {
            statusBarWindowController.setScrimsVisibility(num.intValue());
        }
        StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
        if (statusBarWindowView != null) {
            statusBarWindowView.onScrimVisibilityChanged(num.intValue());
        }
    }

    public /* synthetic */ void lambda$makeStatusBarView$7$StatusBar(View view) {
        Uri reportRejectedTouch = this.mFalsingManager.reportRejectedTouch();
        if (reportRejectedTouch != null) {
            StringWriter stringWriter = new StringWriter();
            stringWriter.write("Build info: ");
            stringWriter.write(SystemProperties.get("ro.build.description"));
            stringWriter.write("\nSerial number: ");
            stringWriter.write(SystemProperties.get("ro.serialno"));
            stringWriter.write("\n");
            PrintWriter printWriter = new PrintWriter(stringWriter);
            FalsingLog.dump(printWriter);
            printWriter.flush();
            startActivityDismissingKeyguard(Intent.createChooser(new Intent("android.intent.action.SEND").setType("*/*").putExtra("android.intent.extra.SUBJECT", "Rejected touch report").putExtra("android.intent.extra.STREAM", reportRejectedTouch).putExtra("android.intent.extra.TEXT", stringWriter.toString()), "Share rejected touch report").addFlags(268435456), true, true);
        }
    }

    public void updateBlurVisibility() {
        float expandedFraction = this.mNotificationPanel.getExpandedFraction() * ((float) (((double) ((float) Settings.System.getInt(this.mContext.getContentResolver(), "qs_background_blur_alpha", 100))) / 100.0d));
        boolean z = expandedFraction > 0.0f && qsBlurIntensity() > 0;
        if (z && !this.blurperformed && !this.mIsKeyguard && isQSBlurEnabled()) {
            drawBlurView();
            this.blurperformed = true;
            this.mQSBlurView.setVisibility(0);
        } else if (!z || this.mState == 1) {
            this.blurperformed = false;
            this.mQSBlurView.setVisibility(8);
        }
        this.mQSBlurView.setAlpha(expandedFraction);
    }

    /* access modifiers changed from: private */
    public void drawBlurView() {
        Bitmap screenshotSurface = ImageUtilities.screenshotSurface(this.mContext);
        if (screenshotSurface == null) {
            this.mQSBlurView.setImageDrawable((Drawable) null);
        } else {
            this.mQSBlurView.setImageBitmap(ImageUtilities.blurImage(this.mContext, screenshotSurface, qsBlurIntensity()));
        }
    }

    private boolean isQSBlurEnabled() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "qs_background_blur", 0) != 0;
    }

    private int qsBlurIntensity() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "qs_background_blur_intensity", 30);
    }

    /* access modifiers changed from: private */
    public void adjustBrightness(int i) {
        this.mBrightnessChanged = true;
        float min = (Math.min(0.85f, Math.max(BRIGHTNESS_CONTROL_PADDING, ((float) i) / getDisplayWidth())) - BRIGHTNESS_CONTROL_PADDING) / 0.7f;
        if (this.mAutomaticBrightness) {
            final float min2 = Math.min(Math.max((min * 2.0f) - 1.0f, -1.0f), 1.0f);
            this.mDisplayManager.setTemporaryAutoBrightnessAdjustment(min2);
            AsyncTask.execute(new Runnable() {
                public void run() {
                    Settings.System.putFloatForUser(StatusBar.this.mContext.getContentResolver(), "screen_auto_brightness_adj", min2, -2);
                }
            });
            return;
        }
        int i2 = this.mMinBrightness;
        final int max = Math.max(Math.min(i2 + Math.round(min * ((float) (255 - i2))), 255), this.mMinBrightness);
        this.mDisplayManager.setTemporaryBrightness(max);
        AsyncTask.execute(new Runnable() {
            public void run() {
                Settings.System.putIntForUser(StatusBar.this.mContext.getContentResolver(), "screen_brightness", max, -2);
            }
        });
    }

    private void brightnessControl(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        if (action == 0) {
            if (rawY < this.mQuickQsTotalHeight) {
                this.mLinger = 0;
                this.mInitialTouchX = rawX;
                this.mInitialTouchY = rawY;
                this.mJustPeeked = true;
                this.mHandler.removeCallbacks(this.mLongPressBrightnessChange);
                this.mHandler.postDelayed(this.mLongPressBrightnessChange, 750);
            }
        } else if (action == 2) {
            if (rawY >= this.mQuickQsTotalHeight || !this.mJustPeeked) {
                if (rawY > this.mQuickQsTotalHeight) {
                    this.mJustPeeked = false;
                }
                this.mHandler.removeCallbacks(this.mLongPressBrightnessChange);
            } else if (this.mLinger > 20) {
                adjustBrightness(rawX);
            } else {
                int abs = Math.abs(rawX - this.mInitialTouchX);
                int abs2 = Math.abs(rawY - this.mInitialTouchY);
                int scaledTouchSlop = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
                if (abs > abs2) {
                    this.mLinger++;
                }
                if (abs > scaledTouchSlop || abs2 > scaledTouchSlop) {
                    this.mHandler.removeCallbacks(this.mLongPressBrightnessChange);
                }
            }
        } else if (action == 1 || action == 3) {
            this.mHandler.removeCallbacks(this.mLongPressBrightnessChange);
        }
    }

    /* access modifiers changed from: protected */
    public C0862QS createDefaultQSFragment() {
        return (C0862QS) FragmentHostManager.get(this.mStatusBarWindow).create(QSFragment.class);
    }

    private void setUpPresenter() {
        this.mActivityLaunchAnimator = new ActivityLaunchAnimator(this.mStatusBarWindow, this, this.mNotificationPanel, (NotificationListContainer) this.mStackScroller);
        NotificationRowBinderImpl notificationRowBinderImpl = new NotificationRowBinderImpl(this.mContext, this.mAllowNotificationLongPress, this.mKeyguardBypassController, this.mStatusBarStateController);
        this.mPresenter = new StatusBarNotificationPresenter(this.mContext, this.mNotificationPanel, this.mHeadsUpManager, this.mStatusBarWindow, this.mStackScroller, this.mDozeScrimController, this.mScrimController, this.mActivityLaunchAnimator, this.mDynamicPrivacyController, this.mNotificationAlertingManager, notificationRowBinderImpl);
        this.mPresenter.addCallback(this);
        this.mNotificationListController = new NotificationListController(this.mEntryManager, (NotificationListContainer) this.mStackScroller, this.mForegroundServiceController, this.mDeviceProvisionedController);
        AppOpsController appOpsController = this.mAppOpsController;
        if (appOpsController != null) {
            appOpsController.addCallback(APP_OPS, this);
        }
        this.mNotificationShelf.setOnActivatedListener(this.mPresenter);
        this.mRemoteInputManager.getController().addCallback(this.mStatusBarWindowController);
        this.mShadeController = (ShadeController) Dependency.get(ShadeController.class);
        ActivityStarter activityStarter = (ActivityStarter) Dependency.get(ActivityStarter.class);
        Context context = this.mContext;
        Context context2 = context;
        CommandQueue commandQueue = this.mCommandQueue;
        AssistManager assistManager = this.mAssistManager;
        NotificationPanelView notificationPanelView = this.mNotificationPanel;
        StatusBarNotificationPresenter statusBarNotificationPresenter = this.mPresenter;
        NotificationEntryManager notificationEntryManager = this.mEntryManager;
        HeadsUpManagerPhone headsUpManagerPhone = this.mHeadsUpManager;
        ActivityLaunchAnimator activityLaunchAnimator = this.mActivityLaunchAnimator;
        IStatusBarService iStatusBarService = this.mBarService;
        SysuiStatusBarStateController sysuiStatusBarStateController = this.mStatusBarStateController;
        Context context3 = context;
        KeyguardManager keyguardManager = this.mKeyguardManager;
        NotificationRowBinderImpl notificationRowBinderImpl2 = notificationRowBinderImpl;
        Context context4 = context3;
        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = r3;
        IDreamManager iDreamManager = this.mDreamManager;
        NotificationRemoteInputManager notificationRemoteInputManager = this.mRemoteInputManager;
        NotificationGroupManager notificationGroupManager = this.mGroupManager;
        NotificationLockscreenUserManager notificationLockscreenUserManager = this.mLockscreenUserManager;
        ShadeController shadeController = this.mShadeController;
        KeyguardMonitor keyguardMonitor = this.mKeyguardMonitor;
        NotificationInterruptionStateProvider notificationInterruptionStateProvider = this.mNotificationInterruptionStateProvider;
        MetricsLogger metricsLogger = this.mMetricsLogger;
        LockPatternUtils lockPatternUtils = r2;
        LockPatternUtils lockPatternUtils2 = new LockPatternUtils(context4);
        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter2 = new StatusBarNotificationActivityStarter(context2, commandQueue, assistManager, notificationPanelView, statusBarNotificationPresenter, notificationEntryManager, headsUpManagerPhone, activityStarter, activityLaunchAnimator, iStatusBarService, sysuiStatusBarStateController, keyguardManager, iDreamManager, notificationRemoteInputManager, (StatusBarRemoteInputCallback) Dependency.get(NotificationRemoteInputManager.Callback.class), notificationGroupManager, notificationLockscreenUserManager, shadeController, keyguardMonitor, notificationInterruptionStateProvider, metricsLogger, lockPatternUtils, (Handler) Dependency.get(Dependency.MAIN_HANDLER), (Handler) Dependency.get(Dependency.BG_HANDLER), this.mActivityIntentHelper, this.mBubbleController);
        this.mNotificationActivityStarter = statusBarNotificationActivityStarter;
        this.mGutsManager.setNotificationActivityStarter(this.mNotificationActivityStarter);
        NotificationRowBinderImpl notificationRowBinderImpl3 = notificationRowBinderImpl2;
        this.mEntryManager.setRowBinder(notificationRowBinderImpl3);
        notificationRowBinderImpl3.setNotificationClicker(new NotificationClicker(this, (BubbleController) Dependency.get(BubbleController.class), this.mNotificationActivityStarter));
        this.mGroupAlertTransferHelper.bind(this.mEntryManager, this.mGroupManager);
        this.mNotificationListController.bind();
    }

    /* access modifiers changed from: protected */
    public void getDependencies() {
        this.mIconController = (StatusBarIconController) Dependency.get(StatusBarIconController.class);
        this.mLightBarController = (LightBarController) Dependency.get(LightBarController.class);
        this.mKeyguardMonitor = (KeyguardMonitor) Dependency.get(KeyguardMonitor.class);
        this.mScreenLifecycle = (ScreenLifecycle) Dependency.get(ScreenLifecycle.class);
        this.mWakefulnessLifecycle = (WakefulnessLifecycle) Dependency.get(WakefulnessLifecycle.class);
        this.mEntryManager = (NotificationEntryManager) Dependency.get(NotificationEntryManager.class);
        this.mForegroundServiceController = (ForegroundServiceController) Dependency.get(ForegroundServiceController.class);
        this.mGroupAlertTransferHelper = (NotificationGroupAlertTransferHelper) Dependency.get(NotificationGroupAlertTransferHelper.class);
        this.mGroupManager = (NotificationGroupManager) Dependency.get(NotificationGroupManager.class);
        this.mGutsManager = (NotificationGutsManager) Dependency.get(NotificationGutsManager.class);
        this.mLockscreenUserManager = (NotificationLockscreenUserManager) Dependency.get(NotificationLockscreenUserManager.class);
        this.mMediaManager = (NotificationMediaManager) Dependency.get(NotificationMediaManager.class);
        this.mMediaManager.addCallback(this);
        this.mNotificationInterruptionStateProvider = (NotificationInterruptionStateProvider) Dependency.get(NotificationInterruptionStateProvider.class);
        this.mNotificationListener = (NotificationListener) Dependency.get(NotificationListener.class);
        this.mNotificationLogger = (NotificationLogger) Dependency.get(NotificationLogger.class);
        this.mEntryManager.setStatusBar(this);
        this.mNotificationAlertingManager.setStatusBar(this);
        this.mRemoteInputManager = (NotificationRemoteInputManager) Dependency.get(NotificationRemoteInputManager.class);
        this.mViewHierarchyManager = (NotificationViewHierarchyManager) Dependency.get(NotificationViewHierarchyManager.class);
        this.mVisualStabilityManager = (VisualStabilityManager) Dependency.get(VisualStabilityManager.class);
        this.mBatteryController = (BatteryController) Dependency.get(BatteryController.class);
        this.mNetworkController = (NetworkController) Dependency.get(NetworkController.class);
        this.mZenController = (ZenModeController) Dependency.get(ZenModeController.class);
        this.mAppOpsController = (AppOpsController) Dependency.get(AppOpsController.class);
        this.mAssistManager = (AssistManager) Dependency.get(AssistManager.class);
        this.mBubbleController = (BubbleController) Dependency.get(BubbleController.class);
        this.mColorExtractor = (SysuiColorExtractor) Dependency.get(SysuiColorExtractor.class);
        this.mNavigationBarController = (NavigationBarController) Dependency.get(NavigationBarController.class);
        this.mUserSwitcherController = (UserSwitcherController) Dependency.get(UserSwitcherController.class);
        this.mVibratorHelper = (VibratorHelper) Dependency.get(VibratorHelper.class);
        this.mOverlayManager = IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay"));
        this.mTaskHelper = (TaskHelper) Dependency.get(TaskHelper.class);
    }

    /* access modifiers changed from: protected */
    public void setUpQuickSettingsTilePanel() {
        View findViewById = this.mStatusBarWindow.findViewById(C1777R$id.qs_frame);
        if (findViewById != null) {
            FragmentHostManager fragmentHostManager = FragmentHostManager.get(findViewById);
            int i = C1777R$id.qs_frame;
            ExtensionController.ExtensionBuilder<C0862QS> newExtension = ((ExtensionController) Dependency.get(ExtensionController.class)).newExtension(C0862QS.class);
            newExtension.withPlugin(C0862QS.class);
            newExtension.withDefault(new Supplier() {
                public final Object get() {
                    return StatusBar.this.createDefaultQSFragment();
                }
            });
            ExtensionFragmentListener.attachExtensonToFragment(findViewById, C0862QS.TAG, i, newExtension.build());
            this.mBrightnessMirrorController = new BrightnessMirrorController(this.mContext, this.mStatusBarWindow, new Consumer() {
                public final void accept(Object obj) {
                    StatusBar.this.lambda$setUpQuickSettingsTilePanel$8$StatusBar((Boolean) obj);
                }
            });
            fragmentHostManager.addTagListener(C0862QS.TAG, new FragmentHostManager.FragmentListener() {
                public final void onFragmentViewCreated(String str, Fragment fragment) {
                    StatusBar.this.lambda$setUpQuickSettingsTilePanel$9$StatusBar(str, fragment);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setUpQuickSettingsTilePanel$8$StatusBar(Boolean bool) {
        this.mBrightnessMirrorVisible = bool.booleanValue();
        updateScrimController();
    }

    public /* synthetic */ void lambda$setUpQuickSettingsTilePanel$9$StatusBar(String str, Fragment fragment) {
        C0862QS qs = (C0862QS) fragment;
        if (qs instanceof QSFragment) {
            QSFragment qSFragment = (QSFragment) qs;
            this.mQSBarHeader = qSFragment.getHeader();
            this.mQSPanel = qSFragment.getQsPanel();
            this.mQSPanel.setBrightnessMirror(this.mBrightnessMirrorController);
            this.mQuickQSPanel = qSFragment.getQuickQsPanel();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: setUpDisableFlags */
    public void lambda$start$3$StatusBar(int i, int i2) {
        this.mCommandQueue.disable(this.mDisplayId, i, i2, false);
    }

    public void addAfterKeyguardGoneRunnable(Runnable runnable) {
        this.mStatusBarKeyguardViewManager.addAfterKeyguardGoneRunnable(runnable);
    }

    public boolean isDozing() {
        return this.mDozing;
    }

    public void wakeUpIfDozing(long j, View view, String str) {
        if (this.mDozing) {
            ((PowerManager) this.mContext.getSystemService(PowerManager.class)).wakeUp(j, 4, "com.android.systemui:" + str);
            this.mWakeUpComingFromTouch = true;
            view.getLocationInWindow(this.mTmpInt2);
            this.mWakeUpTouchLocation = new PointF((float) (this.mTmpInt2[0] + (view.getWidth() / 2)), (float) (this.mTmpInt2[1] + (view.getHeight() / 2)));
            this.mFalsingManager.onScreenOnFromTouch();
        }
    }

    /* access modifiers changed from: protected */
    public void createNavigationBar(RegisterStatusBarResult registerStatusBarResult) {
        this.mNavigationBarController.createNavigationBars(true, registerStatusBarResult);
    }

    /* access modifiers changed from: protected */
    public View.OnTouchListener getStatusBarWindowTouchListener() {
        return new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return StatusBar.this.lambda$getStatusBarWindowTouchListener$10$StatusBar(view, motionEvent);
            }
        };
    }

    public /* synthetic */ boolean lambda$getStatusBarWindowTouchListener$10$StatusBar(View view, MotionEvent motionEvent) {
        this.mAutoHideController.checkUserAutoHide(motionEvent);
        this.mRemoteInputManager.checkRemoteInputOutside(motionEvent);
        if (motionEvent.getAction() == 0 && this.mExpandedVisible) {
            animateCollapsePanels();
        }
        return this.mStatusBarWindow.onTouchEvent(motionEvent);
    }

    private void inflateShelf() {
        this.mNotificationShelf = (NotificationShelf) this.mInjectionInflater.injectable(LayoutInflater.from(this.mContext)).inflate(C1779R$layout.status_bar_notification_shelf, this.mStackScroller, false);
        this.mNotificationShelf.setOnClickListener(this.mGoToLockedShadeListener);
    }

    public void onDensityOrFontScaleChanged() {
        BrightnessMirrorController brightnessMirrorController = this.mBrightnessMirrorController;
        if (brightnessMirrorController != null) {
            brightnessMirrorController.onDensityOrFontScaleChanged();
        }
        ((UserInfoControllerImpl) Dependency.get(UserInfoController.class)).onDensityOrFontScaleChanged();
        ((UserSwitcherController) Dependency.get(UserSwitcherController.class)).onDensityOrFontScaleChanged();
        KeyguardUserSwitcher keyguardUserSwitcher = this.mKeyguardUserSwitcher;
        if (keyguardUserSwitcher != null) {
            keyguardUserSwitcher.onDensityOrFontScaleChanged();
        }
        this.mNotificationIconAreaController.onDensityOrFontScaleChanged(this.mContext);
        this.mHeadsUpManager.onDensityOrFontScaleChanged();
    }

    public void onThemeChanged() {
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
        if (statusBarKeyguardViewManager != null) {
            statusBarKeyguardViewManager.onThemeChanged();
        }
        View view = this.mAmbientIndicationContainer;
        if (view instanceof AutoReinflateContainer) {
            ((AutoReinflateContainer) view).inflateLayout();
        }
        this.mNotificationIconAreaController.onThemeChanged();
    }

    public void onOverlayChanged() {
        BrightnessMirrorController brightnessMirrorController = this.mBrightnessMirrorController;
        if (brightnessMirrorController != null) {
            brightnessMirrorController.onOverlayChanged();
        }
        this.mNotificationPanel.onThemeChanged();
        onThemeChanged();
    }

    public void onUiModeChanged() {
        BrightnessMirrorController brightnessMirrorController = this.mBrightnessMirrorController;
        if (brightnessMirrorController != null) {
            brightnessMirrorController.onUiModeChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void createUserSwitcher() {
        this.mKeyguardUserSwitcher = new KeyguardUserSwitcher(this.mContext, (ViewStub) this.mStatusBarWindow.findViewById(C1777R$id.keyguard_user_switcher), (KeyguardStatusBarView) this.mStatusBarWindow.findViewById(C1777R$id.keyguard_header), this.mNotificationPanel);
    }

    /* access modifiers changed from: protected */
    public void inflateStatusBarWindow(Context context) {
        this.mStatusBarWindow = (StatusBarWindowView) this.mInjectionInflater.injectable(LayoutInflater.from(context)).inflate(C1779R$layout.super_status_bar, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public void startKeyguard() {
        Trace.beginSection("StatusBar#startKeyguard");
        KeyguardViewMediator keyguardViewMediator = (KeyguardViewMediator) getComponent(KeyguardViewMediator.class);
        Context context = this.mContext;
        this.mBiometricUnlockController = new BiometricUnlockController(context, this.mDozeScrimController, keyguardViewMediator, this.mScrimController, this, UnlockMethodCache.getInstance(context), new Handler(), this.mKeyguardUpdateMonitor, this.mKeyguardBypassController);
        putComponent(BiometricUnlockController.class, this.mBiometricUnlockController);
        this.mStatusBarKeyguardViewManager = keyguardViewMediator.registerStatusBar(this, getBouncerContainer(), this.mNotificationPanel, this.mBiometricUnlockController, (ViewGroup) this.mStatusBarWindow.findViewById(C1777R$id.lock_icon_container), this.mStackScroller, this.mKeyguardBypassController, this.mFalsingManager);
        this.mKeyguardIndicationController.setStatusBarKeyguardViewManager(this.mStatusBarKeyguardViewManager);
        this.mBiometricUnlockController.setStatusBarKeyguardViewManager(this.mStatusBarKeyguardViewManager);
        this.mRemoteInputManager.getController().addCallback(this.mStatusBarKeyguardViewManager);
        this.mDynamicPrivacyController.setStatusBarKeyguardViewManager(this.mStatusBarKeyguardViewManager);
        this.mKeyguardViewMediatorCallback = keyguardViewMediator.getViewMediatorCallback();
        this.mLightBarController.setBiometricUnlockController(this.mBiometricUnlockController);
        this.mMediaManager.setBiometricUnlockController(this.mBiometricUnlockController);
        ((KeyguardDismissUtil) Dependency.get(KeyguardDismissUtil.class)).setDismissHandler(new KeyguardDismissHandler() {
            public final void executeWhenUnlocked(ActivityStarter.OnDismissAction onDismissAction, boolean z) {
                StatusBar.this.executeWhenUnlocked(onDismissAction, z);
            }
        });
        Trace.endSection();
    }

    /* access modifiers changed from: protected */
    public View getStatusBarView() {
        return this.mStatusBarView;
    }

    public StatusBarWindowView getStatusBarWindow() {
        return this.mStatusBarWindow;
    }

    /* access modifiers changed from: protected */
    public ViewGroup getBouncerContainer() {
        return this.mStatusBarWindow;
    }

    public void createTicker(Context context, View view, TickerView tickerView, ImageSwitcher imageSwitcher, View view2) {
        if (this.mTicker == null) {
            this.mTicker = new MyTicker(context, view);
        }
        ((MyTicker) this.mTicker).setView(view2);
        tickerView.setTicker(this.mTicker);
        this.mTicker.setViews(tickerView, imageSwitcher);
    }

    public void disableTicker() {
        this.mTickerEnabled = false;
    }

    public int getStatusBarHeight() {
        if (this.mNaturalBarHeight < 0) {
            this.mNaturalBarHeight = this.mContext.getResources().getDimensionPixelSize(17105434);
        }
        return this.mNaturalBarHeight;
    }

    /* access modifiers changed from: protected */
    public boolean toggleSplitScreenMode(int i, int i2) {
        int i3 = 0;
        if (this.mRecents == null) {
            return false;
        }
        if (WindowManagerProxy.getInstance().getDockSide() == -1) {
            int navBarPosition = WindowManagerWrapper.getInstance().getNavBarPosition(this.mDisplayId);
            if (navBarPosition == -1) {
                return false;
            }
            if (navBarPosition == 1) {
                i3 = 1;
            }
            return this.mRecents.splitPrimaryTask(i3, (Rect) null, i);
        }
        Divider divider = (Divider) getComponent(Divider.class);
        if (divider != null) {
            if (divider.isMinimized() && !divider.isHomeStackResizable()) {
                return false;
            }
            divider.onUndockingTask();
            if (i2 != -1) {
                this.mMetricsLogger.action(i2);
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
        if (ONLY_CORE_APPS == false) goto L_0x002a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateQsExpansionEnabled() {
        /*
            r3 = this;
            com.android.systemui.statusbar.policy.DeviceProvisionedController r0 = r3.mDeviceProvisionedController
            boolean r0 = r0.isDeviceProvisioned()
            r1 = 1
            if (r0 == 0) goto L_0x0029
            boolean r0 = r3.mUserSetup
            if (r0 != 0) goto L_0x0017
            com.android.systemui.statusbar.policy.UserSwitcherController r0 = r3.mUserSwitcherController
            if (r0 == 0) goto L_0x0017
            boolean r0 = r0.isSimpleUserSwitcher()
            if (r0 != 0) goto L_0x0029
        L_0x0017:
            int r0 = r3.mDisabled2
            r2 = r0 & 4
            if (r2 != 0) goto L_0x0029
            r0 = r0 & r1
            if (r0 != 0) goto L_0x0029
            boolean r0 = r3.mDozing
            if (r0 != 0) goto L_0x0029
            boolean r0 = ONLY_CORE_APPS
            if (r0 != 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            r1 = 0
        L_0x002a:
            com.android.systemui.statusbar.phone.NotificationPanelView r3 = r3.mNotificationPanel
            r3.setQsExpansionEnabled(r1)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "updateQsExpansionEnabled - QS Expand enabled: "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            java.lang.String r0 = "StatusBar"
            android.util.Log.d(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBar.updateQsExpansionEnabled():void");
    }

    public void addQsTile(ComponentName componentName) {
        QSPanel qSPanel = this.mQSPanel;
        if (qSPanel != null && qSPanel.getHost() != null) {
            this.mQSPanel.getHost().addTile(componentName);
        }
    }

    public void remQsTile(ComponentName componentName) {
        QSPanel qSPanel = this.mQSPanel;
        if (qSPanel != null && qSPanel.getHost() != null) {
            this.mQSPanel.getHost().removeTile(componentName);
        }
    }

    public void clickTile(ComponentName componentName) {
        this.mQSPanel.clickTile(componentName);
    }

    public boolean areNotificationsHidden() {
        return this.mZenController.areNotificationsHiddenInShade();
    }

    public void requestNotificationUpdate() {
        this.mEntryManager.updateNotifications();
    }

    /* access modifiers changed from: protected */
    public boolean hasActiveVisibleNotifications() {
        return this.mEntryManager.getNotificationData().hasActiveVisibleNotifications();
    }

    /* access modifiers changed from: protected */
    public boolean hasActiveOngoingNotifications() {
        return this.mEntryManager.getNotificationData().hasActiveOngoingNotifications();
    }

    public void requestFaceAuth() {
        if (!this.mUnlockMethodCache.canSkipBouncer()) {
            this.mKeyguardUpdateMonitor.requestFaceAuth();
        }
    }

    public void updateAreThereNotifications() {
        C14779 r0;
        PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
        if (phoneStatusBarView != null) {
            final View findViewById = phoneStatusBarView.findViewById(C1777R$id.notification_lights_out);
            boolean z = true;
            boolean z2 = hasActiveNotifications() && !areLightsOn();
            if (findViewById.getAlpha() != 1.0f) {
                z = false;
            }
            if (z2 != z) {
                float f = 0.0f;
                if (z2) {
                    findViewById.setAlpha(0.0f);
                    findViewById.setVisibility(0);
                }
                ViewPropertyAnimator animate = findViewById.animate();
                if (z2) {
                    f = 1.0f;
                }
                ViewPropertyAnimator interpolator = animate.alpha(f).setDuration(z2 ? 750 : 250).setInterpolator(new AccelerateInterpolator(2.0f));
                if (z2) {
                    r0 = null;
                } else {
                    r0 = new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            findViewById.setVisibility(8);
                        }
                    };
                }
                interpolator.setListener(r0).start();
            }
        }
        this.mMediaManager.findAndUpdateMediaNotifications();
    }

    private void updateReportRejectedTouchVisibility() {
        View view = this.mReportRejectedTouch;
        if (view != null) {
            view.setVisibility((this.mState != 1 || this.mDozing || !this.mFalsingManager.isReportingEnabled()) ? 4 : 0);
        }
    }

    public void disable(int i, int i2, int i3, boolean z) {
        int i4 = i2;
        if (i == this.mDisplayId) {
            int adjustDisableFlags = this.mRemoteInputQuickSettingsDisabler.adjustDisableFlags(i3);
            int i5 = this.mStatusBarWindowState;
            int i6 = this.mDisabled1 ^ i4;
            this.mDisabled1 = i4;
            int i7 = this.mDisabled2 ^ adjustDisableFlags;
            this.mDisabled2 = adjustDisableFlags;
            StringBuilder sb = new StringBuilder();
            sb.append("disable<");
            int i8 = i4 & 65536;
            sb.append(i8 != 0 ? 'E' : 'e');
            int i9 = 65536 & i6;
            sb.append(i9 != 0 ? '!' : ' ');
            int i10 = i4 & 131072;
            char c = 'i';
            sb.append(i10 != 0 ? 'I' : 'i');
            int i11 = 131072 & i6;
            sb.append(i11 != 0 ? '!' : ' ');
            int i12 = i4 & 262144;
            sb.append(i12 != 0 ? 'A' : 'a');
            int i13 = i6 & 262144;
            sb.append(i13 != 0 ? '!' : ' ');
            sb.append((i4 & 1048576) != 0 ? 'S' : 's');
            sb.append((i6 & 1048576) != 0 ? '!' : ' ');
            sb.append((i4 & 4194304) != 0 ? 'B' : 'b');
            sb.append((4194304 & i6) != 0 ? '!' : ' ');
            sb.append((i4 & 2097152) != 0 ? 'H' : 'h');
            sb.append((2097152 & i6) != 0 ? '!' : ' ');
            int i14 = 16777216 & i4;
            sb.append(i14 != 0 ? 'R' : 'r');
            int i15 = 16777216 & i6;
            sb.append(i15 != 0 ? '!' : ' ');
            sb.append((8388608 & i4) != 0 ? 'C' : 'c');
            sb.append((8388608 & i6) != 0 ? '!' : ' ');
            sb.append((33554432 & i4) != 0 ? 'S' : 's');
            sb.append((i6 & 33554432) != 0 ? '!' : ' ');
            sb.append("> disable2<");
            sb.append((adjustDisableFlags & 1) != 0 ? 'Q' : 'q');
            int i16 = i7 & 1;
            sb.append(i16 != 0 ? '!' : ' ');
            if ((adjustDisableFlags & 2) != 0) {
                c = 'I';
            }
            sb.append(c);
            sb.append((i7 & 2) != 0 ? '!' : ' ');
            sb.append((adjustDisableFlags & 4) != 0 ? 'N' : 'n');
            int i17 = i7 & 4;
            sb.append(i17 != 0 ? '!' : ' ');
            sb.append('>');
            Log.d(TAG, sb.toString());
            if (!(i9 == 0 || i8 == 0)) {
                animateCollapsePanels();
            }
            if (!(i15 == 0 || i14 == 0)) {
                this.mHandler.removeMessages(MSG_HIDE_RECENT_APPS);
                this.mHandler.sendEmptyMessage(MSG_HIDE_RECENT_APPS);
            }
            if (i13 != 0) {
                this.mNotificationInterruptionStateProvider.setDisableNotificationAlerts(i12 != 0);
            }
            if (!(i11 == 0 || i10 == 0 || !this.mTicking)) {
                haltTicker();
            }
            if (i16 != 0) {
                updateQsExpansionEnabled();
            }
            if (i17 != 0) {
                updateQsExpansionEnabled();
                if ((i4 & 4) != 0) {
                    animateCollapsePanels();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public C1479H createHandler() {
        return new C1479H();
    }

    public void startActivity(Intent intent, boolean z, boolean z2, int i) {
        startActivityDismissingKeyguard(intent, z, z2, i);
    }

    public void startActivity(Intent intent, boolean z) {
        startActivityDismissingKeyguard(intent, false, z);
    }

    public void startActivity(Intent intent, boolean z, boolean z2) {
        startActivityDismissingKeyguard(intent, z, z2);
    }

    public void startActivity(Intent intent, boolean z, ActivityStarter.Callback callback) {
        startActivityDismissingKeyguard(intent, false, z, false, callback, 0);
    }

    public void setQsExpanded(boolean z) {
        this.mStatusBarWindowController.setQsExpanded(z);
        this.mNotificationPanel.setStatusAccessibilityImportance(z ? 4 : 0);
        if (getNavigationBarView() != null) {
            getNavigationBarView().onStatusBarPanelStateChanged();
        }
    }

    public boolean isWakeUpComingFromTouch() {
        return this.mWakeUpComingFromTouch;
    }

    public boolean isFalsingThresholdNeeded() {
        return this.mStatusBarStateController.getState() == 1;
    }

    public void onKeyguardViewManagerStatesUpdated() {
        logStateToEventlog();
    }

    public void onUnlockMethodStateChanged() {
        updateKeyguardState();
        logStateToEventlog();
    }

    public void onHeadsUpPinnedModeChanged(boolean z) {
        if (z) {
            this.mStatusBarWindowController.setHeadsUpShowing(true);
            this.mStatusBarWindowController.setForceStatusBarVisible(true);
            if (this.mNotificationPanel.isFullyCollapsed()) {
                this.mNotificationPanel.requestLayout();
                this.mStatusBarWindowController.setForceWindowCollapsed(true);
                this.mNotificationPanel.post(new Runnable() {
                    public final void run() {
                        StatusBar.this.lambda$onHeadsUpPinnedModeChanged$11$StatusBar();
                    }
                });
                return;
            }
            return;
        }
        boolean z2 = this.mKeyguardBypassController.getBypassEnabled() && this.mState == 1;
        if (!this.mNotificationPanel.isFullyCollapsed() || this.mNotificationPanel.isTracking() || z2) {
            this.mStatusBarWindowController.setHeadsUpShowing(false);
            if (z2) {
                this.mStatusBarWindowController.setForceStatusBarVisible(false);
                return;
            }
            return;
        }
        this.mHeadsUpManager.setHeadsUpGoingAway(true);
        this.mNotificationPanel.runAfterAnimationFinished(new Runnable() {
            public final void run() {
                StatusBar.this.lambda$onHeadsUpPinnedModeChanged$12$StatusBar();
            }
        });
    }

    public /* synthetic */ void lambda$onHeadsUpPinnedModeChanged$11$StatusBar() {
        this.mStatusBarWindowController.setForceWindowCollapsed(false);
    }

    public /* synthetic */ void lambda$onHeadsUpPinnedModeChanged$12$StatusBar() {
        if (!this.mHeadsUpManager.hasPinnedHeadsUp()) {
            this.mStatusBarWindowController.setHeadsUpShowing(false);
            this.mHeadsUpManager.setHeadsUpGoingAway(false);
        }
        this.mRemoteInputManager.onPanelCollapsed();
    }

    public void onHeadsUpStateChanged(NotificationEntry notificationEntry, boolean z) {
        this.mEntryManager.updateNotifications();
        if (isDozing() && z) {
            notificationEntry.setPulseSuppressed(false);
            this.mDozeServiceHost.fireNotificationPulse(notificationEntry);
            if (this.mPulsing) {
                this.mDozeScrimController.cancelPendingPulseTimeout();
            }
        }
        if (!z && !this.mHeadsUpManager.hasNotifications()) {
            this.mDozeScrimController.pulseOutNow();
        }
    }

    public boolean isKeyguardCurrentlySecure() {
        return !this.mUnlockMethodCache.canSkipBouncer();
    }

    public void setPanelExpanded(boolean z) {
        this.mPanelExpanded = z;
        updateHideIconsForBouncer(false);
        this.mStatusBarWindowController.setPanelExpanded(z);
        this.mVisualStabilityManager.setPanelExpanded(z);
        if (z && this.mStatusBarStateController.getState() != 1) {
            clearNotificationEffects();
        }
        if (!z) {
            this.mRemoteInputManager.onPanelCollapsed();
        }
    }

    public ViewGroup getNotificationScrollLayout() {
        return this.mStackScroller;
    }

    public boolean isPulsing() {
        return this.mPulsing;
    }

    public boolean hideStatusBarIconsWhenExpanded() {
        return this.mNotificationPanel.hideStatusBarIconsWhenExpanded();
    }

    public void onColorsChanged(ColorExtractor colorExtractor, int i) {
        updateTheme();
    }

    private void setCutoutOverlay(boolean z) {
        try {
            this.mOverlayManager.setEnabled("com.android.overlay.hidecutout", z, this.mLockscreenUserManager.getCurrentUserId());
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to handle cutout overlay", e);
        }
    }

    private void setStatusBarStockOverlay(boolean z) {
        try {
            this.mOverlayManager.setEnabled("com.android.overlay.statusbarstock", z, this.mLockscreenUserManager.getCurrentUserId());
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to handle statusbar height overlay", e);
        }
    }

    public View getAmbientIndicationContainer() {
        return this.mAmbientIndicationContainer;
    }

    public boolean isOccluded() {
        return this.mIsOccluded;
    }

    public void setOccluded(boolean z) {
        this.mIsOccluded = z;
        this.mScrimController.setKeyguardOccluded(z);
        updateHideIconsForBouncer(false);
    }

    public boolean hideStatusBarIconsForBouncer() {
        return this.mHideIconsForBouncer || this.mWereIconsJustHidden;
    }

    private void updateHideIconsForBouncer(boolean z) {
        boolean z2 = false;
        boolean z3 = this.mTopHidesStatusBar && this.mIsOccluded && (this.mStatusBarWindowHidden || this.mBouncerShowing);
        boolean z4 = !this.mPanelExpanded && !this.mIsOccluded && this.mBouncerShowing;
        if (z3 || z4) {
            z2 = true;
        }
        if (this.mHideIconsForBouncer != z2) {
            this.mHideIconsForBouncer = z2;
            if (z2 || !this.mBouncerWasShowingWhenHidden) {
                this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, z);
            } else {
                this.mWereIconsJustHidden = true;
                this.mHandler.postDelayed(new Runnable() {
                    public final void run() {
                        StatusBar.this.lambda$updateHideIconsForBouncer$13$StatusBar();
                    }
                }, 500);
            }
        }
        if (z2) {
            this.mBouncerWasShowingWhenHidden = this.mBouncerShowing;
        }
    }

    public /* synthetic */ void lambda$updateHideIconsForBouncer$13$StatusBar() {
        this.mWereIconsJustHidden = false;
        this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, true);
    }

    public boolean headsUpShouldBeVisible() {
        return this.mHeadsUpAppearanceController.shouldBeVisible();
    }

    public void onLaunchAnimationCancelled() {
        if (!this.mPresenter.isCollapsing()) {
            onClosingFinished();
        }
    }

    public void onExpandAnimationFinished(boolean z) {
        if (!this.mPresenter.isCollapsing()) {
            onClosingFinished();
        }
        if (z) {
            instantCollapseNotificationPanel();
        }
    }

    public void onExpandAnimationTimedOut() {
        ActivityLaunchAnimator activityLaunchAnimator;
        if (!this.mPresenter.isPresenterFullyCollapsed() || this.mPresenter.isCollapsing() || (activityLaunchAnimator = this.mActivityLaunchAnimator) == null || activityLaunchAnimator.isLaunchForActivity()) {
            collapsePanel(true);
        } else {
            onClosingFinished();
        }
    }

    public boolean areLaunchAnimationsEnabled() {
        return this.mState == 0;
    }

    public boolean isDeviceProvisioned() {
        return this.mDeviceProvisionedController.isDeviceProvisioned();
    }

    public boolean isDeviceInVrMode() {
        return this.mPresenter.isDeviceInVrMode();
    }

    public NotificationPresenter getPresenter() {
        return this.mPresenter;
    }

    public void setPartialScreenshot(boolean z) {
        if (getNavigationBarView() != null) {
            getNavigationBarView().setPartialScreenshot(z);
        }
        this.mNotificationInterruptionStateProvider.setPartialScreenshot(z);
    }

    /* renamed from: com.android.systemui.statusbar.phone.StatusBar$H */
    protected class C1479H extends Handler {
        protected C1479H() {
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == StatusBar.MSG_TOGGLE_KEYBOARD_SHORTCUTS_MENU) {
                StatusBar.this.toggleKeyboardShortcuts(message.arg1);
            } else if (i != StatusBar.MSG_DISMISS_KEYBOARD_SHORTCUTS_MENU) {
                switch (i) {
                    case StatusBar.MSG_OPEN_NOTIFICATION_PANEL /*1000*/:
                        StatusBar.this.animateExpandNotificationsPanel();
                        return;
                    case StatusBar.MSG_CLOSE_PANELS /*1001*/:
                        StatusBar.this.animateCollapsePanels();
                        return;
                    case StatusBar.MSG_OPEN_SETTINGS_PANEL /*1002*/:
                        StatusBar.this.animateExpandSettingsPanel((String) message.obj);
                        return;
                    case StatusBar.MSG_LAUNCH_TRANSITION_TIMEOUT /*1003*/:
                        StatusBar.this.onLaunchTransitionTimeout();
                        return;
                    default:
                        return;
                }
            } else {
                StatusBar.this.dismissKeyboardShortcuts();
            }
        }
    }

    public void maybeEscalateHeadsUp() {
        this.mHeadsUpManager.getAllEntries().forEach($$Lambda$StatusBar$Qz8oyL0qAMzuJuwPLHs4cVCa7kg.INSTANCE);
        this.mHeadsUpManager.releaseAllImmediately();
    }

    static /* synthetic */ void lambda$maybeEscalateHeadsUp$14(NotificationEntry notificationEntry) {
        StatusBarNotification statusBarNotification = notificationEntry.notification;
        Notification notification = statusBarNotification.getNotification();
        if (notification.fullScreenIntent != null) {
            try {
                EventLog.writeEvent(36003, statusBarNotification.getKey());
                notification.fullScreenIntent.send();
                notificationEntry.notifyFullScreenIntentLaunched();
            } catch (PendingIntent.CanceledException unused) {
            }
        }
    }

    public void handleSystemKey(int i) {
        if (88 == i || 87 == i) {
            this.mMediaManager.onSkipTrackEvent(i);
        } else if (this.mCommandQueue.panelsEnabled() && this.mKeyguardMonitor.isDeviceInteractive()) {
            if ((this.mKeyguardMonitor.isShowing() && !this.mKeyguardMonitor.isOccluded()) || !this.mUserSetup) {
                return;
            }
            if (280 == i) {
                this.mMetricsLogger.action(493);
                this.mNotificationPanel.collapse(false, 1.0f);
            } else if (281 == i) {
                this.mMetricsLogger.action(494);
                if (this.mNotificationPanel.isFullyCollapsed()) {
                    if (this.mVibrateOnOpening) {
                        this.mVibratorHelper.vibrate(2);
                    }
                    this.mNotificationPanel.expand(true);
                    ((NotificationListContainer) this.mStackScroller).setWillExpand(true);
                    this.mHeadsUpManager.unpinAll(true);
                    this.mMetricsLogger.count("panel_open", 1);
                } else if (!this.mNotificationPanel.isInSettings() && !this.mNotificationPanel.isExpanding()) {
                    this.mNotificationPanel.flingSettings(0.0f, 0);
                    this.mMetricsLogger.count("panel_open_qs", 1);
                }
            } else if (!this.mFpDismissNotifications) {
            } else {
                if ((282 == i || 283 == i) && !this.mNotificationPanel.isFullyCollapsed() && !this.mNotificationPanel.isExpanding()) {
                    this.mMetricsLogger.action(148);
                    ViewGroup viewGroup = this.mStackScroller;
                    NotificationStackScrollLayout notificationStackScrollLayout = (NotificationStackScrollLayout) viewGroup;
                    NotificationStackScrollLayout notificationStackScrollLayout2 = (NotificationStackScrollLayout) viewGroup;
                    notificationStackScrollLayout.clearNotifications(0, true, 282 == i);
                }
            }
        }
    }

    public void showPinningEnterExitToast(boolean z) {
        if (getNavigationBarView() != null) {
            getNavigationBarView().showPinningEnterExitToast(z);
        }
    }

    public void showPinningEscapeToast() {
        if (getNavigationBarView() != null) {
            getNavigationBarView().showPinningEscapeToast();
        }
    }

    public void toggleCameraFlash() {
        if (isScreenFullyOff() || !this.mDeviceInteractive || this.mPulsing || this.mDozing) {
            this.mDozeServiceHost.toggleFlashlightProximityCheck();
        } else {
            toggleFlashlight();
        }
    }

    /* access modifiers changed from: private */
    public void toggleFlashlight() {
        FlashlightController flashlightController = this.mFlashlightController;
        if (flashlightController != null) {
            flashlightController.initFlashLight();
            if (this.mFlashlightController.hasFlashlight() && this.mFlashlightController.isAvailable()) {
                FlashlightController flashlightController2 = this.mFlashlightController;
                flashlightController2.setFlashlight(!flashlightController2.isEnabled());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void makeExpandedVisible(boolean z) {
        if (z || (!this.mExpandedVisible && this.mCommandQueue.panelsEnabled())) {
            this.mExpandedVisible = true;
            this.mStatusBarWindowController.setPanelVisible(true);
            visibilityChanged(true);
            this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, !z);
            setInteracting(1, true);
        }
    }

    public void animateCollapsePanels() {
        animateCollapsePanels(0);
    }

    public void postAnimateCollapsePanels() {
        this.mHandler.post(this.mAnimateCollapsePanels);
    }

    public void postAnimateForceCollapsePanels() {
        this.mHandler.post(new Runnable() {
            public final void run() {
                StatusBar.this.lambda$postAnimateForceCollapsePanels$15$StatusBar();
            }
        });
    }

    public /* synthetic */ void lambda$postAnimateForceCollapsePanels$15$StatusBar() {
        animateCollapsePanels(0, true);
    }

    public void postAnimateOpenPanels() {
        this.mHandler.sendEmptyMessage(MSG_OPEN_SETTINGS_PANEL);
    }

    public void togglePanel() {
        if (this.mPanelExpanded) {
            animateCollapsePanels();
        } else {
            animateExpandNotificationsPanel();
        }
    }

    public void toggleSettingsPanel() {
        if (this.mPanelExpanded) {
            animateCollapsePanels();
        } else {
            animateExpandSettingsPanel((String) null);
        }
    }

    public void animateCollapsePanels(int i) {
        animateCollapsePanels(i, false, false, 1.0f);
    }

    public void animateCollapsePanels(int i, boolean z) {
        animateCollapsePanels(i, z, false, 1.0f);
    }

    public void animateCollapsePanels(int i, boolean z, boolean z2) {
        animateCollapsePanels(i, z, z2, 1.0f);
    }

    public void animateCollapsePanels(int i, boolean z, boolean z2, float f) {
        if (z || this.mState == 0) {
            if ((i & 2) == 0 && !this.mHandler.hasMessages(MSG_HIDE_RECENT_APPS)) {
                this.mHandler.removeMessages(MSG_HIDE_RECENT_APPS);
                this.mHandler.sendEmptyMessage(MSG_HIDE_RECENT_APPS);
            }
            if (this.mStatusBarWindow == null || !this.mNotificationPanel.canPanelBeCollapsed()) {
                BubbleController bubbleController = this.mBubbleController;
                if (bubbleController != null) {
                    bubbleController.collapseStack();
                    return;
                }
                return;
            }
            this.mStatusBarWindowController.setStatusBarFocusable(false);
            this.mStatusBarWindow.cancelExpandHelper();
            this.mStatusBarView.collapsePanel(true, z2, f);
            return;
        }
        runPostCollapseRunnables();
    }

    /* access modifiers changed from: private */
    public void runPostCollapseRunnables() {
        ArrayList arrayList = new ArrayList(this.mPostCollapseRunnables);
        this.mPostCollapseRunnables.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((Runnable) arrayList.get(i)).run();
        }
        this.mStatusBarKeyguardViewManager.readyForKeyguardDone();
    }

    public void onInputFocusTransfer(boolean z, float f) {
        if (this.mCommandQueue.panelsEnabled()) {
            if (z) {
                this.mNotificationPanel.startWaitingForOpenPanelGesture();
            } else {
                this.mNotificationPanel.stopWaitingForOpenPanelGesture(f);
            }
        }
    }

    public void animateExpandNotificationsPanel() {
        if (this.mCommandQueue.panelsEnabled()) {
            this.mNotificationPanel.expandWithoutQs();
        }
    }

    public void animateExpandSettingsPanel(String str) {
        if (this.mCommandQueue.panelsEnabled() && this.mUserSetup) {
            if (str != null) {
                this.mQSPanel.openDetails(str);
            }
            this.mNotificationPanel.expandWithQs();
        }
    }

    public void animateCollapseQuickSettings() {
        if (this.mState == 0) {
            this.mStatusBarView.collapsePanel(true, false, 1.0f);
        }
    }

    /* access modifiers changed from: package-private */
    public void makeExpandedInvisible() {
        if (this.mExpandedVisible && this.mStatusBarWindow != null) {
            this.mStatusBarView.collapsePanel(false, false, 1.0f);
            this.mNotificationPanel.closeQs();
            this.mExpandedVisible = false;
            visibilityChanged(false);
            this.mStatusBarWindowController.setPanelVisible(false);
            this.mStatusBarWindowController.setForceStatusBarVisible(false);
            this.mGutsManager.closeAndSaveGuts(true, true, true, -1, -1, true);
            runPostCollapseRunnables();
            setInteracting(1, false);
            if (!this.mNotificationActivityStarter.isCollapsingToShowActivityOverLockscreen()) {
                showBouncerIfKeyguard();
            }
            this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, this.mNotificationPanel.hideStatusBarIconsWhenExpanded());
            if (!this.mStatusBarKeyguardViewManager.isShowing()) {
                WindowManagerGlobal.getInstance().trimMemory(20);
            }
        }
    }

    public boolean interceptTouchEvent(MotionEvent motionEvent) {
        if (this.mBrightnessControl) {
            brightnessControl(motionEvent);
            if ((this.mDisabled1 & 65536) != 0) {
                return true;
            }
        }
        boolean z = motionEvent.getAction() == 1 || motionEvent.getAction() == 3;
        if (this.mStatusBarWindowState == 0) {
            if (!z || this.mExpandedVisible) {
                setInteracting(1, true);
            } else {
                setInteracting(1, false);
            }
        }
        if (this.mBrightnessChanged && z) {
            this.mBrightnessChanged = false;
            if (this.mJustPeeked && this.mExpandedVisible) {
                this.mNotificationPanel.fling(10.0f, false);
            }
        }
        return false;
    }

    public GestureRecorder getGestureRecorder() {
        return this.mGestureRec;
    }

    public BiometricUnlockController getBiometricUnlockController() {
        return this.mBiometricUnlockController;
    }

    public void setWindowState(int i, int i2, int i3) {
        if (i == this.mDisplayId) {
            boolean z = true;
            boolean z2 = i3 == 0;
            if (this.mStatusBarWindow != null && i2 == 1 && this.mStatusBarWindowState != i3) {
                this.mStatusBarWindowState = i3;
                if (!z2 && this.mState == 0) {
                    this.mStatusBarView.collapsePanel(false, false, 1.0f);
                }
                if (this.mStatusBarView != null) {
                    if (i3 != 2) {
                        z = false;
                    }
                    this.mStatusBarWindowHidden = z;
                    updateHideIconsForBouncer(false);
                }
            }
        }
    }

    public void setSystemUiVisibility(int i, int i2, int i3, int i4, int i5, Rect rect, Rect rect2, boolean z) {
        int i6 = i;
        int i7 = i2;
        int i8 = i5;
        if (i6 == this.mDisplayId) {
            NavigationBarController.SystemUiVisibility systemUiVisibility = this.mNavigationBarSystemUiVisibility;
            systemUiVisibility.displayId = i6;
            systemUiVisibility.vis = i7;
            systemUiVisibility.fullscreenStackVis = i3;
            systemUiVisibility.dockedStackVis = i4;
            systemUiVisibility.mask = i8;
            systemUiVisibility.fullscreenStackBounds = rect;
            systemUiVisibility.dockedStackBounds = rect2;
            systemUiVisibility.navbarColorManagedByIme = z;
            int i9 = this.mSystemUiVisibility;
            int i10 = ((~i8) & i9) | (i7 & i8);
            int i11 = i10 ^ i9;
            boolean z2 = false;
            if (i11 != 0) {
                this.mSystemUiVisibility = i10;
                if ((i11 & 1) != 0) {
                    updateAreThereNotifications();
                    if ((i7 & 1) != 0) {
                        animateCollapsePanels();
                        if (this.mTicking) {
                            haltTicker();
                        }
                    }
                }
                if ((i7 & 268435456) != 0) {
                    this.mNoAnimationOnNextBarModeChange = true;
                }
                int computeStatusBarMode = computeStatusBarMode(i9, i10);
                if (computeStatusBarMode != -1) {
                    z2 = true;
                }
                if (z2 && computeStatusBarMode != this.mStatusBarMode) {
                    this.mStatusBarMode = computeStatusBarMode;
                    checkBarModes();
                    this.mAutoHideController.touchAutoHide();
                }
                this.mStatusBarStateController.setSystemUiVisibility(this.mSystemUiVisibility);
            }
            this.mLightBarController.onSystemUiVisibilityChanged(i3, i4, i5, rect, rect2, z2, this.mStatusBarMode, z);
        }
    }

    /* access modifiers changed from: protected */
    public final int getSystemUiVisibility() {
        return this.mSystemUiVisibility;
    }

    /* access modifiers changed from: protected */
    public final int getDisplayId() {
        return this.mDisplayId;
    }

    public void showWirelessChargingAnimation(int i) {
        if (this.mDozing || this.mKeyguardManager.isKeyguardLocked()) {
            WirelessChargingAnimation.makeWirelessChargingAnimation(this.mContext, (Looper) null, i, new WirelessChargingAnimation.Callback() {
                public void onAnimationStarting() {
                    CrossFadeHelper.fadeOut((View) StatusBar.this.mNotificationPanel, 1.0f);
                }

                public void onAnimationEnded() {
                    CrossFadeHelper.fadeIn(StatusBar.this.mNotificationPanel);
                }
            }, this.mDozing).show();
        } else {
            WirelessChargingAnimation.makeWirelessChargingAnimation(this.mContext, (Looper) null, i, (WirelessChargingAnimation.Callback) null, false).show();
        }
    }

    public void onRecentsAnimationStateChanged(boolean z) {
        setInteracting(2, z);
    }

    /* access modifiers changed from: protected */
    public int computeStatusBarMode(int i, int i2) {
        return computeBarMode(i, i2);
    }

    /* access modifiers changed from: protected */
    public BarTransitions getStatusBarTransitions() {
        return this.mStatusBarWindow.getBarTransitions();
    }

    /* access modifiers changed from: protected */
    public int computeBarMode(int i, int i2) {
        int barMode = barMode(i);
        int barMode2 = barMode(i2);
        if (barMode == barMode2) {
            return -1;
        }
        return barMode2;
    }

    /* access modifiers changed from: package-private */
    public void checkBarModes() {
        if (!this.mDemoMode) {
            if (!(this.mStatusBarView == null || getStatusBarTransitions() == null)) {
                checkBarMode(this.mStatusBarMode, this.mStatusBarWindowState, getStatusBarTransitions());
            }
            this.mNavigationBarController.checkNavBarModes(this.mDisplayId);
            this.mNoAnimationOnNextBarModeChange = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void setQsScrimEnabled(boolean z) {
        this.mNotificationPanel.setQsScrimEnabled(z);
    }

    /* access modifiers changed from: package-private */
    public void checkBarMode(int i, int i2, BarTransitions barTransitions) {
        barTransitions.transitionTo(i, !this.mNoAnimationOnNextBarModeChange && this.mDeviceInteractive && i2 != 2);
    }

    /* access modifiers changed from: private */
    public void finishBarAnimations() {
        StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
        if (!(statusBarWindowView == null || statusBarWindowView.getBarTransitions() == null)) {
            this.mStatusBarWindow.getBarTransitions().finishAnimations();
        }
        this.mNavigationBarController.finishBarAnimations(this.mDisplayId);
    }

    public void setInteracting(int i, boolean z) {
        int i2;
        boolean z2 = true;
        if (((this.mInteractingWindows & i) != 0) == z) {
            z2 = false;
        }
        if (z) {
            i2 = this.mInteractingWindows | i;
        } else {
            i2 = this.mInteractingWindows & (~i);
        }
        this.mInteractingWindows = i2;
        if (this.mInteractingWindows != 0) {
            this.mAutoHideController.suspendAutoHide();
        } else {
            this.mAutoHideController.resumeSuspendedAutoHide();
        }
        if (z2 && z && i == 2) {
            this.mNavigationBarController.touchAutoDim(this.mDisplayId);
            dismissVolumeDialog();
        }
        checkBarModes();
    }

    /* access modifiers changed from: private */
    public void dismissVolumeDialog() {
        VolumeComponent volumeComponent = this.mVolumeComponent;
        if (volumeComponent != null) {
            volumeComponent.dismissNow();
        }
    }

    public boolean inFullscreenMode() {
        return (this.mSystemUiVisibility & 6) != 0;
    }

    public boolean inImmersiveMode() {
        return (this.mSystemUiVisibility & 6144) != 0;
    }

    private boolean areLightsOn() {
        return (this.mSystemUiVisibility & 1) == 0;
    }

    public void tick(StatusBarNotification statusBarNotification, boolean z, boolean z2, MediaMetadata mediaMetadata, String str) {
        if (this.mTicker != null && this.mTickerEnabled && !isKeyguardShowing() && areLightsOn() && this.mDeviceProvisionedController.isDeviceProvisioned() && isNotificationForCurrentProfiles(statusBarNotification)) {
            if (!z2) {
                if (statusBarNotification.getNotification().tickerText == null) {
                    return;
                }
            } else if (mediaMetadata == null) {
                return;
            }
            StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
            if (statusBarWindowView != null && statusBarWindowView.getWindowToken() != null && (this.mDisabled1 & 655360) == 0) {
                this.mTicker.addEntry(statusBarNotification, z2, mediaMetadata, str);
            }
        }
    }

    private class MyTicker extends Ticker {
        public View mTickerView;

        MyTicker(Context context, View view) {
            super(context, view, StatusBar.this.mTickerAnimationMode, StatusBar.this.mTickerTickDuration);
            if (!StatusBar.this.mTickerEnabled) {
                Log.w(StatusBar.TAG, "MyTicker instantiated with mTickerEnabled", new Throwable());
            }
        }

        public void setView(View view) {
            this.mTickerView = view;
        }

        public void tickerStarting() {
            Animation animation;
            Animation animation2;
            StatusBar statusBar = StatusBar.this;
            if (statusBar.mTicker != null && statusBar.mTickerEnabled) {
                boolean unused = statusBar.mTicking = true;
                if (StatusBar.this.mTickerAnimationMode == 1) {
                    animation2 = StatusBar.this.loadAnim(17432723, (Animation.AnimationListener) null);
                    animation = StatusBar.this.loadAnim(17432722, (Animation.AnimationListener) null);
                } else {
                    animation2 = StatusBar.this.loadAnim(true, (Animation.AnimationListener) null);
                    animation = StatusBar.this.loadAnim(false, (Animation.AnimationListener) null);
                }
                StatusBar.this.mStatusBarContent.setVisibility(8);
                StatusBar.this.mStatusBarContent.startAnimation(animation2);
                StatusBar.this.mCenterClockLayout.setVisibility(8);
                StatusBar.this.mCenterClockLayout.startAnimation(animation2);
                View view = this.mTickerView;
                if (view != null) {
                    view.setVisibility(0);
                    this.mTickerView.startAnimation(animation);
                }
            }
        }

        public void tickerDone() {
            Animation animation;
            Animation animation2;
            if (StatusBar.this.mTickerAnimationMode == 1) {
                StatusBar statusBar = StatusBar.this;
                animation2 = statusBar.loadAnim(17432723, statusBar.mTickingDoneListener);
                animation = StatusBar.this.loadAnim(17432722, (Animation.AnimationListener) null);
            } else {
                StatusBar statusBar2 = StatusBar.this;
                animation2 = statusBar2.loadAnim(true, statusBar2.mTickingDoneListener);
                animation = StatusBar.this.loadAnim(false, (Animation.AnimationListener) null);
            }
            StatusBar.this.mStatusBarContent.setVisibility(0);
            StatusBar.this.mStatusBarContent.startAnimation(animation);
            StatusBar.this.mCenterClockLayout.setVisibility(0);
            StatusBar.this.mCenterClockLayout.startAnimation(animation);
            View view = this.mTickerView;
            if (view != null) {
                view.setVisibility(8);
                this.mTickerView.startAnimation(animation2);
            }
        }

        public void tickerHalting() {
            if (StatusBar.this.mStatusBarContent.getVisibility() != 0) {
                StatusBar.this.mStatusBarContent.setVisibility(0);
                StatusBar statusBar = StatusBar.this;
                statusBar.mStatusBarContent.startAnimation(statusBar.loadAnim(false, (Animation.AnimationListener) null));
                StatusBar.this.mCenterClockLayout.setVisibility(0);
                StatusBar statusBar2 = StatusBar.this;
                statusBar2.mCenterClockLayout.startAnimation(statusBar2.loadAnim(false, (Animation.AnimationListener) null));
            }
            View view = this.mTickerView;
            if (view != null) {
                view.setVisibility(8);
            }
        }

        public void onDarkChanged(Rect rect, float f, int i) {
            applyDarkIntensity(rect, this.mTickerView, i);
        }
    }

    /* access modifiers changed from: private */
    public Animation loadAnim(boolean z, Animation.AnimationListener animationListener) {
        float f = 1.0f;
        float f2 = z ? 1.0f : 0.0f;
        if (z) {
            f = 0.0f;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f);
        alphaAnimation.setInterpolator(AnimationUtils.loadInterpolator(this.mContext, z ? 17563648 : 17563649));
        alphaAnimation.setDuration(350);
        if (animationListener != null) {
            alphaAnimation.setAnimationListener(animationListener);
        }
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public Animation loadAnim(int i, Animation.AnimationListener animationListener) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, i);
        if (animationListener != null) {
            loadAnimation.setAnimationListener(animationListener);
        }
        return loadAnimation;
    }

    public void haltTicker() {
        Ticker ticker = this.mTicker;
        if (ticker != null && this.mTickerEnabled) {
            ticker.halt();
        }
    }

    public static String viewInfo(View view) {
        return "[(" + view.getLeft() + "," + view.getTop() + ")(" + view.getRight() + "," + view.getBottom() + ") " + view.getWidth() + "x" + view.getHeight() + "]";
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str;
        synchronized (this.mQueueLock) {
            printWriter.println("Current Status Bar state:");
            printWriter.println("  mExpandedVisible=" + this.mExpandedVisible);
            printWriter.println("  mDisplayMetrics=" + this.mDisplayMetrics);
            printWriter.println("  mStackScroller: " + viewInfo(this.mStackScroller));
            printWriter.println("  mStackScroller: " + viewInfo(this.mStackScroller) + " scroll " + this.mStackScroller.getScrollX() + "," + this.mStackScroller.getScrollY());
            StringBuilder sb = new StringBuilder();
            sb.append("  mTickerEnabled=");
            sb.append(this.mTickerEnabled);
            printWriter.println(sb.toString());
            if (this.mTickerEnabled) {
                printWriter.println("  mTicking=" + this.mTicking);
            }
        }
        printWriter.print("  mInteractingWindows=");
        printWriter.println(this.mInteractingWindows);
        printWriter.print("  mStatusBarWindowState=");
        printWriter.println(StatusBarManager.windowStateToString(this.mStatusBarWindowState));
        printWriter.print("  mStatusBarMode=");
        printWriter.println(BarTransitions.modeToString(this.mStatusBarMode));
        printWriter.print("  mDozing=");
        printWriter.println(this.mDozing);
        printWriter.print("  mZenMode=");
        printWriter.println(Settings.Global.zenModeToString(Settings.Global.getInt(this.mContext.getContentResolver(), "zen_mode", 0)));
        printWriter.print("  mWallpaperSupported= ");
        printWriter.println(this.mWallpaperSupported);
        StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
        if (statusBarWindowView != null) {
            dumpBarTransitions(printWriter, "mStatusBarWindow", statusBarWindowView.getBarTransitions());
        }
        printWriter.println("  StatusBarWindowView: ");
        StatusBarWindowView statusBarWindowView2 = this.mStatusBarWindow;
        if (statusBarWindowView2 != null) {
            statusBarWindowView2.dump(fileDescriptor, printWriter, strArr);
        }
        printWriter.println("  mMediaManager: ");
        NotificationMediaManager notificationMediaManager = this.mMediaManager;
        if (notificationMediaManager != null) {
            notificationMediaManager.dump(fileDescriptor, printWriter, strArr);
        }
        printWriter.println("  Panels: ");
        if (this.mNotificationPanel != null) {
            printWriter.println("    mNotificationPanel=" + this.mNotificationPanel + " params=" + this.mNotificationPanel.getLayoutParams().debug(""));
            printWriter.print("      ");
            this.mNotificationPanel.dump(fileDescriptor, printWriter, strArr);
        }
        printWriter.println("  mStackScroller: ");
        if (this.mStackScroller instanceof Dumpable) {
            printWriter.print("      ");
            ((Dumpable) this.mStackScroller).dump(fileDescriptor, printWriter, strArr);
        }
        printWriter.println("  Theme:");
        if (this.mUiModeManager == null) {
            str = "null";
        } else {
            str = this.mUiModeManager.getNightMode() + "";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("    dark theme: ");
        sb2.append(str);
        sb2.append(" (auto: ");
        sb2.append(0);
        sb2.append(", yes: ");
        sb2.append(2);
        sb2.append(", no: ");
        boolean z = true;
        sb2.append(1);
        sb2.append(")");
        printWriter.println(sb2.toString());
        if (this.mContext.getThemeResId() != C1785R$style.Theme_SystemUI_Light) {
            z = false;
        }
        printWriter.println("    light wallpaper theme: " + z);
        DozeLog.dump(printWriter);
        BiometricUnlockController biometricUnlockController = this.mBiometricUnlockController;
        if (biometricUnlockController != null) {
            biometricUnlockController.dump(printWriter);
        }
        KeyguardIndicationController keyguardIndicationController = this.mKeyguardIndicationController;
        if (keyguardIndicationController != null) {
            keyguardIndicationController.dump(fileDescriptor, printWriter, strArr);
        }
        ScrimController scrimController = this.mScrimController;
        if (scrimController != null) {
            scrimController.dump(fileDescriptor, printWriter, strArr);
        }
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
        if (statusBarKeyguardViewManager != null) {
            statusBarKeyguardViewManager.dump(printWriter);
        }
        synchronized (this.mEntryManager.getNotificationData()) {
            this.mEntryManager.getNotificationData().dump(printWriter, "  ");
        }
        HeadsUpManagerPhone headsUpManagerPhone = this.mHeadsUpManager;
        if (headsUpManagerPhone != null) {
            headsUpManagerPhone.dump(fileDescriptor, printWriter, strArr);
        } else {
            printWriter.println("  mHeadsUpManager: null");
        }
        NotificationGroupManager notificationGroupManager = this.mGroupManager;
        if (notificationGroupManager != null) {
            notificationGroupManager.dump(fileDescriptor, printWriter, strArr);
        } else {
            printWriter.println("  mGroupManager: null");
        }
        BubbleController bubbleController = this.mBubbleController;
        if (bubbleController != null) {
            bubbleController.dump(fileDescriptor, printWriter, strArr);
        }
        LightBarController lightBarController = this.mLightBarController;
        if (lightBarController != null) {
            lightBarController.dump(fileDescriptor, printWriter, strArr);
        }
        UnlockMethodCache unlockMethodCache = this.mUnlockMethodCache;
        if (unlockMethodCache != null) {
            unlockMethodCache.dump(printWriter);
        }
        KeyguardBypassController keyguardBypassController = this.mKeyguardBypassController;
        if (keyguardBypassController != null) {
            keyguardBypassController.dump(printWriter);
        }
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        if (keyguardUpdateMonitor != null) {
            keyguardUpdateMonitor.dump(fileDescriptor, printWriter, strArr);
        }
        ((FalsingManager) Dependency.get(FalsingManager.class)).dump(printWriter);
        FalsingLog.dump(printWriter);
        printWriter.println("SharedPreferences:");
        for (Map.Entry next : Prefs.getAll(this.mContext).entrySet()) {
            printWriter.print("  ");
            printWriter.print((String) next.getKey());
            printWriter.print("=");
            printWriter.println(next.getValue());
        }
    }

    private /* synthetic */ void lambda$dump$16() {
        this.mStatusBarView.getLocationOnScreen(this.mAbsPos);
        Log.d(TAG, "mStatusBarView: ----- (" + this.mAbsPos[0] + "," + this.mAbsPos[1] + ") " + this.mStatusBarView.getWidth() + "x" + getStatusBarHeight());
        this.mStatusBarView.debug();
    }

    static void dumpBarTransitions(PrintWriter printWriter, String str, BarTransitions barTransitions) {
        printWriter.print("  ");
        printWriter.print(str);
        printWriter.print(".BarTransitions.mMode=");
        printWriter.println(BarTransitions.modeToString(barTransitions.getMode()));
    }

    public void createAndAddWindows(RegisterStatusBarResult registerStatusBarResult) {
        makeStatusBarView(registerStatusBarResult);
        this.mStatusBarWindowController = (StatusBarWindowController) Dependency.get(StatusBarWindowController.class);
        this.mStatusBarWindowController.add(this.mStatusBarWindow, getStatusBarHeight());
    }

    /* access modifiers changed from: package-private */
    public void updateDisplaySize() {
        this.mDisplay.getMetrics(this.mDisplayMetrics);
        this.mDisplay.getSize(this.mCurrentDisplaySize);
    }

    /* access modifiers changed from: package-private */
    public float getDisplayDensity() {
        return this.mDisplayMetrics.density;
    }

    /* access modifiers changed from: package-private */
    public float getDisplayWidth() {
        return (float) this.mDisplayMetrics.widthPixels;
    }

    /* access modifiers changed from: package-private */
    public float getDisplayHeight() {
        return (float) this.mDisplayMetrics.heightPixels;
    }

    /* access modifiers changed from: package-private */
    public int getRotation() {
        return this.mDisplay.getRotation();
    }

    public void startActivityDismissingKeyguard(Intent intent, boolean z, boolean z2, int i) {
        startActivityDismissingKeyguard(intent, z, z2, false, (ActivityStarter.Callback) null, i);
    }

    public void startActivityDismissingKeyguard(Intent intent, boolean z, boolean z2) {
        startActivityDismissingKeyguard(intent, z, z2, 0);
    }

    public void startActivityDismissingKeyguard(Intent intent, boolean z, boolean z2, boolean z3, ActivityStarter.Callback callback, int i) {
        if (!z || this.mDeviceProvisionedController.isDeviceProvisioned()) {
            boolean wouldLaunchResolverActivity = this.mActivityIntentHelper.wouldLaunchResolverActivity(intent, this.mLockscreenUserManager.getCurrentUserId());
            executeRunnableDismissingKeyguard(new Runnable(intent, i, z3, callback) {
                private final /* synthetic */ Intent f$1;
                private final /* synthetic */ int f$2;
                private final /* synthetic */ boolean f$3;
                private final /* synthetic */ ActivityStarter.Callback f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                public final void run() {
                    StatusBar.this.lambda$startActivityDismissingKeyguard$17$StatusBar(this.f$1, this.f$2, this.f$3, this.f$4);
                }
            }, new Runnable() {
                public final void run() {
                    StatusBar.lambda$startActivityDismissingKeyguard$18(ActivityStarter.Callback.this);
                }
            }, z2, wouldLaunchResolverActivity, true);
        }
    }

    public /* synthetic */ void lambda$startActivityDismissingKeyguard$17$StatusBar(Intent intent, int i, boolean z, ActivityStarter.Callback callback) {
        int i2;
        Intent intent2 = intent;
        ActivityStarter.Callback callback2 = callback;
        this.mAssistManager.hideAssist();
        intent2.setFlags(335544320);
        intent.addFlags(i);
        ActivityOptions activityOptions = new ActivityOptions(getActivityOptions((RemoteAnimationAdapter) null));
        activityOptions.setDisallowEnterPictureInPictureWhileLaunching(z);
        if (intent2 == KeyguardBottomAreaView.INSECURE_CAMERA_INTENT) {
            activityOptions.setRotationAnimationHint(3);
        }
        if (intent.getAction() == "android.settings.panel.action.VOLUME") {
            activityOptions.setDisallowEnterPictureInPictureWhileLaunching(true);
        }
        try {
            i2 = ActivityTaskManager.getService().startActivityAsUser((IApplicationThread) null, this.mContext.getBasePackageName(), intent, intent2.resolveTypeIfNeeded(this.mContext.getContentResolver()), (IBinder) null, (String) null, 0, 268435456, (ProfilerInfo) null, activityOptions.toBundle(), UserHandle.CURRENT.getIdentifier());
        } catch (RemoteException e) {
            Log.w(TAG, "Unable to start activity", e);
            i2 = -96;
        }
        if (callback2 != null) {
            callback2.onActivityStarted(i2);
        }
    }

    static /* synthetic */ void lambda$startActivityDismissingKeyguard$18(ActivityStarter.Callback callback) {
        if (callback != null) {
            callback.onActivityStarted(-96);
        }
    }

    public void readyForKeyguardDone() {
        this.mStatusBarKeyguardViewManager.readyForKeyguardDone();
    }

    public void executeRunnableDismissingKeyguard(Runnable runnable, Runnable runnable2, boolean z, boolean z2, boolean z3) {
        dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction(runnable, z, z3) {
            private final /* synthetic */ Runnable f$1;
            private final /* synthetic */ boolean f$2;
            private final /* synthetic */ boolean f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final boolean onDismiss() {
                return StatusBar.this.lambda$executeRunnableDismissingKeyguard$19$StatusBar(this.f$1, this.f$2, this.f$3);
            }
        }, runnable2, z2);
    }

    public /* synthetic */ boolean lambda$executeRunnableDismissingKeyguard$19$StatusBar(Runnable runnable, boolean z, boolean z2) {
        if (runnable != null) {
            if (!this.mStatusBarKeyguardViewManager.isShowing() || !this.mStatusBarKeyguardViewManager.isOccluded()) {
                AsyncTask.execute(runnable);
            } else {
                this.mStatusBarKeyguardViewManager.addAfterKeyguardGoneRunnable(runnable);
            }
        }
        if (z) {
            if (!this.mExpandedVisible || this.mBouncerShowing) {
                this.mHandler.post(new Runnable() {
                    public final void run() {
                        StatusBar.this.runPostCollapseRunnables();
                    }
                });
            } else {
                animateCollapsePanels(2, true, true);
            }
        } else if (isInLaunchTransition() && this.mNotificationPanel.isLaunchTransitionFinished()) {
            C1479H h = this.mHandler;
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
            Objects.requireNonNull(statusBarKeyguardViewManager);
            h.post(new Runnable() {
                public final void run() {
                    StatusBarKeyguardViewManager.this.readyForKeyguardDone();
                }
            });
        }
        return z2;
    }

    public void resetUserExpandedStates() {
        ArrayList<NotificationEntry> activeNotifications = this.mEntryManager.getNotificationData().getActiveNotifications();
        int size = activeNotifications.size();
        for (int i = 0; i < size; i++) {
            activeNotifications.get(i).resetUserExpansion();
        }
    }

    /* access modifiers changed from: private */
    public void executeWhenUnlocked(ActivityStarter.OnDismissAction onDismissAction, boolean z) {
        if (this.mStatusBarKeyguardViewManager.isShowing() && z) {
            this.mStatusBarStateController.setLeaveOpenOnKeyguardHide(true);
        }
        dismissKeyguardThenExecute(onDismissAction, (Runnable) null, false);
    }

    /* access modifiers changed from: protected */
    public void dismissKeyguardThenExecute(ActivityStarter.OnDismissAction onDismissAction, boolean z) {
        dismissKeyguardThenExecute(onDismissAction, (Runnable) null, z);
    }

    public void dismissKeyguardThenExecute(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z) {
        if (this.mWakefulnessLifecycle.getWakefulness() == 0 && this.mUnlockMethodCache.canSkipBouncer() && !this.mStatusBarStateController.leaveOpenOnKeyguardHide() && isPulsing()) {
            this.mBiometricUnlockController.startWakeAndUnlock(2);
        }
        if (this.mStatusBarKeyguardViewManager.isShowing()) {
            this.mStatusBarKeyguardViewManager.dismissWithAction(onDismissAction, runnable, z);
        } else {
            onDismissAction.onDismiss();
        }
    }

    public void onConfigChanged(Configuration configuration) {
        updateResources();
        updateDisplaySize();
        this.mPortrait = RotationUtils.getExactRotation(this.mContext) == 0;
        this.mViewHierarchyManager.updateRowStates();
        this.mScreenPinningRequest.onConfigurationChanged();
        if (this.mImmerseMode == 1) {
            this.mUiOffloadThread.submit(new Runnable() {
                public final void run() {
                    StatusBar.this.lambda$onConfigChanged$20$StatusBar();
                }
            });
        }
        if (this.blurperformed) {
            this.mNotificationPanel.setPanelAlpha(0, false);
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    StatusBar.this.drawBlurView();
                    StatusBar.this.mNotificationPanel.setPanelAlphaFast(255, true);
                }
            }, (long) Math.max(390, Math.round(Settings.Global.getFloat(this.mContext.getContentResolver(), "transition_animation_scale", 1.0f) * 455.0f)));
        }
    }

    public /* synthetic */ void lambda$onConfigChanged$20$StatusBar() {
        setBlackStatusBar(this.mPortrait);
    }

    public void setLockscreenUser(int i) {
        LockscreenWallpaper lockscreenWallpaper = this.mLockscreenWallpaper;
        if (lockscreenWallpaper != null) {
            lockscreenWallpaper.setCurrentUser(i);
        }
        this.mScrimController.setCurrentUser(i);
        if (this.mWallpaperSupported) {
            this.mWallpaperChangedReceiver.onReceive(this.mContext, (Intent) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateResources() {
        QSPanel qSPanel = this.mQSPanel;
        if (qSPanel != null) {
            qSPanel.updateResources();
        }
        QuickQSPanel quickQSPanel = this.mQuickQSPanel;
        if (quickQSPanel != null) {
            quickQSPanel.updateResources();
        }
        loadDimens();
        PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
        if (phoneStatusBarView != null) {
            phoneStatusBarView.updateResources();
        }
        NotificationPanelView notificationPanelView = this.mNotificationPanel;
        if (notificationPanelView != null) {
            notificationPanelView.updateResources();
        }
        BrightnessMirrorController brightnessMirrorController = this.mBrightnessMirrorController;
        if (brightnessMirrorController != null) {
            brightnessMirrorController.updateResources();
        }
    }

    /* access modifiers changed from: protected */
    public void loadDimens() {
        int i;
        Resources resources = this.mContext.getResources();
        int i2 = this.mNaturalBarHeight;
        this.mNaturalBarHeight = resources.getDimensionPixelSize(17105434);
        StatusBarWindowController statusBarWindowController = this.mStatusBarWindowController;
        if (!(statusBarWindowController == null || (i = this.mNaturalBarHeight) == i2)) {
            statusBarWindowController.setBarHeight(i);
        }
        this.mQuickQsTotalHeight = resources.getDimensionPixelSize(17105396);
    }

    /* access modifiers changed from: protected */
    public void handleVisibleToUserChanged(boolean z) {
        if (z) {
            handleVisibleToUserChangedImpl(z);
            this.mNotificationLogger.startNotificationLogging();
            return;
        }
        this.mNotificationLogger.stopNotificationLogging();
        handleVisibleToUserChangedImpl(z);
    }

    /* access modifiers changed from: package-private */
    public void handlePeekToExpandTransistion() {
        try {
            this.mBarService.onPanelRevealed(false, this.mEntryManager.getNotificationData().getActiveNotifications().size());
        } catch (RemoteException unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0011, code lost:
        r0 = r3.mState;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleVisibleToUserChangedImpl(boolean r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0040
            com.android.systemui.statusbar.phone.HeadsUpManagerPhone r4 = r3.mHeadsUpManager
            boolean r4 = r4.hasPinnedHeadsUp()
            com.android.systemui.statusbar.phone.StatusBarNotificationPresenter r0 = r3.mPresenter
            boolean r0 = r0.isPresenterFullyCollapsed()
            r1 = 1
            if (r0 != 0) goto L_0x001a
            int r0 = r3.mState
            if (r0 == 0) goto L_0x0018
            r2 = 2
            if (r0 != r2) goto L_0x001a
        L_0x0018:
            r0 = r1
            goto L_0x001b
        L_0x001a:
            r0 = 0
        L_0x001b:
            com.android.systemui.statusbar.notification.NotificationEntryManager r2 = r3.mEntryManager
            com.android.systemui.statusbar.notification.collection.NotificationData r2 = r2.getNotificationData()
            java.util.ArrayList r2 = r2.getActiveNotifications()
            int r2 = r2.size()
            if (r4 == 0) goto L_0x0034
            com.android.systemui.statusbar.phone.StatusBarNotificationPresenter r4 = r3.mPresenter
            boolean r4 = r4.isPresenterFullyCollapsed()
            if (r4 == 0) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r1 = r2
        L_0x0035:
            com.android.systemui.UiOffloadThread r4 = r3.mUiOffloadThread
            com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$k9wpEujtdQqpZlZhqB1EZ8se9mY r2 = new com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$k9wpEujtdQqpZlZhqB1EZ8se9mY
            r2.<init>(r0, r1)
            r4.submit(r2)
            goto L_0x004a
        L_0x0040:
            com.android.systemui.UiOffloadThread r4 = r3.mUiOffloadThread
            com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$fcayWp-97tKa-NUcU5-zrt48v_0 r0 = new com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$fcayWp-97tKa-NUcU5-zrt48v_0
            r0.<init>()
            r4.submit(r0)
        L_0x004a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBar.handleVisibleToUserChangedImpl(boolean):void");
    }

    public /* synthetic */ void lambda$handleVisibleToUserChangedImpl$21$StatusBar(boolean z, int i) {
        try {
            this.mBarService.onPanelRevealed(z, i);
        } catch (RemoteException unused) {
        }
    }

    public /* synthetic */ void lambda$handleVisibleToUserChangedImpl$22$StatusBar() {
        try {
            this.mBarService.onPanelHidden();
        } catch (RemoteException unused) {
        }
    }

    private void logStateToEventlog() {
        boolean isShowing = this.mStatusBarKeyguardViewManager.isShowing();
        boolean isOccluded = this.mStatusBarKeyguardViewManager.isOccluded();
        boolean isBouncerShowing = this.mStatusBarKeyguardViewManager.isBouncerShowing();
        boolean isMethodSecure = this.mUnlockMethodCache.isMethodSecure();
        boolean canSkipBouncer = this.mUnlockMethodCache.canSkipBouncer();
        int loggingFingerprint = getLoggingFingerprint(this.mState, isShowing, isOccluded, isBouncerShowing, isMethodSecure, canSkipBouncer);
        if (loggingFingerprint != this.mLastLoggedStateFingerprint) {
            if (this.mStatusBarStateLog == null) {
                this.mStatusBarStateLog = new LogMaker(0);
            }
            this.mMetricsLogger.write(this.mStatusBarStateLog.setCategory(isBouncerShowing ? 197 : 196).setType(isShowing ? 1 : 2).setSubtype(isMethodSecure ? 1 : 0));
            EventLogTags.writeSysuiStatusBarState(this.mState, isShowing ? 1 : 0, isOccluded ? 1 : 0, isBouncerShowing ? 1 : 0, isMethodSecure ? 1 : 0, canSkipBouncer ? 1 : 0);
            this.mLastLoggedStateFingerprint = loggingFingerprint;
        }
    }

    /* access modifiers changed from: package-private */
    public void postStartTracing() {
        this.mHandler.postDelayed(this.mStartTracing, 3000);
    }

    /* access modifiers changed from: package-private */
    public void vibrate() {
        ((Vibrator) this.mContext.getSystemService("vibrator")).vibrate(250, VIBRATION_ATTRIBUTES);
    }

    public /* synthetic */ void lambda$new$23$StatusBar() {
        Debug.stopMethodTracing();
        Log.d(TAG, "stopTracing");
        vibrate();
    }

    public void postQSRunnableDismissingKeyguard(Runnable runnable) {
        this.mHandler.post(new Runnable(runnable) {
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StatusBar.this.lambda$postQSRunnableDismissingKeyguard$25$StatusBar(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$postQSRunnableDismissingKeyguard$25$StatusBar(Runnable runnable) {
        this.mStatusBarStateController.setLeaveOpenOnKeyguardHide(true);
        executeRunnableDismissingKeyguard(new Runnable(runnable) {
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StatusBar.this.lambda$postQSRunnableDismissingKeyguard$24$StatusBar(this.f$1);
            }
        }, (Runnable) null, false, false, false);
    }

    public /* synthetic */ void lambda$postQSRunnableDismissingKeyguard$24$StatusBar(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    public void postStartActivityDismissingKeyguard(PendingIntent pendingIntent) {
        this.mHandler.post(new Runnable(pendingIntent) {
            private final /* synthetic */ PendingIntent f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StatusBar.this.lambda$postStartActivityDismissingKeyguard$26$StatusBar(this.f$1);
            }
        });
    }

    public void postStartActivityDismissingKeyguard(Intent intent, int i) {
        this.mHandler.postDelayed(new Runnable(intent) {
            private final /* synthetic */ Intent f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StatusBar.this.lambda$postStartActivityDismissingKeyguard$27$StatusBar(this.f$1);
            }
        }, (long) i);
    }

    public /* synthetic */ void lambda$postStartActivityDismissingKeyguard$27$StatusBar(Intent intent) {
        handleStartActivityDismissingKeyguard(intent, true);
    }

    private void handleStartActivityDismissingKeyguard(Intent intent, boolean z) {
        startActivityDismissingKeyguard(intent, z, true);
    }

    public void dispatchDemoCommand(String str, Bundle bundle) {
        View view;
        VolumeComponent volumeComponent;
        int i = 0;
        if (!this.mDemoModeAllowed) {
            this.mDemoModeAllowed = Settings.Global.getInt(this.mContext.getContentResolver(), "sysui_demo_allowed", 0) != 0;
        }
        if (this.mDemoModeAllowed) {
            if (str.equals("enter")) {
                this.mDemoMode = true;
            } else if (str.equals("exit")) {
                this.mDemoMode = false;
                checkBarModes();
            } else if (!this.mDemoMode) {
                dispatchDemoCommand("enter", new Bundle());
            }
            boolean z = str.equals("enter") || str.equals("exit");
            if ((z || str.equals("volume")) && (volumeComponent = this.mVolumeComponent) != null) {
                volumeComponent.dispatchDemoCommand(str, bundle);
            }
            if (z || str.equals("clock")) {
                dispatchDemoCommandToView(str, bundle, C1777R$id.clock);
            }
            if (z || str.equals("battery")) {
                this.mBatteryController.dispatchDemoCommand(str, bundle);
            }
            if (z || str.equals("status")) {
                ((StatusBarIconControllerImpl) this.mIconController).dispatchDemoCommand(str, bundle);
            }
            if (this.mNetworkController != null && (z || str.equals("network"))) {
                this.mNetworkController.dispatchDemoCommand(str, bundle);
            }
            if (z || str.equals("notifications")) {
                PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
                if (phoneStatusBarView == null) {
                    view = null;
                } else {
                    view = phoneStatusBarView.findViewById(C1777R$id.notification_icon_area);
                }
                if (view != null) {
                    view.setVisibility((!this.mDemoMode || !"false".equals(bundle.getString("visible"))) ? 0 : 4);
                }
            }
            if (str.equals("bars")) {
                String string = bundle.getString("mode");
                if (!"opaque".equals(string)) {
                    if ("translucent".equals(string)) {
                        i = 2;
                    } else if ("semi-transparent".equals(string)) {
                        i = 1;
                    } else if ("transparent".equals(string)) {
                        i = 4;
                    } else {
                        i = "warning".equals(string) ? 5 : -1;
                    }
                }
                if (i != -1) {
                    StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
                    if (!(statusBarWindowView == null || statusBarWindowView.getBarTransitions() == null)) {
                        this.mStatusBarWindow.getBarTransitions().transitionTo(i, true);
                    }
                    this.mNavigationBarController.transitionTo(this.mDisplayId, i, true);
                }
            }
            if (z || str.equals("operator")) {
                dispatchDemoCommandToView(str, bundle, C1777R$id.operator_name);
            }
        }
    }

    private void dispatchDemoCommandToView(String str, Bundle bundle, int i) {
        PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
        if (phoneStatusBarView != null) {
            View findViewById = phoneStatusBarView.findViewById(i);
            if (findViewById instanceof DemoMode) {
                ((DemoMode) findViewById).dispatchDemoCommand(str, bundle);
            }
        }
    }

    public void showKeyguard() {
        this.mStatusBarStateController.setKeyguardRequested(true);
        this.mStatusBarStateController.setLeaveOpenOnKeyguardHide(false);
        this.mPendingRemoteInputView = null;
        updateIsKeyguard();
        AssistManager assistManager = this.mAssistManager;
        if (assistManager != null) {
            assistManager.onLockscreenShown();
        }
    }

    public boolean hideKeyguard() {
        this.mStatusBarStateController.setKeyguardRequested(false);
        return updateIsKeyguard();
    }

    public boolean isFullScreenUserSwitcherState() {
        return this.mState == 3;
    }

    private boolean isAutomotive() {
        Context context = this.mContext;
        return context != null && context.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }

    /* access modifiers changed from: private */
    public boolean updateIsKeyguard() {
        updateBlurVisibility();
        boolean z = true;
        boolean z2 = this.mBiometricUnlockController.getMode() == 1;
        if (this.mScreenLifecycle == null && isAutomotive()) {
            Log.w(TAG, "updateIsKeyguard(): mScreenLifeCycle not set yet");
            this.mScreenLifecycle = (ScreenLifecycle) Dependency.get(ScreenLifecycle.class);
        }
        boolean z3 = this.mDozingRequested && (!this.mDeviceInteractive || (isGoingToSleep() && (isScreenFullyOff() || this.mIsKeyguard)));
        if ((!this.mStatusBarStateController.isKeyguardRequested() && !z3) || z2) {
            z = false;
        }
        if (z3) {
            updatePanelExpansionForKeyguard();
        }
        if (!z) {
            return hideKeyguardImpl();
        }
        if (!isGoingToSleep() || this.mScreenLifecycle.getScreenState() != 3) {
            showKeyguardImpl();
        }
        return false;
    }

    public void showKeyguardImpl() {
        this.mIsKeyguard = true;
        KeyguardMonitor keyguardMonitor = this.mKeyguardMonitor;
        if (keyguardMonitor != null && keyguardMonitor.isLaunchTransitionFadingAway()) {
            this.mNotificationPanel.animate().cancel();
            onLaunchTransitionFadingEnded();
        }
        this.mHandler.removeMessages(MSG_LAUNCH_TRANSITION_TIMEOUT);
        UserSwitcherController userSwitcherController = this.mUserSwitcherController;
        if (userSwitcherController != null && userSwitcherController.useFullscreenUserSwitcher()) {
            this.mStatusBarStateController.setState(3);
        } else if (!this.mPulseExpansionHandler.isWakingToShadeLocked()) {
            this.mStatusBarStateController.setState(1);
        }
        updatePanelExpansionForKeyguard();
        NotificationEntry notificationEntry = this.mDraggedDownEntry;
        if (notificationEntry != null) {
            notificationEntry.setUserLocked(false);
            this.mDraggedDownEntry.notifyHeightChanged(false);
            this.mDraggedDownEntry = null;
        }
    }

    private void updatePanelExpansionForKeyguard() {
        if (this.mState == 1 && this.mBiometricUnlockController.getMode() != 1 && !this.mBouncerShowing) {
            instantExpandNotificationsPanel();
        } else if (this.mState == 3) {
            instantCollapseNotificationPanel();
        }
    }

    /* access modifiers changed from: private */
    public void onLaunchTransitionFadingEnded() {
        this.mNotificationPanel.setAlpha(1.0f);
        this.mNotificationPanel.onAffordanceLaunchEnded();
        releaseGestureWakeLock();
        runLaunchTransitionEndRunnable();
        this.mKeyguardMonitor.setLaunchTransitionFadingAway(false);
        this.mPresenter.updateMediaMetaData(true, true);
    }

    public void addPostCollapseAction(Runnable runnable) {
        this.mPostCollapseRunnables.add(runnable);
    }

    public boolean isInLaunchTransition() {
        return this.mNotificationPanel.isLaunchTransitionRunning() || this.mNotificationPanel.isLaunchTransitionFinished();
    }

    public void fadeKeyguardAfterLaunchTransition(Runnable runnable, Runnable runnable2) {
        this.mHandler.removeMessages(MSG_LAUNCH_TRANSITION_TIMEOUT);
        this.mLaunchTransitionEndRunnable = runnable2;
        $$Lambda$StatusBar$d3HojG2YDY2BSrPSc9Bikt2cSqQ r4 = new Runnable(runnable) {
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StatusBar.this.lambda$fadeKeyguardAfterLaunchTransition$28$StatusBar(this.f$1);
            }
        };
        if (this.mNotificationPanel.isLaunchTransitionRunning()) {
            this.mNotificationPanel.setLaunchTransitionEndRunnable(r4);
        } else {
            r4.run();
        }
    }

    public /* synthetic */ void lambda$fadeKeyguardAfterLaunchTransition$28$StatusBar(Runnable runnable) {
        this.mKeyguardMonitor.setLaunchTransitionFadingAway(true);
        if (runnable != null) {
            runnable.run();
        }
        updateScrimController();
        this.mPresenter.updateMediaMetaData(false, true);
        this.mNotificationPanel.setAlpha(1.0f);
        this.mNotificationPanel.animate().alpha(0.0f).setStartDelay(100).setDuration(300).withLayer().withEndAction(new Runnable() {
            public final void run() {
                StatusBar.this.onLaunchTransitionFadingEnded();
            }
        });
        this.mCommandQueue.appTransitionStarting(this.mDisplayId, SystemClock.uptimeMillis(), 120, true);
    }

    public void fadeKeyguardWhilePulsing() {
        this.mNotificationPanel.animate().alpha(0.0f).setStartDelay(0).setDuration(96).setInterpolator(Interpolators.ALPHA_OUT).withEndAction(new Runnable() {
            public final void run() {
                StatusBar.this.lambda$fadeKeyguardWhilePulsing$29$StatusBar();
            }
        }).start();
    }

    public /* synthetic */ void lambda$fadeKeyguardWhilePulsing$29$StatusBar() {
        hideKeyguard();
        this.mStatusBarKeyguardViewManager.onKeyguardFadedAway();
    }

    public void animateKeyguardUnoccluding() {
        this.mNotificationPanel.setExpandedFraction(0.0f);
        animateExpandNotificationsPanel();
    }

    public void startLaunchTransitionTimeout() {
        this.mHandler.sendEmptyMessageDelayed(MSG_LAUNCH_TRANSITION_TIMEOUT, LAUNCH_TRANSITION_TIMEOUT_MS);
    }

    /* access modifiers changed from: private */
    public void onLaunchTransitionTimeout() {
        Log.w(TAG, "Launch transition: Timeout!");
        this.mNotificationPanel.onAffordanceLaunchEnded();
        releaseGestureWakeLock();
        this.mNotificationPanel.resetViews(false);
    }

    private void runLaunchTransitionEndRunnable() {
        Runnable runnable = this.mLaunchTransitionEndRunnable;
        if (runnable != null) {
            this.mLaunchTransitionEndRunnable = null;
            runnable.run();
        }
    }

    public boolean hideKeyguardImpl() {
        this.mIsKeyguard = false;
        Trace.beginSection("StatusBar#hideKeyguard");
        boolean leaveOpenOnKeyguardHide = this.mStatusBarStateController.leaveOpenOnKeyguardHide();
        if (!this.mStatusBarStateController.setState(0)) {
            this.mLockscreenUserManager.updatePublicMode();
        }
        if (this.mStatusBarStateController.leaveOpenOnKeyguardHide()) {
            if (!this.mStatusBarStateController.isKeyguardRequested()) {
                this.mStatusBarStateController.setLeaveOpenOnKeyguardHide(false);
            }
            long calculateGoingToFullShadeDelay = this.mKeyguardMonitor.calculateGoingToFullShadeDelay();
            this.mNotificationPanel.animateToFullShade(calculateGoingToFullShadeDelay);
            NotificationEntry notificationEntry = this.mDraggedDownEntry;
            if (notificationEntry != null) {
                notificationEntry.setUserLocked(false);
                this.mDraggedDownEntry = null;
            }
            this.mNavigationBarController.disableAnimationsDuringHide(this.mDisplayId, calculateGoingToFullShadeDelay);
        } else if (!this.mNotificationPanel.isCollapsing()) {
            instantCollapseNotificationPanel();
        }
        QSPanel qSPanel = this.mQSPanel;
        if (qSPanel != null) {
            qSPanel.refreshAllTiles();
        }
        this.mHandler.removeMessages(MSG_LAUNCH_TRANSITION_TIMEOUT);
        releaseGestureWakeLock();
        this.mNotificationPanel.onAffordanceLaunchEnded();
        this.mNotificationPanel.animate().cancel();
        this.mNotificationPanel.setAlpha(1.0f);
        ViewGroupFadeHelper.reset(this.mNotificationPanel);
        updateScrimController();
        Trace.endSection();
        return leaveOpenOnKeyguardHide;
    }

    /* access modifiers changed from: private */
    public void releaseGestureWakeLock() {
        if (this.mGestureWakeLock.isHeld()) {
            this.mGestureWakeLock.release();
        }
    }

    public void keyguardGoingAway() {
        this.mKeyguardIndicationController.setVisible(false);
        this.mKeyguardMonitor.notifyKeyguardGoingAway(true);
        this.mCommandQueue.appTransitionPending(this.mDisplayId, true);
        ((PulseController) Dependency.get(PulseController.class)).notifyKeyguardGoingAway();
    }

    public void setKeyguardFadingAway(long j, long j2, long j3, boolean z) {
        this.mCommandQueue.appTransitionStarting(this.mDisplayId, (j + j3) - 120, 120, true);
        this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, j3 > 0);
        this.mCommandQueue.appTransitionStarting(this.mDisplayId, j - 120, 120, true);
        this.mKeyguardMonitor.notifyKeyguardFadingAway(j2, j3, z);
    }

    public void finishKeyguardFadingAway() {
        this.mKeyguardMonitor.notifyKeyguardDoneFading();
        this.mScrimController.setExpansionAffectsAlpha(true);
    }

    public boolean isCurrentRoundedSameAsFw() {
        float f = Resources.getSystem().getDisplayMetrics().density;
        int dimension = (int) (((float) ((int) this.mContext.getResources().getDimension(17105408))) / f);
        int dimension2 = (int) (((float) ((int) this.mContext.getResources().getDimension(C1775R$dimen.rounded_corner_content_padding))) / f);
        return dimension == Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "sysui_rounded_size", dimension, -2) && dimension2 == Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "sysui_rounded_content_padding", dimension2, -2);
    }

    /* access modifiers changed from: protected */
    public void updateTheme() {
        haltTicker();
        int i = this.mColorExtractor.getNeutralColors().supportsDarkText() ? C1785R$style.Theme_SystemUI_Light : C1785R$style.Theme_SystemUI;
        if (this.mContext.getThemeResId() != i) {
            this.mContext.setTheme(i);
            ((ConfigurationController) Dependency.get(ConfigurationController.class)).notifyThemeChanged();
        }
        updateCorners();
    }

    /* access modifiers changed from: private */
    public void updateCorners() {
        boolean z = true;
        if (Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "sysui_rounded_fwvals", 1, -2) != 1) {
            z = false;
        }
        this.mSysuiRoundedFwvals = z;
        if (this.mSysuiRoundedFwvals && !isCurrentRoundedSameAsFw()) {
            float f = Resources.getSystem().getDisplayMetrics().density;
            Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "sysui_rounded_size", (int) (((float) ((int) this.mContext.getResources().getDimension(17105408))) / f), -2);
            Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "sysui_rounded_content_padding", (int) (((float) ((int) this.mContext.getResources().getDimension(C1775R$dimen.rounded_corner_content_padding))) / f), -2);
        }
    }

    private void updateDozingState() {
        Trace.traceCounter(4096, "dozing", this.mDozing ? 1 : 0);
        Trace.beginSection("StatusBar#updateDozingState");
        boolean isGoingToSleepVisibleNotOccluded = this.mStatusBarKeyguardViewManager.isGoingToSleepVisibleNotOccluded();
        boolean z = false;
        boolean z2 = this.mBiometricUnlockController.getMode() == 1;
        if ((!this.mDozing && this.mDozeServiceHost.shouldAnimateWakeup() && !z2) || (this.mDozing && this.mDozeServiceHost.shouldAnimateScreenOff() && isGoingToSleepVisibleNotOccluded)) {
            z = true;
        }
        this.mNotificationPanel.setDozing(this.mDozing, z, this.mWakeUpTouchLocation);
        ((PulseController) Dependency.get(PulseController.class)).setDozing(this.mDozing);
        updateQsExpansionEnabled();
        Trace.endSection();
    }

    public void userActivity() {
        if (this.mState == 1) {
            this.mKeyguardViewMediatorCallback.userActivity();
        }
    }

    public boolean interceptMediaKey(KeyEvent keyEvent) {
        if (this.mState != 1 || !this.mStatusBarKeyguardViewManager.interceptMediaKey(keyEvent)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUnlockOnMenuPressed() {
        return this.mDeviceInteractive && this.mState != 0 && this.mStatusBarKeyguardViewManager.shouldDismissOnMenuPressed();
    }

    public boolean onMenuPressed() {
        if (!shouldUnlockOnMenuPressed()) {
            return false;
        }
        animateCollapsePanels(2, true);
        return true;
    }

    public void endAffordanceLaunch() {
        releaseGestureWakeLock();
        this.mNotificationPanel.onAffordanceLaunchEnded();
    }

    public boolean onBackPressed() {
        boolean z = this.mScrimController.getState() == ScrimState.BOUNCER_SCRIMMED;
        if (this.mStatusBarKeyguardViewManager.onBackPressed(z)) {
            if (!z) {
                this.mNotificationPanel.expandWithoutQs();
            }
            return true;
        } else if (this.mNotificationPanel.isQsExpanded()) {
            if (this.mNotificationPanel.isQsDetailShowing()) {
                this.mNotificationPanel.closeQsDetail();
            } else {
                this.mNotificationPanel.animateCloseQs(false);
            }
            return true;
        } else {
            int i = this.mState;
            if (i == 1 || i == 2) {
                KeyguardUserSwitcher keyguardUserSwitcher = this.mKeyguardUserSwitcher;
                return keyguardUserSwitcher != null && keyguardUserSwitcher.hideIfNotSimple(true);
            }
            if (this.mNotificationPanel.canPanelBeCollapsed()) {
                animateCollapsePanels();
            } else {
                BubbleController bubbleController = this.mBubbleController;
                if (bubbleController != null) {
                    bubbleController.performBackPressIfNeeded();
                }
            }
            return true;
        }
    }

    public boolean onSpacePressed() {
        if (!this.mDeviceInteractive || this.mState == 0) {
            return false;
        }
        animateCollapsePanels(2, true);
        return true;
    }

    private void showBouncerIfKeyguard() {
        int i = this.mState;
        if ((i == 1 || i == 2) && !this.mKeyguardViewMediator.isHiding()) {
            showBouncer(true);
        }
    }

    public void showBouncer(boolean z) {
        this.mStatusBarKeyguardViewManager.showBouncer(z);
    }

    public void instantExpandNotificationsPanel() {
        makeExpandedVisible(true);
        this.mNotificationPanel.expand(false);
        this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, false);
    }

    public boolean closeShadeIfOpen() {
        if (!this.mNotificationPanel.isFullyCollapsed()) {
            this.mCommandQueue.animateCollapsePanels(2, true);
            visibilityChanged(false);
            this.mAssistManager.hideAssist();
        }
        return false;
    }

    public void postOnShadeExpanded(final Runnable runnable) {
        this.mNotificationPanel.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (StatusBar.this.getStatusBarWindow().getHeight() != StatusBar.this.getStatusBarHeight()) {
                    StatusBar.this.mNotificationPanel.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    StatusBar.this.mNotificationPanel.post(runnable);
                }
            }
        });
    }

    private void instantCollapseNotificationPanel() {
        this.mNotificationPanel.instantCollapse();
        runPostCollapseRunnables();
    }

    public void onStatePreChange(int i, int i2) {
        if (this.mVisible && (i2 == 2 || ((SysuiStatusBarStateController) Dependency.get(StatusBarStateController.class)).goingToFullShade())) {
            clearNotificationEffects();
        }
        if (i2 == 1) {
            this.mRemoteInputManager.onPanelCollapsed();
            maybeEscalateHeadsUp();
        }
    }

    public void onStateChanged(int i) {
        this.mState = i;
        updateReportRejectedTouchVisibility();
        updateDozing();
        updateTheme();
        this.mNavigationBarController.touchAutoDim(this.mDisplayId);
        Trace.beginSection("StatusBar#updateKeyguardState");
        boolean z = false;
        if (this.mState == 1) {
            this.mKeyguardIndicationController.setVisible(true);
            KeyguardUserSwitcher keyguardUserSwitcher = this.mKeyguardUserSwitcher;
            if (keyguardUserSwitcher != null) {
                keyguardUserSwitcher.setKeyguard(true, this.mStatusBarStateController.fromShadeLocked());
            }
            PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
            if (phoneStatusBarView != null) {
                phoneStatusBarView.removePendingHideExpandedRunnables();
            }
            View view = this.mAmbientIndicationContainer;
            if (view != null) {
                view.setVisibility(0);
            }
        } else {
            KeyguardUserSwitcher keyguardUserSwitcher2 = this.mKeyguardUserSwitcher;
            if (keyguardUserSwitcher2 != null) {
                keyguardUserSwitcher2.setKeyguard(false, this.mStatusBarStateController.goingToFullShade() || this.mState == 2 || this.mStatusBarStateController.fromShadeLocked());
            }
            View view2 = this.mAmbientIndicationContainer;
            if (view2 != null) {
                view2.setVisibility(4);
            }
        }
        updateDozingState();
        checkBarModes();
        updateScrimController();
        this.mPresenter.updateMediaMetaData(false, this.mState != 1);
        ((PulseController) Dependency.get(PulseController.class)).setKeyguardShowing(this.mState == 1);
        updateKeyguardState();
        StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) this.mIconController;
        if (this.mState == 1) {
            z = true;
        }
        statusBarIconControllerImpl.setKeyguardShowing(z);
        Trace.endSection();
    }

    public void onDozingChanged(boolean z) {
        Trace.beginSection("StatusBar#updateDozing");
        this.mDozing = z;
        this.mNotificationPanel.resetViews(this.mDozingRequested && DozeParameters.getInstance(this.mContext).shouldControlScreenOff());
        updateQsExpansionEnabled();
        this.mKeyguardViewMediator.setDozing(this.mDozing);
        this.mEntryManager.updateNotifications();
        updateDozingState();
        updateScrimController();
        updateReportRejectedTouchVisibility();
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public void updateDozing() {
        boolean z = (this.mDozingRequested && this.mState == 1) || this.mBiometricUnlockController.getMode() == 2;
        if (this.mBiometricUnlockController.getMode() == 1) {
            z = false;
        }
        this.mStatusBarStateController.setIsDozing(z);
    }

    private void updateKeyguardState() {
        KeyguardMonitor keyguardMonitor = this.mKeyguardMonitor;
        if (keyguardMonitor != null) {
            keyguardMonitor.notifyKeyguardState(this.mStatusBarKeyguardViewManager.isShowing(), this.mUnlockMethodCache.isMethodSecure(), this.mStatusBarKeyguardViewManager.isOccluded());
        }
    }

    public void onActivationReset() {
        this.mKeyguardIndicationController.hideTransientIndication();
    }

    public void onTrackingStarted() {
        runPostCollapseRunnables();
    }

    public void onClosingFinished() {
        runPostCollapseRunnables();
        if (!this.mPresenter.isPresenterFullyCollapsed()) {
            this.mStatusBarWindowController.setStatusBarFocusable(true);
        }
    }

    public void onUnlockHintStarted() {
        this.mFalsingManager.onUnlockHintStarted();
        this.mKeyguardIndicationController.showTransientIndication(C1784R$string.keyguard_unlock);
    }

    public void onHintFinished() {
        this.mKeyguardIndicationController.hideTransientIndicationDelayed(1200);
    }

    public void onCameraHintStarted() {
        this.mFalsingManager.onCameraHintStarted();
        this.mKeyguardIndicationController.showTransientIndication(C1784R$string.camera_hint);
    }

    public void onVoiceAssistHintStarted() {
        this.mFalsingManager.onLeftAffordanceHintStarted();
        this.mKeyguardIndicationController.showTransientIndication(C1784R$string.voice_hint);
    }

    public void onPhoneHintStarted() {
        this.mFalsingManager.onLeftAffordanceHintStarted();
        this.mKeyguardIndicationController.showTransientIndication(C1784R$string.phone_hint);
    }

    public void onTrackingStopped(boolean z) {
        int i = this.mState;
        if ((i == 1 || i == 2) && !z && !this.mUnlockMethodCache.canSkipBouncer()) {
            showBouncer(false);
        }
    }

    public NavigationBarView getNavigationBarView() {
        return this.mNavigationBarController.getNavigationBarView(this.mDisplayId);
    }

    public VisualizerView getLsVisualizer() {
        return this.mVisualizerView;
    }

    public KeyguardBottomAreaView getKeyguardBottomAreaView() {
        return this.mNotificationPanel.getKeyguardBottomAreaView();
    }

    public void goToLockedShade(View view) {
        NotificationEntry notificationEntry;
        if ((this.mDisabled2 & 4) == 0) {
            int currentUserId = this.mLockscreenUserManager.getCurrentUserId();
            if (view instanceof ExpandableNotificationRow) {
                notificationEntry = ((ExpandableNotificationRow) view).getEntry();
                notificationEntry.setUserExpanded(true, true);
                notificationEntry.setGroupExpansionChanging(true);
                StatusBarNotification statusBarNotification = notificationEntry.notification;
                if (statusBarNotification != null) {
                    currentUserId = statusBarNotification.getUserId();
                }
            } else {
                notificationEntry = null;
            }
            NotificationLockscreenUserManager notificationLockscreenUserManager = this.mLockscreenUserManager;
            boolean z = !notificationLockscreenUserManager.userAllowsPrivateNotificationsInPublic(notificationLockscreenUserManager.getCurrentUserId()) || !this.mLockscreenUserManager.shouldShowLockscreenNotifications() || this.mFalsingManager.shouldEnforceBouncer();
            if (this.mKeyguardBypassController.getBypassEnabled()) {
                z = false;
            }
            if (!this.mLockscreenUserManager.isLockscreenPublicMode(currentUserId) || !z) {
                this.mNotificationPanel.animateToFullShade(0);
                this.mStatusBarStateController.setState(2);
                return;
            }
            this.mStatusBarStateController.setLeaveOpenOnKeyguardHide(true);
            showBouncerIfKeyguard();
            this.mDraggedDownEntry = notificationEntry;
            this.mPendingRemoteInputView = null;
        }
    }

    public void goToKeyguard() {
        if (this.mState == 2) {
            this.mStatusBarStateController.setState(1);
        }
    }

    public void setBouncerShowing(boolean z) {
        this.mBouncerShowing = z;
        this.mKeyguardBypassController.setBouncerShowing(z);
        this.mPulseExpansionHandler.setBouncerShowing(z);
        this.mStatusBarWindow.setBouncerShowingScrimmed(isBouncerShowingScrimmed());
        PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
        if (phoneStatusBarView != null) {
            phoneStatusBarView.setBouncerShowing(z);
        }
        updateHideIconsForBouncer(true);
        this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, true);
        updateScrimController();
        if (!this.mBouncerShowing) {
            updatePanelExpansionForKeyguard();
        }
    }

    public void collapseShade() {
        if (this.mNotificationPanel.isTracking()) {
            this.mStatusBarWindow.cancelCurrentTouch();
        }
        if (this.mPanelExpanded && this.mState == 0) {
            animateCollapsePanels();
        }
    }

    /* access modifiers changed from: private */
    public void updateNotificationPanelTouchState() {
        boolean z = false;
        boolean z2 = isGoingToSleep() && !DozeParameters.getInstance(this.mContext).shouldControlScreenOff();
        if ((!this.mDeviceInteractive && !this.mPulsing) || z2) {
            z = true;
        }
        this.mNotificationPanel.setTouchAndAnimationDisabled(z);
        this.mNotificationIconAreaController.setAnimationsEnabled(!z);
    }

    private class SbSettingsObserver extends ContentObserver {
        SbSettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = StatusBar.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("double_tap_sleep_gesture"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("double_tap_sleep_lockscreen"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_layout_columns"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_layout_columns_landscape"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_layout_rows"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_layout_rows_landscape"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_quickbar_columns"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_tile_title_visibility"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("heads_up_stoplist_values"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("heads_up_blacklist_values"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("screen_brightness_mode"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_brightness_control"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("pulse_on_new_tracks"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("less_boring_heads_up"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("fp_swipe_to_dismiss_notifications"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_quick_qs_pulldown"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("gaming_mode_active"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("gaming_mode_headsup_toggle"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("sysui_rounded_fwvals"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("show_back_arrow_gesture"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("lockscreen_media_blur"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("back_gesture_haptic"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_background_blur_alpha"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_background_blur_intensity"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("long_back_swipe_timeout"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("left_long_back_swipe_action"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("right_long_back_swipe_action"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("back_swipe_type"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("navigation_bar_show_new"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("show_media_heads_up"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("screen_off_fod"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_show_battery_estimate"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("display_cutout_mode"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("stock_statusbar_in_hide"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_show_ticker"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_ticker_mode"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_ticker_animation_mode"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_ticker_tick_duration"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_tile_accent_tint"), false, this, -1);
        }

        public void onChange(boolean z, Uri uri) {
            if (uri.equals(Settings.System.getUriFor("display_cutout_mode")) || uri.equals(Settings.System.getUriFor("stock_statusbar_in_hide"))) {
                StatusBar.this.handleCutout();
            } else if (uri.equals(Settings.System.getUriFor("qs_tile_accent_tint"))) {
                StatusBar.this.mQSPanel.getHost().reloadAllTiles();
            }
            update();
        }

        public void update() {
            StatusBar.this.setStatusDoubleTapToSleep();
            StatusBar.this.setQsColumns();
            StatusBar.this.setHeadsUpStoplist();
            StatusBar.this.setHeadsUpBlacklist();
            StatusBar.this.setScreenBrightnessMode();
            StatusBar.this.setPulseOnNewTracks();
            StatusBar.this.setUseLessBoringHeadsUp();
            StatusBar.this.setFpToDismissNotifications();
            StatusBar.this.setGamingMode();
            StatusBar.this.updateCorners();
            StatusBar.this.setHideArrowForBackGesture();
            StatusBar.this.setLockScreenMediaBlurLevel();
            StatusBar.this.setHapticFeedbackForBackGesture();
            StatusBar.this.updateBlurVisibility();
            StatusBar.this.setGestureNavOptions();
            StatusBar statusBar = StatusBar.this;
            statusBar.updateNavigationBar(statusBar.getRegisterStatusBarResult(), false);
            StatusBar.this.updateAODDimView();
            StatusBar.this.setMediaHeadsup();
            StatusBar.this.setQsBatteryPercentMode();
            StatusBar.this.updateTicker();
        }
    }

    /* access modifiers changed from: private */
    public void setScreenBrightnessMode() {
        boolean z = false;
        this.mAutomaticBrightness = Settings.System.getIntForUser(this.mContext.getContentResolver(), "screen_brightness_mode", 0, -2) != 0;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_brightness_control", 0, -2) == 1) {
            z = true;
        }
        this.mBrightnessControl = z;
    }

    /* access modifiers changed from: private */
    public void setStatusDoubleTapToSleep() {
        StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
        if (statusBarWindowView != null) {
            statusBarWindowView.updateSettings();
        }
    }

    /* access modifiers changed from: private */
    public void setQsColumns() {
        QSPanel qSPanel = this.mQSPanel;
        if (qSPanel != null) {
            qSPanel.updateResources();
            this.mQSPanel.updateSettings();
        }
        QuickQSPanel quickQSPanel = this.mQuickQSPanel;
        if (quickQSPanel != null) {
            quickQSPanel.updateSettings();
        }
    }

    /* access modifiers changed from: private */
    public void setHeadsUpStoplist() {
        StatusBarNotificationPresenter statusBarNotificationPresenter = this.mPresenter;
        if (statusBarNotificationPresenter != null) {
            statusBarNotificationPresenter.setHeadsUpStoplist();
        }
    }

    /* access modifiers changed from: private */
    public void setHeadsUpBlacklist() {
        StatusBarNotificationPresenter statusBarNotificationPresenter = this.mPresenter;
        if (statusBarNotificationPresenter != null) {
            statusBarNotificationPresenter.setHeadsUpBlacklist();
        }
    }

    /* access modifiers changed from: private */
    public void setPulseOnNewTracks() {
        KeyguardSliceProvider attachedInstance = KeyguardSliceProvider.getAttachedInstance();
        if (attachedInstance != null) {
            boolean z = true;
            if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "pulse_on_new_tracks", 1, -2) != 1) {
                z = false;
            }
            attachedInstance.setPulseOnNewTracks(z);
        }
    }

    /* access modifiers changed from: private */
    public void setUseLessBoringHeadsUp() {
        boolean z = false;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "less_boring_heads_up", 0, -2) == 1) {
            z = true;
        }
        this.mNotificationInterruptionStateProvider.setUseLessBoringHeadsUp(z);
    }

    /* access modifiers changed from: private */
    public void setFpToDismissNotifications() {
        boolean z = false;
        if (Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "fp_swipe_to_dismiss_notifications", 0, -2) == 1) {
            z = true;
        }
        this.mFpDismissNotifications = z;
    }

    /* access modifiers changed from: private */
    public void setGamingMode() {
        boolean z = false;
        this.mGamingModeActivated = Settings.System.getIntForUser(this.mContext.getContentResolver(), "gaming_mode_active", 0, -2) == 1;
        this.mHeadsUpDisabled = Settings.System.getIntForUser(this.mContext.getContentResolver(), "gaming_mode_headsup_toggle", 1, -2) == 1;
        NotificationInterruptionStateProvider notificationInterruptionStateProvider = this.mNotificationInterruptionStateProvider;
        if (this.mGamingModeActivated && this.mHeadsUpDisabled) {
            z = true;
        }
        notificationInterruptionStateProvider.setGamingPeekMode(z);
    }

    /* access modifiers changed from: private */
    public void setHideArrowForBackGesture() {
        if (getNavigationBarView() != null) {
            getNavigationBarView().updateBackArrowForGesture();
        }
    }

    /* access modifiers changed from: private */
    public void setLockScreenMediaBlurLevel() {
        NotificationMediaManager notificationMediaManager = this.mMediaManager;
        if (notificationMediaManager != null) {
            notificationMediaManager.setLockScreenMediaBlurLevel();
        }
    }

    /* access modifiers changed from: private */
    public void setHapticFeedbackForBackGesture() {
        if (getNavigationBarView() != null) {
            getNavigationBarView().updateBackGestureHaptic();
        }
    }

    /* access modifiers changed from: private */
    public void setGestureNavOptions() {
        if (getNavigationBarView() != null) {
            getNavigationBarView().setLongSwipeOptions();
        }
    }

    /* access modifiers changed from: private */
    public void setMediaHeadsup() {
        NotificationMediaManager notificationMediaManager = this.mMediaManager;
        if (notificationMediaManager != null) {
            notificationMediaManager.setMediaHeadsup();
        }
    }

    /* access modifiers changed from: private */
    public void updateAODDimView() {
        AODDimView aODDimView = this.mAODDimView;
        boolean z = false;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "screen_off_fod", 0, -2) != 0) {
            z = true;
        }
        aODDimView.setEnabled(z);
    }

    /* access modifiers changed from: private */
    public void setQsBatteryPercentMode() {
        View view = this.mQSBarHeader;
        if (view != null) {
            ((QuickStatusBarHeader) view).setBatteryPercentMode();
        }
    }

    private void setBlackStatusBar(boolean z) {
        StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
        if (statusBarWindowView != null && statusBarWindowView.getBarTransitions() != null) {
            if (z) {
                this.mStatusBarWindow.getBarTransitions().getBackground().setColorOverride(new Integer(-16777216));
            } else {
                this.mStatusBarWindow.getBarTransitions().getBackground().setColorOverride((Integer) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleCutout() {
        boolean z = false;
        this.mImmerseMode = Settings.System.getIntForUser(this.mContext.getContentResolver(), "display_cutout_mode", 0, -2);
        this.mStockStatusBar = Settings.System.getIntForUser(this.mContext.getContentResolver(), "stock_statusbar_in_hide", 1, -2) == 1;
        boolean z2 = this.mImmerseMode == 1;
        if (this.mImmerseMode == 2) {
            z = true;
        }
        this.mUiOffloadThread.submit(new Runnable(z2, z) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                StatusBar.this.lambda$handleCutout$30$StatusBar(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$handleCutout$30$StatusBar(boolean z, boolean z2) {
        boolean z3 = true;
        setBlackStatusBar(this.mPortrait && z);
        setCutoutOverlay(z2);
        if (!z2 || !this.mStockStatusBar) {
            z3 = false;
        }
        setStatusBarStockOverlay(z3);
    }

    public int getWakefulnessState() {
        return this.mWakefulnessLifecycle.getWakefulness();
    }

    private void vibrateForCameraGesture() {
        this.mVibrator.vibrate(this.mCameraLaunchGestureVibePattern, -1);
    }

    public boolean isScreenFullyOff() {
        return this.mScreenLifecycle.getScreenState() == 0;
    }

    public void showScreenPinningRequest(int i) {
        if (!this.mKeyguardMonitor.isShowing()) {
            showScreenPinningRequest(i, true);
        }
    }

    public void showScreenPinningRequest(int i, boolean z) {
        this.mScreenPinningRequest.showPrompt(i, z);
    }

    public boolean hasActiveNotifications() {
        return !this.mEntryManager.getNotificationData().getActiveNotifications().isEmpty();
    }

    public void appTransitionCancelled(int i) {
        if (i == this.mDisplayId) {
            ((Divider) getComponent(Divider.class)).onAppTransitionFinished();
        }
    }

    public void appTransitionFinished(int i) {
        if (i == this.mDisplayId) {
            ((Divider) getComponent(Divider.class)).onAppTransitionFinished();
        }
    }

    public void onCameraLaunchGestureDetected(int i) {
        this.mLastCameraLaunchSource = i;
        if (isGoingToSleep()) {
            this.mLaunchCameraOnFinishedGoingToSleep = true;
            return;
        }
        if (this.mNotificationPanel.canCameraGestureBeLaunched(this.mStatusBarKeyguardViewManager.isShowing() && (this.mExpandedVisible || this.mBouncerShowing))) {
            if (!this.mDeviceInteractive) {
                ((PowerManager) this.mContext.getSystemService(PowerManager.class)).wakeUp(SystemClock.uptimeMillis(), 5, "com.android.systemui:CAMERA_GESTURE");
            }
            if (i != 3) {
                vibrateForCameraGesture();
            }
            if (i == 1) {
                Log.v(TAG, "Camera launch");
                this.mKeyguardUpdateMonitor.onCameraLaunched();
            }
            if (!this.mStatusBarKeyguardViewManager.isShowing()) {
                startActivityDismissingKeyguard(KeyguardBottomAreaView.INSECURE_CAMERA_INTENT, false, true, true, (ActivityStarter.Callback) null, 0);
                return;
            }
            if (!this.mDeviceInteractive) {
                this.mGestureWakeLock.acquire(6000);
            }
            if (isWakingUpOrAwake()) {
                if (this.mStatusBarKeyguardViewManager.isBouncerShowing()) {
                    this.mStatusBarKeyguardViewManager.reset(true);
                }
                this.mNotificationPanel.launchCamera(this.mDeviceInteractive, i);
                updateScrimController();
                return;
            }
            this.mLaunchCameraWhenFinishedWaking = true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCameraAllowedByAdmin() {
        if (this.mDevicePolicyManager.getCameraDisabled((ComponentName) null, this.mLockscreenUserManager.getCurrentUserId())) {
            return false;
        }
        if (this.mStatusBarKeyguardViewManager != null && (!isKeyguardShowing() || !isKeyguardSecure())) {
            return true;
        }
        if ((this.mDevicePolicyManager.getKeyguardDisabledFeatures((ComponentName) null, this.mLockscreenUserManager.getCurrentUserId()) & 2) == 0) {
            return true;
        }
        return false;
    }

    private boolean isGoingToSleep() {
        return this.mWakefulnessLifecycle.getWakefulness() == 3;
    }

    private boolean isWakingUpOrAwake() {
        if (this.mWakefulnessLifecycle.getWakefulness() == 2 || this.mWakefulnessLifecycle.getWakefulness() == 1) {
            return true;
        }
        return false;
    }

    public void notifyBiometricAuthModeChanged() {
        updateDozing();
        updateScrimController();
        this.mStatusBarWindow.onBiometricAuthModeChanged(this.mBiometricUnlockController.isWakeAndUnlock(), this.mBiometricUnlockController.isBiometricUnlock());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        r0 = r6.mKeyguardMonitor;
     */
    @com.android.internal.annotations.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateScrimController() {
        /*
            r6 = this;
            java.lang.String r0 = "StatusBar#updateScrimController"
            android.os.Trace.beginSection(r0)
            com.android.systemui.statusbar.phone.BiometricUnlockController r0 = r6.mBiometricUnlockController
            boolean r0 = r0.isWakeAndUnlock()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x001c
            com.android.systemui.statusbar.policy.KeyguardMonitor r0 = r6.mKeyguardMonitor
            if (r0 == 0) goto L_0x001a
            boolean r0 = r0.isKeyguardFadingAway()
            if (r0 == 0) goto L_0x001a
            goto L_0x001c
        L_0x001a:
            r0 = r1
            goto L_0x001d
        L_0x001c:
            r0 = r2
        L_0x001d:
            com.android.systemui.statusbar.phone.ScrimController r3 = r6.mScrimController
            com.android.systemui.statusbar.phone.BiometricUnlockController r4 = r6.mBiometricUnlockController
            boolean r4 = r4.isBiometricUnlock()
            r4 = r4 ^ r2
            r3.setExpansionAffectsAlpha(r4)
            com.android.systemui.statusbar.phone.NotificationPanelView r3 = r6.mNotificationPanel
            boolean r3 = r3.isLaunchingAffordanceWithPreview()
            com.android.systemui.statusbar.phone.ScrimController r4 = r6.mScrimController
            if (r3 != 0) goto L_0x003b
            com.android.systemui.statusbar.phone.BiometricUnlockController r5 = r6.mBiometricUnlockController
            boolean r5 = r5.isWakeAndUnlock()
            if (r5 == 0) goto L_0x003c
        L_0x003b:
            r1 = r2
        L_0x003c:
            r4.setLaunchingAffordanceWithPreview(r1)
            boolean r1 = r6.mBouncerShowing
            if (r1 == 0) goto L_0x0057
            com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r0 = r6.mStatusBarKeyguardViewManager
            boolean r0 = r0.bouncerNeedsScrimming()
            if (r0 == 0) goto L_0x004e
            com.android.systemui.statusbar.phone.ScrimState r0 = com.android.systemui.statusbar.phone.ScrimState.BOUNCER_SCRIMMED
            goto L_0x0050
        L_0x004e:
            com.android.systemui.statusbar.phone.ScrimState r0 = com.android.systemui.statusbar.phone.ScrimState.BOUNCER
        L_0x0050:
            com.android.systemui.statusbar.phone.ScrimController r6 = r6.mScrimController
            r6.transitionTo(r0)
            goto L_0x00c5
        L_0x0057:
            boolean r1 = r6.isInLaunchTransition()
            if (r1 != 0) goto L_0x00bc
            boolean r1 = r6.mLaunchCameraWhenFinishedWaking
            if (r1 != 0) goto L_0x00bc
            if (r3 == 0) goto L_0x0064
            goto L_0x00bc
        L_0x0064:
            boolean r1 = r6.mBrightnessMirrorVisible
            if (r1 == 0) goto L_0x0070
            com.android.systemui.statusbar.phone.ScrimController r6 = r6.mScrimController
            com.android.systemui.statusbar.phone.ScrimState r0 = com.android.systemui.statusbar.phone.ScrimState.BRIGHTNESS_MIRROR
            r6.transitionTo(r0)
            goto L_0x00c5
        L_0x0070:
            boolean r1 = r6.isPulsing()
            if (r1 == 0) goto L_0x0084
            com.android.systemui.statusbar.phone.ScrimController r0 = r6.mScrimController
            com.android.systemui.statusbar.phone.ScrimState r1 = com.android.systemui.statusbar.phone.ScrimState.PULSING
            com.android.systemui.statusbar.phone.DozeScrimController r6 = r6.mDozeScrimController
            com.android.systemui.statusbar.phone.ScrimController$Callback r6 = r6.getScrimCallback()
            r0.transitionTo(r1, r6)
            goto L_0x00c5
        L_0x0084:
            boolean r1 = r6.mDozing
            if (r1 == 0) goto L_0x0092
            if (r0 != 0) goto L_0x0092
            com.android.systemui.statusbar.phone.ScrimController r6 = r6.mScrimController
            com.android.systemui.statusbar.phone.ScrimState r0 = com.android.systemui.statusbar.phone.ScrimState.AOD
            r6.transitionTo(r0)
            goto L_0x00c5
        L_0x0092:
            boolean r1 = r6.mIsKeyguard
            if (r1 == 0) goto L_0x00a0
            if (r0 != 0) goto L_0x00a0
            com.android.systemui.statusbar.phone.ScrimController r6 = r6.mScrimController
            com.android.systemui.statusbar.phone.ScrimState r0 = com.android.systemui.statusbar.phone.ScrimState.KEYGUARD
            r6.transitionTo(r0)
            goto L_0x00c5
        L_0x00a0:
            com.android.systemui.bubbles.BubbleController r0 = r6.mBubbleController
            if (r0 == 0) goto L_0x00b2
            boolean r0 = r0.isStackExpanded()
            if (r0 == 0) goto L_0x00b2
            com.android.systemui.statusbar.phone.ScrimController r6 = r6.mScrimController
            com.android.systemui.statusbar.phone.ScrimState r0 = com.android.systemui.statusbar.phone.ScrimState.BUBBLE_EXPANDED
            r6.transitionTo(r0)
            goto L_0x00c5
        L_0x00b2:
            com.android.systemui.statusbar.phone.ScrimController r0 = r6.mScrimController
            com.android.systemui.statusbar.phone.ScrimState r1 = com.android.systemui.statusbar.phone.ScrimState.UNLOCKED
            com.android.systemui.statusbar.phone.ScrimController$Callback r6 = r6.mUnlockScrimCallback
            r0.transitionTo(r1, r6)
            goto L_0x00c5
        L_0x00bc:
            com.android.systemui.statusbar.phone.ScrimController r0 = r6.mScrimController
            com.android.systemui.statusbar.phone.ScrimState r1 = com.android.systemui.statusbar.phone.ScrimState.UNLOCKED
            com.android.systemui.statusbar.phone.ScrimController$Callback r6 = r6.mUnlockScrimCallback
            r0.transitionTo(r1, r6)
        L_0x00c5:
            android.os.Trace.endSection()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBar.updateScrimController():void");
    }

    public boolean isKeyguardShowing() {
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
        if (statusBarKeyguardViewManager != null) {
            return statusBarKeyguardViewManager.isShowing();
        }
        Slog.i(TAG, "isKeyguardShowing() called before startKeyguard(), returning true");
        return true;
    }

    @VisibleForTesting
    final class DozeServiceHost implements DozeHost {
        private boolean mAnimateScreenOff;
        private boolean mAnimateWakeup;
        private final ArrayList<DozeHost.Callback> mCallbacks = new ArrayList<>();
        /* access modifiers changed from: private */
        public boolean mIgnoreTouchWhilePulsing;
        @VisibleForTesting
        boolean mWakeLockScreenPerformsAuth = SystemProperties.getBoolean("persist.sysui.wake_performs_auth", true);

        DozeServiceHost() {
        }

        public String toString() {
            return "PSB.DozeServiceHost[mCallbacks=" + this.mCallbacks.size() + "]";
        }

        public void firePowerSaveChanged(boolean z) {
            Iterator<DozeHost.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onPowerSaveChanged(z);
            }
        }

        public void fireNotificationPulse(NotificationEntry notificationEntry) {
            $$Lambda$StatusBar$DozeServiceHost$EU0Iwy8ToUOMfEUxCMm_jerTo r0 = new Runnable(notificationEntry) {
                private final /* synthetic */ NotificationEntry f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    StatusBar.DozeServiceHost.this.lambda$fireNotificationPulse$0$StatusBar$DozeServiceHost(this.f$1);
                }
            };
            Iterator<DozeHost.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onNotificationAlerted(r0);
            }
        }

        public /* synthetic */ void lambda$fireNotificationPulse$0$StatusBar$DozeServiceHost(NotificationEntry notificationEntry) {
            notificationEntry.setPulseSuppressed(true);
            StatusBar.this.mNotificationIconAreaController.updateAodNotificationIcons();
        }

        public void addCallback(DozeHost.Callback callback) {
            this.mCallbacks.add(callback);
        }

        public void removeCallback(DozeHost.Callback callback) {
            this.mCallbacks.remove(callback);
        }

        public void startDozing() {
            if (!StatusBar.this.mDozingRequested) {
                boolean unused = StatusBar.this.mDozingRequested = true;
                StatusBar statusBar = StatusBar.this;
                DozeLog.traceDozing(statusBar.mContext, statusBar.mDozing);
                StatusBar.this.updateDozing();
                boolean unused2 = StatusBar.this.updateIsKeyguard();
                return;
            }
            boolean unused3 = StatusBar.this.mDozingRequested = true;
        }

        public void pulseWhileDozing(final DozeHost.PulseCallback pulseCallback, int i) {
            StatusBarWindowView statusBarWindowView;
            if (i == 5) {
                StatusBar.this.mPowerManager.wakeUp(SystemClock.uptimeMillis(), 4, "com.android.systemui:LONG_PRESS");
                StatusBar.this.startAssist(new Bundle());
                return;
            }
            if (i == 8) {
                StatusBar.this.mScrimController.setWakeLockScreenSensorActive(true);
            }
            if (i == 6 && (statusBarWindowView = StatusBar.this.mStatusBarWindow) != null) {
                statusBarWindowView.suppressWakeUpGesture(true);
            }
            final boolean z = i == 8 && this.mWakeLockScreenPerformsAuth;
            boolean unused = StatusBar.this.mPulsing = true;
            StatusBar.this.mDozeScrimController.pulse(new DozeHost.PulseCallback() {
                public void onPulseStarted() {
                    pulseCallback.onPulseStarted();
                    StatusBar.this.updateNotificationPanelTouchState();
                    StatusBar.this.mAODDimView.setVisible(false);
                    setPulsing(true);
                    KeyguardUpdateMonitor.getInstance(StatusBar.this.mContext).setPulsing(true);
                }

                public void onPulseFinished() {
                    boolean unused = StatusBar.this.mPulsing = false;
                    pulseCallback.onPulseFinished();
                    StatusBar.this.updateNotificationPanelTouchState();
                    StatusBar.this.mScrimController.setWakeLockScreenSensorActive(false);
                    StatusBarWindowView statusBarWindowView = StatusBar.this.mStatusBarWindow;
                    if (statusBarWindowView != null) {
                        statusBarWindowView.suppressWakeUpGesture(false);
                    }
                    StatusBar.this.mAODDimView.setVisible(true);
                    setPulsing(false);
                    KeyguardUpdateMonitor.getInstance(StatusBar.this.mContext).setPulsing(false);
                }

                private void setPulsing(boolean z) {
                    StatusBar.this.mStatusBarStateController.setPulsing(z);
                    StatusBar.this.mStatusBarKeyguardViewManager.setPulsing(z);
                    StatusBar.this.mKeyguardViewMediator.setPulsing(z);
                    StatusBar.this.mNotificationPanel.setPulsing(z);
                    StatusBar.this.mVisualStabilityManager.setPulsing(z);
                    StatusBar.this.mStatusBarWindow.setPulsing(z);
                    boolean unused = DozeServiceHost.this.mIgnoreTouchWhilePulsing = false;
                    KeyguardUpdateMonitor keyguardUpdateMonitor = StatusBar.this.mKeyguardUpdateMonitor;
                    if (keyguardUpdateMonitor != null && z) {
                        keyguardUpdateMonitor.onAuthInterruptDetected(z);
                    }
                    StatusBar.this.updateScrimController();
                    StatusBar.this.mPulseExpansionHandler.setPulsing(z);
                    StatusBar.this.mWakeUpCoordinator.setPulsing(z);
                }
            }, i);
            StatusBar.this.updateScrimController();
        }

        public void stopDozing() {
            if (StatusBar.this.mDozingRequested) {
                boolean unused = StatusBar.this.mDozingRequested = false;
                StatusBar statusBar = StatusBar.this;
                DozeLog.traceDozing(statusBar.mContext, statusBar.mDozing);
                StatusBar.this.updateDozing();
            }
        }

        public void onIgnoreTouchWhilePulsing(boolean z) {
            if (z != this.mIgnoreTouchWhilePulsing) {
                DozeLog.tracePulseTouchDisabledByProx(StatusBar.this.mContext, z);
            }
            this.mIgnoreTouchWhilePulsing = z;
            if (StatusBar.this.isDozing() && z) {
                StatusBar.this.mStatusBarWindow.cancelCurrentTouch();
            }
        }

        public void dozeTimeTick() {
            StatusBar.this.mNotificationPanel.dozeTimeTick();
            if (StatusBar.this.mAmbientIndicationContainer instanceof DozeReceiver) {
                ((DozeReceiver) StatusBar.this.mAmbientIndicationContainer).dozeTimeTick();
            }
        }

        public boolean isPowerSaveActive() {
            return StatusBar.this.mBatteryController.isAodPowerSave();
        }

        public boolean isPulsingBlocked() {
            return StatusBar.this.mBiometricUnlockController.getMode() == 1;
        }

        public boolean isProvisioned() {
            return StatusBar.this.mDeviceProvisionedController.isDeviceProvisioned() && StatusBar.this.mDeviceProvisionedController.isCurrentUserSetup();
        }

        public boolean isBlockingDoze() {
            if (!StatusBar.this.mBiometricUnlockController.hasPendingAuthentication()) {
                return false;
            }
            Log.i(StatusBar.TAG, "Blocking AOD because fingerprint has authenticated");
            return true;
        }

        public void extendPulse(int i) {
            if (i == 8) {
                StatusBar.this.mScrimController.setWakeLockScreenSensorActive(true);
            }
            if (!StatusBar.this.mDozeScrimController.isPulsing() || !StatusBar.this.mHeadsUpManager.hasNotifications()) {
                StatusBar.this.mDozeScrimController.extendPulse();
            } else {
                StatusBar.this.mHeadsUpManager.extendHeadsUp();
            }
        }

        public void setAnimateWakeup(boolean z) {
            if (StatusBar.this.mWakefulnessLifecycle.getWakefulness() != 2 && StatusBar.this.mWakefulnessLifecycle.getWakefulness() != 1) {
                this.mAnimateWakeup = z;
            }
        }

        public void setAnimateScreenOff(boolean z) {
            this.mAnimateScreenOff = z;
        }

        public void onSlpiTap(float f, float f2, int i) {
            if (StatusBar.this.isDoubleTapOnMusicTicker(f, f2)) {
                StatusBar.this.handleSystemKey(87);
                return;
            }
            Iterator<DozeHost.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().wakeUpFromDoubleTap(i);
            }
        }

        public void setDozeScreenBrightness(int i) {
            StatusBar.this.mStatusBarWindowController.setDozeScreenBrightness(i);
        }

        public void setAodDimmingScrim(float f) {
            StatusBar.this.mScrimController.setAodFrontScrimAlpha(f);
        }

        /* access modifiers changed from: private */
        public boolean shouldAnimateWakeup() {
            return this.mAnimateWakeup;
        }

        public boolean shouldAnimateScreenOff() {
            return this.mAnimateScreenOff;
        }

        public void toggleFlashlightProximityCheck() {
            Iterator<DozeHost.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().toggleFlashlightProximityCheck();
            }
        }

        public void performToggleFlashlight() {
            StatusBar.this.toggleFlashlight();
        }
    }

    public boolean isDoubleTapOnMusicTicker(float f, float f2) {
        KeyguardSliceProvider attachedInstance = KeyguardSliceProvider.getAttachedInstance();
        NotificationPanelView notificationPanelView = this.mNotificationPanel;
        View titleView = notificationPanelView != null ? notificationPanelView.getKeyguardStatusView().getKeyguardSliceView().getTitleView() : null;
        if (f > 0.0f && f2 > 0.0f && attachedInstance != null && titleView != null && attachedInstance.needsMediaLocked()) {
            titleView.getLocationOnScreen(this.mTmpInt2);
            int[] iArr = this.mTmpInt2;
            float f3 = f - ((float) iArr[0]);
            float f4 = f2 - ((float) iArr[1]);
            if (0.0f > f3 || f3 > ((float) titleView.getWidth()) || 0.0f > f4 || f4 > ((float) titleView.getHeight())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean shouldIgnoreTouch() {
        return isDozing() && this.mDozeServiceHost.mIgnoreTouchWhilePulsing;
    }

    public boolean isDeviceInteractive() {
        return this.mDeviceInteractive;
    }

    public boolean isNotificationForCurrentProfiles(StatusBarNotification statusBarNotification) {
        return this.mLockscreenUserManager.isCurrentProfile(statusBarNotification.getUserId());
    }

    /* access modifiers changed from: private */
    public void updateTicker() {
        boolean z = true;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_show_ticker", 0, -2) != 1) {
            z = false;
        }
        this.mTickerEnabled = z;
        this.mTickerMode = Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_ticker_mode", 0, -2);
        this.mTickerAnimationMode = Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_ticker_animation_mode", 0, -2);
        this.mTickerTickDuration = Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_ticker_tick_duration", 3000, -2);
        Ticker ticker = this.mTicker;
        if (ticker != null) {
            ticker.updateAnimation(this.mTickerAnimationMode);
            this.mTicker.updateTickDuration(this.mTickerTickDuration);
        }
    }

    public void collapsePanel(boolean z) {
        if (z) {
            if (!collapsePanel()) {
                runPostCollapseRunnables();
            }
        } else if (!this.mPresenter.isPresenterFullyCollapsed()) {
            instantCollapseNotificationPanel();
            visibilityChanged(false);
        } else {
            runPostCollapseRunnables();
        }
    }

    public boolean collapsePanel() {
        if (this.mNotificationPanel.isFullyCollapsed()) {
            return false;
        }
        animateCollapsePanels(2, true, true);
        visibilityChanged(false);
        return true;
    }

    public void setNotificationSnoozed(StatusBarNotification statusBarNotification, NotificationSwipeActionHelper.SnoozeOption snoozeOption) {
        if (snoozeOption.getSnoozeCriterion() != null) {
            this.mNotificationListener.snoozeNotification(statusBarNotification.getKey(), snoozeOption.getSnoozeCriterion().getId());
        } else {
            this.mNotificationListener.snoozeNotification(statusBarNotification.getKey(), (long) (snoozeOption.getMinutesToSnoozeFor() * 60 * MSG_OPEN_NOTIFICATION_PANEL));
        }
    }

    public void toggleSplitScreen() {
        toggleSplitScreenMode(-1, -1);
    }

    /* access modifiers changed from: package-private */
    public void awakenDreams() {
        ((UiOffloadThread) Dependency.get(UiOffloadThread.class)).submit(new Runnable() {
            public final void run() {
                StatusBar.this.lambda$awakenDreams$31$StatusBar();
            }
        });
    }

    public /* synthetic */ void lambda$awakenDreams$31$StatusBar() {
        try {
            this.mDreamManager.awaken();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void preloadRecentApps() {
        this.mHandler.removeMessages(MSG_PRELOAD_RECENT_APPS);
        this.mHandler.sendEmptyMessage(MSG_PRELOAD_RECENT_APPS);
    }

    public void cancelPreloadRecentApps() {
        this.mHandler.removeMessages(MSG_CANCEL_PRELOAD_RECENT_APPS);
        this.mHandler.sendEmptyMessage(MSG_CANCEL_PRELOAD_RECENT_APPS);
    }

    public void dismissKeyboardShortcutsMenu() {
        this.mHandler.removeMessages(MSG_DISMISS_KEYBOARD_SHORTCUTS_MENU);
        this.mHandler.sendEmptyMessage(MSG_DISMISS_KEYBOARD_SHORTCUTS_MENU);
    }

    public void toggleKeyboardShortcutsMenu(int i) {
        this.mHandler.removeMessages(MSG_TOGGLE_KEYBOARD_SHORTCUTS_MENU);
        this.mHandler.obtainMessage(MSG_TOGGLE_KEYBOARD_SHORTCUTS_MENU, i, 0).sendToTarget();
    }

    public void setTopAppHidesStatusBar(boolean z) {
        this.mTopHidesStatusBar = z;
        if (!z && this.mWereIconsJustHidden) {
            this.mWereIconsJustHidden = false;
            this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, true);
        }
        updateHideIconsForBouncer(true);
    }

    /* access modifiers changed from: protected */
    public void toggleKeyboardShortcuts(int i) {
        KeyboardShortcuts.toggle(this.mContext, i);
    }

    /* access modifiers changed from: protected */
    public void dismissKeyboardShortcuts() {
        KeyboardShortcuts.dismiss();
    }

    public void onPanelLaidOut() {
        updateKeyguardMaxNotifications();
    }

    public void updateKeyguardMaxNotifications() {
        if (this.mState == 1 && this.mPresenter.getMaxNotificationsWhileLocked(false) != this.mPresenter.getMaxNotificationsWhileLocked(true)) {
            this.mViewHierarchyManager.updateRowStates();
        }
    }

    public void executeActionDismissingKeyguard(Runnable runnable, boolean z) {
        if (this.mDeviceProvisionedController.isDeviceProvisioned()) {
            dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction(runnable) {
                private final /* synthetic */ Runnable f$1;

                {
                    this.f$1 = r2;
                }

                public final boolean onDismiss() {
                    return StatusBar.this.lambda$executeActionDismissingKeyguard$33$StatusBar(this.f$1);
                }
            }, z);
        }
    }

    public /* synthetic */ boolean lambda$executeActionDismissingKeyguard$33$StatusBar(Runnable runnable) {
        new Thread(new Runnable(runnable) {
            private final /* synthetic */ Runnable f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                StatusBar.lambda$executeActionDismissingKeyguard$32(this.f$0);
            }
        }).start();
        return collapsePanel();
    }

    static /* synthetic */ void lambda$executeActionDismissingKeyguard$32(Runnable runnable) {
        try {
            ActivityManager.getService().resumeAppSwitches();
        } catch (RemoteException unused) {
        }
        runnable.run();
    }

    /* renamed from: startPendingIntentDismissingKeyguard */
    public void lambda$postStartActivityDismissingKeyguard$26$StatusBar(PendingIntent pendingIntent) {
        startPendingIntentDismissingKeyguard(pendingIntent, (Runnable) null);
    }

    public void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent, Runnable runnable) {
        startPendingIntentDismissingKeyguard(pendingIntent, runnable, (View) null);
    }

    public void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent, Runnable runnable, View view) {
        executeActionDismissingKeyguard(new Runnable(pendingIntent, view, runnable) {
            private final /* synthetic */ PendingIntent f$1;
            private final /* synthetic */ View f$2;
            private final /* synthetic */ Runnable f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                StatusBar.this.lambda$startPendingIntentDismissingKeyguard$34$StatusBar(this.f$1, this.f$2, this.f$3);
            }
        }, pendingIntent.isActivity() && this.mActivityIntentHelper.wouldLaunchResolverActivity(pendingIntent.getIntent(), this.mLockscreenUserManager.getCurrentUserId()));
    }

    public /* synthetic */ void lambda$startPendingIntentDismissingKeyguard$34$StatusBar(PendingIntent pendingIntent, View view, Runnable runnable) {
        try {
            pendingIntent.send((Context) null, 0, (Intent) null, (PendingIntent.OnFinished) null, (Handler) null, (String) null, getActivityOptions(this.mActivityLaunchAnimator.getLaunchAnimation(view, this.mShadeController.isOccluded())));
        } catch (PendingIntent.CanceledException e) {
            Log.w(TAG, "Sending intent failed: " + e);
        }
        if (pendingIntent.isActivity()) {
            this.mAssistManager.hideAssist();
        }
        if (runnable != null) {
            postOnUiThread(runnable);
        }
    }

    private void postOnUiThread(Runnable runnable) {
        this.mMainThreadHandler.post(runnable);
    }

    public static Bundle getActivityOptions(RemoteAnimationAdapter remoteAnimationAdapter) {
        ActivityOptions activityOptions;
        if (remoteAnimationAdapter != null) {
            activityOptions = ActivityOptions.makeRemoteAnimation(remoteAnimationAdapter);
        } else {
            activityOptions = ActivityOptions.makeBasic();
        }
        activityOptions.setLaunchWindowingMode(4);
        return activityOptions.toBundle();
    }

    /* access modifiers changed from: protected */
    public void visibilityChanged(boolean z) {
        if (this.mVisible != z) {
            this.mVisible = z;
            if (!z) {
                this.mGutsManager.closeAndSaveGuts(true, true, true, -1, -1, true);
            }
        }
        updateVisibleToUser();
    }

    /* access modifiers changed from: protected */
    public void updateVisibleToUser() {
        boolean z = this.mVisibleToUser;
        this.mVisibleToUser = this.mVisible && this.mDeviceInteractive;
        boolean z2 = this.mVisibleToUser;
        if (z != z2) {
            handleVisibleToUserChanged(z2);
        }
    }

    public void clearNotificationEffects() {
        try {
            this.mBarService.clearNotificationEffects();
        } catch (RemoteException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void notifyHeadsUpGoingToSleep() {
        maybeEscalateHeadsUp();
    }

    public boolean isBouncerShowing() {
        return this.mBouncerShowing;
    }

    public boolean isBouncerShowingScrimmed() {
        return isBouncerShowing() && this.mStatusBarKeyguardViewManager.bouncerNeedsScrimming();
    }

    public static PackageManager getPackageManagerForUser(Context context, int i) {
        if (i >= 0) {
            try {
                context = context.createPackageContextAsUser(context.getPackageName(), 4, new UserHandle(i));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return context.getPackageManager();
    }

    public boolean isKeyguardSecure() {
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
        if (statusBarKeyguardViewManager != null) {
            return statusBarKeyguardViewManager.isSecure();
        }
        Slog.w(TAG, "isKeyguardSecure() called before startKeyguard(), returning false", new Throwable());
        return false;
    }

    public void showAssistDisclosure() {
        AssistManager assistManager = this.mAssistManager;
        if (assistManager != null) {
            assistManager.showDisclosure();
        }
    }

    public NotificationPanelView getPanel() {
        return this.mNotificationPanel;
    }

    public void startAssist(Bundle bundle) {
        AssistManager assistManager = this.mAssistManager;
        if (assistManager != null) {
            assistManager.startAssist(bundle);
        }
    }

    public NotificationGutsManager getGutsManager() {
        return this.mGutsManager;
    }

    public int getStatusBarMode() {
        return this.mStatusBarMode;
    }

    /* access modifiers changed from: private */
    public RegisterStatusBarResult getRegisterStatusBarResult() {
        try {
            return this.mBarService.registerStatusBar(this.mCommandQueue);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void updateNavigationBar(RegisterStatusBarResult registerStatusBarResult, boolean z) {
        boolean deviceSupportNavigationBar = Utils.deviceSupportNavigationBar(this.mContext);
        if (z) {
            if (deviceSupportNavigationBar) {
                this.mNavigationBarController.createNavigationBars(true, registerStatusBarResult);
            }
        } else if (deviceSupportNavigationBar != this.mShowNavBar) {
            if (deviceSupportNavigationBar) {
                this.mNavigationBarController.recreateNavigationBars(true, registerStatusBarResult, this.mNavigationBarSystemUiVisibility);
            } else {
                this.mNavigationBarController.removeNavigationBar(this.mDisplayId);
            }
        }
        this.mShowNavBar = deviceSupportNavigationBar;
    }
}
