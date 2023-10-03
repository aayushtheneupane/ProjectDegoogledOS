package com.android.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.media.AudioManager;
import androidx.preference.Preference;
import com.android.settings.connecteddevice.DevicePreferenceCallback;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.widget.GearPreference;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

public class ConnectedBluetoothDeviceUpdater extends BluetoothDeviceUpdater {
    private final AudioManager mAudioManager;

    public ConnectedBluetoothDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback) {
        super(context, dashboardFragment, devicePreferenceCallback);
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public void onAudioModeChanged() {
        forceUpdate();
    }

    public boolean isFilterMatched(CachedBluetoothDevice cachedBluetoothDevice) {
        boolean z;
        int mode = this.mAudioManager.getMode();
        boolean z2 = (mode == 1 || mode == 2 || mode == 3) ? true : true;
        if (!isDeviceConnected(cachedBluetoothDevice) || cachedBluetoothDevice.isConnectedHearingAidDevice()) {
            return false;
        }
        if (z2) {
            z = cachedBluetoothDevice.isConnectedHfpDevice();
        } else if (!z2) {
            return false;
        } else {
            z = cachedBluetoothDevice.isConnectedA2dpDevice();
        }
        return !z;
    }

    /* access modifiers changed from: protected */
    public void addPreference(CachedBluetoothDevice cachedBluetoothDevice) {
        super.addPreference(cachedBluetoothDevice);
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (this.mPreferenceMap.containsKey(device)) {
            BluetoothDevicePreference bluetoothDevicePreference = (BluetoothDevicePreference) this.mPreferenceMap.get(device);
            bluetoothDevicePreference.setOnGearClickListener((GearPreference.OnGearClickListener) null);
            bluetoothDevicePreference.hideSecondTarget(true);
            bluetoothDevicePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public final boolean onPreferenceClick(Preference preference) {
                    return ConnectedBluetoothDeviceUpdater.this.lambda$addPreference$0$ConnectedBluetoothDeviceUpdater(preference);
                }
            });
        }
    }

    public /* synthetic */ boolean lambda$addPreference$0$ConnectedBluetoothDeviceUpdater(Preference preference) {
        lambda$new$0$BluetoothDeviceUpdater(preference);
        return true;
    }
}
