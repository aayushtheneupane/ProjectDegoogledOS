package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.notification.BypassHeadsUpNotifier;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.util.InjectionInflationController;
import dagger.MembersInjector;

public final class StatusBar_MembersInjector implements MembersInjector<StatusBar> {
    public static void injectMInjectionInflater(StatusBar statusBar, InjectionInflationController injectionInflationController) {
        statusBar.mInjectionInflater = injectionInflationController;
    }

    public static void injectMPulseExpansionHandler(StatusBar statusBar, PulseExpansionHandler pulseExpansionHandler) {
        statusBar.mPulseExpansionHandler = pulseExpansionHandler;
    }

    public static void injectMWakeUpCoordinator(StatusBar statusBar, NotificationWakeUpCoordinator notificationWakeUpCoordinator) {
        statusBar.mWakeUpCoordinator = notificationWakeUpCoordinator;
    }

    public static void injectMKeyguardBypassController(StatusBar statusBar, KeyguardBypassController keyguardBypassController) {
        statusBar.mKeyguardBypassController = keyguardBypassController;
    }

    public static void injectMHeadsUpManager(StatusBar statusBar, HeadsUpManagerPhone headsUpManagerPhone) {
        statusBar.mHeadsUpManager = headsUpManagerPhone;
    }

    public static void injectMDynamicPrivacyController(StatusBar statusBar, DynamicPrivacyController dynamicPrivacyController) {
        statusBar.mDynamicPrivacyController = dynamicPrivacyController;
    }

    public static void injectMBypassHeadsUpNotifier(StatusBar statusBar, BypassHeadsUpNotifier bypassHeadsUpNotifier) {
        statusBar.mBypassHeadsUpNotifier = bypassHeadsUpNotifier;
    }

    public static void injectMKeyguardLiftController(StatusBar statusBar, KeyguardLiftController keyguardLiftController) {
        statusBar.mKeyguardLiftController = keyguardLiftController;
    }

    public static void injectMAllowNotificationLongPress(StatusBar statusBar, boolean z) {
        statusBar.mAllowNotificationLongPress = z;
    }
}
