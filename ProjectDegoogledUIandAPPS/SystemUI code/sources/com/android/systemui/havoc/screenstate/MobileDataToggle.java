package com.android.systemui.havoc.screenstate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MobileDataToggle extends ScreenStateToggle {
    public MobileDataToggle(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public boolean isEnabled() {
        if (((ConnectivityManager) this.mContext.getSystemService("connectivity")).isNetworkSupported(0) && Settings.System.getIntForUser(this.mContext.getContentResolver(), "screen_state_mobile_data", 0, -2) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean doScreenOnAction() {
        return this.mDoAction;
    }

    /* access modifiers changed from: protected */
    public boolean doScreenOffAction() {
        if (isMobileDataEnabled()) {
            this.mDoAction = true;
        } else {
            this.mDoAction = false;
        }
        return this.mDoAction;
    }

    private boolean isMobileDataEnabled() {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).getDataEnabled();
    }

    /* access modifiers changed from: private */
    public void setMobileDataState(boolean z) {
        ((TelephonyManager) this.mContext.getSystemService("phone")).setDataEnabled(z);
    }

    /* access modifiers changed from: protected */
    public Runnable getScreenOffAction() {
        return new Runnable() {
            public void run() {
                MobileDataToggle.this.setMobileDataState(false);
                Log.d("ScreenStateService_MobileDataToggle", "mobileData = false");
            }
        };
    }

    /* access modifiers changed from: protected */
    public Runnable getScreenOnAction() {
        return new Runnable() {
            public void run() {
                MobileDataToggle.this.setMobileDataState(true);
                Log.d("ScreenStateService_MobileDataToggle", "mobileData = true");
            }
        };
    }
}
