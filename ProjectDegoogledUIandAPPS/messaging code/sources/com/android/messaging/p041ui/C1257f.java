package com.android.messaging.p041ui;

/* renamed from: com.android.messaging.ui.f */
class C1257f implements Runnable {
    final /* synthetic */ AttachmentPreview this$0;

    C1257f(AttachmentPreview attachmentPreview) {
        this.this$0 = attachmentPreview;
    }

    public void run() {
        if (!this.this$0.f1575hf) {
            AttachmentPreview.m2469c(this.this$0);
            this.this$0.f1571df.removeAllViews();
            this.this$0.setVisibility(8);
        }
    }
}
