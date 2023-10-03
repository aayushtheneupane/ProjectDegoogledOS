package com.android.messaging.p041ui.mediapicker;

import android.graphics.Rect;
import android.view.View;

/* renamed from: com.android.messaging.ui.mediapicker.Q */
class C1289Q implements View.OnLayoutChangeListener {
    final /* synthetic */ GalleryGridItemView this$0;

    C1289Q(GalleryGridItemView galleryGridItemView) {
        this.this$0 = galleryGridItemView;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Rect rect = new Rect(0, 0, this.this$0.getWidth(), this.this$0.getHeight());
        GalleryGridItemView galleryGridItemView = this.this$0;
        galleryGridItemView.setTouchDelegate(new C1288P(this, rect, galleryGridItemView.f2013Df));
    }
}
