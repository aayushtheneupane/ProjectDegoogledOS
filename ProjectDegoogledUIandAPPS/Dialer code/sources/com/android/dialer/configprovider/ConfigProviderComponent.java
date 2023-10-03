package com.android.dialer.configprovider;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class ConfigProviderComponent {

    public interface HasComponent {
    }

    public static ConfigProviderComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).configProviderComponent();
    }

    public abstract ConfigProvider getConfigProvider();
}
