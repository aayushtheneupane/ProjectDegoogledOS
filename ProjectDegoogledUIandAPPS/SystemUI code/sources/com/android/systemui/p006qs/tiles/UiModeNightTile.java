package com.android.systemui.p006qs.tiles;

import android.app.UiModeManager;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;

/* renamed from: com.android.systemui.qs.tiles.UiModeNightTile */
public class UiModeNightTile extends QSTileImpl<QSTile.BooleanState> implements ConfigurationController.ConfigurationListener, BatteryController.BatteryStateChangeCallback {
    private final BatteryController mBatteryController;
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(17302796);
    private final UiModeManager mUiModeManager;

    public int getMetricsCategory() {
        return 1706;
    }

    /* access modifiers changed from: protected */
    public void handleSetListening(boolean z) {
    }

    public UiModeNightTile(QSHost qSHost, ConfigurationController configurationController, BatteryController batteryController) {
        super(qSHost);
        this.mBatteryController = batteryController;
        this.mUiModeManager = (UiModeManager) this.mContext.getSystemService(UiModeManager.class);
        configurationController.observe(getLifecycle(), this);
        batteryController.observe(getLifecycle(), this);
    }

    public void onUiModeChanged() {
        refreshState();
    }

    public void onPowerSaveChanged(boolean z) {
        refreshState();
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (((QSTile.BooleanState) getState()).state != 0) {
            boolean z = !((QSTile.BooleanState) this.mState).value;
            this.mUiModeManager.setNightModeActivated(z);
            refreshState(Boolean.valueOf(z));
        }
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        CharSequence charSequence;
        int i;
        int nightMode = this.mUiModeManager.getNightMode();
        boolean isPowerSave = this.mBatteryController.isPowerSave();
        boolean z = nightMode == 0;
        boolean z2 = (this.mContext.getResources().getConfiguration().uiMode & 48) == 32;
        if (isPowerSave) {
            booleanState.secondaryLabel = this.mContext.getResources().getString(C1784R$string.quick_settings_dark_mode_secondary_label_battery_saver);
        } else if (z) {
            Resources resources = this.mContext.getResources();
            if (z2) {
                i = C1784R$string.quick_settings_dark_mode_secondary_label_until_sunrise;
            } else {
                i = C1784R$string.quick_settings_dark_mode_secondary_label_on_at_sunset;
            }
            booleanState.secondaryLabel = resources.getString(i);
        } else {
            booleanState.secondaryLabel = null;
        }
        booleanState.value = z2;
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_ui_mode_night_label);
        booleanState.icon = this.mIcon;
        int i2 = 2;
        if (TextUtils.isEmpty(booleanState.secondaryLabel)) {
            charSequence = booleanState.label;
        } else {
            charSequence = TextUtils.concat(new CharSequence[]{booleanState.label, ", ", booleanState.secondaryLabel});
        }
        booleanState.contentDescription = charSequence;
        if (isPowerSave) {
            booleanState.state = 0;
        } else {
            if (!booleanState.value) {
                i2 = 1;
            }
            booleanState.state = i2;
        }
        booleanState.showRippleEffect = false;
    }

    public Intent getLongClickIntent() {
        return new Intent("android.settings.DARK_THEME_SETTINGS");
    }

    public CharSequence getTileLabel() {
        return ((QSTile.BooleanState) getState()).label;
    }
}
