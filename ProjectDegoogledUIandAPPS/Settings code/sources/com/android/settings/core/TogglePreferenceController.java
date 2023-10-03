package com.android.settings.core;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.android.settings.widget.MasterSwitchPreference;

public abstract class TogglePreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private static final String TAG = "TogglePrefController";

    public int getSliceType() {
        return 1;
    }

    public abstract boolean isChecked();

    public abstract boolean setChecked(boolean z);

    public TogglePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateState(Preference preference) {
        if (preference instanceof TwoStatePreference) {
            ((TwoStatePreference) preference).setChecked(isChecked());
        } else if (preference instanceof MasterSwitchPreference) {
            ((MasterSwitchPreference) preference).setChecked(isChecked());
        } else {
            refreshSummary(preference);
        }
    }

    public final boolean onPreferenceChange(Preference preference, Object obj) {
        return setChecked(((Boolean) obj).booleanValue());
    }
}
