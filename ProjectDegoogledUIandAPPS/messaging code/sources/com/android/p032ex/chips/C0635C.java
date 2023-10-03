package com.android.p032ex.chips;

import android.provider.ContactsContract;

/* renamed from: com.android.ex.chips.C */
class C0635C {
    public static final C0634B EMAIL = new C0633A(new String[]{"display_name", "data1", "data2", "data3", "contact_id", "_id", "photo_thumb_uri", "display_name_source", "lookup", "mimetype"}, ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI, ContactsContract.CommonDataKinds.Email.CONTENT_URI);
    public static final C0634B PHONE = new C0708z(new String[]{"display_name", "data1", "data2", "data3", "contact_id", "_id", "photo_thumb_uri", "display_name_source", "lookup", "mimetype"}, ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
}
