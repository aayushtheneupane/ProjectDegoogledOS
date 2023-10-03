package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.provider.Settings;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.SecureSetting;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.policy.BatteryController;

/* renamed from: com.android.systemui.qs.tiles.AODTile */
public class AODTile extends QSTileImpl<QSTile.BooleanState> implements BatteryController.BatteryStateChangeCallback {
    private final BatteryController mBatteryController;
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_aod);
    private final SecureSetting mSetting = new SecureSetting(this.mContext, this.mHandler, "doze_always_on") {
        /* access modifiers changed from: protected */
        public void handleValueChanged(int i, boolean z) {
            AODTile.this.handleRefreshState(Integer.valueOf(i));
        }
    };

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public AODTile(QSHost qSHost, BatteryController batteryController) {
        super(qSHost);
        this.mBatteryController = batteryController;
        batteryController.observe(getLifecycle(), this);
    }

    public void onPowerSaveChanged(boolean z) {
        refreshState();
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(17891426);
    }

    public QSTile.BooleanState newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.handlesLongClick = false;
        return booleanState;
    }

    public void handleClick() {
        setEnabled(!((QSTile.BooleanState) this.mState).value);
        refreshState();
    }

    private void setEnabled(boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "doze_always_on", z ? 1 : 0);
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_aod_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int i = 1;
        boolean z = (obj instanceof Integer ? ((Integer) obj).intValue() : this.mSetting.getValue()) != 0;
        if (booleanState.slash == null) {
            booleanState.slash = new QSTile.SlashState();
        }
        booleanState.icon = this.mIcon;
        booleanState.value = z;
        booleanState.slash.isSlashed = booleanState.value;
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_aod_label);
        if (this.mBatteryController.isAodPowerSave()) {
            booleanState.state = 0;
            booleanState.secondaryLabel = this.mContext.getString(C1784R$string.quick_settings_aod_off_powersave_label);
            return;
        }
        if (z) {
            i = 2;
        }
        booleanState.state = i;
        booleanState.secondaryLabel = "";
    }
}
