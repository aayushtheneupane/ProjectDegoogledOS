package com.android.settings.password;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class SetupSkipDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    public int getMetricsCategory() {
        return 573;
    }

    public static SetupSkipDialog newInstance(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        SetupSkipDialog setupSkipDialog = new SetupSkipDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean("frp_supported", z);
        bundle.putBoolean("lock_type_pattern", z2);
        bundle.putBoolean("lock_type_alphanumeric", z3);
        bundle.putBoolean("for_fingerprint", z4);
        bundle.putBoolean("for_face", z5);
        setupSkipDialog.setArguments(bundle);
        return setupSkipDialog;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return onCreateDialogBuilder().create();
    }

    public AlertDialog.Builder onCreateDialogBuilder() {
        int i;
        Bundle arguments = getArguments();
        boolean z = arguments.getBoolean("for_face");
        boolean z2 = arguments.getBoolean("for_fingerprint");
        if (z || z2) {
            if (arguments.getBoolean("lock_type_pattern")) {
                i = C1715R.string.lock_screen_pattern_skip_title;
            } else {
                i = arguments.getBoolean("lock_type_alphanumeric") ? C1715R.string.lock_screen_password_skip_title : C1715R.string.lock_screen_pin_skip_title;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setPositiveButton((int) C1715R.string.skip_lock_screen_dialog_button_label, (DialogInterface.OnClickListener) this);
            builder.setNegativeButton((int) C1715R.string.cancel_lock_screen_dialog_button_label, (DialogInterface.OnClickListener) this);
            builder.setTitle(i);
            builder.setMessage(z ? C1715R.string.face_lock_screen_setup_skip_dialog_text : C1715R.string.fingerprint_lock_screen_setup_skip_dialog_text);
            return builder;
        }
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
        builder2.setPositiveButton((int) C1715R.string.skip_anyway_button_label, (DialogInterface.OnClickListener) this);
        builder2.setNegativeButton((int) C1715R.string.go_back_button_label, (DialogInterface.OnClickListener) this);
        builder2.setTitle((int) C1715R.string.lock_screen_intro_skip_title);
        builder2.setMessage(arguments.getBoolean("frp_supported") ? C1715R.string.lock_screen_intro_skip_dialog_text_frp : C1715R.string.lock_screen_intro_skip_dialog_text);
        return builder2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            FragmentActivity activity = getActivity();
            activity.setResult(11);
            activity.finish();
        }
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "skip_dialog");
    }
}
