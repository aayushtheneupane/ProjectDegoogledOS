package com.android.settings.deviceinfo.firmwareversion;

public class BasebandFormatter {
    public static String getFormattedBaseband(String str) {
        if (!str.contains(",")) {
            return str;
        }
        String[] split = str.split(",");
        return (split.length <= 1 || !split[0].equals(split[1])) ? str : split[0];
    }
}
