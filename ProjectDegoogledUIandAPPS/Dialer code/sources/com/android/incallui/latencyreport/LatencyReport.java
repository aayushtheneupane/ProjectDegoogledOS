package com.android.incallui.latencyreport;

import android.os.Bundle;
import android.os.SystemClock;
import android.telecom.Call;

public class LatencyReport {
    private long callBlockingTimeMillis;
    private long callNotificationTimeMillis;
    private long inCallUiShownTimeMillis;
    private final boolean wasIncoming;

    public LatencyReport() {
        this.callBlockingTimeMillis = -1;
        this.callNotificationTimeMillis = -1;
        this.inCallUiShownTimeMillis = -1;
        this.wasIncoming = false;
        SystemClock.elapsedRealtime();
    }

    public void onCallBlockingDone() {
        if (this.callBlockingTimeMillis == -1) {
            this.callBlockingTimeMillis = SystemClock.elapsedRealtime();
        }
    }

    public void onInCallUiShown(boolean z) {
        if (this.inCallUiShownTimeMillis == -1) {
            this.inCallUiShownTimeMillis = SystemClock.elapsedRealtime();
            boolean z2 = this.wasIncoming;
        }
    }

    public void onNotificationShown() {
        if (this.callNotificationTimeMillis == -1) {
            this.callNotificationTimeMillis = SystemClock.elapsedRealtime();
        }
    }

    public LatencyReport(Call call) {
        this.callBlockingTimeMillis = -1;
        this.callNotificationTimeMillis = -1;
        this.inCallUiShownTimeMillis = -1;
        this.wasIncoming = call.getState() == 2;
        Bundle intentExtras = call.getDetails().getIntentExtras();
        if (intentExtras != null) {
            intentExtras.getLong("android.telecom.extra.CALL_CREATED_TIME_MILLIS", -1);
            intentExtras.getLong("android.telecom.extra.CALL_TELECOM_ROUTING_START_TIME_MILLIS", -1);
            intentExtras.getLong("android.telecom.extra.CALL_TELECOM_ROUTING_END_TIME_MILLIS", -1);
        }
        SystemClock.elapsedRealtime();
    }
}
