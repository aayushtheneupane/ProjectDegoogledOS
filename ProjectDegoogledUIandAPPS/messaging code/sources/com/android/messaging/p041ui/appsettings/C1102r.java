package com.android.messaging.p041ui.appsettings;

import android.content.Context;
import android.view.View;
import com.android.messaging.datamodel.data.C0913Z;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.appsettings.r */
class C1102r implements View.OnClickListener {

    /* renamed from: Im */
    final /* synthetic */ C0913Z f1756Im;
    final /* synthetic */ C1103s this$1;

    C1102r(C1103s sVar, C0913Z z) {
        this.this$1 = sVar;
        this.f1756Im = z;
    }

    public void onClick(View view) {
        int type = this.f1756Im.getType();
        if (type == 1) {
            C1040Ea.get().mo6962a((Context) this.this$1.this$0.getActivity(), false);
        } else if (type != 2) {
            C1424b.fail("unrecognized setting type!");
        } else {
            C1040Ea.get().mo6956a((Context) this.this$1.this$0.getActivity(), this.f1756Im.getSubId(), this.f1756Im.mo6389Ch());
        }
    }
}
