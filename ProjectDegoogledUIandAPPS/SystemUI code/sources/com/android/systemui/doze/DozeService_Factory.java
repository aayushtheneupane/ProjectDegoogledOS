package com.android.systemui.doze;

import dagger.internal.Factory;

public final class DozeService_Factory implements Factory<DozeService> {
    private static final DozeService_Factory INSTANCE = new DozeService_Factory();

    public DozeService get() {
        return provideInstance();
    }

    public static DozeService provideInstance() {
        return new DozeService();
    }

    public static DozeService_Factory create() {
        return INSTANCE;
    }
}
