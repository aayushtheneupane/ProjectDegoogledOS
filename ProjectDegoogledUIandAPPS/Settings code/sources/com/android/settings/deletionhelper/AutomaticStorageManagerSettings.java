package com.android.settings.deletionhelper;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import com.android.settings.SettingsActivity;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class AutomaticStorageManagerSettings extends DashboardFragment implements Preference.OnPreferenceChangeListener {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return false;
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return AutomaticStorageManagerSettings.buildPreferenceControllers(context);
        }
    };
    private DropDownPreference mDaysToRetain;
    private SwitchBar mSwitchBar;
    private AutomaticStorageManagerSwitchBarController mSwitchController;

    public int getHelpResource() {
        return C1715R.string.help_uri_storage;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return null;
    }

    public int getMetricsCategory() {
        return 458;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.automatic_storage_management_settings;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        initializeDaysToRetainPreference();
        initializeSwitchBar();
        return onCreateView;
    }

    private void initializeDaysToRetainPreference() {
        this.mDaysToRetain = (DropDownPreference) findPreference("days");
        this.mDaysToRetain.setOnPreferenceChangeListener(this);
        int i = Settings.Secure.getInt(getContentResolver(), "automatic_storage_manager_days_to_retain", Utils.getDefaultStorageManagerDaysToRetain(getResources()));
        String[] stringArray = getResources().getStringArray(C1715R.array.automatic_storage_management_days_values);
        this.mDaysToRetain.setValue(stringArray[daysValueToIndex(i, stringArray)]);
    }

    private void initializeSwitchBar() {
        this.mSwitchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBar.setSwitchBarText(C1715R.string.automatic_storage_manager_master_switch_title, C1715R.string.automatic_storage_manager_master_switch_title);
        this.mSwitchBar.show();
        this.mSwitchController = new AutomaticStorageManagerSwitchBarController(getContext(), this.mSwitchBar, this.mMetricsFeatureProvider, this.mDaysToRetain, getFragmentManager());
    }

    public void onResume() {
        super.onResume();
        this.mDaysToRetain.setEnabled(Utils.isStorageManagerEnabled(getContext()));
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mSwitchBar.hide();
        this.mSwitchController.tearDown();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!"days".equals(preference.getKey())) {
            return true;
        }
        Settings.Secure.putInt(getContentResolver(), "automatic_storage_manager_days_to_retain", Integer.parseInt((String) obj));
        return true;
    }

    private static int daysValueToIndex(int i, String[] strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (i == Integer.parseInt(strArr[i2])) {
                return i2;
            }
        }
        return strArr.length - 1;
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AutomaticStorageManagerDescriptionPreferenceController(context));
        return arrayList;
    }
}
