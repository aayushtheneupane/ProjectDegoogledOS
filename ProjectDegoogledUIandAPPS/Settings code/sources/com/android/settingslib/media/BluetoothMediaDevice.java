package com.android.settingslib.media;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.android.settingslib.R$string;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

public class BluetoothMediaDevice extends MediaDevice {
    private CachedBluetoothDevice mCachedDevice;

    public void disconnect() {
    }

    BluetoothMediaDevice(Context context, CachedBluetoothDevice cachedBluetoothDevice) {
        super(context, 3);
        this.mCachedDevice = cachedBluetoothDevice;
        initDeviceRecord();
    }

    public String getName() {
        return this.mCachedDevice.getName();
    }

    public String getSummary() {
        if (isConnected() || this.mCachedDevice.isBusy()) {
            return this.mCachedDevice.getConnectionSummary();
        }
        return this.mContext.getString(R$string.bluetooth_disconnected);
    }

    public Drawable getIcon() {
        return (Drawable) BluetoothUtils.getBtRainbowDrawableWithDescription(this.mContext, this.mCachedDevice).first;
    }

    public String getId() {
        return MediaDeviceUtils.getId(this.mCachedDevice);
    }

    public boolean connect() {
        boolean active = this.mCachedDevice.setActive();
        setConnectedRecord();
        Log.d("BluetoothMediaDevice", "connect() device : " + getName() + ", is selected : " + active);
        return active;
    }

    public CachedBluetoothDevice getCachedDevice() {
        return this.mCachedDevice;
    }

    /* access modifiers changed from: protected */
    public boolean isCarKitDevice() {
        BluetoothClass bluetoothClass = this.mCachedDevice.getDevice().getBluetoothClass();
        if (bluetoothClass == null) {
            return false;
        }
        int deviceClass = bluetoothClass.getDeviceClass();
        return deviceClass == 1032 || deviceClass == 1056;
    }

    public boolean isConnected() {
        return this.mCachedDevice.getBondState() == 12 && this.mCachedDevice.isConnected();
    }
}
