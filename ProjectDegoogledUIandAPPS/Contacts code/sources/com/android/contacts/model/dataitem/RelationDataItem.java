package com.android.contacts.model.dataitem;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;

public class RelationDataItem extends DataItem {
    RelationDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getName() {
        return getContentValues().getAsString("data1");
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }

    public boolean shouldCollapseWith(DataItem dataItem, Context context) {
        if (!(dataItem instanceof RelationDataItem) || this.mKind == null || dataItem.getDataKind() == null) {
            return false;
        }
        RelationDataItem relationDataItem = (RelationDataItem) dataItem;
        if (!TextUtils.equals(getName(), relationDataItem.getName())) {
            return false;
        }
        if (!hasKindTypeColumn(this.mKind) || !relationDataItem.hasKindTypeColumn(relationDataItem.getDataKind())) {
            if (hasKindTypeColumn(this.mKind) == relationDataItem.hasKindTypeColumn(relationDataItem.getDataKind())) {
                return true;
            }
            return false;
        } else if (getKindTypeColumn(this.mKind) != relationDataItem.getKindTypeColumn(relationDataItem.getDataKind())) {
            return false;
        } else {
            if (getKindTypeColumn(this.mKind) != 0 || TextUtils.equals(getLabel(), relationDataItem.getLabel())) {
                return true;
            }
            return false;
        }
    }
}
