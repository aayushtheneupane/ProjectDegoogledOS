package com.android.messaging.datamodel;

import android.net.Uri;
import android.provider.ContactsContract;

/* renamed from: com.android.messaging.datamodel.t */
class C0959t extends C0962w {
    /* synthetic */ C0959t(C0963x xVar, C0958s sVar) {
        super(xVar, (C0958s) null);
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        return ContactsContract.Contacts.CONTENT_FREQUENT_URI;
    }
}
