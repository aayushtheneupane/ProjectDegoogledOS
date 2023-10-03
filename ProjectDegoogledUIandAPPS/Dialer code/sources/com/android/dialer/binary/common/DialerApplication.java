package com.android.dialer.binary.common;

import android.app.Application;
import android.os.Build;
import android.os.Trace;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.blocking.BlockedNumbersAutoMigrator;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.calllog.CallLogComponent;
import com.android.dialer.calllog.config.CallLogConfigComponent;
import com.android.dialer.calllog.config.CallLogConfigImpl;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.notification.NotificationChannelManager;
import com.android.dialer.persistentlog.PersistentLogger;

public abstract class DialerApplication extends Application implements HasRootComponent {
    private volatile Object rootComponent;

    /* access modifiers changed from: protected */
    public abstract Object buildRootComponent();

    public final Object component() {
        Object obj = this.rootComponent;
        if (obj == null) {
            synchronized (this) {
                obj = this.rootComponent;
                if (obj == null) {
                    obj = buildRootComponent();
                    this.rootComponent = obj;
                }
            }
        }
        return obj;
    }

    public void onCreate() {
        Trace.beginSection("DialerApplication.onCreate");
        ((DaggerAospDialerRootComponent) ((HasRootComponent) getApplicationContext()).component()).strictModeComponent().getDialerStrictMode().onApplicationCreate(this);
        super.onCreate();
        new BlockedNumbersAutoMigrator(getApplicationContext(), new FilteredNumberAsyncQueryHandler(this), DialerExecutorComponent.get(this).dialerExecutorFactory()).asyncAutoMigrate();
        CallLogConfigImpl callLogConfigImpl = (CallLogConfigImpl) CallLogConfigComponent.get(this).callLogConfig();
        callLogConfigImpl.schedulePollingJob();
        if (callLogConfigImpl.isCallLogFrameworkEnabled()) {
            CallLogComponent.get(this).callLogFramework().registerContentObservers();
        } else {
            LogUtil.m9i("DialerApplication.initializeAnnotatedCallLog", "framework not enabled", new Object[0]);
        }
        PersistentLogger.initialize(this);
        int i = Build.VERSION.SDK_INT;
        NotificationChannelManager.initChannels(this);
        Trace.endSection();
    }
}
