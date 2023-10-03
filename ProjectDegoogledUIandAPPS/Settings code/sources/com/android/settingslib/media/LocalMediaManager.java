package com.android.settingslib.media;

import android.app.Notification;
import android.content.Context;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.media.MediaManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocalMediaManager implements BluetoothCallback {
    private static final Comparator<MediaDevice> COMPARATOR = Comparator.naturalOrder();
    private BluetoothMediaManager mBluetoothMediaManager;
    private final Collection<DeviceCallback> mCallbacks = new ArrayList();
    private Context mContext;
    @VisibleForTesting
    MediaDevice mCurrentConnectedDevice;
    private LocalBluetoothManager mLocalBluetoothManager;
    @VisibleForTesting
    final MediaDeviceCallback mMediaDeviceCallback = new MediaDeviceCallback();
    @VisibleForTesting
    List<MediaDevice> mMediaDevices = new ArrayList();
    @VisibleForTesting
    MediaDevice mPhoneDevice;

    public interface DeviceCallback {
        void onDeviceListUpdate(List<MediaDevice> list);

        void onSelectedDeviceStateChanged(MediaDevice mediaDevice, int i);
    }

    public void registerCallback(DeviceCallback deviceCallback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(deviceCallback);
        }
    }

    public void unregisterCallback(DeviceCallback deviceCallback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(deviceCallback);
        }
    }

    public LocalMediaManager(Context context, String str, Notification notification) {
        this.mContext = context;
        this.mLocalBluetoothManager = LocalBluetoothManager.getInstance(context, (LocalBluetoothManager.BluetoothManagerCallback) null);
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        if (localBluetoothManager == null) {
            Log.e("LocalMediaManager", "Bluetooth is not supported on this device");
        } else {
            this.mBluetoothMediaManager = new BluetoothMediaManager(context, localBluetoothManager, notification);
        }
    }

    @VisibleForTesting
    LocalMediaManager(Context context, LocalBluetoothManager localBluetoothManager, BluetoothMediaManager bluetoothMediaManager, InfoMediaManager infoMediaManager) {
        this.mContext = context;
        this.mLocalBluetoothManager = localBluetoothManager;
        this.mBluetoothMediaManager = bluetoothMediaManager;
    }

    public void connectDevice(MediaDevice mediaDevice) {
        MediaDevice mediaDeviceById = getMediaDeviceById(this.mMediaDevices, mediaDevice.getId());
        int i = 1;
        if (mediaDeviceById instanceof BluetoothMediaDevice) {
            CachedBluetoothDevice cachedDevice = ((BluetoothMediaDevice) mediaDeviceById).getCachedDevice();
            if (!cachedDevice.isConnected() && !cachedDevice.isBusy()) {
                cachedDevice.connect(true);
                return;
            }
        }
        MediaDevice mediaDevice2 = this.mCurrentConnectedDevice;
        if (mediaDeviceById == mediaDevice2) {
            Log.d("LocalMediaManager", "connectDevice() this device all ready connected! : " + mediaDeviceById.getName());
            return;
        }
        if (mediaDevice2 != null) {
            mediaDevice2.disconnect();
        }
        boolean connect = mediaDeviceById.connect();
        if (connect) {
            this.mCurrentConnectedDevice = mediaDeviceById;
        }
        if (!connect) {
            i = 3;
        }
        dispatchSelectedDeviceStateChanged(mediaDeviceById, i);
    }

    /* access modifiers changed from: package-private */
    public void dispatchSelectedDeviceStateChanged(MediaDevice mediaDevice, int i) {
        synchronized (this.mCallbacks) {
            for (DeviceCallback onSelectedDeviceStateChanged : this.mCallbacks) {
                onSelectedDeviceStateChanged.onSelectedDeviceStateChanged(mediaDevice, i);
            }
        }
    }

    public void startScan() {
        this.mMediaDevices.clear();
        this.mBluetoothMediaManager.registerCallback(this.mMediaDeviceCallback);
        this.mBluetoothMediaManager.startScan();
    }

    /* access modifiers changed from: private */
    public void addPhoneDeviceIfNecessary() {
        if (this.mMediaDevices.size() > 0 && !this.mMediaDevices.contains(this.mPhoneDevice)) {
            if (this.mPhoneDevice == null) {
                this.mPhoneDevice = new PhoneMediaDevice(this.mContext, this.mLocalBluetoothManager);
            }
            this.mMediaDevices.add(this.mPhoneDevice);
        }
    }

    /* access modifiers changed from: private */
    public void removePhoneMediaDeviceIfNecessary() {
        if (this.mMediaDevices.size() == 1 && this.mMediaDevices.contains(this.mPhoneDevice)) {
            this.mMediaDevices.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchDeviceListUpdate() {
        synchronized (this.mCallbacks) {
            Collections.sort(this.mMediaDevices, COMPARATOR);
            for (DeviceCallback onDeviceListUpdate : this.mCallbacks) {
                onDeviceListUpdate.onDeviceListUpdate(new ArrayList(this.mMediaDevices));
            }
        }
    }

    public void stopScan() {
        this.mBluetoothMediaManager.unregisterCallback(this.mMediaDeviceCallback);
        this.mBluetoothMediaManager.stopScan();
    }

    public MediaDevice getMediaDeviceById(List<MediaDevice> list, String str) {
        for (MediaDevice next : list) {
            if (next.getId().equals(str)) {
                return next;
            }
        }
        Log.i("LocalMediaManager", "getMediaDeviceById() can't found device");
        return null;
    }

    public MediaDevice getCurrentConnectedDevice() {
        return this.mCurrentConnectedDevice;
    }

    /* access modifiers changed from: private */
    public MediaDevice updateCurrentConnectedDevice() {
        for (MediaDevice next : this.mMediaDevices) {
            if ((next instanceof BluetoothMediaDevice) && isConnected(((BluetoothMediaDevice) next).getCachedDevice())) {
                return next;
            }
        }
        if (this.mMediaDevices.contains(this.mPhoneDevice)) {
            return this.mPhoneDevice;
        }
        return null;
    }

    private boolean isConnected(CachedBluetoothDevice cachedBluetoothDevice) {
        return cachedBluetoothDevice.isActiveDevice(2) || cachedBluetoothDevice.isActiveDevice(21);
    }

    class MediaDeviceCallback implements MediaManager.MediaDeviceCallback {
        MediaDeviceCallback() {
        }

        public void onDeviceAdded(MediaDevice mediaDevice) {
            if (!LocalMediaManager.this.mMediaDevices.contains(mediaDevice)) {
                LocalMediaManager.this.mMediaDevices.add(mediaDevice);
                LocalMediaManager.this.addPhoneDeviceIfNecessary();
                LocalMediaManager.this.dispatchDeviceListUpdate();
            }
        }

        public void onDeviceListAdded(List<MediaDevice> list) {
            for (MediaDevice next : list) {
                LocalMediaManager localMediaManager = LocalMediaManager.this;
                if (localMediaManager.getMediaDeviceById(localMediaManager.mMediaDevices, next.getId()) == null) {
                    LocalMediaManager.this.mMediaDevices.add(next);
                }
            }
            LocalMediaManager.this.addPhoneDeviceIfNecessary();
            LocalMediaManager localMediaManager2 = LocalMediaManager.this;
            localMediaManager2.mCurrentConnectedDevice = localMediaManager2.updateCurrentConnectedDevice();
            updatePhoneMediaDeviceSummary();
            LocalMediaManager.this.dispatchDeviceListUpdate();
        }

        private void updatePhoneMediaDeviceSummary() {
            LocalMediaManager localMediaManager = LocalMediaManager.this;
            MediaDevice mediaDevice = localMediaManager.mPhoneDevice;
            if (mediaDevice != null) {
                ((PhoneMediaDevice) mediaDevice).updateSummary(localMediaManager.mCurrentConnectedDevice == mediaDevice);
            }
        }

        public void onDeviceRemoved(MediaDevice mediaDevice) {
            if (LocalMediaManager.this.mMediaDevices.contains(mediaDevice)) {
                LocalMediaManager.this.mMediaDevices.remove(mediaDevice);
                LocalMediaManager.this.removePhoneMediaDeviceIfNecessary();
                LocalMediaManager.this.dispatchDeviceListUpdate();
            }
        }

        public void onDeviceListRemoved(List<MediaDevice> list) {
            LocalMediaManager.this.mMediaDevices.removeAll(list);
            LocalMediaManager.this.removePhoneMediaDeviceIfNecessary();
            LocalMediaManager.this.dispatchDeviceListUpdate();
        }

        public void onConnectedDeviceChanged(String str) {
            LocalMediaManager localMediaManager = LocalMediaManager.this;
            MediaDevice mediaDeviceById = localMediaManager.getMediaDeviceById(localMediaManager.mMediaDevices, str);
            LocalMediaManager localMediaManager2 = LocalMediaManager.this;
            if (mediaDeviceById == localMediaManager2.mCurrentConnectedDevice) {
                Log.d("LocalMediaManager", "onConnectedDeviceChanged() this device all ready connected!");
                return;
            }
            localMediaManager2.mCurrentConnectedDevice = mediaDeviceById;
            updatePhoneMediaDeviceSummary();
            LocalMediaManager.this.dispatchDeviceListUpdate();
        }

        public void onDeviceAttributesChanged() {
            LocalMediaManager.this.addPhoneDeviceIfNecessary();
            LocalMediaManager.this.removePhoneMediaDeviceIfNecessary();
            LocalMediaManager.this.dispatchDeviceListUpdate();
        }
    }
}
