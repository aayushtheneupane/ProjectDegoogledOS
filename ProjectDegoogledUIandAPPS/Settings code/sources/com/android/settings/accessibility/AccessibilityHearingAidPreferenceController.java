package com.android.settings.accessibility;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.bluetooth.BluetoothDeviceDetailsFragment;
import com.android.settings.bluetooth.Utils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.SubSettingLauncher;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.havoc.config.center.C1715R;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class AccessibilityHearingAidPreferenceController extends BasePreferenceController implements LifecycleObserver {
    private static final String TAG = "AccessibilityHearingAidPreferenceController";
    private final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private FragmentManager mFragmentManager;
    private final BroadcastReceiver mHearingAidChangedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                if (intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0) == 2) {
                    AccessibilityHearingAidPreferenceController accessibilityHearingAidPreferenceController = AccessibilityHearingAidPreferenceController.this;
                    accessibilityHearingAidPreferenceController.updateState(accessibilityHearingAidPreferenceController.mHearingAidPreference);
                    return;
                }
                AccessibilityHearingAidPreferenceController.this.mHearingAidPreference.setSummary((int) C1715R.string.accessibility_hearingaid_not_connected_summary);
            } else if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) && intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) != 12) {
                AccessibilityHearingAidPreferenceController.this.mHearingAidPreference.setSummary((int) C1715R.string.accessibility_hearingaid_not_connected_summary);
            }
        }
    };
    /* access modifiers changed from: private */
    public Preference mHearingAidPreference;
    private boolean mHearingAidProfileSupported = isHearingAidProfileSupported();
    private final LocalBluetoothManager mLocalBluetoothManager = getLocalBluetoothManager();

    public AccessibilityHearingAidPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mHearingAidPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public int getAvailabilityStatus() {
        return this.mHearingAidProfileSupported ? 0 : 3;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if (this.mHearingAidProfileSupported) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            this.mContext.registerReceiver(this.mHearingAidChangedReceiver, intentFilter);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        if (this.mHearingAidProfileSupported) {
            this.mContext.unregisterReceiver(this.mHearingAidChangedReceiver);
        }
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        CachedBluetoothDevice connectedHearingAidDevice = getConnectedHearingAidDevice();
        if (connectedHearingAidDevice == null) {
            launchHearingAidInstructionDialog();
            return true;
        }
        launchBluetoothDeviceDetailSetting(connectedHearingAidDevice);
        return true;
    }

    public CharSequence getSummary() {
        CachedBluetoothDevice connectedHearingAidDevice = getConnectedHearingAidDevice();
        if (connectedHearingAidDevice == null) {
            return this.mContext.getText(C1715R.string.accessibility_hearingaid_not_connected_summary);
        }
        return connectedHearingAidDevice.getName();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    /* access modifiers changed from: package-private */
    public CachedBluetoothDevice getConnectedHearingAidDevice() {
        BluetoothAdapter bluetoothAdapter;
        if (this.mHearingAidProfileSupported && (bluetoothAdapter = this.mBluetoothAdapter) != null && bluetoothAdapter.isEnabled()) {
            for (BluetoothDevice next : this.mLocalBluetoothManager.getProfileManager().getHearingAidProfile().getConnectedDevices()) {
                if (!this.mLocalBluetoothManager.getCachedDeviceManager().isSubDevice(next)) {
                    return this.mLocalBluetoothManager.getCachedDeviceManager().findDevice(next);
                }
            }
        }
        return null;
    }

    private boolean isHearingAidProfileSupported() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled() || !this.mBluetoothAdapter.getSupportedProfiles().contains(21)) {
            return false;
        }
        return true;
    }

    private LocalBluetoothManager getLocalBluetoothManager() {
        FutureTask futureTask = new FutureTask(new Callable() {
            public final Object call() {
                return AccessibilityHearingAidPreferenceController.this.mo7695xf1dca286();
            }
        });
        try {
            futureTask.run();
            return (LocalBluetoothManager) futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.w(TAG, "Error getting LocalBluetoothManager.", e);
            return null;
        }
    }

    /* renamed from: lambda$getLocalBluetoothManager$0$AccessibilityHearingAidPreferenceController */
    public /* synthetic */ LocalBluetoothManager mo7695xf1dca286() throws Exception {
        return Utils.getLocalBtManager(this.mContext);
    }

    /* access modifiers changed from: package-private */
    public void setPreference(Preference preference) {
        this.mHearingAidPreference = preference;
    }

    /* access modifiers changed from: package-private */
    public void launchBluetoothDeviceDetailSetting(CachedBluetoothDevice cachedBluetoothDevice) {
        if (cachedBluetoothDevice != null) {
            Bundle bundle = new Bundle();
            bundle.putString("device_address", cachedBluetoothDevice.getDevice().getAddress());
            new SubSettingLauncher(this.mContext).setDestination(BluetoothDeviceDetailsFragment.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.device_details_title).setSourceMetricsCategory(2).launch();
        }
    }

    /* access modifiers changed from: package-private */
    public void launchHearingAidInstructionDialog() {
        HearingAidDialogFragment.newInstance().show(this.mFragmentManager, HearingAidDialogFragment.class.toString());
    }
}
