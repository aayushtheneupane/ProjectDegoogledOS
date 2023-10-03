package com.android.settings.homepage.contextualcards.slices;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.android.settings.bluetooth.Utils;
import com.android.settings.slices.SliceBackgroundWorker;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

public class BluetoothUpdateWorker extends SliceBackgroundWorker implements BluetoothCallback {
    private final LocalBluetoothManager mLocalBluetoothManager;

    public void close() {
    }

    public BluetoothUpdateWorker(Context context, Uri uri) {
        super(context, uri);
        this.mLocalBluetoothManager = Utils.getLocalBtManager(context);
    }

    /* access modifiers changed from: protected */
    public void onSlicePinned() {
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        if (localBluetoothManager == null) {
            Log.i("BluetoothUpdateWorker", "onSlicePinned() Bluetooth is unsupported.");
        } else {
            localBluetoothManager.getEventManager().registerCallback(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onSliceUnpinned() {
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        if (localBluetoothManager == null) {
            Log.i("BluetoothUpdateWorker", "onSliceUnpinned() Bluetooth is unsupported.");
        } else {
            localBluetoothManager.getEventManager().unregisterCallback(this);
        }
    }

    public void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        notifySliceChange();
    }

    public void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        notifySliceChange();
    }

    public void onBluetoothStateChanged(int i) {
        notifySliceChange();
    }

    public void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        notifySliceChange();
    }

    public void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2) {
        notifySliceChange();
    }
}
