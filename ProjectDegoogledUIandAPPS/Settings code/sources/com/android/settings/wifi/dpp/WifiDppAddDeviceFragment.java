package com.android.settings.wifi.dpp;

import android.app.ActionBar;
import android.content.Context;
import android.net.wifi.EasyConnectStatusCallback;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.havoc.config.center.C1715R;

public class WifiDppAddDeviceFragment extends WifiDppQrCodeBaseFragment {
    private Button mButtonLeft;
    private Button mButtonRight;
    private Button mChooseDifferentNetwork;
    OnClickChooseDifferentNetworkListener mClickChooseDifferentNetworkListener;
    private int mLatestStatusCode = 0;
    private ProgressBar mProgressBar;
    private ImageView mWifiApPictureView;

    public interface OnClickChooseDifferentNetworkListener {
        void onClickChooseDifferentNetwork();
    }

    private boolean hasRetryButton(int i) {
        return (i == -3 || i == -1) ? false : true;
    }

    public int getMetricsCategory() {
        return 1595;
    }

    private class EasyConnectConfiguratorStatusCallback extends EasyConnectStatusCallback {
        public void onEnrolleeSuccess(int i) {
        }

        public void onProgress(int i) {
        }

        private EasyConnectConfiguratorStatusCallback() {
        }

        public void onConfiguratorSuccess(int i) {
            WifiDppAddDeviceFragment.this.showSuccessUi(false);
        }

        public void onFailure(int i) {
            Log.d("WifiDppAddDeviceFragment", "EasyConnectConfiguratorStatusCallback.onFailure " + i);
            WifiDppAddDeviceFragment.this.showErrorUi(i, false);
        }
    }

