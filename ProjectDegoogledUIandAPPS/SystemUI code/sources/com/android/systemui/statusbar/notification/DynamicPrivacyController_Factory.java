package com.android.systemui.statusbar.notification;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DynamicPrivacyController_Factory implements Factory<DynamicPrivacyController> {
    private final Provider<Context> contextProvider;
    private final Provider<KeyguardMonitor> keyguardMonitorProvider;
    private final Provider<NotificationLockscreenUserManager> notificationLockscreenUserManagerProvider;
    private final Provider<StatusBarStateController> stateControllerProvider;

    public DynamicPrivacyController_Factory(Provider<Context> provider, Provider<KeyguardMonitor> provider2, Provider<NotificationLockscreenUserManager> provider3, Provider<StatusBarStateController> provider4) {
        this.contextProvider = provider;
        this.keyguardMonitorProvider = provider2;
        this.notificationLockscreenUserManagerProvider = provider3;
        this.stateControllerProvider = provider4;
    }

    public DynamicPrivacyController get() {
        return provideInstance(this.contextProvider, this.keyguardMonitorProvider, this.notificationLockscreenUserManagerProvider, this.stateControllerProvider);
    }

    public static DynamicPrivacyController provideInstance(Provider<Context> provider, Provider<KeyguardMonitor> provider2, Provider<NotificationLockscreenUserManager> provider3, Provider<StatusBarStateController> provider4) {
        return new DynamicPrivacyController(provider.get(), provider2.get(), provider3.get(), provider4.get());
    }

    public static DynamicPrivacyController_Factory create(Provider<Context> provider, Provider<KeyguardMonitor> provider2, Provider<NotificationLockscreenUserManager> provider3, Provider<StatusBarStateController> provider4) {
        return new DynamicPrivacyController_Factory(provider, provider2, provider3, provider4);
    }
}
