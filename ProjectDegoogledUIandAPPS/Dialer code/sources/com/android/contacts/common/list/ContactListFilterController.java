package com.android.contacts.common.list;

import android.content.Context;

public abstract class ContactListFilterController {
    private static ContactListFilterControllerImpl sFilterController;

    public interface ContactListFilterListener {
        void onContactListFilterChanged();
    }

    public static ContactListFilterController getInstance(Context context) {
        if (sFilterController == null) {
            sFilterController = new ContactListFilterControllerImpl(context);
        }
        return sFilterController;
    }

    public abstract void checkFilterValidity(boolean z);
}
