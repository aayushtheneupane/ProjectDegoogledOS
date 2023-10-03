package com.android.messaging.p041ui;

import android.content.DialogInterface;
import com.android.messaging.sms.C1025u;

/* renamed from: com.android.messaging.ui.ja */
class C1266ja implements DialogInterface.OnClickListener {

    /* renamed from: gG */
    final /* synthetic */ int f1986gG;
    final /* synthetic */ C1270la this$0;

    C1266ja(C1270la laVar, int i) {
        this.this$0 = laVar;
        this.f1986gG = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.dismiss();
        C1270la.m3186a(this.this$0, this.f1986gG);
        this.this$0.getActivity().finish();
        C1025u.m2391Di();
    }
}
