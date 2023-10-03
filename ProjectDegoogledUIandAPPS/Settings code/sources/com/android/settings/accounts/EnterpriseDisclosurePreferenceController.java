package com.android.settings.accounts;

import android.content.Context;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.enterprise.EnterprisePrivacyFeatureProvider;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.widget.FooterPreference;
import com.android.settingslib.widget.FooterPreferenceMixinCompat;

public class EnterpriseDisclosurePreferenceController extends BasePreferenceController {
    private final EnterprisePrivacyFeatureProvider mFeatureProvider = FeatureFactory.getFactory(this.mContext).getEnterprisePrivacyFeatureProvider(this.mContext);
    private FooterPreferenceMixinCompat mFooterPreferenceMixin;
    private PreferenceScreen mScreen;

    public EnterpriseDisclosurePreferenceController(Context context) {
        super(context, "add_account_enterprise_disclosure_footer");
    }

    public void setFooterPreferenceMixin(FooterPreferenceMixinCompat footerPreferenceMixinCompat) {
        this.mFooterPreferenceMixin = footerPreferenceMixinCompat;
    }

    public int getAvailabilityStatus() {
        return getDisclosure() == null ? 3 : 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mScreen = preferenceScreen;
        addEnterpriseDisclosure();
    }

    /* access modifiers changed from: package-private */
    public CharSequence getDisclosure() {
        return this.mFeatureProvider.getDeviceOwnerDisclosure();
    }

    private void addEnterpriseDisclosure() {
        CharSequence disclosure = getDisclosure();
        if (disclosure != null) {
            FooterPreference createFooterPreference = this.mFooterPreferenceMixin.createFooterPreference();
            createFooterPreference.setSelectable(false);
            createFooterPreference.setTitle(disclosure);
            this.mScreen.addPreference(createFooterPreference);
        }
    }
}
