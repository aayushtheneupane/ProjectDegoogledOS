package com.android.messaging.p041ui.mediapicker;

import android.view.View;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.mediapicker.ra */
class C1349ra implements View.OnLayoutChangeListener {
    final /* synthetic */ MediaPickerPanel this$0;

    /* renamed from: vI */
    private boolean f2144vI = C1486ya.m3859ok();

    C1349ra(MediaPickerPanel mediaPickerPanel) {
        this.this$0 = mediaPickerPanel;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        boolean ok = C1486ya.m3859ok();
        if (this.f2144vI != ok) {
            this.f2144vI = ok;
            if (this.this$0.f2032Bj) {
                MediaPickerPanel mediaPickerPanel = this.this$0;
                mediaPickerPanel.m3260a(mediaPickerPanel.f2032Bj, false, this.this$0.f2038lb.getCurrentItem(), true);
            }
        }
    }
}
