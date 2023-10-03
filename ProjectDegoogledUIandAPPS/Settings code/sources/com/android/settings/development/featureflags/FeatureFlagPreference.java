package com.android.settings.development.featureflags;

import android.content.Context;
import android.util.FeatureFlagUtils;
import com.havoc.support.preferences.SwitchPreference;

public class FeatureFlagPreference extends SwitchPreference {
    private final boolean mIsPersistent;
    private final String mKey;

    public FeatureFlagPreference(Context context, String str) {
        super(context);
        boolean z;
        this.mKey = str;
        setKey(str);
        setTitle((CharSequence) str);
        this.mIsPersistent = FeatureFlagPersistent.isPersistent(str);
        if (this.mIsPersistent) {
            z = FeatureFlagPersistent.isEnabled(context, str);
        } else {
            z = FeatureFlagUtils.isEnabled(context, str);
        }
        setCheckedInternal(z);
    }

    public void setChecked(boolean z) {
        setCheckedInternal(z);
        if (this.mIsPersistent) {
            FeatureFlagPersistent.setEnabled(getContext(), this.mKey, z);
        } else {
            FeatureFlagUtils.setEnabled(getContext(), this.mKey, z);
        }
    }

    private void setCheckedInternal(boolean z) {
        super.setChecked(z);
        setSummary((CharSequence) Boolean.toString(z));
    }
}
