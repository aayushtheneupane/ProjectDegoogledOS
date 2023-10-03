package com.android.messaging.p041ui.attachmentchooser;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.attachmentchooser.e */
class C1109e implements View.OnLayoutChangeListener {
    final /* synthetic */ AttachmentGridItemView this$0;

    C1109e(AttachmentGridItemView attachmentGridItemView) {
        this.this$0 = attachmentGridItemView;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int dimensionPixelOffset = this.this$0.getResources().getDimensionPixelOffset(R.dimen.attachment_grid_checkbox_area_increase);
        Rect rect = new Rect();
        this.this$0.f1759Df.getHitRect(rect);
        int i9 = -dimensionPixelOffset;
        rect.inset(i9, i9);
        AttachmentGridItemView attachmentGridItemView = this.this$0;
        attachmentGridItemView.setTouchDelegate(new TouchDelegate(rect, attachmentGridItemView.f1759Df));
    }
}
