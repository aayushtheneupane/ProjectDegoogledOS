package com.android.settingslib.media;

import android.bluetooth.BluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

public class MediaDeviceUtils {
    public static String getId(CachedBluetoothDevice cachedBluetoothDevice) {
        return cachedBluetoothDevice.getAddress();
    }

    public static String getId(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice.getAddress();
    }
}
