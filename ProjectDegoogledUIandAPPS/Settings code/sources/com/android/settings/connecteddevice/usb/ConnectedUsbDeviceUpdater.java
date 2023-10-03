package com.android.settings.connecteddevice.usb;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.android.settings.connecteddevice.DevicePreferenceCallback;
import com.android.settings.connecteddevice.usb.UsbConnectionBroadcastReceiver;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.DashboardFragment;
import com.havoc.config.center.C1715R;

public class ConnectedUsbDeviceUpdater {
    private DevicePreferenceCallback mDevicePreferenceCallback;
    private DashboardFragment mFragment;
    private UsbBackend mUsbBackend;
    UsbConnectionBroadcastReceiver.UsbConnectionListener mUsbConnectionListener;
    Preference mUsbPreference;
    UsbConnectionBroadcastReceiver mUsbReceiver;

    public static int getSummary(long j, int i) {
        return i != 1 ? i != 2 ? C1715R.string.usb_summary_charging_only : j == 4 ? C1715R.string.usb_summary_file_transfers : j == 32 ? C1715R.string.usb_summary_tether : j == 16 ? C1715R.string.usb_summary_photo_transfers : j == 8 ? C1715R.string.usb_summary_MIDI : C1715R.string.usb_summary_charging_only : j == 4 ? C1715R.string.usb_summary_file_transfers_power : j == 32 ? C1715R.string.usb_summary_tether_power : j == 16 ? C1715R.string.usb_summary_photo_transfers_power : j == 8 ? C1715R.string.usb_summary_MIDI_power : C1715R.string.usb_summary_power_only;
    }

    public /* synthetic */ void lambda$new$0$ConnectedUsbDeviceUpdater(boolean z, long j, int i, int i2) {
        if (z) {
            Preference preference = this.mUsbPreference;
            if (i2 != 2) {
                j = 0;
            }
            preference.setSummary(getSummary(j, i));
            this.mDevicePreferenceCallback.onDeviceAdded(this.mUsbPreference);
            return;
        }
        this.mDevicePreferenceCallback.onDeviceRemoved(this.mUsbPreference);
    }

    public ConnectedUsbDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback) {
        this(context, dashboardFragment, devicePreferenceCallback, new UsbBackend(context));
    }

    ConnectedUsbDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback, UsbBackend usbBackend) {
        this.mUsbConnectionListener = new UsbConnectionBroadcastReceiver.UsbConnectionListener() {
            public final void onUsbConnectionChanged(boolean z, long j, int i, int i2) {
                ConnectedUsbDeviceUpdater.this.lambda$new$0$ConnectedUsbDeviceUpdater(z, j, i, i2);
            }
        };
        this.mFragment = dashboardFragment;
        this.mDevicePreferenceCallback = devicePreferenceCallback;
        this.mUsbBackend = usbBackend;
        this.mUsbReceiver = new UsbConnectionBroadcastReceiver(context, this.mUsbConnectionListener, this.mUsbBackend);
    }

    public void registerCallback() {
        this.mUsbReceiver.register();
    }

    public void unregisterCallback() {
        this.mUsbReceiver.unregister();
    }

    public void initUsbPreference(Context context) {
        this.mUsbPreference = new Preference(context, (AttributeSet) null);
        this.mUsbPreference.setTitle((int) C1715R.string.usb_pref);
        this.mUsbPreference.setIcon((int) C1715R.C1717drawable.ic_usb);
        this.mUsbPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return ConnectedUsbDeviceUpdater.this.lambda$initUsbPreference$1$ConnectedUsbDeviceUpdater(preference);
            }
        });
        forceUpdate();
    }

    public /* synthetic */ boolean lambda$initUsbPreference$1$ConnectedUsbDeviceUpdater(Preference preference) {
        new SubSettingLauncher(this.mFragment.getContext()).setDestination(UsbDetailsFragment.class.getName()).setTitleRes(C1715R.string.device_details_title).setSourceMetricsCategory(this.mFragment.getMetricsCategory()).launch();
        return true;
    }

    private void forceUpdate() {
        this.mUsbReceiver.register();
    }
}
