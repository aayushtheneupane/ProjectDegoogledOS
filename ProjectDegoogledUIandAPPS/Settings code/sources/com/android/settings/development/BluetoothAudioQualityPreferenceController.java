package com.android.settings.development;

import android.bluetooth.BluetoothCodecConfig;
import android.content.Context;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class BluetoothAudioQualityPreferenceController extends AbstractBluetoothA2dpPreferenceController {
    /* access modifiers changed from: protected */
    public int getDefaultIndex() {
        return 3;
    }

    public String getPreferenceKey() {
        return "bluetooth_select_a2dp_ldac_playback_quality";
    }

    public BluetoothAudioQualityPreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
    }

    /* access modifiers changed from: protected */
    public String[] getListValues() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_ldac_playback_quality_values);
    }

    /* access modifiers changed from: protected */
    public String[] getListSummaries() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_ldac_playback_quality_summaries);
    }

    /* access modifiers changed from: protected */
    public void writeConfigurationValues(Object obj) {
        int findIndexOfValue = this.mPreference.findIndexOfValue(obj.toString());
        this.mBluetoothA2dpConfigStore.setCodecSpecific1Value((findIndexOfValue == 0 || findIndexOfValue == 1 || findIndexOfValue == 2 || findIndexOfValue == 3) ? findIndexOfValue + 1000 : 0);
    }

    /* access modifiers changed from: protected */
    public int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig) {
        int codecSpecific1 = (int) bluetoothCodecConfig.getCodecSpecific1();
        int i = codecSpecific1 > 0 ? codecSpecific1 % 10 : 3;
        if (i == 0 || i == 1 || i == 2 || i == 3) {
            return i;
        }
        return 3;
    }
}
