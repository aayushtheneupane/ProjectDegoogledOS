package com.android.dialer.metrics;

import android.os.SystemClock;
import com.android.dialer.common.Assert;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class StubMetrics implements Metrics {
    private final ConcurrentMap<String, StubTimerEvent> namedEvents = new ConcurrentHashMap();
    private final ConcurrentMap<Integer, StubTimerEvent> unnamedEvents = new ConcurrentHashMap();

    private static class StubTimerEvent {
        final long startTime = SystemClock.elapsedRealtime();

        StubTimerEvent() {
        }
    }

    StubMetrics() {
    }

    public void recordMemory(String str) {
        new Object[1][0] = str;
    }

    public void startJankRecorder(String str) {
        new Object[1][0] = str;
    }

    public void startTimer(String str) {
        this.namedEvents.put(str, new StubTimerEvent());
    }

    public Integer startUnnamedTimer() {
        StubTimerEvent stubTimerEvent = new StubTimerEvent();
        int hashCode = stubTimerEvent.hashCode();
        new Object[1][0] = Integer.valueOf(hashCode);
        this.unnamedEvents.put(Integer.valueOf(hashCode), stubTimerEvent);
        return Integer.valueOf(hashCode);
    }

    public void stopJankRecorder(String str) {
        new Object[1][0] = str;
    }

    public void stopTimer(String str) {
        StubTimerEvent stubTimerEvent = (StubTimerEvent) this.namedEvents.remove(str);
        if (stubTimerEvent != null) {
            Object[] objArr = {str, Long.valueOf(SystemClock.elapsedRealtime() - stubTimerEvent.startTime)};
        }
    }

    public void stopUnnamedTimer(int i, String str) {
        StubTimerEvent stubTimerEvent = (StubTimerEvent) this.unnamedEvents.remove(Integer.valueOf(i));
        Assert.isNotNull(stubTimerEvent, "no timer found for id: %d (%s)", Integer.valueOf(i), str);
        Object[] objArr = {str, Long.valueOf(SystemClock.elapsedRealtime() - stubTimerEvent.startTime)};
    }
}
