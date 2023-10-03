package com.android.messaging.p041ui.debug;

import android.content.Context;
import android.content.DialogInterface;
import android.view.inputmethod.InputMethodManager;

/* renamed from: com.android.messaging.ui.debug.c */
class C1250c implements DialogInterface.OnShowListener {
    final /* synthetic */ DebugMmsConfigItemView this$0;
    final /* synthetic */ Context val$context;

    C1250c(DebugMmsConfigItemView debugMmsConfigItemView, Context context) {
        this.this$0 = debugMmsConfigItemView;
        this.val$context = context;
    }

    public void onShow(DialogInterface dialogInterface) {
        this.this$0.f1970H.requestFocus();
        this.this$0.f1970H.selectAll();
        ((InputMethodManager) this.val$context.getSystemService("input_method")).toggleSoftInput(1, 0);
    }
}
