package com.android.settings.applications.appinfo;

import android.content.Context;

public class DefaultHomeShortcutPreferenceController extends DefaultAppShortcutPreferenceControllerBase {
    private static final String KEY = "default_home";

    public DefaultHomeShortcutPreferenceController(Context context, String str) {
        super(context, KEY, "android.app.role.HOME", str);
    }
}
