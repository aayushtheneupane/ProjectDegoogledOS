package com.havoc.support.preferences;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;

public class SystemSettingSwitchPreference extends SwitchPreference {
    public SystemSettingSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public boolean persistBoolean(boolean z) {
        if (!shouldPersist()) {
            return false;
        }
        if (z == getPersistedBoolean(!z)) {
            return true;
        }
        Settings.System.putInt(getContext().getContentResolver(), getKey(), z ? 1 : 0);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getPersistedBoolean(boolean z) {
        if (!shouldPersist()) {
            return z;
        }
        return Settings.System.getInt(getContext().getContentResolver(), getKey(), z ? 1 : 0) != 0;
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(boolean z, Object obj) {
        boolean z2;
        if (Settings.System.getString(getContext().getContentResolver(), getKey()) != null) {
            z2 = getPersistedBoolean(isChecked());
        } else {
            z2 = ((Boolean) obj).booleanValue();
        }
        setChecked(z2);
    }
}
