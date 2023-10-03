package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class BuildDatePreferenceController extends BasePreferenceController {
    static final String BUILD_DATE_PROPERTY = "ro.build.date";

    public BuildDatePreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return !TextUtils.isEmpty(SystemProperties.get(BUILD_DATE_PROPERTY)) ? 0 : 3;
    }

    public CharSequence getSummary() {
        return SystemProperties.get(BUILD_DATE_PROPERTY, this.mContext.getString(C1715R.string.device_info_default));
    }
}
