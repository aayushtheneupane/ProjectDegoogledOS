package com.android.dialer.callcomposer.camera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import com.android.dialer.callcomposer.camera.CameraPreview;
import java.io.IOException;

public class HardwareCameraPreview extends TextureView implements CameraPreview.CameraPreviewHost {
    /* access modifiers changed from: private */
    public CameraPreview preview = new CameraPreview(this);

    public HardwareCameraPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                CameraManager.get().setSurface(HardwareCameraPreview.this.preview);
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                CameraManager.get().setSurface((CameraPreview) null);
                return true;
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                CameraManager.get().setSurface(HardwareCameraPreview.this.preview);
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                CameraManager.get().setSurface(HardwareCameraPreview.this.preview);
            }
        });
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
        this.preview.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.preview.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = this.preview.getWidthMeasureSpec(i, i2);
        super.onMeasure(widthMeasureSpec, this.preview.getHeightMeasureSpec(widthMeasureSpec, i2));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        this.preview.onRestoreInstanceState();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.preview.onVisibilityChanged(i);
    }

    public void setShown() {
        this.preview.setShown();
    }

    public void startPreview(Camera camera) throws IOException {
        camera.setPreviewTexture(getSurfaceTexture());
    }
}
