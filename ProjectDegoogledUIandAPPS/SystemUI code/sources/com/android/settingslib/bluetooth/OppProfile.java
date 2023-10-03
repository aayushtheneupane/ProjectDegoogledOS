package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;

final class OppProfile implements LocalBluetoothProfile {
    public boolean accessProfileEnabled() {
        return false;
    }

    public boolean connect(BluetoothDevice bluetoothDevice) {
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothDevice) {
        return false;
    }

    public int getConnectionStatus(BluetoothDevice bluetoothDevice) {
        return 0;
    }

    public int getProfileId() {
        return 20;
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
        return "OPP";
    }

    OppProfile() {
    }
}
