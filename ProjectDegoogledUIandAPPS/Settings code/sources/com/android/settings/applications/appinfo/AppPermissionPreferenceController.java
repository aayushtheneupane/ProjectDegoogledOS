package com.android.settings.applications.appinfo;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.icu.text.ListFormatter;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settingslib.applications.PermissionsSummaryHelper;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class AppPermissionPreferenceController extends AppInfoPreferenceControllerBase {
    private static final String EXTRA_HIDE_INFO_BUTTON = "hideInfoButton";
    private static final String TAG = "PermissionPrefControl";
    private String mPackageName;
    final PermissionsSummaryHelper.PermissionsResultCallback mPermissionCallback = new PermissionsSummaryHelper.PermissionsResultCallback() {
        public void onPermissionSummaryResult(int i, int i2, int i3, List<CharSequence> list) {
            String str;
            Resources resources = AppPermissionPreferenceController.this.mContext.getResources();
            if (i2 == 0) {
                str = resources.getString(C1715R.string.runtime_permissions_summary_no_permissions_requested);
                AppPermissionPreferenceController.this.mPreference.setEnabled(false);
            } else {
                ArrayList arrayList = new ArrayList(list);
                if (i3 > 0) {
                    arrayList.add(resources.getQuantityString(C1715R.plurals.runtime_permissions_additional_count, i3, new Object[]{Integer.valueOf(i3)}));
                }
                if (arrayList.size() == 0) {
                    str = resources.getString(C1715R.string.runtime_permissions_summary_no_permissions_granted);
                } else {
                    str = ListFormatter.getInstance().format(arrayList);
                }
                AppPermissionPreferenceController.this.mPreference.setEnabled(true);
            }
            AppPermissionPreferenceController.this.mPreference.setSummary((CharSequence) str);
        }
    };

    public AppPermissionPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateState(Preference preference) {
        PermissionsSummaryHelper.getPermissionSummary(this.mContext, this.mPackageName, this.mPermissionCallback);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return false;
        }
        startManagePermissionsActivity();
        return true;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    private void startManagePermissionsActivity() {
        Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSIONS");
        intent.putExtra("android.intent.extra.PACKAGE_NAME", this.mParent.getAppEntry().info.packageName);
        intent.putExtra(EXTRA_HIDE_INFO_BUTTON, true);
        try {
            this.mParent.getActivity().startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException unused) {
            Log.w(TAG, "No app can handle android.intent.action.MANAGE_APP_PERMISSIONS");
        }
    }
}
