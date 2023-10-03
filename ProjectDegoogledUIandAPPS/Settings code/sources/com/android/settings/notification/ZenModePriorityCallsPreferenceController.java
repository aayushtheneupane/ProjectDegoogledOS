package com.android.settings.notification;

import android.content.Context;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class ZenModePriorityCallsPreferenceController extends AbstractZenModePreferenceController implements Preference.OnPreferenceChangeListener {
    private final ZenModeBackend mBackend;
    private final String[] mListValues;
    private ListPreference mPreference;

    public String getPreferenceKey() {
        return "zen_mode_calls";
    }

    public boolean isAvailable() {
        return true;
    }

    public ZenModePriorityCallsPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, "zen_mode_calls", lifecycle);
        this.mBackend = ZenModeBackend.getInstance(context);
        this.mListValues = context.getResources().getStringArray(C1715R.array.zen_mode_contacts_values);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ListPreference) preferenceScreen.findPreference("zen_mode_calls");
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        updateFromContactsValue(preference);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mBackend.saveSenders(8, ZenModeBackend.getSettingFromPrefKey(obj.toString()));
        updateFromContactsValue(preference);
        return true;
    }

    private void updateFromContactsValue(Preference preference) {
        this.mPreference = (ListPreference) preference;
        int zenMode = getZenMode();
        if (zenMode == 2 || zenMode == 3) {
            this.mPreference.setEnabled(false);
            this.mPreference.setValue("zen_mode_from_none");
            this.mPreference.setSummary(this.mBackend.getAlarmsTotalSilenceCallsMessagesSummary(8));
            return;
        }
        preference.setEnabled(true);
        preference.setSummary(this.mBackend.getContactsSummary(8));
        this.mPreference.setValue(this.mListValues[getIndexOfSendersValue(ZenModeBackend.getKeyFromSetting(this.mBackend.getPriorityCallSenders()))]);
    }

    /* access modifiers changed from: protected */
    public int getIndexOfSendersValue(String str) {
        int i = 0;
        while (true) {
            String[] strArr = this.mListValues;
            if (i >= strArr.length) {
                return 3;
            }
            if (TextUtils.equals(str, strArr[i])) {
                return i;
            }
            i++;
        }
    }
}
