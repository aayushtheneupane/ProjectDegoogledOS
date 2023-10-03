package com.android.systemui.statusbar.phone;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.tuner.TunerService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class KeyguardBypassController_Factory implements Factory<KeyguardBypassController> {
    private final Provider<Context> contextProvider;
    private final Provider<NotificationLockscreenUserManager> lockscreenUserManagerProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;
    private final Provider<TunerService> tunerServiceProvider;

    public KeyguardBypassController_Factory(Provider<Context> provider, Provider<TunerService> provider2, Provider<StatusBarStateController> provider3, Provider<NotificationLockscreenUserManager> provider4) {
        this.contextProvider = provider;
        this.tunerServiceProvider = provider2;
        this.statusBarStateControllerProvider = provider3;
        this.lockscreenUserManagerProvider = provider4;
    }

    public KeyguardBypassController get() {
        return provideInstance(this.contextProvider, this.tunerServiceProvider, this.statusBarStateControllerProvider, this.lockscreenUserManagerProvider);
    }

    public static KeyguardBypassController provideInstance(Provider<Context> provider, Provider<TunerService> provider2, Provider<StatusBarStateController> provider3, Provider<NotificationLockscreenUserManager> provider4) {
        return new KeyguardBypassController(provider.get(), provider2.get(), provider3.get(), provider4.get());
    }

    public static KeyguardBypassController_Factory create(Provider<Context> provider, Provider<TunerService> provider2, Provider<StatusBarStateController> provider3, Provider<NotificationLockscreenUserManager> provider4) {
        return new KeyguardBypassController_Factory(provider, provider2, provider3, provider4);
    }
}
