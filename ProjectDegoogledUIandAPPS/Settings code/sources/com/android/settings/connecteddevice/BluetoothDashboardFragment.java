package com.android.settings.connecteddevice;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Bundle;
import com.android.settings.SettingsActivity;
import com.android.settings.bluetooth.BluetoothDeviceRenamePreferenceController;
import com.android.settings.bluetooth.BluetoothSwitchPreferenceController;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import com.android.settings.widget.SwitchBar;
import com.android.settings.widget.SwitchBarController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.FooterPreference;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class BluetoothDashboardFragment extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(context);
            searchIndexableRaw.title = context.getString(C1715R.string.bluetooth_settings_title);
            searchIndexableRaw.screenTitle = context.getString(C1715R.string.bluetooth_settings_title);
            searchIndexableRaw.keywords = context.getString(C1715R.string.keywords_bluetooth_settings);
            searchIndexableRaw.key = "bluetooth_switchbar_screen";
            arrayList.add(searchIndexableRaw);
            return arrayList;
        }

        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
            if (bluetoothManager != null) {
                if ((bluetoothManager.getAdapter() != null ? (char) 0 : 3) != 0) {
                    nonIndexableKeys.add("bluetooth_switchbar_screen");
                }
            }
            return nonIndexableKeys;
        }
    };
    private BluetoothSwitchPreferenceController mController;
    private FooterPreference mFooterPreference;
    private SwitchBar mSwitchBar;

    public int getHelpResource() {
        return C1715R.string.help_uri_bluetooth_screen;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "BluetoothDashboardFrag";
    }

    public int getMetricsCategory() {
        return 1390;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.bluetooth_screen;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFooterPreference = this.mFooterPreferenceMixin.createFooterPreference();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((BluetoothDeviceRenamePreferenceController) use(BluetoothDeviceRenamePreferenceController.class)).setFragment(this);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        this.mSwitchBar = settingsActivity.getSwitchBar();
        this.mController = new BluetoothSwitchPreferenceController(settingsActivity, new SwitchBarController(this.mSwitchBar), this.mFooterPreference);
        Lifecycle settingsLifecycle = getSettingsLifecycle();
        if (settingsLifecycle != null) {
            settingsLifecycle.addObserver(this.mController);
        }
    }
}
