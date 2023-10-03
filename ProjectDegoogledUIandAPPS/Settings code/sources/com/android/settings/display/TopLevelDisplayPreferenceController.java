package com.android.settings.display;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class TopLevelDisplayPreferenceController extends BasePreferenceController {
    public TopLevelDisplayPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_top_level_display) ? 0 : 3;
    }

    public CharSequence getSummary() {
        WallpaperPreferenceController wallpaperPreferenceController = new WallpaperPreferenceController(this.mContext, "dummy_key");
        if (!wallpaperPreferenceController.isAvailable()) {
            return this.mContext.getText(C1715R.string.display_dashboard_nowallpaper_summary);
        }
        return this.mContext.getText(wallpaperPreferenceController.areStylesAvailable() ? C1715R.string.display_dashboard_summary_with_style : C1715R.string.display_dashboard_summary);
    }
}
