package com.android.contacts.model.dataitem;

import android.content.ContentValues;

public class EmailDataItem extends DataItem {
    EmailDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getAddress() {
        return getContentValues().getAsString("data1");
    }

    public String getDisplayName() {
        return getContentValues().getAsString("data4");
    }

    public String getData() {
        return getContentValues().getAsString("data1");
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }
}
