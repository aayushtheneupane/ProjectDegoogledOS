package com.android.settings.display;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class FontSizePreferenceController extends BasePreferenceController {
    public int getAvailabilityStatus() {
        return 1;
    }

    public FontSizePreferenceController(Context context, String str) {
        super(context, str);
    }

    public CharSequence getSummary() {
        float f = Settings.System.getFloat(this.mContext.getContentResolver(), "font_scale", 1.0f);
        Resources resources = this.mContext.getResources();
        return resources.getStringArray(C1715R.array.entries_font_size_percent)[ToggleFontSizePreferenceFragment.fontSizeValueToIndex(f, resources.getStringArray(C1715R.array.entryvalues_font_size))];
    }
}
