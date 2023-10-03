package com.android.settings.applications;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.text.ListFormatter;
import android.util.ArraySet;
import androidx.preference.Preference;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.applications.PermissionsSummaryHelper;
import com.havoc.config.center.C1715R;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppPermissionsPreferenceController extends BasePreferenceController {
    private static int NUM_PACKAGE_TO_CHECK = 3;
    static int NUM_PERMISSIONS_TO_SHOW = 3;
    private static final String TAG = "AppPermissionPrefCtrl";
    int mNumPackageChecked;
    private final PackageManager mPackageManager;
    private final Set<CharSequence> mPermissionGroups;
    private final PermissionsSummaryHelper.PermissionsResultCallback mPermissionsCallback = new PermissionsSummaryHelper.PermissionsResultCallback() {
        public void onPermissionSummaryResult(int i, int i2, int i3, List<CharSequence> list) {
            AppPermissionsPreferenceController.this.updateSummary(list);
        }
    };
    private Preference mPreference;

    public int getAvailabilityStatus() {
        return 0;
    }

    public AppPermissionsPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
        this.mPermissionGroups = new ArraySet();
    }

    public void updateState(Preference preference) {
        this.mPreference = preference;
        this.mNumPackageChecked = 0;
        queryPermissionSummary();
    }

    /* access modifiers changed from: package-private */
    public void queryPermissionSummary() {
        for (PackageInfo packageInfo : (List) this.mPackageManager.getInstalledPackages(4096).stream().filter(C0508xf4f7226.INSTANCE).limit((long) NUM_PACKAGE_TO_CHECK).collect(Collectors.toList())) {
            PermissionsSummaryHelper.getPermissionSummary(this.mContext, packageInfo.packageName, this.mPermissionsCallback);
        }
    }

    static /* synthetic */ boolean lambda$queryPermissionSummary$0(PackageInfo packageInfo) {
        return packageInfo.permissions != null;
    }

    /* access modifiers changed from: package-private */
    public void updateSummary(List<CharSequence> list) {
        String str;
        this.mPermissionGroups.addAll(list);
        this.mNumPackageChecked++;
        if (this.mNumPackageChecked >= NUM_PACKAGE_TO_CHECK) {
            List list2 = (List) this.mPermissionGroups.stream().limit((long) NUM_PERMISSIONS_TO_SHOW).collect(Collectors.toList());
            if (list2.isEmpty()) {
                str = null;
            } else {
                str = this.mContext.getString(C1715R.string.app_permissions_summary, new Object[]{Utils.normalizeTitleCaseIfRequired(this.mContext, ListFormatter.getInstance().format(list2))});
            }
            this.mPreference.setSummary((CharSequence) str);
        }
    }
}
