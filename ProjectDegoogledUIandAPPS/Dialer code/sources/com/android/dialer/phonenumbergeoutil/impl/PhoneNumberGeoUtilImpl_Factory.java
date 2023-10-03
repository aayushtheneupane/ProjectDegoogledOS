package com.android.dialer.phonenumbergeoutil.impl;

import dagger.internal.Factory;

public enum PhoneNumberGeoUtilImpl_Factory implements Factory<PhoneNumberGeoUtilImpl> {
    INSTANCE;

    public Object get() {
        return new PhoneNumberGeoUtilImpl();
    }
}
