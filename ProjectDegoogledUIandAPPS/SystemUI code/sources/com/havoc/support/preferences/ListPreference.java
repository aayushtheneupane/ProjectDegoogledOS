package com.havoc.support.preferences;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

public class ListPreference extends androidx.preference.ListPreference {
    private boolean mAutoSummary = false;

    public ListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
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
}
