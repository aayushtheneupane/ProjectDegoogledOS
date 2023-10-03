package com.android.settings.applications.appinfo;

import android.content.Context;

public class DefaultBrowserShortcutPreferenceController extends DefaultAppShortcutPreferenceControllerBase {
    private static final String KEY = "default_browser";

    public DefaultBrowserShortcutPreferenceController(Context context, String str) {
        super(context, KEY, "android.app.role.BROWSER", str);
    }
}
