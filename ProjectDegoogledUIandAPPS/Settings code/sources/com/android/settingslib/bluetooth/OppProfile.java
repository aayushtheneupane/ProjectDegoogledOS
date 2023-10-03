package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import com.android.settingslib.R$string;

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

    public int getDrawableResource(BluetoothClass bluetoothClass) {
        return 0;
    }

    public int getOrdinal() {
        return 2;
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

    public boolean isProfileReady() {
        return true;
    }

    public void setPreferred(BluetoothDevice bluetoothDevice, boolean z) {
    }

    public String toString() {
        return "OPP";
    }

    OppProfile() {
    }

    public int getNameResource(BluetoothDevice bluetoothDevice) {
        return R$string.bluetooth_profile_opp;
    }
}
