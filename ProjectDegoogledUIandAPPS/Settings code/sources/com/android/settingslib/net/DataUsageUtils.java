package com.android.settingslib.net;

import android.content.Context;
import android.net.NetworkTemplate;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;
import com.android.internal.util.ArrayUtils;

public class DataUsageUtils {
    public static NetworkTemplate getMobileTemplate(Context context, int i) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        NetworkTemplate buildTemplateMobileAll = NetworkTemplate.buildTemplateMobileAll(telephonyManager.getSubscriberId(i));
        if (!((SubscriptionManager) context.getSystemService(SubscriptionManager.class)).isActiveSubId(i)) {
            Log.i("DataUsageUtils", "Subscription is not active: " + i);
            return buildTemplateMobileAll;
        }
        String[] mergedSubscriberIdsFromGroup = telephonyManager.createForSubscriptionId(i).getMergedSubscriberIdsFromGroup();
        if (!ArrayUtils.isEmpty(mergedSubscriberIdsFromGroup)) {
            return NetworkTemplate.normalize(buildTemplateMobileAll, mergedSubscriberIdsFromGroup);
        }
        Log.i("DataUsageUtils", "mergedSubscriberIds is null.");
        return buildTemplateMobileAll;
    }

    public static long getTodayMillis() {
        Time time = new Time();
        time.set(System.currentTimeMillis());
        return (long) (((time.hour * 60 * 60) + (time.minute * 60) + time.second) * 1000);
    }
}
