package com.android.dialer.activecalls;

import android.telecom.PhoneAccountHandle;
import com.android.dialer.activecalls.AutoValue_ActiveCallInfo;
import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;

@AutoValue
public abstract class ActiveCallInfo {

    public static abstract class Builder {
        public abstract ActiveCallInfo build();

        public abstract Builder setPhoneAccountHandle(Optional<PhoneAccountHandle> optional);
    }

    public static Builder builder() {
        return new AutoValue_ActiveCallInfo.Builder();
    }

    public abstract Optional<PhoneAccountHandle> phoneAccountHandle();
}
