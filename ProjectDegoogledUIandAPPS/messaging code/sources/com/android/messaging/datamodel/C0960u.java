package com.android.messaging.datamodel;

import android.net.Uri;
import android.provider.ContactsContract;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.datamodel.u */
class C0960u extends C0962w {
    /* synthetic */ C0960u(C0963x xVar, C0958s sVar) {
        super(xVar, (C0958s) null);
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        boolean Yj = C1464na.m3758Yj();
        return ContactsContract.Contacts.CONTENT_STREQUENT_URI.buildUpon().appendQueryParameter("strequent_phone_only", "true").build();
    }
}
