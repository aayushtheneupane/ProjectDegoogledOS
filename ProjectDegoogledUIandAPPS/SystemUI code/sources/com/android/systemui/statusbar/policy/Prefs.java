package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    public static SharedPreferences read(Context context) {
        return context.getSharedPreferences("status_bar", 0);
    }

    public static SharedPreferences.Editor edit(Context context) {
        return context.getSharedPreferences("status_bar", 0).edit();
    }

    public static void setLastBatteryLevel(Context context, int i) {
        edit(context).putInt("last_battery_level", i).commit();
    }

    public static int getLastBatteryLevel(Context context) {
        return read(context).getInt("last_battery_level", 50);
    }
}
