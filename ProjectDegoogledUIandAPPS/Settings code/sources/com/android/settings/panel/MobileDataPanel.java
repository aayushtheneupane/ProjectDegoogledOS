package com.android.settings.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import com.android.settings.SubSettings;
import com.android.settings.network.NetworkDashboardFragment;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.SliceBuilderUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class MobileDataPanel implements PanelContent {
    private final Context mContext;
    private final SubscriptionManager mSubscriptionManager;

    public int getMetricsCategory() {
        return 2000;
    }

    public static MobileDataPanel create(Context context) {
        return new MobileDataPanel(context);
    }

    private MobileDataPanel(Context context) {
        this.mContext = context.getApplicationContext();
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
    }

    public CharSequence getTitle() {
        return getSummary();
    }

    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CustomSliceRegistry.MOBILE_DATA_SLICE_URI);
        arrayList.add(CustomSliceRegistry.DATA_USAGE_SLICE_URI);
        arrayList.add(CustomSliceRegistry.ENHANCED_4G_SLICE_URI);
        return arrayList;
    }

    public Intent getSeeMoreIntent() {
        Intent buildSearchResultPageIntent = SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, NetworkDashboardFragment.class.getName(), (String) null, this.mContext.getText(C1715R.string.cellular_data_title).toString(), 746);
        buildSearchResultPageIntent.setClassName(this.mContext.getPackageName(), SubSettings.class.getName());
        buildSearchResultPageIntent.addFlags(268435456);
        return buildSearchResultPageIntent;
    }

    private CharSequence getSummary() {
        SubscriptionInfo defaultDataSubscriptionInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
        if (defaultDataSubscriptionInfo == null) {
            return this.mContext.getText(C1715R.string.cellular_data_title).toString();
        }
        return defaultDataSubscriptionInfo.getDisplayName();
    }
}
