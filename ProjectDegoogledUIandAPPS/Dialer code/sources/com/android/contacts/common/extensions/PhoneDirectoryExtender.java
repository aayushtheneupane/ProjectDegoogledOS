package com.android.contacts.common.extensions;

import android.content.Context;
import android.net.Uri;

public interface PhoneDirectoryExtender {
    Uri getContentUri();

    boolean isEnabled(Context context);
}
