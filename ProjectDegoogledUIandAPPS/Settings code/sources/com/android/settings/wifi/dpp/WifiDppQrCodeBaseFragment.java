package com.android.settings.wifi.dpp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.settings.core.InstrumentedFragment;
import com.havoc.config.center.C1715R;

public abstract class WifiDppQrCodeBaseFragment extends InstrumentedFragment {
    private ImageView mDevicesCheckCircleGreenHeaderIcon;
    private ImageView mHeaderIcon;
    protected TextView mSummary;
    protected TextView mTitle;
    protected View mTitleSummaryContainer;

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mHeaderIcon = (ImageView) view.findViewById(16908294);
        this.mDevicesCheckCircleGreenHeaderIcon = (ImageView) view.findViewById(C1715R.C1718id.devices_check_circle_green_icon);
        this.mTitle = (TextView) view.findViewById(16908310);
        this.mSummary = (TextView) view.findViewById(16908304);
        this.mTitleSummaryContainer = view.findViewById(C1715R.C1718id.title_summary_container);
    }

    /* access modifiers changed from: protected */
    public void setHeaderIconImageResource(int i) {
        if (i == C1715R.C1717drawable.ic_devices_check_circle_green) {
            this.mHeaderIcon.setVisibility(8);
            this.mDevicesCheckCircleGreenHeaderIcon.setVisibility(0);
            return;
        }
        this.mDevicesCheckCircleGreenHeaderIcon.setVisibility(8);
        this.mHeaderIcon.setImageResource(i);
        this.mHeaderIcon.setVisibility(0);
    }
}
