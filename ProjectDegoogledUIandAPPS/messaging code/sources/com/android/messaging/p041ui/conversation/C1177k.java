package com.android.messaging.p041ui.conversation;

import android.view.View;

/* renamed from: com.android.messaging.ui.conversation.k */
class C1177k implements View.OnLongClickListener {
    final /* synthetic */ ComposeMessageView this$0;

    C1177k(ComposeMessageView composeMessageView) {
        this.this$0 = composeMessageView;
    }

    public boolean onLongClick(View view) {
        this.this$0.m2771va(this.this$0.f1775Jh.mo7465a(true, this.this$0.m2751_m()));
        if (this.this$0.mHost.mo7379D()) {
            ComposeMessageView.m2769m(this.this$0);
        }
        return true;
    }
}
