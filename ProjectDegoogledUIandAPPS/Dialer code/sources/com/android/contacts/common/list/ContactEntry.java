package com.android.contacts.common.list;

import android.content.Context;
import android.net.Uri;
import com.android.dialer.contacts.ContactsComponent;

public class ContactEntry {
    public static final ContactEntry BLANK_ENTRY = new ContactEntry();

    /* renamed from: id */
    public long f5id;
    public boolean isDefaultNumber = false;
    public boolean isFavorite = false;
    public String lookupKey;
    public Uri lookupUri;
    public String nameAlternative;
    public String namePrimary;
    public String phoneNumber;
    public Uri photoUri;
    public int pinned = 0;

    public String getPreferredDisplayName(Context context) {
        return ContactsComponent.get(context).contactDisplayPreferences().getDisplayName(this.namePrimary, this.nameAlternative);
    }
}
