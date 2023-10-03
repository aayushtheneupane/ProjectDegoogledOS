package com.android.messaging.p041ui.mediapicker;

import com.android.messaging.datamodel.data.MessagePartData;

/* renamed from: com.android.messaging.ui.mediapicker.ka */
class C1335ka implements Runnable {

    /* renamed from: Im */
    final /* synthetic */ MessagePartData f2125Im;
    final /* synthetic */ C1345pa this$0;

    C1335ka(C1345pa paVar, MessagePartData messagePartData) {
        this.this$0 = paVar;
        this.f2125Im = messagePartData;
    }

    public void run() {
        this.this$0.mListener.mo7455c(this.f2125Im);
    }
}
