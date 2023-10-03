package com.android.settings.notification;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class GentleNotificationsPreferenceController extends BasePreferenceController {

    /* renamed from: ON */
    static final int f53ON = 1;
    private NotificationBackend mBackend = new NotificationBackend();

    public int getAvailabilityStatus() {
        return 0;
    }

    public GentleNotificationsPreferenceController(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: package-private */
    public void setBackend(NotificationBackend notificationBackend) {
        this.mBackend = notificationBackend;
    }

    public CharSequence getSummary() {
        boolean showOnLockscreen = showOnLockscreen();
        boolean showOnStatusBar = showOnStatusBar();
        if (showOnLockscreen) {
            if (showOnStatusBar) {
                return this.mContext.getString(C1715R.string.gentle_notifications_display_summary_shade_status_lock);
            }
            return this.mContext.getString(C1715R.string.gentle_notifications_display_summary_shade_lock);
        } else if (showOnStatusBar) {
            return this.mContext.getString(C1715R.string.gentle_notifications_display_summary_shade_status);
        } else {
            return this.mContext.getString(C1715R.string.gentle_notifications_display_summary_shade);
        }
    }

    private boolean showOnLockscreen() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "lock_screen_show_silent_notifications", 1) == 1;
    }

    private boolean showOnStatusBar() {
        return !this.mBackend.shouldHideSilentStatusBarIcons(this.mContext);
    }
}
