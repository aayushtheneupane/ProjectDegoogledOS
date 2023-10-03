package com.android.contacts.model.dataitem;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;

public class EventDataItem extends DataItem {
    EventDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getStartDate() {
        return getContentValues().getAsString("data1");
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }

    public boolean shouldCollapseWith(DataItem dataItem, Context context) {
        if (!(dataItem instanceof EventDataItem) || this.mKind == null || dataItem.getDataKind() == null) {
            return false;
        }
        EventDataItem eventDataItem = (EventDataItem) dataItem;
        if (!TextUtils.equals(getStartDate(), eventDataItem.getStartDate())) {
            return false;
        }
        if (!hasKindTypeColumn(this.mKind) || !eventDataItem.hasKindTypeColumn(eventDataItem.getDataKind())) {
            if (hasKindTypeColumn(this.mKind) == eventDataItem.hasKindTypeColumn(eventDataItem.getDataKind())) {
                return true;
            }
            return false;
        } else if (getKindTypeColumn(this.mKind) != eventDataItem.getKindTypeColumn(eventDataItem.getDataKind())) {
            return false;
        } else {
            if (getKindTypeColumn(this.mKind) != 0 || TextUtils.equals(getLabel(), eventDataItem.getLabel())) {
                return true;
            }
            return false;
        }
    }
}
