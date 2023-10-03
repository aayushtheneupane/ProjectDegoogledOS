package com.android.settings.development;

import android.bluetooth.BluetoothCodecConfig;
import android.content.Context;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class BluetoothAudioSampleRatePreferenceController extends AbstractBluetoothA2dpPreferenceController {
    /* access modifiers changed from: protected */
    public int getDefaultIndex() {
        return 0;
    }

    public String getPreferenceKey() {
        return "bluetooth_select_a2dp_sample_rate";
    }

    public BluetoothAudioSampleRatePreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
    }

    /* access modifiers changed from: protected */
    public String[] getListValues() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_sample_rate_values);
    }

    /* access modifiers changed from: protected */
    public String[] getListSummaries() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_sample_rate_summaries);
    }

    /* access modifiers changed from: protected */
    public void writeConfigurationValues(Object obj) {
        int findIndexOfValue = this.mPreference.findIndexOfValue(obj.toString());
        int i = 4;
        if (findIndexOfValue != 0) {
            if (findIndexOfValue == 1) {
                i = 1;
            } else if (findIndexOfValue == 2) {
                i = 2;
            } else if (findIndexOfValue != 3) {
                if (findIndexOfValue == 4) {
                    i = 8;
                }
            }
            this.mBluetoothA2dpConfigStore.setSampleRate(i);
        }
        i = 0;
        this.mBluetoothA2dpConfigStore.setSampleRate(i);
    }

    /* access modifiers changed from: protected */
    public int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig) {
        int sampleRate = bluetoothCodecConfig.getSampleRate();
        if (sampleRate == 1) {
            return 1;
        }
        if (sampleRate == 2) {
            return 2;
        }
        if (sampleRate != 4) {
            return sampleRate != 8 ? 0 : 4;
        }
        return 3;
    }
}
