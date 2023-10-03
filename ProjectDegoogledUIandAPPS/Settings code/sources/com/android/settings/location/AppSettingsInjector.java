package com.android.settings.location;

import android.content.Context;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.widget.RestrictedAppPreference;
import com.android.settingslib.location.InjectedSetting;
import com.android.settingslib.location.SettingsInjector;
import com.android.settingslib.widget.apppreference.AppPreference;

public class AppSettingsInjector extends SettingsInjector {
    public AppSettingsInjector(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public Preference createPreference(Context context, InjectedSetting injectedSetting) {
        if (TextUtils.isEmpty(injectedSetting.userRestriction)) {
            return new AppPreference(context);
        }
        return new RestrictedAppPreference(context, injectedSetting.userRestriction);
    }
}
