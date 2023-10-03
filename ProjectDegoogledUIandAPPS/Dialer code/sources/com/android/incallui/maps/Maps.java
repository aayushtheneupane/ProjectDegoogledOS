package com.android.incallui.maps;

import android.location.Location;
import android.support.p000v4.app.Fragment;

public interface Maps {
    Fragment createStaticMapFragment(Location location);

    boolean isAvailable();
}
