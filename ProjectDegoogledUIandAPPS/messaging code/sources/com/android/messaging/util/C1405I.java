package com.android.messaging.util;

import android.app.Activity;
import com.android.messaging.p041ui.C1040Ea;

/* renamed from: com.android.messaging.util.I */
class C1405I extends C1407K {

    /* renamed from: XJ */
    final /* synthetic */ Activity f2210XJ;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1405I(String str, Activity activity) {
        super(str);
        this.f2210XJ = activity;
    }

    public void run() {
        C1040Ea.get().mo6988y(this.f2210XJ);
    }
}
