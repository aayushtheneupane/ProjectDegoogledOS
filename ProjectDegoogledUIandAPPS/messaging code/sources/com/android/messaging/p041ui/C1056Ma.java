package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.Ma */
class C1056Ma implements View.OnClickListener {
    final /* synthetic */ VideoThumbnailView this$0;

    C1056Ma(VideoThumbnailView videoThumbnailView) {
        this.this$0 = videoThumbnailView;
    }

    public void onClick(View view) {
        if (this.this$0.f1682Af != null) {
            if (this.this$0.mMode == 1) {
                this.this$0.f1687wf.seekTo(0);
                this.this$0.start();
                return;
            }
            C1040Ea.get().mo6972d(this.this$0.getContext(), this.this$0.f1682Af);
        }
    }
}
