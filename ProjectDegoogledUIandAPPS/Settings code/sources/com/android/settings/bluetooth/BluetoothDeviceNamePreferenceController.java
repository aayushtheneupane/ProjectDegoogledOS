package com.android.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.BidiFormatter;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;

public class BluetoothDeviceNamePreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final String TAG = "BluetoothNamePrefCtrl";
    protected BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Preference mPreference;
    final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            BluetoothAdapter bluetoothAdapter;
            String action = intent.getAction();
            if (TextUtils.equals(action, "android.bluetooth.adapter.action.LOCAL_NAME_CHANGED")) {
                BluetoothDeviceNamePreferenceController bluetoothDeviceNamePreferenceController = BluetoothDeviceNamePreferenceController.this;
                if (bluetoothDeviceNamePreferenceController.mPreference != null && (bluetoothAdapter = bluetoothDeviceNamePreferenceController.mBluetoothAdapter) != null && bluetoothAdapter.isEnabled()) {
                    BluetoothDeviceNamePreferenceController bluetoothDeviceNamePreferenceController2 = BluetoothDeviceNamePreferenceController.this;
                    bluetoothDeviceNamePreferenceController2.updatePreferenceState(bluetoothDeviceNamePreferenceController2.mPreference);
                }
            } else if (TextUtils.equals(action, "android.bluetooth.adapter.action.STATE_CHANGED")) {
                BluetoothDeviceNamePreferenceController bluetoothDeviceNamePreferenceController3 = BluetoothDeviceNamePreferenceController.this;
                bluetoothDeviceNamePreferenceController3.updatePreferenceState(bluetoothDeviceNamePreferenceController3.mPreference);
            }
        }
    };

    public BluetoothDeviceNamePreferenceController(Context context, String str) {
        super(context, str);
        if (this.mBluetoothAdapter == null) {
            Log.e(TAG, "Bluetooth is not supported on this device");
        }
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        super.displayPreference(preferenceScreen);
    }

    public void onStart() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
    }

    public void onStop() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public int getAvailabilityStatus() {
        return this.mBluetoothAdapter != null ? 0 : 3;
    }

    public void updateState(Preference preference) {
        updatePreferenceState(preference);
    }

    public CharSequence getSummary() {
        String deviceName = getDeviceName();
        if (TextUtils.isEmpty(deviceName)) {
            return super.getSummary();
        }
        return TextUtils.expandTemplate(this.mContext.getText(C1715R.string.bluetooth_device_name_summary), new CharSequence[]{BidiFormatter.getInstance().unicodeWrap(deviceName)}).toString();
    }

    public Preference createBluetoothDeviceNamePreference(PreferenceScreen preferenceScreen, int i) {
        this.mPreference = new Preference(preferenceScreen.getContext());
        this.mPreference.setOrder(i);
        this.mPreference.setKey(getPreferenceKey());
        preferenceScreen.addPreference(this.mPreference);
        return this.mPreference;
    }

    /* access modifiers changed from: protected */
    public void updatePreferenceState(Preference preference) {
        preference.setSelectable(false);
        preference.setSummary(getSummary());
    }

    /* access modifiers changed from: protected */
    public String getDeviceName() {
        return this.mBluetoothAdapter.getName();
    }
}
