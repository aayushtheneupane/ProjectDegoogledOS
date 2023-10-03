package com.android.settings.wifi.details;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.wifi.WifiDialog;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.wifi.AccessPoint;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class WifiNetworkDetailsFragment extends DashboardFragment implements WifiDialog.WifiDialogListener {
    private AccessPoint mAccessPoint;
    private WifiDetailPreferenceController mWifiDetailPreferenceController;
    private List<WifiDialog.WifiDialogListener> mWifiDialogListeners = new ArrayList();

    public int getDialogMetricsCategory(int i) {
        return i == 1 ? 603 : 0;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "WifiNetworkDetailsFrg";
    }

    public int getMetricsCategory() {
        return 849;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.wifi_network_details_fragment;
    }

    public void onAttach(Context context) {
        this.mAccessPoint = new AccessPoint(context, getArguments());
        super.onAttach(context);
    }

    public Dialog onCreateDialog(int i) {
        if (getActivity() == null || this.mWifiDetailPreferenceController == null || this.mAccessPoint == null) {
            return null;
        }
        return WifiDialog.createModal(getActivity(), this, this.mAccessPoint, 2);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem add = menu.add(0, 1, 0, C1715R.string.wifi_modify);
        add.setIcon(17302742);
        add.setShowAsAction(2);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (!this.mWifiDetailPreferenceController.canModifyNetwork()) {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), RestrictedLockUtilsInternal.getDeviceOwner(getContext()));
        } else {
            showDialog(1);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mWifiDetailPreferenceController = WifiDetailPreferenceController.newInstance(this.mAccessPoint, (ConnectivityManager) context.getSystemService(ConnectivityManager.class), context, this, new Handler(Looper.getMainLooper()), getSettingsLifecycle(), (WifiManager) context.getSystemService(WifiManager.class), this.mMetricsFeatureProvider);
        arrayList.add(this.mWifiDetailPreferenceController);
        arrayList.add(new AddDevicePreferenceController(context).init(this.mAccessPoint));
        WifiMeteredPreferenceController wifiMeteredPreferenceController = new WifiMeteredPreferenceController(context, this.mAccessPoint.getConfig());
        arrayList.add(wifiMeteredPreferenceController);
        WifiPrivacyPreferenceController wifiPrivacyPreferenceController = new WifiPrivacyPreferenceController(context);
        wifiPrivacyPreferenceController.setWifiConfiguration(this.mAccessPoint.getConfig());
        wifiPrivacyPreferenceController.setIsEphemeral(this.mAccessPoint.isEphemeral());
        wifiPrivacyPreferenceController.setIsPasspoint(this.mAccessPoint.isPasspoint() || this.mAccessPoint.isPasspointConfig());
        arrayList.add(wifiPrivacyPreferenceController);
        this.mWifiDialogListeners.add(this.mWifiDetailPreferenceController);
        this.mWifiDialogListeners.add(wifiPrivacyPreferenceController);
        this.mWifiDialogListeners.add(wifiMeteredPreferenceController);
        return arrayList;
    }

    public void onSubmit(WifiDialog wifiDialog) {
        for (WifiDialog.WifiDialogListener onSubmit : this.mWifiDialogListeners) {
            onSubmit.onSubmit(wifiDialog);
        }
    }
}
