package com.android.settings.bluetooth;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.DeviceConfig;
import android.util.Pair;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class BluetoothDetailsHeaderController extends BluetoothDetailsController {
    private CachedBluetoothDeviceManager mDeviceManager = this.mLocalManager.getCachedDeviceManager();
    private EntityHeaderController mHeaderController;
    private LocalBluetoothManager mLocalManager;

    public String getPreferenceKey() {
        return "bluetooth_device_header";
    }

    public BluetoothDetailsHeaderController(Context context, PreferenceFragmentCompat preferenceFragmentCompat, CachedBluetoothDevice cachedBluetoothDevice, Lifecycle lifecycle, LocalBluetoothManager localBluetoothManager) {
        super(context, preferenceFragmentCompat, cachedBluetoothDevice, lifecycle);
        this.mLocalManager = localBluetoothManager;
    }

    public boolean isAvailable() {
        if (!DeviceConfig.getBoolean("settings_ui", "bt_advanced_header_enabled", true) || !BluetoothUtils.getBooleanMetaData(this.mCachedDevice.getDevice(), 6)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void init(PreferenceScreen preferenceScreen) {
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference("bluetooth_device_header");
        this.mHeaderController = EntityHeaderController.newInstance(this.mFragment.getActivity(), this.mFragment, layoutPreference.findViewById(C1715R.C1718id.entity_header));
        preferenceScreen.addPreference(layoutPreference);
    }

    /* access modifiers changed from: protected */
    public void setHeaderProperties() {
        Pair<Drawable, String> btRainbowDrawableWithDescription = BluetoothUtils.getBtRainbowDrawableWithDescription(this.mContext, this.mCachedDevice);
        String connectionSummary = this.mCachedDevice.getConnectionSummary();
        this.mHeaderController.setSecondSummary(this.mDeviceManager.getSubDeviceSummary(this.mCachedDevice));
        this.mHeaderController.setLabel((CharSequence) this.mCachedDevice.getName());
        this.mHeaderController.setIcon((Drawable) btRainbowDrawableWithDescription.first);
        this.mHeaderController.setIconContentDescription((String) btRainbowDrawableWithDescription.second);
        this.mHeaderController.setSummary((CharSequence) connectionSummary);
    }

    /* access modifiers changed from: protected */
    public void refresh() {
        if (isAvailable()) {
            setHeaderProperties();
            this.mHeaderController.done((Activity) this.mFragment.getActivity(), true);
        }
    }
}
