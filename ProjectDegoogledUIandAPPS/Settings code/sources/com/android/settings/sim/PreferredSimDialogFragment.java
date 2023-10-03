package com.android.settings.sim;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import androidx.appcompat.app.AlertDialog;
import com.havoc.config.center.C1715R;

public class PreferredSimDialogFragment extends SimDialogFragment implements DialogInterface.OnClickListener {
    public int getMetricsCategory() {
        return 1709;
    }

    public static PreferredSimDialogFragment newInstance() {
        PreferredSimDialogFragment preferredSimDialogFragment = new PreferredSimDialogFragment();
        preferredSimDialogFragment.setArguments(SimDialogFragment.initArguments(3, C1715R.string.sim_preferred_title));
        return preferredSimDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getTitleResId());
        builder.setPositiveButton((int) C1715R.string.yes, (DialogInterface.OnClickListener) this);
        builder.setNegativeButton((int) C1715R.string.f93no, (DialogInterface.OnClickListener) null);
        AlertDialog create = builder.create();
        updateDialog(create);
        return create;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            SimDialogActivity simDialogActivity = (SimDialogActivity) getActivity();
            SubscriptionInfo preferredSubscription = getPreferredSubscription();
            if (preferredSubscription != null) {
                simDialogActivity.onSubscriptionSelected(getDialogType(), preferredSubscription.getSubscriptionId());
            }
        }
    }

    public SubscriptionInfo getPreferredSubscription() {
        return getSubscriptionManager().getActiveSubscriptionInfoForSimSlotIndex(getActivity().getIntent().getIntExtra(SimDialogActivity.PREFERRED_SIM, -1));
    }

    private void updateDialog(AlertDialog alertDialog) {
        SubscriptionInfo preferredSubscription = getPreferredSubscription();
        if (preferredSubscription == null) {
            dismiss();
            return;
        }
        alertDialog.setMessage(getContext().getString(C1715R.string.sim_preferred_message, new Object[]{preferredSubscription.getDisplayName()}));
    }

    public void updateDialog() {
        updateDialog((AlertDialog) getDialog());
    }

    /* access modifiers changed from: protected */
    public SubscriptionManager getSubscriptionManager() {
        return (SubscriptionManager) getContext().getSystemService(SubscriptionManager.class);
    }
}
