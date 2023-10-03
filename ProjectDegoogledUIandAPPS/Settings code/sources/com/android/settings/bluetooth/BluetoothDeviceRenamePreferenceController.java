package com.android.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

public class BluetoothDeviceRenamePreferenceController extends BluetoothDeviceNamePreferenceController {
    private Fragment mFragment;
    private MetricsFeatureProvider mMetricsFeatureProvider;

    public BluetoothDeviceRenamePreferenceController(Context context, String str) {
        super(context, str);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    /* access modifiers changed from: protected */
    public void updatePreferenceState(Preference preference) {
        preference.setSummary(getSummary());
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        preference.setVisible(bluetoothAdapter != null && bluetoothAdapter.isEnabled());
    }

    public CharSequence getSummary() {
        return getDeviceName();
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(getPreferenceKey(), preference.getKey()) || this.mFragment == null) {
            return false;
        }
        this.mMetricsFeatureProvider.action(this.mContext, 161, (Pair<Integer, Object>[]) new Pair[0]);
        new LocalDeviceNameDialogFragment().show(this.mFragment.getFragmentManager(), "LocalAdapterName");
        return true;
    }
}
