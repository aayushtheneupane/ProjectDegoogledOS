package com.android.messaging.p041ui.conversation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import com.android.messaging.R;
import com.android.messaging.datamodel.ParticipantRefresh;
import com.android.messaging.util.C1451h;

/* renamed from: com.android.messaging.ui.conversation.ua */
public class C1198ua extends DialogFragment {
    /* access modifiers changed from: private */

    /* renamed from: H */
    public EditText f1892H;
    private int mSubId;

    public static C1198ua newInstance(int i) {
        C1198ua uaVar = new C1198ua();
        uaVar.mSubId = i;
        return uaVar;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        this.f1892H = (EditText) LayoutInflater.from(activity).inflate(R.layout.enter_phone_number_view, (ViewGroup) null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.BugleThemeDialog);
        builder.setTitle(R.string.enter_phone_number_title).setMessage(R.string.enter_phone_number_text).setView(this.f1892H).setNegativeButton(17039360, new C1196ta(this)).setPositiveButton(17039370, new C1194sa(this));
        return builder.create();
    }

    /* renamed from: a */
    static /* synthetic */ void m3039a(C1198ua uaVar, String str) {
        C1451h.m3725ha(uaVar.mSubId).putString(uaVar.getString(R.string.mms_phone_number_pref_key), str);
        ParticipantRefresh.m1288pe();
    }
}
