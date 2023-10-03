package com.android.settings.notification;

import android.content.Context;
import android.text.TextUtils;
import com.havoc.config.center.C1715R;

public class MediaVolumePreferenceController extends VolumeSeekBarPreferenceController {
    private static final String KEY_MEDIA_VOLUME = "media_volume";

    public int getAudioStream() {
        return 3;
    }

    public int getMuteIcon() {
        return C1715R.C1717drawable.ic_media_stream_off;
    }

    public String getPreferenceKey() {
        return KEY_MEDIA_VOLUME;
    }

    public boolean useDynamicSliceSummary() {
        return true;
    }

    public MediaVolumePreferenceController(Context context) {
        super(context, KEY_MEDIA_VOLUME);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_media_volume) ? 0 : 3;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_MEDIA_VOLUME);
    }
}
