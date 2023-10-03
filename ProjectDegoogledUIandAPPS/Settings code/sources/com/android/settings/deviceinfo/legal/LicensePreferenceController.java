package com.android.settings.deviceinfo.legal;

import android.content.Context;
import android.content.Intent;

public class LicensePreferenceController extends LegalPreferenceController {
    private static final Intent INTENT = new Intent("android.settings.LICENSE");

    public LicensePreferenceController(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public Intent getIntent() {
        return INTENT;
    }
}
