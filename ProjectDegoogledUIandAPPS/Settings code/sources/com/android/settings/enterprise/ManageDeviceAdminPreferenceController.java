package com.android.settings.enterprise;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.havoc.config.center.C1715R;

public class ManageDeviceAdminPreferenceController extends BasePreferenceController {
    private final EnterprisePrivacyFeatureProvider mFeatureProvider;

    public ManageDeviceAdminPreferenceController(Context context, String str) {
        super(context, str);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getEnterprisePrivacyFeatureProvider(context);
    }

    public CharSequence getSummary() {
        int numberOfActiveDeviceAdminsForCurrentUserAndManagedProfile = this.mFeatureProvider.getNumberOfActiveDeviceAdminsForCurrentUserAndManagedProfile();
        if (numberOfActiveDeviceAdminsForCurrentUserAndManagedProfile == 0) {
            return this.mContext.getResources().getString(C1715R.string.number_of_device_admins_none);
        }
        return this.mContext.getResources().getQuantityString(C1715R.plurals.number_of_device_admins, numberOfActiveDeviceAdminsForCurrentUserAndManagedProfile, new Object[]{Integer.valueOf(numberOfActiveDeviceAdminsForCurrentUserAndManagedProfile)});
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_manage_device_admin) ? 1 : 3;
    }
}
