package com.android.systemui.classifier;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.shared.plugins.PluginManager;
import com.android.systemui.util.ProximitySensor;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FalsingManagerProxy_Factory implements Factory<FalsingManagerProxy> {
    private final Provider<Context> contextProvider;
    private final Provider<Handler> handlerProvider;
    private final Provider<PluginManager> pluginManagerProvider;
    private final Provider<ProximitySensor> proximitySensorProvider;

    public FalsingManagerProxy_Factory(Provider<Context> provider, Provider<PluginManager> provider2, Provider<Handler> provider3, Provider<ProximitySensor> provider4) {
        this.contextProvider = provider;
        this.pluginManagerProvider = provider2;
        this.handlerProvider = provider3;
        this.proximitySensorProvider = provider4;
    }

    public FalsingManagerProxy get() {
        return provideInstance(this.contextProvider, this.pluginManagerProvider, this.handlerProvider, this.proximitySensorProvider);
    }

    public static FalsingManagerProxy provideInstance(Provider<Context> provider, Provider<PluginManager> provider2, Provider<Handler> provider3, Provider<ProximitySensor> provider4) {
        return new FalsingManagerProxy(provider.get(), provider2.get(), provider3.get(), provider4.get());
    }

    public static FalsingManagerProxy_Factory create(Provider<Context> provider, Provider<PluginManager> provider2, Provider<Handler> provider3, Provider<ProximitySensor> provider4) {
        return new FalsingManagerProxy_Factory(provider, provider2, provider3, provider4);
    }
}
