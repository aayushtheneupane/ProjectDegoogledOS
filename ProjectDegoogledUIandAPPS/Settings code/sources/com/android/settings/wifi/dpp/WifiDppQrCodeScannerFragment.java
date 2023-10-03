package com.android.settings.wifi.dpp;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.net.wifi.EasyConnectStatusCallback;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.android.settings.wifi.dpp.WifiNetworkConfig;
import com.android.settings.wifi.qrcode.QrCamera;
import com.android.settings.wifi.qrcode.QrDecorateView;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import com.havoc.config.center.C1715R;

public class WifiDppQrCodeScannerFragment extends WifiDppQrCodeBaseFragment implements TextureView.SurfaceTextureListener, QrCamera.ScannerCallback, WifiManager.ActionListener, WifiTracker.WifiListener {
    private QrCamera mCamera;
    /* access modifiers changed from: private */
    public QrDecorateView mDecorateView;
    /* access modifiers changed from: private */
    public WifiConfiguration mEnrolleeWifiConfiguration;
    /* access modifiers changed from: private */
    public TextView mErrorMessage;
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                WifiDppQrCodeScannerFragment.this.mErrorMessage.setVisibility(4);
            } else if (i == 2) {
                WifiDppQrCodeScannerFragment.this.mErrorMessage.setVisibility(0);
                WifiDppQrCodeScannerFragment.this.mErrorMessage.setText((String) message.obj);
                WifiDppQrCodeScannerFragment.this.mErrorMessage.sendAccessibilityEvent(32);
                removeMessages(1);
                sendEmptyMessageDelayed(1, 10000);
                if (message.arg1 == 1) {
                    WifiDppQrCodeScannerFragment.this.mProgressBar.setVisibility(4);
                    WifiDppQrCodeScannerFragment.this.mDecorateView.setFocused(false);
                    WifiDppQrCodeScannerFragment.this.restartCamera();
                }
            } else if (i == 3) {
                OnScanWifiDppSuccessListener onScanWifiDppSuccessListener = WifiDppQrCodeScannerFragment.this.mScanWifiDppSuccessListener;
                if (onScanWifiDppSuccessListener != null) {
                    onScanWifiDppSuccessListener.onScanWifiDppSuccess((WifiQrCode) message.obj);
                    if (!WifiDppQrCodeScannerFragment.this.mIsConfiguratorMode) {
                        WifiDppQrCodeScannerFragment.this.mProgressBar.setVisibility(0);
                        WifiDppQrCodeScannerFragment.this.startWifiDppEnrolleeInitiator((WifiQrCode) message.obj);
                        WifiDppQrCodeScannerFragment.this.updateEnrolleeSummary();
                        WifiDppQrCodeScannerFragment.this.mSummary.sendAccessibilityEvent(32);
                    }
                    WifiDppQrCodeScannerFragment.this.notifyUserForQrCodeRecognition();
                }
            } else if (i == 4) {
                WifiManager wifiManager = (WifiManager) WifiDppQrCodeScannerFragment.this.getContext().getSystemService(WifiManager.class);
                boolean z = false;
                for (WifiConfiguration next : ((WifiNetworkConfig) message.obj).getWifiConfigurations()) {
                    int addNetwork = wifiManager.addNetwork(next);
                    if (addNetwork != -1) {
                        wifiManager.enableNetwork(addNetwork, false);
                        if (next.hiddenSSID || WifiDppQrCodeScannerFragment.this.isReachableWifiNetwork(next)) {
                            WifiConfiguration unused = WifiDppQrCodeScannerFragment.this.mEnrolleeWifiConfiguration = next;
                            wifiManager.connect(addNetwork, WifiDppQrCodeScannerFragment.this);
                            z = true;
                        }
                    }
                }
                if (!z) {
                    WifiDppQrCodeScannerFragment.this.showErrorMessageAndRestartCamera(C1715R.string.wifi_dpp_check_connection_try_again);
                    return;
                }
                WifiDppQrCodeScannerFragment.this.mMetricsFeatureProvider.action(WifiDppQrCodeScannerFragment.this.mMetricsFeatureProvider.getAttribution(WifiDppQrCodeScannerFragment.this.getActivity()), 1711, 1596, (String) null, Integer.MIN_VALUE);
                WifiDppQrCodeScannerFragment.this.notifyUserForQrCodeRecognition();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mIsConfiguratorMode = true;
    /* access modifiers changed from: private */
    public int mLatestStatusCode = 0;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    OnScanWifiDppSuccessListener mScanWifiDppSuccessListener;
    private String mSsid;
    private TextureView mTextureView;
    /* access modifiers changed from: private */
    public WifiQrCode mWifiQrCode;
    private WifiTracker mWifiTracker;

    public interface OnScanWifiDppSuccessListener {
        void onScanWifiDppSuccess(WifiQrCode wifiQrCode);
    }

    public void onAccessPointsChanged() {
    }

    public void onConnectedChanged() {
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onWifiStateChanged(int i) {
    }

    /* access modifiers changed from: private */
    public void notifyUserForQrCodeRecognition() {
        QrCamera qrCamera = this.mCamera;
        if (qrCamera != null) {
            qrCamera.stop();
        }
        this.mDecorateView.setFocused(true);
        this.mErrorMessage.setVisibility(4);
        WifiDppUtils.triggerVibrationForQrCodeRecognition(getContext());
    }

    /* access modifiers changed from: private */
    public boolean isReachableWifiNetwork(WifiConfiguration wifiConfiguration) {
        for (AccessPoint next : this.mWifiTracker.getAccessPoints()) {
            if (next.matches(wifiConfiguration) && next.isReachable()) {
                return true;
            }
        }
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mIsConfiguratorMode = bundle.getBoolean("key_is_configurator_mode");
            this.mLatestStatusCode = bundle.getInt("key_latest_error_code");
            this.mEnrolleeWifiConfiguration = (WifiConfiguration) bundle.getParcelable("key_wifi_configuration");
        }
        WifiDppInitiatorViewModel wifiDppInitiatorViewModel = (WifiDppInitiatorViewModel) ViewModelProviders.m3of(this).get(WifiDppInitiatorViewModel.class);
        wifiDppInitiatorViewModel.getEnrolleeSuccessNetworkId().observe(this, new Observer(wifiDppInitiatorViewModel) {
            private final /* synthetic */ WifiDppInitiatorViewModel f$1;

            {
                this.f$1 = r2;
            }

            public final void onChanged(Object obj) {
                WifiDppQrCodeScannerFragment.this.lambda$onCreate$0$WifiDppQrCodeScannerFragment(this.f$1, (Integer) obj);
            }
        });
        wifiDppInitiatorViewModel.getStatusCode().observe(this, new Observer(wifiDppInitiatorViewModel) {
            private final /* synthetic */ WifiDppInitiatorViewModel f$1;

            {
                this.f$1 = r2;
            }

            public final void onChanged(Object obj) {
                WifiDppQrCodeScannerFragment.this.lambda$onCreate$1$WifiDppQrCodeScannerFragment(this.f$1, (Integer) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$WifiDppQrCodeScannerFragment(WifiDppInitiatorViewModel wifiDppInitiatorViewModel, Integer num) {
        if (!wifiDppInitiatorViewModel.isGoingInitiator()) {
            new EasyConnectEnrolleeStatusCallback().onEnrolleeSuccess(num.intValue());
        }
    }

    public /* synthetic */ void lambda$onCreate$1$WifiDppQrCodeScannerFragment(WifiDppInitiatorViewModel wifiDppInitiatorViewModel, Integer num) {
        if (!wifiDppInitiatorViewModel.isGoingInitiator()) {
            int intValue = num.intValue();
            Log.d("WifiDppQrCodeScanner", "Easy connect enrollee callback onFailure " + intValue);
            new EasyConnectEnrolleeStatusCallback().onFailure(intValue);
        }
    }

    public void onPause() {
        QrCamera qrCamera = this.mCamera;
        if (qrCamera != null) {
            qrCamera.stop();
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (!isGoingInitiator()) {
            restartCamera();
        }
    }

    public int getMetricsCategory() {
        return this.mIsConfiguratorMode ? 1595 : 1596;
    }

    public WifiDppQrCodeScannerFragment() {
    }

    public WifiDppQrCodeScannerFragment(String str) {
        this.mSsid = str;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mWifiTracker = WifiTrackerFactory.create(getActivity(), this, getSettingsLifecycle(), false, true);
        if (this.mIsConfiguratorMode) {
            getActivity().setTitle(C1715R.string.wifi_dpp_add_device_to_network);
        } else {
            getActivity().setTitle(C1715R.string.wifi_dpp_scan_qr_code);
        }
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.show();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mScanWifiDppSuccessListener = (OnScanWifiDppSuccessListener) context;
    }

    public void onDetach() {
        this.mScanWifiDppSuccessListener = null;
        super.onDetach();
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C1715R.layout.wifi_dpp_qrcode_scanner_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTextureView = (TextureView) view.findViewById(C1715R.C1718id.preview_view);
        this.mTextureView.setSurfaceTextureListener(this);
        this.mDecorateView = (QrDecorateView) view.findViewById(C1715R.C1718id.decorate_view);
        setHeaderIconImageResource(C1715R.C1717drawable.ic_scan_24dp);
        this.mProgressBar = (ProgressBar) view.findViewById(C1715R.C1718id.indeterminate_bar);
        this.mProgressBar.setVisibility(isGoingInitiator() ? 0 : 4);
        if (this.mIsConfiguratorMode) {
            this.mTitle.setText(C1715R.string.wifi_dpp_add_device_to_network);
            WifiNetworkConfig wifiNetworkConfig = ((WifiNetworkConfig.Retriever) getActivity()).getWifiNetworkConfig();
            if (WifiNetworkConfig.isValidConfig(wifiNetworkConfig)) {
                this.mSummary.setText(getString(C1715R.string.wifi_dpp_center_qr_code, wifiNetworkConfig.getSsid()));
            } else {
                throw new IllegalStateException("Invalid Wi-Fi network for configuring");
            }
        } else {
            this.mTitle.setText(C1715R.string.wifi_dpp_scan_qr_code);
            updateEnrolleeSummary();
        }
        this.mErrorMessage = (TextView) view.findViewById(C1715R.C1718id.error_message);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.removeItem(1);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        initCamera(surfaceTexture);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        destroyCamera();
        return true;
    }

    public Size getViewSize() {
        return new Size(this.mTextureView.getWidth(), this.mTextureView.getHeight());
    }

    public Rect getFramePosition(Size size, int i) {
        return new Rect(0, 0, size.getHeight(), size.getHeight());
    }

    public void setTransform(Matrix matrix) {
        this.mTextureView.setTransform(matrix);
    }

    public boolean isValid(String str) {
        try {
            this.mWifiQrCode = new WifiQrCode(str);
            String scheme = this.mWifiQrCode.getScheme();
            if (!this.mIsConfiguratorMode && "WIFI".equals(scheme)) {
                String ssid = this.mWifiQrCode.getWifiNetworkConfig().getSsid();
                if (!TextUtils.isEmpty(this.mSsid) && !this.mSsid.equals(ssid)) {
                    showErrorMessage(C1715R.string.wifi_dpp_could_not_detect_valid_qr_code);
                    return false;
                }
            }
            if (!this.mIsConfiguratorMode || !"WIFI".equals(scheme)) {
                return true;
            }
            showErrorMessage(C1715R.string.wifi_dpp_could_not_detect_valid_qr_code);
            return false;
        } catch (IllegalArgumentException unused) {
            showErrorMessage(C1715R.string.wifi_dpp_could_not_detect_valid_qr_code);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleSuccessfulResult(java.lang.String r4) {
        /*
            r3 = this;
            com.android.settings.wifi.dpp.WifiQrCode r4 = r3.mWifiQrCode
            java.lang.String r4 = r4.getScheme()
            int r0 = r4.hashCode()
            r1 = 67908(0x10944, float:9.516E-41)
            r2 = 1
            if (r0 == r1) goto L_0x0020
            r1 = 2664213(0x28a715, float:3.733358E-39)
            if (r0 == r1) goto L_0x0016
            goto L_0x002a
        L_0x0016:
            java.lang.String r0 = "WIFI"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x002a
            r4 = r2
            goto L_0x002b
        L_0x0020:
            java.lang.String r0 = "DPP"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x002a
            r4 = 0
            goto L_0x002b
        L_0x002a:
            r4 = -1
        L_0x002b:
            if (r4 == 0) goto L_0x0034
            if (r4 == r2) goto L_0x0030
            goto L_0x0037
        L_0x0030:
            r3.handleZxingWifiFormat()
            goto L_0x0037
        L_0x0034:
            r3.handleWifiDpp()
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.wifi.dpp.WifiDppQrCodeScannerFragment.handleSuccessfulResult(java.lang.String):void");
    }

    private void handleWifiDpp() {
        Message obtainMessage = this.mHandler.obtainMessage(3);
        obtainMessage.obj = new WifiQrCode(this.mWifiQrCode.getQrCode());
        this.mHandler.sendMessageDelayed(obtainMessage, 1000);
    }

    private void handleZxingWifiFormat() {
        Message obtainMessage = this.mHandler.obtainMessage(4);
        obtainMessage.obj = new WifiQrCode(this.mWifiQrCode.getQrCode()).getWifiNetworkConfig();
        this.mHandler.sendMessageDelayed(obtainMessage, 1000);
    }

    public void handleCameraFailure() {
        destroyCamera();
    }

    private void initCamera(SurfaceTexture surfaceTexture) {
        if (this.mCamera == null) {
            this.mCamera = new QrCamera(getContext(), this);
            if (isGoingInitiator()) {
                QrDecorateView qrDecorateView = this.mDecorateView;
                if (qrDecorateView != null) {
                    qrDecorateView.setFocused(true);
                    return;
                }
                return;
            }
            this.mCamera.start(surfaceTexture);
        }
    }

    private void destroyCamera() {
        QrCamera qrCamera = this.mCamera;
        if (qrCamera != null) {
            qrCamera.stop();
            this.mCamera = null;
        }
    }

    private void showErrorMessage(int i) {
        this.mHandler.obtainMessage(2, getString(i)).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void showErrorMessageAndRestartCamera(int i) {
        Message obtainMessage = this.mHandler.obtainMessage(2, getString(i));
        obtainMessage.arg1 = 1;
        obtainMessage.sendToTarget();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("key_is_configurator_mode", this.mIsConfiguratorMode);
        bundle.putInt("key_latest_error_code", this.mLatestStatusCode);
        bundle.putParcelable("key_wifi_configuration", this.mEnrolleeWifiConfiguration);
        super.onSaveInstanceState(bundle);
    }

    private class EasyConnectEnrolleeStatusCallback extends EasyConnectStatusCallback {
        public void onConfiguratorSuccess(int i) {
        }

        public void onProgress(int i) {
        }

        private EasyConnectEnrolleeStatusCallback() {
        }

        public void onEnrolleeSuccess(int i) {
            WifiManager wifiManager = (WifiManager) WifiDppQrCodeScannerFragment.this.getContext().getSystemService(WifiManager.class);
            for (WifiConfiguration wifiConfiguration : wifiManager.getPrivilegedConfiguredNetworks()) {
                if (wifiConfiguration.networkId == i) {
                    int unused = WifiDppQrCodeScannerFragment.this.mLatestStatusCode = 1;
                    WifiConfiguration unused2 = WifiDppQrCodeScannerFragment.this.mEnrolleeWifiConfiguration = wifiConfiguration;
                    wifiManager.connect(wifiConfiguration, WifiDppQrCodeScannerFragment.this);
                    return;
                }
            }
            Log.e("WifiDppQrCodeScanner", "Invalid networkId " + i);
            int unused3 = WifiDppQrCodeScannerFragment.this.mLatestStatusCode = -7;
            WifiDppQrCodeScannerFragment.this.updateEnrolleeSummary();
            WifiDppQrCodeScannerFragment.this.showErrorMessageAndRestartCamera(C1715R.string.wifi_dpp_check_connection_try_again);
        }

        public void onFailure(int i) {
            Log.d("WifiDppQrCodeScanner", "EasyConnectEnrolleeStatusCallback.onFailure " + i);
            int i2 = C1715R.string.wifi_dpp_failure_authentication_or_configuration;
            switch (i) {
                case -9:
                    throw new IllegalStateException("EASY_CONNECT_EVENT_FAILURE_INVALID_NETWORK should be a configurator only error");
                case -8:
                    throw new IllegalStateException("EASY_CONNECT_EVENT_FAILURE_NOT_SUPPORTED should be a configurator only error");
                case -7:
                    i2 = C1715R.string.wifi_dpp_failure_generic;
                    break;
                case -6:
                    i2 = C1715R.string.wifi_dpp_failure_timeout;
                    break;
                case -5:
                    if (i != WifiDppQrCodeScannerFragment.this.mLatestStatusCode) {
                        int unused = WifiDppQrCodeScannerFragment.this.mLatestStatusCode = i;
                        ((WifiManager) WifiDppQrCodeScannerFragment.this.getContext().getSystemService(WifiManager.class)).stopEasyConnectSession();
                        WifiDppQrCodeScannerFragment wifiDppQrCodeScannerFragment = WifiDppQrCodeScannerFragment.this;
                        wifiDppQrCodeScannerFragment.startWifiDppEnrolleeInitiator(wifiDppQrCodeScannerFragment.mWifiQrCode);
                        return;
                    }
                    throw new IllegalStateException("stopEasyConnectSession and try again forEASY_CONNECT_EVENT_FAILURE_BUSY but still failed");
                case -4:
                case -2:
                    break;
                case -3:
                    i2 = C1715R.string.wifi_dpp_failure_not_compatible;
                    break;
                case -1:
                    i2 = C1715R.string.wifi_dpp_could_not_detect_valid_qr_code;
                    break;
                default:
                    throw new IllegalStateException("Unexpected Wi-Fi DPP error");
            }
            int unused2 = WifiDppQrCodeScannerFragment.this.mLatestStatusCode = i;
            WifiDppQrCodeScannerFragment.this.updateEnrolleeSummary();
            WifiDppQrCodeScannerFragment.this.showErrorMessageAndRestartCamera(i2);
        }
    }

    /* access modifiers changed from: private */
    public void startWifiDppEnrolleeInitiator(WifiQrCode wifiQrCode) {
        ((WifiDppInitiatorViewModel) ViewModelProviders.m3of(this).get(WifiDppInitiatorViewModel.class)).startEasyConnectAsEnrolleeInitiator(wifiQrCode.getQrCode());
    }

    public void onSuccess() {
        Intent intent = new Intent();
        intent.putExtra("wifi_configuration", this.mEnrolleeWifiConfiguration);
        FragmentActivity activity = getActivity();
        activity.setResult(-1, intent);
        activity.finish();
    }

    public void onFailure(int i) {
        Log.d("WifiDppQrCodeScanner", "Wi-Fi connect onFailure reason - " + i);
        showErrorMessageAndRestartCamera(C1715R.string.wifi_dpp_check_connection_try_again);
    }

    private boolean isGoingInitiator() {
        return ((WifiDppInitiatorViewModel) ViewModelProviders.m3of(this).get(WifiDppInitiatorViewModel.class)).isGoingInitiator();
    }

    /* access modifiers changed from: private */
    public void restartCamera() {
        QrCamera qrCamera = this.mCamera;
        if (qrCamera == null) {
            Log.d("WifiDppQrCodeScanner", "mCamera is not available for restarting camera");
            return;
        }
        if (qrCamera.isDecodeTaskAlive()) {
            this.mCamera.stop();
        }
        SurfaceTexture surfaceTexture = this.mTextureView.getSurfaceTexture();
        if (surfaceTexture != null) {
            this.mCamera.start(surfaceTexture);
            return;
        }
        throw new IllegalStateException("SurfaceTexture is not ready for restarting camera");
    }

    /* access modifiers changed from: private */
    public void updateEnrolleeSummary() {
        String str;
        if (isGoingInitiator()) {
            this.mSummary.setText(C1715R.string.wifi_dpp_connecting);
            return;
        }
        if (TextUtils.isEmpty(this.mSsid)) {
            str = getString(C1715R.string.wifi_dpp_scan_qr_code_join_unknown_network, this.mSsid);
        } else {
            str = getString(C1715R.string.wifi_dpp_scan_qr_code_join_network, this.mSsid);
        }
        this.mSummary.setText(str);
    }

    /* access modifiers changed from: protected */
    public boolean isDecodeTaskAlive() {
        QrCamera qrCamera = this.mCamera;
        return qrCamera != null && qrCamera.isDecodeTaskAlive();
    }
}
