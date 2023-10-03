package com.android.settingslib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.telephony.CarrierConfigManager;

public class TetherUtil {
    static boolean isEntitlementCheckRequired(Context context) {
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config");
        if (carrierConfigManager == null || carrierConfigManager.getConfig() == null) {
            return true;
        }
        return carrierConfigManager.getConfig().getBoolean("require_entitlement_checks_bool");
    }

    public static boolean isProvisioningNeeded(Context context) {
        String[] stringArray = context.getResources().getStringArray(17236057);
        if (SystemProperties.getBoolean("net.tethering.noprovisioning", false) || stringArray == null || !isEntitlementCheckRequired(context) || stringArray.length != 2) {
            return false;
        }
        return true;
    }

    public static boolean isTetherAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        boolean z = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(context, "no_config_tethering", UserHandle.myUserId()) != null;
        boolean hasBaseUserRestriction = RestrictedLockUtilsInternal.hasBaseUserRestriction(context, "no_config_tethering", UserHandle.myUserId());
        if ((connectivityManager.isTetheringSupported() || z) && !hasBaseUserRestriction) {
            return true;
        }
        return false;
    }
}
