package com.havoc.support.preferences;

import android.content.ContentResolver;
import android.provider.Settings;
import androidx.preference.PreferenceDataStore;

public class SystemSettingsStore extends PreferenceDataStore implements android.preference.PreferenceDataStore {
    private ContentResolver mContentResolver;

    public SystemSettingsStore(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    public boolean getBoolean(String str, boolean z) {
        return getInt(str, z ? 1 : 0) != 0;
    }

    public float getFloat(String str, float f) {
        return Settings.System.getFloat(this.mContentResolver, str, f);
    }

    public int getInt(String str, int i) {
        return Settings.System.getInt(this.mContentResolver, str, i);
    }

    public long getLong(String str, long j) {
        return Settings.System.getLong(this.mContentResolver, str, j);
    }

    public String getString(String str, String str2) {
        String string = Settings.System.getString(this.mContentResolver, str);
        return string == null ? str2 : string;
    }

    public void putBoolean(String str, boolean z) {
        putInt(str, z ? 1 : 0);
    }

    public void putFloat(String str, float f) {
        Settings.System.putFloat(this.mContentResolver, str, f);
    }

    public void putInt(String str, int i) {
        Settings.System.putInt(this.mContentResolver, str, i);
    }

    public void putLong(String str, long j) {
        Settings.System.putLong(this.mContentResolver, str, j);
    }

    public void putString(String str, String str2) {
        Settings.System.putString(this.mContentResolver, str, str2);
    }
}
