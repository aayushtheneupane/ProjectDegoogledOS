package com.android.settings.deviceinfo.legal;

import android.content.Context;
import android.content.Intent;

public class CopyrightPreferenceController extends LegalPreferenceController {
    private static final Intent INTENT = new Intent("android.settings.COPYRIGHT");

    public CopyrightPreferenceController(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public Intent getIntent() {
        return INTENT;
    }
}
