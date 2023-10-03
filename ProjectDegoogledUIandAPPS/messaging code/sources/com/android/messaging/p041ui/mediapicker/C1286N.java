package com.android.messaging.p041ui.mediapicker;

import android.view.View;

/* renamed from: com.android.messaging.ui.mediapicker.N */
class C1286N implements View.OnClickListener {
    final /* synthetic */ GalleryGridItemView this$0;

    C1286N(GalleryGridItemView galleryGridItemView) {
        this.this$0 = galleryGridItemView;
    }

    public void onClick(View view) {
        C1290S a = this.this$0.mHostInterface;
        GalleryGridItemView galleryGridItemView = this.this$0;
        a.mo7708a(galleryGridItemView, galleryGridItemView.mData, false);
    }
}
