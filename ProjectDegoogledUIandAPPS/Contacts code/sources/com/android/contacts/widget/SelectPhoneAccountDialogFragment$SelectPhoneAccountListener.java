package com.android.contacts.widget;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.telecom.PhoneAccountHandle;

public class SelectPhoneAccountDialogFragment$SelectPhoneAccountListener extends ResultReceiver {
    public void onDialogDismissed() {
    }

    public void onPhoneAccountSelected(PhoneAccountHandle phoneAccountHandle, boolean z) {
    }

    public SelectPhoneAccountDialogFragment$SelectPhoneAccountListener() {
        super(new Handler());
    }

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        if (i == 1) {
            onPhoneAccountSelected((PhoneAccountHandle) bundle.getParcelable("extra_selected_account_handle"), bundle.getBoolean("extra_set_default"));
        } else if (i == 2) {
            onDialogDismissed();
        }
    }
}
