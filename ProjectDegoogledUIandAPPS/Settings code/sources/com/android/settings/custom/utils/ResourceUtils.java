package com.android.settings.custom.utils;

import android.content.res.Resources;
import android.util.Log;

public class ResourceUtils {
    private static final String TAG = "ResourceUtils";

    public static String getLocalizedString(Resources resources, String str, String str2) {
        return getStringForResourceName(resources, String.format(str2, new Object[]{str.toLowerCase().replace(" ", "_")}), str);
    }

    public static String getStringForResourceName(Resources resources, String str, String str2) {
        int identifier = resources.getIdentifier(str, "string", "com.android.settings");
        if (identifier > 0) {
            return resources.getString(identifier);
        }
        String str3 = TAG;
        Log.e(str3, "No resource found for " + str);
        return str2;
    }
}
