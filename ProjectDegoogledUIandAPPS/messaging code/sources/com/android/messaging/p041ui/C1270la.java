package com.android.messaging.p041ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.android.messaging.R;
import com.android.messaging.datamodel.action.HandleLowStorageAction;
import com.android.messaging.sms.C1002A;
import com.android.messaging.sms.C1030z;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.la */
class C1270la extends DialogFragment {

    /* renamed from: G */
    private String f1988G;
    private C1030z mDuration;

    private C1270la() {
    }

    /* renamed from: a */
    static /* synthetic */ void m3186a(C1270la laVar, int i) {
        long a = C1002A.m2324a(laVar.mDuration);
        if (i == 0) {
            HandleLowStorageAction.m1373j(a);
        } else if (i != 1) {
            C1424b.fail("Unsupported action");
        } else {
            HandleLowStorageAction.m1374k(a);
        }
    }

    public static C1270la newInstance(int i) {
        C1270la laVar = new C1270la();
        Bundle bundle = new Bundle();
        bundle.putInt("action_index", i);
        laVar.setArguments(bundle);
        return laVar;
    }

    public void onCancel(DialogInterface dialogInterface) {
        ((C1272ma) getTargetFragment()).getActivity().finish();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String str;
        this.mDuration = C1002A.m2323Mi();
        this.f1988G = C1002A.m2325b(this.mDuration);
        int i = getArguments().getInt("action_index");
        if (i < 0 || i > 1) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog.Builder title = builder.setTitle(R.string.sms_storage_low_title);
        if (i == 0) {
            str = getString(R.string.delete_all_media_confirmation, new Object[]{this.f1988G});
        } else if (i == 1) {
            str = getString(R.string.delete_oldest_messages_confirmation, new Object[]{this.f1988G});
        } else if (i == 2) {
            str = getString(R.string.auto_delete_oldest_messages_confirmation, new Object[]{this.f1988G});
        } else {
            throw new IllegalArgumentException("SmsStorageLowWarningFragment: invalid action index " + i);
        }
        title.setMessage(str).setNegativeButton(17039360, new C1268ka(this)).setPositiveButton(17039370, new C1266ja(this, i));
        return builder.create();
    }
}
