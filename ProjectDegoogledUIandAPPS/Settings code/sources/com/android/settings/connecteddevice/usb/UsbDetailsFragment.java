package com.android.settings.connecteddevice.usb;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.settings.connecteddevice.usb.UsbConnectionBroadcastReceiver;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.google.android.collect.Lists;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class UsbDetailsFragment extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.usb_details_fragment;
            return Lists.newArrayList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return new ArrayList(UsbDetailsFragment.createControllerList(context, new UsbBackend(context), (UsbDetailsFragment) null));
        }
    };
    private static final String TAG = "UsbDetailsFragment";
    private List<UsbDetailsController> mControllers;
    private UsbBackend mUsbBackend;
    private UsbConnectionBroadcastReceiver.UsbConnectionListener mUsbConnectionListener = new UsbConnectionBroadcastReceiver.UsbConnectionListener() {
        public final void onUsbConnectionChanged(boolean z, long j, int i, int i2) {
            UsbDetailsFragment.this.lambda$new$0$UsbDetailsFragment(z, j, i, i2);
        }
    };
    UsbConnectionBroadcastReceiver mUsbReceiver;

    public int getMetricsCategory() {
        return 1291;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.usb_details_fragment;
    }

    public /* synthetic */ void lambda$new$0$UsbDetailsFragment(boolean z, long j, int i, int i2) {
        for (UsbDetailsController refresh : this.mControllers) {
            refresh.refresh(z, j, i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return TAG;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mUsbBackend = new UsbBackend(context);
        this.mControllers = createControllerList(context, this.mUsbBackend, this);
        this.mUsbReceiver = new UsbConnectionBroadcastReceiver(context, this.mUsbConnectionListener, this.mUsbBackend);
        getSettingsLifecycle().addObserver(this.mUsbReceiver);
        return new ArrayList(this.mControllers);
    }

    /* access modifiers changed from: private */
    public static List<UsbDetailsController> createControllerList(Context context, UsbBackend usbBackend, UsbDetailsFragment usbDetailsFragment) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new UsbDetailsHeaderController(context, usbDetailsFragment, usbBackend));
        arrayList.add(new UsbDetailsDataRoleController(context, usbDetailsFragment, usbBackend));
        arrayList.add(new UsbDetailsFunctionsController(context, usbDetailsFragment, usbBackend));
        arrayList.add(new UsbDetailsPowerRoleController(context, usbDetailsFragment, usbBackend));
        return arrayList;
    }
}
