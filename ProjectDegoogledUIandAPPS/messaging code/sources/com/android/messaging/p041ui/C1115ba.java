package com.android.messaging.p041ui;

import android.view.View;
import com.android.messaging.datamodel.data.C0909V;

/* renamed from: com.android.messaging.ui.ba */
class C1115ba implements View.OnLongClickListener {
    final /* synthetic */ PersonItemView this$0;

    C1115ba(PersonItemView personItemView) {
        this.this$0 = personItemView;
    }

    public boolean onLongClick(View view) {
        if (this.this$0.mListener == null || !this.this$0.mBinding.isBound()) {
            return false;
        }
        return this.this$0.mListener.mo7225c((C0909V) this.this$0.mBinding.getData());
    }
}
