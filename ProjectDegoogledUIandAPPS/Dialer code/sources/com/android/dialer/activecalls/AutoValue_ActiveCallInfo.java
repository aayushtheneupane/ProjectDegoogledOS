package com.android.dialer.activecalls;

import android.telecom.PhoneAccountHandle;
import com.android.dialer.activecalls.ActiveCallInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;

final class AutoValue_ActiveCallInfo extends ActiveCallInfo {
    private final Optional<PhoneAccountHandle> phoneAccountHandle;

    static final class Builder extends ActiveCallInfo.Builder {
        private Optional<PhoneAccountHandle> phoneAccountHandle = Optional.absent();

        Builder() {
        }

        public ActiveCallInfo build() {
            return new AutoValue_ActiveCallInfo(this.phoneAccountHandle, (C02821) null);
        }

        public ActiveCallInfo.Builder setPhoneAccountHandle(Optional<PhoneAccountHandle> optional) {
            if (optional != null) {
                this.phoneAccountHandle = optional;
                return this;
            }
            throw new NullPointerException("Null phoneAccountHandle");
        }
    }

    /* synthetic */ AutoValue_ActiveCallInfo(Optional optional, C02821 r2) {
        this.phoneAccountHandle = optional;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ActiveCallInfo) {
            return this.phoneAccountHandle.equals(((AutoValue_ActiveCallInfo) obj).phoneAccountHandle);
        }
        return false;
    }

    public int hashCode() {
        return this.phoneAccountHandle.hashCode() ^ 1000003;
    }

    public Optional<PhoneAccountHandle> phoneAccountHandle() {
        return this.phoneAccountHandle;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("ActiveCallInfo{phoneAccountHandle="), this.phoneAccountHandle, "}");
    }
}
