package com.android.settings.users;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.widget.FooterPreference;
import com.android.settingslib.widget.FooterPreferenceMixinCompat;
import com.havoc.config.center.C1715R;

public class MultiUserFooterPreferenceController extends BasePreferenceController {
    private FooterPreferenceMixinCompat mFooterMixin;
    final UserCapabilities mUserCaps;

    public MultiUserFooterPreferenceController(Context context) {
        super(context, "dummy_key");
        this.mUserCaps = UserCapabilities.create(context);
    }

    public MultiUserFooterPreferenceController setFooterMixin(FooterPreferenceMixinCompat footerPreferenceMixinCompat) {
        this.mFooterMixin = footerPreferenceMixinCompat;
        return this;
    }

    public int getAvailabilityStatus() {
        UserCapabilities userCapabilities = this.mUserCaps;
        return (!userCapabilities.mEnabled || userCapabilities.mUserSwitcherEnabled) ? 4 : 1;
    }

    public void updateState(Preference preference) {
        this.mUserCaps.updateAddUserCapabilities(this.mContext);
        FooterPreference createFooterPreference = this.mFooterMixin.createFooterPreference();
        createFooterPreference.setTitle((int) C1715R.string.user_settings_footer_text);
        createFooterPreference.setVisible(isAvailable());
    }
}
