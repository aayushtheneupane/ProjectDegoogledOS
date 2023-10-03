package com.android.settings.notification;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.havoc.config.center.C1715R;

public class NotificationsOffPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin {
    public String getPreferenceKey() {
        return "block_desc";
    }

    public NotificationsOffPreferenceController(Context context) {
        super(context, (NotificationBackend) null);
    }

    public boolean isAvailable() {
        if (this.mAppRow == null) {
            return false;
        }
        return !super.isAvailable();
    }

    public void updateState(Preference preference) {
        if (this.mAppRow != null) {
            if (this.mChannel != null) {
                preference.setTitle((int) C1715R.string.channel_notifications_off_desc);
            } else if (this.mChannelGroup != null) {
                preference.setTitle((int) C1715R.string.channel_group_notifications_off_desc);
            } else {
                preference.setTitle((int) C1715R.string.app_notifications_off_desc);
            }
        }
        preference.setSelectable(false);
    }
}
