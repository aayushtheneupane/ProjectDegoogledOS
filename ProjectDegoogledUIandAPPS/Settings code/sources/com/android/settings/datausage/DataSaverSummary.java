package com.android.settings.datausage;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.widget.Switch;
import androidx.preference.Preference;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settings.datausage.AppStateDataUsageBridge;
import com.android.settings.datausage.DataSaverBackend;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.applications.ApplicationsState;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class DataSaverSummary extends SettingsPreferenceFragment implements SwitchBar.OnSwitchChangeListener, DataSaverBackend.Listener, AppStateBaseBridge.Callback, ApplicationsState.Callbacks {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.data_saver;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return DataUsageUtils.hasMobileData(context) && DataUsageUtils.getDefaultSubscriptionId(context) != -1;
        }
    };
    private ApplicationsState mApplicationsState;
    private DataSaverBackend mDataSaverBackend;
    private AppStateDataUsageBridge mDataUsageBridge;
    private ApplicationsState.Session mSession;
    private SwitchBar mSwitchBar;
    private boolean mSwitching;
    private Preference mUnrestrictedAccess;

    public int getHelpResource() {
        return C1715R.string.help_url_data_saver;
    }

    public int getMetricsCategory() {
        return 348;
    }

    public void onAllSizesComputed() {
    }

    public void onBlacklistStatusChanged(int i, boolean z) {
    }

    public void onLauncherInfoChanged() {
    }

    public void onLoadEntriesCompleted() {
    }

    public void onPackageIconChanged() {
    }

    public void onPackageListChanged() {
    }

    public void onPackageSizeChanged(String str) {
    }

    public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> arrayList) {
    }

    public void onRunningStateChanged(boolean z) {
    }

    public void onWhitelistStatusChanged(int i, boolean z) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.data_saver);
        this.mFooterPreferenceMixin.createFooterPreference().setTitle(17039847);
        this.mUnrestrictedAccess = findPreference("unrestricted_access");
        this.mApplicationsState = ApplicationsState.getInstance((Application) getContext().getApplicationContext());
        this.mDataSaverBackend = new DataSaverBackend(getContext());
        this.mDataUsageBridge = new AppStateDataUsageBridge(this.mApplicationsState, this, this.mDataSaverBackend);
        this.mSession = this.mApplicationsState.newSession(this, getSettingsLifecycle());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mSwitchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBar.setSwitchBarText(C1715R.string.data_saver_switch_title, C1715R.string.data_saver_switch_title);
        this.mSwitchBar.show();
        this.mSwitchBar.addOnSwitchChangeListener(this);
    }

    public void onResume() {
        super.onResume();
        this.mDataSaverBackend.refreshWhitelist();
        this.mDataSaverBackend.refreshBlacklist();
        this.mDataSaverBackend.addListener(this);
        this.mDataUsageBridge.resume();
    }

    public void onPause() {
        super.onPause();
        this.mDataSaverBackend.remListener(this);
        this.mDataUsageBridge.pause();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDataUsageBridge.release();
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        synchronized (this) {
            if (!this.mSwitching) {
                this.mSwitching = true;
                this.mDataSaverBackend.setDataSaverEnabled(z);
            }
        }
    }

    public void onDataSaverChanged(boolean z) {
        synchronized (this) {
            this.mSwitchBar.setChecked(z);
            this.mSwitching = false;
        }
    }

    public void onExtraInfoUpdated() {
        Object obj;
        if (isAdded()) {
            ArrayList<ApplicationsState.AppEntry> allApps = this.mSession.getAllApps();
            int size = allApps.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                ApplicationsState.AppEntry appEntry = allApps.get(i2);
                if (ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER.filterApp(appEntry) && (obj = appEntry.extraInfo) != null && ((AppStateDataUsageBridge.DataUsageState) obj).isDataSaverWhitelisted) {
                    i++;
                }
            }
            this.mUnrestrictedAccess.setSummary((CharSequence) getResources().getQuantityString(C1715R.plurals.data_saver_unrestricted_summary, i, new Object[]{Integer.valueOf(i)}));
        }
    }
}
