package com.android.settings.deviceinfo.legal;

import android.content.Context;
import android.content.Intent;

public class TermsPreferenceController extends LegalPreferenceController {
    private static final Intent INTENT = new Intent("android.settings.TERMS");

    public TermsPreferenceController(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public Intent getIntent() {
        return INTENT;
    }
}
