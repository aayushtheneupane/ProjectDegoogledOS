package com.android.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.phone.KeyguardBypassController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationRoundnessManager_Factory implements Factory<NotificationRoundnessManager> {
    private final Provider<KeyguardBypassController> keyguardBypassControllerProvider;

    public NotificationRoundnessManager_Factory(Provider<KeyguardBypassController> provider) {
        this.keyguardBypassControllerProvider = provider;
    }

    public NotificationRoundnessManager get() {
        return provideInstance(this.keyguardBypassControllerProvider);
    }

    public static NotificationRoundnessManager provideInstance(Provider<KeyguardBypassController> provider) {
        return new NotificationRoundnessManager(provider.get());
    }

    public static NotificationRoundnessManager_Factory create(Provider<KeyguardBypassController> provider) {
        return new NotificationRoundnessManager_Factory(provider);
    }
}
