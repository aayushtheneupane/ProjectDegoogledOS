package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;

public class SystemPropListPreference extends ListPreference {
    public SystemPropListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemPropStore());
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        setValue(z ? getPersistedString((String) obj) : (String) obj);
    }
}
