package com.android.systemui.havoc.screenstate;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ThreeGToggle extends ScreenStateToggle {
    public ThreeGToggle(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public boolean isEnabled() {
        if (!((ConnectivityManager) this.mContext.getSystemService("connectivity")).isNetworkSupported(0)) {
            Log.d("ScreenStateService_ThreeGToggle", "Data not enabled");
            return false;
        } else if (Settings.System.getInt(this.mContext.getContentResolver(), "screen_state_threeg", 0) != 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean doScreenOnAction() {
        return this.mDoAction;
    }

    /* access modifiers changed from: protected */
    public boolean doScreenOffAction() {
        if (isNotThreeGMode()) {
            this.mDoAction = true;
        } else {
            this.mDoAction = false;
        }
        return this.mDoAction;
    }

    private int getCurrentPreferredNetworkMode() {
        int i;
        try {
            int i2 = Settings.Global.getInt(this.mContext.getContentResolver(), "multi_sim_data_call");
            if (i2 != 0) {
                ContentResolver contentResolver = this.mContext.getContentResolver();
                i = Settings.Global.getInt(contentResolver, "preferred_network_mode" + i2);
            } else {
                i = Settings.Global.getInt(this.mContext.getContentResolver(), "preferred_network_mode");
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            i = -1;
        }
        Log.d("ScreenStateService_ThreeGToggle", "preferred SIM data network: " + i);
        return i;
    }

    private boolean isNotThreeGMode() {
        return getCurrentPreferredNetworkMode() != 3;
    }

    /* access modifiers changed from: protected */
    public Runnable getScreenOffAction() {
        return new Runnable() {
            public void run() {
                Log.d("ScreenStateService_ThreeGToggle", "3G = true");
                ((TelephonyManager) ThreeGToggle.this.mContext.getSystemService("phone")).toggle3G(true);
            }
        };
    }

    /* access modifiers changed from: protected */
    public Runnable getScreenOnAction() {
        return new Runnable() {
            public void run() {
                Log.d("ScreenStateService_ThreeGToggle", "3G = false");
                ((TelephonyManager) ThreeGToggle.this.mContext.getSystemService("phone")).toggle3G(false);
            }
        };
    }
}
