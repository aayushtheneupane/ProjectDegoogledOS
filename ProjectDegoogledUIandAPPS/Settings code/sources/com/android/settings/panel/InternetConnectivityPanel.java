package com.android.settings.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.settings.slices.CustomSliceRegistry;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class InternetConnectivityPanel implements PanelContent {
    private final Context mContext;

    public int getMetricsCategory() {
        return 1654;
    }

    public static InternetConnectivityPanel create(Context context) {
        return new InternetConnectivityPanel(context);
    }

    private InternetConnectivityPanel(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public CharSequence getTitle() {
        return this.mContext.getText(C1715R.string.internet_connectivity_panel_title);
    }

    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CustomSliceRegistry.WIFI_SLICE_URI);
        arrayList.add(CustomSliceRegistry.MOBILE_DATA_SLICE_URI);
        arrayList.add(CustomSliceRegistry.AIRPLANE_URI);
        return arrayList;
    }

    public Intent getSeeMoreIntent() {
        return new Intent("android.settings.WIRELESS_SETTINGS").addFlags(268435456);
    }
}
