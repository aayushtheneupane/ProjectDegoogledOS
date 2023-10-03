package com.android.contacts.common.model.dataitem;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;

public class ImDataItem extends DataItem {
    private final boolean mCreatedFromEmail = false;

    ImDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getCustomProtocol() {
        return getContentValues().getAsString("data6");
    }

    public String getData() {
        if (this.mCreatedFromEmail) {
            return getContentValues().getAsString("data1");
        }
        return getContentValues().getAsString("data1");
    }

    public Integer getProtocol() {
        return getContentValues().getAsInteger("data5");
    }

    public boolean isProtocolValid() {
        return getProtocol() != null;
    }

    public boolean shouldCollapseWith(Object obj, Context context) {
        DataItem dataItem = (DataItem) obj;
        if (!(dataItem instanceof ImDataItem) || this.mKind == null || dataItem.mKind == null) {
            return false;
        }
        ImDataItem imDataItem = (ImDataItem) dataItem;
        if (!getData().equals(imDataItem.getData())) {
            return false;
        }
        if (!isProtocolValid() || !imDataItem.isProtocolValid()) {
            if (isProtocolValid()) {
                if (getProtocol().intValue() != -1) {
                    return false;
                }
            } else if (imDataItem.isProtocolValid() && imDataItem.getProtocol().intValue() != -1) {
                return false;
            }
        } else if (getProtocol() != imDataItem.getProtocol()) {
            return false;
        } else {
            if (getProtocol().intValue() == -1 && !TextUtils.equals(getCustomProtocol(), imDataItem.getCustomProtocol())) {
                return false;
            }
        }
        return true;
    }

    public boolean shouldCollapseWith(DataItem dataItem, Context context) {
        if (!(dataItem instanceof ImDataItem) || this.mKind == null || dataItem.mKind == null) {
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
            } else if (!imDataItem.isProtocolValid()) {
                return true;
            } else {
                if (imDataItem.getProtocol().intValue() == -1) {
                    return true;
                }
                return false;
            }
        } else if (getProtocol() != imDataItem.getProtocol()) {
            return false;
        } else {
            if (getProtocol().intValue() != -1 || TextUtils.equals(getContentValues().getAsString("data6"), imDataItem.getContentValues().getAsString("data6"))) {
                return true;
            }
            return false;
        }
    }
}
