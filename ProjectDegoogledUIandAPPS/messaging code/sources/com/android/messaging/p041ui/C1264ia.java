package com.android.messaging.p041ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.ia */
class C1264ia extends DialogFragment {
    private C1264ia() {
    }

    public static C1264ia newInstance() {
        return new C1264ia();
    }

    public void onCancel(DialogInterface dialogInterface) {
        ((C1272ma) getTargetFragment()).getActivity().finish();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BugleThemeDialog);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.sms_storage_low_warning_dialog, (ViewGroup) null);
        ((ListView) inflate.findViewById(R.id.free_storage_action_list)).setAdapter(new C1262ha(this, getActivity(), C1272ma.m3187a(getActivity().getResources())));
        builder.setTitle(R.string.sms_storage_low_title).setView(inflate).setNegativeButton(R.string.ignore, new C1258fa(this));
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }
}
