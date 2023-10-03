package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.ArraySet;
import androidx.preference.Preference;
import com.android.settings.Utils;
import com.android.settings.applications.AppDomainsPreference;
import com.android.settingslib.applications.AppUtils;

public class InstantAppDomainsPreferenceController extends AppInfoPreferenceControllerBase {
    private PackageManager mPackageManager = this.mContext.getPackageManager();

    public InstantAppDomainsPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return AppUtils.isInstant(this.mParent.getPackageInfo().applicationInfo) ? 0 : 4;
    }

    public void updateState(Preference preference) {
        AppDomainsPreference appDomainsPreference = (AppDomainsPreference) preference;
        ArraySet<String> handledDomains = Utils.getHandledDomains(this.mPackageManager, this.mParent.getPackageInfo().packageName);
        String[] strArr = (String[]) handledDomains.toArray(new String[handledDomains.size()]);
        appDomainsPreference.setTitles(strArr);
        appDomainsPreference.setValues(new int[strArr.length]);
    }
}
