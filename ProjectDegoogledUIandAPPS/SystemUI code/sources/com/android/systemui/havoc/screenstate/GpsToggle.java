package com.android.systemui.havoc.screenstate;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

public class GpsToggle extends ScreenStateToggle {
    public GpsToggle(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public boolean isEnabled() {
        return Settings.System.getIntForUser(this.mContext.getContentResolver(), "screen_state_gps", 0, -2) != 0;
    }

    /* access modifiers changed from: protected */
    public boolean doScreenOnAction() {
        return this.mDoAction;
    }

    /* access modifiers changed from: protected */
    public boolean doScreenOffAction() {
        if (isGpsEnabled()) {
            this.mDoAction = true;
        } else {
            this.mDoAction = false;
        }
        return this.mDoAction;
    }

    private boolean isGpsEnabled() {
        return Settings.Secure.isLocationProviderEnabled(this.mContext.getContentResolver(), "gps");
    }

    /* access modifiers changed from: protected */
    public Runnable getScreenOffAction() {
        return new Runnable() {
            public void run() {
                Settings.Secure.setLocationProviderEnabled(GpsToggle.this.mContext.getContentResolver(), "gps", false);
                Log.d("ScreenStateService_GpsToggle", "gps = false");
            }
        };
    }

    /* access modifiers changed from: protected */
    public Runnable getScreenOnAction() {
        return new Runnable() {
            public void run() {
                Settings.Secure.setLocationProviderEnabled(GpsToggle.this.mContext.getContentResolver(), "gps", true);
                Log.d("ScreenStateService_GpsToggle", "gps = true");
            }
        };
    }
}