    /* access modifiers changed from: private */
    public void showSuccessUi(boolean z) {
        setHeaderIconImageResource(C1715R.C1717drawable.ic_devices_check_circle_green);
        this.mTitle.setText(C1715R.string.wifi_dpp_wifi_shared_with_device);
        this.mProgressBar.setVisibility(isGoingInitiator() ? 0 : 4);
        this.mSummary.setVisibility(4);
        this.mWifiApPictureView.setImageResource(C1715R.C1717drawable.wifi_dpp_success);
        this.mChooseDifferentNetwork.setVisibility(4);
        this.mButtonLeft.setText(C1715R.string.wifi_dpp_add_another_device);
        this.mButtonLeft.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDppAddDeviceFragment.this.lambda$showSuccessUi$0$WifiDppAddDeviceFragment(view);
            }
        });
        this.mButtonRight.setText(C1715R.string.done);
        this.mButtonRight.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDppAddDeviceFragment.this.lambda$showSuccessUi$1$WifiDppAddDeviceFragment(view);
            }
        });
        if (!z) {
            this.mLatestStatusCode = 1;
            changeFocusAndAnnounceChange(this.mButtonRight, this.mTitle);
        }
    }

    public /* synthetic */ void lambda$showSuccessUi$0$WifiDppAddDeviceFragment(View view) {
        getFragmentManager().popBackStack();
    }

    public /* synthetic */ void lambda$showSuccessUi$1$WifiDppAddDeviceFragment(View view) {
        FragmentActivity activity = getActivity();
        activity.setResult(-1);
        activity.finish();
    }

    /* access modifiers changed from: private */
    public void showErrorUi(int i, boolean z) {
        int i2 = 0;
        switch (i) {
            case -9:
                throw new IllegalStateException("Wi-Fi DPP configurator used a non-PSK/non-SAEnetwork to handshake");
            case -8:
                this.mSummary.setText(getString(C1715R.string.wifi_dpp_failure_not_supported, getSsid()));
                break;
            case -7:
                this.mSummary.setText(C1715R.string.wifi_dpp_failure_generic);
                break;
            case -6:
                this.mSummary.setText(C1715R.string.wifi_dpp_failure_timeout);
                break;
            case -5:
                if (!z) {
                    if (i != this.mLatestStatusCode) {
                        this.mLatestStatusCode = i;
                        ((WifiManager) getContext().getSystemService(WifiManager.class)).stopEasyConnectSession();
                        startWifiDppConfiguratorInitiator();
                        return;
                    }
                    throw new IllegalStateException("Tried restarting EasyConnectSession but stillreceiving EASY_CONNECT_EVENT_FAILURE_BUSY");
                }
                return;
            case -4:
                this.mSummary.setText(C1715R.string.wifi_dpp_failure_authentication_or_configuration);
                break;
            case -3:
                this.mSummary.setText(C1715R.string.wifi_dpp_failure_not_compatible);
                break;
            case -2:
                this.mSummary.setText(C1715R.string.wifi_dpp_failure_authentication_or_configuration);
                break;
            case -1:
                this.mSummary.setText(C1715R.string.wifi_dpp_could_not_detect_valid_qr_code);
                break;
            default:
                throw new IllegalStateException("Unexpected Wi-Fi DPP error");
        }
        this.mTitle.setText(C1715R.string.wifi_dpp_could_not_add_device);
        this.mWifiApPictureView.setImageResource(C1715R.C1717drawable.wifi_dpp_error);
        this.mChooseDifferentNetwork.setVisibility(4);
        if (hasRetryButton(i)) {
            this.mButtonRight.setText(C1715R.string.retry);
        } else {
            this.mButtonRight.setText(C1715R.string.done);
            this.mButtonRight.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    WifiDppAddDeviceFragment.this.lambda$showErrorUi$2$WifiDppAddDeviceFragment(view);
                }
            });
            this.mButtonLeft.setVisibility(4);
        }
        if (isGoingInitiator()) {
            this.mSummary.setText(C1715R.string.wifi_dpp_sharing_wifi_with_this_device);
        }
        this.mProgressBar.setVisibility(isGoingInitiator() ? 0 : 4);
        Button button = this.mButtonRight;
        if (isGoingInitiator()) {
            i2 = 4;
        }
        button.setVisibility(i2);
        if (!z) {
            this.mLatestStatusCode = i;
            changeFocusAndAnnounceChange(this.mButtonRight, this.mSummary);
        }
    }

    public /* synthetic */ void lambda$showErrorUi$2$WifiDppAddDeviceFragment(View view) {
        getActivity().finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mLatestStatusCode = bundle.getInt("key_latest_error_code");
        }
        WifiDppInitiatorViewModel wifiDppInitiatorViewModel = (WifiDppInitiatorViewModel) ViewModelProviders.m3of(this).get(WifiDppInitiatorViewModel.class);
        wifiDppInitiatorViewModel.getStatusCode().observe(this, new Observer(wifiDppInitiatorViewModel) {
            private final /* synthetic */ WifiDppInitiatorViewModel f$1;

            {
                this.f$1 = r2;
            }

            public final void onChanged(Object obj) {
                WifiDppAddDeviceFragment.this.lambda$onCreate$3$WifiDppAddDeviceFragment(this.f$1, (Integer) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$3$WifiDppAddDeviceFragment(WifiDppInitiatorViewModel wifiDppInitiatorViewModel, Integer num) {
        if (!wifiDppInitiatorViewModel.isGoingInitiator()) {
            int intValue = num.intValue();
            if (intValue == 1) {
                new EasyConnectConfiguratorStatusCallback().onConfiguratorSuccess(intValue);
            } else {
                new EasyConnectConfiguratorStatusCallback().onFailure(intValue);
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C1715R.layout.wifi_dpp_add_device_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setHeaderIconImageResource(C1715R.C1717drawable.ic_devices_other_opaque_black);
        this.mProgressBar = (ProgressBar) view.findViewById(C1715R.C1718id.indeterminate_bar);
        String information = ((WifiDppConfiguratorActivity) getActivity()).getWifiDppQrCode().getInformation();
        if (TextUtils.isEmpty(information)) {
            this.mTitle.setText(C1715R.string.wifi_dpp_device_found);
        } else {
            this.mTitle.setText(information);
        }
        updateSummary();
        this.mWifiApPictureView = (ImageView) view.findViewById(C1715R.C1718id.wifi_ap_picture_view);
        this.mChooseDifferentNetwork = (Button) view.findViewById(C1715R.C1718id.choose_different_network);
        this.mChooseDifferentNetwork.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDppAddDeviceFragment.this.lambda$onViewCreated$4$WifiDppAddDeviceFragment(view);
            }
        });
        this.mButtonLeft = (Button) view.findViewById(C1715R.C1718id.button_left);
        this.mButtonLeft.setText(C1715R.string.cancel);
        this.mButtonLeft.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDppAddDeviceFragment.this.lambda$onViewCreated$5$WifiDppAddDeviceFragment(view);
            }
        });
        this.mButtonRight = (Button) view.findViewById(C1715R.C1718id.button_right);
        this.mButtonRight.setText(C1715R.string.wifi_dpp_share_wifi);
        this.mButtonRight.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDppAddDeviceFragment.this.lambda$onViewCreated$6$WifiDppAddDeviceFragment(view);
            }
        });
        if (bundle != null) {
            int i = this.mLatestStatusCode;
            if (i == 1) {
                showSuccessUi(true);
            } else if (i == 0) {
                int i2 = 0;
                this.mProgressBar.setVisibility(isGoingInitiator() ? 0 : 4);
                Button button = this.mButtonRight;
                if (isGoingInitiator()) {
                    i2 = 4;
                }
                button.setVisibility(i2);
            } else {
                showErrorUi(i, true);
            }
        } else {
            changeFocusAndAnnounceChange(this.mButtonRight, this.mTitleSummaryContainer);
        }
    }

    public /* synthetic */ void lambda$onViewCreated$4$WifiDppAddDeviceFragment(View view) {
        this.mClickChooseDifferentNetworkListener.onClickChooseDifferentNetwork();
    }

    public /* synthetic */ void lambda$onViewCreated$5$WifiDppAddDeviceFragment(View view) {
        getActivity().finish();
    }

    public /* synthetic */ void lambda$onViewCreated$6$WifiDppAddDeviceFragment(View view) {
        this.mProgressBar.setVisibility(0);
        this.mButtonRight.setVisibility(4);
        startWifiDppConfiguratorInitiator();
        updateSummary();
        this.mTitleSummaryContainer.sendAccessibilityEvent(8);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("key_latest_error_code", this.mLatestStatusCode);
        super.onSaveInstanceState(bundle);
    }

    private String getSsid() {
        WifiNetworkConfig wifiNetworkConfig = ((WifiDppConfiguratorActivity) getActivity()).getWifiNetworkConfig();
        if (WifiNetworkConfig.isValidConfig(wifiNetworkConfig)) {
            return wifiNetworkConfig.getSsid();
        }
        throw new IllegalStateException("Invalid Wi-Fi network for configuring");
    }

    private void startWifiDppConfiguratorInitiator() {
        ((WifiDppInitiatorViewModel) ViewModelProviders.m3of(this).get(WifiDppInitiatorViewModel.class)).startEasyConnectAsConfiguratorInitiator(((WifiDppConfiguratorActivity) getActivity()).getWifiDppQrCode().getQrCode(), ((WifiDppConfiguratorActivity) getActivity()).getWifiNetworkConfig().getNetworkId());
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mClickChooseDifferentNetworkListener = (OnClickChooseDifferentNetworkListener) context;
    }

    public void onDetach() {
        this.mClickChooseDifferentNetworkListener = null;
        super.onDetach();
    }

    private boolean isGoingInitiator() {
        return ((WifiDppInitiatorViewModel) ViewModelProviders.m3of(this).get(WifiDppInitiatorViewModel.class)).isGoingInitiator();
    }

    private void updateSummary() {
        if (isGoingInitiator()) {
            this.mSummary.setText(C1715R.string.wifi_dpp_sharing_wifi_with_this_device);
            return;
        }
        this.mSummary.setText(getString(C1715R.string.wifi_dpp_add_device_to_wifi, getSsid()));
    }

    private void changeFocusAndAnnounceChange(View view, View view2) {
        view.sendAccessibilityEvent(8);
        view2.sendAccessibilityEvent(32);
    }
}
