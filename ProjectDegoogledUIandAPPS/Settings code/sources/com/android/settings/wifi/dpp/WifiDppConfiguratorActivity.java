package com.android.settings.wifi.dpp;

import android.app.ActionBar;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.settings.core.InstrumentedActivity;
import com.android.settings.wifi.dpp.WifiDppAddDeviceFragment;
import com.android.settings.wifi.dpp.WifiDppQrCodeGeneratorFragment;
import com.android.settings.wifi.dpp.WifiDppQrCodeScannerFragment;
import com.android.settings.wifi.dpp.WifiNetworkConfig;
import com.android.settings.wifi.dpp.WifiNetworkListFragment;
import com.havoc.config.center.C1715R;

public class WifiDppConfiguratorActivity extends InstrumentedActivity implements WifiNetworkConfig.Retriever, WifiDppQrCodeGeneratorFragment.OnQrCodeGeneratorFragmentAddButtonClickedListener, WifiDppQrCodeScannerFragment.OnScanWifiDppSuccessListener, WifiDppAddDeviceFragment.OnClickChooseDifferentNetworkListener, WifiNetworkListFragment.OnChooseNetworkListener {
    private FragmentManager mFragmentManager;
    private boolean mIsTest;
    private WifiQrCode mWifiDppQrCode;
    private WifiNetworkConfig mWifiNetworkConfig;

