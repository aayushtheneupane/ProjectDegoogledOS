package com.android.messaging.p041ui;

import android.view.View;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.i */
class C1263i implements View.OnClickListener {

    /* renamed from: kF */
    final /* synthetic */ C1063Q f1982kF;

    /* renamed from: lF */
    final /* synthetic */ MessagePartData f1983lF;

    C1263i(C1063Q q, MessagePartData messagePartData) {
        this.f1982kF = q;
        this.f1983lF = messagePartData;
    }

    public void onClick(View view) {
        this.f1982kF.mo6870a(this.f1983lF, C1486ya.m3858h(view), false);
    }
}
