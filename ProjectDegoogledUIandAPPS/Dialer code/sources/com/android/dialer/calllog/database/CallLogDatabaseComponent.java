package com.android.dialer.calllog.database;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class CallLogDatabaseComponent {

    public interface HasComponent {
    }

    public static CallLogDatabaseComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).callLogDatabaseComponent();
    }

    public abstract AnnotatedCallLogDatabaseHelper annotatedCallLogDatabaseHelper();

    public abstract Coalescer coalescer();
}
