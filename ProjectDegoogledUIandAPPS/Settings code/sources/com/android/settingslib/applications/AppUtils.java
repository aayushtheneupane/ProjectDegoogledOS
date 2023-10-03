package com.android.settingslib.applications;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.IUsbManager;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import com.android.settingslib.R$string;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.applications.instantapps.InstantAppDataProvider;
import java.util.ArrayList;

public class AppUtils {
    private static InstantAppDataProvider sInstantAppDataProvider;

    public static CharSequence getLaunchByDefaultSummary(ApplicationsState.AppEntry appEntry, IUsbManager iUsbManager, PackageManager packageManager, Context context) {
        int i;
        String str = appEntry.info.packageName;
        boolean z = false;
        boolean z2 = hasPreferredActivities(packageManager, str) || hasUsbDefaults(iUsbManager, str);
        if (packageManager.getIntentVerificationStatusAsUser(str, UserHandle.myUserId()) != 0) {
            z = true;
        }
        if (z2 || z) {
            i = R$string.launch_defaults_some;
        } else {
            i = R$string.launch_defaults_none;
        }
        return context.getString(i);
    }

    public static boolean hasUsbDefaults(IUsbManager iUsbManager, String str) {
        if (iUsbManager == null) {
            return false;
        }
        try {
            return iUsbManager.hasDefaults(str, UserHandle.myUserId());
        } catch (RemoteException e) {
            Log.e("AppUtils", "mUsbManager.hasDefaults", e);
            return false;
        }
    }

    public static boolean hasPreferredActivities(PackageManager packageManager, String str) {
        ArrayList arrayList = new ArrayList();
        packageManager.getPreferredActivities(new ArrayList(), arrayList, str);
        Log.d("AppUtils", "Have " + arrayList.size() + " number of activities in preferred list");
        return arrayList.size() > 0;
    }

    public static boolean isInstant(ApplicationInfo applicationInfo) {
        String[] split;
        InstantAppDataProvider instantAppDataProvider = sInstantAppDataProvider;
        if (instantAppDataProvider != null) {
            if (instantAppDataProvider.isInstantApp(applicationInfo)) {
                return true;
            }
        } else if (applicationInfo.isInstantApp()) {
            return true;
        }
        String str = SystemProperties.get("settingsdebug.instant.packages");
        if (!(str == null || str.isEmpty() || applicationInfo.packageName == null || (split = str.split(",")) == null)) {
            for (String contains : split) {
                if (applicationInfo.packageName.contains(contains)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static CharSequence getApplicationLabel(PackageManager packageManager, String str) {
        try {
            return packageManager.getApplicationInfo(str, 4194816).loadLabel(packageManager);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("AppUtils", "Unable to find info for package: " + str);
            return null;
        }
    }

    public static boolean isHiddenSystemModule(Context context, String str) {
        return ApplicationsState.getInstance((Application) context.getApplicationContext()).isHiddenModule(str);
    }

    public static boolean isSystemModule(Context context, String str) {
        return ApplicationsState.getInstance((Application) context.getApplicationContext()).isSystemModule(str);
    }
}
