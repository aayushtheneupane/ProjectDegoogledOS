package androidx.appcompat.mms;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import java.net.MalformedURLException;
import java.net.URL;

class Utils {
    Utils() {
    }

    static int getEffectiveSubscriptionId(int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i == -1) {
            i = SmsManager.getDefaultSmsSubscriptionId();
        }
        if (i < 0) {
            return -1;
        }
        return i;
    }

    static int[] getMccMnc(Context context, int i) {
        int[] iArr = {0, 0};
        int i2 = Build.VERSION.SDK_INT;
        SubscriptionInfo activeSubscriptionInfo = SubscriptionManager.from(context).getActiveSubscriptionInfo(i);
        if (activeSubscriptionInfo != null) {
            iArr[0] = activeSubscriptionInfo.getMcc();
            iArr[1] = activeSubscriptionInfo.getMnc();
        }
        return iArr;
    }

    static SmsManager getSmsManager(int i) {
        int i2 = Build.VERSION.SDK_INT;
        return SmsManager.getSmsManagerForSubscriptionId(i);
    }

    static Context getSubDepContext(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        int[] mccMnc = getMccMnc(context, i);
        int i3 = mccMnc[0];
        int i4 = mccMnc[1];
        if (i3 == 0 && i4 == 0) {
            return context;
        }
        Configuration configuration = new Configuration();
        configuration.mcc = i3;
        configuration.mnc = i4;
        return context.createConfigurationContext(configuration);
    }

    static boolean hasMmsApi() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    static boolean hasUserAgentApi() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    static String redactUrlForNonVerbose(String str) {
        String str2;
        if (Log.isLoggable("MmsLib", 2) || TextUtils.isEmpty(str)) {
            return str;
        }
        String str3 = "http";
        try {
            URL url = new URL(str);
            str3 = url.getProtocol();
            str2 = url.getHost();
        } catch (MalformedURLException unused) {
            str2 = "";
        }
        return str3 + "://" + str2 + "[" + str.length() + "]";
    }

    static boolean supportMSim() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }
}
