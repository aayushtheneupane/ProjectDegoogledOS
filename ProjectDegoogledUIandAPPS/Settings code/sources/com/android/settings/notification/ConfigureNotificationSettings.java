package com.android.settings.notification;

import android.app.Activity;
import android.app.Application;
import android.app.usage.IUsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.settings.RingtonePreference;
import com.android.settings.core.OnActivityResultListener;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigureNotificationSettings extends DashboardFragment implements OnActivityResultListener {
    static final String KEY_LOCKSCREEN = "lock_screen_notifications";
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.configure_notification_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return ConfigureNotificationSettings.buildPreferenceControllers(context, (Application) null, (Fragment) null);
        }

        public List<String> getNonIndexableKeys(Context context) {
            return super.getNonIndexableKeys(context);
        }
    };
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() {
        public SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return new SummaryProvider(activity, summaryLoader);
        }
    };
    private RingtonePreference mRequestPreference;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "ConfigNotiSettings";
    }

    public int getMetricsCategory() {
        return 337;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.configure_notification_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        FragmentActivity activity = getActivity();
        return buildPreferenceControllers(context, activity != null ? activity.getApplication() : null, this);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Application application, Fragment fragment) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RecentNotifyingAppsPreferenceController(context, new NotificationBackend(), IUsageStatsManager.Stub.asInterface(ServiceManager.getService("usagestats")), (UserManager) context.getSystemService(UserManager.class), application, fragment));
        arrayList.add(new ShowOnLockScreenNotificationPreferenceController(context, KEY_LOCKSCREEN));
        arrayList.add(new NotificationRingtonePreferenceController(context) {
            public String getPreferenceKey() {
                return "notification_default_ringtone";
            }
        });
        return arrayList;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Bundle arguments = getArguments();
        if (preferenceScreen != null && arguments != null && !TextUtils.isEmpty(arguments.getString(":settings:fragment_args_key"))) {
            PreferenceCategory preferenceCategory = (PreferenceCategory) preferenceScreen.findPreference("configure_notifications_advanced");
            preferenceCategory.setInitialExpandedChildrenCount(Integer.MAX_VALUE);
            scrollToPreference(preferenceCategory);
        }
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (!(preference instanceof RingtonePreference)) {
            return super.onPreferenceTreeClick(preference);
        }
        this.mRequestPreference = (RingtonePreference) preference;
        RingtonePreference ringtonePreference = this.mRequestPreference;
        ringtonePreference.onPrepareRingtonePickerIntent(ringtonePreference.getIntent());
        getActivity().startActivityForResultAsUser(this.mRequestPreference.getIntent(), 200, (Bundle) null, UserHandle.of(this.mRequestPreference.getUserId()));
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        RingtonePreference ringtonePreference = this.mRequestPreference;
        if (ringtonePreference != null) {
            ringtonePreference.onActivityResult(i, i2, intent);
            this.mRequestPreference = null;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        RingtonePreference ringtonePreference = this.mRequestPreference;
        if (ringtonePreference != null) {
            bundle.putString("selected_preference", ringtonePreference.getKey());
        }
    }

    static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private NotificationBackend mBackend = new NotificationBackend();
        private final Context mContext;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
        }

        /* access modifiers changed from: protected */
        public void setBackend(NotificationBackend notificationBackend) {
            this.mBackend = notificationBackend;
        }

        public void setListening(boolean z) {
            if (z) {
                int blockedAppCount = this.mBackend.getBlockedAppCount();
                if (blockedAppCount == 0) {
                    this.mSummaryLoader.setSummary(this, this.mContext.getText(C1715R.string.app_notification_listing_summary_zero));
                    return;
                }
                this.mSummaryLoader.setSummary(this, this.mContext.getResources().getQuantityString(C1715R.plurals.app_notification_listing_summary_others, blockedAppCount, new Object[]{Integer.valueOf(blockedAppCount)}));
            }
        }
    }
}
