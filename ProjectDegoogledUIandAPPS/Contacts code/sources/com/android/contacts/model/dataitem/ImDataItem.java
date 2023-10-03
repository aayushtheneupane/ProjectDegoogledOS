package com.android.contacts.model.dataitem;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;

public class ImDataItem extends DataItem {
    private final boolean mCreatedFromEmail;

    ImDataItem(ContentValues contentValues) {
        super(contentValues);
        this.mCreatedFromEmail = false;
    }

    private ImDataItem(ContentValues contentValues, boolean z) {
        super(contentValues);
        this.mCreatedFromEmail = z;
    }

    public static ImDataItem createFromEmail(EmailDataItem emailDataItem) {
        ImDataItem imDataItem = new ImDataItem(new ContentValues(emailDataItem.getContentValues()), true);
        imDataItem.setMimeType("vnd.android.cursor.item/im");
        return imDataItem;
    }

    public String getData() {
        if (this.mCreatedFromEmail) {
            return getContentValues().getAsString("data1");
        }
        return getContentValues().getAsString("data1");
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }

    public Integer getProtocol() {
        return getContentValues().getAsInteger("data5");
    }

    public boolean isProtocolValid() {
        return getProtocol() != null;
    }

    public String getCustomProtocol() {
        return getContentValues().getAsString("data6");
    }

    public int getChatCapability() {
        Integer asInteger = getContentValues().getAsInteger("chat_capability");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public boolean isCreatedFromEmail() {
        return this.mCreatedFromEmail;
    }

    public boolean shouldCollapseWith(DataItem dataItem, Context context) {
        if (!(dataItem instanceof ImDataItem) || this.mKind == null || dataItem.getDataKind() == null) {
            return false;
        }
        ImDataItem imDataItem = (ImDataItem) dataItem;
        if (!getData().equals(imDataItem.getData())) {
            return false;
        }
        if (!isProtocolValid() || !imDataItem.isProtocolValid()) {
            if (isProtocolValid()) {
                if (getProtocol().intValue() == -1) {
                    return true;
                }
                return false;
            } else if (!imDataItem.isProtocolValid() || imDataItem.getProtocol().intValue() == -1) {
                return true;
            } else {
                return false;
            }
        } else if (getProtocol() != imDataItem.getProtocol()) {
            return false;
        } else {
            if (getProtocol().intValue() != -1 || TextUtils.equals(getCustomProtocol(), imDataItem.getCustomProtocol())) {
                return true;
            }
            return false;
        }
    }
}
