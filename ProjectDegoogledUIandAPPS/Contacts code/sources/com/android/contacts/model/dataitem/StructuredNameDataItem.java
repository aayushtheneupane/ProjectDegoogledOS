package com.android.contacts.model.dataitem;

import android.content.ContentValues;

public class StructuredNameDataItem extends DataItem {
    public StructuredNameDataItem() {
        super(new ContentValues());
        getContentValues().put("mimetype", "vnd.android.cursor.item/name");
    }

    StructuredNameDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getDisplayName() {
        return getContentValues().getAsString("data1");
    }

    public void setDisplayName(String str) {
        getContentValues().put("data1", str);
    }

    public String getGivenName() {
        return getContentValues().getAsString("data2");
    }

    public String getFamilyName() {
        return getContentValues().getAsString("data3");
    }

    public String getPrefix() {
        return getContentValues().getAsString("data4");
    }

    public String getMiddleName() {
        return getContentValues().getAsString("data5");
    }

    public String getSuffix() {
        return getContentValues().getAsString("data6");
    }

    public String getPhoneticGivenName() {
        return getContentValues().getAsString("data7");
    }

    public String getPhoneticMiddleName() {
        return getContentValues().getAsString("data8");
    }

    public String getPhoneticFamilyName() {
        return getContentValues().getAsString("data9");
    }

    public String getFullNameStyle() {
        return getContentValues().getAsString("data10");
    }

    public void setPhoneticFamilyName(String str) {
        getContentValues().put("data9", str);
    }

    public void setPhoneticMiddleName(String str) {
        getContentValues().put("data8", str);
    }

    public void setPhoneticGivenName(String str) {
        getContentValues().put("data7", str);
    }

    public boolean isSuperPrimary() {
        ContentValues contentValues = getContentValues();
        if (contentValues == null || !contentValues.containsKey("is_super_primary")) {
            return false;
        }
        return contentValues.getAsBoolean("is_super_primary").booleanValue();
    }
}
