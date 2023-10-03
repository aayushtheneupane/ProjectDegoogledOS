package com.android.systemui;

import android.app.INotificationManager;
import android.hardware.SensorPrivacyManager;
import android.hardware.display.NightDisplayListener;
import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.view.IWindowManager;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.Preconditions;
import com.android.keyguard.clock.ClockManager;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.appops.AppOpsController;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.bubbles.BubbleController;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.dock.DockManager;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.power.PowerUI;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shared.plugins.PluginManager;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.DevicePolicyManagerWrapper;
import com.android.systemui.shared.system.PackageManagerWrapper;
import com.android.systemui.statusbar.NavigationBarController;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationViewHierarchyManager;
import com.android.systemui.statusbar.SmartReplyController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.notification.NotificationAlertingManager;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.NotificationFilter;
import com.android.systemui.statusbar.notification.NotificationInterruptionStateProvider;
import com.android.systemui.statusbar.notification.VisualStabilityManager;
import com.android.systemui.statusbar.notification.collection.NotificationData;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.row.ChannelEditorDialogController;
import com.android.systemui.statusbar.notification.row.NotificationBlockingHelperManager;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.LockscreenGestureLogger;
import com.android.systemui.statusbar.phone.ManagedProfileController;
import com.android.systemui.statusbar.phone.NavigationModeController;
import com.android.systemui.statusbar.phone.NotificationGroupAlertTransferHelper;
import com.android.systemui.statusbar.phone.NotificationGroupManager;
import com.android.systemui.statusbar.phone.ShadeController;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.phone.StatusBarWindowController;
import com.android.systemui.statusbar.policy.AccessibilityController;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DataSaverController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.ExtensionController;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.PulseController;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisabler;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.statusbar.policy.SecurityController;
import com.android.systemui.statusbar.policy.SensorPrivacyController;
import com.android.systemui.statusbar.policy.SmartReplyConstants;
import com.android.systemui.statusbar.policy.TaskHelper;
import com.android.systemui.statusbar.policy.UserInfoController;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.tuner.TunablePadding;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.AsyncSensorManager;
import com.android.systemui.util.leak.GarbageMonitor;
import com.android.systemui.util.leak.LeakDetector;
import com.android.systemui.util.leak.LeakReporter;
import dagger.Lazy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Dependency {
    public static final DependencyKey<Handler> BG_HANDLER = new DependencyKey<>("background_handler");
    public static final DependencyKey<Looper> BG_LOOPER = new DependencyKey<>("background_looper");
    public static final DependencyKey<String> LEAK_REPORT_EMAIL = new DependencyKey<>("leak_report_email");
    public static final DependencyKey<Handler> MAIN_HANDLER = new DependencyKey<>("main_handler");
    public static final DependencyKey<Handler> TIME_TICK_HANDLER = new DependencyKey<>("time_tick_handler");
    private static Dependency sDependency;
    Lazy<AccessibilityController> mAccessibilityController;
    Lazy<AccessibilityManagerWrapper> mAccessibilityManagerWrapper;
    Lazy<ActivityManagerWrapper> mActivityManagerWrapper;
    Lazy<ActivityStarter> mActivityStarter;
    Lazy<ActivityStarterDelegate> mActivityStarterDelegate;
    Lazy<AppOpsController> mAppOpsController;
    Lazy<AssistManager> mAssistManager;
    Lazy<AsyncSensorManager> mAsyncSensorManager;
    Lazy<AutoHideController> mAutoHideController;
    Lazy<BatteryController> mBatteryController;
    Lazy<Handler> mBgHandler;
    Lazy<Looper> mBgLooper;
    Lazy<BluetoothController> mBluetoothController;
    Lazy<BubbleController> mBubbleController;
    Lazy<CastController> mCastController;
    Lazy<ChannelEditorDialogController> mChannelEditorDialogController;
    Lazy<ClockManager> mClockManager;
    Lazy<ConfigurationController> mConfigurationController;
    Lazy<CustomSettingsService> mCustomSettingsService;
    Lazy<DarkIconDispatcher> mDarkIconDispatcher;
    Lazy<DataSaverController> mDataSaverController;
    private final ArrayMap<Object, Object> mDependencies = new ArrayMap<>();
    Lazy<DevicePolicyManagerWrapper> mDevicePolicyManagerWrapper;
    Lazy<DeviceProvisionedController> mDeviceProvisionedController;
    Lazy<DisplayMetrics> mDisplayMetrics;
    Lazy<DockManager> mDockManager;
    Lazy<DumpController> mDumpController;
    Lazy<EnhancedEstimates> mEnhancedEstimates;
    Lazy<ExtensionController> mExtensionController;
    Lazy<FalsingManager> mFalsingManager;
    Lazy<FlashlightController> mFlashlightController;
    Lazy<ForegroundServiceController> mForegroundServiceController;
    Lazy<ForegroundServiceNotificationListener> mForegroundServiceNotificationListener;
    Lazy<FragmentService> mFragmentService;
    Lazy<GarbageMonitor> mGarbageMonitor;
    Lazy<HotspotController> mHotspotController;
    Lazy<INotificationManager> mINotificationManager;
    Lazy<IStatusBarService> mIStatusBarService;
    Lazy<IWindowManager> mIWindowManager;
    Lazy<InitController> mInitController;
    Lazy<KeyguardDismissUtil> mKeyguardDismissUtil;
    Lazy<NotificationData.KeyguardEnvironment> mKeyguardEnvironment;
    Lazy<KeyguardMonitor> mKeyguardMonitor;
    Lazy<LeakDetector> mLeakDetector;
    Lazy<String> mLeakReportEmail;
    Lazy<LeakReporter> mLeakReporter;
    Lazy<LightBarController> mLightBarController;
    Lazy<LocalBluetoothManager> mLocalBluetoothManager;
    Lazy<LocationController> mLocationController;
    Lazy<LockscreenGestureLogger> mLockscreenGestureLogger;
    Lazy<Handler> mMainHandler;
    Lazy<ManagedProfileController> mManagedProfileController;
    Lazy<MetricsLogger> mMetricsLogger;
    Lazy<NavigationModeController> mNavBarModeController;
    Lazy<NavigationBarController> mNavigationBarController;
    Lazy<NetworkController> mNetworkController;
    Lazy<NextAlarmController> mNextAlarmController;
    Lazy<NightDisplayListener> mNightDisplayListener;
    Lazy<NotificationAlertingManager> mNotificationAlertingManager;
    Lazy<NotificationBlockingHelperManager> mNotificationBlockingHelperManager;
    Lazy<NotificationEntryManager> mNotificationEntryManager;
    Lazy<NotificationFilter> mNotificationFilter;
    Lazy<NotificationGroupAlertTransferHelper> mNotificationGroupAlertTransferHelper;
    Lazy<NotificationGroupManager> mNotificationGroupManager;
    Lazy<NotificationGutsManager> mNotificationGutsManager;
    Lazy<NotificationInterruptionStateProvider> mNotificationInterruptionStateProvider;
    Lazy<NotificationListener> mNotificationListener;
    Lazy<NotificationLockscreenUserManager> mNotificationLockscreenUserManager;
    Lazy<NotificationLogger> mNotificationLogger;
    Lazy<NotificationMediaManager> mNotificationMediaManager;
    Lazy<NotificationRemoteInputManager> mNotificationRemoteInputManager;
    Lazy<NotificationRemoteInputManager.Callback> mNotificationRemoteInputManagerCallback;
    Lazy<NotificationViewHierarchyManager> mNotificationViewHierarchyManager;
    Lazy<OverviewProxyService> mOverviewProxyService;
    Lazy<PackageManagerWrapper> mPackageManagerWrapper;
    Lazy<PluginDependencyProvider> mPluginDependencyProvider;
    Lazy<PluginManager> mPluginManager;
    Lazy<PrivacyItemController> mPrivacyItemController;
    private final ArrayMap<Object, LazyDependencyCreator> mProviders = new ArrayMap<>();
    Lazy<PulseController> mPulseController;
    Lazy<RemoteInputQuickSettingsDisabler> mRemoteInputQuickSettingsDisabler;
    Lazy<RotationLockController> mRotationLockController;
    Lazy<ScreenLifecycle> mScreenLifecycle;
    Lazy<SecurityController> mSecurityController;
    Lazy<SensorPrivacyController> mSensorPrivacyController;
    Lazy<SensorPrivacyManager> mSensorPrivacyManager;
    Lazy<ShadeController> mShadeController;
    Lazy<SmartReplyConstants> mSmartReplyConstants;
    Lazy<SmartReplyController> mSmartReplyController;
    Lazy<StatusBarIconController> mStatusBarIconController;
    Lazy<StatusBarStateController> mStatusBarStateController;
    Lazy<StatusBarWindowController> mStatusBarWindowController;
    Lazy<SysuiColorExtractor> mSysuiColorExtractor;
    Lazy<TaskHelper> mTaskHelper;
    Lazy<Handler> mTimeTickHandler;
    Lazy<TunablePadding.TunablePaddingService> mTunablePaddingService;
    Lazy<TunerService> mTunerService;
    Lazy<UiOffloadThread> mUiOffloadThread;
    Lazy<UserInfoController> mUserInfoController;
    Lazy<UserSwitcherController> mUserSwitcherController;
    Lazy<VibratorHelper> mVibratorHelper;
    Lazy<VisualStabilityManager> mVisualStabilityManager;
    Lazy<VolumeDialogController> mVolumeDialogController;
    Lazy<WakefulnessLifecycle> mWakefulnessLifecycle;
    Lazy<PowerUI.WarningsUI> mWarningsUI;
    Lazy<ZenModeController> mZenModeController;

    public interface DependencyInjector {
        void createSystemUI(Dependency dependency);
    }

    private interface LazyDependencyCreator<T> {
        T createDependency();
    }

    public static void initDependencies(SystemUIRootComponent systemUIRootComponent) {
        if (sDependency == null) {
            sDependency = new Dependency();
            systemUIRootComponent.createDependency().createSystemUI(sDependency);
            sDependency.start();
        }
    }

    /* access modifiers changed from: protected */
    public void start() {
        ArrayMap<Object, LazyDependencyCreator> arrayMap = this.mProviders;
        DependencyKey<Handler> dependencyKey = TIME_TICK_HANDLER;
        Lazy<Handler> lazy = this.mTimeTickHandler;
        Objects.requireNonNull(lazy);
        arrayMap.put(dependencyKey, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        ArrayMap<Object, LazyDependencyCreator> arrayMap2 = this.mProviders;
        DependencyKey<Looper> dependencyKey2 = BG_LOOPER;
        Lazy<Looper> lazy2 = this.mBgLooper;
        Objects.requireNonNull(lazy2);
        arrayMap2.put(dependencyKey2, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        ArrayMap<Object, LazyDependencyCreator> arrayMap3 = this.mProviders;
        DependencyKey<Handler> dependencyKey3 = BG_HANDLER;
        Lazy<Handler> lazy3 = this.mBgHandler;
        Objects.requireNonNull(lazy3);
        arrayMap3.put(dependencyKey3, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        ArrayMap<Object, LazyDependencyCreator> arrayMap4 = this.mProviders;
        DependencyKey<Handler> dependencyKey4 = MAIN_HANDLER;
        Lazy<Handler> lazy4 = this.mMainHandler;
        Objects.requireNonNull(lazy4);
        arrayMap4.put(dependencyKey4, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ActivityStarter> lazy5 = this.mActivityStarter;
        Objects.requireNonNull(lazy5);
        this.mProviders.put(ActivityStarter.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ActivityStarterDelegate> lazy6 = this.mActivityStarterDelegate;
        Objects.requireNonNull(lazy6);
        this.mProviders.put(ActivityStarterDelegate.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<AsyncSensorManager> lazy7 = this.mAsyncSensorManager;
        Objects.requireNonNull(lazy7);
        this.mProviders.put(AsyncSensorManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<BluetoothController> lazy8 = this.mBluetoothController;
        Objects.requireNonNull(lazy8);
        this.mProviders.put(BluetoothController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<SensorPrivacyManager> lazy9 = this.mSensorPrivacyManager;
        Objects.requireNonNull(lazy9);
        this.mProviders.put(SensorPrivacyManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<LocationController> lazy10 = this.mLocationController;
        Objects.requireNonNull(lazy10);
        this.mProviders.put(LocationController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<RotationLockController> lazy11 = this.mRotationLockController;
        Objects.requireNonNull(lazy11);
        this.mProviders.put(RotationLockController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NetworkController> lazy12 = this.mNetworkController;
        Objects.requireNonNull(lazy12);
        this.mProviders.put(NetworkController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ZenModeController> lazy13 = this.mZenModeController;
        Objects.requireNonNull(lazy13);
        this.mProviders.put(ZenModeController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<HotspotController> lazy14 = this.mHotspotController;
        Objects.requireNonNull(lazy14);
        this.mProviders.put(HotspotController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<CastController> lazy15 = this.mCastController;
        Objects.requireNonNull(lazy15);
        this.mProviders.put(CastController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<FlashlightController> lazy16 = this.mFlashlightController;
        Objects.requireNonNull(lazy16);
        this.mProviders.put(FlashlightController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<KeyguardMonitor> lazy17 = this.mKeyguardMonitor;
        Objects.requireNonNull(lazy17);
        this.mProviders.put(KeyguardMonitor.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<UserSwitcherController> lazy18 = this.mUserSwitcherController;
        Objects.requireNonNull(lazy18);
        this.mProviders.put(UserSwitcherController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<UserInfoController> lazy19 = this.mUserInfoController;
        Objects.requireNonNull(lazy19);
        this.mProviders.put(UserInfoController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<BatteryController> lazy20 = this.mBatteryController;
        Objects.requireNonNull(lazy20);
        this.mProviders.put(BatteryController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NightDisplayListener> lazy21 = this.mNightDisplayListener;
        Objects.requireNonNull(lazy21);
        this.mProviders.put(NightDisplayListener.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ManagedProfileController> lazy22 = this.mManagedProfileController;
        Objects.requireNonNull(lazy22);
        this.mProviders.put(ManagedProfileController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NextAlarmController> lazy23 = this.mNextAlarmController;
        Objects.requireNonNull(lazy23);
        this.mProviders.put(NextAlarmController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<DataSaverController> lazy24 = this.mDataSaverController;
        Objects.requireNonNull(lazy24);
        this.mProviders.put(DataSaverController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<AccessibilityController> lazy25 = this.mAccessibilityController;
        Objects.requireNonNull(lazy25);
        this.mProviders.put(AccessibilityController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<DeviceProvisionedController> lazy26 = this.mDeviceProvisionedController;
        Objects.requireNonNull(lazy26);
        this.mProviders.put(DeviceProvisionedController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<PluginManager> lazy27 = this.mPluginManager;
        Objects.requireNonNull(lazy27);
        this.mProviders.put(PluginManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<AssistManager> lazy28 = this.mAssistManager;
        Objects.requireNonNull(lazy28);
        this.mProviders.put(AssistManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<SecurityController> lazy29 = this.mSecurityController;
        Objects.requireNonNull(lazy29);
        this.mProviders.put(SecurityController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<LeakDetector> lazy30 = this.mLeakDetector;
        Objects.requireNonNull(lazy30);
        this.mProviders.put(LeakDetector.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        ArrayMap<Object, LazyDependencyCreator> arrayMap5 = this.mProviders;
        DependencyKey<String> dependencyKey5 = LEAK_REPORT_EMAIL;
        Lazy<String> lazy31 = this.mLeakReportEmail;
        Objects.requireNonNull(lazy31);
        arrayMap5.put(dependencyKey5, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<LeakReporter> lazy32 = this.mLeakReporter;
        Objects.requireNonNull(lazy32);
        this.mProviders.put(LeakReporter.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<GarbageMonitor> lazy33 = this.mGarbageMonitor;
        Objects.requireNonNull(lazy33);
        this.mProviders.put(GarbageMonitor.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<TunerService> lazy34 = this.mTunerService;
        Objects.requireNonNull(lazy34);
        this.mProviders.put(TunerService.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<StatusBarWindowController> lazy35 = this.mStatusBarWindowController;
        Objects.requireNonNull(lazy35);
        this.mProviders.put(StatusBarWindowController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<DarkIconDispatcher> lazy36 = this.mDarkIconDispatcher;
        Objects.requireNonNull(lazy36);
        this.mProviders.put(DarkIconDispatcher.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ConfigurationController> lazy37 = this.mConfigurationController;
        Objects.requireNonNull(lazy37);
        this.mProviders.put(ConfigurationController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<StatusBarIconController> lazy38 = this.mStatusBarIconController;
        Objects.requireNonNull(lazy38);
        this.mProviders.put(StatusBarIconController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ScreenLifecycle> lazy39 = this.mScreenLifecycle;
        Objects.requireNonNull(lazy39);
        this.mProviders.put(ScreenLifecycle.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<WakefulnessLifecycle> lazy40 = this.mWakefulnessLifecycle;
        Objects.requireNonNull(lazy40);
        this.mProviders.put(WakefulnessLifecycle.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<FragmentService> lazy41 = this.mFragmentService;
        Objects.requireNonNull(lazy41);
        this.mProviders.put(FragmentService.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ExtensionController> lazy42 = this.mExtensionController;
        Objects.requireNonNull(lazy42);
        this.mProviders.put(ExtensionController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<PluginDependencyProvider> lazy43 = this.mPluginDependencyProvider;
        Objects.requireNonNull(lazy43);
        this.mProviders.put(PluginDependencyProvider.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<LocalBluetoothManager> lazy44 = this.mLocalBluetoothManager;
        Objects.requireNonNull(lazy44);
        this.mProviders.put(LocalBluetoothManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<VolumeDialogController> lazy45 = this.mVolumeDialogController;
        Objects.requireNonNull(lazy45);
        this.mProviders.put(VolumeDialogController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<MetricsLogger> lazy46 = this.mMetricsLogger;
        Objects.requireNonNull(lazy46);
        this.mProviders.put(MetricsLogger.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<AccessibilityManagerWrapper> lazy47 = this.mAccessibilityManagerWrapper;
        Objects.requireNonNull(lazy47);
        this.mProviders.put(AccessibilityManagerWrapper.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<SysuiColorExtractor> lazy48 = this.mSysuiColorExtractor;
        Objects.requireNonNull(lazy48);
        this.mProviders.put(SysuiColorExtractor.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<TunablePadding.TunablePaddingService> lazy49 = this.mTunablePaddingService;
        Objects.requireNonNull(lazy49);
        this.mProviders.put(TunablePadding.TunablePaddingService.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ForegroundServiceController> lazy50 = this.mForegroundServiceController;
        Objects.requireNonNull(lazy50);
        this.mProviders.put(ForegroundServiceController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<UiOffloadThread> lazy51 = this.mUiOffloadThread;
        Objects.requireNonNull(lazy51);
        this.mProviders.put(UiOffloadThread.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<PowerUI.WarningsUI> lazy52 = this.mWarningsUI;
        Objects.requireNonNull(lazy52);
        this.mProviders.put(PowerUI.WarningsUI.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<LightBarController> lazy53 = this.mLightBarController;
        Objects.requireNonNull(lazy53);
        this.mProviders.put(LightBarController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<IWindowManager> lazy54 = this.mIWindowManager;
        Objects.requireNonNull(lazy54);
        this.mProviders.put(IWindowManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<OverviewProxyService> lazy55 = this.mOverviewProxyService;
        Objects.requireNonNull(lazy55);
        this.mProviders.put(OverviewProxyService.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NavigationModeController> lazy56 = this.mNavBarModeController;
        Objects.requireNonNull(lazy56);
        this.mProviders.put(NavigationModeController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<EnhancedEstimates> lazy57 = this.mEnhancedEstimates;
        Objects.requireNonNull(lazy57);
        this.mProviders.put(EnhancedEstimates.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<VibratorHelper> lazy58 = this.mVibratorHelper;
        Objects.requireNonNull(lazy58);
        this.mProviders.put(VibratorHelper.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<IStatusBarService> lazy59 = this.mIStatusBarService;
        Objects.requireNonNull(lazy59);
        this.mProviders.put(IStatusBarService.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<DisplayMetrics> lazy60 = this.mDisplayMetrics;
        Objects.requireNonNull(lazy60);
        this.mProviders.put(DisplayMetrics.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<LockscreenGestureLogger> lazy61 = this.mLockscreenGestureLogger;
        Objects.requireNonNull(lazy61);
        this.mProviders.put(LockscreenGestureLogger.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationData.KeyguardEnvironment> lazy62 = this.mKeyguardEnvironment;
        Objects.requireNonNull(lazy62);
        this.mProviders.put(NotificationData.KeyguardEnvironment.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ShadeController> lazy63 = this.mShadeController;
        Objects.requireNonNull(lazy63);
        this.mProviders.put(ShadeController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationRemoteInputManager.Callback> lazy64 = this.mNotificationRemoteInputManagerCallback;
        Objects.requireNonNull(lazy64);
        this.mProviders.put(NotificationRemoteInputManager.Callback.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<InitController> lazy65 = this.mInitController;
        Objects.requireNonNull(lazy65);
        this.mProviders.put(InitController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<AppOpsController> lazy66 = this.mAppOpsController;
        Objects.requireNonNull(lazy66);
        this.mProviders.put(AppOpsController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NavigationBarController> lazy67 = this.mNavigationBarController;
        Objects.requireNonNull(lazy67);
        this.mProviders.put(NavigationBarController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<StatusBarStateController> lazy68 = this.mStatusBarStateController;
        Objects.requireNonNull(lazy68);
        this.mProviders.put(StatusBarStateController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationLockscreenUserManager> lazy69 = this.mNotificationLockscreenUserManager;
        Objects.requireNonNull(lazy69);
        this.mProviders.put(NotificationLockscreenUserManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<VisualStabilityManager> lazy70 = this.mVisualStabilityManager;
        Objects.requireNonNull(lazy70);
        this.mProviders.put(VisualStabilityManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationGroupManager> lazy71 = this.mNotificationGroupManager;
        Objects.requireNonNull(lazy71);
        this.mProviders.put(NotificationGroupManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationGroupAlertTransferHelper> lazy72 = this.mNotificationGroupAlertTransferHelper;
        Objects.requireNonNull(lazy72);
        this.mProviders.put(NotificationGroupAlertTransferHelper.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationMediaManager> lazy73 = this.mNotificationMediaManager;
        Objects.requireNonNull(lazy73);
        this.mProviders.put(NotificationMediaManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationGutsManager> lazy74 = this.mNotificationGutsManager;
        Objects.requireNonNull(lazy74);
        this.mProviders.put(NotificationGutsManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationBlockingHelperManager> lazy75 = this.mNotificationBlockingHelperManager;
        Objects.requireNonNull(lazy75);
        this.mProviders.put(NotificationBlockingHelperManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationRemoteInputManager> lazy76 = this.mNotificationRemoteInputManager;
        Objects.requireNonNull(lazy76);
        this.mProviders.put(NotificationRemoteInputManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<SmartReplyConstants> lazy77 = this.mSmartReplyConstants;
        Objects.requireNonNull(lazy77);
        this.mProviders.put(SmartReplyConstants.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationListener> lazy78 = this.mNotificationListener;
        Objects.requireNonNull(lazy78);
        this.mProviders.put(NotificationListener.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationLogger> lazy79 = this.mNotificationLogger;
        Objects.requireNonNull(lazy79);
        this.mProviders.put(NotificationLogger.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationViewHierarchyManager> lazy80 = this.mNotificationViewHierarchyManager;
        Objects.requireNonNull(lazy80);
        this.mProviders.put(NotificationViewHierarchyManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationFilter> lazy81 = this.mNotificationFilter;
        Objects.requireNonNull(lazy81);
        this.mProviders.put(NotificationFilter.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationInterruptionStateProvider> lazy82 = this.mNotificationInterruptionStateProvider;
        Objects.requireNonNull(lazy82);
        this.mProviders.put(NotificationInterruptionStateProvider.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<KeyguardDismissUtil> lazy83 = this.mKeyguardDismissUtil;
        Objects.requireNonNull(lazy83);
        this.mProviders.put(KeyguardDismissUtil.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<SmartReplyController> lazy84 = this.mSmartReplyController;
        Objects.requireNonNull(lazy84);
        this.mProviders.put(SmartReplyController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<RemoteInputQuickSettingsDisabler> lazy85 = this.mRemoteInputQuickSettingsDisabler;
        Objects.requireNonNull(lazy85);
        this.mProviders.put(RemoteInputQuickSettingsDisabler.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<BubbleController> lazy86 = this.mBubbleController;
        Objects.requireNonNull(lazy86);
        this.mProviders.put(BubbleController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationEntryManager> lazy87 = this.mNotificationEntryManager;
        Objects.requireNonNull(lazy87);
        this.mProviders.put(NotificationEntryManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<NotificationAlertingManager> lazy88 = this.mNotificationAlertingManager;
        Objects.requireNonNull(lazy88);
        this.mProviders.put(NotificationAlertingManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ForegroundServiceNotificationListener> lazy89 = this.mForegroundServiceNotificationListener;
        Objects.requireNonNull(lazy89);
        this.mProviders.put(ForegroundServiceNotificationListener.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ClockManager> lazy90 = this.mClockManager;
        Objects.requireNonNull(lazy90);
        this.mProviders.put(ClockManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<PrivacyItemController> lazy91 = this.mPrivacyItemController;
        Objects.requireNonNull(lazy91);
        this.mProviders.put(PrivacyItemController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ActivityManagerWrapper> lazy92 = this.mActivityManagerWrapper;
        Objects.requireNonNull(lazy92);
        this.mProviders.put(ActivityManagerWrapper.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<DevicePolicyManagerWrapper> lazy93 = this.mDevicePolicyManagerWrapper;
        Objects.requireNonNull(lazy93);
        this.mProviders.put(DevicePolicyManagerWrapper.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<PackageManagerWrapper> lazy94 = this.mPackageManagerWrapper;
        Objects.requireNonNull(lazy94);
        this.mProviders.put(PackageManagerWrapper.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<SensorPrivacyController> lazy95 = this.mSensorPrivacyController;
        Objects.requireNonNull(lazy95);
        this.mProviders.put(SensorPrivacyController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<DumpController> lazy96 = this.mDumpController;
        Objects.requireNonNull(lazy96);
        this.mProviders.put(DumpController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<DockManager> lazy97 = this.mDockManager;
        Objects.requireNonNull(lazy97);
        this.mProviders.put(DockManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<ChannelEditorDialogController> lazy98 = this.mChannelEditorDialogController;
        Objects.requireNonNull(lazy98);
        this.mProviders.put(ChannelEditorDialogController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<INotificationManager> lazy99 = this.mINotificationManager;
        Objects.requireNonNull(lazy99);
        this.mProviders.put(INotificationManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<FalsingManager> lazy100 = this.mFalsingManager;
        Objects.requireNonNull(lazy100);
        this.mProviders.put(FalsingManager.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<PulseController> lazy101 = this.mPulseController;
        Objects.requireNonNull(lazy101);
        this.mProviders.put(PulseController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<TaskHelper> lazy102 = this.mTaskHelper;
        Objects.requireNonNull(lazy102);
        this.mProviders.put(TaskHelper.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<AutoHideController> lazy103 = this.mAutoHideController;
        Objects.requireNonNull(lazy103);
        this.mProviders.put(AutoHideController.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        Lazy<CustomSettingsService> lazy104 = this.mCustomSettingsService;
        Objects.requireNonNull(lazy104);
        this.mProviders.put(CustomSettingsService.class, new LazyDependencyCreator() {
            public final Object createDependency() {
                return Lazy.this.get();
            }
        });
        sDependency = this;
    }

    static void staticDump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        sDependency.dump(fileDescriptor, printWriter, strArr);
    }

    public synchronized void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        getDependency(DumpController.class);
        String lowerCase = (strArr == null || strArr.length <= 1) ? null : strArr[1].toLowerCase();
        if (lowerCase != null) {
            printWriter.println("Dumping controller=" + lowerCase + ":");
        } else {
            printWriter.println("Dumping existing controllers:");
        }
        this.mDependencies.values().stream().filter(new Predicate(lowerCase) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final boolean test(Object obj) {
                return Dependency.lambda$dump$0(this.f$0, obj);
            }
        }).forEach(new Consumer(fileDescriptor, printWriter, strArr) {
            private final /* synthetic */ FileDescriptor f$0;
            private final /* synthetic */ PrintWriter f$1;
            private final /* synthetic */ String[] f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void accept(Object obj) {
                ((Dumpable) obj).dump(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    static /* synthetic */ boolean lambda$dump$0(String str, Object obj) {
        return (obj instanceof Dumpable) && (str == null || obj.getClass().getName().toLowerCase().endsWith(str));
    }

    /* access modifiers changed from: protected */
    public final <T> T getDependency(Class<T> cls) {
        return getDependencyInner(cls);
    }

    /* access modifiers changed from: protected */
    public final <T> T getDependency(DependencyKey<T> dependencyKey) {
        return getDependencyInner(dependencyKey);
    }

    private synchronized <T> T getDependencyInner(Object obj) {
        T t;
        t = this.mDependencies.get(obj);
        if (t == null) {
            t = createDependency(obj);
            this.mDependencies.put(obj, t);
        }
        return t;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public <T> T createDependency(Object obj) {
        Preconditions.checkArgument((obj instanceof DependencyKey) || (obj instanceof Class));
        LazyDependencyCreator lazyDependencyCreator = this.mProviders.get(obj);
        if (lazyDependencyCreator != null) {
            return lazyDependencyCreator.createDependency();
        }
        throw new IllegalArgumentException("Unsupported dependency " + obj + ". " + this.mProviders.size() + " providers known.");
    }

    private <T> void destroyDependency(Class<T> cls, Consumer<T> consumer) {
        Object remove = this.mDependencies.remove(cls);
        if (remove != null && consumer != null) {
            consumer.accept(remove);
        }
    }

    public static void clearDependencies() {
        sDependency = null;
    }

    public static <T> void destroy(Class<T> cls, Consumer<T> consumer) {
        sDependency.destroyDependency(cls, consumer);
    }

    @Deprecated
    public static <T> T get(Class<T> cls) {
        return sDependency.getDependency(cls);
    }

    @Deprecated
    public static <T> T get(DependencyKey<T> dependencyKey) {
        return sDependency.getDependency(dependencyKey);
    }

    public static final class DependencyKey<V> {
        private final String mDisplayName;

        public DependencyKey(String str) {
            this.mDisplayName = str;
        }

        public String toString() {
            return this.mDisplayName;
        }
    }
}
