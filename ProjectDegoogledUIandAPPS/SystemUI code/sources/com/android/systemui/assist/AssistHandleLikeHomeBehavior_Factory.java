package com.android.systemui.assist;

import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.recents.OverviewProxyService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AssistHandleLikeHomeBehavior_Factory implements Factory<AssistHandleLikeHomeBehavior> {
    private final Provider<OverviewProxyService> overviewProxyServiceProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;
    private final Provider<WakefulnessLifecycle> wakefulnessLifecycleProvider;

    public AssistHandleLikeHomeBehavior_Factory(Provider<StatusBarStateController> provider, Provider<WakefulnessLifecycle> provider2, Provider<OverviewProxyService> provider3) {
        this.statusBarStateControllerProvider = provider;
        this.wakefulnessLifecycleProvider = provider2;
        this.overviewProxyServiceProvider = provider3;
    }

    public AssistHandleLikeHomeBehavior get() {
        return provideInstance(this.statusBarStateControllerProvider, this.wakefulnessLifecycleProvider, this.overviewProxyServiceProvider);
    }

    public static AssistHandleLikeHomeBehavior provideInstance(Provider<StatusBarStateController> provider, Provider<WakefulnessLifecycle> provider2, Provider<OverviewProxyService> provider3) {
        return new AssistHandleLikeHomeBehavior(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static AssistHandleLikeHomeBehavior_Factory create(Provider<StatusBarStateController> provider, Provider<WakefulnessLifecycle> provider2, Provider<OverviewProxyService> provider3) {
        return new AssistHandleLikeHomeBehavior_Factory(provider, provider2, provider3);
    }
}
