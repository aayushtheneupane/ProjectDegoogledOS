package com.android.dialer.spam.stub;

import dagger.internal.Factory;

public enum SpamSettingsStub_Factory implements Factory<SpamSettingsStub> {
    INSTANCE;

    public Object get() {
        return new SpamSettingsStub();
    }
}
