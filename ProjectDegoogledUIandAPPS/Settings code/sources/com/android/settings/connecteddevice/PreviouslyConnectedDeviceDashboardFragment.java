package com.android.settings.connecteddevice;

import android.content.Context;
import android.content.res.Resources;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class PreviouslyConnectedDeviceDashboardFragment extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            Resources resources = context.getResources();
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(context);
            searchIndexableRaw.key = "saved_device_list";
            searchIndexableRaw.title = resources.getString(C1715R.string.connected_device_previously_connected_title);
            searchIndexableRaw.screenTitle = resources.getString(C1715R.string.connected_device_previously_connected_title);
            arrayList.add(searchIndexableRaw);
            return arrayList;
        }
    };

    public int getHelpResource() {
        return C1715R.string.help_url_previously_connected_devices;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "PreConnectedDeviceFrag";
    }

    public int getMetricsCategory() {
        return 1370;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.previously_connected_devices;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((SavedDeviceGroupController) use(SavedDeviceGroupController.class)).init(this);
    }
}
