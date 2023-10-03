package com.android.settings.notification;

import android.content.Context;
import android.text.TextUtils;
import com.havoc.config.center.C1715R;

public class AlarmVolumePreferenceController extends VolumeSeekBarPreferenceController {
    private static final String KEY_ALARM_VOLUME = "alarm_volume";

    public int getAudioStream() {
        return 4;
    }

    public int getMuteIcon() {
        return 17302291;
    }

    public String getPreferenceKey() {
        return KEY_ALARM_VOLUME;
    }

    public boolean useDynamicSliceSummary() {
        return true;
    }

    public AlarmVolumePreferenceController(Context context) {
        super(context, KEY_ALARM_VOLUME);
    }

    public int getAvailabilityStatus() {
        return (!this.mContext.getResources().getBoolean(C1715R.bool.config_show_alarm_volume) || this.mHelper.isSingleVolume()) ? 3 : 0;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_ALARM_VOLUME);
    }
}
