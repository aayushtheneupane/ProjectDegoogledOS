package com.android.dialer.duo;

import com.google.auto.value.AutoValue;

public interface Duo {

    @AutoValue
    public static abstract class ReachabilityData {
        public abstract boolean videoCallable();
    }
}
