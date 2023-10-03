package com.android.settings.notification;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.settings.notification.GlobalBubblePermissionObserverMixin;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class AppBubbleNotificationSettings extends NotificationSettingsBase implements GlobalBubblePermissionObserverMixin.Listener {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return false;
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return new ArrayList(AppBubbleNotificationSettings.getPreferenceControllers(context, (AppBubbleNotificationSettings) null));
        }
    };
    private GlobalBubblePermissionObserverMixin mObserverMixin;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "AppBubNotiSettings";
    }

    public int getMetricsCategory() {
        return 1700;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.app_bubble_notification_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mControllers = getPreferenceControllers(context, this);
        return new ArrayList(this.mControllers);
    }

    protected static List<NotificationPreferenceController> getPreferenceControllers(Context context, AppBubbleNotificationSettings appBubbleNotificationSettings) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HeaderPreferenceController(context, appBubbleNotificationSettings));
        arrayList.add(new BubblePreferenceController(context, appBubbleNotificationSettings != null ? appBubbleNotificationSettings.getChildFragmentManager() : null, new NotificationBackend(), true));
        return arrayList;
    }

    public void onGlobalBubblePermissionChanged() {
        updatePreferenceStates();
    }

    public void onResume() {
        super.onResume();
        if (this.mUid < 0 || TextUtils.isEmpty(this.mPkg) || this.mPkgInfo == null) {
            Log.w("AppBubNotiSettings", "Missing package or uid or packageinfo");
            finish();
            return;
        }
        for (NotificationPreferenceController next : this.mControllers) {
            next.onResume(this.mAppRow, this.mChannel, this.mChannelGroup, this.mSuspendedAppsAdmin);
            next.displayPreference(getPreferenceScreen());
        }
        updatePreferenceStates();
        this.mObserverMixin = new GlobalBubblePermissionObserverMixin(getContext(), this);
        this.mObserverMixin.onStart();
    }

    public void onPause() {
        this.mObserverMixin.onStop();
        super.onPause();
    }
}
