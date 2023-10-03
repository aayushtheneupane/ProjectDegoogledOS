package com.android.settings.accounts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.search.SearchIndexableRaw;
import com.havoc.config.center.C1715R;
import java.util.List;

public class EmergencyInfoPreferenceController extends BasePreferenceController {
    public static String getIntentAction(Context context) {
        return context.getResources().getString(C1715R.string.config_emergency_intent_action);
    }

    public EmergencyInfoPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateRawDataToIndex(List<SearchIndexableRaw> list) {
        if (isAvailable()) {
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(this.mContext);
            Resources resources = this.mContext.getResources();
            searchIndexableRaw.title = resources.getString(C1715R.string.emergency_info_title);
            searchIndexableRaw.screenTitle = resources.getString(C1715R.string.emergency_info_title);
            list.add(searchIndexableRaw);
        }
    }

    public void updateState(Preference preference) {
        UserInfo userInfo = ((UserManager) this.mContext.getSystemService(UserManager.class)).getUserInfo(UserHandle.myUserId());
        preference.setSummary((CharSequence) this.mContext.getString(C1715R.string.emergency_info_summary, new Object[]{userInfo.name}));
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(getPreferenceKey(), preference.getKey())) {
            return false;
        }
        Intent intent = new Intent(getIntentAction(this.mContext));
        intent.setFlags(67108864);
        this.mContext.startActivity(intent);
        return true;
    }

    public int getAvailabilityStatus() {
        List<ResolveInfo> queryIntentActivities;
        if (this.mContext.getResources().getBoolean(C1715R.bool.config_show_emergency_info_in_device_info) && (queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(new Intent(getIntentAction(this.mContext)).setPackage(getPackageName(this.mContext)), 0)) != null && !queryIntentActivities.isEmpty()) {
            return 0;
        }
        return 3;
    }

    private static String getPackageName(Context context) {
        return context.getResources().getString(C1715R.string.config_emergency_package_name);
    }
}
