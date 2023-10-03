package com.android.settings.datausage;

import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkTemplate;
import android.os.RemoteException;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.BidiFormatter;
import android.text.format.Formatter;
import android.util.Log;
import java.util.List;

public final class DataUsageUtils extends com.android.settingslib.net.DataUsageUtils {
    public static CharSequence formatDataUsage(Context context, long j) {
        Formatter.BytesResult formatBytes = Formatter.formatBytes(context.getResources(), j, 8);
        return BidiFormatter.getInstance().unicodeWrap(context.getString(17040051, new Object[]{formatBytes.value, formatBytes.units}));
    }

    public static boolean hasEthernet(Context context) {
        if (!ConnectivityManager.from(context).isNetworkSupported(9)) {
            return false;
        }
        TelephonyManager from = TelephonyManager.from(context);
        try {
            NetworkStats.Bucket querySummaryForUser = ((NetworkStatsManager) context.getSystemService(NetworkStatsManager.class)).querySummaryForUser(9, from.getSubscriberId(), 0, System.currentTimeMillis());
            if (querySummaryForUser == null) {
                return false;
            }
            if (querySummaryForUser.getRxBytes() > 0 || querySummaryForUser.getTxBytes() > 0) {
                return true;
            }
            return false;
        } catch (RemoteException e) {
            Log.e("DataUsageUtils", "Exception querying network detail.", e);
            return false;
        }
    }

    public static boolean hasMobileData(Context context) {
        ConnectivityManager from = ConnectivityManager.from(context);
        if (from == null || !from.isNetworkSupported(0)) {
            return false;
        }
        return true;
    }

    public static boolean hasReadyMobileRadio(Context context) {
        List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null) {
            return false;
        }
        TelephonyManager from = TelephonyManager.from(context);
        boolean z = true;
        for (SubscriptionInfo simSlotIndex : activeSubscriptionInfoList) {
            z &= from.getSimState(simSlotIndex.getSimSlotIndex()) == 5;
        }
        if (!ConnectivityManager.from(context).isNetworkSupported(0) || !z) {
            return false;
        }
        return true;
    }

    public static boolean hasWifiRadio(Context context) {
        ConnectivityManager from = ConnectivityManager.from(context);
        if (from == null || !from.isNetworkSupported(1)) {
            return false;
        }
        return true;
    }

    public static boolean hasSim(Context context) {
        int simState = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getSimState();
        return (simState == 1 || simState == 0) ? false : true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.telephony.SubscriptionInfo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getDefaultSubscriptionId(android.content.Context r2) {
        /*
            android.telephony.SubscriptionManager r2 = android.telephony.SubscriptionManager.from(r2)
            r0 = -1
            if (r2 != 0) goto L_0x0008
            return r0
        L_0x0008:
            android.telephony.SubscriptionInfo r1 = r2.getDefaultDataSubscriptionInfo()
            if (r1 != 0) goto L_0x0021
            java.util.List r2 = r2.getAllSubscriptionInfoList()
            int r1 = r2.size()
            if (r1 != 0) goto L_0x0019
            return r0
        L_0x0019:
            r0 = 0
            java.lang.Object r2 = r2.get(r0)
            r1 = r2
            android.telephony.SubscriptionInfo r1 = (android.telephony.SubscriptionInfo) r1
        L_0x0021:
            int r2 = r1.getSubscriptionId()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.datausage.DataUsageUtils.getDefaultSubscriptionId(android.content.Context):int");
    }

    public static NetworkTemplate getDefaultTemplate(Context context, int i) {
        if (hasMobileData(context) && i != -1) {
            return com.android.settingslib.net.DataUsageUtils.getMobileTemplate(context, i);
        }
        if (hasWifiRadio(context)) {
            return NetworkTemplate.buildTemplateWifiWildcard();
        }
        return NetworkTemplate.buildTemplateEthernet();
    }
}
