package androidx.preference;

import java.util.Set;

public abstract class PreferenceDataStore {
    public abstract boolean getBoolean(String str, boolean z);

    public abstract int getInt(String str, int i);

    public abstract String getString(String str, String str2);

    public Set<String> getStringSet(String str, Set<String> set) {
        return set;
    }

    public abstract void putBoolean(String str, boolean z);

    public abstract void putInt(String str, int i);

    public abstract void putString(String str, String str2);

    public void putStringSet(String str, Set<String> set) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }
}
