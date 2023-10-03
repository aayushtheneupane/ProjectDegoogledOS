package com.havoc.support.preferences;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;

public class GlobalSettingSwitchPreference extends SwitchPreference {
    public GlobalSettingSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public GlobalSettingSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GlobalSettingSwitchPreference(Context context) {
        super(context, (AttributeSet) null);
    }

    /* access modifiers changed from: protected */
    public boolean persistBoolean(boolean z) {
        if (!shouldPersist()) {
            return false;
        }
        if (z == getPersistedBoolean(!z)) {
            return true;
        }
        Settings.Global.putInt(getContext().getContentResolver(), getKey(), z ? 1 : 0);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getPersistedBoolean(boolean z) {
        if (!shouldPersist()) {
            return z;
        }
        return Settings.Global.getInt(getContext().getContentResolver(), getKey(), z ? 1 : 0) != 0;
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        boolean z2;
        if (Settings.Global.getString(getContext().getContentResolver(), getKey()) != null) {
            z2 = getPersistedBoolean(isChecked());
        } else {
            z2 = ((Boolean) obj).booleanValue();
        }
        setChecked(z2);
    }
}
