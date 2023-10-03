package com.android.systemui.recents;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import com.android.systemui.SysUiServiceProvider;
import java.io.PrintWriter;

interface RecentsImplementation {
    void cancelPreloadRecentApps() {
    }

    void dump(PrintWriter printWriter) {
    }

    void growRecents() {
    }

    void hideRecentApps(boolean z, boolean z2) {
    }

    void onAppTransitionFinished() {
    }

    void onBootCompleted() {
    }

    void onConfigurationChanged(Configuration configuration) {
    }

    void onStart(Context context, SysUiServiceProvider sysUiServiceProvider) {
    }

    void preloadRecentApps() {
    }

    void showRecentApps(boolean z) {
    }

    boolean splitPrimaryTask(int i, Rect rect, int i2) {
        return false;
    }

    void toggleRecentApps() {
    }
}
