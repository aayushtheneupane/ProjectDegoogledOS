package com.android.contacts.common.list;

import android.net.Uri;
import com.android.dialer.callintent.CallSpecificAppData;

public interface OnPhoneNumberPickerActionListener {
    void onPickDataUri(Uri uri, boolean z, CallSpecificAppData callSpecificAppData);

    void onPickPhoneNumber(String str, boolean z, CallSpecificAppData callSpecificAppData);
}
