package com.android.dialer.location;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.design.R$dimen;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import java.util.List;
import java.util.Locale;

public class CountryDetector {
    public static CountryDetector instance;
    private final Context appContext;
    /* access modifiers changed from: private */
    public final Geocoder geocoder;
    private final LocaleProvider localeProvider;
    private final TelephonyManager telephonyManager;

    private static class GeocodeCountryWorker implements DialerExecutor.Worker<Location, String> {
        private final Geocoder geocoder;

        GeocodeCountryWorker(Geocoder geocoder2) {
            Assert.isNotNull(geocoder2);
            this.geocoder = geocoder2;
        }

        public Object doInBackground(Object obj) throws Throwable {
            List<Address> fromLocation;
            Location location = (Location) obj;
            if (location == null || (fromLocation = this.geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1)) == null || fromLocation.isEmpty()) {
                return null;
            }
            return fromLocation.get(0).getCountryCode();
        }
    }

    public interface LocaleProvider {
        Locale getLocale();
    }

    public static class LocationChangedReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("location")) {
                Geocoder access$000 = CountryDetector.getInstance(context).geocoder;
                ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new GeocodeCountryWorker(access$000)).onSuccess(new DialerExecutor.SuccessListener(context) {
                    private final /* synthetic */ Context f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void onSuccess(Object obj) {
                        CountryDetector.lambda$processLocationUpdate$0(this.f$0, (String) obj);
                    }
                }).onFailure($$Lambda$CountryDetector$RPP7Wp_CSmYDKmSIMSLXmEqMTJo.INSTANCE).build().executeParallel((Location) intent.getExtras().get("location"));
            }
        }
    }

    public CountryDetector(Context context, TelephonyManager telephonyManager2, LocationManager locationManager, LocaleProvider localeProvider2, Geocoder geocoder2) {
        this.telephonyManager = telephonyManager2;
        this.localeProvider = localeProvider2;
        this.appContext = context;
        this.geocoder = geocoder2;
        if (!Geocoder.isPresent()) {
            return;
        }
        if (!hasLocationPermissions(context)) {
            LogUtil.m10w("CountryDetector.registerForLocationUpdates", "no location permissions, not registering for location updates", new Object[0]);
            return;
        }
        LogUtil.m9i("CountryDetector.registerForLocationUpdates", "registering for location updates", new Object[0]);
        locationManager.requestLocationUpdates("passive", 43200000, 5000.0f, PendingIntent.getBroadcast(context, 0, new Intent(context, LocationChangedReceiver.class), 134217728));
    }

    public static synchronized CountryDetector getInstance(Context context) {
        CountryDetector countryDetector;
        synchronized (CountryDetector.class) {
            if (instance == null) {
                Context applicationContext = context.getApplicationContext();
                instance = new CountryDetector(applicationContext, (TelephonyManager) context.getSystemService("phone"), (LocationManager) context.getSystemService("location"), $$Lambda$CX4jMmtHqSh4oz0xWLz6XC1x04.INSTANCE, new Geocoder(applicationContext));
            }
            countryDetector = instance;
        }
        return countryDetector;
    }

    private static boolean hasLocationPermissions(Context context) {
        return context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    static /* synthetic */ void lambda$processLocationUpdate$0(Context context, String str) {
        if (str != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putLong("preference_time_updated", System.currentTimeMillis()).putString("preference_current_country", str).apply();
        }
    }

    public String getCurrentCountryIso() {
        boolean z = true;
        if (this.telephonyManager.getPhoneType() != 1) {
            z = false;
        }
        String str = null;
        String networkCountryIso = z ? this.telephonyManager.getNetworkCountryIso() : null;
        if (TextUtils.isEmpty(networkCountryIso)) {
            networkCountryIso = (!Geocoder.isPresent() || !hasLocationPermissions(this.appContext) || !R$dimen.isUserUnlocked(this.appContext)) ? null : PreferenceManager.getDefaultSharedPreferences(this.appContext).getString("preference_current_country", (String) null);
        }
        if (TextUtils.isEmpty(networkCountryIso)) {
            networkCountryIso = this.telephonyManager.getSimCountryIso();
        }
        if (TextUtils.isEmpty(networkCountryIso)) {
            Locale locale = this.localeProvider.getLocale();
            if (locale != null) {
                str = locale.getCountry();
            }
            networkCountryIso = str;
        }
        if (TextUtils.isEmpty(networkCountryIso)) {
            networkCountryIso = "US";
        }
        return networkCountryIso.toUpperCase(Locale.US);
    }
}
