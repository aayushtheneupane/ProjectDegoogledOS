package com.android.settings.deviceinfo;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannedString;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.bluetooth.BluetoothLengthDeviceNameFilter;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.widget.ValidatedEditTextPreference;
import com.android.settings.wifi.tether.WifiDeviceNameTextValidator;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreate;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;
import com.havoc.config.center.C1715R;

public class DeviceNamePreferenceController extends BasePreferenceController implements ValidatedEditTextPreference.Validator, Preference.OnPreferenceChangeListener, LifecycleObserver, OnSaveInstanceState, OnCreate {
    private static final String KEY_PENDING_DEVICE_NAME = "key_pending_device_name";
    private final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private String mDeviceName;
    private DeviceNamePreferenceHost mHost;
    private String mPendingDeviceName;
    private ValidatedEditTextPreference mPreference;
    private final WifiDeviceNameTextValidator mWifiDeviceNameTextValidator = new WifiDeviceNameTextValidator();
    protected WifiManager mWifiManager;

    public interface DeviceNamePreferenceHost {
        void showDeviceNameWarningDialog(String str);
    }

    public DeviceNamePreferenceController(Context context, String str) {
        super(context, str);
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        initializeDeviceName();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ValidatedEditTextPreference) preferenceScreen.findPreference(getPreferenceKey());
        CharSequence summary = getSummary();
        this.mPreference.setSummary(summary);
        this.mPreference.setText(summary.toString());
        this.mPreference.setValidator(this);
    }

    private void initializeDeviceName() {
        this.mDeviceName = Settings.Global.getString(this.mContext.getContentResolver(), "device_name");
        if (this.mDeviceName == null) {
            this.mDeviceName = Build.MODEL;
        }
    }

    public CharSequence getSummary() {
        return this.mDeviceName;
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_device_name) ? 0 : 3;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mPendingDeviceName = (String) obj;
        DeviceNamePreferenceHost deviceNamePreferenceHost = this.mHost;
        if (deviceNamePreferenceHost == null) {
            return true;
        }
        deviceNamePreferenceHost.showDeviceNameWarningDialog(this.mPendingDeviceName);
        return true;
    }

    public boolean isTextValid(String str) {
        return this.mWifiDeviceNameTextValidator.isTextValid(str);
    }

    public void updateDeviceName(boolean z) {
        String str;
        if (!z || (str = this.mPendingDeviceName) == null) {
            this.mPreference.setText(getSummary().toString());
        } else {
            setDeviceName(str);
        }
    }

    public void setHost(DeviceNamePreferenceHost deviceNamePreferenceHost) {
        this.mHost = deviceNamePreferenceHost;
    }

    private void setDeviceName(String str) {
        this.mDeviceName = str;
        setSettingsGlobalDeviceName(str);
        setBluetoothDeviceName(str);
        setTetherSsidName(str);
        this.mPreference.setSummary(getSummary());
    }

    private void setSettingsGlobalDeviceName(String str) {
        Settings.Global.putString(this.mContext.getContentResolver(), "device_name", str);
    }

    private void setBluetoothDeviceName(String str) {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.setName(getFilteredBluetoothString(str));
        }
    }

    private static final String getFilteredBluetoothString(String str) {
        CharSequence filter = new BluetoothLengthDeviceNameFilter().filter(str, 0, str.length(), new SpannedString(""), 0, 0);
        if (filter == null) {
            return str;
        }
        return filter.toString();
    }

    private void setTetherSsidName(String str) {
        WifiConfiguration wifiApConfiguration = this.mWifiManager.getWifiApConfiguration();
        wifiApConfiguration.SSID = str;
        this.mWifiManager.setWifiApConfiguration(wifiApConfiguration);
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.mPendingDeviceName = bundle.getString(KEY_PENDING_DEVICE_NAME, (String) null);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString(KEY_PENDING_DEVICE_NAME, this.mPendingDeviceName);
    }
}
