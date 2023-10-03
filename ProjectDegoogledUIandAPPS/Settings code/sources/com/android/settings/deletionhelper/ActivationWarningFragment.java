package com.android.settings.deletionhelper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.havoc.config.center.C1715R;

public class ActivationWarningFragment extends DialogFragment {
    public static ActivationWarningFragment newInstance() {
        return new ActivationWarningFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage((int) C1715R.string.automatic_storage_manager_activation_warning);
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
        return builder.create();
    }
}
