package androidx.appcompat.mms;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;

public class MmsManager {
    public static final int DEFAULT_SUB_ID = -1;
    private static SparseArray sConfigOverridesMap = new SparseArray();
    private static volatile boolean sForceLegacyMms = false;

    private static void computeConfigDelta(Bundle bundle, Bundle bundle2, Bundle bundle3) {
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            Object obj2 = bundle.get(str);
            if (!(obj == null || obj2 == null || obj.equals(obj2)) || ((obj != null && obj2 == null) || (obj == null && obj2 != null))) {
                if (obj == null || (obj instanceof String)) {
                    bundle3.putString(str, (String) obj);
                } else if (obj instanceof Integer) {
                    bundle3.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Boolean) {
                    bundle3.putBoolean(str, ((Boolean) obj).booleanValue());
                }
            }
        }
    }

    private static void computeOverridesLocked(int i, Bundle bundle) {
        CarrierConfigValuesLoader carrierConfigValuesLoader = MmsService.getCarrierConfigValuesLoader();
        if (carrierConfigValuesLoader != null && !(carrierConfigValuesLoader instanceof DefaultCarrierConfigValuesLoader)) {
            Bundle carrierConfigValues = Utils.getSmsManager(i).getCarrierConfigValues();
            Bundle bundle2 = MmsService.getCarrierConfigValuesLoader().get(i);
            if (carrierConfigValues != null && bundle2 != null) {
                computeConfigDelta(carrierConfigValues, bundle2, bundle);
            } else if (carrierConfigValues == null && bundle2 != null) {
                bundle.putAll(bundle2);
            }
        }
        UserAgentInfoLoader userAgentInfoLoader = MmsService.getUserAgentInfoLoader();
        if (userAgentInfoLoader != null && !(userAgentInfoLoader instanceof DefaultUserAgentInfoLoader)) {
            bundle.putString("userAgent", userAgentInfoLoader.getUserAgent());
            bundle.putString("uaProfUrl", userAgentInfoLoader.getUAProfUrl());
        }
    }

    public static void downloadMultimediaMessage(int i, Context context, String str, Uri uri, PendingIntent pendingIntent) {
        if (shouldUseLegacyMms()) {
            MmsService.startRequest(context, new DownloadRequest(str, uri, pendingIntent));
            return;
        }
        int effectiveSubscriptionId = Utils.getEffectiveSubscriptionId(i);
        Utils.getSmsManager(effectiveSubscriptionId).downloadMultimediaMessage(context, str, uri, getConfigOverrides(effectiveSubscriptionId), pendingIntent);
    }

    private static Bundle getConfigOverrides(int i) {
        Bundle bundle;
        int i2 = Build.VERSION.SDK_INT;
        synchronized (sConfigOverridesMap) {
            bundle = (Bundle) sConfigOverridesMap.get(i);
            if (bundle == null) {
                bundle = new Bundle();
                sConfigOverridesMap.put(i, bundle);
                computeOverridesLocked(i, bundle);
            }
        }
        return bundle;
    }

    public static void sendMultimediaMessage(int i, Context context, Uri uri, String str, PendingIntent pendingIntent) {
        if (shouldUseLegacyMms()) {
            MmsService.startRequest(context, new SendRequest(str, uri, pendingIntent));
            return;
        }
        int effectiveSubscriptionId = Utils.getEffectiveSubscriptionId(i);
        Utils.getSmsManager(effectiveSubscriptionId).sendMultimediaMessage(context, uri, str, getConfigOverrides(effectiveSubscriptionId), pendingIntent);
    }

    public static void setApnSettingsLoader(ApnSettingsLoader apnSettingsLoader) {
        if (apnSettingsLoader != null) {
            MmsService.setApnSettingsLoader(apnSettingsLoader);
            return;
        }
        throw new IllegalArgumentException("APN settings loader can not be empty");
    }

    public static void setCarrierConfigValuesLoader(CarrierConfigValuesLoader carrierConfigValuesLoader) {
        if (carrierConfigValuesLoader != null) {
            synchronized (sConfigOverridesMap) {
                MmsService.setCarrierConfigValuesLoader(carrierConfigValuesLoader);
                sConfigOverridesMap.clear();
            }
            return;
        }
        throw new IllegalArgumentException("Carrier configuration loader can not be empty");
    }

    public static void setForceLegacyMms(boolean z) {
        sForceLegacyMms = z;
    }

    public static void setThreadPoolSize(int i) {
        MmsService.setThreadPoolSize(i);
    }

    public static void setUseWakeLock(boolean z) {
        MmsService.setUseWakeLock(z);
    }

    public static void setUserAgentInfoLoader(UserAgentInfoLoader userAgentInfoLoader) {
        if (userAgentInfoLoader != null) {
            synchronized (sConfigOverridesMap) {
                MmsService.setUserAgentInfoLoader(userAgentInfoLoader);
                sConfigOverridesMap.clear();
            }
            return;
        }
        throw new IllegalArgumentException("User agent info loader can not be empty");
    }

    public static boolean shouldUseLegacyMms() {
        if (sForceLegacyMms) {
            return true;
        }
        int i = Build.VERSION.SDK_INT;
        return false;
    }
}
