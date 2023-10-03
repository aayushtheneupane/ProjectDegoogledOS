package com.android.contacts.logging;

import com.google.common.base.MoreObjects;

public final class ListEvent {
    public int actionType;
    public int clickedIndex = -1;
    public int count;
    public int listType;
    public int numSelected;

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Object) this);
        stringHelper.add("actionType", this.actionType);
        stringHelper.add("listType", this.listType);
        stringHelper.add("count", this.count);
        stringHelper.add("clickedIndex", this.clickedIndex);
        stringHelper.add("numSelected", this.numSelected);
        return stringHelper.toString();
    }
}
