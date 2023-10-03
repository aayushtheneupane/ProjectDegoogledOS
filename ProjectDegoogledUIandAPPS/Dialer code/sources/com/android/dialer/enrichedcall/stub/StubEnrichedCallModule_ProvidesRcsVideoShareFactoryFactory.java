package com.android.dialer.enrichedcall.stub;

import android.support.p002v7.appcompat.R$style;
import com.android.dialer.enrichedcall.RcsVideoShareFactory;
import dagger.internal.Factory;

public enum StubEnrichedCallModule_ProvidesRcsVideoShareFactoryFactory implements Factory<RcsVideoShareFactory> {
    INSTANCE;

    public Object get() {
        $$Lambda$StubEnrichedCallModule$wrr8NfGRhVMWOxc7hQlZBxKXT8 r1 = $$Lambda$StubEnrichedCallModule$wrr8NfGRhVMWOxc7hQlZBxKXT8.INSTANCE;
        R$style.checkNotNull1(r1, "Cannot return null from a non-@Nullable @Provides method");
        return r1;
    }
}
