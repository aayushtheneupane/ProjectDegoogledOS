package com.android.settings.datetime.timezone;

import android.content.Context;
import androidx.preference.Preference;
import com.havoc.config.center.C1715R;

public class RegionZonePreferenceController extends BaseTimeZonePreferenceController {
    private static final String PREFERENCE_KEY = "region_zone";
    private boolean mIsClickable;
    private TimeZoneInfo mTimeZoneInfo;

    public int getAvailabilityStatus() {
        return 0;
    }

    public RegionZonePreferenceController(Context context) {
        super(context, PREFERENCE_KEY);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(isClickable());
    }

    public CharSequence getSummary() {
        if (this.mTimeZoneInfo == null) {
            return "";
        }
        return SpannableUtil.getResourcesText(this.mContext.getResources(), C1715R.string.zone_info_exemplar_location_and_offset, this.mTimeZoneInfo.getExemplarLocation(), this.mTimeZoneInfo.getGmtOffset());
    }

    public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo) {
        this.mTimeZoneInfo = timeZoneInfo;
    }

    public TimeZoneInfo getTimeZoneInfo() {
        return this.mTimeZoneInfo;
    }

    public void setClickable(boolean z) {
        this.mIsClickable = z;
    }

    public boolean isClickable() {
        return this.mIsClickable;
    }
}
