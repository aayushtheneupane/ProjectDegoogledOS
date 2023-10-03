package com.android.messaging.p041ui.conversation;

import android.view.View;
import com.android.messaging.datamodel.data.C0889A;

/* renamed from: com.android.messaging.ui.conversation.i */
class C1173i implements View.OnClickListener {
    final /* synthetic */ ComposeMessageView this$0;

    C1173i(ComposeMessageView composeMessageView) {
        this.this$0 = composeMessageView;
    }

    public void onClick(View view) {
        ComposeMessageView.m2758d(this.this$0);
        this.this$0.f1779yh.setText((CharSequence) null);
        ((C0889A) this.this$0.mBinding.getData()).mo6171Z((String) null);
    }
}
