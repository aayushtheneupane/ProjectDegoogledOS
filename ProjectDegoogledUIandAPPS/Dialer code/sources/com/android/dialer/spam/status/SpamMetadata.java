package com.android.dialer.spam.status;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SpamMetadata {

    public static abstract class Builder {
        public abstract SpamMetadata build();
    }
}
