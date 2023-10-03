package com.android.settings.wallpaper;

import com.android.settings.dashboard.DashboardFragment;
import com.havoc.config.center.C1715R;

public class WallpaperTypeSettings extends DashboardFragment {
    public int getHelpResource() {
        return C1715R.string.help_uri_wallpaper;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "WallpaperTypeSettings";
    }

    public int getMetricsCategory() {
        return 101;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.wallpaper_settings;
    }
}
