package com.android.messaging.p041ui.mediapicker;

import android.graphics.SurfaceTexture;
import android.view.TextureView;

/* renamed from: com.android.messaging.ui.mediapicker.Y */
class C1296Y implements TextureView.SurfaceTextureListener {
    final /* synthetic */ HardwareCameraPreview this$0;

    C1296Y(HardwareCameraPreview hardwareCameraPreview) {
        this.this$0 = hardwareCameraPreview;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        C1352t.get().mo7935a(this.this$0.f2025We);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        C1352t.get().mo7935a((C1278F) null);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        C1352t.get().mo7935a(this.this$0.f2025We);
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        C1352t.get().mo7935a(this.this$0.f2025We);
    }
}
