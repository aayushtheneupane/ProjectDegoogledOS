package com.android.systemui.p006qs.tiles;

import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import com.android.internal.util.havoc.Utils;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.NavBarTile */
public class NavBarTile extends QSTileImpl<QSTile.BooleanState> {
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_navbar);

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public NavBarTile(QSHost qSHost) {
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
        return this.mContext.getString(C1784R$string.quick_settings_navbar_title);
    }

    public Intent getLongClickIntent() {
        return new Intent().setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$ButtonSettingsActivity"));
    }

    private void setEnabled(boolean z) {
        Settings.System.putInt(this.mContext.getContentResolver(), "navigation_bar_show_new", z ? 1 : 0);
    }

    private boolean isNavbarEnabled() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "navigation_bar_show_new", Utils.deviceSupportNavigationBar(this.mContext) ? 1 : 0) == 1;
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        if (booleanState.slash == null) {
            booleanState.slash = new QSTile.SlashState();
        }
        booleanState.value = isNavbarEnabled();
        booleanState.icon = this.mIcon;
        booleanState.slash.isSlashed = !booleanState.value;
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_navbar_title);
        if (isNavbarEnabled()) {
            booleanState.state = 2;
        } else {
            booleanState.state = 1;
        }
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_navbar_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_navbar_off);
    }
}
