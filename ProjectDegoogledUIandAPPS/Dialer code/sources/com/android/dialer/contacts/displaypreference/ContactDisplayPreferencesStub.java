package com.android.dialer.contacts.displaypreference;

import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;

public final class ContactDisplayPreferencesStub implements ContactDisplayPreferences {
    ContactDisplayPreferencesStub() {
    }

    public ContactDisplayPreferences.DisplayOrder getDisplayOrder() {
        return ContactDisplayPreferences.DisplayOrder.PRIMARY;
    }

    public ContactDisplayPreferences.SortOrder getSortOrder() {
        return ContactDisplayPreferences.SortOrder.BY_PRIMARY;
    }
}
