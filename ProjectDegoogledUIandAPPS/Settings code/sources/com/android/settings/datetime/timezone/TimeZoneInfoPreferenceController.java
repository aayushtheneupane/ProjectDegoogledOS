package com.android.settings.datetime.timezone;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.icu.util.TimeZoneTransition;
import androidx.preference.Preference;
import com.havoc.config.center.C1715R;
import java.util.Date;

public class TimeZoneInfoPreferenceController extends BaseTimeZonePreferenceController {
    private static final String PREFERENCE_KEY = "footer_preference";
    Date mDate;
    private final DateFormat mDateFormat = DateFormat.getDateInstance(1);
    private TimeZoneInfo mTimeZoneInfo;

    public int getAvailabilityStatus() {
        return 0;
    }

    public TimeZoneInfoPreferenceController(Context context) {
        super(context, PREFERENCE_KEY);
        this.mDateFormat.setContext(DisplayContext.CAPITALIZATION_NONE);
        this.mDate = new Date();
    }

    public void updateState(Preference preference) {
        TimeZoneInfo timeZoneInfo = this.mTimeZoneInfo;
        preference.setTitle(timeZoneInfo == null ? "" : formatInfo(timeZoneInfo));
        preference.setVisible(this.mTimeZoneInfo != null);
    }

    public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo) {
        this.mTimeZoneInfo = timeZoneInfo;
    }

    public TimeZoneInfo getTimeZoneInfo() {
        return this.mTimeZoneInfo;
    }

    private CharSequence formatOffsetAndName(TimeZoneInfo timeZoneInfo) {
        String genericName = timeZoneInfo.getGenericName();
        if (genericName == null) {
            if (timeZoneInfo.getTimeZone().inDaylightTime(this.mDate)) {
                genericName = timeZoneInfo.getDaylightName();
            } else {
                genericName = timeZoneInfo.getStandardName();
            }
        }
        if (genericName == null) {
            return timeZoneInfo.getGmtOffset().toString();
        }
        return SpannableUtil.getResourcesText(this.mContext.getResources(), C1715R.string.zone_info_offset_and_name, timeZoneInfo.getGmtOffset(), genericName);
    }

    private CharSequence formatInfo(TimeZoneInfo timeZoneInfo) {
        CharSequence formatOffsetAndName = formatOffsetAndName(timeZoneInfo);
        TimeZone timeZone = timeZoneInfo.getTimeZone();
        if (!timeZone.observesDaylightTime()) {
            return this.mContext.getString(C1715R.string.zone_info_footer_no_dst, new Object[]{formatOffsetAndName});
        }
        TimeZoneTransition findNextDstTransition = findNextDstTransition(timeZone);
        if (findNextDstTransition == null) {
            return null;
        }
        boolean z = findNextDstTransition.getTo().getDSTSavings() != 0;
        String daylightName = z ? timeZoneInfo.getDaylightName() : timeZoneInfo.getStandardName();
        if (daylightName == null) {
            if (z) {
                daylightName = this.mContext.getString(C1715R.string.zone_time_type_dst);
            } else {
                daylightName = this.mContext.getString(C1715R.string.zone_time_type_standard);
            }
        }
        Calendar instance = Calendar.getInstance(timeZone);
        instance.setTimeInMillis(findNextDstTransition.getTime());
        return SpannableUtil.getResourcesText(this.mContext.getResources(), C1715R.string.zone_info_footer, formatOffsetAndName, daylightName, this.mDateFormat.format(instance));
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0013 A[LOOP:0: B:4:0x0013->B:7:0x002e, LOOP_START, PHI: r0 
      PHI: (r0v3 android.icu.util.TimeZoneTransition) = (r0v2 android.icu.util.TimeZoneTransition), (r0v6 android.icu.util.TimeZoneTransition) binds: [B:3:0x0006, B:7:0x002e] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.icu.util.TimeZoneTransition findNextDstTransition(android.icu.util.TimeZone r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof android.icu.util.BasicTimeZone
            if (r0 != 0) goto L_0x0006
            r3 = 0
            return r3
        L_0x0006:
            android.icu.util.BasicTimeZone r4 = (android.icu.util.BasicTimeZone) r4
            java.util.Date r3 = r3.mDate
            long r0 = r3.getTime()
            r3 = 0
            android.icu.util.TimeZoneTransition r0 = r4.getNextTransition(r0, r3)
        L_0x0013:
            android.icu.util.TimeZoneRule r1 = r0.getTo()
            int r1 = r1.getDSTSavings()
            android.icu.util.TimeZoneRule r2 = r0.getFrom()
            int r2 = r2.getDSTSavings()
            if (r1 == r2) goto L_0x0026
            goto L_0x0030
        L_0x0026:
            long r0 = r0.getTime()
            android.icu.util.TimeZoneTransition r0 = r4.getNextTransition(r0, r3)
            if (r0 != 0) goto L_0x0013
        L_0x0030:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.datetime.timezone.TimeZoneInfoPreferenceController.findNextDstTransition(android.icu.util.TimeZone):android.icu.util.TimeZoneTransition");
    }
}
