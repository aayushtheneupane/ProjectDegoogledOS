package com.android.systemui;

import dagger.internal.Factory;

public final class ForegroundServiceController_Factory implements Factory<ForegroundServiceController> {
    private static final ForegroundServiceController_Factory INSTANCE = new ForegroundServiceController_Factory();

    public ForegroundServiceController get() {
        return provideInstance();
    }

    public static ForegroundServiceController provideInstance() {
        return new ForegroundServiceController();
    }

    public static ForegroundServiceController_Factory create() {
        return INSTANCE;
    }
}
