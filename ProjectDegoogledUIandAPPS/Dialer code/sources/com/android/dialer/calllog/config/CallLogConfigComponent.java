package com.android.dialer.calllog.config;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class CallLogConfigComponent {

    public interface HasComponent {
    }

    public static CallLogConfigComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).callLogConfigComponent();
    }

    public abstract CallLogConfig callLogConfig();
}
