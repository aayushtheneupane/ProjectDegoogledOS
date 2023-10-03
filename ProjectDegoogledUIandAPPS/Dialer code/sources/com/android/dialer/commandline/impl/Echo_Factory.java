package com.android.dialer.commandline.impl;

import dagger.internal.Factory;

public enum Echo_Factory implements Factory<Echo> {
    INSTANCE;

    public Object get() {
        return new Echo();
    }
}
