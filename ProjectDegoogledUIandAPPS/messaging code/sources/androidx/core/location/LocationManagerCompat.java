package androidx.core.location;

import android.location.LocationManager;
import android.os.Build;

public final class LocationManagerCompat {
    private LocationManagerCompat() {
    }

    public static boolean isLocationEnabled(LocationManager locationManager) {
        int i = Build.VERSION.SDK_INT;
        return locationManager.isLocationEnabled();
    }
}
