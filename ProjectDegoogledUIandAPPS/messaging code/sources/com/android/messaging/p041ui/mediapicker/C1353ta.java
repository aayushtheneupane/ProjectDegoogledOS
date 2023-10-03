package com.android.messaging.p041ui.mediapicker;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: com.android.messaging.ui.mediapicker.ta */
class C1353ta extends Animation {

    /* renamed from: ll */
    final /* synthetic */ int f2167ll;

    /* renamed from: ml */
    final /* synthetic */ int f2168ml;
    final /* synthetic */ MediaPickerPanel this$0;

    C1353ta(MediaPickerPanel mediaPickerPanel, int i, int i2) {
        this.this$0 = mediaPickerPanel;
        this.f2167ll = i;
        this.f2168ml = i2;
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f, Transformation transformation) {
        int unused = this.this$0.f2033Cj = (int) ((((float) this.f2168ml) * f) + ((float) this.f2167ll));
        this.this$0.requestLayout();
    }

    public boolean willChangeBounds() {
        return true;
    }
}
