package com.android.dialer.strictmode.impl;

import dagger.internal.Factory;

public enum SystemDialerStrictMode_Factory implements Factory<SystemDialerStrictMode> {
    INSTANCE;

    public Object get() {
        return new SystemDialerStrictMode();
    }
}
