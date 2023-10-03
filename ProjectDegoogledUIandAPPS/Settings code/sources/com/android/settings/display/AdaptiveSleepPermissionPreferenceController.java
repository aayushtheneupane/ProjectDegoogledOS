package com.android.settings.display;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;

public class AdaptiveSleepPermissionPreferenceController extends BasePreferenceController {
    static final String PREF_NAME = "adaptive_sleep_permission";
    private final Intent mIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");

    public int getAvailabilityStatus() {
        return 1;
    }

    public AdaptiveSleepPermissionPreferenceController(Context context, String str) {
        super(context, str);
        String attentionServicePackageName = context.getPackageManager().getAttentionServicePackageName();
        Intent intent = this.mIntent;
        intent.setData(Uri.parse("package:" + attentionServicePackageName));
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(getPreferenceKey(), preference.getKey())) {
            return super.handlePreferenceTreeClick(preference);
        }
        this.mContext.startActivity(this.mIntent);
        return true;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (TextUtils.equals(getPreferenceKey(), preference.getKey())) {
            preference.setVisible(!AdaptiveSleepPreferenceController.hasSufficientPermission(this.mContext.getPackageManager()));
        }
    }
}
