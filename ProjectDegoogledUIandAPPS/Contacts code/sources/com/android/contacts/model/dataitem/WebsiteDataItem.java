package com.android.contacts.model.dataitem;

import android.content.ContentValues;

public class WebsiteDataItem extends DataItem {
    WebsiteDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getUrl() {
        return getContentValues().getAsString("data1");
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }
}
