package com.android.settingslib.core.instrumentation;

import android.content.Context;
import android.os.SystemClock;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class VisibilityLoggerMixin implements LifecycleObserver {
    private final int mMetricsCategory;
    private MetricsFeatureProvider mMetricsFeature;
    private int mSourceMetricsCategory;
    private long mVisibleTimestamp;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        int i;
        this.mVisibleTimestamp = SystemClock.elapsedRealtime();
        MetricsFeatureProvider metricsFeatureProvider = this.mMetricsFeature;
        if (metricsFeatureProvider != null && (i = this.mMetricsCategory) != 0) {
            metricsFeatureProvider.visible((Context) null, this.mSourceMetricsCategory, i);
            throw null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        int i;
        this.mVisibleTimestamp = 0;
        MetricsFeatureProvider metricsFeatureProvider = this.mMetricsFeature;
        if (metricsFeatureProvider != null && (i = this.mMetricsCategory) != 0) {
            metricsFeatureProvider.hidden((Context) null, i);
            throw null;
        }
    }
}
