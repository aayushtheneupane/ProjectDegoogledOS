package com.android.settingslib.core.instrumentation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class MetricsFeatureProvider {
    protected List<LogWriter> mLoggerWriters = new ArrayList();

    public MetricsFeatureProvider() {
        installLogWriters();
    }

    /* access modifiers changed from: protected */
    public void installLogWriters() {
        this.mLoggerWriters.add(new EventLogWriter());
    }

    public int getAttribution(Activity activity) {
        Intent intent;
        if (activity == null || (intent = activity.getIntent()) == null) {
            return 0;
        }
        return intent.getIntExtra(":settings:source_metrics", 0);
    }

    public void visible(Context context, int i, int i2) {
        for (LogWriter visible : this.mLoggerWriters) {
            visible.visible(context, i, i2);
        }
    }

    public void hidden(Context context, int i) {
        for (LogWriter hidden : this.mLoggerWriters) {
            hidden.hidden(context, i);
        }
    }

    public void action(Context context, int i, Pair<Integer, Object>... pairArr) {
        for (LogWriter action : this.mLoggerWriters) {
            action.action(context, i, pairArr);
        }
    }

    public void action(Context context, int i, String str) {
        for (LogWriter action : this.mLoggerWriters) {
            action.action(context, i, str);
        }
    }

    public void action(int i, int i2, int i3, String str, int i4) {
        for (LogWriter action : this.mLoggerWriters) {
            action.action(i, i2, i3, str, i4);
        }
    }

    public void action(Context context, int i, int i2) {
        for (LogWriter action : this.mLoggerWriters) {
            action.action(context, i, i2);
        }
    }

    public void action(Context context, int i, boolean z) {
        for (LogWriter action : this.mLoggerWriters) {
            action.action(context, i, z);
        }
    }

    public int getMetricsCategory(Object obj) {
        if (obj == null || !(obj instanceof Instrumentable)) {
            return 0;
        }
        return ((Instrumentable) obj).getMetricsCategory();
    }

    public void logDashboardStartIntent(Context context, Intent intent, int i) {
        if (intent != null) {
            ComponentName component = intent.getComponent();
            if (component == null) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action)) {
                    action(i, 830, 0, action, 0);
                }
            } else if (!TextUtils.equals(component.getPackageName(), context.getPackageName())) {
                action(i, 830, 0, component.flattenToString(), 0);
            }
        }
    }
}
