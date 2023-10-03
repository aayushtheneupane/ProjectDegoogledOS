package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.ga */
class C1260ga implements View.OnClickListener {

    /* renamed from: fG */
    final /* synthetic */ int f1981fG;
    final /* synthetic */ C1262ha this$1;

    C1260ga(C1262ha haVar, int i) {
        this.this$1 = haVar;
        this.f1981fG = i;
    }

    public void onClick(View view) {
        this.this$1.this$0.dismiss();
        C1272ma.m3189a((C1272ma) this.this$1.this$0.getTargetFragment(), this.f1981fG);
    }
}
