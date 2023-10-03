package com.android.settings.deviceinfo.legal;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class WallpaperAttributionsPreferenceController extends BasePreferenceController {
    public WallpaperAttributionsPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_wallpaper_attribution) ? 0 : 3;
    }
}
