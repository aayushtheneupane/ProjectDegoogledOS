package com.android.messaging.p041ui;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: com.android.messaging.ui.wa */
class C1388wa implements View.OnTouchListener {
    final /* synthetic */ C1038Da this$0;

    C1388wa(C1038Da da) {
        this.this$0 = da;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.this$0.dismiss();
        return false;
    }
}
