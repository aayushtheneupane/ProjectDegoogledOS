package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.notification.AppNotificationSettings;
import com.android.settings.notification.NotificationBackend;
import com.android.settingslib.applications.ApplicationsState;
import com.havoc.config.center.C1715R;

public class AppNotificationPreferenceController extends AppInfoPreferenceControllerBase {
    private final NotificationBackend mBackend = new NotificationBackend();
    private String mChannelId = null;

    public AppNotificationPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void setParentFragment(AppInfoDashboardFragment appInfoDashboardFragment) {
        super.setParentFragment(appInfoDashboardFragment);
        if (appInfoDashboardFragment != null && appInfoDashboardFragment.getActivity() != null && appInfoDashboardFragment.getActivity().getIntent() != null) {
            this.mChannelId = appInfoDashboardFragment.getActivity().getIntent().getStringExtra(":settings:fragment_args_key");
        }
    }

    public void updateState(Preference preference) {
        preference.setSummary(getNotificationSummary(this.mParent.getAppEntry(), this.mContext, this.mBackend));
    }

    /* access modifiers changed from: protected */
    public Class<? extends SettingsPreferenceFragment> getDetailFragmentClass() {
        return AppNotificationSettings.class;
    }

    /* access modifiers changed from: protected */
    public Bundle getArguments() {
        if (this.mChannelId == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(":settings:fragment_args_key", this.mChannelId);
        return bundle;
    }

    private CharSequence getNotificationSummary(ApplicationsState.AppEntry appEntry, Context context, NotificationBackend notificationBackend) {
        return getNotificationSummary(notificationBackend.loadAppRow(context, context.getPackageManager(), appEntry.info), context);
    }

    public static CharSequence getNotificationSummary(NotificationBackend.AppRow appRow, Context context) {
        if (appRow == null) {
            return "";
        }
        if (appRow.banned) {
            return context.getText(C1715R.string.notifications_disabled);
        }
        int i = appRow.channelCount;
        if (i == 0) {
            return NotificationBackend.getSentSummary(context, appRow.sentByApp, false);
        }
        int i2 = appRow.blockedChannelCount;
        if (i == i2) {
            return context.getText(C1715R.string.notifications_disabled);
        }
        if (i2 == 0) {
            return NotificationBackend.getSentSummary(context, appRow.sentByApp, false);
        }
        Resources resources = context.getResources();
        int i3 = appRow.blockedChannelCount;
        return context.getString(C1715R.string.notifications_enabled_with_info, new Object[]{NotificationBackend.getSentSummary(context, appRow.sentByApp, false), resources.getQuantityString(C1715R.plurals.notifications_categories_off, i3, new Object[]{Integer.valueOf(i3)})});
    }
}
