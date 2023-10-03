package com.android.messaging.p041ui;

import android.view.View;
import com.android.messaging.datamodel.data.C0909V;

/* renamed from: com.android.messaging.ui.aa */
class C1084aa implements View.OnClickListener {
    final /* synthetic */ PersonItemView this$0;

    C1084aa(PersonItemView personItemView) {
        this.this$0 = personItemView;
    }

    public void onClick(View view) {
        if (this.this$0.mListener != null && this.this$0.mBinding.isBound()) {
            this.this$0.mListener.mo7224a((C0909V) this.this$0.mBinding.getData());
        }
    }
}
