package com.android.settings.development.featureflags;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.FeatureFlagUtils;
import java.util.HashSet;

public class FeatureFlagPersistent {
    private static final HashSet<String> PERSISTENT_FLAGS = new HashSet<>();

    static {
        PERSISTENT_FLAGS.add("settings_bluetooth_hearing_aid");
        PERSISTENT_FLAGS.add("settings_network_and_internet_v2");
        PERSISTENT_FLAGS.add("settings_dynamic_system");
    }

    public static boolean isEnabled(Context context, String str) {
        String str2 = SystemProperties.get("persist.sys.fflag.override." + str);
        if (!TextUtils.isEmpty(str2)) {
            return Boolean.parseBoolean(str2);
        }
        return FeatureFlagUtils.isEnabled(context, str);
    }

    public static void setEnabled(Context context, String str, boolean z) {
        SystemProperties.set("persist.sys.fflag.override." + str, z ? "true" : "false");
        FeatureFlagUtils.setEnabled(context, str, z);
    }

    public static boolean isPersistent(String str) {
        return PERSISTENT_FLAGS.contains(str);
    }

    static HashSet<String> getAllPersistentFlags() {
        return PERSISTENT_FLAGS;
    }
}
