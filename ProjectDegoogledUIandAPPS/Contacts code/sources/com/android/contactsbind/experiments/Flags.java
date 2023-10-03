package com.android.contactsbind.experiments;

import java.util.HashMap;
import java.util.Map;

public final class Flags {
    private static Flags sInstance;
    private Map<String, Object> mMap = new HashMap();

    public static Flags getInstance() {
        if (sInstance == null) {
            sInstance = new Flags();
        }
        return sInstance;
    }

    private Flags() {
    }

    public boolean getBoolean(String str) {
        if (this.mMap.containsKey(str)) {
            return ((Boolean) this.mMap.get(str)).booleanValue();
        }
        return false;
    }

    public int getInteger(String str) {
        if (this.mMap.containsKey(str)) {
            return ((Integer) this.mMap.get(str)).intValue();
        }
        return 0;
    }
}
