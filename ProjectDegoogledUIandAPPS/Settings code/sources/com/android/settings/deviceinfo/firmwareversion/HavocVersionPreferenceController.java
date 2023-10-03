package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class HavocVersionPreferenceController extends BasePreferenceController {
    static final String HAVOC_VERSION_PROPERTY = "ro.havoc.version";

    public HavocVersionPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return !TextUtils.isEmpty(SystemProperties.get(HAVOC_VERSION_PROPERTY)) ? 0 : 3;
    }

    public CharSequence getSummary() {
        return SystemProperties.get(HAVOC_VERSION_PROPERTY, this.mContext.getString(C1715R.string.device_info_default));
    }
}
