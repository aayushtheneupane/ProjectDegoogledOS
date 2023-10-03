package com.android.systemui.p006qs.tiles;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.RemoteException;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.policy.BatteryController;
import java.util.NoSuchElementException;
import vendor.lineage.powershare.V1_0.IPowerShare;

/* renamed from: com.android.systemui.qs.tiles.PowerShareTile */
public class PowerShareTile extends QSTileImpl<QSTile.BooleanState> implements BatteryController.BatteryStateChangeCallback {
    private BatteryController mBatteryController;
    private Notification mNotification;
    private NotificationManager mNotificationManager;
    private IPowerShare mPowerShare = getPowerShare();

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public PowerShareTile(QSHost qSHost, BatteryController batteryController) {
        super(qSHost);
        if (this.mPowerShare != null) {
            this.mBatteryController = batteryController;
            batteryController.addCallback(this);
            this.mNotificationManager = (NotificationManager) this.mContext.getSystemService(NotificationManager.class);
            this.mNotificationManager.createNotificationChannel(new NotificationChannel("powershare", this.mContext.getString(C1784R$string.quick_settings_powershare_label), 3));
            Notification.Builder builder = new Notification.Builder(this.mContext, "powershare");
            builder.setContentTitle(this.mContext.getString(C1784R$string.quick_settings_powershare_enabled_label));
            builder.setSmallIcon(C1776R$drawable.ic_qs_powershare);
            builder.setOnlyAlertOnce(true);
            this.mNotification = builder.build();
            Notification notification = this.mNotification;
            notification.flags |= 34;
            notification.visibility = 1;
        }
    }

    public void onPowerSaveChanged(boolean z) {
        refreshState();
    }

    public void refreshState() {
        updatePowerShareState();
        super.refreshState();
    }

    private void updatePowerShareState() {
        if (isAvailable()) {
            if (this.mBatteryController.isPowerSave()) {
                try {
                    this.mPowerShare.setEnabled(false);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (this.mPowerShare.isEnabled()) {
                    this.mNotificationManager.notify(273298, this.mNotification);
                } else {
                    this.mNotificationManager.cancel(273298);
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean isAvailable() {
        return this.mPowerShare != null;
    }

    public QSTile.BooleanState newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.handlesLongClick = false;
        return booleanState;
    }

    public void handleClick() {
        try {
            boolean isEnabled = this.mPowerShare.isEnabled();
            if (this.mPowerShare.setEnabled(!isEnabled) != isEnabled) {
                refreshState();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_powershare_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        if (isAvailable()) {
            if (booleanState.slash == null) {
                booleanState.slash = new QSTile.SlashState();
            }
            booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_powershare);
            try {
                booleanState.value = this.mPowerShare.isEnabled();
            } catch (RemoteException e) {
                booleanState.value = false;
                e.printStackTrace();
            }
            booleanState.slash.isSlashed = booleanState.value;
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_powershare_label);
            if (this.mBatteryController.isPowerSave()) {
                booleanState.secondaryLabel = this.mContext.getString(C1784R$string.quick_settings_powershare_off_powersave_label);
            } else if (getBatteryLevel() < getMinBatteryLevel()) {
                booleanState.secondaryLabel = this.mContext.getString(C1784R$string.quick_settings_powershare_off_low_battery_label);
            } else {
                booleanState.secondaryLabel = null;
            }
            if (this.mBatteryController.isPowerSave() || getBatteryLevel() < getMinBatteryLevel()) {
                booleanState.state = 0;
            } else if (!booleanState.value) {
                booleanState.state = 1;
            } else {
                booleanState.state = 2;
            }
        }
    }

    private synchronized IPowerShare getPowerShare() {
        try {
            return IPowerShare.getService();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchElementException unused) {
            return null;
        }
    }

    private int getMinBatteryLevel() {
        try {
            return this.mPowerShare.getMinBattery();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int getBatteryLevel() {
        return ((BatteryManager) this.mContext.getSystemService(BatteryManager.class)).getIntProperty(4);
    }
}
