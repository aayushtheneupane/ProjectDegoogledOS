package com.android.contacts.model.dataitem;

import android.content.ContentValues;

public class OrganizationDataItem extends DataItem {
    OrganizationDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getCompany() {
        return getContentValues().getAsString("data1");
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }

    public String getTitle() {
        return getContentValues().getAsString("data4");
    }

    public String getDepartment() {
        return getContentValues().getAsString("data5");
    }

    public String getJobDescription() {
        return getContentValues().getAsString("data6");
    }

    public String getSymbol() {
        return getContentValues().getAsString("data7");
    }

    public String getPhoneticName() {
        return getContentValues().getAsString("data8");
    }

    public String getOfficeLocation() {
        return getContentValues().getAsString("data9");
    }
}
