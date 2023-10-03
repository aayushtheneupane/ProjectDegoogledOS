package com.android.settingslib.development;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import com.android.settingslib.core.ConfirmationDialogController;
import com.havoc.support.preferences.SwitchPreference;

public abstract class AbstractEnableAdbPreferenceController extends DeveloperOptionsPreferenceController implements ConfirmationDialogController {
    protected SwitchPreference mPreference;

    public String getPreferenceKey() {
        return "enable_adb";
    }

    public AbstractEnableAdbPreferenceController(Context context) {
        super(context);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            this.mPreference = (SwitchPreference) preferenceScreen.findPreference("enable_adb");
        }
    }

    public boolean isAvailable() {
        return ((UserManager) this.mContext.getSystemService(UserManager.class)).isAdminUser();
    }

    private boolean isAdbEnabled() {
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "adb_enabled", 0) != 0) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        ((TwoStatePreference) preference).setChecked(isAdbEnabled());
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (isUserAMonkey() || !TextUtils.equals("enable_adb", preference.getKey())) {
            return false;
        }
        if (!isAdbEnabled()) {
            showConfirmationDialog(preference);
            return true;
        }
        writeAdbSetting(false);
        return true;
    }

    /* access modifiers changed from: protected */
    public void writeAdbSetting(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "adb_enabled", z ? 1 : 0);
        notifyStateChanged();
    }

    private void notifyStateChanged() {
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent("com.android.settingslib.development.AbstractEnableAdbController.ENABLE_ADB_STATE_CHANGED"));
    }

    /* access modifiers changed from: package-private */
    public boolean isUserAMonkey() {
        return ActivityManager.isUserAMonkey();
    }
}
