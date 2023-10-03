package com.android.contacts.model.dataitem;

import android.content.ContentValues;

public class PhotoDataItem extends DataItem {
    PhotoDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public byte[] getPhoto() {
        return getContentValues().getAsByteArray("data15");
    }
}
