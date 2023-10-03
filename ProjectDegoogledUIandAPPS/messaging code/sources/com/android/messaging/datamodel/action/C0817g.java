package com.android.messaging.datamodel.action;

import android.widget.Toast;
import com.android.messaging.C0967f;

/* renamed from: com.android.messaging.datamodel.action.g */
class C0817g implements Runnable {

    /* renamed from: Xy */
    final /* synthetic */ String f1086Xy;

    C0817g(String str) {
        this.f1086Xy = str;
    }

    public void run() {
        Toast.makeText(C0967f.get().getApplicationContext(), this.f1086Xy, 1).show();
    }
}
