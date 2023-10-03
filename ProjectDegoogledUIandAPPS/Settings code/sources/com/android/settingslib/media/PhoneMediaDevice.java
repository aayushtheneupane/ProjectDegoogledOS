package com.android.settingslib.media;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.android.settingslib.R$drawable;
import com.android.settingslib.R$string;
import com.android.settingslib.bluetooth.A2dpProfile;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.HearingAidProfile;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;

public class PhoneMediaDevice extends MediaDevice {
    private LocalBluetoothManager mLocalBluetoothManager;
    private LocalBluetoothProfileManager mProfileManager;
    private String mSummary = "";

    public String getId() {
        return "phone_media_device_id_1";
    }

    public boolean isConnected() {
        return true;
    }

    PhoneMediaDevice(Context context, LocalBluetoothManager localBluetoothManager) {
        super(context, 1);
        this.mLocalBluetoothManager = localBluetoothManager;
        this.mProfileManager = this.mLocalBluetoothManager.getProfileManager();
        initDeviceRecord();
    }

    public String getName() {
        return this.mContext.getString(R$string.media_transfer_this_device_name);
    }

    public String getSummary() {
        return this.mSummary;
    }

    public Drawable getIcon() {
        Context context = this.mContext;
        return BluetoothUtils.buildBtRainbowDrawable(context, context.getDrawable(R$drawable.ic_smartphone), getId().hashCode());
    }

    public boolean connect() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        boolean z = false;
        if (hearingAidProfile == null || a2dpProfile == null) {
            if (a2dpProfile != null) {
                z = a2dpProfile.setActiveDevice((BluetoothDevice) null);
            } else if (hearingAidProfile != null) {
                z = hearingAidProfile.setActiveDevice((BluetoothDevice) null);
            }
        } else if (hearingAidProfile.setActiveDevice((BluetoothDevice) null) && a2dpProfile.setActiveDevice((BluetoothDevice) null)) {
            z = true;
        }
        updateSummary(z);
        setConnectedRecord();
        Log.d("PhoneMediaDevice", "connect() device : " + getName() + ", is selected : " + z);
        return z;
    }

    public void disconnect() {
        updateSummary(false);
    }

    public void updateSummary(boolean z) {
        this.mSummary = z ? this.mContext.getString(R$string.bluetooth_active_no_battery_level) : "";
    }
}
