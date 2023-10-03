package com.android.systemui.statusbar.notification.row;

import android.content.Context;
import com.android.systemui.statusbar.notification.VisualStabilityManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationGutsManager_Factory implements Factory<NotificationGutsManager> {
    private final Provider<Context> contextProvider;
    private final Provider<VisualStabilityManager> visualStabilityManagerProvider;

    public NotificationGutsManager_Factory(Provider<Context> provider, Provider<VisualStabilityManager> provider2) {
        this.contextProvider = provider;
        this.visualStabilityManagerProvider = provider2;
    }

    public NotificationGutsManager get() {
        return provideInstance(this.contextProvider, this.visualStabilityManagerProvider);
    }

    public static NotificationGutsManager provideInstance(Provider<Context> provider, Provider<VisualStabilityManager> provider2) {
        return new NotificationGutsManager(provider.get(), provider2.get());
    }

    public static NotificationGutsManager_Factory create(Provider<Context> provider, Provider<VisualStabilityManager> provider2) {
        return new NotificationGutsManager_Factory(provider, provider2);
    }
}
