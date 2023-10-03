package com.android.settings.accounts;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.users.AutoSyncDataPreferenceController;
import com.android.settings.users.AutoSyncPersonalDataPreferenceController;
import com.android.settings.users.AutoSyncWorkDataPreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountDashboardFragment extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.accounts_dashboard_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return AccountDashboardFragment.buildPreferenceControllers(context, (SettingsPreferenceFragment) null, (String[]) null);
        }
    };

    public int getHelpResource() {
        return C1715R.string.help_url_user_and_account_dashboard;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "AccountDashboardFrag";
    }

    public int getMetricsCategory() {
        return 8;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.accounts_dashboard_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, this, getIntent().getStringArrayExtra("authorities"));
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, SettingsPreferenceFragment settingsPreferenceFragment, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        AccountPreferenceController accountPreferenceController = new AccountPreferenceController(context, settingsPreferenceFragment, strArr);
        if (settingsPreferenceFragment != null) {
            settingsPreferenceFragment.getSettingsLifecycle().addObserver(accountPreferenceController);
        }
        arrayList.add(accountPreferenceController);
        arrayList.add(new AutoSyncDataPreferenceController(context, settingsPreferenceFragment));
        arrayList.add(new AutoSyncPersonalDataPreferenceController(context, settingsPreferenceFragment));
        arrayList.add(new AutoSyncWorkDataPreferenceController(context, settingsPreferenceFragment));
        return arrayList;
    }
}
