package com.android.settings.development;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothCodecConfig;
import android.bluetooth.BluetoothCodecStatus;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnDestroy;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.havoc.config.center.C1715R;

public abstract class AbstractBluetoothA2dpPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin, BluetoothServiceConnectionListener, LifecycleObserver, OnDestroy {
    static final int STREAMING_LABEL_ID = 2131887049;
    protected BluetoothA2dp mBluetoothA2dp;
    protected final BluetoothA2dpConfigStore mBluetoothA2dpConfigStore;
    private final String[] mListSummaries = getListSummaries();
    private final String[] mListValues = getListValues();
    protected ListPreference mPreference;

    /* access modifiers changed from: protected */
    public abstract int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig);

    /* access modifiers changed from: protected */
    public abstract int getDefaultIndex();

    /* access modifiers changed from: protected */
    public abstract String[] getListSummaries();

    /* access modifiers changed from: protected */
    public abstract String[] getListValues();

    public void onBluetoothCodecUpdated() {
    }

    /* access modifiers changed from: protected */
    public abstract void writeConfigurationValues(Object obj);

    public AbstractBluetoothA2dpPreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context);
        this.mBluetoothA2dpConfigStore = bluetoothA2dpConfigStore;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ListPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setValue(this.mListValues[getDefaultIndex()]);
        this.mPreference.setSummary(this.mListSummaries[getDefaultIndex()]);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mBluetoothA2dp == null) {
            return false;
        }
        writeConfigurationValues(obj);
        BluetoothCodecConfig createCodecConfig = this.mBluetoothA2dpConfigStore.createCodecConfig();
        synchronized (this.mBluetoothA2dpConfigStore) {
            if (this.mBluetoothA2dp != null) {
                setCodecConfigPreference((BluetoothDevice) null, createCodecConfig);
            }
        }
        int findIndexOfValue = this.mPreference.findIndexOfValue(obj.toString());
        if (findIndexOfValue == getDefaultIndex()) {
            this.mPreference.setSummary(this.mListSummaries[findIndexOfValue]);
        } else {
            this.mPreference.setSummary(this.mContext.getResources().getString(C1715R.string.bluetooth_select_a2dp_codec_streaming_label, new Object[]{this.mListSummaries[findIndexOfValue]}));
        }
        return true;
    }

    public void updateState(Preference preference) {
        BluetoothCodecConfig codecConfig;
        if (getCodecConfig((BluetoothDevice) null) != null && this.mPreference != null) {
            synchronized (this.mBluetoothA2dpConfigStore) {
                codecConfig = getCodecConfig((BluetoothDevice) null);
            }
            int currentA2dpSettingIndex = getCurrentA2dpSettingIndex(codecConfig);
            this.mPreference.setValue(this.mListValues[currentA2dpSettingIndex]);
            if (currentA2dpSettingIndex == getDefaultIndex()) {
                this.mPreference.setSummary(this.mListSummaries[currentA2dpSettingIndex]);
            } else {
                this.mPreference.setSummary(this.mContext.getResources().getString(C1715R.string.bluetooth_select_a2dp_codec_streaming_label, new Object[]{this.mListSummaries[currentA2dpSettingIndex]}));
            }
            writeConfigurationValues(this.mListValues[currentA2dpSettingIndex]);
        }
    }

    public void onBluetoothServiceConnected(BluetoothA2dp bluetoothA2dp) {
        this.mBluetoothA2dp = bluetoothA2dp;
        updateState(this.mPreference);
    }

    public void onBluetoothServiceDisconnected() {
        this.mBluetoothA2dp = null;
    }

    public void onDestroy() {
        this.mBluetoothA2dp = null;
    }

    /* access modifiers changed from: package-private */
    public void setCodecConfigPreference(BluetoothDevice bluetoothDevice, BluetoothCodecConfig bluetoothCodecConfig) {
        this.mBluetoothA2dp.setCodecConfigPreference(bluetoothDevice, bluetoothCodecConfig);
    }

    /* access modifiers changed from: package-private */
    public BluetoothCodecConfig getCodecConfig(BluetoothDevice bluetoothDevice) {
        BluetoothCodecStatus codecStatus;
        BluetoothA2dp bluetoothA2dp = this.mBluetoothA2dp;
        if (bluetoothA2dp == null || (codecStatus = bluetoothA2dp.getCodecStatus(bluetoothDevice)) == null) {
            return null;
        }
        return codecStatus.getCodecConfig();
    }
}
