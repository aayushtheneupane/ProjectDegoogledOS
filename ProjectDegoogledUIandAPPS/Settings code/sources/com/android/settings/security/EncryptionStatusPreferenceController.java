package com.android.settings.security;

import android.content.Context;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class EncryptionStatusPreferenceController extends BasePreferenceController {
    static final String PREF_KEY_ENCRYPTION_DETAIL_PAGE = "encryption_and_credentials_encryption_status";
    static final String PREF_KEY_ENCRYPTION_SECURITY_PAGE = "encryption_and_credential";
    private final UserManager mUserManager;

    public EncryptionStatusPreferenceController(Context context, String str) {
        super(context, str);
        this.mUserManager = (UserManager) context.getSystemService("user");
    }

    public int getAvailabilityStatus() {
        if (!TextUtils.equals(getPreferenceKey(), PREF_KEY_ENCRYPTION_DETAIL_PAGE) || this.mContext.getResources().getBoolean(C1715R.bool.config_show_encryption_and_credentials_encryption_status)) {
            return this.mUserManager.isAdminUser() ? 0 : 4;
        }
        return 3;
    }

    public void updateState(Preference preference) {
        if (LockPatternUtils.isDeviceEncryptionEnabled()) {
            if (TextUtils.equals(getPreferenceKey(), PREF_KEY_ENCRYPTION_DETAIL_PAGE)) {
                preference.setFragment((String) null);
            }
            preference.setSummary((int) C1715R.string.crypt_keeper_encrypted_summary);
            return;
        }
        if (TextUtils.equals(getPreferenceKey(), PREF_KEY_ENCRYPTION_DETAIL_PAGE)) {
            preference.setFragment(CryptKeeperSettings.class.getName());
        }
        preference.setSummary((int) C1715R.string.decryption_settings_summary);
    }
}
