package com.android.contacts.common;

import android.content.Context;
import android.content.CursorLoader;
import android.provider.ContactsContract;

public final class ContactTileLoaderFactory {
    public static final String[] COLUMNS_PHONE_ONLY = {"_id", "display_name", "starred", "photo_uri", "lookup", "data1", "data2", "data3", "is_super_primary", "pinned", "contact_id", "display_name_alt"};

    public static CursorLoader createStrequentPhoneOnlyLoader(Context context) {
        return new CursorLoader(context, ContactsContract.Contacts.CONTENT_STREQUENT_URI.buildUpon().appendQueryParameter("strequent_phone_only", "true").build(), COLUMNS_PHONE_ONLY, (String) null, (String[]) null, (String) null);
    }
}
