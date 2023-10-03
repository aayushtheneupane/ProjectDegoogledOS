package com.android.systemui.p006qs.tiles;

import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.policy.BatteryController;

/* renamed from: com.android.systemui.qs.tiles.SmartPixelsTile */
public class SmartPixelsTile extends QSTileImpl<QSTile.BooleanState> implements BatteryController.BatteryStateChangeCallback {
    private static final Intent SMART_PIXELS_SETTINGS = new Intent().setComponent(SMART_PIXELS_SETTING_COMPONENT);
    private static final ComponentName SMART_PIXELS_SETTING_COMPONENT = new ComponentName("com.android.settings", "com.android.settings.Settings$SmartPixelsActivity");
    private final BatteryController mBatteryController = ((BatteryController) Dependency.get(BatteryController.class));
    private boolean mLowPowerMode;
    private boolean mSmartPixelsEnable;
    private boolean mSmartPixelsOnPowerSave;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onBatteryLevelChanged(int i, boolean z, boolean z2) {
    }

    public SmartPixelsTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleSetListening(boolean z) {
        if (z) {
            this.mBatteryController.addCallback(this);
        } else {
            this.mBatteryController.removeCallback(this);
        }
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(17891460);
    }

    public void handleClick() {
        this.mSmartPixelsEnable = Settings.System.getIntForUser(this.mContext.getContentResolver(), "smart_pixels_enable", 0, -2) == 1;
        this.mSmartPixelsOnPowerSave = Settings.System.getIntForUser(this.mContext.getContentResolver(), "smart_pixels_on_power_save", 0, -2) == 1;
        if (this.mLowPowerMode && this.mSmartPixelsOnPowerSave) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "smart_pixels_on_power_save", 0, -2);
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "smart_pixels_enable", 0, -2);
        } else if (!this.mSmartPixelsEnable) {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "smart_pixels_enable", 1, -2);
        } else {
            Settings.System.putIntForUser(this.mContext.getContentResolver(), "smart_pixels_enable", 0, -2);
        }
        refreshState();
    }

    public Intent getLongClickIntent() {
        return SMART_PIXELS_SETTINGS;
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int i = 1;
        this.mSmartPixelsEnable = Settings.System.getIntForUser(this.mContext.getContentResolver(), "smart_pixels_enable", 0, -2) == 1;
        this.mSmartPixelsOnPowerSave = Settings.System.getIntForUser(this.mContext.getContentResolver(), "smart_pixels_on_power_save", 0, -2) == 1;
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_smart_pixels);
        if (booleanState.slash == null) {
            booleanState.slash = new QSTile.SlashState();
        }
        if (this.mLowPowerMode && this.mSmartPixelsOnPowerSave) {
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_smart_pixels_on_power_save);
            booleanState.value = true;
        } else if (this.mSmartPixelsEnable) {
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_smart_pixels);
            booleanState.value = true;
        } else {
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_smart_pixels);
            booleanState.value = false;
        }
        QSTile.SlashState slashState = booleanState.slash;
        boolean z = booleanState.value;
        slashState.isSlashed = !z;
        if (z) {
            i = 2;
        }
        booleanState.state = i;
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_smart_pixels);
    }

    public void onPowerSaveChanged(boolean z) {
        this.mLowPowerMode = z;
        refreshState();
    }
}
