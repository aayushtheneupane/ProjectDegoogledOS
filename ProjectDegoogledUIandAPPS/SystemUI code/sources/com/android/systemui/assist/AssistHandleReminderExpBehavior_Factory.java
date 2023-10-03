package com.android.systemui.assist;

import android.os.Handler;
import androidx.slice.Clock;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.PackageManagerWrapper;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AssistHandleReminderExpBehavior_Factory implements Factory<AssistHandleReminderExpBehavior> {
    private final Provider<ActivityManagerWrapper> activityManagerWrapperProvider;
    private final Provider<Clock> clockProvider;
    private final Provider<DeviceConfigHelper> deviceConfigHelperProvider;
    private final Provider<Handler> handlerProvider;
    private final Provider<OverviewProxyService> overviewProxyServiceProvider;
    private final Provider<PackageManagerWrapper> packageManagerWrapperProvider;
    private final Provider<StatusBarStateController> statusBarStateControllerProvider;
    private final Provider<WakefulnessLifecycle> wakefulnessLifecycleProvider;

    public AssistHandleReminderExpBehavior_Factory(Provider<Clock> provider, Provider<Handler> provider2, Provider<DeviceConfigHelper> provider3, Provider<StatusBarStateController> provider4, Provider<ActivityManagerWrapper> provider5, Provider<OverviewProxyService> provider6, Provider<WakefulnessLifecycle> provider7, Provider<PackageManagerWrapper> provider8) {
        this.clockProvider = provider;
        this.handlerProvider = provider2;
        this.deviceConfigHelperProvider = provider3;
        this.statusBarStateControllerProvider = provider4;
        this.activityManagerWrapperProvider = provider5;
        this.overviewProxyServiceProvider = provider6;
        this.wakefulnessLifecycleProvider = provider7;
        this.packageManagerWrapperProvider = provider8;
    }

    public AssistHandleReminderExpBehavior get() {
        return provideInstance(this.clockProvider, this.handlerProvider, this.deviceConfigHelperProvider, this.statusBarStateControllerProvider, this.activityManagerWrapperProvider, this.overviewProxyServiceProvider, this.wakefulnessLifecycleProvider, this.packageManagerWrapperProvider);
    }

    public static AssistHandleReminderExpBehavior provideInstance(Provider<Clock> provider, Provider<Handler> provider2, Provider<DeviceConfigHelper> provider3, Provider<StatusBarStateController> provider4, Provider<ActivityManagerWrapper> provider5, Provider<OverviewProxyService> provider6, Provider<WakefulnessLifecycle> provider7, Provider<PackageManagerWrapper> provider8) {
        return new AssistHandleReminderExpBehavior(provider.get(), provider2.get(), provider3.get(), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8));
    }

    public static AssistHandleReminderExpBehavior_Factory create(Provider<Clock> provider, Provider<Handler> provider2, Provider<DeviceConfigHelper> provider3, Provider<StatusBarStateController> provider4, Provider<ActivityManagerWrapper> provider5, Provider<OverviewProxyService> provider6, Provider<WakefulnessLifecycle> provider7, Provider<PackageManagerWrapper> provider8) {
        return new AssistHandleReminderExpBehavior_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }
}
