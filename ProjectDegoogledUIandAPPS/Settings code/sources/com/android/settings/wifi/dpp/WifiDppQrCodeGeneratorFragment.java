package com.android.settings.wifi.dpp;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.settings.wifi.dpp.WifiNetworkConfig;
import com.android.settings.wifi.qrcode.QrCodeGenerator;
import com.google.zxing.WriterException;
import com.havoc.config.center.C1715R;

public class WifiDppQrCodeGeneratorFragment extends WifiDppQrCodeBaseFragment {
    OnQrCodeGeneratorFragmentAddButtonClickedListener mListener;
    private TextView mPasswordView;
    private String mQrCode;
    private ImageView mQrCodeView;

    public interface OnQrCodeGeneratorFragmentAddButtonClickedListener {
    }

    public int getMetricsCategory() {
        return 1595;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getWifiNetworkConfigFromHostActivity().isHotspot()) {
            getActivity().setTitle(C1715R.string.wifi_dpp_share_hotspot);
        } else {
            getActivity().setTitle(C1715R.string.wifi_dpp_share_wifi);
        }
        setHasOptionsMenu(true);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.show();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mListener = (OnQrCodeGeneratorFragmentAddButtonClickedListener) context;
    }

    public void onDetach() {
        this.mListener = null;
        super.onDetach();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem findItem = menu.findItem(1);
        if (findItem != null) {
            findItem.setShowAsAction(0);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C1715R.layout.wifi_dpp_qrcode_generator_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mQrCodeView = (ImageView) view.findViewById(C1715R.C1718id.qrcode_view);
        setHeaderIconImageResource(C1715R.C1717drawable.ic_qrcode_24dp);
        WifiNetworkConfig wifiNetworkConfigFromHostActivity = getWifiNetworkConfigFromHostActivity();
        if (wifiNetworkConfigFromHostActivity.isHotspot()) {
            this.mTitle.setText(C1715R.string.wifi_dpp_share_hotspot);
        } else {
            this.mTitle.setText(C1715R.string.wifi_dpp_share_wifi);
        }
        String preSharedKey = wifiNetworkConfigFromHostActivity.getPreSharedKey();
        this.mPasswordView = (TextView) view.findViewById(C1715R.C1718id.password);
        if (TextUtils.isEmpty(preSharedKey)) {
            this.mSummary.setText(getString(C1715R.string.wifi_dpp_scan_open_network_qr_code_with_another_device, wifiNetworkConfigFromHostActivity.getSsid()));
            this.mPasswordView.setVisibility(8);
        } else {
            this.mSummary.setText(getString(C1715R.string.wifi_dpp_scan_qr_code_with_another_device, wifiNetworkConfigFromHostActivity.getSsid()));
            if (wifiNetworkConfigFromHostActivity.isHotspot()) {
                this.mPasswordView.setText(getString(C1715R.string.wifi_dpp_hotspot_password, preSharedKey));
            } else {
                this.mPasswordView.setText(getString(C1715R.string.wifi_dpp_wifi_password, preSharedKey));
            }
        }
        this.mQrCode = wifiNetworkConfigFromHostActivity.getQrCode();
        setQrCode();
    }

    private void setQrCode() {
        try {
            this.mQrCodeView.setImageBitmap(QrCodeGenerator.encodeQrCode(this.mQrCode, getContext().getResources().getDimensionPixelSize(C1715R.dimen.qrcode_size)));
        } catch (WriterException e) {
            Log.e("WifiDppQrCodeGeneratorFragment", "Error generatting QR code bitmap " + e);
        }
    }

    /* access modifiers changed from: package-private */
    public WifiNetworkConfig getWifiNetworkConfigFromHostActivity() {
        WifiNetworkConfig wifiNetworkConfig = ((WifiNetworkConfig.Retriever) getActivity()).getWifiNetworkConfig();
        if (WifiNetworkConfig.isValidConfig(wifiNetworkConfig)) {
            return wifiNetworkConfig;
        }
        throw new IllegalStateException("Invalid Wi-Fi network for configuring");
    }
}
