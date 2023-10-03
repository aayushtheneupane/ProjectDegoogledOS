package com.android.messaging.p041ui.mediapicker;

import android.view.View;

/* renamed from: com.android.messaging.ui.mediapicker.O */
class C1287O implements View.OnLongClickListener {
    final /* synthetic */ GalleryGridItemView this$0;

    C1287O(GalleryGridItemView galleryGridItemView) {
        this.this$0 = galleryGridItemView;
    }

    public boolean onLongClick(View view) {
        this.this$0.mHostInterface.mo7708a(view, this.this$0.mData, true);
        return true;
    }
}
