package com.android.systemui;

import dagger.internal.Factory;

public final class DumpController_Factory implements Factory<DumpController> {
    private static final DumpController_Factory INSTANCE = new DumpController_Factory();

    public DumpController get() {
        return provideInstance();
    }

    public static DumpController provideInstance() {
        return new DumpController();
    }

    public static DumpController_Factory create() {
        return INSTANCE;
    }
}
