package com.android.settings.connecteddevice;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.BidiFormatter;
import android.text.TextUtils;
import androidx.preference.PreferenceScreen;
import com.android.settings.bluetooth.AlwaysDiscoverable;
import com.android.settings.bluetooth.Utils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.widget.FooterPreference;
import com.android.settingslib.widget.FooterPreferenceMixinCompat;
import com.havoc.config.center.C1715R;

public class DiscoverableFooterPreferenceController extends BasePreferenceController implements LifecycleObserver, OnResume, OnPause {
    private static final String KEY = "discoverable_footer_preference";
    private AlwaysDiscoverable mAlwaysDiscoverable;
    private BluetoothAdapter mBluetoothAdapter;
    BroadcastReceiver mBluetoothChangedReceiver;
    private FooterPreferenceMixinCompat mFooterPreferenceMixin;
    LocalBluetoothManager mLocalManager;
    private FooterPreference mPreference;

    public DiscoverableFooterPreferenceController(Context context) {
        super(context, KEY);
        this.mLocalManager = Utils.getLocalBtManager(context);
        if (this.mLocalManager != null) {
            this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            this.mAlwaysDiscoverable = new AlwaysDiscoverable(context);
            initReceiver();
        }
    }

    private void initReceiver() {
        this.mBluetoothChangedReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    DiscoverableFooterPreferenceController.this.updateFooterPreferenceTitle(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE));
                }
            }
        };
    }

    public void init(DashboardFragment dashboardFragment) {
        this.mFooterPreferenceMixin = new FooterPreferenceMixinCompat(dashboardFragment, dashboardFragment.getSettingsLifecycle());
    }

    /* access modifiers changed from: package-private */
    public void init(FooterPreferenceMixinCompat footerPreferenceMixinCompat, FooterPreference footerPreference, AlwaysDiscoverable alwaysDiscoverable) {
        this.mFooterPreferenceMixin = footerPreferenceMixinCompat;
        this.mPreference = footerPreference;
        this.mAlwaysDiscoverable = alwaysDiscoverable;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        addFooterPreference(preferenceScreen);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth") ? 0 : 3;
    }

    private void addFooterPreference(PreferenceScreen preferenceScreen) {
        this.mPreference = this.mFooterPreferenceMixin.createFooterPreference();
        this.mPreference.setKey(KEY);
        preferenceScreen.addPreference(this.mPreference);
    }

    public void onResume() {
        if (this.mLocalManager != null) {
            this.mContext.registerReceiver(this.mBluetoothChangedReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            this.mAlwaysDiscoverable.start();
            updateFooterPreferenceTitle(this.mBluetoothAdapter.getState());
        }
    }

    public void onPause() {
        if (this.mLocalManager != null) {
            this.mContext.unregisterReceiver(this.mBluetoothChangedReceiver);
            this.mAlwaysDiscoverable.stop();
        }
    }

    /* access modifiers changed from: private */
    public void updateFooterPreferenceTitle(int i) {
        if (i == 12) {
            this.mPreference.setTitle(getPreferenceTitle());
        } else {
            this.mPreference.setTitle((int) C1715R.string.bluetooth_off_footer);
        }
    }

    private CharSequence getPreferenceTitle() {
        String name = this.mBluetoothAdapter.getName();
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        return TextUtils.expandTemplate(this.mContext.getText(C1715R.string.bluetooth_device_name_summary), new CharSequence[]{BidiFormatter.getInstance().unicodeWrap(name)});
    }
}
