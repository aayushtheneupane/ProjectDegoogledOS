package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.plugins.ActivityStarter;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserSwitcherController_Factory implements Factory<UserSwitcherController> {
    private final Provider<ActivityStarter> activityStarterProvider;
    private final Provider<Context> contextProvider;
    private final Provider<Handler> handlerProvider;
    private final Provider<KeyguardMonitor> keyguardMonitorProvider;

    public UserSwitcherController_Factory(Provider<Context> provider, Provider<KeyguardMonitor> provider2, Provider<Handler> provider3, Provider<ActivityStarter> provider4) {
        this.contextProvider = provider;
        this.keyguardMonitorProvider = provider2;
        this.handlerProvider = provider3;
        this.activityStarterProvider = provider4;
    }

    public UserSwitcherController get() {
        return provideInstance(this.contextProvider, this.keyguardMonitorProvider, this.handlerProvider, this.activityStarterProvider);
    }

    public static UserSwitcherController provideInstance(Provider<Context> provider, Provider<KeyguardMonitor> provider2, Provider<Handler> provider3, Provider<ActivityStarter> provider4) {
        return new UserSwitcherController(provider.get(), provider2.get(), provider3.get(), provider4.get());
    }

    public static UserSwitcherController_Factory create(Provider<Context> provider, Provider<KeyguardMonitor> provider2, Provider<Handler> provider3, Provider<ActivityStarter> provider4) {
        return new UserSwitcherController_Factory(provider, provider2, provider3, provider4);
    }
}
