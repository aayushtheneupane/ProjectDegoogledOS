package com.android.settings.core;

import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.Utils;

public abstract class WorkProfilePreferenceController extends BasePreferenceController {
    private final UserHandle mWorkProfileUser;

    /* access modifiers changed from: protected */
    public abstract int getSourceMetricsCategory();

    public WorkProfilePreferenceController(Context context, String str) {
        super(context, str);
        this.mWorkProfileUser = Utils.getManagedProfile(UserManager.get(context));
    }

    /* access modifiers changed from: protected */
    public UserHandle getWorkProfileUser() {
        return this.mWorkProfileUser;
    }

    public int getAvailabilityStatus() {
        return this.mWorkProfileUser != null ? 0 : 4;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return super.handlePreferenceTreeClick(preference);
        }
        new SubSettingLauncher(preference.getContext()).setDestination(preference.getFragment()).setSourceMetricsCategory(getSourceMetricsCategory()).setArguments(preference.getExtras()).setUserHandle(this.mWorkProfileUser).launch();
        return true;
    }
}
