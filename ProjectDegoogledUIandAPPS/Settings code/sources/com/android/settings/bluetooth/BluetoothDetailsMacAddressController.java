package com.android.settings.bluetooth;

import android.content.Context;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.FooterPreference;
import com.android.settingslib.widget.FooterPreferenceMixinCompat;
import com.havoc.config.center.C1715R;

public class BluetoothDetailsMacAddressController extends BluetoothDetailsController {
    FooterPreference mFooterPreference;
    FooterPreferenceMixinCompat mFooterPreferenceMixin;

    public BluetoothDetailsMacAddressController(Context context, PreferenceFragmentCompat preferenceFragmentCompat, CachedBluetoothDevice cachedBluetoothDevice, Lifecycle lifecycle) {
        super(context, preferenceFragmentCompat, cachedBluetoothDevice, lifecycle);
        this.mFooterPreferenceMixin = new FooterPreferenceMixinCompat(preferenceFragmentCompat, lifecycle);
    }

    /* access modifiers changed from: protected */
    public void init(PreferenceScreen preferenceScreen) {
        this.mFooterPreference = this.mFooterPreferenceMixin.createFooterPreference();
        this.mFooterPreference.setTitle((CharSequence) this.mContext.getString(C1715R.string.bluetooth_device_mac_address, new Object[]{this.mCachedDevice.getAddress()}));
    }

    /* access modifiers changed from: protected */
    public void refresh() {
        this.mFooterPreference.setTitle((CharSequence) this.mContext.getString(C1715R.string.bluetooth_device_mac_address, new Object[]{this.mCachedDevice.getAddress()}));
    }

    public String getPreferenceKey() {
        FooterPreference footerPreference = this.mFooterPreference;
        if (footerPreference == null) {
            return null;
        }
        return footerPreference.getKey();
    }
}
