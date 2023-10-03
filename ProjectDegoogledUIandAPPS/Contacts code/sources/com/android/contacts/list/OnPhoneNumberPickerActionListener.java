package com.android.contacts.list;

import android.content.Intent;
import android.net.Uri;

public interface OnPhoneNumberPickerActionListener {
    void onHomeInActionBarSelected();

    void onPickDataUri(Uri uri, boolean z, int i);

    void onPickPhoneNumber(String str, boolean z, int i);

    void onShortcutIntentCreated(Intent intent);
}
