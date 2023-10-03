package com.android.settings.notification;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.notification.NotificationBackend;
import com.havoc.config.center.C1715R;

public class DeletedChannelsPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin {
    public String getPreferenceKey() {
        return "deleted";
    }

    public DeletedChannelsPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    public boolean isAvailable() {
        if (!super.isAvailable() || this.mChannel != null || hasValidGroup()) {
            return false;
        }
        NotificationBackend notificationBackend = this.mBackend;
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (notificationBackend.getDeletedChannelCount(appRow.pkg, appRow.uid) > 0) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow != null) {
            int deletedChannelCount = this.mBackend.getDeletedChannelCount(appRow.pkg, appRow.uid);
            preference.setTitle((CharSequence) this.mContext.getResources().getQuantityString(C1715R.plurals.deleted_channels, deletedChannelCount, new Object[]{Integer.valueOf(deletedChannelCount)}));
        }
        preference.setSelectable(false);
    }
}
