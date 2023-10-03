package com.android.settings.connecteddevice;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.bluetooth.BluetoothDeviceUpdater;
import com.android.settings.bluetooth.ConnectedBluetoothDeviceUpdater;
import com.android.settings.connecteddevice.dock.DockUpdater;
import com.android.settings.connecteddevice.usb.ConnectedUsbDeviceUpdater;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

public class ConnectedDeviceGroupController extends BasePreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart, OnStop, DevicePreferenceCallback {
    private static final String KEY = "connected_device_list";
    private BluetoothDeviceUpdater mBluetoothDeviceUpdater;
    private DockUpdater mConnectedDockUpdater;
    private ConnectedUsbDeviceUpdater mConnectedUsbDeviceUpdater;
    PreferenceGroup mPreferenceGroup;

    public String getPreferenceKey() {
        return KEY;
    }

    public ConnectedDeviceGroupController(Context context) {
        super(context, KEY);
    }

    public void onStart() {
        this.mBluetoothDeviceUpdater.registerCallback();
        this.mConnectedUsbDeviceUpdater.registerCallback();
        this.mConnectedDockUpdater.registerCallback();
    }

    public void onStop() {
        this.mConnectedUsbDeviceUpdater.unregisterCallback();
        this.mBluetoothDeviceUpdater.unregisterCallback();
        this.mConnectedDockUpdater.unregisterCallback();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(KEY);
        this.mPreferenceGroup.setVisible(false);
        if (isAvailable()) {
            Context context = preferenceScreen.getContext();
            this.mBluetoothDeviceUpdater.setPrefContext(context);
            this.mBluetoothDeviceUpdater.forceUpdate();
            this.mConnectedUsbDeviceUpdater.initUsbPreference(context);
            this.mConnectedDockUpdater.setPreferenceContext(context);
            this.mConnectedDockUpdater.forceUpdate();
        }
    }

    public int getAvailabilityStatus() {
        PackageManager packageManager = this.mContext.getPackageManager();
        return (packageManager.hasSystemFeature("android.hardware.bluetooth") || packageManager.hasSystemFeature("android.hardware.usb.accessory") || packageManager.hasSystemFeature("android.hardware.usb.host") || this.mConnectedDockUpdater != null) ? 1 : 3;
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

    public void init(BluetoothDeviceUpdater bluetoothDeviceUpdater, ConnectedUsbDeviceUpdater connectedUsbDeviceUpdater, DockUpdater dockUpdater) {
        this.mBluetoothDeviceUpdater = bluetoothDeviceUpdater;
        this.mConnectedUsbDeviceUpdater = connectedUsbDeviceUpdater;
        this.mConnectedDockUpdater = dockUpdater;
    }

    public void init(DashboardFragment dashboardFragment) {
        Context context = dashboardFragment.getContext();
        init(new ConnectedBluetoothDeviceUpdater(context, dashboardFragment, this), new ConnectedUsbDeviceUpdater(context, dashboardFragment, this), FeatureFactory.getFactory(context).getDockUpdaterFeatureProvider().getConnectedDockUpdater(context, this));
    }
}
