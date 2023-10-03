package com.android.systemui.privacy;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.appops.AppOpsController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PrivacyItemController_Factory implements Factory<PrivacyItemController> {
    private final Provider<AppOpsController> appOpsControllerProvider;
    private final Provider<Handler> bgHandlerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<Handler> uiHandlerProvider;

    public PrivacyItemController_Factory(Provider<Context> provider, Provider<AppOpsController> provider2, Provider<Handler> provider3, Provider<Handler> provider4) {
        this.contextProvider = provider;
        this.appOpsControllerProvider = provider2;
        this.uiHandlerProvider = provider3;
        this.bgHandlerProvider = provider4;
    }

    public PrivacyItemController get() {
        return provideInstance(this.contextProvider, this.appOpsControllerProvider, this.uiHandlerProvider, this.bgHandlerProvider);
    }

    public static PrivacyItemController provideInstance(Provider<Context> provider, Provider<AppOpsController> provider2, Provider<Handler> provider3, Provider<Handler> provider4) {
        return new PrivacyItemController(provider.get(), provider2.get(), provider3.get(), provider4.get());
    }

    public static PrivacyItemController_Factory create(Provider<Context> provider, Provider<AppOpsController> provider2, Provider<Handler> provider3, Provider<Handler> provider4) {
        return new PrivacyItemController_Factory(provider, provider2, provider3, provider4);
    }
}
