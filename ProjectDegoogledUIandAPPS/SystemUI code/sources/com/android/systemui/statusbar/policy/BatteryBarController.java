package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class BatteryBarController extends LinearLayout {
    boolean isAttached = false;
    boolean isVertical = false;
    /* access modifiers changed from: private */
    public boolean mBatteryCharging = false;
    /* access modifiers changed from: private */
    public int mBatteryLevel = 0;
    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                boolean z = false;
                int unused = BatteryBarController.this.mBatteryLevel = intent.getIntExtra("level", 0);
                BatteryBarController batteryBarController = BatteryBarController.this;
                if (intent.getIntExtra("status", 0) == 2) {
                    z = true;
                }
                boolean unused2 = batteryBarController.mBatteryCharging = z;
                Prefs.setLastBatteryLevel(context, BatteryBarController.this.mBatteryLevel);
            }
        }
    };
    int mLocation = 0;
    int mLocationToLookFor = 0;
    int mStyle = 0;

    class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observer() {
            ContentResolver contentResolver = BatteryBarController.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_location"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_style"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_thickness"), false, this);
        }

        public void onChange(boolean z) {
            BatteryBarController.this.updateSettings();
        }
    }

    public BatteryBarController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet != null) {
            this.mLocationToLookFor = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/com.android.systemui", "viewLocation", 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.isAttached) {
            this.isVertical = getLayoutParams().height == -1;
            this.isAttached = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            getContext().registerReceiver(this.mIntentReceiver, intentFilter);
            new SettingsObserver(new Handler()).observer();
            updateSettings();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.isAttached) {
            this.isAttached = false;
            removeBars();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.isAttached) {
            getHandler().postDelayed(new Runnable() {
                public void run() {
                    BatteryBarController.this.updateSettings();
                }
            }, 500);
        }
    }

    public void addBars() {
        int i = (int) (((double) (getContext().getResources().getDisplayMetrics().density * ((float) Settings.System.getInt(getContext().getContentResolver(), "statusbar_battery_bar_thickness", 2)))) + 0.5d);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (this.isVertical) {
            layoutParams.width = i;
        } else {
            layoutParams.height = i;
        }
        setLayoutParams(layoutParams);
        if (this.isVertical) {
            layoutParams.width = i;
        } else {
            layoutParams.height = i;
        }
        setLayoutParams(layoutParams);
        this.mBatteryLevel = Prefs.getLastBatteryLevel(getContext());
        int i2 = this.mStyle;
        if (i2 == 0) {
            addView(new BatteryBar(this.mContext, this.mBatteryCharging, this.mBatteryLevel, this.isVertical), new LinearLayout.LayoutParams(-1, -1, 1.0f));
        } else if (i2 == 1) {
            BatteryBar batteryBar = new BatteryBar(this.mContext, this.mBatteryCharging, this.mBatteryLevel, this.isVertical);
            BatteryBar batteryBar2 = new BatteryBar(this.mContext, this.mBatteryCharging, this.mBatteryLevel, this.isVertical);
            if (this.isVertical) {
                batteryBar2.setRotation(180.0f);
                addView(batteryBar2, new LinearLayout.LayoutParams(-1, -1, 1.0f));
                addView(batteryBar, new LinearLayout.LayoutParams(-1, -1, 1.0f));
                return;
            }
            batteryBar.setRotation(180.0f);
            addView(batteryBar, new LinearLayout.LayoutParams(-1, -1, 1.0f));
            addView(batteryBar2, new LinearLayout.LayoutParams(-1, -1, 1.0f));
        } else if (i2 == 2) {
            BatteryBar batteryBar3 = new BatteryBar(this.mContext, this.mBatteryCharging, this.mBatteryLevel, this.isVertical);
            batteryBar3.setRotation(180.0f);
            addView(batteryBar3, new LinearLayout.LayoutParams(-1, -1, 1.0f));
        }
    }

    public void removeBars() {
        removeAllViews();
    }

    public void updateSettings() {
        this.mStyle = Settings.System.getInt(getContext().getContentResolver(), "statusbar_battery_bar_style", 0);
        if (Settings.System.getInt(getContext().getContentResolver(), "statusbar_battery_bar", 0) != 0) {
            this.mLocation = Settings.System.getInt(getContext().getContentResolver(), "statusbar_battery_bar_location", 1);
        } else {
            this.mLocation = 0;
        }
        int i = this.mLocation;
        if (i <= 0 || !isLocationValid(i)) {
            removeBars();
            return;
        }
        removeBars();
        addBars();
    }

    /* access modifiers changed from: protected */
    public boolean isLocationValid(int i) {
        return this.mLocationToLookFor == i;
    }
}
