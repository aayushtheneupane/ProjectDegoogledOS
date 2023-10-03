package com.android.settings.sound;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.A2dpProfile;
import com.android.settingslib.bluetooth.HearingAidProfile;
import com.havoc.config.center.C1715R;
import java.util.List;

public class MediaOutputPreferenceController extends AudioSwitchPreferenceController {
    public MediaOutputPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateState(Preference preference) {
        CharSequence charSequence;
        if (preference != null) {
            boolean z = false;
            if (Utils.isAudioModeOngoingCall(this.mContext)) {
                this.mPreference.setVisible(false);
                preference.setSummary(this.mContext.getText(C1715R.string.media_out_summary_ongoing_call_state));
                return;
            }
            BluetoothDevice bluetoothDevice = null;
            List<BluetoothDevice> connectedA2dpDevices = getConnectedA2dpDevices();
            List<BluetoothDevice> connectedHearingAidDevices = getConnectedHearingAidDevices();
            if (this.mAudioManager.getMode() == 0 && ((connectedA2dpDevices != null && !connectedA2dpDevices.isEmpty()) || (connectedHearingAidDevices != null && !connectedHearingAidDevices.isEmpty()))) {
                z = true;
                bluetoothDevice = findActiveDevice();
            }
            this.mPreference.setVisible(z);
            Preference preference2 = this.mPreference;
            if (bluetoothDevice == null) {
                charSequence = this.mContext.getText(C1715R.string.media_output_default_summary);
            } else {
                charSequence = bluetoothDevice.getAliasName();
            }
            preference2.setSummary(charSequence);
        }
    }

    public BluetoothDevice findActiveDevice() {
        BluetoothDevice findActiveHearingAidDevice = findActiveHearingAidDevice();
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        return (findActiveHearingAidDevice != null || a2dpProfile == null) ? findActiveHearingAidDevice : a2dpProfile.getActiveDevice();
    }

    /* access modifiers changed from: protected */
    public BluetoothDevice findActiveHearingAidDevice() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile == null) {
            return null;
        }
        for (BluetoothDevice next : hearingAidProfile.getActiveDevices()) {
            if (next != null) {
                return next;
            }
        }
        return null;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        this.mContext.startActivity(new Intent().setAction("com.android.settings.panel.action.MEDIA_OUTPUT").setFlags(268435456));
        return true;
    }
}
