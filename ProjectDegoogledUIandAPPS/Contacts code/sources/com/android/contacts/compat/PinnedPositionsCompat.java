package com.android.contacts.compat;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.ContactsContract;

public class PinnedPositionsCompat {
    public static void undemote(ContentResolver contentResolver, long j) {
        if (contentResolver != null) {
            if (CompatUtils.isLollipopCompatible()) {
                ContactsContract.PinnedPositions.undemote(contentResolver, j);
            } else {
                contentResolver.call(ContactsContract.AUTHORITY_URI, "undemote", String.valueOf(j), (Bundle) null);
            }
        }
    }
}
