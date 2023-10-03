package com.android.settings.fuelgauge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.settings.SettingsActivity;
import com.android.settings.core.InstrumentedPreferenceFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerUsageAdvanced extends PowerUsageBase {
    static final int MENU_TOGGLE_APPS = 2;
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.power_usage_advanced;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BatteryAppListPreferenceController(context, "app_list", (Lifecycle) null, (SettingsActivity) null, (InstrumentedPreferenceFragment) null));
            return arrayList;
        }
    };
    private BatteryAppListPreferenceController mBatteryAppListPreferenceController;
    private BatteryUtils mBatteryUtils;
    BatteryHistoryPreference mHistPref;
    private PowerUsageFeatureProvider mPowerUsageFeatureProvider;
    boolean mShowAllApps = false;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "AdvancedBatteryUsage";
    }

    public int getMetricsCategory() {
        return 51;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.power_usage_advanced;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        this.mHistPref = (BatteryHistoryPreference) findPreference("battery_graph");
        this.mPowerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
        this.mBatteryUtils = BatteryUtils.getInstance(context);
        updateHistPrefSummary(context);
        restoreSavedInstance(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        if (getActivity().isChangingConfigurations()) {
            BatteryEntry.clearUidCache();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 2, 0, this.mShowAllApps ? C1715R.string.hide_extra_apps : C1715R.string.show_all_apps);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 2) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.mShowAllApps = !this.mShowAllApps;
        menuItem.setTitle(this.mShowAllApps ? C1715R.string.hide_extra_apps : C1715R.string.show_all_apps);
        this.mMetricsFeatureProvider.action(getContext(), 852, this.mShowAllApps);
        lambda$onCreate$0$PowerUsageBase(0);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void restoreSavedInstance(Bundle bundle) {
        if (bundle != null) {
            this.mShowAllApps = bundle.getBoolean("show_all_apps", false);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("show_all_apps", this.mShowAllApps);
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mBatteryAppListPreferenceController = new BatteryAppListPreferenceController(context, "app_list", getSettingsLifecycle(), (SettingsActivity) getActivity(), this);
        arrayList.add(this.mBatteryAppListPreferenceController);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void refreshUi(int i) {
        Context context = getContext();
        if (context != null) {
            updatePreference(this.mHistPref);
            updateHistPrefSummary(context);
            this.mBatteryAppListPreferenceController.refreshAppListGroup(this.mStatsHelper, this.mShowAllApps);
        }
    }

    private void updateHistPrefSummary(Context context) {
        boolean z = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1) != 0;
        if (!this.mPowerUsageFeatureProvider.isEnhancedBatteryPredictionEnabled(context) || z) {
            this.mHistPref.hideBottomSummary();
        } else {
            this.mHistPref.setBottomSummary(this.mPowerUsageFeatureProvider.getAdvancedUsageScreenInfoString());
        }
    }
}
