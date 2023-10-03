package com.android.systemui.havoc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import com.android.systemui.VendorServices;
import java.util.ArrayList;

public class AppPickerServices extends VendorServices {
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                String string = Settings.System.getString(context.getContentResolver(), "key_back_long_press_custom_app");
                String string2 = Settings.System.getString(context.getContentResolver(), "key_back_double_tap_custom_app");
                String string3 = Settings.System.getString(context.getContentResolver(), "key_home_long_press_custom_app");
                String string4 = Settings.System.getString(context.getContentResolver(), "key_home_double_tap_custom_app");
                String string5 = Settings.System.getString(context.getContentResolver(), "key_app_switch_long_press_custom_app");
                String string6 = Settings.System.getString(context.getContentResolver(), "key_app_switch_double_tap_custom_app");
                if (schemeSpecificPart.equals(string)) {
                    AppPickerServices.this.resetAction("key_back_long_press_action", "key_back_long_press_custom_app_fr_name");
                }
                if (schemeSpecificPart.equals(string2)) {
                    AppPickerServices.this.resetAction("key_back_double_tap_action", "key_back_double_tap_custom_app_fr_name");
                }
                if (schemeSpecificPart.equals(string3)) {
                    AppPickerServices.this.resetAction("key_home_long_press_action", "key_home_long_press_custom_app_fr_name");
                }
                if (schemeSpecificPart.equals(string4)) {
                    AppPickerServices.this.resetAction("key_home_double_tap_action", "key_home_double_tap_custom_app_fr_name");
                }
                if (schemeSpecificPart.equals(string5)) {
                    AppPickerServices.this.resetAction("key_home_long_press_action", "key_home_long_press_custom_app_fr_name");
                }
                if (schemeSpecificPart.equals(string6)) {
                    AppPickerServices.this.resetAction("key_app_switch_double_tap_action", "key_app_switch_double_tap_custom_app_fr_name");
                }
            }
        }
    };
    private ArrayList<Object> mServices = new ArrayList<>();

    /* access modifiers changed from: private */
    public void resetAction(String str, String str2) {
        Settings.System.putInt(this.mContext.getContentResolver(), str, 0);
        Settings.System.putString(this.mContext.getContentResolver(), str2, "");
    }

    public void start() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter);
    }
}
