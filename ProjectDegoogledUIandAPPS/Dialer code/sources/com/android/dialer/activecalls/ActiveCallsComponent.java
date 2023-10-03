package com.android.dialer.activecalls;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class ActiveCallsComponent {

    public interface HasComponent {
    }

    public static ActiveCallsComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).activeCallsComponent();
    }

    public abstract ActiveCalls activeCalls();
}
