package com.android.messaging.p041ui;

import android.content.DialogInterface;

/* renamed from: com.android.messaging.ui.ka */
class C1268ka implements DialogInterface.OnClickListener {
    final /* synthetic */ C1270la this$0;

    C1268ka(C1270la laVar) {
        this.this$0 = laVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.dismiss();
        ((C1272ma) this.this$0.getTargetFragment()).getActivity().finish();
    }
}
