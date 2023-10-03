package com.android.dialer.logging;

import android.content.Context;
import java.util.Objects;

public class Logger {
    private static LoggingBindings loggingBindings;

    public static LoggingBindings get(Context context) {
        Objects.requireNonNull(context);
        LoggingBindings loggingBindings2 = loggingBindings;
        if (loggingBindings2 != null) {
            return loggingBindings2;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof LoggingBindingsFactory) {
            loggingBindings = ((LoggingBindingsFactory) applicationContext).newLoggingBindings();
        }
        if (loggingBindings == null) {
            loggingBindings = new LoggingBindingsStub();
        }
        return loggingBindings;
    }
}
