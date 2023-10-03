package com.android.dialer.enrichedcall;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class EnrichedCallComponent {

    public interface HasComponent {
    }

    public static EnrichedCallComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).enrichedCallComponent();
    }

    public abstract EnrichedCallManager getEnrichedCallManager();

    public abstract RcsVideoShareFactory getRcsVideoShareFactory();
}
