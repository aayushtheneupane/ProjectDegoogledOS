package com.android.systemui.statusbar.notification;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.phone.HeadsUpManagerPhone;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationWakeUpCoordinator_Factory implements Factory<NotificationWakeUpCoordinator> {
    private final Provider<KeyguardBypassController> bypassControllerProvider;
    private final Provider<Context> mContextProvider;
    private final Provider<HeadsUpManagerPhone> mHeadsUpManagerPhoneProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;

    public NotificationWakeUpCoordinator_Factory(Provider<Context> provider, Provider<HeadsUpManagerPhone> provider2, Provider<StatusBarStateController> provider3, Provider<KeyguardBypassController> provider4) {
        this.mContextProvider = provider;
        this.mHeadsUpManagerPhoneProvider = provider2;
        this.statusBarStateControllerProvider = provider3;
        this.bypassControllerProvider = provider4;
    }

    public NotificationWakeUpCoordinator get() {
        return provideInstance(this.mContextProvider, this.mHeadsUpManagerPhoneProvider, this.statusBarStateControllerProvider, this.bypassControllerProvider);
    }

    public static NotificationWakeUpCoordinator provideInstance(Provider<Context> provider, Provider<HeadsUpManagerPhone> provider2, Provider<StatusBarStateController> provider3, Provider<KeyguardBypassController> provider4) {
        return new NotificationWakeUpCoordinator(provider.get(), provider2.get(), provider3.get(), provider4.get());
    }

    public static NotificationWakeUpCoordinator_Factory create(Provider<Context> provider, Provider<HeadsUpManagerPhone> provider2, Provider<StatusBarStateController> provider3, Provider<KeyguardBypassController> provider4) {
        return new NotificationWakeUpCoordinator_Factory(provider, provider2, provider3, provider4);
    }
}
