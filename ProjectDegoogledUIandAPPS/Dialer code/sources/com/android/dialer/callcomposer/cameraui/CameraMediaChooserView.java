package com.android.dialer.callcomposer.cameraui;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.dialer.R;
import com.android.dialer.callcomposer.camera.CameraManager;
import com.android.dialer.callcomposer.camera.HardwareCameraPreview;
import com.android.dialer.callcomposer.camera.SoftwareCameraPreview;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class CameraMediaChooserView extends FrameLayout {
    private boolean isSoftwareFallbackActive;

    public CameraMediaChooserView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!canvas.isHardwareAccelerated() && !this.isSoftwareFallbackActive) {
            this.isSoftwareFallbackActive = true;
            post(new Runnable() {
                public void run() {
                    HardwareCameraPreview hardwareCameraPreview = (HardwareCameraPreview) CameraMediaChooserView.this.findViewById(R.id.camera_preview);
                    if (hardwareCameraPreview != null) {
                        ViewGroup viewGroup = (ViewGroup) hardwareCameraPreview.getParent();
                        int indexOfChild = viewGroup.indexOfChild(hardwareCameraPreview);
                        SoftwareCameraPreview softwareCameraPreview = new SoftwareCameraPreview(CameraMediaChooserView.this.getContext());
                        viewGroup.removeView(hardwareCameraPreview);
                        viewGroup.addView(softwareCameraPreview, indexOfChild);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            int i = bundle.getInt("camera_index");
            super.onRestoreInstanceState(bundle.getParcelable("super"));
            LogUtil.m9i("CameraMediaChooserView.onRestoreInstanceState", GeneratedOutlineSupport.outline5("restoring camera index:", i), new Object[0]);
            if (i != -1) {
                CameraManager.get().selectCameraByIndex(i);
            } else {
                resetState();
            }
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", super.onSaveInstanceState());
        int cameraIndex = CameraManager.get().getCameraIndex();
        LogUtil.m9i("CameraMediaChooserView.onSaveInstanceState", GeneratedOutlineSupport.outline5("saving camera index:", cameraIndex), new Object[0]);
        bundle.putInt("camera_index", cameraIndex);
        return bundle;
    }

    public void resetState() {
        CameraManager.get().selectCamera(0);
    }
}
