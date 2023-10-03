package com.android.settings.wifi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class NetworkRequestErrorDialogFragment extends InstrumentedDialogFragment {
    private WifiManager.NetworkRequestUserSelectionCallback mRejectCallback;

    public enum ERROR_DIALOG_TYPE {
        TIME_OUT,
        ABORT
    }

    public int getMetricsCategory() {
        return 1373;
    }

    public static NetworkRequestErrorDialogFragment newInstance() {
        return new NetworkRequestErrorDialogFragment();
    }

    private NetworkRequestErrorDialogFragment() {
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        rejectNetworkRequestAndFinish();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ERROR_DIALOG_TYPE error_dialog_type = ERROR_DIALOG_TYPE.TIME_OUT;
        if (getArguments() != null) {
            error_dialog_type = (ERROR_DIALOG_TYPE) getArguments().getSerializable("DIALOG_ERROR_TYPE");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (error_dialog_type == ERROR_DIALOG_TYPE.TIME_OUT) {
            builder.setMessage((int) C1715R.string.network_connection_timeout_dialog_message);
            builder.setPositiveButton((int) C1715R.string.network_connection_timeout_dialog_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    NetworkRequestErrorDialogFragment.this.lambda$onCreateDialog$0$NetworkRequestErrorDialogFragment(dialogInterface, i);
                }
            });
            builder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    NetworkRequestErrorDialogFragment.this.lambda$onCreateDialog$1$NetworkRequestErrorDialogFragment(dialogInterface, i);
                }
            });
        } else {
            builder.setMessage((int) C1715R.string.network_connection_errorstate_dialog_message);
            builder.setPositiveButton((int) C1715R.string.okay, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    NetworkRequestErrorDialogFragment.this.lambda$onCreateDialog$2$NetworkRequestErrorDialogFragment(dialogInterface, i);
                }
            });
        }
        return builder.create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$NetworkRequestErrorDialogFragment(DialogInterface dialogInterface, int i) {
        startScanningDialog();
    }

    public /* synthetic */ void lambda$onCreateDialog$1$NetworkRequestErrorDialogFragment(DialogInterface dialogInterface, int i) {
        rejectNetworkRequestAndFinish();
    }

    public /* synthetic */ void lambda$onCreateDialog$2$NetworkRequestErrorDialogFragment(DialogInterface dialogInterface, int i) {
        rejectNetworkRequestAndFinish();
    }

    public void setRejectCallback(WifiManager.NetworkRequestUserSelectionCallback networkRequestUserSelectionCallback) {
        this.mRejectCallback = networkRequestUserSelectionCallback;
    }

    /* access modifiers changed from: protected */
    public void startScanningDialog() {
        NetworkRequestDialogFragment.newInstance().show(getActivity().getSupportFragmentManager(), NetworkRequestErrorDialogFragment.class.getSimpleName());
    }

    private void rejectNetworkRequestAndFinish() {
        if (getActivity() != null) {
            WifiManager.NetworkRequestUserSelectionCallback networkRequestUserSelectionCallback = this.mRejectCallback;
            if (networkRequestUserSelectionCallback != null) {
                networkRequestUserSelectionCallback.reject();
            }
            getActivity().finish();
        }
    }
}
