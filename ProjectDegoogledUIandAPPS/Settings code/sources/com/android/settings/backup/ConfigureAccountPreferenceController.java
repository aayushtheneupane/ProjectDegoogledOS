package com.android.settings.backup;

import android.content.Context;
import android.content.Intent;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class ConfigureAccountPreferenceController extends BasePreferenceController {
    private PrivacySettingsConfigData mPSCD = PrivacySettingsConfigData.getInstance();

    public ConfigureAccountPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!PrivacySettingsUtils.isAdminUser(this.mContext)) {
            return 4;
        }
        return PrivacySettingsUtils.isInvisibleKey(this.mContext, "configure_account") ? 3 : 0;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        Intent configIntent = this.mPSCD.getConfigIntent();
        preference.setEnabled(configIntent != null && this.mPSCD.isBackupEnabled());
        preference.setIntent(configIntent);
    }

    public CharSequence getSummary() {
        String configSummary = this.mPSCD.getConfigSummary();
        return configSummary != null ? configSummary : this.mContext.getText(C1715R.string.backup_configure_account_default_summary);
    }
}
