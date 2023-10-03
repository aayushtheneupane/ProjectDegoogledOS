package com.android.settings.deviceinfo.legal;

import com.android.settings.dashboard.DashboardFragment;
import com.havoc.config.center.C1715R;

public class ModuleLicensesDashboard extends DashboardFragment {
    public int getHelpResource() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "ModuleLicensesDashboard";
    }

    public int getMetricsCategory() {
        return 1746;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.module_licenses;
    }
}
