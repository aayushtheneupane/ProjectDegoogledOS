package com.android.settings.media;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.android.settings.bluetooth.Utils;
import com.android.settings.slices.SliceBackgroundWorker;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.io.IOException;

public class MediaOutputIndicatorWorker extends SliceBackgroundWorker implements BluetoothCallback {
    private LocalBluetoothManager mLocalBluetoothManager;

    public MediaOutputIndicatorWorker(Context context, Uri uri) {
        super(context, uri);
    }

    /* access modifiers changed from: protected */
    public void onSlicePinned() {
        this.mLocalBluetoothManager = Utils.getLocalBtManager(getContext());
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        if (localBluetoothManager == null) {
            Log.e("MediaOutputIndicatorWorker", "Bluetooth is not supported on this device");
        } else {
            localBluetoothManager.getEventManager().registerCallback(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onSliceUnpinned() {
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        if (localBluetoothManager == null) {
            Log.e("MediaOutputIndicatorWorker", "Bluetooth is not supported on this device");
        } else {
            localBluetoothManager.getEventManager().unregisterCallback(this);
        }
    }

    public void close() throws IOException {
        this.mLocalBluetoothManager = null;
    }

    public void onBluetoothStateChanged(int i) {
        notifySliceChange();
    }

    public void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        if (i == 2) {
            notifySliceChange();
        }
    }

    public void onAudioModeChanged() {
        notifySliceChange();
    }
}
