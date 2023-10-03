package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.e */
class C1255e implements View.OnLayoutChangeListener {
    final /* synthetic */ AttachmentPreview this$0;

    C1255e(AttachmentPreview attachmentPreview) {
        this.this$0 = attachmentPreview;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.this$0.post(new C1246d(this));
    }
}
