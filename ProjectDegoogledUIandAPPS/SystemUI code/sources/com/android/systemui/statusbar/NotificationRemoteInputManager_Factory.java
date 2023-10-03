package com.android.systemui.statusbar;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.phone.ShadeController;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationRemoteInputManager_Factory implements Factory<NotificationRemoteInputManager> {
    private final Provider<Context> contextProvider;
    private final Provider<NotificationLockscreenUserManager> lockscreenUserManagerProvider;
    private final Provider<Handler> mainHandlerProvider;
    private final Provider<NotificationEntryManager> notificationEntryManagerProvider;
    private final Provider<ShadeController> shadeControllerProvider;
    private final Provider<SmartReplyController> smartReplyControllerProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;

    public NotificationRemoteInputManager_Factory(Provider<Context> provider, Provider<NotificationLockscreenUserManager> provider2, Provider<SmartReplyController> provider3, Provider<NotificationEntryManager> provider4, Provider<ShadeController> provider5, Provider<StatusBarStateController> provider6, Provider<Handler> provider7) {
        this.contextProvider = provider;
        this.lockscreenUserManagerProvider = provider2;
        this.smartReplyControllerProvider = provider3;
        this.notificationEntryManagerProvider = provider4;
        this.shadeControllerProvider = provider5;
        this.statusBarStateControllerProvider = provider6;
        this.mainHandlerProvider = provider7;
    }

    public NotificationRemoteInputManager get() {
        return provideInstance(this.contextProvider, this.lockscreenUserManagerProvider, this.smartReplyControllerProvider, this.notificationEntryManagerProvider, this.shadeControllerProvider, this.statusBarStateControllerProvider, this.mainHandlerProvider);
    }

    public static NotificationRemoteInputManager provideInstance(Provider<Context> provider, Provider<NotificationLockscreenUserManager> provider2, Provider<SmartReplyController> provider3, Provider<NotificationEntryManager> provider4, Provider<ShadeController> provider5, Provider<StatusBarStateController> provider6, Provider<Handler> provider7) {
        return new NotificationRemoteInputManager(provider.get(), provider2.get(), provider3.get(), provider4.get(), DoubleCheck.lazy(provider5), provider6.get(), provider7.get());
    }

    public static NotificationRemoteInputManager_Factory create(Provider<Context> provider, Provider<NotificationLockscreenUserManager> provider2, Provider<SmartReplyController> provider3, Provider<NotificationEntryManager> provider4, Provider<ShadeController> provider5, Provider<StatusBarStateController> provider6, Provider<Handler> provider7) {
        return new NotificationRemoteInputManager_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
