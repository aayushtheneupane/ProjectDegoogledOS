package com.android.settings.deviceinfo.legal;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.deviceinfo.legal.ModuleLicensesPreferenceController;

public class ModuleLicensesListPreferenceController extends BasePreferenceController {
    public ModuleLicensesListPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (this.mContext.getPackageManager().getInstalledModules(0).stream().anyMatch(new ModuleLicensesPreferenceController.Predicate(this.mContext))) {
            return 0;
        }
        return 2;
    }
}
