package com.android.settings.notification;

import android.app.NotificationChannel;
import android.content.Context;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.notification.NotificationSettingsBase;
import com.android.settingslib.RestrictedSwitchPreference;

public class AllowSoundPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private NotificationSettingsBase.ImportanceListener mImportanceListener;

    public String getPreferenceKey() {
        return "allow_sound";
    }

    public AllowSoundPreferenceController(Context context, NotificationSettingsBase.ImportanceListener importanceListener, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
        this.mImportanceListener = importanceListener;
    }

    public boolean isAvailable() {
        NotificationChannel notificationChannel;
        if (super.isAvailable() && (notificationChannel = this.mChannel) != null && "miscellaneous".equals(notificationChannel.getId())) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        if (this.mChannel != null) {
            RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
            restrictedSwitchPreference.setDisabledByAdmin(this.mAdmin);
            boolean z = true;
            restrictedSwitchPreference.setEnabled(!restrictedSwitchPreference.isDisabledByAdmin());
            if (this.mChannel.getImportance() < 3 && this.mChannel.getImportance() != -1000) {
                z = false;
            }
            restrictedSwitchPreference.setChecked(z);
            return;
        }
        Log.i("AllowSoundPrefContr", "tried to updatestate on a null channel?!");
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mChannel == null) {
            return true;
        }
        this.mChannel.setImportance(((Boolean) obj).booleanValue() ? -1000 : 2);
        this.mChannel.lockFields(4);
        saveChannel();
        this.mImportanceListener.onImportanceChanged();
        return true;
    }
}
