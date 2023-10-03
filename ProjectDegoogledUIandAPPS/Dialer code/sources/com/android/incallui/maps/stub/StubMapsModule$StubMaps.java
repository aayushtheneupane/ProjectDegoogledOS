package com.android.incallui.maps.stub;

import android.location.Location;
import android.support.p000v4.app.Fragment;
import com.android.incallui.maps.Maps;

final class StubMapsModule$StubMaps implements Maps {
    public Fragment createStaticMapFragment(Location location) {
        throw new UnsupportedOperationException();
    }

    public boolean isAvailable() {
        return false;
    }
}
