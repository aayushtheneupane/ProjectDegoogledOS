package com.android.settings.notification;

import android.content.Context;
import android.text.TextUtils;
import com.havoc.config.center.C1715R;

public class NotificationVolumePreferenceController extends RingVolumePreferenceController {
    private static final String KEY_NOTIFICATION_VOLUME = "notification_volume";

    public int getAudioStream() {
        return 5;
    }

    public int getMuteIcon() {
        return C1715R.C1717drawable.ic_audio_notifications_off_24dp;
    }

    public String getPreferenceKey() {
        return KEY_NOTIFICATION_VOLUME;
    }

    public NotificationVolumePreferenceController(Context context) {
        super(context, KEY_NOTIFICATION_VOLUME);
    }

    public int getAvailabilityStatus() {
        return (!this.mContext.getResources().getBoolean(C1715R.bool.config_show_notification_volume) || this.mHelper.isSingleVolume()) ? 3 : 0;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_NOTIFICATION_VOLUME);
    }

    /* access modifiers changed from: protected */
    public void updatePreferenceIcon() {
        VolumeSeekBarPreference volumeSeekBarPreference = this.mPreference;
        if (volumeSeekBarPreference != null) {
            int i = this.mRingerMode;
            if (i == 1) {
                this.mMuteIcon = C1715R.C1717drawable.ic_volume_ringer_vibrate;
                volumeSeekBarPreference.showIcon(C1715R.C1717drawable.ic_volume_ringer_vibrate);
            } else if (i == 0) {
                this.mMuteIcon = C1715R.C1717drawable.ic_audio_notifications_off_24dp;
                volumeSeekBarPreference.showIcon(C1715R.C1717drawable.ic_audio_notifications_off_24dp);
            } else {
                volumeSeekBarPreference.showIcon(C1715R.C1717drawable.ic_audio_notifications);
            }
        }
    }
}
