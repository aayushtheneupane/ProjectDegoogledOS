package com.android.dialer.enrichedcall.stub;

import android.support.p002v7.appcompat.R$style;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import dagger.internal.Factory;

public enum StubEnrichedCallModule_ProvideEnrichedCallManagerFactory implements Factory<EnrichedCallManager> {
    INSTANCE;

    public Object get() {
        EnrichedCallManagerStub enrichedCallManagerStub = new EnrichedCallManagerStub();
        R$style.checkNotNull1(enrichedCallManagerStub, "Cannot return null from a non-@Nullable @Provides method");
        return enrichedCallManagerStub;
    }
}
