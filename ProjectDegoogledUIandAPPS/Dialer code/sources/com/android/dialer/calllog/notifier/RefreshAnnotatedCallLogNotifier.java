package com.android.dialer.calllog.notifier;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.p000v4.content.LocalBroadcastManager;
import com.android.dialer.common.LogUtil;

public class RefreshAnnotatedCallLogNotifier {
    private final Context appContext;
    private final SharedPreferences sharedPreferences;

    RefreshAnnotatedCallLogNotifier(Context context, SharedPreferences sharedPreferences2) {
        this.appContext = context;
        this.sharedPreferences = sharedPreferences2;
    }

    public void cancel() {
        LogUtil.enterBlock("RefreshAnnotatedCallLogNotifier.cancel");
        Intent intent = new Intent();
        intent.setAction("cancel_refreshing_annotated_call_log");
        LocalBroadcastManager.getInstance(this.appContext).sendBroadcast(intent);
    }

    public void markDirtyAndNotify() {
        LogUtil.enterBlock("RefreshAnnotatedCallLogNotifier.markDirtyAndNotify");
        this.sharedPreferences.edit().putBoolean("force_rebuild", true).apply();
        notify(false);
    }

    public void notify(boolean z) {
        LogUtil.m9i("RefreshAnnotatedCallLogNotifier.notify", "checkDirty = %s", Boolean.valueOf(z));
        Intent intent = new Intent();
        intent.setAction("refresh_annotated_call_log");
        intent.putExtra("check_dirty", z);
        LocalBroadcastManager.getInstance(this.appContext).sendBroadcast(intent);
    }
}
