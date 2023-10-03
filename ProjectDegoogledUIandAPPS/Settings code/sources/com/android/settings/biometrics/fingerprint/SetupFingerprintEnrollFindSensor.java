package com.android.settings.biometrics.fingerprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.android.settings.SetupWizardUtils;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class SetupFingerprintEnrollFindSensor extends FingerprintEnrollFindSensor {
    /* access modifiers changed from: protected */
    public int getContentView() {
        return C1715R.layout.fingerprint_enroll_find_sensor;
    }

    public int getMetricsCategory() {
        return 247;
    }

    /* access modifiers changed from: protected */
    public Intent getFingerprintEnrollingIntent() {
        Intent intent = new Intent(this, SetupFingerprintEnrollEnrolling.class);
        intent.putExtra("hw_auth_token", this.mToken);
        int i = this.mUserId;
        if (i != -10000) {
            intent.putExtra("android.intent.extra.USER_ID", i);
        }
        SetupWizardUtils.copySetupExtras(getIntent(), intent);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onSkipButtonClick(View view) {
        new SkipFingerprintDialog().show(getSupportFragmentManager());
    }

    public static class SkipFingerprintDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        public int getMetricsCategory() {
            return 573;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            return onCreateDialogBuilder().create();
        }

        public AlertDialog.Builder onCreateDialogBuilder() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle((int) C1715R.string.setup_fingerprint_enroll_skip_title);
            builder.setPositiveButton((int) C1715R.string.skip_anyway_button_label, (DialogInterface.OnClickListener) this);
            builder.setNegativeButton((int) C1715R.string.go_back_button_label, (DialogInterface.OnClickListener) this);
            builder.setMessage((int) C1715R.string.setup_fingerprint_enroll_skip_after_adding_lock_text);
            return builder;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            FragmentActivity activity;
            if (i == -1 && (activity = getActivity()) != null) {
                activity.setResult(2);
                activity.finish();
            }
        }

        public void show(FragmentManager fragmentManager) {
            show(fragmentManager, "skip_dialog");
        }
    }
}
