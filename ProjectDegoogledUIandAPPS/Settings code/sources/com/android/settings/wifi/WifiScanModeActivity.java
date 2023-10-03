package com.android.settings.wifi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class WifiScanModeActivity extends FragmentActivity {
    private String mApp;
    private DialogFragment mDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (bundle != null) {
            this.mApp = bundle.getString("app");
        } else if (intent == null || !intent.getAction().equals("android.net.wifi.action.REQUEST_SCAN_ALWAYS_AVAILABLE")) {
            finish();
            return;
        } else {
            this.mApp = getCallingPackage();
            try {
                PackageManager packageManager = getPackageManager();
                this.mApp = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mApp, 0));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        createDialog();
    }

    private void createDialog() {
        if (this.mDialog == null) {
            this.mDialog = AlertDialogFragment.newInstance(this.mApp);
            this.mDialog.show(getSupportFragmentManager(), "dialog");
        }
    }

    private void dismissDialog() {
        DialogFragment dialogFragment = this.mDialog;
        if (dialogFragment != null) {
            dialogFragment.dismiss();
            this.mDialog = null;
        }
    }

    /* access modifiers changed from: private */
    public void doPositiveClick() {
        Settings.Global.putInt(getContentResolver(), "wifi_scan_always_enabled", 1);
        setResult(-1);
        finish();
    }

    /* access modifiers changed from: private */
    public void doNegativeClick() {
        setResult(0);
        finish();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("app", this.mApp);
    }

    public void onPause() {
        super.onPause();
        dismissDialog();
    }

    public void onResume() {
        super.onResume();
        createDialog();
    }

    public static class AlertDialogFragment extends InstrumentedDialogFragment {
        private final String mApp;

        public int getMetricsCategory() {
            return 543;
        }

        static AlertDialogFragment newInstance(String str) {
            return new AlertDialogFragment(str);
        }

        public AlertDialogFragment(String str) {
            this.mApp = str;
        }

        public AlertDialogFragment() {
            this.mApp = null;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            String str;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            if (TextUtils.isEmpty(this.mApp)) {
                str = getString(C1715R.string.wifi_scan_always_turn_on_message_unknown);
            } else {
                str = getString(C1715R.string.wifi_scan_always_turnon_message, this.mApp);
            }
            builder.setMessage((CharSequence) str);
            builder.setPositiveButton((int) C1715R.string.wifi_scan_always_confirm_allow, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((WifiScanModeActivity) AlertDialogFragment.this.getActivity()).doPositiveClick();
                }
            });
            builder.setNegativeButton((int) C1715R.string.wifi_scan_always_confirm_deny, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((WifiScanModeActivity) AlertDialogFragment.this.getActivity()).doNegativeClick();
                }
            });
            return builder.create();
        }

        public void onCancel(DialogInterface dialogInterface) {
            ((WifiScanModeActivity) getActivity()).doNegativeClick();
        }
    }
}
