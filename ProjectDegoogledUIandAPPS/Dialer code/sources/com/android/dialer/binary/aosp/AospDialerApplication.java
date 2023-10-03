package com.android.dialer.binary.aosp;

import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.binary.common.DialerApplication;
import com.android.dialer.inject.ContextModule;

public class AospDialerApplication extends DialerApplication {
    /* access modifiers changed from: protected */
    public Object buildRootComponent() {
        DaggerAospDialerRootComponent.Builder builder = new DaggerAospDialerRootComponent.Builder((DaggerAospDialerRootComponent.C03661) null);
        builder.contextModule(new ContextModule(this));
        return builder.build();
    }
}
