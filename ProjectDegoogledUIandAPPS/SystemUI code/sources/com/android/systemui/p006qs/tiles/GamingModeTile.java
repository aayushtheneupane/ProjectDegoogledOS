package com.android.systemui.p006qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.SysUIToast;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.SystemSetting;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.GamingModeTile */
public class GamingModeTile extends QSTileImpl<QSTile.BooleanState> {
    private static final Intent GAMING_MODE_SETTINGS = new Intent("android.settings.GAMING_MODE_SETTINGS");
    private final SystemSetting mGamingModeActivated = new SystemSetting(this.mContext, this.mHandler, "gaming_mode_active") {
        /* access modifiers changed from: protected */
        public void handleValueChanged(int i, boolean z) {
            GamingModeTile.this.handleRefreshState(Integer.valueOf(i));
        }
    };
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_gaming_mode);

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public boolean isAvailable() {
        return true;
    }

    public GamingModeTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        boolean z = false;
        if (Settings.System.getInt(this.mContext.getContentResolver(), "gaming_mode_enabled", 0) == 1) {
            z = true;
        }
        this.mHost.collapsePanels();
        if (z) {
            this.mGamingModeActivated.setValue(((QSTile.BooleanState) this.mState).value ^ true ? 1 : 0);
        } else {
            Context context = this.mContext;
            SysUIToast.makeText(context, (CharSequence) context.getString(C1784R$string.gaming_mode_not_enabled), 1).show();
        }
        refreshState();
    }

    public Intent getLongClickIntent() {
        return GAMING_MODE_SETTINGS;
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_gaming_mode_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean z = (obj instanceof Integer ? ((Integer) obj).intValue() : this.mGamingModeActivated.getValue()) == 1;
        booleanState.value = z;
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_gaming_mode_label);
        booleanState.icon = this.mIcon;
        if (z) {
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_gaming_mode_enabled);
            booleanState.state = 2;
            return;
        }
        booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_gaming_mode_disabled);
        booleanState.state = 1;
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_gaming_mode_enabled);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_gaming_mode_disabled);
    }
}
