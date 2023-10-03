package com.android.settings.notification;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.notification.NotificationBackend;

public class AppLinkPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin {
    public String getPreferenceKey() {
        return "app_link";
    }

    public AppLinkPreferenceController(Context context) {
        super(context, (NotificationBackend) null);
    }

    public boolean isAvailable() {
        if (super.isAvailable() && this.mAppRow.settingsIntent != null) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow != null) {
            preference.setIntent(appRow.settingsIntent);
        }
    }
}
