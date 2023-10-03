package com.android.contacts.compat;

import android.content.res.Resources;
import android.provider.ContactsContract;
import android.text.TextUtils;

public class EventCompat {
    public static CharSequence getTypeLabel(Resources resources, int i, CharSequence charSequence) {
        if (CompatUtils.isLollipopCompatible()) {
            return ContactsContract.CommonDataKinds.Event.getTypeLabel(resources, i, charSequence);
        }
        return getTypeLabelInternal(resources, i, charSequence);
    }

    private static CharSequence getTypeLabelInternal(Resources resources, int i, CharSequence charSequence) {
        if (i != 0 || TextUtils.isEmpty(charSequence)) {
            return resources.getText(ContactsContract.CommonDataKinds.Event.getTypeResource(Integer.valueOf(i)));
        }
        return charSequence;
    }
}
