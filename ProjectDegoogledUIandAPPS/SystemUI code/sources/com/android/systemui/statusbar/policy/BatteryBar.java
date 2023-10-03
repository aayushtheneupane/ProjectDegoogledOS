package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.android.internal.util.havoc.Utils;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.DarkIconDispatcher;

public class BatteryBar extends RelativeLayout implements Animatable, DarkIconDispatcher.DarkReceiver {
    private boolean isAnimating;
    private boolean isDark;
    private boolean mAttached;
    View mBatteryBar;
    LinearLayout mBatteryBarLayout;
    /* access modifiers changed from: private */
    public boolean mBatteryCharging;
    /* access modifiers changed from: private */
    public int mBatteryLevel;
    private int mBatteryLowColor;
    private int mBatteryLowDarkColor;
    private boolean mBlendColorsReversed;
    private boolean mBlendDarkColorsReversed;
    View mCharger;
    LinearLayout mChargerLayout;
    private int mChargingColor;
    private int mChargingDarkColor;
    private int mChargingLevel;
    private int mColor;
    private int mDarkColor;
    private Handler mHandler;
    private final BroadcastReceiver mIntentReceiver;
    private boolean mUseChargingColor;
    private boolean shouldAnimateCharging;
    boolean vertical;

    class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observer() {
            ContentResolver contentResolver = BatteryBar.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_color"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_dark_color"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_charging_color"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_charging_dark_color"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_battery_low_color"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_battery_low_dark_color"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_animate"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_enable_charging_color"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_blend_color_reverse"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_battery_bar_blend_dark_color_reverse"), false, this);
        }

        public void onChange(boolean z) {
            BatteryBar.this.updateSettings();
        }
    }

    public BatteryBar(Context context, boolean z, int i, boolean z2) {
        this(context, (AttributeSet) null);
        this.mBatteryLevel = i;
        this.mBatteryCharging = z;
        this.vertical = z2;
    }

    public BatteryBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BatteryBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAttached = false;
        this.mBatteryLevel = 0;
        this.mChargingLevel = -1;
        this.mBatteryCharging = false;
        this.shouldAnimateCharging = true;
        this.isAnimating = false;
        this.mColor = -1;
        this.mChargingColor = -256;
        this.mBatteryLowColor = -1;
        this.mDarkColor = -1728053248;
        this.mChargingDarkColor = -15906911;
        this.mBatteryLowDarkColor = -1728053248;
        this.mUseChargingColor = true;
        this.mBlendColorsReversed = false;
        this.mBlendDarkColorsReversed = false;
        this.mHandler = new Handler();
        this.vertical = false;
        this.mIntentReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
                    boolean z = false;
                    int unused = BatteryBar.this.mBatteryLevel = intent.getIntExtra("level", 0);
                    BatteryBar batteryBar = BatteryBar.this;
                    if (intent.getIntExtra("status", 0) == 2) {
                        z = true;
                    }
                    boolean unused2 = batteryBar.mBatteryCharging = z;
                    if (!BatteryBar.this.mBatteryCharging || BatteryBar.this.mBatteryLevel >= 100) {
                        BatteryBar.this.stop();
                    } else {
                        BatteryBar.this.start();
                    }
                    BatteryBar batteryBar2 = BatteryBar.this;
                    batteryBar2.setProgress(batteryBar2.mBatteryLevel);
                } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    BatteryBar.this.stop();
                } else if ("android.intent.action.SCREEN_ON".equals(action) && BatteryBar.this.mBatteryCharging && BatteryBar.this.mBatteryLevel < 100) {
                    BatteryBar.this.start();
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            this.mBatteryBarLayout = new LinearLayout(this.mContext);
            addView(this.mBatteryBarLayout, new RelativeLayout.LayoutParams(-1, -1));
            this.mBatteryBar = new View(this.mContext);
            this.mBatteryBarLayout.addView(this.mBatteryBar, new LinearLayout.LayoutParams(-1, -1));
            int i = (int) ((getContext().getResources().getDisplayMetrics().density * 4.0f) + 0.5f);
            this.mChargerLayout = new LinearLayout(this.mContext);
            if (this.vertical) {
                addView(this.mChargerLayout, new RelativeLayout.LayoutParams(-1, i));
            } else {
                addView(this.mChargerLayout, new RelativeLayout.LayoutParams(i, -1));
            }
            this.mCharger = new View(this.mContext);
            this.mChargerLayout.setVisibility(8);
            this.mChargerLayout.addView(this.mCharger, new LinearLayout.LayoutParams(-1, -1));
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            getContext().registerReceiver(this.mIntentReceiver, intentFilter, (String) null, getHandler());
            new SettingsObserver(this.mHandler).observer();
            updateSettings();
        }
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).addDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttached) {
            this.mAttached = false;
            getContext().unregisterReceiver(this.mIntentReceiver);
        }
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).removeDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
    }

    /* access modifiers changed from: private */
    public void updateSettings() {
        ContentResolver contentResolver = getContext().getContentResolver();
        this.mColor = Settings.System.getInt(contentResolver, "statusbar_battery_bar_color", -1);
        this.mDarkColor = Settings.System.getInt(contentResolver, "statusbar_battery_bar_dark_color", -1728053248);
        this.mChargingColor = Settings.System.getInt(contentResolver, "statusbar_battery_bar_charging_color", -256);
        this.mChargingDarkColor = Settings.System.getInt(contentResolver, "statusbar_battery_bar_charging_dark_color", -15906911);
        this.mBatteryLowColor = Settings.System.getInt(contentResolver, "statusbar_battery_bar_battery_low_color", -1);
        this.mBatteryLowDarkColor = Settings.System.getInt(contentResolver, "statusbar_battery_bar_battery_low_dark_color", -1728053248);
        boolean z = false;
        this.shouldAnimateCharging = Settings.System.getInt(contentResolver, "statusbar_battery_bar_animate", 0) == 1;
        this.mUseChargingColor = Settings.System.getInt(contentResolver, "statusbar_battery_bar_enable_charging_color", 1) == 1;
        if (Settings.System.getInt(contentResolver, "statusbar_battery_bar_blend_color_reverse", 0) == 1) {
            z = true;
        }
        this.mBlendColorsReversed = z;
        if (!this.mBatteryCharging || this.mBatteryLevel >= 100 || !this.shouldAnimateCharging) {
            stop();
        } else {
            start();
        }
        setProgress(this.mBatteryLevel);
    }

    /* access modifiers changed from: private */
    public void setProgress(int i) {
        if (this.vertical) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mBatteryBarLayout.getLayoutParams();
            layoutParams.height = (int) (((((double) getHeight()) / 100.0d) * ((double) i)) + 0.5d);
            this.mBatteryBarLayout.setLayoutParams(layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mBatteryBarLayout.getLayoutParams();
            layoutParams2.width = (int) (((((double) getWidth()) / 100.0d) * ((double) i)) + 0.5d);
            this.mBatteryBarLayout.setLayoutParams(layoutParams2);
        }
        int colorForPercent = getColorForPercent(i);
        this.mBatteryBar.setBackgroundColor(colorForPercent);
        this.mCharger.setBackgroundColor(colorForPercent);
    }

    public void start() {
        if (this.shouldAnimateCharging) {
            if (this.vertical) {
                TranslateAnimation translateAnimation = new TranslateAnimation(getX(), getX(), (float) getHeight(), (float) this.mBatteryBarLayout.getHeight());
                translateAnimation.setInterpolator(new AccelerateInterpolator());
                translateAnimation.setDuration(1000);
                translateAnimation.setRepeatCount(-1);
                this.mChargerLayout.startAnimation(translateAnimation);
                this.mChargerLayout.setVisibility(0);
            } else {
                TranslateAnimation translateAnimation2 = new TranslateAnimation((float) getWidth(), (float) this.mBatteryBarLayout.getWidth(), (float) getTop(), (float) getTop());
                translateAnimation2.setInterpolator(new AccelerateInterpolator());
                translateAnimation2.setDuration(1000);
                translateAnimation2.setRepeatCount(-1);
                this.mChargerLayout.startAnimation(translateAnimation2);
                this.mChargerLayout.setVisibility(0);
            }
            this.isAnimating = true;
        }
    }

    public void stop() {
        this.mChargerLayout.clearAnimation();
        this.mChargerLayout.setVisibility(8);
        this.isAnimating = false;
    }

    public boolean isRunning() {
        return this.isAnimating;
    }

    private int getColorForPercent(int i) {
        if (this.mBatteryCharging && this.mUseChargingColor) {
            return this.isDark ? this.mChargingDarkColor : this.mChargingColor;
        }
        if (this.isDark) {
            return Utils.getBlendColorForPercent(this.mDarkColor, this.mBatteryLowDarkColor, this.mBlendDarkColorsReversed, i);
        }
        return Utils.getBlendColorForPercent(this.mColor, this.mBatteryLowColor, this.mBlendColorsReversed, i);
    }

    public void onDarkChanged(Rect rect, float f, int i) {
        if (!DarkIconDispatcher.isInArea(rect, this)) {
            f = 0.0f;
        }
        this.isDark = ((double) f) > 0.5d;
        setProgress(this.mBatteryLevel);
    }
}
