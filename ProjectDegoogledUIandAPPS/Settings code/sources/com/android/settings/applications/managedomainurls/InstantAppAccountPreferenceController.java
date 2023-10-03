package com.android.settings.applications.managedomainurls;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;

public class InstantAppAccountPreferenceController extends BasePreferenceController {
    private Intent mLaunchIntent;

    public InstantAppAccountPreferenceController(Context context, String str) {
        super(context, str);
        initAppSettingsIntent();
    }

    public int getAvailabilityStatus() {
        return (this.mLaunchIntent == null || WebActionCategoryController.isDisableWebActions(this.mContext)) ? 3 : 0;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return false;
        }
        Intent intent = this.mLaunchIntent;
        if (intent == null) {
            return true;
        }
        this.mContext.startActivity(intent);
        return true;
    }

    private void initAppSettingsIntent() {
        ComponentName instantAppResolverSettingsComponent = this.mContext.getPackageManager().getInstantAppResolverSettingsComponent();
        Intent component = instantAppResolverSettingsComponent != null ? new Intent().setComponent(instantAppResolverSettingsComponent) : null;
        if (component != null) {
            this.mLaunchIntent = component;
        }
    }
}
