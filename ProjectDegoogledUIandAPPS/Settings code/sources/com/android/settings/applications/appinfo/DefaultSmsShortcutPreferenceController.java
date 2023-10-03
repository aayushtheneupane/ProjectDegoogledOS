package com.android.settings.applications.appinfo;

import android.content.Context;

public class DefaultSmsShortcutPreferenceController extends DefaultAppShortcutPreferenceControllerBase {
    private static final String KEY = "default_sms_app";

    public DefaultSmsShortcutPreferenceController(Context context, String str) {
        super(context, KEY, "android.app.role.SMS", str);
    }
}
