package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.Na */
class C1058Na implements View.OnLongClickListener {
    final /* synthetic */ VideoThumbnailView this$0;

    C1058Na(VideoThumbnailView videoThumbnailView) {
        this.this$0 = videoThumbnailView;
    }

    public boolean onLongClick(View view) {
        this.this$0.performLongClick();
        return true;
    }
}
