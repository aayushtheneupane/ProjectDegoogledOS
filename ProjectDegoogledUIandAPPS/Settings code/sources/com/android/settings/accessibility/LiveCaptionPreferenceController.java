package com.android.settings.accessibility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import java.util.List;

public class LiveCaptionPreferenceController extends BasePreferenceController {
    static final Intent LIVE_CAPTION_INTENT = new Intent("com.android.settings.action.live_caption");
    private final PackageManager mPackageManager;

    public LiveCaptionPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
    }

    public int getAvailabilityStatus() {
        List<ResolveInfo> queryIntentActivities = this.mPackageManager.queryIntentActivities(LIVE_CAPTION_INTENT, 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
            return 3;
        }
        return 0;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setIntent(LIVE_CAPTION_INTENT);
    }
}
