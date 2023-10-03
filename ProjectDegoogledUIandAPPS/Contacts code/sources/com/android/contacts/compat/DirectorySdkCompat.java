package com.android.contacts.compat;

import android.net.Uri;
import android.provider.ContactsContract;

public class DirectorySdkCompat {
    public static final Uri ENTERPRISE_CONTENT_URI = ContactsContract.Directory.ENTERPRISE_CONTENT_URI;

    public static boolean isRemoteDirectoryId(long j) {
        if (CompatUtils.isNCompatible()) {
            return ContactsContract.Directory.isRemoteDirectoryId(j);
        }
        return false;
    }

    public static boolean isEnterpriseDirectoryId(long j) {
        if (CompatUtils.isNCompatible()) {
            return ContactsContract.Directory.isEnterpriseDirectoryId(j);
        }
        return false;
    }
}
