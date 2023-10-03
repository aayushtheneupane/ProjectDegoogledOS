package com.android.settings.development;

import android.bluetooth.BluetoothCodecConfig;
import android.content.Context;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class BluetoothAudioChannelModePreferenceController extends AbstractBluetoothA2dpPreferenceController {
    /* access modifiers changed from: protected */
    public int getDefaultIndex() {
        return 0;
    }

    public String getPreferenceKey() {
        return "bluetooth_select_a2dp_channel_mode";
    }

    public BluetoothAudioChannelModePreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
    }

    /* access modifiers changed from: protected */
    public String[] getListValues() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_channel_mode_values);
    }

    /* access modifiers changed from: protected */
    public String[] getListSummaries() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_channel_mode_summaries);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r3 != 2) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeConfigurationValues(java.lang.Object r3) {
        /*
            r2 = this;
            androidx.preference.ListPreference r0 = r2.mPreference
            java.lang.String r3 = r3.toString()
            int r3 = r0.findIndexOfValue(r3)
            r0 = 2
            r1 = 1
            if (r3 == 0) goto L_0x0015
            if (r3 == r1) goto L_0x0013
            if (r3 == r0) goto L_0x0016
            goto L_0x0015
        L_0x0013:
            r0 = r1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            com.android.settings.development.BluetoothA2dpConfigStore r2 = r2.mBluetoothA2dpConfigStore
            r2.setChannelMode(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.development.BluetoothAudioChannelModePreferenceController.writeConfigurationValues(java.lang.Object):void");
    }

    /* access modifiers changed from: protected */
    public int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig) {
        int channelMode = bluetoothCodecConfig.getChannelMode();
        if (channelMode != 1) {
            return channelMode != 2 ? 0 : 2;
        }
        return 1;
    }
}
