package com.android.dialer.performancereport;

import android.os.SystemClock;
import android.support.p002v7.widget.RecyclerView;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.UiAction$Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class PerformanceReport {
    private static final long ACTIVE_DURATION = TimeUnit.MINUTES.toMillis(5);
    private static final List<Long> actionTimestamps = new ArrayList();
    private static final List<UiAction$Type> actions = new ArrayList();
    private static long appLaunchTimeMillis = -1;
    private static long firstClickTimeMillis = -1;
    private static UiAction$Type ignoreActionOnce = null;
    private static long lastActionTimeMillis = -1;
    private static final RecyclerView.OnScrollListener recordOnScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 2) {
                PerformanceReport.recordClick(UiAction$Type.SCROLL);
            }
        }
    };
    private static boolean recording = false;
    private static int startingTabIndex = -1;

    public static List<Long> getActionTimestamps() {
        return actionTimestamps;
    }

    public static List<UiAction$Type> getActions() {
        return actions;
    }

    public static int getStartingTabIndex() {
        return startingTabIndex;
    }

    public static long getTimeSinceAppLaunch() {
        if (appLaunchTimeMillis == -1) {
            return -1;
        }
        return SystemClock.elapsedRealtime() - appLaunchTimeMillis;
    }

    public static long getTimeSinceFirstClick() {
        if (firstClickTimeMillis == -1) {
            return -1;
        }
        return SystemClock.elapsedRealtime() - firstClickTimeMillis;
    }

    public static boolean isRecording() {
        return recording;
    }

    public static void logOnScrollStateChange(RecyclerView recyclerView) {
        recyclerView.removeOnScrollListener(recordOnScrollListener);
        recyclerView.addOnScrollListener(recordOnScrollListener);
    }

    public static void recordClick(UiAction$Type uiAction$Type) {
        if (recording) {
            if (uiAction$Type == ignoreActionOnce) {
                LogUtil.m9i("PerformanceReport.recordClick", "%s is ignored", uiAction$Type.toString());
                ignoreActionOnce = null;
                return;
            }
            ignoreActionOnce = null;
            uiAction$Type.toString();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - lastActionTimeMillis > ACTIVE_DURATION) {
                startRecording();
                recordClick(uiAction$Type);
                return;
            }
            lastActionTimeMillis = elapsedRealtime;
            if (actions.isEmpty()) {
                firstClickTimeMillis = elapsedRealtime;
            }
            actions.add(uiAction$Type);
            actionTimestamps.add(Long.valueOf(elapsedRealtime - appLaunchTimeMillis));
        }
    }

    public static void recordScrollStateChange(int i) {
        if (i == 1) {
            recordClick(UiAction$Type.SCROLL);
        }
    }

    public static void setIgnoreActionOnce(UiAction$Type uiAction$Type) {
        ignoreActionOnce = uiAction$Type;
        LogUtil.m9i("PerformanceReport.setIgnoreActionOnce", "next action will be ignored once if it is %s", uiAction$Type.toString());
    }

    public static void setStartingTabIndex(int i) {
        startingTabIndex = i;
    }

    public static void startRecording() {
        LogUtil.enterBlock("PerformanceReport.startRecording");
        appLaunchTimeMillis = SystemClock.elapsedRealtime();
        lastActionTimeMillis = appLaunchTimeMillis;
        if (!actions.isEmpty()) {
            actions.clear();
            actionTimestamps.clear();
        }
        recording = true;
    }

    public static void stopRecording() {
        LogUtil.enterBlock("PerformanceReport.stopRecording");
        recording = false;
    }
}
