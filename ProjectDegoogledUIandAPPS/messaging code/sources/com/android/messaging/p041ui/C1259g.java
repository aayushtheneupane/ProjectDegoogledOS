package com.android.messaging.p041ui;

import java.util.List;

/* renamed from: com.android.messaging.ui.g */
class C1259g implements Runnable {

    /* renamed from: iF */
    final /* synthetic */ List f1979iF;

    /* renamed from: jF */
    final /* synthetic */ List f1980jF;
    final /* synthetic */ AttachmentPreview this$0;

    C1259g(AttachmentPreview attachmentPreview, List list, List list2) {
        this.this$0 = attachmentPreview;
        this.f1979iF = list;
        this.f1980jF = list2;
    }

    public void run() {
        Runnable unused = this.this$0.mHideRunnable = null;
        if (this.f1980jF.size() + this.f1979iF.size() == 0) {
            this.this$0.mo6867Gb();
        }
    }
}
