package android.support.p002v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.R$dimen;
import android.util.Log;
import java.util.Calendar;

/* renamed from: android.support.v7.app.TwilightManager */
class TwilightManager {
    private static TwilightManager sInstance;
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState mTwilightState = new TwilightState();

    /* renamed from: android.support.v7.app.TwilightManager$TwilightState */
    private static class TwilightState {
        boolean isNight;
        long nextUpdate;

        TwilightState() {
        }
    }

    TwilightManager(Context context, LocationManager locationManager) {
        this.mContext = context;
        this.mLocationManager = locationManager;
    }

    static TwilightManager getInstance(Context context) {
        if (sInstance == null) {
            Context applicationContext = context.getApplicationContext();
            sInstance = new TwilightManager(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return sInstance;
    }

    private Location getLastKnownLocationForProvider(String str) {
        try {
            if (this.mLocationManager.isProviderEnabled(str)) {
                return this.mLocationManager.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }

    static void setInstance(TwilightManager twilightManager) {
        sInstance = twilightManager;
    }

    /* access modifiers changed from: package-private */
    public boolean isNight() {
        long j;
        TwilightState twilightState = this.mTwilightState;
        boolean z = true;
        if (twilightState.nextUpdate > System.currentTimeMillis()) {
            return twilightState.isNight;
        }
        Location location = null;
        Location lastKnownLocationForProvider = R$dimen.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? getLastKnownLocationForProvider("network") : null;
        if (R$dimen.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location = getLastKnownLocationForProvider("gps");
        }
        if (location == null || lastKnownLocationForProvider == null ? location != null : location.getTime() > lastKnownLocationForProvider.getTime()) {
            lastKnownLocationForProvider = location;
        }
        if (lastKnownLocationForProvider != null) {
            TwilightState twilightState2 = this.mTwilightState;
            long currentTimeMillis = System.currentTimeMillis();
            TwilightCalculator instance = TwilightCalculator.getInstance();
            instance.calculateTwilight(currentTimeMillis - 86400000, lastKnownLocationForProvider.getLatitude(), lastKnownLocationForProvider.getLongitude());
            long j2 = instance.sunset;
            instance.calculateTwilight(currentTimeMillis, lastKnownLocationForProvider.getLatitude(), lastKnownLocationForProvider.getLongitude());
            if (instance.state != 1) {
                z = false;
            }
            long j3 = instance.sunrise;
            long j4 = instance.sunset;
            long j5 = j3;
            instance.calculateTwilight(currentTimeMillis + 86400000, lastKnownLocationForProvider.getLatitude(), lastKnownLocationForProvider.getLongitude());
            long j6 = instance.sunrise;
            if (j5 == -1 || j4 == -1) {
                j = 43200000 + currentTimeMillis;
            } else {
                j = (currentTimeMillis > j4 ? j6 + 0 : currentTimeMillis > j5 ? j4 + 0 : j5 + 0) + 60000;
            }
            twilightState2.isNight = z;
            twilightState2.nextUpdate = j;
            return twilightState.isNight;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        if (i < 6 || i >= 22) {
            return true;
        }
        return false;
    }
}
