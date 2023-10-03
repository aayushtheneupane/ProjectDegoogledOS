package com.android.dialer.callcomposer.camera;

import android.content.Context;
import android.hardware.Camera;
import android.view.View;
import com.android.dialer.common.Assert;
import com.android.dialer.util.PermissionsUtil;
import java.io.IOException;

public class CameraPreview {
    private int cameraHeight = -1;
    private int cameraWidth = -1;
    private final CameraPreviewHost host;
    private View.OnTouchListener listener;
    private boolean tabHasBeenShown = false;

    public interface CameraPreviewHost {
        View getView();

        boolean isValid();

        void setShown();

        void startPreview(Camera camera) throws IOException;
    }

    public CameraPreview(CameraPreviewHost cameraPreviewHost) {
        Assert.isNotNull(cameraPreviewHost);
        Assert.isNotNull(cameraPreviewHost.getView());
        this.host = cameraPreviewHost;
    }

    private void maybeOpenCamera() {
        boolean z = this.host.getView().getVisibility() == 0;
        if (this.tabHasBeenShown && z && PermissionsUtil.hasCameraPermissions(getContext())) {
            CameraManager.get().openCamera();
        }
    }

    public Context getContext() {
        return this.host.getView().getContext();
    }

    public int getHeightMeasureSpec(int i, int i2) {
        if (this.cameraHeight < 0) {
            return i2;
        }
        int i3 = getContext().getResources().getConfiguration().orientation;
        int size = View.MeasureSpec.getSize(i);
        float f = ((float) this.cameraWidth) / ((float) this.cameraHeight);
        return View.MeasureSpec.makeMeasureSpec((int) (i3 == 2 ? ((float) size) * f : ((float) size) / f), 1073741824);
    }

    public int getWidthMeasureSpec(int i, int i2) {
        return this.cameraHeight >= 0 ? View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i), 1073741824) : i;
    }

    public boolean isValid() {
        return this.host.isValid();
    }

    public void onAttachedToWindow() {
        maybeOpenCamera();
    }

    public void onDetachedFromWindow() {
        CameraManager.get().closeCamera();
    }

    public void onRestoreInstanceState() {
        maybeOpenCamera();
    }

    public void onVisibilityChanged(int i) {
        if (!PermissionsUtil.hasCameraPermissions(getContext())) {
            return;
        }
        if (i == 0) {
            maybeOpenCamera();
        } else {
            CameraManager.get().closeCamera();
        }
    }

    public void setFocusable(boolean z) {
        this.host.getView().setOnTouchListener(z ? this.listener : null);
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.listener = onTouchListener;
        this.host.getView().setOnTouchListener(onTouchListener);
    }

    public void setShown() {
        this.tabHasBeenShown = true;
        maybeOpenCamera();
    }

    public void setSize(Camera.Size size, int i) {
        if (i == 0 || i == 180) {
            this.cameraWidth = size.width;
            this.cameraHeight = size.height;
        } else {
            this.cameraWidth = size.height;
            this.cameraHeight = size.width;
        }
        this.host.getView().requestLayout();
    }

    public void startPreview(Camera camera) throws IOException {
        this.host.startPreview(camera);
    }
}
