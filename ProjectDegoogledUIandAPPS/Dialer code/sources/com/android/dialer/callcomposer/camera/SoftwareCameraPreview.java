package com.android.dialer.callcomposer.camera;

import android.content.Context;
import android.hardware.Camera;
import android.os.Parcelable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.android.dialer.callcomposer.camera.CameraPreview;
import java.io.IOException;

public class SoftwareCameraPreview extends SurfaceView implements CameraPreview.CameraPreviewHost {
    /* access modifiers changed from: private */
    public final CameraPreview preview = new CameraPreview(this);

    public SoftwareCameraPreview(Context context) {
        super(context);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                CameraManager.get().setSurface(SoftwareCameraPreview.this.preview);
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                CameraManager.get().setSurface(SoftwareCameraPreview.this.preview);
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                CameraManager.get().setSurface((CameraPreview) null);
            }
        });
    }

    public View getView() {
        return this;
    }

    public boolean isValid() {
        return getHolder() != null;
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
        camera.setPreviewDisplay(getHolder());
    }
}
