package com.android.messaging.p041ui.conversation;

import android.content.DialogInterface;
import com.android.messaging.datamodel.data.C0931n;
import com.android.messaging.datamodel.p037a.C0784d;

/* renamed from: com.android.messaging.ui.conversation.u */
class C1197u implements DialogInterface.OnClickListener {

    /* renamed from: QG */
    final /* synthetic */ String f1891QG;
    final /* synthetic */ C1146O this$0;

    C1197u(C1146O o, String str) {
        this.this$0 = o;
        this.f1891QG = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ((C0931n) this.this$0.mBinding.getData()).mo6470a((C0784d) this.this$0.mBinding, this.f1891QG);
    }
}
