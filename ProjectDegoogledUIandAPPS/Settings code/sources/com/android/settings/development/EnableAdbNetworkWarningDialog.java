package com.android.settings.development;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class EnableAdbNetworkWarningDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    public int getMetricsCategory() {
        return 1999;
    }

    public static void show(Fragment fragment) {
        FragmentManager supportFragmentManager = fragment.getActivity().getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("EnableAdbNetworkWarningDialog") == null) {
            EnableAdbNetworkWarningDialog enableAdbNetworkWarningDialog = new EnableAdbNetworkWarningDialog();
            enableAdbNetworkWarningDialog.setTargetFragment(fragment, 0);
            enableAdbNetworkWarningDialog.show(supportFragmentManager, "EnableAdbNetworkWarningDialog");
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle((int) C1715R.string.adb_over_network_warning_title);
        builder.setMessage((int) C1715R.string.adb_over_network_warning);
        builder.setPositiveButton(17039379, (DialogInterface.OnClickListener) this);
        builder.setNegativeButton(17039369, (DialogInterface.OnClickListener) this);
        return builder.create();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AdbNetworkDialogHost adbNetworkDialogHost = (AdbNetworkDialogHost) getTargetFragment();
        if (adbNetworkDialogHost != null) {
            if (i == -1) {
                adbNetworkDialogHost.onEnableAdbNetworkDialogConfirmed();
            } else {
                adbNetworkDialogHost.onEnableAdbNetworkDialogDismissed();
            }
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        AdbDialogHost adbDialogHost = (AdbDialogHost) getTargetFragment();
        if (adbDialogHost != null) {
            adbDialogHost.onEnableAdbDialogDismissed();
        }
    }
}
