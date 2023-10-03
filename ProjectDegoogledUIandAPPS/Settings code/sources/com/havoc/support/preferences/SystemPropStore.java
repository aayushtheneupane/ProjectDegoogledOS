package com.havoc.support.preferences;

import android.os.SystemProperties;
import androidx.preference.PreferenceDataStore;

public class SystemPropStore extends PreferenceDataStore implements android.preference.PreferenceDataStore {
    public boolean getBoolean(String str, boolean z) {
        return SystemProperties.getBoolean(str, z);
    }

    public float getFloat(String str, float f) {
        return Float.parseFloat(SystemProperties.get(str, "" + f));
    }

    public int getInt(String str, int i) {
        return SystemProperties.getInt(str, i);
    }

    public long getLong(String str, long j) {
        return SystemProperties.getLong(str, j);
    }

    public String getString(String str, String str2) {
        return SystemProperties.get(str, str2);
    }

    public void putBoolean(String str, boolean z) {
        SystemProperties.set(str, "" + z);
    }

    public void putFloat(String str, float f) {
        SystemProperties.set(str, "" + f);
    }

    public void putInt(String str, int i) {
        SystemProperties.set(str, "" + i);
    }

    public void putLong(String str, long j) {
        SystemProperties.set(str, "" + j);
    }

    public void putString(String str, String str2) {
        SystemProperties.set(str, str2);
    }
}
