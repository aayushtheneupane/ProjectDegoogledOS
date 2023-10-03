package com.android.settings.applications.appinfo;

import android.content.Context;

public class DefaultEmergencyShortcutPreferenceController extends DefaultAppShortcutPreferenceControllerBase {
    private static final String KEY = "default_emergency_app";

    public DefaultEmergencyShortcutPreferenceController(Context context, String str) {
        super(context, KEY, "android.app.role.EMERGENCY", str);
    }
}
