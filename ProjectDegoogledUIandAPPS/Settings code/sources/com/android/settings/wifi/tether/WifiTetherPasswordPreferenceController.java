package com.android.settings.wifi.tether;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.text.TextUtils;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import com.android.settings.widget.ValidatedEditTextPreference;
import com.android.settings.wifi.WifiUtils;
import com.android.settings.wifi.tether.WifiTetherBasePreferenceController;
import com.havoc.config.center.C1715R;
import java.util.UUID;

public class WifiTetherPasswordPreferenceController extends WifiTetherBasePreferenceController implements ValidatedEditTextPreference.Validator {
    private String mPassword;

    public String getPreferenceKey() {
        return "wifi_tether_network_password";
    }

    public WifiTetherPasswordPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
    }

    public void updateDisplay() {
        WifiConfiguration wifiApConfiguration = this.mWifiManager.getWifiApConfiguration();
        if (wifiApConfiguration == null || (wifiApConfiguration.getAuthType() == 4 && TextUtils.isEmpty(wifiApConfiguration.preSharedKey))) {
            this.mPassword = generateRandomPassword();
        } else {
            this.mPassword = wifiApConfiguration.preSharedKey;
        }
        ((ValidatedEditTextPreference) this.mPreference).setValidator(this);
        ((ValidatedEditTextPreference) this.mPreference).setIsPassword(true);
        ((ValidatedEditTextPreference) this.mPreference).setIsSummaryPassword(true);
        updatePasswordDisplay((EditTextPreference) this.mPreference);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mPassword = (String) obj;
        updatePasswordDisplay((EditTextPreference) this.mPreference);
        this.mListener.onTetherConfigUpdated();
        return true;
    }

    public String getPasswordValidated(int i) {
        if (i == 0) {
            return "";
        }
        if (!isTextValid(this.mPassword)) {
            this.mPassword = generateRandomPassword();
            updatePasswordDisplay((EditTextPreference) this.mPreference);
        }
        return this.mPassword;
    }

    public void updateVisibility(int i) {
        this.mPreference.setVisible(i != 0);
    }

    public boolean isTextValid(String str) {
        return WifiUtils.isHotspotPasswordValid(str);
    }

    private static String generateRandomPassword() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13);
    }

    private void updatePasswordDisplay(EditTextPreference editTextPreference) {
        ValidatedEditTextPreference validatedEditTextPreference = (ValidatedEditTextPreference) editTextPreference;
        validatedEditTextPreference.setText(this.mPassword);
        if (!TextUtils.isEmpty(this.mPassword)) {
            validatedEditTextPreference.setIsSummaryPassword(true);
            validatedEditTextPreference.setSummary((CharSequence) this.mPassword);
            validatedEditTextPreference.setVisible(true);
            return;
        }
        validatedEditTextPreference.setIsSummaryPassword(false);
        validatedEditTextPreference.setSummary((int) C1715R.string.wifi_hotspot_no_password_subtext);
        validatedEditTextPreference.setVisible(false);
    }
}
