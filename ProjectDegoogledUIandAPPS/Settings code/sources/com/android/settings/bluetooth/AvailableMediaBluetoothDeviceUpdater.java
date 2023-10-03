package com.android.settings.bluetooth;

import android.content.Context;
import android.media.AudioManager;
import androidx.preference.Preference;
import com.android.settings.connecteddevice.DevicePreferenceCallback;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

public class AvailableMediaBluetoothDeviceUpdater extends BluetoothDeviceUpdater implements Preference.OnPreferenceClickListener {
    private final AudioManager mAudioManager;

    public AvailableMediaBluetoothDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback) {
        super(context, dashboardFragment, devicePreferenceCallback);
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public void onAudioModeChanged() {
        forceUpdate();
    }

    public boolean isFilterMatched(CachedBluetoothDevice cachedBluetoothDevice) {
        int mode = this.mAudioManager.getMode();
        boolean z = (mode == 1 || mode == 2 || mode == 3) ? true : true;
        if (!isDeviceConnected(cachedBluetoothDevice)) {
            return false;
        }
        if (cachedBluetoothDevice.isConnectedHearingAidDevice()) {
            return true;
        }
        if (z) {
            return cachedBluetoothDevice.isConnectedHfpDevice();
        }
        if (!z) {
            return false;
        }
        return cachedBluetoothDevice.isConnectedA2dpDevice();
    }

    public boolean onPreferenceClick(Preference preference) {
        return ((BluetoothDevicePreference) preference).getBluetoothDevice().setActive();
    }
}
