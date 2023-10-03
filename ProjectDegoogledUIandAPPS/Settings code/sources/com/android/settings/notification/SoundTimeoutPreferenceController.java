package com.android.settings.notification;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.RestrictedListPreference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.notification.NotificationBackend;

public class SoundTimeoutPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    public String getPreferenceKey() {
        return "sound_timeout";
    }

    public SoundTimeoutPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    public boolean isAvailable() {
        if (super.isAvailable() && this.mAppRow != null) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        if (this.mAppRow != null) {
            RestrictedListPreference restrictedListPreference = (RestrictedListPreference) preference;
            restrictedListPreference.setDisabledByAdmin(this.mAdmin);
            restrictedListPreference.setSummary("%s");
            restrictedListPreference.setValue(Long.toString(this.mAppRow.soundTimeout));
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null) {
            return true;
        }
        appRow.soundTimeout = Long.valueOf((String) obj).longValue();
        NotificationBackend notificationBackend = this.mBackend;
        NotificationBackend.AppRow appRow2 = this.mAppRow;
        notificationBackend.setNotificationSoundTimeout(appRow2.pkg, appRow2.uid, appRow2.soundTimeout);
        return true;
    }
}
