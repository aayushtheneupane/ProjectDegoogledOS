package com.android.settings.datetime.timezone;

import android.content.Context;
import com.havoc.config.center.C1715R;

public class FixedOffsetPreferenceController extends BaseTimeZonePreferenceController {
    private static final String PREFERENCE_KEY = "fixed_offset";
    private TimeZoneInfo mTimeZoneInfo;

    public FixedOffsetPreferenceController(Context context) {
        super(context, PREFERENCE_KEY);
    }

    public CharSequence getSummary() {
        TimeZoneInfo timeZoneInfo = this.mTimeZoneInfo;
        if (timeZoneInfo == null) {
            return "";
        }
        String standardName = timeZoneInfo.getStandardName();
        if (standardName == null) {
            return this.mTimeZoneInfo.getGmtOffset();
        }
        return SpannableUtil.getResourcesText(this.mContext.getResources(), C1715R.string.zone_info_offset_and_name, this.mTimeZoneInfo.getGmtOffset(), standardName);
    }

    public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo) {
        this.mTimeZoneInfo = timeZoneInfo;
    }

    public TimeZoneInfo getTimeZoneInfo() {
        return this.mTimeZoneInfo;
    }
}
