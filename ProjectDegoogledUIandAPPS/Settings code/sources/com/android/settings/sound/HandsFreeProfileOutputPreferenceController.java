package com.android.settings.sound;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.HeadsetProfile;
import com.android.settingslib.bluetooth.HearingAidProfile;
import com.havoc.config.center.C1715R;
import java.util.List;

public class HandsFreeProfileOutputPreferenceController extends AudioSwitchPreferenceController implements Preference.OnPreferenceChangeListener {
    private static final int INVALID_INDEX = -1;

    public HandsFreeProfileOutputPreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        String str = (String) obj;
        if (!(preference instanceof ListPreference)) {
            return false;
        }
        CharSequence text = this.mContext.getText(C1715R.string.media_output_default_summary);
        ListPreference listPreference = (ListPreference) preference;
        if (TextUtils.equals(str, text)) {
            this.mSelectedIndex = getDefaultDeviceIndex();
            setActiveBluetoothDevice((BluetoothDevice) null);
            listPreference.setSummary(text);
            return true;
        }
        int connectedDeviceIndex = getConnectedDeviceIndex(str);
        if (connectedDeviceIndex == INVALID_INDEX) {
            return false;
        }
        BluetoothDevice bluetoothDevice = this.mConnectedDevices.get(connectedDeviceIndex);
        this.mSelectedIndex = connectedDeviceIndex;
        setActiveBluetoothDevice(bluetoothDevice);
        listPreference.setSummary(bluetoothDevice.getAliasName());
        return true;
    }

    private int getConnectedDeviceIndex(String str) {
        List<BluetoothDevice> list = this.mConnectedDevices;
        if (list == null) {
            return INVALID_INDEX;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(this.mConnectedDevices.get(i).getAddress(), str)) {
                return i;
            }
        }
        return INVALID_INDEX;
    }

    public void updateState(Preference preference) {
        if (preference != null) {
            if (!Utils.isAudioModeOngoingCall(this.mContext)) {
                this.mPreference.setVisible(false);
                preference.setSummary(this.mContext.getText(C1715R.string.media_output_default_summary));
                return;
            }
            this.mConnectedDevices.clear();
            this.mConnectedDevices.addAll(getConnectedHfpDevices());
            this.mConnectedDevices.addAll(getConnectedHearingAidDevices());
            int size = this.mConnectedDevices.size();
            if (size == 0) {
                this.mPreference.setVisible(false);
                CharSequence text = this.mContext.getText(C1715R.string.media_output_default_summary);
                CharSequence[] charSequenceArr = {text};
                this.mSelectedIndex = getDefaultDeviceIndex();
                preference.setSummary(text);
                setPreference(charSequenceArr, charSequenceArr, preference);
                return;
            }
            this.mPreference.setVisible(true);
            int i = size + 1;
            CharSequence[] charSequenceArr2 = new CharSequence[i];
            CharSequence[] charSequenceArr3 = new CharSequence[i];
            setupPreferenceEntries(charSequenceArr2, charSequenceArr3, findActiveDevice());
            setPreference(charSequenceArr2, charSequenceArr3, preference);
        }
    }

    /* access modifiers changed from: package-private */
    public int getDefaultDeviceIndex() {
        return this.mConnectedDevices.size();
    }

    /* access modifiers changed from: package-private */
    public void setupPreferenceEntries(CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2, BluetoothDevice bluetoothDevice) {
        this.mSelectedIndex = getDefaultDeviceIndex();
        CharSequence text = this.mContext.getText(C1715R.string.media_output_default_summary);
        int i = this.mSelectedIndex;
        charSequenceArr[i] = text;
        charSequenceArr2[i] = text;
        int size = this.mConnectedDevices.size();
        for (int i2 = 0; i2 < size; i2++) {
            BluetoothDevice bluetoothDevice2 = this.mConnectedDevices.get(i2);
            charSequenceArr[i2] = bluetoothDevice2.getAliasName();
            charSequenceArr2[i2] = bluetoothDevice2.getAddress();
            if (bluetoothDevice2.equals(bluetoothDevice)) {
                this.mSelectedIndex = i2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setPreference(CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2, Preference preference) {
        ListPreference listPreference = (ListPreference) preference;
        listPreference.setEntries(charSequenceArr);
        listPreference.setEntryValues(charSequenceArr2);
        listPreference.setValueIndex(this.mSelectedIndex);
        listPreference.setSummary(charSequenceArr[this.mSelectedIndex]);
        this.mAudioSwitchPreferenceCallback.onPreferenceDataChanged(listPreference);
    }

    public void setActiveBluetoothDevice(BluetoothDevice bluetoothDevice) {
        if (Utils.isAudioModeOngoingCall(this.mContext)) {
            HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
            HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
            if (hearingAidProfile != null && headsetProfile != null && bluetoothDevice == null) {
                headsetProfile.setActiveDevice((BluetoothDevice) null);
                hearingAidProfile.setActiveDevice((BluetoothDevice) null);
            } else if (hearingAidProfile != null && hearingAidProfile.getHiSyncId(bluetoothDevice) != 0) {
                hearingAidProfile.setActiveDevice(bluetoothDevice);
            } else if (headsetProfile != null) {
                headsetProfile.setActiveDevice(bluetoothDevice);
            }
        }
    }

    public BluetoothDevice findActiveDevice() {
        BluetoothDevice findActiveHearingAidDevice = findActiveHearingAidDevice();
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        return (findActiveHearingAidDevice != null || headsetProfile == null) ? findActiveHearingAidDevice : headsetProfile.getActiveDevice();
    }
}
