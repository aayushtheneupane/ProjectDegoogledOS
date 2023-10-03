package com.android.p032ex.chips;

import android.content.res.Resources;
import android.net.Uri;
import android.provider.ContactsContract;

/* renamed from: com.android.ex.chips.A */
class C0633A extends C0634B {
    C0633A(String[] strArr, Uri uri, Uri uri2) {
        super(strArr, uri, uri2);
    }

    public CharSequence getTypeLabel(Resources resources, int i, CharSequence charSequence) {
        return ContactsContract.CommonDataKinds.Email.getTypeLabel(resources, i, charSequence);
    }
}
