package com.android.settingslib.core.instrumentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class VisibilityLoggerMixin implements LifecycleObserver {
    private final int mMetricsCategory;
    private MetricsFeatureProvider mMetricsFeature;
    private int mSourceMetricsCategory = 0;
    private long mVisibleTimestamp;

    public VisibilityLoggerMixin(int i, MetricsFeatureProvider metricsFeatureProvider) {
        this.mMetricsCategory = i;
        this.mMetricsFeature = metricsFeatureProvider;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        int i;
        this.mVisibleTimestamp = SystemClock.elapsedRealtime();
        MetricsFeatureProvider metricsFeatureProvider = this.mMetricsFeature;
        if (metricsFeatureProvider != null && (i = this.mMetricsCategory) != 0) {
            metricsFeatureProvider.visible((Context) null, this.mSourceMetricsCategory, i);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        int i;
        this.mVisibleTimestamp = 0;
        MetricsFeatureProvider metricsFeatureProvider = this.mMetricsFeature;
        if (metricsFeatureProvider != null && (i = this.mMetricsCategory) != 0) {
            metricsFeatureProvider.hidden((Context) null, i);
        }
    }

    public void setSourceMetricsCategory(Activity activity) {
        Intent intent;
        if (this.mSourceMetricsCategory == 0 && activity != null && (intent = activity.getIntent()) != null) {
            this.mSourceMetricsCategory = intent.getIntExtra(":settings:source_metrics", 0);
        }
    }
}
