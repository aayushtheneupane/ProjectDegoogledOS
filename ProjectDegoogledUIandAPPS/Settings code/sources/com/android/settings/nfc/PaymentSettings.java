package com.android.settings.nfc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class PaymentSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.nfc_payment_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            if (((UserManager) context.getSystemService(UserManager.class)).getUserInfo(UserHandle.myUserId()).isGuest()) {
                return false;
            }
            return context.getPackageManager().hasSystemFeature("android.hardware.nfc");
        }
    };
    private PaymentBackend mPaymentBackend;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "PaymentSettings";
    }

    public int getMetricsCategory() {
        return 70;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.nfc_payment_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mPaymentBackend = new PaymentBackend(getActivity());
        setHasOptionsMenu(true);
        ((NfcPaymentPreferenceController) use(NfcPaymentPreferenceController.class)).setPaymentBackend(this.mPaymentBackend);
        ((NfcForegroundPreferenceController) use(NfcForegroundPreferenceController.class)).setPaymentBackend(this.mPaymentBackend);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewGroup viewGroup = (ViewGroup) getListView().getParent();
        View inflate = getActivity().getLayoutInflater().inflate(C1715R.layout.nfc_payment_empty, viewGroup, false);
        viewGroup.addView(inflate);
        setEmptyView(inflate);
    }

    public void onResume() {
        super.onResume();
        this.mPaymentBackend.onResume();
    }

    public void onPause() {
        super.onPause();
        this.mPaymentBackend.onPause();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        MenuItem add = menu.add(C1715R.string.nfc_payment_how_it_works);
        add.setIntent(new Intent(getActivity(), HowItWorks.class));
        add.setShowAsActionFlags(0);
    }
}
