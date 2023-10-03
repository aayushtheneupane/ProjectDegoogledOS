package com.android.settings.network.telephony.cdma;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.internal.telephony.Phone;

public class CdmaSystemSelectPreferenceController extends CdmaBasePreferenceController implements Preference.OnPreferenceChangeListener {
    public CdmaSystemSelectPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        ListPreference listPreference = (ListPreference) preference;
        boolean z = true;
        listPreference.setVisible(getAvailabilityStatus() == 0);
        int cdmaRoamingMode = this.mTelephonyManager.getCdmaRoamingMode();
        if (cdmaRoamingMode != -1) {
            if (cdmaRoamingMode == 0 || cdmaRoamingMode == 2) {
                listPreference.setValue(Integer.toString(cdmaRoamingMode));
            } else {
                resetCdmaRoamingModeToDefault();
            }
        }
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "preferred_network_mode" + this.mSubId, Phone.PREFERRED_NT_MODE) == 9) {
            z = false;
        }
        listPreference.setEnabled(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        int parseInt = Integer.parseInt((String) obj);
        if (!this.mTelephonyManager.setCdmaRoamingMode(parseInt)) {
            return false;
        }
        Settings.Global.putInt(this.mContext.getContentResolver(), "roaming_settings", parseInt);
        return true;
    }

    private void resetCdmaRoamingModeToDefault() {
        ((ListPreference) this.mPreference).setValue(Integer.toString(2));
        Settings.Global.putInt(this.mContext.getContentResolver(), "roaming_settings", 2);
        this.mTelephonyManager.setCdmaRoamingMode(2);
    }
}
