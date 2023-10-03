package com.android.messaging.p041ui.conversation;

import android.view.View;

/* renamed from: com.android.messaging.ui.conversation.e */
class C1165e implements View.OnFocusChangeListener {
    final /* synthetic */ ComposeMessageView this$0;

    C1165e(ComposeMessageView composeMessageView) {
        this.this$0 = composeMessageView;
    }

    public void onFocusChange(View view, boolean z) {
        if (view == this.this$0.f1778xh && z) {
            this.this$0.mHost.mo7382Q();
        }
    }
}
