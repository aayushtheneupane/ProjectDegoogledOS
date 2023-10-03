package com.android.settings.network.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.PersistableBundle;
import android.os.SystemProperties;
import android.provider.Settings;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.euicc.EuiccManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.ims.ImsException;
import com.android.ims.ImsManager;
import com.android.internal.telephony.Phone;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.graph.SignalDrawable;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class MobileNetworkUtils {
    public static final Drawable EMPTY_DRAWABLE = new ColorDrawable(0);

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        if (r6 != null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        r0.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isDpcApnEnforced(android.content.Context r6) {
        /*
            android.content.ContentResolver r0 = r6.getContentResolver()
            android.net.Uri r1 = android.provider.Telephony.Carriers.ENFORCE_MANAGED_URI
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)
            r0 = 0
            if (r6 == 0) goto L_0x0037
            int r1 = r6.getCount()     // Catch:{ all -> 0x0029 }
            r2 = 1
            if (r1 == r2) goto L_0x0019
            goto L_0x0037
        L_0x0019:
            r6.moveToFirst()     // Catch:{ all -> 0x0029 }
            int r1 = r6.getInt(r0)     // Catch:{ all -> 0x0029 }
            if (r1 <= 0) goto L_0x0023
            r0 = r2
        L_0x0023:
            if (r6 == 0) goto L_0x0028
            r6.close()
        L_0x0028:
            return r0
        L_0x0029:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002b }
        L_0x002b:
            r1 = move-exception
            if (r6 == 0) goto L_0x0036
            r6.close()     // Catch:{ all -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r6 = move-exception
            r0.addSuppressed(r6)
        L_0x0036:
            throw r1
        L_0x0037:
            if (r6 == 0) goto L_0x003c
            r6.close()
        L_0x003c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.telephony.MobileNetworkUtils.isDpcApnEnforced(android.content.Context):boolean");
    }

    public static boolean isWifiCallingEnabled(Context context, int i) {
        PhoneAccountHandle simCallManagerForSubscription = TelecomManager.from(context).getSimCallManagerForSubscription(i);
        int slotIndex = SubscriptionManager.getSlotIndex(i);
        if (simCallManagerForSubscription == null) {
            ImsManager instance = ImsManager.getInstance(context, slotIndex);
            if (instance != null && instance.isWfcEnabledByPlatform() && instance.isWfcProvisionedOnDevice() && isImsServiceStateReady(instance)) {
                return true;
            }
        } else if (buildPhoneAccountConfigureIntent(context, simCallManagerForSubscription) != null) {
            return true;
        }
        return false;
    }

    static Intent buildPhoneAccountConfigureIntent(Context context, PhoneAccountHandle phoneAccountHandle) {
        Intent buildConfigureIntent = buildConfigureIntent(context, phoneAccountHandle, "android.telecom.action.CONFIGURE_PHONE_ACCOUNT");
        return buildConfigureIntent == null ? buildConfigureIntent(context, phoneAccountHandle, "android.telecom.action.CONNECTION_SERVICE_CONFIGURE") : buildConfigureIntent;
    }

    private static Intent buildConfigureIntent(Context context, PhoneAccountHandle phoneAccountHandle, String str) {
        if (phoneAccountHandle == null || phoneAccountHandle.getComponentName() == null || TextUtils.isEmpty(phoneAccountHandle.getComponentName().getPackageName())) {
            return null;
        }
        Intent intent = new Intent(str);
        intent.setPackage(phoneAccountHandle.getComponentName().getPackageName());
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() == 0) {
            return null;
        }
        return intent;
    }

    public static boolean isImsServiceStateReady(ImsManager imsManager) {
        boolean z = false;
        if (imsManager != null) {
            try {
                if (imsManager.getImsServiceState() == 2) {
                    z = true;
                }
            } catch (ImsException e) {
                Log.e("MobileNetworkUtils", "Exception when trying to get ImsServiceStatus: " + e);
            }
        }
        Log.d("MobileNetworkUtils", "isImsServiceStateReady=" + z);
        return z;
    }

    public static boolean showEuiccSettings(Context context) {
        boolean z;
        if (!((EuiccManager) context.getSystemService(EuiccManager.class)).isEnabled()) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        String lowerCase = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getNetworkCountryIso().toLowerCase();
        String string = Settings.Global.getString(contentResolver, "euicc_supported_countries");
        String string2 = Settings.Global.getString(contentResolver, "euicc_unsupported_countries");
        if (TextUtils.isEmpty(string)) {
            Log.d("MobileNetworkUtils", "Using blacklist unsupportedCountries=" + string2);
            z = isEsimUnsupportedCountry(lowerCase, string2) ^ true;
        } else {
            Log.d("MobileNetworkUtils", "Using whitelist supportedCountries=" + string);
            z = isEsimSupportedCountry(lowerCase, string);
        }
        Log.d("MobileNetworkUtils", "inEsimSupportedCountries=" + z);
        boolean contains = Arrays.asList(TextUtils.split(SystemProperties.get("ro.setupwizard.esim_cid_ignore", ""), ",")).contains(SystemProperties.get("ro.boot.cid", (String) null));
        boolean z2 = SystemProperties.getBoolean("esim.enable_esim_system_ui_by_default", true);
        boolean z3 = Settings.Global.getInt(contentResolver, "euicc_provisioned", 0) != 0;
        if ((Settings.Global.getInt(contentResolver, "development_settings_enabled", 0) != 0) || z3 || (!contains && z2 && z)) {
            return true;
        }
        return false;
    }

    public static void setMobileDataEnabled(Context context, int i, boolean z, boolean z2) {
        List<SubscriptionInfo> activeSubscriptionInfoList;
        TelephonyManager createForSubscriptionId = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        createForSubscriptionId.setDataEnabled(z);
        if (z2 && (activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList(true)) != null) {
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getSubscriptionId() != i && !subscriptionInfo.isOpportunistic()) {
                    ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(subscriptionInfo.getSubscriptionId()).setDataEnabled(false);
                }
            }
        }
    }

    public static boolean isCdmaOptions(Context context, int i) {
        if (i == -1) {
            return false;
        }
        TelephonyManager createForSubscriptionId = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
        PersistableBundle configForSubId = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfigForSubId(i);
        if (createForSubscriptionId.getPhoneType() == 2) {
            return true;
        }
        if (configForSubId != null && !configForSubId.getBoolean("hide_carrier_network_settings_bool") && configForSubId.getBoolean("world_phone_bool")) {
            return true;
        }
        if (isWorldMode(context, i)) {
            ContentResolver contentResolver = context.getContentResolver();
            int i2 = Settings.Global.getInt(contentResolver, "preferred_network_mode" + i, Phone.PREFERRED_NT_MODE);
            if (i2 == 9 || i2 == 8 || shouldSpeciallyUpdateGsmCdma(context, i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGsmOptions(Context context, int i) {
        if (i == -1) {
            return false;
        }
        if (isGsmBasicOptions(context, i)) {
            return true;
        }
        ContentResolver contentResolver = context.getContentResolver();
        int i2 = Settings.Global.getInt(contentResolver, "preferred_network_mode" + i, Phone.PREFERRED_NT_MODE);
        return isWorldMode(context, i) && (i2 == 8 || i2 == 9 || shouldSpeciallyUpdateGsmCdma(context, i));
    }

    private static boolean isGsmBasicOptions(Context context, int i) {
        TelephonyManager createForSubscriptionId = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
        PersistableBundle configForSubId = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfigForSubId(i);
        if (createForSubscriptionId.getPhoneType() == 1) {
            return true;
        }
        if (configForSubId == null || configForSubId.getBoolean("hide_carrier_network_settings_bool") || !configForSubId.getBoolean("world_phone_bool")) {
            return false;
        }
        return true;
    }

    public static boolean isWorldMode(Context context, int i) {
        PersistableBundle configForSubId = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfigForSubId(i);
        if (configForSubId == null) {
            return false;
        }
        return configForSubId.getBoolean("world_mode_enabled_bool");
    }

    public static boolean shouldDisplayNetworkSelectOptions(Context context, int i) {
        TelephonyManager createForSubscriptionId = TelephonyManager.from(context).createForSubscriptionId(i);
        PersistableBundle configForSubId = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfigForSubId(i);
        if (i != -1 && configForSubId != null && configForSubId.getBoolean("operator_selection_expand_bool") && !configForSubId.getBoolean("hide_carrier_network_settings_bool") && (!configForSubId.getBoolean("csp_enabled_bool") || createForSubscriptionId.isManualNetworkSelectionAllowed())) {
            ContentResolver contentResolver = context.getContentResolver();
            int i2 = Settings.Global.getInt(contentResolver, "preferred_network_mode" + i, Phone.PREFERRED_NT_MODE);
            if ((i2 == 8 && isWorldMode(context, i)) || shouldSpeciallyUpdateGsmCdma(context, i)) {
                return false;
            }
            if (isGsmBasicOptions(context, i)) {
                return true;
            }
            if (!isWorldMode(context, i) || i2 != 9) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isTdscdmaSupported(Context context, int i) {
        return isTdscdmaSupported(context, ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i));
    }

    private static boolean isTdscdmaSupported(Context context, TelephonyManager telephonyManager) {
        PersistableBundle config = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfig();
        if (config == null) {
            return false;
        }
        if (config.getBoolean("support_tdscdma_bool")) {
            return true;
        }
        String operatorNumeric = telephonyManager.getServiceState().getOperatorNumeric();
        String[] stringArray = config.getStringArray("support_tdscdma_roaming_networks_string_array");
        if (!(stringArray == null || operatorNumeric == null)) {
            for (String equals : stringArray) {
                if (operatorNumeric.equals(equals)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getSearchableSubscriptionId(Context context) {
        int[] activeSubscriptionIdList = ((SubscriptionManager) context.getSystemService(SubscriptionManager.class)).getActiveSubscriptionIdList();
        if (activeSubscriptionIdList.length >= 1) {
            return activeSubscriptionIdList[0];
        }
        return -1;
    }

    public static int getAvailability(Context context, int i, TelephonyAvailabilityCallback telephonyAvailabilityCallback) {
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        if (i != -1) {
            return telephonyAvailabilityCallback.getAvailabilityStatus(i);
        }
        int[] activeSubscriptionIdList = subscriptionManager.getActiveSubscriptionIdList();
        if (ArrayUtils.isEmpty(activeSubscriptionIdList)) {
            return telephonyAvailabilityCallback.getAvailabilityStatus(-1);
        }
        for (int availabilityStatus : activeSubscriptionIdList) {
            int availabilityStatus2 = telephonyAvailabilityCallback.getAvailabilityStatus(availabilityStatus);
            if (availabilityStatus2 == 0) {
                return availabilityStatus2;
            }
        }
        return telephonyAvailabilityCallback.getAvailabilityStatus(activeSubscriptionIdList[0]);
    }

    static boolean shouldSpeciallyUpdateGsmCdma(Context context, int i) {
        ContentResolver contentResolver = context.getContentResolver();
        int i2 = Settings.Global.getInt(contentResolver, "preferred_network_mode" + i, Phone.PREFERRED_NT_MODE);
        return (i2 == 17 || i2 == 20 || i2 == 15 || i2 == 19 || i2 == 22 || i2 == 10) && !isTdscdmaSupported(context, i) && isWorldMode(context, i);
    }

    public static Drawable getSignalStrengthIcon(Context context, int i, int i2, int i3, boolean z) {
        Drawable drawable;
        SignalDrawable signalDrawable = new SignalDrawable(context);
        signalDrawable.setLevel(SignalDrawable.getState(i, i2, z));
        if (i3 == 0) {
            drawable = EMPTY_DRAWABLE;
        } else {
            drawable = context.getResources().getDrawable(i3, context.getTheme());
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(C1715R.dimen.signal_strength_icon_size);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, signalDrawable});
        layerDrawable.setLayerGravity(0, 51);
        layerDrawable.setLayerGravity(1, 85);
        layerDrawable.setLayerSize(1, dimensionPixelSize, dimensionPixelSize);
        layerDrawable.setTintList(Utils.getColorAttr(context, 16843817));
        return layerDrawable;
    }

    public static CharSequence getCurrentCarrierNameForDisplay(Context context, int i) {
        SubscriptionInfo subscriptionInfo;
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        if (subscriptionManager == null || (subscriptionInfo = getSubscriptionInfo(subscriptionManager, i)) == null) {
            return getOperatorNameFromTelephonyManager(context);
        }
        return subscriptionInfo.getCarrierName();
    }

    public static CharSequence getCurrentCarrierNameForDisplay(Context context) {
        SubscriptionInfo subscriptionInfo;
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        if (subscriptionManager == null || (subscriptionInfo = getSubscriptionInfo(subscriptionManager, SubscriptionManager.getDefaultSubscriptionId())) == null) {
            return getOperatorNameFromTelephonyManager(context);
        }
        return subscriptionInfo.getCarrierName();
    }

    private static SubscriptionInfo getSubscriptionInfo(SubscriptionManager subscriptionManager, int i) {
        List<SubscriptionInfo> accessibleSubscriptionInfoList = subscriptionManager.getAccessibleSubscriptionInfoList();
        if (accessibleSubscriptionInfoList == null) {
            accessibleSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
        }
        if (accessibleSubscriptionInfoList == null) {
            return null;
        }
        for (SubscriptionInfo next : accessibleSubscriptionInfoList) {
            if (next.getSubscriptionId() == i) {
                return next;
            }
        }
        return null;
    }

    private static String getOperatorNameFromTelephonyManager(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }

    private static boolean isEsimSupportedCountry(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        return Arrays.asList(TextUtils.split(str2.toLowerCase(), ",")).contains(str);
    }

    private static boolean isEsimUnsupportedCountry(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return Arrays.asList(TextUtils.split(str2.toLowerCase(), ",")).contains(str);
    }
}
