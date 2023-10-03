package com.android.settings.deviceinfo.legal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import java.util.List;

public abstract class LegalPreferenceController extends BasePreferenceController {
    private final PackageManager mPackageManager = this.mContext.getPackageManager();
    private Preference mPreference;

    /* access modifiers changed from: protected */
    public abstract Intent getIntent();

    public LegalPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return findMatchingSpecificActivity() != null ? 0 : 3;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        super.displayPreference(preferenceScreen);
        if (getAvailabilityStatus() == 0) {
            replacePreferenceIntent();
        }
    }

    private ResolveInfo findMatchingSpecificActivity() {
        List<ResolveInfo> queryIntentActivities;
        Intent intent = getIntent();
        if (intent == null || (queryIntentActivities = this.mPackageManager.queryIntentActivities(intent, 0)) == null) {
            return null;
        }
        for (ResolveInfo next : queryIntentActivities) {
            if ((next.activityInfo.applicationInfo.flags & 1) != 0) {
                return next;
            }
        }
        return null;
    }

    private void replacePreferenceIntent() {
        ResolveInfo findMatchingSpecificActivity = findMatchingSpecificActivity();
        if (findMatchingSpecificActivity != null) {
            this.mPreference.setIntent(new Intent().setClassName(findMatchingSpecificActivity.activityInfo.packageName, findMatchingSpecificActivity.activityInfo.name));
            this.mPreference.setTitle(findMatchingSpecificActivity.loadLabel(this.mPackageManager));
        }
    }
}
