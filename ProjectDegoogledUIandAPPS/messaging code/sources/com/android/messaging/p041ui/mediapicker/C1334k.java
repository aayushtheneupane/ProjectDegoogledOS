package com.android.messaging.p041ui.mediapicker;

import android.hardware.Camera;

/* renamed from: com.android.messaging.ui.mediapicker.k */
class C1334k implements Camera.PictureCallback {

    /* renamed from: SH */
    final /* synthetic */ float f2124SH;
    final /* synthetic */ C1352t this$0;
    final /* synthetic */ C1360x val$callback;

    C1334k(C1352t tVar, C1360x xVar, float f) {
        this.this$0 = tVar;
        this.val$callback = xVar;
        this.f2124SH = f;
    }

    public void onPictureTaken(byte[] bArr, Camera camera) {
        int i;
        int i2;
        boolean unused = this.this$0.f2163gI = false;
        if (this.this$0.f2162fI != camera) {
            this.val$callback.mo7967La(1);
        } else if (bArr == null) {
            this.val$callback.mo7967La(2);
        } else {
            Camera.Size pictureSize = camera.getParameters().getPictureSize();
            if (this.this$0.f2149Ak == 90 || this.this$0.f2149Ak == 270) {
                i2 = pictureSize.height;
                i = pictureSize.width;
            } else {
                i2 = pictureSize.width;
                i = pictureSize.height;
            }
            new C1297Z(i2, i, this.f2124SH, bArr, this.this$0.f2157aI.getContext(), this.val$callback).mo8233b(new Void[0]);
        }
    }
}
