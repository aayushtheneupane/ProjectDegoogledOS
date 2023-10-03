package com.android.contacts.model.dataitem;

import android.content.ContentValues;
import android.content.Context;
import com.android.contacts.compat.PhoneNumberUtilsCompat;

public class PhoneDataItem extends DataItem {
    public static final String KEY_FORMATTED_PHONE_NUMBER = "formattedPhoneNumber";
    private DataItem mReachableDataItem;
    private boolean mTachyonReachable;

    PhoneDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getNumber() {
        return getContentValues().getAsString("data1");
    }

    public String getNormalizedNumber() {
        return getContentValues().getAsString("data4");
    }

    public String getFormattedPhoneNumber() {
        return getContentValues().getAsString(KEY_FORMATTED_PHONE_NUMBER);
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }

    public void setTachyonReachable(boolean z) {
        this.mTachyonReachable = z;
    }

    public boolean isTachyonReachable() {
        return this.mTachyonReachable;
    }

    public DataItem getReachableDataItem() {
        return this.mReachableDataItem;
    }

    public void setReachableDataItem(DataItem dataItem) {
        this.mReachableDataItem = dataItem;
    }

    public void computeFormattedPhoneNumber(String str) {
        String number = getNumber();
        if (number != null) {
            getContentValues().put(KEY_FORMATTED_PHONE_NUMBER, PhoneNumberUtilsCompat.formatNumber(number, getNormalizedNumber(), str));
        }
    }

    public String buildDataStringForDisplay(Context context, DataKind dataKind) {
        String formattedPhoneNumber = getFormattedPhoneNumber();
        if (formattedPhoneNumber != null) {
            return formattedPhoneNumber;
        }
        return getNumber();
    }
}
