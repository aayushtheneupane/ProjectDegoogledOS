package com.android.dialer.metrics;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class MetricsComponent {

    public interface HasComponent {
    }

    public static MetricsComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).metricsComponent();
    }

    public abstract FutureTimer futureTimer();

    public abstract Metrics metrics();
}
