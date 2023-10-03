package com.android.settings.backup;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;

public class DataManagementPreferenceController extends BasePreferenceController {
    private PrivacySettingsConfigData mPSCD = PrivacySettingsConfigData.getInstance();

    public DataManagementPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!PrivacySettingsUtils.isAdminUser(this.mContext)) {
            return 4;
        }
        return !(this.mPSCD.getManageIntent() != null && this.mPSCD.isBackupEnabled()) ? 3 : 0;
    }

    public void updateState(Preference preference) {
        if (isAvailable()) {
            preference.setIntent(this.mPSCD.getManageIntent());
            CharSequence manageLabel = this.mPSCD.getManageLabel();
            if (manageLabel != null) {
                preference.setTitle(manageLabel);
            }
        }
    }
}
