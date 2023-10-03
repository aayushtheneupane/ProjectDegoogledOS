package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.overlay.FeatureFactory;

public class AppSettingPreferenceController extends AppInfoPreferenceControllerBase {
    private String mPackageName;

    public AppSettingPreferenceController(Context context, String str) {
        super(context, str);
    }

    public AppSettingPreferenceController setPackageName(String str) {
        this.mPackageName = str;
        return this;
    }

    public int getAvailabilityStatus() {
        if (TextUtils.isEmpty(this.mPackageName) || this.mParent == null || resolveIntent(new Intent("android.intent.action.APPLICATION_PREFERENCES").setPackage(this.mPackageName)) == null) {
            return 2;
        }
        return 0;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        Intent resolveIntent;
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey()) || (resolveIntent = resolveIntent(new Intent("android.intent.action.APPLICATION_PREFERENCES").setPackage(this.mPackageName))) == null) {
            return false;
        }
        FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(0, 1017, this.mParent.getMetricsCategory(), (String) null, 0);
        this.mContext.startActivity(resolveIntent);
        return true;
    }

    private Intent resolveIntent(Intent intent) {
        ResolveInfo resolveActivity = this.mContext.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity != null) {
            return new Intent(intent.getAction()).setClassName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
        }
        return null;
    }
}
