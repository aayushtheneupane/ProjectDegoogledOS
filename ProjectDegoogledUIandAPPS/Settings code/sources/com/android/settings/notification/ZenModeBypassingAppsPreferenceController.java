package com.android.settings.notification;

import android.content.Context;
import android.os.UserHandle;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class ZenModeBypassingAppsPreferenceController extends AbstractZenModePreferenceController {
    private NotificationBackend mNotificationBackend = new NotificationBackend();

    public ZenModeBypassingAppsPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, "zen_mode_bypassing_apps", lifecycle);
    }

    public boolean isAvailable() {
        return this.mNotificationBackend.getNumAppsBypassingDnd(UserHandle.getCallingUserId()) != 0;
    }

    public String getSummary() {
        int numAppsBypassingDnd = this.mNotificationBackend.getNumAppsBypassingDnd(UserHandle.getCallingUserId());
        return this.mContext.getResources().getQuantityString(C1715R.plurals.zen_mode_bypassing_apps_subtext, numAppsBypassingDnd, new Object[]{Integer.valueOf(numAppsBypassingDnd)});
    }
}
