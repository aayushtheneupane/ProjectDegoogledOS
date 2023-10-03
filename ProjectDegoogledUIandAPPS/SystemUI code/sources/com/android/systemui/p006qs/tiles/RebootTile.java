package com.android.systemui.p006qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.PowerManager;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.RebootTile */
public class RebootTile extends QSTileImpl<QSTile.BooleanState> {
    /* access modifiers changed from: private */
    public int mRebootToRecovery = 0;

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public RebootTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleClick() {
        int i = this.mRebootToRecovery;
        if (i == 0) {
            this.mRebootToRecovery = 1;
        } else if (i == 1) {
            this.mRebootToRecovery = 2;
        } else {
            this.mRebootToRecovery = 0;
        }
        refreshState();
    }

    /* access modifiers changed from: protected */
    public void handleLongClick() {
        this.mHost.collapsePanels();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PowerManager powerManager = (PowerManager) RebootTile.this.mContext.getSystemService("power");
                if (RebootTile.this.mRebootToRecovery == 1) {
                    powerManager.reboot("recovery");
                } else if (RebootTile.this.mRebootToRecovery == 2) {
                    powerManager.shutdown(false, "userrequested", false);
                } else {
                    powerManager.reboot("");
                }
            }
        }, 500);
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_reboot_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int i = this.mRebootToRecovery;
        if (i == 1) {
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_reboot_recovery_label);
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_reboot_recovery);
        } else if (i == 2) {
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_poweroff_label);
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_poweroff);
        } else {
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_reboot_label);
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_reboot);
        }
        booleanState.state = 1;
    }
}
