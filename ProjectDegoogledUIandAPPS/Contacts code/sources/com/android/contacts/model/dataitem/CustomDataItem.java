package com.android.contacts.model.dataitem;

import android.content.ContentValues;

public class CustomDataItem extends DataItem {
    CustomDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getSummary() {
        return getContentValues().getAsString("data1");
    }

    public String getContent() {
        return getContentValues().getAsString("data2");
    }
}
