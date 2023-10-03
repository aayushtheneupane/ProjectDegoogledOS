package com.android.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.connecteddevice.DevicePreferenceCallback;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.widget.GearPreference;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import com.havoc.config.center.C1715R;
import java.util.HashMap;
import java.util.Map;

public abstract class BluetoothDeviceUpdater implements BluetoothCallback, LocalBluetoothProfileManager.ServiceListener {
    protected final DevicePreferenceCallback mDevicePreferenceCallback;
    final GearPreference.OnGearClickListener mDeviceProfilesListener;
    protected DashboardFragment mFragment;
    protected LocalBluetoothManager mLocalManager;
    protected Context mPrefContext;
    protected final Map<BluetoothDevice, Preference> mPreferenceMap;

    public abstract boolean isFilterMatched(CachedBluetoothDevice cachedBluetoothDevice);

    public void onServiceDisconnected() {
    }

    public BluetoothDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback) {
        this(dashboardFragment, devicePreferenceCallback, Utils.getLocalBtManager(context));
    }

    BluetoothDeviceUpdater(DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback, LocalBluetoothManager localBluetoothManager) {
        this.mDeviceProfilesListener = new GearPreference.OnGearClickListener() {
            public final void onGearClick(GearPreference gearPreference) {
                BluetoothDeviceUpdater.this.lambda$new$0$BluetoothDeviceUpdater(gearPreference);
            }
        };
        this.mFragment = dashboardFragment;
        this.mDevicePreferenceCallback = devicePreferenceCallback;
        this.mPreferenceMap = new HashMap();
        this.mLocalManager = localBluetoothManager;
    }

    public void registerCallback() {
        LocalBluetoothManager localBluetoothManager = this.mLocalManager;
        if (localBluetoothManager == null) {
            Log.e("BluetoothDeviceUpdater", "registerCallback() Bluetooth is not supported on this device");
            return;
        }
        localBluetoothManager.setForegroundActivity(this.mFragment.getContext());
        this.mLocalManager.getEventManager().registerCallback(this);
        this.mLocalManager.getProfileManager().addServiceListener(this);
        forceUpdate();
    }

    public void unregisterCallback() {
        LocalBluetoothManager localBluetoothManager = this.mLocalManager;
        if (localBluetoothManager == null) {
            Log.e("BluetoothDeviceUpdater", "unregisterCallback() Bluetooth is not supported on this device");
            return;
        }
        localBluetoothManager.setForegroundActivity((Context) null);
        this.mLocalManager.getEventManager().unregisterCallback(this);
        this.mLocalManager.getProfileManager().removeServiceListener(this);
    }

    public void forceUpdate() {
        if (this.mLocalManager == null) {
            Log.e("BluetoothDeviceUpdater", "forceUpdate() Bluetooth is not supported on this device");
        } else if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            for (CachedBluetoothDevice update : this.mLocalManager.getCachedDeviceManager().getCachedDevicesCopy()) {
                update(update);
            }
        } else {
            removeAllDevicesFromPreference();
        }
    }

    public void removeAllDevicesFromPreference() {
        LocalBluetoothManager localBluetoothManager = this.mLocalManager;
        if (localBluetoothManager == null) {
            Log.e("BluetoothDeviceUpdater", "removeAllDevicesFromPreference() BT is not supported on this device");
            return;
        }
        for (CachedBluetoothDevice removePreference : localBluetoothManager.getCachedDeviceManager().getCachedDevicesCopy()) {
            removePreference(removePreference);
        }
    }

    public void onBluetoothStateChanged(int i) {
        if (12 == i) {
            forceUpdate();
        } else if (10 == i) {
            removeAllDevicesFromPreference();
        }
    }

    public void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        update(cachedBluetoothDevice);
    }

    public void onDeviceDeleted(CachedBluetoothDevice cachedBluetoothDevice) {
        removePreference(cachedBluetoothDevice);
    }

    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        update(cachedBluetoothDevice);
    }

    public void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2) {
        update(cachedBluetoothDevice);
    }

    public void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        update(cachedBluetoothDevice);
    }

    public void onServiceConnected() {
        forceUpdate();
    }

    public void setPrefContext(Context context) {
        this.mPrefContext = context;
    }

    /* access modifiers changed from: protected */
    public void update(CachedBluetoothDevice cachedBluetoothDevice) {
        if (isFilterMatched(cachedBluetoothDevice)) {
            addPreference(cachedBluetoothDevice);
        } else {
            removePreference(cachedBluetoothDevice);
        }
    }

    /* access modifiers changed from: protected */
    public void addPreference(CachedBluetoothDevice cachedBluetoothDevice) {
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (!this.mPreferenceMap.containsKey(device)) {
            BluetoothDevicePreference bluetoothDevicePreference = new BluetoothDevicePreference(this.mPrefContext, cachedBluetoothDevice, true);
            bluetoothDevicePreference.setOnGearClickListener(this.mDeviceProfilesListener);
            if (this instanceof Preference.OnPreferenceClickListener) {
                bluetoothDevicePreference.setOnPreferenceClickListener((Preference.OnPreferenceClickListener) this);
            }
            this.mPreferenceMap.put(device, bluetoothDevicePreference);
            this.mDevicePreferenceCallback.onDeviceAdded(bluetoothDevicePreference);
        }
    }

    /* access modifiers changed from: protected */
    public void removePreference(CachedBluetoothDevice cachedBluetoothDevice) {
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        CachedBluetoothDevice subDevice = cachedBluetoothDevice.getSubDevice();
        if (this.mPreferenceMap.containsKey(device)) {
            this.mDevicePreferenceCallback.onDeviceRemoved(this.mPreferenceMap.get(device));
            this.mPreferenceMap.remove(device);
        } else if (subDevice != null) {
            BluetoothDevice device2 = subDevice.getDevice();
            if (this.mPreferenceMap.containsKey(device2)) {
                this.mDevicePreferenceCallback.onDeviceRemoved(this.mPreferenceMap.get(device2));
                this.mPreferenceMap.remove(device2);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: launchDeviceDetails */
    public void lambda$new$0$BluetoothDeviceUpdater(Preference preference) {
        CachedBluetoothDevice bluetoothDevice = ((BluetoothDevicePreference) preference).getBluetoothDevice();
        if (bluetoothDevice != null) {
            Bundle bundle = new Bundle();
            bundle.putString("device_address", bluetoothDevice.getDevice().getAddress());
            new SubSettingLauncher(this.mFragment.getContext()).setDestination(BluetoothDeviceDetailsFragment.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.device_details_title).setSourceMetricsCategory(this.mFragment.getMetricsCategory()).launch();
        }
    }

    public boolean isDeviceConnected(CachedBluetoothDevice cachedBluetoothDevice) {
        if (cachedBluetoothDevice == null) {
            return false;
        }
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (device.getBondState() != 12 || !device.isConnected()) {
            return false;
        }
        return true;
    }
}
