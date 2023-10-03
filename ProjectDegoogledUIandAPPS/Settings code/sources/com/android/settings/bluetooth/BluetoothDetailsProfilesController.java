package com.android.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.bluetooth.A2dpProfile;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import com.android.settingslib.bluetooth.MapProfile;
import com.android.settingslib.bluetooth.PanProfile;
import com.android.settingslib.bluetooth.PbapServerProfile;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.support.preferences.SwitchPreference;
import java.util.List;

public class BluetoothDetailsProfilesController extends BluetoothDetailsController implements Preference.OnPreferenceClickListener, LocalBluetoothProfileManager.ServiceListener {
    static final String HIGH_QUALITY_AUDIO_PREF_TAG = "A2dpProfileHighQualityAudio";
    private CachedBluetoothDevice mCachedDevice;
    private LocalBluetoothManager mManager;
    private LocalBluetoothProfileManager mProfileManager = this.mManager.getProfileManager();
    private PreferenceCategory mProfilesContainer;

    public String getPreferenceKey() {
        return "bluetooth_profiles";
    }

    public BluetoothDetailsProfilesController(Context context, PreferenceFragmentCompat preferenceFragmentCompat, LocalBluetoothManager localBluetoothManager, CachedBluetoothDevice cachedBluetoothDevice, Lifecycle lifecycle) {
        super(context, preferenceFragmentCompat, cachedBluetoothDevice, lifecycle);
        this.mManager = localBluetoothManager;
        this.mCachedDevice = cachedBluetoothDevice;
        lifecycle.addObserver(this);
    }

    /* access modifiers changed from: protected */
    public void init(PreferenceScreen preferenceScreen) {
        this.mProfilesContainer = (PreferenceCategory) preferenceScreen.findPreference(getPreferenceKey());
        refresh();
    }

    private SwitchPreference createProfilePreference(Context context, LocalBluetoothProfile localBluetoothProfile) {
        SwitchPreference switchPreference = new SwitchPreference(context);
        switchPreference.setKey(localBluetoothProfile.toString());
        switchPreference.setTitle(localBluetoothProfile.getNameResource(this.mCachedDevice.getDevice()));
        switchPreference.setOnPreferenceClickListener(this);
        switchPreference.setOrder(localBluetoothProfile.getOrdinal());
        return switchPreference;
    }

    private void refreshProfilePreference(SwitchPreference switchPreference, LocalBluetoothProfile localBluetoothProfile) {
        BluetoothDevice device = this.mCachedDevice.getDevice();
        switchPreference.setEnabled(!this.mCachedDevice.isBusy());
        if (localBluetoothProfile instanceof MapProfile) {
            switchPreference.setChecked(device.getMessageAccessPermission() == 1);
        } else if (localBluetoothProfile instanceof PbapServerProfile) {
            switchPreference.setChecked(device.getPhonebookAccessPermission() == 1);
        } else if (localBluetoothProfile instanceof PanProfile) {
            switchPreference.setChecked(localBluetoothProfile.getConnectionStatus(device) == 2);
        } else {
            switchPreference.setChecked(localBluetoothProfile.isPreferred(device));
        }
        if (localBluetoothProfile instanceof A2dpProfile) {
            A2dpProfile a2dpProfile = (A2dpProfile) localBluetoothProfile;
            SwitchPreference switchPreference2 = (SwitchPreference) this.mProfilesContainer.findPreference(HIGH_QUALITY_AUDIO_PREF_TAG);
            if (switchPreference2 == null) {
                return;
            }
            if (!a2dpProfile.isPreferred(device) || !a2dpProfile.supportsHighQualityAudio(device)) {
                switchPreference2.setVisible(false);
                return;
            }
            switchPreference2.setVisible(true);
            switchPreference2.setTitle((CharSequence) a2dpProfile.getHighQualityAudioOptionLabel(device));
            switchPreference2.setChecked(a2dpProfile.isHighQualityAudioEnabled(device));
            switchPreference2.setEnabled(!this.mCachedDevice.isBusy());
        }
    }

    private void enableProfile(LocalBluetoothProfile localBluetoothProfile) {
        BluetoothDevice device = this.mCachedDevice.getDevice();
        if (localBluetoothProfile instanceof PbapServerProfile) {
            device.setPhonebookAccessPermission(1);
            return;
        }
        if (localBluetoothProfile instanceof MapProfile) {
            device.setMessageAccessPermission(1);
        }
        localBluetoothProfile.setPreferred(device, true);
        this.mCachedDevice.connectProfile(localBluetoothProfile);
    }

