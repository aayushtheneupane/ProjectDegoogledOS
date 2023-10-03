package com.android.settings.display;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class NightDisplayAutoModePreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private ColorDisplayManager mColorDisplayManager;
    private DropDownPreference mPreference;

    public NightDisplayAutoModePreferenceController(Context context, String str) {
        super(context, str);
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
    }

    public int getAvailabilityStatus() {
        return ColorDisplayManager.isNightDisplayAvailable(this.mContext) ? 0 : 3;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (DropDownPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setEntries(new CharSequence[]{this.mContext.getString(C1715R.string.night_display_auto_mode_never), this.mContext.getString(C1715R.string.night_display_auto_mode_custom), this.mContext.getString(C1715R.string.night_display_auto_mode_twilight)});
        this.mPreference.setEntryValues(new CharSequence[]{String.valueOf(0), String.valueOf(1), String.valueOf(2)});
    }

    public final void updateState(Preference preference) {
        this.mPreference.setValue(String.valueOf(this.mColorDisplayManager.getNightDisplayAutoMode()));
    }

    public final boolean onPreferenceChange(Preference preference, Object obj) {
        int parseInt = Integer.parseInt((String) obj);
        if (!(this.mColorDisplayManager.getNightDisplayAutoMode() == 0 || parseInt == 0)) {
            this.mColorDisplayManager.setNightDisplayActivated(false);
        }
        return this.mColorDisplayManager.setNightDisplayAutoMode(parseInt);
    }
}
