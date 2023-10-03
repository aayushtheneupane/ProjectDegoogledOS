package com.android.contacts.common.model.dataitem;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;

public class EventDataItem extends DataItem {
    EventDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }

    public String getStartDate() {
        return getContentValues().getAsString("data1");
    }

    public boolean shouldCollapseWith(Object obj, Context context) {
        DataItem dataItem = (DataItem) obj;
        if (!(dataItem instanceof EventDataItem) || this.mKind == null || dataItem.mKind == null) {
            return false;
        }
        EventDataItem eventDataItem = (EventDataItem) dataItem;
        if (!TextUtils.equals(getStartDate(), eventDataItem.getStartDate())) {
            return false;
        }
        if (!hasKindTypeColumn(this.mKind) || !eventDataItem.hasKindTypeColumn(eventDataItem.mKind)) {
            if (hasKindTypeColumn(this.mKind) != eventDataItem.hasKindTypeColumn(eventDataItem.mKind)) {
                return false;
            }
        } else if (getKindTypeColumn(this.mKind) != eventDataItem.getKindTypeColumn(eventDataItem.mKind)) {
            return false;
        } else {
            if (getKindTypeColumn(this.mKind) == 0 && !TextUtils.equals(getLabel(), eventDataItem.getLabel())) {
                return false;
            }
        }
        return true;
    }

    public boolean shouldCollapseWith(DataItem dataItem, Context context) {
        if (!(dataItem instanceof EventDataItem) || this.mKind == null || dataItem.mKind == null) {
            return false;
        }
        EventDataItem eventDataItem = (EventDataItem) dataItem;
        if (!TextUtils.equals(getContentValues().getAsString("data1"), eventDataItem.getContentValues().getAsString("data1"))) {
            return false;
        }
        if (!hasKindTypeColumn(this.mKind) || !eventDataItem.hasKindTypeColumn(eventDataItem.mKind)) {
            if (hasKindTypeColumn(this.mKind) == eventDataItem.hasKindTypeColumn(eventDataItem.mKind)) {
                return true;
            }
            return false;
        } else if (getKindTypeColumn(this.mKind) != eventDataItem.getKindTypeColumn(eventDataItem.mKind)) {
            return false;
        } else {
            if (getKindTypeColumn(this.mKind) != 0 || TextUtils.equals(getContentValues().getAsString("data3"), eventDataItem.getContentValues().getAsString("data3"))) {
                return true;
            }
            return false;
        }
    }
}
