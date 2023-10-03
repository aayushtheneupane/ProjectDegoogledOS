package com.android.settings.backup;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;

public class BackupInactivePreferenceController extends BasePreferenceController {
    public BackupInactivePreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!new BackupSettingsHelper(this.mContext).isBackupServiceActive()) {
            return 1;
        }
        return PrivacySettingsUtils.isInvisibleKey(this.mContext, "backup_inactive") ? 3 : 0;
    }
}
