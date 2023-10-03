package com.android.settings.connecteddevice;

import android.content.Context;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.bluetooth.AvailableMediaBluetoothDeviceUpdater;
import com.android.settings.bluetooth.BluetoothDeviceUpdater;
import com.android.settings.bluetooth.Utils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;

public class AvailableMediaDeviceGroupController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop, DevicePreferenceCallback, BluetoothCallback {
    private static final String KEY = "available_device_list";
    private static final String TAG = "AvailableMediaDeviceGroupController";
    private BluetoothDeviceUpdater mBluetoothDeviceUpdater;
    LocalBluetoothManager mLocalBluetoothManager = Utils.getLocalBtManager(this.mContext);
    PreferenceGroup mPreferenceGroup;

    public String getPreferenceKey() {
        return KEY;
    }

    public AvailableMediaDeviceGroupController(Context context) {
        super(context, KEY);
    }

    public void onStart() {
        if (this.mLocalBluetoothManager == null) {
            Log.e(TAG, "onStart() Bluetooth is not supported on this device");
            return;
        }
        this.mBluetoothDeviceUpdater.registerCallback();
        this.mLocalBluetoothManager.getEventManager().registerCallback(this);
    }

    public void onStop() {
        if (this.mLocalBluetoothManager == null) {
            Log.e(TAG, "onStop() Bluetooth is not supported on this device");
            return;
        }
        this.mBluetoothDeviceUpdater.unregisterCallback();
        this.mLocalBluetoothManager.getEventManager().unregisterCallback(this);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(KEY);
        this.mPreferenceGroup.setVisible(false);
        if (isAvailable()) {
            updateTitle();
            this.mBluetoothDeviceUpdater.setPrefContext(preferenceScreen.getContext());
            this.mBluetoothDeviceUpdater.forceUpdate();
        }
    }

    public int getAvailabilityStatus() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth") ? 1 : 3;
    }

    public void onDeviceAdded(Preference preference) {
        if (this.mPreferenceGroup.getPreferenceCount() == 0) {
            this.mPreferenceGroup.setVisible(true);
        }
        this.mPreferenceGroup.addPreference(preference);
    }

    public void onDeviceRemoved(Preference preference) {
        this.mPreferenceGroup.removePreference(preference);
        if (this.mPreferenceGroup.getPreferenceCount() == 0) {
            this.mPreferenceGroup.setVisible(false);
        }
    }

    public void init(DashboardFragment dashboardFragment) {
        this.mBluetoothDeviceUpdater = new AvailableMediaBluetoothDeviceUpdater(dashboardFragment.getContext(), dashboardFragment, this);
    }

    public void setBluetoothDeviceUpdater(BluetoothDeviceUpdater bluetoothDeviceUpdater) {
        this.mBluetoothDeviceUpdater = bluetoothDeviceUpdater;
    }

    public void onAudioModeChanged() {
        updateTitle();
    }

    private void updateTitle() {
        if (com.android.settingslib.Utils.isAudioModeOngoingCall(this.mContext)) {
            this.mPreferenceGroup.setTitle((CharSequence) this.mContext.getString(C1715R.string.connected_device_available_call_title));
        } else {
            this.mPreferenceGroup.setTitle((CharSequence) this.mContext.getString(C1715R.string.connected_device_available_media_title));
        }
    }
}
