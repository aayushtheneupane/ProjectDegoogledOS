package com.google.android.setupcompat.partnerconfig;

import android.os.Bundle;

public final class ResourceEntry {
    static final String KEY_PACKAGE_NAME = "packageName";
    static final String KEY_RESOURCE_ID = "resourceId";
    static final String KEY_RESOURCE_NAME = "resourceName";
    private final String packageName;
    private final int resourceId;
    private final String resourceName;

    public static ResourceEntry fromBundle(Bundle bundle) {
        if (!bundle.containsKey(KEY_PACKAGE_NAME) || !bundle.containsKey(KEY_RESOURCE_NAME) || !bundle.containsKey(KEY_RESOURCE_ID)) {
            return null;
        }
        return new ResourceEntry(bundle.getString(KEY_PACKAGE_NAME), bundle.getString(KEY_RESOURCE_NAME), bundle.getInt(KEY_RESOURCE_ID));
    }

    public ResourceEntry(String str, String str2, int i) {
        this.packageName = str;
        this.resourceName = str2;
        this.resourceId = i;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public int getResourceId() {
        return this.resourceId;
    }
}
