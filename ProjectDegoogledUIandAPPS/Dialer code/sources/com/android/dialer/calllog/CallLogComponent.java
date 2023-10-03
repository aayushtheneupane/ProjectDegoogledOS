package com.android.dialer.calllog;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.calllog.notifier.RefreshAnnotatedCallLogNotifier;
import com.android.dialer.inject.HasRootComponent;

public abstract class CallLogComponent {

    public interface HasComponent {
    }

    public static CallLogComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).callLogComponent();
    }

    public abstract CallLogFramework callLogFramework();

    public abstract ClearMissedCalls getClearMissedCalls();

    public abstract RefreshAnnotatedCallLogNotifier getRefreshAnnotatedCallLogNotifier();

    public abstract RefreshAnnotatedCallLogWorker getRefreshAnnotatedCallLogWorker();
}
