package com.android.contacts.common.model.dataitem;

import android.content.ContentValues;

public class StructuredNameDataItem extends DataItem {
    StructuredNameDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public boolean isSuperPrimary() {
        ContentValues contentValues = getContentValues();
        if (contentValues == null || !contentValues.containsKey("is_super_primary")) {
            return false;
        }
        return contentValues.getAsBoolean("is_super_primary").booleanValue();
    }
}
