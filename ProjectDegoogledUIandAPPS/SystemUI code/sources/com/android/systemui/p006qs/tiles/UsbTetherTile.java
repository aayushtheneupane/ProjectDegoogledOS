package com.android.systemui.p006qs.tiles;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.UsbTetherTile */
public class UsbTetherTile extends QSTileImpl<QSTile.BooleanState> {
    private static final Intent TETHER_SETTINGS = new Intent().setComponent(new ComponentName("com.android.settings", "com.android.settings.TetherSettings"));
    /* access modifiers changed from: private */
    public final ConnectivityManager mConnectivityManager = ((ConnectivityManager) this.mContext.getSystemService(ConnectivityManager.class));
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_usb_tether);
    private boolean mListening;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean unused = UsbTetherTile.this.mUsbConnected = intent.getBooleanExtra("connected", false);
            if (!UsbTetherTile.this.mUsbConnected || !UsbTetherTile.this.mConnectivityManager.isTetheringSupported()) {
                boolean unused2 = UsbTetherTile.this.mUsbTetherEnabled = false;
            } else {
                boolean unused3 = UsbTetherTile.this.mUsbTetherEnabled = intent.getBooleanExtra("rndis", false);
            }
            UsbTetherTile.this.refreshState();
        }
    };
    /* access modifiers changed from: private */
    public boolean mUsbConnected = false;
    /* access modifiers changed from: private */
    public boolean mUsbTetherEnabled = false;

    public int getMetricsCategory() {
        return 1999;
    }

    public UsbTetherTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleSetListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
            if (z) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.hardware.usb.action.USB_STATE");
                this.mContext.registerReceiver(this.mReceiver, intentFilter);
                return;
            }
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (this.mUsbConnected) {
            this.mConnectivityManager.setUsbTethering(!this.mUsbTetherEnabled);
        }
    }

    public Intent getLongClickIntent() {
        return new Intent(TETHER_SETTINGS);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int i;
        booleanState.value = this.mUsbTetherEnabled;
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_usb_tether_label);
        booleanState.icon = this.mIcon;
        if (!this.mUsbConnected) {
            i = 0;
        } else {
            i = this.mUsbTetherEnabled ? 2 : 1;
        }
        booleanState.state = i;
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_usb_tether_label);
    }
}
