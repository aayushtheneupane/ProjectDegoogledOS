package com.android.incallui.maps;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class MapsComponent {

    public interface HasComponent {
    }

    public static MapsComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).mapsComponent();
    }

    public abstract Maps getMaps();
}
