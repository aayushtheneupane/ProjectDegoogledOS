package com.android.contacts.location;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Locale;

public class CountryDetector {
    private static final String TAG = "CountryDetector";
    private static CountryDetector sInstance;
    private final String DEFAULT_COUNTRY_ISO;
    private final Context mContext;
    private final LocaleProvider mLocaleProvider;
    private final TelephonyManager mTelephonyManager;

    public static class LocaleProvider {
        public Locale getDefaultLocale() {
            return Locale.getDefault();
        }
    }

    private CountryDetector(Context context) {
        this(context, (TelephonyManager) context.getSystemService("phone"), new LocaleProvider());
    }

    private CountryDetector(Context context, TelephonyManager telephonyManager, LocaleProvider localeProvider) {
        this.DEFAULT_COUNTRY_ISO = "US";
        this.mTelephonyManager = telephonyManager;
        this.mLocaleProvider = localeProvider;
        this.mContext = context;
    }

    public CountryDetector getInstanceForTest(Context context, TelephonyManager telephonyManager, LocaleProvider localeProvider) {
        return new CountryDetector(context, telephonyManager, localeProvider);
    }

    public static synchronized CountryDetector getInstance(Context context) {
        CountryDetector countryDetector;
        synchronized (CountryDetector.class) {
            if (sInstance == null) {
                sInstance = new CountryDetector(context.getApplicationContext());
            }
            countryDetector = sInstance;
        }
        return countryDetector;
    }

    public String getCurrentCountryIso() {
        String networkBasedCountryIso = isNetworkCountryCodeAvailable() ? getNetworkBasedCountryIso() : null;
        if (TextUtils.isEmpty(networkBasedCountryIso)) {
            networkBasedCountryIso = getSimBasedCountryIso();
        }
        if (TextUtils.isEmpty(networkBasedCountryIso)) {
            networkBasedCountryIso = getLocaleBasedCountryIso();
        }
        if (TextUtils.isEmpty(networkBasedCountryIso)) {
            networkBasedCountryIso = "US";
        }
        return networkBasedCountryIso.toUpperCase(Locale.US);
    }

    private String getNetworkBasedCountryIso() {
        return this.mTelephonyManager.getNetworkCountryIso();
    }

    private String getSimBasedCountryIso() {
        return this.mTelephonyManager.getSimCountryIso();
    }

    private String getLocaleBasedCountryIso() {
        Locale defaultLocale = this.mLocaleProvider.getDefaultLocale();
        if (defaultLocale != null) {
            return defaultLocale.getCountry();
        }
        return null;
    }

    private boolean isNetworkCountryCodeAvailable() {
        return this.mTelephonyManager.getPhoneType() == 1;
    }
}
