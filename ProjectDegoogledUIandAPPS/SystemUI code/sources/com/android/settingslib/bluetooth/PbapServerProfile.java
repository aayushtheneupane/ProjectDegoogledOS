package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothPbap;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;

public class PbapServerProfile implements LocalBluetoothProfile {
    @VisibleForTesting
    public static final String NAME = "PBAP Server";
    static final ParcelUuid[] PBAB_CLIENT_UUIDS = {BluetoothUuid.HSP, BluetoothUuid.Handsfree, BluetoothUuid.PBAP_PCE};
    /* access modifiers changed from: private */
    public boolean mIsProfileReady;
    /* access modifiers changed from: private */
    public BluetoothPbap mService;

    public boolean accessProfileEnabled() {
        return true;
    }

    public boolean connect(BluetoothDevice bluetoothDevice) {
        return false;
    }

    public int getProfileId() {
        return 6;
    }

    public boolean isAutoConnectable() {
        return false;
    }

    public boolean isPreferred(BluetoothDevice bluetoothDevice) {
        return false;
    }

    public void setPreferred(BluetoothDevice bluetoothDevice, boolean z) {
    }

    public String toString() {
        return NAME;
    }

    private final class PbapServiceListener implements BluetoothPbap.ServiceListener {
        private PbapServiceListener() {
        }

        public void onServiceConnected(BluetoothPbap bluetoothPbap) {
            BluetoothPbap unused = PbapServerProfile.this.mService = bluetoothPbap;
            boolean unused2 = PbapServerProfile.this.mIsProfileReady = true;
        }

        public void onServiceDisconnected() {
            boolean unused = PbapServerProfile.this.mIsProfileReady = false;
        }
    }

    PbapServerProfile(Context context) {
        new BluetoothPbap(context, new PbapServiceListener());
    }

    public boolean disconnect(BluetoothDevice bluetoothDevice) {
        BluetoothPbap bluetoothPbap = this.mService;
        if (bluetoothPbap == null) {
            return false;
        }
        return bluetoothPbap.disconnect(bluetoothDevice);
    }

    public int getConnectionStatus(BluetoothDevice bluetoothDevice) {
        BluetoothPbap bluetoothPbap = this.mService;
        if (bluetoothPbap != null && bluetoothPbap.isConnected(bluetoothDevice)) {
            return 2;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        Log.d("PbapServerProfile", "finalize()");
        BluetoothPbap bluetoothPbap = this.mService;
        if (bluetoothPbap != null) {
            try {
                bluetoothPbap.close();
                this.mService = null;
            } catch (Throwable th) {
                Log.w("PbapServerProfile", "Error cleaning up PBAP proxy", th);
            }
        }
    }
}
