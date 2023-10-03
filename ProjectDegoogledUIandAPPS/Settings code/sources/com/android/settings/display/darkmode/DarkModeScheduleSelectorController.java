package com.android.settings.display.darkmode;

import android.app.UiModeManager;
import android.content.Context;
import android.os.PowerManager;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class DarkModeScheduleSelectorController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private String mCurrentMode;
    private PowerManager mPowerManager;
    private DropDownPreference mPreference;
    private final UiModeManager mUiModeManager;

    public int getAvailabilityStatus() {
        return 0;
    }

    public DarkModeScheduleSelectorController(Context context, String str) {
        super(context, str);
        this.mUiModeManager = (UiModeManager) context.getSystemService(UiModeManager.class);
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (DropDownPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public final void updateState(Preference preference) {
        String str;
        this.mPreference.setEnabled(!this.mPowerManager.isPowerSaveMode());
        if (this.mUiModeManager.getNightMode() == 0) {
            str = this.mContext.getString(C1715R.string.dark_ui_auto_mode_auto);
        } else {
            str = this.mContext.getString(C1715R.string.dark_ui_auto_mode_never);
        }
        this.mCurrentMode = str;
        this.mPreference.setValue(this.mCurrentMode);
    }

    public final boolean onPreferenceChange(Preference preference, Object obj) {
        String str = (String) obj;
        boolean z = false;
        if (str == this.mCurrentMode) {
            return false;
        }
        this.mCurrentMode = str;
        if (this.mCurrentMode == this.mContext.getString(C1715R.string.dark_ui_auto_mode_never)) {
            if ((this.mContext.getResources().getConfiguration().uiMode & 32) != 0) {
                z = true;
            }
            this.mUiModeManager.setNightMode(z ? 2 : 1);
        } else if (this.mCurrentMode == this.mContext.getString(C1715R.string.dark_ui_auto_mode_auto)) {
            this.mUiModeManager.setNightMode(0);
        }
        return true;
    }
}
