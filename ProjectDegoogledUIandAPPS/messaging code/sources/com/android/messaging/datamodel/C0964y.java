package com.android.messaging.datamodel;

import android.content.Context;
import android.provider.ContactsContract;

/* renamed from: com.android.messaging.datamodel.y */
public class C0964y extends C0946g {
    public C0964y(Context context, String[] strArr, String str, String[] strArr2, String str2) {
        super(context, ContactsContract.Contacts.CONTENT_STREQUENT_URI, strArr, str, strArr2, str2);
    }

    /* renamed from: J */
    public C0837b mo6583J(String str) {
        return new C0963x(str, this.mContext, this.mUri, this.mProjection, this.mSelection, this.mSelectionArgs, this.mSortOrder);
    }
}
