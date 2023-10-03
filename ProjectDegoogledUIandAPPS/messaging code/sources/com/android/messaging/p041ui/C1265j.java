package com.android.messaging.p041ui;

import android.view.View;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.j */
class C1265j implements View.OnLongClickListener {

    /* renamed from: kF */
    final /* synthetic */ C1063Q f1984kF;

    /* renamed from: lF */
    final /* synthetic */ MessagePartData f1985lF;

    C1265j(C1063Q q, MessagePartData messagePartData) {
        this.f1984kF = q;
        this.f1985lF = messagePartData;
    }

    public boolean onLongClick(View view) {
        return this.f1984kF.mo6870a(this.f1985lF, C1486ya.m3858h(view), true);
    }
}
