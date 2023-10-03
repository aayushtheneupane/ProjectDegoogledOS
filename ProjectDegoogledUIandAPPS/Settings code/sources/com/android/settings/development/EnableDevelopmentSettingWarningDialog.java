package com.android.settings.development;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class EnableDevelopmentSettingWarningDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    public int getMetricsCategory() {
        return 1219;
    }

    public static void show(DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment) {
        EnableDevelopmentSettingWarningDialog enableDevelopmentSettingWarningDialog = new EnableDevelopmentSettingWarningDialog();
        enableDevelopmentSettingWarningDialog.setTargetFragment(developmentSettingsDashboardFragment, 0);
        FragmentManager supportFragmentManager = developmentSettingsDashboardFragment.getActivity().getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("EnableDevSettingDlg") == null) {
            enableDevelopmentSettingWarningDialog.show(supportFragmentManager, "EnableDevSettingDlg");
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage((int) C1715R.string.dev_settings_warning_message);
        builder.setTitle((int) C1715R.string.dev_settings_warning_title);
        builder.setPositiveButton(17039379, (DialogInterface.OnClickListener) this);
        builder.setNegativeButton(17039369, (DialogInterface.OnClickListener) this);
        return builder.create();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment = (DevelopmentSettingsDashboardFragment) getTargetFragment();
        if (i == -1) {
            developmentSettingsDashboardFragment.onEnableDevelopmentOptionsConfirmed();
        } else {
            developmentSettingsDashboardFragment.onEnableDevelopmentOptionsRejected();
        }
    }
}
