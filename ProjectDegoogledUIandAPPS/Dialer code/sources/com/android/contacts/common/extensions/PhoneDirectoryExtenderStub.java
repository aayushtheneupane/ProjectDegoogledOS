package com.android.contacts.common.extensions;

import android.content.Context;
import android.net.Uri;

class PhoneDirectoryExtenderStub implements PhoneDirectoryExtender {
    PhoneDirectoryExtenderStub() {
    }

    public Uri getContentUri() {
        return null;
    }

    public boolean isEnabled(Context context) {
        return false;
    }
}
