package com.android.settings.notification;

import android.app.NotificationChannel;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.notification.NotificationBackend;
import com.havoc.config.center.C1715R;

public class BubbleSummaryPreferenceController extends NotificationPreferenceController {
    static final int SYSTEM_WIDE_OFF = 0;
    static final int SYSTEM_WIDE_ON = 1;

    public String getPreferenceKey() {
        return "bubble_link_pref";
    }

    public BubbleSummaryPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    public boolean isAvailable() {
        if (!super.isAvailable()) {
            return false;
        }
        if (this.mAppRow == null && this.mChannel == null) {
            return false;
        }
        if (this.mChannel == null) {
            return isGloballyEnabled();
        }
        if (!isGloballyEnabled()) {
            return false;
        }
        if (isDefaultChannel()) {
            return true;
        }
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null || !appRow.allowBubbles) {
            return false;
        }
        return true;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mAppRow != null) {
            Bundle bundle = new Bundle();
            bundle.putString("package", this.mAppRow.pkg);
            bundle.putInt("uid", this.mAppRow.uid);
            preference.setIntent(new SubSettingLauncher(this.mContext).setDestination(AppBubbleNotificationSettings.class.getName()).setArguments(bundle).setSourceMetricsCategory(72).toIntent());
        }
    }

    public CharSequence getSummary() {
        NotificationBackend.AppRow appRow = this.mAppRow;
        boolean z = false;
        if (appRow != null) {
            NotificationChannel notificationChannel = this.mChannel;
            boolean z2 = true;
            if (notificationChannel == null ? !appRow.allowBubbles || !isGloballyEnabled() : !notificationChannel.canBubble() || !isGloballyEnabled()) {
                z2 = false;
            }
            z = false | z2;
        }
        return this.mContext.getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text);
    }

    private boolean isGloballyEnabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "notification_bubbles", 0) == 1;
    }
}
