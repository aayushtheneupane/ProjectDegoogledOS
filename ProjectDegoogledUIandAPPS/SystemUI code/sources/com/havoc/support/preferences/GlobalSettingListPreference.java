package com.havoc.support.preferences;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.preference.ListPreference;

public class GlobalSettingListPreference extends ListPreference {
    private boolean mAutoSummary = false;

    public GlobalSettingListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }

    public void setValue(String str) {
        super.setValue(str);
        if (this.mAutoSummary || TextUtils.isEmpty(getSummary())) {
            setSummary(getEntry(), true);
        }
    }

    public void setSummary(CharSequence charSequence) {
        setSummary(charSequence, false);
    }

    private void setSummary(CharSequence charSequence, boolean z) {
        this.mAutoSummary = z;
        super.setSummary(charSequence);
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        setValue(z ? getPersistedString((String) obj) : (String) obj);
    }
}
