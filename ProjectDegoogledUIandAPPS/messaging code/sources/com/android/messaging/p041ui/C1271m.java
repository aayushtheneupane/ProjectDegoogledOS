package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.m */
class C1271m implements View.OnClickListener {
    final /* synthetic */ AudioAttachmentView this$0;

    C1271m(AudioAttachmentView audioAttachmentView) {
        this.this$0 = audioAttachmentView;
    }

    public void onClick(View view) {
        if (this.this$0.f1581ch == null || !this.this$0.f1589kh) {
            if (this.this$0.f1587ih) {
                boolean unused = this.this$0.f1587ih = false;
            } else {
                boolean unused2 = this.this$0.f1587ih = true;
                this.this$0.m2479Wm();
            }
        } else if (this.this$0.f1581ch.isPlaying()) {
            this.this$0.f1581ch.pause();
            this.this$0.mChronometer.pause();
            this.this$0.mProgressBar.pause();
        } else {
            AudioAttachmentView.m2492e(this.this$0);
        }
        this.this$0.m2480Xm();
    }
}
