package com.android.settings.backup;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class PrivacySettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.privacy_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            BackupSettingsHelper backupSettingsHelper = new BackupSettingsHelper(context);
            return !backupSettingsHelper.isBackupProvidedByManufacturer() && !backupSettingsHelper.isIntentProvidedByTransport();
        }
    };

    public int getHelpResource() {
        return C1715R.string.help_url_backup_reset;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "PrivacySettings";
    }

    public int getMetricsCategory() {
        return 81;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.privacy_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        updatePrivacySettingsConfigData(context);
    }

    /* access modifiers changed from: protected */
    public void updatePreferenceStates() {
        updatePrivacySettingsConfigData(getContext());
        super.updatePreferenceStates();
    }

    private void updatePrivacySettingsConfigData(Context context) {
        if (PrivacySettingsUtils.isAdminUser(context)) {
            PrivacySettingsUtils.updatePrivacyBuffer(context, PrivacySettingsConfigData.getInstance());
        }
    }
}
