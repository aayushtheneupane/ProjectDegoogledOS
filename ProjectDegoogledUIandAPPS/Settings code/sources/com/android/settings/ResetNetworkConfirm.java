package com.android.settings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkPolicyManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.os.UserHandle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.android.ims.ImsManager;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.havoc.config.center.C1715R;

public class ResetNetworkConfirm extends InstrumentedFragment {
    Activity mActivity;
    /* access modifiers changed from: private */
    public AlertDialog mAlertDialog;
    View mContentView;
    boolean mEraseEsim;
    View.OnClickListener mFinalClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (!Utils.isMonkeyRunning()) {
                ResetNetworkConfirm resetNetworkConfirm = ResetNetworkConfirm.this;
                ProgressDialog unused = resetNetworkConfirm.mProgressDialog = resetNetworkConfirm.getProgressDialog(resetNetworkConfirm.mActivity);
                ResetNetworkConfirm.this.mProgressDialog.show();
                ResetNetworkConfirm resetNetworkConfirm2 = ResetNetworkConfirm.this;
                resetNetworkConfirm2.mResetNetworkTask = new ResetNetworkTask(resetNetworkConfirm2.mActivity);
                ResetNetworkConfirm.this.mResetNetworkTask.execute(new Void[0]);
            }
        }
    };
    /* access modifiers changed from: private */
    public ProgressDialog mProgressDialog;
    ResetNetworkTask mResetNetworkTask;
    /* access modifiers changed from: private */
    public int mSubId = -1;

    public int getMetricsCategory() {
        return 84;
    }

    private class ResetNetworkTask extends AsyncTask<Void, Void, Boolean> {
        private final Context mContext;
        private final String mPackageName;

        ResetNetworkTask(Context context) {
            this.mContext = context;
            this.mPackageName = context.getPackageName();
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void... voidArr) {
            BluetoothAdapter adapter;
            ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
            if (connectivityManager != null) {
                connectivityManager.factoryReset();
            }
            WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
            if (wifiManager != null) {
                wifiManager.factoryReset();
            }
            ResetNetworkConfirm.this.p2pFactoryReset(this.mContext);
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
            if (telephonyManager != null) {
                telephonyManager.factoryReset(ResetNetworkConfirm.this.mSubId);
            }
            NetworkPolicyManager networkPolicyManager = (NetworkPolicyManager) this.mContext.getSystemService("netpolicy");
            if (networkPolicyManager != null) {
                networkPolicyManager.factoryReset(telephonyManager.getSubscriberId(ResetNetworkConfirm.this.mSubId));
            }
            BluetoothManager bluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
            if (!(bluetoothManager == null || (adapter = bluetoothManager.getAdapter()) == null)) {
                adapter.factoryReset();
            }
            ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(ResetNetworkConfirm.this.mSubId)).factoryReset();
            ResetNetworkConfirm.this.restoreDefaultApn(this.mContext);
            if (ResetNetworkConfirm.this.mEraseEsim) {
                return Boolean.valueOf(RecoverySystem.wipeEuiccData(this.mContext, this.mPackageName));
            }
            return true;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            ResetNetworkConfirm.this.mProgressDialog.dismiss();
            if (bool.booleanValue()) {
                Toast.makeText(this.mContext, C1715R.string.reset_network_complete_toast, 0).show();
                return;
            }
            ResetNetworkConfirm resetNetworkConfirm = ResetNetworkConfirm.this;
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            builder.setTitle((int) C1715R.string.reset_esim_error_title);
            builder.setMessage((int) C1715R.string.reset_esim_error_msg);
            builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
            AlertDialog unused = resetNetworkConfirm.mAlertDialog = builder.show();
        }
    }

    /* access modifiers changed from: package-private */
    public void p2pFactoryReset(Context context) {
        WifiP2pManager.Channel initialize;
        WifiP2pManager wifiP2pManager = (WifiP2pManager) context.getSystemService("wifip2p");
        if (wifiP2pManager != null && (initialize = wifiP2pManager.initialize(context.getApplicationContext(), context.getMainLooper(), (WifiP2pManager.ChannelListener) null)) != null) {
            wifiP2pManager.factoryReset(initialize, (WifiP2pManager.ActionListener) null);
        }
    }

    /* access modifiers changed from: private */
    public ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(C1715R.string.master_clear_progress_text));
        return progressDialog;
    }

    /* access modifiers changed from: private */
    public void restoreDefaultApn(Context context) {
        Uri parse = Uri.parse("content://telephony/carriers/restore");
        if (SubscriptionManager.isUsableSubIdValue(this.mSubId)) {
            parse = Uri.withAppendedPath(parse, "subId/" + String.valueOf(this.mSubId));
        }
        context.getContentResolver().delete(parse, (String) null, (String[]) null);
    }

    private void establishFinalConfirmationState() {
        this.mContentView.findViewById(C1715R.C1718id.execute_reset_network).setOnClickListener(this.mFinalClickListener);
    }

    /* access modifiers changed from: package-private */
    public void setSubtitle() {
        if (this.mEraseEsim) {
            ((TextView) this.mContentView.findViewById(C1715R.C1718id.reset_network_confirm)).setText(C1715R.string.reset_network_final_desc_esim);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mActivity, "no_network_reset", UserHandle.myUserId());
        if (RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mActivity, "no_network_reset", UserHandle.myUserId())) {
            return layoutInflater.inflate(C1715R.layout.network_reset_disallowed_screen, (ViewGroup) null);
        }
        if (checkIfRestrictionEnforced != null) {
            AlertDialog.Builder prepareDialogBuilder = new ActionDisabledByAdminDialogHelper(this.mActivity).prepareDialogBuilder("no_network_reset", checkIfRestrictionEnforced);
            prepareDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public final void onDismiss(DialogInterface dialogInterface) {
                    ResetNetworkConfirm.this.lambda$onCreateView$0$ResetNetworkConfirm(dialogInterface);
                }
            });
            prepareDialogBuilder.show();
            return new View(this.mActivity);
        }
        this.mContentView = layoutInflater.inflate(C1715R.layout.reset_network_confirm, (ViewGroup) null);
        establishFinalConfirmationState();
        setSubtitle();
        return this.mContentView;
    }

    public /* synthetic */ void lambda$onCreateView$0$ResetNetworkConfirm(DialogInterface dialogInterface) {
        this.mActivity.finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mSubId = arguments.getInt("subscription", -1);
            this.mEraseEsim = arguments.getBoolean("erase_esim");
        }
        this.mActivity = getActivity();
    }

    public void onDestroy() {
        ResetNetworkTask resetNetworkTask = this.mResetNetworkTask;
        if (resetNetworkTask != null) {
            resetNetworkTask.cancel(true);
            this.mResetNetworkTask = null;
        }
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }
}
