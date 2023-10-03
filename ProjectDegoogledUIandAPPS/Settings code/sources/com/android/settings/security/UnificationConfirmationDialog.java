package com.android.settings.security;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class UnificationConfirmationDialog extends InstrumentedDialogFragment {
    public int getMetricsCategory() {
        return 532;
    }

    public static UnificationConfirmationDialog newInstance(boolean z) {
        UnificationConfirmationDialog unificationConfirmationDialog = new UnificationConfirmationDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean("compliant", z);
        unificationConfirmationDialog.setArguments(bundle);
        return unificationConfirmationDialog;
    }

    public void show(SecuritySettings securitySettings) {
        FragmentManager childFragmentManager = securitySettings.getChildFragmentManager();
        if (childFragmentManager.findFragmentByTag("unification_dialog") == null) {
            show(childFragmentManager, "unification_dialog");
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        SecuritySettings securitySettings = (SecuritySettings) getParentFragment();
        boolean z = getArguments().getBoolean("compliant");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle((int) C1715R.string.lock_settings_profile_unification_dialog_title);
        builder.setMessage(z ? C1715R.string.lock_settings_profile_unification_dialog_body : C1715R.string.lock_settings_profile_unification_dialog_uncompliant_body);
        builder.setPositiveButton(z ? C1715R.string.lock_settings_profile_unification_dialog_confirm : C1715R.string.lock_settings_profile_unification_dialog_uncompliant_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                SecuritySettings.this.startUnification();
            }
        });
        builder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        ((SecuritySettings) getParentFragment()).updateUnificationPreference();
    }
}
