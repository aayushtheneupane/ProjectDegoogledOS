package com.android.settings.accounts;

import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import com.android.settings.Utils;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ChooseAccountFragment extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.add_account_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return ChooseAccountFragment.buildControllers(context);
        }
    };

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "ChooseAccountFragment";
    }

    public int getMetricsCategory() {
        return 10;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.add_account_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((ChooseAccountPreferenceController) use(ChooseAccountPreferenceController.class)).initialize(getIntent().getStringArrayExtra("authorities"), getIntent().getStringArrayExtra("account_types"), Utils.getSecureTargetUser(getActivity().getActivityToken(), UserManager.get(getContext()), (Bundle) null, getIntent().getExtras()), getActivity());
        ((EnterpriseDisclosurePreferenceController) use(EnterpriseDisclosurePreferenceController.class)).setFooterPreferenceMixin(this.mFooterPreferenceMixin);
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildControllers(context);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EnterpriseDisclosurePreferenceController(context));
        return arrayList;
    }
}
