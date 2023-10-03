package com.android.settings.development;

import android.adb.ADBRootService;
import android.content.Context;
import android.os.Build;
import android.os.UserManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.havoc.support.preferences.SwitchPreference;

public class AdbRootPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    private final ADBRootService mADBRootService = new ADBRootService();

    public String getPreferenceKey() {
        return "enable_adb_root";
    }

    public AdbRootPreferenceController(Context context, DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment) {
        super(context);
    }

    public boolean isAvailable() {
        return Build.IS_DEBUGGABLE;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ((SwitchPreference) this.mPreference).setChecked(this.mADBRootService.getEnabled());
        if (!isAdminUser()) {
            this.mPreference.setEnabled(false);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mADBRootService.setEnabled(((Boolean) obj).booleanValue());
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDeveloperOptionsSwitchEnabled() {
        if (isAdminUser()) {
            this.mPreference.setEnabled(true);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isAdminUser() {
        return ((UserManager) this.mContext.getSystemService("user")).isAdminUser();
    }
}
