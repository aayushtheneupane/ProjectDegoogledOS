package com.android.contacts.compat;

import android.net.Uri;
import android.provider.ContactsContract;
import com.android.contacts.ContactsUtils;

public class CallableCompat {
    private static final Uri ENTERPRISE_CONTENT_FILTER_URI = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Callable.CONTENT_URI, "filter_enterprise");

    public static Uri getContentFilterUri() {
        if (ContactsUtils.FLAG_N_FEATURE) {
            return ENTERPRISE_CONTENT_FILTER_URI;
        }
        return ContactsContract.CommonDataKinds.Callable.CONTENT_FILTER_URI;
    }
}
