package com.android.settings.notification;

import android.app.NotificationChannel;
import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.notification.NotificationSettingsBase;
import com.android.settingslib.RestrictedSwitchPreference;

public class MinImportancePreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private NotificationSettingsBase.ImportanceListener mImportanceListener;

    public String getPreferenceKey() {
        return "min_importance";
    }

    public MinImportancePreferenceController(Context context, NotificationSettingsBase.ImportanceListener importanceListener, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
        this.mImportanceListener = importanceListener;
    }

    public boolean isAvailable() {
        if (super.isAvailable() && this.mChannel != null && !isDefaultChannel() && this.mChannel.getImportance() <= 2) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        NotificationChannel notificationChannel;
        if (this.mAppRow != null && (notificationChannel = this.mChannel) != null) {
            boolean z = false;
            preference.setEnabled(this.mAdmin == null && !notificationChannel.isImportanceLockedByOEM());
            RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
            if (this.mChannel.getImportance() == 1) {
                z = true;
            }
            restrictedSwitchPreference.setChecked(z);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mChannel != null) {
            this.mChannel.setImportance(((Boolean) obj).booleanValue() ? 1 : 2);
            this.mChannel.lockFields(4);
            saveChannel();
            this.mImportanceListener.onImportanceChanged();
        }
        return true;
    }
}
