package com.android.voicemail.impl;

import android.annotation.TargetApi;
import com.google.auto.value.AutoValue;

@AutoValue
@TargetApi(26)
public abstract class CarrierIdentifier {

    public static abstract class Builder {
        public abstract CarrierIdentifier build();

        public abstract Builder setGid1(String str);

        public abstract Builder setMccMnc(String str);
    }

    public abstract String gid1();

    public abstract String mccMnc();
}
