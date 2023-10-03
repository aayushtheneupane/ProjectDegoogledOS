package com.android.settingslib.media;

import android.app.Notification;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import com.android.settingslib.bluetooth.A2dpProfile;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.HearingAidProfile;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import java.util.ArrayList;
import java.util.Iterator;

public class BluetoothMediaManager extends MediaManager implements BluetoothCallback, LocalBluetoothProfileManager.ServiceListener {
    private CachedBluetoothDeviceManager mCachedBluetoothDeviceManager;
    private final DeviceAttributeChangeCallback mDeviceAttributeChangeCallback = new DeviceAttributeChangeCallback();
    private boolean mIsA2dpProfileReady = false;
    private boolean mIsHearingAidProfileReady = false;
    private MediaDevice mLastAddedDevice;
    private MediaDevice mLastRemovedDevice;
    private LocalBluetoothManager mLocalBluetoothManager;
    private LocalBluetoothProfileManager mProfileManager;

    public void onServiceDisconnected() {
    }

    BluetoothMediaManager(Context context, LocalBluetoothManager localBluetoothManager, Notification notification) {
        super(context, notification);
        this.mLocalBluetoothManager = localBluetoothManager;
        this.mProfileManager = this.mLocalBluetoothManager.getProfileManager();
        this.mCachedBluetoothDeviceManager = this.mLocalBluetoothManager.getCachedDeviceManager();
    }

    public void startScan() {
        this.mLocalBluetoothManager.getEventManager().registerCallback(this);
        buildBluetoothDeviceList();
        dispatchDeviceListAdded();
        addServiceListenerIfNecessary();
    }

    private void addServiceListenerIfNecessary() {
        if (!this.mIsA2dpProfileReady || !this.mIsHearingAidProfileReady) {
            this.mProfileManager.addServiceListener(this);
        }
    }

    private void buildBluetoothDeviceList() {
        this.mMediaDevices.clear();
        addConnectableA2dpDevices();
        addConnectableHearingAidDevices();
    }

