package com.android.settings.wifi.dpp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.settings.core.InstrumentedActivity;
import com.android.settings.wifi.dpp.WifiDppQrCodeScannerFragment;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.havoc.config.center.C1715R;

public class WifiDppEnrolleeActivity extends InstrumentedActivity implements WifiDppQrCodeScannerFragment.OnScanWifiDppSuccessListener {
    private FragmentManager mFragmentManager;

    public int getMetricsCategory() {
        return 1596;
    }

    public void onScanWifiDppSuccess(WifiQrCode wifiQrCode) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (WizardManagerHelper.isAnySetupWizard(getIntent())) {
            setTheme(2131951894);
        }
        setContentView(C1715R.layout.wifi_dpp_activity);
        this.mFragmentManager = getSupportFragmentManager();
        if (bundle == null) {
            handleIntent(getIntent());
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0.0f);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (((action.hashCode() == 2082201034 && action.equals("android.settings.WIFI_DPP_ENROLLEE_QR_CODE_SCANNER")) ? (char) 0 : 65535) != 0) {
            Log.e("WifiDppEnrolleeActivity", "Launch with an invalid action");
            finish();
            return;
        }
        showQrCodeScannerFragment(false, intent.getStringExtra("ssid"));
    }

    private void showQrCodeScannerFragment(boolean z, String str) {
        WifiDppQrCodeScannerFragment wifiDppQrCodeScannerFragment = (WifiDppQrCodeScannerFragment) this.mFragmentManager.findFragmentByTag("qr_code_scanner_fragment");
        if (wifiDppQrCodeScannerFragment == null) {
            WifiDppQrCodeScannerFragment wifiDppQrCodeScannerFragment2 = new WifiDppQrCodeScannerFragment(str);
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

    public boolean onNavigateUp() {
        finish();
        return true;
    }
}
