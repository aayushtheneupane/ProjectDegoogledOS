package com.android.contacts.logging;

import com.google.common.base.MoreObjects;

public class EditorEvent {
    public int eventType;
    public int numberRawContacts;

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Object) this);
        stringHelper.add("eventType", this.eventType);
        stringHelper.add("numberRawContacts", this.numberRawContacts);
        return stringHelper.toString();
    }
}
