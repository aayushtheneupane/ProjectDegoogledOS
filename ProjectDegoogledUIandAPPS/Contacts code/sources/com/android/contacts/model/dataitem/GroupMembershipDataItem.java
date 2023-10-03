package com.android.contacts.model.dataitem;

import android.content.ContentValues;

public class GroupMembershipDataItem extends DataItem {
    GroupMembershipDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public Long getGroupRowId() {
        return getContentValues().getAsLong("data1");
    }
}
