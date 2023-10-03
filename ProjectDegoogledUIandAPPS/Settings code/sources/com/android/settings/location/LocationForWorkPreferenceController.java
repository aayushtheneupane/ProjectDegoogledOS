package com.android.settings.location;

import android.content.Context;
import android.os.UserManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class LocationForWorkPreferenceController extends LocationBasePreferenceController {
    private RestrictedSwitchPreference mPreference;

    public String getPreferenceKey() {
        return "managed_profile_location_switch";
    }

    public LocationForWorkPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        boolean z = false;
        if (!"managed_profile_location_switch".equals(preference.getKey())) {
            return false;
        }
        boolean isChecked = this.mPreference.isChecked();
        UserManager userManager = this.mUserManager;
        if (!isChecked) {
            z = true;
        }
        userManager.setUserRestriction("no_share_location", z, Utils.getManagedProfile(this.mUserManager));
        this.mPreference.setSummary(isChecked ? C1715R.string.switch_on_text : C1715R.string.switch_off_text);
        return true;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (RestrictedSwitchPreference) preferenceScreen.findPreference("managed_profile_location_switch");
    }

    public boolean isAvailable() {
        return Utils.getManagedProfile(this.mUserManager) != null;
    }

    public void onLocationModeChanged(int i, boolean z) {
        if (this.mPreference.isVisible() && isAvailable()) {
            RestrictedLockUtils.EnforcedAdmin shareLocationEnforcedAdmin = this.mLocationEnabler.getShareLocationEnforcedAdmin(Utils.getManagedProfile(this.mUserManager).getIdentifier());
            boolean isManagedProfileRestrictedByBase = this.mLocationEnabler.isManagedProfileRestrictedByBase();
            if (isManagedProfileRestrictedByBase || shareLocationEnforcedAdmin == null) {
                boolean isEnabled = this.mLocationEnabler.isEnabled(i);
                this.mPreference.setEnabled(isEnabled);
                int i2 = C1715R.string.switch_off_text;
                if (!isEnabled) {
                    this.mPreference.setChecked(false);
                } else {
                    this.mPreference.setChecked(!isManagedProfileRestrictedByBase);
                    if (!isManagedProfileRestrictedByBase) {
                        i2 = C1715R.string.switch_on_text;
                    }
                }
                this.mPreference.setSummary(i2);
                return;
            }
            this.mPreference.setDisabledByAdmin(shareLocationEnforcedAdmin);
            this.mPreference.setChecked(false);
        }
    }
}
