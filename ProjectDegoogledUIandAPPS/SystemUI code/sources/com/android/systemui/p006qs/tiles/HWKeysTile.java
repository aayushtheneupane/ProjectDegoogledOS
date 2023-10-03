package com.android.systemui.p006qs.tiles;

import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.HWKeysTile */
public class HWKeysTile extends QSTileImpl<QSTile.BooleanState> {
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_hwkeys);

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public HWKeysTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        setEnabled(!((QSTile.BooleanState) this.mState).value);
        refreshState();
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_hwkeys_label);
    }

    public Intent getLongClickIntent() {
        return new Intent().setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$ButtonSettingsActivity"));
    }

    private void setEnabled(boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "hardware_keys_enable", z ? 1 : 0);
    }

    private boolean isHWKeysEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "hardware_keys_enable", 1) == 1;
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        if (booleanState.slash == null) {
            booleanState.slash = new QSTile.SlashState();
        }
        booleanState.value = isHWKeysEnabled();
        booleanState.icon = this.mIcon;
        booleanState.slash.isSlashed = !booleanState.value;
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_hwkeys_label);
        if (isHWKeysEnabled()) {
            booleanState.state = 2;
        } else {
            booleanState.state = 1;
        }
    }

    public boolean isAvailable() {
        int integer = this.mContext.getResources().getInteger(17694793);
        return ((integer & 1) != 0) || ((integer & 2) != 0) || ((integer & 4) != 0) || ((integer & 16) != 0);
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_hwkeys_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_hwkeys_off);
    }
}
