package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.d */
class C1246d implements Runnable {
    final /* synthetic */ C1255e this$1;

    C1246d(C1255e eVar) {
        this.this$1 = eVar;
    }

    public void run() {
        int childCount = this.this$1.this$0.getChildCount();
        if (childCount > 0) {
            View childAt = this.this$1.this$0.getChildAt(childCount - 1);
            AttachmentPreview attachmentPreview = this.this$1.this$0;
            attachmentPreview.scrollTo(attachmentPreview.getScrollX(), childAt.getBottom() - this.this$1.this$0.getHeight());
        }
    }
}
