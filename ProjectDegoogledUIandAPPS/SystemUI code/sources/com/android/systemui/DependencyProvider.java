package com.android.systemui;

import android.app.INotificationManager;
import android.content.Context;
import android.hardware.SensorPrivacyManager;
import android.hardware.display.NightDisplayListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.DisplayMetrics;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.navigation.pulse.PulseControllerImpl;
import com.android.systemui.plugins.PluginInitializerImpl;
import com.android.systemui.shared.plugins.PluginManager;
import com.android.systemui.shared.plugins.PluginManagerImpl;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.DevicePolicyManagerWrapper;
import com.android.systemui.shared.system.PackageManagerWrapper;
import com.android.systemui.statusbar.NavigationBarController;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DataSaverController;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.PulseController;
import com.android.systemui.statusbar.policy.TaskHelper;
import com.android.systemui.util.leak.LeakDetector;

public class DependencyProvider {
    public Handler provideHandler() {
        HandlerThread handlerThread = new HandlerThread("TimeTick");
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }

    public Looper provideBgLooper() {
        HandlerThread handlerThread = new HandlerThread("SysUiBg", 10);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    public Handler provideBgHandler(Looper looper) {
        return new Handler(looper);
    }

    public Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    public DataSaverController provideDataSaverController(NetworkController networkController) {
        return networkController.getDataSaverController();
    }

    public LocalBluetoothManager provideLocalBluetoothController(Context context, Handler handler) {
        return LocalBluetoothManager.create(context, handler, UserHandle.ALL);
    }

    public MetricsLogger provideMetricsLogger() {
        return new MetricsLogger();
    }

    public IWindowManager provideIWindowManager() {
        return WindowManagerGlobal.getWindowManagerService();
    }

    public IStatusBarService provideIStatusBarService() {
        return IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
    }

    public INotificationManager provideINotificationManager() {
        return INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    }

    public DisplayMetrics provideDisplayMetrics() {
        return new DisplayMetrics();
    }

    public SensorPrivacyManager provideSensorPrivacyManager(Context context) {
        return (SensorPrivacyManager) context.getSystemService(SensorPrivacyManager.class);
    }

    public LeakDetector provideLeakDetector() {
        return LeakDetector.create();
    }

    public NightDisplayListener provideNightDisplayListener(Context context, Handler handler) {
        return new NightDisplayListener(context, handler);
    }

    public PluginManager providePluginManager(Context context) {
        return new PluginManagerImpl(context, new PluginInitializerImpl());
    }

    public NavigationBarController provideNavigationBarController(Context context, Handler handler) {
        return new NavigationBarController(context, handler);
    }

    public ConfigurationController provideConfigurationController(Context context) {
        return new ConfigurationControllerImpl(context);
    }

    public AutoHideController provideAutoHideController(Context context, Handler handler) {
        return new AutoHideController(context, handler);
    }

    public ActivityManagerWrapper provideActivityManagerWrapper() {
        return ActivityManagerWrapper.getInstance();
    }

    public DevicePolicyManagerWrapper provideDevicePolicyManagerWrapper() {
        return DevicePolicyManagerWrapper.getInstance();
    }

    public PackageManagerWrapper providePackageManagerWrapper() {
        return PackageManagerWrapper.getInstance();
    }

    public PulseController providePulseController(Context context, Handler handler) {
        return new PulseControllerImpl(context, handler);
    }

    public TaskHelper provideTaskHelper(Context context, Handler handler) {
        return new TaskHelper(context, handler);
    }
}
