package com.android.systemui.statusbar;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.bubbles.BubbleController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.VisualStabilityManager;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.NotificationGroupManager;
import com.android.systemui.statusbar.phone.ShadeController;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NotificationViewHierarchyManager_Factory implements Factory<NotificationViewHierarchyManager> {
    private final Provider<BubbleController> bubbleControllerProvider;
    private final Provider<KeyguardBypassController> bypassControllerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<NotificationGroupManager> groupManagerProvider;
    private final Provider<Handler> mainHandlerProvider;
    private final Provider<NotificationEntryManager> notificationEntryManagerProvider;
    private final Provider<NotificationLockscreenUserManager> notificationLockscreenUserManagerProvider;
    private final Provider<DynamicPrivacyController> privacyControllerProvider;
    private final Provider<ShadeController> shadeControllerProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;
    private final Provider<VisualStabilityManager> visualStabilityManagerProvider;

    public NotificationViewHierarchyManager_Factory(Provider<Context> provider, Provider<Handler> provider2, Provider<NotificationLockscreenUserManager> provider3, Provider<NotificationGroupManager> provider4, Provider<VisualStabilityManager> provider5, Provider<StatusBarStateController> provider6, Provider<NotificationEntryManager> provider7, Provider<ShadeController> provider8, Provider<KeyguardBypassController> provider9, Provider<BubbleController> provider10, Provider<DynamicPrivacyController> provider11) {
        this.contextProvider = provider;
        this.mainHandlerProvider = provider2;
        this.notificationLockscreenUserManagerProvider = provider3;
        this.groupManagerProvider = provider4;
        this.visualStabilityManagerProvider = provider5;
        this.statusBarStateControllerProvider = provider6;
        this.notificationEntryManagerProvider = provider7;
        this.shadeControllerProvider = provider8;
        this.bypassControllerProvider = provider9;
        this.bubbleControllerProvider = provider10;
        this.privacyControllerProvider = provider11;
    }

    public NotificationViewHierarchyManager get() {
        return provideInstance(this.contextProvider, this.mainHandlerProvider, this.notificationLockscreenUserManagerProvider, this.groupManagerProvider, this.visualStabilityManagerProvider, this.statusBarStateControllerProvider, this.notificationEntryManagerProvider, this.shadeControllerProvider, this.bypassControllerProvider, this.bubbleControllerProvider, this.privacyControllerProvider);
    }

    public static NotificationViewHierarchyManager provideInstance(Provider<Context> provider, Provider<Handler> provider2, Provider<NotificationLockscreenUserManager> provider3, Provider<NotificationGroupManager> provider4, Provider<VisualStabilityManager> provider5, Provider<StatusBarStateController> provider6, Provider<NotificationEntryManager> provider7, Provider<ShadeController> provider8, Provider<KeyguardBypassController> provider9, Provider<BubbleController> provider10, Provider<DynamicPrivacyController> provider11) {
        return new NotificationViewHierarchyManager(provider.get(), provider2.get(), provider3.get(), provider4.get(), provider5.get(), provider6.get(), provider7.get(), DoubleCheck.lazy(provider8), provider9.get(), provider10.get(), provider11.get());
    }

    public static NotificationViewHierarchyManager_Factory create(Provider<Context> provider, Provider<Handler> provider2, Provider<NotificationLockscreenUserManager> provider3, Provider<NotificationGroupManager> provider4, Provider<VisualStabilityManager> provider5, Provider<StatusBarStateController> provider6, Provider<NotificationEntryManager> provider7, Provider<ShadeController> provider8, Provider<KeyguardBypassController> provider9, Provider<BubbleController> provider10, Provider<DynamicPrivacyController> provider11) {
        return new NotificationViewHierarchyManager_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }
}
