package com.android.messaging.p041ui.appsettings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1451h;

/* renamed from: com.android.messaging.ui.appsettings.n */
public class C1098n {
    private final Context mContext;
    private AlertDialog mDialog;
    private final int mSubId;

    private C1098n(Context context, int i) {
        this.mContext = context;
        this.mSubId = i;
    }

    /* renamed from: a */
    static /* synthetic */ void m2718a(C1098n nVar, boolean z) {
        C1424b.m3594t(nVar.mDialog);
        C1451h.m3725ha(nVar.mSubId).putBoolean(nVar.mContext.getString(R.string.group_mms_pref_key), z);
        nVar.mDialog.dismiss();
    }

    /* renamed from: f */
    public static void m2719f(Context context, int i) {
        C1098n nVar = new C1098n(context, i);
        C1424b.isNull(nVar.mDialog);
        AlertDialog.Builder builder = new AlertDialog.Builder(nVar.mContext, R.style.BugleThemeDialog);
        View inflate = ((LayoutInflater) nVar.mContext.getSystemService("layout_inflater")).inflate(R.layout.group_mms_setting_dialog, (ViewGroup) null, false);
        RadioButton radioButton = (RadioButton) inflate.findViewById(R.id.disable_group_mms_button);
        RadioButton radioButton2 = (RadioButton) inflate.findViewById(R.id.enable_group_mms_button);
        radioButton.setOnClickListener(new C1096l(nVar));
        radioButton2.setOnClickListener(new C1097m(nVar));
        boolean z = C1451h.m3725ha(nVar.mSubId).getBoolean(nVar.mContext.getString(R.string.group_mms_pref_key), nVar.mContext.getResources().getBoolean(R.bool.group_mms_pref_default));
        radioButton2.setChecked(z);
        radioButton.setChecked(!z);
        nVar.mDialog = builder.setView(inflate).setTitle(R.string.group_mms_pref_title).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
    }
}
