package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;

public class BasebandVersionPreferenceController extends BasePreferenceController {
    static final String BASEBAND_PROPERTY = "gsm.version.baseband";

    public BasebandVersionPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return !Utils.isWifiOnly(this.mContext) ? 0 : 3;
    }

    public CharSequence getSummary() {
        return BasebandFormatter.getFormattedBaseband(SystemProperties.get(BASEBAND_PROPERTY, this.mContext.getString(C1715R.string.device_info_default)));
    }
}
