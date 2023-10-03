package com.android.settings.wifi.dpp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.havoc.config.center.C1715R;

public class WifiDppChooseSavedWifiNetworkFragment extends WifiDppQrCodeBaseFragment {
    private Button mButtonLeft;
    private Button mButtonRight;

    public int getMetricsCategory() {
        return 1595;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        WifiNetworkListFragment wifiNetworkListFragment = new WifiNetworkListFragment();
        Bundle arguments = getArguments();
        if (arguments != null) {
            wifiNetworkListFragment.setArguments(arguments);
        }
        FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
        beginTransaction.replace(C1715R.C1718id.wifi_network_list_container, wifiNetworkListFragment, "wifi_network_list_fragment");
        beginTransaction.commit();
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C1715R.layout.wifi_dpp_choose_saved_wifi_network_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setHeaderIconImageResource(C1715R.C1717drawable.ic_wifi_signal_4);
        this.mTitle.setText(C1715R.string.wifi_dpp_choose_network);
        this.mSummary.setText(C1715R.string.wifi_dpp_choose_network_to_connect_device);
        this.mButtonLeft = (Button) view.findViewById(C1715R.C1718id.button_left);
        this.mButtonLeft.setText(C1715R.string.cancel);
        this.mButtonLeft.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDppChooseSavedWifiNetworkFragment.this.lambda$onViewCreated$0$WifiDppChooseSavedWifiNetworkFragment(view);
            }
        });
        this.mButtonRight = (Button) view.findViewById(C1715R.C1718id.button_right);
        this.mButtonRight.setVisibility(8);
        if (bundle == null) {
            this.mTitleSummaryContainer.sendAccessibilityEvent(32);
        }
    }

    public /* synthetic */ void lambda$onViewCreated$0$WifiDppChooseSavedWifiNetworkFragment(View view) {
        Intent intent = getActivity().getIntent();
        String action = intent != null ? intent.getAction() : null;
        if ("android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_SCANNER".equals(action) || "android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_GENERATOR".equals(action)) {
            getFragmentManager().popBackStack();
        } else {
            getActivity().finish();
        }
    }
}
