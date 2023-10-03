package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.hardware.Camera;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;

/* renamed from: com.android.messaging.ui.mediapicker.HardwareCameraPreview */
public class HardwareCameraPreview extends TextureView implements C1277E {
    /* access modifiers changed from: private */

    /* renamed from: We */
    public C1278F f2025We = new C1278F(this);

    public HardwareCameraPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSurfaceTextureListener(new C1296Y(this));
    }

    /* renamed from: ca */
    public void mo7687ca() {
        this.f2025We.mo7693ca();
    }

    public View getView() {
        return this;
    }

    public boolean isValid() {
        return getSurfaceTexture() != null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f2025We.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2025We.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int y = this.f2025We.mo7701y(i, i2);
        super.onMeasure(y, this.f2025We.mo7700x(y, i2));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        this.f2025We.mo7699vj();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.f2025We.mo7690Ma(i);
    }

    /* renamed from: a */
    public void mo7686a(Camera camera) {
        camera.setPreviewTexture(getSurfaceTexture());
    }
}
