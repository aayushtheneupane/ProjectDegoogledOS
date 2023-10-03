package com.android.messaging.p041ui.attachmentchooser;

import android.view.View;

/* renamed from: com.android.messaging.ui.attachmentchooser.d */
class C1108d implements View.OnClickListener {
    final /* synthetic */ AttachmentGridItemView this$0;

    C1108d(AttachmentGridItemView attachmentGridItemView) {
        this.this$0 = attachmentGridItemView;
    }

    public void onClick(View view) {
        C1110f a = this.this$0.mHostInterface;
        AttachmentGridItemView attachmentGridItemView = this.this$0;
        a.mo7209a(attachmentGridItemView, attachmentGridItemView.mAttachmentData);
    }
}
