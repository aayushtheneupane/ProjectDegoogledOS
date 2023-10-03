package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.na */
class C1367na implements View.OnClickListener {
    final /* synthetic */ C1380sa this$0;

    C1367na(C1380sa saVar) {
        this.this$0 = saVar;
    }

    public void onClick(View view) {
        this.this$0.mAction.mo7985Yi().run();
        if (this.this$0.mListener != null) {
            ((C1390xa) this.this$0.mListener).this$0.dismiss();
        }
    }
}
