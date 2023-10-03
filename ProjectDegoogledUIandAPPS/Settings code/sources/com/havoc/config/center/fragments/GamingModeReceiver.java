package com.havoc.config.center.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class GamingModeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.GAMING_MODE_TURN_OFF")) {
            Settings.System.putInt(context.getContentResolver(), "gaming_mode_active", 0);
        } else if (intent.getAction() != null && intent.getAction().equals("android.intent.action.GAMING_MODE_TURN_ON")) {
            Settings.System.putInt(context.getContentResolver(), "gaming_mode_active", 1);
        }
    }
}
