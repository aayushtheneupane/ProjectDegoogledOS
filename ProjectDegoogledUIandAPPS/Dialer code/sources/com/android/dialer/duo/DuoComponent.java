package com.android.dialer.duo;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class DuoComponent {

    public interface HasComponent {
    }

    public static DuoComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).duoComponent();
    }

    public abstract Duo getDuo();
}
