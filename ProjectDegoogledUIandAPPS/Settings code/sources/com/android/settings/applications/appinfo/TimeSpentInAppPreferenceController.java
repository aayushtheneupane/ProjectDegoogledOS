package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.ApplicationFeatureProvider;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import java.util.List;

public class TimeSpentInAppPreferenceController extends BasePreferenceController {
    static final Intent SEE_TIME_IN_APP_TEMPLATE = new Intent("android.settings.action.APP_USAGE_SETTINGS");
    private final ApplicationFeatureProvider mAppFeatureProvider;
    private Intent mIntent;
    private final PackageManager mPackageManager;
    private String mPackageName;

    public TimeSpentInAppPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
        this.mAppFeatureProvider = FeatureFactory.getFactory(context).getApplicationFeatureProvider(context);
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
        this.mIntent = new Intent(SEE_TIME_IN_APP_TEMPLATE).putExtra("android.intent.extra.PACKAGE_NAME", this.mPackageName);
    }

    public int getAvailabilityStatus() {
        List<ResolveInfo> queryIntentActivities;
        if (!TextUtils.isEmpty(this.mPackageName) && (queryIntentActivities = this.mPackageManager.queryIntentActivities(this.mIntent, 0)) != null && !queryIntentActivities.isEmpty()) {
            for (ResolveInfo isSystemApp : queryIntentActivities) {
                if (isSystemApp(isSystemApp)) {
                    return 0;
                }
            }
        }
        return 3;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (findPreference != null) {
            findPreference.setIntent(this.mIntent);
        }
    }

    public CharSequence getSummary() {
        return this.mAppFeatureProvider.getTimeSpentInApp(this.mPackageName);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = r2.activityInfo;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isSystemApp(android.content.pm.ResolveInfo r2) {
        /*
            r1 = this;
            r1 = 1
            if (r2 == 0) goto L_0x0015
            android.content.pm.ActivityInfo r0 = r2.activityInfo
            if (r0 == 0) goto L_0x0015
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo
            if (r0 == 0) goto L_0x0015
            android.content.pm.ActivityInfo r2 = r2.activityInfo
            android.content.pm.ApplicationInfo r2 = r2.applicationInfo
            int r2 = r2.flags
            r2 = r2 & r1
            if (r2 == 0) goto L_0x0015
            goto L_0x0016
        L_0x0015:
            r1 = 0
        L_0x0016:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.applications.appinfo.TimeSpentInAppPreferenceController.isSystemApp(android.content.pm.ResolveInfo):boolean");
    }
}
