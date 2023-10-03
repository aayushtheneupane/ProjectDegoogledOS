package com.android.dialer.assisteddialing;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.dialer.common.LogUtil;
import java.util.Locale;
import java.util.Optional;

final class LocationDetector {
    private final TelephonyManager telephonyManager;
    private final String userProvidedHomeCountry;

    LocationDetector(TelephonyManager telephonyManager2, String str) {
        if (telephonyManager2 != null) {
            this.telephonyManager = telephonyManager2;
            this.userProvidedHomeCountry = str;
            return;
        }
        throw new NullPointerException("Provided TelephonyManager was null");
    }

    /* access modifiers changed from: package-private */
    public Optional<String> getUpperCaseUserHomeCountry() {
        if (!TextUtils.isEmpty(this.userProvidedHomeCountry)) {
            LogUtil.m9i("LocationDetector.getUpperCaseUserRoamingCountry", "user provided home country code", new Object[0]);
            return Optional.of(this.userProvidedHomeCountry.toUpperCase(Locale.US));
        } else if (this.telephonyManager.getSimCountryIso() != null) {
            LogUtil.m9i("LocationDetector.getUpperCaseUserRoamingCountry", "using sim country iso", new Object[0]);
            return Optional.of(this.telephonyManager.getSimCountryIso().toUpperCase(Locale.US));
        } else {
            LogUtil.m9i("LocationDetector.getUpperCaseUserHomeCountry", "user home country was null", new Object[0]);
            return Optional.empty();
        }
    }

    /* access modifiers changed from: package-private */
    public Optional<String> getUpperCaseUserRoamingCountry() {
        if (this.telephonyManager.getNetworkCountryIso() != null) {
            return Optional.of(this.telephonyManager.getNetworkCountryIso().toUpperCase(Locale.US));
        }
        LogUtil.m9i("LocationDetector.getUpperCaseUserRoamingCountry", "user roaming country was null", new Object[0]);
        return Optional.empty();
    }
}
