package com.android.contacts.common.model.dataitem;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;

public class RelationDataItem extends DataItem {
    RelationDataItem(ContentValues contentValues) {
        super(contentValues);
    }

    public String getLabel() {
        return getContentValues().getAsString("data3");
    }

    public String getName() {
        return getContentValues().getAsString("data1");
    }

    public boolean shouldCollapseWith(Object obj, Context context) {
        DataItem dataItem = (DataItem) obj;
        if (!(dataItem instanceof RelationDataItem) || this.mKind == null || dataItem.mKind == null) {
            return false;
        }
        RelationDataItem relationDataItem = (RelationDataItem) dataItem;
        if (!TextUtils.equals(getName(), relationDataItem.getName())) {
            return false;
        }
        if (!hasKindTypeColumn(this.mKind) || !relationDataItem.hasKindTypeColumn(relationDataItem.mKind)) {
            if (hasKindTypeColumn(this.mKind) != relationDataItem.hasKindTypeColumn(relationDataItem.mKind)) {
                return false;
            }
        } else if (getKindTypeColumn(this.mKind) != relationDataItem.getKindTypeColumn(relationDataItem.mKind)) {
            return false;
        } else {
            if (getKindTypeColumn(this.mKind) == 0 && !TextUtils.equals(getLabel(), relationDataItem.getLabel())) {
                return false;
            }
        }
        return true;
    }

    public boolean shouldCollapseWith(DataItem dataItem, Context context) {
        if (!(dataItem instanceof RelationDataItem) || this.mKind == null || dataItem.mKind == null) {
            return false;
        }
        RelationDataItem relationDataItem = (RelationDataItem) dataItem;
        if (!TextUtils.equals(getContentValues().getAsString("data1"), relationDataItem.getContentValues().getAsString("data1"))) {
            return false;
        }
        if (!hasKindTypeColumn(this.mKind) || !relationDataItem.hasKindTypeColumn(relationDataItem.mKind)) {
            if (hasKindTypeColumn(this.mKind) == relationDataItem.hasKindTypeColumn(relationDataItem.mKind)) {
                return true;
            }
            return false;
        } else if (getKindTypeColumn(this.mKind) != relationDataItem.getKindTypeColumn(relationDataItem.mKind)) {
            return false;
        } else {
            if (getKindTypeColumn(this.mKind) != 0 || TextUtils.equals(getContentValues().getAsString("data3"), relationDataItem.getContentValues().getAsString("data3"))) {
                return true;
            }
            return false;
        }
    }
}
