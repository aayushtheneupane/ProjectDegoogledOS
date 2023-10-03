package com.android.contacts.model.dataitem;

import android.content.ContentValues;

public class NicknameDataItem extends DataItem {
    public NicknameDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getName() {
        return getContentValues().getAsString("data1");
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }
}
