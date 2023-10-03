package com.android.messaging.datamodel.action;

import android.widget.Toast;
import com.android.messaging.C0967f;

/* renamed from: com.android.messaging.datamodel.action.f */
class C0816f implements Runnable {

    /* renamed from: Wy */
    final /* synthetic */ int f1085Wy;

    C0816f(int i) {
        this.f1085Wy = i;
    }

    public void run() {
        Toast.makeText(C0967f.get().getApplicationContext(), C0967f.get().getApplicationContext().getString(this.f1085Wy), 1).show();
    }
}
