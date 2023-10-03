package com.android.contacts.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.ContactsContract;

public final class ContactLoaderUtils {
    public static Uri ensureIsContactUri(ContentResolver contentResolver, Uri uri) throws IllegalArgumentException {
        if (uri != null) {
            String authority = uri.getAuthority();
            if ("com.android.contacts".equals(authority)) {
                String type = contentResolver.getType(uri);
                if ("vnd.android.cursor.item/contact".equals(type)) {
                    return uri;
                }
                if ("vnd.android.cursor.item/raw_contact".equals(type)) {
                    return ContactsContract.RawContacts.getContactLookupUri(contentResolver, ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, ContentUris.parseId(uri)));
                }
                throw new IllegalArgumentException("uri format is unknown");
            } else if ("contacts".equals(authority)) {
                return ContactsContract.RawContacts.getContactLookupUri(contentResolver, ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, ContentUris.parseId(uri)));
            } else {
                throw new IllegalArgumentException("uri authority is unknown");
            }
        } else {
            throw new IllegalArgumentException("uri must not be null");
        }
    }
}
