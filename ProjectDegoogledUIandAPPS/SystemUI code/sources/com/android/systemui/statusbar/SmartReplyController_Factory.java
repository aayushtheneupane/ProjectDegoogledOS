package com.android.systemui.statusbar;

import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SmartReplyController_Factory implements Factory<SmartReplyController> {
    private final Provider<NotificationEntryManager> entryManagerProvider;
    private final Provider<IStatusBarService> statusBarServiceProvider;

    public SmartReplyController_Factory(Provider<NotificationEntryManager> provider, Provider<IStatusBarService> provider2) {
        this.entryManagerProvider = provider;
        this.statusBarServiceProvider = provider2;
    }

    public SmartReplyController get() {
        return provideInstance(this.entryManagerProvider, this.statusBarServiceProvider);
    }

    public static SmartReplyController provideInstance(Provider<NotificationEntryManager> provider, Provider<IStatusBarService> provider2) {
        return new SmartReplyController(provider.get(), provider2.get());
    }

    public static SmartReplyController_Factory create(Provider<NotificationEntryManager> provider, Provider<IStatusBarService> provider2) {
        return new SmartReplyController_Factory(provider, provider2);
    }
}
