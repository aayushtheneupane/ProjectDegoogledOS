package com.android.settings.development;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.havoc.support.preferences.SwitchPreference;

public class BubbleGlobalPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    static final int OFF = 0;

    /* renamed from: ON */
    static final int f25ON = 1;

    public String getPreferenceKey() {
        return "notification_bubbles";
    }

    public BubbleGlobalPreferenceController(Context context) {
        super(context);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        writeSetting(((Boolean) obj).booleanValue());
        return true;
    }

    public void updateState(Preference preference) {
        ((SwitchPreference) this.mPreference).setChecked(isEnabled());
    }

    /* access modifiers changed from: protected */
    public void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        writeSetting(false);
        updateState(this.mPreference);
    }

    private boolean isEnabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "notification_bubbles", 0) == 1;
    }

    private void writeSetting(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "notification_bubbles", z ? 1 : 0);
    }
}
