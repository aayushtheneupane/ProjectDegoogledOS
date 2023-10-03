package com.havoc.support.preferences;

import android.content.ContentResolver;
import android.provider.Settings;
import androidx.preference.PreferenceDataStore;

public class SecureSettingsStore extends PreferenceDataStore implements android.preference.PreferenceDataStore {
    private ContentResolver mContentResolver;

    public SecureSettingsStore(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    public boolean getBoolean(String str, boolean z) {
        return getInt(str, z ? 1 : 0) != 0;
    }

    public float getFloat(String str, float f) {
        return Settings.Secure.getFloat(this.mContentResolver, str, f);
    }

    public int getInt(String str, int i) {
        return Settings.Secure.getInt(this.mContentResolver, str, i);
    }

    public long getLong(String str, long j) {
        return Settings.Secure.getLong(this.mContentResolver, str, j);
    }

    public String getString(String str, String str2) {
        String string = Settings.Secure.getString(this.mContentResolver, str);
        return string == null ? str2 : string;
    }

    public void putBoolean(String str, boolean z) {
        putInt(str, z ? 1 : 0);
    }

    public void putFloat(String str, float f) {
        Settings.Secure.putFloat(this.mContentResolver, str, f);
    }

    public void putInt(String str, int i) {
        Settings.Secure.putInt(this.mContentResolver, str, i);
    }

    public void putLong(String str, long j) {
        Settings.Secure.putLong(this.mContentResolver, str, j);
    }

    public void putString(String str, String str2) {
        Settings.Secure.putString(this.mContentResolver, str, str2);
    }
}
