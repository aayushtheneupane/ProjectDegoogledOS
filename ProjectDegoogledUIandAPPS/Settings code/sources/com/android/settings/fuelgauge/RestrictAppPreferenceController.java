package com.android.settings.fuelgauge;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.InstrumentedPreferenceFragment;
import com.android.settings.fuelgauge.batterytip.AppInfo;
import com.android.settings.fuelgauge.batterytip.BatteryTipUtils;
import com.havoc.config.center.C1715R;
import java.util.List;

public class RestrictAppPreferenceController extends BasePreferenceController {
    static final String KEY_RESTRICT_APP = "restricted_app";
    List<AppInfo> mAppInfos;
    private AppOpsManager mAppOpsManager;
    private InstrumentedPreferenceFragment mPreferenceFragment;
    private UserManager mUserManager;

    public int getAvailabilityStatus() {
        return 0;
    }

    public RestrictAppPreferenceController(Context context) {
        super(context, KEY_RESTRICT_APP);
        this.mAppOpsManager = (AppOpsManager) context.getSystemService("appops");
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
    }

    public RestrictAppPreferenceController(InstrumentedPreferenceFragment instrumentedPreferenceFragment) {
        this(instrumentedPreferenceFragment.getContext());
        this.mPreferenceFragment = instrumentedPreferenceFragment;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mAppInfos = BatteryTipUtils.getRestrictedAppsList(this.mAppOpsManager, this.mUserManager);
        int size = this.mAppInfos.size();
        preference.setVisible(size > 0);
        preference.setSummary((CharSequence) this.mContext.getResources().getQuantityString(C1715R.plurals.restricted_app_summary, size, new Object[]{Integer.valueOf(size)}));
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return super.handlePreferenceTreeClick(preference);
        }
        RestrictedAppDetails.startRestrictedAppDetails(this.mPreferenceFragment, this.mAppInfos);
        return true;
    }
}
