package com.android.settings.network;

import android.content.Context;
import android.icu.text.ListFormatter;
import android.text.BidiFormatter;
import android.text.TextUtils;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.wifi.WifiMasterSwitchPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;

public class TopLevelNetworkEntryPreferenceController extends BasePreferenceController {
    private final MobileNetworkPreferenceController mMobileNetworkPreferenceController = new MobileNetworkPreferenceController(this.mContext);
    private final TetherPreferenceController mTetherPreferenceController = new TetherPreferenceController(this.mContext, (Lifecycle) null);
    private final WifiMasterSwitchPreferenceController mWifiPreferenceController = new WifiMasterSwitchPreferenceController(this.mContext, (MetricsFeatureProvider) null);

    public TopLevelNetworkEntryPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return Utils.isDemoUser(this.mContext) ? 3 : 1;
    }

    public CharSequence getSummary() {
        String unicodeWrap = BidiFormatter.getInstance().unicodeWrap(this.mContext.getString(C1715R.string.wifi_settings_title));
        String string = this.mContext.getString(C1715R.string.network_dashboard_summary_mobile);
        String string2 = this.mContext.getString(C1715R.string.network_dashboard_summary_data_usage);
        String string3 = this.mContext.getString(C1715R.string.network_dashboard_summary_hotspot);
        ArrayList arrayList = new ArrayList();
        if (this.mWifiPreferenceController.isAvailable() && !TextUtils.isEmpty(unicodeWrap)) {
            arrayList.add(unicodeWrap);
        }
        if (this.mMobileNetworkPreferenceController.isAvailable() && !TextUtils.isEmpty(string)) {
            arrayList.add(string);
        }
        if (!TextUtils.isEmpty(string2)) {
            arrayList.add(string2);
        }
        if (this.mTetherPreferenceController.isAvailable() && !TextUtils.isEmpty(string3)) {
            arrayList.add(string3);
        }
        return ListFormatter.getInstance().format(arrayList);
    }
}
