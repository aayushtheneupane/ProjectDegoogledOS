package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.provider.Settings;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.GlobalSetting;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.HeadsUpTile */
public class HeadsUpTile extends QSTileImpl<QSTile.BooleanState> {
    private static final Intent NOTIFICATION_SETTINGS = new Intent("android.settings.NOTIFICATION_SETTINGS");
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_heads_up);
    private final GlobalSetting mSetting = new GlobalSetting(this.mContext, this.mHandler, "heads_up_notifications_enabled") {
        /* access modifiers changed from: protected */
        public void handleValueChanged(int i) {
            HeadsUpTile.this.handleRefreshState(Integer.valueOf(i));
        }
    };

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public HeadsUpTile(QSHost qSHost) {
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

    public Intent getLongClickIntent() {
        return NOTIFICATION_SETTINGS;
    }

    private void setEnabled(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "heads_up_notifications_enabled", z ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean z = (obj instanceof Integer ? ((Integer) obj).intValue() : this.mSetting.getValue()) != 0;
        booleanState.value = z;
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_heads_up_label);
        booleanState.icon = this.mIcon;
        if (z) {
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_heads_up_on);
            booleanState.state = 2;
            return;
        }
        booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_heads_up_off);
        booleanState.state = 1;
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_heads_up_label);
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_heads_up_changed_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_heads_up_changed_off);
    }
}
