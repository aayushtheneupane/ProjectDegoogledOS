package com.android.dialer.metrics.jank;

import android.support.p002v7.widget.RecyclerView;
import com.android.dialer.metrics.Metrics;
import com.android.dialer.metrics.StubMetrics;

public final class RecyclerViewJankLogger extends RecyclerView.OnScrollListener {
    private final String eventName;
    private boolean isScrolling;
    private final Metrics metrics;

    public RecyclerViewJankLogger(Metrics metrics2, String str) {
        this.metrics = metrics2;
        this.eventName = str;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        if (!this.isScrolling && i == 1) {
            this.isScrolling = true;
            ((StubMetrics) this.metrics).startJankRecorder(this.eventName);
        } else if (this.isScrolling && i == 0) {
            this.isScrolling = false;
            ((StubMetrics) this.metrics).stopJankRecorder(this.eventName);
        }
    }
}
