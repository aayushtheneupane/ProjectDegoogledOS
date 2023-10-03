package com.android.settings.applications.appinfo;

import android.content.Context;

public class DefaultPhoneShortcutPreferenceController extends DefaultAppShortcutPreferenceControllerBase {
    private static final String KEY = "default_phone_app";

    public DefaultPhoneShortcutPreferenceController(Context context, String str) {
        super(context, KEY, "android.app.role.DIALER", str);
    }
}