    private void addConnectableA2dpDevices() {
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile == null) {
            Log.w("BluetoothMediaManager", "addConnectableA2dpDevices() a2dp profile is null!");
            return;
        }
        for (BluetoothDevice next : a2dpProfile.getConnectableDevices()) {
            CachedBluetoothDevice findDevice = this.mCachedBluetoothDeviceManager.findDevice(next);
            if (findDevice == null) {
                Log.w("BluetoothMediaManager", "Can't found CachedBluetoothDevice : " + next.getName());
            } else {
                Log.d("BluetoothMediaManager", "addConnectableA2dpDevices() device : " + findDevice.getName() + ", is connected : " + findDevice.isConnected() + ", is preferred : " + a2dpProfile.isPreferred(next));
                if (a2dpProfile.isPreferred(next) && 12 == findDevice.getBondState()) {
                    addMediaDevice(findDevice);
                }
            }
        }
        this.mIsA2dpProfileReady = a2dpProfile.isProfileReady();
    }

    private void addConnectableHearingAidDevices() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile == null) {
            Log.w("BluetoothMediaManager", "addConnectableHearingAidDevices() hap profile is null!");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (BluetoothDevice next : hearingAidProfile.getConnectableDevices()) {
            CachedBluetoothDevice findDevice = this.mCachedBluetoothDeviceManager.findDevice(next);
            if (findDevice == null) {
                Log.w("BluetoothMediaManager", "Can't found CachedBluetoothDevice : " + next.getName());
            } else {
                Log.d("BluetoothMediaManager", "addConnectableHearingAidDevices() device : " + findDevice.getName() + ", is connected : " + findDevice.isConnected() + ", is preferred : " + hearingAidProfile.isPreferred(next));
                long hiSyncId = hearingAidProfile.getHiSyncId(next);
                if (!arrayList.contains(Long.valueOf(hiSyncId)) && hearingAidProfile.isPreferred(next) && 12 == findDevice.getBondState()) {
                    arrayList.add(Long.valueOf(hiSyncId));
                    addMediaDevice(findDevice);
                }
            }
        }
        this.mIsHearingAidProfileReady = hearingAidProfile.isProfileReady();
    }

    private void addMediaDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        if (findMediaDevice(MediaDeviceUtils.getId(cachedBluetoothDevice)) == null) {
            BluetoothMediaDevice bluetoothMediaDevice = new BluetoothMediaDevice(this.mContext, cachedBluetoothDevice);
            cachedBluetoothDevice.registerCallback(this.mDeviceAttributeChangeCallback);
            this.mLastAddedDevice = bluetoothMediaDevice;
            this.mMediaDevices.add(bluetoothMediaDevice);
        }
    }

    public void stopScan() {
        this.mLocalBluetoothManager.getEventManager().unregisterCallback(this);
        unregisterDeviceAttributeChangeCallback();
    }

    private void unregisterDeviceAttributeChangeCallback() {
        Iterator<MediaDevice> it = this.mMediaDevices.iterator();
        while (it.hasNext()) {
            ((BluetoothMediaDevice) it.next()).getCachedDevice().unregisterCallback(this.mDeviceAttributeChangeCallback);
        }
    }

    public void onBluetoothStateChanged(int i) {
        if (12 == i) {
            buildBluetoothDeviceList();
            dispatchDeviceListAdded();
            addServiceListenerIfNecessary();
        } else if (10 == i) {
            ArrayList arrayList = new ArrayList();
            for (MediaDevice next : this.mMediaDevices) {
                ((BluetoothMediaDevice) next).getCachedDevice().unregisterCallback(this.mDeviceAttributeChangeCallback);
                arrayList.add(next);
            }
            this.mMediaDevices.removeAll(arrayList);
            dispatchDeviceListRemoved(arrayList);
        }
    }

    public void onAudioModeChanged() {
        dispatchDataChanged();
    }

    public void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        if (isCachedDeviceConnected(cachedBluetoothDevice)) {
            addMediaDevice(cachedBluetoothDevice);
            dispatchDeviceAdded(cachedBluetoothDevice);
        }
    }

    private boolean isCachedDeviceConnected(CachedBluetoothDevice cachedBluetoothDevice) {
        boolean isConnectedHearingAidDevice = cachedBluetoothDevice.isConnectedHearingAidDevice();
        boolean isConnectedA2dpDevice = cachedBluetoothDevice.isConnectedA2dpDevice();
        Log.d("BluetoothMediaManager", "isCachedDeviceConnected() cachedDevice : " + cachedBluetoothDevice + ", is hearing aid connected : " + isConnectedHearingAidDevice + ", is a2dp connected : " + isConnectedA2dpDevice);
        return isConnectedHearingAidDevice || isConnectedA2dpDevice;
    }

    private void dispatchDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        if (this.mLastAddedDevice != null && MediaDeviceUtils.getId(cachedBluetoothDevice) == this.mLastAddedDevice.getId()) {
            dispatchDeviceAdded(this.mLastAddedDevice);
        }
    }

    public void onDeviceDeleted(CachedBluetoothDevice cachedBluetoothDevice) {
        if (!isCachedDeviceConnected(cachedBluetoothDevice)) {
            removeMediaDevice(cachedBluetoothDevice);
            dispatchDeviceRemoved(cachedBluetoothDevice);
        }
    }

    private void removeMediaDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        MediaDevice findMediaDevice = findMediaDevice(MediaDeviceUtils.getId(cachedBluetoothDevice));
        if (findMediaDevice != null) {
            cachedBluetoothDevice.unregisterCallback(this.mDeviceAttributeChangeCallback);
            this.mLastRemovedDevice = findMediaDevice;
            this.mMediaDevices.remove(findMediaDevice);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchDeviceRemoved(CachedBluetoothDevice cachedBluetoothDevice) {
        if (this.mLastRemovedDevice != null && MediaDeviceUtils.getId(cachedBluetoothDevice) == this.mLastRemovedDevice.getId()) {
            dispatchDeviceRemoved(this.mLastRemovedDevice);
        }
    }

    public void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2) {
        Log.d("BluetoothMediaManager", "onProfileConnectionStateChanged() device: " + cachedBluetoothDevice + ", state: " + i + ", bluetoothProfile: " + i2);
        updateMediaDeviceListIfNecessary(cachedBluetoothDevice);
    }

    private void updateMediaDeviceListIfNecessary(CachedBluetoothDevice cachedBluetoothDevice) {
        if (10 == cachedBluetoothDevice.getBondState()) {
            removeMediaDevice(cachedBluetoothDevice);
            dispatchDeviceRemoved(cachedBluetoothDevice);
        } else if (findMediaDevice(MediaDeviceUtils.getId(cachedBluetoothDevice)) != null) {
            dispatchDataChanged();
        }
    }

    public void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        Log.d("BluetoothMediaManager", "onAclConnectionStateChanged() device: " + cachedBluetoothDevice + ", state: " + i);
        updateMediaDeviceListIfNecessary(cachedBluetoothDevice);
    }

    public void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        String str;
        Log.d("BluetoothMediaManager", "onActiveDeviceChanged : device : " + cachedBluetoothDevice + ", profile : " + i);
        if (21 == i) {
            if (cachedBluetoothDevice != null) {
                dispatchConnectedDeviceChanged(MediaDeviceUtils.getId(cachedBluetoothDevice));
            }
        } else if (2 == i) {
            MediaDevice findActiveHearingAidDevice = findActiveHearingAidDevice();
            if (cachedBluetoothDevice != null) {
                str = MediaDeviceUtils.getId(cachedBluetoothDevice);
            } else if (findActiveHearingAidDevice == null) {
                str = "phone_media_device_id_1";
            } else {
                str = findActiveHearingAidDevice.getId();
            }
            dispatchConnectedDeviceChanged(str);
        }
    }

    private MediaDevice findActiveHearingAidDevice() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile == null) {
            return null;
        }
        for (BluetoothDevice next : hearingAidProfile.getActiveDevices()) {
            if (next != null) {
                return findMediaDevice(MediaDeviceUtils.getId(next));
            }
        }
        return null;
    }

    public void onServiceConnected() {
        if (!this.mIsA2dpProfileReady || !this.mIsHearingAidProfileReady) {
            buildBluetoothDeviceList();
            dispatchDeviceListAdded();
        }
        if (this.mIsA2dpProfileReady && this.mIsHearingAidProfileReady) {
            this.mProfileManager.removeServiceListener(this);
        }
    }

    private class DeviceAttributeChangeCallback implements CachedBluetoothDevice.Callback {
        private DeviceAttributeChangeCallback() {
        }

        public void onDeviceAttributesChanged() {
            BluetoothMediaManager.this.dispatchDataChanged();
        }
    }
}
