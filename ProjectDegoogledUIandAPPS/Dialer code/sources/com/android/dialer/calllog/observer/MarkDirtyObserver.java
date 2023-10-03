package com.android.dialer.calllog.observer;

import android.database.ContentObserver;
import android.net.Uri;
import com.android.dialer.calllog.notifier.RefreshAnnotatedCallLogNotifier;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;

public final class MarkDirtyObserver extends ContentObserver {
    private final RefreshAnnotatedCallLogNotifier refreshAnnotatedCallLogNotifier;

    public MarkDirtyObserver(RefreshAnnotatedCallLogNotifier refreshAnnotatedCallLogNotifier2) {
        super(DialerExecutorModule.getUiThreadHandler());
        this.refreshAnnotatedCallLogNotifier = refreshAnnotatedCallLogNotifier2;
    }

    public void onChange(boolean z, Uri uri) {
        Assert.isMainThread();
        LogUtil.m9i("MarkDirtyObserver.onChange", "Uri:%s, SelfChange:%b", String.valueOf(uri), Boolean.valueOf(z));
        this.refreshAnnotatedCallLogNotifier.markDirtyAndNotify();
    }
}
