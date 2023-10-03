package com.android.settings.backup;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class BackupDataPreferenceController extends BasePreferenceController {
    private PrivacySettingsConfigData mPSCD = PrivacySettingsConfigData.getInstance();

    public BackupDataPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!PrivacySettingsUtils.isAdminUser(this.mContext)) {
            return 4;
        }
        return PrivacySettingsUtils.isInvisibleKey(this.mContext, "backup_data") ? 3 : 0;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mPSCD.isBackupGray()) {
            preference.setEnabled(false);
        }
    }

    public CharSequence getSummary() {
        if (this.mPSCD.isBackupGray()) {
            return null;
        }
        if (this.mPSCD.isBackupEnabled()) {
            return this.mContext.getText(C1715R.string.accessibility_feature_state_on);
        }
        return this.mContext.getText(C1715R.string.accessibility_feature_state_off);
    }
}
