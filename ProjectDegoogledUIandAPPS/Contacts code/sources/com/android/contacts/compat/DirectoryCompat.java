package com.android.contacts.compat;

import android.net.Uri;
import android.provider.ContactsContract;
import com.android.contacts.ContactsUtils;

public class DirectoryCompat {
    public static Uri getContentUri() {
        if (ContactsUtils.FLAG_N_FEATURE) {
            return DirectorySdkCompat.ENTERPRISE_CONTENT_URI;
        }
        return ContactsContract.Directory.CONTENT_URI;
    }

    public static boolean isInvisibleDirectory(long j) {
        return ContactsUtils.FLAG_N_FEATURE ? j == 1 || j == 1000000001 : j == 1;
    }

    public static boolean isRemoteDirectoryId(long j) {
        if (ContactsUtils.FLAG_N_FEATURE) {
            return DirectorySdkCompat.isRemoteDirectoryId(j);
        }
        return (j == 0 || j == 1) ? false : true;
    }

    public static boolean isEnterpriseDirectoryId(long j) {
        if (ContactsUtils.FLAG_N_FEATURE) {
            return DirectorySdkCompat.isEnterpriseDirectoryId(j);
        }
        return false;
    }
}
