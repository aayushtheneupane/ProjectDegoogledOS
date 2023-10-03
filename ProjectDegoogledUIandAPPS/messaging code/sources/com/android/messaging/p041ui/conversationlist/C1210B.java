package com.android.messaging.p041ui.conversationlist;

import android.content.DialogInterface;

/* renamed from: com.android.messaging.ui.conversationlist.B */
class C1210B implements DialogInterface.OnClickListener {
    final /* synthetic */ ShareIntentFragment this$0;

    C1210B(ShareIntentFragment shareIntentFragment) {
        this.this$0 = shareIntentFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        boolean unused = this.this$0.mDismissed = true;
        this.this$0.mHost.mo7108Z();
    }
}