    public int getMetricsCategory() {
        return 1595;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1715R.layout.wifi_dpp_activity);
        this.mFragmentManager = getSupportFragmentManager();
        if (bundle != null) {
            this.mWifiDppQrCode = WifiQrCode.getValidWifiDppQrCodeOrNull(bundle.getString("key_qr_code"));
            this.mWifiNetworkConfig = WifiNetworkConfig.getValidConfigOrNull(bundle.getString("key_wifi_security"), bundle.getString("key_wifi_ssid"), bundle.getString("key_wifi_preshared_key"), bundle.getBoolean("key_wifi_hidden_ssid"), bundle.getInt("key_wifi_network_id"), bundle.getBoolean("key_is_hotspot"));
        } else {
            handleIntent(getIntent());
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0.0f);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleIntent(android.content.Intent r7) {
        /*
            r6 = this;
            java.lang.String r0 = r7.getAction()
            int r1 = r0.hashCode()
            r2 = -902592152(0xffffffffca338968, float:-2941530.0)
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == r2) goto L_0x002f
            r2 = 360935630(0x158370ce, float:5.308845E-26)
            if (r1 == r2) goto L_0x0025
            r2 = 1361318585(0x512412b9, float:4.4043047E10)
            if (r1 == r2) goto L_0x001b
            goto L_0x0039
        L_0x001b:
            java.lang.String r1 = "android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_SCANNER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0039
            r0 = r5
            goto L_0x003a
        L_0x0025:
            java.lang.String r1 = "android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_GENERATOR"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0039
            r0 = r4
            goto L_0x003a
        L_0x002f:
            java.lang.String r1 = "android.settings.PROCESS_WIFI_EASY_CONNECT_URI"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0039
            r0 = r3
            goto L_0x003a
        L_0x0039:
            r0 = -1
        L_0x003a:
            if (r0 == 0) goto L_0x0098
            if (r0 == r4) goto L_0x008b
            java.lang.String r1 = "WifiDppConfiguratorActivity"
            if (r0 == r3) goto L_0x0048
            java.lang.String r7 = "Launch with an invalid action"
            android.util.Log.e(r1, r7)
            goto L_0x00a5
        L_0x0048:
            android.net.Uri r0 = r7.getData()
            if (r0 != 0) goto L_0x0050
            r0 = 0
            goto L_0x0054
        L_0x0050:
            java.lang.String r0 = r0.toString()
        L_0x0054:
            java.lang.String r2 = "test"
            boolean r7 = r7.getBooleanExtra(r2, r5)
            r6.mIsTest = r7
            com.android.settings.wifi.dpp.WifiQrCode r7 = com.android.settings.wifi.dpp.WifiQrCode.getValidWifiDppQrCodeOrNull(r0)
            r6.mWifiDppQrCode = r7
            boolean r7 = com.android.settings.wifi.dpp.WifiDppUtils.isWifiDppEnabled(r6)
            if (r7 != 0) goto L_0x006d
            java.lang.String r0 = "Device doesn't support Wifi DPP"
            android.util.Log.d(r1, r0)
        L_0x006d:
            com.android.settings.wifi.dpp.WifiQrCode r0 = r6.mWifiDppQrCode
            if (r0 == 0) goto L_0x00a5
            if (r7 != 0) goto L_0x0074
            goto L_0x00a5
        L_0x0074:
            com.android.settings.wifi.dpp.WifiNetworkConfig r7 = r6.getConnectedWifiNetworkConfigOrNull()
            if (r7 == 0) goto L_0x0087
            boolean r0 = r7.isSupportWifiDpp(r6)
            if (r0 != 0) goto L_0x0081
            goto L_0x0087
        L_0x0081:
            r6.mWifiNetworkConfig = r7
            r6.showAddDeviceFragment(r5)
            goto L_0x00a4
        L_0x0087:
            r6.showChooseSavedWifiNetworkFragment(r5)
            goto L_0x00a4
        L_0x008b:
            com.android.settings.wifi.dpp.WifiNetworkConfig r7 = com.android.settings.wifi.dpp.WifiNetworkConfig.getValidConfigOrNull(r7)
            if (r7 != 0) goto L_0x0092
            goto L_0x00a5
        L_0x0092:
            r6.mWifiNetworkConfig = r7
            r6.showQrCodeGeneratorFragment()
            goto L_0x00a4
        L_0x0098:
            com.android.settings.wifi.dpp.WifiNetworkConfig r7 = com.android.settings.wifi.dpp.WifiNetworkConfig.getValidConfigOrNull(r7)
            if (r7 != 0) goto L_0x009f
            goto L_0x00a5
        L_0x009f:
            r6.mWifiNetworkConfig = r7
            r6.showQrCodeScannerFragment(r5)
        L_0x00a4:
            r4 = r5
        L_0x00a5:
            if (r4 == 0) goto L_0x00aa
            r6.finish()
        L_0x00aa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.wifi.dpp.WifiDppConfiguratorActivity.handleIntent(android.content.Intent):void");
    }

    private void showQrCodeScannerFragment(boolean z) {
        WifiDppQrCodeScannerFragment wifiDppQrCodeScannerFragment = (WifiDppQrCodeScannerFragment) this.mFragmentManager.findFragmentByTag("qr_code_scanner_fragment");
        if (wifiDppQrCodeScannerFragment == null) {
            WifiDppQrCodeScannerFragment wifiDppQrCodeScannerFragment2 = new WifiDppQrCodeScannerFragment();
            FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
            beginTransaction.replace(C1715R.C1718id.fragment_container, wifiDppQrCodeScannerFragment2, "qr_code_scanner_fragment");
            if (z) {
                beginTransaction.addToBackStack((String) null);
            }
            beginTransaction.commit();
        } else if (!wifiDppQrCodeScannerFragment.isVisible()) {
            this.mFragmentManager.popBackStackImmediate();
        }
    }

    private void showQrCodeGeneratorFragment() {
        WifiDppQrCodeGeneratorFragment wifiDppQrCodeGeneratorFragment = (WifiDppQrCodeGeneratorFragment) this.mFragmentManager.findFragmentByTag("qr_code_generator_fragment");
        if (wifiDppQrCodeGeneratorFragment == null) {
            WifiDppQrCodeGeneratorFragment wifiDppQrCodeGeneratorFragment2 = new WifiDppQrCodeGeneratorFragment();
            FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
            beginTransaction.replace(C1715R.C1718id.fragment_container, wifiDppQrCodeGeneratorFragment2, "qr_code_generator_fragment");
            beginTransaction.commit();
        } else if (!wifiDppQrCodeGeneratorFragment.isVisible()) {
            this.mFragmentManager.popBackStackImmediate();
        }
    }

    private void showChooseSavedWifiNetworkFragment(boolean z) {
        WifiDppChooseSavedWifiNetworkFragment wifiDppChooseSavedWifiNetworkFragment = (WifiDppChooseSavedWifiNetworkFragment) this.mFragmentManager.findFragmentByTag("choose_saved_wifi_network_fragment");
        if (wifiDppChooseSavedWifiNetworkFragment == null) {
            WifiDppChooseSavedWifiNetworkFragment wifiDppChooseSavedWifiNetworkFragment2 = new WifiDppChooseSavedWifiNetworkFragment();
            if (this.mIsTest) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("test", true);
                wifiDppChooseSavedWifiNetworkFragment2.setArguments(bundle);
            }
            FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
            beginTransaction.replace(C1715R.C1718id.fragment_container, wifiDppChooseSavedWifiNetworkFragment2, "choose_saved_wifi_network_fragment");
            if (z) {
                beginTransaction.addToBackStack((String) null);
            }
            beginTransaction.commit();
        } else if (!wifiDppChooseSavedWifiNetworkFragment.isVisible()) {
            this.mFragmentManager.popBackStackImmediate();
        }
    }

    private void showAddDeviceFragment(boolean z) {
        WifiDppAddDeviceFragment wifiDppAddDeviceFragment = (WifiDppAddDeviceFragment) this.mFragmentManager.findFragmentByTag("add_device_fragment");
        if (wifiDppAddDeviceFragment == null) {
            WifiDppAddDeviceFragment wifiDppAddDeviceFragment2 = new WifiDppAddDeviceFragment();
            FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
            beginTransaction.replace(C1715R.C1718id.fragment_container, wifiDppAddDeviceFragment2, "add_device_fragment");
            if (z) {
                beginTransaction.addToBackStack((String) null);
            }
            beginTransaction.commit();
        } else if (!wifiDppAddDeviceFragment.isVisible()) {
            this.mFragmentManager.popBackStackImmediate();
        }
    }

    public WifiNetworkConfig getWifiNetworkConfig() {
        return this.mWifiNetworkConfig;
    }

    public WifiQrCode getWifiDppQrCode() {
        return this.mWifiDppQrCode;
    }

    /* access modifiers changed from: package-private */
    public boolean setWifiNetworkConfig(WifiNetworkConfig wifiNetworkConfig) {
        if (!WifiNetworkConfig.isValidConfig(wifiNetworkConfig)) {
            return false;
        }
        this.mWifiNetworkConfig = new WifiNetworkConfig(wifiNetworkConfig);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setWifiDppQrCode(WifiQrCode wifiQrCode) {
        if (wifiQrCode == null || !"DPP".equals(wifiQrCode.getScheme())) {
            return false;
        }
        this.mWifiDppQrCode = new WifiQrCode(wifiQrCode.getQrCode());
        return true;
    }

    public boolean onNavigateUp() {
        if (this.mFragmentManager.popBackStackImmediate()) {
            return true;
        }
        finish();
        return true;
    }

    public void onScanWifiDppSuccess(WifiQrCode wifiQrCode) {
        this.mWifiDppQrCode = wifiQrCode;
        showAddDeviceFragment(true);
    }

    public void onClickChooseDifferentNetwork() {
        showChooseSavedWifiNetworkFragment(true);
    }

    public void onSaveInstanceState(Bundle bundle) {
        WifiQrCode wifiQrCode = this.mWifiDppQrCode;
        if (wifiQrCode != null) {
            bundle.putString("key_qr_code", wifiQrCode.getQrCode());
        }
        WifiNetworkConfig wifiNetworkConfig = this.mWifiNetworkConfig;
        if (wifiNetworkConfig != null) {
            bundle.putString("key_wifi_security", wifiNetworkConfig.getSecurity());
            bundle.putString("key_wifi_ssid", this.mWifiNetworkConfig.getSsid());
            bundle.putString("key_wifi_preshared_key", this.mWifiNetworkConfig.getPreSharedKey());
            bundle.putBoolean("key_wifi_hidden_ssid", this.mWifiNetworkConfig.getHiddenSsid());
            bundle.putInt("key_wifi_network_id", this.mWifiNetworkConfig.getNetworkId());
            bundle.putBoolean("key_is_hotspot", this.mWifiNetworkConfig.isHotspot());
        }
        super.onSaveInstanceState(bundle);
    }

    public void onChooseNetwork(WifiNetworkConfig wifiNetworkConfig) {
        this.mWifiNetworkConfig = new WifiNetworkConfig(wifiNetworkConfig);
        showAddDeviceFragment(true);
    }

    private WifiNetworkConfig getConnectedWifiNetworkConfigOrNull() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) getSystemService(WifiManager.class);
        if (!wifiManager.isWifiEnabled() || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return null;
        }
        int networkId = connectionInfo.getNetworkId();
        for (WifiConfiguration next : wifiManager.getConfiguredNetworks()) {
            if (next.networkId == networkId) {
                return WifiNetworkConfig.getValidConfigOrNull(WifiDppUtils.getSecurityString(next), next.getPrintableSsid(), next.preSharedKey, next.hiddenSSID, next.networkId, false);
            }
        }
        return null;
    }
}
