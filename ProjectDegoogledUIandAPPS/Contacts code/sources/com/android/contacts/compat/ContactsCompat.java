package com.android.contacts.compat;

import android.net.Uri;
import android.provider.ContactsContract;
import com.android.contacts.ContactsUtils;

public class ContactsCompat {
    private static final Uri ENTERPRISE_CONTENT_FILTER_URI = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "filter_enterprise");

    public static Uri getContentUri() {
        if (ContactsUtils.FLAG_N_FEATURE) {
            return ENTERPRISE_CONTENT_FILTER_URI;
        }
        return ContactsContract.Contacts.CONTENT_FILTER_URI;
    }

    public static boolean isEnterpriseContactId(long j) {
        if (CompatUtils.isLollipopCompatible()) {
            return ContactsContract.Contacts.isEnterpriseContactId(j);
        }
        return j >= 1000000000 && j < 9223372034707292160L;
    }
}
