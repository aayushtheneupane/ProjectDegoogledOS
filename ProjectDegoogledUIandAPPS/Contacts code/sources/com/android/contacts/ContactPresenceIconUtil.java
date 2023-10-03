package com.android.contacts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;

public class ContactPresenceIconUtil {
    public static Drawable getPresenceIcon(Context context, int i) {
        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            return context.getResources().getDrawable(ContactsContract.StatusUpdates.getPresenceIconResourceId(i));
        }
        return null;
    }
}
