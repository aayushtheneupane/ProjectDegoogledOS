package com.android.contacts.list;

import android.net.Uri;

public interface OnContactBrowserActionListener {
    void onInvalidSelection();

    void onSelectionChange();

    void onViewContactAction(int i, Uri uri, boolean z);
}
