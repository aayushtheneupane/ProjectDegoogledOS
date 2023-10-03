package com.android.settings.enterprise;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;

public class EnterprisePrivacyPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final EnterprisePrivacyFeatureProvider mFeatureProvider;

    public String getPreferenceKey() {
        return "enterprise_privacy";
    }

    public EnterprisePrivacyPreferenceController(Context context) {
        super(context);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getEnterprisePrivacyFeatureProvider(context);
    }

    public void updateState(Preference preference) {
        if (preference != null) {
            String deviceOwnerOrganizationName = this.mFeatureProvider.getDeviceOwnerOrganizationName();
            if (deviceOwnerOrganizationName == null) {
                preference.setSummary((int) C1715R.string.enterprise_privacy_settings_summary_generic);
                return;
            }
            preference.setSummary((CharSequence) this.mContext.getResources().getString(C1715R.string.enterprise_privacy_settings_summary_with_name, new Object[]{deviceOwnerOrganizationName}));
        }
    }

    public boolean isAvailable() {
        return this.mFeatureProvider.hasDeviceOwner();
    }
}
