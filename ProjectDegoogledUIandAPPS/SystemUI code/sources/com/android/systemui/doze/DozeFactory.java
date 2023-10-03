package com.android.systemui.doze;

import android.app.AlarmManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.Handler;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.dock.DockManager;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.util.AsyncSensorManager;
import com.android.systemui.util.wakelock.DelayedWakeLock;
import com.android.systemui.util.wakelock.WakeLock;

public class DozeFactory {
    public DozeMachine assembleMachine(DozeService dozeService, FalsingManager falsingManager) {
        DozeService dozeService2 = dozeService;
        SensorManager sensorManager = (SensorManager) Dependency.get(AsyncSensorManager.class);
        AlarmManager alarmManager = (AlarmManager) dozeService2.getSystemService(AlarmManager.class);
        DockManager dockManager = (DockManager) Dependency.get(DockManager.class);
        DozeHost host = getHost(dozeService);
        AmbientDisplayConfiguration ambientDisplayConfiguration = new AmbientDisplayConfiguration(dozeService2);
        DozeParameters instance = DozeParameters.getInstance(dozeService);
        Handler handler = new Handler();
        DelayedWakeLock delayedWakeLock = new DelayedWakeLock(handler, WakeLock.createPartial(dozeService2, "Doze"));
        DozeMachine.Service wrapIfNeeded = DozeSuspendScreenStatePreventingAdapter.wrapIfNeeded(DozeScreenStatePreventingAdapter.wrapIfNeeded(new DozeBrightnessHostForwarder(dozeService2, host), instance), instance);
        DozeMachine dozeMachine = r1;
        DozeMachine dozeMachine2 = new DozeMachine(wrapIfNeeded, ambientDisplayConfiguration, delayedWakeLock, (WakefulnessLifecycle) Dependency.get(WakefulnessLifecycle.class), (BatteryController) Dependency.get(BatteryController.class));
        DozeMachine.Part[] partArr = new DozeMachine.Part[9];
        partArr[0] = new DozePauser(handler, dozeMachine, alarmManager, instance.getPolicy());
        partArr[1] = new DozeFalsingManagerAdapter(falsingManager);
        SensorManager sensorManager2 = sensorManager;
        DozeMachine.Service service = wrapIfNeeded;
        DozeMachine.Part[] partArr2 = partArr;
        DelayedWakeLock delayedWakeLock2 = delayedWakeLock;
        Handler handler2 = handler;
        DozeParameters dozeParameters = instance;
        DozeMachine dozeMachine3 = dozeMachine;
        partArr2[2] = createDozeTriggers(dozeService, sensorManager, host, alarmManager, ambientDisplayConfiguration, instance, handler, delayedWakeLock2, dozeMachine3, dockManager);
        partArr2[3] = createDozeUi(dozeService, host, delayedWakeLock2, dozeMachine, handler2, alarmManager, dozeParameters);
        DozeMachine.Service service2 = service;
        Handler handler3 = handler2;
        DozeParameters dozeParameters2 = dozeParameters;
        partArr2[4] = new DozeScreenState(service2, handler3, dozeParameters2, delayedWakeLock2);
        partArr2[5] = createDozeScreenBrightness(dozeService, service2, sensorManager2, host, dozeParameters2, handler3);
        DozeService dozeService3 = dozeService;
        partArr2[6] = new DozeWallpaperState(dozeService3, getBiometricUnlockController(dozeService));
        partArr2[7] = new DozeDockHandler(dozeService, dozeMachine3, host, ambientDisplayConfiguration, handler3, dockManager);
        partArr2[8] = new DozeAuthRemover(dozeService3);
        dozeMachine3.setParts(partArr2);
        return dozeMachine3;
    }

    private DozeMachine.Part createDozeScreenBrightness(Context context, DozeMachine.Service service, SensorManager sensorManager, DozeHost dozeHost, DozeParameters dozeParameters, Handler handler) {
        return new DozeScreenBrightness(context, service, sensorManager, DozeSensors.findSensorWithType(sensorManager, context.getString(C1784R$string.doze_brightness_sensor_type)), dozeHost, handler, dozeParameters.getPolicy());
    }

    private DozeTriggers createDozeTriggers(Context context, SensorManager sensorManager, DozeHost dozeHost, AlarmManager alarmManager, AmbientDisplayConfiguration ambientDisplayConfiguration, DozeParameters dozeParameters, Handler handler, WakeLock wakeLock, DozeMachine dozeMachine, DockManager dockManager) {
        return new DozeTriggers(context, dozeMachine, dozeHost, alarmManager, ambientDisplayConfiguration, dozeParameters, sensorManager, handler, wakeLock, true, dockManager);
    }

    private DozeMachine.Part createDozeUi(Context context, DozeHost dozeHost, WakeLock wakeLock, DozeMachine dozeMachine, Handler handler, AlarmManager alarmManager, DozeParameters dozeParameters) {
        return new DozeUi(context, alarmManager, dozeMachine, wakeLock, dozeHost, handler, dozeParameters, KeyguardUpdateMonitor.getInstance(context));
    }

    public static DozeHost getHost(DozeService dozeService) {
        return (DozeHost) ((SystemUIApplication) dozeService.getApplication()).getComponent(DozeHost.class);
    }

    public static BiometricUnlockController getBiometricUnlockController(DozeService dozeService) {
        return (BiometricUnlockController) ((SystemUIApplication) dozeService.getApplication()).getComponent(BiometricUnlockController.class);
    }
}
