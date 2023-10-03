package com.android.systemui;

import dagger.internal.Factory;

public final class ActivityStarterDelegate_Factory implements Factory<ActivityStarterDelegate> {
    private static final ActivityStarterDelegate_Factory INSTANCE = new ActivityStarterDelegate_Factory();

    public ActivityStarterDelegate get() {
        return provideInstance();
    }

    public static ActivityStarterDelegate provideInstance() {
        return new ActivityStarterDelegate();
    }

    public static ActivityStarterDelegate_Factory create() {
        return INSTANCE;
    }
}
