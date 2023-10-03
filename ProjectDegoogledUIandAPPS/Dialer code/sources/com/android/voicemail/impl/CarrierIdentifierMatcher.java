package com.android.voicemail.impl;

import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;

@AutoValue
public abstract class CarrierIdentifierMatcher {

    public static abstract class Builder {
        public abstract CarrierIdentifierMatcher build();

        public abstract Builder setGid1(String str);

        public abstract Builder setMccMnc(String str);
    }

    public abstract Optional<String> gid1();

    public abstract String mccMnc();
}