    private void disableProfile(LocalBluetoothProfile localBluetoothProfile) {
        BluetoothDevice device = this.mCachedDevice.getDevice();
        this.mCachedDevice.disconnect(localBluetoothProfile);
        localBluetoothProfile.setPreferred(device, false);
        if (localBluetoothProfile instanceof MapProfile) {
            device.setMessageAccessPermission(2);
        } else if (localBluetoothProfile instanceof PbapServerProfile) {
            device.setPhonebookAccessPermission(2);
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        LocalBluetoothProfile profileByName = this.mProfileManager.getProfileByName(preference.getKey());
        PbapServerProfile pbapServerProfile = profileByName;
        if (profileByName == null) {
            PbapServerProfile pbapProfile = this.mManager.getProfileManager().getPbapProfile();
            boolean equals = TextUtils.equals(preference.getKey(), pbapProfile.toString());
            pbapServerProfile = pbapProfile;
            if (!equals) {
                return false;
            }
        }
        SwitchPreference switchPreference = (SwitchPreference) preference;
        if (switchPreference.isChecked()) {
            enableProfile(pbapServerProfile);
        } else {
            disableProfile(pbapServerProfile);
        }
        refreshProfilePreference(switchPreference, pbapServerProfile);
        return true;
    }

    private List<LocalBluetoothProfile> getProfiles() {
        List<LocalBluetoothProfile> connectableProfiles = this.mCachedDevice.getConnectableProfiles();
        BluetoothDevice device = this.mCachedDevice.getDevice();
        if (device.getPhonebookAccessPermission() != 0) {
            connectableProfiles.add(this.mManager.getProfileManager().getPbapProfile());
        }
        MapProfile mapProfile = this.mManager.getProfileManager().getMapProfile();
        if (device.getMessageAccessPermission() != 0) {
            connectableProfiles.add(mapProfile);
        }
        return connectableProfiles;
    }

    private void maybeAddHighQualityAudioPref(LocalBluetoothProfile localBluetoothProfile) {
        if (localBluetoothProfile instanceof A2dpProfile) {
            BluetoothDevice device = this.mCachedDevice.getDevice();
            A2dpProfile a2dpProfile = (A2dpProfile) localBluetoothProfile;
            if (a2dpProfile.isProfileReady() && a2dpProfile.supportsHighQualityAudio(device)) {
                SwitchPreference switchPreference = new SwitchPreference(this.mProfilesContainer.getContext());
                switchPreference.setKey(HIGH_QUALITY_AUDIO_PREF_TAG);
                switchPreference.setVisible(false);
                switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(a2dpProfile) {
                    private final /* synthetic */ A2dpProfile f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final boolean onPreferenceClick(Preference preference) {
                        return BluetoothDetailsProfilesController.this.mo8720x50bbd160(this.f$1, preference);
                    }
                });
                this.mProfilesContainer.addPreference(switchPreference);
            }
        }
    }

    /* renamed from: lambda$maybeAddHighQualityAudioPref$0$BluetoothDetailsProfilesController */
    public /* synthetic */ boolean mo8720x50bbd160(A2dpProfile a2dpProfile, Preference preference) {
        a2dpProfile.setHighQualityAudioEnabled(this.mCachedDevice.getDevice(), ((SwitchPreference) preference).isChecked());
        return true;
    }

    public void onPause() {
        super.onPause();
        this.mProfileManager.removeServiceListener(this);
    }

    public void onResume() {
        super.onResume();
        this.mProfileManager.addServiceListener(this);
    }

    public void onServiceConnected() {
        refresh();
    }

    public void onServiceDisconnected() {
        refresh();
    }

    /* access modifiers changed from: protected */
    public void refresh() {
        for (LocalBluetoothProfile next : getProfiles()) {
            if (next.isProfileReady()) {
                SwitchPreference switchPreference = (SwitchPreference) this.mProfilesContainer.findPreference(next.toString());
                if (switchPreference == null) {
                    switchPreference = createProfilePreference(this.mProfilesContainer.getContext(), next);
                    this.mProfilesContainer.addPreference(switchPreference);
                    maybeAddHighQualityAudioPref(next);
                }
                refreshProfilePreference(switchPreference, next);
            }
        }
        for (LocalBluetoothProfile obj : this.mCachedDevice.getRemovedProfiles()) {
            SwitchPreference switchPreference2 = (SwitchPreference) this.mProfilesContainer.findPreference(obj.toString());
            if (switchPreference2 != null) {
                this.mProfilesContainer.removePreference(switchPreference2);
            }
        }
    }
}
