package com.android.settings.development;

import android.bluetooth.BluetoothCodecConfig;
import android.content.Context;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class BluetoothAudioBitsPerSamplePreferenceController extends AbstractBluetoothA2dpPreferenceController {
    /* access modifiers changed from: protected */
    public int getDefaultIndex() {
        return 0;
    }

    public String getPreferenceKey() {
        return "bluetooth_select_a2dp_bits_per_sample";
    }

    public BluetoothAudioBitsPerSamplePreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
    }

    /* access modifiers changed from: protected */
    public String[] getListValues() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_bits_per_sample_values);
    }

    /* access modifiers changed from: protected */
    public String[] getListSummaries() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_bits_per_sample_summaries);
    }

    /* access modifiers changed from: protected */
    public void writeConfigurationValues(Object obj) {
        int findIndexOfValue = this.mPreference.findIndexOfValue(obj.toString());
        int i = 2;
        if (findIndexOfValue != 0) {
            if (findIndexOfValue == 1) {
                i = 1;
            } else if (findIndexOfValue != 2) {
                if (findIndexOfValue == 3) {
                    i = 4;
                }
            }
            this.mBluetoothA2dpConfigStore.setBitsPerSample(i);
        }
        i = 0;
        this.mBluetoothA2dpConfigStore.setBitsPerSample(i);
    }

    /* access modifiers changed from: protected */
    public int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig) {
        int bitsPerSample = bluetoothCodecConfig.getBitsPerSample();
        if (bitsPerSample == 1) {
            return 1;
        }
        if (bitsPerSample != 2) {
            return bitsPerSample != 4 ? 0 : 3;
        }
        return 2;
    }
}
